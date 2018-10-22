package com.rograndec.feijiayun.chain.inf.pos.sale.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class POSNewSpecialRegisterVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月8日 下午1:20:48 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 登记人员ID
     */
    @ApiModelProperty(value = "登记人员ID")
    private Long registerManId;
    
    /**
     * 登记日期
     */
    @ApiModelProperty(value = "登记日期")
    private Date registerDate;
    
    /**
     * 登记人员名称
     */
    @ApiModelProperty(value = "会员id")
    private Long memberId;
    
    /**
     * 购药者姓名
     */
    @ApiModelProperty(value = "购药者姓名")
    private String consumerName;
    
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

	public Long getRegisterManId() {
		return registerManId;
	}

	public void setRegisterManId(Long registerManId) {
		this.registerManId = registerManId;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
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
    
}
