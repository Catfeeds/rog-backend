package com.rograndec.feijiayun.chain.business.retail.prescription.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
 
/**
 * 
 * saas_prescription_register
 * 
 * 
 * @author ST
 * 
 * 2017-09-22
 */
public class PrescriptionRegister implements Serializable {
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
     * 处方登记单类型（6202）
     */
    @ApiModelProperty(value = "处方登记单类型（6202）")
    private Integer orderType;

    /**
     * 登记日期
     */
    @ApiModelProperty(value = "登记日期")
    private Date registerDate;

    /**
     * 处方单号
     */
    @ApiModelProperty(value = "处方单号")
    private String prescriptionCode;

    /**
     * 登记单号
     */
    @ApiModelProperty(value = "登记单号")
    private String code;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID")
    private Long baseOrderId;

    /**
     * 基础单据编码
     */
    @ApiModelProperty(value = "基础单据编码")
    private String baseOrderCode;

    /**
     * 基础单据类型
     */
    @ApiModelProperty(value = "基础单据类型")
    private Integer baseOrderType;

    /**
     * 基础单据日期
     */
    @ApiModelProperty(value = "基础单据日期")
    private Date baseOrderDate;

    /**
     * 登记人员ID
     */
    @ApiModelProperty(value = "登记人员ID")
    private Long registerManId;

    /**
     * 登记人员编码
     */
    @ApiModelProperty(value = "登记人员编码")
    private String registerManCode;

    /**
     * 登记人员名称
     */
    @ApiModelProperty(value = "登记人员名称")
    private String registerManName;

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
    @ApiModelProperty(value = "性别（0-男；1-女）")
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
     * url
     */
    @ApiModelProperty(value = "url")
    private String url;

    /**
     * 上传方式 0-本地上传 1-base64上传
     */
    @ApiModelProperty(value = "上传方式 0-本地上传 1-base64上传")
    private Integer uploadType;

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
     * 利润合计
     */
    @ApiModelProperty(value = "利润合计")
    private BigDecimal profitTotal;

    /**
     * 不含税利润合计
     */
    @ApiModelProperty(value = "不含税利润合计")
    private BigDecimal notaxProfitTotal;

    /**
     * 利润率
     */
    @ApiModelProperty(value = "利润率")
    private BigDecimal profitRate;

    /**
     * 不含税利润率
     */
    @ApiModelProperty(value = "不含税利润率")
    private BigDecimal notaxProfitRate;

    /**
     * 审核时间
     */
    @ApiModelProperty(value = "审核时间")
    private Date auditTime;

    /**
     * 审核人ID
     */
    @ApiModelProperty(value = "审核人ID")
    private Long auditorId;

    /**
     * 审核人编码
     */
    @ApiModelProperty(value = "审核人编码")
    private String auditorCode;

    /**
     * 审核人名称
     */
    @ApiModelProperty(value = "审核人名称")
    private String auditorName;

    /**
     * 审批意见
     */
    @ApiModelProperty(value = "审批意见")
    private String auditOpinion;

    /**
     * 审批结果（0-拒绝；1-同意）
     */
    @ApiModelProperty(value = "审批结果（0-拒绝；1-同意）")
    private Integer auditResult;

    /**
     * 调剂时间
     */
    @ApiModelProperty(value = "调剂时间")
    private Date swapTime;

    /**
     * 调剂人ID
     */
    @ApiModelProperty(value = "调剂人ID")
    private Long swapManId;

    /**
     * 调剂人编码
     */
    @ApiModelProperty(value = "调剂人编码")
    private String swapManCode;

    /**
     * 调剂人名称
     */
    @ApiModelProperty(value = "调剂人名称")
    private String swapManName;

    /**
     * 核对人ID
     */
    @ApiModelProperty(value = "核对人ID")
    private Long checkerId;

    /**
     * 核对人编码
     */
    @ApiModelProperty(value = "核对人编码")
    private String checkerCode;

