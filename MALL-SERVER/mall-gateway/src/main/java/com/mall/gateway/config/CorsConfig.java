package com.mall.geteway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @Author:joker
 * @Date:2020/9/22
 * @version:1.0
 */
@Configuration
public class CorsConfig {
    @Bean
    public CorsWebFilter corsWebFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration() {{
            addAllowedMethod("*");

            addAllowedHeader("*");
            addAllowedOrigin("http://localhost:3000");//前端请求地址
            addAllowedOrigin("http://localhost:10086");//管理系统请求地址

            //2) 是否发送Cookie信息
            setAllowCredentials(true);
            //3) 允许的请求方式
            addAllowedMethod("OPTIONS");
            addAllowedMethod("HEAD");
            addAllowedMethod("GET");
            addAllowedMethod("PUT");
            addAllowedMethod("POST");
            addAllowedMethod("DELETE");
            addAllowedMethod("PATCH");
        }};

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource(new PathPatternParser()) {{
            registerCorsConfiguration("/**", corsConfiguration);
        }};
        return new CorsWebFilter(urlBasedCorsConfigurationSource);
    }
}
