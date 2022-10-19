package cn.cyyaw.buildcode.code;

import java.sql.*;

/**
 * @Description:
 * @Author: zyn
 * @Date: 2021-5-25
 */
public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String driverName="jdbc:mysql://139.198.115.132:3306/config";
        String userName="root";
        String userPwd="because";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection(driverName,userName,userPwd);
        getTableInfo(con,con.createStatement(),"t_admin");
    }
    /**
     * 取得一张表的结构信息
     * 使用DatabaseMetaData与ResultSetMetaData结合的方式获取全部属性
     * @param conn   数据连接
     * @param tableName    表名
     * @return  表结构中列的存储对象
     * @throws SQLException
     */
    public static void getTableInfo(Connection conn, Statement st, String tableName) throws SQLException {

        DatabaseMetaData dbmd = conn.getMetaData();
        ResultSet rs = dbmd.getColumns(null, null, tableName.toUpperCase(), null);
        /**判断字段是否自增*/
        String sql = "select * from " + tableName + " where 1=2";
        ResultSet rst = conn.prepareStatement(sql).executeQuery();
        ResultSetMetaData rsmd = rst.getMetaData();
        int i=1;

        while(rs.next()){




            //列名称
            String columnName = rs.getString("COLUMN_NAME");//列名
            //数据类型
            int dataType = rs.getInt("DATA_TYPE");//类型
            //数据类型名称
            String dataTypeName = rs.getString("TYPE_NAME");
            //精度,列的大小
            int precision = rs.getInt("COLUMN_SIZE");//精度
            //小数位数
            int scale = rs.getInt("DECIMAL_DIGITS");// 小数的位数
            //是否为空
            int isNull = rs.getInt("NULLABLE");//是否为空
            //字段默认值
            String defaultValue = rs.getString("COLUMN_DEF");
            //是否自增
            boolean isAutoIncrement = rsmd.isAutoIncrement(i); //自增

            ColumnInfo col = new ColumnInfo();
            col.setName(columnName);
            col.setDataType(dataType);
            col.setDataTypeName(dataTypeName);
            col.setPrecision(precision);
            col.setScale(scale);
            col.setIsNull(isNull);
            col.setDefaultValue(defaultValue);
            col.setAutoIncrement(isAutoIncrement);
            System.out.println(col);
            i++;
        }
        rs.close();
        /**设置主键*/
        rs = dbmd.getPrimaryKeys(null, null, tableName);
        while(rs.next()){
            System.out.println(rs.getString("COLUMN_NAME"));
        }
        rs.close();
    }


}