    /**
     * 核对人名称
     */
    @ApiModelProperty(value = "核对人名称")
    private String checkerName;

    /**
     * 发药人ID
     */
    @ApiModelProperty(value = "发药人ID")
    private Long sendId;

    /**
     * 发药人编码
     */
    @ApiModelProperty(value = "发药人编码")
    private String sendCode;

    /**
     * 发药人名称
     */
    @ApiModelProperty(value = "发药人名称")
    private String sendName;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）")
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
     * saas_prescription_register
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
     * 处方登记单类型（6202）
     * @return order_type 处方登记单类型（6202）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 处方登记单类型（6202）
     * @param orderType 处方登记单类型（6202）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 登记日期
     * @return register_date 登记日期
     */
    public Date getRegisterDate() {
        return registerDate;
    }

    /**
     * 登记日期
     * @param registerDate 登记日期
     */
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    /**
     * 登记单号
     * @return prescription_code 登记单号
     */
    public String getPrescriptionCode() {
        return prescriptionCode;
    }

    /**
     * 登记单号
     * @param prescriptionCode 登记单号
     */
    public void setPrescriptionCode(String prescriptionCode) {
        this.prescriptionCode = prescriptionCode == null ? null : prescriptionCode.trim();
    }

    /**
     * 处方单号
     * @return code 处方单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 处方单号
     * @param code 处方单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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
     * 登记人员ID
     * @return register_man_id 登记人员ID
     */
    public Long getRegisterManId() {
        return registerManId;
    }

    /**
     * 登记人员ID
     * @param registerManId 登记人员ID
     */
    public void setRegisterManId(Long registerManId) {
        this.registerManId = registerManId;
    }

    /**
     * 登记人员编码
     * @return register_man_code 登记人员编码
     */
    public String getRegisterManCode() {
        return registerManCode;
    }

    /**
     * 登记人员编码
     * @param registerManCode 登记人员编码
     */
    public void setRegisterManCode(String registerManCode) {
        this.registerManCode = registerManCode == null ? null : registerManCode.trim();
    }

    /**
     * 登记人员名称
     * @return register_man_name 登记人员名称
     */
    public String getRegisterManName() {
        return registerManName;
    }

    /**
     * 登记人员名称
     * @param registerManName 登记人员名称
     */
    public void setRegisterManName(String registerManName) {
        this.registerManName = registerManName == null ? null : registerManName.trim();
    }

    /**
     * 开具日期
     * @return billing_date 开具日期
     */
    public Date getBillingDate() {
        return billingDate;
    }

    /**
     * 开具日期
     * @param billingDate 开具日期
     */
    public void setBillingDate(Date billingDate) {
        this.billingDate = billingDate;
    }

    /**
     * 医疗机构编码
     * @return medical_org_code 医疗机构编码
     */
    public String getMedicalOrgCode() {
        return medicalOrgCode;
    }

    /**
     * 医疗机构编码
     * @param medicalOrgCode 医疗机构编码
     */
    public void setMedicalOrgCode(String medicalOrgCode) {
        this.medicalOrgCode = medicalOrgCode == null ? null : medicalOrgCode.trim();
    }

    /**
     * 医疗机构名称
     * @return medical_org_name 医疗机构名称
     */
    public String getMedicalOrgName() {
        return medicalOrgName;
    }

    /**
     * 医疗机构名称
     * @param medicalOrgName 医疗机构名称
     */
    public void setMedicalOrgName(String medicalOrgName) {
        this.medicalOrgName = medicalOrgName == null ? null : medicalOrgName.trim();
    }

    /**
     * 处方类型（0-普通；1-急诊；2-儿科；3-麻；4-精一；5-精二；6-中药）
     * @return prescription_type 处方类型（0-普通；1-急诊；2-儿科；3-麻；4-精一；5-精二；6-中药）
     */
    public Integer getPrescriptionType() {
        return prescriptionType;
    }

