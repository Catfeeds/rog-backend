package com.rograndec.feijiayun.chain.business.system.set.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ConnectTree<T> implements Serializable {
    private Long value;
    private Long parentId;
    private String label;
    private boolean open;
    private boolean checked;
    private boolean nodeSelectNotAll;
    private boolean visible = true;
    private boolean leaf;
    private boolean searched;
    private boolean nodeShowDelete;
    private boolean nodeShowUpdate;
    private boolean isPosition = false;
    //父对象存放部门信息
    private T data;
    //子对象
    private List<ConnectTree> children = new ArrayList<ConnectTree>();

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
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

    public List<ConnectTree> getChildren() {
        return children;
    }

    public void setChildren(List<ConnectTree> children) {
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


    @Override
    public String toString() {
        return "ConnectTree{" +
                "value=" + value +
                ", parentId=" + parentId +
                ", label='" + label + '\'' +
                ", open=" + open +
                ", checked=" + checked +
                ", nodeSelectNotAll=" + nodeSelectNotAll +
                ", visible=" + visible +
                ", leaf=" + leaf +
                ", searched=" + searched +
                ", nodeShowDelete=" + nodeShowDelete +
                ", nodeShowUpdate=" + nodeShowUpdate +
                ", isPosition=" + isPosition +
                ", data=" + data +
                ", children=" + children +
                '}';
    }
}
