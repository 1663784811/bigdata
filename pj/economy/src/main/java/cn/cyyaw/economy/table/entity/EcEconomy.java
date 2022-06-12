package cn.cyyaw.economy.table.entity;

import cn.cyyaw.jpa.entity.BaseTable;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ec_economy", catalog = "")
@org.hibernate.annotations.Table(appliesTo = "ec_economy", comment = "股票")
public class EcEconomy extends BaseTable implements Serializable {

    @Basic
    @Column(name = "ad_type", columnDefinition = "int COMMENT '地区类型{1:A股,2:港股,3:美股}'")
    private Integer adType;



    @Basic
    @Column(name = "name", columnDefinition = "varchar(255) COMMENT '股票名称'")
    private String name;


    @Basic
    @Column(name = "company_name", columnDefinition = "varchar(255) COMMENT '公司名称'")
    private String companyName;










}
