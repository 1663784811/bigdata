package com.cyyaw.jpa.util.tools;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class JpaUtils {


    /**
     * 获取PageRequest  =====     分页
     */
    public static PageRequest getPageRequest(Integer page, Integer size, Sort sort) {
        PageRequest pageRequest;
        page = page == null ? 1 : page;
        size = size == null ? 20 : size;
        if (null != sort) {
            pageRequest = PageRequest.of(page - 1, size, sort);
        } else {
            pageRequest = PageRequest.of(page - 1, size);
        }
        return pageRequest;
    }


    /**
     * 获取Sort  ========  排序
     */
    public static Sort getSort(String sortStr) {
        Sort sort = null;
        if (null != sortStr) {
            sortStr = sortStr.toLowerCase();
            String[] sortArr = sortStr.split(",");
            // 判断是否有这个字段
            for (int i = 0; i < sortArr.length; i++) {
                String itemStr = sortArr[i];
                int lasted = itemStr.lastIndexOf("_desc");
                if (lasted == -1) {
                    if (null == sort) {
                        sort = Sort.by(Sort.Direction.ASC, itemStr);
                    } else {
                        sort.and(Sort.by(Sort.Direction.ASC, itemStr));
                    }
                } else {
                    String subStr = itemStr.substring(lasted);
                    if (null == sort) {
                        sort = Sort.by(Sort.Direction.DESC, subStr);
                    } else {
                        sort.and(Sort.by(Sort.Direction.DESC, subStr));
                    }
                }
            }
        }
        return sort;
    }


}
