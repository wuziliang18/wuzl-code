package com.yoloho.server.queue.redis.bean;

import redis.clients.jedis.Jedis;
/**
 * 对redis连接的封装
 * @author wuzl
 *
 */
public class RedisConnection {
	private Jedis jedis;
	private String redisDbName;
	public Jedis getJedis() {
		return jedis;
	}
	public void setJedis(Jedis jedis) {
		this.jedis = jedis;
	}
	public String getRedisDbName() {
		return redisDbName;
	}
	public void setRedisDbName(String redisDbName) {
		this.redisDbName = redisDbName;
	} 
}
