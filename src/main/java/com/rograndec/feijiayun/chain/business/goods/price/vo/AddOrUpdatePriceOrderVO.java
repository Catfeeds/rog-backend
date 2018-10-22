package com.rograndec.feijiayun.chain.business.goods.price.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 新增和保存价格清单VO
 */
@ApiModel
public class AddOrUpdatePriceOrderVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "价格清单id,修改时需要传入,新增时不需要", required = false)
    private Long id;

    /**
     * 基础价格清单ID（为0代表无）
     */
    @ApiModelProperty(value = "基础价格清单ID（为0代表无）", required = false)
    private Long parentOrderId = 0L;

    /**
     * 清单编码
     */
    @ApiModelProperty(value = "清单编码,新增时需要传递,修改时不需要传递,该字段不允许修改", required = false)
    @NotNull(message = "清单编码不能为空")
    @Size(max = 20,message = "清单编码超长,长度为20")
    private String code;

    /**
     * 清单名称
     */
    @ApiModelProperty(value = "清单名称", required = true)
    @NotNull(message = "清单名称不能为空")
    @Size(min = 1,message = "清单名称不能为空")
    private String name;

    /**
     * 价格类型（0-基础价格；1-配货价格；2-零售价格）
     */
    @ApiModelProperty(value = "价格类型（0-基础价格；1-配货价格；2-零售价格）新增必须传递,修改不需要传递", required = false)
    private Integer priceType;

    /**
     * 状态（0-禁用；1-启用）
     */
    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "状态（0-禁用；1-启用）", required = true)
    private Integer status;

    @ApiModelProperty(value = "价格清单明细,修改时候需要传递,修改时传递已经修改的明细,新增不需要", required = false)
    private List<UpdatePriceOrderDetailVO> updatePriceOrderDetailVOS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentOrderId() {
        return parentOrderId;
    }

    public void setParentOrderId(Long parentOrderId) {
        this.parentOrderId = parentOrderId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriceType() {
        return priceType;
    }

    public void setPriceType(Integer priceType) {
        this.priceType = priceType;
    }

    public List<UpdatePriceOrderDetailVO> getUpdatePriceOrderDetailVOS() {
        return updatePriceOrderDetailVOS;
    }

    public void setUpdatePriceOrderDetailVOS(List<UpdatePriceOrderDetailVO> updatePriceOrderDetailVOS) {
        this.updatePriceOrderDetailVOS = updatePriceOrderDetailVOS;
    }
}