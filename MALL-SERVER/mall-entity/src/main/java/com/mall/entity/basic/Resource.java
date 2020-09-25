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
 * @Author: C-aranlism create by 2020/9/22.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("mall_resource")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "资源列表", description = "Resource Entity")
public class Resource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public static final Integer TYPE_MENU = 1;
    public static final Integer TYPE_BTN = 0;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键id")
    private String id;

    /**
     * 资源默认图标
     */
    @ApiModelProperty(value = "资源默认图标")
    private String defaultIcon;

    /**
     * 资源模块
     */
    @ApiModelProperty(value = "资源模块")
    private Integer menu;

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称")
    private String name;

    /**
     * 父模块ID
     */
    @ApiModelProperty(value = "父模块ID")
    private String parentId;

    /**
     * 权限
     */
    @ApiModelProperty(value = "权限")
    private String permission;

    /**
     * 资源访问路径
     */
    @ApiModelProperty(value = "资源访问路径")
    private String url;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    private Integer sorts;

    /**
     * 资源类型
     */
    @ApiModelProperty(value = "排序字段")
    private Integer type;

    private transient String hasMenu;

}
