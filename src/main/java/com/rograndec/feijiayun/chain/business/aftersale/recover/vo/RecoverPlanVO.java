package com.rograndec.feijiayun.chain.business.aftersale.recover.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

 /**
 * 
 * @ClassName: RecoverPlanVO
 * @Description:  售后管理-追回管理-追回计划
 * @author xingjian.lan
 * @version 1.0 
 * @date 2017-10-16 17:51:12
 */
@ApiModel(value = "RecoverPlanVO", description = "售后管理-追回管理-追回计划")
public class RecoverPlanVO implements Serializable {
	
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
     * 单据类型（6303）
     */
	@ApiModelProperty(required = true, value = "单据类型（6303）")
	private Integer orderType;
	
	/**
     * 单据编码
     */
	@ApiModelProperty(required = true, value = "单据编码")
	private String code;
	
	/**
     * 通知日期
     */
	@ApiModelProperty(required = false, value = "通知日期")
	private Date planDate;
	
	/**
     * 通知人ID
     */
	@ApiModelProperty(required = false, value = "通知人ID")
	private Long planManId;
	
	/**
     * 通知人编码
     */
	@ApiModelProperty(required = false, value = "通知人编码")
	private String planManCode;
	
	/**
     * 通知人名称
     */
	@ApiModelProperty(required = false, value = "通知人名称")
	private String planManName;
	
	/**
     * 追回责任人
     */
	@ApiModelProperty(required = false, value = "追回责任人")
	private String recoverMan;
	
	/**
     * 追回责任人电话
     */
	@ApiModelProperty(required = false, value = "追回责任人电话")
	private String recoverManPhone;
	
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

     @ApiModelProperty(value = "能否修改/删除标志,true可修改/删除,false不可修改/删除")
     private Boolean modifyFlag;

	private List<RecoverPlanDetailVO> recoverPlanDetailVOList;

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
	 * 单据类型（6303）
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	
	/**
	 * 单据类型（6303）
	 */
	public Integer getOrderType() {
		return orderType;
	}
	
	/**
	 * 单据编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 单据编码
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 通知日期
	 */
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	
	/**
	 * 通知日期
	 */
	public Date getPlanDate() {
		return planDate;
	}
	
	/**
	 * 通知人ID
	 */
	public void setPlanManId(Long planManId) {
		this.planManId = planManId;
	}
	
	/**
	 * 通知人ID
	 */
	public Long getPlanManId() {
		return planManId;
	}
	
	/**
	 * 通知人编码
	 */
	public void setPlanManCode(String planManCode) {
		this.planManCode = planManCode;
	}
	
	/**
	 * 通知人编码
	 */
	public String getPlanManCode() {
		return planManCode;
	}
	
	/**
	 * 通知人名称
	 */
	public void setPlanManName(String planManName) {
		this.planManName = planManName;
	}
	
	/**
	 * 通知人名称
	 */
	public String getPlanManName() {
		return planManName;
	}
	
	/**
	 * 追回责任人
	 */
	public void setRecoverMan(String recoverMan) {
		this.recoverMan = recoverMan;
	}
	
	/**
	 * 追回责任人
	 */
	public String getRecoverMan() {
		return recoverMan;
	}
	
	/**
	 * 追回责任人电话
	 */
	public void setRecoverManPhone(String recoverManPhone) {
		this.recoverManPhone = recoverManPhone;
	}
	
	/**
	 * 追回责任人电话
	 */
	public String getRecoverManPhone() {
		return recoverManPhone;
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

     public List<RecoverPlanDetailVO> getRecoverPlanDetailVOList() {
         return recoverPlanDetailVOList;
     }

     public void setRecoverPlanDetailVOList(List<RecoverPlanDetailVO> recoverPlanDetailVOList) {
         this.recoverPlanDetailVOList = recoverPlanDetailVOList;
     }

     public Boolean getModifyFlag() {
         return modifyFlag;
     }

     public void setModifyFlag(Boolean modifyFlag) {
         this.modifyFlag = modifyFlag;
     }
 }