package com.cyyaw;

import cn.hutool.core.util.StrUtil;
import com.cyyaw.data.PageSettingData;
import com.cyyaw.service.StWordService;
import com.cyyaw.spider.table.dao.StWordDao;
import com.cyyaw.spider.table.entity.StWord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.File;
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
        log.info("数据库: {}", connection.getCatalog());
        log.info("------------ 启动成功 ---------");
        Environment environment = run.getBean(Environment.class);
        log.info("打开程序：http://127.0.0.1:" + environment.getProperty("local.server.port"));
        log.info("文档地址：http://127.0.0.1:" + environment.getProperty("local.server.port") + "/doc.html");

        PageSettingData pageSettingData = run.getBean(PageSettingData.class);
        pageSettingData.pageComponentsToComponentsObj();
    }


    public static void aaaa(ConfigurableApplicationContext run) throws Exception {


        StWordService bean = run.getBean(StWordService.class);
        StWordDao stWordDao = run.getBean(StWordDao.class);
        File file = new File("H:\\bbb\\aa");
        File[] files = file.listFiles();
        for (int j = 0; j < files.length; j++) {
            File file1 = files[j];
            if (file1.isFile()) {
                List<StWord> list = readBook(file1.getAbsolutePath());
                long start = System.currentTimeMillis();
                int count = 0;
                for (int i = 0; i < list.size(); i++) {


                    StWord stWord = list.get(i);
                    String word = stWord.getWord();
                    List<StWord> wo = stWordDao.findByWord(word);
                    // =================================================
                    count++;
                    long end = System.currentTimeMillis() - start;
                    if (end > 1000) {
                        log.info("============" + end + "毫秒    执行:" + count + "条数据");
                        start = System.currentTimeMillis();
                        count = 0;
                    }
                    // =================================================
                    if (wo.size() == 1) {
                        StWord stWord1 = wo.get(0);
                        Integer num = stWord1.getNum();
                        Integer num1 = stWord.getNum();
                        stWord1.setNum(num + num1);
                        stWordDao.save(stWord1);
                    } else if (wo.size() == 0) {
                        bean.save(stWord);
                    } else {
                        System.err.println("=============错误：" + word);
                    }
                    // =================================================


                }
            }
        }


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
            String linStr = line.replace(".", " ").replace("\"", " ").replace("?", "").replace(",", " ").replace(";", " ").replace("!", " ").replace("_", " ").replace("-", " ").replace("‘", " ").replace(":", " ");
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

