package com.syashiei.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syashiei.reggie.Exc.CustomException;
import com.syashiei.reggie.dto.SetmealDto;
import com.syashiei.reggie.entity.Setmeal;
import com.syashiei.reggie.entity.SetmealDish;
import com.syashiei.reggie.mapper.SetmealMapper;
import com.syashiei.reggie.service.SetmealDishService;
import com.syashiei.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.soap.Addressing;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    /**
     *
     * @param setmealDto
     */
    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        //基本情報格納,setmealを扱って、insert実行
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //全部情報を格納
        setmealDishService.saveBatch(setmealDishes);
    }


    /**
     * セットの削除
     * @param ids
     */
    @Transactional
    public void deleteWithDish(List<Long> ids) {
        //select count(*) from setmeal where id in (1,2,3) and status = 1
        //セット状態納得、削除する出来かどうかの判断
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);

        int count = this.count(queryWrapper);
        if (count > 0){
        //もしできなれば、例外処理
            throw new CustomException("禁用のセットだけ削除できる");
        }

        //出来れば、テーブルsetmealの内容を削除
        this.removeByIds(ids);

        //delete from setmeal_dish where setmeal_id in (1,2,3)
        //setmeal_dishの内容を削除
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        //setmeal_dishテーブルのデータを削除
        setmealDishService.remove(lambdaQueryWrapper);
    }
}
