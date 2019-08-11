package com.yoloho.datasource.split.utils;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.yoloho.datasource.split.vo.BaseDataSourceConfBean;
import com.yoloho.datasource.split.vo.DataSourceConfBean;
/**
 * 数据源操作
 * @author wuzl
 *
 */
public class DataSourceUtil {
	/**
	 * 生成DruidDataSource数据源 
	 * 
	 * @param dataSourceConf
	 * @param baseConfig
	 * @param staratInit
	 *            是否创建后立即初始化
	 * @return
	 * @throws Exception
	 */
	//TODO 此处应该作为子工程去引入 最好使用SPI
	public static DataSource createDataSource(
			DataSourceConfBean dataSourceConf,
			BaseDataSourceConfBean baseConfig, boolean staratInit)
			throws Exception {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(dataSourceConf.getDriverClassName());
		dataSource.setUsername(dataSourceConf.getUserName());
		dataSource.setPassword(dataSourceConf.getPassword());
		dataSource.setUrl(dataSourceConf.getUrl());
		dataSource.setInitialSize(baseConfig.getInitialSize());
		dataSource.setMinIdle(baseConfig.getMinIdle());
		dataSource.setMaxActive(baseConfig.getMaxActive());
		dataSource.setMaxWait(baseConfig.getMaxWait());
		dataSource.setTimeBetweenEvictionRunsMillis(baseConfig
				.getTimeBetweenEvictionRunsMillis());
		dataSource.setMinEvictableIdleTimeMillis(baseConfig
				.getMinEvictableIdleTimeMillis());
		dataSource.setValidationQuery(baseConfig.getValidationQuery());
		dataSource.setTestWhileIdle(baseConfig.isTestWhileIdle());
		dataSource.setTestOnBorrow(baseConfig.isTestOnBorrow());
		dataSource.setTestOnReturn(baseConfig.isTestOnReturn());
		dataSource.setPoolPreparedStatements(baseConfig
				.isPoolPreparedStatements());
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(baseConfig
				.getMaxPoolPreparedStatementPerConnectionSize());
		dataSource.setFilters(baseConfig.getFilters());
		if (staratInit) {
			dataSource.init();
		}
		return dataSource;
	}

	/**
	 * 创建一个连接，并立即初始化连接
	 * 
	 * @param dataSourceConf
	 * @param baseConfig
	 * @return
	 * @throws Exception
	 */
	public static DataSource createDataSource(
			DataSourceConfBean dataSourceConf, BaseDataSourceConfBean baseConfig)
			throws Exception {
		return createDataSource(dataSourceConf, baseConfig, true);
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param dataSource
	 */
	public static void closeDataSource(DataSource dataSource) {
		DruidDataSource druidDataSource = (DruidDataSource) dataSource;
		druidDataSource.close();
	}

}
