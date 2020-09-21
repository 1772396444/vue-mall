package com.mall.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Created with IntelliJ IDEA.
 * @Author: C-aranlism
 * @Date: 2020/9/21
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext _applicationContext) {
        if(applicationContext == null){
            SpringUtils.applicationContext = _applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name , Class<T> clazz){
        return applicationContext.getBean(name , clazz);
    }

}
