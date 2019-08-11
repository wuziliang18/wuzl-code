package org.wuzl.test.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestPrivateMap {
	//是不安全的
	private final ConcurrentHashMap<String, String> channels = new ConcurrentHashMap<String, String>(); // <ip:port,
																							// channel>

	public Map<String, String> getChannels() {
		return channels;
	}
	public void put(String key,String value){
		System.out.println(channels);
		channels.putIfAbsent(key, value);
	}
}
