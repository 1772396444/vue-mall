package com.mall.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Created with IntelliJ IDEA.
 * @Author: C-aranlism
 * @Date: 2020/9/21
 * @Description 移动端版本控制器
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mall")
public class AppConfig {

    private String version;

    private String appKey;

}
