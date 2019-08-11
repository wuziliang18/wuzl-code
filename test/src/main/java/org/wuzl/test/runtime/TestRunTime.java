package org.wuzl.test.runtime;

public class TestRunTime {
	public static void main(String[] args) {
		Runtime runtime = Runtime.getRuntime();
        long freeMemory = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();
        System.out.println(freeMemory/1024/1024);
        System.out.println(totalMemory/1024/1024);
	}
}
