package com.example.bookservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.bookservice.entity.Cart;
import com.example.bookservice.mapper.CartMapper;
import com.example.common.enetity.Result;
import org.springframework.stereotype.Service;

public interface CartService extends IService<Cart> {

    Result setCart();
}
