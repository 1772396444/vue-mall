package com.mall.common.bean;

import com.mall.common.constants.SystemConstant;
import com.mall.common.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @Created with IntelliJ IDEA.
 * @Author: C-aranlism
 * @Date: 2020/9/21
 */
@Slf4j
@Component
public class TokenResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Token.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        String authorization = Objects.requireNonNull(request).getHeader(SystemConstant.HEADER_AUTHORIZATION);
        Map<String , Object> map = TokenUtils.verify(authorization);
        if(MapUtils.isEmpty(map)){
            return null;
        }
        return new Token(){
            {
                setId(MapUtils.getString(map , "id"));
                setUserId(MapUtils.getString(map, "userId"));
                setRoleId(MapUtils.getString(map, "roleId"));
                setStaffId(MapUtils.getString(map, "staffId"));
            }
        };
    }

}
