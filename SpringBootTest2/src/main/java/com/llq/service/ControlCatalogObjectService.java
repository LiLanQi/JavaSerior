package com.llq.service;

import com.llq.bean.ProductReqDto;
import com.squareup.square.api.CatalogApi;

public interface ControlCatalogObjectService {

    Object createImageObject(CatalogApi catalogApi,String name, String picPath);

    void upsertCatalogObject(CatalogApi catalogApi,Long price,String selectedCurrency,String name, String selecedPricingType,Long imgVersion,String imgId);

    void test(ProductReqDto productReqDto);
}
