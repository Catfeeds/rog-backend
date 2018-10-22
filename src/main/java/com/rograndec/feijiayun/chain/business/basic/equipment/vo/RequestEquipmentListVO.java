package com.rograndec.feijiayun.chain.business.basic.equipment.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: EquipmentSaveOrupdateVO
 * @Description: 设施设备-Rest接口
 * @date 2017-10-13 13:26:02
 */
@ApiModel(value = "RequestEquipmentListVO", description = "设施设备")
public class RequestEquipmentListVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(required = false, value = "类型ID")
	private Long typeId;

	@ApiModelProperty(required = false, value = "编码/名称")
	private String code;

	@ApiModelProperty(required = false,value = "组织机构类型")
	private Integer chainType;
	@ApiModelProperty(required = false,value = "组织机构编码")
	private String enterpriseCode;
	@ApiModelProperty(required = false,value = "组织机构名称")
	private String enterpriseName;

	@NotNull(message = "每页显示数据不能为空")
	@ApiModelProperty(value = "每页显示数据", required = true)
	private Integer pageSize;

	@NotNull(message = "页码不能为空")
	@ApiModelProperty(value = "页码", required = true)
	private Integer pageNo;

	@ApiModelProperty(required = false, value = "按组织机构编码排序0-升序 1-降序")
	private Integer sortCode;

	@ApiModelProperty(required = false, value = "按设备编码排序0-升序 1-降序")
	private Integer sortECode;

	private String sort;

	private Long enterpriseId;
	
	/**
	 * 企业id集合
	 */
	private List<Long> list;

	public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

	public String getEnterpriseCode() {
		return enterpriseCode;
	}

	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Integer getSortECode() {
		return sortECode;
	}

	public void setSortECode(Integer sortECode) {
		this.sortECode = sortECode;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getSortCode() {
		return sortCode;
	}

	public void setSortCode(Integer sortCode) {
		this.sortCode = sortCode;
	}

	public List<Long> getList() {
		return list;
	}

	public void setList(List<Long> list) {
		this.list = list;
	}
	
}