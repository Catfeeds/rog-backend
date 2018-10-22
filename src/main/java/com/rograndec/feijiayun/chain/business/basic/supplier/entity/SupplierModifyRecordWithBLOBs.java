package com.rograndec.feijiayun.chain.business.basic.supplier.entity;

import java.io.Serializable;

public class SupplierModifyRecordWithBLOBs extends SupplierModifyRecord implements Serializable{

	/**
     * 原内容
     */
    private String oldContent;

    /**
     * 新内容
     */
    private String newContent;

    /**
     * saas_user_modify_record
     */
    private static final long serialVersionUID = 1L;

    /**
     * 原内容
     * @return old_content 原内容
     */
    public String getOldContent() {
        return oldContent;
    }

    /**
     * 原内容
     * @param oldContent 原内容
     */
    public void setOldContent(String oldContent) {
        this.oldContent = oldContent == null ? null : oldContent.trim();
    }

    /**
     * 新内容
     * @return new_content 新内容
     */
    public String getNewContent() {
        return newContent;
    }

    /**
     * 新内容
     * @param newContent 新内容
     */
    public void setNewContent(String newContent) {
        this.newContent = newContent == null ? null : newContent.trim();
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", oldContent=").append(oldContent);
        sb.append(", newContent=").append(newContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
