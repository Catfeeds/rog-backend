package com.rograndec.feijiayun.chain.business.goods.pharmacy.entity;

import java.io.Serializable;

public class IncompatibilityGoodsOne implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 配伍禁忌ID
     */
    private Long incompatibilityId;

    /**
     * 药品ID集合
     */
    private String goodsIds;

    /**
     * 药品名称集合（形如：编码A-药品A,编码B-药品B…）
     */
    private String goodsNames;

    /**
     * 药品编码集合
     */
    private String goodsCodes;

    /**
     * saas_incompatibility_goods_one
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 配伍禁忌ID
     * @return incompatibility_id 配伍禁忌ID
     */
    public Long getIncompatibilityId() {
        return incompatibilityId;
    }

    /**
     * 配伍禁忌ID
     * @param incompatibilityId 配伍禁忌ID
     */
    public void setIncompatibilityId(Long incompatibilityId) {
        this.incompatibilityId = incompatibilityId;
    }

    /**
     * 药品ID集合
     * @return goods_ids 药品ID集合
     */
    public String getGoodsIds() {
        return goodsIds;
    }

    /**
     * 药品ID集合
     * @param goodsIds 药品ID集合
     */
    public void setGoodsIds(String goodsIds) {
        this.goodsIds = goodsIds == null ? null : goodsIds.trim();
    }

    /**
     * 药品名称集合（形如：编码A-药品A,编码B-药品B…）
     * @return goods_names 药品名称集合（形如：编码A-药品A,编码B-药品B…）
     */
    public String getGoodsNames() {
        return goodsNames;
    }

    /**
     * 药品名称集合（形如：编码A-药品A,编码B-药品B…）
     * @param goodsNames 药品名称集合（形如：编码A-药品A,编码B-药品B…）
     */
    public void setGoodsNames(String goodsNames) {
        this.goodsNames = goodsNames == null ? null : goodsNames.trim();
    }


    public String getGoodsCodes() {
        return goodsCodes;
    }

    public void setGoodsCodes(String goodsCodes) {
        this.goodsCodes = goodsCodes;
    }

    @Override
    public String toString() {
        return "IncompatibilityGoodsOne[" +
                "id=" + id +
                ", enterpriseId=" + enterpriseId +
                ", parentId=" + parentId +
                ", incompatibilityId=" + incompatibilityId +
                ", goodsIds='" + goodsIds + '\'' +
                ", goodsNames='" + goodsNames + '\'' +
                ", goodsCodes='" + goodsCodes + '\'' +
                ']';
    }
}