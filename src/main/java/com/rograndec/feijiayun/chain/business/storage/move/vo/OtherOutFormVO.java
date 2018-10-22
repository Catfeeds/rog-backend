package com.rograndec.feijiayun.chain.business.storage.move.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.*;

/**
 * 表单,保存修改
 */
@ApiModel
public class OtherOutFormVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "其他出库头表id,新增时不需要传递,修改的时候需要传递")
    private Long id;

    /**
     * 出库日期
     */
    @ApiModelProperty(value = "出库日期",required = true)
    private Date outDate;

    /**
     * 出库人员ID
     */
    @ApiModelProperty(value = "出库人员ID",required = true)
    private Long outManId;


    /**
     * 复核人员ID
     */
    @ApiModelProperty(value = "复核人员ID",required = true)
    private Long auditManId;


    /**
     * 出库类型（0-赠送；1-报损；2-内部领用；3-抽检出库；4-其它）
     */
    @ApiModelProperty(value = "出库类型（0-赠送；1-报损；2-内部领用；3-抽检出库；4-其它）",required = true)
    private Integer outType;

    /**
     * 流向单位类型（0-部门；1-总部；2-）
     */
    @ApiModelProperty(value = "流向单位类型（0-部门；1-总部；2-）")
    private Integer flowUnitType;

    /**
     * 流向单位ID
     */
    @ApiModelProperty(value = "流向单位ID",required = true)
    private Long flowUnitId;

    /**
     * 流向监管码
     */
    @ApiModelProperty(value = "流向监管码")
    private String flowCode;


    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "其他出库明细集合")
    private List<OtherOutDetailFormVO> otherOutDetailFormVOS;

    public static Set<Long> getOutManIdAndAuditManId(OtherOutFormVO otherOutFormVO){
        Set<Long> ids = new HashSet<>();
        ids.add(otherOutFormVO.getAuditManId());
        ids.add(otherOutFormVO.getOutManId());
        return ids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public Long getOutManId() {
        return outManId;
    }

    public void setOutManId(Long outManId) {
        this.outManId = outManId;
    }

    public Long getAuditManId() {
        return auditManId;
    }

    public void setAuditManId(Long auditManId) {
        this.auditManId = auditManId;
    }


    public Integer getOutType() {
        return outType;
    }

    public void setOutType(Integer outType) {
        this.outType = outType;
    }

    public Long getFlowUnitId() {
        return flowUnitId;
    }

    public void setFlowUnitId(Long flowUnitId) {
        this.flowUnitId = flowUnitId;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public List<OtherOutDetailFormVO> getOtherOutDetailFormVOS() {
        return otherOutDetailFormVOS;
    }

    public void setOtherOutDetailFormVOS(List<OtherOutDetailFormVO> otherOutDetailFormVOS) {
        this.otherOutDetailFormVOS = otherOutDetailFormVOS;
    }

    public Integer getFlowUnitType() {
        return flowUnitType;
    }

    public void setFlowUnitType(Integer flowUnitType) {
        this.flowUnitType = flowUnitType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
