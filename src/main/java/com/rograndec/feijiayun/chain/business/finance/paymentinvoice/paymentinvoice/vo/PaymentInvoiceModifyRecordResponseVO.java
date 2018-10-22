package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentinvoice.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * saas_prepay_invoice_modify_record
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
@ApiModel
public class PaymentInvoiceModifyRecordResponseVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 表名
     */
    @ApiModelProperty(value = "表名")
    private String tableName;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long keyId;

    /**
     * 修改字段英文名称
     */
    @ApiModelProperty(value = "修改字段英文名称")
    private String columnEnName;

    /**
     * 修改字段中文名称（修改项目）
     */
    @ApiModelProperty(value = "修改字段中文名称（修改项目）")
    private String columnChName;

    /**
     * 原内容
     */
    @ApiModelProperty(value = "原内容")
    private String oldContent;

    /**
     * 新内容
     */
    @ApiModelProperty(value = "新内容")
    private String newContent;

    /**
     * 修改原因
     */
    @ApiModelProperty(value = "修改原因")
    private String reason;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    /**
     * 修改人ID
     */
    @ApiModelProperty(value = "修改人ID")
    private Long modifierId;

    /**
     * 修改人编码
     */
    @ApiModelProperty(value = "修改人编码")
    private String modifierCode;

    /**
     * 修改人名称
     */
    @ApiModelProperty(value = "修改人名称")
    private String modifierName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getKeyId() {
        return keyId;
    }

    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    public String getColumnEnName() {
        return columnEnName;
    }

    public void setColumnEnName(String columnEnName) {
        this.columnEnName = columnEnName;
    }

    public String getColumnChName() {
        return columnChName;
    }

    public void setColumnChName(String columnChName) {
        this.columnChName = columnChName;
    }

    public String getOldContent() {
        return oldContent;
    }

    public void setOldContent(String oldContent) {
        this.oldContent = oldContent;
    }

    public String getNewContent() {
        return newContent;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifierCode() {
        return modifierCode;
    }

    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }
}