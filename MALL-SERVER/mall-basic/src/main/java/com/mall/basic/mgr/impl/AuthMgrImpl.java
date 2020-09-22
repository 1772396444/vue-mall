package com.mall.basic.mgr.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mall.basic.from.AuthFrom;
import com.mall.basic.mgr.AuthMgr;
import com.mall.basic.service.RoleService;
import com.mall.basic.service.UserService;
import com.mall.common.constants.SystemConstant;
import com.mall.entity.basic.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: C-aranlism create by 2020/9/22.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Service
@Transactional(readOnly = true , rollbackFor = Exception.class)
public class AuthMgrImpl implements AuthMgr {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public User loginValid(AuthFrom authFrom) {
        List<User> list = userService.list(new LambdaQueryWrapper<User>()
            .and(wrapper ->
                wrapper.eq(User::getEmail, authFrom.getName())
                    .or()
                    .eq(User::getMobile, authFrom.getName())
                    .or()
                    .eq(User::getCode, authFrom.getName())
            ).eq(User::getStatus, SystemConstant.STATUS_ACTIVE));
        User u = null;
        if (CollectionUtils.isNotEmpty(list)) {
            if (list.size() > 1) {
                throw new RuntimeException("登录帐号对应多个用户，请联系管理员处理");
            } else {
                u = list.get(0);
            }
        } else {
            throw new RuntimeException("用户名不存在或已被锁定");
        }
        if (StringUtils.isEmpty(u.getPassword())) {
            throw new RuntimeException("用户在系统中未设置密码，请联系管理员");
        }
        if (!u.getPassword().toLowerCase().equals(new Md5Hash(authFrom.getPassword(), u.getSalt()).toString())) {
            throw new RuntimeException("用户名或者密码出错");
        }
        if (u.getStatus().equals(SystemConstant.STATUS_INACTIVE)) {
            throw new RuntimeException("用户已被锁定");
        }
        u.setCurrentRole(roleService.getRole(u.getId()));
        return u;
    }

}
