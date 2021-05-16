package com.atguigu.java;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

    @Test
    public void test1(){
        InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getByName("www.baidu.com");
            System.out.println(inetAddress);
            inetAddress = InetAddress.getLocalHost();
            System.out.println(inetAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
}
