package com.syashiei.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syashiei.reggie.entity.DishFlavor;
import com.syashiei.reggie.mapper.DishFlavorMapper;
import com.syashiei.reggie.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
