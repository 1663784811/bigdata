package com.cyyaw.spider.table.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * @author why
 */
@NoRepositoryBean
public interface BaseDao<E, ID> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {

    /**
     * 根据ID查询
     *
     * @return
     */
    E findByid(ID id);

    /**
     * 根据id数组查询
     */
    List<E> findByidIsIn(List<ID> idList);

    @Override
    void deleteById(ID id);


}
