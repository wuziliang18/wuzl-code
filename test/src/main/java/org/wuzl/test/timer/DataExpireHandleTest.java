package org.wuzl.test.timer;

import java.util.Random;

public class DataExpireHandleTest {
	public static void main(String[] args) throws InterruptedException {
		DataExpireHandle<Long> handle = new DataExpireHandle<>(500l,
				new DataExpireHandle.DataExpireListener<String, Long>() {

					@Override
					public void onExpire(String key, Long value) {
						System.out.println("数据已经过期:" + "时间:" +(System.currentTimeMillis()-value));
					};
				});
		Random random = new Random();
		for (int i = 0; i < 50; i++) {
			 Thread.sleep(random.nextInt(100) * 100);
			handle.putData("数字" + i, System.currentTimeMillis());
		}
		// handle.close();
	}
}
