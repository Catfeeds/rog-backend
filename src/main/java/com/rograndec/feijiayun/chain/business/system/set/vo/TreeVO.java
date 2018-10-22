package com.rograndec.feijiayun.chain.business.system.set.vo;

import java.io.Serializable;
import java.util.List;

public class TreeVO<T> implements Serializable{
	//父对象
	private Long id;
	private Long parentId;
	private String label;
	private boolean open;
	private boolean checked;
	private boolean nodeSelectNotAll;
	private boolean visible = true;
	private boolean leaf;
	private boolean searched;
	private T data;
	
	//子对象

	public Long getId() {
		return id;
	}

	private List<TreeVO> children;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<TreeVO> getChildren() {
		return children;
	}

	public boolean getSearched() {
		return searched;
	}

	public void setChildren(List<TreeVO> children) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TreeVO<?> treePOJO = (TreeVO<?>) o;

		if (open != treePOJO.open) return false;
		if (checked != treePOJO.checked) return false;
		if (nodeSelectNotAll != treePOJO.nodeSelectNotAll) return false;
		if (visible != treePOJO.visible) return false;
		if (leaf != treePOJO.leaf) return false;
		if (searched != treePOJO.searched) return false;
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
		result = 31 * result + (data != null ? data.hashCode() : 0);
		result = 31 * result + (children != null ? children.hashCode() : 0);
		return result;
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
				", data=" + data +
				", children=" + children +
				'}';
	}
}
