package com.atguigu.java;

import org.junit.Test;

import java.io.*;

public class BufferedTest {

    @Test
    public  void test1(){
        File srcFile = new File("oneyuu200.jpg");
        File destFile = new File("oneyuu1230.jpg");

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            fileInputStream = new FileInputStream(srcFile);
            fileOutputStream = new FileOutputStream(destFile);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            int len;
            byte[] buff = new byte[1024];
            while((len = bufferedInputStream.read(buff)) != -1) {
                bufferedOutputStream.write(buff,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bufferedInputStream != null)
                    bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(bufferedOutputStream != null)
                    bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2(){
    }
}
