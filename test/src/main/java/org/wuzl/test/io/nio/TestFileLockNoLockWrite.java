package org.wuzl.test.io.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import org.wuzl.test.io.file.GetCurrentDir;
/**
 * 测试文件锁 锁的是文件而不是对象  TestFIleLock 上锁后 该方法一样可以执行
 * 但是如果去获取锁 就会阻塞
 * @author wuzl
 *
 */
public class TestFileLockNoLockWrite {
	public static void main(String[] args) {
		try {
			ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
			byteBuffer.put("撒旦法撒旦法".getBytes());
			byteBuffer.flip();
			File file=GetCurrentDir.getFileCreate("org.wuzl.test.io.nio","lock.txt");
			FileChannel fileChanel=new FileOutputStream(file).getChannel();
//			FileLock lock=fileChanel.lock();
			fileChanel.write(byteBuffer);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
