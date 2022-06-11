package cn.cyyaw.buildcode.extend.mybatis;

import cn.cyyaw.buildcode.extend.CodeTemplate;
import cn.cyyaw.buildcode.croe.tools.OperationTools;
import cn.cyyaw.buildcode.croe.entity.java.JavaColumn;
import cn.cyyaw.buildcode.croe.entity.java.JavaData;

import java.util.List;

public class TkJavaEntity implements CodeTemplate {

    // ================
    private String table;
    private JavaColumn primaryKey;
    private List<JavaColumn> javaColumns;


    public TkJavaEntity(JavaData javaData) {
        this.setJavaData(javaData);
    }


    @Override
    public void setJavaData(JavaData javaData) {
        this.table = javaData.getTable();
        this.javaColumns = javaData.getJavaColumns();
        for (int i = 0; i < javaColumns.size(); i++) {
            JavaColumn javaColumn = javaColumns.get(i);
            Boolean isPrimary = javaColumn.getIsPrimary();
            if (isPrimary) {
                primaryKey = javaColumn;
            }
        }
    }


    public String getBeanList() {
        StringBuffer res = new StringBuffer();
        for (JavaColumn javaColumn: javaColumns) {
            res.append("\n");
            res.append("        @ApiModelProperty(\""+javaColumn.getNote()+"\")\n");
            if(javaColumn.getIsPrimary()){
                res.append("        @Id\n");
            }
            res.append("        @Column(name = \""+javaColumn.getColumnName()+"\")\n");
            res.append("        private " + javaColumn.getJavaType() +" "+ OperationTools.indexToLowerCase(javaColumn.getColumnName())+";\n");
        }
        return res.toString();
    }


}
