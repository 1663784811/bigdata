package com.cyyaw.buildcode.code;


import lombok.Data;

/**
 * 列信息存储对象
 *
 * @author
 */
@Data
public class ColumnInfo {
    /**
     * 主键标识
     */
    private boolean isKey;
    /**
     * 列名称
     */
    private String name;
    /**
     * 数据类型
     */
    private int dataType;
    /**
     * 数据类型名称
     */
    private String dataTypeName;
    /**
     * 自增标识
     */
    private boolean isAutoIncrement;
    /**
     * 精度
     */
    private int precision;
    /**
     * 是否为空
     */
    private int isNull;
    /**
     * 小数位数
     */
    private int scale;
    /**
     * 默认值
     */
    private String defaultValue;

}