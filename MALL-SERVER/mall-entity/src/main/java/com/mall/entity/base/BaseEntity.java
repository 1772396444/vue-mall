package com.mall.entity.base;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "创建时间")
    protected String createDate;

    /**
     * 创建人
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "创建人")
    protected String createBy;

    /**
     * 修改时间
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "修改时间")
    protected String modifyDate;

    /**
     * 修改人
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "修改人")
    protected String modifyBy;

    /**
     * 状态 0 无效 1 有效
     */
    @Getter
    @Setter
    @ApiModelProperty(value = "状态")
    protected Integer status;

    public static final Integer STATUS_ACTIVE = 1;
    public static final Integer STATUS_INACTIVE = 0;

}
