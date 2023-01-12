package com.syashiei.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syashiei.reggie.entity.Dish;
import com.syashiei.reggie.mapper.DishMapper;
import com.syashiei.reggie.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper,Dish> implements DishService {
}
