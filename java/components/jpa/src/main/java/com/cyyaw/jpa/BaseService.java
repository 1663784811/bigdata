package com.cyyaw.jpa;

import cn.hutool.json.JSONObject;
import com.cyyaw.jpa.util.tools.JpaUtils;
import com.cyyaw.util.tools.PageRespone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public abstract class BaseService<T, D> implements BaseTableService<T, D> {
    public abstract BaseDao getBaseDao();

    @Override
    public List<T> findAll(JSONObject json) {
        String sortStr = json.getStr("sort");
        Sort sort = JpaUtils.getSort(sortStr);
        Specification<T> sp = new JpaSpecification(json);
        if (null != sort) {
            return getBaseDao().findAll(sp, sort);
        } else {
            return getBaseDao().findAll(sp);
        }
    }

    @Override
    public PageRespone<T> findPage(JSONObject json) {
        String sortStr = json.getStr("sort");
        Integer page = json.getInt("page");
        Integer size = json.getInt("size");
        Sort sort = JpaUtils.getSort(sortStr);
        PageRequest pa = JpaUtils.getPageRequest(page, size, sort);
        Specification<T> sp = new JpaSpecification(json);
        Page all = getBaseDao().findAll(sp, pa);
        PageRespone respone = new PageRespone();
        respone.setContent(all.getContent());
        respone.setTotal(all.getTotalElements());
        respone.setPage(all.getNumber() + 1);
        respone.setSize(all.getSize());
        respone.setTotalPage(all.getTotalPages());
        return respone;
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
