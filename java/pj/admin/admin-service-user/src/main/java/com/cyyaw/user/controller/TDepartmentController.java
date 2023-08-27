package com.cyyaw.user.controller;

import cn.hutool.json.JSONObject;
import com.cyyaw.user.service.TDepartmentService;
import com.cyyaw.user.table.entity.TDepartment;
import com.cyyaw.util.tools.BaseResult;
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
@RequestMapping("/admin/tDepartment")
@RestController
public class TDepartmentController {

    @Autowired
    private TDepartmentService tDepartmentService;

    /**
     * 分页条件查询
     */
    @GetMapping("/findDepartmentTree")
    public BaseResult findDepartmentTree(@RequestParam Map<String, Object> map) {
        return BaseResult.ok(tDepartmentService.findTree(new JSONObject(map)));
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findIdTDepartment")
    public BaseResult findIdTDepartment(Integer id) {
        TDepartment obj = tDepartmentService.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/saveTDepartment")
    public BaseResult saveTDepartment(@RequestBody TDepartment saveObj) {
        TDepartment obj = tDepartmentService.saveTree(saveObj);
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/delTDepartment")
    public BaseResult delTDepartment(@RequestBody Integer idArr[]) {
        tDepartmentService.del(idArr);
        return BaseResult.ok("删除成功");
    }

}
