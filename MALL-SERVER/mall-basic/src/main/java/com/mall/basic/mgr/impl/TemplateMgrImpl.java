//package com.mall.basic.mgr.impl;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import com.mall.basic.mgr.TemplateMgr;
//import com.mall.basic.service.TemplateService;
//import com.mall.common.utils.PageUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * @Author: C-aranlism create by 2020/9/26.
// * @Version 1.0
// * @Parm request
// * @Description
// */
//@Service
//@Transactional(rollbackFor = RuntimeException.class)
//public class TemplateMgrImpl implements TemplateMgr {
//
//    @Autowired
//    TemplateService templateService;
//
//    @Override
//    public Page find() {
//        Page page = PageUtils.getPage();
//        page.setRecords(templateService.list());
//        return page;
//    }
//
//}
