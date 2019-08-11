package org.wuzl.test.io.file;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.regex.Pattern;

public class TestFileReName {
	public static void main(String[] args) {
		try {
			File dir=new File(".");
			System.out.println(dir.getAbsoluteFile().getParent());
			String filePath=dir.getAbsoluteFile().getParent();
			File oldFile=new File(filePath+"/test.txt");
			if(!oldFile.exists()){
				oldFile.createNewFile();
			}
			File newFile=new File(filePath+"/test2.txt");
			oldFile.renameTo(newFile);//老的移动为新的 新的如果有会被覆盖
			System.out.println(oldFile.exists());
			System.out.println(newFile.exists());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
