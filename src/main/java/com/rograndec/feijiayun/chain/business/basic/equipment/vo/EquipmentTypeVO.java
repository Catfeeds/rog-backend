package com.rograndec.feijiayun.chain.business.basic.equipment.vo;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

 /**
 * 
 * @ClassName: EquipmentTypeVO
 * @Description:  设备类型
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-10-13 11:11:11
 */
@ApiModel(value = "EquipmentTypeVO", description = "设备类型")
public class EquipmentTypeVO implements Serializable {
	
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
     * 编码
     */
	@ApiModelProperty(required = true, value = "编码")
	private String code;
	
	/**
     * 名称
     */
	@ApiModelProperty(required = true, value = "名称")
	private String name;
	
	/**
     * 上级类型编码
     */
	@ApiModelProperty(required = false, value = "上级类型编码")
	private String parentTypeCode;
	
	/**
     * 上级类型名称
     */
	@ApiModelProperty(required = false, value = "上级类型名称")
	private String parentTypeName;
	

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
	 * 编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 编码
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 名称
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 上级类型编码
	 */
	public void setParentTypeCode(String parentTypeCode) {
		this.parentTypeCode = parentTypeCode;
	}
	
	/**
	 * 上级类型编码
	 */
	public String getParentTypeCode() {
		return parentTypeCode;
	}
	
	/**
	 * 上级类型名称
	 */
	public void setParentTypeName(String parentTypeName) {
		this.parentTypeName = parentTypeName;
	}
	
	/**
	 * 上级类型名称
	 */
	public String getParentTypeName() {
		return parentTypeName;
	}
	

}