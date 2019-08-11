package org.datasource.split;

import com.yoloho.datasource.split.DataSourceStrategyContext;

public class TestDataSourceStrategyContext {
	public static void main(String[] args) {
		try {
			System.out.println(DataSourceStrategyContext.getDataSourceName("user", "uid",1l));
			System.out.println(DataSourceStrategyContext.getDataSourceName("user", "uid",2l));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println(DataSourceStrategyContext.getDataSourceName("user1", "uid", 1l));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
