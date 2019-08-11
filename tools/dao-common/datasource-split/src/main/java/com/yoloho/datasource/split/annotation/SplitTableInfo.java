package com.yoloho.datasource.split.annotation;

public class SplitTableInfo {
	private String tableName;
	private String splitField;
	private boolean forceSplit;
	private boolean debug;
	private String defaultTableName;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSplitField() {
		return splitField;
	}

	public void setSplitField(String splitField) {
		this.splitField = splitField;
	}

	public boolean isForceSplit() {
		return forceSplit;
	}

	public void setForceSplit(boolean forceSplit) {
		this.forceSplit = forceSplit;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getDefaultTableName() {
		return defaultTableName;
	}

	public void setDefaultTableName(String defaultTableName) {
		this.defaultTableName = defaultTableName;
	}


	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("表名：").append(tableName).append(",");
		result.append("切表字段：").append(splitField).append(",");
		result.append("是否强制所有方法动态切换数据库：").append(forceSplit).append(",");
		result.append("是否开启调试模式：").append(debug).append(",");
		result.append("默认表名称：").append(defaultTableName);
		return result.toString();
	}
}
