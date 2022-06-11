package cn.cyyaw.economy.table.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ec_economy", catalog = "")
@org.hibernate.annotations.Table(appliesTo = "ec_economy", comment = "股票")
public class EcEconomy  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic
    @Column(name = "[id]", columnDefinition = "int COMMENT 'id'")
    private Integer id;


    @Basic
    @Column(name = "[name]", length = 45, nullable = true, columnDefinition = "varchar(45) COMMENT '名称'")
    private String name;


}
