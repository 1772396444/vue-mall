package com.mall.common.filter.shiro;

import com.alibaba.fastjson.JSON;
import com.mall.common.bean.JWTToken;
import com.mall.common.constants.SystemConstant;
import com.mall.common.utils.MessageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @Author: C-aranlism
 * @Date: 2020/9/21
 */
@Slf4j
public class JWTFilter extends BasicHttpAuthenticationFilter {

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    private static final String EXCLUSIVE_URL = "/login";

    @Override
    protected boolean isLoginAttempt(ServletRequest request , ServletResponse response) {
        return ((HttpServletRequest) request).getHeader(SystemConstant.HEADER_AUTHORIZATION) != null;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest req, ServletResponse response, Object mappedValue) {
        HttpServletRequest request = (HttpServletRequest) req;
        if(antPathMatcher.match(EXCLUSIVE_URL , request.getRequestURI())){
            return true;
        }
        if(isLoginAttempt(req , response)){
            boolean result = false;
            try {
                result = executeLogin(req , response);
            }catch (Exception e){
                response.setContentType("text/json;charset=UTF-8");
                try {
                    response.getWriter().println(JSON.toJSONString(MessageUtils.failure(e.getMessage())));
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                return false;
            }
            if(!result){
                abort401(req , response);
            }
            return true;
        }
        return abort401(req , response);
    }

    @Override
    protected boolean executeLogin(ServletRequest req, ServletResponse response) {
        HttpServletRequest request = (HttpServletRequest) req;
        getSubject(req , response).login(new JWTToken(request.getHeader(SystemConstant.HEADER_AUTHORIZATION)));
        return true;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        try {
            return super.preHandle(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean abort401(ServletRequest request, ServletResponse response){
        try {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.setContentType("text/json;charset=UTF-8");
            resp.getWriter().write(JSON.toJSONString(MessageUtils.failure(404, "请求的资源不存在")));
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return false;
    }

}
