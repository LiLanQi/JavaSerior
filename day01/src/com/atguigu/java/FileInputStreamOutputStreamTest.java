package com.atguigu.java;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 對於文本文件（。txt,.java,.c,.cpp)，使用字符流處理
 * 對於飛文本文件(.jpg,.mp3,.mp4,.doc)，使用字節流處理
 */
public class FileInputStreamOutputStreamTest {

    @Test
    public void testFileInputStream(){
        File file = new File("hello.txt");
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] bbuf = new byte[5];
            int len;
            while((len = fileInputStream.read(bbuf)) != -1) {
                for (int i = 0; i < len; i++) {
                    System.out.print((char)bbuf[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testFileInputStreamOutputStream(){
        File srcFile = new File("oneyuu200.jpg");
        File destFile = new File("oneyuu201.jpg");

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(srcFile);
            fileOutputStream = new FileOutputStream(destFile);

            byte[] bbuf = new byte[5];
            int len;
            while((len = fileInputStream.read(bbuf)) != -1) {
                fileOutputStream.write(bbuf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fileOutputStream != null)
                    fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
