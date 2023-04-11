package com.cyyaw.tx.admin;

import com.cyyaw.service.admin.TPowerService;
import com.cyyaw.table.admin.entity.TPower;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.PageRespone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Slf4j
@RequestMapping("/admin/tpower")
@RestController
public class TPowerController {

    @Autowired
    private TPowerService tPowerService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPage")
    public BaseResult<TPower> findPageTPower(@RequestParam Map<String, Object> map) {
        PageRespone<TPower> page = tPowerService.findPage(new JSONObject(map));
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findIdTPower")
    public BaseResult findIdTPower(Integer id) {
        TPower obj = tPowerService.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/saveTPower")
    public BaseResult saveTPower(@RequestBody TPower saveObj) {
        TPower obj = null;
        Integer id = saveObj.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            saveObj.setCreateTime(new Date());
            saveObj.setId(IdWorker.nextId());
            log.info("添加:{}", saveObj);
            obj = tPowerService.save(saveObj);
        } else {
            //修改
            log.info("修改:{}", saveObj);
            TPower cpObj = tPowerService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(saveObj,cpObj);
            obj = tPowerService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delTPower")
    public BaseResult delTPower(@RequestBody Integer idArr[]) {
        tPowerService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}
