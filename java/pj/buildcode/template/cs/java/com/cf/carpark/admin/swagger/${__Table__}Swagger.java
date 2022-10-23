package com.cf.carpark.admin.swagger;

import com.cf.carpark.domain.${__Table__};
import com.cf.carpark.domain.request.CfCarParkLinkUserQuery;
import com.cf.framework.domain.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;

/**
 * @author why
 */
@Api(tags = {"${javaData.tableNote}"})
public interface ${__Table__}Swagger {

    @ApiOperation(value = "保存${javaData.tableNote}(新增/修改)")
    @ApiImplicitParams({
            @ApiImplicitParam(name="authorization",value = "jwt串(请加\"Bearer \"前缀，注意有空格)",required=true,paramType="header",dataType="string")
    })
    ResponseResult save(@Validated ${__Table__} ${__table__});

    @ApiOperation(value = "删除${javaData.tableNote}")
    @ApiImplicitParams({
            @ApiImplicitParam(name="authorization",value = "jwt串(请加\"Bearer \"前缀，注意有空格)",required=true,paramType="header",dataType="string")
    })
    ResponseResult delete(String id);

    @ApiOperation(value = "根据ID 查询${javaData.tableNote}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "jwt串(请加\"Bearer \"前缀，注意有空格)", required = true, paramType = "header", dataType = "string"),
            @ApiImplicitParam(name = "${__pk__}", value = "", dataType = "")
    })
    ResponseResult getById(${__pkJava__} ${__pk__});

    @ApiOperation(value = "根据条件获取${javaData.tableNote}列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "authorization", value = "jwt串(请加\"Bearer \"前缀，注意有空格)", required = true, paramType = "header", dataType = "string"),
    })
    ResponseResult selectListByCondition(String conditions, HttpServletRequest request) throws Exception;

}
