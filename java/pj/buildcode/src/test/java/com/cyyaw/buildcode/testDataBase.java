package com.cyyaw.buildcode;

import com.cyyaw.buildcode.croe.database.DataBase;
import com.cyyaw.buildcode.croe.entity.java.JavaColumn;
import com.cyyaw.buildcode.croe.entity.java.JavaData;
import com.cyyaw.buildcode.croe.entity.java.PrimaryKeys;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class testDataBase {


    /**
     * 获取数据表数据
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void test01() throws SQLException, ClassNotFoundException {
        DataBase dataBase = new DataBase(null, null, null, null);
        List<JavaData> tableList = dataBase.getTableList(null);
        for (int i = 0; i < tableList.size(); i++) {
            JavaData javaData = tableList.get(i);
            System.out.println("=======================");
            System.out.println("表名：" + javaData.getTableNote());
            System.out.println("类型：" + javaData.getTableType());
            System.out.println("注释：" + javaData.getTableNote());
            System.out.println("字段列表===");
            List<JavaColumn> javaColumns = javaData.getJavaColumns();
            for (int j = 0; j < javaColumns.size(); j++) {
                JavaColumn javaColumn = javaColumns.get(j);
//                System.out.println("==============");
//                System.out.println("=====字段名：" + javaColumn.getName());
//                System.out.println("=====注释：" + javaColumn.getNote());
//                System.out.println("=====数据库类型：" + javaColumn.getDbType());
//                System.out.println("=====java类型：" + javaColumn.getJavaType());
//                System.out.println("=====大小：" + javaColumn.getLength());
//                System.out.println("=====是否自增：" + javaColumn.getIsAutoIncrement());
//                System.out.println("=====是否是主键：" + javaColumn.getIsPrimary());
//                System.out.println("=====是否是外键：" + javaColumn.getIsFktable());
//                System.out.println("=====外键指向的表：" + javaColumn.getPkTableName());
//                System.out.println("=====外键指向的字段：" + javaColumn.getPkTableColumn());
//                System.out.println("==============");
            }
        }
    }

    /**
     * 获取主键列表
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void test02() throws SQLException, ClassNotFoundException {
        System.out.println("主键列表====================================");
        DataBase dataBase = new DataBase(null, null, null, null);
        List<PrimaryKeys> primaryKeys = dataBase.getPrimaryKeys("t_admin");
        for (int i = 0; i < primaryKeys.size(); i++) {
            System.out.println(primaryKeys.get(i).getColumnName());
        }

    }

    /**
     * 获取外键列表
     *
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    @Test
    public void test03() throws SQLException, ClassNotFoundException {
        DataBase dataBase = new DataBase(null, null, null, null);
        System.out.println("外键列表====================================");
//        List<ForeignKey> t_admin = dataBase.getForeignKeys("t_role_power");
//        for (int i = 0; i < t_admin.size(); i++) {
//            System.out.println(t_admin.get(i).getPkTableName());
//            System.out.println(t_admin.get(i).getPkColumnName());
////            System.out.println(t_admin.get(i).getPkTableNote());
//        }
    }
}
