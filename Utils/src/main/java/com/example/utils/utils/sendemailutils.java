package com.example.utils.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description sendemail
 * @since 2024/3/26 22:24
 */
@Component
@Slf4j
public class sendemailutils {

    @RabbitListener(queues = "user.sendmail")
    public void receive(String message){

        log.info(message);

    }


}