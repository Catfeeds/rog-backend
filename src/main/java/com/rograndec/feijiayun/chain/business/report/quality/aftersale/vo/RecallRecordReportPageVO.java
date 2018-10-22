package com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo;

import com.rograndec.feijiayun.chain.business.aftersale.recall.constant.HandleMeasuresEnum;
import com.rograndec.feijiayun.chain.business.aftersale.recall.constant.RecallDepositEnum;
import com.rograndec.feijiayun.chain.business.aftersale.recall.constant.RecallLevelEnum;
import com.rograndec.feijiayun.chain.business.aftersale.recall.constant.RecallReasonEnum;
import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsDetailVO;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class RecallRecordReportPageVO extends BaseGoodsDetailVO {


    @ApiModelProperty(value = "主键ID")
    private Long id;


    @ApiModelProperty("组织机构编码")
    private String enterpriseCode;

    @ApiModelProperty("组织机构名称")
    private String enterpriseName;


    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 通知日期
     */
    @ApiModelProperty(value = "日期")
    private Date planDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    /**
     * 召回单位
     */
    @ApiModelProperty(value = "召回单位")
    private String recallCompany;

    /**
     * 召回责任人
     */
    @ApiModelProperty(value = "召回责任人")
    private String recallMan;

    /**
     * 召回责任人电话
     */
    @ApiModelProperty(value = "召回责任人电话")
    private String recallManPhone;


    /**
     * 召回级别（0-一级召回；1-二级召回；2-三级召回）
     */
    @ApiModelProperty(value = "召回级别（0-一级召回；1-二级召回；2-三级召回）")
    private Integer recallLevel;

    /**
     * 召回级别名
     */
    @ApiModelProperty(value = "召回级别名")
    private String recallLevelName;


    /**
     * 召回时限
     */
    @ApiModelProperty(value = "召回时限")
    private Integer recallTimeLimit;

    /**
     * 召回原因（0-药品留样观察中发现质量不合格情况；1-用户（接种者、医生、经销商）来信、来人投诉药品质量情况，经调查属实；2-药品质量监督管理部门抽检通报有质量问题的药品；3-用户反映有未知的药品不良反应；4-国家已通报淘汰的药品；5-其它认为需要召回的药品；6-药品包装标签说明书内容或者设计印制存在缺陷，影响用药安全的；7-执行国家有关的药品召回规定）
     */
    @ApiModelProperty(value = "召回原因（0-药品留样观察中发现质量不合格情况；1-用户（接种者、医生、经销商）来信、来人投诉药品质量情况，经调查属实；2-药品质量监督管理部门抽检通报有质量问题的药品；3-用户反映有未知的药品不良反应；4-国家已通报淘汰的药品；5-其它认为需要召回的药品；6-药品包装标签说明书内容或者设计印制存在缺陷，影响用药安全的；7-执行国家有关的药品召回规定）")
    private Integer recallReason;


    /**
     * 召回原因名
     */
    @ApiModelProperty(value = "召回原因名")
    private String recallReasonName;

    /**
     * 召回存放（0-退回公司；1-异地封存）
     */
    @ApiModelProperty(value = "召回存放（0-退回公司；1-异地封存）")
    private Integer recallDeposit;


    /**
     * 召回存放名
     */
    @ApiModelProperty(value = "召回存放名")
    private String recallDepositName;


    @ApiModelProperty(value = "召回处理（0-更换包装后重新销售；1-退回供货单位；2-销毁）")
    private Integer handleMeasures;


    @ApiModelProperty(value = "召回处理名称")
    private String handleMeasuresName;

    /**
     * 退回数量
     */
    @ApiModelProperty(value = "召回数量")
    private BigDecimal quantity;


    /**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    private Date validDate;


    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public String getRecallLevelName() {

        if (recallLevel == null) {
            return "";
        }

        return RecallLevelEnum.getName(recallLevel);
    }

    public void setRecallLevelName(String recallLevelName) {
        this.recallLevelName = recallLevelName;
    }

    public String getRecallReasonName() {

        if (recallReason == null) {
            return "";
        }

        return RecallReasonEnum.getName(recallReason);
    }

    public void setRecallReasonName(String recallReasonName) {
        this.recallReasonName = recallReasonName;
    }

    public String getRecallDepositName() {

        if (recallDeposit == null) {
            return "";
        }
        return RecallDepositEnum.getName(recallDeposit);
    }

    public void setRecallDepositName(String recallDepositName) {
        this.recallDepositName = recallDepositName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecallCompany() {
        return recallCompany;
    }

    public void setRecallCompany(String recallCompany) {
        this.recallCompany = recallCompany;
    }

    public String getRecallMan() {
        return recallMan;
    }

    public void setRecallMan(String recallMan) {
        this.recallMan = recallMan;
    }

    public String getRecallManPhone() {
        return recallManPhone;
    }

    public void setRecallManPhone(String recallManPhone) {
        this.recallManPhone = recallManPhone;
    }

    public Integer getRecallLevel() {
        return recallLevel;
    }

    public void setRecallLevel(Integer recallLevel) {
        this.recallLevel = recallLevel;
    }

    public Integer getRecallTimeLimit() {
        return recallTimeLimit;
    }

    public void setRecallTimeLimit(Integer recallTimeLimit) {
        this.recallTimeLimit = recallTimeLimit;
    }

    public Integer getRecallReason() {
        return recallReason;
    }

    public void setRecallReason(Integer recallReason) {
        this.recallReason = recallReason;
    }

    public Integer getRecallDeposit() {
        return recallDeposit;
    }

    public void setRecallDeposit(Integer recallDeposit) {
        this.recallDeposit = recallDeposit;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }


    public int getHandleMeasures() {
        return handleMeasures;
    }

    public void setHandleMeasures(int handleMeasures) {
        this.handleMeasures = handleMeasures;
    }

    public String getHandleMeasuresName() {

        if(handleMeasures == null){
            return "";
        }
        return HandleMeasuresEnum.getName(handleMeasures);
    }

    public void setHandleMeasuresName(String handleMeasuresName) {
        this.handleMeasuresName = handleMeasuresName;
    }
}
