package org.wuzl.test.classtest;

import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.List;

public class TestGetClassSource {
	public static void main(String[] args) {
		System.out.println(getCodeBase(List.class));
		System.out.println(getCodeBase(TestGetClassSource.class));
	}
	public static String getCodeBase(Class<?> cls) {
	    if (cls == null)
	        return null;
	    ProtectionDomain domain = cls.getProtectionDomain();
	    if (domain == null)
	        return null;
	    CodeSource source = domain.getCodeSource();
	    if (source == null)
	        return null;
	    URL location = source.getLocation();
	    if (location == null)
	        return null;
	    return location.getFile();
	}
}
