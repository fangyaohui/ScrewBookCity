package com.example.bookservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bookservice.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
}
