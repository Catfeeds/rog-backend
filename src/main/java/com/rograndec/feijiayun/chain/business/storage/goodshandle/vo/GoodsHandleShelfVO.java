package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

 /**
 * 
 * @ClassName: GoodsHandleShelfVO
 * @Description:  储存管理-商品处理-药品处理货位明细
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:28:01
 */
@ApiModel(value = "GoodsHandleShelfVO", description = "储存管理-商品处理-药品处理货位明细")
public class GoodsHandleShelfVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID")
	private Long id;
	
	/**
     * 企业ID
     */
	@ApiModelProperty(required = true, value = "企业ID")
	private Long enterpriseId;
	
	/**
     * 上级企业ID
     */
	@ApiModelProperty(required = true, value = "上级企业ID")
	private Long parentId;
	
	/**
     * 单据（药品处理单）ID
     */
	@ApiModelProperty(required = true, value = "单据（药品处理单）ID")
	private Long handleId;
	
	/**
     * 商品ID
     */
	@ApiModelProperty(required = true, value = "商品ID")
	private Long goodsId;
	
	/**
     * 批号ID
     */
	@ApiModelProperty(required = true, value = "批号ID")
	private Long lotId;
	
	/**
     * 批号
     */
	@ApiModelProperty(required = true, value = "批号")
	private String lotNumber;
	
	/**
     * 生产日期
     */
	@ApiModelProperty(required = true, value = "生产日期")
	private Date productDate;
	
	/**
     * 有效期
     */
	@ApiModelProperty(required = true, value = "有效期")
	private Date validDate;
	
	/**
     * 货位ID
     */
	@ApiModelProperty(required = true, value = "货位ID")
	private Long shelfId;
	
	/**
     * 货位名称
     */
	@ApiModelProperty(required = true, value = "货位名称")
	private String shelfName;
	
	/**
     * 质量状况
     */
	@ApiModelProperty(required = false, value = "质量状况")
	private String shelfStatusDesc;
	
	/**
     * 数量
     */
	@ApiModelProperty(required = true, value = "数量")
	private BigDecimal quantity;
	
	/**
     * 行号
     */
	@ApiModelProperty(required = true, value = "行号")
	private Integer lineNum;
	
	/**
     * 状态
     */
	@ApiModelProperty(required = true, value = "状态")
	private Integer status;
	
	/**
     * 备注
     */
	@ApiModelProperty(required = false, value = "备注")
	private String remark;
	

	/**
	 * 主键ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 主键ID
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * 企业ID
	 */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	/**
	 * 企业ID
	 */
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	
	/**
	 * 上级企业ID
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	/**
	 * 上级企业ID
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * 单据（药品处理单）ID
	 */
	public void setHandleId(Long handleId) {
		this.handleId = handleId;
	}
	
	/**
	 * 单据（药品处理单）ID
	 */
	public Long getHandleId() {
		return handleId;
	}
	
	/**
	 * 商品ID
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	
	/**
	 * 商品ID
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	
	/**
	 * 批号ID
	 */
	public void setLotId(Long lotId) {
		this.lotId = lotId;
	}
	
	/**
	 * 批号ID
	 */
	public Long getLotId() {
		return lotId;
	}
	
	/**
	 * 批号
	 */
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	
	/**
	 * 批号
	 */
	public String getLotNumber() {
		return lotNumber;
	}
	
	/**
	 * 生产日期
	 */
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	
	/**
	 * 生产日期
	 */
	public Date getProductDate() {
		return productDate;
	}
	
	/**
	 * 有效期
	 */
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	
	/**
	 * 有效期
	 */
	public Date getValidDate() {
		return validDate;
	}
	
	/**
	 * 货位ID
	 */
	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}
	
	/**
	 * 货位ID
	 */
	public Long getShelfId() {
		return shelfId;
	}
	
	/**
	 * 货位名称
	 */
	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}
	
	/**
	 * 货位名称
	 */
	public String getShelfName() {
		return shelfName;
	}
	
	/**
	 * 质量状况
	 */
	public void setShelfStatusDesc(String shelfStatusDesc) {
		this.shelfStatusDesc = shelfStatusDesc;
	}
	
	/**
	 * 质量状况
	 */
	public String getShelfStatusDesc() {
		return shelfStatusDesc;
	}
	
	/**
	 * 数量
	 */
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * 数量
	 */
	public BigDecimal getQuantity() {
		return quantity;
	}
	
	/**
	 * 行号
	 */
	public void setLineNum(Integer lineNum) {
		this.lineNum = lineNum;
	}
	
	/**
	 * 行号
	 */
	public Integer getLineNum() {
		return lineNum;
	}
	
	/**
	 * 状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	/**
	 * 状态
	 */
	public Integer getStatus() {
		return status;
	}
	
	/**
	 * 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 备注
	 */
	public String getRemark() {
		return remark;
	}
	

}