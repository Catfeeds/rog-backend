package com.rograndec.feijiayun.chain.business.distr.branch.vo;

import com.rograndec.feijiayun.chain.common.constant.status.DistrInStatus;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dudy on 2017/10/8.
 */
public class DistrInNoticeRequestVO  implements Serializable{

    @ApiModelProperty("当前页码(必传)")
    private Integer  pageNo;

    @ApiModelProperty("显示条数(必传)")
    private Integer pageSize;

    @ApiModelProperty("开始时间")
    private String startDate;

    @ApiModelProperty("结束时间")
    private  String endDate;

    @ApiModelProperty(value = "配进订单单号")
    private String code;

    @ApiModelProperty(value = "配进订单ID集合，以','分隔")
    private String noticeIds;


    @ApiModelProperty(value = "配货单位编码")
    private String distrUnitCode;

    @ApiModelProperty(value = "配货单位名称")
    private String distrUnitName;


    @ApiModelProperty(value = "配货类型（0-总部配送；1-分店间调剂；2-直调配送）")
    private Integer distrType;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态: 21-待审核,22-审核通过,23-审核驳回,30-待收货,31-待验收（已收货）,32-待入库（已验收）,33-已完成（已入库）,34-已取消")
    private Integer status;


    private List<Integer> statusList;


    @ApiModelProperty(value = "配进人员名称")
    private String storageManName;


    @ApiModelProperty("按某一列排序(日期：date(默认),单号:code )")
    private String order;

    @ApiModelProperty("排序方式（升序：asc,降序：desc(默认)）")
    private String sort;

    /**
     * 数据库查询转换用
     */
    @ApiModelProperty("不需要传")
    private Date start;

    /**
     * 数据库查询转换用
     */
    @ApiModelProperty("不需要传")
    private  Date end;

    @ApiModelProperty("不需要传")
    private Integer pageStart;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "不需要传")
    private Long enterpriseId;

    @ApiModelProperty(value = "不需要传")
    private Integer finishStatus;


    public List<Integer> getStatusList() {

        if (status == null) return null;

        List<Integer> list = new ArrayList<>();
        list.add(status);
        if(DistrInStatus.FINISHED == status){
            list.add(DistrInStatus.WAIT_BILL);
            list.add(DistrInStatus.PART_BILL);
        }
        return list;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getFinishStatus() {
        return finishStatus;
    }

    public void setFinishStatus(Integer finishStatus) {
        this.finishStatus = finishStatus;
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


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDistrUnitCode() {
        return distrUnitCode;
    }

    public void setDistrUnitCode(String distrUnitCode) {
        this.distrUnitCode = distrUnitCode;
    }

    public String getDistrUnitName() {
        return distrUnitName;
    }

    public void setDistrUnitName(String distrUnitName) {
        this.distrUnitName = distrUnitName;
    }

    public Integer getDistrType() {
        return distrType;
    }

    public void setDistrType(Integer distrType) {
        this.distrType = distrType;
    }


    public String getStorageManName() {
        return storageManName;
    }

    public void setStorageManName(String storageManName) {
        this.storageManName = storageManName;
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

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNoticeIds() {
        return noticeIds;
    }

    public void setNoticeIds(String noticeIds) {
        this.noticeIds = noticeIds;
    }
}
