package org.wuzl.test.concurrent;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试Synchronized中yield是否会让出资源 yield与sleep不会让出 但时间片会让开 可以在同步代码中测试同步!! wait可以释放锁
 * 但需要唤醒
 * 
 * @author wuzl
 *
 */
public class TestSynchronizedAndYield {
	public static void main(String[] args) {
		final Task task = new Task();
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					task.task();
				}
			});
		}
	}
}

class Task {
	Random ran = new Random();

	public void task() {
		long begin = System.currentTimeMillis();
		int random = ran.nextInt(10);
		try {
			TimeUnit.MILLISECONDS.sleep(random * 200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("进入方法");
		synchronized (this) {
			while (true) {
				long end = System.currentTimeMillis();
				if ((end - begin) > 100 * random) {
					begin = System.currentTimeMillis();
					System.out.println(Thread.currentThread().getName() + "运行"
							+ random + "后尝试让出");
					random = ran.nextInt(10);
					Thread.currentThread().yield();// 不会让出 但不是无效的操作 线程会让出 但锁依然在
					// try {
					// wait();//有效 但需要唤醒
					// } catch (InterruptedException e) {
					// e.printStackTrace();
					// }
					// break;
				}

			}
		}
	}
}
