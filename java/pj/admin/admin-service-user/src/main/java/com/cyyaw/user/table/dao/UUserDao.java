package com.cyyaw.user.table.dao;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.user.table.entity.UUser;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UUserDao extends BaseDao<UUser, Integer> {

    @Query("select m from UUser m where m.tid in ( select t.touserid from UFriendsUser t where t.userid = ?1 )")
    List<UUser> findByUserid(String userid);

    @Query("select m from UUser m where m.type =?1")
    List<UUser> findByType(Integer type);

    @Query("select m from UUser m where m.tid in ( select t.userid from UGroupUser t where t.groupid = ?1)")
    List<UUser> findByGroup(String userid);

    List<UUser> findByAccount(String account);

    @Query("select m from UUser m where m.openId =?1 and m.unionId = ?2")
    List<UUser> findByOpenIdAndUnionid(String openid, String unionid);

    @Query("select m from UUser m where m.openId =?1")
    List<UUser> findByOpenId(String openid);

}
