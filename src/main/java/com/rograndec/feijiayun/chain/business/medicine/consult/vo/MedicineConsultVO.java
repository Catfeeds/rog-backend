package com.rograndec.feijiayun.chain.business.medicine.consult.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
public class MedicineConsultVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 单据类型（5331）
     */
    @ApiModelProperty(value = "单据类型（5331）")
    private Integer orderType;
    
    @ApiModelProperty(value = "编码")
    private String code;
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
    
    @ApiModelProperty(value = "性别（0-男；1-女；2-其它）")
    private String sexDesc;
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
     * 出生日期
     * */
    @ApiModelProperty(value = "出生日期")
    private Date birthDate;
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
     * saas_medicine_consult
     */
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用药咨询药品列表")
    List<MedicineConsultGoodsVO> medicineConsultGoodsVOList;
    

    @ApiModelProperty(value = "用药咨询用药检查列表")
    List<MedicineConsultCheckVO> medicineConsultCheckVOList;
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getOrderType() {
		return orderType;
	}


	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}


	public Date getRegisterDate() {
		return registerDate;
	}


	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}


	public Long getRegisterManId() {
		return registerManId;
	}


	public void setRegisterManId(Long registerManId) {
		this.registerManId = registerManId;
	}


	public String getRegisterManCode() {
		return registerManCode;
	}


	public void setRegisterManCode(String registerManCode) {
		this.registerManCode = registerManCode;
	}


	public String getRegisterManName() {
		return registerManName;
	}


	public void setRegisterManName(String registerManName) {
		this.registerManName = registerManName;
	}


	public String getMemberCardCode() {
		return memberCardCode;
	}


	public void setMemberCardCode(String memberCardCode) {
		this.memberCardCode = memberCardCode;
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
		if(sex!=null){
			if(sex.equals(0)){
				this.sexDesc="男";
			}else if(sex.equals(1)){
				this.sexDesc="女";
			}else{
				this.sexDesc="其他";
			}
		}
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


	public Long getNationId() {
		return nationId;
	}


	public void setNationId(Long nationId) {
		this.nationId = nationId;
	}


	public String getNationName() {
		return nationName;
	}


	public void setNationName(String nationName) {
		this.nationName = nationName;
	}


	public String getMobilePhone() {
		return mobilePhone;
	}


	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
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


	public String getBloodType() {
		return bloodType;
	}


	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}


	public BigDecimal getBloodPressure() {
		return bloodPressure;
	}


	public void setBloodPressure(BigDecimal bloodPressure) {
		this.bloodPressure = bloodPressure;
	}


	public String getBadHabit() {
		return badHabit;
	}


	public void setBadHabit(String badHabit) {
		this.badHabit = badHabit;
	}


	public String getComplaintDiseaseHistory() {
		return complaintDiseaseHistory;
	}


	public void setComplaintDiseaseHistory(String complaintDiseaseHistory) {
		this.complaintDiseaseHistory = complaintDiseaseHistory;
	}


	public String getDiseaseHistory() {
		return diseaseHistory;
	}


	public void setDiseaseHistory(String diseaseHistory) {
		this.diseaseHistory = diseaseHistory;
	}


	public String getMedicineHistory() {
		return medicineHistory;
	}


	public void setMedicineHistory(String medicineHistory) {
		this.medicineHistory = medicineHistory;
	}


	public String getFamilyHistory() {
		return familyHistory;
	}


	public void setFamilyHistory(String familyHistory) {
		this.familyHistory = familyHistory;
	}


	public String getAllergyHistory() {
		return allergyHistory;
	}


	public void setAllergyHistory(String allergyHistory) {
		this.allergyHistory = allergyHistory;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public List<MedicineConsultGoodsVO> getMedicineConsultGoodsVOList() {
		return medicineConsultGoodsVOList;
	}


	public void setMedicineConsultGoodsVOList(List<MedicineConsultGoodsVO> medicineConsultGoodsVOList) {
		this.medicineConsultGoodsVOList = medicineConsultGoodsVOList;
	}


	public List<MedicineConsultCheckVO> getMedicineConsultCheckVOList() {
		return medicineConsultCheckVOList;
	}


	public void setMedicineConsultCheckVOList(List<MedicineConsultCheckVO> medicineConsultCheckVOList) {
		this.medicineConsultCheckVOList = medicineConsultCheckVOList;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public Date getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}


	public String getSexDesc() {
		return sexDesc;
	}


	public void setSexDesc(String sexDesc) {
		this.sexDesc = sexDesc;
	}

	
	
}