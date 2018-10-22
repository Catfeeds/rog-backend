package com.rograndec.feijiayun.chain.business.aftersale.recall.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_recall_plan
 * 
 * 
 * @author Asze
 * 
 * 2017-10-16
 */
public class RecallPlanVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 单据类型（6301）
     */
    @ApiModelProperty(value = "单据类型（6301）")
    private Integer orderType;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 通知日期
     */
    @ApiModelProperty(value = "通知日期")
    private Date planDate;

    /**
     * 通知人ID
     */
    @ApiModelProperty(value = "通知人ID")
    private Long planManId;

    /**
     * 通知人编码
     */
    @ApiModelProperty(value = "通知人编码")
    private String planManCode;

    /**
     * 通知人名称
     */
    @ApiModelProperty(value = "通知人名称")
    private String planManName;

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
     * 召回计划细单集合
     */
    @ApiModelProperty(value = "召回计划细单集合")
    private List<RecallPlanDetailVO> recallPlanDetailVOS;

    @ApiModelProperty(value = "能否修改/删除标志,true可修改/删除,false不可修改/删除")
    private Boolean modifyFlag;
    /**
     * saas_recall_plan
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
     * 单据类型（6301）
     * @return order_type 单据类型（6301）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（6301）
     * @param orderType 单据类型（6301）
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
     * 通知日期
     * @return plan_date 通知日期
     */
    public Date getPlanDate() {
        return planDate;
    }

    /**
     * 通知日期
     * @param planDate 通知日期
     */
    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    /**
     * 通知人ID
     * @return plan_man_id 通知人ID
     */
    public Long getPlanManId() {
        return planManId;
    }

    /**
     * 通知人ID
     * @param planManId 通知人ID
     */
    public void setPlanManId(Long planManId) {
        this.planManId = planManId;
    }

    /**
     * 通知人编码
     * @return plan_man_code 通知人编码
     */
    public String getPlanManCode() {
        return planManCode;
    }

    /**
     * 通知人编码
     * @param planManCode 通知人编码
     */
    public void setPlanManCode(String planManCode) {
        this.planManCode = planManCode == null ? null : planManCode.trim();
    }

    /**
     * 通知人名称
     * @return plan_man_name 通知人名称
     */
    public String getPlanManName() {
        return planManName;
    }

    /**
     * 通知人名称
     * @param planManName 通知人名称
     */
    public void setPlanManName(String planManName) {
        this.planManName = planManName == null ? null : planManName.trim();
    }

    /**
     * 召回单位
     * @return recall_company 召回单位
     */
    public String getRecallCompany() {
        return recallCompany;
    }

    /**
     * 召回单位
     * @param recallCompany 召回单位
     */
    public void setRecallCompany(String recallCompany) {
        this.recallCompany = recallCompany == null ? null : recallCompany.trim();
    }

    /**
     * 召回责任人
     * @return recall_man 召回责任人
     */
    public String getRecallMan() {
        return recallMan;
    }

    /**
     * 召回责任人
     * @param recallMan 召回责任人
     */
    public void setRecallMan(String recallMan) {
        this.recallMan = recallMan == null ? null : recallMan.trim();
    }

    /**
     * 召回责任人电话
     * @return recall_man_phone 召回责任人电话
     */
    public String getRecallManPhone() {
        return recallManPhone;
    }

    /**
     * 召回责任人电话
     * @param recallManPhone 召回责任人电话
     */
    public void setRecallManPhone(String recallManPhone) {
        this.recallManPhone = recallManPhone == null ? null : recallManPhone.trim();
    }

    /**
     * 召回级别（0-一级召回；1-二级召回；2-三级召回）
     * @return recall_level 召回级别（0-一级召回；1-二级召回；2-三级召回）
     */
    public Integer getRecallLevel() {
        return recallLevel;
    }

    /**
     * 召回级别（0-一级召回；1-二级召回；2-三级召回）
     * @param recallLevel 召回级别（0-一级召回；1-二级召回；2-三级召回）
     */
    public void setRecallLevel(Integer recallLevel) {
        this.recallLevel = recallLevel;
    }

    /**
     * 召回时限
     * @return recall_time_limit 召回时限
     */
    public Integer getRecallTimeLimit() {
        return recallTimeLimit;
    }

    /**
     * 召回时限
     * @param recallTimeLimit 召回时限
     */
    public void setRecallTimeLimit(Integer recallTimeLimit) {
        this.recallTimeLimit = recallTimeLimit;
    }

    /**
     * 召回原因（0-药品留样观察中发现质量不合格情况；1-用户（接种者、医生、经销商）来信、来人投诉药品质量情况，经调查属实；2-药品质量监督管理部门抽检通报有质量问题的药品；3-用户反映有未知的药品不良反应；4-国家已通报淘汰的药品；5-其它认为需要召回的药品；6-药品包装标签说明书内容或者设计印制存在缺陷，影响用药安全的；7-执行国家有关的药品召回规定）
     * @return recall_reason 召回原因（0-药品留样观察中发现质量不合格情况；1-用户（接种者、医生、经销商）来信、来人投诉药品质量情况，经调查属实；2-药品质量监督管理部门抽检通报有质量问题的药品；3-用户反映有未知的药品不良反应；4-国家已通报淘汰的药品；5-其它认为需要召回的药品；6-药品包装标签说明书内容或者设计印制存在缺陷，影响用药安全的；7-执行国家有关的药品召回规定）
     */
    public Integer getRecallReason() {
        return recallReason;
    }

    /**
     * 召回原因（0-药品留样观察中发现质量不合格情况；1-用户（接种者、医生、经销商）来信、来人投诉药品质量情况，经调查属实；2-药品质量监督管理部门抽检通报有质量问题的药品；3-用户反映有未知的药品不良反应；4-国家已通报淘汰的药品；5-其它认为需要召回的药品；6-药品包装标签说明书内容或者设计印制存在缺陷，影响用药安全的；7-执行国家有关的药品召回规定）
     * @param recallReason 召回原因（0-药品留样观察中发现质量不合格情况；1-用户（接种者、医生、经销商）来信、来人投诉药品质量情况，经调查属实；2-药品质量监督管理部门抽检通报有质量问题的药品；3-用户反映有未知的药品不良反应；4-国家已通报淘汰的药品；5-其它认为需要召回的药品；6-药品包装标签说明书内容或者设计印制存在缺陷，影响用药安全的；7-执行国家有关的药品召回规定）
     */
    public void setRecallReason(Integer recallReason) {
        this.recallReason = recallReason;
    }

    /**
     * 召回存放（0-退回公司；1-异地封存）
     * @return recall_deposit 召回存放（0-退回公司；1-异地封存）
     */
    public Integer getRecallDeposit() {
        return recallDeposit;
    }

    /**
     * 召回存放（0-退回公司；1-异地封存）
     * @param recallDeposit 召回存放（0-退回公司；1-异地封存）
     */
    public void setRecallDeposit(Integer recallDeposit) {
        this.recallDeposit = recallDeposit;
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

    public List<RecallPlanDetailVO> getRecallPlanDetailVOS() {
        return recallPlanDetailVOS;
    }

    public void setRecallPlanDetailVOS(List<RecallPlanDetailVO> recallPlanDetailVOS) {
        this.recallPlanDetailVOS = recallPlanDetailVOS;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRecallLevelName() {
        return recallLevelName;
    }

    public void setRecallLevelName(String recallLevelName) {
        this.recallLevelName = recallLevelName;
    }

    public String getRecallReasonName() {
        return recallReasonName;
    }

    public void setRecallReasonName(String recallReasonName) {
        this.recallReasonName = recallReasonName;
    }

    public String getRecallDepositName() {
        return recallDepositName;
    }

    public void setRecallDepositName(String recallDepositName) {
        this.recallDepositName = recallDepositName;
    }

    public Boolean getModifyFlag() {
        return modifyFlag;
    }

    public void setModifyFlag(Boolean modifyFlag) {
        this.modifyFlag = modifyFlag;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "RecallPlanVO{" +
                "id=" + id +
                ", orderType=" + orderType +
                ", code='" + code + '\'' +
                ", planDate=" + planDate +
                ", planManId=" + planManId +
                ", planManCode='" + planManCode + '\'' +
                ", planManName='" + planManName + '\'' +
                ", recallCompany='" + recallCompany + '\'' +
                ", recallMan='" + recallMan + '\'' +
                ", recallManPhone='" + recallManPhone + '\'' +
                ", recallLevel=" + recallLevel +
                ", recallLevelName='" + recallLevelName + '\'' +
                ", recallTimeLimit=" + recallTimeLimit +
                ", recallReason=" + recallReason +
                ", recallReasonName='" + recallReasonName + '\'' +
                ", recallDeposit=" + recallDeposit +
                ", recallDepositName='" + recallDepositName + '\'' +
                ", varietiesQuantity=" + varietiesQuantity +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", recallPlanDetailVOS='" + recallPlanDetailVOS + '\'' +
                ", modifyFlag='" + modifyFlag + '\'' +
                '}';
    }
}