package com.rograndec.feijiayun.chain.business.goods.sale.controller;

import com.rograndec.feijiayun.chain.business.goods.sale.service.CommissionStrategyDetailService;
import io.swagger.annotations.Api;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by madong on 2017/9/5.
 */
@Api(value = "goods_sale_strategy_detail", description = "商品管理-销售管理-提成明细设置服务接口", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/goods/sale/strategy/detail")
@Validated
public class CommissionStrategyDetailController {
    private static final Log logger = LogFactory.getLog(CommissionStrategyDetailController.class);
    @Autowired
    CommissionStrategyDetailService royaltyStrategyDetailService;
}
