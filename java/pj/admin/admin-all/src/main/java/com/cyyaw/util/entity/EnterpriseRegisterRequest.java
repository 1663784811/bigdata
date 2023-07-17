package com.cyyaw.util.entity;

import com.cyyaw.enterprise.table.entity.EEnterprise;
import com.cyyaw.user.utils.entity.LoginRequest;
import lombok.Data;


@Data
public class EnterpriseRegisterRequest {

    private EEnterprise eEnterprise;

    private LoginRequest admin;


}
