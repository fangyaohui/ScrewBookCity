package com.example.common.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description MyMateObjectHandler
 * @since 2024/3/18 20:45
 */
@Component
@Slf4j
public class MyMateObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insert success");
        metaObject.setValue("registerdate", LocalDateTime.now());

    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}