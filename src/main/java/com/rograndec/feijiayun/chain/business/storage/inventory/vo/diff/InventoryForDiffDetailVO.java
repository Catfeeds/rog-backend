package com.rograndec.feijiayun.chain.business.storage.inventory.vo.diff;

import com.rograndec.feijiayun.chain.business.storage.inventory.vo.InventoryDetailForOrderDetailVO;
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
public class InventoryForDiffDetailVO implements Serializable {
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
     * 处理日期
     */
    @ApiModelProperty(value = "处理日期")
    private Date handleDate;

    /**
     * 处理人员ID
     */
    @ApiModelProperty(value = "处理人员ID")
    private Long handleManId;


    /**
     * 处理人员名称
     */
    @ApiModelProperty(value = "处理人员名称")
    private String handleManName;
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
     * 状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）
     */
    @ApiModelProperty(value = "状态（0-待登记；1-待处理；2-待过账；3-已完成；4-已取消）")
    private Integer status;

    private List<InventoryDetailForOrderDetailVO> detailForAddVOList;

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

    public Date getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }

    public Long getHandleManId() {
        return handleManId;
    }

    public void setHandleManId(Long handleManId) {
        this.handleManId = handleManId;
    }

    public String getHandleManName() {
        return handleManName;
    }

    public void setHandleManName(String handleManName) {
        this.handleManName = handleManName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<InventoryDetailForOrderDetailVO> getDetailForAddVOList() {
        return detailForAddVOList;
    }

    public void setDetailForAddVOList(List<InventoryDetailForOrderDetailVO> detailForAddVOList) {
        this.detailForAddVOList = detailForAddVOList;
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
}