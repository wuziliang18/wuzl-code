package org.wuzl.test.io.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel.MapMode;

import org.wuzl.test.io.file.GetCurrentDir;

/**
 * 很方便的操作大文件
 * 速度很快
 * 似乎只能与RandomAccessFile合用
 * MappedByteBuffer只会被java回收系统回收 不会随着channel的关闭而关闭
 * @author wuzl
 *
 */
public class TestMemoryMapBigFile {
	static int length=0xfffff;// 128m
	public static void main(String[] args) {
		File file=GetCurrentDir.getFileCreate("org.wuzl.test.io.nio","big.txt");
		try {
			MappedByteBuffer mappedByteBuffer=new RandomAccessFile(file, "rw").getChannel().map(MapMode.READ_WRITE, 0, length);
			long begin=System.currentTimeMillis();
			for(int i=0;i<length/6	;i++){
				mappedByteBuffer.put("测试".getBytes());
			}
			System.out.println("写入完毕:"+(System.currentTimeMillis()-begin));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
