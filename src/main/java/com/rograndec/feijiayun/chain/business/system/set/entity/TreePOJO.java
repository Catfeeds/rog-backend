package com.rograndec.feijiayun.chain.business.system.set.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TreePOJO<T> implements Serializable{
	
	private static final long serialVersionUID = -9059185339771252135L;
	//父对象
	private Long id;
	private Long parentId;
	private String label;
	private boolean open=true;//是否展开,默认为true
	private boolean checked;
	private boolean nodeSelectNotAll;
	private boolean visible = true;
	private boolean leaf;//叶子节点
	private boolean searched;
	private boolean nodeShowDelete;//是否显示删除
	private boolean nodeShowRemove;//是否显示移出
	private boolean nodeShowRelateBranch;//是否关联
	private boolean supplierShow = true;//是否修改
	private boolean seeDelete;//是否显示删除
	private boolean seeUpdate;//是够显示修改

	private T data;
	
	//子对象

	public Long getId() {
		return id;
	}

	private List<TreePOJO<T>> children = new ArrayList<TreePOJO<T>>();

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<TreePOJO<T>> getChildren() {
		return children;
	}

	public boolean getSearched() {
		return searched;
	}

	public void setChildren(List<TreePOJO<T>> children) {
		this.children = children;
	}

	public String getLabel() {
		return label;
	}

	public boolean getOpen() {
		return open;
	}

	public boolean getChecked() {
		return checked;
	}

	public boolean getNodeSelectNotAll() {
		return nodeSelectNotAll;
	}

	public boolean getVisible() {
		return visible;
	}

	public boolean getLeaf() {
		return leaf;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public void setNodeSelectNotAll(boolean nodeSelectNotAll) {
		this.nodeSelectNotAll = nodeSelectNotAll;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public void setSearched(boolean searched) {
		this.searched = searched;
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

	public boolean getNodeShowDelete() {
		return nodeShowDelete;
	}

	public void setNodeShowDelete(boolean nodeShowDelete) {
		this.nodeShowDelete = nodeShowDelete;
	}

	public boolean getNodeShowRemove() {
		return nodeShowRemove;
	}

	public void setNodeShowRemove(boolean nodeShowRemove) {
		this.nodeShowRemove = nodeShowRemove;
	}

	public boolean getNodeShowRelateBranch() {
		return nodeShowRelateBranch;
	}

	public void setNodeShowRelateBranch(boolean nodeShowRelateBranch) {
		this.nodeShowRelateBranch = nodeShowRelateBranch;
	}

	public boolean getSupplierShow() {
		return supplierShow;
	}

	public void setSupplierShow(boolean supplierShow) {
		this.supplierShow = supplierShow;
	}

	public boolean getSeeDelete() {
		return seeDelete;
	}

	public void setSeeDelete(boolean seeDelete) {
		this.seeDelete = seeDelete;
	}

	public boolean getSeeUpdate() {
		return seeUpdate;
	}

	public void setSeeUpdate(boolean seeUpdate) {
		this.seeUpdate = seeUpdate;
	}


	@Override
	public String toString() {
		return "TreePOJO{" +
				"id=" + id +
				", parentId=" + parentId +
				", label='" + label + '\'' +
				", open=" + open +
				", checked=" + checked +
				", nodeSelectNotAll=" + nodeSelectNotAll +
				", visible=" + visible +
				", leaf=" + leaf +
				", searched=" + searched +
				", nodeShowDelete=" + nodeShowDelete +
				", nodeShowRemove=" + nodeShowRemove +
				", nodeShowRelateBranch=" + nodeShowRelateBranch +
				", supplierShow=" + supplierShow +
				", seeDelete=" + seeDelete +
				", seeUpdate=" + seeUpdate +
				", data=" + data +
				", children=" + children +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TreePOJO<?> treePOJO = (TreePOJO<?>) o;

		if (open != treePOJO.open) return false;
		if (checked != treePOJO.checked) return false;
		if (nodeSelectNotAll != treePOJO.nodeSelectNotAll) return false;
		if (visible != treePOJO.visible) return false;
		if (leaf != treePOJO.leaf) return false;
		if (searched != treePOJO.searched) return false;
		if (nodeShowDelete != treePOJO.nodeShowDelete) return false;
		if (nodeShowRemove != treePOJO.nodeShowRemove) return false;
		if (nodeShowRelateBranch != treePOJO.nodeShowRelateBranch) return false;
		if (supplierShow != treePOJO.supplierShow) return false;
		if (seeDelete != treePOJO.seeDelete) return false;
		if (seeUpdate != treePOJO.seeUpdate) return false;
		if (id != null ? !id.equals(treePOJO.id) : treePOJO.id != null) return false;
		if (parentId != null ? !parentId.equals(treePOJO.parentId) : treePOJO.parentId != null) return false;
		if (label != null ? !label.equals(treePOJO.label) : treePOJO.label != null) return false;
		if (data != null ? !data.equals(treePOJO.data) : treePOJO.data != null) return false;
		return children != null ? children.equals(treePOJO.children) : treePOJO.children == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
		result = 31 * result + (label != null ? label.hashCode() : 0);
		result = 31 * result + (open ? 1 : 0);
		result = 31 * result + (checked ? 1 : 0);
		result = 31 * result + (nodeSelectNotAll ? 1 : 0);
		result = 31 * result + (visible ? 1 : 0);
		result = 31 * result + (leaf ? 1 : 0);
		result = 31 * result + (searched ? 1 : 0);
		result = 31 * result + (nodeShowDelete ? 1 : 0);
		result = 31 * result + (nodeShowRemove ? 1 : 0);
		result = 31 * result + (nodeShowRelateBranch ? 1 : 0);
		result = 31 * result + (supplierShow ? 1 : 0);
		result = 31 * result + (seeDelete ? 1 : 0);
		result = 31 * result + (seeUpdate ? 1 : 0);
		result = 31 * result + (data != null ? data.hashCode() : 0);
		result = 31 * result + (children != null ? children.hashCode() : 0);
		return result;
	}
}
