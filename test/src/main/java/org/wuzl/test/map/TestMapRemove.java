package org.wuzl.test.map;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestMapRemove {
	public static void main(String[] args) {
		
		ConcurrentHashMap<Long, String> USER_MAP = new ConcurrentHashMap<Long, String>();
//		USER_MAP.put(new Long(1), "1");
		System.out.println(USER_MAP.putIfAbsent(new Long(1), "2"));
		System.out.println(USER_MAP.get(1l));
		Map<String,String> data=new HashMap<String,String>();
		data.put("key1", "1");
		data.put("key2", "2");
		data.put("hash", "3");
		data.put("count", "3");
		data.put("lasttime", "3");
		data.put("model", "3");
		data.put("platform", "3");
		data.put("releasever", "3");
		data.put("sdkver", "3");
		data.put("ver", "3");
		Map<String,String> attribute=new HashMap<String,String>();
		attribute.put("model", "3");
		attribute.put("platform", "3");
		attribute.put("releasever", "3");
		attribute.put("sdkver", "3");
		attribute.put("ver", "3");
		System.out.println("删除前："+data);
		data.keySet().retainAll(attribute.keySet());//交集
		System.out.println("删除后:"+data);
	}
}
