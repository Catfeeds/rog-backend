package com.rograndec.feijiayun.chain.business.member.info.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述：
 * Created by ST on 2017/9/23 14:43
 */

public class MemberForPrescVO {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 会员姓名
     */
    @ApiModelProperty(value = "会员姓名")
    private String name;

    /**
     * 性别（0-男；1-女；2-其它）
     */
    @ApiModelProperty(value = "性别（0-男；1-女；2-其它）")
    private Integer sex;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idNum;

    /**
     * 出生日期
     */
    @ApiModelProperty(value = "出生日期")
    private Date birthDate;

    @ApiModelProperty(required = true, value = "价格策略（0-零售价；1-会员价）")
    private int priceStrategy;

    @ApiModelProperty(required = true, value = "折让策略")
    private BigDecimal discountStrategy;

    @ApiModelProperty(required = true, value = "价格策略（0-零售价；1-会员价）")
    private String priceStrategyStr;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public int getPriceStrategy() {
        return priceStrategy;
    }

    public void setPriceStrategy(int priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    public BigDecimal getDiscountStrategy() {
        if(discountStrategy == null){
            discountStrategy = new BigDecimal(100);
        }
        return discountStrategy;
    }

    public void setDiscountStrategy(BigDecimal discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public String getPriceStrategyStr() {
        return priceStrategyStr;
    }

    public void setPriceStrategyStr(String priceStrategyStr) {
        this.priceStrategyStr = priceStrategyStr;
    }
}