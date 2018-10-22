package com.rograndec.feijiayun.chain.business.goods.price.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 *
 * 修改时价格清单明细价格不可显示并且不可以被修改的价格字段
 * Created by zhaiwei on 2017/9/7.
 */
@ApiModel
public class PriceOrderInitVO {

    /**
     * 不可被显示并且不可修改字段map,key为字段属性名
     */
    @ApiModelProperty(value = "价格类型为基本价格类型 不可被显示并且不可修改字段map,key为字段属性名")
    private List<String> notShowAndUpdateBaseParam;
    @ApiModelProperty(value = "价格类型为配货类型 不可被显示并且不可修改字段map,key为字段属性名")
    private List<String> notShowAndUpdateDisParam;

    @ApiModelProperty(value = "价格类型为零售类型 不可被显示并且不可修改字段map,key为字段属性名")
    private List<String> notShowAndUpdateRetailParam;

    public List<String> getNotShowAndUpdateBaseParam() {
        return notShowAndUpdateBaseParam;
    }

    public void setNotShowAndUpdateBaseParam(List<String> notShowAndUpdateBaseParam) {
        this.notShowAndUpdateBaseParam = notShowAndUpdateBaseParam;
    }

    public List<String> getNotShowAndUpdateDisParam() {
        return notShowAndUpdateDisParam;
    }

    public void setNotShowAndUpdateDisParam(List<String> notShowAndUpdateDisParam) {
        this.notShowAndUpdateDisParam = notShowAndUpdateDisParam;
    }

    public List<String> getNotShowAndUpdateRetailParam() {
        return notShowAndUpdateRetailParam;
    }

    public void setNotShowAndUpdateRetailParam(List<String> notShowAndUpdateRetailParam) {
        this.notShowAndUpdateRetailParam = notShowAndUpdateRetailParam;
    }
}
