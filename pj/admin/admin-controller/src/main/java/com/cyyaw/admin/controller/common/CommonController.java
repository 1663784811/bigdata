package com.cyyaw.admin.controller.common;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 公共模块
 */
@RequestMapping("/home/common")
@RestController
public class CommonController {


//    @Autowired
//    private CommonService commonService;
//
//    /**
//     * 通用查询
//     *
//     * @return
//     */
//    @RequestMapping("/query")
//    public Map<String, Object> query(@RequestBody Map<String,Object> map) {
//        JSONObject json = new JSONObject();
//        for (String key: map.keySet()) {
//            json.put(key,map.get(key));
//        }
//        return commonService.query(json);
//    }
//
//    /**
//     * 通用修改或添加
//     */
//    @RequestMapping("/update")
//    public Map<String, Object> update(@RequestBody Map<String,Object> map) {
//        JSONObject json = new JSONObject();
//        for (String key: map.keySet()) {
//            json.put(key,map.get(key));
//        }
//        return commonService.update(json);
//    }
//
//    /**
//     * 通用删除
//     */
//    @RequestMapping("/delete")
//    public Map<String, Object> delete(@RequestBody Map<String,Object> map) {
//        JSONObject json = new JSONObject();
//        for (String key: map.keySet()) {
//            json.put(key,map.get(key));
//        }
//        return commonService.delete(json);
//    }



}