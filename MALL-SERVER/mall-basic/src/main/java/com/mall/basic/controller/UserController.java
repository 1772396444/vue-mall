package com.mall.basic.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mall.basic.mgr.UserMgr;
import com.mall.common.controller.ApiBaseController;
import com.mall.entity.basic.User;
import io.swagger.annotations.*;
import com.mall.common.bean.ApiResponse;
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
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController extends ApiBaseController {

    @Autowired
    private UserMgr userMgr;

    @ApiOperation(value = "用户管理列表")
    @PostMapping("/find")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id" , value = "用户ID/CODE" , dataType = "string"),
            @ApiImplicitParam(name = "name" , value = "用户姓名" , dataType = "string"),
            @ApiImplicitParam(name = "status" , value = "状态" , dataType = "int"),
    })
    public ApiResponse find(String id , String name , Integer status){
        return success(User.class , userMgr.find(id , name , status));
    }

}
