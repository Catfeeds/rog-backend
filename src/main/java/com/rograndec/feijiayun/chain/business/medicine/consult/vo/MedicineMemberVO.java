package com.rograndec.feijiayun.chain.business.medicine.consult.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class MedicineMemberVO implements Serializable{
	@ApiModelProperty(value = "会员卡号")
	private String memberCardCode;
	@ApiModelProperty(value = "姓名")
	private String name;
	@ApiModelProperty(value = "性别")
	private Integer sex;
	@ApiModelProperty(value = "年龄")
	private Integer age;
	@ApiModelProperty(value = "出生日期")
	private Date birthDate;
	@ApiModelProperty(value = "身份证号")
	private String idNum;
	@ApiModelProperty(value = "手机")
    private String mobilePhone;
	@ApiModelProperty(value = "住址")
	private String address;
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
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		int year=0;
		if(birthDate!=null){
		 year=new Date().getYear()-birthDate.getYear();
		}
		this.age=year;
		this.birthDate = birthDate;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
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

}
