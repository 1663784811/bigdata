package com.cyyaw.config.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.cyyaw.user.utils.LoginInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalContext {

    private static ThreadLocal<LoginInfo> anyinfo = new ThreadLocal<>();

    public static LoginInfo getLoginInfo() {
        return anyinfo.get();
    }

    public static void setLoginInfo(String json) {
        anyinfo.remove();
        if (StrUtil.isNotBlank(json)) {
            try {
                log.info("----------请求头信息:----------{}", json);
                LoginInfo loginInfo = new JSONObject(json).toBean(LoginInfo.class);
                anyinfo.set(loginInfo);
            } catch (Exception e) {
                log.error("-----------ThreadLocalContext.setLoginInfo--------{}", e);
            }
        }
    }

}
