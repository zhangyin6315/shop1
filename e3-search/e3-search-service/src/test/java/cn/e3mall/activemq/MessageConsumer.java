package cn.e3mall.activemq;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MessageConsumer {
@SuppressWarnings("resource")
@Test
	public void msgConsumer()throws Exception{
	@SuppressWarnings("unused")
	ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
	System.in.read();
}
}
