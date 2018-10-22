package com.rograndec.feijiayun.chain.business.system.opening.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品导入返回的商品信息实体
 * Created by ST on 2017/9/10.
 */
public class ResponseOpeningGoodsExcelVO implements Serializable {

    @ApiModelProperty(value = "导入时存储数据的key值，在导入成功数据或者导出合格/不合格商品信息时传递该值获取对应的数据")
    private String timestamp;
    //合格产品
    private List<OpeningGoodsExcelVO> qualifiedGoodsList = new ArrayList<>();
    //不合格产品
    private List<OpeningGoodsExcelVO> disqualificationGoodsList = new ArrayList<>();


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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<OpeningGoodsExcelVO> getQualifiedGoodsList() {
        return qualifiedGoodsList;
    }

    public void setQualifiedGoodsList(List<OpeningGoodsExcelVO> qualifiedGoodsList) {
        this.qualifiedGoodsList = qualifiedGoodsList;
    }

    public List<OpeningGoodsExcelVO> getDisqualificationGoodsList() {
        return disqualificationGoodsList;
    }

    public void setDisqualificationGoodsList(List<OpeningGoodsExcelVO> disqualificationGoodsList) {
        this.disqualificationGoodsList = disqualificationGoodsList;
    }
}