package com.rograndec.feijiayun.chain.business.aftersale.recall.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_recall_record
 * 
 * 
 * @author Asze
 * 
 * 2017-10-16
 */
public class RecallRecordVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 单据类型（6302）
     */
    @ApiModelProperty(value = "单据类型（6302）")
    private Integer orderType;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 基础单据ID(召回通知ID)
     */
    @ApiModelProperty(value = "基础单据ID(召回通知ID)")
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
     * 召回日期
     */
    @ApiModelProperty(value = "召回日期")
    private Date recallDate;

    /**
     * 退回单位ID
     */
    @ApiModelProperty(value = "退回单位ID")
    private Long returnUnitId;

    /**
     * 退回单位编码
     */
    @ApiModelProperty(value = "退回单位编码")
    private String returnUnitCode;

    /**
     * 退回单位名称
     */
    @ApiModelProperty(value = "退回单位名称")
    private String returnUnitName;

    /**
     * 召回人员ID
     */
    @ApiModelProperty(value = "召回人员ID")
    private Long recallManId;

    /**
     * 召回人员编码
     */
    @ApiModelProperty(value = "召回人员编码")
    private String recallManCode;

    /**
     * 召回人员名称
     */
    @ApiModelProperty(value = "召回人员名称")
    private String recallManName;

    /**
     * 召回处理（0-更换包装后重新销售；1-退回供货单位；2-销毁）
     */
    @ApiModelProperty(value = "召回处理（0-更换包装后重新销售；1-退回供货单位；2-销毁）")
    private Integer handleMeasures;

    /**
     * 召回处理名
     */
    @ApiModelProperty(value = "召回处理名")
    private String handleMeasuresName;

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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 召回记录明细集合
     */
    @ApiModelProperty(value = "召回记录明细集合")
    private List<RecallRecordDetailVO> recallRecordDetailVOList;
    /**
     * saas_recall_record
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
     * 单据类型（6302）
     * @return order_type 单据类型（6302）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（6302）
     * @param orderType 单据类型（6302）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 单据编码
     * @return code 单据编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 单据编码
     * @param code 单据编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 召回日期
     * @return recall_date 召回日期
     */
    public Date getRecallDate() {
        return recallDate;
    }

    /**
     * 召回日期
     * @param recallDate 召回日期
     */
    public void setRecallDate(Date recallDate) {
        this.recallDate = recallDate;
    }

    /**
     * 召回人员ID
     * @return recall_man_id 召回人员ID
     */
    public Long getRecallManId() {
        return recallManId;
    }

    /**
     * 召回人员ID
     * @param recallManId 召回人员ID
     */
    public void setRecallManId(Long recallManId) {
        this.recallManId = recallManId;
    }

    /**
     * 召回人员编码
     * @return recall_man_code 召回人员编码
     */
    public String getRecallManCode() {
        return recallManCode;
    }

    /**
     * 召回人员编码
     * @param recallManCode 召回人员编码
     */
    public void setRecallManCode(String recallManCode) {
        this.recallManCode = recallManCode == null ? null : recallManCode.trim();
    }

    /**
     * 召回人员名称
     * @return recall_man_name 召回人员名称
     */
    public String getRecallManName() {
        return recallManName;
    }

    /**
     * 召回人员名称
     * @param recallManName 召回人员名称
     */
    public void setRecallManName(String recallManName) {
        this.recallManName = recallManName == null ? null : recallManName.trim();
    }

    /**
     * 召回处理（0-更换包装后重新销售；1-退回供货单位；2-销毁）
     * @return handle_measures 召回处理（0-更换包装后重新销售；1-退回供货单位；2-销毁）
     */
    public Integer getHandleMeasures() {
        return handleMeasures;
    }

    /**
     * 召回处理（0-更换包装后重新销售；1-退回供货单位；2-销毁）
     * @param handleMeasures 召回处理（0-更换包装后重新销售；1-退回供货单位；2-销毁）
     */
    public void setHandleMeasures(Integer handleMeasures) {
        this.handleMeasures = handleMeasures;
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

    public String getHandleMeasuresName() {
        return handleMeasuresName;
    }

    public void setHandleMeasuresName(String handleMeasuresName) {
        this.handleMeasuresName = handleMeasuresName;
    }

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
    }

    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    public List<RecallRecordDetailVO> getRecallRecordDetailVOList() {
        return recallRecordDetailVOList;
    }

    public void setRecallRecordDetailVOList(List<RecallRecordDetailVO> recallRecordDetailVOList) {
        this.recallRecordDetailVOList = recallRecordDetailVOList;
    }

    public Long getReturnUnitId() {
        return returnUnitId;
    }

    public void setReturnUnitId(Long returnUnitId) {
        this.returnUnitId = returnUnitId;
    }

    public String getReturnUnitCode() {
        return returnUnitCode;
    }

    public void setReturnUnitCode(String returnUnitCode) {
        this.returnUnitCode = returnUnitCode;
    }

    public String getReturnUnitName() {
        return returnUnitName;
    }

    public void setReturnUnitName(String returnUnitName) {
        this.returnUnitName = returnUnitName;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "RecallRecordVO{" +
                "id=" + id +
                ", orderType=" + orderType +
                ", code='" + code + '\'' +
                ", baseOrderId=" + baseOrderId +
                ", baseOrderType=" + baseOrderType +
                ", baseOrderCode='" + baseOrderCode + '\'' +
                ", baseOrderDate=" + baseOrderDate +
                ", recallDate=" + recallDate +
                ", returnUnitId=" + returnUnitId +
                ", returnUnitCode='" + returnUnitCode + '\'' +
                ", returnUnitName='" + returnUnitName + '\'' +
                ", recallManId=" + recallManId +
                ", recallManCode='" + recallManCode + '\'' +
                ", recallManName='" + recallManName + '\'' +
                ", handleMeasures=" + handleMeasures +
                ", handleMeasuresName='" + handleMeasuresName + '\'' +
                ", quantityTotal=" + quantityTotal +
                ", varietiesQuantity=" + varietiesQuantity +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", recallRecordDetailVOList=" + recallRecordDetailVOList +
                '}';
    }
}