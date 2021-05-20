package org.test;
import java.io.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.*;

public class TestFile {

    public static String downLoadFromUrl(String urlStr, String fileName, String savePath) {
        try {

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置超时间为3秒
            conn.setConnectTimeout(3 * 1000);
            // 防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            // 得到输入流
            InputStream inputStream = conn.getInputStream();
            // 获取字节数组
            byte[] getData = readInputStream(inputStream);

            // 文件保存位置
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            File file = new File(saveDir + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getData);
            if (fos != null) {
                fos.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            // System.out.println("info:"+url+" download success");
            return saveDir + File.separator + fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static void main(String[] args) throws Exception {
          String strUrl = "http://buk.mixshop.world/fb76ad3e374508c30c0fb79e2d38b71f?alt=media";
//        try {
//            URL url = new URL(strUrl);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setConnectTimeout(5 * 1000);
//            // 通过输入流获取图片数据
//            // 得到图片的二进制数据
//            byte[] bytes = readInputStream(conn.getInputStream());
//
//        } catch (Exception e) {
//
//        }

        String picturePath = downLoadFromUrl(strUrl, "Picture.jpg", "D:\\1688pic");
        System.out.println(picturePath);

    }
}
