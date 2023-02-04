package com.cyyaw.tx.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cyyaw.table.store.goods.entity.GDetails;
import com.cyyaw.table.store.goods.entity.GGoods;
import com.cyyaw.table.store.goods.entity.GPhoto;
import com.cyyaw.table.store.goods.entity.GStoreGoodsSku;
import com.cyyaw.tx.web.service.GGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RequestMapping("/tx/admin/goods")
@RestController
public class HomeGoodsController {

    @Autowired
    private GGoodsService gGoodsService;

    @PostMapping("/saveGoods")
    public GGoods saveGoods(@RequestBody Map<String,Object> map){
        JSONObject json = new JSONObject();
        for (String key: map.keySet()) {
            json.put(key,map.get(key));
        }
        GGoods gGoods = JSON.parseObject(json.toJSONString(), GGoods.class);
        List<GPhoto> photoList =  JSONArray.toJavaObject(json.getJSONArray("photoList"), List.class);
        List<GStoreGoodsSku> skuList = JSONArray.toJavaObject(json.getJSONArray("skuList"), List.class);
        GDetails gDetails = JSON.toJavaObject(json.getJSONObject("details"),GDetails.class);

       return gGoodsService.saveGoods(gGoods, photoList, skuList, gDetails);
    }




}
