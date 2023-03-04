package com.cyyaw.service.admin;

import com.cyyaw.table.admin.entity.TAdmin;

public interface TAdminService {

    TAdmin findById(Integer id);

    TAdmin findByTid(String tid);

    TAdmin save(TAdmin admin);



}
