package com.starryfei.cache;

import java.util.List;
import java.util.Map;

import com.starryfei.common.Query;
import com.starryfei.entity.User;

import redis.clients.jedis.Jedis;

public class RedisCache {
	public void redisCaache() {
		Jedis jedis = new Jedis("localhost");
		Query query = new Query();
		List<User> list = query.query("select * from user");
		for (User user : list) {
			System.out.println(user);
			jedis.rpush( user.getName(),user.getPwd());
			
		}
		query.close();
	}
}
