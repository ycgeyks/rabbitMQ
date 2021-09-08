package org.crazyit.cloud;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		Connection conn = factory.newConnection();
		Channel channel = conn.createChannel();
		
		String queueName = "hello";
		//申明一个name为queueName的queue
		channel.queueDeclare(queueName, false, false, false, null);

		String message = "Hello world 11111111122222222!";
		//交换器将消息发送到指定routingKey的queue里
		//exchange为空时,用默认交换器:AMQP default
		channel.basicPublish("", queueName, null, message.getBytes());
		channel.close();
		conn.close();
	}

}
