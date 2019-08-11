package org.datasource.split;

import com.yoloho.datasource.split.DebugContext;
import com.yoloho.datasource.split.annotation.SplitTableInfo;

public class TestDebugContext {
	public static void main(String[] args) {
		System.out.println("?>>>>>>>>>>>>>>>>isSearchMethod");
		System.out.println(DebugContext.isSearchMethod("get"));
		System.out.println(DebugContext.isSearchMethod("getUser"));
		System.out.println(DebugContext.isSearchMethod("find"));
		System.out.println(DebugContext.isSearchMethod("findUser"));
		System.out.println(DebugContext.isSearchMethod("search"));
		System.out.println(DebugContext.isSearchMethod("searchUser"));
		System.out.println(DebugContext.isSearchMethod("cpunt"));
		
		System.out.println(DebugContext.isSearchMethod("1get"));
		System.out.println(DebugContext.isSearchMethod("1getUser"));
		System.out.println(DebugContext.isSearchMethod("1find"));
		System.out.println(DebugContext.isSearchMethod("1findUser"));
		System.out.println(DebugContext.isSearchMethod("1search"));
		System.out.println(DebugContext.isSearchMethod("1searchUser"));
		System.out.println(DebugContext.isSearchMethod("1cpunt"));
		
		System.out.println("?>>>>>>>>>>>>>>>>getDebugStatus");
		SplitTableInfo splitTableInfo=new SplitTableInfo();
		splitTableInfo.setDebug(true);
		System.out.println(DebugContext.getDebugStatus(splitTableInfo, "get11"));
		System.out.println(DebugContext.getDebugStatus(splitTableInfo, "update"));
		splitTableInfo.setDebug(false);
		System.out.println(DebugContext.getDebugStatus(splitTableInfo, "get11"));
		System.out.println(DebugContext.getDebugStatus(splitTableInfo, "update"));
		splitTableInfo.setDebug(true);
//		DebugContext.openDebug();
		System.out.println(DebugContext.getDebugStatus(splitTableInfo, "get11"));
		System.out.println(DebugContext.getDebugStatus(splitTableInfo, "update"));
//		DebugContext.closeDebug();
		System.out.println(DebugContext.getDebugStatus(splitTableInfo, "get11"));
		System.out.println(DebugContext.getDebugStatus(splitTableInfo, "update")==DebugContext.DebugStatus.cudNotInDebug);
		
	}
}
