package org.wuzl.learn.mybatis.bean;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -6188544384149284540L;
	private Long uid;
	private String password;
	private String nick;
	private Integer sex;
	private Long regtime;
	private Long loginnum;
	private Long lastlogin;
	private Long onlinetime;
	private Long lastactivity;
	private String regip;
	private Integer initPassword;
	private String regcountry;
	private String lastloginip;
	private String lastlogincountry;
	private Long logindays;
	private Long groupId;
	private Long nicklastupdate;
	private short platform;
	private Short push;
	private Long topicnum;
	private Long replynum;
	private Long favnum;
	private Long score;
	private Long credit;
	/**
	 * 非数据库中表的属性，只为多表关连查询结果
	 */
	private String email;
	private String mobile;
	private Integer age;
	private String userIcon;

	/**
	 * 初始化对象的工厂方法
	 * 
	 * @return
	 */
	public static User newInstance() {
		User ui = new User();
		// 匹配PHP时间
		long dateline = System.currentTimeMillis() / 1000;
		ui.setRegtime(dateline);
		ui.setLogindays(0L);
		ui.setLoginnum(0L);
		ui.setLastlogin(dateline);
		ui.setLastactivity(dateline);
		ui.setNicklastupdate(dateline);
		ui.setOnlinetime(dateline);
		ui.setPush((short) 0);
		ui.setTopicnum(0L);
		ui.setReplynum(0L);
		ui.setFavnum(0L);
		ui.setScore(0L);
		ui.setCredit(0L);
		return ui;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Long getRegtime() {
		return regtime;
	}

	public void setRegtime(Long regtime) {
		this.regtime = regtime;
	}

	public Long getLoginnum() {
		return loginnum;
	}

	public void setLoginnum(Long loginnum) {
		this.loginnum = loginnum;
	}

	public Long getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Long lastlogin) {
		this.lastlogin = lastlogin;
	}

	public Long getOnlinetime() {
		return onlinetime;
	}

	public void setOnlinetime(Long onlinetime) {
		this.onlinetime = onlinetime;
	}

	public Long getLastactivity() {
		return lastactivity;
	}

	public void setLastactivity(Long lastactivity) {
		this.lastactivity = lastactivity;
	}

	public String getRegip() {
		return regip;
	}

	public void setRegip(String regip) {
		this.regip = regip;
	}

	public Integer getInitPassword() {
		return initPassword;
	}

	public void setInitPassword(Integer initPassword) {
		this.initPassword = initPassword;
	}

	public String getRegcountry() {
		return regcountry;
	}

	public void setRegcountry(String regcountry) {
		this.regcountry = regcountry;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	public String getLastlogincountry() {
		return lastlogincountry;
	}

	public void setLastlogincountry(String lastlogincountry) {
		this.lastlogincountry = lastlogincountry;
	}

	public Long getLogindays() {
		return logindays;
	}

	public void setLogindays(Long logindays) {
		this.logindays = logindays;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getNicklastupdate() {
		return nicklastupdate;
	}

	public void setNicklastupdate(Long nicklastupdate) {
		this.nicklastupdate = nicklastupdate;
	}

	public short getPlatform() {
		return platform;
	}

	public void setPlatform(short platform) {
		this.platform = platform;
	}

	public Short getPush() {
		return push;
	}

	public void setPush(Short push) {
		this.push = push;
	}

	public Long getTopicnum() {
		return topicnum;
	}

	public void setTopicnum(Long topicnum) {
		this.topicnum = topicnum;
	}

	public Long getReplynum() {
		return replynum;
	}

	public void setReplynum(Long replynum) {
		this.replynum = replynum;
	}

	public Long getFavnum() {
		return favnum;
	}

	public void setFavnum(Long favnum) {
		this.favnum = favnum;
	}

	public Long getScore() {
		return score;
	}

	public void setScore(Long score) {
		this.score = score;
	}

	public Long getCredit() {
		return credit;
	}

	public void setCredit(Long credit) {
		this.credit = credit;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

}
