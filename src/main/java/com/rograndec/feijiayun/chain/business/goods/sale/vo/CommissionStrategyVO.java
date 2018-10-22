package com.rograndec.feijiayun.chain.business.goods.sale.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class CommissionStrategyVO implements Serializable {
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
     * 方法（0-按销售数量提成；1-按销售金额提成；2-按利润额提成；3-按利润率提成）
     */
    private Integer method;

    /**
     * 方式（0-按比例；1-按金额）
     */
    private Integer mode;

    /**
     * 0-全额提成；1-盈余提成
     */
    private Integer range;

    /**
     * 状态（0-禁用；1-启用）
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 公式
     */
    private String formula;

    /**
     * 是否允许修改
     */
    @ApiModelProperty(value = "是否允许修改 true 允许 false 不允许")
    private Boolean updateFlag;

    /**
     * saas_royalty_strategy
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
     * 方法（0-按销售数量提成；1-按销售金额提成；2-按利润额提成；3-按利润率提成）
     * @return method 方法（0-按销售数量提成；1-按销售金额提成；2-按利润额提成；3-按利润率提成）
     */
    public Integer getMethod() {
        return method;
    }

    /**
     * 方法（0-按销售数量提成；1-按销售金额提成；2-按利润额提成；3-按利润率提成）
     * @param method 方法（0-按销售数量提成；1-按销售金额提成；2-按利润额提成；3-按利润率提成）
     */
    public void setMethod(Integer method) {
        this.method = method;
    }

    /**
     * 方式（0-按比例；1-按金额）
     * @return mode 方式（0-按比例；1-按金额）
     */
    public Integer getMode() {
        return mode;
    }

    /**
     * 方式（0-按比例；1-按金额）
     * @param mode 方式（0-按比例；1-按金额）
     */
    public void setMode(Integer mode) {
        this.mode = mode;
    }

    /**
     * 0-全额提成；1-盈余提成
     * @return range 0-全额提成；1-盈余提成
     */
    public Integer getRange() {
        return range;
    }

    /**
     * 0-全额提成；1-盈余提成
     * @param range 0-全额提成；1-盈余提成
     */
    public void setRange(Integer range) {
        this.range = range;
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

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public Boolean getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Boolean updateFlag) {
        this.updateFlag = updateFlag;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        return "CommissionStrategyVO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", method=" + method +
                ", mode=" + mode +
                ", range=" + range +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", formula='" + formula + '\'' +
                ", updateFlag=" + updateFlag +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommissionStrategyVO that = (CommissionStrategyVO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(method, that.method) &&
                Objects.equals(mode, that.mode) &&
                Objects.equals(range, that.range) &&
                Objects.equals(status, that.status) &&
                Objects.equals(remark, that.remark) &&
                Objects.equals(formula, that.formula) &&
                Objects.equals(updateFlag, that.updateFlag);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, code, name, method, mode, range, status, remark, formula, updateFlag);
    }
}