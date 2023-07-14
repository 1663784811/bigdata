package com.cyyaw.tx.login;

import com.cyyaw.entity.AdminAuthToken;
import com.cyyaw.entity.EnterpriseRegisterRequest;
import com.cyyaw.entity.LoginRequest;
import com.cyyaw.service.admin.LoginService;
import com.cyyaw.service.admin.TPowerService;
import com.cyyaw.service.admin.TRoleService;
import com.cyyaw.service.enterprise.EEnterpriseService;
import com.cyyaw.table.admin.entity.TAdmin;
import com.cyyaw.table.admin.entity.TPower;
import com.cyyaw.table.enterprise.entity.EEnterprise;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Api(tags = "登录模块")
@RequestMapping("/login/admin")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TPowerService tPowerService;

    @Autowired
    private EEnterpriseService eEnterpriseService;


    @Autowired
    private TRoleService tRoleService;


    @ApiOperation(value = "退出登录", notes = "退出登录")
    @GetMapping(value = "/logout")
    public BaseResult logout() {
        return BaseResult.ok("退出登录成功");
    }

    @ApiOperation(value = "后台登录", notes = "后台登录")
    @PostMapping(value = "/login")
    public BaseResult login(@RequestBody LoginRequest loginRequest) {
        String enterpriseId = loginRequest.getEnterpriseId();
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        AdminAuthToken authToken = loginService.loginUserNameAndPassword(enterpriseId, userName, password);
        TAdmin tAdmin = authToken.getTAdmin();
        tAdmin.setPassword(null);
        List<TPower> tPowerList = tPowerService.findAdminPower(tAdmin.getTid());
        authToken.setAuthList(tPowerList);
        return BaseResult.ok(authToken, "登录成功");
    }

    @ApiOperation(value = "管理员注册", notes = "管理员注册")
    @PostMapping(value = "/register")
    public BaseResult register(@RequestBody LoginRequest registerInfo) {
        TAdmin tAdmin = loginService.adminRegister(registerInfo);
        tAdmin.setPassword(null);
        return BaseResult.ok(tAdmin, "注册成功");
    }


    @ApiOperation(value = "企业注册", notes = "企业注册")
    @PostMapping(value = "/enterpriseRegister")
    public BaseResult enterpriseRegister(@RequestBody EnterpriseRegisterRequest enterpriseRegisterRequest) {
        log.info("------------企业注册----------{}", enterpriseRegisterRequest);
        EEnterprise eEnterprise = enterpriseRegisterRequest.getEEnterprise();
        LoginRequest loginRequest = enterpriseRegisterRequest.getAdmin();
        // 第一步：保存企业信息
        EEnterprise e = eEnterpriseService.registerEnterprise(eEnterprise);
        // 第二步：保存负责人信息
        String tid = e.getTid();
        loginRequest.setEnterpriseId(tid);
        TAdmin admin = loginService.adminRegister(loginRequest);
        admin.setPassword(null);
        // 第三步:分配权限
        tRoleService.initRole(tid, admin.getTid());
        return BaseResult.ok(admin, "注册成功");
    }


    @GetMapping("/checkCode")
    public void checkCode(){

    }

}
