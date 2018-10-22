package com.rograndec.feijiayun.chain.business.finance.receivableinvoice.reveivableinvoice.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class InvoiceModifyRecordPageVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 修改人名称
     */
    @ApiModelProperty(value = "修改人名称")
    private String modifierName;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
        private Date updateTime;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    @Override
    public String toString() {
        return "InvoiceModifyRecordPageVO[" +
                "id=" + id +
                ", modifierName='" + modifierName + '\'' +
                ", updateTime=" + updateTime +
                ", columnChName='" + columnChName + '\'' +
                ", oldContent='" + oldContent + '\'' +
                ", newContent='" + newContent + '\'' +
                ", reason='" + reason + '\'' +
                ']';
    }
}
