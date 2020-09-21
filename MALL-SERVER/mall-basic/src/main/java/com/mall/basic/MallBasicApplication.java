package com.mall.basic;

import com.mall.common.bean.Api;
import com.mall.common.bean.Token;
import com.mall.common.utils.ApiUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class MallBasicApplication {

    public static void main(String[] args) {
        new SpringApplication(MallBasicApplication.class).run(args);
    }

    @Bean
    public Docket createBasicApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.POST , ApiUtils.responseMessageList)
                .globalResponseMessage(RequestMethod.GET , ApiUtils.responseMessageList)
                .enable(ApiUtils.enable)
                .ignoredParameterTypes(Token.class)
                .apiInfo(ApiUtils.createApi(
                        new Api().title("Mall Service API")
                                .description("Mall Service Basic API")
                                .author("C-aranlism")
                                .url("www.c-aranlism.top")
                                .email("c-aranlism@qq.com")
                ))
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(ApiUtils.securitySchemes())
                .securityContexts(ApiUtils.securityContexts());
    }

}
