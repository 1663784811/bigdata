package com.cyyaw.entity;

import com.cyyaw.enterprise.table.entity.EEnterprise;
import lombok.Data;


@Data
public class EnterpriseRegisterRequest {

    private EEnterprise eEnterprise;

    private LoginRequest admin;


}
