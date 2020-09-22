package com.mall.basic.from;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * @Author: C-aranlism create by 2020/9/22.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Data
@Accessors(chain = true)
public class AuthFrom {

    @ApiModelProperty(value = "用户名",required = true)
    @NotBlank(message = "用户名不能为空")
    private String name;

    @ApiModelProperty(value = "登录密码",required = true)
    @NotBlank(message = "登录密码不能为空")
    private String password;

}
