package com.example.mq.factory;

import com.example.mq.util.InitStaticParam;
import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqFactory {
    /***
     * RabbitMq 连接工厂
     * @return
     */
    public static RabbitConnectionFactoryBean linkRabbitMq(){
        RabbitConnectionFactoryBean connectionFactory = new RabbitConnectionFactoryBean();

            connectionFactory.setUri(InitStaticParam.getMqUrl());
            connectionFactory.setPort(InitStaticParam.getMqPort());
            connectionFactory.setUsername(InitStaticParam.getMaUser());
            connectionFactory.setPassword(InitStaticParam.getMqPassword());

        return connectionFactory;
    }

}
