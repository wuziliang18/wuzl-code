package org.wuzl.test.concurrent;
/**
 * 后台进程不会运行finally
 * @author wuzl
 *
 */
public class TestDaemon {
	public static void main(String[] args) {
		DaemonThread thread=new DaemonThread();
		thread.setDaemon(true);
		thread.start();
	}
}
class DaemonThread extends Thread{
	@Override
	public void run() {
		try {
			System.out.println("start");//这一段也不一定会打印 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			System.out.println("finally方法不会进入");
		}
	}
}
