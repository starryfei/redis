# redis
### redis命令:
#### 1、字符串：
- set key value//设置
- get key //获取
- incr key //原子增长(一次增长1)属于原子性
- incrby key 50 //增长的值自定义	
- 一次设置和检索多个值
- mset key1 value1 key2 value2,...,
- mget key1 key2  
- exists key //是否存在key
- del key //删除
- type key //返回key的类型
- expire key time //设置过期时间
- 或者 set key value ex time
- ttl key  //检查键的生存剩余时间
#### 2、列表：
		Redis 采用链表来实现列表是因为,对于数据库系统来说,快速插入一个元素到一个很长的列表非常重要。另外一个即将描述的优势是,Redis 列表能在常数时间内获得常数长度。
- lpush list value1 ...//从左边 (头部) 添加一个元素到列表
- rpush list value1 .... //.右。。。。。。。。。。。。。。
- lrang list 0 -1  //从列表中提取一个范围内的元素- 1 表示最后一个元素,-2 表示倒数第二个元素.注：是一个O(N)时间复杂度的命令,访问列表头尾附近的小范围是常量时间的操作。
- rpop list 从右边弹出元素（list中就不存在弹出的元素）
- lpop list 左
- ltrim list 0 2 //仅仅只记住最新的 N 项,丢弃掉所有老的项,即删除0 -2 范围以外的值
- brpop/blpop  tasks 5 //等待 tasks 列表中的元素,如果 5 秒后还没有可用元素就返回
- llen list //获取列表的长度
       
#### 3、哈希/散列 (Hashes)
- *hmset-  key value1 value2 value3 *//设置哈希值
- 例如：hmset user:1000 username antirez birthyear 1977 verified 1
- hget key //获取hash值
- 例如：hget user:1000 username   -->"antirez"
- hincrby birthyear 10 //增长10年	
   		
#### 4、Redis 集合 (Sets) 无序
- sadd myset value1 value2 ..//添加元素到集合
- smembers  myset  //获取集合的值
- sismember myset 3 //测试成员是否存在
- tags //添加标签
- 例如：sadd news:1000:tags 1 2 5 77
- smembers news:1000:tags // 获取指定对象的标签
- spop  myset value //从集合中随机删除一个值
- sunionstore	：命令通常对多个集合执行交集,然后把结果存储在另一个集合中，而对单个集合求交集就是其自身,
- 例如：sunionstore game:1:deck deck
- scard myset //集合中元素数量
- srandmember myset //获得随机元素而不需要从集合中删除
#### 5、Redis 有序集合 (Sorted sets)
     集合中的每个元素是无序的,但有序集合中的每个元素都关联了一个浮点值,称为分数(score,这就是为什么该类型也类似于哈希,因为每一个元素都映射到一个值)
规则排序：
1） 如果 A 和 B 是拥有不同分数的元素,A.score > B.score,则 A > B。
2） 如果 A 和 B 是有相同的分数的元素,如果按字典顺序 A 大于 B,则 A > B。A 和 B 不能相同,因为排序集合只能有唯一元素。
- zadd key score value1 //向有序集合中添加
- zrange key 0 -1 //获取有序集合的所有元素(从小到大)
- zrevrange key 0 -1 //与上面相反
- withscores //返回分数 ，zrange key 0 -1 withscores
范围操作
- zrangebyscore key -inf socre
- zremrangebyscore key socre1 score2 //删除score1 到score2之间的值
- zrank key value //集合中元素的排序位置
- zrevrank key value //降序排序返回元素的排行

注：
1、有序集合是通过双端(dual-ported)数据结构实现的,包括跳跃表和哈希表(hashtable),所以我们每次添加元素时 Redis 执行 O(log(N)) 的操作。这还好,但是当我们请求有序元素时,Redis 根本不需要做什么工作,因为已经是全部有序了

2、集合中的元素都具有相同的分数,允许按字典顺序获取范围(元素按照 C 语言中的 memcmp 函数进行比较,因此可以保证没有整理,每个 Redis 实例会有相同的输出
zrangebylex Key [A[Z 范围可以是包容性的或者排除性的(取决于第一个字符,即开闭区间),+ 和 - 分别表示正无穷和负无穷

#### 6、位图 (Bitmaps)
- 位图不是一个真实的数据类型,而是定义在字符串类型上的面向位的操作的集合。由于字符串类型是二进制安全的二进制大对象(blobs),并且最大长度是 512MB,适合于设置 232 个不同的位。
- 位操作分为两组:常量时间单个位的操作,像设置一个位为 1 或者 0,或者获取该位的值。对一组位的操作,例如计算指定范围位的置位数量。
- 位图的最大优势是有时是一种非常显著的节省空间来存储信息的方式。例如,在一个系统中,不同用户由递增的用户 ID 来表示,可以使用 512MB 的内存来表示 400 万用户的单个位信息
- setbit key 10 1 //SETBIT 命令把第一个参数作为位数,第二个参数作为要给位设置的值,0 或者1 。如果位的位置超过了当前字符串的长度,这个命令或自动扩充这个字符串。
- getbit key 10 //GETBIT 命令只是返回指定下标处的位的值。超出范围的位(指定的位超出了该键下字符串的长度)被认为是 0。
### 使用原生jdbc连接数据库，采用单例设计模式，数据库采用mysql,使用redis实现数据缓存


