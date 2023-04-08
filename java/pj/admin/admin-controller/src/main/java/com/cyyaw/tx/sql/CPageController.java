package com.cyyaw.tx.sql;

import cn.hutool.json.JSONObject;
import com.cyyaw.service.sql.CPageService;
import com.cyyaw.table.config.entity.CPage;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.PageRespone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


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


}
