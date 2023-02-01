package com.cyyaw.tx.admin.controller;


import com.cyyaw.jpa.util.entity.SelectEntity;
import com.cyyaw.table.goods.GGoodsSearch;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.PageRespone;
import com.cyyaw.tx.web.service.GGoodsSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RequestMapping("/admin/ggoodssearch")
@RestController
public class GGoodsSearchController {

    @Autowired
    private GGoodsSearchService gGoodsSearchService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPageGGoodsSearch")
    public BaseResult findPageGGoodsSearch(String jsonStr, SelectEntity selectEntity) {
        PageRespone<GGoodsSearch> page = gGoodsSearchService.findPage(jsonStr, selectEntity);
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findIdGGoodsSearch")
    public BaseResult findIdGGoodsSearch(Integer id) {
        GGoodsSearch obj = gGoodsSearchService.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/saveGGoodsSearch")
    public BaseResult saveGGoodsSearch(@RequestBody GGoodsSearch gGoodsSearch) {
        GGoodsSearch obj = null;
        Integer id = gGoodsSearch.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            gGoodsSearch.setCreatetime(new Date());
            gGoodsSearch.setTid(IdWorker.nextId());
            log.info("添加:{}", gGoodsSearch);
            obj = gGoodsSearchService.save(gGoodsSearch);
        } else {
            //修改
            log.info("修改:{}", gGoodsSearch);
            GGoodsSearch cpObj = gGoodsSearchService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(gGoodsSearch, cpObj);
            obj = gGoodsSearchService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delGGoodsSearch")
    public BaseResult delGGoodsSearch(@RequestBody Integer idArr[]) {
        gGoodsSearchService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}
