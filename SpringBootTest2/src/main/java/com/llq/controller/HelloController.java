package com.llq.controller;


import cn.hutool.aop.interceptor.SpringCglibInterceptor;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.llq.Dao.SearchProductDao;
import com.llq.bean.Car;
import com.llq.bean.PmsProduct;
import com.llq.bean.PmsSkuStock;
import com.llq.bean.ProductReqDto;
import com.llq.service.ControlCatalogObjectService;
import com.squareup.square.SquareClient;
import com.squareup.square.api.CatalogApi;
import com.squareup.square.models.CreateCatalogImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class HelloController {
    @Autowired
    private ControlCatalogObjectService controlCatalogObjectService ;


    @Autowired
    Car car;

    @RequestMapping("/car")
    public Car car(){
        return car;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String handler01( @RequestParam Long adminId,@RequestParam Long productId,@RequestParam Long skuId){

        // 查商品信息
        // 上传到shop
        controlCatalogObjectService.test( new ProductReqDto(adminId,productId,skuId));

        String result2 = HttpRequest.get("http://34.96.223.35:7004/product/list?idList="+productId)
                .header(Header.ACCEPT,"*/*")
                .header(Header.AUTHORIZATION, "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtYWNybyIsImNyZWF0ZWQiOjE2MjE0MDU2NjA2MDgsImV4cCI6MTYyMjAxMDQ2MH0.zHZ8oZBqLDn4JfYvku6dmo875tBXdUGEE3QuRsZgClVDybw81T39FZIrvkrPTRngS269F9PPqry2wlEKWwxCFA")
                .timeout(20000)//超时，毫秒
                .execute().body();
        System.out.println("result2=" + result2);

        //获取得到的数据，并且封装到PmsProduct中
        JSONObject jsonObject = JSONUtil.parseObj(result2, false, true);
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("list");
        jsonObject = jsonArray.getJSONObject(0);
        System.out.println("jsonObject = " + jsonObject);
        PmsProduct pmsProduct = JSONUtil.toBean(jsonObject, PmsProduct.class);

        SquareClient client = new SquareClient.Builder()
                .httpClientConfig(configBuilder -> configBuilder
                        .timeout(0))
                .squareVersion("2021-05-13")
                .accessToken("EAAAEIl3awo49_UE8Is_9S0pAp2n_0-fIrDfgDnVoSZYU5_dD4ZhWbnPH534oBGh")
                .build();
        CatalogApi catalogApi = client.getCatalogApi();
        Object imageObject = controlCatalogObjectService.createImageObject(catalogApi, pmsProduct.getName(), pmsProduct.getPic());
        CreateCatalogImageResponse catalogImage = (CreateCatalogImageResponse)imageObject;
        String imageId = catalogImage.getImage().getId();
        Long imgVersion = catalogImage.getImage().getVersion();
//        String url = catalogImage.getImage().getImageData().getUrl();
        controlCatalogObjectService.upsertCatalogObject(catalogApi,pmsProduct.getPrice().longValue(),"USD",pmsProduct.getName(), "FIXED_PRICING", imgVersion, imageId);
        return "success";
    }
}
