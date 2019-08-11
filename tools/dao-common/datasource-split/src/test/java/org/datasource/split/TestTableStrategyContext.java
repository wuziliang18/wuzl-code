package org.datasource.split;

import com.yoloho.datasource.split.TableStrategyContext;

public class TestTableStrategyContext {
	public static void main(String[] args) {
		System.out.println(TableStrategyContext.getRealTableName("user", "uid", 1l));
		System.out.println(TableStrategyContext.getRealTableName("user", "uid", 2l));
		System.out.println(TableStrategyContext.getRealTableName("user", "uid", 1024l));
		String realTableName=TableStrategyContext.getRealTableName("user", "uid", 1l);
		System.out.println(TableStrategyContext.getRealTableSuffix("user", "uid", 1l));
		System.out.println(realTableName.substring(realTableName.indexOf("_")+1, realTableName.length()));
	}
}
