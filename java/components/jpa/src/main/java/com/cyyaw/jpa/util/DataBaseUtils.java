package com.cyyaw.jpa.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.cyyaw.jpa.util.entity.FieldInfo;
import com.cyyaw.jpa.util.entity.TableInfo;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 数据库
 */
public class DataBaseUtils {

    /**
     * 获取 字段 信息
     */
    public static List<FieldInfo> tableInfo(JdbcTemplate jt, String table) {
        List<Map<String, Object>> maps = jt.queryForList("SHOW FULL COLUMNS FROM " + table);
        List<FieldInfo> arr = new ArrayList<>();
        for (int i = 0; i < maps.size(); i++) {
            Map<String, Object> oldMap = maps.get(i);
            FieldInfo fieldInfo = new FieldInfo();
            fieldInfo.setField(StrUtil.toString(oldMap.get("Field")));
            fieldInfo.setType(StrUtil.toString(oldMap.get("Type")));
            fieldInfo.setCollation(StrUtil.toString(oldMap.get("Collation")));
            fieldInfo.setIsNull(StrUtil.toString(oldMap.get("Null")));
            fieldInfo.setKey(StrUtil.toString(oldMap.get("Kield")));
            fieldInfo.setDefaultValue(StrUtil.toString(oldMap.get("Default")));
            fieldInfo.setExtra(StrUtil.toString(oldMap.get("Extra")));
            arr.add(fieldInfo);
        }
        return arr;
    }


    /**
     * 创建inset 语句
     *
     * @param tableInfo
     * @return
     */
    public static String createInsetSql(TableInfo tableInfo) {
        String table = tableInfo.getTable();
        List<FieldInfo> fieldInfoList = tableInfo.getFieldInfoList();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfo = fieldInfoList.get(i);
            String extra = fieldInfo.getExtra();
            if (extra.indexOf("auto_increment") == -1) {
                String field = fieldInfo.getField();
                if (sb.length() > 0) {
                    sb.append("," + field + "=?");
                } else {
                    sb.append(field + "=?");
                }
            }
        }
        return "insert into " + table + " " + sb.toString();
    }


    /**
     * 创建  随机 数据
     *
     * @return
     */
    public static Object[] createData(List<FieldInfo> fieldInfoList) {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < fieldInfoList.size(); i++) {
            FieldInfo fieldInfo = fieldInfoList.get(i);
            String extra = fieldInfo.getExtra();
            if (extra.indexOf("auto_increment") == -1) {
                String type = fieldInfo.getType();
                if (type.indexOf("int") != -1) {
                    list.add(RandomUtil.randomInt());
                } else if (type.indexOf("datetime") == -1) {
                    list.add(DateUtil.now());
                } else if (type.indexOf("date") == -1) {
                    list.add(DateUtil.date().toString("yyyy-MM-dd"));
                } else if (type.indexOf("decimal") == -1) {
                    list.add(RandomUtil.randomInt());
                } else {
                    list.add(RandomUtil.randomString(10));
                }
            }
        }
        return list.toArray();
    }


}
