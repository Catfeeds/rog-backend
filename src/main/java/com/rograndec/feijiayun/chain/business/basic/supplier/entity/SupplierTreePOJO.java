package com.rograndec.feijiayun.chain.business.basic.supplier.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;

public class SupplierTreePOJO<T> implements Serializable{

	//父对象
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
		private boolean nodeShowRemove;
		private boolean supplierShow;
		private boolean nodeShowRelateSupplier;
		private Integer dataType;
		private T data;
		private List<SupplierTreePOJO<T>> children = new ArrayList<>();
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
		public boolean isSupplierShow() {
			return supplierShow;
		}
		public void setSupplierShow(boolean supplierShow) {
			this.supplierShow = supplierShow;
		}
		public boolean isNodeShowRelateSupplier() {
			return nodeShowRelateSupplier;
		}
		public void setNodeShowRelateSupplier(boolean nodeShowRelateSupplier) {
			this.nodeShowRelateSupplier = nodeShowRelateSupplier;
		}
		public Integer getDataType() {
			return dataType;
		}
		public void setDataType(Integer dataType) {
			this.dataType = dataType;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

	public List<SupplierTreePOJO<T>> getChildren() {
			return children;
		}
		public void setChildren(List<SupplierTreePOJO<T>> children) {
			this.children = children;
		}
		@Override
		public String toString() {
			return "SupplierTreePOJO [id=" + id + ", parentId=" + parentId + ", label=" + label + ", open=" + open
					+ ", checked=" + checked + ", nodeSelectNotAll=" + nodeSelectNotAll + ", visible=" + visible
					+ ", leaf=" + leaf + ", searched=" + searched + ", nodeShowDelete=" + nodeShowDelete
					+ ", nodeShowRemove=" + nodeShowRemove + ", supplierShow=" + supplierShow
					+ ", nodeShowRelateSupplier=" + nodeShowRelateSupplier + ",, children=" + children
					+ "]";
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (checked ? 1231 : 1237);
			result = prime * result + ((children == null) ? 0 : children.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			result = prime * result + (leaf ? 1231 : 1237);
			result = prime * result + (nodeSelectNotAll ? 1231 : 1237);
			result = prime * result + (nodeShowDelete ? 1231 : 1237);
			result = prime * result + (nodeShowRelateSupplier ? 1231 : 1237);
			result = prime * result + (nodeShowRemove ? 1231 : 1237);
			result = prime * result + (open ? 1231 : 1237);
			result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
			result = prime * result + (searched ? 1231 : 1237);
			result = prime * result + (supplierShow ? 1231 : 1237);
			result = prime * result + (visible ? 1231 : 1237);
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SupplierTreePOJO other = (SupplierTreePOJO) obj;
			if (checked != other.checked)
				return false;
			if (children == null) {
				if (other.children != null)
					return false;
			} else if (!children.equals(other.children))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			if (leaf != other.leaf)
				return false;
			if (nodeSelectNotAll != other.nodeSelectNotAll)
				return false;
			if (nodeShowDelete != other.nodeShowDelete)
				return false;
			if (nodeShowRelateSupplier != other.nodeShowRelateSupplier)
				return false;
			if (nodeShowRemove != other.nodeShowRemove)
				return false;
			if (open != other.open)
				return false;
			if (parentId == null) {
				if (other.parentId != null)
					return false;
			} else if (!parentId.equals(other.parentId))
				return false;
			if (searched != other.searched)
				return false;
			if (supplierShow != other.supplierShow)
				return false;
			if (visible != other.visible)
				return false;
			return true;
		}
		
		
}
