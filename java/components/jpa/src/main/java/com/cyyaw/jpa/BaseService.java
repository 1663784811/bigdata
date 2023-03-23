package com.cyyaw.jpa;

import cn.hutool.json.JSONObject;
import com.cyyaw.util.tools.PageRespone;

import java.util.List;


public abstract class BaseService<T, D> implements BaseTableService<T, D> {
    public abstract BaseDao getBaseDao();

    @Override
    public List<T> findAll(JSONObject json) {



        String sort = json.getStr("sort");



        if (null != sort) {
//            return getBaseDao().findAll(new JpaSpecification<T>(json), sort);


        } else {
            return getBaseDao().findAll(new JpaSpecification<T>(json));
        }
        return null;
    }

    @Override
    public PageRespone<T> findPage(JSONObject json) {
//        return getBaseDao().findAll(new JpaSpecification<T>(jsonStr), pageRequest);
        return null;
    }

    @Override
    public T findId(D d) {
        return (T) getBaseDao().findByid(d);
    }

    @Override
    public T save(T t) {
        return (T) getBaseDao().save(t);
    }

    @Override
    public void del(D[] ds) {
        getBaseDao().deleteInBatch(getBaseDao().findByidIsIn(ds));
    }
}
