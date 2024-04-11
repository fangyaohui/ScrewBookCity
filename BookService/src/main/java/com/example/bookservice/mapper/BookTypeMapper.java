package com.example.bookservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.bookservice.entity.BookType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookTypeMapper extends BaseMapper<BookType> {
}
