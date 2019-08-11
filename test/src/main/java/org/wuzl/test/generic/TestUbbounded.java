package org.wuzl.test.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * ?无限制的通配符 只能读 是安全的 应用于查询的方法参数中
 * 
 * @author wuzl
 * 
 */
public class TestUbbounded {
	public static void main(String[] args) {
		// List<?> rows=new ArrayList<?>();//不合法
		List<?> rows = new ArrayList<String>();// 合法
		// rows.add("");//会报错 因为无限制的通配符只能读

	}

	public static boolean checkEmpty(List<?> rows) {
		if (rows == null || rows.size() > 0) {
			return true;
		}

		return false;
	}
}
