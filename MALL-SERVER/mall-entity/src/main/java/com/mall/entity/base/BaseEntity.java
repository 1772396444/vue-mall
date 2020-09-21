package com.mall.entity.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: C-aranlism create by 2020/9/21.
 * @Version 1.0
 * @Parm request
 * @Description
 */
public class BaseEntity implements Serializable {

    /**
     * 创建人
     */
    @Getter
    @Setter
    protected String createUser;

    /**
     * 创建时间
     */
    @Getter
    @Setter
    protected String createTime;

    /**
     * 修改人
     */
    @Getter
    @Setter
    protected String updateUser;

    /**
     * 修改时间
     */
    @Getter
    @Setter
    protected String updateTime;

    /**
     * 状态
     */
    @Getter
    @Setter
    public Integer status;

    public static final Integer ACTIVE = 1;
    public static final Integer INACTIVE = 0;

}
