package com.rograndec.feijiayun.chain.business.report.quality.storage.vo;

import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsRequestParam;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <获取盘点记录报表请求参数>
 * xingjian.lan
 */
@ApiModel(value = "RequestReportInventoryRecordVO", description = "盘点记录报表")
public class RequestReportInventoryRecordVO extends BaseGoodsRequestParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "每页显示数据", required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "页码", required = true)
    private Integer pageNo;

    @ApiModelProperty(value = "创建时间从", required = false)
    private String invDateStart;

    @ApiModelProperty(value = "创建时间至", required = false)
    private String invDateEnd;

    @ApiModelProperty(value = "登记日期从", required = false)
    private String registerDateStart;

    @ApiModelProperty(value = "登记日期至", required = false)
    private String registerDateEnd;

    @ApiModelProperty(value = "处理日期从", required = false)
    private String handleDateStart;

    @ApiModelProperty(value = "处理日期至", required = false)
    private String handleDateEnd;

    @ApiModelProperty(value = "过账日期从", required = false)
    private String postDateStart;

    @ApiModelProperty(value = "过账日期至", required = false)
    private String postDateEnd;

    @ApiModelProperty(value = "盘点单号", required = false)
    private String code;

    @ApiModelProperty(value = "创建人员", required = false)
    private String createrName;

    @ApiModelProperty(value = "登记人员", required = false)
    private String registerManName;

    @ApiModelProperty(value = "盘点人员", required = false)
    private String invManName;

    @ApiModelProperty(value = "复盘人员", required = false)
    private String secondInvManName;

    @ApiModelProperty(value = "过账人员", required = false)
    private String postManName;

    @ApiModelProperty(value = "状态（-1-全部；0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）", required = false)
    private Integer status;

    @ApiModelProperty(required = false, value = "企业id，前端不用传")
    private Long enterpriseId;

    /**
     * 搜索类型，1-搜索总部及总部下所有门店信息，2-仅当前企业信息
     */
    private Integer type;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getInvDateStart() {
        return invDateStart;
    }

    public void setInvDateStart(String invDateStart) {
        this.invDateStart = invDateStart;
    }

    public String getInvDateEnd() {
        return invDateEnd;
    }

    public void setInvDateEnd(String invDateEnd) {
        this.invDateEnd = invDateEnd;
    }

    public String getRegisterDateStart() {
        return registerDateStart;
    }

    public void setRegisterDateStart(String registerDateStart) {
        this.registerDateStart = registerDateStart;
    }

    public String getRegisterDateEnd() {
        return registerDateEnd;
    }

    public void setRegisterDateEnd(String registerDateEnd) {
        this.registerDateEnd = registerDateEnd;
    }

    public String getHandleDateStart() {
        return handleDateStart;
    }

    public void setHandleDateStart(String handleDateStart) {
        this.handleDateStart = handleDateStart;
    }

    public String getHandleDateEnd() {
        return handleDateEnd;
    }

    public void setHandleDateEnd(String handleDateEnd) {
        this.handleDateEnd = handleDateEnd;
    }

    public String getPostDateStart() {
        return postDateStart;
    }

    public void setPostDateStart(String postDateStart) {
        this.postDateStart = postDateStart;
    }

    public String getPostDateEnd() {
        return postDateEnd;
    }

    public void setPostDateEnd(String postDateEnd) {
        this.postDateEnd = postDateEnd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public String getRegisterManName() {
        return registerManName;
    }

    public void setRegisterManName(String registerManName) {
        this.registerManName = registerManName;
    }

    public String getInvManName() {
        return invManName;
    }

    public void setInvManName(String invManName) {
        this.invManName = invManName;
    }

    public String getSecondInvManName() {
        return secondInvManName;
    }

    public void setSecondInvManName(String secondInvManName) {
        this.secondInvManName = secondInvManName;
    }

    public String getPostManName() {
        return postManName;
    }

    public void setPostManName(String postManName) {
        this.postManName = postManName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}