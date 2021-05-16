package com;

import com.fym.dao.UserDao;
import com.fym.dao.UserDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class SpringAOPTest {

    public static void main(String[] args) {
        //创建接口实现类的代理对象
        Class[] interfaces = { UserDao.class};
        UserDaoImpl userDao = new UserDaoImpl();
        UserDao u = (UserDao)Proxy.newProxyInstance(SpringAOPTest.class.getClassLoader(), interfaces, new UserDaoProxy(userDao));
        int result = u.add(1,2);
        System.out.println("result = " + result);
    }
}

//创建代理对象代码
class UserDaoProxy implements InvocationHandler{

    //1.把创建的是谁的代理对象，把谁传递过来
    private Object obj;
    public UserDaoProxy(Object obj){
        this.obj = obj;
    }

    //增强的逻辑
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //方法之前
        System.out.println("方法之前执行。。。" +method.getName() + "传递的参数" + Arrays.toString(args));

        //当前方法，可以在此利用method.getName（）判断当前方法是哪一个，进而对不同的方法进行不同的操作
        Object res = method.invoke(obj, args);
        //方法之后
        System.out.println("方法之后执行。。。" +obj);

        return res;
    }
}