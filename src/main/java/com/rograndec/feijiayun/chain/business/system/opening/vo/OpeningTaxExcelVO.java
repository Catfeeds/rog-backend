package com.rograndec.feijiayun.chain.business.system.opening.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 期初应付/应收导入返回的信息实体
 */
public class OpeningTaxExcelVO implements Serializable {

    @ApiModelProperty(value = "导入时存储数据的key值，在导入成功数据或者导出合格/不合格期初税额信息时传递该值获取对应的数据")
    private String key;
    //合格数据
    private List<OpeningTaxDetailVO> qualifiedList = new ArrayList<>();
    //不合格数据
    private List<OpeningTaxDetailVO> disqualificationList = new ArrayList<>();


    // 合格条数
    @ApiModelProperty(value = "合格条数")
    private Integer qualifiedCount;

    // 不合格条数
    @ApiModelProperty(value = "不合格条数")
    private Integer disqualificationCount;

    @ApiModelProperty(value = "总数")
    private Integer totalCount;

    public Integer getTotalCount() {
        return qualifiedCount + disqualificationCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<OpeningTaxDetailVO> getQualifiedList() {
        return qualifiedList;
    }

    public void setQualifiedList(List<OpeningTaxDetailVO> qualifiedList) {
        this.qualifiedList = qualifiedList;
    }

    public List<OpeningTaxDetailVO> getDisqualificationList() {
        return disqualificationList;
    }

    public void setDisqualificationList(List<OpeningTaxDetailVO> disqualificationList) {
        this.disqualificationList = disqualificationList;
    }
}