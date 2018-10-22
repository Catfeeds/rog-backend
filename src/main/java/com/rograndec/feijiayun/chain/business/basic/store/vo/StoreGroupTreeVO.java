package com.rograndec.feijiayun.chain.business.basic.store.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: StoreGroupVo   
 * @Description: TODO分组数
 * @author liuqun
 * @version 1.0 
 * @date 2017年8月30日 下午7:33:34
 */
@ApiModel(value = "StoreGroupTreeVo", description = "门店分组树对象")
public class StoreGroupTreeVO<T> implements Serializable{

	/**
	 * @Description:
	 * author liuqun
	 * @date 2017年8月30日 下午7:33:19 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = -8356774721216148296L;
	
	@ApiModelProperty(required = true, value = "主键ID")
	private Long id;
	
	@ApiModelProperty(required = true, value = "父级ID")
	private Long parentId;
	
	@ApiModelProperty(required = true, value = "是否叶子节点")
	private boolean leaf;
	
	private String label;
	
	@ApiModelProperty(required = true, value = "是否显示编辑")
	private boolean supplierShow;
	
	@ApiModelProperty(required = true, value = "是否显示删除")
	private boolean nodeShowDelete;
	
	@ApiModelProperty(required = true, value = "是否显示移除")
	private boolean nodeShowRemove;
	
	@ApiModelProperty(required = true, value = "是否显示关联")
	private boolean nodeShowRelateBranch;
	
	@ApiModelProperty(required = true, value = "级别,目前共三级,1,2,3，2级支持关联、修改、删除、移除，3级支持移除")
	private Integer level;//级别,目前共三级
	
	private T data;
	
	private List<StoreGroupTreeVO<T>> children = new ArrayList<StoreGroupTreeVO<T>>();
	
	
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

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public boolean isSupplierShow() {
		return supplierShow;
	}

	public void setSupplierShow(boolean supplierShow) {
		this.supplierShow = supplierShow;
	}

	public boolean isNodeShowDelete() {
		return nodeShowDelete;
	}

	public void setNodeShowDelete(boolean nodeShowDelete) {
		this.nodeShowDelete = nodeShowDelete;
	}

	public boolean isNodeShowRemove() {
		return nodeShowRemove;
	}

	public void setNodeShowRemove(boolean nodeShowRemove) {
		this.nodeShowRemove = nodeShowRemove;
	}

	public boolean isNodeShowRelateBranch() {
		return nodeShowRelateBranch;
	}

	public void setNodeShowRelateBranch(boolean nodeShowRelateBranch) {
		this.nodeShowRelateBranch = nodeShowRelateBranch;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<StoreGroupTreeVO<T>> getChildren() {
		return children;
	}

	public void setChildren(List<StoreGroupTreeVO<T>> children) {
		this.children = children;
	}

	
}
