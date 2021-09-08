package org.crazyit.cloud;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Receive {

	public static void main(String[] args) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		Connection conn = factory.newConnection();
		Channel channel = conn.createChannel();
		
		String queueName = "hello";

		//将consumer和channel绑定 ?
		Consumer consumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					BasicProperties properties, byte[] body) throws IOException {
				String msg = new String(body, "UTF-8");
				System.out.println("接收到的消息：" + msg);
			}
		};

		//消费指定queueName里的消息,是通过上面自定义的DefaultConsumer.handleDelivery方法消费
		channel.basicConsume(queueName, consumer);

		//如果不close的话,会一直定期去queue里取message
//		channel.close();
//		conn.close();
	}
}
