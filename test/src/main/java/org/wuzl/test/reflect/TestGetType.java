package org.wuzl.test.reflect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
/**
 * getReturnType,getGenericReturnType方法唯一区别在于后者获取了泛型
 * @author wuzl
 *
 */
public class TestGetType {
	public static void main(String[] args) {
		Class clazz=Test.class;
		Method[] methods=clazz.getMethods();
		for(Method method:methods){
			System.out.println(method.getName()+"的type:"+method.getReturnType()+",generictype:"+method.getGenericReturnType());
		}
	}
}
class Test{
	public static String getString(){//都是string
		return "";
	}
	public static Map getMap(){//getReturnType:Map ,getGenericReturnType:Map
		Map dto=new HashMap();
		return dto;
	}
	public static Map<String,String> getGenericMap(){//getReturnType:Map ,getGenericReturnType:Map<java.lang.String, java.lang.String>
		Map<String,String> dto=new HashMap<String,String>();
		return dto;
	}
}