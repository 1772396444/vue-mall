package com.mall.repository.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.entity.basic.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: C-aranlism create by 2020/9/24.
 * @Version 1.0
 * @Parm request
 * @Description
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 根据角色ID获取菜单列表
     * @param roleId
     * @param status
     * @return
     */
    List<Resource> menuList(@Param("roleId") String roleId , @Param("status") Integer status);

    /**
     * 当前父节点下最大的资源ID
     * @param type
     * @param parentId
     * @return
     */
    String maxId(@Param("type") Integer type , @Param("parentId") String parentId);

}
