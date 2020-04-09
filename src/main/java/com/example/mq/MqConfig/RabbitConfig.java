package com.example.mq.MqConfig;

import com.example.mq.util.FinalParmarConfig;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MQ 配置类
 */
@Configuration
public class RabbitConfig {

    /**
     *  DirectExchange 直连交换机 精准匹配
     * @return DirectExchange
     * */
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directChange");
    }

    /**
     * 配置消息队列
     * */
    //@Bean
    /*public Queue Queue1(){
        return new Queue(FinalParmarConfig.MQ_NAME_ONE);
    }*/

   /**
     * 直连交换机 绑定 直连交消息队队列
     * @param Queue1
     * @param directExchange
     * @return
     */
    /*@Bean
    public Binding bindingDireceExchange(Queue Queue1,DirectExchange directExchange){
        return BindingBuilder.bind(Queue1).to(directExchange).with(FinalParmarConfig.ROUTINGKEY_ONE);
    }*/


    /**
     *广播形式 交换机
     * @return FanoutExchange
     *//*
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    *//**
     * 广播形式队列
     * @return
     *//*
    @Bean
    public Queue Queue2(){
        *//**
         * 配置消息队列
         * *//*
        return new Queue(FinalParmarConfig.MQ_NAME_TWO);
    }

    *//**
     *广播形式交换机 绑定 广播形式队列
     * @param Queue2
     * @param fanoutExchange
     * @return
     *//*
    @Bean
    public Binding bindingFanoutExchang(Queue Queue2,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(Queue2).to(fanoutExchange);
    }

    *//**
     *转发模式交换机
     * @return TopicExchange
     *//*
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    *//**
     * 转发模式 队列
     * @return
     *//*
    @Bean
    public Queue Queue3(){
        *//**
         * 配置消息队列
         * *//*
        return new Queue(FinalParmarConfig.MQ_NAME_THREE);
    }

    *//**
     * 转发模式交换机 绑定 转发模式队列
     * @param Queue3
     * @param topicExchange
     * @return
     *//*
    @Bean
    public Binding bindingTopicExchange(Queue Queue3,TopicExchange topicExchange){
        return BindingBuilder.bind(Queue3).to(topicExchange).with(FinalParmarConfig.ROUTINGKEY_THREE);
    }*/
}
