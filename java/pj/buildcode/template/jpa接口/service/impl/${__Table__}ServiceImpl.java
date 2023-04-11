package ${basePackage}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



@Slf4j
@Service
public class ${__Table__}ServiceImpl extends BaseService<${__Table__}, ${__pkJava__}> implements ${__Table__}Service {

    @Autowired
    private ${__Table__}Dao ${__table__}Dao;

    @Override
    public BaseDao getBaseDao() {
        return ${__table__}Dao;
    }

}

