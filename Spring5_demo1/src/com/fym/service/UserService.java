package com.fym.service;


import com.fym.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/*
在注解里面value属性可以省略不写，默认值是类名称，首字母小写
使用4大注解中的任何一个注解都可以，但是一般有使用习惯
 */
@Service(value = "userService")//等价于注解注入bean
public class UserService {
    //定义dao类型属性
    //不需要set方法
//    @Autowired
//    @Qualifier(value = "userDaoImpl1")//一般与Autowired搭配使用，根据名称注入
    private UserDao userDao;//根据类型注入

    public void add(int a,int b){
        userDao.add(a, b);
        System.out.println("service add...");
    }
}
