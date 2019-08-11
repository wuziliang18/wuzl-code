package org.wuzl.test.pattern;

import java.util.regex.Pattern;

public class TestPattern {
	public static void main(String[] args) {
		Pattern METHODS_PATTERN = Pattern
				.compile("\n(private|public|protected)\\s+");
		String code="\npublic adfa();\nprivate String s=1;";
		String[] methods=METHODS_PATTERN.split(code);
		for(String s:methods){
			System.out.println(s);
		}
		String s="asdfasdf回复+1231aerwqerwer::";
		System.out.println(s.replaceAll("回复\\+\\d+(.*?):", "$1"));
	}
}
