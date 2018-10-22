package com.rograndec.feijiayun.chain.business.system.set.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConnectTreePOJO<T> implements Serializable{

    private static final long serialVersionUID = -9059185339771252135L;
    //父对象
    private Long value;
    private Long parentId;
    private String label;
    private boolean open;
    private boolean checked;
    private boolean nodeSelectNotAll;
    private boolean visible = true;
    private boolean leaf;//叶子节点
    private boolean searched;
    private boolean nodeShowDelete;//是否显示删除
    private boolean nodeShowRemove;//是否显示移出
    private boolean nodeShowRelateBranch;//是否关联
    private boolean supplierShow = true;//是否修改

    private T data;

    //子对象


    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    private List<ConnectTreePOJO<T>> children = new ArrayList<ConnectTreePOJO<T>>();

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public boolean getSearched() {
        return searched;
    }

    public List<ConnectTreePOJO<T>> getChildren() {
        return children;
    }

    public void setChildren(List<ConnectTreePOJO<T>> children) {
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



}
