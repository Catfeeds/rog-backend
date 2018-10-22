package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheckDetail;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderDetail;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceiveDetail;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class  GetReceiptGoodsDataVO implements Serializable {
    /**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年11月29日 下午9:21:38
	 * @version 1.0
	 */
	private static final long serialVersionUID = -4730117708556295199L;

    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;
    
    @ApiModelProperty(value = "订单明细ID", required = true)
    private Long orderDetailId;

    @ApiModelProperty(value = "saas商品ID", required = true)
    private Long goodsId;

    @ApiModelProperty(value = "saas商品CODE", required = true)
    private String goodsCode;

    @ApiModelProperty(value = "MPH商品ID", required = true)
    private Long mphGoodsId;

    @ApiModelProperty(value = "MPH商品名称", required = true)
    private String mphGoodsName;

    @ApiModelProperty(value = "MPH生产企业", required = true)
    private String mphGoodsManufacturer;

    @ApiModelProperty(value = "规格", required = true)
    private String specification;

    @ApiModelProperty(value = "采购单价", required = true)
    private BigDecimal purchasePrice;

    @ApiModelProperty(value = "订单数量", required = true)
    private BigDecimal purchaseQty;

    @ApiModelProperty(value = "收货数量", required = true)
    private BigDecimal deliverQty;

    @ApiModelProperty(value = "拒收数量", required = true)
    private BigDecimal returnQty;

    @ApiModelProperty(value = "发货货款金额", required = true)
    private BigDecimal deliverAmount;

    @ApiModelProperty(value = "绑定商品", required = true)
    private String boundGoods;

    @ApiModelProperty(value = "是否为新品 0：不是新品 1:是新品", required = true)
    private Integer newGoods;

    @ApiModelProperty(value = "税率ID")
    private Long taxRateId;

    @ApiModelProperty(value = "不含税单价")
    private BigDecimal notaxRealPrice;

    @ApiModelProperty(value = "不含税金额")
    private BigDecimal notaxRealAmount;

    @ApiModelProperty(value = "商品信息", required = true)
    private Goods goods;
    
    List<GetReceiptGoodsLotDataVO> getReceiptGoodsLotDataVO;
    
    private PurchaseOrderDetail purchaseOrderDetail;
    
    private PurchaseReceiveDetail purchaseReceiveDetail;
    
    private PurchaseCheckDetail purchaseCheckDetail;
    
    public Long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(Long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public BigDecimal getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(BigDecimal returnQty) {
		this.returnQty = returnQty;
	}

	public Long getTaxRateId() {
		return taxRateId;
	}

	public void setTaxRateId(Long taxRateId) {
		this.taxRateId = taxRateId;
	}

	public BigDecimal getNotaxRealPrice() {
		return notaxRealPrice;
	}

	public void setNotaxRealPrice(BigDecimal notaxRealPrice) {
		this.notaxRealPrice = notaxRealPrice;
	}

	public BigDecimal getNotaxRealAmount() {
		return notaxRealAmount;
	}

	public void setNotaxRealAmount(BigDecimal notaxRealAmount) {
		this.notaxRealAmount = notaxRealAmount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getMphGoodsId() {
        return mphGoodsId;
    }

    public void setMphGoodsId(Long mphGoodsId) {
        this.mphGoodsId = mphGoodsId;
    }

    public String getMphGoodsName() {
        return mphGoodsName;
    }

    public void setMphGoodsName(String mphGoodsName) {
        this.mphGoodsName = mphGoodsName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public BigDecimal getPurchaseQty() {
        return purchaseQty;
    }

    public void setPurchaseQty(BigDecimal purchaseQty) {
        this.purchaseQty = purchaseQty;
    }

    public BigDecimal getDeliverQty() {
        return deliverQty;
    }

    public void setDeliverQty(BigDecimal deliverQty) {
        this.deliverQty = deliverQty;
    }

    public BigDecimal getDeliverAmount() {
        return deliverAmount;
    }

    public void setDeliverAmount(BigDecimal deliverAmount) {
        this.deliverAmount = deliverAmount;
    }

    public String getBoundGoods() {
        return boundGoods;
    }

    public void setBoundGoods(String boundGoods) {
        this.boundGoods = boundGoods;
    }

    public List<GetReceiptGoodsLotDataVO> getGetReceiptGoodsLotDataVO() {
        return getReceiptGoodsLotDataVO;
    }

    public void setGetReceiptGoodsLotDataVO(List<GetReceiptGoodsLotDataVO> getReceiptGoodsLotDataVO) {
        this.getReceiptGoodsLotDataVO = getReceiptGoodsLotDataVO;
    }

    public Integer getNewGoods() {
        return newGoods;
    }

    public void setNewGoods(Integer newGoods) {
        this.newGoods = newGoods;
    }

    public String getMphGoodsManufacturer() {
        return mphGoodsManufacturer;
    }

    public void setMphGoodsManufacturer(String mphGoodsManufacturer) {
        this.mphGoodsManufacturer = mphGoodsManufacturer;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
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

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }
    
    public PurchaseOrderDetail getPurchaseOrderDetail() {
		return purchaseOrderDetail;
	}

	public void setPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail) {
		this.purchaseOrderDetail = purchaseOrderDetail;
	}

	public PurchaseReceiveDetail getPurchaseReceiveDetail() {
		return purchaseReceiveDetail;
	}

	public void setPurchaseReceiveDetail(PurchaseReceiveDetail purchaseReceiveDetail) {
		this.purchaseReceiveDetail = purchaseReceiveDetail;
	}
	
	public PurchaseCheckDetail getPurchaseCheckDetail() {
		return purchaseCheckDetail;
	}

	public void setPurchaseCheckDetail(PurchaseCheckDetail purchaseCheckDetail) {
		this.purchaseCheckDetail = purchaseCheckDetail;
	}

	@Override
    public String toString() {
        return "GetReceiptGoodsDataVO[" +
                "goodsId=" + goodsId +
                ", mphGoodsId='" + mphGoodsId + '\'' +
                ", mphGoodsName='" + mphGoodsName + '\'' +
                ", specification='" + specification + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", purchaseQty=" + purchaseQty +
                ", mphGoodsManufacturer=" + mphGoodsManufacturer +
                ", deliverQty=" + deliverQty +
                ", deliverAmount=" + deliverAmount +
                ", boundGoods='" + boundGoods + '\'' +
                ", newGoods='" + newGoods + '\'' +
                ", getReceiptGoodsLotDataVO=" + getReceiptGoodsLotDataVO +
                ']';
    }
}
