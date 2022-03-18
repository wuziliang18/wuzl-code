package org.wuzl.test.io.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.wuzl.test.io.FileUtil;

public class DeleteGitAndTarget {
    private final String path = "D:\\wuzlWorkSpace\\jddlink-api-service";
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
            this.process(path);
        }
        if (!path.isDirectory()) {
            return;
        }
        File[] files = path.listFiles();
        for (File file : files) {
            this.delete(file);
        }
    }

    private void process(File file) {
        paths.add(file.getPath());
        if (file.isDirectory()) {
            FileUtil.deleteDir(file);
            return;
        }
        file.delete();

    }

    private boolean checkDelete(File file) {
        String name = file.getName();
        if (file.isDirectory()) {
            if (name.equals("target") || name.equals(".git") || name.equals(".settings")) {
                return true;
            }
        }
        if (name.equals(".project") || name.equals(".classpath")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        DeleteGitAndTarget obj = new DeleteGitAndTarget();
        obj.delete();
        for (String path : obj.paths) {
            System.out.println(path);
        }

    }
}
