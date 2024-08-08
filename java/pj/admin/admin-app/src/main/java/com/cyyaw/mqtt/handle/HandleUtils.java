package com.cyyaw.mqtt.handle;

import cn.hutool.json.JSONObject;
import com.cyyaw.mqtt.entity.PwJson;
import org.apache.poi.ss.formula.functions.T;

public class HandleUtils {
    public static String getSendStr(Object pt, Object info) {
        JSONObject rest = new JSONObject();
        JSONObject response = new JSONObject();
        response.set("PK_Type", pt);
        response.set("Info", info);
        rest.set("Response", response);
        return rest.toString();
    }


//    public static T,M Request<T, M> getRequest(String msg) {
//        Request<T, M> request = new Request();
//
//        JSONObject entries = new JSONObject(msg);
//
//
//        return request;
//    }
//
//
//    public static class Request<T, M> {
//
//
//    }


}