    /**
     * 处方类型（0-普通；1-急诊；2-儿科；3-麻；4-精一；5-精二；6-中药）
     * @param prescriptionType 处方类型（0-普通；1-急诊；2-儿科；3-麻；4-精一；5-精二；6-中药）
     */
    public void setPrescriptionType(Integer prescriptionType) {
        this.prescriptionType = prescriptionType;
    }

    /**
     * 费别（0-自费；1-公费；2-医保；3-农合；4-其它）
     * @return fee_type 费别（0-自费；1-公费；2-医保；3-农合；4-其它）
     */
    public Integer getFeeType() {
        return feeType;
    }

    /**
     * 费别（0-自费；1-公费；2-医保；3-农合；4-其它）
     * @param feeType 费别（0-自费；1-公费；2-医保；3-农合；4-其它）
     */
    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    /**
     * 门诊／病例号
     * @return outpatient_case_num 门诊／病例号
     */
    public String getOutpatientCaseNum() {
        return outpatientCaseNum;
    }

    /**
     * 门诊／病例号
     * @param outpatientCaseNum 门诊／病例号
     */
    public void setOutpatientCaseNum(String outpatientCaseNum) {
        this.outpatientCaseNum = outpatientCaseNum == null ? null : outpatientCaseNum.trim();
    }

    /**
     * 科室
     * @return section_room 科室
     */
    public String getSectionRoom() {
        return sectionRoom;
    }

    /**
     * 科室
     * @param sectionRoom 科室
     */
    public void setSectionRoom(String sectionRoom) {
        this.sectionRoom = sectionRoom == null ? null : sectionRoom.trim();
    }

