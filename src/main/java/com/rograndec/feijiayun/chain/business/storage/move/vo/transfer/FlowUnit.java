package com.rograndec.feijiayun.chain.business.storage.move.vo.transfer;

/**
 * 流向单位
 *
 DEPT(0,"部门"),
 HEADQUARTERS(1,"总部"),
 STORES(2,"门店"),
 SUPPLIER(3,"供货单位")
 * Created by zhaiwei on 2017/9/29.
 */
public class FlowUnit {

    /**
     * 流向单位类型（0-部门；1-总部；2-）
     */
    private Integer flowUnitType;

    /**
     * 流向单位ID
     */
    private Long flowUnitId;

    /**
     * 流向单位编码
     */
    private String flowUnitCode;

    /**
     * 流向单位名称
     */
    private String flowUnitName;

    public FlowUnit(Integer flowUnitType, Long flowUnitId, String flowUnitCode, String flowUnitName) {
        this.flowUnitType = flowUnitType;
        this.flowUnitId = flowUnitId;
        this.flowUnitCode = flowUnitCode;
        this.flowUnitName = flowUnitName;
    }

    public FlowUnit() {
    }

    public Integer getFlowUnitType() {
        return flowUnitType;
    }

    public void setFlowUnitType(Integer flowUnitType) {
        this.flowUnitType = flowUnitType;
    }

    public Long getFlowUnitId() {
        return flowUnitId;
    }

    public void setFlowUnitId(Long flowUnitId) {
        this.flowUnitId = flowUnitId;
    }

    public String getFlowUnitCode() {
        return flowUnitCode;
    }

    public void setFlowUnitCode(String flowUnitCode) {
        this.flowUnitCode = flowUnitCode;
    }

    public String getFlowUnitName() {
        return flowUnitName;
    }

    public void setFlowUnitName(String flowUnitName) {
        this.flowUnitName = flowUnitName;
    }
}
