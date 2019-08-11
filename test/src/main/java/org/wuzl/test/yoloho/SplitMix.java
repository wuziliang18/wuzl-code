package org.wuzl.test.yoloho;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitMix {
	public static void main(String[] args) {
		String regex="(\\{\\{<[^}]*>\\}\\}){0,1}[^{]*";
//		String regex="\\{\\{<button>\\}\\}";
		String content="{{<button>}}{{<button>}}1{{<img>}}asdfsafd{{<video>}}asdfsadfasdfasdf12222{{<button>}}2343{{<vote>}}24444{{<vote1>}}22{{<vote1>}}";
		System.out.println(content.replaceFirst(regex, ">>>>>>"));
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(content);
		while(matcher.find()){
			String contentPart=matcher.group();
			String tag=matcher.group(1);
			if(contentPart!=null&&!"".equals(contentPart)){
				if(tag!=null&&!tag.equals("")){
					System.out.println(contentPart);
					int tagEndIndex=contentPart.lastIndexOf("}");
					if(tagEndIndex!=contentPart.length()-1){
						System.out.println(contentPart.substring(tagEndIndex+1));
					}
				}
			}
		}
		System.out.println(content.replaceAll("\\{\\{<[^}]*>\\}\\}", ""));
	}
}
