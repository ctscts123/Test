package com.example.demo.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.pojo.User;

public interface UserMapper extends BaseMapper<User>{
	List<User> findAll();
}
