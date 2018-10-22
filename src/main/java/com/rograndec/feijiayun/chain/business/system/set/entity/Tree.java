package com.rograndec.feijiayun.chain.business.system.set.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

public class Tree<T> implements Serializable{
	
	private Long id;
	private Long parentId;
	private String label;
	private boolean open = true;
	private boolean checked;
	private boolean nodeSelectNotAll;
	private boolean visible = true;
	private boolean leaf;
	private boolean searched;
	private boolean nodeShowDelete;
	private boolean nodeShowUpdate;
	private boolean hasPosition = false;
	private boolean isPosition = false;
	//父对象存放部门信息
	private T data;
	//子对象
	private List<Tree> children = new ArrayList<Tree>();
	
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean isNodeSelectNotAll() {
		return nodeSelectNotAll;
	}
	public void setNodeSelectNotAll(boolean nodeSelectNotAll) {
		this.nodeSelectNotAll = nodeSelectNotAll;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public boolean isSearched() {
		return searched;
	}
	public void setSearched(boolean searched) {
		this.searched = searched;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	public boolean isNodeShowDelete() {
		return nodeShowDelete;
	}
	public void setNodeShowDelete(boolean nodeShowDelete) {
		this.nodeShowDelete = nodeShowDelete;
	}

	public boolean isNodeShowUpdate() {
		return nodeShowUpdate;
	}

	public void setNodeShowUpdate(boolean nodeShowUpdate) {
		this.nodeShowUpdate = nodeShowUpdate;
	}

	public boolean isPosition() {
		return isPosition;
	}

	public void setPosition(boolean position) {
		isPosition = position;
	}

	public boolean isHasPosition() {
		return hasPosition;
	}

	public void setHasPosition(boolean hasPosition) {
		this.hasPosition = hasPosition;
	}

	@Override
	public String toString() {
		return "Tree [id=" + id + ", parentId=" + parentId + ", label=" + label + ", open=" + open + ", checked="
				+ checked + ", nodeSelectNotAll=" + nodeSelectNotAll + ", visible=" + visible + ", leaf=" + leaf
				+ ", searched=" + searched + ", data=" + data + ", children=" + children
				+ "]";
	}

	
	
	

}
