package com.cyyaw.tx.spider;

import cn.hutool.json.JSONObject;
import com.cyyaw.service.spider.PpPeopleService;
import com.cyyaw.table.spider.user.entity.PpPeople;
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
@Api(tags = "人")
@RequestMapping("/spider/pppeople")
@RestController
public class PpPeopleController {

    @Autowired
    private PpPeopleService ppPeopleService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPage")
    public BaseResult<PpPeople> findPagePpPeople(@RequestParam Map<String, Object> map) {
        PageRespone<PpPeople> page = ppPeopleService.findPage(new JSONObject(map));
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findIdPpPeople")
    public BaseResult findIdPpPeople(Integer id) {
        PpPeople obj = ppPeopleService.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/savePpPeople")
    public BaseResult savePpPeople(@RequestBody PpPeople saveObj) {
        PpPeople obj = null;
        Integer id = saveObj.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            saveObj.setCreateTime(new Date());
            saveObj.setTid(WhyStringUtil.getUUID());
            log.info("添加:{}", saveObj);
            obj = ppPeopleService.save(saveObj);
        } else {
            //修改
            log.info("修改:{}", saveObj);
            PpPeople cpObj = ppPeopleService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(saveObj,cpObj);
            obj = ppPeopleService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delPpPeople")
    public BaseResult delPpPeople(@RequestBody Integer idArr[]) {
        ppPeopleService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}
