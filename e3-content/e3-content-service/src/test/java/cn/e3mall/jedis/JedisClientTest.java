package cn.e3mall.jedis;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.e3mall.common.jedis.JedisClient;

public class JedisClientTest {
	@Test
public void testJedisClientTest() {
	//初始化Spring 容器
		@SuppressWarnings("resource")
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-redis.xml");
		JedisClient jedisClient=applicationContext.getBean(JedisClient.class);
//		jedisClient.set("345", "value");
		System.out.println(jedisClient.get("345"));
}
}
