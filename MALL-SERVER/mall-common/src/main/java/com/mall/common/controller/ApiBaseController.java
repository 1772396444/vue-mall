package com.mall.common.controller;

import com.alibaba.fastjson.JSON;
import com.mall.common.utils.MessageUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SignatureException;
import java.util.List;
import java.util.Map;

/**
 * @Author: C-aranlism create by 2020/9/21.
 * @Version 1.0
 * @Parm request
 * @Description
 */
public class ApiBaseController extends BaseController {

    @ExceptionHandler(Exception.class)
    public void handlerException(Exception e, HttpServletResponse response) {
        Map<String, Object> map = failure("服务端异常[00]");
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException me = (MethodArgumentNotValidException) e;
            BindingResult result = me.getBindingResult();
            if (result.hasErrors()) {
                List<ObjectError> allErrors = result.getAllErrors();
                StringBuilder sb = new StringBuilder();
                for (ObjectError objectError : allErrors) {
                    sb.append(objectError.getDefaultMessage()).append(";");
                }
                map = MessageUtils.failure(400, sb.toString());
            }
        } else if (e instanceof IllegalArgumentException || e instanceof SignatureException || e instanceof ExpiredJwtException || e instanceof MalformedJwtException) {
            String message = e.getMessage();
            map = MessageUtils.failure(400, message);
        } else if (e instanceof UnauthorizedException || e instanceof AuthenticationException) {
            map = MessageUtils.failure(403, "该角色没有访问该资源的权限[02]");
        } else {
            e.printStackTrace();
            String message = "服务器异常:" + e.getMessage();
            map = MessageUtils.failure(500, message);
        }
        try {
            response.setContentType("text/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(JSON.toJSONString(map));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
