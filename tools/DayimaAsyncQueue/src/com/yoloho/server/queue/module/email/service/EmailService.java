package com.yoloho.server.queue.module.email.service;

import com.yoloho.server.queue.module.email.bean.EmailBean;

public class EmailService {
	private String emailHost;
	private int emailPort;
	private String username;
	private String password;
	public String getEmailHost() {
		return emailHost;
	}
	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}
	public int getEmailPort() {
		return emailPort;
	}
	public void setEmailPort(int emailPort) {
		this.emailPort = emailPort;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 初始化邮件的配置
	 */
	public void initEmailConfig(){
		System.out.println("模拟实现");
		System.out.println("host:"+getEmailHost()+",port:"+getEmailPort()+",username:"+getUsername()+",password:"+getPassword());
	}
	/**
	 * 发送邮件
	 * @param emailBean
	 */
	public void sendMail(EmailBean emailBean){
		//模拟长时间业务运行
		long wait=5000000000l;
		while(wait-->=0){
			if(wait%1000000000l==0){
				System.out.println("已经等待了10000000000了");
			}
		}
		System.out.println("发送邮件to"+emailBean.getTo()+"subject："+emailBean.getSub());
	}
}
