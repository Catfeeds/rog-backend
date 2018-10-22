package com.rograndec.feijiayun.chain.business.goods.price.vo;

import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApiModel
public class GoodsTaxRateReturnVO implements Serializable {
    /**
     *  主键ID
     */
    @ApiModelProperty(value=" 主键ID")
    private Long id;

    /**
     * 编码
     */
    @ApiModelProperty(value=" 编码")
    private String code;

    /**
     * 税率
     */
    @ApiModelProperty(value=" 税率")
    private BigDecimal taxRate;


    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value=" 状态（0-禁用；1-启用）")
    private Integer status;

    public static List<GoodsTaxRateReturnVO> getGoodsTaxRateReturnVOs4GoodsTaxRates(List<GoodsTaxRate> goodsTaxRates){
        List<GoodsTaxRateReturnVO> rateReturnVOS = new ArrayList<>();

        for(GoodsTaxRate gt : goodsTaxRates){

            GoodsTaxRateReturnVO goodsTaxRateReturnVO = getGoodsTaxRateReturnVO4GoodsTaxRate(gt);
            rateReturnVOS.add(goodsTaxRateReturnVO);
        }

        return rateReturnVOS;
    }


    public static GoodsTaxRateReturnVO getGoodsTaxRateReturnVO4GoodsTaxRate(GoodsTaxRate goodsTaxRate){
        GoodsTaxRateReturnVO goodsTaxRateReturnVO = new GoodsTaxRateReturnVO();

        goodsTaxRateReturnVO.setId(goodsTaxRate.getId());
        goodsTaxRateReturnVO.setCode(goodsTaxRate.getCode());
        goodsTaxRateReturnVO.setStatus(goodsTaxRate.getStatus());
        goodsTaxRateReturnVO.setTaxRate(goodsTaxRate.getTaxRate());

        return goodsTaxRateReturnVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}