package com.rograndec.feijiayun.chain.business.goods.price.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel
public class AddOrUpdatePriceAdjustVO implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "价格调整单头id,修改时需要传入,新增时不需要")
    private Long id;


    /**
     * 调整日期
     */
    @ApiModelProperty(value = "调整日期,质量管理开关控制是否只读/编写")
    @NotNull(message = "调整日期不能为空")
    private Date adjustDate;

    /**
     * 调整人员ID
     */
    @ApiModelProperty(value = "调整人员ID",required = true)
    @NotNull(message = "调整人员ID不能为空")
    private Long adjustManId;


    /**
     * 价格清单ID
     */
    @ApiModelProperty(value = "价格清单ID",required = true)
    @NotNull(message = "价格清单不能为空")
    private Long priceOrderId;

    /**
     * 调整原因
     */
    @ApiModelProperty(value = "调整原因",required = false)
    private String adjustReason;


    @ApiModelProperty(value = "调整单明细集合",required = false)
    private List<AddOrUpdateAdjustDetailVO> addOrUpdateAdjustDetailVOS;

    @ApiModelProperty(value = "调整明细单删除id集合",required = false)
    private List<Long> deleteAdjustDetailIds;

//    public static List<Long> getAdjustManIds(List<AddOrUpdatePriceAdjustVO> addOrUpdatePriceAdjustVOS){
//
//        List<Long> ids = new ArrayList<>();
//
//        for(AddOrUpdatePriceAdjustVO addOrUpdatePriceAdjustVO : addOrUpdatePriceAdjustVOS){
//            ids.add(addOrUpdatePriceAdjustVO.getAdjustManId());
//        }
//
//        return ids;
//    }

    public static List<Long> getPriceOrderIds(List<AddOrUpdatePriceAdjustVO> addOrUpdatePriceAdjustVOS){

        List<Long> ids = new ArrayList<>();

        for(AddOrUpdatePriceAdjustVO addOrUpdatePriceAdjustVO : addOrUpdatePriceAdjustVOS){
            ids.add(addOrUpdatePriceAdjustVO.getPriceOrderId());
        }

        return ids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAdjustDate() {
        return adjustDate;
    }

    public void setAdjustDate(Date adjustDate) {
        this.adjustDate = adjustDate;
    }

    public Long getPriceOrderId() {
        return priceOrderId;
    }

    public void setPriceOrderId(Long priceOrderId) {
        this.priceOrderId = priceOrderId;
    }

    public String getAdjustReason() {
        return adjustReason;
    }

    public void setAdjustReason(String adjustReason) {
        this.adjustReason = adjustReason;
    }

    public List<AddOrUpdateAdjustDetailVO> getAddOrUpdateAdjustDetailVOS() {
        return addOrUpdateAdjustDetailVOS;
    }

    public void setAddOrUpdateAdjustDetailVOS(List<AddOrUpdateAdjustDetailVO> addOrUpdateAdjustDetailVOS) {
        this.addOrUpdateAdjustDetailVOS = addOrUpdateAdjustDetailVOS;
    }

    public List<Long> getDeleteAdjustDetailIds() {
        return deleteAdjustDetailIds;
    }

    public void setDeleteAdjustDetailIds(List<Long> deleteAdjustDetailIds) {
        this.deleteAdjustDetailIds = deleteAdjustDetailIds;
    }

    public Long getAdjustManId() {
        return adjustManId;
    }

    public void setAdjustManId(Long adjustManId) {
        this.adjustManId = adjustManId;
    }
}