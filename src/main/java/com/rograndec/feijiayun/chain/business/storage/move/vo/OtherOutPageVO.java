package com.rograndec.feijiayun.chain.business.storage.move.vo;

import com.rograndec.feijiayun.chain.business.storage.move.constant.FlowUnitType;
import com.rograndec.feijiayun.chain.business.storage.move.constant.OtherOutType;
import com.rograndec.feijiayun.chain.business.storage.move.entity.OtherOut;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 其他出库vo
 */
public class OtherOutPageVO implements Serializable{

    /**
     * 其他出库ID
     */
    @ApiModelProperty(value = "其他出库ID")
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
     * 单据类型（5304）
     */
    @ApiModelProperty(value = "单据类型（5304）")
    private Integer orderType;

    /**
     * 出库日期
     */
    @ApiModelProperty(value = "出库日期")
    private Date outDate;

    /**
     * 出库单号
     */
    @ApiModelProperty(value = "出库单号")
    private String code;

    /**
     * 出库人员ID
     */
    @ApiModelProperty(value = "出库人员ID")
    private Long outManId;

    /**
     * 出库人员编码
     */
    @ApiModelProperty(value = "出库人员编码")
    private String outManCode;

    /**
     * 出库人员名称
     */
    @ApiModelProperty(value = "出库人员名称")
    private String outManName;

    /**
     * 复核人员ID
     */
    @ApiModelProperty(value = "复核人员ID")
    private Long auditManId;

    /**
     * 复核人员编码
     */
    @ApiModelProperty(value = "复核人员编码")
    private String auditManCode;

    /**
     * 复核人员名称
     */
    @ApiModelProperty(value = "复核人员名称")
    private String auditManName;

    /**
     * 出库类型（0-赠送；1-报损；2-内部领用；3-抽检出库；4-其它）
     */
    @ApiModelProperty(value = "出库类型（0-赠送；1-报损；2-内部领用；3-抽检出库；4-其它）")
    private Integer outType;

    @ApiModelProperty(value = "出库类型描述（0-赠送；1-报损；2-内部领用；3-抽检出库；4-其它）")
    private String outTypeDesc;

    /**
     * 流向单位类型（0-部门；1-总部；2-）
     */
    @ApiModelProperty(value = "流向单位类型（0-部门；1-总部；2-）")
    private Integer flowUnitType;

    @ApiModelProperty(value = "流向单位类型描述（0-部门；1-总部；2-）")
    private String flowUnitTypeDesc;
    /**
     * 流向单位ID
     */
    @ApiModelProperty(value = "流向单位ID")
    private Long flowUnitId;

    /**
     * 流向单位编码
     */
    @ApiModelProperty(value = "流向单位编码")
    private String flowUnitCode;

    /**
     * 流向单位名称
     */
    @ApiModelProperty(value = "流向单位名称")
    private String flowUnitName;

    /**
     * 流向监管码
     */
    @ApiModelProperty(value = "流向监管码")
    private String flowCode;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;

    /**
     * 金额合计
     */
    @ApiModelProperty(value = "金额合计")
    private BigDecimal amountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxAmountTotal;

