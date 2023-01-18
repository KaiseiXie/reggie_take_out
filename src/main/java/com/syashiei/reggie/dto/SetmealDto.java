package com.syashiei.reggie.dto;

import com.syashiei.reggie.entity.Setmeal;
import com.syashiei.reggie.entity.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
