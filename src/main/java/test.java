import java.util.List;

import org.junit.Test;

import com.starryfei.cache.RedisCache;
import com.starryfei.common.Query;
import com.starryfei.entity.User;

public class test {
	public static void main(String[] args) {
		Query query = new Query();
		String sql = "select * from user";
		List<User> user = query.query(sql);
		for (User user2 : user) {
			System.out.println(user2);
		}
		String delete = "delete from user where id =1";
		System.out.println(query.execSql(delete));
		
		String update ="update user set uname='starry' where id=2";
		System.err.println(query.execSql(update));
		query.close();
	}
	@Test
	public void test() {
		RedisCache redisCache = new RedisCache();
		redisCache.redisCaache();
	}
}
