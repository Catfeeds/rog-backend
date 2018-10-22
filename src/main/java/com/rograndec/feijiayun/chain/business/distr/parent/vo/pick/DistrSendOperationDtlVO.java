package com.rograndec.feijiayun.chain.business.distr.parent.vo.pick;

import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOut;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "DistrSendOperationDtlVO", description = "配货单-拣货明细对象")
public class DistrSendOperationDtlVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = false, value = "配货单明细ID")
	private Long sendDetailId;
	
	/**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    
    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;
    
    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 商品产地
     */
    @ApiModelProperty(value = "商品产地")
    private String goodsPlace;
    
    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;
    
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    
    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    @ApiModelProperty(value = "货位明细")
    private List<DistrSendOperationSlfVO> shelfList = new ArrayList<DistrSendOperationSlfVO>();

	public static List<DistrSendOperationDtlVO> getDistrSendOperationDtlVOs(DistrOut distrOut){
		List<DistrOutDetail> distrOutDetailList = distrOut.getDistrOutDetailList();

		List<DistrSendOperationDtlVO> distrSendOperationDtlVOS = new ArrayList<>();

		int i = 0;
		for(DistrOutDetail distrOutDetail : distrOutDetailList){

			i++;
			DistrSendOperationDtlVO distrSendOperationDtlVO = new DistrSendOperationDtlVO();
			/**
			 * 配货单明细ID
			 */
			distrSendOperationDtlVO.setSendDetailId(distrOutDetail.getBaseOrderDtlId());
			/**
			 * 商品ID
			 */
			distrSendOperationDtlVO.setGoodsId(distrOutDetail.getGoodsId());

			/**
			 * 商品编码
			 */
			distrSendOperationDtlVO.setGoodsCode(distrOutDetail.getGoodsCode());

			/**
			 * 商品名称
			 */
			distrSendOperationDtlVO.setGoodsName(distrOutDetail.getGoodsName());

			/**
			 * 商品通用名称
			 */
			distrSendOperationDtlVO.setGoodsGenericName(distrOutDetail.getGoodsGenericName());

			/**
			 * 剂型ID
			 */
			distrSendOperationDtlVO.setDosageId(distrOutDetail.getDosageId());

			/**
			 * 剂型名称
			 */
			distrSendOperationDtlVO.setDosageName(distrOutDetail.getDosageName());

			/**
			 * 单位ID
			 */
			distrSendOperationDtlVO.setUnitId(distrOutDetail.getUnitId());

			/**
			 * 单位名称
			 */
			distrSendOperationDtlVO.setUnitName(distrOutDetail.getUnitName());

			/**
			 * 商品规格
			 */
			distrSendOperationDtlVO.setGoodsSpecification(distrOutDetail.getGoodsSpecification());

			/**
			 * 生产厂商ID
			 */
			distrSendOperationDtlVO.setManufacturerId(distrOutDetail.getManufacturerId());

			/**
			 * 生产厂商
			 */
			distrSendOperationDtlVO.setManufacturer(distrOutDetail.getManufacturer());

			/**
			 * 商品产地
			 */
			distrSendOperationDtlVO.setGoodsPlace(distrOutDetail.getGoodsPlace());

			/**
			 * 数量
			 */
			distrSendOperationDtlVO.setQuantity(distrOutDetail.getQuantity());

			/**
			 * 行号
			 */
			distrSendOperationDtlVO.setLineNum(i);


			List<DistrSendOperationSlfVO> distrSendOperationSlfVOS = DistrSendOperationSlfVO.getDistrSendOperationSlfVOs(distrOutDetail.getDistrOutShelfList(),distrOutDetail);


			distrSendOperationDtlVO.setShelfList(distrSendOperationSlfVOS);

			distrSendOperationDtlVOS.add(distrSendOperationDtlVO);
		}

		return distrSendOperationDtlVOS;
	}

	public Long getSendDetailId() {
		return sendDetailId;
	}

	public void setSendDetailId(Long sendDetailId) {
		this.sendDetailId = sendDetailId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
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

	public String getGoodsGenericName() {
		return goodsGenericName;
	}

	public void setGoodsGenericName(String goodsGenericName) {
		this.goodsGenericName = goodsGenericName;
	}

	public Long getDosageId() {
		return dosageId;
	}

	public void setDosageId(Long dosageId) {
		this.dosageId = dosageId;
	}

	public String getDosageName() {
		return dosageName;
	}

	public void setDosageName(String dosageName) {
		this.dosageName = dosageName;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getGoodsSpecification() {
		return goodsSpecification;
	}

	public void setGoodsSpecification(String goodsSpecification) {
		this.goodsSpecification = goodsSpecification;
	}

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getGoodsPlace() {
		return goodsPlace;
	}

	public void setGoodsPlace(String goodsPlace) {
		this.goodsPlace = goodsPlace;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getLineNum() {
		return lineNum;
	}

	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}

	public List<DistrSendOperationSlfVO> getShelfList() {
		return shelfList;
	}

	public void setShelfList(List<DistrSendOperationSlfVO> shelfList) {
		this.shelfList = shelfList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}
