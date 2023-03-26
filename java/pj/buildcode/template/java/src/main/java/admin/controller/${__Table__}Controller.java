package ${basePackage}.controller;

import ${basePackage}.service.${__Table__}Service;
import ${basePackage}.table.entity.${__Table__};
import ${basePackage}.table.entityconst.${__Table__}Const;

import com.cyyaw.jpa.BaseConstants;
import com.cyyaw.util.entity.SelectEntity;
import com.cyyaw.util.tools.*;

<#list javaColumns as column>
    <#if column.isFk>
import ${basePackage}.table.entity.${operationTools.indexToUpperCase(column.pkTableName)};
import ${basePackage}.table.entityconst.${operationTools.indexToUpperCase(column.pkTableName)}Const;
    </#if>
</#list>

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Date;


@Slf4j
@Api(tags = "${javaData.table}")
@RequestMapping("/admin/${javaData.table}")
@RestController
public class ${__Table__}Controller {

    @Autowired
    private ${__Table__}Service ${__table__}Service;

    /**
     * 表:${javaData.table} ===> 所有:带条件
     */
    @GetMapping(value = "/findAll${__Table__}")
    public void findAll${__Table__}(HttpServletResponse response, String jsonStr, SelectEntity selectEntity) {
        List<${__Table__}> list = ${__table__}Service.findAll(jsonStr, selectEntity);
        ResponseUtils.responseJsonFilter(response, list,${__Table__}Const.filterselectColumnArr);
    }

    /**
     * 分页条件查询
     */
    @GetMapping(value = "/findPage")
    public BaseResult<${__Table__}> findPage(@RequestParam Map<String, Object> map) {
        PageRespone<${__Table__}> page = ${__table__}Service.findPage(new JSONObject(map));
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping(value = "/findId${__Table__}")
    public void findId${__Table__}(HttpServletResponse response,@RequestParam ${__pkJava__} ${__pk__}) {
        ${__Table__} obj = ${__table__}Service.findId(${__pk__});
        ResponseUtils.responseJsonFilter(response, obj,${__Table__}Const.filterselectColumnArr);
    }


    /**
     * 添加或修改
     */
    @PostMapping(value = "/save${__Table__}")
    public void save${__Table__}(HttpServletResponse response,@RequestBody ${__Table__} ${__table__}) {
        ${__Table__} obj = null;
        //添加
        ${__pkJava__} id = ${__table__}.get${operationTools.indexToUpperCase( __Pk__ )}();
        if (null == id) {
            //添加
            log.info("添加:{}", ${__table__});
            WhyBeanUtils.filterField(${__table__}, ${__Table__}Const.filteraddColumnArr);
<#list javaColumns as column>
        <#if column.columnName == 'tid' >
            ${__table__}.setTid(WhyStringUtil.getUUID());
        </#if>
        <#if column.columnName == 'createtime' >
            ${__table__}.setCreatetime(new Date());
        </#if>
</#list>
            obj = ${__table__}Service.save(${__table__});
        } else {
            //修改
            log.info("修改:{}", ${__table__});
            ${__Table__} ${__table__}1 = ${__table__}Service.findId(${__pk__});
            Assert.notNull(${__table__}1, "操作失败！");
            WhyBeanUtils.filterField(${__table__}, ${__Table__}Const.filteraddColumnArr);
            obj = ${__table__}Service.save(${__table__});
        }
        ResponseUtils.responseJsonFilter(response, obj,${__Table__}Const.filterselectColumnArr);
    }

    /**
     * 删除
     */
    @PostMapping(value = "/del${__Table__}")
    public Map del${__Table__}( @RequestBody ${__pkJava__} ${__pk__}Arr[]) {
        ${__table__}Service.del(${__pk__}Arr);
        return BaseConstants.tableDelSuccess;
    }

<#list javaColumns as column>
<#if column.isFk>
    /**
     * 外键查询
     */
    @GetMapping(value = "/fk${__Table__}Find${operationTools.indexToUpperCase(column.pkTableName)}")
    @ResponseBody
    public List fk${__Table__}Find${operationTools.indexToUpperCase(column.pkTableName)}(${column.javaType} ${operationTools.indexToLowerCase(column.pkTableColumn)}) {
        List<${__Table__}> ${__table__}List = ${__table__}Service.fk${__Table__}Find${operationTools.indexToUpperCase(column.pkTableName)}(${operationTools.indexToLowerCase(column.pkTableColumn)});
        return ${__table__}List;
    }
</#if>
</#list>
}
