package org.test;

import com.squareup.square.SquareClient;
import com.squareup.square.api.CatalogApi;
import com.squareup.square.models.CatalogImage;
import com.squareup.square.models.CatalogObject;
import com.squareup.square.models.CreateCatalogImageRequest;
import com.squareup.square.utilities.FileWrapper;
import org.junit.Test;

import java.io.File;
import java.util.UUID;


public class Test1 {



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
                .name("177867")
                .build();

        CatalogObject image = new CatalogObject.Builder("IMAGE", "#111111")
                .imageData(imageData)
                .build();

        CreateCatalogImageRequest request = new CreateCatalogImageRequest.Builder(UUID.randomUUID().toString())
                .image(image)
                .build();

        // Modify this to point to your desired file.
        File imageFile = new File("C:\\Users\\Administrator\\Desktop\\016.png");
        FileWrapper f = new FileWrapper(imageFile);
        catalogApi.createCatalogImageAsync(request, f)
                .thenAccept(result -> {
                    System.out.println("Success!!!");
                })
                .exceptionally(exception -> {
                    System.out.println("Failed to make the request");
                    System.out.println(String.format("Exception: %s", exception.getMessage()));
                    return null;
                });
    }


    //插入一个商品
    public void testUpsertCatalogObject() {
        SquareClient client = new SquareClient.Builder()
                .httpClientConfig(configBuilder -> configBuilder
                        .timeout(0))
                .squareVersion("2021-05-13")
                .accessToken("EAAAEIl3awo49_UE8Is_9S0pAp2n_0-fIrDfgDnVoSZYU5_dD4ZhWbnPH534oBGh")
                .build();
        CatalogApi catalogApi = client.getCatalogApi();
        CatalogImage imageData = new CatalogImage.Builder()
                .name("the picture of qixi")
                .caption("very dilicious")
                .build();

        CatalogObject image = new CatalogObject.Builder("IMAGE", "#1042520531")
                .imageData(imageData)
                .build();

        CreateCatalogImageRequest request = new CreateCatalogImageRequest.Builder(UUID.randomUUID().toString())
                .image(image)
                .build();

// Modify this to point to your desired file.
        File imageFile = new File("C:\\Users\\Administrator\\Desktop\\016.png");
        FileWrapper f = new FileWrapper(imageFile);
        catalogApi.createCatalogImageAsync(request, f)
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
