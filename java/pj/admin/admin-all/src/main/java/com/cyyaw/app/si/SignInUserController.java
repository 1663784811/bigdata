package com.cyyaw.app.si;


import com.cyyaw.user.config.TokenData;
import com.cyyaw.user.service.TAdminService;
import com.cyyaw.user.table.entity.TAdmin;
import com.cyyaw.user.utils.LoginInfo;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "app后台用户")
@RequestMapping("/app/si/admin/signIn")
@RestController
public class SignInUserController {

    @Autowired
    private TAdminService tAdminService;

    @ApiOperation(value = "app后台用户信息", notes = "app后台用户信息")
    @GetMapping("/userInfo")
    public BaseResult userInfo(@TokenData LoginInfo loginInfo) {
        String enterpriseId = loginInfo.getEnterpriseId();
        String account = loginInfo.getAccount();
        TAdmin admin = tAdminService.findByAccount(enterpriseId, account);
        return BaseResult.ok(admin);
    }

}
