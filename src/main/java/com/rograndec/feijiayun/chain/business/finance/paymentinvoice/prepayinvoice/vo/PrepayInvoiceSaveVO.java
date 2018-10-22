package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.prepayinvoice.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_prepay_invoice
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
@ApiModel
@Validated
public class PrepayInvoiceSaveVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID ,修改的时候需要传递,新增时不需要")
    private Long id;


    /**
     * 开票日期
     */
    @ApiModelProperty(value = "开票日期",required = true)
    @JsonFormat(pattern="yyyy-MM-dd")
    @NotNull(message = "开票日期不能为空")
    private Date billDate;

    /**
     * 开票人员ID
     */
    @ApiModelProperty(value = "开票人员ID",required = true)
    @NotNull(message = "开票人员不能为空")
    private Long billManId;

    /**
     * 供货单位ID
     */
    @ApiModelProperty(value = "供货单位ID",required = true)
    @NotNull(message = "供货单位不能为空")
    private Long supplierId;

    @ApiModelProperty(value = "数据类型,默认为价格清单:0,1:企业,2:供应商",required = true)
    @NotNull(message = "供货单位类型不能为空")
    private Integer type = 0;

    /**
     * 草稿ID
     * */
    @ApiModelProperty(value = "草稿ID")
    private String redisKeyValue;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "修改原因")
    private String reason;

    @ApiModelProperty(name = "prepayInvoiceDetailSaveVOS",value = "商品品种行集合")
    @NotNull(message = "商品品种不能为空")
    private List<PrepayInvoiceDetailSaveVO> prepayInvoiceDetailSaveVOS;

    @ApiModelProperty(name = "prepayInvoiceInfoSaveVO",value = "开票信息")
    private PrepayInvoiceInfoSaveVO prepayInvoiceInfoSaveVO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Long getBillManId() {
        return billManId;
    }

    public void setBillManId(Long billManId) {
        this.billManId = billManId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<PrepayInvoiceDetailSaveVO> getPrepayInvoiceDetailSaveVOS() {
        return prepayInvoiceDetailSaveVOS;
    }

    public void setPrepayInvoiceDetailSaveVOS(List<PrepayInvoiceDetailSaveVO> prepayInvoiceDetailSaveVOS) {
        this.prepayInvoiceDetailSaveVOS = prepayInvoiceDetailSaveVOS;
    }

    public PrepayInvoiceInfoSaveVO getPrepayInvoiceInfoSaveVO() {
        return prepayInvoiceInfoSaveVO;
    }

    public void setPrepayInvoiceInfoSaveVO(PrepayInvoiceInfoSaveVO prepayInvoiceInfoSaveVO) {
        this.prepayInvoiceInfoSaveVO = prepayInvoiceInfoSaveVO;
    }

    public String getRedisKeyValue() {
        return redisKeyValue;
    }

    public void setRedisKeyValue(String redisKeyValue) {
        this.redisKeyValue = redisKeyValue;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}