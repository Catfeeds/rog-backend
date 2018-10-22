package com.rograndec.feijiayun.chain.business.report.quality.retail.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：查询处方登记列表参数
 * Created by dongdong.zhang on 2017/10/25 
 */

public class RequestPrescriptionVO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	    @ApiModelProperty(value = "当前页")
	    private Integer pageNo;
	    
	    @ApiModelProperty(value = "每页显示数据")
	    private Integer pageSize;
	    
	    @ApiModelProperty(value = "按日期排序  0倒序  1正序  默认0")
	    private Integer dateOrder=0;
	    @ApiModelProperty(value = "按单号排序  0倒序  1正序  默认0")
	    private Integer codeOrder=0;
	    
	    private Long enterpriseId;
	    
	    private Integer start;
	    
	    /**
	     * 起始日期
	     */
	    @ApiModelProperty(value = "登记日期(从)")
	    private Date startDate;
	    
	    /**
	     * 截至日期
	     */
	    @ApiModelProperty(value = "登记日期(至)")
	    private Date endDate;
	    
	    /**
	     * 组织机构类型
	     */
	    @ApiModelProperty("组织机构类型   0-总部；1-自营店；2-加盟店")
	    private Integer chainType;
	    
	    /**
	     * 
	     */
	    @ApiModelProperty("组织机构编码")
	    private String enterpriseCode;
	    
	    /**
	     * 
	     */
	    @ApiModelProperty("组织结构名称")
	    private String enterpriseName;


    @ApiModelProperty(value = "登记单号")
    private String code;

    @ApiModelProperty(value = "登记人员")
    private String registerManName;
    
    @ApiModelProperty(value = "处方单号")
    private String prescriptionCode;
    /**
     * 开具日期
     */
    @ApiModelProperty(value = "开具日期")
    private Date billingDate;
    
    private String startBillingDate;
    private String endBillingDate;
    
    /**
     * 处方类型（0-普通；1-急诊；2-儿科；3-麻；4-精一；5-精二；6-中药）
     */
    @ApiModelProperty(value = "处方类型（0-普通；1-急诊；2-儿科；3-麻；4-精一；5-精二；6-中药）")
    private Integer prescriptionType;
    

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

    @ApiModelProperty(value = "状态（状态（21-待审核 31-待调配 32-待收款 33-已完成 34-已取消））")
    private Integer status;



    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getPrescriptionCode() {
        return prescriptionCode;
    }

    public void setPrescriptionCode(String prescriptionCode) {
        this.prescriptionCode = prescriptionCode;
    }

    public String getRegisterManName() {
        return registerManName;
    }

    public void setRegisterManName(String registerManName) {
        this.registerManName = registerManName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDateOrder() {
        return dateOrder == null ? 0 : dateOrder;
    }

    public void setDateOrder(Integer dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public Integer getCodeOrder() {
		return codeOrder;
	}

	public void setCodeOrder(Integer codeOrder) {
		this.codeOrder = codeOrder;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getBillingDate() {
		return billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public Integer getPrescriptionType() {
		return prescriptionType;
	}

	public void setPrescriptionType(Integer prescriptionType) {
		this.prescriptionType = prescriptionType;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getStartBillingDate() {
		return startBillingDate;
	}

	public void setStartBillingDate(String startBillingDate) {
		this.startBillingDate = startBillingDate;
	}

	public String getEndBillingDate() {
		return endBillingDate;
	}

	public void setEndBillingDate(String endBillingDate) {
		this.endBillingDate = endBillingDate;
	}
    
    
}