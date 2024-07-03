package com.cyyaw.demo.jpa.service.impl;


import com.cyyaw.demo.jpa.service.CttCommentService;
import com.cyyaw.demo.jpa.table.dao.CttCommentDao;
import com.cyyaw.demo.jpa.table.entity.CttComment;
import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CttCommentServiceImpl extends BaseService<CttComment, Integer> implements CttCommentService {

    @Autowired
    private CttCommentDao cttCommentDao;

    @Override
    public BaseDao getBaseDao() {
        return cttCommentDao;
    }

}

