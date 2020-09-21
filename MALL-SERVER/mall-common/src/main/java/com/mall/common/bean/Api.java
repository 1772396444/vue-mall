package com.mall.common.bean;

import lombok.Data;

/**
 * @Created with IntelliJ IDEA.
 * @Author: C-aranlism
 * @Date: 2020/9/21
 * @Description 用于swagger文档信息配置
 */
@Data
public class Api {

    /**
     * Swagger标题
     */
    private String title;

    /**
     * Swagger描述
     */
    private String description;

    /**
     * 作者
     */
    private String author;

    /**
     * 地址
     */
    private String url;

    /**
     * Email
     */
    private String email;

    /**
     * 提供链式调用
     */
    public Api title(String _title){
        this.title = _title;
        return this;
    }

    public Api description(String _description){
        this.description = _description;
        return this;
    }

    public Api author(String _author){
        this.author = _author;
        return this;
    }

    public Api url(String _url){
        this.url = _url;
        return this;
    }

    public Api email(String _email){
        this.email = _email;
        return this;
    }

}
