package org.wuzl.test.map;

import java.util.WeakHashMap;

public class TestWeakHashMap {
	public static void main(String[] args) {
		WeakHashMap<Element,Element> map=new WeakHashMap<Element,Element>();
		int size=50;
		Element[] keys=new Element[size];//保存key
		for(int i=0;i<size;i++){
			Element key=new Element("key"+i+"");
			if(i%3==0){// 另外去保存 就不会回收
				keys[i]=key;
			}
			map.put(key, new Element("value"+i+""));
		}
		System.gc();
		while(true){
			
		}
	}
}
class Element{
	private String id;
	public Element(String id) {
		this.id=id;
	}
	@Override
	protected void finalize() throws Throwable {
		System.out.println("回收："+getClass().getSimpleName()+"-id:"+id);
	}
	@Override
	public String toString() {
		return id;
	}
}