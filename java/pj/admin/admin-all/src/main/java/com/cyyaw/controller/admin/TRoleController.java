package com.cyyaw.controller.admin;

import com.cyyaw.user.config.TokenData;
import com.cyyaw.user.service.TRoleService;
import com.cyyaw.user.table.entity.TRole;
import com.cyyaw.user.utils.LoginInfo;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.WhyStringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RequestMapping("/admin/role")
@RestController
public class TRoleController {
    @Autowired
    private TRoleService tRoleService;

    /**
     * 添加或修改
     */
    @PostMapping("/saveTRole")
    public BaseResult saveTRole(@RequestBody TRole saveObj, @TokenData LoginInfo loginInfo) {
        TRole obj = null;
        Integer id = saveObj.getId();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            saveObj.setEnterpriseCode(loginInfo.getEnterpriseCode());
            saveObj.setCreateTime(new Date());
            saveObj.setTid(WhyStringUtil.getUUID());
            log.info("添加:{}", saveObj);
            obj = tRoleService.save(saveObj);
        } else {
            //修改
            log.info("修改:{}", saveObj);
            TRole cpObj = tRoleService.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(saveObj, cpObj);
            obj = tRoleService.save(cpObj);
        }
        return BaseResult.ok(obj);
    }
}
