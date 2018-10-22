package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.purchase;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PurchaseReceiveReportVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 供货单位编码
     */
    @ApiModelProperty(value = "供货单位编码")
    private String supplierCode;

    /**
     * 供货单位名称
     */
    @ApiModelProperty(value = "供货单位名称")
    private String supplierName;

    /**
     * 供货单位销售人员ID
     */
    @ApiModelProperty(value = "供货单位销售人员ID")
    private Long supplierSalerId;

    /**
     * 供货单位销售人员编码
     */
    @ApiModelProperty(value = "供货单位销售人员编码")
    private String supplierSalerCode;

    /**
     * 供货单位销售人员名称
     */
    @ApiModelProperty(value = "供货单位销售人员名称")
    private String supplierSalerName;

    /**
     * 供货单位销售人员电话
     */
    @ApiModelProperty(value = "供货单位销售人员电话")
    private String supplierSalerPhone;

    /**
     * 收货人员ID
     */
    @ApiModelProperty(required = true, value = "收货人员ID")
    private Long receiverId;

    /**
     * 收货人员编码
     */
    @ApiModelProperty(required = true, value = "收货人员编码")
    private String receiverCode;

    /**
     * 收货人员名称
     */
    @ApiModelProperty(required = true, value = "收货人员名称")
    private String receiverName;

    /**
     * 收货人2ID
     */
    @ApiModelProperty(required = true, value = "收货人2ID")
    private Long secondReceiverId;

    /**
     * 收货人2编码
     */
    @ApiModelProperty(required = true, value = "收货人2编码")
    private String secondReceiverCode;

    /**
     * 收货人2名称
     */
    @ApiModelProperty(required = true, value = "收货人2名称")
    private String secondReceiverName;

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
     * 收货单ID
     */
    @ApiModelProperty(value = "收货单ID")
    private Long receiveId;

    /**
     * 单据（收货单）类型（5212）
     */
    @ApiModelProperty(value = "单据（收货单）类型（5212）")
    private Integer orderType;

    /**
     * 收货单编号
     */
    @ApiModelProperty(value = "收货单编号")
    private String receiveCode;

    /**
     * 收货日期
     */
    @ApiModelProperty(value = "收货日期")
    private Date receiveDate;

    /**
     * 基础单据明细ID
     */
    @ApiModelProperty(value = "基础单据明细ID")
    private Long baseOrderDtlId;

    /**
     * 基础单据ID
     */
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
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    /**
     * 条形码
     */
    @ApiModelProperty(value = "条形码")
    private String barcode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;

    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 商品产地
     */
    @ApiModelProperty(value = "商品产地")
    private String goodsPlace;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;

    /**
     * 订单数量
     */
    @ApiModelProperty(value = "订单数量")
    private BigDecimal orderQuantity;

    /**
     * 到货数量
     */
    @ApiModelProperty(value = "到货数量")
    private BigDecimal arrivalQuantity;

    /**
     * 收货数量
     */
    @ApiModelProperty(value = "收货数量")
    private BigDecimal receiveQuantity;

    /**
     * 拒收数量
     */
    @ApiModelProperty(value = "拒收数量")
    private BigDecimal refuseQuantity;

    /**
     * 拒收原因ID集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "拒收原因ID集合，多个用逗号分隔")
    private String refuseReasonIds;
    /**
     * 拒收原因集合，多个用逗号分隔
     */
    @ApiModelProperty(value = "拒收原因集合，多个用逗号分隔")
    private String refuseReason;

    /**
     * 未清数量
     */
    @ApiModelProperty(value = "未清数量")
    private BigDecimal unclearQuantity;

    /**
     * 已清数量
     */
    @ApiModelProperty(value = "已清数量")
    private BigDecimal clearQuantity;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private String statusName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
    @ApiModelProperty(value="品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private Integer businessVariety;

    /**
     * 品种类别
     */
    @ApiModelProperty(value="品种类别")
    private String businessVarietyName;

    /**
     * 商品分类ID
     */
    @ApiModelProperty(value="商品分类ID")
    private Long categoryId;

    @ApiModelProperty(value="商品分类名称")
    private String categoryName;

    /**
     * 商品属性（0-成药；1-中药材；2-中药饮片）
     */
    @ApiModelProperty(value="商品属性（0-成药；1-中药材；2-中药饮片）")
    private Integer goodsAttribute;

    /**
     * 商品属性名
     */
    @ApiModelProperty(value="商品属性名")
    private String goodsAttributeName;

    /**
     * 国产/进口（0-国产；1-进口）
     */
    @ApiModelProperty(value="国产/进口（0-国产；1-进口）")
    private Integer domesticImport;

    /**
     * 国产/进口
     */
    @ApiModelProperty(value="国产/进口")
    private String domesticImportName;

    /**
     * 经营范围ID
     */
    @ApiModelProperty(value="经营范围ID")
    private Long managementScopeId;

    /**
     * 经营范围名称
     */
    @ApiModelProperty(value=" 经营范围名称")
    private String managementScopeName;

    /**
     *
     * 特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）
     */
    @ApiModelProperty(value="特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）")
    private Integer specialDrug;

    /**
     *
     * 特殊管理药品
     */
    @ApiModelProperty(value="特殊管理药品")
    private String specialDrugName;

    /**
     * 专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）
     */
    @ApiModelProperty(value="专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）")
    private Integer inChargeDrug;

    /**
     * 专管药品
     */
    @ApiModelProperty(value="专管药品")
    private String inChargeDrugName;

    /**
     * 限购数量
     */
    @ApiModelProperty(value="限购数量")
    private BigDecimal limitQuantity;

    /**
     * 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）
     */
    @ApiModelProperty(value=" 贮藏温度（0-常温；1-阴凉；2-冷藏；3-冷冻）")
    private Integer storageTemp;

    /**
     * 贮藏温度
     */
    @ApiModelProperty(value=" 贮藏温度")
    private String storageTempName;

    /**
     * 贮藏条件名称
     */
    @ApiModelProperty(value="贮藏条件名称")
    private String storageConditionName;

    /**
     * 保质期
     */
    @ApiModelProperty(value="保质期")
    private Integer qualityPeriod;

    /**
     * 保质期单位（0-日；1-月；2-年）
     */
    @ApiModelProperty(value="保质期单位（0-日；1-月；2-年）")
    private Integer qualityPeriodUnit;

    /**
     * 保质期
     */
    @ApiModelProperty(value="保质期")
    private String qualityPeriodUnitName;

    /**
     * saas_purchase_receive_detail
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
     * 收货单ID
     * @return receive_id 收货单ID
     */
    public Long getReceiveId() {
        return receiveId;
    }

    /**
     * 收货单ID
     * @param receiveId 收货单ID
     */
    public void setReceiveId(Long receiveId) {
        this.receiveId = receiveId;
    }

    /**
     * 单据（收货单）类型（5212）
     * @return order_type 单据（收货单）类型（5212）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据（收货单）类型（5212）
     * @param orderType 单据（收货单）类型（5212）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 收货单编号
     * @return receive_code 收货单编号
     */
    public String getReceiveCode() {
        return receiveCode;
    }

    /**
     * 收货单编号
     * @param receiveCode 收货单编号
     */
    public void setReceiveCode(String receiveCode) {
        this.receiveCode = receiveCode == null ? null : receiveCode.trim();
    }

    /**
     * 收货日期
     * @return receive_date 收货日期
     */
    public Date getReceiveDate() {
        return receiveDate;
    }

    /**
     * 收货日期
     * @param receiveDate 收货日期
     */
    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    /**
     * 基础单据明细ID
     * @return base_order_dtl_id 基础单据明细ID
     */
    public Long getBaseOrderDtlId() {
        return baseOrderDtlId;
    }

    /**
     * 基础单据明细ID
     * @param baseOrderDtlId 基础单据明细ID
     */
    public void setBaseOrderDtlId(Long baseOrderDtlId) {
        this.baseOrderDtlId = baseOrderDtlId;
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
     * 商品ID
     * @return goods_id 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 商品ID
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 商品编码
     * @return goods_code 商品编码
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * 商品编码
     * @param goodsCode 商品编码
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    /**
     * 条形码
     * @return barcode 条形码
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * 条形码
     * @param barcode 条形码
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    /**
     * 商品名称
     * @return goods_name 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 商品名称
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 商品通用名称
     * @return goods_generic_name 商品通用名称
     */
    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    /**
     * 商品通用名称
     * @param goodsGenericName 商品通用名称
     */
    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName == null ? null : goodsGenericName.trim();
    }

    /**
     * 剂型ID
     * @return dosage_id 剂型ID
     */
    public Long getDosageId() {
        return dosageId;
    }

    /**
     * 剂型ID
     * @param dosageId 剂型ID
     */
    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    /**
     * 剂型名称
     * @return dosage_name 剂型名称
     */
    public String getDosageName() {
        return dosageName;
    }

    /**
     * 剂型名称
     * @param dosageName 剂型名称
     */
    public void setDosageName(String dosageName) {
        this.dosageName = dosageName == null ? null : dosageName.trim();
    }

    /**
     * 单位ID
     * @return unit_id 单位ID
     */
    public Long getUnitId() {
        return unitId;
    }

    /**
     * 单位ID
     * @param unitId 单位ID
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    /**
     * 单位名称
     * @return unit_name 单位名称
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 单位名称
     * @param unitName 单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    /**
     * 商品规格
     * @return goods_specification 商品规格
     */
    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    /**
     * 商品规格
     * @param goodsSpecification 商品规格
     */
    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification == null ? null : goodsSpecification.trim();
    }

    /**
     * 生产厂商ID
     * @return manufacturer_id 生产厂商ID
     */
    public Long getManufacturerId() {
        return manufacturerId;
    }

    /**
     * 生产厂商ID
     * @param manufacturerId 生产厂商ID
     */
    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * 生产厂商
     * @return manufacturer 生产厂商
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 生产厂商
     * @param manufacturer 生产厂商
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    /**
     * 商品产地
     * @return goods_place 商品产地
     */
    public String getGoodsPlace() {
        return goodsPlace;
    }

    /**
     * 商品产地
     * @param goodsPlace 商品产地
     */
    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace == null ? null : goodsPlace.trim();
    }

    /**
     * 批准文号
     * @return approval_number 批准文号
     */
    public String getApprovalNumber() {
        return approvalNumber;
    }

    /**
     * 批准文号
     * @param approvalNumber 批准文号
     */
    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber == null ? null : approvalNumber.trim();
    }

    /**
     * 订单数量
     * @return order_quantity 订单数量
     */
    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    /**
     * 订单数量
     * @param orderQuantity 订单数量
     */
    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    /**
     * 到货数量
     * @return arrival_quantity 到货数量
     */
    public BigDecimal getArrivalQuantity() {
        return arrivalQuantity;
    }

    /**
     * 到货数量
     * @param arrivalQuantity 到货数量
     */
    public void setArrivalQuantity(BigDecimal arrivalQuantity) {
        this.arrivalQuantity = arrivalQuantity;
    }

    /**
     * 收货数量
     * @return receive_quantity 收货数量
     */
    public BigDecimal getReceiveQuantity() {
        return receiveQuantity;
    }

    /**
     * 收货数量
     * @param receiveQuantity 收货数量
     */
    public void setReceiveQuantity(BigDecimal receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    /**
     * 拒收数量
     * @return refuse_quantity 拒收数量
     */
    public BigDecimal getRefuseQuantity() {
        return refuseQuantity;
    }

    /**
     * 拒收数量
     * @param refuseQuantity 拒收数量
     */
    public void setRefuseQuantity(BigDecimal refuseQuantity) {
        this.refuseQuantity = refuseQuantity;
    }

    /**
     * 拒收原因ID集合，多个用逗号分隔
     * @return refuse_reason_ids 拒收原因ID集合，多个用逗号分隔
     */
    public String getRefuseReasonIds() {
        return refuseReasonIds;
    }

    /**
     * 拒收原因ID集合，多个用逗号分隔
     * @param refuseReasonIds 拒收原因ID集合，多个用逗号分隔
     */
    public void setRefuseReasonIds(String refuseReasonIds) {
        this.refuseReasonIds = refuseReasonIds == null ? null : refuseReasonIds.trim();
    }

    /**
     * 未清数量
     * @return unclear_quantity 未清数量
     */
    public BigDecimal getUnclearQuantity() {
        return unclearQuantity;
    }

    /**
     * 未清数量
     * @param unclearQuantity 未清数量
     */
    public void setUnclearQuantity(BigDecimal unclearQuantity) {
        this.unclearQuantity = unclearQuantity;
    }

    /**
     * 已清数量
     * @return clear_quantity 已清数量
     */
    public BigDecimal getClearQuantity() {
        return clearQuantity;
    }

    /**
     * 已清数量
     * @param clearQuantity 已清数量
     */
    public void setClearQuantity(BigDecimal clearQuantity) {
        this.clearQuantity = clearQuantity;
    }

    /**
     * 行号
     * @return line_num 行号
     */
    public Integer getLineNum() {
        return lineNum;
    }

    /**
     * 行号
     * @param lineNum 行号
     */
    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
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

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Long getSupplierSalerId() {
        return supplierSalerId;
    }

    public void setSupplierSalerId(Long supplierSalerId) {
        this.supplierSalerId = supplierSalerId;
    }

    public String getSupplierSalerCode() {
        return supplierSalerCode;
    }

    public void setSupplierSalerCode(String supplierSalerCode) {
        this.supplierSalerCode = supplierSalerCode;
    }

    public String getSupplierSalerName() {
        return supplierSalerName;
    }

    public void setSupplierSalerName(String supplierSalerName) {
        this.supplierSalerName = supplierSalerName;
    }

    public String getSupplierSalerPhone() {
        return supplierSalerPhone;
    }

    public void setSupplierSalerPhone(String supplierSalerPhone) {
        this.supplierSalerPhone = supplierSalerPhone;
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

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public Integer getBusinessVariety() {
        return businessVariety;
    }

    public void setBusinessVariety(Integer businessVariety) {
        this.businessVariety = businessVariety;
    }

    public String getBusinessVarietyName() {
        return businessVarietyName;
    }

    public void setBusinessVarietyName(String businessVarietyName) {
        this.businessVarietyName = businessVarietyName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getGoodsAttribute() {
        return goodsAttribute;
    }

    public void setGoodsAttribute(Integer goodsAttribute) {
        this.goodsAttribute = goodsAttribute;
    }

    public String getGoodsAttributeName() {
        return goodsAttributeName;
    }

    public void setGoodsAttributeName(String goodsAttributeName) {
        this.goodsAttributeName = goodsAttributeName;
    }

    public Integer getDomesticImport() {
        return domesticImport;
    }

    public void setDomesticImport(Integer domesticImport) {
        this.domesticImport = domesticImport;
    }

    public String getDomesticImportName() {
        return domesticImportName;
    }

    public void setDomesticImportName(String domesticImportName) {
        this.domesticImportName = domesticImportName;
    }

    public Long getManagementScopeId() {
        return managementScopeId;
    }

    public void setManagementScopeId(Long managementScopeId) {
        this.managementScopeId = managementScopeId;
    }

    public String getManagementScopeName() {
        return managementScopeName;
    }

    public void setManagementScopeName(String managementScopeName) {
        this.managementScopeName = managementScopeName;
    }

    public Integer getSpecialDrug() {
        return specialDrug;
    }

    public void setSpecialDrug(Integer specialDrug) {
        this.specialDrug = specialDrug;
    }

    public String getSpecialDrugName() {
        return specialDrugName;
    }

    public void setSpecialDrugName(String specialDrugName) {
        this.specialDrugName = specialDrugName;
    }

    public Integer getInChargeDrug() {
        return inChargeDrug;
    }

    public void setInChargeDrug(Integer inChargeDrug) {
        this.inChargeDrug = inChargeDrug;
    }

    public String getInChargeDrugName() {
        return inChargeDrugName;
    }

    public void setInChargeDrugName(String inChargeDrugName) {
        this.inChargeDrugName = inChargeDrugName;
    }

    public BigDecimal getLimitQuantity() {
        return limitQuantity;
    }

    public void setLimitQuantity(BigDecimal limitQuantity) {
        this.limitQuantity = limitQuantity;
    }

    public Integer getStorageTemp() {
        return storageTemp;
    }

    public void setStorageTemp(Integer storageTemp) {
        this.storageTemp = storageTemp;
    }

    public String getStorageTempName() {
        return storageTempName;
    }

    public void setStorageTempName(String storageTempName) {
        this.storageTempName = storageTempName;
    }

    public String getStorageConditionName() {
        return storageConditionName;
    }

    public void setStorageConditionName(String storageConditionName) {
        this.storageConditionName = storageConditionName;
    }

    public Integer getQualityPeriod() {
        return qualityPeriod;
    }

    public void setQualityPeriod(Integer qualityPeriod) {
        this.qualityPeriod = qualityPeriod;
    }

    public Integer getQualityPeriodUnit() {
        return qualityPeriodUnit;
    }

    public void setQualityPeriodUnit(Integer qualityPeriodUnit) {
        this.qualityPeriodUnit = qualityPeriodUnit;
    }

    public String getQualityPeriodUnitName() {
        return qualityPeriodUnitName;
    }

    public void setQualityPeriodUnitName(String qualityPeriodUnitName) {
        this.qualityPeriodUnitName = qualityPeriodUnitName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "PurchaseReceiveReportVO{" +
                "id=" + id +
                ", supplierCode='" + supplierCode + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierSalerId=" + supplierSalerId +
                ", supplierSalerCode='" + supplierSalerCode + '\'' +
                ", supplierSalerName='" + supplierSalerName + '\'' +
                ", supplierSalerPhone='" + supplierSalerPhone + '\'' +
                ", receiverId=" + receiverId +
                ", receiverCode='" + receiverCode + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", secondReceiverId=" + secondReceiverId +
                ", secondReceiverCode='" + secondReceiverCode + '\'' +
                ", secondReceiverName='" + secondReceiverName + '\'' +
                ", enterpriseId=" + enterpriseId +
                ", parentId=" + parentId +
                ", receiveId=" + receiveId +
                ", orderType=" + orderType +
                ", receiveCode='" + receiveCode + '\'' +
                ", receiveDate=" + receiveDate +
                ", baseOrderDtlId=" + baseOrderDtlId +
                ", baseOrderId=" + baseOrderId +
                ", baseOrderType=" + baseOrderType +
                ", baseOrderCode='" + baseOrderCode + '\'' +
                ", baseOrderDate=" + baseOrderDate +
                ", goodsId=" + goodsId +
                ", goodsCode='" + goodsCode + '\'' +
                ", barcode='" + barcode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsGenericName='" + goodsGenericName + '\'' +
                ", dosageId=" + dosageId +
                ", dosageName='" + dosageName + '\'' +
                ", unitId=" + unitId +
                ", unitName='" + unitName + '\'' +
                ", goodsSpecification='" + goodsSpecification + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", manufacturer='" + manufacturer + '\'' +
                ", goodsPlace='" + goodsPlace + '\'' +
                ", approvalNumber='" + approvalNumber + '\'' +
                ", orderQuantity=" + orderQuantity +
                ", arrivalQuantity=" + arrivalQuantity +
                ", receiveQuantity=" + receiveQuantity +
                ", refuseQuantity=" + refuseQuantity +
                ", refuseReasonIds='" + refuseReasonIds + '\'' +
                ", refuseReason='" + refuseReason + '\'' +
                ", unclearQuantity=" + unclearQuantity +
                ", clearQuantity=" + clearQuantity +
                ", lineNum=" + lineNum +
                ", status=" + status +
                ", statusName=" + statusName +
                ", remark='" + remark + '\'' +
                ", businessVariety=" + businessVariety +
                ", businessVarietyName='" + businessVarietyName + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", goodsAttribute=" + goodsAttribute +
                ", goodsAttributeName='" + goodsAttributeName + '\'' +
                ", domesticImport=" + domesticImport +
                ", domesticImportName='" + domesticImportName + '\'' +
                ", managementScopeId=" + managementScopeId +
                ", managementScopeName='" + managementScopeName + '\'' +
                ", specialDrug=" + specialDrug +
                ", specialDrugName='" + specialDrugName + '\'' +
                ", inChargeDrug=" + inChargeDrug +
                ", inChargeDrugName='" + inChargeDrugName + '\'' +
                ", limitQuantity=" + limitQuantity +
                ", storageTemp=" + storageTemp +
                ", storageTempName='" + storageTempName + '\'' +
                ", storageConditionName='" + storageConditionName + '\'' +
                ", qualityPeriod=" + qualityPeriod +
                ", qualityPeriodUnit=" + qualityPeriodUnit +
                ", qualityPeriodUnitName='" + qualityPeriodUnitName + '\'' +
                '}';
    }
}