package org.wuzl.test.io.file;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DeleteDdf {
    public static void main(String[] args) {
        String input = "/Users/ziliang.wu/Documents/教程/47-Java性能调优实战";
        File targe = new File(input);
        if (!targe.exists()) {
            throw new RuntimeException(input + " 文件夹不存在");
        }
        LinkedList<File> list = new LinkedList<>();
        list.add(targe);
        while (!list.isEmpty()) {
            File folder = list.poll();
            List<String> delNames = new ArrayList<>();
            Map<String, File> map = new HashMap<>();
            for (File child : folder.listFiles()) {
                if (child.isDirectory()) {
                    list.add(child);
                    continue;
                }
                String fileName = child.getName();
                if (fileName.endsWith(".pdf")) {
                    map.put(fileName.substring(0, fileName.length() - 4), child);
                } else if (fileName.endsWith(".html")) {
                    delNames.add(fileName.substring(0, fileName.length() - 5));
                }
            }
            for (String del : delNames) {
                File delFile = map.get(del);
                if (delFile != null) {
                    System.out.println(delFile);
                    delFile.delete();
                }
            }
        }

    }
}
