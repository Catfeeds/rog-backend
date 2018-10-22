package com.rograndec.feijiayun.chain.business.member.info.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseMemberExcelVO implements Serializable{
    @ApiModelProperty(value = "导入时存储数据的key值，在导入成功数据或者导出合格/不合格会员信息时传递该值获取对应的数据")
    private String timestamp;
    //合格产品
    private List<MemberExcelVO> qualifiedMemberList = new ArrayList<>();

    // 合格条数
    private Integer qualifiedCount;

    // 不合格条数
    private Integer disqualificationCount;

    //不合格产品
    private List<MemberExcelVO> disqualificationMemberList = new ArrayList<>();

    @ApiModelProperty(value = "总数")
    private Integer totalCount;

    public Integer getTotalCount() {
        return qualifiedCount + disqualificationCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<MemberExcelVO> getQualifiedMemberList() {
        return qualifiedMemberList;
    }

    public void setQualifiedMemberList(List<MemberExcelVO> qualifiedMemberList) {
        this.qualifiedMemberList = qualifiedMemberList;
    }

    public List<MemberExcelVO> getDisqualificationMemberList() {
        return disqualificationMemberList;
    }

    public void setDisqualificationMemberList(List<MemberExcelVO> disqualificationMemberList) {
        this.disqualificationMemberList = disqualificationMemberList;
    }

    public Integer getQualifiedCount() {
        return qualifiedCount;
    }

    public void setQualifiedCount(Integer qualifiedCount) {
        this.qualifiedCount = qualifiedCount;
    }

    public Integer getDisqualificationCount() {
        return disqualificationCount;
    }

    public void setDisqualificationCount(Integer disqualificationCount) {
        this.disqualificationCount = disqualificationCount;
    }

}
