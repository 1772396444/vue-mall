package com.mall.basic.controller;

import com.mall.basic.from.AuthFrom;
import com.mall.basic.mgr.AuthMgr;
import com.mall.common.controller.ApiBaseController;
import com.mall.common.utils.TokenUtils;
import com.mall.entity.basic.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.MapUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: C-aranlism create by 2020/9/22.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Log4j2
@Validated
@RestController
@Api(tags = "系统登录/退出")
public class AuthController extends ApiBaseController {

    @Autowired
    private AuthMgr authMgr;

    @ApiOperation(value = "登录授权", notes = "不进行拦截")
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody @Valid AuthFrom from) {
        User user = authMgr.loginValid(from);

        Map resultMap = successMap();
        Map<String, Object> claims = new HashMap<>(5);
        claims.put("id", from.getName());
        claims.put("userId", user.getId());
        claims.put("userName", user.getName());
        claims.put("staffId", MapUtils.getString(resultMap, "staffId", user.getId()));
        claims.put("ouId", MapUtils.getString(resultMap, "ouId", user.getId()));
        claims.put("roleId", user.getCurrentRole().getId());
        claims.put("client", user.getClient());
        String token = TokenUtils.sign(user.getId(), claims);
        Map<String, Object> map = successMap("用户登录成功");
        resultMap.put("token", token);
        map.put("data", resultMap);
        return map;
    }

    @ApiOperation(value = "退出信息")
    @GetMapping("/logout")
    public Map<String, Object> logout() {
        SecurityUtils.getSubject().logout();
        return successMap("退出完成");
    }

}
