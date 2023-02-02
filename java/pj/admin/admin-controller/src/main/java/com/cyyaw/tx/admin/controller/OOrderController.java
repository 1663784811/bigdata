package com.cyyaw.tx.admin.controller;

import com.cyyaw.jpa.util.entity.SelectEntity;
import com.cyyaw.service.OOrderService;
import com.cyyaw.table.store.order.OOrder;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.PageRespone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RequestMapping("/admin/oorder")
@RestController
public class OOrderController {

    @Autowired
    private OOrderService oOrderService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPageOOrder")
    public BaseResult findPageOOrder(String jsonStr, SelectEntity selectEntity) {
        PageRespone<OOrder> page = oOrderService.findPage(jsonStr, selectEntity);
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findIdOOrder")
    public BaseResult findIdOOrder(Integer id) {
        OOrder obj = oOrderService.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/saveOOrder")
    public BaseResult saveOOrder(@RequestBody OOrder oOrder) {
        OOrder obj = null;
        Integer id = oOrder.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            oOrder.setCreatetime(new Date());
            oOrder.setTid(IdWorker.nextId());
            log.info("添加:{}", oOrder);
            obj = oOrderService.save(oOrder);
        } else {
            //修改
            log.info("修改:{}", oOrder);
            OOrder cpObj = oOrderService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(oOrder,cpObj);
            obj = oOrderService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delOOrder")
    public BaseResult delOOrder(@RequestBody Integer idArr[]) {
        oOrderService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}
