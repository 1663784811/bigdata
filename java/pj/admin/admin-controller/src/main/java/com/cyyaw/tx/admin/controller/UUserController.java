package com.cyyaw.tx.admin.controller;

import com.cyyaw.jpa.util.entity.SelectEntity;
import com.cyyaw.table.admin.tadmin.UUser;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.PageRespone;
import com.cyyaw.tx.web.service.UUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@Slf4j
@RequestMapping("/admin/uuser")
@RestController
public class UUserController {

    @Autowired
    private UUserService uUserService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPageUUser")
    public BaseResult findPageUUser(String jsonStr, SelectEntity selectEntity) {
        PageRespone<UUser> page = uUserService.findPage(jsonStr, selectEntity);
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findIdUUser")
    public BaseResult findIdUUser(Integer id) {
        UUser obj = uUserService.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/saveUUser")
    public BaseResult saveUUser(@RequestBody UUser uUser) {
        UUser obj = null;
        Integer id = uUser.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            uUser.setCreatetime(new Date());
            uUser.setTid(IdWorker.nextId());
            log.info("添加:{}", uUser);
            obj = uUserService.save(uUser);
        } else {
            //修改
            log.info("修改:{}", uUser);
            UUser cpObj = uUserService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(uUser, cpObj);
            obj = uUserService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delUUser")
    public BaseResult delUUser(@RequestBody Integer idArr[]) {
        uUserService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}
