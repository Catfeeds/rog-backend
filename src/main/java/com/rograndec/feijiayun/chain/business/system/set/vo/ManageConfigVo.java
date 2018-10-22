package com.rograndec.feijiayun.chain.business.system.set.vo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "manageConfigVo", description = "系统设置相关查询")
public class ManageConfigVo {
	
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

	public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

	public Integer getSupplierCodeRule() {
		return supplierCodeRule;
	}

	public void setSupplierCodeRule(Integer supplierCodeRule) {
		this.supplierCodeRule = supplierCodeRule;
	}

	public Integer getGoodsCodeRule() {
		return goodsCodeRule;
	}

	public void setGoodsCodeRule(Integer goodsCodeRule) {
		this.goodsCodeRule = goodsCodeRule;
	}

	public Integer getUserCodeRule() {
		return userCodeRule;
	}

	public void setUserCodeRule(Integer userCodeRule) {
		this.userCodeRule = userCodeRule;
	}

	public Integer getPriceControl() {
		return priceControl;
	}

	public void setPriceControl(Integer priceControl) {
		this.priceControl = priceControl;
	}

	public Integer getEnterOrder() {
		return enterOrder;
	}

	public void setEnterOrder(Integer enterOrder) {
		this.enterOrder = enterOrder;
	}

	public Integer getZeroCostIn() {
		return zeroCostIn;
	}

	public void setZeroCostIn(Integer zeroCostIn) {
		this.zeroCostIn = zeroCostIn;
	}

	public Integer getInventoryOccupancy() {
		return inventoryOccupancy;
	}

	public void setInventoryOccupancy(Integer inventoryOccupancy) {
		this.inventoryOccupancy = inventoryOccupancy;
	}

	public Integer getQualityControl() {
		return qualityControl;
	}

	public void setQualityControl(Integer qualityControl) {
		this.qualityControl = qualityControl;
	}

	public Integer getBusinessControl() {
		return businessControl;
	}

	public void setBusinessControl(Integer businessControl) {
		this.businessControl = businessControl;
	}

	public Integer getPosControl() {
		return posControl;
	}

	public void setPosControl(Integer posControl) {
		this.posControl = posControl;
	}

	public Integer getApprovalControl() {
		return approvalControl;
	}

	public void setApprovalControl(Integer approvalControl) {
		this.approvalControl = approvalControl;
	}

	public Long getPurchaserId() {
		return purchaserId;
	}

	public void setPurchaserId(Long purchaserId) {
		this.purchaserId = purchaserId;
	}

	public String getPurchaserCode() {
		return purchaserCode;
	}

	public void setPurchaserCode(String purchaserCode) {
		this.purchaserCode = purchaserCode;
	}

	public String getPurchaserName() {
		return purchaserName;
	}

	public void setPurchaserName(String purchaserName) {
		this.purchaserName = purchaserName;
	}

	public Long getDistrManId() {
		return distrManId;
	}

	public void setDistrManId(Long distrManId) {
		this.distrManId = distrManId;
	}

	public String getDistrManCode() {
		return distrManCode;
	}

	public void setDistrManCode(String distrManCode) {
		this.distrManCode = distrManCode;
	}

	public String getDistrManName() {
		return distrManName;
	}

	public void setDistrManName(String distrManName) {
		this.distrManName = distrManName;
	}

	public Long getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}

	public String getRequesterCode() {
		return requesterCode;
	}

	public void setRequesterCode(String requesterCode) {
		this.requesterCode = requesterCode;
	}

	public String getRequesterName() {
		return requesterName;
	}

	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverCode() {
		return receiverCode;
	}

	public void setReceiverCode(String receiverCode) {
		this.receiverCode = receiverCode;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Long getSecondReceiverId() {
		return secondReceiverId;
	}

	public void setSecondReceiverId(Long secondReceiverId) {
		this.secondReceiverId = secondReceiverId;
	}

	public String getSecondReceiverCode() {
		return secondReceiverCode;
	}

	public void setSecondReceiverCode(String secondReceiverCode) {
		this.secondReceiverCode = secondReceiverCode;
	}

	public String getSecondReceiverName() {
		return secondReceiverName;
	}

	public void setSecondReceiverName(String secondReceiverName) {
		this.secondReceiverName = secondReceiverName;
	}

	public Long getCheckerId() {
		return checkerId;
	}

	public void setCheckerId(Long checkerId) {
		this.checkerId = checkerId;
	}

	public String getCheckerCode() {
		return checkerCode;
	}

	public void setCheckerCode(String checkerCode) {
		this.checkerCode = checkerCode;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public Long getSecondCheckerId() {
		return secondCheckerId;
	}

	public void setSecondCheckerId(Long secondCheckerId) {
		this.secondCheckerId = secondCheckerId;
	}

	public String getSecondCheckerCode() {
		return secondCheckerCode;
	}

	public void setSecondCheckerCode(String secondCheckerCode) {
		this.secondCheckerCode = secondCheckerCode;
	}

	public String getSecondCheckerName() {
		return secondCheckerName;
	}

	public void setSecondCheckerName(String secondCheckerName) {
		this.secondCheckerName = secondCheckerName;
	}

	public Long getInOutManId() {
		return inOutManId;
	}

	public void setInOutManId(Long inOutManId) {
		this.inOutManId = inOutManId;
	}

	public String getInOutManCode() {
		return inOutManCode;
	}

	public void setInOutManCode(String inOutManCode) {
		this.inOutManCode = inOutManCode;
	}

	public String getInOutManName() {
		return inOutManName;
	}

	public void setInOutManName(String inOutManName) {
		this.inOutManName = inOutManName;
	}

	public Long getOutCheckerId() {
		return outCheckerId;
	}

	public void setOutCheckerId(Long outCheckerId) {
		this.outCheckerId = outCheckerId;
	}

	public String getOutCheckerCode() {
		return outCheckerCode;
	}

	public void setOutCheckerCode(String outCheckerCode) {
		this.outCheckerCode = outCheckerCode;
	}

	public String getOutCheckerName() {
		return outCheckerName;
	}

	public void setOutCheckerName(String outCheckerName) {
		this.outCheckerName = outCheckerName;
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
     * 如果是分店质量控制是否可编辑(0-不可编辑 1-可编辑)
     */
    @ApiModelProperty(value="如果是分店质量控制是否可编辑(0-不可编辑 1-可编辑)",required=true)
    private Integer qualityEdit;
    
    /**
     * 如果是分店审批控制是否可编辑(0-不可编辑 1-可编辑)
     */
    @ApiModelProperty(value="如果是分店审批控制是否可编辑(0-不可编辑 1-可编辑)",required=true)
    private Integer approvalEdit;

}
