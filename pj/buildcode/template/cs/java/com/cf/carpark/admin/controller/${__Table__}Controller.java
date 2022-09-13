package com.cf.carpark.admin.controller;



import java.util.List;

/**
 * @author why
 */
@RestController
@RequestMapping("${__table__}Admin/")
@Validated
public class ${__Table__}Controller implements ${__Table__}Swagger {

    @Reference(version = "1.0.0", retries = 0, timeout = 30000, check = false)
    private ${__Table__}Service ${__table__}Service;


    @Override
    @PostMapping(value = "save")
    public ResponseResult save(${__Table__} ${__table__}) {
        ${__Table__} obj = ${__table__}Service.save(${__table__});
        return new ResponseResult(CommonCode.SUCCESS, obj);
    }

    @Override
    @GetMapping(value = "delete")
    public ResponseResult delete(${__pkJava__} ${__pk__}) {
        ${__Table__} obj = ${__table__}Service.delete(id);
        return new ResponseResult(CommonCode.SUCCESS, obj);
    }

    @Override
    @GetMapping(value = "getById")
    public ResponseResult getById(${__pkJava__} ${__pk__}) {
        ${__Table__} obj = ${__table__}Service.getById(id);
        return new ResponseResult(CommonCode.SUCCESS, obj);
    }

    @GetMapping("/findPage${__Table__}")
    public ResponseResult findPage${__Table__}(String jsonStr) {
        JSONObject json = JSONObject.parseObject(jsonStr);
        List<${__Table__}> list = ${__table__}Service.findPage${__Table__}(json);
        Integer total = ${__table__}Service.findCount${__Table__}(json);
        return new ResponseResult(CommonCode.SUCCESS, list, null,total);
    }
}
