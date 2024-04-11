package com.example.gateway.fegin;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "userservice",url = "http://localhost:8082")
public interface UserServiceClient {

    @RequestMapping("/verifyToken/{token}")
    Boolean verifyToken(@PathVariable("token") String token);
}
