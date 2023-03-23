package com.cyyaw.service.enterprise;

import com.cyyaw.table.enterprise.entity.EEnterprise;
import com.cyyaw.util.tools.PageRespone;

import java.util.List;
import java.util.Map;

public interface EEnterpriseService {

    // 查询所有
    List<EEnterprise> findAll(Map<String,Object> map);

    // 分页查询
    PageRespone<EEnterprise> findPage(Map<String,Object> map);

    // 保存数据
    EEnterprise save(EEnterprise enterprise);

    // 删除
    boolean delById(Integer[] id);
    // ==========================================

    /**
     * 企业注册
     */
    EEnterprise registerEnterprise(EEnterprise enterprise);


}
