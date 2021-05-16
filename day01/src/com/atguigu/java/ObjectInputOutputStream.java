package com.atguigu.java;

import org.junit.Test;

import java.io.*;

public class ObjectInputOutputStream {
    /*
    序列化：将内存中的java对象保存到磁盘中或通过网络传输出去
     */
    @Test
    public void testt1() throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("llq.dat"));
        objectOutputStream.writeObject(new String("一二三四"));
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /*
    反序列化
     */
    @Test
    public void test2() throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("llq.dat"));
        Object o = objectInputStream.readObject();
        String str = (String) o;
        System.out.println(str);
        objectInputStream.close();
    }




}
