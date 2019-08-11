package org.wuzl.test.concurrent;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 1可以使用同步 wait 和notify notifyAll
2可以使用lock condition 与1类似
3使用阻塞队列 BlackingQueue
4使用管道io
使用阻塞队列比较好
 * @author wuzl
 *
 */
public class TestThreadsPie {
	public static void main(String[] args) throws IOException {
		ExecutorService service=Executors.newCachedThreadPool();
		Sender sender=new Sender();
		Receive receive=new Receive(sender);//这段必须放在启动线程前 以为 管道的输入输出要连在一起 否则两个都无法工作
//		Receive receive2=new Receive(sender);//不可以会报错  只能连接一个  局限性很大
		service.execute(sender);
		service.execute(receive);
		service.shutdown();
	}
}
class Receive implements Runnable{
	private Sender sender;
	private PipedReader reader;
	public Receive(Sender sender) throws IOException{
		this.sender=sender;
		reader=new PipedReader(sender.getWriter());
	}
	@Override
	public void run() {
		try {
			while(true){
				System.out.println("接收："+reader.read());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
class Sender implements Runnable{
	PipedWriter  writer=new PipedWriter();
	public PipedWriter getWriter() {
		return writer;
	}
	@Override
	public void run() {
		try {
			while(true){
				for(char c='A';c<='z';c++){
					System.out.println("写入"+c);
					writer.write(c);
					TimeUnit.MILLISECONDS.sleep(1000);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}