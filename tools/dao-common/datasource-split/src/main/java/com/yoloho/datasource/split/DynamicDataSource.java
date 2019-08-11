package com.yoloho.datasource.split;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.AbstractDataSource;

/**
 * 动态数据源
 * 
 * @author wuzl
 * 
 */
public class DynamicDataSource extends AbstractDataSource {

	private DataSource defaultDataSource;

	public void setDefaultDataSource(DataSource defaultDataSource) {
		this.defaultDataSource = defaultDataSource;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {

		return getDataSource().getConnection(username, password);
	}

	private DataSource getDataSource() {
		DataSource dataSource = DataSourceContext.getDataSourceContextHolder();
		if (dataSource != null) {
			return dataSource;
		}
		return defaultDataSource;
	}
}
