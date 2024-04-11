package com.example.bookservice.fegin;

import com.example.common.enetity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "userservice",url = "http://localhost:8082")
public interface UserServiceClient {



    @RequestMapping("/setcart/{token}")
    Result setCart(@PathVariable("token") String token);
}
