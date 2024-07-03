package com.cyyaw.demo.jpa.service.impl;


import com.cyyaw.demo.jpa.service.CttContentService;
import com.cyyaw.demo.jpa.table.dao.CttContentDao;
import com.cyyaw.demo.jpa.table.entity.CttContent;
import com.cyyaw.jpa.BaseDao;
import com.cyyaw.jpa.BaseService;
import com.cyyaw.jpa.JpaSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@Slf4j
@Service
public class CttContentServiceImpl extends BaseService<CttContent, Integer> implements CttContentService {

    @Autowired
    private CttContentDao cttContentDao;

    @Override
    public BaseDao getBaseDao() {
        return cttContentDao;
    }


    public void aaa() {

        // ========================================
        CttContent cttContent = new CttContent();
        Example<CttContent> example = Example.of(cttContent);
        cttContentDao.findAll(example);
        // ========================================

        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<CttContent> all = cttContentDao.findAll(pageRequest);

        // ========================================
        cttContentDao.findAll(example, pageRequest);
        // ========================================
        Sort userId = Sort.by("userId").descending();
        cttContentDao.findAll(userId);

        // ========================================

        // cttContentDao.findAll(new SimpleJpaRepository<CttContent, Integer>());


       // new JpaSpecification("").like().eq().page(0, 10);


    }

}

