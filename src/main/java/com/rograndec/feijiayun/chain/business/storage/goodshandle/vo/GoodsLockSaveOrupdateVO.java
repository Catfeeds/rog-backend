package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: GoodsLockSaveOrupdateVO
 * @Description: 储存管理-商品处理-药品锁定-Rest接口
 * @date 2017-09-27 17:26:40
 */
@ApiModel(value = "GoodsLockSaveOrupdateVO", description = "储存管理-商品处理-药品锁定")
public class GoodsLockSaveOrupdateVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 锁定日期
     */
    @NotNull(message = "锁定日期不能为空!")
    @ApiModelProperty(required = true, value = "锁定日期")
    private String lockDateStr;

    /**
     * 锁定人员ID
     */
    @NotNull(message = "锁定人员ID不能为空!")
    @ApiModelProperty(required = true, value = "锁定人员ID")
    private Long lockManId;

    /**
     * 锁定原因（0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品）
     */
    @NotNull(message = "锁定原因（0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品）不能为空!")
    @ApiModelProperty(required = true, value = "锁定原因（0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品）")
    private Integer lockReason;

    /**
     * 备注
     */
    @ApiModelProperty(required = false, value = "备注")
    private String remark;
    /*
    * 商品列表
    */
    @NotNull(message = "商品列表不能为空")
    @ApiModelProperty(required = true,value = "商品列表")
    List<GoodsLockDetailSaveOrupdateVO> goodsLockDetailSaveOrupdateVOList;

    /**
     * 基础单据ID
     */
    @ApiModelProperty(value = "基础单据ID")
    private Long baseOrderId;


    public List<GoodsLockDetailSaveOrupdateVO> getGoodsLockDetailSaveOrupdateVOList() {
        return goodsLockDetailSaveOrupdateVOList;
    }

    public void setGoodsLockDetailSaveOrupdateVOList(List<GoodsLockDetailSaveOrupdateVO> goodsLockDetailSaveOrupdateVOList) {
        this.goodsLockDetailSaveOrupdateVOList = goodsLockDetailSaveOrupdateVOList;
    }

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public String getLockDateStr() {
        return lockDateStr;
    }

    public void setLockDateStr(String lockDateStr) {
        this.lockDateStr = lockDateStr;
    }

    public Long getLockManId() {
        return lockManId;
    }

    public void setLockManId(Long lockManId) {
        this.lockManId = lockManId;
    }

    public Integer getLockReason() {
        return lockReason;
    }

    public void setLockReason(Integer lockReason) {
        this.lockReason = lockReason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}