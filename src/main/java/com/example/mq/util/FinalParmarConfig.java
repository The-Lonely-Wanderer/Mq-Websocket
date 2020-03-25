package com.example.mq.util;

public class FinalParmarConfig {

    /**
     * 消息队列 Direct 模式的 key
     * */
    public static final String MQ_NAME_ONE = "queue1";
    public static final String MQ_NAME_TWO = "queue2";
    public static final String MQ_NAME_THREE = "queue3";

    /**
     * 交换机匹配 routingkey
     * */
    public static final String ROUTINGKEY_ONE = "routingkey_one";
    public static final String ROUTINGKEY_TWO = "routingkey_two";
    public static final String ROUTINGKEY_THREE = "routingkey_three";

    /**
     * 直连交换机
     */
    public static final String DIRECTEXCHANGE = "directChange";
    /**
     * 广播交换机
     */
    public static final String FANOUTEXCHANGE = "fanoutExchange";
    /**
     * 转发交换机
     */
    public static final String TOPICEXCHANGE = "topicExchange";

    /**
     * 异常类型
     * */
    public static final String AMQP_EXCEPTION = "MQ 服务器连接异常";

    /**
     * 用户名必填
     */
    public static final String USERNAEM_ISMUST ="用户名必填";



    /**
     * 密码必填
     */
    public static final String PASSWORD_ISMUST ="密码名必填";
}
