package com.mall.geteway.provider;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @Created with IntelliJ IDEA.
 * @Author: C-aranlism
 * @Date: 2020/9/22
 * @Description Swagger服务列表从nacos中获取
 */
@Primary
@Component
@AllArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {

    @Autowired
    private GatewayProperties gatewayProperties;

    @Autowired
    private DiscoveryClient discoveryClient;

    public static final String API_URI = "/v2/api-docs";

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();
        //取出gateway的route
        discoveryClient.getServices().forEach(route -> routes.add(route));
        //结合配置的route-路径(Path)，和route过滤，只获取有效的route节点
        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
            .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
                .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                    .forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(),
                        predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
                                .replace("/**", API_URI), "2.0"))));

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        return new SwaggerResource() {
            {
                setName(name);
                setLocation(location);
                setSwaggerVersion(version);
            }
        };
    }

}