    /**
     * 病人标识
     * @return patient_id 病人标识
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * 病人标识
     * @param patientId 病人标识
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId == null ? null : patientId.trim();
    }

    /**
     * 姓名
     * @return name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 性别（0-女；1-男）
     * @return sex 性别（0-女；1-男）
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 性别（0-女；1-男）
     * @param sex 性别（0-女；1-男）
     */
    public void setSex(Integer sex) {
        this.sex = sex;
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
     * 身份证号
     * @return id_num 身份证号
     */
    public String getIdNum() {
        return idNum;
    }

    /**
     * 身份证号
     * @param idNum 身份证号
     */
    public void setIdNum(String idNum) {
        this.idNum = idNum == null ? null : idNum.trim();
    }

    /**
     * 身高（cm）
     * @return height 身高（cm）
     */
    public BigDecimal getHeight() {
        return height;
    }

    /**
     * 身高（cm）
     * @param height 身高（cm）
     */
    public void setHeight(BigDecimal height) {
        this.height = height;
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
     * 诊断
     * @return diagnosis 诊断
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * 诊断
     * @param diagnosis 诊断
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis == null ? null : diagnosis.trim();
    }

    /**
     * 过敏试验
     * @return allergy_test 过敏试验
     */
    public String getAllergyTest() {
        return allergyTest;
    }

    /**
     * 过敏试验
     * @param allergyTest 过敏试验
     */
    public void setAllergyTest(String allergyTest) {
        this.allergyTest = allergyTest == null ? null : allergyTest.trim();
    }

    /**
     * 医师
     * @return physician 医师
     */
    public String getPhysician() {
        return physician;
    }

    /**
     * 医师
     * @param physician 医师
     */
    public void setPhysician(String physician) {
        this.physician = physician == null ? null : physician.trim();
    }

    /**
     * 医嘱
     * @return doctor_advice 医嘱
     */
    public String getDoctorAdvice() {
        return doctorAdvice;
    }

    /**
     * 医嘱
     * @param doctorAdvice 医嘱
     */
    public void setDoctorAdvice(String doctorAdvice) {
        this.doctorAdvice = doctorAdvice == null ? null : doctorAdvice.trim();
    }

    /**
     * 每日剂量
     * @return day_dose 每日剂量
     */
    public BigDecimal getDayDose() {
        return dayDose;
    }

    /**
     * 每日剂量
     * @param dayDose 每日剂量
     */
    public void setDayDose(BigDecimal dayDose) {
        this.dayDose = dayDose;
    }

    /**
     * 用法ID
     * @return usage_id 用法ID
     */
    public Long getUsageId() {
        return usageId;
    }

    /**
     * 用法ID
     * @param usageId 用法ID
     */
    public void setUsageId(Long usageId) {
        this.usageId = usageId;
    }

    /**
     * 用法名称
     * @return usage_name 用法名称
     */
    public String getUsageName() {
        return usageName;
    }

    /**
     * 用法名称
     * @param usageName 用法名称
     */
    public void setUsageName(String usageName) {
        this.usageName = usageName == null ? null : usageName.trim();
    }

    /**
     * 用法数值
     * @return usage_value 用法数值
     */
    public String getUsageValue() {
        return usageValue;
    }

    /**
     * 用法数值
     * @param usageValue 用法数值
     */
    public void setUsageValue(String usageValue) {
        this.usageValue = usageValue == null ? null : usageValue.trim();
    }

    /**
     * 用量ID
     * @return use_qty_id 用量ID
     */
    public Long getUseQtyId() {
        return useQtyId;
    }

    /**
     * 用量ID
     * @param useQtyId 用量ID
     */
    public void setUseQtyId(Long useQtyId) {
        this.useQtyId = useQtyId;
    }

    /**
     * 用量名称
     * @return use_qty_name 用量名称
     */
    public String getUseQtyName() {
        return useQtyName;
    }

    /**
     * 用量名称
     * @param useQtyName 用量名称
     */
    public void setUseQtyName(String useQtyName) {
        this.useQtyName = useQtyName == null ? null : useQtyName.trim();
    }

    /**
     * 注意事项ID
     * @return attention_matter_id 注意事项ID
     */
    public Long getAttentionMatterId() {
        return attentionMatterId;
    }

    /**
     * 注意事项ID
     * @param attentionMatterId 注意事项ID
     */
    public void setAttentionMatterId(Long attentionMatterId) {
        this.attentionMatterId = attentionMatterId;
    }

    /**
     * 注意事项名称
     * @return attention_matter_name 注意事项名称
     */
    public String getAttentionMatterName() {
        return attentionMatterName;
    }

    /**
     * 注意事项名称
     * @param attentionMatterName 注意事项名称
     */
    public void setAttentionMatterName(String attentionMatterName) {
        this.attentionMatterName = attentionMatterName == null ? null : attentionMatterName.trim();
    }

    /**
     * 剂量
     * @return dose 剂量
     */
    public BigDecimal getDose() {
        return dose;
    }

    /**
     * 剂量
     * @param dose 剂量
     */
    public void setDose(BigDecimal dose) {
        this.dose = dose;
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
     * 金额合计（整单优惠前的金额合计）
     * @return amount_total 金额合计（整单优惠前的金额合计）
     */
    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    /**
     * 金额合计（整单优惠前的金额合计）
     * @param amountTotal 金额合计（整单优惠前的金额合计）
     */
    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    /**
     * 整单折扣（%）
     * @return whole_discount 整单折扣（%）
     */
    public BigDecimal getWholeDiscount() {
        return wholeDiscount;
    }

    /**
     * 整单折扣（%）
     * @param wholeDiscount 整单折扣（%）
     */
    public void setWholeDiscount(BigDecimal wholeDiscount) {
        this.wholeDiscount = wholeDiscount;
    }

    /**
     * 整单优惠金额
     * @return whole_discount_amount 整单优惠金额
     */
    public BigDecimal getWholeDiscountAmount() {
        return wholeDiscountAmount;
    }

    /**
     * 整单优惠金额
     * @param wholeDiscountAmount 整单优惠金额
     */
    public void setWholeDiscountAmount(BigDecimal wholeDiscountAmount) {
        this.wholeDiscountAmount = wholeDiscountAmount;
    }

    /**
     * 实际金额合计
     * @return real_amount_total 实际金额合计
     */
    public BigDecimal getRealAmountTotal() {
        return realAmountTotal;
    }

    /**
     * 实际金额合计
     * @param realAmountTotal 实际金额合计
     */
    public void setRealAmountTotal(BigDecimal realAmountTotal) {
        this.realAmountTotal = realAmountTotal;
    }

    /**
     * 不含税金额合计
     * @return notax_real_amount_total 不含税金额合计
     */
    public BigDecimal getNotaxRealAmountTotal() {
        return notaxRealAmountTotal;
    }

    /**
     * 不含税金额合计
     * @param notaxRealAmountTotal 不含税金额合计
     */
    public void setNotaxRealAmountTotal(BigDecimal notaxRealAmountTotal) {
        this.notaxRealAmountTotal = notaxRealAmountTotal;
    }

    /**
     * 税额合计
     * @return tax_amount_total 税额合计
     */
    public BigDecimal getTaxAmountTotal() {
        return taxAmountTotal;
    }

    /**
     * 税额合计
     * @param taxAmountTotal 税额合计
     */
    public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
        this.taxAmountTotal = taxAmountTotal;
    }

    /**
     * 利润合计
     * @return profit_total 利润合计
     */
    public BigDecimal getProfitTotal() {
        return profitTotal;
    }

    /**
     * 利润合计
     * @param profitTotal 利润合计
     */
    public void setProfitTotal(BigDecimal profitTotal) {
        this.profitTotal = profitTotal;
    }

    /**
     * 不含税利润合计
     * @return notax_profit_total 不含税利润合计
     */
    public BigDecimal getNotaxProfitTotal() {
        return notaxProfitTotal;
    }

    /**
     * 不含税利润合计
     * @param notaxProfitTotal 不含税利润合计
     */
    public void setNotaxProfitTotal(BigDecimal notaxProfitTotal) {
        this.notaxProfitTotal = notaxProfitTotal;
    }

    /**
     * 利润率
     * @return profit_rate 利润率
     */
    public BigDecimal getProfitRate() {
        return profitRate;
    }

    /**
     * 利润率
     * @param profitRate 利润率
     */
    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    /**
     * 不含税利润率
     * @return notax_profit_rate 不含税利润率
     */
    public BigDecimal getNotaxProfitRate() {
        return notaxProfitRate;
    }

    /**
     * 不含税利润率
     * @param notaxProfitRate 不含税利润率
     */
    public void setNotaxProfitRate(BigDecimal notaxProfitRate) {
        this.notaxProfitRate = notaxProfitRate;
    }

    /**
     * 审核时间
     * @return audit_time 审核时间
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * 审核时间
     * @param auditTime 审核时间
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * 审核人ID
     * @return auditor_id 审核人ID
     */
    public Long getAuditorId() {
        return auditorId;
    }

    /**
     * 审核人ID
     * @param auditorId 审核人ID
     */
    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    /**
     * 审核人编码
     * @return auditor_code 审核人编码
     */
    public String getAuditorCode() {
        return auditorCode;
    }

    /**
     * 审核人编码
     * @param auditorCode 审核人编码
     */
    public void setAuditorCode(String auditorCode) {
        this.auditorCode = auditorCode == null ? null : auditorCode.trim();
    }

    /**
     * 审核人名称
     * @return auditor_name 审核人名称
     */
    public String getAuditorName() {
        return auditorName;
    }

    /**
     * 审核人名称
     * @param auditorName 审核人名称
     */
    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName == null ? null : auditorName.trim();
    }

