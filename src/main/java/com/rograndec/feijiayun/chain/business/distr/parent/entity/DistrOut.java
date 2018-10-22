package com.rograndec.feijiayun.chain.business.distr.parent.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_distr_out
 * 
 * 
 * @author Administrator
 * 
 * 2017-10-07
 */
public class DistrOut implements Serializable {
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
     * 单据类型（5412）
     */
    @ApiModelProperty(value = "单据类型（5412）")
    private Integer orderType;

    /**
     * 配货出库单号
     */
    @ApiModelProperty(value = "配货出库单号")
    private String code;

    /**
     * 配货出库日期
     */
    @ApiModelProperty(value = "配货出库日期")
    private Date outDate;

//    @NotNull(message = "出库日期不能为空")
    @ApiModelProperty(value = "配货出库日期")
    private String outDateStr;

    /**
     * 基础单据ID
     */
//    @NotNull(message = "基础单据id不能为空")
    @ApiModelProperty(value = "基础单据ID")
    private Long baseOrderId;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型")
    private Integer baseOrderType;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "基础单据编码")
    private String baseOrderCode;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期")
    private Date baseOrderDate;

    /**
     * 要货单位ID
     */
    @ApiModelProperty(value = "要货单位ID")
    private Long requestUnitId;

    /**
     * 要货单位编码
     */
    @ApiModelProperty(value = "要货单位编码")
    private String requestUnitCode;

    /**
     * 要货单位名称
     */
    @ApiModelProperty(value = "要货单位名称")
    private String requestUnitName;

    /**
     * 配货人员ID
     */
    @ApiModelProperty(value = "配货人员ID")
    private Long sendManId;

    /**
     * 配货人员编码
     */
    @ApiModelProperty(value = "配货人员编码")
    private String sendManCode;

    /**
     * 配货人员名称
     */
    @ApiModelProperty(value = "配货人员名称")
    private String sendManName;

    /**
     * 配送类型（0-总部配送；1-分店间调剂；2-直调配送）
     */
    @ApiModelProperty(value = "配送类型（0-总部配送；1-分店间调剂；2-直调配送）")
    private Integer distrType;
    @ApiModelProperty(value = "配送类型（0-总部配送；1-分店间调剂；2-直调配送）")
    private String distrTypeName;

    /**
     * 出库人员ID
     */
    @NotNull(message = "出库人员id不能为空")
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
    private Long checkerId;

    /**
     * 复核人员编码
     */
    @ApiModelProperty(value = "复核人员编码")
    private String checkerCode;

    /**
     * 复核人员名称
     */
    @ApiModelProperty(value = "复核人员名称")
    private String checkerName;

    /**
     * 流通监管码
     */
    @ApiModelProperty(value = "流通监管码")
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
     * 金额合计（整单优惠前的金额合计）
     */
    @ApiModelProperty(value = "金额合计（整单优惠前的金额合计）")
    private BigDecimal amountTotal;

    /**
     * 整单折扣（%）
     */
    @ApiModelProperty(value = "整单折扣（%）")
    private BigDecimal wholeDiscount;

    /**
     * 整单优惠金额
     */
    @ApiModelProperty(value = "整单优惠金额")
    private BigDecimal wholeDiscountAmount;

    /**
     * 实际金额合计
     */
    @ApiModelProperty(value = "实际金额合计")
    private BigDecimal realAmountTotal;

    /**
     * 不含税金额合计
     */
    @ApiModelProperty(value = "不含税金额合计")
    private BigDecimal notaxRealAmountTotal;

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
     * 打印需要新增的字段
     */
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;

    @ApiModelProperty(value = "整单折扣金额：整单金额*整单折扣")
    private BigDecimal wholeDiscountValue;


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

    @ApiModelProperty(value = "出库单明细")
    List<DistrOutDetail> distrOutDetailList = new ArrayList<>();

    public static DistrOut getDistrOut(PickOrder pickOrder, DistrSend distrSend){


        DistrOut distrOut = new DistrOut();

        /**
         * 企业ID
         */
        distrOut.setEnterpriseId(pickOrder.getEnterpriseId());

        /**
         * 上级企业ID
         */
        distrOut.setParentId(pickOrder.getParentId());

        /**
         * 单据类型（5412）
         */
        distrOut.setOrderType(pickOrder.getOrderType());

        /**
         * 配货出库日期
         */
        distrOut.setOutDate(new Date());

        /**
         * 基础单据ID
         */
        distrOut.setBaseOrderId(distrSend.getId());

        /**
         * 基础单据类型
         */
        distrOut.setBaseOrderType(distrSend.getOrderType());

        /**
         * 基础单据编码
         */
        distrOut.setBaseOrderCode(distrSend.getCode());

        /**
         * 基础单据日期
         */
        distrOut.setBaseOrderDate(distrSend.getDistrDate());

        /**
         * 要货单位ID
         */
        distrOut.setRequestUnitId(pickOrder.getRequestUnitId());

        /**
         * 要货单位编码
         */
        distrOut.setRequestUnitCode(pickOrder.getRequestUnitCode());

        /**
         * 要货单位名称
         */
        distrOut.setRequestUnitName(pickOrder.getRequestUnitName());

        /**
         * 配货人员ID
         */
        distrOut.setSendManId(distrSend.getDistrManId());

        /**
         * 配货人员编码
         */
        distrOut.setSendManCode(distrSend.getDistrManCode());

        /**
         * 配货人员名称
         */
        distrOut.setSendManName(distrSend.getDistrManName());

        /**
         * 配送类型（0-总部配送；1-分店间调剂；2-直调配送）
         */
        distrOut.setDistrType(1);

        /**
         * 出库人员ID
         */
        distrOut.setOutManId(distrSend.getDistrManId());

        /**
         * 出库人员编码
         */
        distrOut.setOutManCode(distrSend.getDistrManCode());

        /**
         * 出库人员名称
         */
        distrOut.setOutManName(distrSend.getDistrManName());

        /**
         * 复核人员ID
         */
        distrOut.setCheckerId(distrSend.getDistrManId());

        /**
         * 复核人员编码
         */
        distrOut.setCheckerCode(distrSend.getDistrManCode());

        /**
         * 复核人员名称
         */
        distrOut.setCheckerName(distrSend.getDistrManName());

        /**
         * 数量合计
         */
        distrOut.setQuantityTotal(distrSend.getQuantityTotal());

        /**
         * 品种数量
         */
        distrOut.setVarietiesQuantity(distrSend.getVarietiesQuantity());

        /**
         * 金额合计（整单优惠前的金额合计）
         */
        distrOut.setAmountTotal(distrSend.getAmountTotal());

        /**
         * 整单折扣（%）
         */
        distrOut.setWholeDiscount(distrSend.getWholeDiscount());

        /**
         * 整单优惠金额
         */
        distrOut.setWholeDiscountAmount(distrSend.getWholeDiscountAmount());

        /**
         * 实际金额合计
         */
        distrOut.setRealAmountTotal(distrSend.getRealAmountTotal());

        /**
         * 不含税金额合计
         */
        distrOut.setNotaxRealAmountTotal(distrSend.getNotaxRealAmountTotal());

        /**
         * 税额合计
         */
        distrOut.setTaxAmountTotal(distrSend.getTaxAmountTotal());

        List<DistrOutDetail> distrOutDetails = DistrOutDetail.getDistrOutDetails(pickOrder, distrSend);
        distrOut.setDistrOutDetailList(distrOutDetails);

        return distrOut;
    }

    public String getOutDateStr() {
        return outDateStr;
    }

    public void setOutDateStr(String outDateStr) {
        this.outDateStr = outDateStr;
    }

    public List<DistrOutDetail> getDistrOutDetailList() {
        return distrOutDetailList;
    }

    public void setDistrOutDetailList(List<DistrOutDetail> distrOutDetailList) {
        this.distrOutDetailList = distrOutDetailList;
    }

    /**
     * saas_distr_out
     */
    private static final long serialVersionUID = 1L;

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
     * 单据类型（5412）
     * @return order_type 单据类型（5412）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5412）
     * @param orderType 单据类型（5412）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 配货出库单号
     * @return code 配货出库单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 配货出库单号
     * @param code 配货出库单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 配货出库日期
     * @return out_date 配货出库日期
     */
    public Date getOutDate() {
        return outDate;
    }

    /**
     * 配货出库日期
     * @param outDate 配货出库日期
     */
    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    /**
     * 基础单据ID
     * @return base_order_id 基础单据ID
     */
    public Long getBaseOrderId() {
        return baseOrderId;
    }

    /**
     * 基础单据ID
     * @param baseOrderId 基础单据ID
     */
    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    /**
     * 基础单据类型
     * @return base_order_type 基础单据类型
     */
    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    /**
     * 基础单据类型
     * @param baseOrderType 基础单据类型
     */
    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    /**
     * 基础单据编码
     * @return base_order_code 基础单据编码
     */
    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    /**
     * 基础单据编码
     * @param baseOrderCode 基础单据编码
     */
    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode == null ? null : baseOrderCode.trim();
    }

    /**
     * 基础单据日期
     * @return base_order_date 基础单据日期
     */
    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    /**
     * 基础单据日期
     * @param baseOrderDate 基础单据日期
     */
    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    /**
     * 要货单位ID
     * @return request_unit_id 要货单位ID
     */
    public Long getRequestUnitId() {
        return requestUnitId;
    }

    /**
     * 要货单位ID
     * @param requestUnitId 要货单位ID
     */
    public void setRequestUnitId(Long requestUnitId) {
        this.requestUnitId = requestUnitId;
    }

    /**
     * 要货单位编码
     * @return request_unit_code 要货单位编码
     */
    public String getRequestUnitCode() {
        return requestUnitCode;
    }

    /**
     * 要货单位编码
     * @param requestUnitCode 要货单位编码
     */
    public void setRequestUnitCode(String requestUnitCode) {
        this.requestUnitCode = requestUnitCode == null ? null : requestUnitCode.trim();
    }

    /**
     * 要货单位名称
     * @return request_unit_name 要货单位名称
     */
    public String getRequestUnitName() {
        return requestUnitName;
    }

    /**
     * 要货单位名称
     * @param requestUnitName 要货单位名称
     */
    public void setRequestUnitName(String requestUnitName) {
        this.requestUnitName = requestUnitName == null ? null : requestUnitName.trim();
    }

    /**
     * 配货人员ID
     * @return send_man_id 配货人员ID
     */
    public Long getSendManId() {
        return sendManId;
    }

    /**
     * 配货人员ID
     * @param sendManId 配货人员ID
     */
    public void setSendManId(Long sendManId) {
        this.sendManId = sendManId;
    }

    /**
     * 配货人员编码
     * @return send_man_code 配货人员编码
     */
    public String getSendManCode() {
        return sendManCode;
    }

    /**
     * 配货人员编码
     * @param sendManCode 配货人员编码
     */
    public void setSendManCode(String sendManCode) {
        this.sendManCode = sendManCode == null ? null : sendManCode.trim();
    }

    /**
     * 配货人员名称
     * @return send_man_name 配货人员名称
     */
    public String getSendManName() {
        return sendManName;
    }

    /**
     * 配货人员名称
     * @param sendManName 配货人员名称
     */
    public void setSendManName(String sendManName) {
        this.sendManName = sendManName == null ? null : sendManName.trim();
    }

    /**
     * 配送类型（0-总部配送；1-分店间调剂；2-直调配送）
     * @return distr_type 配送类型（0-总部配送；1-分店间调剂；2-直调配送）
     */
    public Integer getDistrType() {
        return distrType;
    }

    /**
     * 配送类型（0-总部配送；1-分店间调剂；2-直调配送）
     * @param distrType 配送类型（0-总部配送；1-分店间调剂；2-直调配送）
     */
    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
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
     * @return checker_id 复核人员ID
     */
    public Long getCheckerId() {
        return checkerId;
    }

    /**
     * 复核人员ID
     * @param checkerId 复核人员ID
     */
    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    /**
     * 复核人员编码
     * @return checker_code 复核人员编码
     */
    public String getCheckerCode() {
        return checkerCode;
    }

    /**
     * 复核人员编码
     * @param checkerCode 复核人员编码
     */
    public void setCheckerCode(String checkerCode) {
        this.checkerCode = checkerCode == null ? null : checkerCode.trim();
    }

    /**
     * 复核人员名称
     * @return checker_name 复核人员名称
     */
    public String getCheckerName() {
        return checkerName;
    }

    /**
     * 复核人员名称
     * @param checkerName 复核人员名称
     */
    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName == null ? null : checkerName.trim();
    }

    /**
     * 流通监管码
     * @return flow_code 流通监管码
     */
    public String getFlowCode() {
        return flowCode;
    }

    /**
     * 流通监管码
     * @param flowCode 流通监管码
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
     * 金额合计（整单优惠前的金额合计）
     * @return amount_total 金额合计（整单优惠前的金额合计）
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @param amountTotal 金额合计（整单优惠前的金额合计）
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 整单折扣（%）
     * @return whole_discount 整单折扣（%）
     */
    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    /**
     * 整单折扣（%）
     * @param wholeDiscount 整单折扣（%）
     */
    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    /**
     * 整单优惠金额
     * @return whole_discount_amount 整单优惠金额
     */
    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    /**
     * 整单优惠金额
     * @param wholeDiscountAmount 整单优惠金额
     */
    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    /**
     * 实际金额合计
     * @return real_amount_total 实际金额合计
     */
    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    /**
     * 实际金额合计
     * @param realAmountTotal 实际金额合计
     */
    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    /**
     * 不含税金额合计
     * @return notax_real_amount_total 不含税金额合计
     */
    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    /**
     * 不含税金额合计
     * @param notaxRealAmountTotal 不含税金额合计
     */
    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
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

    public String getDistrTypeName() {
        return distrTypeName;
    }

    public void setDistrTypeName(String distrTypeName) {
        this.distrTypeName = distrTypeName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public BigDecimal getWholeDiscountValue() {
        return wholeDiscountValue;
    }

    public void setWholeDiscountValue(BigDecimal wholeDiscountValue) {
        this.wholeDiscountValue = wholeDiscountValue;
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
        sb.append(", code=").append(code);
        sb.append(", outDate=").append(outDate);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", baseOrderType=").append(baseOrderType);
        sb.append(", baseOrderCode=").append(baseOrderCode);
        sb.append(", baseOrderDate=").append(baseOrderDate);
        sb.append(", requestUnitId=").append(requestUnitId);
        sb.append(", requestUnitCode=").append(requestUnitCode);
        sb.append(", requestUnitName=").append(requestUnitName);
        sb.append(", sendManId=").append(sendManId);
        sb.append(", sendManCode=").append(sendManCode);
        sb.append(", sendManName=").append(sendManName);
        sb.append(", distrType=").append(distrType);
        sb.append(", outManId=").append(outManId);
        sb.append(", outManCode=").append(outManCode);
        sb.append(", outManName=").append(outManName);
        sb.append(", checkerId=").append(checkerId);
        sb.append(", checkerCode=").append(checkerCode);
        sb.append(", checkerName=").append(checkerName);
        sb.append(", flowCode=").append(flowCode);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", wholeDiscount=").append(wholeDiscount);
        sb.append(", wholeDiscountAmount=").append(wholeDiscountAmount);
        sb.append(", realAmountTotal=").append(realAmountTotal);
        sb.append(", notaxRealAmountTotal=").append(notaxRealAmountTotal);
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}