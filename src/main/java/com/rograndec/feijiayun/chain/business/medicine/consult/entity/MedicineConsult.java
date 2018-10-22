package com.rograndec.feijiayun.chain.business.medicine.consult.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_medicine_consult
 * 
 * 
 * @author kexinhao
 * 
 * 2017-10-16
 */
public class MedicineConsult implements Serializable {
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
     * 单据类型（5331）
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 单据类型（5331）
     */
    @ApiModelProperty(value = "单据类型（5331）")
    private Integer orderType;

    /**
     * 登记日期
     */
    @ApiModelProperty(value = "登记日期")
    private Date registerDate;

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
     * 会员卡号
     */
    @ApiModelProperty(value = "会员卡号")
    private String memberCardCode;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 性别（0-男；1-女；2-其它）
     */
    @ApiModelProperty(value = "性别（0-男；1-女；2-其它）")
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
     * 出生日期
     * */
    @ApiModelProperty(value = "出生日期")
    private Date birthDate;
    /**
     * 民族ID
     */
    @ApiModelProperty(value = "民族ID")
    private Long nationId;

    /**
     * 民族名称
     */
    @ApiModelProperty(value = "民族名称")
    private String nationName;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobilePhone;

    /**
     * 住址
     */
    @ApiModelProperty(value = "住址")
    private String address;

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
     * 血型
     */
    @ApiModelProperty(value = "血型")
    private String bloodType;

    /**
     * 血压（mmHg）
     */
    @ApiModelProperty(value = "血压（mmHg）")
    private BigDecimal bloodPressure;

    /**
     * 不良嗜好（烟、酒、药物依赖）
     */
    @ApiModelProperty(value = "不良嗜好（烟、酒、药物依赖）")
    private String badHabit;

    /**
     * 主诉和现病史
     */
    @ApiModelProperty(value = "主诉和现病史")
    private String complaintDiseaseHistory;

    /**
     * 既往病史
     */
    @ApiModelProperty(value = "既往病史")
    private String diseaseHistory;

    /**
     * 既往用药史
     */
    @ApiModelProperty(value = "既往用药史")
    private String medicineHistory;

    /**
     * 家族史
     */
    @ApiModelProperty(value = "家族史")
    private String familyHistory;

    /**
     * 过敏史
     */
    @ApiModelProperty(value = "过敏史")
    private String allergyHistory;

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
     * saas_medicine_consult
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
     * 单据类型（5331）
     * @return order_type 单据类型（5331）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5331）
     * @param orderType 单据类型（5331）
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
     * 会员卡号
     * @return member_card_code 会员卡号
     */
    public String getMemberCardCode() {
        return memberCardCode;
    }

    /**
     * 会员卡号
     * @param memberCardCode 会员卡号
     */
    public void setMemberCardCode(String memberCardCode) {
        this.memberCardCode = memberCardCode == null ? null : memberCardCode.trim();
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
     * 民族名称
     * @return nation_name 民族名称
     */
    public String getNationName() {
        return nationName;
    }

    /**
     * 民族名称
     * @param nationName 民族名称
     */
    public void setNationName(String nationName) {
        this.nationName = nationName == null ? null : nationName.trim();
    }

    /**
     * 手机
     * @return mobile_phone 手机
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 手机
     * @param mobilePhone 手机
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * 住址
     * @return address 住址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 住址
     * @param address 住址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
     * 血型
     * @return blood_type 血型
     */
    public String getBloodType() {
        return bloodType;
    }

    /**
     * 血型
     * @param bloodType 血型
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType == null ? null : bloodType.trim();
    }

    /**
     * 血压（mmHg）
     * @return blood_pressure 血压（mmHg）
     */
    public BigDecimal getBloodPressure() {
        return bloodPressure;
    }

    /**
     * 血压（mmHg）
     * @param bloodPressure 血压（mmHg）
     */
    public void setBloodPressure(BigDecimal bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    /**
     * 不良嗜好（烟、酒、药物依赖）
     * @return bad_habit 不良嗜好（烟、酒、药物依赖）
     */
    public String getBadHabit() {
        return badHabit;
    }

    /**
     * 不良嗜好（烟、酒、药物依赖）
     * @param badHabit 不良嗜好（烟、酒、药物依赖）
     */
    public void setBadHabit(String badHabit) {
        this.badHabit = badHabit == null ? null : badHabit.trim();
    }

    /**
     * 主诉和现病史
     * @return complaint_disease_history 主诉和现病史
     */
    public String getComplaintDiseaseHistory() {
        return complaintDiseaseHistory;
    }

    /**
     * 主诉和现病史
     * @param complaintDiseaseHistory 主诉和现病史
     */
    public void setComplaintDiseaseHistory(String complaintDiseaseHistory) {
        this.complaintDiseaseHistory = complaintDiseaseHistory == null ? null : complaintDiseaseHistory.trim();
    }

    /**
     * 既往病史
     * @return disease_history 既往病史
     */
    public String getDiseaseHistory() {
        return diseaseHistory;
    }

    /**
     * 既往病史
     * @param diseaseHistory 既往病史
     */
    public void setDiseaseHistory(String diseaseHistory) {
        this.diseaseHistory = diseaseHistory == null ? null : diseaseHistory.trim();
    }

    /**
     * 既往用药史
     * @return medicine_history 既往用药史
     */
    public String getMedicineHistory() {
        return medicineHistory;
    }

    /**
     * 既往用药史
     * @param medicineHistory 既往用药史
     */
    public void setMedicineHistory(String medicineHistory) {
        this.medicineHistory = medicineHistory == null ? null : medicineHistory.trim();
    }

    /**
     * 家族史
     * @return family_history 家族史
     */
    public String getFamilyHistory() {
        return familyHistory;
    }

    /**
     * 家族史
     * @param familyHistory 家族史
     */
    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory == null ? null : familyHistory.trim();
    }

    /**
     * 过敏史
     * @return allergy_history 过敏史
     */
    public String getAllergyHistory() {
        return allergyHistory;
    }

    /**
     * 过敏史
     * @param allergyHistory 过敏史
     */
    public void setAllergyHistory(String allergyHistory) {
        this.allergyHistory = allergyHistory == null ? null : allergyHistory.trim();
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

    public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
        sb.append(", code=").append(code);
        sb.append(", registerDate=").append(registerDate);
        sb.append(", registerManId=").append(registerManId);
        sb.append(", registerManCode=").append(registerManCode);
        sb.append(", registerManName=").append(registerManName);
        sb.append(", memberCardCode=").append(memberCardCode);
        sb.append(", name=").append(name);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", idNum=").append(idNum);
        sb.append(", birthDate=").append(birthDate);
        sb.append(", nationId=").append(nationId);
        sb.append(", nationName=").append(nationName);
        sb.append(", mobilePhone=").append(mobilePhone);
        sb.append(", address=").append(address);
        sb.append(", height=").append(height);
        sb.append(", weight=").append(weight);
        sb.append(", bloodType=").append(bloodType);
        sb.append(", bloodPressure=").append(bloodPressure);
        sb.append(", badHabit=").append(badHabit);
        sb.append(", complaintDiseaseHistory=").append(complaintDiseaseHistory);
        sb.append(", diseaseHistory=").append(diseaseHistory);
        sb.append(", medicineHistory=").append(medicineHistory);
        sb.append(", familyHistory=").append(familyHistory);
        sb.append(", allergyHistory=").append(allergyHistory);
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