package com.syashiei.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syashiei.reggie.entity.Category;
import com.syashiei.reggie.mapper.CategoryMapper;
import com.syashiei.reggie.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService{
}
