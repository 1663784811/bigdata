package com.cyyaw.tx.admin.controller;

import com.cyyaw.jpa.util.entity.SelectEntity;
import com.cyyaw.table.goods.GGoods;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.PageRespone;
import com.cyyaw.tx.web.service.GGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Slf4j
@RequestMapping("/admin/ggoods")
@RestController
public class GGoodsController {

    @Autowired
    private GGoodsService gGoodsService;

    /**
     * 查询未上架的商品
     */
    @GetMapping("/findPageGGoodsNoSearch")
    public BaseResult findPageGGoodsNoSearch(SelectEntity selectEntity) {
        PageRespone<GGoods> page = gGoodsService.findPageGGoodsNoSearch(selectEntity);
        return BaseResult.ok(page);
    }

    /**
     * 分页条件查询
     */
    @GetMapping("/findPageGGoods")
    public BaseResult findPageGGoods(String jsonStr, SelectEntity selectEntity) {
        PageRespone<GGoods> page = gGoodsService.findPage(jsonStr, selectEntity);
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findIdGGoods")
    public BaseResult findIdGGoods(Integer id) {
        GGoods obj = gGoodsService.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/saveGGoods")
    public BaseResult saveGGoods(@RequestBody GGoods gGoods) {
        GGoods obj = null;
        Integer id = gGoods.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            gGoods.setCreatetime(new Date());
            gGoods.setTid(IdWorker.nextId());
            log.info("添加:{}", gGoods);
            obj = gGoodsService.save(gGoods);
        } else {
            //修改
            log.info("修改:{}", gGoods);
            GGoods cpObj = gGoodsService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(gGoods, cpObj);
            obj = gGoodsService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delGGoods")
    public BaseResult delGGoods(@RequestBody Integer idArr[]) {
        gGoodsService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}
