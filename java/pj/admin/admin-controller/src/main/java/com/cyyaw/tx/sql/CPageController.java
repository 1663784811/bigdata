package com.cyyaw.tx.sql;

import cn.hutool.json.JSONObject;
import com.cyyaw.service.sql.CPageService;
import com.cyyaw.table.config.entity.CPage;
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
@RestController
@RequestMapping("/admin/config/page")
public class CPageController {

    @Autowired
    private CPageService cPageService;


    /**
     * 分页条件查询
     */
    @GetMapping("/findPage")
    public BaseResult<CPage> findPageCPage(@RequestParam Map<String, Object> map) {
        PageRespone<CPage> page = cPageService.findPage(new JSONObject(map));
        return BaseResult.ok(page);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/saveCPage")
    public BaseResult saveCPage(@RequestBody CPage saveObj) {
        CPage obj = null;
        Integer id = saveObj.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            saveObj.setCreateTime(new Date());
            saveObj.setTid(WhyStringUtil.getUUID());
            log.info("添加:{}", saveObj);
            obj = cPageService.save(saveObj);
        } else {
            //修改
            log.info("修改:{}", saveObj);
            CPage cpObj = cPageService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(saveObj, cpObj);
            obj = cPageService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delCPage")
    public BaseResult delCPage(@RequestBody Integer idArr[]) {
        cPageService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}
