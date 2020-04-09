package com.example.mq.factory;

import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import com.example.mq.util.InitStaticParam;
import com.example.mq.util.SpringContext;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqFactory {
    /***
     * RabbitMq 连接工厂
     * 
     * @return
     * @throws URISyntaxException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public static ConnectionFactory linkRabbitMq()
            throws KeyManagementException, NoSuchAlgorithmException, URISyntaxException {
            //获取MQ 连接参数
            InitStaticParam initStaticParam = SpringContext.getBean(InitStaticParam.class);
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(initStaticParam.getMqUrl());
            connectionFactory.setPort(Integer.parseInt(initStaticParam.getMqPort()));
            connectionFactory.setUsername(initStaticParam.getMqUser());
            connectionFactory.setPassword(initStaticParam.getMqPassword());
        return connectionFactory;
    }

}
