package com.llq.Dao;

import com.llq.bean.ProductReqDto;

public interface SearchProductDao {

    ProductReqDto searchProductByIdAndSkuId(Long id, Long skuId);
}
