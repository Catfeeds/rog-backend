package com.rograndec.feijiayun.chain.business.distr.parent.vo.pick;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOut;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOutDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrSend;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(value = "DistrSendOperationVO", description = "配货单-拣货对象")
public class DistrSendOperationVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(required = false, value = "配货单ID")
	private Long id;
	
	@ApiModelProperty(required = false, value = "拣货单号")
	private String code;

	@ApiModelProperty(required = false, value = "拣货日期")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date pickDate;

	@ApiModelProperty(required = false, value = "拣货人员ID")
	private Long pickManId;
	
	@ApiModelProperty(required = false, value = "拣货人员")
	private String pickManName;
	
	@ApiModelProperty(required = false, value = "备注")
	private String remark;
	
	@ApiModelProperty(required = false, value = "要货单位编码")
	private String requestUnitCode;
	
	@ApiModelProperty(required = false, value = "要货单位名称")
	private String requestUnitName;
	
	@ApiModelProperty(required = false, value = "配货单号")
	private String distrCode;
	
	@ApiModelProperty(required = false, value = "配货明细")
	private List<DistrSendOperationDtlVO> dtlList = new ArrayList<DistrSendOperationDtlVO>();
	
	@ApiModelProperty(required = false, value = "拣货数量合计")
	private BigDecimal quantityTotal;

	public static DistrSendOperationVO getDistrSendOperationVO(DistrSend distrSend,DistrOut distrOut){

		List<DistrOutDetail> distrOutDetailList = distrOut.getDistrOutDetailList();

		DistrSendOperationVO distrSendOperationVO = new DistrSendOperationVO();
		distrSendOperationVO.setId(distrSend.getId());

		distrSendOperationVO.setPickDate(new Date());

		distrSendOperationVO.setPickManId(distrOut.getOutManId());

		distrSendOperationVO.setPickManName(distrOut.getOutManName());

		distrSendOperationVO.setRequestUnitCode(distrOut.getRequestUnitCode());

		distrSendOperationVO.setRequestUnitName(distrOut.getRequestUnitName());

		distrSendOperationVO.setDistrCode(distrSend.getCode());

		List<DistrSendOperationDtlVO> distrSendOperationDtlVOs = DistrSendOperationDtlVO.getDistrSendOperationDtlVOs(distrOut);
		distrSendOperationVO.setDtlList(distrSendOperationDtlVOs);

		BigDecimal quantityTotal = BigDecimal.ZERO;
		for(DistrSendOperationDtlVO distrSendOperationDtlVO : distrSendOperationDtlVOs){
			quantityTotal = quantityTotal.add(distrSendOperationDtlVO.getQuantity());
		}
		distrSendOperationVO.setQuantityTotal(quantityTotal);

		return distrSendOperationVO;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getPickDate() {
		return pickDate;
	}

	public void setPickDate(Date pickDate) {
		this.pickDate = pickDate;
	}

	public Long getPickManId() {
		return pickManId;
	}

	public void setPickManId(Long pickManId) {
		this.pickManId = pickManId;
	}

	public String getPickManName() {
		return pickManName;
	}

	public void setPickManName(String pickManName) {
		this.pickManName = pickManName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRequestUnitCode() {
		return requestUnitCode;
	}

	public void setRequestUnitCode(String requestUnitCode) {
		this.requestUnitCode = requestUnitCode;
	}

	public String getRequestUnitName() {
		return requestUnitName;
	}

	public void setRequestUnitName(String requestUnitName) {
		this.requestUnitName = requestUnitName;
	}

	public String getDistrCode() {
		return distrCode;
	}

	public void setDistrCode(String distrCode) {
		this.distrCode = distrCode;
	}

	public List<DistrSendOperationDtlVO> getDtlList() {
		return dtlList;
	}

	public void setDtlList(List<DistrSendOperationDtlVO> dtlList) {
		this.dtlList = dtlList;
	}

	public BigDecimal getQuantityTotal() {
		return quantityTotal;
	}

	public void setQuantityTotal(BigDecimal quantityTotal) {
		this.quantityTotal = quantityTotal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
