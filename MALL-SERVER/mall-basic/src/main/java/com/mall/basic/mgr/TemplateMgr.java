package com.mall.basic.mgr;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @Author: C-aranlism create by 2020/9/26.
 * @Version 1.0
 * @Parm request
 * @Description
 */
public interface TemplateMgr {

    /**
     * 模版列表
     */
    Page find();

}
