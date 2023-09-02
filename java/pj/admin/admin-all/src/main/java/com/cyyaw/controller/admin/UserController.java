package com.cyyaw.controller.admin;

import cn.hutool.json.JSONObject;
import com.cyyaw.user.config.TokenData;
import com.cyyaw.user.service.UUserService;
import com.cyyaw.user.table.entity.UUser;
import com.cyyaw.user.utils.LoginInfo;
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
@RequestMapping("/admin/user")
@RestController
public class UserController {

    @Autowired
    private UUserService uUserService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPage")
    public BaseResult<UUser> findPageUUser(@RequestParam Map<String, Object> map,@TokenData LoginInfo loginInfo) {
        String eId = loginInfo.getEnterpriseId();
        map.put("eq_enterpriseId", eId);
        PageRespone<UUser> page = uUserService.findPage(new JSONObject(map));
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
    public BaseResult saveUUser(@RequestBody UUser saveObj) {
        UUser obj = null;
        Integer id = saveObj.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            saveObj.setCreateTime(new Date());
            saveObj.setTid(WhyStringUtil.getUUID());
            log.info("添加:{}", saveObj);
            obj = uUserService.save(saveObj);
        } else {
            //修改
            log.info("修改:{}", saveObj);
            UUser cpObj = uUserService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(saveObj,cpObj);
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
