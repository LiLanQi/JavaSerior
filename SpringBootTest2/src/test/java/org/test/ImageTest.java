package org.test;
import com.squareup.square.SquareClient;
import com.squareup.square.api.CatalogApi;
import com.squareup.square.models.CatalogImage;
import com.squareup.square.models.CatalogObject;
import com.squareup.square.models.CreateCatalogImageRequest;
import com.squareup.square.models.CreateCatalogImageResponse;
import com.squareup.square.utilities.FileWrapper;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class ImageTest {
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


    @Test
    public static void main(String[] args) {
        SquareClient client = new SquareClient.Builder()
                .httpClientConfig(configBuilder -> configBuilder
                        .timeout(0))
                .squareVersion("2021-05-13")
                .accessToken("EAAAEIl3awo49_UE8Is_9S0pAp2n_0-fIrDfgDnVoSZYU5_dD4ZhWbnPH534oBGh")
                .build();
        CatalogApi catalogApi = client.getCatalogApi();

        CatalogImage imageData = new CatalogImage.Builder()
                .name("eagle")
                .build();

        CatalogObject image = new CatalogObject.Builder("IMAGE", "#111111")
                .imageData(imageData)
                .build();

        CreateCatalogImageRequest request = new CreateCatalogImageRequest.Builder(UUID.randomUUID().toString())
                .image(image)
                .build();

        // Modify this to point to your desired file.

        String picturePath = downLoadFromUrl("http://buk.mixshop.world/fb76ad3e374508c30c0fb79e2d38b71f?alt=media", "Picture.jpg", "D:\\1688pic");
        File imageFile = new File(picturePath);
        FileWrapper f = new FileWrapper(imageFile);

        catalogApi.createCatalogImageAsync(request, f)
                .thenAccept(result -> {
                    String imageId = result.getImage().getId();
                    Long version = result.getImage().getVersion();
                    String name = result.getImage().getImageData().getName();
                    String url = result.getImage().getImageData().getUrl();
                    System.out.println(result);
                    System.out.println("imageId=" + imageId);
                    System.out.println("version=" + version);
                    System.out.println("name=" + name);
                    System.out.println("url=" + url);
                    System.out.println("Success!");
                })
                .exceptionally(exception -> {
                    System.out.println("Failed to make the request");
                    System.out.println(String.format("Exception: %s", exception.getMessage()));
                    return null;
                });
    }

}
