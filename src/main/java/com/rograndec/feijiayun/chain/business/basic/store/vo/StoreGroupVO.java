package com.rograndec.feijiayun.chain.business.basic.store.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * @ClassName: StoreGroupVo   
 * @Description: TODO分组数
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月30日 下午7:33:34
 */
@ApiModel(value = "StoreGroupVo", description = "门店分组对象")
public class StoreGroupVO implements Serializable{

	/**
	 * @Description:
	 * author liuqun
	 * @date 2017年8月30日 下午7:33:19 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = -8356774721216148296L;
	
	/**
     * 主键ID
     */
	@ApiModelProperty(required = true, value = "主键ID，0时新建，其他值时修改")
    private Long id;

    /**
     * 分组父级ID
     */
	@ApiModelProperty(required = true, value = "父级ID，取自类型下拉框（1-自营店；2-加盟店）")
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
     * 状态（0-禁用；1-启用）
     */
	@ApiModelProperty(required = true, value = "状态（0-禁用；1-启用）")
    private Integer status;
	
	/**
     * 备注
     */
	@ApiModelProperty(required = false, value = "备注")
    private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
    
}
