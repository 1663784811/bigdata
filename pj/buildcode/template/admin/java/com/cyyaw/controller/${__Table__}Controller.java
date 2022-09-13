package ${basePackage}.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.ObjectUtils;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * <pre>
 *
 * =========================  声名 START
 * 所有实体类： 属性都是小写
 * =========================  声名 END
 *
 * jsonChaXun - 查询json
 *      例：like_string_cjjlYhmc 对应 (1)_(2)_(3)
 *      (1) - 本条件进行查询时与值的对比关系
 *          like : where [字符串] like '%YYYY%'
 *          likeleft : where [字符串] like 'YYYY%'
 *          likeright : where [字符串] like '%YYYY'
 *          equals : where [字符串/数/日期] = YYY
 *          notequals : where [字符串/数/日期] != YYY
 *          ge : where [数/日期] >= YYY
 *          gt : where [数/日期] > YYY
 *          le : where [数/日期] <= YYY
 *          lt : where [数/日期] < YYY
 *      (2) - 本条件字段的数据类型
 *          string integer float double date
 *      (3) - 本条件对应字段的实体类（不区分大小写）
 *
 *
 * 简单查询条件  and or
 * json 格式：  {like_string_adminid:"abcdefg",like_string_username:"WHY"}
 * 对应的sql 语句：　　where  adminid like '%abcdefg%'  and username like '%WHY%'
 *
 * 复杂查询条件
 * json 格式
 * {or:{like_string_adminid:"abcdefg",like_string_username:"WHY"},like_string_username:"WHY"}
 * 对应的sql 语句： where ( adminid like '%abcdefg%' and username like '%WHY%' ) or username like '%WHY%'
 *
 *  jsonStr格式：      {like_string_adminid:"abcdefg",like_string_username:"WHY"}    传参： http://localhost:8080/admin?jsonStr={like_string_adminid:"abcdefg",like_string_username:"WHY"}
 *  sort格式：         admin_desc,adminname_asc                                      传参： http://localhost:8080/admin?sort=admin_desc,adminname_asc
 *  page格式：         1                                                             传参： http://localhost:8080/admin?page=1
 *  size格式：         30                                                            传参： http://localhost:8080/admin?size=30
 *  id格式:            abcddeffdfd                                                   传参： http://localhost:8080/admin?id=abcddeffdfd
 *  对象格式:          对象                                                           传参： http://localhost:8080/admin? 对象
 *  id数组格式:                                                                       传参： http://localhost:8080/admin? id数组
 *
 * 1.  查询所有         findAll表名     有参      jsonStr    sort
 *     分页条件查询     findPage表名    有参       jsonStr    sort    page    size
 *     根据ID查询      findId表名       有参      id
 *
 * 2.  添加           add表名          有参       对象
 *
 * 3.  修改           update表名       有参       对象
 *
 * 4.  删除           del表名          有参       id数组
 *
 *  </pre>
 */
@Slf4j
@RequestMapping("/admin/${__table__all__}")
@RestController
public class ${__Table__}Controller {

    @Reference(version = "1.0.0", retries = 0, timeout = 30000, check = false)
    private ${__Table__}Service ${__table__}Service;

    /**
     * 分页条件查询
     */
    @GetMapping("/findPage${__Table__}")
    public BaseResult findPage${__Table__}(String jsonStr, SelectEntity selectEntity) {
        PageRespone<${__Table__}> page = ${__table__}Service.findPage(jsonStr, selectEntity);
        return BaseResult.ok(page);
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/findId${__Table__}")
    public BaseResult findId${__Table__}(String id) {
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
            saveObj.set${__Pk__}(IdWorker.nextId());
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
