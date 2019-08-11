package org.wuzl.test.io.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 存取可以跨平台 不局限于文件 可以是任意流 但要注意顺序
 * @author wuzl
 *
 */
public class TestDataInputStream {
	public static void main(String[] args) {
		try {
			File file=new File(GetCurrentDir.getCurrentDir("org.wuzl.test.io.file")+"/Data.txt");
			if(!file.exists()){
				file.createNewFile();
			}
			DataOutputStream out=new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)) );
			DataInputStream in=new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
			out.writeDouble(12.1);
			out.writeBoolean(false);
			out.writeUTF("用来跨平台的存取");
			out.close();
			System.out.println(in.readDouble());
			System.out.println(in.readBoolean());
			System.out.println(in.readUTF());
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
