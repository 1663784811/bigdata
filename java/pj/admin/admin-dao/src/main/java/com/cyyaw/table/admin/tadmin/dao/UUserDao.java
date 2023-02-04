package com.cyyaw.table.admin.tadmin.dao;

import com.cyyaw.jpa.BaseDao;
import com.cyyaw.table.admin.tadmin.entity.UUser;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UUserDao extends BaseDao<UUser, Integer> {


    @Query("select m from UUser m where m.tid in ( select t.touserid from UFriendsUser t where t.userid = ?1 )")
    List<UUser> findByUserid(String userid);


    UUser findFirstByTid(String userid);

    List<UUser> findByType(Integer type);

    @Query("select m from UUser m where m.tid in ( select t.userid from UGroupUser t where t.groupid = ?1)")
    List<UUser> findByGroup(String userid);

    List<UUser> findByAccount(String account);

    @Query("select m from UUser m where m.openid =?1 and m.unionid = ?2")
    List<UUser> findByOpenIdAndUnionid(String openid, String unionid);

    @Query("select m from UUser m where m.openid =?1")
    List<UUser> findByOpenId(String openid);

}
