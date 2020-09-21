package com.mall.common.config;

import com.mall.common.bean.JWTRealm;
import com.mall.common.filter.shiro.JWTFilter;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Created with IntelliJ IDEA.
 * @Author: C-aranlism
 * @Date: 2020/9/21
 */
@Configuration
public class ShiroConfig {

    @Bean("ehCacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager() {
        //使用ehcache缓存
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:cache/ehcache.xml");
        return ehCacheManager;
    }

    @Bean
    public JWTRealm jwtRealm() {
        JWTRealm realm = new JWTRealm();
        //启用缓存  否则每次都查询数据库
        realm.setCachingEnabled(true);
        realm.setAuthenticationCacheName("etms-authentication");
        realm.setAuthenticationCachingEnabled(true);
        realm.setAuthorizationCacheName("etms-authorization");
        realm.setAuthorizationCachingEnabled(true);
        return realm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(JWTRealm realm, EhCacheManager cacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        //设置缓存
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());
        factoryBean.setFilters(filterMap);

        factoryBean.setSecurityManager(securityManager);
        factoryBean.setUnauthorizedUrl("/401");
        Map<String, String> filterRuleMap = new HashMap<>(15);
        // 所有的请求通过ShiroFilter执行处理
        filterRuleMap.put("/**", "jwt");
        // 排除路径，ShiroFilter将不做过滤的操作
        // filterRuleMap.put("/system/**", "anon");
        filterRuleMap.put("/doc.html", "anon");
        filterRuleMap.put("/authorization/**", "anon");
        filterRuleMap.put("/401", "anon");
        filterRuleMap.put("/v2/api-docs/**", "anon");
        filterRuleMap.put("/webjars/**", "anon");
        filterRuleMap.put("/swagger-resources/**", "anon");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);

        return factoryBean;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
