package com.cyyaw.user.controller;

import cn.hutool.json.JSONObject;
import com.cyyaw.user.service.TAdminService;
import com.cyyaw.user.table.entity.TAdmin;
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
@RequestMapping("/admin/tadmin")
@RestController
public class TAdminController {

    @Autowired
    private TAdminService tAdminService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPage")
    public BaseResult<TAdmin> findPageTAdmin(@RequestParam Map<String, Object> map) {
        PageRespone<TAdmin> page = tAdminService.findPage(new JSONObject(map));
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
    public BaseResult saveTAdmin(@RequestBody TAdmin saveObj) {
        TAdmin obj = null;
        Integer id = saveObj.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            saveObj.setCreateTime(new Date());
            saveObj.setTid(WhyStringUtil.getUUID());
            log.info("添加:{}", saveObj);
            obj = tAdminService.save(saveObj);
        } else {
            //修改
            log.info("修改:{}", saveObj);
            TAdmin cpObj = tAdminService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(saveObj,cpObj);
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
