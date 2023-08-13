package com.cyyaw.controller.admin;

import com.cyyaw.user.service.TPowerService;
import com.cyyaw.user.table.entity.TPower;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Api(tags = "菜单管理")
@RequestMapping("/admin/power")
public class PowerController {

    @Autowired
    private TPowerService tPowerService;


    @ApiOperation(value = "查询系统菜单", notes = "查询系统菜单")
    @GetMapping("/queryMenu")
    public BaseResult queryMenu(@RequestParam Map<String, Object> map) {
        // 查询数据
        return tPowerService.queryMenu();
    }

    @ApiOperation(value = "保存菜单", notes = "保存菜单")
    @PostMapping("/saveMenu")
    public BaseResult saveMenu(@RequestBody TPower tPower){
        TPower save = tPowerService.save(tPower);
        return BaseResult.ok(save);
    }

    @ApiOperation(value = "删除菜单", notes = "删除菜单")
    @PostMapping("/delMenu")
    public BaseResult delMenu(@RequestBody Integer id){
        tPowerService.delMenu(id);
        return BaseResult.ok();
    }

}
