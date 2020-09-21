package com.mall.entity.basic;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: C-aranlism create by 2020/9/21.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("crm_user")
@NoArgsConstructor
public class User extends BaseEntity {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户编码
     */
    private String code;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 性别
     */
    private String sex;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 盐
     */
    private String salt;

    /**
     * 是否为客服
     */
    private String client;

}
