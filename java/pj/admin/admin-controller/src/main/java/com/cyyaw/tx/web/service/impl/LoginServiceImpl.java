package com.cyyaw.tx.web.service.impl;

import com.cyyaw.entity.AdminAuthToken;
import com.cyyaw.entity.AuthToken;
import com.cyyaw.entity.LoginRequest;
import com.cyyaw.table.tadmin.TAdmin;
import com.cyyaw.table.tadmin.TPower;
import com.cyyaw.table.tadmin.TRole;
import com.cyyaw.table.tadmin.UUser;
import com.cyyaw.util.tools.WebErrCodeEnum;
import com.cyyaw.tx.web.service.LoginService;
import com.cyyaw.tx.web.service.UUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private TAdminDaoSystem tAdminDaoSystem;

    @Autowired
    private TRoleDaoSystem tRoleDaoSystem;

    @Autowired
    private TPowerSystem tPowerSystem;

    @Autowired
    private UUserDao uUserDao;

    @Autowired
    private UUserService uUserService;

    @Autowired
    private TAdminDao tAdminDao;

    @Autowired
    private TRoleDao tRoleDao;




    @Override
    public TAdmin getLoignInfo(String account) {
        List<TAdmin> tAdmins = tAdminDaoSystem.getLoignInfo(account);
        if (tAdmins == null || tAdmins.size() == 0) {
            WebException.fail("账号不存在");
        } else if (tAdmins.size() > 1) {
            WebException.fail("账号异常请与管理员联系");
        }
        return tAdmins.get(0);
    }

    @Override
    public List<TRole> getRolesByTAdminTid(String tid) {
        return tRoleDaoSystem.getRolesByTAdminTid(tid);
    }

    @Override
    public List<TPower> getTPowerByTAdminTid(String tid) {
        return tPowerSystem.getTPowerByTAdminTid(tid);
    }

    /**
     * 管理员注册
     *
     * @param tAdmin
     * @return
     */
    @Override
    public TAdmin register(TAdmin tAdmin) {
//        List<TAdmin> tAdmins = tAdminDaoSystem.getLoignInfo(tAdmin.getAccount());
//        if (tAdmins != null && tAdmins.size() > 0) WebException.fail("账号已经被注册");
//        String tid = StringUtilWHY.getUUID();
//        String salt = StringUtilWHY.getRandomString(20);
//        String hash = new SimpleHash(hashAlgorithmName, tAdmin.getPassword(), salt, hashIterations).toString();
//        TAdmin tAdmin1 = new TAdmin();
//        tAdmin1.setAccount(tAdmin.getAccount());
//        tAdmin1.setPassword(hash);
//        tAdmin1.setTid(tid);
//        tAdmin1.setSalt(salt);
//        tAdmin1.setEmail(tAdmin.getEmail());
//        tAdmin1.setNickname(tAdmin.getAccount());
//        tAdmin1.setCanlogintime(new Date());
//        tAdmin1.setCreatetime(new Date());
//        tAdmin1.setStatus(0);
//        return tAdminDaoSystem.save(tAdmin1);
        return null;
    }

    /**
     * 修改密码
     *
     * @param password
     * @param newpassword
     * @return
     */
    @Override
    public TAdmin updatePassword(String password, String newpassword) {
//        Subject subject = SecurityUtils.getSubject();
//        Session session = subject.getSession();
//        TAdmin oldeTAdmin = (TAdmin) session.getAttribute(ShiroEnum.USERINFO);
//        TAdmin tAdmin = tAdminDaoSystem.findByid(oldeTAdmin.getId());
//        String hash = new SimpleHash(hashAlgorithmName, password, tAdmin.getSalt(), hashIterations).toString();
//        if (!tAdmin.getPassword().equals(hash)) WebException.fail("原密码错误");
//        String hash1 = new SimpleHash(hashAlgorithmName, newpassword, tAdmin.getSalt(), hashIterations).toString();
//        tAdmin.setPassword(hash1);
//        return tAdminDaoSystem.save(tAdmin);
        return null;
    }

    /**
     * 修改个人信息
     *
     * @param tAdmin
     * @return
     */
    @Override
    public TAdmin updateUserInfo(TAdmin tAdmin) {
//        Subject subject = SecurityUtils.getSubject();
//        Session session = subject.getSession();
//        TAdmin oldeTAdmin = (TAdmin) session.getAttribute(ShiroEnum.USERINFO);
//        TAdmin tAdmin1 = tAdminDaoSystem.findByid(oldeTAdmin.getId());
//        WhyBeanUtils.copyPropertiesAccess(tAdmin, tAdmin1, LoginConstants.UpdateColumnArr);
//        TAdmin save = tAdminDaoSystem.save(tAdmin1);
//        session.setAttribute(ShiroEnum.USERINFO, save);
//        return save;
        return null;
    }

    @Override
    public AuthToken weixinLogin(String openid, String unionid) {
        // 第一步: 查用户
        List<UUser> uUserList = uUserDao.findByOpenId(openid);
        UUser user = null;
        if(null != uUserList && uUserList.size()>0){
            user = uUserList.get(0);
        }else{
            UUser uUser = new UUser();
            uUser.setAccount(IdWorker.nextId());
            uUser.setPassword(IdWorker.nextId());
            uUser.setOpenid(openid);
            uUser.setCreatetime(new Date());
            uUser.setDel(0);
            uUser.setTid(IdWorker.nextId());
            uUser.setBalance(BigDecimal.ZERO);
            uUser.setUnionid(unionid);
            user = uUserService.save(uUser);
        }
        String account = user.getAccount();
        String tid = user.getTid();
        // 第三步: 查角色
        List<TRole> roleList = new ArrayList<>();
        StringBuffer sb = new StringBuffer("user");
        if(null != roleList && roleList.size()>0){
            for (TRole role:roleList) {
                if(sb.length()>0){
                    sb.append(role.getCode());
                }else{
                    sb.append(","+role.getCode());
                }
            }
        }
        // 第四步: 生成jwt
        String token = JwtTokenUtils.createToken(account, tid, sb.toString());
        AuthToken authToken = new AuthToken();
        authToken.setUUser(user);
        authToken.setJwtToken(token);
        return authToken;
    }

    @Override
    public AdminAuthToken loginUserNameAndPassword(String userName, String password) {
        // 第一步: 查用户
        TAdmin tAdmin = tAdminDao.findByAccount(userName);
        if(ObjectUtils.isEmpty(tAdmin)){ WebException.fail(WebErrCodeEnum.WEB_LOGINERR,"用户名不存在");}
        String account = tAdmin.getAccount();
        String tid = tAdmin.getTid();
        String passworded = tAdmin.getPassword();
        // 第二步: 对比密码
        if(!BCryptUtil.matches(password, passworded)){ WebException.fail(WebErrCodeEnum.WEB_LOGINERR,"密码错误");}
        // 第三步: 查角色
        List<TRole> roleList = tRoleDao.findByAdminId(tid);
        StringBuffer sb = new StringBuffer();
        if(null != roleList && roleList.size()>0){
            for (TRole role:roleList) {
                if(sb.length()>0){
                    sb.append(role.getCode());
                }else{
                    sb.append(","+role.getCode());
                }
            }
        }
        // 第四步: 生成jwt
        String token = JwtTokenUtils.createToken(account, tid, sb.toString());
        AdminAuthToken authToken = new AdminAuthToken();
        authToken.setTAdmin(tAdmin);
        authToken.setJwtToken(token);
        // 第五步: jwt存放到 redis里
        return authToken;
    }

    @Override
    public TAdmin adminRegister(LoginRequest registerInfo) {
        String password = registerInfo.getPassword();
        String passworded = registerInfo.getPassworded();
        if(password==null || (!password.equals(passworded))){ WebException.fail(WebErrCodeEnum.WEB_REGISTER_ERR,"再次密码不一致");}
        // 判断用户是否存在
        String userName = registerInfo.getUserName();
        TAdmin tAdmin = tAdminDao.findByAccount(userName);
        if(!ObjectUtils.isEmpty(tAdmin)){
            WebException.fail(WebErrCodeEnum.WEB_REGISTER_ERR,"用户已存在");
        }
        // 添加用户
        String encode = BCryptUtil.encode(password);
        TAdmin t = new TAdmin();
        t.setTid(IdWorker.nextId());
        t.setAccount(userName);
        t.setPassword(encode);
        TAdmin save = tAdminDao.save(t);
        return save;
    }

}
