package com.cyyaw.store.controller;

import cn.hutool.json.JSONObject;
import com.cyyaw.store.service.GTypeService;
import com.cyyaw.store.table.goods.entity.GType;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.PageRespone;
import com.cyyaw.util.tools.WhyStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Slf4j
@RequestMapping("/admin/gType")
@RestController
public class GTypeController {

    @Autowired
    private GTypeService gTypeService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPage")
    public BaseResult<GType> findPageGType(@RequestParam Map<String, Object> map) {
        PageRespone<GType> page = gTypeService.findPage(new JSONObject(map));
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findIdGType")
    public BaseResult findIdGType(Integer id) {
        GType obj = gTypeService.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/saveGType")
    public BaseResult saveGType(@RequestBody GType saveObj) {
        GType obj = null;
        Integer id = saveObj.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            saveObj.setCreateTime(new Date());
             saveObj.setTid(WhyStringUtil.getUUID());
            log.info("添加:{}", saveObj);
            obj = gTypeService.save(saveObj);
        } else {
            //修改
            log.info("修改:{}", saveObj);
            GType cpObj = gTypeService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(saveObj,cpObj);
            obj = gTypeService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delGType")
    public BaseResult delGType(@RequestBody Integer idArr[]) {
        gTypeService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}
