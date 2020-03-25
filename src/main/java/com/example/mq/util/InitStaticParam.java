package com.example.mq.util;

import org.springframework.beans.factory.annotation.Value;

public class InitStaticParam {

    @Value("spring.rabbitmq.host")
    private static String mqUrl;
    @Value("spring.rabbitmq.username")
    private static String maUser;
    @Value("spring.rabbitmq.port")
    private static Integer mqPort;
@Value("spring.rabbitmq.password")
private static String mqPassword;

    public static String getMqUrl() {
        return mqUrl;
    }

    public static void setMqUrl(String mqUrl) {
        InitStaticParam.mqUrl = mqUrl;
    }

    public static String getMaUser() {
        return maUser;
    }

    public static void setMaUser(String maUser) {
        InitStaticParam.maUser = maUser;
    }

    public static Integer getMqPort() {
        return mqPort;
    }

    public static void setMqPort(Integer mqPort) {
        InitStaticParam.mqPort = mqPort;
    }

    public static String getMqPassword() {
        return mqPassword;
    }

    public static void setMqPassword(String mqPassword) {
        InitStaticParam.mqPassword = mqPassword;
    }
}
