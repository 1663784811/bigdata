package com.cyyaw.tx.spider;

import cn.hutool.json.JSONObject;
import com.cyyaw.service.spider.SpiderTargetAService;
import com.cyyaw.table.spider.spider.entity.SpiderTargetA;
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

@Slf4j
@Api(tags = "抓取")
@RequestMapping("/spider/spidertargeta")
@RestController
public class SpiderTargetAController {

    @Autowired
    private SpiderTargetAService spiderTargetAService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPage")
    public BaseResult<SpiderTargetA> findPageSpiderTargetA(@RequestParam Map<String, Object> map) {
        PageRespone<SpiderTargetA> page = spiderTargetAService.findPage(new JSONObject(map));
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findIdSpiderTargetA")
    public BaseResult findIdSpiderTargetA(Integer id) {
        SpiderTargetA obj = spiderTargetAService.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/saveSpiderTargetA")
    public BaseResult saveSpiderTargetA(@RequestBody SpiderTargetA saveObj) {
        SpiderTargetA obj = null;
        Integer id = saveObj.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            saveObj.setCreateTime(new Date());
            saveObj.setTid(WhyStringUtil.getUUID());
            log.info("添加:{}", saveObj);
            obj = spiderTargetAService.save(saveObj);
        } else {
            //修改
            log.info("修改:{}", saveObj);
            SpiderTargetA cpObj = spiderTargetAService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(saveObj,cpObj);
            obj = spiderTargetAService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delSpiderTargetA")
    public BaseResult delSpiderTargetA(@RequestBody Integer idArr[]) {
        spiderTargetAService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}