package org.wuzl.test.io.file;

import java.io.File;
import java.io.IOException;

public class GetCurrentDir {
	public static String getCurrentProjcetDir(){
		File dir=new File(".");
		return dir.getAbsoluteFile().getParent();
	}
	public static String getCurrentDir(String packagePath){
		return getCurrentProjcetDir()+"/src/main/java/"+packagePath.replace(".", "/");
	}
	public static File getFileCreateAfterDelete(String dirPath,String fileName){
		File file;
		try {
			file = new File(GetCurrentDir.getCurrentDir(dirPath)+"/"+fileName);
			if(!file.exists()){
				file.createNewFile();
			}else{
				file.delete();
				file.createNewFile();
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return file;
	}
	public static File getFileCreate(String dirPath,String fileName){
		File file;
		try {
			file = new File(GetCurrentDir.getCurrentDir(dirPath)+"/"+fileName);
			if(!file.exists()){
				file.createNewFile();
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return file;
	}
	public static void main(String[] args) {
		System.out.println(getCurrentDir("org.wuzl.test.nio"));
	}
}
