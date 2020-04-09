package com.example.mq.MqController;

import com.example.mq.factory.RabbitMqFactory;
import com.example.mq.po.MessageTemplate;
import com.example.mq.util.FinalParmarConfig;
import com.rabbitmq.client.*;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

@Component
public class SendMesageClient {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public String sendMessage(MessageTemplate template) {
     try{
        rabbitTemplate.convertAndSend(FinalParmarConfig.DIRECTEXCHANGE,FinalParmarConfig.ROUTINGKEY_ONE, template);
         /*try {
             ConnectionFactory connectionFactory = RabbitMqFactory.linkRabbitMq();
             Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel();
            //动态创建可自动删除的创建队列
             Queue queue = new Queue(template.getSendUser(),false,true,true);
         } catch (KeyManagementException e) {
             e.printStackTrace();
         } catch (NoSuchAlgorithmException e) {
             e.printStackTrace();
         } catch (URISyntaxException e) {
             e.printStackTrace();
         }*/
     } catch (Exception ex) {
        if (ex instanceof AmqpException) {
            return FinalParmarConfig.AMQP_EXCEPTION;
        } else if (ex instanceof ShutdownSignalException) {
            return "ShutdownSignalException";
        } else if (ex instanceof ConnectException) {
            return "ConnectException";
        } else if (ex instanceof PossibleAuthenticationFailureException) {
            return "PossibleAuthenticationFailureException";
        } else if (ex instanceof UnsupportedEncodingException) {
            return "UnsupportedEncodingException";
        } else if (ex instanceof IOException) {
            return "IOException";
        } else if (ex instanceof TimeoutException) {
            return "TimeoutException";
        } else if (ex instanceof ConsumerCancelledException) {
            return "ConsumerCancelledException";
        } else if (ex instanceof org.springframework.amqp.rabbit.support.ConsumerCancelledException) {
            return "ConsumerCancelledException";
        } else {
            return "fail";
        }
    }
        return "SendSuccess";
    }
}
