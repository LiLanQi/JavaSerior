package com.llq.config;

import com.llq.bean.Pet;
import com.llq.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
/*
配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的
配置类本身也是组件
proxyBeanMethods：代理bean的方法
Full(proxyBeanMethods = true)
lite(proxyBeanMethods = false)
组件依赖
 */
//表明只有含有id为Pet的组件时，MyConfig类下面的所有组件才能执行，否则不执行
@Configuration(proxyBeanMethods = true)//告诉SpringBoot这是一个配置类,proxyBeanMethods = true为默认，表示单利，false为多列表
public class MyConfig {

    /*
    外部无论对配置类中的这个组件注册方式调用多少次，获取的都是之前注册容器中的单实例对象
     */


    @Bean//给容器中添加组件，以方法名作为组件的id，返回类型是组件的类型，返回的值就是组件在容器中的实例
    public User user01(){
        User user = new User("小明", 18);
        user.setPet(littlePet());
        return user;
    }


    @Bean("Pet")//该注解里面的值为组件id
    public Pet littlePet(){
        return new Pet("小黑");
    }
}
