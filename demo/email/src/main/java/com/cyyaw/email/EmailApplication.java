package com.cyyaw.email;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmailApplication {


    public static void main(String[] args) {
        // POP3/SMTP服务     aifsjqolasonnbagf
        // IMAP/SMTP服务     acoqndzgynsjphcic
        // 授权码            addnhluvewgpocbbh



        MailAccount account = new MailAccount();
        account.setHost("smtp.qq.com");
        account.setPort(587);
        account.setAuth(true);
        account.setFrom("1663784811@qq.com");
        account.setUser("1663784811");
        account.setPass("");
        MailUtil.send(account, CollUtil.newArrayList("1663784811@qq.com"), "测试", "邮件来自Hutool测试", false);



    }



}