    /**
     * 税额合计
     */
    @ApiModelProperty(value = "税额合计")
    private BigDecimal taxAmountTotal;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "其他出库明细")
    private List<OtherOutDetailPageVO> otherOutDetailFormVOS;

    public static List<OtherOutPageVO> getOtherOutPageVOs(List<OtherOut> otherOuts){
        List<OtherOutPageVO> list = new ArrayList<>();

        otherOuts.forEach(ot -> {
            OtherOutPageVO otherOutPageVO = getOtherOutPageVO(ot);
            list.add(otherOutPageVO);
        });

        return list;
    }

    public static OtherOutPageVO getOtherOutPageVO(OtherOut ohterOut){
        OtherOutPageVO otherOutPageVO = new OtherOutPageVO();
        /**
         * 其他出库ID
         */
        otherOutPageVO.setId(ohterOut.getId());

        /**
         * 企业ID
         */
        otherOutPageVO.setEnterpriseId(ohterOut.getEnterpriseId());

        /**
         * 上级企业ID
         */
        otherOutPageVO.setParentId(ohterOut.getParentId());

        /**
         * 单据类型（5304）
         */
        otherOutPageVO.setOrderType(ohterOut.getOrderType());
        /**
         * 出库日期
         */
        otherOutPageVO.setOutDate(ohterOut.getOutDate());

        /**
         * 出库单号
         */
        otherOutPageVO.setCode(ohterOut.getCode());

        /**
         * 出库人员ID
         */
        otherOutPageVO.setOutManId(ohterOut.getOutManId());

        /**
         * 出库人员编码
         */
        otherOutPageVO.setOutManCode(ohterOut.getOutManCode());

        /**
         * 出库人员名称
         */
        otherOutPageVO.setOutManName(ohterOut.getOutManName());

        /**
         * 复核人员ID
         */
        otherOutPageVO.setAuditManId(ohterOut.getAuditManId());

        /**
         * 复核人员编码
         */
        otherOutPageVO.setAuditManCode(ohterOut.getAuditManCode());

        /**
         * 复核人员名称
         */
        otherOutPageVO.setAuditManName(ohterOut.getAuditManName());

        /**
         * 出库类型（0-赠送；1-报损；2-内部领用；3-抽检出库；4-其它）
         */
        otherOutPageVO.setOutType(ohterOut.getOutType());

        otherOutPageVO.setOutTypeDesc(OtherOutType.getValue(ohterOut.getOutType()));
        /**
         * 流向单位类型（0-部门；1-总部；2-）
         */
        if(null != ohterOut.getFlowUnitId()){
            otherOutPageVO.setFlowUnitType(ohterOut.getFlowUnitType());
            otherOutPageVO.setFlowUnitTypeDesc(FlowUnitType.getValue(ohterOut.getFlowUnitType()));
            /**
             * 流向单位ID
             */
            otherOutPageVO.setFlowUnitId(ohterOut.getFlowUnitId());

            /**
             * 流向单位编码
             */
            otherOutPageVO.setFlowUnitCode(ohterOut.getFlowUnitCode());

            /**
             * 流向单位名称
             */
            otherOutPageVO.setFlowUnitName(ohterOut.getFlowUnitName());

        }


        /**
         * 流向监管码
         */
        otherOutPageVO.setFlowCode(ohterOut.getFlowCode());

        /**
         * 数量合计
         */
        otherOutPageVO.setQuantityTotal(ohterOut.getQuantityTotal());

        /**
         * 品种数量
         */
        otherOutPageVO.setVarietiesQuantity(ohterOut.getVarietiesQuantity());

        /**
         * 金额合计
         */
        otherOutPageVO.setAmountTotal(ohterOut.getAmountTotal());

        /**
         * 不含税金额合计
         */
        otherOutPageVO.setNotaxAmountTotal(ohterOut.getNotaxAmountTotal());

        /**
         * 税额合计
         */
        otherOutPageVO.setTaxAmountTotal(ohterOut.getTaxAmountTotal());

        /**
         * 状态
         */
        otherOutPageVO.setStatus(ohterOut.getStatus());

        otherOutPageVO.setRemark(ohterOut.getRemark());

        return otherOutPageVO;
    }

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

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getOutManId() {
        return outManId;
    }

    public void setOutManId(Long outManId) {
        this.outManId = outManId;
    }

    public String getOutManCode() {
        return outManCode;
    }

    public void setOutManCode(String outManCode) {
        this.outManCode = outManCode;
    }

    public String getOutManName() {
        return outManName;
    }

    public void setOutManName(String outManName) {
        this.outManName = outManName;
    }

    public Long getAuditManId() {
        return auditManId;
    }

    public void setAuditManId(Long auditManId) {
        this.auditManId = auditManId;
    }

    public String getAuditManCode() {
        return auditManCode;
    }

    public void setAuditManCode(String auditManCode) {
        this.auditManCode = auditManCode;
    }

    public String getAuditManName() {
        return auditManName;
    }

    public void setAuditManName(String auditManName) {
        this.auditManName = auditManName;
    }

    public Integer getOutType() {
        return outType;
    }

    public void setOutType(Integer outType) {
        this.outType = outType;
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

    public String getFlowCode() {
        return flowCode;
    }

    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFlowUnitTypeDesc() {
        return flowUnitTypeDesc;
    }

    public void setFlowUnitTypeDesc(String flowUnitTypeDesc) {
        this.flowUnitTypeDesc = flowUnitTypeDesc;
    }

    public String getOutTypeDesc() {
        return outTypeDesc;
    }

    public void setOutTypeDesc(String outTypeDesc) {
        this.outTypeDesc = outTypeDesc;
    }

    public List<OtherOutDetailPageVO> getOtherOutDetailFormVOS() {
        return otherOutDetailFormVOS;
    }

    public void setOtherOutDetailFormVOS(List<OtherOutDetailPageVO> otherOutDetailFormVOS) {
        this.otherOutDetailFormVOS = otherOutDetailFormVOS;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
