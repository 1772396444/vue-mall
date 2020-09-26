package com.mall.basic.controller;

import com.mall.basic.mgr.ResourceMgr;
import com.mall.common.bean.ApiResponse;
import com.mall.common.bean.Token;
import com.mall.common.controller.ApiBaseController;
import com.mall.entity.basic.Resource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: C-aranlism create by 2020/9/24.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Api(tags = "资源管理")
@RestController
@RequestMapping("/resource")
public class ResourceController extends ApiBaseController {

    @Autowired
    private ResourceMgr resourceMgr;

    @ApiOperation(value = "获取当前角色菜单")
    @PostMapping("/menuList")
    public ApiResponse<Resource> menuList(Token token) {
        return success(Resource.class , resourceMgr.menuList(token.getRoleId()));
    }

}
