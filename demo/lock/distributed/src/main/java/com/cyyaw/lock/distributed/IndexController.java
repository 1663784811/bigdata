package com.cyyaw.lock.distributed;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private Integer data = 0;

    /**
     * 更新数据
     */
    @GetMapping("/updateData")
    public void updateData(){
        data++;
        System.out.println("数据为:"+data);
    }


}
