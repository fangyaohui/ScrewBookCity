package com.example.userservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description User
 * @since 2024/3/16 15:03
 */
@Data
@Slf4j
@TableName("tb_user")
public class User implements Serializable {
    private int id;

    private String username;

    private String password;

    private String name;

    private String imgpath;

    private int balance;

    private int state;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime registerdate;
}