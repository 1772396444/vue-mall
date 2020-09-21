package com.mall.common.config;

import com.alibaba.fastjson.JSON;
import com.mall.common.constants.SystemConstant;
import com.mall.common.filter.page.PageFilter;
import com.mall.common.utils.MessageUtils;
import com.mall.common.utils.TokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @Author: C-aranlism create by 2020/9/21.
 * @Version 1.0
 * @Parm request
 * @Description
 */
@Slf4j
@Configuration
public class ApiConfig {

    @Bean
    public FilterRegistrationBean<PageFilter> pagerFilter(){
        return new FilterRegistrationBean<PageFilter>(){
            {
                setOrder(1);
                addUrlPatterns("/*");
                setName("PageFilter");
                setFilter(new PageFilter());
                addInitParameter("pageSize" , "10");
            }
        };
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> tokenFilter(){
        return new FilterRegistrationBean<JwtFilter>(){
            {
                setOrder(2);
                setFilter(new JwtFilter());
                setUrlPatterns(new ArrayList(){
                    {
                        add("/basic/*");
                    }
                });
            }
        };
    }

    class JwtFilter implements Filter {

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String authorization = request.getHeader(SystemConstant.HEADER_AUTHORIZATION);
            if(RequestMethod.OPTIONS.name().equals(request.getMethod())){
                response.setStatus(HttpServletResponse.SC_OK);
                filterChain.doFilter(request , response);
            }else{
                if(StringUtils.isBlank(authorization)){
                    response.setContentType("text/json;charset=UTF-8");
                    response.getWriter().println(JSON.toJSON(MessageUtils.failure("错误或者无效的认证")));
                }else{
                    try {
                        TokenUtils.verify(authorization);
                        filterChain.doFilter(request , response);
                    } catch (ExpiredJwtException | MalformedJwtException e) {
                        e.printStackTrace();
                        outClient(response, 400, e.getMessage());
                    } catch (UnauthorizedException | AuthenticationException e) {
                        e.printStackTrace();
                        outClient(response, 401, e.getMessage());
                    } catch (Exception e) {
                        e.printStackTrace();
                        outClient(response, 408, e.getMessage());
                    }
                }
            }

        }

        public void outClient(HttpServletResponse response, int code, String message) {
            response.setContentType("text/json;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            Map<String, Object> map = MessageUtils.failure(code, message);
            try {
                response.getWriter().println(JSON.toJSON(map));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
