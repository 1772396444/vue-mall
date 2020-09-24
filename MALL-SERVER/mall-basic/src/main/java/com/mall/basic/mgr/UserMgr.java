package com.mall.basic.mgr;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Author: C-aranlism create by 2020/9/24.
 * @Version 1.0
 * @Parm request
 * @Description
 */
public interface UserMgr {

    /**
     * 用户资源列表
     * @param id
     * @param name
     * @param status
     * @return
     */
    Page find(String id , String name , Integer status);

}
