package com.cf.carpark.service;


import java.util.List;


/**
 * @author why
 */
public interface ${__Table__}Service {

    /**
     * 保存
     */
    ${__Table__} save(${__Table__} ${__table__});

    /**
     * 删除
     */
    ${__Table__} delete(${__pkJava__} ${__pk__});

    /**
     * 查询列表
     */
    List<${__Table__}> getListByQuery(${__Table__}Query ${__table__}Query);

    /**
     * 根据ID查询
     */
    ${__Table__} getById(${__pkJava__} ${__pk__});

    List<__Table__> findPage__Table__(JSONObject json);

    Integer findCount__Table__(JSONObject json);
}
