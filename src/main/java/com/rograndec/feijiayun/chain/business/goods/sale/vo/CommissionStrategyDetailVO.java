package com.rograndec.feijiayun.chain.business.goods.sale.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class CommissionStrategyDetailVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 提成设置ID
     */
    @ApiModelProperty(value = "提成设置ID")
    private Long setId;

    /**
     * 提成基数
     */
    @ApiModelProperty(value = "提成基数")
    @NotNull
    private BigDecimal base;

    /**
     * 提成基数结束
     */
    @NotNull
    @ApiModelProperty(value = "提成基数结束")
    private BigDecimal baseTo;

    /**
     * 提成比例
     */
    @ApiModelProperty(value = "提成比例")
    private BigDecimal ratio;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * saas_royalty_strategy_detail
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
     * 提成设置ID
     * @return set_id 提成设置ID
     */
    public Long getSetId() {
        return setId;
    }

    /**
     * 提成设置ID
     * @param setId 提成设置ID
     */
    public void setSetId(Long setId) {
        this.setId = setId;
    }

    /**
     * 提成基数
     * @return base 提成基数
     */
    public BigDecimal getBase() {
        return base;
    }

    /**
     * 提成基数
     * @param base 提成基数
     */
    public void setBase(BigDecimal base) {
        this.base = base;
    }

    /**
     * 提成比例
     * @return ratio 提成比例
     */
    public BigDecimal getRatio() {
        return ratio;
    }

    /**
     * 提成比例
     * @param ratio 提成比例
     */
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
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

    public BigDecimal getBaseTo() {
        return baseTo;
    }

    public void setBaseTo(BigDecimal baseTo) {
        this.baseTo = baseTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommissionStrategyDetailVO that = (CommissionStrategyDetailVO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(setId, that.setId) &&
                Objects.equals(base, that.base) &&
                Objects.equals(baseTo, that.baseTo) &&
                Objects.equals(ratio, that.ratio) &&
                Objects.equals(status, that.status) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, setId, base, baseTo, ratio, status, remark);
    }

    /**
     *
     * @mbg.generated
     */

    @Override
    public String toString() {
        return "RoyaltyStrategyDetailVO{" +
                "id=" + id +
                ", setId=" + setId +
                ", base=" + base +
                ", baseTo=" + baseTo +
                ", ratio=" + ratio +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                '}';
    }
}