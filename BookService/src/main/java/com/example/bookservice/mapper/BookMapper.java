package com.example.bookservice.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bookservice.entity.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
