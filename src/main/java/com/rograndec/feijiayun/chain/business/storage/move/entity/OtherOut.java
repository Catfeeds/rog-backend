package com.rograndec.feijiayun.chain.business.storage.move.entity;

import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherOutFormVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.transfer.FlowUnit;
import com.rograndec.feijiayun.chain.business.storage.move.vo.transfer.OtherOutHeaderParam;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_other_out
 * 
 * 
 * @author dudy
 * 
 * 2017-09-27
 */
public class OtherOut implements Serializable {
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

    /**
     * 流向单位类型（0-部门；1-总部；2-）
     */
    @ApiModelProperty(value = "流向单位类型（0-部门；1-总部；2-）")
    private Integer flowUnitType;

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

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 组装其他出库实体
     * @param otherOutHeaderParam
     * @return
     * @throws Exception
     */
    public static OtherOut getOtherOut4VO(OtherOutHeaderParam otherOutHeaderParam) throws Exception {
        UserVO userVO = otherOutHeaderParam.getUserVO();
        OtherOutFormVO otherOutFormVO = otherOutHeaderParam.getOtherOutFormVO();

        OtherOut otherOut = new OtherOut();

        /**
         * 企业ID
         */
        otherOut.setEnterpriseId(userVO.getEnterpriseId());
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){
            otherOut.setParentId(0L);
        }else {
            otherOut.setParentId(userVO.getParentId());
        }

        /**
         * 单据类型（5304）
         */
        otherOut.setOrderType(OrderRule.SEND.getType());

        /**
         * 出库日期
         */
        otherOut.setOutDate(otherOutFormVO.getOutDate());

        /**
         * 出库单号
         */
        otherOut.setCode(otherOutHeaderParam.getCode());

        /**
         * 出库人员ID
         */
        otherOut.setOutManId(otherOutHeaderParam.getOutMan().getId());

        /**
         * 出库人员编码
         */
        otherOut.setOutManCode(otherOutHeaderParam.getOutMan().getCode());

        /**
         * 出库人员名称
         */
        otherOut.setOutManName(otherOutHeaderParam.getOutMan().getName());

        /**
         * 复核人员ID
         */
        otherOut.setAuditManId(otherOutHeaderParam.getAuditMan().getId());

        /**
         * 复核人员编码
         */
        otherOut.setAuditManCode(otherOutHeaderParam.getAuditMan().getCode());

        /**
         * 复核人员名称
         */
        otherOut.setAuditManName(otherOutHeaderParam.getAuditMan().getName());

        /**
         * 出库类型（0-赠送；1-报损；2-内部领用；3-抽检出库；4-其它）
         */
        otherOut.setOutType(otherOutFormVO.getOutType());

        FlowUnit flowUnit = otherOutHeaderParam.getFlowUnit();

        if(null != flowUnit){

            /**
             * 流向单位类型（0-部门；1-总部；2-）
             */
            otherOut.setFlowUnitType(flowUnit.getFlowUnitType());
            /**
             * 流向单位ID
             */
            otherOut.setFlowUnitId(flowUnit.getFlowUnitId());

            /**
             * 流向单位编码
             */
            otherOut.setFlowUnitCode(flowUnit.getFlowUnitCode());

            /**
             * 流向单位名称
             */
            otherOut.setFlowUnitName(flowUnit.getFlowUnitName());
        }



        /**
         * 流向监管码
         */
        otherOut.setFlowCode(otherOutFormVO.getFlowCode());

        List<OtherOutDetail> otherOutDetails = otherOutHeaderParam.getOtherOutDetails();
        /**
         * 数量合计
         */
        otherOut.setQuantityTotal(OtherOutDetail.getQuantityTotal(otherOutDetails));

        /**
         * 品种数量
         */
        otherOut.setVarietiesQuantity(OtherOutDetail.getVarietiesQuantityTotal(otherOutDetails));

        /**
         * 金额合计
         */
        otherOut.setAmountTotal(OtherOutDetail.getAmountTotal(otherOutDetails));

        /**
         * 不含税金额合计
         */
        otherOut.setNotaxAmountTotal(OtherOutDetail.getNotaxAmountTotal(otherOutDetails));

        /**
         * 税额合计
         */
        otherOut.setTaxAmountTotal(OtherOutDetail.getTaxAmountTotal(otherOutDetails));

        otherOut.setRemark(otherOutFormVO.getRemark());
        otherOut.setStatus(EnableStatus.ENABLE.getStatus());

        UserEnterpriseUtils.setUserCreateOrModify(otherOut,userVO,true);

