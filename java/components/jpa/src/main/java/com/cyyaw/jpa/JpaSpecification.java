package com.cyyaw.jpa;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * jsonStr 格式
 */
@Slf4j
public class JpaSpecification<T> implements Specification<T> {
    /**
     * json查询条件
     */
    private JSONObject json;

    /**
     * 条件描述对象
     */
    private Predicate pred = null;

    /**
     * 查询条件
     * <p>
     * { page:1,size:20,note:"1524156",like_note:“abcsde”, }
     */
    public JpaSpecification(JSONObject json) {
        this.json = json;
    }


    /**
     * 转换成条件描述对象
     *
     * @param root  查询根对象
     * @param query 查询对象
     * @param cb    标准查询构造器
     * @return
     */
    public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder cb) {
        if (pred != null) {
            return pred;
        }
        return jsonPredicate(root, cb, json, null);
    }


    /**
     * 递归实现 ，json数据解释，Predicate组装
     *
     * @return
     */
    public Predicate jsonPredicate(final Root<T> root, final CriteriaBuilder cb, JSONObject json, String type) {
        if (json != null && json.size() > 0) {
            //解释json
            List<Predicate> predicateList = new ArrayList<>();
            for (String key : json.keySet()) {
                String keylc = key.toLowerCase().split("_")[0];
                if (keylc.equals("page") || keylc.equals("size")) {
                    continue;
                } else if (keylc.equals("or") || keylc.equals("and")) {
                    Predicate predicate1 = jsonPredicate(root, cb, json.getJSONObject(key), keylc);
                    if (null != predicate1) {
                        predicateList.add(predicate1);
                    }
                } else {
                    // ====================================
                    JpaWhereType wheres = JpaWhereType.eq;
                    String columns = key;
                    Object val = json.get(key);
                    JpaWhereType[] values = JpaWhereType.values();
                    for (int i = 0; i < values.length; i++) {
                        JpaWhereType value = values[i];
                        String where = value.getWhere();
                        String mk = where + "_";
                        if (key.indexOf(mk) == 0) {
                            wheres = value;
                            columns = key.substring(mk.length());
                            break;
                        }
                    }
                    // ========================
                    Predicate p = getPredicate(root, cb, wheres, columns, val);
                    if (null != p) {
                        predicateList.add(p);
                    }
                }
            }
            // ==================================================
            if (predicateList.size() > 0) {
                Predicate[] predicates = new Predicate[predicateList.size()];
                if (null != type && type.toLowerCase().equals("or")) {
                    return cb.or(predicateList.toArray(predicates));
                } else {
                    return cb.and(predicateList.toArray(predicates));
                }
            }
        }
        return null;
    }


    /**
     * @param root
     * @param cb
     * @param wheres  条件
     * @param columns 字段
     * @param value   值
     * @return
     */
    private Predicate getPredicate(final Root<T> root, final CriteriaBuilder cb, JpaWhereType wheres, final String columns, Object value) {


        String column = columns.toLowerCase();


        Predicate predicate = null;
        if (JpaWhereType.like.equals(wheres)) {
            predicate = cb.like(root.get(column).as(String.class), "%" + value + "%");
        } else if (JpaWhereType.likeL.equals(wheres)) {
            predicate = cb.like(root.get(column).as(String.class), "%" + value);
        } else if (JpaWhereType.likeR.equals(wheres)) {
            predicate = cb.like(root.get(column), value + "%");
        } else if (JpaWhereType.neq.equals(wheres)) {
            predicate = cb.notEqual(root.get(column), value);
        } else if (JpaWhereType.geq.equals(wheres)) {
            predicate = cb.ge(root.get(column), (Number) value);
        } else if (JpaWhereType.gt.equals(wheres)) {
            predicate = cb.gt(root.get(column), (Number) value);
        } else if (JpaWhereType.leq.equals(wheres)) {
            predicate = cb.le(root.get(column), (Number) value);
        } else if (JpaWhereType.lt.equals(wheres)) {
            predicate = cb.lt(root.get(column), (Number) value);
        } else {
            predicate = cb.equal(root.get(column), value);
        }
        return predicate;
    }
}
