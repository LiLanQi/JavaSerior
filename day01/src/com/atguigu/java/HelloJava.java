package com.atguigu.java;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class HelloJava {
    public static void main(String[] args) {
        /**
         * 字符串转为日期以及日期转为字符串
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(new Date());
        System.out.println(format);
        System.out.println("-------------");
        try{
            Date parse = simpleDateFormat.parse(format);
            System.out.println(parse);
        }catch (Exception e){

        }
    }

    @Test
    public void test1(){
        String birth = "2020-09-08";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date parse = simpleDateFormat.parse(birth);
            System.out.println(parse);
        }catch (Exception e){

        }

    }

    @Test
    public void test2(){
        LocalDate of = LocalDate.of(2020, 10, 10);
        System.out.println(of);

        LocalTime of1 = LocalTime.of(10, 10, 20);
        String str = of.toString()+" "+of1.toString();
        System.out.println(str);

    }

    @Test
    public void test3(){
        double a = 3.14154456456456459;
        double b = 4.213164;
        String format = String.format("%.7f", a);
        double v = Double.parseDouble(format);
        System.out.println(v);

    }
    
    
}
