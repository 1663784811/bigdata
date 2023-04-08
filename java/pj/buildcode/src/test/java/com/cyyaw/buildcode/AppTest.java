package com.cyyaw.buildcode;

import com.cyyaw.buildcode.env.controller.CodeController;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppTest {


    @Test
    public void shouldAnswerWithTrue() throws SQLException, IOException, ClassNotFoundException {

        CodeController codeController = new CodeController();

        codeController.buildCode("jdbc:mysql://192.168.118.8:3306/t_config?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai", "root", "123456", "");

    }

    @Test
    public void test001(){

        Date date = new Date(1619071607840L);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sd.format(date);
        System.out.println(format);
    }
}
