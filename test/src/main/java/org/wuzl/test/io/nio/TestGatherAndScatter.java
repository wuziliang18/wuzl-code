package org.wuzl.test.io.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.GatheringByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.wuzl.test.io.file.GetCurrentDir;


public class TestGatherAndScatter {
	private static String[] col1={
		"1","2","3","4","5"
	};
	private static String[] col2={
		"one","two","three","for","five"
	};
//	private static String[] col3={
//		"one","two","three","for","five"
//	};
	private static String[] col3={
		"一","二","三","四","五"
	};
	private static Random random=new Random();
	private static String LINE=System.getProperty("line.separator");
	private static ByteBuffer[] utterBytes(int total){
		List<ByteBuffer> list=new ArrayList<ByteBuffer>();
		for(int i=0;i<total;i++){
			list.add(randomByteBuffer(col1, ""));
			list.add(randomByteBuffer(col2, ""));
			list.add(randomByteBuffer(col3, LINE));
		}
		ByteBuffer[] byteBuffers=new ByteBuffer[total];
		return list.toArray(byteBuffers);
	}
	private static ByteBuffer randomByteBuffer(String[] cols,String suffix){
		String str=cols[random.nextInt(cols.length)];
//		int size=str.length()+suffix.length();//不准确 只能处理英文字符
		int size=str.getBytes().length+suffix.length();
		ByteBuffer byteBuffer=ByteBuffer.allocate(size);
		byteBuffer.put(str.getBytes());
		byteBuffer.put(suffix.getBytes());
		byteBuffer.flip();//注释后没有数据
		return byteBuffer;
	}
	public static void main(String[] args) {
		try {
			int total=20;
			File file=GetCurrentDir.getFileCreate("org.wuzl.test.io.nio","channel.txt");
			GatheringByteChannel channel=new FileOutputStream(file).getChannel();
			ByteBuffer[] byteBuffers=utterBytes(total);
			while(channel.write(byteBuffers)>0){//把total改大 才会发现 循环的作用 否则只写入第一次 应该是write的时候 每次只提交一批
				System.out.println("合并写入一次");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
