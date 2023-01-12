package com.syashiei.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syashiei.reggie.Exc.CustomException;
import com.syashiei.reggie.entity.Category;
import com.syashiei.reggie.entity.Dish;
import com.syashiei.reggie.entity.Setmeal;
import com.syashiei.reggie.mapper.CategoryMapper;
import com.syashiei.reggie.mapper.DishMapper;
import com.syashiei.reggie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{

    @Autowired
    private DishServiceImpl dishService;

    private SetmealServiceImpl setmealService;
    /**
     * 根据id删除分类
     * @param id
     */
    @Override
    public void remove(Long id) {

        //获取条件选择器
        LambdaQueryWrapper<Dish> dishQueryWrapper = new LambdaQueryWrapper<>();
        //设置选择条件
        dishQueryWrapper.eq(Dish::getCategoryId,id);
        //获取查询结果
        int count1 = dishService.count(dishQueryWrapper);
        //判断是否需要抛出异常
        if (count1 >= 1){
            throw  new CustomException("当前分类下关联了菜品，不能删除");
        }

        //查询当前分类是否关联套餐，如果已关联则抛出异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //设置选择条件
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        //获取查询结果
        int count2 = dishService.count(dishQueryWrapper);
        //判断是否需要抛出异常
        if (count2 >= 1){
            throw  new CustomException("当前分类下关联了套餐，不能删除");
        }

        //正常删除分类
        super.removeById(id);
    }
}
