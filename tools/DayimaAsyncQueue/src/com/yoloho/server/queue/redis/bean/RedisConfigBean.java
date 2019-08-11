package com.yoloho.server.queue.redis.bean;

public class RedisConfigBean {
	private String redisDbName;
	private String redisHost;
	private int redisPort;
	private int redisTimeout;
	private int redisMaxActive;
	private int redisMaxIdle;
	private int redisMaxWait;
	private boolean redisTestOnBorrow;
	private boolean redisTestOnReturn;

	public String getRedisDbName() {
		return redisDbName;
	}

	public void setRedisDbName(String redisDbName) {
		this.redisDbName = redisDbName;
	}

	public String getRedisHost() {
		return redisHost;
	}

	public void setRedisHost(String redisHost) {
		this.redisHost = redisHost;
	}

	public int getRedisPort() {
		return redisPort;
	}

	public void setRedisPort(int redisPort) {
		this.redisPort = redisPort;
	}

	public int getRedisTimeout() {
		return redisTimeout;
	}

	public void setRedisTimeout(int redisTimeout) {
		this.redisTimeout = redisTimeout;
	}

	public int getRedisMaxActive() {
		return redisMaxActive;
	}

	public void setRedisMaxActive(int redisMaxActive) {
		this.redisMaxActive = redisMaxActive;
	}

	public int getRedisMaxIdle() {
		return redisMaxIdle;
	}

	public void setRedisMaxIdle(int redisMaxIdle) {
		this.redisMaxIdle = redisMaxIdle;
	}

	public int getRedisMaxWait() {
		return redisMaxWait;
	}

	public void setRedisMaxWait(int redisMaxWait) {
		this.redisMaxWait = redisMaxWait;
	}

	public boolean isRedisTestOnBorrow() {
		return redisTestOnBorrow;
	}

	public void setRedisTestOnBorrow(boolean redisTestOnBorrow) {
		this.redisTestOnBorrow = redisTestOnBorrow;
	}

	public boolean isRedisTestOnReturn() {
		return redisTestOnReturn;
	}

	public void setRedisTestOnReturn(boolean redisTestOnReturn) {
		this.redisTestOnReturn = redisTestOnReturn;
	}

}
