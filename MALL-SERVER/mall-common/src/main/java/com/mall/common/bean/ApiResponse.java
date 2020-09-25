package com.mall.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: C-aranlism create by 2020/9/25.
 * @Version 1.0
 * @Parm request
 * @Description 由于返回必须是对象，包装类型不能渲染 定义描述字段
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "通用返回API" , description = "ApiResponse")
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -8987146499044811408L;

    /**
     * 返回状态码
     */
    @ApiModelProperty(value = "返回状态码", required = true)
    private Integer code;

    /**
     * 返回状态
     */
    @ApiModelProperty(value = "返回状态", required = true)
    private Boolean status;

    /**
     * 返回信息
     */
    @ApiModelProperty(value = "返回信息", required = true)
    private String message;

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回数据", required = true)
    private Object data;

    /**
     * 返回字段描述
     */
    @ApiModelProperty(value = "返回字段描述", required = true)
    private T description;

}
