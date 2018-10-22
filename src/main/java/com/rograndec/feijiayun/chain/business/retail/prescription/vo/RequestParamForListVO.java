package com.rograndec.feijiayun.chain.business.retail.prescription.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * 功能描述：查询处方登记列表参数
 * Created by ST on 2017/9/22 14:54
 */

public class RequestParamForListVO {

    @ApiModelProperty("页码")
    private Integer pageNo;

    @ApiModelProperty("每页显示数量")
    private Integer pageSize;

    private Integer start;


    @ApiModelProperty("登记日期排序 0/倒序；1/正序;默认0")
    private Integer dateOrder = 0;

    @ApiModelProperty("处方单号排序 0/倒序；1/正序;默认0")
    private Integer prescriptionCodeOrder = 0;

    @ApiModelProperty("登记单号排序 0/倒序；1/正序;默认0")
    private Integer codeOrder = 0;


    @ApiModelProperty(value = "开始日期")
    private String startDate;

    @ApiModelProperty(value = "结束日期")
    private String endDate;


    @ApiModelProperty(value = "登记单号")
    private String code;

    @ApiModelProperty(value = "处方单号")
    private String prescriptionCode;

    @ApiModelProperty(value = "登记人员")
    private String registerManName;

    @ApiModelProperty(value = "患者姓名")
    private String name;

    @ApiModelProperty(value = "组织机构编码")
    private String enterpriseCode;

    @ApiModelProperty(value = "组织机构名称")
    private String enterpriseName;


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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public Integer getPrescriptionCodeOrder() {
        return prescriptionCodeOrder == null ? 0 : prescriptionCodeOrder;
    }

    public void setPrescriptionCodeOrder(Integer prescriptionCodeOrder) {
        this.prescriptionCodeOrder = prescriptionCodeOrder;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCodeOrder() {
        if(codeOrder == null){
            return 0;
        }
        return codeOrder;
    }

    public void setCodeOrder(Integer codeOrder) {
        this.codeOrder = codeOrder;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }
}