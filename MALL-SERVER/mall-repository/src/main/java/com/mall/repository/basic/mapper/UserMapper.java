package com.mall.repository.basic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mall.entity.basic.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: C-aranlism create by 2020/9/21.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    List<User> find();

}
