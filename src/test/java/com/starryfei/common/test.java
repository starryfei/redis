package com.starryfei.common;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.junit.Before;
import org.junit.Test;

import com.starryfei.cache.RedisCache;
import com.starryfei.common.Query;
import com.starryfei.dao.LoginAction;
import com.starryfei.entity.User;

import junit.framework.TestCase;
import redis.clients.jedis.Jedis;

public class test extends TestCase{
	private static RedisCache redisCache;

	private static Jedis jedis;
//    @Before	
	public static void conRedis() {
		redisCache = RedisCache.getRedis();
		jedis = redisCache.redisCaache();
	}
	public  void main() {
		Query query = new Query();
		String sql = "select * from user";
		List<User> user = query.query(sql);
		for (User user2 : user) {
			System.out.println(user2);
		}
//		String delete = "delete from user where id =1";
//		System.out.println(query.execSql(delete));
//		
//		String update ="update user set uname='starry' where id=2";
//		System.err.println(query.execSql(update));
		query.close();
	}
	public void test() {
		redisCache = RedisCache.getRedis();
		jedis = redisCache.redisCaache();
		LoginAction login = new LoginAction();
		Query query = new Query();
		String sql = "select * from user";
		List<User> user = query.query(sql);
		System.out.println(user);
		Map<String, String> map = new HashMap<String, String>();
		for(int i=0;i<user.size();i++) {
			map.put("name", user.get(i).getName());
			map.put("pwd", user.get(i).getPwd());
			System.out.println(user.get(i).getName());
			//user:1000 username antirez birthyear 1977 verified 1
				jedis.hmset("user"+i, map);
		}
//			if(name.equals(user2.getName()) && pwd.equals(user2.getPwd())) {
//		login.checkLogin("aa", "cc");
	}
}
