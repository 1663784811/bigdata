package com.cyyaw.jpa;

import com.cyyaw.util.tools.PageRespone;

import java.util.List;
import java.util.Map;

public interface BaseTableService<T, D> {

    List<T> findAll(Map<String, Object> map);

    PageRespone<T> findPage(Map<String, Object> map);

    T findId(D id);

    T save(T t);

    void del(D ...idArr);

}
