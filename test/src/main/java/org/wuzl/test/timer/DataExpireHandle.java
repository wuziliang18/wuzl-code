package org.wuzl.test.timer;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DataExpireHandle<T> {
	private ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(1);
	private Map<String, T> dataMap = new ConcurrentHashMap<>();
	private Map<String, Long> dataKeyMap = new ConcurrentHashMap<>();
	// 最后一个等待
	private final Long expire;// 超时时间 毫秒级
	private final DataExpireListener<String, T> listener;
	private final int deviationParam; // 误差参数 值越大执行时间可能越准确
										// 最大错误率为1/deviationParam

	public DataExpireHandle(Long expire, DataExpireListener<String, T> listener) {
		this(expire, listener, 3);
	}

	/**
	 * 
	 * @param expire
	 *            过期时间毫秒级
	 * @param listener
	 *            监听器
	 * @param deviationParam
	 *            误差参数 值越大执行时间可能越准确 最大错误率为1/deviationParam
	 */
	public DataExpireHandle(Long expire, DataExpireListener<String, T> listener, int deviationParam) {
		this.expire = expire;
		this.listener = listener;
		this.deviationParam = deviationParam;
		executorService.scheduleWithFixedDelay(new Thread() {
			@Override
			public void run() {
				// 检查超时数据
				checkExpireData();
			}
		}, expire / deviationParam, expire / deviationParam, TimeUnit.MILLISECONDS);
	}

	/**
	 * 检查超时数据
	 */
	private void checkExpireData() {
		long endTime = System.currentTimeMillis() - expire;
		Set<Entry<String, Long>> entrySet = dataKeyMap.entrySet();
		for (Entry<String, Long> entry : entrySet) {
			if (entry.getValue() <= endTime) {
				dataKeyMap.remove(entry.getKey());
				T param = dataMap.remove(entry.getKey());
				if (param != null) {
					listener.onExpire(entry.getKey(), param);
				}
			}
		}
	}

	/**
	 * 插入数据
	 * 
	 * @param key
	 * @param value
	 */
	public void putData(String key, T value) {
		dataMap.put(key, value);
		dataKeyMap.put(key, System.currentTimeMillis());
	}

	/**
	 * 删除数据
	 * 
	 * @param key
	 */
	public void remove(String key) {
		dataKeyMap.remove(key);
		dataMap.remove(key);
	}

	public Long getExpire() {
		return expire;
	}

	public DataExpireListener<String, T> getListener() {
		return listener;
	}

	public int getDeviationParam() {
		return deviationParam;
	}

	public void close() {
		executorService.shutdown();
	}

	/**
	 * 超时处理接口
	 * 
	 * @author ziliang.wu
	 *
	 * @param <T>
	 * @param <O>
	 */
	public static interface DataExpireListener<T, O> {

		public void onExpire(T key, O value);
	}
}
