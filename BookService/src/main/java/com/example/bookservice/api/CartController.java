package com.example.bookservice.api;

import com.example.bookservice.fegin.UserServiceClient;
import com.example.bookservice.service.BookService;
import com.example.common.enetity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description CartController
 * @since 2024/3/24 14:06
 */

@RestController
@Slf4j
@RequestMapping("/cart")
public class CartController {

    @Autowired
    public BookService bookService;

    @Autowired
    private UserServiceClient userServiceClient;

    @RequestMapping("/setcart")
    public Result setCart(HttpServletRequest request){

        String token = request.getHeader("Authorization");
        log.info("setcart:" + token);

        Result result = userServiceClient.setCart(token);
        return Result.ok(result);

//        return null;
    }


}