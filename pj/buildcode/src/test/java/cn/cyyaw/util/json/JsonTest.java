package com.cyyaw.util.json;

import com.cyyaw.util.entity.User;
import com.cyyaw.util.tools.JsonUtil;
import org.junit.Test;

public class JsonTest {


    @Test
    public void test01() {
        User user = new User();
        user.setAge(1);
        user.setName("sssssss");

        String s = JsonUtil.toJSONStringFilter(user,"age");

        System.out.println("数据：" + s);

    }


}
