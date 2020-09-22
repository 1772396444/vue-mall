package com.mall.basic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mall.entity.basic.Role;

/**
 * @Author: C-aranlism create by 2020/9/22.
 * @Version 1.0
 * @Parm request
 * @Description
 */
public interface RoleService extends IService<Role> {

    /**
     * 根据当前人获取角色信息
     * @param userId
     * @return
     */
    Role getRole(String userId);

}
