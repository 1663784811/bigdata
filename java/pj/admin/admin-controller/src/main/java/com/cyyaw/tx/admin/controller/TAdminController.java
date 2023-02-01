package com.cyyaw.tx.admin.controller;

import com.cyyaw.jpa.util.entity.SelectEntity;
import com.cyyaw.service.TAdminService;
import com.cyyaw.table.tadmin.TAdmin;
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
@RequestMapping("/admin/tadmin")
@RestController
public class TAdminController {

    @Autowired
    private TAdminService tAdminService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPageTAdmin")
    public BaseResult findPageTAdmin(String jsonStr, SelectEntity selectEntity) {
        PageRespone<TAdmin> page = tAdminService.findPage(jsonStr, selectEntity);
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findIdTAdmin")
    public BaseResult findIdTAdmin(Integer id) {
        TAdmin obj = tAdminService.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/saveTAdmin")
    public BaseResult saveTAdmin(@RequestBody TAdmin tAdmin) {
        TAdmin obj = null;
        Integer id = tAdmin.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            tAdmin.setCreatetime(new Date());
            tAdmin.setTid(IdWorker.nextId());
            log.info("添加:{}", tAdmin);
            obj = tAdminService.save(tAdmin);
        } else {
            //修改
            log.info("修改:{}", tAdmin);
            TAdmin cpObj = tAdminService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(tAdmin,cpObj);
            obj = tAdminService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delTAdmin")
    public BaseResult delTAdmin(@RequestBody Integer idArr[]) {
        tAdminService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}
