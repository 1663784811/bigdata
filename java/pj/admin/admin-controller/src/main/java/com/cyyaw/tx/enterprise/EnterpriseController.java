package com.cyyaw.tx.enterprise;


import com.cyyaw.service.enterprise.EEnterpriseService;
import com.cyyaw.table.enterprise.dao.EEnterpriseDao;
import com.cyyaw.table.enterprise.entity.EEnterprise;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import java.util.Map;

@Slf4j
@Api(tags = "企业管理")
@RequestMapping("/admin/enterprise")
@RestController
public class EnterpriseController {

    @Autowired
    private EEnterpriseService eEnterpriseService;

    @Autowired
    private EEnterpriseDao eEnterpriseDao;


    @GetMapping("/findPage")
    public BaseResult<EEnterprise> findPage(@RequestParam Map<String, Object> map) {
//        PageRespone<EEnterprise> page = eEnterpriseService.findPage(new JSONObject(map));
//        return BaseResult.ok(page);

//        EEnterprise one = eEnterpriseDao.getOne(1);

//
//        if(eEnterpriseDao instanceof SimpleJpaRepository){
//            SimpleJpaRepository ss = (SimpleJpaRepository)  eEnterpriseDao;
//
////            ss.
//
//
//        }


        eEnterpriseDao.findAll(new Specification<EEnterprise>() {
            @Override
            public Predicate toPredicate(Root<EEnterprise> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Class<? extends EEnterprise> javaType = root.getJavaType();
                EntityType<EEnterprise> model = root.getModel();
                Attribute<EEnterprise, ?> note = model.getDeclaredAttribute("note");
                String name = note.getName();
                Class<?> javaType1 = note.getJavaType();

                log.info("================{}", root);

                Predicate note1 = cb.equal(root.get("id"), "111111");



                return cb.and(note1);
            }
        });




        return null;


    }


}
