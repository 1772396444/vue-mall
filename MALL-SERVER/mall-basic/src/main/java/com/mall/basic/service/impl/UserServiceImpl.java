package com.mall.basic.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.basic.service.UserService;
import com.mall.entity.basic.User;
import com.mall.repository.basic.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: C-aranlism create by 2020/9/22.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public List<User> find(Page page, String id, String name, Integer status) {
        return this.baseMapper.find(page , id , name , status);
    }

}
