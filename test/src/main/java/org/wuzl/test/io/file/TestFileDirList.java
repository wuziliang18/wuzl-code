package org.wuzl.test.io.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * 打印目录下所有复合条件的文件名称
 * @author wuzl
 *
 */
public class TestFileDirList {
	public static void main(String[] args) {
		File dir=new File(".");
		String[] files= dir.list(new FilenameFilter() {
			private Pattern pattern=Pattern.compile(".*\\.xml");//只要.xml结尾的
			@Override
			public boolean accept(File dir, String name) {
				return pattern.matcher(name).matches();
			}
		});
		for(String file:files){
			System.out.println(file);
		}
	}
}
