package com.cyyaw.service.impl.login;

import com.cyyaw.config.exception.WebException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCommon {


    /**
     * 验证密码
     */
    public static void checkPassword(String appId, String password, String dbPassword) {
        if (dbPassword.equals(password)) {

        } else {
            WebException.fail("用户名或密码错误");
        }
    }

    /**
     * 密码加密
     */
    public static String pwdEncryption(String pwd) {

        return pwd;
    }

    /**
     * 验证手机
     */
    public static void verifyPhoneCode(String appId, String phone, String eCode) {
        if (!eCode.equals("")) {

        } else {
            WebException.fail("验证码错误");
        }
    }

    /**
     * 获取手机验证码
     */
    public static void getPhoneCode(String appId, String phone) {

    }
}
