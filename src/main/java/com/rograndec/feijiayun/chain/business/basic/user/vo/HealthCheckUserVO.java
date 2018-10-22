package com.rograndec.feijiayun.chain.business.basic.user.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_health_check
 * 
 * 
 * @author kexinhao
 * 
 * 2017-10-12
 */
public class HealthCheckUserVO implements Serializable {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "员工ID")
    private Long userId;


    /**
     * 用户编码
     */
    @ApiModelProperty(value = "员工编码")
    private String userCode;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "员工姓名")
    private String userName;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    /**
     * 岗位名称
     */
    @ApiModelProperty(value = "岗位名称")
    private String positionName;

    /**
     * sex
     * */
    @ApiModelProperty(value = "性别")
    private String sex;
    
    /**
     * 出生日期
     * */
    @ApiModelProperty(value = "出生日期")
    private Date birthDate;
    
    /**
     * 入职日期
     * */
    @ApiModelProperty(value = "入职日期")
    private Date inductionTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		if(sex!=null){
			if(sex.equals("0")){
				sex="男";
			}else if(sex.equals("1")){
				sex="女";
			}else if(sex.equals("2")){
				sex="其他";
			}else{
				sex="未知";
			}
		}
		this.sex = sex;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getInductionTime() {
		return inductionTime;
	}

	public void setInductionTime(Date inductionTime) {
		this.inductionTime = inductionTime;
	}
}