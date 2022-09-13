package com.cf.carpark.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author why
 */
@Service(version = "1.0.0")
@Transactional
@Slf4j
public class ${__Table__}ServiceImpl implements ${__Table__}Service {

    @Autowired
    private ${__Table__}Mapper ${__table__}Mapper;

    @Autowired
    private IdWorker idWorker;

    @Override
    public ${__Table__} save(${__Table__} ${__table__}) {
        String id = ${__table__}.get${__Pk__}();
        if(StringUtils.isEmpty(id)){
            ${__table__}.set${__Pk__}(idWorker.nextId());
            // 新增
            ${__table__}Mapper.insertSelective(${__table__});
        }else{
            // 修改
            // 首先查一次
            ${__Table__} obj = ${__table__}Mapper.selectByPrimaryKey(id);
            BeanUtils.copyProperties(${__table__},obj);
            // 再更新
            ${__table__}Mapper.updateByPrimaryKey(${__table__});
        }
        return ${__table__};
    }

    @Override
    public ${__Table__} delete(${__pkJava__} ${__pk__}) {
        ${__Table__} obj = ${__table__}Mapper.selectByPrimaryKey(${__pk__});
        log.info("========>> 尝试删除：${__Table__} 删除数据: {}", obj);
        ${__table__}Mapper.deleteByPrimaryKey(obj.getId());
        log.info("========>> 删除成功");
        return obj;
    }

    @Override
    public List<${__Table__}> getListByQuery(${__Table__}Query cfCarParkLinkUserQuery) {
        List<${__Table__}> ${__table__}s = ${__table__}Mapper.selectByQuery(cfCarParkLinkUserQuery);
        return ${__table__}s;
    }

    @Override
    public ${__Table__} getById(${__pkJava__} ${__pk__}) {
        ${__Table__} ${__table__} = ${__table__}Mapper.selectByPrimaryKey(${__pk__});
        return ${__table__};
    }

    @Override
    public List<${__Table__}> findPage${__Table__}(JSONObject json) {
        return ${__table__}Mapper.selectByExample(TkMyBatisExampleUtils.createPageExample(${__Table__}.class, json));
    }

    @Override
    public Integer findCount${__Table__}(JSONObject json) {
        return ${__table__}Mapper.selectCountByExample(TkMyBatisExampleUtils.createExample(${__Table__}.class, json));
    }
}
