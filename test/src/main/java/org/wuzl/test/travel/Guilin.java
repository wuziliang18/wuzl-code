package org.wuzl.test.travel;

public class Guilin {
	public static void main(String[] args) {
		int jipiao=500;
		int wopu=400;
		int gaotie=806;
		int binguan=400*4;
		System.out.println("机票"+(jipiao*2+binguan));
		System.out.println("卧铺"+(wopu*2+binguan));
		System.out.println("高铁"+(gaotie*2+binguan));
	}
}
