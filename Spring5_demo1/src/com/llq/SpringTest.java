package com.llq;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringTest {

    @Test
    public void test1(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("user.xml");
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);
        user.add();
        user.show();
    }

    @Test
    public void test2(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("user.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.add();

    }

    @Test
    public void test3(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("user.xml");
        Employee employee = applicationContext.getBean("employee", Employee.class);
        System.out.println(employee.toString());

    }

    @Test
    public void test4(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("user.xml");
        Student student = applicationContext.getBean("student", Student.class);
        System.out.println(student.toString());
    }

    @Test
    public void test5(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("user.xml");
        Course course = applicationContext.getBean("myBean", Course.class);//工厂类
        System.out.println(course.toString());
    }
}
