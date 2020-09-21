package com.mall.common.utils;

import com.mall.common.bean.Api;
import com.mall.common.constants.SystemConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @Author: C-aranlism
 * @Date: 2020/9/21
 */
@Component
public class ApiUtils {

    public static boolean enable;

    @Value("${swagger.enable}")
    private void setEnable(boolean _enable){
        enable = _enable;
    }

    private final static String VERSION = "2.0";

    public static final List<ResponseMessage> responseMessageList = new ArrayList(){
        {
            add(new ResponseMessageBuilder().code(200).message("返回正常").responseModel(new ModelRef("API-OK")).build());
            add(new ResponseMessageBuilder().code(404).message("找不到资源").responseModel(new ModelRef("API-ERROR")).build());
            add(new ResponseMessageBuilder().code(405).message("业务逻辑异常").responseModel(new ModelRef("API-ERROR")).build());
            add(new ResponseMessageBuilder().code(406).message("参数校验异常").responseModel(new ModelRef("API-ERROR")).build());
            add(new ResponseMessageBuilder().code(500).message("服务器内部错误").responseModel(new ModelRef("API-ERROR")).build());
            add(new ResponseMessageBuilder().code(503).message("服务器内部错误").responseModel(new ModelRef("API-ERROR")).build());
        }
    };

    public static ApiInfo createApi(Api api){
        return new ApiInfoBuilder().
                title(api.getTitle()).
                description(api.getDescription()).
                contact(new Contact(api.getAuthor() , api.getUrl() , api.getEmail())).
                version(VERSION).
                build();
    }

    public static List<ApiKey> securitySchemes(){
        List<ApiKey> apiKeyList= new ArrayList();
        apiKeyList.add(new ApiKey(SystemConstant.HEADER_AUTHORIZATION, SystemConstant.HEADER_AUTHORIZATION, "header"));
        return apiKeyList;
    }

    public static List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts=new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }

    static List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences=new ArrayList<>();
        securityReferences.add(new SecurityReference(SystemConstant.HEADER_AUTHORIZATION , authorizationScopes));
        return securityReferences;
    }

}
