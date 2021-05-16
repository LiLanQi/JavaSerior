package com.llq;

import org.springframework.beans.factory.FactoryBean;

//工厂bean,实现FactoryBean接口，设置泛型，并且实现其抽象方法，
public class MyBean implements FactoryBean<Course> {
    //定义返回bean
    @Override
    public Course getObject() throws Exception {
        Course course = new Course();
        course.setCname("英语");
        return course;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
