package com.mall.basic.mgr.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.basic.mgr.UserMgr;
import com.mall.basic.service.UserService;
import com.mall.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: C-aranlism create by 2020/9/24.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserMgrImpl implements UserMgr {

    @Autowired
    UserService userService;

    @Override
    @Transactional(readOnly = true)
    public Page find(String id , String name , Integer status){
        Page page = PageUtils.getPage();
        page.setRecords(userService.find(page , id , name , status));
        return page;
    }

}
