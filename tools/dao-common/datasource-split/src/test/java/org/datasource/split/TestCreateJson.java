package org.datasource.split;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.yoloho.datasource.split.vo.BaseDataSourceConfBean;
import com.yoloho.datasource.split.vo.DataSourceConfBean;

public class TestCreateJson {
	public static void main(String[] args) {
		Map<String, Object> dto = new HashMap<String, Object>();
		// 数据源策略
		dto.put("default", "'dataSource'+(uid%1024/256)");
		System.out.println(JSON.toJSON(dto));
		dto.clear();
		// 表策略
		dto.put("default", "'_'+(uid%1024)");
		System.out.println(JSON.toJSON(dto));
		// 生成数据源
		dto.clear();
		DataSourceConfBean confBean = new DataSourceConfBean();
		confBean.setDataSourceName("dataSource0");
		confBean.setDriverClassName("com.mysql.jdbc.Driver");
		confBean.setUrl("jdbc:mysql://dbserver:3306/dayima?useUnicode=true&characterEncoding=utf-8&autoReconnect=true");
		confBean.setUserName("kangseed");
		confBean.setPassword("JuiIo90PoiUiejOiu38Hu");
		List<DataSourceConfBean> rows = new ArrayList<DataSourceConfBean>();
		rows.add(confBean);
		System.out.println(JSON.toJSON(rows));
		// 数据库基本配置
		dto.clear();

		BaseDataSourceConfBean bean = new BaseDataSourceConfBean();
		bean.setInitialSize(20);
		bean.setMaxActive(100);
		bean.setMinIdle(20);
		bean.setMaxWait(60000);
		bean.setTimeBetweenEvictionRunsMillis(60000l);
		bean.setMinEvictableIdleTimeMillis(300000l);
		bean.setValidationQuery("select 'x'");
		bean.setTestWhileIdle(true);
		bean.setTestOnBorrow(false);
		bean.setTestOnReturn(false);
		bean.setPoolPreparedStatements(true);
		bean.setMaxPoolPreparedStatementPerConnectionSize(20);
		bean.setFilters("stat");
		System.out.println(JSON.toJSON(bean));
		
		String url="jdbc:mysql://dbserver:3306/dayima?useUnicode=true&characterEncoding=utf-8&autoReconnect=true";
		System.out.println(url.substring(0,url.indexOf("?")));
	}
}
