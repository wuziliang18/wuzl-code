package org.wuzl.test.yoloho;

public class CreateWuKongMsgMap {
	public static void main(String[] args) {
		String msg="110000#openIdSecret不匹配,110001#appSecret不匹配,110002#参数不合法,"
				+ "110003#原openIdSecret错误,110004#用户已存在,"
				+ "110005#不规范的用户tag,110006#无效的手机号,110007#验证码重试次数过多,"
				+ "110008#验证码不存在,110009#验证码已过期,110010#错误的验证码,110011#批量获取超限,"
				+ "110012#一分钟内只能发送一次验证码,110101#你想使用的手机号已经被他人占用,"
				+ "110102#你想使用的昵称已经被他人占用,110103#只能更新自己的profile信息,110104#检查昵称不合法"
				+ "请修改后重试,110105#扩展字段超过4k请修改后提交,110106#昵称长度限制,110107#昵称不能包含表情符号,"
				+ "110108#扩展字段不能被转码,119998#appkey与domain数据不匹配";
		String[] array=msg.split(",");
		StringBuilder result=new StringBuilder();
		for(String msgMap:array){
			String[] msg_=msgMap.split("#");
			result.append("ERROR_MSG_MAP.put(\""+msg_[0]+"\",\""+msg_[1]+"\"").append(");\n");
		}
		System.out.println(result);
	}
}
