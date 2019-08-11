package com.yoloho.datasource.split;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.yoloho.datasource.split.utils.Assert;
import com.yoloho.spring.util.SpElUtil;

/**
 * 表策略 初期简单 后期可以做成动态的 static中动态加载分表策略
 * 
 * @author wuzl
 * 
 */
public class TableStrategyContext {
	private static final Map<String, String> TABLE_SPLIT_STRATEGY_MAP = new ConcurrentHashMap<String, String>();// 表切分策略
	public static final String DEFAULT_TABLE_STRATEGY_KEY = "default";
	static {
		TABLE_SPLIT_STRATEGY_MAP.put(DEFAULT_TABLE_STRATEGY_KEY,
				"'_'+(uid%1024)");
	}

	/**
	 * 加入新的策略
	 * 
	 * @param map
	 */
	public static void putStrategy(Map<String, String> map) {
		TABLE_SPLIT_STRATEGY_MAP.putAll(map);
	}

	/**
	 * 获取分表名称
	 * 
	 * @param tableName
	 * @param splitField
	 * @param splitFieldValue
	 * @return
	 */
	public static final String getRealTableName(String tableName,
			String splitField, Object splitFieldValue) {
		Assert.notNull(splitFieldValue, "分表字段值不可以为空");
		return tableName
				+ getRealTableSuffix(tableName, splitField, splitFieldValue);
	}

	/**
	 * 获取后缀
	 * 
	 * @param tableName
	 * @param splitField
	 * @param splitFieldValue
	 * @return
	 */
	public static final String getRealTableSuffix(String tableName,
			String splitField, Object splitFieldValue) {
		Assert.notNull(splitFieldValue, "分表字段值不可以为空");
		String strategy = TABLE_SPLIT_STRATEGY_MAP.get(tableName);
		if (strategy == null) {
			strategy = TABLE_SPLIT_STRATEGY_MAP.get(DEFAULT_TABLE_STRATEGY_KEY);
		}
		Assert.notEmpty(strategy, String.format("没有table【%s】的分库策略", tableName));
		strategy = strategy
				.replace(splitField, String.valueOf(splitFieldValue));// 替换el表达式中的值
		Object suffix = SpElUtil.compute(strategy, true);
		return String.valueOf(suffix);
	}

}
