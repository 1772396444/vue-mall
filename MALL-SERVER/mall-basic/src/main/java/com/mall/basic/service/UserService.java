package com.mall.basic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.entity.basic.User;

import java.util.List;

/**
 * @Author: C-aranlism create by 2020/9/22.
 * @Version 1.0
 * @Parm request
 * @Description
 */
public interface UserService extends IService<User> {

    /**
     * 页面列表查询
     * @param page
     * @param id
     * @param name
     * @param status
     * @return
     */
    List<User> find(Page page , String id , String name , Integer status);

}
