package org.wuzl.test.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TestGetPrivateMap {
	private  Map<String, String> channels; //与TestPrivateMap共用一个 不安全
	private TestPrivateMap testPrivateMap;
	public void setTestPrivateMap(TestPrivateMap testPrivateMap) {
		this.testPrivateMap = testPrivateMap;
	}
	public Map<String, String> getChannels() {
		channels=testPrivateMap.getChannels();
		channels.put("s", "s");
		return channels;
	} 
	public static void main(String[] args) {
		TestGetPrivateMap testGetPrivateMap=new TestGetPrivateMap();
		TestPrivateMap testPrivateMap=new TestPrivateMap();
		testGetPrivateMap.setTestPrivateMap(testPrivateMap);
		System.out.println(testGetPrivateMap.getChannels());//第一次打印
		testPrivateMap.put("aa", "bb");
		System.out.println(testGetPrivateMap.getChannels());//第er次打印
	}
}
