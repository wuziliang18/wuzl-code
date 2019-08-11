package org.datasource.split;

import com.yoloho.datasource.split.utils.SplitFieldUtil;

public class TestSplitFieldUtil {
	
	public static void main(String[] args) {
		//测试单个
		System.out.println(SplitFieldUtil.getSplitFieldValue(
				new Object[] { "2" }, "uid", Long.class));
		Bean bean=new Bean();
		bean.setUid(12l);
		bean.setName("asdfsa");
		bean.setMoney(12321l);
		System.out.println(SplitFieldUtil.getSplitFieldValue(
				new Object[] { bean }, "uid", Long.class));
		//测试多个参数
		System.out.println(SplitFieldUtil.getSplitFieldValue(
				new Object[] { "asdfas",123l }, "uid", Long.class));
		System.out.println(SplitFieldUtil.getSplitFieldValue(
				new Object[] { 555l,"asdfas",123l }, "uid", Long.class));
		System.out.println(SplitFieldUtil.getSplitFieldValue(
				new Object[] { 12321,"asdfas",123l }, "uid", Long.class));
		System.out.println(SplitFieldUtil.getSplitFieldValue(
				new Object[] { "asdfsadfas","asdfas",bean }, "uid", Long.class));
	}
}
