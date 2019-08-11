package org.wuzl.test.io.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TestReadFileContent {
	private final static String FILE_SEPARATOR = System.getProperty("line.separator");

	public static void main(String[] args) {
		String fileName = "D:/wuzl/data/weibo.txt";
		StringBuilder content = new StringBuilder();
		File file = new File(fileName);
		if (!file.exists()) {
			throw new RuntimeException("文件【" + fileName + "】不存在!");
		}
		FileReader fr = null;
		BufferedReader br = null;
		int i=0;
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String data = br.readLine();
			while (data != null) {
				content.append(data + FILE_SEPARATOR);
				data = br.readLine(); // 接着读下一行
				if(i++>=1000){
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		System.out.println(content);
	}
}
