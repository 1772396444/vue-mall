package com.mall.basic.mgr;

import com.mall.entity.basic.Resource;

import java.util.List;

/**
 * @Author: C-aranlism create by 2020/9/24.
 * @Version 1.0
 * @Parm request
 * @Description
 */
public interface ResourceMgr {

    /**
     * 根据角色ID获取菜单
     * @param roleId
     * @return
     */
    List<Resource> menuList(String roleId);

    /**
     * 规则生成资源键ID
     * @param type
     * @param parentId
     * @return
     */
    String createId(Integer type , String parentId);

}
