package com.example.userservice.api;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.common.enetity.Result;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.common.utils.JWTUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description LoginController
 * @since 2024/3/17 16:34
 */

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 该函数进行登录逻辑处理
     * @param user 前端用户输入的用户名和密码
     * @return 是否登录成功 登录成功返回一个token
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){

        log.info("login......" + user.toString());

        return userService.login(user);

    }

    @RequestMapping("/sign")
    public Result sign(HttpServletRequest request){

        try{
            String token = request.getHeader("Authorization");
            log.info("sing..."+token);
            if(token == null){
                return Result.fail("请重新登录");
            }
            token = token.replace("Bearer ","");
            DecodedJWT decodedJWT = JWTUtils.decode(token);
            String payload = decodedJWT.getClaim("token").asString();
            log.info(payload);
            return userService.sign(payload);
        }catch (Exception e){
            return Result.fail("请重新登录");
        }

    }

    @RequestMapping("/verifyToken/{token}")
    public boolean verifyToken(@PathVariable("token") String token){

        String tokentemp = token.replace("Bearer ","");


        log.info("verify Token ..." + token);

        return JWTUtils.verifyToken(tokentemp);

    }

    @RequestMapping("/setcart/{token}")
    public Result setCart(@PathVariable("token") String token){
        token = token.replace("Bearer ","");
        DecodedJWT decodedJWT = JWTUtils.decode(token);
        String payload = decodedJWT.getClaim("token").asString();
        log.info(payload);
        return userService.sign(payload);
    }
}