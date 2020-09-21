package com.mall.common.utils;

import java.util.UUID;

/**
 * @Created with IntelliJ IDEA.
 * @Author: C-aranlism
 * @Date: 2020/9/21
 * @Description ID生成规则
 */
public class IDUtils {

    public static String UUID(){
        return UUID.randomUUID().toString().replaceAll("-" , "").toUpperCase();
    }

}
