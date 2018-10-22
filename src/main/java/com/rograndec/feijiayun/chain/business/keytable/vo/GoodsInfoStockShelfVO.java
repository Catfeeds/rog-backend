package com.rograndec.feijiayun.chain.business.keytable.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能描述：
 * Created by ST on 2017/9/22 21:29
 */

public class GoodsInfoStockShelfVO {

    @ApiModelProperty("商品id")
    private Long goodsId;

    @ApiModelProperty("商品编码")
    private String goodsCode;

    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("生产日期")
    private Date productDate;

    @ApiModelProperty("有效期")
    private Date validUtil;

    @ApiModelProperty("批号ID")
    private Long lotNumberId;

    @ApiModelProperty("批号")
    private String lotNum;

    @ApiModelProperty("货位id")
    private Long shelfId;

    @ApiModelProperty("货位名称")
    private String shelfName;

    @ApiModelProperty("可用数量")
    private BigDecimal usableQuantity;

    @ApiModelProperty("库存数量")
    private BigDecimal quantity;

    @ApiModelProperty("货位质量状态描述")
    private String shelfStatusDesc;

    public String getShelfStatusDesc() {
        return shelfStatusDesc;
    }

    public void setShelfStatusDesc(String shelfStatusDesc) {
        this.shelfStatusDesc = shelfStatusDesc;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getValidUtil() {
        return validUtil;
    }

    public void setValidUtil(Date validUtil) {
        this.validUtil = validUtil;
    }

    public Long getLotNumberId() {
        return lotNumberId;
    }

    public void setLotNumberId(Long lotNumberId) {
        this.lotNumberId = lotNumberId;
    }

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public BigDecimal getUsableQuantity() {
        return usableQuantity;
    }

    public void setUsableQuantity(BigDecimal usableQuantity) {
        this.usableQuantity = usableQuantity;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
}