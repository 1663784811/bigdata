package com.cyyaw.tx.store;

import com.cyyaw.jpa.util.entity.SelectEntity;
import com.cyyaw.table.store.goods.entity.GGoods;
import com.cyyaw.tx.web.service.GGoodsService;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.PageRespone;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Api(tags = "商品管理")
@RequestMapping("/admin/goods")
@RestController
public class GGoodsController {

    @Autowired
    private GGoodsService gGoodsService;

    /**
     * 查询未上架d的商品
     */
    @GetMapping("/findPageGGoodsNoSearch")
    public BaseResult findPageGGoodsNoSearch(SelectEntity selectEntity) {
        PageRespone<GGoods> page = gGoodsService.findPageGGoodsNoSearch(selectEntity);
        return BaseResult.ok(page);
    }

}
