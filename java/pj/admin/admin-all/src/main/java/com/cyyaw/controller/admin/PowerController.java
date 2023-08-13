package com.cyyaw.controller.admin;

import com.cyyaw.user.service.TPowerService;
import com.cyyaw.user.table.entity.TPower;
import com.cyyaw.user.utils.entity.TreeEntity;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "菜单管理")
@RequestMapping("/admin/power")
public class PowerController {

    @Autowired
    private TPowerService tPowerService;


    @ApiOperation(value = "查询系统菜单", notes = "查询系统菜单")
    @GetMapping("/queryMenu")
    public BaseResult queryMenu(){
        // 查询数据
        TPower tPower = new TPower();
        List<TPower> powers = tPowerService.findByExample(tPower);

        // 组装成树
        TreeEntity treeEntity = new TreeEntity();
        for (TPower tpower : powers) {
            TreeEntity.Node<TPower> node = new TreeEntity.Node<TPower>();
            node.setTid(tpower.getTid());
            node.setData(tpower);
            node.setName(tpower.getName());
            node.setPid(tpower.getPid());
            treeEntity.add(node);
        }
        return BaseResult.ok(treeEntity);
    }






}
