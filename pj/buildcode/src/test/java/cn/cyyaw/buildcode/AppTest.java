package cn.cyyaw.buildcode;

import cn.cyyaw.buildcode.env.controller.CodeController;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppTest {


    @Test
    public void shouldAnswerWithTrue() throws SQLException, IOException, ClassNotFoundException {

        CodeController codeController = new CodeController();

        codeController.buildCode("jdbc:mysql://127.0.0.1:3306/config", "root", "because", "bolog");

    }

    @Test
    public void test001(){

        Date date = new Date(1619071607840L);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sd.format(date);
        System.out.println(format);
    }
}
