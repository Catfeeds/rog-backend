package com.rograndec.feijiayun.chain.business.member.info.vo;

import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

public class MadeCardInfoVO implements Serializable,Cloneable{

    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 级别名称
     */
    @ApiModelProperty(value = "级别名称")
    private String levelName;

    /**
     * 类别（0-积分+储值；1-仅积分；2-仅储值）
     */
    @ApiModelProperty(value = "类别（0-积分+储值；1-仅积分；2-仅储值）")
    private Integer type;

    /**
     * 类别（0-积分+储值；1-仅积分；2-仅储值）
     */
    @ApiModelProperty(value = "类别[用于给前台显示]（0-积分+储值；1-仅积分；2-仅储值）")
    private String typeName;

    /**
     * 价格策略（0-零售价；1-会员价）
     */
    @ApiModelProperty(value = "价格策略（0-零售价；1-会员价）")
    private Integer priceStrategy;

    /**
     * 价格策略（0-零售价；1-会员价）
     */
    @ApiModelProperty(value = "价格策略[用于给前台显示]（0-零售价；1-会员价）")
    private String priceStrategyName;

    /**
     * 价格策略
     */
    @ApiModelProperty(value = "折让策略")
    private BigDecimal discountStrategy;

    /**
     * 折让策略 + %
     */
    @ApiModelProperty(value = "[用于给前台显示]折让策略")
    private String discountStrategyName;

    /**
     * 积分策略金额
     */
    @ApiModelProperty(value = "积分策略金额")
    private BigDecimal amount;

    /**
     * 积分策略积分
     */
    @ApiModelProperty(value = "积分策略积分")
    private BigDecimal integral;

    /**
     * amount元积 + integral + 分
     */
    @ApiModelProperty(value = "积分策略[给前台显示]")
    private String amountIntegralName;

    /**
     * 储值金额
     */
    @ApiModelProperty(value = "储值金额[在type=1的情况不显示，其他情况前台默认值给0.00]")
    private BigDecimal storedAmount;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码[在type = 1的情况下不显示，其他情况前台默认值显示8个0]")
    private String password;

    /**
     * 密码确认
     */
    @ApiModelProperty(value = "密码确认[保存之前前台确认两次填的是否一致]")
    private String passwordConfirm;

    /**
     * 初始卡号
     */
    @ApiModelProperty(value = "初始卡号 [必填并且后四位必须为数字类似ABC0001，不是的话提示它内容格式不符合要求]")
    private String startCardCode;

    /**
     * 制卡数量
     */
    @ApiModelProperty(value = "制卡数量[必填 并且 为正整数]")
    private Integer cardNum;

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

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getPriceStrategy() {
        return priceStrategy;
    }

    public void setPriceStrategy(Integer priceStrategy) {
        this.priceStrategy = priceStrategy;
    }

    public String getPriceStrategyName() {
        return priceStrategyName;
    }

    public void setPriceStrategyName(String priceStrategyName) {
        this.priceStrategyName = priceStrategyName;
    }

    public BigDecimal getDiscountStrategy() {
        return discountStrategy;
    }

    public void setDiscountStrategy(BigDecimal discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public String getDiscountStrategyName() {
        return discountStrategyName;
    }

    public void setDiscountStrategyName(String discountStrategyName) {
        this.discountStrategyName = discountStrategyName;
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

    public String getAmountIntegralName() {
        return amountIntegralName;
    }

    public void setAmountIntegralName(String amountIntegralName) {
        this.amountIntegralName = amountIntegralName;
    }

    public BigDecimal getStoredAmount() {
        return storedAmount;
    }

    public void setStoredAmount(BigDecimal storedAmount) {
        this.storedAmount = storedAmount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getStartCardCode() {
        return startCardCode;
    }

    public void setStartCardCode(String startCardCode) {
        this.startCardCode = startCardCode;
    }

    public Integer getCardNum() {
        return cardNum;
    }

    public void setCardNum(Integer cardNum) {
        this.cardNum = cardNum;
    }

    public static MemberInfo convertEntity(MadeCardInfoVO madeCardInfoVO, UserVO loginUser, Integer type) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setEnterpriseId(loginUser.getEnterpriseId());
        memberInfo.setParentId(loginUser.getParentId());
        memberInfo.setCardTypeId(madeCardInfoVO.getId());
        memberInfo.setCardTypeName(madeCardInfoVO.getName());
        memberInfo.setCardCode(madeCardInfoVO.getStartCardCode());
        /**
         * 因为制卡时只能制会员卡 0-会员卡 1-手机号
         */
        memberInfo.setCardCodeType(0);
        /**
         * 并且状态为未发卡
         */
        memberInfo.setStatus(1);
        if (1 != type){
            memberInfo.setStoredAmount(madeCardInfoVO.getStoredAmount());
            //初始化累计储值和当前储值
            memberInfo.setTotalStoredAmount(madeCardInfoVO.getStoredAmount());
            memberInfo.setCurrentStoredAmount(madeCardInfoVO.getStoredAmount());
            memberInfo.setTotalIntegral(BigDecimal.ZERO);
            memberInfo.setCurrentIntegral(BigDecimal.ZERO);
            memberInfo.setPassword(madeCardInfoVO.getPassword());
            memberInfo.setPasswordConfirm(madeCardInfoVO.getPasswordConfirm());
        }else{
            //初始化累计储值和当前储值
            memberInfo.setTotalStoredAmount(BigDecimal.ZERO);
            memberInfo.setCurrentStoredAmount(BigDecimal.ZERO);
            memberInfo.setTotalIntegral(BigDecimal.ZERO);
            memberInfo.setCurrentIntegral(BigDecimal.ZERO);
        }
        return memberInfo;
    }

    @Override
    public Object clone() {
        Object obj = null;
        try{
            obj = (MadeCardInfoVO)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
