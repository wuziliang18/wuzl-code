package com.yoloho.datasource.split;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.yoloho.datasource.split.utils.Assert;
import com.yoloho.datasource.split.vo.BaseDataSourceConfBean;
import com.yoloho.datasource.split.vo.DataSourceConfBean;

/**
 * 切分初始化配置 辅助的功能 如果应该到的数据可以在各自的static里动态记载就不需要这里的操作配置
 * 
 * @author wuzl
 * 
 */
public class SplitDatasourceInit {
	private static final Log log = LogFactory.getLog(SplitDatasourceInit.class);
	private String dataSourceStrategyJson;// 数据源策略json
	private String tableStrategyJson;// 表策略策略json
	private String dataSourceJson;// 数据源json
	private String dataSourceBaseConfJson;// 数据源基本配置json
	private String tableNameServiceJson;// 表名与servicename对应的关系配置json

	// private String tableDataSourceMapJson;//表名与数据源的对应关系
	//
	// public void setTableDataSourceMapJson(String tableDataSourceMapJson) {
	// this.tableDataSourceMapJson = tableDataSourceMapJson;
	// }

	public void setTableNameServiceJson(String tableNameServiceJson) {
		this.tableNameServiceJson = tableNameServiceJson;
	}

	public void setDataSourceJson(String dataSourceJson) {
		this.dataSourceJson = dataSourceJson;
	}

	public void setDataSourceBaseConfJson(String dataSourceBaseConfJson) {
		this.dataSourceBaseConfJson = dataSourceBaseConfJson;
	}

	public void setTableStrategyJson(String tableStrategyJson) {
		this.tableStrategyJson = tableStrategyJson;
	}

	public void setDataSourceStrategyJson(String dataSourceStrategyJson) {
		this.dataSourceStrategyJson = dataSourceStrategyJson;
	}

	@SuppressWarnings("unchecked")
	public void init() {
		// // 初始化表和库的对应
		// Assert.notEmpty(tableDataSourceMapJson,
		// "表与数据库的对应关系tableDataSourceMapJson没有配置");
		// try {
		// SimpleDataSourceStrategyContext
		// .setTableDataSourceMap((Map<String, String>) JSON
		// .parseObject(tableDataSourceMapJson, Map.class));
		// } catch (JSONException e) {
		// throw new
		// RuntimeException("表与数据库的对应关系tableDataSourceMapJson配置不对，不是正确的json类型");
		// }
		// 初始化数据源的策略
		Assert.notEmpty(dataSourceStrategyJson,
				"数据源的策略dataSourceStrategyJson没有配置");
		try {
			DataSourceStrategyContext.putStrategy((Map<String, String>) JSON
					.parseObject(dataSourceStrategyJson, Map.class));
			log.info("加载数据源的策略成功");
		} catch (JSONException e) {
			throw new RuntimeException(
					"数据源的策略dataSourceStrategyJson配置不对，不是正确的json类型");
		}
		// 初始化表的策略
		Assert.notEmpty(tableStrategyJson, "表的策略tableStrategyJson没有配置");
		try {
			TableStrategyContext.putStrategy((Map<String, String>) JSON
					.parseObject(tableStrategyJson, Map.class));
			log.info("加载表的策略成功");
		} catch (JSONException e) {
			throw new RuntimeException("表的策略tableStrategyJson配置不对，不是正确的json类型");
		}
		// 初始化默认配置
		Assert.notEmpty(dataSourceBaseConfJson,
				"数据源基本配置dataSourceBaseConfJson没有配置");
		try {
			DataSourceContext.setBaseConf(JSON.parseObject(
					dataSourceBaseConfJson, BaseDataSourceConfBean.class));
			log.info("加载数据源的基本配置成功");
		} catch (Exception e) {
			throw new RuntimeException(
					"数据源基本配置dataSourceBaseConfJson配置不对，不是正确的json类型");
		}
		// 初始化数据源 必须在初始化默认之后
		Assert.notEmpty(dataSourceJson, "数据源dataSourceJson没有配置");
		try {
			List<DataSourceConfBean> rows = JSON.parseArray(dataSourceJson,
					DataSourceConfBean.class);
			DataSourceContext.addDataSource(rows);
			log.info(String.format("加载%d个数据源的配置成功", rows.size()));
		} catch (Exception e) {
			throw new RuntimeException("数据源dataSourceJson配置不对，不是正确的json数组类型");
		}
		// 初始化表名与servicename对应
		if (tableNameServiceJson != null && !tableNameServiceJson.equals("")) {
			try {
				DebugContext.putTableNameForService((Map<String, String>) JSON
						.parseObject(tableNameServiceJson, Map.class));
				log.info("加载表名与servicename的对应配置成功");
			} catch (Exception e) {
				log.info(String.format("加载表名与servicename的对应配置失败：%s",
						e.getMessage()));
			}
		}
	}
}
