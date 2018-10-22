package com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.vo;

import com.rograndec.feijiayun.chain.business.finance.receivablepayment.payment.entity.FinancePaymentModifyRecord;
import com.rograndec.feijiayun.chain.business.finance.receivablepayment.receivable.vo.ReceiveUpdateRecordVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class PayUpdateRecordVO implements Serializable{

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

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
     * 修改原因
     */
    @ApiModelProperty(value = "修改原因")
    private String reason;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    @Override
    public String toString() {
        return "PayUpdateRecordVO{" +
                "id=" + id +
                ", updateTime=" + updateTime +
                ", modifierId=" + modifierId +
                ", modifierCode='" + modifierCode + '\'' +
                ", modifierName='" + modifierName + '\'' +
                ", reason='" + reason + '\'' +
                ", columnChName='" + columnChName + '\'' +
                ", oldContent='" + oldContent + '\'' +
                ", newContent='" + newContent + '\'' +
                '}';
    }

    public PayUpdateRecordVO convertToVO(FinancePaymentModifyRecord record) {
        PayUpdateRecordVO recordVO = new PayUpdateRecordVO();
        recordVO.setId(record.getId());
        recordVO.setUpdateTime(record.getUpdateTime());
        recordVO.setModifierId(record.getModifierId());
        recordVO.setModifierCode(record.getModifierCode());
        recordVO.setModifierName(record.getModifierName());
        recordVO.setReason(record.getReason());
        recordVO.setColumnChName(record.getColumnChName());
        recordVO.setOldContent(record.getOldContent());
        recordVO.setNewContent(record.getNewContent());
        return recordVO;
    }
}
