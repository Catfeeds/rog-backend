package com.rograndec.feijiayun.chain.business.report.quality.user.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * @author dongdong.zhang
 */
public class ResponseHealthCheckDetailVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

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
    @ApiModelProperty(value = "员工姓名")
    private String userName;
    
    /**
     * 部门集合
     */
    @ApiModelProperty(value = "部门")
    private String deptNames;
    
    /**
     * 岗位集合
     */
    @ApiModelProperty(value = "岗位")
    private String positionNames;
    
    /**
     * 性别（0-男；1-女；2-其它）
     */
    @ApiModelProperty(value = "性别ID（0-男；1-女；2-其它）")
    private Integer sex;
    
    /**
     * 
     */
    @ApiModelProperty(value = "性别")
    private String sexName;
    
    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;
    
    /**
     * 入职时间
     */
    @ApiModelProperty(value = " 入职时间")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date inductionTime;

    /**
     * 检查日期
     */
    @ApiModelProperty(value = "检查日期")
    @JsonFormat(pattern="yyyy-MM-dd")
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
     * 附件
     */
    @ApiModelProperty(value = "附件")
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

	public String getDeptNames() {
		return deptNames;
	}

	public void setDeptNames(String deptNames) {
		this.deptNames = deptNames;
	}

	public String getPositionNames() {
		return positionNames;
	}

	public void setPositionNames(String positionNames) {
		this.positionNames = positionNames;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
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