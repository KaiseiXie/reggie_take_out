package com.syashiei.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.syashiei.reggie.dto.DishDto;
import com.syashiei.reggie.entity.Dish;

public interface DishService extends IService<Dish> {

    //新增菜品，加入口味数据，需要同时操作两张表dish和dish_flavor
    void saveWithFlavor(DishDto dishDto);

    //根据id查询菜品信息和对应的口味信息
    DishDto getByIdWithFlavor(Long id);

    //更新菜品信息，同时更新对应口味信息
    void updateWithFlavor(DishDto dishDto);
}
