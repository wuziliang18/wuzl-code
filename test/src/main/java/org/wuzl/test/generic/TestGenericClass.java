package org.wuzl.test.generic;
/**
 * 价值在于参数类型随子类型发生变化
 * @author wuzl
 *
 */
public class TestGenericClass {
	static void  test(Getter getter){
		Getter ge1=getter.get();
		GenecicGetter ge2=getter.get();
	}
	public static void main(String[] args) {
		
	}
}
interface GenecicGetter<T>{
	T get();
}
interface Getter extends GenecicGetter<Getter>{
	
}