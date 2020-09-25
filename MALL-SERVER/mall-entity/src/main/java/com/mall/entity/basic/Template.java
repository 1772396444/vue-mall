package com.mall.entity.basic;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: C-aranlism create by 2020/9/26.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("mall_template")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "模版列表", description = "Template Entity")
public class Template extends BaseEntity {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 模版CODE
     */
    @ApiModelProperty(value = "模版CODE")
    private String code;

    /**
     * 模版名称
     */
    @ApiModelProperty(value = "模版名称")
    private String name;

}
