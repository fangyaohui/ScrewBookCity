package com.example.bookservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description Book
 * @since 2024/3/19 19:46
 */

@Data
@Slf4j
@TableName("tb_book")
public class Book implements Serializable {

    private int id;

    private String bookname;

    private String author;

    private int booktype;

    private String imgpath;

    private String bookabstract;

    private int state;

    private int price;

    private int buynum;

    private int grade;

    private int gradenum;
}