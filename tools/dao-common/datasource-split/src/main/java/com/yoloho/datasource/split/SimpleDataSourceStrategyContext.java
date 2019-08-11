package com.yoloho.datasource.split;

import java.util.Map;
/**
 * 简单的获取数据源名称 过于繁琐
 * @author wuzl
 *
 */
public class SimpleDataSourceStrategyContext {
	private static Map<String, String> tableDataSourceMap;

	public static void setTableDataSourceMap(Map<String, String> tableDataSourceMap) {
		SimpleDataSourceStrategyContext.tableDataSourceMap = tableDataSourceMap;
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
		if (tableDataSourceMap == null) {
			throw new RuntimeException("表与数据库的对应关系没有配置");
		}
		String realTableName = TableStrategyContext.getRealTableName(tableName,
				splitField, splitFieldValue);
		String number = realTableName.substring(realTableName.indexOf("_") + 1,
				realTableName.length());
		return tableDataSourceMap.get(number);
	}
}
