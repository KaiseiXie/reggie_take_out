package com.syashiei.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syashiei.reggie.entity.User;
import com.syashiei.reggie.mapper.UserMapper;
import com.syashiei.reggie.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{
}
