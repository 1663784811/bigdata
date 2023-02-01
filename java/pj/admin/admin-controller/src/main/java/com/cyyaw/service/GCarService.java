package com.cyyaw.service;

import com.cyyaw.config.common.entity.AddMyCar;
import com.cyyaw.config.common.entity.MyCarEntity;
import com.cyyaw.config.common.service.BaseTableService;
import com.cyyaw.config.table.table.entity.goods.GCar;

import java.util.List;

public interface GCarService extends BaseTableService<GCar, Integer> {

    /**
     * 获取我的购物车
     * @param uid
     * @param page
     * @param size
     * @return
     */
    List<MyCarEntity> myCar(String uid, Integer page, Integer size);

    /**
     * 加入购物车
     * @param uid
     * @param addMyCar
     */
    MyCarEntity addCar(String uid,String userName, AddMyCar addMyCar);

    Integer carNumber(String uid);

    void delCarData(String uid, String skuid);

}
