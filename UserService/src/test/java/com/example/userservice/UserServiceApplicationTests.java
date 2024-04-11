package com.example.userservice;

//import org.junit.Test;
//import org.testng.annotations.Test;
//import org.junit.Test;
import com.example.userservice.dto.UserDTO;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.LocalDateTime;

@SpringBootTest
class UserServiceApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue sendMailQueue;

    @Test
    void contextLoads() {
    }

    @Test
    public void testStringRedis(){
        redisTemplate.opsForValue().set("name","胡歌");
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

    @Test
    public void testRedisObject(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1010);
        userDTO.setUsername("username");
        userDTO.setRegisterdate(LocalDateTime.now());

        redisTemplate.opsForValue().set("123456",userDTO);

        Object userDTO1 =  redisTemplate.opsForValue().get("123456");

        UserDTO userDTO2 = (UserDTO) userDTO1;

        assert userDTO1 != null;
        System.out.println(userDTO2.getUsername());
    }

    @Test
    public void rabbitMQTest(){

        rabbitTemplate.convertAndSend(sendMailQueue.getName(),"fangyaohui hello");

    }

}
