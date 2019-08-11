package org.wuzl.test.io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.wuzl.test.io.file.GetCurrentDir;

public class TestFileChannel {
	public static void main(String[] args) {
		try {
			File file=GetCurrentDir.getFileCreate("org.wuzl.test.nio","Data.txt");
			FileChannel fileChannel=new FileOutputStream(file).getChannel();
			fileChannel.write(ByteBuffer.wrap("测试下数据".getBytes()));
			fileChannel.close();
			fileChannel=new RandomAccessFile(file, "rw").getChannel();//可以追加 RandomAccessFile可以在任意指定位置操作
//			fileChannel=new FileOutputStream(file).getChannel();//不可以追加
			fileChannel.position(fileChannel.size());//最后
			fileChannel.write(ByteBuffer.wrap("刚才忘了再加点".getBytes()));
			fileChannel.close();
			fileChannel=new FileInputStream(file).getChannel();
			ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
			fileChannel.read(byteBuffer);
			byteBuffer.flip();
//			System.out.println(new String(byteBuffer.array()));//不会乱码
			System.out.println(byteBuffer.asCharBuffer().toString());//乱码 即使是英文字符也一样乱码 可以在写入的时候用charbuffer
			byteBuffer.rewind();
			fileChannel=new FileInputStream(file).getChannel();
			byteBuffer=ByteBuffer.allocate(1024);
			fileChannel.read(byteBuffer);
//			System.out.println(new String(byteBuffer.array()));
			byteBuffer.flip();
			System.out.println(Charset.forName("UTF-8").decode(byteBuffer));//不会乱码
			file.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
