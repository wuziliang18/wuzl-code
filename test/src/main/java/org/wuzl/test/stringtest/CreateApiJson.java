package org.wuzl.test.stringtest;

public class CreateApiJson {
	public static void main(String[] args) {
		String src="array('api' => 'user/usercount', 'arg' => '', 'name' => '用户统计专用接口'),"+
                "array('api' => 'user/reg', 'arg' => 'nick,noinit,password,sign', 'name' => '用户注册'),"+
                "array('api' => 'user/login', 'arg' => 'username,password,uid,sign', 'name' => '用户登录'),"+
                "array('api' => 'user/updateinfo', 'arg' => 'age,period,cycle,menarche,last_period,recent_symptom,recent_symptom_user,extra_info', 'name' => '更新用户个人信息'),"+
                "array('api' => 'user/getinfo', 'arg' => '', 'name' => '获得用户个人信息'),"+
                "array('api' => 'user/getlvinfo', 'arg' => '', 'name' => '取得用户的积分经验以及登陆天数'),"+
                "array('api' => 'user/getlvinfomy', 'arg' => '', 'name' => '获取自己的积分经验等信息'),"+
                "array('api' => 'user/flower', 'arg' => 'uid', 'name' => '为某用户献花'),"+
                "array('api' => 'user/logout', 'arg' => 'uid', 'name' => '用户退出登录'),"+
                "array('api' => 'user/updatepassword', 'arg' => 'old,new', 'name' => '修改密码'),"+
                "array('api' => 'user/updatenick', 'arg' => 'nick', 'name' => '修改昵称'),"+
                "array('api' => 'user/register_iphone', 'arg' => 'token', 'name' => '注册用户的token'),"+
                "array('api' => 'user/unregister_iphone', 'arg' => '', 'name' => '注销用户的token'),"+
                "array('api' => 'user/register_android', 'arg' => 'token', 'name' => '注册用户的token'),"+
                "array('api' => 'user/unregister_android', 'arg' => 'token', 'name' => '注销用户的token'),"+
                "array('api' => 'user/setpush', 'arg' => 'push', 'name' => '设置用户的push字段'),"+
                "array('api' => 'user/getpush', 'arg' => 'push', 'name' => '获得用户的push字段'),"+
                "array('api' => 'user/setemail', 'arg' => 'p,email', 'name' => '绑定邮箱验证'),"+
                "array('api' => 'user/getemail', 'arg' => '', 'name' => '取得绑定的邮箱'),"+
                "array('api' => 'user/getemail_v2', 'arg' => '', 'name' => '取得绑定的邮箱'),"+
                "array('api' => 'user/resendbindemail', 'arg' => '', 'name' => '重发验证邮件'),"+
                "array('api' => 'user/uploadavatar', 'arg' => 'pic', 'name' => '更新用户头像'),"+
                "array('api' => 'user/saveiosinfo', 'arg' => 'str', 'name' => '保存IOS IDFA/MAC 信息'),"+
                "array('api' => 'user/getavatar', 'arg' => '', 'name' => '取得用户头像'),"+
                "array('api' => 'user/getuserrank', 'arg' => '', 'name' => '取得用户排名'),"+
                "array('api' => 'user/verify', 'arg' => 'code', 'name' => '验证验证码'),"+
                "array('api' => 'user/sendverify', 'arg' => 'mobile,passwd', 'name' => '重发验证码'),"+
                "array('api' => 'user/getusermobile', 'arg' => '', 'name' => '取得用户手机号'),"+
                "array('api' => 'user/resendverify', 'arg' => '', 'name' => '重发验证码'),"+
                "array('api' => 'user/getchangepasswordcode', 'arg' => 'mobile,nick', 'name' => '通过手机更改密码时，取得手机验证码'),"+
                "array('api' => 'user/verifycode', 'arg' => 'code,mobile', 'name' => '通过手机更改密码时，验证验证码'),"+
                "array('api' => 'user/updatepasswordbymobilecode', 'arg' => 'passwd,code', 'name' => '通过手机验证码修改密码')";
		StringBuilder json=new StringBuilder("[");
		String[] array=src.split("\\),");
		for(String msg:array){
			String[] msgArray=msg.split("\\s+=>\\s+");
//			for(String s:msgArray){
//				System.out.println(s);
//			}
			String apiUrlMsg=msgArray[1];
			String apiUrl=apiUrlMsg.split(",")[0].replace("'", "");
			String apiName=msgArray[3].replace("'", "");
			String paramMsg=msgArray[2].substring(0,msgArray[2].lastIndexOf(",")).replace("'", "");
//			System.out.println(apiUrl+":"+paramMsg+":"+apiName);
			json.append("{");
			json.append("\"api\":");
			json.append("\"");
			json.append(apiUrl);
			json.append("\",");
			
			json.append("\"arg\":");
			json.append("\"");
			json.append(paramMsg);
			json.append("\",");
			
			json.append("\"name\":");
			json.append("\"");
			json.append(apiName);
			json.append("\",");
			
			json.append("\"desc\":\"\"");
			
			json.append("},");
		}
		json.deleteCharAt(json.lastIndexOf(","));
		json.append("]");
		System.out.println(json);
		System.out.println(">>>>>>>>");
		System.out.println(String.format("完成了%d个jsonapi", array.length));
	}
}
