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
     * 创建时间
     */
    @Getter
    @Setter
    protected String createDate;

    /**
     * 创建人
     */
    @Getter
    @Setter
    protected String createBy;

    /**
     * 修改时间
     */
    @Getter
    @Setter
    protected String modifyDate;

    /**
     * 修改人
     */
    @Getter
    @Setter
    protected String modifyBy;

    /**
     * 状态 0 无效 1 有效
     */
    @Getter
    @Setter
    protected Integer status;

    public static final Integer STATUS_ACTIVE = 1;
    public static final Integer STATUS_INACTIVE = 0;

}
