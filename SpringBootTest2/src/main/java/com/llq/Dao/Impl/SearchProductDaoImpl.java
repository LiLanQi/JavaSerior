package com.llq.Dao.Impl;

import com.llq.Dao.SearchProductDao;
import com.llq.bean.ProductReqDto;

public class SearchProductDaoImpl implements SearchProductDao {

    @Override
    public ProductReqDto searchProductByIdAndSkuId(Long id, Long skuId) {
        String sql = "select * from product where id=" + id + "and skuId = " + skuId;


        return null;
    }
}
