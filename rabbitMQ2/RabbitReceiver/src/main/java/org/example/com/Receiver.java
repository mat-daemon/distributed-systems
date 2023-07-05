package org.example.com;

import com.google.gson.JsonSyntaxException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Receiver {
    public static final String QUEUE_NAME = "wojciechmateusz03";
    private static int end_of_production_signals = 0;
    public static void main(String[] args) throws Exception{

        MyData.info();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("156.17.141.214");
        factory.setPassword("admin");
        factory.setUsername("admin");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            if(message.equals("END")) {
                end_of_production_signals+=1;
                System.out.println(" [x] Received END");
            }


            if(message.startsWith("{")) {
                try {
                    System.out.println(" [x] Received PersonData");
                    PersonData data = PersonData.deserializeFromJson(message);
                    System.out.println("Deserialization successful:");
                    System.out.println(data);
                } catch (JsonSyntaxException e) {
                    System.out.println("Deserialization failed: " + e.getMessage());
                }
            }


            if (end_of_production_signals >= 2) {
                channel.basicCancel(consumerTag);
                System.out.println(" [*] Stopped receiving messages.");
            }

        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });


    }
}
