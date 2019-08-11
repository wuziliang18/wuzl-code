package com.yoloho.server.queue;

public class DaemonConfiguration {
	static String pidPath = "";

	public static String getPidPath() {
		return pidPath;
	}

	public static void setPidPath(String pidPath) {
		DaemonConfiguration.pidPath = pidPath;
	}

}
