package com.cyyaw.tx;


import cn.hutool.core.util.StrUtil;
import com.cyyaw.store.service.GTypeService;
import com.cyyaw.store.table.goods.entity.GType;
import com.cyyaw.user.utils.entity.TreeEntity;
import com.cyyaw.util.tools.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Api(tags = "商品品类")
@RestController
@RequestMapping("/shopping/goods/type")
public class ShoppingGoodsTypeController {

    @Autowired
    private GTypeService gTypeService;

    @ApiOperation(value = "商品品类", notes = "商品品类")
    @GetMapping("/enterpriseType")
    public BaseResult enterpriseType(GType gType) {
        if (StrUtil.isNotBlank(gType.getEnterpriseId())) {
            List<GType> wBannerList = gTypeService.findByExample(gType);
            TreeEntity treeEntity = new TreeEntity();
            for (GType tpower : wBannerList) {
                TreeEntity.Node<GType> node = new TreeEntity.Node<GType>();
                node.setTid(tpower.getTid());
                node.setData(tpower);
                node.setName(tpower.getName());
                node.setPid(tpower.getPid());
                treeEntity.add(node);
            }
            return BaseResult.ok(treeEntity);
        }
        return null;
    }


}
