package com.rograndec.feijiayun.chain.business.retail.prescription.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_prescription_register
 * 
 * 新增 修改 处方登记实体
 * @author ST
 * 
 * 2017-09-22
 */
public class RequestPrescriptionRegisterForAddUpdateVO implements Serializable {


    private Long id;
    /**
     * 登记日期
     */
    @ApiModelProperty(value = "登记日期")
    private Date registerDate;

    @ApiModelProperty(value = "新增时为空；补单登记时为销售单主键")
    private Long baseOrderId;

    /**
     * 处方单号
     */
    @ApiModelProperty(value = "处方单号")
    private String prescriptionCode;

    /**
     * 登记人员ID
     */
    @ApiModelProperty(value = "登记人员ID")
    private Long registerManId;


    /**
     * 开具日期
     */
    @ApiModelProperty(value = "开具日期")
    private Date billingDate;

    /**
     * 医疗机构编码
     */
    @ApiModelProperty(value = "医疗机构编码")
    private String medicalOrgCode;

    /**
     * 医疗机构名称
     */
    @ApiModelProperty(value = "医疗机构名称")
    private String medicalOrgName;

    /**
     * 处方类型（0-普通；1-急诊；2-儿科；3-麻；4-精一；5-精二；6-中药）
     */
    @ApiModelProperty(value = "处方类型（0-普通；1-急诊；2-儿科；3-麻；4-精一；5-精二；6-中药）")
    private Integer prescriptionType;

    /**
     * 费别（0-自费；1-公费；2-医保；3-农合；4-其它）
     */
    @ApiModelProperty(value = "费别（0-自费；1-公费；2-医保；3-农合；4-其它）")
    private Integer feeType;

    /**
     * 门诊／病例号
     */
    @ApiModelProperty(value = "门诊／病例号")
    private String outpatientCaseNum;

    /**
     * 科室
     */
    @ApiModelProperty(value = "科室")
    private String sectionRoom;


    @ApiModelProperty(value = "病人ID")
    private Long memberId;

    /**
     * 病人标识
     */
    @ApiModelProperty(value = "病人标识")
    private String patientId;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 性别（0-女；1-男）
     */
    @ApiModelProperty(value = "性别（0-女；1-男）")
    private Integer sex;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idNum;

    /**
     * 身高（cm）
     */
    @ApiModelProperty(value = "身高（cm）")
    private BigDecimal height;

    /**
     * 体重（kg）
     */
    @ApiModelProperty(value = "体重（kg）")
    private BigDecimal weight;

    /**
     * 诊断
     */
    @ApiModelProperty(value = "诊断")
    private String diagnosis;

    /**
     * 过敏试验
     */
    @ApiModelProperty(value = "过敏试验")
    private String allergyTest;

    /**
     * 医师
     */
    @ApiModelProperty(value = "医师")
    private String physician;

    /**
     * 医嘱
     */
    @ApiModelProperty(value = "医嘱")
    private String doctorAdvice;

    /**
     * 每日剂量
     */
    @ApiModelProperty(value = "每日剂量")
    private BigDecimal dayDose;

    /**
     * 用法ID
     */
    @ApiModelProperty(value = "用法ID")
    private Long usageId;

    /**
     * 用法名称
     */
    @ApiModelProperty(value = "用法名称")
    private String usageName;

    /**
     * 用法数值
     */
    @ApiModelProperty(value = "用法数值")
    private String usageValue;

    /**
     * 用量ID
     */
    @ApiModelProperty(value = "用量ID")
    private Long useQtyId;

    /**
     * 用量名称
     */
    @ApiModelProperty(value = "用量名称")
    private String useQtyName;

    /**
     * 注意事项ID
     */
    @ApiModelProperty(value = "注意事项ID")
    private Long attentionMatterId;

    /**
     * 注意事项名称
     */
    @ApiModelProperty(value = "注意事项名称")
    private String attentionMatterName;

    /**
     * 剂量
     */
    @ApiModelProperty(value = "剂量")
    private BigDecimal dose;

    /**
     * 附件ID
     */
    @ApiModelProperty(value = "附件ID")
    private Long fileId;

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
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * url
     */
    @ApiModelProperty(value = "url")
    private String url;

    /**
     * 上传方式 0-本地上传 1-base64上传
     */
    @ApiModelProperty(value = "上传方式 0-本地上传 1-base64上传")
    private Integer uploadType;


