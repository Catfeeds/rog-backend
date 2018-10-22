package com.rograndec.feijiayun.chain.business.distr.parent.vo.pick;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutShelf;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ApiModel(value = "DistrSendOperationSlfVO", description = "配货单-拣货锁定明细对象")
public class DistrSendOperationSlfVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = false, value = "配货单锁定明细ID")
	private Long sendSlfId;
	
	/**
     * 批号
     */
    @ApiModelProperty(value = "批号ID")
	private Long lotId;
    
	/**
     * 批号
     */
    @ApiModelProperty(value = "批号")
    private String lotNumber;

    /**
     * 生产日期
     */
    @ApiModelProperty(value = "生产日期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date productDate;

    /**
     * 有效期
     */
    @ApiModelProperty(value = "有效期")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date validDate;

    /**
     * 货位ID
     */
    @ApiModelProperty(value = "货位ID")
    private Long shelfId;

    /**
     * 货位名称
     */
    @ApiModelProperty(value = "货位名称")
    private String shelfName;

    /**
     * 货位质量状态描述
     */
    @ApiModelProperty(value = "货位质量状态描述")
    private String shelfStatusDesc;

    /**
     * 库存可用量
     */
    @ApiModelProperty(value = "库存可用量")
    private BigDecimal usableQuantity;
    
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;
    
    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    public static List<DistrSendOperationSlfVO> getDistrSendOperationSlfVOs(List<DistrOutShelf> distrOutShelfList,DistrOutDetail distrOutDetail){

		List<DistrSendOperationSlfVO> distrSendOperationSlfVOS = new ArrayList<>();
		int j = 0;
		for(DistrOutShelf distrOutShelf : distrOutShelfList){
			j++;
			DistrSendOperationSlfVO distrSendOperationSlfVO = new DistrSendOperationSlfVO();

			distrSendOperationSlfVO.setSendSlfId(distrOutDetail.getBaseOrderDtlId());
			/**
			 * 行号
			 */
			distrSendOperationSlfVO.setLineNum(j);
			/**
			 * 批号
			 */
			distrSendOperationSlfVO.setLotId(distrOutShelf.getLotId());

			/**
			 * 批号
			 */
			distrSendOperationSlfVO.setLotNumber(distrOutShelf.getLotNumber());

			/**
			 * 生产日期
			 */
			distrSendOperationSlfVO.setProductDate(distrOutShelf.getProductDate());

			/**
			 * 有效期
			 */
			distrSendOperationSlfVO.setValidDate(distrOutShelf.getValidDate());

			/**
			 * 货位ID
			 */
			distrSendOperationSlfVO.setShelfId(distrOutShelf.getShelfId());

			/**
			 * 货位名称
			 */
			distrSendOperationSlfVO.setShelfName(distrOutShelf.getShelfName());

			/**
			 * 货位质量状态描述
			 */
			distrSendOperationSlfVO.setShelfStatusDesc(distrOutShelf.getShelfStatusDesc());

			/**
			 * 数量
			 */
			distrSendOperationSlfVO.setQuantity(distrOutShelf.getQuantity());

			distrSendOperationSlfVOS.add(distrSendOperationSlfVO);
		}



		return distrSendOperationSlfVOS;
	}

	public Long getSendSlfId() {
		return sendSlfId;
	}

	public void setSendSlfId(Long sendSlfId) {
		this.sendSlfId = sendSlfId;
	}

	public Long getLotId() {
		return lotId;
	}

	public void setLotId(Long lotId) {
		this.lotId = lotId;
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

	public String getShelfStatusDesc() {
		return shelfStatusDesc;
	}

	public void setShelfStatusDesc(String shelfStatusDesc) {
		this.shelfStatusDesc = shelfStatusDesc;
	}

	public BigDecimal getUsableQuantity() {
		return usableQuantity;
	}

	public void setUsableQuantity(BigDecimal usableQuantity) {
		this.usableQuantity = usableQuantity;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}
	
}
