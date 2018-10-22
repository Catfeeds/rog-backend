package com.rograndec.feijiayun.chain.business.storage.split.vo;

import com.rograndec.feijiayun.chain.business.storage.split.entity.SplitSet;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/28 <br/>
 * 描述：拆零设置保存传参
 */
public class SplitSetSaveVO {

    @ApiModelProperty(value = "拆零单据主键ID，修改拆零设置时必须传", required = false)
    private Long splitSetId;

    @ApiModelProperty(value = "原商品id", required = true)
    private Long goodsId;

    @ApiModelProperty(value = "拆零商品id", required = false)
    private Long splitGoodsId;

    @ApiModelProperty(value = "拆零规格", required = true)
    private String splitSpecification;

    @ApiModelProperty(value = "拆零单位id", required = true)
    private Long splitUnitId;

    @ApiModelProperty(value = "拆零单位名称", required = false)
    private String splitUnitName;

    @ApiModelProperty(value = "拆零货位id", required = true)
    private Long splitShelfId;

    @ApiModelProperty(value = "拆零货位名称", required = false)
    private String splitShelfName;

    @ApiModelProperty(value = "拆零数量", required = true)
    private BigDecimal splitQuantity;

    @ApiModelProperty(value = "拆零零售单价", required = true)
    private BigDecimal splitRetailPrice;

    @ApiModelProperty(value = "拆零会员单价", required = true)
    private BigDecimal splitMemberPrice;

    public Long getSplitSetId() {
        return splitSetId;
    }

    public void setSplitSetId(Long splitSetId) {
        this.splitSetId = splitSetId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getSplitGoodsId() {
        return splitGoodsId;
    }

    public void setSplitGoodsId(Long splitGoodsId) {
        this.splitGoodsId = splitGoodsId;
    }

    public String getSplitSpecification() {
        return splitSpecification;
    }

    public void setSplitSpecification(String splitSpecification) {
        this.splitSpecification = splitSpecification;
    }

    public Long getSplitUnitId() {
        return splitUnitId;
    }

    public void setSplitUnitId(Long splitUnitId) {
        this.splitUnitId = splitUnitId;
    }

    public String getSplitUnitName() {
        return splitUnitName;
    }

    public void setSplitUnitName(String splitUnitName) {
        this.splitUnitName = splitUnitName;
    }

    public Long getSplitShelfId() {
        return splitShelfId;
    }

    public void setSplitShelfId(Long splitShelfId) {
        this.splitShelfId = splitShelfId;
    }

    public String getSplitShelfName() {
        return splitShelfName;
    }

    public void setSplitShelfName(String splitShelfName) {
        this.splitShelfName = splitShelfName;
    }

    public BigDecimal getSplitQuantity() {
        return splitQuantity;
    }

    public void setSplitQuantity(BigDecimal splitQuantity) {
        this.splitQuantity = splitQuantity;
    }

    public BigDecimal getSplitRetailPrice() {
        return splitRetailPrice;
    }

    public void setSplitRetailPrice(BigDecimal splitRetailPrice) {
        this.splitRetailPrice = splitRetailPrice;
    }

    public BigDecimal getSplitMemberPrice() {
        return splitMemberPrice;
    }

    public void setSplitMemberPrice(BigDecimal splitMemberPrice) {
        this.splitMemberPrice = splitMemberPrice;
    }

    /**
     * 转换为SplitSet用于保存数据库
     * @param splitSetSaveVO
     * @return
     */
    public static SplitSet convertToSplitSet(SplitSetSaveVO splitSetSaveVO) {
        SplitSet splitSet = new SplitSet();
        if (splitSetSaveVO == null) {
            return splitSet;
        }
        splitSet.setGoodsId(splitSetSaveVO.getGoodsId());
        splitSet.setSplitSpecification(splitSetSaveVO.getSplitSpecification());
        splitSet.setSplitUnitId(splitSetSaveVO.getSplitUnitId());
        splitSet.setSplitUnitName(splitSetSaveVO.getSplitUnitName());
        splitSet.setSplitShelfId(splitSetSaveVO.getSplitShelfId());
        splitSet.setSplitShelfName(splitSetSaveVO.getSplitShelfName());
        splitSet.setSplitQuantity(splitSetSaveVO.getSplitQuantity());
        splitSet.setSplitRetailPrice(splitSetSaveVO.getSplitRetailPrice());
        splitSet.setSplitMemberPrice(splitSetSaveVO.getSplitMemberPrice());
        return splitSet;
    }
}
