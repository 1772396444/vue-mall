package com.mall.common.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.common.bean.ApiResponse;
import com.mall.common.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: C-aranlism create by 2020/9/21.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Slf4j
public class BaseController {

    public <T> ApiResponse success(Class<T> clz) {
        return ApiResponse.<T>builder().code(200).message("OK").status(true).data(null).build();
    }

    public <T> ApiResponse success(Class<T> clz , String message) {
        return ApiResponse.<T>builder().code(200).message(message).status(true).data(null).build();
    }

    public <T> ApiResponse success(Class<T> clz , Object data) {
        return ApiResponse.<T>builder().code(200).message("OK").status(true).data(data).build();
    }

    /**
     * 常规Map结果集
     * @return
     */
    public Map<String , Object> successMap(){
        return MessageUtils.success();
    }

    public Map<String , Object> successMap(String message){
        return MessageUtils.success(message);
    }

    public Map<String , Object> successMap(Object data){
        return MessageUtils.success("data" , data);
    }

    public Map<String , Object> successMap(String key , Object data){
        return MessageUtils.success(key , data);
    }

    public Map<String , Object> failure(){
        return MessageUtils.failure();
    }

    public Map<String , Object> failure(String message){
        return MessageUtils.failure(message);
    }

    public Map<String , Object> failure(String key , Object data){
        return MessageUtils.failure(key , data);
    }

    /**
     * mybatis-plus分页
     */
    public Map<String , Object> pagerMap(Page<?> pager){
        Map map = MessageUtils.success();
        map.put("page" , pager);
        return map;
    }

    /**
     * 获得HttpServletRequest
     * @return
     */
    public HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public Map<String , String> getParams(){
        HttpServletRequest request = getRequest();
        Enumeration<String> enums = request.getParameterNames();
        Map<String , String> map = new HashMap<>();
        while (enums.hasMoreElements()){
            String name = enums.nextElement();
            map.put(name , request.getParameter(name));
        }
        return map;
    }

    /**
     * session操作
     * @param key
     * @param value
     */
    public void putSession(String key , Object value){
        getRequest().getSession().setAttribute(key , value);
    }

}
