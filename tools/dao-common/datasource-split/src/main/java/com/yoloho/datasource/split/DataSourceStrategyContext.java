package com.yoloho.datasource.split;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.yoloho.datasource.split.utils.Assert;
import com.yoloho.spring.util.SpElUtil;

/**
 * 数据源策略
 * 
 * @author wuzl
 * 
 */
public class DataSourceStrategyContext {
	private static final Map<String, String> DATA_SOURCE_SPLIT_STRATEGY_MAP = new ConcurrentHashMap<String, String>();// 数据源切分策略
	public static final String DEFAULT_STRATEGY_KEY = "default";//默认的规则

	static {
		// 可以动态加载规则
	}
	/**
	 * 加入新的策略
	 * @param map
	 */
	public static void putStrategy(Map<String, String> map){
		DATA_SOURCE_SPLIT_STRATEGY_MAP.putAll(map);
	}
	/**
	 * 获取数据源名称
	 * 
	 * @param tableName
	 *            表名称
	 * @param splitField
	 *            分表字段
	 * @param splitFieldValue
	 *            分表字段值
	 * @return
	 */
	public static final String getDataSourceName(String tableName,
			String splitField, Object splitFieldValue) {
		Assert.notNull(splitFieldValue, "分表字段值不可以为空");
		String strategy = getStrategy(tableName);
		Assert.notEmpty(strategy, String.format("没有table【%s】和默认的分库策略", tableName));
		strategy = strategy
				.replace(splitField, String.valueOf(splitFieldValue));// 替换el表达式中的值
		Object dateSourceName = SpElUtil.compute(strategy, true);
		return String.valueOf(dateSourceName);
	}

	private static final String getStrategy(String tableName) {
		String strategy = DATA_SOURCE_SPLIT_STRATEGY_MAP.get(tableName);
		if (strategy == null) {
			strategy = DATA_SOURCE_SPLIT_STRATEGY_MAP.get(DEFAULT_STRATEGY_KEY);
		}
		return strategy;
	}
}
