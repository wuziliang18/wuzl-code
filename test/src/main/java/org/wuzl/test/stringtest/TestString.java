package org.wuzl.test.stringtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestString {
	public static void main(String[] args) {
	    System.out.println("А".equals("A"));
	    String subject1 = "HCL-176327a-444";
        int index = subject1.lastIndexOf("-");
        String subjectCommonCode2 = subject1.substring(0, index);
        System.out.println(subjectCommonCode2);
	    String s="吧abc"+"123"+"poi";
	    String s2="吧abc123poi";
	    System.out.println(s==s2);
//		String price = "1235";
//		System.out.println(price.substring(0, price.indexOf(".")) + "00");
//		String uids = "234234,12321,40";
//		System.out.println(uids.length());
//		if (uids.endsWith(",")) {
//			uids = uids.substring(0, uids.length() - 1);
//		}
//		List<String> rows = new ArrayList<String>();
//		rows.add("234234");
//		rows.add("40");
//		rows.add("12321");
//		String[] uidArray = uids.split(",");
//		List<String> lostUidList = new ArrayList<String>(Arrays.asList(uidArray));
//		lostUidList.removeAll(rows);
//		System.out.println(lostUidList);
//		System.out.println(uids);
//		String s = "Hello world";
//		System.out.println(s.substring(1, 9));
//		String s="安慰安慰安慰儿玩命{{<img>}}{{<img>}}<br/><br/><br/><br/><br/>图文混排的链接<a herf=\"www.qq.com\">马化腾</a>";
//		System.out.println(s.replace("{{<img>}}", ""));
//		System.out.println("sadf<br/>safsd<br/>11sa<br/>dfas".replace("<br/>", "\r\n"));
//		System.out.println("sadf\"".replace("\"", ""));
//		String s = "hello %s";
//		String user = "admin";
//		System.out.println(String.format(s, user));
//		System.out.println(Byte.MAX_VALUE);
//		System.out.println(1300 + 500 + 200);
//		String[] array = { "ad", "sadf" };
//		System.out.println(toKey(array));
	}

	public static String toKey(String[] array) {
		int iMax = array.length - 1;

		StringBuilder b = new StringBuilder();
		for (int i = 0;; i++) {
			b.append(String.valueOf(array[i]));
			if (i == iMax)
				return b.toString();
			b.append(", ");
		}
	}
}
