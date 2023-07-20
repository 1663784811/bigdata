package com.cyyaw.sql.controller;


import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j

@Api(tags = "构建代码")
@RequestMapping("/admin/buildCode")
@RestController
public class BuildCodeController {



    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void loadTable() throws SQLException {


        Connection connection = jdbcTemplate.getDataSource().getConnection();




    }




}
