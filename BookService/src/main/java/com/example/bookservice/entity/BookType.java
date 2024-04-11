package com.example.bookservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description BookType
 * @since 2024/3/22 21:34
 */
@Data
@Slf4j
@TableName("tb_booktype")
public class BookType implements Serializable {

    private int id;

    private String name;

    private int num;
}