package com.cyyaw.demo.mybatis.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/tableName")
@RestController
public class TemplateController {


    /**
     * 页码
     * 大小
     * 条件
     */
    @GetMapping("/queryPage")
    public void queryPage() {

    }


    @GetMapping("/queryObj")
    public void queryObj() {

    }

    @PostMapping("/saveData")
    public void saveData(){

    }

    @PostMapping("/delData")
    public void delData(){

    }



}

