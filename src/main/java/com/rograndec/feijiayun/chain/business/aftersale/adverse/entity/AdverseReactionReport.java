package com.rograndec.feijiayun.chain.business.aftersale.adverse.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
 
/**
 * 
 * saas_adverse_reaction_report
 * 
 * 
 * @author dudy
 * 
 * 2017-10-16
 */
public class AdverseReactionReport implements Serializable {
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
     * 单据类型（6305）
     */
    @ApiModelProperty(value = "单据类型（6306）")
    private Integer orderType;

    /**
     * 编码
     */
    @ApiModelProperty(value = "单号")
    private String code;

    /**
     * 报告日期
     */
    @ApiModelProperty(value = "报告日期")
    private Date reportDate;

    /**
     * 是否首次报告（0-首次报告；1-跟踪报告）
     */
    @ApiModelProperty(value = "是否首次报告（0-首次报告；1-跟踪报告）")
    private Integer firstReport;

    /**
     * 报告类型（0-新的；1-严重；2-一般）
     */
    @ApiModelProperty(value = "报告类型（0-新的；1-严重；2-一般）")
    private Integer reportType;

    /**
     * 报告单位类型（0-医疗机构；1-生产企业；2-经营企业；3-个人；4-其它）
     */
    @ApiModelProperty(value = "报告单位类型（0-医疗机构；1-生产企业；2-经营企业；3-个人；4-其它）")
    private Integer reportUnitType;


    @ApiModelProperty(value = "编码")
    private String reportCode;

    /**
     * 患者姓名
     */
    @ApiModelProperty(value = "患者姓名")
    private String patientName;

    /**
     * 性别（0-男；1-女；2-其它）
     */
    @ApiModelProperty(value = "性别（0-男；1-女；2-其它）")
    private Integer sex;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    private Date birthday;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 民族ID
     */
    @ApiModelProperty(value = "民族ID")
    private Long nationId;



    /**
     * 体重（kg）
     */
    @ApiModelProperty(value = "体重（kg）")
    private BigDecimal weight;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String telephone;

    /**
     * 原患疾病
     */
    @ApiModelProperty(value = "原患疾病")
    private String primaryDisease;

    /**
     * 医院名称
     */
    @ApiModelProperty(value = "医院名称")
    private String hospitalName;

    /**
     * 病例号／门诊号
     */
    @ApiModelProperty(value = "病例号／门诊号")
    private String outpatientCode;

    /**
     * 既往药品不良反应（0-无；1-有；1-不详）
     */
    @ApiModelProperty(value = "既往药品不良反应（0-无；1-有；1-不详）")
    private Integer historyAdverseReaction;

    /**
     * 家族药品不良反应（0-无；1-有；1-不详）
     */
    @ApiModelProperty(value = "家族药品不良反应（0-无；1-有；1-不详）")
    private Integer familyAdverseReaction;

    /**
     * 相关重要信息（0-吸烟史；1-饮酒史；2-妊娠史；3-肝病史；4-肾病史；5-过敏史；6-其它）
     */
    @ApiModelProperty(value = "相关重要信息（0-吸烟史；1-饮酒史；2-妊娠史；3-肝病史；4-肾病史；5-过敏史；6-其它）")
    private Integer importantInfo;

    /**
     * 不良反应名称
     */
    @ApiModelProperty(value = "不良反应名称")
    private String adverseReactionName;

    /**
     * 不良反应时间
     */
    @ApiModelProperty(value = "不良反应时间")
    private Date adverseReactionTime;

    /**
     * 不良反应结果（0-痊愈；1-好转；2-未好转；3-不详；4-有后遗症；5-死亡）
     */
    @ApiModelProperty(value = "不良反应结果（0-痊愈；1-好转；2-未好转；3-不详；4-有后遗症；5-死亡）")
    private Integer adverseReactionResult;

    /**
     * 报告时间
     */
    @ApiModelProperty(value = "报告时间")
    private Date reportTime;

    /**
     * 后遗症表现
     */
    @ApiModelProperty(value = "后遗症表现")
    private String sequelaePerformance;

    /**
     * 直接死亡原因
     */
    @ApiModelProperty(value = "直接死亡原因")
    private String directDeathReason;

    /**
     * 死亡时间
     */
    @ApiModelProperty(value = "死亡时间")
    private Date deathTime;

