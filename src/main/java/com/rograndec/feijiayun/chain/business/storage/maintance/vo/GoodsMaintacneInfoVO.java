package com.rograndec.feijiayun.chain.business.storage.maintance.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class GoodsMaintacneInfoVO implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 总单信息
     */
    @ApiModelProperty(value = "总单信息")
    private GoodsMaintanceVO goodsMaintanceVO;

    /**
     * 细单信息集合
     */
    @ApiModelProperty(value = "细单信息集合")
    private List<GoodsMaintanceDetailVO> goodsMaintanceDetailVOS;

    public GoodsMaintanceVO getGoodsMaintanceVO() {
        return goodsMaintanceVO;
    }

    public void setGoodsMaintanceVO(GoodsMaintanceVO goodsMaintanceVO) {
        this.goodsMaintanceVO = goodsMaintanceVO;
    }

    public List<GoodsMaintanceDetailVO> getGoodsMaintanceDetailVOS() {
        return goodsMaintanceDetailVOS;
    }

    public void setGoodsMaintanceDetailVOS(List<GoodsMaintanceDetailVO> goodsMaintanceDetailVOS) {
        this.goodsMaintanceDetailVOS = goodsMaintanceDetailVOS;
    }

    @Override
    public String toString() {
        return "GoodsMaintacneInfoVO{" +
                "goodsMaintanceVO=" + goodsMaintanceVO +
                ", goodsMaintanceDetailVOS=" + goodsMaintanceDetailVOS +
                '}';
    }
}
