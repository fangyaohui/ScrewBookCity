package com.example.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
//import com.example.userservice.entity.Result;
import com.example.userservice.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {


}
