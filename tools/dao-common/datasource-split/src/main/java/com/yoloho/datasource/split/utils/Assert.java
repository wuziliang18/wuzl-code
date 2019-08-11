package com.yoloho.datasource.split.utils;

public abstract class Assert {

	protected Assert() {
	}

	public static void notNull(Object obj, String message) {
		if (obj == null) {
			throw new RuntimeException(message);
		}
	}

	public static void notEmpty(String s, String message) {
		if (s == null || "".equals(s)) {
			throw new RuntimeException(message);
		}
	}
}
