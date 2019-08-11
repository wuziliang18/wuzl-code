package org.wuzl.test.move;

public class TestMove {
	public static void main(String[] args) {
		//<<左移按二进制形式把所有的数字向左移动对应的位数，高位移出（舍弃），低位的空位补
		// 如果没有越界的话 相当于等于2的n次方
		System.out.println(3<<4);//48
		//>>运算规则：按二进制形式把所有的数字向右移动对应巍峨位数，低位移出（舍弃），高位的空位补符号位，即正数补零，负数补1.
		//右移一位相当于除2，右移n位相当于除以2的n次方。 多余舍去
		System.out.println(8>>2);//2
		System.out.println(11>>2);//2
		System.out.println(12>>2);//3
		System.out.println(16>>2);//4
		//>>>运算规则：按二进制形式把所有的数字向右移动对应巍峨位数，低位移出（舍弃），高位的空位补零。对于正数来说和带符号右移相同，对于负数来说不同。
		System.out.println(11>>>2);//2
		System.out.println(-11>>>2);//2
		System.out.println(-12>>>2);//3
	}
}
