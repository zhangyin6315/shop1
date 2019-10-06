package cn.e3mall.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	/**
	 * 单机版
	 */
	@Test
public void testJedis() {
	//创建一个Jedis对象，参数host port
	Jedis jedis=new Jedis("192.168.21.135", 6379);
	//直接使用jedis操作redis，所有命令都对应一个方法
	jedis.set("test23", "my first jedis test");
	String string=jedis.get("test23");
	System.out.println(string);
	//关闭连接
	jedis.close();
	
	
}
	@Test
	public void testJedisPool() {
		//创建一个连接池对象，参数host port
		JedisPool jedisPool=new JedisPool("192.168.21.135", 6379);
		//获得一个连接，Jedis对象，
		Jedis jedis =jedisPool.getResource();
		//使用jedis操作redis
			System.out.println(	jedis.get("test23"));
		//关闭连接，连接池回收
		jedis.close();
		//关闭连接池
		jedisPool.close();
	}
	/**
	 * 集群
	 */
	@Test
	public void testJedisCluster() {
		//创建一个JedisCluster对象，有一个参数的nodes是set类型。set包含若干个HostAndPort对象
		Set<HostAndPort> nodes=new HashSet<>();
		nodes.add(new HostAndPort("192.168.21.135", 6379));
		nodes.add(new HostAndPort("192.168.21.138", 6379));
		nodes.add(new HostAndPort("192.168.21.139", 6379));
		nodes.add(new HostAndPort("192.168.21.141", 6379));
		nodes.add(new HostAndPort("192.168.21.142", 6379));
		nodes.add(new HostAndPort("192.168.21.143", 6379));
		JedisCluster jedisCluster=new JedisCluster(nodes);
		//直接使用
		jedisCluster.set("123", "value");
		System.out.println(	jedisCluster.get("test23"));
		//关闭JedisCluster
		
		jedisCluster.close();
	}
	
}
