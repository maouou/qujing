package qj.admin.util;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MQUtil {
    static ConnectionFactory connectionFactory;
    static Connection connection;
    static {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.100.49.47");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/qujing");
        connectionFactory.setUsername("ozg");
        connectionFactory.setPassword("ozg123zxc");
        try {
            connection = connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static void send(String text) throws IOException, TimeoutException {
    	System.out.println("MQ1");
        Channel channel = connection.createChannel();
        System.out.println("MQ2");
        channel.queueDeclare("qujing", true, false, false,null);
        System.out.println("MQ3");
        channel.basicPublish("","qujing", null, text.getBytes());
        System.out.println("MQ4");
        channel.close();
        System.out.println("MQ5");
    }

}