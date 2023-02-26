package com.cyyaw.entity;

import com.cyyaw.table.enterprise.entity.EEnterprise;
import lombok.Data;


@Data
public class EnterpriseRegisterRequest {

    private EEnterprise eEnterprise;

    private LoginRequest admin;


}
