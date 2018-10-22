package com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale;

import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherIn;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class ExcessSaleOtherInFormVO implements Serializable{
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 入库单号
     */
    @ApiModelProperty(value = "入库单号")
    private String code;

    /**
     * 入库日期
     */
    @ApiModelProperty(value = "入库日期")
    private Date inDate;

    /**
     * 入库人员ID
     */
    @ApiModelProperty(value = "入库人员ID",required = true)
    private Long inManId;

    /**
     * 入库人员名称
     */
    @ApiModelProperty(value = "入库人员名称")
    private String inManName;

    /**
     * 入库类型（0-获赠；1-报溢；2-领用退回；3-其它）
     */
    @ApiModelProperty(value = "入库类型（0-获赠；1-报溢；2-领用退回；3-其它）",required = true)
    private Integer inType;

    /**
     * 来源单位类型（0-部门；1-总部；2-门店；3-供货单位）
     */
    @ApiModelProperty(value = "来源单位类型（0-部门；1-总部；2-门店；3-供货单位）",required = true)
    private Integer srcUnitType;

    /**
     * 来源单位ID
     */
    @ApiModelProperty(value = "来源单位ID",required = true)
    private Long srcUnitId;

    /**
     * 来源单位名称
     */
    @ApiModelProperty(value = "来源单位名称")
    private String srcUnitName;

    /**
     * 流通监管码
     */
    @ApiModelProperty(value = "流通监管码")
    private String flowCode;

    /**
     * 明细
     */
    @ApiModelProperty(value = "其他入库明细",required = true)
    private List<OtherInDetailVO> otherInDetailVOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Long getInManId() {
        return inManId;
    }

    public void setInManId(Long inManId) {
        this.inManId = inManId;
    }

    public String getInManName() {
        return inManName;
    }

    public void setInManName(String inManName) {
        this.inManName = inManName;
    }

    public Integer getInType() {
        return inType;
    }

    public void setInType(Integer inType) {
        this.inType = inType;
    }

    public Integer getSrcUnitType() {
        return srcUnitType;
    }

    public void setSrcUnitType(Integer srcUnitType) {
        this.srcUnitType = srcUnitType;
    }

    public Long getSrcUnitId() {
        return srcUnitId;
    }

    public void setSrcUnitId(Long srcUnitId) {
        this.srcUnitId = srcUnitId;
    }

    public String getSrcUnitName() {
        return srcUnitName;
    }

    public void setSrcUnitName(String srcUnitName) {
        this.srcUnitName = srcUnitName;
    }

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public List<OtherInDetailVO> getOtherInDetailVOList() {
        return otherInDetailVOList;
    }

    public void setOtherInDetailVOList(List<OtherInDetailVO> otherInDetailVOList) {
        this.otherInDetailVOList = otherInDetailVOList;
    }

    @Override
    public String toString() {
        return "OtherInFormVO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", inDate=" + inDate +
                ", inManId=" + inManId +
                ", inManName='" + inManName + '\'' +
                ", inType=" + inType +
                ", srcUnitType=" + srcUnitType +
                ", srcUnitId=" + srcUnitId +
                ", srcUnitName='" + srcUnitName + '\'' +
                ", flowCode='" + flowCode + '\'' +
                ", otherInDetailVOList=" + otherInDetailVOList +
                '}';
    }

    public static ExcessSaleOtherInFormVO convertToVO(OtherIn otherIn) {
        ExcessSaleOtherInFormVO vo = new ExcessSaleOtherInFormVO();
        vo.setId(otherIn.getId());
        vo.setCode(otherIn.getCode());
        vo.setInDate(otherIn.getInDate());
        vo.setInManId(otherIn.getInManId());
        vo.setInManName(otherIn.getInManName());
        vo.setInType(otherIn.getInType());
        vo.setSrcUnitType(otherIn.getSrcUnitType());
        vo.setSrcUnitId(otherIn.getSrcUnitId());
        vo.setSrcUnitName(otherIn.getSrcUnitName());
        vo.setFlowCode(otherIn.getFlowCode());
        return vo;
    }
}
