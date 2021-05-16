package com;

import com.fym.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAop {

    @Test
    public void testAopAnno(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop.xml");
        User user = applicationContext.getBean("user", User.class);
        user.add();
    }
}
