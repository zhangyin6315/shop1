package cn.e3mall.acticemq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class ActiveMqSpring  {
@Test
public void sendMessage() throws Exception {
//初始化Spring
	@SuppressWarnings("resource")
	ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext-activemq.xml");
//从容器中获得JMSTemplate对象
	JmsTemplate jmsTemplate=applicationContext.getBean(JmsTemplate.class);
//从容器中获得Destination对象
	Destination destination=(Destination) applicationContext.getBean("queueDestination");
//发送请求
	jmsTemplate.send(destination,new MessageCreator() {
		
		@Override
		public Message createMessage(Session session) throws JMSException {
			
			return session.createTextMessage("sent");
		}
	});
}
}
