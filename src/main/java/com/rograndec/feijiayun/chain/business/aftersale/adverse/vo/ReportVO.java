package com.rograndec.feijiayun.chain.business.aftersale.adverse.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ReportVO implements Serializable{

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


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

    /**
     * 编码
     */
    @ApiModelProperty(value = "单号编码(不需要传)")
    private String code;

    @ApiModelProperty(value = "编码")
    @NotNull(message = "编码不能为空")
    private String reportCode;

    /**
     * 患者姓名
     */
    @ApiModelProperty(value = "患者姓名（必传）")
    @NotNull(message = "患者姓名不能为空")
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

    @ApiModelProperty(value = "民族Name")
    private String nationName;
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
    @ApiModelProperty(value = "报告时间（必传）")
    @NotNull( message = "报告时间不能为空")
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

    @ApiModelProperty(value = "附件名称")
    private String fileName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    @ApiModelProperty(value = "怀疑药品列表")
    private List<ReportGoodsVO> doubtGoodsVOList;

    @ApiModelProperty(value = "并用药品列表")
    private List<ReportGoodsVO> togetherGoodsVOList;


    public String getReportCode() {
        return reportCode;
    }

    public void setReportCode(String reportCode) {
        this.reportCode = reportCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFirstReport() {
        return firstReport;
    }

    public void setFirstReport(Integer firstReport) {
        this.firstReport = firstReport;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public Integer getReportUnitType() {
        return reportUnitType;
    }

    public void setReportUnitType(Integer reportUnitType) {
        this.reportUnitType = reportUnitType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getNationId() {
        return nationId;
    }

    public void setNationId(Long nationId) {
        this.nationId = nationId;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPrimaryDisease() {
        return primaryDisease;
    }

    public void setPrimaryDisease(String primaryDisease) {
        this.primaryDisease = primaryDisease;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getOutpatientCode() {
        return outpatientCode;
    }

    public void setOutpatientCode(String outpatientCode) {
        this.outpatientCode = outpatientCode;
    }

    public Integer getHistoryAdverseReaction() {
        return historyAdverseReaction;
    }

    public void setHistoryAdverseReaction(Integer historyAdverseReaction) {
        this.historyAdverseReaction = historyAdverseReaction;
    }

    public Integer getFamilyAdverseReaction() {
        return familyAdverseReaction;
    }

    public void setFamilyAdverseReaction(Integer familyAdverseReaction) {
        this.familyAdverseReaction = familyAdverseReaction;
    }

    public Integer getImportantInfo() {
        return importantInfo;
    }

    public void setImportantInfo(Integer importantInfo) {
        this.importantInfo = importantInfo;
    }

    public String getAdverseReactionName() {
        return adverseReactionName;
    }

    public void setAdverseReactionName(String adverseReactionName) {
        this.adverseReactionName = adverseReactionName;
    }

    public Date getAdverseReactionTime() {
        return adverseReactionTime;
    }

    public void setAdverseReactionTime(Date adverseReactionTime) {
        this.adverseReactionTime = adverseReactionTime;
    }

    public Integer getAdverseReactionResult() {
        return adverseReactionResult;
    }

    public void setAdverseReactionResult(Integer adverseReactionResult) {
        this.adverseReactionResult = adverseReactionResult;
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getSequelaePerformance() {
        return sequelaePerformance;
    }

    public void setSequelaePerformance(String sequelaePerformance) {
        this.sequelaePerformance = sequelaePerformance;
    }

    public String getDirectDeathReason() {
        return directDeathReason;
    }

    public void setDirectDeathReason(String directDeathReason) {
        this.directDeathReason = directDeathReason;
    }

    public Date getDeathTime() {
        return deathTime;
    }

    public void setDeathTime(Date deathTime) {
        this.deathTime = deathTime;
    }

    public Date getMedicineStartTime() {
        return medicineStartTime;
    }

    public void setMedicineStartTime(Date medicineStartTime) {
        this.medicineStartTime = medicineStartTime;
    }

    public Date getMedicineStopTime() {
        return medicineStopTime;
    }

    public void setMedicineStopTime(Date medicineStopTime) {
        this.medicineStopTime = medicineStopTime;
    }

    public String getMedicineReason() {
        return medicineReason;
    }

    public void setMedicineReason(String medicineReason) {
        this.medicineReason = medicineReason;
    }

    public String getReactionReduce() {
        return reactionReduce;
    }

    public void setReactionReduce(String reactionReduce) {
        this.reactionReduce = reactionReduce;
    }

    public String getAgainSameReaction() {
        return againSameReaction;
    }

    public void setAgainSameReaction(String againSameReaction) {
        this.againSameReaction = againSameReaction;
    }

    public String getEffectPrimaryDisease() {
        return effectPrimaryDisease;
    }

    public void setEffectPrimaryDisease(String effectPrimaryDisease) {
        this.effectPrimaryDisease = effectPrimaryDisease;
    }

    public String getReportManEvaluate() {
        return reportManEvaluate;
    }

    public void setReportManEvaluate(String reportManEvaluate) {
        this.reportManEvaluate = reportManEvaluate;
    }

    public String getReportManPhone() {
        return reportManPhone;
    }

    public void setReportManPhone(String reportManPhone) {
        this.reportManPhone = reportManPhone;
    }

    public String getReportManVocation() {
        return reportManVocation;
    }

    public void setReportManVocation(String reportManVocation) {
        this.reportManVocation = reportManVocation;
    }

    public String getReportManEmail() {
        return reportManEmail;
    }

    public void setReportManEmail(String reportManEmail) {
        this.reportManEmail = reportManEmail;
    }

    public String getReportManName() {
        return reportManName;
    }

    public void setReportManName(String reportManName) {
        this.reportManName = reportManName;
    }

    public String getReportUnitEvaluate() {
        return reportUnitEvaluate;
    }

    public void setReportUnitEvaluate(String reportUnitEvaluate) {
        this.reportUnitEvaluate = reportUnitEvaluate;
    }

    public String getReportUnitName() {
        return reportUnitName;
    }

    public void setReportUnitName(String reportUnitName) {
        this.reportUnitName = reportUnitName;
    }

    public String getReportUnitMan() {
        return reportUnitMan;
    }

    public void setReportUnitMan(String reportUnitMan) {
        this.reportUnitMan = reportUnitMan;
    }

    public String getReportUnitPhone() {
        return reportUnitPhone;
    }

    public void setReportUnitPhone(String reportUnitPhone) {
        this.reportUnitPhone = reportUnitPhone;
    }

    public String getInfoSrc() {
        return infoSrc;
    }

    public void setInfoSrc(String infoSrc) {
        this.infoSrc = infoSrc;
    }

    public String getAdverseReactionDesc() {
        return adverseReactionDesc;
    }

    public void setAdverseReactionDesc(String adverseReactionDesc) {
        this.adverseReactionDesc = adverseReactionDesc;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<ReportGoodsVO> getDoubtGoodsVOList() {
        return doubtGoodsVOList;
    }

    public void setDoubtGoodsVOList(List<ReportGoodsVO> doubtGoodsVOList) {
        this.doubtGoodsVOList = doubtGoodsVOList;
    }

    public List<ReportGoodsVO> getTogetherGoodsVOList() {
        return togetherGoodsVOList;
    }

    public void setTogetherGoodsVOList(List<ReportGoodsVO> togetherGoodsVOList) {
        this.togetherGoodsVOList = togetherGoodsVOList;
    }

    public String getNationName() {
        return nationName;
    }

    public void setNationName(String nationName) {
        this.nationName = nationName;
    }
}
