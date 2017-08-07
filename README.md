 # redis
从零开始学习redis
 ###redis命令:
 ####1、字符串：
		set key value//设置
		get key //获取
		incr key //原子增长(一次增长1)属于原子性
		incrby key 50 //增长的值自定义	
		一次设置和检索多个值
		mset key1 value1 key2 value2,...,
		mget key1 key2  
		exists key //是否存在key
		del key //删除
		type key //返回key的类型
		expire key time //设置过期时间
		或者 set key value ex time
		ttl key  //检查键的生存剩余时间
 ####2、列表：
		Redis 采用链表来实现列表是因为,对于数据库系统来说,快速插入一个元素到一个很长的列表非常重要。另外一个即将描述的优势是,Redis 列表能在常数时间内获得常数长度。
       lpush list value1 ...//从左边 (头部) 添加一个元素到列表
       rpush list value1 .... //.右。。。。。。。。。。。。。。
       lrang list 0 -1  //从列表中提取一个范围内的元素- 1 表示最后一个元素,-2 表示倒数第二个元素.注：是一个O(N)时间复杂度的命令,访问列表头尾附近的小范围是常量时间的操作。
       rpop list 从右边弹出元素（list中就不存在弹出的元素）
       lpop list 左
       ltrim list 0 2 //仅仅只记住最新的 N 项,丢弃掉所有老的项,即删除0 -2 范围以外的值
       
   
		
 ###使用原生jdbc连接数据库，采用单例设计模式，数据库采用mysql,使用redis实现数据缓存


