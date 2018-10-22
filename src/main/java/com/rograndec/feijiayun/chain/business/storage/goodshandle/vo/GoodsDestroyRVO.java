package com.rograndec.feijiayun.chain.business.storage.goodshandle.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_goods_destroy
 * @author 孙帮祥
 * 返回到前端和接受前端的参数的对象
 * 2017-09-25
 */
public class GoodsDestroyRVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;


    /**
     * 单据类型（5308）
     */
    @ApiModelProperty(value = "单据类型（5308）")
    private Integer orderType;

    /**
     * 销毁日期
     */
    @ApiModelProperty(value = "销毁日期")
    private Date destroyDate;

    /**
     * 销毁单号
     */
    @ApiModelProperty(value = "销毁单号")
    private String code;

    /**
     * 销毁人员ID
     */
    @ApiModelProperty(value = "销毁人员ID")
    private Long destroyManId;

    /**
     * 销毁人员编码
     */
    @ApiModelProperty(value = "销毁人员编码")
    private String destroyManCode;

    /**
     * 销毁人员名称
     */
    @ApiModelProperty(value = "销毁人员名称")
    private String destroyManName;

    /**
     * 销毁品种（0-常规商品；1-特殊管理药品）
     */
    @ApiModelProperty(value = "销毁品种（0-常规商品；1-特殊管理药品）")
    private Integer destroyGoods;

    /**
     * 销毁原因（0-过期；1-失效；2-破损；3-残损；4-变色；5-发霉；6-虫蛀；7-其它）
     */
    @ApiModelProperty(value = "销毁原因（0-过期；1-失效；2-破损；3-残损；4-变色；5-发霉；6-虫蛀；7-其它）")
    private Integer destroyReason;

    /**
     * 销毁时间
     */
    @ApiModelProperty(value = "销毁时间")
    private String destroyTime;

    /**
     * 销毁方式
     */
    @ApiModelProperty(value = "销毁方式")
    private String destroyMode;

    /**
     * 销毁单位
     */
    @ApiModelProperty(value = "销毁单位")
    private String destroyUnit;

    /**
     * 销毁地点
     */
    @ApiModelProperty(value = "销毁地点")
    private String destroyPlace;

    /**
     * 监控人员1
     */
    @ApiModelProperty(value = "监控人员1")
    private String monitor;

    /**
     * 监控人员2
     */
    @ApiModelProperty(value = "监控人员2")
    private String secondMonitor;

    /**
     * 数量合计
     */
    @ApiModelProperty(value = "数量合计")
    private BigDecimal quantityTotal;

    /**
     * 品种数量
     */
    @ApiModelProperty(value = "品种数量")
    private Integer varietiesQuantity;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 详情列表
     * */
    @ApiModelProperty(value = "商品销毁单详情备注")
    private List<GoodsDestroyDetailRVO> goodsDestroyDetailRVOList;
    /**
     * saas_goods_destroy
     */
    private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Date getDestroyDate() {
		return destroyDate;
	}

	public void setDestroyDate(Date destroyDate) {
		this.destroyDate = destroyDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getDestroyManId() {
		return destroyManId;
	}

	public void setDestroyManId(Long destroyManId) {
		this.destroyManId = destroyManId;
	}

	public String getDestroyManCode() {
		return destroyManCode;
	}

	public void setDestroyManCode(String destroyManCode) {
		this.destroyManCode = destroyManCode;
	}

	public String getDestroyManName() {
		return destroyManName;
	}

	public void setDestroyManName(String destroyManName) {
		this.destroyManName = destroyManName;
	}

	public Integer getDestroyGoods() {
		return destroyGoods;
	}

	public void setDestroyGoods(Integer destroyGoods) {
		this.destroyGoods = destroyGoods;
	}

	public Integer getDestroyReason() {
		return destroyReason;
	}

	public void setDestroyReason(Integer destroyReason) {
		this.destroyReason = destroyReason;
	}

	public String getDestroyTime() {
		return destroyTime;
	}

	public void setDestroyTime(String destroyTime) {
		this.destroyTime = destroyTime;
	}

	public String getDestroyMode() {
		return destroyMode;
	}

	public void setDestroyMode(String destroyMode) {
		this.destroyMode = destroyMode;
	}

	public String getDestroyUnit() {
		return destroyUnit;
	}

	public void setDestroyUnit(String destroyUnit) {
		this.destroyUnit = destroyUnit;
	}

	public String getDestroyPlace() {
		return destroyPlace;
	}

	public void setDestroyPlace(String destroyPlace) {
		this.destroyPlace = destroyPlace;
	}

	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}

	public String getSecondMonitor() {
		return secondMonitor;
	}

	public void setSecondMonitor(String secondMonitor) {
		this.secondMonitor = secondMonitor;
	}

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public Integer getVarietiesQuantity() {
		return varietiesQuantity;
	}

	public void setVarietiesQuantity(Integer varietiesQuantity) {
		this.varietiesQuantity = varietiesQuantity;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<GoodsDestroyDetailRVO> getGoodsDestroyDetailRVOList() {
		return goodsDestroyDetailRVOList;
	}

	public void setGoodsDestroyDetailRVOList(List<GoodsDestroyDetailRVO> goodsDestroyDetailRVOList) {
		this.goodsDestroyDetailRVOList = goodsDestroyDetailRVOList;
	}
	

}