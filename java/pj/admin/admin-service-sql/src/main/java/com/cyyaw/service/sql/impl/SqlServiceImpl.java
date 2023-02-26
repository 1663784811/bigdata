package com.cyyaw.service.sql.impl;

import com.cyyaw.entity.SelectEntity;
import com.cyyaw.service.sql.SqlService;
import com.cyyaw.table.config.dao.CSqlDao;
import com.cyyaw.table.config.entity.CSql;
import com.cyyaw.util.tools.BaseResult;
import com.cyyaw.util.tools.WhyStringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SqlServiceImpl implements SqlService {

    @Autowired
    private CSqlDao cSqlDao;


    @Override
    public BaseResult<CSql> sqlList(SelectEntity select) {
        int page = select.getPage();
        int size = select.getSize();
        CSql cSql = new CSql();
        ExampleMatcher matcher = ExampleMatcher.matching();
        PageRequest of = PageRequest.of(page - 1, size);
        Example<CSql> ex = Example.of(cSql, matcher);
        Page<CSql> sqlPage = cSqlDao.findAll(ex, of);

        int number = sqlPage.getNumber() + 1;
        int sizeN = sqlPage.getSize();
        long total = sqlPage.getTotalElements();
        BaseResult.Result result = new BaseResult.Result(number, sizeN, total);
        BaseResult<CSql> ok = BaseResult.ok(sqlPage.getContent(), result);
        return ok;
    }

    @Override
    public CSql save(CSql cSql) {
        Integer id = cSql.getId();
        if (null != id) {
            CSql oldCSql = cSqlDao.findByid(id);
            BeanUtils.copyProperties(cSql, oldCSql);
            return cSqlDao.save(oldCSql);
        } else {
            cSql.setCreateTime(new Date());
            cSql.setDel(0);
            cSql.setTid(WhyStringUtil.getUUID());
            return cSqlDao.save(cSql);
        }
    }

    @Override
    public void delSql(Integer[] idArr) {
        for (int i = 0; i < idArr.length; i++) {
            CSql cSql = cSqlDao.findByid(idArr[i]);
            cSqlDao.delete(cSql);
        }
    }
}
