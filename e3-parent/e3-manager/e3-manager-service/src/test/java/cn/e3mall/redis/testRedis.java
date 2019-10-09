package cn.e3mall.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class  testRedis {
	@Test
public void tese() {
	Jedis jedis = new Jedis("192.168.21.152",6379);
	System.out.println(jedis.ping());
	jedis.close();
}
}
