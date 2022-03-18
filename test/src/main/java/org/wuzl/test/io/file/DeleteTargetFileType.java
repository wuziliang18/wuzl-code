package org.wuzl.test.io.file;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteTargetFileType {
    private final String path = "D:\\教程\\117-后端存储实战课";
    private final static Set<String> deleteFileTypeSet = new HashSet<>();
    static {
        deleteFileTypeSet.add("mp3");
        deleteFileTypeSet.add("pdf");
        deleteFileTypeSet.add("m4a");
    }
    int count = 0;
    List<String> paths = new ArrayList<>();

    public void delete() {
        File file = new File(path);
        if (!file.exists()) {
            throw new RuntimeException("目录不存在" + path);
        }
        if (!file.isDirectory()) {
            throw new RuntimeException("不是目录" + path);
        }
        this.delete(file);
    }

    private void delete(File path) {
        if (this.checkDelete(path)) {
            path.delete();
            System.out.println("delete:" + path);
            return;
        }
        if (!path.isDirectory()) {
            return;
        }
        File[] files = path.listFiles();
        for (File file : files) {
            this.delete(file);
        }
    }

    private boolean checkDelete(File file) {

        if (file.isDirectory()) {
            return false;
        }
        String name = file.getName();
        int index = name.lastIndexOf(".");
        return index > 0 && deleteFileTypeSet.contains(name.substring(index + 1));
    }

    public static void main(String[] args) {
        DeleteTargetFileType obj = new DeleteTargetFileType();
        obj.delete();

    }
}
