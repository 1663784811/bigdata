package com.cyyaw.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 后台登录、注册 提交信息
 */
@Data
public class LoginRequest implements Serializable {

    @ApiModelProperty(value = "验证码Key", example = "abcdfef")
    private String codeUuid;

    @ApiModelProperty(value = "验证码", example = "123456")
    private String code;

    @ApiModelProperty(value = "企业ID", example = "123456")
    private String enterpriseId;

    @ApiModelProperty(value = "账号", example = "why", notes = "测试")
    private String userName;

    @ApiModelProperty(value = "密码", example = "password")
    private String password;

}
