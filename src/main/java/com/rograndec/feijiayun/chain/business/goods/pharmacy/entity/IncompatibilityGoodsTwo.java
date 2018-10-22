package com.rograndec.feijiayun.chain.business.goods.pharmacy.entity;

import java.io.Serializable;

public class IncompatibilityGoodsTwo implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 配伍禁忌药子表ID
     */
    private Long oneId;

    /**
     * 配伍禁忌ID
     */
    private Long incompatibilityId;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 配伍药品ID集合
     */
    private String compatibilityGoodsIds;

    /**
     * 配伍药品名称集合（形如：编码A-药品A,编码B-药品B…）
     */
    private String compatibilityGoodsNames;

    /**
     * 配伍结果
     */
    private String compatibilityResult;

    /**
     * 配伍药品编码集合
     */
    private String compatibilityGoodsCodes;

    /**
     * saas_incompatibility_goods_two
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
     * 配伍禁忌药子表ID
     * @return one_id 配伍禁忌药子表ID
     */
    public Long getOneId() {
        return oneId;
    }

    /**
     * 配伍禁忌药子表ID
     * @param oneId 配伍禁忌药子表ID
     */
    public void setOneId(Long oneId) {
        this.oneId = oneId;
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
     * 配伍药品ID集合
     * @return compatibility_goods_ids 配伍药品ID集合
     */
    public String getCompatibilityGoodsIds() {
        return compatibilityGoodsIds;
    }

    /**
     * 配伍药品ID集合
     * @param compatibilityGoodsIds 配伍药品ID集合
     */
    public void setCompatibilityGoodsIds(String compatibilityGoodsIds) {
        this.compatibilityGoodsIds = compatibilityGoodsIds == null ? null : compatibilityGoodsIds.trim();
    }

    /**
     * 配伍药品名称集合（形如：编码A-药品A,编码B-药品B…）
     * @return compatibility_goods_names 配伍药品名称集合（形如：编码A-药品A,编码B-药品B…）
     */
    public String getCompatibilityGoodsNames() {
        return compatibilityGoodsNames;
    }

    /**
     * 配伍药品名称集合（形如：编码A-药品A,编码B-药品B…）
     * @param compatibilityGoodsNames 配伍药品名称集合（形如：编码A-药品A,编码B-药品B…）
     */
    public void setCompatibilityGoodsNames(String compatibilityGoodsNames) {
        this.compatibilityGoodsNames = compatibilityGoodsNames == null ? null : compatibilityGoodsNames.trim();
    }

    /**
     * 配伍结果
     * @return compatibility_result 配伍结果
     */
    public String getCompatibilityResult() {
        return compatibilityResult;
    }

    /**
     * 配伍结果
     * @param compatibilityResult 配伍结果
     */
    public void setCompatibilityResult(String compatibilityResult) {
        this.compatibilityResult = compatibilityResult == null ? null : compatibilityResult.trim();
    }

    public String getCompatibilityGoodsCodes() {
        return compatibilityGoodsCodes;
    }

    public void setCompatibilityGoodsCodes(String compatibilityGoodsCodes) {
        this.compatibilityGoodsCodes = compatibilityGoodsCodes;
    }

    @Override
    public String toString() {
        return "IncompatibilityGoodsTwo[" +
                "id=" + id +
                ", oneId=" + oneId +
                ", incompatibilityId=" + incompatibilityId +
                ", enterpriseId=" + enterpriseId +
                ", parentId=" + parentId +
                ", compatibilityGoodsIds='" + compatibilityGoodsIds + '\'' +
                ", compatibilityGoodsNames='" + compatibilityGoodsNames + '\'' +
                ", compatibilityResult='" + compatibilityResult + '\'' +
                ", compatibilityGoodsCodes='" + compatibilityGoodsCodes + '\'' +
                ']';
    }
}