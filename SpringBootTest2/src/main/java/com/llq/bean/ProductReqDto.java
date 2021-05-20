package com.llq.bean;

public class ProductReqDto {

    private Long productId ;
    private Long skuId ;
    private Long adminId ;

    public ProductReqDto(){


    }

    public ProductReqDto(Long productId, Long skuId,Long adminId){

        this.adminId = adminId ;
        this.productId = productId ;
        this.skuId = skuId ;

    }

    public Long getAdminId() {
        return adminId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }



    @Override
    public String toString() {
        return "ProductReqDto{" +
                "productId=" + productId +
                ", skuId=" + skuId +
                ", adminId=" + adminId +
                '}';
    }
}
