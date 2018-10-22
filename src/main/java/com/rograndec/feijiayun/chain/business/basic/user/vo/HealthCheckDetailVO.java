package com.rograndec.feijiayun.chain.business.basic.user.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_health_check_detail
 * 
 * 
 * @author kexinhao
 * 
 * 2017-10-12
 */
public class HealthCheckDetailVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 检查ID
     */
    @ApiModelProperty(value = "检查ID")
    private Long checkId;

    /**
     * 员工ID
     */
    @ApiModelProperty(value = "员工ID")
    private Long userId;

    /**
     * 员工编码
     */
    @ApiModelProperty(value = "员工编码")
    private String userCode;

    /**
     * 员工名称
     */
    @ApiModelProperty(value = "员工名称")
    private String userName;
    
    
    @ApiModelProperty(value = "部门")
    private String deptName;
    @ApiModelProperty(value = "岗位")
    private String positionName;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "出生日期")
    private Date  birthDate;
    @ApiModelProperty(value = "入职日期")
    private Date inductionTime;
    
    /**
     * 检查日期
     */
    @ApiModelProperty(value = "检查日期")
    private Date checkDate;

    /**
     * 检查结果
     */
    @ApiModelProperty(value = "检查结果")
    private String checkResult;

    /**
     * 采取措施
     */
    @ApiModelProperty(value = "采取措施")
    private String measures;

    /**
     * 附件ID
     */
    @ApiModelProperty(value = "附件ID")
    private Long fileId;

    /**
     * 附件名称
     */
    @ApiModelProperty(value = "附件名称")
    private String fileName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
   
    /**
     * saas_health_check_detail
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
     * 检查ID
     * @return check_id 检查ID
     */
    public Long getCheckId() {
        return checkId;
    }

    /**
     * 检查ID
     * @param checkId 检查ID
     */
    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    /**
     * 员工ID
     * @return user_id 员工ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 员工ID
     * @param userId 员工ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 员工编码
     * @return user_code 员工编码
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * 员工编码
     * @param userCode 员工编码
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * 员工名称
     * @return user_name 员工名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 员工名称
     * @param userName 员工名称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 检查日期
     * @return check_date 检查日期
     */
    public Date getCheckDate() {
        return checkDate;
    }

    /**
     * 检查日期
     * @param checkDate 检查日期
     */
    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    /**
     * 检查结果
     * @return check_result 检查结果
     */
    public String getCheckResult() {
        return checkResult;
    }

    /**
     * 检查结果
     * @param checkResult 检查结果
     */
    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult == null ? null : checkResult.trim();
    }

    /**
     * 采取措施
     * @return measures 采取措施
     */
    public String getMeasures() {
        return measures;
    }

    /**
     * 采取措施
     * @param measures 采取措施
     */
    public void setMeasures(String measures) {
        this.measures = measures == null ? null : measures.trim();
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
    
}