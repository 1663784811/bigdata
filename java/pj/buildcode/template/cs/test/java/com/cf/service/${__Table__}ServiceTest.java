package com.cf.ucenter.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = UcenterApplication.class
        ,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class ${__Table__}ServiceTest {

    @Autowired
    private ${__Table__}Mapper ${__table__}Mapper;

    private static String id = "1234564";

    @Test
    public void test001() {
        ${__Table__} ${__table__} = new ${__Table__}();
        ${__table__}.setCreateTime(System.currentTimeMillis());
        ${__table__}.setCarParkId("21215");
        ${__table__}.setUid("uid");
        for(int i=0;i<2;i++){
            String id = ${__table__}.getId();
            if(StringUtils.isEmpty(id)){
                ${__table__}.setId(this.id);
                // 新增
                ${__table__}Mapper.insertSelective(${__table__});
                log.info("添加成功：{}", JSONObject.toJSON(${__table__}));
            }else{
                // 修改
                // 首先查一次
                ${__Table__} obj = ${__table__}Mapper.selectByPrimaryKey(id);
                BeanUtils.copyProperties(${__table__},obj);
                // 再更新
                ${__table__}Mapper.updateByPrimaryKey(${__table__});
                log.info("修改成功：{}", JSONObject.toJSON(${__table__}));
            }

        }
    }

    @Test
    public void test002() {
        ${__Table__} ${__table__} = ${__table__}Mapper.selectByPrimaryKey(id);

        log.info("查询成功：{}", JSONObject.toJSON(${__table__}));
    }

    @Test
    public void test003() {


        ${__Table__} obj = ${__table__}Mapper.selectByPrimaryKey(id);
        log.info("========>> 尝试删除：${__table__} 删除数据: {}", obj);
        ${__table__}Mapper.deleteByPrimaryKey(obj.getId());
        log.info("========>> 删除成功");
    }






}
