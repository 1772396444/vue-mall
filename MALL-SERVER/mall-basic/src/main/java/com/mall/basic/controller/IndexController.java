package com.mall.basic.controller;

import com.mall.common.controller.ApiBaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Created with IntelliJ IDEA.
 * @Author: C-aranlism
 * @Date: 2020/9/21
 */
@Api(tags = "测试1")
@RestController
public class IndexController extends ApiBaseController {

    @RequestMapping("/index")
    public Map<String, Object> index(){
        return success("成功");
    }

}
