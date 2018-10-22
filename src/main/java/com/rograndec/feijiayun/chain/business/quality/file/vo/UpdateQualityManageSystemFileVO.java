package com.rograndec.feijiayun.chain.business.quality.file.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class UpdateQualityManageSystemFileVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 当前单据状态（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）
     */
    @ApiModelProperty(value = "当前单据状态（0-生效；1-失效；2-废止；3-撤销；4-替换；5-销毁）")
    private Integer statusNow;

    /**
     * 更改状态（3-撤销；4-替换；5-销毁）
     */
    @ApiModelProperty(value = "更改状态（3-撤销；4-替换；5-销毁）")
    private Integer status;

    /**
     * 撤销日期/替换日期/销毁日期
     */
    @ApiModelProperty(value = "（撤销日期,替换日期,销毁日期）")
    private Date operateDate;

    /**
     * 撤销人员/替换人员/销毁人员
     */
    @ApiModelProperty(value = "（撤销人员,替换人员,销毁人员）")
    private String operator;

    /**
     * 撤销原因/替换文件编号/销毁原因
     */
    @ApiModelProperty(value = "（撤销原因,替换文件编号,销毁原因）")
    private String operateRemark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate = operateDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperateRemark() {
        return operateRemark;
    }

    public void setOperateRemark(String operateRemark) {
        this.operateRemark = operateRemark;
    }

    public Integer getStatusNow() {
        return statusNow;
    }

    public void setStatusNow(Integer statusNow) {
        this.statusNow = statusNow;
    }

    @Override
    public String toString() {
        return "UpdateQualityManageSystemFileVO[" +
                "id=" + id +
                ", statusNow=" + statusNow +
                ", status=" + status +
                ", operateDate=" + operateDate +
                ", operator='" + operator + '\'' +
                ", operateRemark='" + operateRemark + '\'' +
                ']';
    }
}
