package ${basePackage}.controller;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ObjectUtils;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Date;

@Slf4j
@RequestMapping("/admin/${__table__}")
@RestController
public class ${__Table__}Controller {

    @Autowired
    private ${__Table__}Service ${__table__}Service;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPage")
    public BaseResult<${__Table__}> findPage${__Table__}(@RequestParam Map<String, Object> map) {
        PageRespone<${__Table__}> page = ${__table__}Service.findPage(new JSONObject(map));
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findId${__Table__}")
    public BaseResult findId${__Table__}(${__pkJava__} id) {
        ${__Table__} obj = ${__table__}Service.findId(id);
        return BaseResult.ok(obj);
    }


    /**
     * 添加或修改
     */
    @PostMapping("/save${__Table__}")
    public BaseResult save${__Table__}(@RequestBody ${__Table__} saveObj) {
        ${__Table__} obj = null;
        ${__pkJava__} id = saveObj.get${__Pk__}();
        if (ObjectUtils.isEmpty(id)) {
            //添加
            saveObj.setCreateTime(new Date());
<#list javaColumns as column>
        <#if column.columnName == 'tid' >
             saveObj.setTid(WhyStringUtil.getUUID());
        </#if>
        <#if column.columnName == 'createtime' >
             saveObj.setCreatetime(new Date());
        </#if>
</#list>
            log.info("添加:{}", saveObj);
            obj = ${__table__}Service.save(saveObj);
        } else {
            //修改
            log.info("修改:{}", saveObj);
            ${__Table__} cpObj = ${__table__}Service.findId(id);
            Assert.notNull(cpObj, "操作失败！");
            BeanUtils.copyProperties(saveObj,cpObj);
            obj = ${__table__}Service.save(cpObj);
        }
        return BaseResult.ok(obj);
    }

    /**
     * 删除
     */
    @PostMapping("/del${__Table__}")
    public BaseResult del${__Table__}(@RequestBody ${__pkJava__} ${__pk__}Arr[]) {
        ${__table__}Service.del(${__pk__}Arr);
        return BaseResult.ok("删除成功");
    }

}
