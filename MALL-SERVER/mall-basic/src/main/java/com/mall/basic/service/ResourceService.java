package com.mall.basic.service;

import com.mall.entity.basic.Resource;

import java.util.List;

/**
 * @Author: C-aranlism create by 2020/9/24.
 * @Version 1.0
 * @Parm request
 * @Description
 */
public interface ResourceService {

    /**
     * 根据角色ID获取资源信息
     * @param roleId
     * @return
     */
    List<Resource> menuList(String roleId);

    /**
     * 当前父节点下最大的资源ID
     * @param type
     * @param parentId
     * @return
     */
    String maxId(Integer type , String parentId);

}
