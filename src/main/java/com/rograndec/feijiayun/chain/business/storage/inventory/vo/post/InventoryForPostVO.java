package com.rograndec.feijiayun.chain.business.storage.inventory.vo.post;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_inventory
 * 
 * 
 * @author ST
 * 
 * 2017-09-29
 */
public class InventoryForPostVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;




    /**
     * 盘点单号
     */
    @ApiModelProperty(value = "盘点单号")
    private String code;



    /**
     * 过账日期
     */
    @ApiModelProperty(value = "过账日期")
    private Date postDate;

    /**
     * 过账人员ID
     */
    @ApiModelProperty(value = "过账人员ID")
    private Long postManId;


    /**
     * 过账人员名称
     */
    @ApiModelProperty(value = "过账人员名称")
    private String postManName;

    /**
     * 账面数量合计
     */
    @ApiModelProperty(value = "账面数量合计")
    private BigDecimal quantityTotal;


    /**
     * 实盘数量合计
     */
    @ApiModelProperty(value = "实盘数量合计")
    private BigDecimal invQuantityTotal;

    /**
     * 损益数量合计
     */
    @ApiModelProperty(value = "损益数量合计")
    private BigDecimal diffQuantityTotal;

    /**
     * 损益金额合计
     */
    @ApiModelProperty(value = "损益金额合计")
    private BigDecimal diffAmountTotal;

    /**
     * 不含税损益金额合计
     */
    @ApiModelProperty(value = "不含税损益金额合计")
    private BigDecimal diffNotaxAmountTotal;


    /**
     * 损益税额合计
     */
    @ApiModelProperty(value = "损益税额合计")
    private BigDecimal diffTaxAmountTotal;


    /**
     * 状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）
     */
    @ApiModelProperty(value = "状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;


    private List<InventoryDetailForPostVO> postVOList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Long getPostManId() {
        return postManId;
    }

    public void setPostManId(Long postManId) {
        this.postManId = postManId;
    }

    public String getPostManName() {
        return postManName;
    }

    public void setPostManName(String postManName) {
        this.postManName = postManName;
    }

    public BigDecimal getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(BigDecimal quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public BigDecimal getInvQuantityTotal() {
        return invQuantityTotal;
    }

    public void setInvQuantityTotal(BigDecimal invQuantityTotal) {
        this.invQuantityTotal = invQuantityTotal;
    }

    public BigDecimal getDiffQuantityTotal() {
        return diffQuantityTotal;
    }

    public void setDiffQuantityTotal(BigDecimal diffQuantityTotal) {
        this.diffQuantityTotal = diffQuantityTotal;
    }



    public BigDecimal getDiffAmountTotal() {
        return diffAmountTotal;
    }

    public void setDiffAmountTotal(BigDecimal diffAmountTotal) {
        this.diffAmountTotal = diffAmountTotal;
    }

    public BigDecimal getDiffNotaxAmountTotal() {
        return diffNotaxAmountTotal;
    }

    public void setDiffNotaxAmountTotal(BigDecimal diffNotaxAmountTotal) {
        this.diffNotaxAmountTotal = diffNotaxAmountTotal;
    }

    public BigDecimal getDiffTaxAmountTotal() {
        return diffTaxAmountTotal;
    }

    public void setDiffTaxAmountTotal(BigDecimal diffTaxAmountTotal) {
        this.diffTaxAmountTotal = diffTaxAmountTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}