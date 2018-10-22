package com.rograndec.feijiayun.chain.business.goods.pharmacy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zeshi.sun on 2017/9/9.
 */

public class SaveIncompatibilityHeadVO implements Serializable {

    /**
     * ID
     */
    @ApiModelProperty(required = true, value = "ID")
    private Long id;

    /**
     * 种类
     */
    @ApiModelProperty(required = true, value = "种类")
    private String varity;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(required = true, value = "状态（0-禁用；1-启用）")
    private Integer status;

    List<SaveIncompatibilityGoodsOneVO> goodsList ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVarity() {
        return varity;
    }

    public void setVarity(String varity) {
        this.varity = varity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SaveIncompatibilityGoodsOneVO> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<SaveIncompatibilityGoodsOneVO> goodsList) {
        this.goodsList = goodsList;
    }

    @Override
    public String toString() {
        return "SaveIncompatibilityHeadVO{" +
                "id=" + id +
                ", varity='" + varity + '\'' +
                ", status=" + status +
                ", goodsList=" + goodsList +
                '}';
    }
}
