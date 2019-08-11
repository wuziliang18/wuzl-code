package org.wuzl.test.overloading;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * 结果都是未知的类型
 * 重载是静态的 编译时候就选择好了  
 * 所有少用或者不用重载
 * 重载的时候可以所有实现一样
 * @author wuzl
 *
 */
public class TestOverloadingError {

	public void test(List<?> list) {
		System.out.println("list");
	}

	public void test(Set<?> set) {
		System.out.println("set类型");
	}

	public void test(Collection<?> collection) {
		System.out.println("未知的类型");
	}
	public static void main(String[] args) {
		Collection<?>[] collections={
			new HashMap<String,String>().values(),
			new HashSet<String>(),
			new ArrayList<String>()
		};
		TestOverloadingError test=new TestOverloadingError();
		for(Collection<?> c:collections){
			test.test(c);
		}
	}
}
