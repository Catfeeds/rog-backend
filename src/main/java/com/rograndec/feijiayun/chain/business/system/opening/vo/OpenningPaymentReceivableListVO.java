package com.rograndec.feijiayun.chain.business.system.opening.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 期初应付（应收）VO
 * @author zhangyu
 * @create 2018-01-03
 */
public class OpenningPaymentReceivableListVO {

    /**
     * 期初应付(应收)表是否已存在数据（0：未存在；1:已存在）
     */
    @ApiModelProperty(value = "期初应付表是否已存在数据（0：未存在；1:已存在）")
    private Integer isGenerate;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 付款(收款)日期
     */
    @ApiModelProperty(value = "付款(收款)日期")
    private Date date;

    /**
     * 付款(收款)人员ID
     */
    @ApiModelProperty(value = "付款(收款)人员ID")
    private Long manId;


    /**
     * 付款(收款)人员名称
     */
    @ApiModelProperty(value = "付款(收款)人员名称")
    private String manName;

    /**
     * 金额合计
     */
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 当isGenerate=1，list为期初应付（应收）详情表数据，isGenerate=0，list为当前企业应该录入的数据
     */
    @ApiModelProperty(value = "当isGenerate=1，list为期初应付详情表数据，isGenerate=0，list为当前企业应该录入的数据")
    @Valid
    private List<OpeningPaymentReceivableDetailVO> detailVOS;

    public Integer getIsGenerate() {
        return isGenerate;
    }

    public void setIsGenerate(Integer isGenerate) {
        this.isGenerate = isGenerate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getManId() {
        return manId;
    }

    public void setManId(Long manId) {
        this.manId = manId;
    }

    public String getManName() {
        return manName;
    }

    public void setManName(String manName) {
        this.manName = manName;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<OpeningPaymentReceivableDetailVO> getDetailVOS() {
        return detailVOS;
    }

    public void setDetailVOS(List<OpeningPaymentReceivableDetailVO> detailVOS) {
        this.detailVOS = detailVOS;
    }
}
