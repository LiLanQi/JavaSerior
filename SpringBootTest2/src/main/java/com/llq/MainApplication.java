package com.llq;

import ch.qos.logback.core.db.DBHelper;
import com.llq.bean.Car;
import com.llq.bean.Pet;
import com.llq.bean.User;
import com.llq.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/*
主程序类
用该注解告诉SpringBoot这是一个SpringBoot应用

@Import({User.class, DBHelper.class})
给容器中自动创建出这俩个类型的组件，默认无参构造方法，如果User中没有无参构造，则报错。默认组件名字为全类名
 */
@SpringBootApplication
@Import({User.class, DBHelper.class})
@ImportResource("classpath:bean2.xml")
@EnableConfigurationProperties(Car.class)
/*
@EnableConfigurationProperties作用：
开启car配置绑定功能
把这car组件自动注册到容器中，可以省略@Conponent注解
 */
public class MainApplication {

    public static void main(String[] args) {
        //返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
        //查看容器里面的组件
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for(String str:beanDefinitionNames){
            System.out.println(str);
        }
        /*//从容器中获取组件
        Pet pet1 = run.getBean("Pet", Pet.class);
        Pet pet2 = run.getBean("Pet", Pet.class);
        System.out.println("组件："+(pet1==pet2));

        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean);

        User user1 = bean.user01();
        User user2 = bean.user01();
        *//*
        @Configuration(proxyBeanMethods = true)时，下面都返回true
        @Configuration(proxyBeanMethods = false)时，下面都返回false
         *//*
        System.out.println(user1==user2);
        System.out.println(user1.getPet()==pet1);

        System.out.println("-----------------");
        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        for (String str:beanNamesForType) {
            System.out.println(str);
        }
        String[] beanNamesForType1 = run.getBeanNamesForType(DBHelper.class);
        for (String str:beanNamesForType1) {
            System.out.println(str);
        }*/

      /*  boolean user01 = run.containsBean("user01");
        System.out.println(user01);

        boolean pet = run.containsBean("Pet");
        System.out.println(pet);*/

        boolean bean1 = run.containsBean("bean1");
        System.out.println(bean1);

        boolean bean2 = run.containsBean("bean2");
        System.out.println(bean2);
    }
}
