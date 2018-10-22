package com.rograndec.feijiayun.chain.business.goods.info.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品导入返回的商品信息实体
 * Created by ST on 2017/9/10.
 */
public class ResponseGoodsExcelVO implements Serializable {

    @ApiModelProperty(value = "导入时存储数据的key值，在导入成功数据或者导出合格/不合格商品信息时传递该值获取对应的数据")
    private String timestamp;
    //合格产品
    private List<GoodsExcelVO> qualifiedGoodsList = new ArrayList<>();

    // 合格条数
    private Integer qualifiedCount;

    // 不合格条数
    private Integer disqualificationCount;

    //不合格产品
    private List<GoodsExcelVO> disqualificationGoodsList = new ArrayList<>();


    @ApiModelProperty(value = "总数")
    private Integer totalCount;

    public Integer getTotalCount() {
        return (qualifiedCount == null ? 0 : qualifiedCount) + (disqualificationCount == null ? 0 : disqualificationCount);
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

    public List<GoodsExcelVO> getQualifiedGoodsList() {
        return qualifiedGoodsList;
    }

    public void setQualifiedGoodsList(List<GoodsExcelVO> qualifiedGoodsList) {
        this.qualifiedGoodsList = qualifiedGoodsList;
    }

    public List<GoodsExcelVO> getDisqualificationGoodsList() {
        return disqualificationGoodsList;
    }

    public void setDisqualificationGoodsList(List<GoodsExcelVO> disqualificationGoodsList) {
        this.disqualificationGoodsList = disqualificationGoodsList;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}