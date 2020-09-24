package com.mall.basic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mall.basic.service.ResourceService;
import com.mall.entity.basic.Resource;
import com.mall.repository.basic.mapper.ResourceMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: C-aranlism create by 2020/9/24.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper , Resource> implements ResourceService {

    @Override
    public List<Resource> menuList(String roleId){
        return this.baseMapper.menuList(roleId , Resource.STATUS_ACTIVE);
    }

    @Override
    public String maxId(Integer type , String parentId){
        return this.baseMapper.maxId(type , parentId);
    }

}
