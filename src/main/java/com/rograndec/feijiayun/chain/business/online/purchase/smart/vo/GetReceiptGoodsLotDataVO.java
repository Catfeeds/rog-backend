package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheckLot;

public class GetReceiptGoodsLotDataVO implements Serializable {

    /**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年11月29日 下午9:32:41
	 * @version 1.0
	 */
	private static final long serialVersionUID = 9151498185722759760L;
    @ApiModelProperty(value = "批号表主键ID", required = true)
    private Long id;

    @ApiModelProperty(value = "商品ID", required = true)
    private Long goodsId;

    @ApiModelProperty(value = "批次号", required = true)
    private String lotNum;

    @ApiModelProperty(value = "生产日期", required = true)
    private Date productDate;

    @ApiModelProperty(value = "有效期至", required = true)
    private Date validDate;

    @ApiModelProperty(value = "收货数量", required = true)
    private BigDecimal receiveQuantity;

    @ApiModelProperty(value = "货位", required = true)
    private String shelfName;

    @ApiModelProperty(value = "货位ID", required = true)
    private Long shelfId;

    @ApiModelProperty(value = "检验项目ID集合,多个用逗号分隔", required = true)
    private String checkProjectIds;

    @ApiModelProperty(value = "检验项目集合,多个用逗号分隔", required = true)
    private String checkProjectNames;

    @ApiModelProperty(value = "验收结论ID", required = true)
    private Long checkResult;
    
    private PurchaseCheckLot purchaseCheckLot;

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public Date getProductDate() {
        return productDate;
    }

    public void setProductDate(Date productDate) {
        this.productDate = productDate;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public BigDecimal getReceiveQuantity() {
        return receiveQuantity;
    }

    public void setReceiveQuantity(BigDecimal receiveQuantity) {
        this.receiveQuantity = receiveQuantity;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public String getCheckProjectIds() {
        return checkProjectIds;
    }

    public void setCheckProjectIds(String checkProjectIds) {
        this.checkProjectIds = checkProjectIds;
    }

    public String getCheckProjectNames() {
        return checkProjectNames;
    }

    public void setCheckProjectNames(String checkProjectNames) {
        this.checkProjectNames = checkProjectNames;
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Long checkResult) {
        this.checkResult = checkResult;
    }

	public PurchaseCheckLot getPurchaseCheckLot() {
		return purchaseCheckLot;
	}

	public void setPurchaseCheckLot(PurchaseCheckLot purchaseCheckLot) {
		this.purchaseCheckLot = purchaseCheckLot;
	}
    
}
