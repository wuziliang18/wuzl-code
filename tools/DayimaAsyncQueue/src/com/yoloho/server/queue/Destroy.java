package com.yoloho.server.queue;

public class Destroy {
	private static void tryToDaemon() {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				System.out.println("????");
				super.run();
				
			}
		});
	}

	public static void main(String[] args) {
		tryToDaemon();
		System.out.println(">");
		while (true) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				
			}
		}
	}
}
