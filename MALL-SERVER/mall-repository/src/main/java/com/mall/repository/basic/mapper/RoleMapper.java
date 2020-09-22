package com.mall.repository.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.entity.basic.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: C-aranlism create by 2020/9/21.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    Role getRole(@Param("userId") String userId , @Param("status") Integer status);

}
