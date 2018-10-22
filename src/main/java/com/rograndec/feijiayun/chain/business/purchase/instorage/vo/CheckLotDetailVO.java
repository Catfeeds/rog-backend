package com.rograndec.feijiayun.chain.business.purchase.instorage.vo;

import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageDetail;
import com.rograndec.feijiayun.chain.business.purchase.instorage.entity.PurchaseInStorageShelf;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "CheckLotDetailVO", description = "待入库列表批号明细对象")
public class CheckLotDetailVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = true, value = "验收批号明细ID")
    private Long checkLotId;
	
	@ApiModelProperty(value = "质量状况：0-合格品，2不合格品" )
    private Integer jobArea;
	
	@ApiModelProperty(required = true, value = "入库类型名称")
	private String jobAreaName;
	
	@ApiModelProperty(required = true, value = "批号")
	private String lotNumber;
	
	@ApiModelProperty(required = true, value = "生产日期")
	private Date productDate;
	
	@ApiModelProperty(required = true, value = "有效期")
	private Date validDate;
	
	@ApiModelProperty(required = true, value = "生产日期")
	private String stringProductDate;
	
	@ApiModelProperty(required = true, value = "有效期")
	private String stringValidDate;
	
	@ApiModelProperty(required = true, value = "货位ID")
    private Long shelfId;
	
	@ApiModelProperty(required = true, value = "货位名称")
    private String shelfName;
	
	@ApiModelProperty(required = true, value = "数量")
	private BigDecimal receiveQuantity;

	@ApiModelProperty(required = true, value = "行号")
	private Integer lineNum;
	
	@ApiModelProperty(required = true, value = "合格数量-页面不展示")
	private BigDecimal qualifiedQuantity;
	
	@ApiModelProperty(required = true, value = "不合格数量-页面不展示")
	private BigDecimal unqualifiedQuantity;

	/**
	 * 入库数量
	 */
	@ApiModelProperty(required = true, value = "货位信息数量")
	private BigDecimal quantity;

	public Long getCheckLotId() {
		return checkLotId;
	}

	public void setCheckLotId(Long checkLotId) {
		this.checkLotId = checkLotId;
	}
	
	public Integer getJobArea() {
		return jobArea;
	}

	public void setJobArea(Integer jobArea) {
		this.jobArea = jobArea;
	}

	public String getJobAreaName() {
		return jobAreaName;
	}

	public void setJobAreaName(String jobAreaName) {
		this.jobAreaName = jobAreaName;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
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

	public String getStringProductDate() {
		return stringProductDate;
	}

	public void setStringProductDate(String stringProductDate) {
		this.stringProductDate = stringProductDate;
	}

	public String getStringValidDate() {
		return stringValidDate;
	}

	public void setStringValidDate(String stringValidDate) {
		this.stringValidDate = stringValidDate;
	}

	public BigDecimal getReceiveQuantity() {
		return receiveQuantity;
	}

	public void setReceiveQuantity(BigDecimal receiveQuantity) {
		this.receiveQuantity = receiveQuantity;
	}

	public BigDecimal getQualifiedQuantity() {
		return qualifiedQuantity;
	}

	public void setQualifiedQuantity(BigDecimal qualifiedQuantity) {
		this.qualifiedQuantity = qualifiedQuantity;
	}

	public BigDecimal getUnqualifiedQuantity() {
		return unqualifiedQuantity;
	}

	public void setUnqualifiedQuantity(BigDecimal unqualifiedQuantity) {
		this.unqualifiedQuantity = unqualifiedQuantity;
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

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}

	public static CheckLotDetailVO convertToVO(PurchaseInStorageShelf ps, PurchaseInStorageDetail purchaseInStorageDetail, WarehouseCargoArea warehouseCargoArea) {
		CheckLotDetailVO vo = new CheckLotDetailVO();
		vo.setLotNumber(ps.getLotNumber());
		vo.setProductDate(ps.getProductDate());
		vo.setValidDate(ps.getValidDate());
		vo.setShelfName(ps.getShelfName());
		String jobAreaName = "";
		// " job_area=0，含义为“合格品”；\n" +
		//" job_area=2，含义为“不合格品”；\n"
		if (0 == warehouseCargoArea.getJobArea()){
			jobAreaName = "合格品";
		}else if(2 == warehouseCargoArea.getJobArea()){
			jobAreaName = "不合格品";
		}
		vo.setJobArea(warehouseCargoArea.getJobArea());
		vo.setJobAreaName(jobAreaName);
		vo.setQuantity(ps.getQuantity());
		vo.setLineNum(ps.getLineNum());
		return vo;
	}
}
