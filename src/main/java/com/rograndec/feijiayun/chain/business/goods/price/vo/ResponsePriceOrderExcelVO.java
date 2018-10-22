package com.rograndec.feijiayun.chain.business.goods.price.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 价格清单导入excel信息实体
 */
public class ResponsePriceOrderExcelVO implements Serializable {

    @ApiModelProperty(value = "导入时存储数据的key值，在导入成功数据或者导出合格/不合格商品信息时传递该值获取对应的数据")
    private String key;


    // 合格条数
    @ApiModelProperty(value = "合格条数")
    private Integer qualifiedCount = 0;

    // 不合格条数
    @ApiModelProperty(value = "不合格条数")
    private Integer disqualificationCount = 0;

    @ApiModelProperty(value = "总数")
    private Integer totalCount = 0;
    @ApiModelProperty(value = "价格清单id")
    private Long id;
    @ApiModelProperty(value = "不合格提示")
    private List<String> disqualificationMsg;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getDisqualificationMsg() {
        return disqualificationMsg;
    }

    public void setDisqualificationMsg(List<String> disqualificationMsg) {
        this.disqualificationMsg = disqualificationMsg;
    }
}
