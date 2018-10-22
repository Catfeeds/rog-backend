package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class SaveDistrReturnCheckHeadVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收货单id
     */
    @ApiModelProperty(value = "收货单id")
    private Long receiveId;

    /**
     * 配货类型ID
     */
    @ApiModelProperty(value = "配货类型ID")
    private Integer distrType;

    /**
     * 验收日期
     */
    @ApiModelProperty(value = "验收日期")
    private Date checkDate;

    /**
     * 验收人员1ID
     */
    @ApiModelProperty(value = "验收人员1ID")
    private Long checkerId;

    /**
     * 验收人员2ID
     */
    @ApiModelProperty(value = "验收人员2ID")
    private Long secondCheckerId;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    public Long getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Long receiveId) {
        this.receiveId = receiveId;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Long getCheckerId() {
        return checkerId;
    }

    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    public Long getSecondCheckerId() {
        return secondCheckerId;
    }

    public void setSecondCheckerId(Long secondCheckerId) {
        this.secondCheckerId = secondCheckerId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SaveDistrReturnCheckHeadVO[" +
                "receiveId=" + receiveId +
                ", distrType=" + distrType +
                ", checkDate=" + checkDate +
                ", checkerId=" + checkerId +
                ", secondCheckerId=" + secondCheckerId +
                ", remark='" + remark + '\'' +
                ']';
    }
}
