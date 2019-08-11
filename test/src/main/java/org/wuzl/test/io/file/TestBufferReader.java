package org.wuzl.test.io.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class TestBufferReader {
	public static void main(String[] args) {
		FileReader reader = null;
		BufferedReader buffer = null;
		try {
			reader=new FileReader("/home/wuzl/桌面/WuzlStartService.java");
			buffer=new BufferedReader(reader);//使用buffer和不使用性能差很多
			String line;
			long begin=System.currentTimeMillis();
			while((line=buffer.readLine())!=null){
//				System.out.println(line);
			}
//			int read;
//			while(buffer.read()!=-1){//不用buffer
//				
//			}
			System.out.println(System.currentTimeMillis()-begin);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(buffer!=null){
				try {
					buffer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
