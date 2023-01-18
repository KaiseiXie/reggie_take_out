package com.syashiei.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.syashiei.reggie.dto.SetmealDto;
import com.syashiei.reggie.entity.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    void saveWithDish(SetmealDto setmealDto);

    void deleteWithDish(List<Long> ids);
}
