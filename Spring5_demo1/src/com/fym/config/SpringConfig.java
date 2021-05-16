package com.fym.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration//作为配置类，替代xml文件
@ComponentScan(basePackages = {"com.fym"})
public class SpringConfig {

}
