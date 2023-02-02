package com.cyyaw.tx.admin.controller;

import com.cyyaw.jpa.util.entity.SelectEntity;
import com.cyyaw.service.GStoreGoodsSkuService;
import com.cyyaw.table.store.goods.GStoreGoodsSku;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.PageRespone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Slf4j
@RequestMapping("/admin/gstoregoodssku")
@RestController
public class GStoreGoodsSkuController {

    @Autowired
    private GStoreGoodsSkuService gStoreGoodsSkuService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPageGStoreGoodsSku")
    public BaseResult findPageGStoreGoodsSku(String jsonStr, SelectEntity selectEntity) {
        PageRespone<GStoreGoodsSku> page = gStoreGoodsSkuService.findPage(jsonStr, selectEntity);
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findIdGStoreGoodsSku")
    public BaseResult findIdGStoreGoodsSku(Integer id) {
        GStoreGoodsSku obj = gStoreGoodsSkuService.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/saveGStoreGoodsSku")
    public BaseResult saveGStoreGoodsSku(@RequestBody GStoreGoodsSku gStoreGoodsSku) {
        GStoreGoodsSku obj = null;
        Integer id = gStoreGoodsSku.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            gStoreGoodsSku.setCreatetime(new Date());
            gStoreGoodsSku.setTid(IdWorker.nextId());
            log.info("添加:{}", gStoreGoodsSku);
            obj = gStoreGoodsSkuService.save(gStoreGoodsSku);
        } else {
            //修改
            log.info("修改:{}", gStoreGoodsSku);
            GStoreGoodsSku cpObj = gStoreGoodsSkuService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(gStoreGoodsSku, cpObj);
            obj = gStoreGoodsSkuService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delGStoreGoodsSku")
    public BaseResult delGStoreGoodsSku(@RequestBody Integer idArr[]) {
        gStoreGoodsSkuService.del(idArr);
        return BaseResult.ok("删除成功");
    }


    /**
     * 保存列表
     */
    @PostMapping("/saveGStoreGoodsSkuList")
    public BaseResult saveGStoreGoodsSkuList(@RequestBody List<GStoreGoodsSku> gStoreGoodsSkuList){
        List<GStoreGoodsSku> list = gStoreGoodsSkuService.saveGStoreGoodsSkuList(gStoreGoodsSkuList);
        return BaseResult.ok(list);
    }

}