    /**
     * 用药开始时间
     */
    @ApiModelProperty(value = "用药开始时间")
    private Date medicineStartTime;

    /**
     * 用药结束时间
     */
    @ApiModelProperty(value = "用药结束时间")
    private Date medicineStopTime;

    /**
     * 用药原因
     */
    @ApiModelProperty(value = "用药原因")
    private String medicineReason;

    /**
     * 停药或减量后，反应是否消失或减轻
     */
    @ApiModelProperty(value = "停药或减量后，反应是否消失或减轻")
    private String reactionReduce;

    /**
     * 再次使用可疑药品后是否再次出现同样反应
     */
    @ApiModelProperty(value = "再次使用可疑药品后是否再次出现同样反应")
    private String againSameReaction;

    /**
     * 对原患疾病的影响
     */
    @ApiModelProperty(value = "对原患疾病的影响")
    private String effectPrimaryDisease;

    /**
     * 报告人评价
     */
    @ApiModelProperty(value = "报告人评价")
    private String reportManEvaluate;

    /**
     * 报告人电话
     */
    @ApiModelProperty(value = "报告人电话")
    private String reportManPhone;

    /**
     * 报告人职业
     */
    @ApiModelProperty(value = "报告人职业")
    private String reportManVocation;

    /**
     * 报告人邮箱
     */
    @ApiModelProperty(value = "报告人邮箱")
    private String reportManEmail;

    /**
     * 报告人姓名
     */
    @ApiModelProperty(value = "报告人姓名")
    private String reportManName;

    /**
     * 报告单位评价
     */
    @ApiModelProperty(value = "报告单位评价")
    private String reportUnitEvaluate;

    /**
     * 报告单位名称
     */
    @ApiModelProperty(value = "报告单位名称")
    private String reportUnitName;

    /**
     * 报告单位联系人
     */
    @ApiModelProperty(value = "报告单位联系人")
    private String reportUnitMan;

    /**
     * 报告单位联系电话
     */
    @ApiModelProperty(value = "报告单位联系电话")
    private String reportUnitPhone;

    /**
     * 生产企业请填写信息系来源
     */
    @ApiModelProperty(value = "生产企业请填写信息系来源")
    private String infoSrc;

    /**
     * 不良反应过程描述（包括症状、体征、临床检验等）及处理情况（可附页）
     */
    @ApiModelProperty(value = "不良反应过程描述（包括症状、体征、临床检验等）及处理情况（可附页）")
    private String adverseReactionDesc;

    /**
     * 附件ID
     */
    @ApiModelProperty(value = "附件ID")
    private Long fileId;

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
     * saas_adverse_reaction_report
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
     * 单据类型（6305）
     * @return order_type 单据类型（6305）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（6305）
     * @param orderType 单据类型（6305）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 报告日期
     * @return report_date 报告日期
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     * 报告日期
     * @param reportDate 报告日期
     */
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    /**
     * 是否首次报告（0-首次报告；1-跟踪报告）
     * @return first_report 是否首次报告（0-首次报告；1-跟踪报告）
     */
    public Integer getFirstReport() {
        return firstReport;
    }

    /**
     * 是否首次报告（0-首次报告；1-跟踪报告）
     * @param firstReport 是否首次报告（0-首次报告；1-跟踪报告）
     */
    public void setFirstReport(Integer firstReport) {
        this.firstReport = firstReport;
    }

    /**
     * 报告类型（0-新的；1-严重；2-一般）
     * @return report_type 报告类型（0-新的；1-严重；2-一般）
     */
    public Integer getReportType() {
        return reportType;
    }

    /**
     * 报告类型（0-新的；1-严重；2-一般）
     * @param reportType 报告类型（0-新的；1-严重；2-一般）
     */
    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    /**
     * 报告单位类型（0-医疗机构；1-生产企业；2-经营企业；3-个人；4-其它）
     * @return report_unit_type 报告单位类型（0-医疗机构；1-生产企业；2-经营企业；3-个人；4-其它）
     */
    public Integer getReportUnitType() {
        return reportUnitType;
    }

    /**
     * 报告单位类型（0-医疗机构；1-生产企业；2-经营企业；3-个人；4-其它）
     * @param reportUnitType 报告单位类型（0-医疗机构；1-生产企业；2-经营企业；3-个人；4-其它）
     */
    public void setReportUnitType(Integer reportUnitType) {
        this.reportUnitType = reportUnitType;
    }

