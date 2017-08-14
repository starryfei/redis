package com.starryfei.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.starryfei.cache.RedisCache;
import com.starryfei.common.Query;
import com.starryfei.entity.User;
import com.starryfei.service.UserImpl;

import redis.clients.jedis.Jedis;

public class LoginAction implements UserImpl {
	private static RedisCache redisCache;
	private static Jedis jedis;
	private static Query query;

	public static void conRedis() {
		redisCache = RedisCache.getRedis();
		jedis = redisCache.redisCaache();
		query = new Query();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.starryfei.service.UserImpl#checkLogin(java.lang.String,
	 * java.lang.String) 先从Redis中读取用户信息，若不存在从mysql数据库中读取，然后写入到redis缓存之中
	 */
	public boolean checkLogin(String name, String pwd) {
		conRedis();
		if (name != null && pwd != null) {
			List<String> userlist = jedis.hmget("user", "name", "pwd");
			System.out.println(userlist);
			List<String> list = new ArrayList<String>();
			list.add(name);
			list.add(pwd);
			if (list.equals(userlist)) {
				System.out.println("执行了调用redis");
				return true;
			} else {
				System.out.println("开始执行从mysql数据库中获取信息");
				String getUser = "select * from user where uname='" + name + "' and pwd='" + pwd + "'";
				List<User> user = query.query(getUser);
				if (!user.isEmpty()) {
					if (user.get(0).getName().equals(name) && user.get(0).getPwd().equals(pwd)) {
						System.out.println("写入到redis中");
						Map<String, String> map = new HashMap<String, String>();
						map.put("name", user.get(0).getName());
						map.put("pwd", user.get(0).getPwd());
						// user:1000 username antirez birthyear 1977 verified 1
						jedis.hmset("user", map);
						// }
						return true;
					} else
						return false;
				}
			}
			return false;
		} else {
			return false;
		}
	}

	public void closeMysql() {
		query.close();
	}

	public boolean register(String name, String pwd) {
		// TODO Auto-generated method stub
		return false;
	}
}
