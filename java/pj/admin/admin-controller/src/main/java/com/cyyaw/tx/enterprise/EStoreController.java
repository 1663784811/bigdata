package com.cyyaw.tx.enterprise;

import cn.hutool.json.JSONObject;
import com.cyyaw.service.enterprise.EStoreService;
import com.cyyaw.table.enterprise.entity.EStore;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.PageRespone;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Slf4j
@Api(tags = "商店")
@RequestMapping("/admin/store")
@RestController
public class EStoreController {

    @Autowired
    private EStoreService eStoreService;

    /**
     * 分页条件查询
     */
    @GetMapping(value = "/findPage")
    public BaseResult<EStore> findPage(@RequestParam Map<String, Object> map) {
        PageRespone<EStore> page = eStoreService.findPage(new JSONObject(map));
        return BaseResult.ok(page);
    }


}
