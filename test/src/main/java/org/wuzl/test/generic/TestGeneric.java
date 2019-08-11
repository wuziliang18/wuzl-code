package org.wuzl.test.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestGeneric {
	public static void first(List<? extends Object> rows){
		
	}
public static void sec(Map<String,?> dto){
		
	}
	public static void main(String[] args) {
		first(new ArrayList());
		sec(new HashMap());
	}
}
