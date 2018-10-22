package com.rograndec.feijiayun.chain.business.goods.price.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class PriceModifyRecordWithBLOBs extends PriceModifyRecord implements Serializable {
    /**
     * 原内容
     */
    @ApiModelProperty(value = "原内容")
    private String oldContent;

    /**
     * 新内容
     */
    @ApiModelProperty(value = "新内容")
    private String newContent;

    /**
     * saas_price_modify_record
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