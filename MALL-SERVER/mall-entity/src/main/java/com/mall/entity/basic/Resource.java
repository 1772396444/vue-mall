package com.mall.entity.basic;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.entity.base.BaseEntity;
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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mall_resource")
@NoArgsConstructor
public class Resource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    public static final Integer TYPE_MENU = 1;
    public static final Integer TYPE_BTN = 0;

    private String id;

    private String defaultIcon;

    private Integer menu;

    private String name;

    private String parentId;

    private String permission;

    private String url;

    private Integer sorts;

    private Integer type;

    private transient String hasMenu;

}
