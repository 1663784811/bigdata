package com.cyyaw;

import cn.hutool.core.util.StrUtil;
import com.cyyaw.spider.table.entity.StWord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@SpringBootApplication
public class AllApplication {


    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(AllApplication.class, args);
        DataSource ds = run.getBean(DataSource.class);
        Connection connection = ds.getConnection();
        log.info("{}", connection.getCatalog());
        log.info("------------ 启动成功 ---------");
        Environment environment = run.getBean(Environment.class);
        log.info("打开程序：http://127.0.0.1:" + environment.getProperty("local.server.port"));
        log.info("打开程序：http://127.0.0.1:" + environment.getProperty("spring.jackson.default-property-inclusion"));

    }


    /**
     * 统计 单词出现次数
     */
    public static List<StWord> readBook(String filePath) throws Exception {
        Map<String, Integer> map = new ConcurrentHashMap();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            // 处理每一行的内容
            String linStr = line.
                    replace(".", " ")
                    .replace("\"", " ")
                    .replace("?", "")
                    .replace(",", " ")
                    .replace(";", " ")
                    .replace("!", " ")
                    .replace("_", " ")
                    .replace("-", " ")
                    .replace("‘", " ")
                    .replace(":", " ");
            String[] word = linStr.split(" ");
            for (int i = 0; i < word.length; i++) {
                String w = word[i];
                // 首页字母大字
                w = w.toLowerCase();
                if (StrUtil.isNotBlank(w)) {
                    Integer num = map.get(w);
                    if (num == null) {
                        map.put(w, 1);
                    } else {
                        map.put(w, num += 1);
                    }
                }
            }
        }
        List<StWord> list = new CopyOnWriteArrayList<>();
        for (String key : map.keySet()) {
            StWord stWord = new StWord();
            stWord.setWord(key);
            stWord.setNum(map.get(key));
            list.add(stWord);
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                StWord stWord = list.get(i);
                Integer num = stWord.getNum();
                StWord wo = list.get(j);
                Integer nu = wo.getNum();
                if (num < nu) {
                    list.set(i, wo);
                    list.set(j, stWord);
                }
            }
        }
        return list;
    }

}

