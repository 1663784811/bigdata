package com.cyyaw.spider.task;

import cn.cyyaw.util.tools.WhyStringUtil;
import com.cyyaw.spider.table.dao.PaATagDao;
import com.cyyaw.spider.table.entity.PaATag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DomainName {


    private volatile AtomicInteger page = new AtomicInteger(1);
    private volatile Integer size = 1000;
    private volatile AtomicInteger num = new AtomicInteger(1);

    @Autowired
    private PaATagDao paATagDao;


    public void updateDomainName(){
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
        new Thread(()->{
            updateFn();
        }).start();
    }


    private void updateFn(){

        boolean ispagedo = true;

        do{
            int andIncrement = page.getAndIncrement();
            PageRequest of = PageRequest.of(andIncrement, size);
            Page<PaATag> all = paATagDao.findAll(of);
            int totalPages = all.getTotalPages();
            if(totalPages>andIncrement){
                List<PaATag> content = all.getContent();
                for (PaATag paATag: content) {
                    String href = paATag.getHref();
                    if(!ObjectUtils.isEmpty(href)){
                        String domainName = WhyStringUtil.getDomainName(href);
                        paATag.setDn(domainName);
                        paATagDao.save(paATag);
                        System.out.println("更新速度:"+ (num.getAndIncrement()));
                    }
                }
            }else{
                ispagedo = false;
            }


        }while (ispagedo);
    }







}
