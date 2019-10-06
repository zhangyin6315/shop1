package cn.e3mall.acticemq;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class ActiveMqTest  {
@Test
	public void testQueueProducer() throws Exception {
		//创建一个连接工厂 指定需要的ip喝端口
		ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.21.165:61616");
		//使用连接工厂创建一个Connection对象
		Connection connection=connectionFactory.createConnection();
		//开启连接，掉用Connection的start方法
		connection.start();
		//创建一个session对象
		//1是否开启事务,一般不开启 开启 第二参数无意义 
		//2 应答模式 一般自动 或者 手动 一般自动
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//使用Session对象创建一个Destination对象两种形式queue、topic现在用topic
		Queue queue = session.createQueue("test-queue");
		//使用session创建一个Producter对象
		MessageProducer messageProducer = session.createProducer(queue);
		//创建以后Message对象 可以使用TextMessage
//		TextMessage textMessage =new ActiveMQTextMessage();
//		textMessage.setText("hellowore");
		TextMessage textMessage = session.createTextMessage("hello activemq");
		//发送消息
		messageProducer.send(textMessage);;		
		//关闭资源
		messageProducer.close();
		session.close();
		connection.close();
	}
@Test
public void testQueueConsumer() throws Exception {
	//创建一个连接工厂 指定需要的ip喝端口
	ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.21.165:61616");
	//使用连接工厂创建一个Connection对象
	Connection connection=connectionFactory.createConnection();
	//开启连接，掉用Connection的start方法
	connection.start();
	//创建一个session对象
	//1是否开启事务,一般不开启 开启 第二参数无意义 
	//2 应答模式 一般自动 或者 手动 一般自动
	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	//使用Session对象创建一个Destination对象两种形式queue、topic现在用topic
	Queue queue = session.createQueue("test-queue");
	//使用session创建一个Consumer对象
	MessageConsumer messageConsumer = session.createConsumer(queue);
	//接收消息
	messageConsumer.setMessageListener(new MessageListener() {
		
		@Override
		public void onMessage(Message message) {
			// TODO Auto-generated method stub
			TextMessage textMessage= (TextMessage) message;
			String test;
			try {
				test =textMessage.getText();
				System.out.println(test);
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	});
	System.in.read();
	//关闭资源
	messageConsumer.close();
	session.close();
	connection.close();
}

@Test
public void testTopicProducer() throws Exception {
	//创建一个连接工厂 指定需要的ip喝端口
	ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.21.165:61616");
	//使用连接工厂创建一个Connection对象
	Connection connection=connectionFactory.createConnection();
	//开启连接，掉用Connection的start方法
	connection.start();
	//创建一个session对象
	//1是否开启事务,一般不开启 开启 第二参数无意义 
	//2 应答模式 一般自动 或者 手动 一般自动
	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	//使用Session对象创建一个Destination对象两种形式queue、topic现在用topic
	Topic topic = session.createTopic("test-topic");
	//使用session创建一个Producter对象
	MessageProducer messageProducer = session.createProducer(topic);
	//创建以后Message对象 可以使用TextMessage
//	TextMessage textMessage =new ActiveMQTextMessage();
//	textMessage.setText("hellowore");
	TextMessage textMessage = session.createTextMessage("hello activemq topic");
	//发送消息
	messageProducer.send(textMessage);;		
	//关闭资源
	messageProducer.close();
	session.close();
	connection.close();
}
@Test
public void testTopicCustomer() throws Exception {
	//创建一个连接工厂 指定需要的ip喝端口
	ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://192.168.21.165:61616");
	//使用连接工厂创建一个Connection对象
	Connection connection=connectionFactory.createConnection();
	//开启连接，掉用Connection的start方法
	connection.start();
	//创建一个session对象
	//1是否开启事务,一般不开启 开启 第二参数无意义 
	//2 应答模式 一般自动 或者 手动 一般自动
	Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	//使用Session对象创建一个Destination对象两种形式queue、topic现在用topic
	Topic topic = session.createTopic("test-topic");
	//使用session创建一个Producter对象
	MessageConsumer messageConsumer = session.createConsumer(topic);
	//接收消息
		messageConsumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				// TODO Auto-generated method stub
				TextMessage textMessage= (TextMessage) message;
				String test;
				try {
					test =textMessage.getText();
					System.out.println(test);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		});
	System.out.println("3");
	System.in.read();
	//关闭资源
	messageConsumer.close();
	session.close();
	connection.close();
}
}
