package org.wuzl.test.stringtest;
/**
 * string 相同的字符串映射到同一内存地址 所以hashcode一样
 * @author wuzl
 *
 */
public class TestStringHashCode {
	public static void main(String[] args) {
		String s1="abc";
		String s2=new String("abc");
		String s3=new String(s1);
		System.out.println(s1==s2);//false
		System.out.println(s2==s3);//false
		System.out.println(s1==s3);//false
		System.out.println(s1.hashCode());//一样
		System.out.println(s2.hashCode());//一样
		System.out.println(s3.hashCode());//一样
	}
}
