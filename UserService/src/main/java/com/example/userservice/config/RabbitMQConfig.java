package com.example.userservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description RabbitMQConfig
 * @since 2024/3/26 22:53
 */
@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue sendMailQueue(){
        return new Queue("user.sendmail");
    }
}