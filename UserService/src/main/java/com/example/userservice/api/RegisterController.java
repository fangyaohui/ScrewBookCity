package com.example.userservice.api;

import com.example.common.enetity.Result;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description RegisterController
 * @since 2024/3/18 20:04
 */
@RestController
@Slf4j
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public Result register(@RequestBody User user){

        return userService.register(user);
//        return null;
    }



}