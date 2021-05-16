package com.example.MyFirstDemo;

public class Test {


    public static void main(String[] args) {
        catalogApi.listCatalogAsync(
                null,
                null,
                null)
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
