package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

 /**
 * 
 * @ClassName: GoodsLockVO
 * @Description:  储存管理-商品处理-药品锁定
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:26:40
 */
@ApiModel(value = "GoodsLockVO", description = "储存管理-商品处理-药品锁定")
public class GoodsLockVO implements Serializable {
	
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
	@ApiModelProperty(required = false, value = "上级企业ID")
	private Long parentId;
	
	/**
     * 单据类型（5311）
     */
	@ApiModelProperty(required = true, value = "单据类型（5311）")
	private Integer orderType;
	
	/**
     * 锁定日期
     */
	@ApiModelProperty(required = true, value = "锁定日期")
	private Date lockDate;
	
	/**
     * 锁定单号
     */
	@ApiModelProperty(required = true, value = "锁定单号")
	private String code;
	
	/**
     * 锁定人员ID
     */
	@ApiModelProperty(required = true, value = "锁定人员ID")
	private Long lockManId;
	
	/**
     * 锁定人员编码
     */
	@ApiModelProperty(required = true, value = "锁定人员编码")
	private String lockManCode;
	
	/**
     * 锁定人员名称
     */
	@ApiModelProperty(required = true, value = "锁定人员名称")
	private String lockManName;
	
	/**
     * 锁定原因（0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品）
     */
	@ApiModelProperty(required = true, value = "锁定原因（0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品）")
	private Integer lockReason;
	
	/**
     * 数量合计
     */
	@ApiModelProperty(required = true, value = "数量合计")
	private BigDecimal quantityTotal;
	
	/**
     * 品种数量
     */
	@ApiModelProperty(required = true, value = "品种数量")
	private Integer varietiesQuantity;
	
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
	 * 单据类型（5311）
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * 单据类型（5311）
	 */
	public Integer getOrderType() {
		return orderType;
	}
	
	/**
	 * 锁定日期
	 */
	public void setLockDate(Date lockDate) {
		this.lockDate = lockDate;
	}
	
	/**
	 * 锁定日期
	 */
	public Date getLockDate() {
		return lockDate;
	}
	
	/**
	 * 锁定单号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 锁定单号
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 锁定人员ID
	 */
	public void setLockManId(Long lockManId) {
		this.lockManId = lockManId;
	}
	
	/**
	 * 锁定人员ID
	 */
	public Long getLockManId() {
		return lockManId;
	}
	
	/**
	 * 锁定人员编码
	 */
	public void setLockManCode(String lockManCode) {
		this.lockManCode = lockManCode;
	}
	
	/**
	 * 锁定人员编码
	 */
	public String getLockManCode() {
		return lockManCode;
	}
	
	/**
	 * 锁定人员名称
	 */
	public void setLockManName(String lockManName) {
		this.lockManName = lockManName;
	}
	
	/**
	 * 锁定人员名称
	 */
	public String getLockManName() {
		return lockManName;
	}
	
	/**
	 * 锁定原因（0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品）
	 */
	public void setLockReason(Integer lockReason) {
		this.lockReason = lockReason;
	}
	
	/**
	 * 锁定原因（0-疑似质量问题；1-疑似伪劣商品；2-药品养护问题商品；3-陈列检查问题商品）
	 */
	public Integer getLockReason() {
		return lockReason;
	}
	
	/**
	 * 数量合计
	 */
	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}
	
	/**
	 * 数量合计
	 */
	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}
	
	/**
	 * 品种数量
	 */
	public void setVarietiesQuantity(Integer varietiesQuantity) {
		this.varietiesQuantity = varietiesQuantity;
	}
	
	/**
	 * 品种数量
	 */
	public Integer getVarietiesQuantity() {
		return varietiesQuantity;
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