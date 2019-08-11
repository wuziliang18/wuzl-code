package org.wuzl.test.long_;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.zookeeper.proto.GetACLRequest;

public class TestLong {
	public static void main(String[] args) {
		Map<Long, String> BROADCAST_SIGN_KEY_MAP = new ConcurrentHashMap<Long, String>();// 签名key保存
		BROADCAST_SIGN_KEY_MAP.put(new Long(1), "2");
		System.out.println(BROADCAST_SIGN_KEY_MAP.get(new Long(1)));
		System.out.println(BROADCAST_SIGN_KEY_MAP.containsKey(new Long(1)));
		System.out.println(1024*1024*30);
		System.out.println((31457280-12582912)/1024/1024);
		Long l1=new Long(100);
		Long l2=new Long(100);
		Long l3=new Long(101);
		System.out.println(l1==l2);
		System.out.println(l1==100l);
		System.out.println(l1.equals(l2));
		System.out.println(l3>l2);
		System.out.println(l3<l2);
		List<String> rows=new ArrayList<String>();
		rows.add("12312");
		rows.add("123112d2");
		rows.add("3");
		rows.add("aa");
		Long uid=12312l;
		System.out.println(rows.contains(uid+""));
	}
}
