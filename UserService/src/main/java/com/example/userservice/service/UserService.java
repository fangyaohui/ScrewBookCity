package com.example.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.enetity.Result;
import com.example.userservice.entity.User;


public interface UserService extends IService<User> {
    Result login(User user);

    Result register(User user);

    Result sign(String token);
}
