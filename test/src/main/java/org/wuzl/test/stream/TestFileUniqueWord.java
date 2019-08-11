package org.wuzl.test.stream;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 统计文件中多少不重复单词
 * 
 * @author ziliang.wu
 *
 */
public class TestFileUniqueWord {
	public static void main(String[] args) {
		String file = "D:/wuzl/shell/english.txt";
		try (Stream<String> lines = Files.lines(Paths.get(file))) {
			// Stream<String[]> arrayStream = lines.map(line ->
			// Arrays.stream(line.split(" ")));// 语法错误
			// Stream<String[]> arrayStream = lines.map(line -> line.split("
			// "));//使用map会生成stream数组
			// arrayStream.forEach(words ->
			// System.out.println(Arrays.toString(words)));

			// Stream<String[]> arrayStream = lines.flatMap(line ->
			// line.split(""));// 语法错误必须要求是流

			long count = lines.flatMap(line -> Arrays.stream(line.split(" "))).distinct().count();
			System.out.println("不重复的单词总数:" + count);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
