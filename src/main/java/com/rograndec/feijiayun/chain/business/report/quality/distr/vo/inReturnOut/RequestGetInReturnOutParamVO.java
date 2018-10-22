package com.rograndec.feijiayun.chain.business.report.quality.distr.vo.inReturnOut;


import com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.returnout.RequestGetCommonGoodsParamVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：
 * Created by ST on 2017/10/23 13:57
 */

public class RequestGetInReturnOutParamVO extends RequestGetCommonGoodsParamVO{

    /**
     * 购进退出单号
     */
    @ApiModelProperty(value = "配进退出出库单号")
    private String code;

    @ApiModelProperty(value = "出库人员名称")
    private String outManName;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "配货单位编码")
    private String distrUnitCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "配货单位名称")
    private String distrUnitName;

    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;


    @ApiModelProperty(value = "状态（83-待复核；91-待开票;92-待支付;93-已支付）")
    private Integer status;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOutManName() {
        return outManName;
    }

    public void setOutManName(String outManName) {
        this.outManName = outManName;
    }

    public String getDistrUnitCode() {
        return distrUnitCode;
    }

    public void setDistrUnitCode(String distrUnitCode) {
        this.distrUnitCode = distrUnitCode;
    }

    public String getDistrUnitName() {
        return distrUnitName;
    }

    public void setDistrUnitName(String distrUnitName) {
        this.distrUnitName = distrUnitName;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}