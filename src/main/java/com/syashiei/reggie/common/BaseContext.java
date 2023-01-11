package com.syashiei.reggie.common;

import lombok.extern.slf4j.Slf4j;

/**
 * 基于ThreadLocal封装工具类，用户保存和获取登录当前id
 */
@Slf4j
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置值
     * @param id
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
        log.info("catch id = {}",id);
    }

    /**
     * 获取值
     * @return
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
