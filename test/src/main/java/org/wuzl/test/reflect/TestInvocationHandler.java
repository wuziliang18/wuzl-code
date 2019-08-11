package org.wuzl.test.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.wuzl.test.base.IWorkService;
import org.wuzl.test.base.impl.WorkServiceImpl;
/**
 * 用来动态代理
 * @author wuzl
 *
 */
public class TestInvocationHandler implements InvocationHandler{
	private Object obj;
	
	public TestInvocationHandler(Object obj) {
		this.obj=obj;
	}
	@Override
	public String toString() {
		return "test to String override Object";
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("开始代理"+method.getName());
		System.out.println("具体调用的类："+method.getDeclaringClass());//如果有继承且没有覆盖的方法 则打印父类的 重写后 还是打印父类的 但实际调用是子类的
		//此处可以选择如何调用 
		Object result=method.invoke(obj, args);//默认的
		if(method.getName().equals("toString")){
			result=obj.toString();//使用对象的 
		}
		System.out.println("结束代理");
		return result;
	}
	/**
	 * 生成真正的代理对象
	 * @param obj
	 * @return
	 */
	public static Object newInstance(Object obj){
		//方法的意思是说 生成一个实现第二个参数所有方法的一个对象（实际上是代理，方法调用都是TestInvocationHandler中执行）
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new TestInvocationHandler(obj));
	}
	public static void main(String[] args) {
		IWorkService work=new WorkServiceImpl();//要被代理的对象
		IWorkService proxy=(IWorkService)TestInvocationHandler.newInstance(work);
		proxy.getStatus();
		proxy.work("尝试下代理");
		System.out.println(proxy.toString());//调用的是Object的
		
	}
}
