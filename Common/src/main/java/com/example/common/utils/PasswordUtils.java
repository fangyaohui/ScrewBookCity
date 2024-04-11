package com.example.common.utils;

import org.springframework.util.DigestUtils;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description PasswordUtils
 * @since 2024/3/18 20:28
 */
public class PasswordUtils {

    public static String getPassword(String password){
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}