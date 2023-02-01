package com.cyyaw.tx.admin.controller;


import com.cyyaw.jpa.util.entity.SelectEntity;
import com.cyyaw.service.TRoleService;
import com.cyyaw.table.tadmin.TRole;
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
@RequestMapping("/admin/trole")
@RestController
public class TRoleController {

    @Autowired
    private TRoleService tRoleService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPageTRole")
    public BaseResult findPageTRole(String jsonStr, SelectEntity selectEntity) {
        PageRespone<TRole> page = tRoleService.findPage(jsonStr, selectEntity);
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findIdTRole")
    public BaseResult findIdTRole(Integer id) {
        TRole obj = tRoleService.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/saveTRole")
    public BaseResult saveTRole(@RequestBody TRole tRole) {
        TRole obj = null;
        Integer id = tRole.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            tRole.setCreatetime(new Date());
            tRole.setTid(IdWorker.nextId());
            log.info("添加:{}", tRole);
            obj = tRoleService.save(tRole);
        } else {
            //修改
            log.info("修改:{}", tRole);
            TRole cpObj = tRoleService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(tRole, cpObj);
            obj = tRoleService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delTRole")
    public BaseResult delTRole(@RequestBody Integer idArr[]) {
        tRoleService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}
