package com.cyyaw.jpa;

import com.cyyaw.util.tools.PageRespone;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public abstract class BaseService<T, D> implements BaseTableService<T, D> {
    public abstract BaseDao getBaseDao();

    @Override
    public List<T> findAll(Map<String, Object> map) {
//        Sort sort = JpaUtils.getSort(selectModel);
//        if (null != sort) {
//            return getBaseDao().findAll(new JpaSpecification<T>(jsonStr), sort);
//        } else {
//            return getBaseDao().findAll(new JpaSpecification<T>(jsonStr));
//        }
        return null;
    }

    @Override
    public PageRespone<T> findPage(Map<String, Object> map) {
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
