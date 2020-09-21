package com.mall.common.config;

import com.mall.common.constants.SystemConstant;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @Created with IntelliJ IDEA.
 * @Author: C-aranlism
 * @Date: 2020/9/21
 * @Description 服务间调用在请求头中统一加入token
 */
@Configuration
public class FeignConfig implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        requestTemplate.header(SystemConstant.HEADER_AUTHORIZATION , attributes.getRequest().getHeader(SystemConstant.HEADER_AUTHORIZATION));
    }

}
