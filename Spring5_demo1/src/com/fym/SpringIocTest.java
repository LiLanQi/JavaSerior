package com.fym;

import com.fym.config.SpringConfig;
import com.fym.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
利用注解实现对象创建
1.引入依赖jar包
2.开启组件扫描
3.创建类，在类上面加上注解
 */
public class SpringIocTest {


    public void testService(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("fym.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.add(1, 2);
        System.out.println(userService);
    }


    public void testService1(){
        //加载配置类
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.add(1,2);
        System.out.println(userService);
    }
}
