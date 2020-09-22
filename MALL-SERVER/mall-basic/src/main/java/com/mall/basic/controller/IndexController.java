package com.mall.basic.controller;

import com.mall.common.controller.ApiBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: C-aranlism create by 2020/9/22.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Api("测试拦截")
@RestController
public class IndexController extends ApiBaseController {

    @ApiOperation(value = "测试index")
    @PostMapping("index")
    public Map<String , Object> index(){
        return success("成功");
    }

}
