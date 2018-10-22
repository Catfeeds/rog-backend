package com.rograndec.feijiayun.chain.business.goods.info.entity;

import java.io.Serializable;

public class GoodsModifyRecordWithBLOBs extends GoodsModifyRecord implements Serializable {
    /**
     * 修改字段英文名称
     */
    private String columnEnName;

    /**
     * 修改字段中文名称（修改项目）
     */
    private String columnChName;

    /**
     * 原内容
     */
    private String oldContent;

    /**
     * 新内容
     */
    private String newContent;

    /**
     * saas_goods_modify_record
     */

    /**
     * 修改字段英文名称
     * @return column_en_name 修改字段英文名称
     */
    public String getColumnEnName() {
        return columnEnName;
    }

    /**
     * 修改字段英文名称
     * @param columnEnName 修改字段英文名称
     */
    public void setColumnEnName(String columnEnName) {
        this.columnEnName = columnEnName == null ? null : columnEnName.trim();
    }

    /**
     * 修改字段中文名称（修改项目）
     * @return column_ch_name 修改字段中文名称（修改项目）
     */
    public String getColumnChName() {
        return columnChName;
    }

    /**
     * 修改字段中文名称（修改项目）
     * @param columnChName 修改字段中文名称（修改项目）
     */
    public void setColumnChName(String columnChName) {
        this.columnChName = columnChName == null ? null : columnChName.trim();
    }

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
        sb.append(", columnEnName=").append(columnEnName);
        sb.append(", columnChName=").append(columnChName);
        sb.append(", oldContent=").append(oldContent);
        sb.append(", newContent=").append(newContent);
        sb.append("]");
        return sb.toString();
    }
}