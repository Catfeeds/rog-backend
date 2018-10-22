package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;

public class GetReceiptDataVO implements Serializable {

    /**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年11月29日 下午9:20:50
	 * @version 1.0
	 */
	private static final long serialVersionUID = 3317653757587315240L;

	@ApiModelProperty(value = "saas供货商ID", required = true)
    private Long temSaveId;

    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

    @ApiModelProperty(value = "saas供货商ID", required = true)
    private Long supplyId;

    @ApiModelProperty(value = "saas供货商编码", required = true)
    private String supplyCode;

    @ApiModelProperty(value = "saas供货商", required = true)
    private String supplyName;

    @ApiModelProperty(value = "saas供货商销售人员ID", required = true)
    private Long supplierSalerId;

    @ApiModelProperty(value = "saas供货商销售人员编码", required = true)
    private String supplierSalerCode;

    @ApiModelProperty(value = "saas供货商销售人员名称", required = true)
    private String supplierSalerName;

    @ApiModelProperty(value = "saas供货商销售人员联系电话", required = true)
    private String supplierSalerPhone;

    @ApiModelProperty(value = "MPH供货商ID", required = true)
    private Long mphSupplyId;

    @ApiModelProperty(value = "MPH供货商", required = true)
    private String mphSupplyName;

    @ApiModelProperty(value = "订单ID", required = true)
    private Long orderId;

    @ApiModelProperty(value = "订单编码", required = true)
    private String orderCode;

    @ApiModelProperty(value = "采购日期", required = true)
    private Date purchaseDate;

    @ApiModelProperty(value = "收货日期", required = true)
    private Date receiveDate;

    @ApiModelProperty(value = "验收日期", required = true)
    private Date checkDate;

    @ApiModelProperty(value = "入库日期", required = true)
    private Date inDate;

    @ApiModelProperty(value = "采购人员ID", required = true)
    private Long purchaseManId;

    @ApiModelProperty(value = "收货人员ID", required = true)
    private Long receiveManId;

    @ApiModelProperty(value = "收货人员ID2", required = false)
    private Long secondReceiveManId;

    @ApiModelProperty(value = "验收人员ID", required = true)
    private Long checkManId;

    @ApiModelProperty(value = "验收人员ID2", required = false)
    private Long secondCheckManId;

    @ApiModelProperty(value = "入库人员ID", required = true)
    private Long inManId;

    @ApiModelProperty(value = "流通监管码", required = false)
    private String flowCode;

    private List<GetReceiptGoodsDataVO> getReceiptGoodsDataVO;
    
    @ApiModelProperty(value = "后端保存时使用，前端不用", required = false)
    private PurchaseOrder purchase;

    public Long getTemSaveId() {
		return temSaveId;
	}

	public void setTemSaveId(Long temSaveId) {
		this.temSaveId = temSaveId;
	}

	public Long getMphSupplyId() {
        return mphSupplyId;
    }

    public void setMphSupplyId(Long mphSupplyId) {
        this.mphSupplyId = mphSupplyId;
    }

    public String getMphSupplyName() {
        return mphSupplyName;
    }

    public void setMphSupplyName(String mphSupplyName) {
        this.mphSupplyName = mphSupplyName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public List<GetReceiptGoodsDataVO> getGetReceiptGoodsDataVO() {
        return getReceiptGoodsDataVO;
    }

    public void setGetReceiptGoodsDataVO(List<GetReceiptGoodsDataVO> getReceiptGoodsDataVO) {
        this.getReceiptGoodsDataVO = getReceiptGoodsDataVO;
    }

    public Long getSupplyId() {
        return supplyId;
    }

    public void setSupplyId(Long supplyId) {
        this.supplyId = supplyId;
    }

    public String getSupplyName() {
        return supplyName;
    }

    public void setSupplyName(String supplyName) {
        this.supplyName = supplyName;
    }

    public String getSupplyCode() {
        return supplyCode;
    }

    public void setSupplyCode(String supplyCode) {
        this.supplyCode = supplyCode;
    }

    public Long getSupplierSalerId() {
        return supplierSalerId;
    }

    public void setSupplierSalerId(Long supplierSalerId) {
        this.supplierSalerId = supplierSalerId;
    }

    public String getSupplierSalerCode() {
        return supplierSalerCode;
    }

    public void setSupplierSalerCode(String supplierSalerCode) {
        this.supplierSalerCode = supplierSalerCode;
    }

    public String getSupplierSalerName() {
        return supplierSalerName;
    }

    public void setSupplierSalerName(String supplierSalerName) {
        this.supplierSalerName = supplierSalerName;
    }

    public String getSupplierSalerPhone() {
        return supplierSalerPhone;
    }

    public void setSupplierSalerPhone(String supplierSalerPhone) {
        this.supplierSalerPhone = supplierSalerPhone;
    }

    public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Date getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(Date receiveDate) {
		this.receiveDate = receiveDate;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public Long getPurchaseManId() {
		return purchaseManId;
	}

	public void setPurchaseManId(Long purchaseManId) {
		this.purchaseManId = purchaseManId;
	}

	public Long getReceiveManId() {
		return receiveManId;
	}

	public void setReceiveManId(Long receiveManId) {
		this.receiveManId = receiveManId;
	}

	public Long getSecondReceiveManId() {
		return secondReceiveManId;
	}

	public void setSecondReceiveManId(Long secondReceiveManId) {
		this.secondReceiveManId = secondReceiveManId;
	}

	public Long getCheckManId() {
		return checkManId;
	}

	public void setCheckManId(Long checkManId) {
		this.checkManId = checkManId;
	}

	public Long getSecondCheckManId() {
		return secondCheckManId;
	}

	public void setSecondCheckManId(Long secondCheckManId) {
		this.secondCheckManId = secondCheckManId;
	}

	public Long getInManId() {
		return inManId;
	}

	public void setInManId(Long inManId) {
		this.inManId = inManId;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
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
    
    public PurchaseOrder getPurchase() {
		return purchase;
	}

	public void setPurchase(PurchaseOrder purchase) {
		this.purchase = purchase;
	}

	@Override
    public String toString() {
        return "GetReceiptDataVO[" +
                "supplyId=" + supplyId +
                ", supplyCode='" + supplyCode + '\'' +
                ", supplyName='" + supplyName + '\'' +
                ", supplierSalerId=" + supplierSalerId +
                ", supplierSalerCode='" + supplierSalerCode + '\'' +
                ", supplierSalerName='" + supplierSalerName + '\'' +
                ", supplierSalerPhone='" + supplierSalerPhone + '\'' +
                ", mphSupplyId=" + mphSupplyId +
                ", mphSupplyName='" + mphSupplyName + '\'' +
                ", orderId=" + orderId +
                ", orderCode='" + orderCode + '\'' +
                ", getReceiptGoodsDataVO=" + getReceiptGoodsDataVO +
                ']';
    }
}
