package com.mall.entity.basic;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mall.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
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
@TableName("mall_template_detail")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "模版列表", description = "Template Entity")
public class TemplateDetail extends BaseEntity {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 模版ID
     */
    private String templateId;

}
