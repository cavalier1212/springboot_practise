修正windows 無法開啟的前置動作

`redis-cli.exe` -> `exit` -> `redis-cli.exe` -> `shutdown` -> `exit`

再去開

`redis-server.exe redis.windows.conf`

---

cli 操作

一般key-value操作  
`set name Alix`  
`set id 1`  
`get name`  
`get id`  
`keys *`  

hash

`hset ckey a11 b11`  
`hset ckey a22 b22`  
`hget ckey a11`  
`hget ckey a22`  

---

在cli 操作 是以string的形式操作，要在程式內獲得需使用`StringRedisTemplate`

`RedisTemplate` 始以物件操作

client-type : 預設 lettuce，可改成jedis

* jedis 有線程安全問題
* lettuce **修正線程安全問題**

