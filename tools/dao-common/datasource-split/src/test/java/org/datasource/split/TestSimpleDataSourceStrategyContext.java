package org.datasource.split;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.yoloho.datasource.split.SimpleDataSourceStrategyContext;

public class TestSimpleDataSourceStrategyContext {
	public static void main(String[] args) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("1", "dataSource1");
		SimpleDataSourceStrategyContext
		.setTableDataSourceMap((Map<String, String>) JSON.parseObject(
				JSON.toJSONString(map), Map.class));
		System.out.println(SimpleDataSourceStrategyContext.getDataSourceName("user", "uid",1l));
	}
}
