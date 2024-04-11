package com.example.bookservice.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.bookservice.entity.BookType;
import com.example.bookservice.mapper.BookTypeMapper;
import com.example.bookservice.service.BookTypeService;
import org.springframework.stereotype.Service;

/**
 * @author fangyaohui
 * @version 0.0.3
 * @description BookTypeServiceImpl
 * @since 2024/3/22 21:36
 */
@Service
public class BookTypeServiceImpl extends ServiceImpl<BookTypeMapper, BookType> implements BookTypeService {
}