    /**
     * 编码
     * @return code 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 编码
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 患者姓名
     * @return patient_name 患者姓名
     */
    public String getPatientName() {
        return patientName;
    }

    /**
     * 患者姓名
     * @param patientName 患者姓名
     */
    public void setPatientName(String patientName) {
        this.patientName = patientName == null ? null : patientName.trim();
    }

    /**
     * 性别（0-男；1-女；2-其它）
     * @return sex 性别（0-男；1-女；2-其它）
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 性别（0-男；1-女；2-其它）
     * @param sex 性别（0-男；1-女；2-其它）
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 出生日期
     * @return birthday 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 出生日期
     * @param birthday 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 年龄
     * @return age 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 年龄
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 民族ID
     * @return nation_id 民族ID
     */
    public Long getNationId() {
        return nationId;
    }

    /**
     * 民族ID
     * @param nationId 民族ID
     */
    public void setNationId(Long nationId) {
        this.nationId = nationId;
    }

    /**
     * 体重（kg）
     * @return weight 体重（kg）
     */
    public BigDecimal getWeight() {
        return weight;
    }

    /**
     * 体重（kg）
     * @param weight 体重（kg）
     */
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    /**
     * 联系电话
     * @return telephone 联系电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 联系电话
     * @param telephone 联系电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * 原患疾病
     * @return primary_disease 原患疾病
     */
    public String getPrimaryDisease() {
        return primaryDisease;
    }

    /**
     * 原患疾病
     * @param primaryDisease 原患疾病
     */
    public void setPrimaryDisease(String primaryDisease) {
        this.primaryDisease = primaryDisease == null ? null : primaryDisease.trim();
    }

    /**
     * 医院名称
     * @return hospital_name 医院名称
     */
    public String getHospitalName() {
        return hospitalName;
    }

