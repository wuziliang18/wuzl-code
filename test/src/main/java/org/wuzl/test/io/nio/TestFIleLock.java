package org.wuzl.test.io.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import org.wuzl.test.io.file.GetCurrentDir;
/**
 * 可以阻塞 
 * 应该是依赖系统底层 
 * 锁的是文件 不是对象或者通道 所以第一个线程锁上 第二个也可以获取锁 
 * @author wuzl
 *
 */
public class TestFIleLock {
	public static void main(String[] args) {
		final File file=GetCurrentDir.getFileCreate("org.wuzl.test.io.nio","lock.txt");
		try {
			FileOutputStream fileOutputStream=new FileOutputStream(file);
//			FileLock lock=fileOutputStream.getChannel().tryLock();
			final FileChannel fileChanel=new FileOutputStream(file).getChannel();
			FileLock lock=fileOutputStream.getChannel().lock();
			final ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
			byteBuffer.put("asdfasdf".getBytes());
			byteBuffer.flip();
			if(lock!=null){
				new Thread(){
					public void run() {
						try {
							System.out.println("》》");
//							fileChanel.tryLock();//会报错 已经重复锁了
							fileChanel.write(byteBuffer);
							System.out.println("可以写入 不用获取锁");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					};
				}.start();
				System.out.println("已经锁上");
				Thread.sleep(100000l);
				lock.release();
				System.out.println("已经释放锁");
			}
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
