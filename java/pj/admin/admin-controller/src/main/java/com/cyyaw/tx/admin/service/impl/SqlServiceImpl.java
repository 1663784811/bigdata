package com.cyyaw.tx.admin.service.impl;

import com.cyyaw.entity.SelectEntity;
import com.cyyaw.table.confit.dao.CSqlDao;
import com.cyyaw.table.confit.entity.CSql;
import com.cyyaw.tx.admin.service.SqlService;
import com.cyyaw.util.tools.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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


        PageRequest of = PageRequest.of(page, size);
        Example<CSql> ex = Example.of(cSql, matcher);
        Page<CSql> sqlPage = cSqlDao.findAll(ex, of);

        int number = sqlPage.getNumber();
        int sizeN = sqlPage.getSize();
        long total = sqlPage.getTotalElements();
        BaseResult.Result result = new BaseResult.Result(number, sizeN, total);
        BaseResult<CSql> ok = BaseResult.ok(sqlPage.getContent(), result);
        return ok;
    }
}
