package org.wuzl.test.preference;

import java.util.prefs.Preferences;
/**
 * 可以保存到当前系统
 * @author wuzl
 *
 */
public class TestPreference {
	public static void main(String[] args) {
		Preferences pre=Preferences.userNodeForPackage(TestPreference.class);//用户级别
		int version=pre.getInt("verison", 0);
		System.out.println("version:"+version);
		pre.putInt("verison",++version);//会保存到系统 依赖底层
	}
}
