package com.rograndec.feijiayun.chain.business.online.purchase.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: MphOrderVO   
 * @Description: 我的订单VO
 * @author yuting.li
 * @version 1.0 
 * @date 2017年11月23日 上午11:52:16
 */

@ApiModel(value = "MphOrderVO", description = "线上采购-我的订单")
public class MphOrderVO implements Serializable{
	
	
	/**
	 * @Description: TODO(描述funcion功能)
	 * author yuting.li
	 * @date 2017年11月23日 下午1:40:22 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;

	//订单ID
	@ApiModelProperty(value = "订单ID")
	private String oid;
	
	//订单号
	@ApiModelProperty(value = "订单号")
	private String osn;
	
	@ApiModelProperty(value = "融贯通用户id")
	private Integer uId;
	
	@ApiModelProperty(value = "订单来源")
	private String source;
	
	//订单来源
	@ApiModelProperty(value = "来源编码",hidden=true)
	private String sourceCode;
	
	@ApiModelProperty(value = "订单类型",hidden=true)
	private String orderType;
	
	//下单时间
	@JsonFormat(pattern="yyyy年MM月dd HH:mm:ss")
	@ApiModelProperty(value = "下单时间")
	private String oaddTime;
	
	//供应商
	@ApiModelProperty(value = "供应商")
	private String osellerName;
	
	@ApiModelProperty(value = "付款方式",hidden=true)
	private Integer opaymentMethod;
	
	@ApiModelProperty(value = "付款状态")
	private Integer payStatus;
	
	@ApiModelProperty(value = "付款方式显示")
	private String opaymentMethodDesc;
	
	@ApiModelProperty(value = "付款状态显示")
	private String payStatusDesc;
	
	//实付/应收金额
	@ApiModelProperty(value = "支付金额")
	private BigDecimal payAmount;
	
	//订单状态
	@ApiModelProperty(value = "订单状态")
	private Integer ostatus;
	
	@ApiModelProperty(value = "订单状态显示")
	private String ostatusShow;
	
	@ApiModelProperty(value = "mph跳转url")
	private String mphImplUrl;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getOsn() {
		return osn;
	}

	public void setOsn(String osn) {
		this.osn = osn;
	}

	public String getSource() {
		if ("saas".equals(sourceCode)) {
			if ("2".equals(orderType)) {
				return "智采";
			} else if ("3".equals(orderType)) {
				return "集采";
			}
		} else if ("mypharma".equals(sourceCode)) {
			return "我的医药网";
		} else if ("kkmy".equals(sourceCode)) {
			return "康康";
		} else if ("wdyy-app".equals(sourceCode)) {
			return "我的医药网APP";
		} else if ("wdzs-app".equals(sourceCode)) {
			return "诊所";
		}
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOaddTime() {
		return oaddTime;
	}

	public void setOaddTime(String oaddTime) {
		this.oaddTime = oaddTime;
	}

	public String getOsellerName() {
		return osellerName;
	}

	public void setOsellerName(String osellerName) {
		this.osellerName = osellerName;
	}

	public Integer getOpaymentMethod() {
		return opaymentMethod;
	}

	public void setOpaymentMethod(Integer opaymentMethod) {
		this.opaymentMethod = opaymentMethod;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	
	public String getOpaymentMethodDesc() {
		// 付款方式
		switch (opaymentMethod) {
		case 1:
			opaymentMethodDesc = "在线支付";
			break;
		case 2:
			opaymentMethodDesc = "账期付款";
			break;
		case 3:
			opaymentMethodDesc = "线下付款";
			break;
		case 4:
			opaymentMethodDesc = "药白条";
			break;
		case 5:
			opaymentMethodDesc = "帮你付";
			break;
		case 6:
			opaymentMethodDesc = "快速贷";
			break;
		default:
			opaymentMethodDesc = "未知";
			break;
		}
		return opaymentMethodDesc;
	}

	public void setOpaymentMethodDesc(String opaymentMethodDesc) {
		this.opaymentMethodDesc = opaymentMethodDesc;
	}

	public String getPayStatusDesc() {
		switch (payStatus) {
		case 0:
			payStatusDesc = "无";
			break;
		case 10:
			payStatusDesc = "待支付";
			break;
		case 50:
			payStatusDesc = "已支付";
			break;
		default:
			payStatusDesc = "未知";
			break;
		}
		return payStatusDesc;
	}

	public void setPayStatusDesc(String payStatusDesc) {
		this.payStatusDesc = payStatusDesc;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public Integer getOstatus() {
		return ostatus;
	}

	public void setOstatus(Integer ostatus) {
		this.ostatus = ostatus;
	}

	public String getOstatusShow() {
		switch (ostatus) {
		case -100:
			ostatusShow = "已删除";
			break;
		case -90:
			ostatusShow = "已废弃";
			break;
		case -21:
			ostatusShow = "交易取消";
			break;
		case -22:
			ostatusShow = "交易取消";
			break;
		case -10:
			ostatusShow = "交易取消";
			break;
		case 0:
			ostatusShow = "待供应商发货";
			break;
		case 10:
			ostatusShow = "待供应商发货";
			break;
		case 30:
			ostatusShow = "待供应商发货";   //不可取消
			break;
		case 90:
			ostatusShow = "待确认收货";
			break;
		case 100:
			ostatusShow = "交易完成";
			break;

		default:
			ostatusShow = "未知";
			break;
		}
		return ostatusShow;
	}

	public void setOstatusShow(String ostatusShow) {
		this.ostatusShow = ostatusShow;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public String getMphImplUrl() {
		return mphImplUrl;
	}

	public void setMphImplUrl(String mphImplUrl) {
		this.mphImplUrl = mphImplUrl;
	}

	@Override
	public String toString() {
		return "MphOrderVO [oid=" + oid + ", osn=" + osn + ", uId=" + uId + ", source=" + source + ", sourceCode="
				+ sourceCode + ", orderType=" + orderType + ", oaddTime=" + oaddTime + ", osellerName=" + osellerName
				+ ", opaymentMethod=" + opaymentMethod + ", payStatus=" + payStatus + ", opaymentMethodDesc="
				+ opaymentMethodDesc + ", payStatusDesc=" + payStatusDesc + ", payAmount=" + payAmount + ", ostatus="
				+ ostatus + ", ostatusShow=" + ostatusShow + "]";
	}

	
	
}
