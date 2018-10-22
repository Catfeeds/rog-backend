package com.rograndec.feijiayun.chain.common.vo;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 库存锁定更新参数对象
 *
 * @author lizhongyi
 *
 * 2017-10-26 17:12:11
 */
public class LockQtyArgVO {

    private Long enterpriseId;// 企业ID
    private Long parentId;// 上级企业ID
    private Long baseOrderId;
    private Integer baseOrderType;// 基础单据类型
    private String baseOrderCode;// 基础单据编码
    private Date baseOrderDate;// 基础单据日期
    private Long goodsId;// 商品ID
    private Long lotId;// 批号ID
    private Long shelfId;// 货位ID
    private BigDecimal quantity;// 锁定（释放）数量
    private UserVO user;

    public LockQtyArgVO() {}

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

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public Integer getBaseOrderType() {
        return baseOrderType;
    }

    public void setBaseOrderType(Integer baseOrderType) {
        this.baseOrderType = baseOrderType;
    }

    public String getBaseOrderCode() {
        return baseOrderCode;
    }

    public void setBaseOrderCode(String baseOrderCode) {
        this.baseOrderCode = baseOrderCode;
    }

    public Date getBaseOrderDate() {
        return baseOrderDate;
    }

    public void setBaseOrderDate(Date baseOrderDate) {
        this.baseOrderDate = baseOrderDate;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getLotId() {
        return lotId;
    }

    public void setLotId(Long lotId) {
        this.lotId = lotId;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }
}