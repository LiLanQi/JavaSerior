package com.atguigu.java;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileTest{

    public static void main(String[] args) {
        File file = new File("hello.txt");//相較於當前工程
        System.out.println(file.getAbsolutePath());
    }

    @Test
    public void testFileReader() {
        //讀入的文件必須存在，不然報異常
        File file = new File("hello.txt");//相較於當前Modul
        FileReader fileReader = null;
        try {
            System.out.println(file.getAbsolutePath());
            fileReader = new FileReader(file);
            //返回讀入的一個字符，如果達到文件末尾，返回-1.
            int read = fileReader.read();
            while(read != -1) {
                System.out.print((char)read);
                read = fileReader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testFileReader1()  {
        //1.File類的實例化
        File file = new File("hello.txt");
        //2.FileReader流的實例化
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            //3.讀入的操作
            char[] cbuf= new char[5];
            int len = 0;
            while((len = fileReader.read(cbuf)) != -1){
                for (int i = 0; i < len; i++) {
                    System.out.print(cbuf[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.資源的關閉
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    說明：
    1.輸出皂搓，對應的File可以不存在
    如果不存在，在輸出的過程中，回自動創建此對象
    如果存在，看使用的構造器進行操作 new FileWriter(file,true)為在原有文件後面進行追加
    new FileWriter(file)為在替換原有文件
     */
    @Test
    public  void testFileWriter() throws IOException {
        //1.提出File類的對象，指明寫出到的文件
        File file = new File("hello1.txt");
        //2.提供FileWriter的對象，用於數據的寫出
        FileWriter fileWriter = new FileWriter(file,true);
        //3.寫出的操作
        fileWriter.write("I have a dream!\n");
        fileWriter.write("you need to have a dream");
        //4.流資源的關閉
        fileWriter.close();
    }

    @Test
    public void testFileReaderFileWriter(){
        //1.創建File類的對象，指明輸入、輸出文件
        File sourceFile = new File("hello1.txt");
        File destFile = new File("hello2.txt");
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            //2.創建輸入流和輸出流的對象
            fileReader = new FileReader(sourceFile);
            fileWriter = new FileWriter(destFile);

            //3.數據的輸入和寫出操作
            char[] cbuf = new char[5];
            int len = 0;
            while((len = fileReader.read(cbuf)) != -1) {
                fileWriter.write(cbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.關閉流資源
            try {
                if(fileWriter != null)
                    fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(fileReader != null)
                    fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    @Test
    public  void test(){
        File file = new File("hello.txt");
        File file1 = new File("E:\\新建文件夹","hello");
        System.out.println(file.toString());
        System.out.println(file1);
        File file2 = new File(file1,"hello.txt");
        System.out.println(file2);

        System.out.println("------------------------");
        System.out.println(file.getAbsoluteFile());
        System.out.println(file.getPath());
        System.out.println(file.getName());
        System.out.println(file.getParentFile());
        System.out.println(file.length());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = simpleDateFormat.format(new Date(file.lastModified()));
        System.out.println(format);
    }

    @Test
    public  void test1(){
        File file1 = new File("E:\\新建文件夹");
        String[] list = file1.list();
        for (String s: list){
            System.out.println(s);
        }
        File[] files = file1.listFiles();
        for(File f: files) {
            System.out.println(f);
        }

    }


    @Test
    public  void test2() throws IOException {
        File file = new File("E:\\新建文件夹", "hi.txt");
        if(!file.exists()){
            boolean newFile = file.createNewFile();
            System.out.println(newFile);
            System.out.println("創建成功");
        } else {
            file.delete();
            System.out.println("刪除成功");
        }
    }

}
