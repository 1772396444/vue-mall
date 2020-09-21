package com.mall.common.bean;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Created with IntelliJ IDEA.
 * @Author: C-aranlism
 * @Date: 2020/9/21
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String _token){
        token = _token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
