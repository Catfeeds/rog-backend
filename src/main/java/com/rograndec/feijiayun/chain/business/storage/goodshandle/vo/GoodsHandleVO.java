package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

 /**
 * 
 * @ClassName: GoodsHandleVO
 * @Description:  储存管理-商品处理-药品处理
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-27 17:27:38
 */
@ApiModel(value = "GoodsHandleVO", description = "储存管理-商品处理-药品处理")
public class GoodsHandleVO implements Serializable {
	
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
     * 单据类型（5312）
     */
	@ApiModelProperty(required = true, value = "单据类型（5312）")
	private Integer orderType;
	
	/**
     * 处理日期
     */
	@ApiModelProperty(required = true, value = "处理日期")
	private Date handleDate;
	
	/**
     * 处理单号
     */
	@ApiModelProperty(required = true, value = "处理单号")
	private String code;
	
	/**
     * 处理人员ID
     */
	@ApiModelProperty(required = true, value = "处理人员ID")
	private Long handleManId;
	
	/**
     * 处理人员编码
     */
	@ApiModelProperty(required = true, value = "处理人员编码")
	private String handleManCode;
	
	/**
     * 处理人员名称
     */
	@ApiModelProperty(required = true, value = "处理人员名称")
	private String handleManName;
	
	/**
     * 处理结果（0-解除锁定；1-移动到不合格品货位）
     */
	@ApiModelProperty(required = true, value = "处理结果（0-解除锁定；1-移动到不合格品货位）")
	private Integer handleResult;
	
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
	 * 单据类型（5312）
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * 单据类型（5312）
	 */
	public Integer getOrderType() {
		return orderType;
	}
	
	/**
	 * 处理日期
	 */
	public void setHandleDate(Date handleDate) {
		this.handleDate = handleDate;
	}
	
	/**
	 * 处理日期
	 */
	public Date getHandleDate() {
		return handleDate;
	}
	
	/**
	 * 处理单号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 处理单号
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 处理人员ID
	 */
	public void setHandleManId(Long handleManId) {
		this.handleManId = handleManId;
	}
	
	/**
	 * 处理人员ID
	 */
	public Long getHandleManId() {
		return handleManId;
	}
	
	/**
	 * 处理人员编码
	 */
	public void setHandleManCode(String handleManCode) {
		this.handleManCode = handleManCode;
	}
	
	/**
	 * 处理人员编码
	 */
	public String getHandleManCode() {
		return handleManCode;
	}
	
	/**
	 * 处理人员名称
	 */
	public void setHandleManName(String handleManName) {
		this.handleManName = handleManName;
	}
	
	/**
	 * 处理人员名称
	 */
	public String getHandleManName() {
		return handleManName;
	}
	
	/**
	 * 处理结果（0-解除锁定；1-移动到不合格品货位）
	 */
	public void setHandleResult(Integer handleResult) {
		this.handleResult = handleResult;
	}
	
	/**
	 * 处理结果（0-解除锁定；1-移动到不合格品货位）
	 */
	public Integer getHandleResult() {
		return handleResult;
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