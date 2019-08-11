package org.wuzl.test.yoloho;

public class RemoveBbcode {
	public static void main(String[] args) {
		String s="哈哈改好[[url=dayima://broadcastroom/new?id=56]]查看详情[[/url]]";
		System.out.println(s.replaceAll("\\[\\[url=.*\\]\\].*\\[\\[/url\\]\\]", ""));
	}
}
