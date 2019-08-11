package org.wuzl.test.stringtest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUrl {
	public static void main(String[] args) {
		String url="https://testapi.yoloho.com/v1/index.php/app/iphonever";
		System.out.println(url);
		Pattern pattern=Pattern.compile("(https://([^/]+))(.*)");
		Matcher matcher=pattern.matcher(url);
		if(matcher.find()){
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println(matcher.group(3));
		}
		
	}
}
