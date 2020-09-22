package com.mall.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.basic.service.RoleService;
import com.mall.common.constants.SystemConstant;
import com.mall.entity.basic.Role;
import com.mall.repository.basic.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: C-aranlism create by 2020/9/22.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper , Role> implements RoleService {

    @Override
    public Role getRole(String userId){
        return this.baseMapper.getRole(userId , SystemConstant.STATUS_ACTIVE);
    }

}
