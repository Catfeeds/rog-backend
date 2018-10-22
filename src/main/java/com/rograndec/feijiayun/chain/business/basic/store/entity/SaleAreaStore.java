package com.rograndec.feijiayun.chain.business.basic.store.entity;

import java.io.Serializable;

public class SaleAreaStore implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 销售片区ID
     */
    private Long saleAreaId;

    /**
     * 企业（门店）ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * saas_sale_area_store
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
     * 销售片区ID
     * @return sale_area_id 销售片区ID
     */
    public Long getSaleAreaId() {
        return saleAreaId;
    }

    /**
     * 销售片区ID
     * @param saleAreaId 销售片区ID
     */
    public void setSaleAreaId(Long saleAreaId) {
        this.saleAreaId = saleAreaId;
    }

    /**
     * 企业（门店）ID
     * @return enterprise_id 企业（门店）ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业（门店）ID
     * @param enterpriseId 企业（门店）ID
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
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", saleAreaId=").append(saleAreaId);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}