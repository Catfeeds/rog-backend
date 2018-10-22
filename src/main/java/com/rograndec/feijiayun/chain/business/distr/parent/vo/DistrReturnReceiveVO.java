package com.rograndec.feijiayun.chain.business.distr.parent.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_distr_return_receive
 * 
 * 
 * @author Asze
 * 
 * 2017-10-08
 */
public class DistrReturnReceiveVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 配后退回收货单号
     */
    @ApiModelProperty(value = "配后退回收货单号")
    private String code;

    /**
     * 配后退回收货日期
     */
    @ApiModelProperty(value = "配后退回收货日期")
    private Date receiveDate;

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
     * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    @ApiModelProperty(value = "配货类型（0-总部配送；3-分店间调剂；4-直调配送）")
    private Integer distrType;

    /**
     * 配货类型名
     */
    @ApiModelProperty(value = "配货类型名")
    private String distrTypeName;


    /**
     * 收货人员ID
     */
    @ApiModelProperty(value = "收货人员ID")
    private Long receiverId;

    /**
     * 收货人员编码
     */
    @ApiModelProperty(value = "收货人员编码")
    private String receiverCode;

    /**
     * 收货人员名称
     */
    @ApiModelProperty(value = "收货人员名称")
    private String receiverName;

    /**
     * 第二收货人员ID
     */
    @ApiModelProperty(value = "第二收货人员ID")
    private Long secondReceiverId;

    /**
     * 收货人员编码
     */
    @ApiModelProperty(value = "收货人员编码")
    private String secondReceiverCode;

    /**
     * 第二收货人员名称
     */
    @ApiModelProperty(value = "第二收货人员名称")
    private String secondReceiverName;

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
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 状态名
     */
    @ApiModelProperty(value = "状态名")
    private String statusName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 用于打印（企业名称，到货数量合计，收货数量合计，拒收数量合计）
     */
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String enterpriseName;
    /**
     * 到货数量合计
     */
    @ApiModelProperty(value = "到货数量合计")
    private BigDecimal arrivalQuantityTotal;

    /**
     * 收货数量合计
     */
    @ApiModelProperty(value = "收货数量合计")
    private BigDecimal receiveQuantityTotal;

    /**
     * 拒收数量合计
     */
    @ApiModelProperty(value = "拒收数量合计")
    private BigDecimal refuseQuantityTotal;

    /**
     * 细单集合
     */
    @ApiModelProperty(value = "细单集合")
    private List<DistrReturnReceiveDetailVO> distrReturnReceiveDetailVOS;

    /**
     * 结算单位是否是加盟店（0-否  1-是）
     */
    @ApiModelProperty(required = true, value = "结算单位是否是加盟店（0-否  1-是）")
    private Integer franchisedStoreFlag;

    /**
     * saas_distr_return_receive
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
     * 配后退回收货单号
     * @return code 配后退回收货单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 配后退回收货单号
     * @param code 配后退回收货单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 配后退回收货日期
     * @return receive_date 配后退回收货日期
     */
    public Date getReceiveDate() {
        return receiveDate;
    }

    /**
     * 配后退回收货日期
     * @param receiveDate 配后退回收货日期
     */
    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
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
     * 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    public Integer getDistrType() {
        return distrType;
    }

    /**
     *配货类型（0-总部配送；3-分店间调剂；4-直调配送）
     */
    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<DistrReturnReceiveDetailVO> getDistrReturnReceiveDetailVOS() {
        return distrReturnReceiveDetailVOS;
    }

    public void setDistrReturnReceiveDetailVOS(List<DistrReturnReceiveDetailVO> distrReturnReceiveDetailVOS) {
        this.distrReturnReceiveDetailVOS = distrReturnReceiveDetailVOS;
    }

    public String getDistrTypeName() {
        return distrTypeName;
    }

    public void setDistrTypeName(String distrTypeName) {
        this.distrTypeName = distrTypeName;
    }

    public Integer getFranchisedStoreFlag() {
        return franchisedStoreFlag;
    }

    public void setFranchisedStoreFlag(Integer franchisedStoreFlag) {
        this.franchisedStoreFlag = franchisedStoreFlag;
    }

    public BigDecimal getArrivalQuantityTotal() {
        return arrivalQuantityTotal;
    }

    public void setArrivalQuantityTotal(BigDecimal arrivalQuantityTotal) {
        this.arrivalQuantityTotal = arrivalQuantityTotal;
    }

    public BigDecimal getReceiveQuantityTotal() {
        return receiveQuantityTotal;
    }

    public void setReceiveQuantityTotal(BigDecimal receiveQuantityTotal) {
        this.receiveQuantityTotal = receiveQuantityTotal;
    }

    public BigDecimal getRefuseQuantityTotal() {
        return refuseQuantityTotal;
    }

    public void setRefuseQuantityTotal(BigDecimal refuseQuantityTotal) {
        this.refuseQuantityTotal = refuseQuantityTotal;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "DistrReturnReceiveVO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", receiveDate=" + receiveDate +
                ", baseOrderId=" + baseOrderId +
                ", baseOrderType=" + baseOrderType +
                ", baseOrderCode='" + baseOrderCode + '\'' +
                ", baseOrderDate=" + baseOrderDate +
                ", requestUnitId=" + requestUnitId +
                ", requestUnitCode='" + requestUnitCode + '\'' +
                ", requestUnitName='" + requestUnitName + '\'' +
                ", distrType=" + distrType +
                ", distrTypeName='" + distrTypeName + '\'' +
                ", receiverId=" + receiverId +
                ", receiverCode='" + receiverCode + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", secondReceiverId=" + secondReceiverId +
                ", secondReceiverCode='" + secondReceiverCode + '\'' +
                ", secondReceiverName='" + secondReceiverName + '\'' +
                ", quantityTotal=" + quantityTotal +
                ", varietiesQuantity=" + varietiesQuantity +
                ", status=" + status +
                ", statusName='" + statusName + '\'' +
                ", remark='" + remark + '\'' +
                ", distrReturnReceiveDetailVOS=" + distrReturnReceiveDetailVOS +
                ", franchisedStoreFlag=" + franchisedStoreFlag +
                '}';
    }
}