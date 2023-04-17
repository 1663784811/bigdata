package com.cyyaw.tx.spider;

import cn.hutool.json.JSONObject;
import com.cyyaw.service.spider.SpiderNickNameService;
import com.cyyaw.table.spider.spider.entity.SpiderNickName;
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
@Api(tags = "抓取标签")
@RequestMapping("/spider/spidernickname")
@RestController
public class SpiderNickNameController {

    @Autowired
    private SpiderNickNameService spiderNickNameService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPage")
    public BaseResult<SpiderNickName> findPageSpiderNickName(@RequestParam Map<String, Object> map) {
        PageRespone<SpiderNickName> page = spiderNickNameService.findPage(new JSONObject(map));
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findIdSpiderNickName")
    public BaseResult findIdSpiderNickName(Integer id) {
        SpiderNickName obj = spiderNickNameService.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/saveSpiderNickName")
    public BaseResult saveSpiderNickName(@RequestBody SpiderNickName saveObj) {
        SpiderNickName obj = null;
        Integer id = saveObj.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            saveObj.setCreateTime(new Date());
            saveObj.setTid(WhyStringUtil.getUUID());
            log.info("添加:{}", saveObj);
            obj = spiderNickNameService.save(saveObj);
        } else {
            //修改
            log.info("修改:{}", saveObj);
            SpiderNickName cpObj = spiderNickNameService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(saveObj,cpObj);
            obj = spiderNickNameService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delSpiderNickName")
    public BaseResult delSpiderNickName(@RequestBody Integer idArr[]) {
        spiderNickNameService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}
