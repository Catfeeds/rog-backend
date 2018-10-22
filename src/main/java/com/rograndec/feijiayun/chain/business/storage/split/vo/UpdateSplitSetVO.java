package com.rograndec.feijiayun.chain.business.storage.split.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/29 <br/>
 * 描述：
 */
public class UpdateSplitSetVO implements Serializable {
    private static final long serialVersionUID = 2018497984032849712L;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 默认货位ID
     */
    private Long defaultShelfId;

    /**
     * 默认货位名称
     */
    private String defaultShelfName;

    /**
     * 零售单价
     */
    private BigDecimal retailPrice;

    /**
     * 会员单价
     */
    private BigDecimal memberPrice;

    /**
     * 最后修改人ID
     */
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    private String modifierName;



    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;


    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getDefaultShelfId() {
        return defaultShelfId;
    }

    public void setDefaultShelfId(Long defaultShelfId) {
        this.defaultShelfId = defaultShelfId;
    }

    public String getDefaultShelfName() {
        return defaultShelfName;
    }

    public void setDefaultShelfName(String defaultShelfName) {
        this.defaultShelfName = defaultShelfName;
    }

    public BigDecimal getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }

    public BigDecimal getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(BigDecimal memberPrice) {
        this.memberPrice = memberPrice;
    }

    public Long getModifierId() {
        return modifierId;
    }

    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifierCode() {
        return modifierCode;
    }

    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }
}
