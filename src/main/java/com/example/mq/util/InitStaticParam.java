package com.example.mq.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class InitStaticParam {
    @Value("${spring.rabbitmq.host}")
    private String mqUrl;
    @Value("${spring.rabbitmq.username}")
    private String mqUser;
    @Value("${spring.rabbitmq.port}")
    private String mqPort;
    @Value("${spring.rabbitmq.password}")
    private String mqPassword;
}
