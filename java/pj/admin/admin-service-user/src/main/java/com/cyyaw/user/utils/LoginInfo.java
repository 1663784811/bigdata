package com.cyyaw.user.utils;

import lombok.Data;

/**
 * 登录信息
 */
@Data
public class LoginInfo {

    private Integer id;

    private String tid;

    private String enterpriseId;

    private String account;

    private String email;

    private String nickName;

    private String phone;

    private Integer status;

    private String trueName;

    private String role;
}
