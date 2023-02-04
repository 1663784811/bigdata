package com.cyyaw.init;

import com.cyyaw.table.confit.dao.CPageComponentsDao;
import com.cyyaw.table.confit.dao.CPageDao;
import com.cyyaw.table.spider.tag.dao.TagDao;
import com.cyyaw.table.spider.tag.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 初始化
 */
@Service
public class InitApplication {

    @Autowired
    private CPageDao pageDao;

    @Autowired
    private CPageComponentsDao cPageComponentsDao;

    @Autowired
    private TagDao tagDao;


    @Autowired
    private InitTag initTag;

    public void init() {
//        CPageComponents components = new CPageComponents();
//        components.setId(0);
//        components.setTid("aa");
//        components.setPageId("aa");
//        components.setName("表格组件");
//        components.setComponents_code("aa");
//        components.setData("");
//        components.setSort(0);
//        cPageComponentsDao.save(components);
//        tag();
    }


    public void tag() {
//        Tag tag = getTag(1);
//        tag.setId(0);
//        tag.setCreateTime(new Date());
//        tag.setDel(0);
//        tag.setNote("");
//        tag.setTid("");
//        tag.setName("");
//        tag.setPid("");
//        tag.setTreecode("");
//        tag.setType(0);
//
//        tagDao.save(tag);

    }


    public Tag getTag(Integer id) {
        Tag obj = tagDao.findByid(id);
        if (null == obj) {
            Tag tag = new Tag();
            tag.setId(id);
            return tag;
        }
        return obj;
    }


}