    @ApiModelProperty(value = "处方登记单品种明细")
    private List<RequestPrescriptionRegisterDetailForAddUpdateVO> registerDetailForAddUpdateVOList;

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getPrescriptionCode() {
        return prescriptionCode;
    }

    public void setPrescriptionCode(String prescriptionCode) {
        this.prescriptionCode = prescriptionCode;
    }

    public Long getRegisterManId() {
        return registerManId;
    }

    public void setRegisterManId(Long registerManId) {
        this.registerManId = registerManId;
    }

    public Date getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    public String getMedicalOrgCode() {
        return medicalOrgCode;
    }

    public void setMedicalOrgCode(String medicalOrgCode) {
        this.medicalOrgCode = medicalOrgCode;
    }

    public String getMedicalOrgName() {
        return medicalOrgName;
    }

    public void setMedicalOrgName(String medicalOrgName) {
        this.medicalOrgName = medicalOrgName;
    }

    public Integer getPrescriptionType() {
        return prescriptionType;
    }

    public void setPrescriptionType(Integer prescriptionType) {
        this.prescriptionType = prescriptionType;
    }

    public Integer getFeeType() {
        return feeType;
    }

    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    public String getOutpatientCaseNum() {
        return outpatientCaseNum;
    }

    public void setOutpatientCaseNum(String outpatientCaseNum) {
        this.outpatientCaseNum = outpatientCaseNum;
    }

    public String getSectionRoom() {
        return sectionRoom;
    }

    public void setSectionRoom(String sectionRoom) {
        this.sectionRoom = sectionRoom;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getAllergyTest() {
        return allergyTest;
    }

    public void setAllergyTest(String allergyTest) {
        this.allergyTest = allergyTest;
    }

    public String getPhysician() {
        return physician;
    }

    public void setPhysician(String physician) {
        this.physician = physician;
    }

    public String getDoctorAdvice() {
        return doctorAdvice;
    }

    public void setDoctorAdvice(String doctorAdvice) {
        this.doctorAdvice = doctorAdvice;
    }

    public BigDecimal getDayDose() {
        return dayDose;
    }

    public void setDayDose(BigDecimal dayDose) {
        this.dayDose = dayDose;
    }

    public Long getUsageId() {
        return usageId;
    }

    public void setUsageId(Long usageId) {
        this.usageId = usageId;
    }

    public String getUsageName() {
        return usageName;
    }

    public void setUsageName(String usageName) {
        this.usageName = usageName;
    }

    public String getUsageValue() {
        return usageValue;
    }

    public void setUsageValue(String usageValue) {
        this.usageValue = usageValue;
    }

    public Long getUseQtyId() {
        return useQtyId;
    }

    public void setUseQtyId(Long useQtyId) {
        this.useQtyId = useQtyId;
    }

    public String getUseQtyName() {
        return useQtyName;
    }

    public void setUseQtyName(String useQtyName) {
        this.useQtyName = useQtyName;
    }

    public Long getAttentionMatterId() {
        return attentionMatterId;
    }

    public void setAttentionMatterId(Long attentionMatterId) {
        this.attentionMatterId = attentionMatterId;
    }

    public String getAttentionMatterName() {
        return attentionMatterName;
    }

    public void setAttentionMatterName(String attentionMatterName) {
        this.attentionMatterName = attentionMatterName;
    }

    public BigDecimal getDose() {
        return dose;
    }

    public void setDose(BigDecimal dose) {
        this.dose = dose;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public Integer getVarietiesQuantity() {
        return varietiesQuantity;
    }

    public void setVarietiesQuantity(Integer varietiesQuantity) {
        this.varietiesQuantity = varietiesQuantity;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<RequestPrescriptionRegisterDetailForAddUpdateVO> getRegisterDetailForAddUpdateVOList() {
        return registerDetailForAddUpdateVOList;
    }

    public void setRegisterDetailForAddUpdateVOList(List<RequestPrescriptionRegisterDetailForAddUpdateVO> registerDetailForAddUpdateVOList) {
        this.registerDetailForAddUpdateVOList = registerDetailForAddUpdateVOList;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUploadType() {
        return uploadType;
    }

    public void setUploadType(Integer uploadType) {
        this.uploadType = uploadType;
    }
}