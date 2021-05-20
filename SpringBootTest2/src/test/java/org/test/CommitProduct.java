package org.test;

import com.squareup.square.SquareClient;
import com.squareup.square.api.CatalogApi;
import com.squareup.square.models.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.UUID;

public class CommitProduct {


    public static void main(String[] args) {
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
