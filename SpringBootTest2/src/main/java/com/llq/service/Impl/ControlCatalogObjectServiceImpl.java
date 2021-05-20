package com.llq.service.Impl;

import com.llq.Dao.Impl.SearchProductDaoImpl;
import com.llq.Dao.SearchProductDao;
import com.llq.bean.ProductReqDto;
import com.llq.service.ControlCatalogObjectService;
import com.squareup.square.SquareClient;
import com.squareup.square.api.CatalogApi;
import com.squareup.square.models.*;
import com.squareup.square.utilities.FileWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class ControlCatalogObjectServiceImpl implements ControlCatalogObjectService {

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


    @Override
    public Object createImageObject(CatalogApi catalogApi,String name, String picPath) {
        CatalogImage imageData = new CatalogImage.Builder()
                .name(name)
                .build();

        CatalogObject image = new CatalogObject.Builder("IMAGE", "#111111")
                .imageData(imageData)
                .build();

        CreateCatalogImageRequest request = new CreateCatalogImageRequest.Builder(UUID.randomUUID().toString())
                .image(image)
                .build();

        // Modify this to point to your desired file.
        String picturePath = downLoadFromUrl(picPath, "Picture.jpg", "D:\\1688pic");
        File imageFile = new File(picturePath);
        FileWrapper f = new FileWrapper(imageFile);
        CreateCatalogImageResponse catalogImage = null;
        try {
            catalogImage = catalogApi.createCatalogImage(request, f);
            System.out.println("插入图片信息=" + catalogImage);
        }catch ( Exception e){

        }finally {
            return catalogImage;
        }
    }

    @Override
    public void upsertCatalogObject(CatalogApi catalogApi,Long price,String selectedCurrency
            ,String name, String selecedPricingType,Long imgVersion,String imgId) {
        Money priceMoney = new Money.Builder()
                .amount(price)
                .currency(selectedCurrency)
                .build();

        CatalogItemVariation itemVariationData = new CatalogItemVariation.Builder()
                .itemId("#qqqqq")
                .name(name)
                .pricingType(selecedPricingType)
                .priceMoney(priceMoney)
                .build();

        CatalogObject catalogObject = new CatalogObject.Builder("ITEM_VARIATION", "#12345")
                .itemVariationData(itemVariationData)
                .build();

        LinkedList<CatalogObject> variations = new LinkedList<>();
        variations.add(catalogObject);

        CatalogItem itemData = new CatalogItem.Builder()
                .name("test111")
                .variations(variations)
                .productType("REGULAR")
                .build();

        CatalogObject object = new CatalogObject.Builder("ITEM", "#qqqqq")
                .version(imgVersion)
                .imageId(imgId)
                .itemData(itemData)
                .build();

        UpsertCatalogObjectRequest body = new UpsertCatalogObjectRequest.Builder(UUID.randomUUID().toString(), object)
                .build();

        catalogApi.upsertCatalogObjectAsync(body)
                .thenAccept(result -> {
                    System.out.println("插入商品信息=" + result);
                    System.out.println("Success!!!!!!");
                })
                .exceptionally(exception -> {
                    System.out.println("Failed to make the request");
                    System.out.println(String.format("Exception: %s", exception.getMessage()));
                    return null;
                });
    }

    @Override
    public  void test(ProductReqDto productReqDto) {
        // 根据商品id和skuId查询商品数据



        System.out.println(productReqDto.toString());
        SquareClient client = new SquareClient.Builder()
                .httpClientConfig(configBuilder -> configBuilder
                        .timeout(0))
                .squareVersion("2021-05-13")
                .accessToken("EAAAEIl3awo49_UE8Is_9S0pAp2n_0-fIrDfgDnVoSZYU5_dD4ZhWbnPH534oBGh")
                .build();
        CatalogApi catalogApi = client.getCatalogApi();
        Money priceMoney = new Money.Builder()
                .amount(1000L)
                .currency("USD")
                .build();

        CatalogItemVariation itemVariationData = new CatalogItemVariation.Builder()
                .itemId("#qqqqq")
                .name("#hhhhh")
                .pricingType("FIXED_PRICING")
                .priceMoney(priceMoney)
                .build();

        CatalogObject catalogObject = new CatalogObject.Builder("ITEM_VARIATION", "#12345")
                .itemVariationData(itemVariationData)
                .build();

        LinkedList<CatalogObject> variations = new LinkedList<>();
        variations.add(catalogObject);

        CatalogItem itemData = new CatalogItem.Builder()
                .name("test")
                .variations(variations)
                .productType("REGULAR")
                .build();

        CatalogObject object = new CatalogObject.Builder("ITEM", "#qqqqq")
                .version(1621397503238L)
                .imageId("ZA36UFQJWU2PFX6LZRBXY3PI")
                .itemData(itemData)
                .build();

        UpsertCatalogObjectRequest body = new UpsertCatalogObjectRequest.Builder(UUID.randomUUID().toString(), object)
                .build();

        catalogApi.upsertCatalogObjectAsync(body)
                .thenAccept(result -> {
                    System.out.println("Success!");
                })
                .exceptionally(exception -> {
                    System.out.println("Failed to make the request");
                    System.out.println(String.format("Exception: %s", exception.getMessage()));
                    return null;
                });
    }

}
