package com.mall.basic.controller;

import com.mall.common.bean.ApiResponse;
import com.mall.common.controller.ApiBaseController;
import com.mall.entity.basic.Template;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: C-aranlism create by 2020/9/26.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Api(tags = "模版管理")
@RestController
@RequestMapping("/template")
public class TemplateController extends ApiBaseController {

    @ApiOperation(value = "获取模版资源")
    @PostMapping("/list")
    public ApiResponse list() {
        return success(Template.class);
    }

}
