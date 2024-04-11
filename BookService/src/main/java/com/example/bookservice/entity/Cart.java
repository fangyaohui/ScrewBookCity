package com.example.bookservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description Cart
 * @since 2024/3/24 14:19
 */
@Data
@TableName("tb_shopcart")
public class Cart {

    public String id;

    public String user_id;

    public String book_id;

}