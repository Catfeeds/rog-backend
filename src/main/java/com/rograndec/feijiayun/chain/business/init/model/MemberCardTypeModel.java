package com.rograndec.feijiayun.chain.business.init.model;


import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName: MemberCardTypeModel
 * @Description: 会员卡类型初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com
 * @date 2017年10月23日 下午7:02:55
 *
 */
public class MemberCardTypeModel {

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 类别（0-积分+储值；1-仅积分；2-仅储值）
     */
    private Integer type;

    /**
     * 级别名称
     */
    private String levelName;

    /**
     * 价格策略（0-零售价；1-会员价）
     */
    private Integer priceStrategy;

    /**
     * 价格策略
     */
    private BigDecimal discountStrategy;

    /**
     * 积分策略金额
     */
    private BigDecimal amount;

    /**
     * 积分策略积分
     */
    private BigDecimal integral;

    public MemberCardTypeModel(String code, String name, Integer type, String levelName, Integer priceStrategy,
                               BigDecimal discountStrategy, BigDecimal amount, BigDecimal integral) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.levelName = levelName;
        this.priceStrategy = priceStrategy;
        this.discountStrategy = discountStrategy;
        this.amount = amount;
        this.integral = integral;
    }

    public static List<MemberCardTypeModel> build(){
        List<MemberCardTypeModel> mList = new ArrayList<MemberCardTypeModel>();
        mList.add(new MemberCardTypeModel("01","积分储值卡",0,"注册会员",0,new BigDecimal(100), BigDecimal.ONE, BigDecimal.ONE));
        mList.add(new MemberCardTypeModel("02","积分卡",1,"注册会员",0,new BigDecimal(100), BigDecimal.ONE, BigDecimal.ONE));
        mList.add(new MemberCardTypeModel("03","储值卡",2,"注册会员",0,new BigDecimal(100), BigDecimal.ONE, BigDecimal.ONE));
        return mList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getPriceStrategy() {
        return priceStrategy;
    }

    public void setPriceStrategy(Integer priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    public BigDecimal getDiscountStrategy() {
        return discountStrategy;
    }

    public void setDiscountStrategy(BigDecimal discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }


}