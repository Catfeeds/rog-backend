package com.rograndec.feijiayun.chain.business.report.quality.aftersale.vo;

import com.rograndec.feijiayun.chain.business.report.common.vo.BaseGoodsRequestParam;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class RecallRecordReportReqVO extends BaseGoodsRequestParam{


    @ApiModelProperty("当前页码(必传)")
    private Integer pageNo;

    @ApiModelProperty("显示条数(必传)")
    private Integer pageSize;

    @ApiModelProperty("召回日期(从)")
    private String startDate;

    @ApiModelProperty("召回日期(至)")
    private String endDate;

    @ApiModelProperty("组织机构类型（0-总部；1-自营店；2-加盟店）")
    private Integer chainType;

    @ApiModelProperty("组织机构编码")
    private String enterpriseCode;

    @ApiModelProperty("组织机构名称")
    private String enterpriseName;

    @ApiModelProperty("召回单号")
    private String code;

    @ApiModelProperty("召回负责人")
    private String recallMan;

    @ApiModelProperty("召回单位")
    private String recallCompany;

    @ApiModelProperty("召回级别")
    private Integer recallLevel;

    @ApiModelProperty("按某一列排序(日期：date,单号:code )")
    private String order;

    @ApiModelProperty("排序方式（升序：asc,降序：desc）")
    private String sort;

    @ApiModelProperty("不需要传")
    private Long enterpriseId;

    @ApiModelProperty("不需要传")
    private Date start;

    @ApiModelProperty("不需要传")
    private Date end;


    @ApiModelProperty("后台用:1-获取总部及门店所有的，2-获取当前企业的")
    private Integer Type;

    private Integer pageStart;

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public String getRecallMan() {
        return recallMan;
    }

    public void setRecallMan(String recallMan) {
        this.recallMan = recallMan;
    }

    public String getRecallCompany() {
        return recallCompany;
    }

    public void setRecallCompany(String recallCompany) {
        this.recallCompany = recallCompany;
    }

    public Integer getRecallLevel() {
        return recallLevel;
    }

    public void setRecallLevel(Integer recallLevel) {
        this.recallLevel = recallLevel;
    }

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

    @Override
    public Integer getChainType() {
        return chainType;
    }

    @Override
    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    @Override
    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    @Override
    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    @Override
    public String getEnterpriseName() {
        return enterpriseName;
    }

    @Override
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }


    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
