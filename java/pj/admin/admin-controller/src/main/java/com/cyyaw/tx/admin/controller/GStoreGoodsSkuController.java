//package com.cyyaw.tx.admin.controller;
//
//import com.cyyaw.table.store.goods.GStoreGoodsSku;
//import com.cyyaw.util.tools.BaseResult;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@Slf4j
//@RequestMapping("/admin/gstoregoodssku")
//@RestController
//public class GStoreGoodsSkuController {
//
//    @Autowired
//    private GStoreGoodsSkuService gStoreGoodsSkuService;
//
//    /**
//     * 保存列表
//     */
//    @PostMapping("/saveGStoreGoodsSkuList")
//    public BaseResult saveGStoreGoodsSkuList(@RequestBody List<GStoreGoodsSku> gStoreGoodsSkuList){
//        List<GStoreGoodsSku> list = gStoreGoodsSkuService.saveGStoreGoodsSkuList(gStoreGoodsSkuList);
//        return BaseResult.ok(list);
//    }
//
//}
