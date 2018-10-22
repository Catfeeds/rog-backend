package com.rograndec.feijiayun.chain.business.goods.sale.vo;

import java.io.Serializable;
import java.util.Date;

public class SpecialPriceStrategyVO implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 变价（0-禁止；1-允许）
     */
    private Integer valence;

    /**
     * 折扣（0-禁止；1-允许）
     */
    private Integer discount;

    /**
     * 会员策略（0-禁止；1-允许）
     */
    private Integer memberStrategy;

    /**
     * 会员日
     */
    private Integer memberDay;

    /**
     * 促销活动（0-禁用；1-启用）
     */
    private Integer salesPromotion;

    /**
     * 状态（0-禁用；1-启用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * saas_special_price_strategy
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 编码
     * @return code 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 编码
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 变价（0-禁止；1-允许）
     * @return valence 变价（0-禁止；1-允许）
     */
    public Integer getValence() {
        return valence;
    }

    /**
     * 变价（0-禁止；1-允许）
     * @param valence 变价（0-禁止；1-允许）
     */
    public void setValence(Integer valence) {
        this.valence = valence;
    }

    /**
     * 折扣（0-禁止；1-允许）
     * @return discount 折扣（0-禁止；1-允许）
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * 折扣（0-禁止；1-允许）
     * @param discount 折扣（0-禁止；1-允许）
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * 会员策略（0-禁止；1-允许）
     * @return member_strategy 会员策略（0-禁止；1-允许）
     */
    public Integer getMemberStrategy() {
        return memberStrategy;
    }

    /**
     * 会员策略（0-禁止；1-允许）
     * @param memberStrategy 会员策略（0-禁止；1-允许）
     */
    public void setMemberStrategy(Integer memberStrategy) {
        this.memberStrategy = memberStrategy;
    }

    /**
     * 会员日
     * @return member_day 会员日
     */
    public Integer getMemberDay() {
        return memberDay;
    }

    /**
     * 会员日
     * @param memberDay 会员日
     */
    public void setMemberDay(Integer memberDay) {
        this.memberDay = memberDay;
    }

    /**
     * 促销活动（0-禁用；1-启用）
     * @return sales_promotion 促销活动（0-禁用；1-启用）
     */
    public Integer getSalesPromotion() {
        return salesPromotion;
    }

    /**
     * 促销活动（0-禁用；1-启用）
     * @param salesPromotion 促销活动（0-禁用；1-启用）
     */
    public void setSalesPromotion(Integer salesPromotion) {
        this.salesPromotion = salesPromotion;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @return status 状态（0-禁用；1-启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @param status 状态（0-禁用；1-启用）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }


    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", valence=").append(valence);
        sb.append(", discount=").append(discount);
        sb.append(", memberStrategy=").append(memberStrategy);
        sb.append(", memberDay=").append(memberDay);
        sb.append(", salesPromotion=").append(salesPromotion);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecialPriceStrategyVO that = (SpecialPriceStrategyVO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (valence != null ? !valence.equals(that.valence) : that.valence != null) return false;
        if (discount != null ? !discount.equals(that.discount) : that.discount != null) return false;
        if (memberStrategy != null ? !memberStrategy.equals(that.memberStrategy) : that.memberStrategy != null)
            return false;
        if (memberDay != null ? !memberDay.equals(that.memberDay) : that.memberDay != null) return false;
        if (salesPromotion != null ? !salesPromotion.equals(that.salesPromotion) : that.salesPromotion != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return remark != null ? remark.equals(that.remark) : that.remark == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (valence != null ? valence.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (memberStrategy != null ? memberStrategy.hashCode() : 0);
        result = 31 * result + (memberDay != null ? memberDay.hashCode() : 0);
        result = 31 * result + (salesPromotion != null ? salesPromotion.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}