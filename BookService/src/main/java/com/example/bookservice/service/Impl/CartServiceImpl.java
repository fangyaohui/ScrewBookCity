package com.example.bookservice.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookservice.entity.Cart;
import com.example.bookservice.mapper.CartMapper;
import com.example.bookservice.service.CartService;
import com.example.common.enetity.Result;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description CartServiceImpl
 * @since 2024/3/24 14:22
 */
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {


    @Override
    public Result setCart() {
        return null;
    }
}