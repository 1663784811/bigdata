package com.cyyaw.tx.spider;

import cn.hutool.json.JSONObject;
import com.cyyaw.spider.service.PpHistoryService;
import com.cyyaw.table.spider.user.entity.PpHistory;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.PageRespone;
import com.cyyaw.util.tools.WhyStringUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Api(tags = "人历史")
@Slf4j
@RequestMapping("/spider/pphistory")
@RestController
public class PpHistoryController {

    @Autowired
    private PpHistoryService ppHistoryService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPage")
    public BaseResult<PpHistory> findPagePpHistory(@RequestParam Map<String, Object> map) {
        PageRespone<PpHistory> page = ppHistoryService.findPage(new JSONObject(map));
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findIdPpHistory")
    public BaseResult findIdPpHistory(Integer id) {
        PpHistory obj = ppHistoryService.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/savePpHistory")
    public BaseResult savePpHistory(@RequestBody PpHistory saveObj) {
        PpHistory obj = null;
        Integer id = saveObj.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            saveObj.setCreateTime(new Date());
            saveObj.setTid(WhyStringUtil.getUUID());
            log.info("添加:{}", saveObj);
            obj = ppHistoryService.save(saveObj);
        } else {
            //修改
            log.info("修改:{}", saveObj);
            PpHistory cpObj = ppHistoryService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(saveObj,cpObj);
            obj = ppHistoryService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delPpHistory")
    public BaseResult delPpHistory(@RequestBody Integer idArr[]) {
        ppHistoryService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}