    /**
     * 审批意见
     * @return audit_opinion 审批意见
     */
    public String getAuditOpinion() {
        return auditOpinion;
    }

    /**
     * 审批意见
     * @param auditOpinion 审批意见
     */
    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion == null ? null : auditOpinion.trim();
    }

    /**
     * 审批结果（0-拒绝；1-同意）
     * @return audit_result 审批结果（0-拒绝；1-同意）
     */
    public Integer getAuditResult() {
        return auditResult;
    }

    /**
     * 审批结果（0-拒绝；1-同意）
     * @param auditResult 审批结果（0-拒绝；1-同意）
     */
    public void setAuditResult(Integer auditResult) {
        this.auditResult = auditResult;
    }

    /**
     * 调剂时间
     * @return swap_time 调剂时间
     */
    public Date getSwapTime() {
        return swapTime;
    }

    /**
     * 调剂时间
     * @param swapTime 调剂时间
     */
    public void setSwapTime(Date swapTime) {
        this.swapTime = swapTime;
    }

    /**
     * 调剂人ID
     * @return swap_man_id 调剂人ID
     */
    public Long getSwapManId() {
        return swapManId;
    }

    /**
     * 调剂人ID
     * @param swapManId 调剂人ID
     */
    public void setSwapManId(Long swapManId) {
        this.swapManId = swapManId;
    }

    /**
     * 调剂人编码
     * @return swap_man_code 调剂人编码
     */
    public String getSwapManCode() {
        return swapManCode;
    }

    /**
     * 调剂人编码
     * @param swapManCode 调剂人编码
     */
    public void setSwapManCode(String swapManCode) {
        this.swapManCode = swapManCode == null ? null : swapManCode.trim();
    }

    /**
     * 调剂人名称
     * @return swap_man_name 调剂人名称
     */
    public String getSwapManName() {
        return swapManName;
    }

    /**
     * 调剂人名称
     * @param swapManName 调剂人名称
     */
    public void setSwapManName(String swapManName) {
        this.swapManName = swapManName == null ? null : swapManName.trim();
    }

    /**
     * 核对人ID
     * @return checker_id 核对人ID
     */
    public Long getCheckerId() {
        return checkerId;
    }

    /**
     * 核对人ID
     * @param checkerId 核对人ID
     */
    public void setCheckerId(Long checkerId) {
        this.checkerId = checkerId;
    }

    /**
     * 核对人编码
     * @return checker_code 核对人编码
     */
    public String getCheckerCode() {
        return checkerCode;
    }

    /**
     * 核对人编码
     * @param checkerCode 核对人编码
     */
    public void setCheckerCode(String checkerCode) {
        this.checkerCode = checkerCode == null ? null : checkerCode.trim();
    }

    /**
     * 核对人名称
     * @return checker_name 核对人名称
     */
    public String getCheckerName() {
        return checkerName;
    }

    /**
     * 核对人名称
     * @param checkerName 核对人名称
     */
    public void setCheckerName(String checkerName) {
        this.checkerName = checkerName == null ? null : checkerName.trim();
    }

    /**
     * 发药人ID
     * @return send_id 发药人ID
     */
    public Long getSendId() {
        return sendId;
    }

    /**
     * 发药人ID
     * @param sendId 发药人ID
     */
    public void setSendId(Long sendId) {
        this.sendId = sendId;
    }

    /**
     * 发药人编码
     * @return send_code 发药人编码
     */
    public String getSendCode() {
        return sendCode;
    }

    /**
     * 发药人编码
     * @param sendCode 发药人编码
     */
    public void setSendCode(String sendCode) {
        this.sendCode = sendCode == null ? null : sendCode.trim();
    }

    /**
     * 发药人名称
     * @return send_name 发药人名称
     */
    public String getSendName() {
        return sendName;
    }

    /**
     * 发药人名称
     * @param sendName 发药人名称
     */
    public void setSendName(String sendName) {
        this.sendName = sendName == null ? null : sendName.trim();
    }

    /**
     * 状态（0-禁用；1-启用）
     * @return status 状态（0-禁用；1-启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @param status 状态（0-禁用；1-启用）
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
        sb.append(", registerDate=").append(registerDate);
        sb.append(", prescriptionCode=").append(prescriptionCode);
        sb.append(", code=").append(code);
        sb.append(", baseOrderId=").append(baseOrderId);
        sb.append(", baseOrderCode=").append(baseOrderCode);
        sb.append(", baseOrderType=").append(baseOrderType);
        sb.append(", baseOrderDate=").append(baseOrderDate);
        sb.append(", registerManId=").append(registerManId);
        sb.append(", registerManCode=").append(registerManCode);
        sb.append(", registerManName=").append(registerManName);
        sb.append(", billingDate=").append(billingDate);
        sb.append(", medicalOrgCode=").append(medicalOrgCode);
        sb.append(", medicalOrgName=").append(medicalOrgName);
        sb.append(", prescriptionType=").append(prescriptionType);
        sb.append(", feeType=").append(feeType);
        sb.append(", outpatientCaseNum=").append(outpatientCaseNum);
        sb.append(", sectionRoom=").append(sectionRoom);
        sb.append(", patientId=").append(patientId);
        sb.append(", name=").append(name);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", idNum=").append(idNum);
        sb.append(", height=").append(height);
        sb.append(", weight=").append(weight);
        sb.append(", diagnosis=").append(diagnosis);
        sb.append(", allergyTest=").append(allergyTest);
        sb.append(", physician=").append(physician);
        sb.append(", doctorAdvice=").append(doctorAdvice);
        sb.append(", dayDose=").append(dayDose);
        sb.append(", usageId=").append(usageId);
        sb.append(", usageName=").append(usageName);
        sb.append(", usageValue=").append(usageValue);
        sb.append(", useQtyId=").append(useQtyId);
        sb.append(", useQtyName=").append(useQtyName);
        sb.append(", attentionMatterId=").append(attentionMatterId);
        sb.append(", attentionMatterName=").append(attentionMatterName);
        sb.append(", dose=").append(dose);
        sb.append(", fileId=").append(fileId);
        sb.append(", url=").append(url);
        sb.append(", uploadType=").append(uploadType);
        sb.append(", quantityTotal=").append(quantityTotal);
        sb.append(", varietiesQuantity=").append(varietiesQuantity);
        sb.append(", amountTotal=").append(amountTotal);
        sb.append(", wholeDiscount=").append(wholeDiscount);
        sb.append(", wholeDiscountAmount=").append(wholeDiscountAmount);
        sb.append(", realAmountTotal=").append(realAmountTotal);
        sb.append(", notaxRealAmountTotal=").append(notaxRealAmountTotal);
        sb.append(", taxAmountTotal=").append(taxAmountTotal);
        sb.append(", profitTotal=").append(profitTotal);
        sb.append(", notaxProfitTotal=").append(notaxProfitTotal);
        sb.append(", profitRate=").append(profitRate);
        sb.append(", notaxProfitRate=").append(notaxProfitRate);
        sb.append(", auditTime=").append(auditTime);
        sb.append(", auditorId=").append(auditorId);
        sb.append(", auditorCode=").append(auditorCode);
        sb.append(", auditorName=").append(auditorName);
        sb.append(", auditOpinion=").append(auditOpinion);
        sb.append(", auditResult=").append(auditResult);
        sb.append(", swapTime=").append(swapTime);
        sb.append(", swapManId=").append(swapManId);
        sb.append(", swapManCode=").append(swapManCode);
        sb.append(", swapManName=").append(swapManName);
        sb.append(", checkerId=").append(checkerId);
        sb.append(", checkerCode=").append(checkerCode);
        sb.append(", checkerName=").append(checkerName);
        sb.append(", sendId=").append(sendId);
        sb.append(", sendCode=").append(sendCode);
        sb.append(", sendName=").append(sendName);
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