        return otherOut;
    }

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 单据类型（5304）
     * @return order_type 单据类型（5304）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5304）
     * @param orderType 单据类型（5304）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 出库日期
     * @return out_date 出库日期
     */
    public Date getOutDate() {
        return outDate;
    }

    /**
     * 出库日期
     * @param outDate 出库日期
     */
    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    /**
     * 出库单号
     * @return code 出库单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 出库单号
     * @param code 出库单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 出库人员ID
     * @return out_man_id 出库人员ID
     */
    public Long getOutManId() {
        return outManId;
    }

    /**
     * 出库人员ID
     * @param outManId 出库人员ID
     */
    public void setOutManId(Long outManId) {
        this.outManId = outManId;
    }

    /**
     * 出库人员编码
     * @return out_man_code 出库人员编码
     */
    public String getOutManCode() {
        return outManCode;
    }

    /**
     * 出库人员编码
     * @param outManCode 出库人员编码
     */
    public void setOutManCode(String outManCode) {
        this.outManCode = outManCode == null ? null : outManCode.trim();
    }

    /**
     * 出库人员名称
     * @return out_man_name 出库人员名称
     */
    public String getOutManName() {
        return outManName;
    }

    /**
     * 出库人员名称
     * @param outManName 出库人员名称
     */
    public void setOutManName(String outManName) {
        this.outManName = outManName == null ? null : outManName.trim();
    }

    /**
     * 复核人员ID
     * @return audit_man_id 复核人员ID
     */
    public Long getAuditManId() {
        return auditManId;
    }

    /**
     * 复核人员ID
     * @param auditManId 复核人员ID
     */
    public void setAuditManId(Long auditManId) {
        this.auditManId = auditManId;
    }

    /**
     * 复核人员编码
     * @return audit_man_code 复核人员编码
     */
    public String getAuditManCode() {
        return auditManCode;
    }

    /**
     * 复核人员编码
     * @param auditManCode 复核人员编码
     */
    public void setAuditManCode(String auditManCode) {
        this.auditManCode = auditManCode == null ? null : auditManCode.trim();
    }

    /**
     * 复核人员名称
     * @return audit_man_name 复核人员名称
     */
    public String getAuditManName() {
        return auditManName;
    }

    /**
     * 复核人员名称
     * @param auditManName 复核人员名称
     */
    public void setAuditManName(String auditManName) {
        this.auditManName = auditManName == null ? null : auditManName.trim();
    }

    /**
     * 出库类型（0-赠送；1-报损；2-内部领用；3-抽检出库；4-其它）
     * @return out_type 出库类型（0-赠送；1-报损；2-内部领用；3-抽检出库；4-其它）
     */
    public Integer getOutType() {
        return outType;
    }

    /**
     * 出库类型（0-赠送；1-报损；2-内部领用；3-抽检出库；4-其它）
     * @param outType 出库类型（0-赠送；1-报损；2-内部领用；3-抽检出库；4-其它）
     */
    public void setOutType(Integer outType) {
        this.outType = outType;
    }

    /**
     * 流向单位类型（0-部门；1-总部；2-）
     * @return flow_unit_type 流向单位类型（0-部门；1-总部；2-）
     */
    public Integer getFlowUnitType() {
        return flowUnitType;
    }

    /**
     * 流向单位类型（0-部门；1-总部；2-）
     * @param flowUnitType 流向单位类型（0-部门；1-总部；2-）
     */
    public void setFlowUnitType(Integer flowUnitType) {
        this.flowUnitType = flowUnitType;
    }

    /**
     * 流向单位ID
     * @return flow_unit_id 流向单位ID
     */
    public Long getFlowUnitId() {
        return flowUnitId;
    }

    /**
     * 流向单位ID
     * @param flowUnitId 流向单位ID
     */
    public void setFlowUnitId(Long flowUnitId) {
        this.flowUnitId = flowUnitId;
    }

    /**
     * 流向单位编码
     * @return flow_unit_code 流向单位编码
     */
    public String getFlowUnitCode() {
        return flowUnitCode;
    }

    /**
     * 流向单位编码
     * @param flowUnitCode 流向单位编码
     */
    public void setFlowUnitCode(String flowUnitCode) {
        this.flowUnitCode = flowUnitCode == null ? null : flowUnitCode.trim();
    }

    /**
     * 流向单位名称
     * @return flow_unit_name 流向单位名称
     */
    public String getFlowUnitName() {
        return flowUnitName;
    }

    /**
     * 流向单位名称
     * @param flowUnitName 流向单位名称
     */
    public void setFlowUnitName(String flowUnitName) {
        this.flowUnitName = flowUnitName == null ? null : flowUnitName.trim();
    }

    /**
     * 流向监管码
     * @return flow_code 流向监管码
     */
    public String getFlowCode() {
        return flowCode;
    }

    /**
     * 流向监管码
     * @param flowCode 流向监管码
     */
    public void setFlowCode(String flowCode) {
        this.flowCode = flowCode == null ? null : flowCode.trim();
    }

    /**
     * 数量合计
     * @return quantity_total 数量合计
     */
    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    /**
     * 数量合计
     * @param quantityTotal 数量合计
     */
    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    /**
     * 品种数量
     * @return varieties_quantity 品种数量
     */
    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    /**
     * 品种数量
     * @param varietiesQuantity 品种数量
     */
    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    /**
     * 金额合计
     * @return amount_total 金额合计
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 金额合计
     * @param amountTotal 金额合计
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 不含税金额合计
     * @return notax_amount_total 不含税金额合计
     */
    public BigDecimal getNotaxAmountTotal() {
        return notaxAmountTotal;
    }

    /**
     * 不含税金额合计
     * @param notaxAmountTotal 不含税金额合计
     */
    public void setNotaxAmountTotal(BigDecimal notaxAmountTotal) {
        this.notaxAmountTotal = notaxAmountTotal;
    }

    /**
     * 税额合计
     * @return tax_amount_total 税额合计
     */
    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    /**
     * 税额合计
     * @param taxAmountTotal 税额合计
     */
    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    /**
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", orderType=").append(orderType);
        sb.append(", outDate=").append(outDate);
        sb.append(", code=").append(code);
        sb.append(", outManId=").append(outManId);
        sb.append(", outManCode=").append(outManCode);
        sb.append(", outManName=").append(outManName);
        sb.append(", auditManId=").append(auditManId);
        sb.append(", auditManCode=").append(auditManCode);
        sb.append(", auditManName=").append(auditManName);
        sb.append(", outType=").append(outType);
        sb.append(", flowUnitType=").append(flowUnitType);
        sb.append(", flowUnitId=").append(flowUnitId);
        sb.append(", flowUnitCode=").append(flowUnitCode);
        sb.append(", flowUnitName=").append(flowUnitName);
        sb.append(", flowCode=").append(flowCode);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", notaxAmountTotal=").append(notaxAmountTotal);
        sb.append(", taxAmountTotal=").append(taxAmountTotal);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}