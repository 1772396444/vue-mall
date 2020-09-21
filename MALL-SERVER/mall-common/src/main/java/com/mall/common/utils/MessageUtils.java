package com.mall.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: C-aranlism create by 2020/9/21.
 * @Version 1.0
 * @Parm request
 * @Description
 */
public class MessageUtils {

    private static final String STATUS = "status";
    private static final String CODE = "code";
    private static final String MESSAGE = "message";

    private static Map<String , Object> createMap(int code , boolean status , String message){
        return new HashMap(){
            {
                put("code" , code);
                put("status" , status);
                put("message" , message);
            }
        };
    }

    public static Map<String , Object> success(String message){
        return createMap(200 , true , message);
    }

    public static Map<String , Object> success(String key , Object data){
        Map map = success();
        map.put(key , data);
        return map;
    }

    public static Map<String , Object> success(){
        return success("OK");
    }

    public static Map<String , Object> failure(int code , String message){
        return createMap(code , false , message);
    }

    public static Map<String , Object> failure(String message){
        return failure(400 , message);
    }

    public static Map<String , Object> failure(String key , Object data){
        Map map = failure();
        map.put(key , data);
        return map;
    }

    public static Map<String , Object> failure(){
        return failure("服务器操作错误");
    }


}
