package org.wuzl.test.io.file;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        TrasformFile trasformFile = new TrasformFile();
        trasformFile.transformGbk2Utf8("/Users/ziliang.wu/Downloads/11.txt",
                "/Users/ziliang.wu/Downloads/2.txt");
    }

    public void transformGbk2Utf8(String inputFile, String outputFile) {
        try {
            List<String> list = Files.readAllLines(Paths.get(inputFile), Charset.forName("GBK"));
            Files.write(Paths.get(outputFile), list, Charset.forName("UTF-8"));
            System.out.println("转换成功");
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
}
