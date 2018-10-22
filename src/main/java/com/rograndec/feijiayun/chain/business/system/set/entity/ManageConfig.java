 package com.rograndec.feijiayun.chain.business.system.set.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class ManageConfig implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value="主键ID",required=true)
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value="企业ID",required=true)
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value="上级企业ID",required=true)
    private Long parentId;

    /**
     * 企业类型（0-总部；1-自营店；2-加盟店）
     */
    @ApiModelProperty(value="企业类型（0-总部；1-自营店；2-加盟店）",required=true)
    private Integer chainType;

    /**
     * 供货单位编码规则（0-供货单位分组+4位流水码；1-4位流水码；2-自定义编码）
     */
    @ApiModelProperty(value="供货单位编码规则（0-供货单位分组+4位流水码；1-4位流水码；2-自定义编码）",required=true)
    private Integer supplierCodeRule;

    /**
     * 商品信息编码规则（0-商品分组+4位流水码；1-自定义编码）
     */
    @ApiModelProperty(value="商品信息编码规则（0-商品分组+4位流水码；1-自定义编码）",required=true)
    private Integer goodsCodeRule;

    /**
     * 员工信息编码规则（0-部门+4位流水码；1-4位流水码；2-自定义编码）
     */
    @ApiModelProperty(value="员工信息编码规则（0-部门+4位流水码；1-4位流水码；2-自定义编码）",required=true)
    private Integer userCodeRule;

    /**
     * 业务单据价格（0-含税单价；1-不含税单价）
     */
    @ApiModelProperty(value="业务单据价格（0-含税单价；1-不含税单价）",required=true)
    private Integer priceControl;

    /**
     * 业务单据输入顺序（0-顺序；1-倒叙，默认值1）
     */
    @ApiModelProperty(value="业务单据输入顺序（0-顺序；1-倒叙，默认值1）",required=true)
    private Integer enterOrder;

    /**
     * 零成本入库（0禁止；1-允许）
     */
    @ApiModelProperty(value="零成本入库（0禁止；1-允许）",required=true)
    private Integer zeroCostIn;

    /**
     * 库存占用量控制（0关闭；1-开启）
     */
    @ApiModelProperty(value="库存占用量控制（0关闭；1-开启）",required=true)
    private Integer inventoryOccupancy;

    /**
     * 基础数据质量控制（0关闭；1-开启）
     */
    @ApiModelProperty(value="基础数据质量控制（0关闭；1-开启）",required=true)
    private Integer qualityControl;

    /**
     * 业务流程质量控制（0关闭；1-开启）
     */
    @ApiModelProperty(value="业务流程质量控制（0关闭；1-开启）",required=true)
    private Integer businessControl;

    /**
     * Pos质量控制（0关闭；1-开启）
     */
    @ApiModelProperty(value="Pos质量控制（0关闭；1-开启）",required=true)
    private Integer posControl;

    /**
     * 审批流程控制（0关闭；1-开启）
     */
    @ApiModelProperty(value="审批流程控制（0关闭；1-开启）",required=true)
    private Integer approvalControl;

    /**
     * 总部：采购人员ID
     */
    @ApiModelProperty(value="总部：采购人员ID",required=true)
    private Long purchaserId;

    /**
     * 总部：采购人员编码
     */
    @ApiModelProperty(value="总部：采购人员编码",required=true)
    private String purchaserCode;

    /**
     * 总部：采购人员名称
     */
    @ApiModelProperty(value="总部：采购人员名称",required=true)
    private String purchaserName;

    /**
     * 总部：配货人员ID
     */
    @ApiModelProperty(value="总部：配货人员ID",required=true)
    private Long distrManId;

    /**
     * 总部：配货人员编码
     */
    @ApiModelProperty(value="总部：配货人员编码",required=true)
    private String distrManCode;

    /**
     * 总部：配货人员名称
     */
    @ApiModelProperty(value="总部：配货人员名称",required=true)
    private String distrManName;

    /**
     * 分店：要货人员ID
     */
    @ApiModelProperty(value="分店：要货人员ID",required=true)
    private Long requesterId;

    /**
     * 分店：要货人员编码
     */
    @ApiModelProperty(value="分店：要货人员编码",required=true)
    private String requesterCode;

    /**
     * 分店：要货人员名称
     */
    @ApiModelProperty(value="分店：要货人员名称",required=true)
    private String requesterName;

    /**
     * 收货人员ID
     */
    @ApiModelProperty(value="收货人员ID",required=true)
    private Long receiverId;

    /**
     * 收货人员编码
     */
    @ApiModelProperty(value="收货人员编码",required=true)
    private String receiverCode;

    /**
     * 收货人员名称
     */
    @ApiModelProperty(value="收货人员名称",required=true)
    private String receiverName;

    /**
     * 第二收货人员ID
     */
    @ApiModelProperty(value="第二收货人员ID",required=true)
    private Long secondReceiverId;

    /**
     * 收货人员编码
     */
    @ApiModelProperty(value="收货人员编码",required=true)
    private String secondReceiverCode;

    /**
     * 第二收货人员名称
     */
    @ApiModelProperty(value="第二收货人员名称",required=true)
    private String secondReceiverName;

    /**
     * 验收人员ID
     */
    @ApiModelProperty(value="验收人员ID",required=true)
    private Long checkerId;

    /**
     * 验收人员编码
     */
    @ApiModelProperty(value="验收人员编码",required=true)
    private String checkerCode;

    /**
     * 验收人员名称
     */
    @ApiModelProperty(value="验收人员名称",required=true)
    private String checkerName;

    /**
     * 第二验收人员ID
     */
    @ApiModelProperty(value="第二验收人员ID",required=true)
    private Long secondCheckerId;

    /**
     * 第二验收人员编码
     */
    @ApiModelProperty(value="第二验收人员编码",required=true)
    private String secondCheckerCode;

    /**
     * 第二验收人员名称
     */
    @ApiModelProperty(value="第二验收人员名称",required=true)
    private String secondCheckerName;

    /**
     * 入库/出库人员ID
     */
    @ApiModelProperty(value="入库/出库人员ID",required=true)
    private Long inOutManId;

    /**
     * 入库/出库人员编码
     */
    @ApiModelProperty(value="入库/出库人员编码",required=true)
    private String inOutManCode;

    /**
     * 入库/出库人员名称
     */
    @ApiModelProperty(value="入库/出库人员名称",required=true)
    private String inOutManName;

    /**
     * 出库复核人员ID
     */
    @ApiModelProperty(value="出库复核人员ID",required=true)
    private Long outCheckerId;

    /**
     * 出库复核人员编码
     */
    @ApiModelProperty(value="出库复核人员编码",required=true)
    private String outCheckerCode;

    /**
     * 出库复核人员名称
     */
    @ApiModelProperty(value="出库复核人员名称",required=true)
    private String outCheckerName;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注",required=true)
    private String remark;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value="创建人ID",required=true)
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value="创建人编码",required=true)
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value="创建人名称",required=true)
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间",required=true)
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value="最后修改人ID",required=true)
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value="最后修改人编码",required=true)
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value="最后修改人名称",required=true)
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间",required=true)
    private Date updateTime;

    /**
     * 如果是分店质量控制是否可编辑(0-不可编辑 1-可编辑)
     */
    @ApiModelProperty(value="如果是分店质量控制是否可编辑(0-不可编辑 1-可编辑)",required=true)
    private Integer qualityEdit;
    
    /**
     * 如果是分店审批控制是否可编辑(0-不可编辑 1-可编辑)
     */
    @ApiModelProperty(value="如果是分店审批控制是否可编辑(0-不可编辑 1-可编辑)",required=true)
    private Integer approvalEdit;
    
	/**
     * saas_manage_config
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
     * 企业类型（0-总部；1-自营店；2-加盟店）
     * @return chain_type 企业类型（0-总部；1-自营店；2-加盟店）
     */
    public Integer getChainType() {
        return chainType;
    }

    /**
     * 企业类型（0-总部；1-自营店；2-加盟店）
     * @param chainType 企业类型（0-总部；1-自营店；2-加盟店）
     */
    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    /**
     * 供货单位编码规则（0-供货单位分组+4位流水码；1-4位流水码；2-自定义编码）
     * @return supplier_code_rule 供货单位编码规则（0-供货单位分组+4位流水码；1-4位流水码；2-自定义编码）
     */
    public Integer getSupplierCodeRule() {
        return supplierCodeRule;
    }

    /**
     * 供货单位编码规则（0-供货单位分组+4位流水码；1-4位流水码；2-自定义编码）
     * @param supplierCodeRule 供货单位编码规则（0-供货单位分组+4位流水码；1-4位流水码；2-自定义编码）
     */
    public void setSupplierCodeRule(Integer supplierCodeRule) {
        this.supplierCodeRule = supplierCodeRule;
    }

    /**
     * 商品信息编码规则（0-商品分组+4位流水码；1-自定义编码）
     * @return goods_code_rule 商品信息编码规则（0-商品分组+4位流水码；1-自定义编码）
     */
    public Integer getGoodsCodeRule() {
        return goodsCodeRule;
    }

    /**
     * 商品信息编码规则（0-商品分组+4位流水码；1-自定义编码）
     * @param goodsCodeRule 商品信息编码规则（0-商品分组+4位流水码；1-自定义编码）
     */
    public void setGoodsCodeRule(Integer goodsCodeRule) {
        this.goodsCodeRule = goodsCodeRule;
    }

    /**
     * 员工信息编码规则（0-部门+4位流水码；1-4位流水码；2-自定义编码）
     * @return user_code_rule 员工信息编码规则（0-部门+4位流水码；1-4位流水码；2-自定义编码）
     */
    public Integer getUserCodeRule() {
        return userCodeRule;
    }

    /**
     * 员工信息编码规则（0-部门+4位流水码；1-4位流水码；2-自定义编码）
     * @param userCodeRule 员工信息编码规则（0-部门+4位流水码；1-4位流水码；2-自定义编码）
     */
    public void setUserCodeRule(Integer userCodeRule) {
        this.userCodeRule = userCodeRule;
    }

    /**
     * 业务单据价格（0-含税单价；1-不含税单价）
     * @return price_control 业务单据价格（0-含税单价；1-不含税单价）
     */
    public Integer getPriceControl() {
        return priceControl;
    }

    /**
     * 业务单据价格（0-含税单价；1-不含税单价）
     * @param priceControl 业务单据价格（0-含税单价；1-不含税单价）
     */
    public void setPriceControl(Integer priceControl) {
        this.priceControl = priceControl;
    }

    /**
     * 业务单据输入顺序（0-顺序；1-倒叙，默认值1）
     * @return enter_order 业务单据输入顺序（0-顺序；1-倒叙，默认值1）
     */
    public Integer getEnterOrder() {
        return enterOrder;
    }

    /**
     * 业务单据输入顺序（0-顺序；1-倒叙，默认值1）
     * @param enterOrder 业务单据输入顺序（0-顺序；1-倒叙，默认值1）
     */
    public void setEnterOrder(Integer enterOrder) {
        this.enterOrder = enterOrder;
    }

    /**
     * 零成本入库（0禁止；1-允许）
     * @return zero_cost_in 零成本入库（0禁止；1-允许）
     */
    public Integer getZeroCostIn() {
        return zeroCostIn;
    }

    /**
     * 零成本入库（0禁止；1-允许）
     * @param zeroCostIn 零成本入库（0禁止；1-允许）
     */
    public void setZeroCostIn(Integer zeroCostIn) {
        this.zeroCostIn = zeroCostIn;
    }

    /**
     * 库存占用量控制（0关闭；1-开启）
     * @return inventory_occupancy 库存占用量控制（0关闭；1-开启）
     */
    public Integer getInventoryOccupancy() {
        return inventoryOccupancy;
    }

    /**
     * 库存占用量控制（0关闭；1-开启）
     * @param inventoryOccupancy 库存占用量控制（0关闭；1-开启）
     */
    public void setInventoryOccupancy(Integer inventoryOccupancy) {
        this.inventoryOccupancy = inventoryOccupancy;
    }

    /**
     * 基础数据质量控制（0关闭；1-开启）
     * @return quality_control 基础数据质量控制（0关闭；1-开启）
     */
    public Integer getQualityControl() {
        return qualityControl;
    }

    /**
     * 基础数据质量控制（0关闭；1-开启）
     * @param qualityControl 基础数据质量控制（0关闭；1-开启）
     */
    public void setQualityControl(Integer qualityControl) {
        this.qualityControl = qualityControl;
    }

    /**
     * 业务流程质量控制（0关闭；1-开启）
     * @return business_control 业务流程质量控制（0关闭；1-开启）
     */
    public Integer getBusinessControl() {
        return businessControl;
    }

    /**
     * 业务流程质量控制（0关闭；1-开启）
     * @param businessControl 业务流程质量控制（0关闭；1-开启）
     */
    public void setBusinessControl(Integer businessControl) {
        this.businessControl = businessControl;
    }

    /**
     * Pos质量控制（0关闭；1-开启）
     * @return pos_control Pos质量控制（0关闭；1-开启）
     */
    public Integer getPosControl() {
        return posControl;
    }

    /**
     * Pos质量控制（0关闭；1-开启）
     * @param posControl Pos质量控制（0关闭；1-开启）
     */
    public void setPosControl(Integer posControl) {
        this.posControl = posControl;
    }

    /**
     * 审批流程控制（0关闭；1-开启）
     * @return approval_control 审批流程控制（0关闭；1-开启）
     */
    public Integer getApprovalControl() {
        return approvalControl;
    }

    /**
     * 审批流程控制（0关闭；1-开启）
     * @param approvalControl 审批流程控制（0关闭；1-开启）
     */
    public void setApprovalControl(Integer approvalControl) {
        this.approvalControl = approvalControl;
    }

    /**
     * 总部：采购人员ID
     * @return purchaser_id 总部：采购人员ID
     */
    public Long getPurchaserId() {
        return purchaserId;
    }

    /**
     * 总部：采购人员ID
     * @param purchaserId 总部：采购人员ID
     */
    public void setPurchaserId(Long purchaserId) {
        this.purchaserId = purchaserId;
    }

    /**
     * 总部：采购人员编码
     * @return purchaser_code 总部：采购人员编码
     */
    public String getPurchaserCode() {
        return purchaserCode;
    }

    /**
     * 总部：采购人员编码
     * @param purchaserCode 总部：采购人员编码
     */
    public void setPurchaserCode(String purchaserCode) {
        this.purchaserCode = purchaserCode == null ? null : purchaserCode.trim();
    }

    /**
     * 总部：采购人员名称
     * @return purchaser_name 总部：采购人员名称
     */
    public String getPurchaserName() {
        return purchaserName;
    }

    /**
     * 总部：采购人员名称
     * @param purchaserName 总部：采购人员名称
     */
    public void setPurchaserName(String purchaserName) {
        this.purchaserName = purchaserName == null ? null : purchaserName.trim();
    }

    /**
     * 总部：配货人员ID
     * @return distr_man_id 总部：配货人员ID
     */
    public Long getDistrManId() {
        return distrManId;
    }

    /**
     * 总部：配货人员ID
     * @param distrManId 总部：配货人员ID
     */
    public void setDistrManId(Long distrManId) {
        this.distrManId = distrManId;
    }

    /**
     * 总部：配货人员编码
     * @return distr_man_code 总部：配货人员编码
     */
    public String getDistrManCode() {
        return distrManCode;
    }

    /**
     * 总部：配货人员编码
     * @param distrManCode 总部：配货人员编码
     */
    public void setDistrManCode(String distrManCode) {
        this.distrManCode = distrManCode == null ? null : distrManCode.trim();
    }

    /**
     * 总部：配货人员名称
     * @return distr_man_name 总部：配货人员名称
     */
    public String getDistrManName() {
        return distrManName;
    }

    /**
     * 总部：配货人员名称
     * @param distrManName 总部：配货人员名称
     */
    public void setDistrManName(String distrManName) {
        this.distrManName = distrManName == null ? null : distrManName.trim();
    }

    /**
     * 分店：要货人员ID
     * @return requester_id 分店：要货人员ID
     */
    public Long getRequesterId() {
        return requesterId;
    }

    /**
     * 分店：要货人员ID
     * @param requesterId 分店：要货人员ID
     */
    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    /**
     * 分店：要货人员编码
     * @return requester_code 分店：要货人员编码
     */
    public String getRequesterCode() {
        return requesterCode;
    }

    /**
     * 分店：要货人员编码
     * @param requesterCode 分店：要货人员编码
     */
    public void setRequesterCode(String requesterCode) {
        this.requesterCode = requesterCode == null ? null : requesterCode.trim();
    }

    /**
     * 分店：要货人员名称
     * @return requester_name 分店：要货人员名称
     */
    public String getRequesterName() {
        return requesterName;
    }

    /**
     * 分店：要货人员名称
     * @param requesterName 分店：要货人员名称
     */
    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName == null ? null : requesterName.trim();
    }

    /**
     * 收货人员ID
     * @return receiver_id 收货人员ID
     */
    public Long getReceiverId() {
        return receiverId;
    }

    /**
     * 收货人员ID
     * @param receiverId 收货人员ID
     */
    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    /**
     * 收货人员编码
     * @return receiver_code 收货人员编码
     */
    public String getReceiverCode() {
        return receiverCode;
    }

    /**
     * 收货人员编码
     * @param receiverCode 收货人员编码
     */
    public void setReceiverCode(String receiverCode) {
        this.receiverCode = receiverCode == null ? null : receiverCode.trim();
    }

    /**
     * 收货人员名称
     * @return receiver_name 收货人员名称
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 收货人员名称
     * @param receiverName 收货人员名称
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    /**
     * 第二收货人员ID
     * @return second_receiver_id 第二收货人员ID
     */
    public Long getSecondReceiverId() {
        return secondReceiverId;
    }

    /**
     * 第二收货人员ID
     * @param secondReceiverId 第二收货人员ID
     */
    public void setSecondReceiverId(Long secondReceiverId) {
        this.secondReceiverId = secondReceiverId;
    }

    /**
     * 收货人员编码
     * @return second_receiver_code 收货人员编码
     */
    public String getSecondReceiverCode() {
        return secondReceiverCode;
    }

    /**
     * 收货人员编码
     * @param secondReceiverCode 收货人员编码
     */
    public void setSecondReceiverCode(String secondReceiverCode) {
        this.secondReceiverCode = secondReceiverCode == null ? null : secondReceiverCode.trim();
    }

    /**
     * 第二收货人员名称
     * @return second_receiver_name 第二收货人员名称
     */
    public String getSecondReceiverName() {
        return secondReceiverName;
    }

    /**
     * 第二收货人员名称
     * @param secondReceiverName 第二收货人员名称
     */
    public void setSecondReceiverName(String secondReceiverName) {
        this.secondReceiverName = secondReceiverName == null ? null : secondReceiverName.trim();
    }

    /**
     * 验收人员ID
     * @return checker_id 验收人员ID
     */
    public Long getCheckerId() {
        return checkerId;
    }

    /**
     * 验收人员ID
     * @param checkerId 验收人员ID
     */
    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    /**
     * 验收人员编码
     * @return checker_code 验收人员编码
     */
    public String getCheckerCode() {
        return checkerCode;
    }

    /**
     * 验收人员编码
     * @param checkerCode 验收人员编码
     */
    public void setCheckerCode(String checkerCode) {
        this.checkerCode = checkerCode == null ? null : checkerCode.trim();
    }

    /**
     * 验收人员名称
     * @return checker_name 验收人员名称
     */
    public String getCheckerName() {
        return checkerName;
    }

    /**
     * 验收人员名称
     * @param checkerName 验收人员名称
     */
    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName == null ? null : checkerName.trim();
    }

    /**
     * 第二验收人员ID
     * @return second_checker_id 第二验收人员ID
     */
    public Long getSecondCheckerId() {
        return secondCheckerId;
    }

    /**
     * 第二验收人员ID
     * @param secondCheckerId 第二验收人员ID
     */
    public void setSecondCheckerId(Long secondCheckerId) {
        this.secondCheckerId = secondCheckerId;
    }

    /**
     * 第二验收人员编码
     * @return second_checker_code 第二验收人员编码
     */
    public String getSecondCheckerCode() {
        return secondCheckerCode;
    }

    /**
     * 第二验收人员编码
     * @param secondCheckerCode 第二验收人员编码
     */
    public void setSecondCheckerCode(String secondCheckerCode) {
        this.secondCheckerCode = secondCheckerCode == null ? null : secondCheckerCode.trim();
    }

    /**
     * 第二验收人员名称
     * @return second_checker_name 第二验收人员名称
     */
    public String getSecondCheckerName() {
        return secondCheckerName;
    }

    /**
     * 第二验收人员名称
     * @param secondCheckerName 第二验收人员名称
     */
    public void setSecondCheckerName(String secondCheckerName) {
        this.secondCheckerName = secondCheckerName == null ? null : secondCheckerName.trim();
    }

    /**
     * 入库/出库人员ID
     * @return in_out_man_id 入库/出库人员ID
     */
    public Long getInOutManId() {
        return inOutManId;
    }

    /**
     * 入库/出库人员ID
     * @param inOutManId 入库/出库人员ID
     */
    public void setInOutManId(Long inOutManId) {
        this.inOutManId = inOutManId;
    }

    /**
     * 入库/出库人员编码
     * @return in_out_man_code 入库/出库人员编码
     */
    public String getInOutManCode() {
        return inOutManCode;
    }

    /**
     * 入库/出库人员编码
     * @param inOutManCode 入库/出库人员编码
     */
    public void setInOutManCode(String inOutManCode) {
        this.inOutManCode = inOutManCode == null ? null : inOutManCode.trim();
    }

    /**
     * 入库/出库人员名称
     * @return in_out_man_name 入库/出库人员名称
     */
    public String getInOutManName() {
        return inOutManName;
    }

    /**
     * 入库/出库人员名称
     * @param inOutManName 入库/出库人员名称
     */
    public void setInOutManName(String inOutManName) {
        this.inOutManName = inOutManName == null ? null : inOutManName.trim();
    }

    /**
     * 出库复核人员ID
     * @return out_checker_id 出库复核人员ID
     */
    public Long getOutCheckerId() {
        return outCheckerId;
    }

    /**
     * 出库复核人员ID
     * @param outCheckerId 出库复核人员ID
     */
    public void setOutCheckerId(Long outCheckerId) {
        this.outCheckerId = outCheckerId;
    }

    /**
     * 出库复核人员编码
     * @return out_checker_code 出库复核人员编码
     */
    public String getOutCheckerCode() {
        return outCheckerCode;
    }

    /**
     * 出库复核人员编码
     * @param outCheckerCode 出库复核人员编码
     */
    public void setOutCheckerCode(String outCheckerCode) {
        this.outCheckerCode = outCheckerCode == null ? null : outCheckerCode.trim();
    }

    /**
     * 出库复核人员名称
     * @return out_checker_name 出库复核人员名称
     */
    public String getOutCheckerName() {
        return outCheckerName;
    }

    /**
     * 出库复核人员名称
     * @param outCheckerName 出库复核人员名称
     */
    public void setOutCheckerName(String outCheckerName) {
        this.outCheckerName = outCheckerName == null ? null : outCheckerName.trim();
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
    
    public Integer getQualityEdit() {
		return qualityEdit;
	}

	public void setQualityEdit(Integer qualityEdit) {
		this.qualityEdit = qualityEdit;
	}

	public Integer getApprovalEdit() {
		return approvalEdit;
	}

	public void setApprovalEdit(Integer approvalEdit) {
		this.approvalEdit = approvalEdit;
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
        sb.append(", chainType=").append(chainType);
        sb.append(", supplierCodeRule=").append(supplierCodeRule);
        sb.append(", goodsCodeRule=").append(goodsCodeRule);
        sb.append(", userCodeRule=").append(userCodeRule);
        sb.append(", priceControl=").append(priceControl);
        sb.append(", enterOrder=").append(enterOrder);
        sb.append(", zeroCostIn=").append(zeroCostIn);
        sb.append(", inventoryOccupancy=").append(inventoryOccupancy);
        sb.append(", qualityControl=").append(qualityControl);
        sb.append(", businessControl=").append(businessControl);
        sb.append(", posControl=").append(posControl);
        sb.append(", approvalControl=").append(approvalControl);
        sb.append(", purchaserId=").append(purchaserId);
        sb.append(", purchaserCode=").append(purchaserCode);
        sb.append(", purchaserName=").append(purchaserName);
        sb.append(", distrManId=").append(distrManId);
        sb.append(", distrManCode=").append(distrManCode);
        sb.append(", distrManName=").append(distrManName);
        sb.append(", requesterId=").append(requesterId);
        sb.append(", requesterCode=").append(requesterCode);
        sb.append(", requesterName=").append(requesterName);
        sb.append(", receiverId=").append(receiverId);
        sb.append(", receiverCode=").append(receiverCode);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", secondReceiverId=").append(secondReceiverId);
        sb.append(", secondReceiverCode=").append(secondReceiverCode);
        sb.append(", secondReceiverName=").append(secondReceiverName);
        sb.append(", checkerId=").append(checkerId);
        sb.append(", checkerCode=").append(checkerCode);
        sb.append(", checkerName=").append(checkerName);
        sb.append(", secondCheckerId=").append(secondCheckerId);
        sb.append(", secondCheckerCode=").append(secondCheckerCode);
        sb.append(", secondCheckerName=").append(secondCheckerName);
        sb.append(", inOutManId=").append(inOutManId);
        sb.append(", inOutManCode=").append(inOutManCode);
        sb.append(", inOutManName=").append(inOutManName);
        sb.append(", outCheckerId=").append(outCheckerId);
        sb.append(", outCheckerCode=").append(outCheckerCode);
        sb.append(", outCheckerName=").append(outCheckerName);
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