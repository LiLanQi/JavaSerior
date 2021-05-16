package com.llq.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
只有在容器中的组件，才会拥有springboot提供的强大功能，因此在这里要加入@Component
 */
//@Component
@ConfigurationProperties(prefix = "mycar")//以mycar开头
public class Car {
    private String brand;
    private Integer price;

    public String getBrand() {
        return brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}