    /**
     * 医院名称
     * @param hospitalName 医院名称
     */
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName == null ? null : hospitalName.trim();
    }

    /**
     * 病例号／门诊号
     * @return outpatient_code 病例号／门诊号
     */
    public String getOutpatientCode() {
        return outpatientCode;
    }

    /**
     * 病例号／门诊号
     * @param outpatientCode 病例号／门诊号
     */
    public void setOutpatientCode(String outpatientCode) {
        this.outpatientCode = outpatientCode == null ? null : outpatientCode.trim();
    }

    /**
     * 既往药品不良反应（0-无；1-有；1-不详）
     * @return history_adverse_reaction 既往药品不良反应（0-无；1-有；1-不详）
     */
    public Integer getHistoryAdverseReaction() {
        return historyAdverseReaction;
    }

    /**
     * 既往药品不良反应（0-无；1-有；1-不详）
     * @param historyAdverseReaction 既往药品不良反应（0-无；1-有；1-不详）
     */
    public void setHistoryAdverseReaction(Integer historyAdverseReaction) {
        this.historyAdverseReaction = historyAdverseReaction;
    }

    /**
     * 家族药品不良反应（0-无；1-有；1-不详）
     * @return family_adverse_reaction 家族药品不良反应（0-无；1-有；1-不详）
     */
    public Integer getFamilyAdverseReaction() {
        return familyAdverseReaction;
    }

    /**
     * 家族药品不良反应（0-无；1-有；1-不详）
     * @param familyAdverseReaction 家族药品不良反应（0-无；1-有；1-不详）
     */
    public void setFamilyAdverseReaction(Integer familyAdverseReaction) {
        this.familyAdverseReaction = familyAdverseReaction;
    }

    /**
     * 相关重要信息（0-吸烟史；1-饮酒史；2-妊娠史；3-肝病史；4-肾病史；5-过敏史；6-其它）
     * @return important_info 相关重要信息（0-吸烟史；1-饮酒史；2-妊娠史；3-肝病史；4-肾病史；5-过敏史；6-其它）
     */
    public Integer getImportantInfo() {
        return importantInfo;
    }

    /**
     * 相关重要信息（0-吸烟史；1-饮酒史；2-妊娠史；3-肝病史；4-肾病史；5-过敏史；6-其它）
     * @param importantInfo 相关重要信息（0-吸烟史；1-饮酒史；2-妊娠史；3-肝病史；4-肾病史；5-过敏史；6-其它）
     */
    public void setImportantInfo(Integer importantInfo) {
        this.importantInfo = importantInfo;
    }

    /**
     * 不良反应名称
     * @return adverse_reaction_name 不良反应名称
     */
    public String getAdverseReactionName() {
        return adverseReactionName;
    }

    /**
     * 不良反应名称
     * @param adverseReactionName 不良反应名称
     */
    public void setAdverseReactionName(String adverseReactionName) {
        this.adverseReactionName = adverseReactionName == null ? null : adverseReactionName.trim();
    }

    /**
     * 不良反应时间
     * @return adverse_reaction_time 不良反应时间
     */
    public Date getAdverseReactionTime() {
        return adverseReactionTime;
    }

    /**
     * 不良反应时间
     * @param adverseReactionTime 不良反应时间
     */
    public void setAdverseReactionTime(Date adverseReactionTime) {
        this.adverseReactionTime = adverseReactionTime;
    }

    /**
     * 不良反应结果（0-痊愈；1-好转；2-未好转；3-不详；4-有后遗症；5-死亡）
     * @return adverse_reaction_result 不良反应结果（0-痊愈；1-好转；2-未好转；3-不详；4-有后遗症；5-死亡）
     */
    public Integer getAdverseReactionResult() {
        return adverseReactionResult;
    }

    /**
     * 不良反应结果（0-痊愈；1-好转；2-未好转；3-不详；4-有后遗症；5-死亡）
     * @param adverseReactionResult 不良反应结果（0-痊愈；1-好转；2-未好转；3-不详；4-有后遗症；5-死亡）
     */
    public void setAdverseReactionResult(Integer adverseReactionResult) {
        this.adverseReactionResult = adverseReactionResult;
    }

    /**
     * 报告时间
     * @return report_time 报告时间
     */
    public Date getReportTime() {
        return reportTime;
    }

    /**
     * 报告时间
     * @param reportTime 报告时间
     */
    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    /**
     * 后遗症表现
     * @return sequelae_performance 后遗症表现
     */
    public String getSequelaePerformance() {
        return sequelaePerformance;
    }

    /**
     * 后遗症表现
     * @param sequelaePerformance 后遗症表现
     */
    public void setSequelaePerformance(String sequelaePerformance) {
        this.sequelaePerformance = sequelaePerformance == null ? null : sequelaePerformance.trim();
    }

    /**
     * 直接死亡原因
     * @return direct_death_reason 直接死亡原因
     */
    public String getDirectDeathReason() {
        return directDeathReason;
    }

    /**
     * 直接死亡原因
     * @param directDeathReason 直接死亡原因
     */
    public void setDirectDeathReason(String directDeathReason) {
        this.directDeathReason = directDeathReason == null ? null : directDeathReason.trim();
    }

    /**
     * 死亡时间
     * @return death_time 死亡时间
     */
    public Date getDeathTime() {
        return deathTime;
    }

    /**
     * 死亡时间
     * @param deathTime 死亡时间
     */
    public void setDeathTime(Date deathTime) {
        this.deathTime = deathTime;
    }

    /**
     * 用药开始时间
     * @return medicine_start_time 用药开始时间
     */
    public Date getMedicineStartTime() {
        return medicineStartTime;
    }

    /**
     * 用药开始时间
     * @param medicineStartTime 用药开始时间
     */
    public void setMedicineStartTime(Date medicineStartTime) {
        this.medicineStartTime = medicineStartTime;
    }

    /**
     * 用药结束时间
     * @return medicine_stop_time 用药结束时间
     */
    public Date getMedicineStopTime() {
        return medicineStopTime;
    }

    /**
     * 用药结束时间
     * @param medicineStopTime 用药结束时间
     */
    public void setMedicineStopTime(Date medicineStopTime) {
        this.medicineStopTime = medicineStopTime;
    }

    /**
     * 用药原因
     * @return medicine_reason 用药原因
     */
    public String getMedicineReason() {
        return medicineReason;
    }

    /**
     * 用药原因
     * @param medicineReason 用药原因
     */
    public void setMedicineReason(String medicineReason) {
        this.medicineReason = medicineReason == null ? null : medicineReason.trim();
    }

    /**
     * 停药或减量后，反应是否消失或减轻
     * @return reaction_reduce 停药或减量后，反应是否消失或减轻
     */
    public String getReactionReduce() {
        return reactionReduce;
    }

    /**
     * 停药或减量后，反应是否消失或减轻
     * @param reactionReduce 停药或减量后，反应是否消失或减轻
     */
    public void setReactionReduce(String reactionReduce) {
        this.reactionReduce = reactionReduce == null ? null : reactionReduce.trim();
    }

    /**
     * 再次使用可疑药品后是否再次出现同样反应
     * @return again_same_reaction 再次使用可疑药品后是否再次出现同样反应
     */
    public String getAgainSameReaction() {
        return againSameReaction;
    }

    /**
     * 再次使用可疑药品后是否再次出现同样反应
     * @param againSameReaction 再次使用可疑药品后是否再次出现同样反应
     */
    public void setAgainSameReaction(String againSameReaction) {
        this.againSameReaction = againSameReaction == null ? null : againSameReaction.trim();
    }

    /**
     * 对原患疾病的影响
     * @return effect_primary_disease 对原患疾病的影响
     */
    public String getEffectPrimaryDisease() {
        return effectPrimaryDisease;
    }

    /**
     * 对原患疾病的影响
     * @param effectPrimaryDisease 对原患疾病的影响
     */
    public void setEffectPrimaryDisease(String effectPrimaryDisease) {
        this.effectPrimaryDisease = effectPrimaryDisease == null ? null : effectPrimaryDisease.trim();
    }

    /**
     * 报告人评价
     * @return report_man_evaluate 报告人评价
     */
    public String getReportManEvaluate() {
        return reportManEvaluate;
    }

    /**
     * 报告人评价
     * @param reportManEvaluate 报告人评价
     */
    public void setReportManEvaluate(String reportManEvaluate) {
        this.reportManEvaluate = reportManEvaluate == null ? null : reportManEvaluate.trim();
    }

    /**
     * 报告人电话
     * @return report_man_phone 报告人电话
     */
    public String getReportManPhone() {
        return reportManPhone;
    }

    /**
     * 报告人电话
     * @param reportManPhone 报告人电话
     */
    public void setReportManPhone(String reportManPhone) {
        this.reportManPhone = reportManPhone == null ? null : reportManPhone.trim();
    }

    /**
     * 报告人职业
     * @return report_man_vocation 报告人职业
     */
    public String getReportManVocation() {
        return reportManVocation;
    }

    /**
     * 报告人职业
     * @param reportManVocation 报告人职业
     */
    public void setReportManVocation(String reportManVocation) {
        this.reportManVocation = reportManVocation == null ? null : reportManVocation.trim();
    }

    /**
     * 报告人邮箱
     * @return report_man_email 报告人邮箱
     */
    public String getReportManEmail() {
        return reportManEmail;
    }

    /**
     * 报告人邮箱
     * @param reportManEmail 报告人邮箱
     */
    public void setReportManEmail(String reportManEmail) {
        this.reportManEmail = reportManEmail == null ? null : reportManEmail.trim();
    }

    /**
     * 报告人姓名
     * @return report_man_name 报告人姓名
     */
    public String getReportManName() {
        return reportManName;
    }

    /**
     * 报告人姓名
     * @param reportManName 报告人姓名
     */
    public void setReportManName(String reportManName) {
        this.reportManName = reportManName == null ? null : reportManName.trim();
    }

    /**
     * 报告单位评价
     * @return report_unit_evaluate 报告单位评价
     */
    public String getReportUnitEvaluate() {
        return reportUnitEvaluate;
    }

    /**
     * 报告单位评价
     * @param reportUnitEvaluate 报告单位评价
     */
    public void setReportUnitEvaluate(String reportUnitEvaluate) {
        this.reportUnitEvaluate = reportUnitEvaluate == null ? null : reportUnitEvaluate.trim();
    }

    /**
     * 报告单位名称
     * @return report_unit_name 报告单位名称
     */
    public String getReportUnitName() {
        return reportUnitName;
    }

    /**
     * 报告单位名称
     * @param reportUnitName 报告单位名称
     */
    public void setReportUnitName(String reportUnitName) {
        this.reportUnitName = reportUnitName == null ? null : reportUnitName.trim();
    }

    /**
     * 报告单位联系人
     * @return report_unit_man 报告单位联系人
     */
    public String getReportUnitMan() {
        return reportUnitMan;
    }

    /**
     * 报告单位联系人
     * @param reportUnitMan 报告单位联系人
     */
    public void setReportUnitMan(String reportUnitMan) {
        this.reportUnitMan = reportUnitMan == null ? null : reportUnitMan.trim();
    }

    /**
     * 报告单位联系电话
     * @return report_unit_phone 报告单位联系电话
     */
    public String getReportUnitPhone() {
        return reportUnitPhone;
    }

    /**
     * 报告单位联系电话
     * @param reportUnitPhone 报告单位联系电话
     */
    public void setReportUnitPhone(String reportUnitPhone) {
        this.reportUnitPhone = reportUnitPhone == null ? null : reportUnitPhone.trim();
    }

    /**
     * 生产企业请填写信息系来源
     * @return info_src 生产企业请填写信息系来源
     */
    public String getInfoSrc() {
        return infoSrc;
    }

    /**
     * 生产企业请填写信息系来源
     * @param infoSrc 生产企业请填写信息系来源
     */
    public void setInfoSrc(String infoSrc) {
        this.infoSrc = infoSrc == null ? null : infoSrc.trim();
    }

    /**
     * 不良反应过程描述（包括症状、体征、临床检验等）及处理情况（可附页）
     * @return adverse_reaction_desc 不良反应过程描述（包括症状、体征、临床检验等）及处理情况（可附页）
     */
    public String getAdverseReactionDesc() {
        return adverseReactionDesc;
    }

    /**
     * 不良反应过程描述（包括症状、体征、临床检验等）及处理情况（可附页）
     * @param adverseReactionDesc 不良反应过程描述（包括症状、体征、临床检验等）及处理情况（可附页）
     */
    public void setAdverseReactionDesc(String adverseReactionDesc) {
        this.adverseReactionDesc = adverseReactionDesc == null ? null : adverseReactionDesc.trim();
    }

    /**
     * 附件ID
     * @return file_id 附件ID
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * 附件ID
     * @param fileId 附件ID
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
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

    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
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
        sb.append(", reportDate=").append(reportDate);
        sb.append(", firstReport=").append(firstReport);
        sb.append(", reportType=").append(reportType);
        sb.append(", reportUnitType=").append(reportUnitType);
        sb.append(", code=").append(code);
        sb.append(", reportCode=").append(reportCode);
        sb.append(", patientName=").append(patientName);
        sb.append(", sex=").append(sex);
        sb.append(", birthday=").append(birthday);
        sb.append(", age=").append(age);
        sb.append(", nationId=").append(nationId);
        sb.append(", weight=").append(weight);
        sb.append(", telephone=").append(telephone);
        sb.append(", primaryDisease=").append(primaryDisease);
        sb.append(", hospitalName=").append(hospitalName);
        sb.append(", outpatientCode=").append(outpatientCode);
        sb.append(", historyAdverseReaction=").append(historyAdverseReaction);
        sb.append(", familyAdverseReaction=").append(familyAdverseReaction);
        sb.append(", importantInfo=").append(importantInfo);
        sb.append(", adverseReactionName=").append(adverseReactionName);
        sb.append(", adverseReactionTime=").append(adverseReactionTime);
        sb.append(", adverseReactionResult=").append(adverseReactionResult);
        sb.append(", reportTime=").append(reportTime);
        sb.append(", sequelaePerformance=").append(sequelaePerformance);
        sb.append(", directDeathReason=").append(directDeathReason);
        sb.append(", deathTime=").append(deathTime);
        sb.append(", medicineStartTime=").append(medicineStartTime);
        sb.append(", medicineStopTime=").append(medicineStopTime);
        sb.append(", medicineReason=").append(medicineReason);
        sb.append(", reactionReduce=").append(reactionReduce);
        sb.append(", againSameReaction=").append(againSameReaction);
        sb.append(", effectPrimaryDisease=").append(effectPrimaryDisease);
        sb.append(", reportManEvaluate=").append(reportManEvaluate);
        sb.append(", reportManPhone=").append(reportManPhone);
        sb.append(", reportManVocation=").append(reportManVocation);
        sb.append(", reportManEmail=").append(reportManEmail);
        sb.append(", reportManName=").append(reportManName);
        sb.append(", reportUnitEvaluate=").append(reportUnitEvaluate);
        sb.append(", reportUnitName=").append(reportUnitName);
        sb.append(", reportUnitMan=").append(reportUnitMan);
        sb.append(", reportUnitPhone=").append(reportUnitPhone);
        sb.append(", infoSrc=").append(infoSrc);
        sb.append(", adverseReactionDesc=").append(adverseReactionDesc);
        sb.append(", fileId=").append(fileId);
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