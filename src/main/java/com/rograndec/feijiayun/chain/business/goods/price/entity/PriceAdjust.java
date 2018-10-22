package com.rograndec.feijiayun.chain.business.goods.price.entity;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.price.vo.AddOrUpdatePriceAdjustVO;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;

import java.io.Serializable;
import java.util.Date;

public class PriceAdjust implements Serializable {
    /**
     * 主键id
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
     * 调整单号
     */
    private String orderCode;

    /**
     * 调整日期
     */
    private Date adjustDate;

    /**
     * 调整人员ID
     */
    private Long adjustManId;

    /**
     * 调整人员编码
     */
    private String adjustManCode;

    /**
     * 调整人员名称
     */
    private String adjustManName;

    /**
     * 价格清单ID
     */
    private Long priceOrderId;

    /**
     * 调整原因
     */
    private String adjustReason;

    /**
     * 状态（0-禁用；1-启用
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人ID
     */
    private Long createrId;

    /**
     * 创建人编码 
     */
    private String createrCode;

    /**
     * 创建人名称 
     */
    private String createrName;

    /**
     * 创建时间
     */
    private Date createTime;

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
     * 更新时间
     */
    private Date updateTime;

    /**
     * saas_price_adjust
     */
    private static final long serialVersionUID = 1L;

    public static PriceAdjust getPriceAdjust4VO(UserVO userVO, String orderCode, User user,AddOrUpdatePriceAdjustVO addOrUpdatePriceAdjustVO
        ,boolean isAdd
    ) throws Exception {

        PriceAdjust priceAdjust = new PriceAdjust();

        priceAdjust.setId(addOrUpdatePriceAdjustVO.getId());
        priceAdjust.setEnterpriseId(userVO.getEnterpriseId());
        if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){
            priceAdjust.setParentId(0L);
        }else {
            priceAdjust.setParentId(userVO.getParentId());
        }
        priceAdjust.setOrderCode(orderCode);
        priceAdjust.setAdjustDate(addOrUpdatePriceAdjustVO.getAdjustDate());
        priceAdjust.setAdjustManId(userVO.getUserId());


        priceAdjust.setAdjustManCode(user.getCode());
        priceAdjust.setAdjustManName(user.getName());
        priceAdjust.setPriceOrderId(addOrUpdatePriceAdjustVO.getPriceOrderId());
        priceAdjust.setAdjustReason(addOrUpdatePriceAdjustVO.getAdjustReason());
        priceAdjust.setStatus(PurchaseStatus.PENDING_AUDIT.getStatus());

        UserEnterpriseUtils.setUserCreateOrModify(priceAdjust,userVO,isAdd);

        return priceAdjust;
    }

    /**
     * 主键id
     * @return id 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键id
     * @param id 主键id
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
     * 调整单号
     * @return order_code 调整单号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 调整单号
     * @param orderCode 调整单号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode == null ? null : orderCode.trim();
    }

    /**
     * 调整日期
     * @return adjust_date 调整日期
     */
    public Date getAdjustDate() {
        return adjustDate;
    }

    /**
     * 调整日期
     * @param adjustDate 调整日期
     */
    public void setAdjustDate(Date adjustDate) {
        this.adjustDate = adjustDate;
    }

    /**
     * 调整人员ID
     * @return adjust_man_id 调整人员ID
     */
    public Long getAdjustManId() {
        return adjustManId;
    }

    /**
     * 调整人员ID
     * @param adjustManId 调整人员ID
     */
    public void setAdjustManId(Long adjustManId) {
        this.adjustManId = adjustManId;
    }

    /**
     * 调整人员编码
     * @return adjust_man_code 调整人员编码
     */
    public String getAdjustManCode() {
        return adjustManCode;
    }

    /**
     * 调整人员编码
     * @param adjustManCode 调整人员编码
     */
    public void setAdjustManCode(String adjustManCode) {
        this.adjustManCode = adjustManCode == null ? null : adjustManCode.trim();
    }

    /**
     * 调整人员名称
     * @return adjust_man_name 调整人员名称
     */
    public String getAdjustManName() {
        return adjustManName;
    }

    /**
     * 调整人员名称
     * @param adjustManName 调整人员名称
     */
    public void setAdjustManName(String adjustManName) {
        this.adjustManName = adjustManName == null ? null : adjustManName.trim();
    }

    /**
     * 价格清单ID
     * @return price_order_id 价格清单ID
     */
    public Long getPriceOrderId() {
        return priceOrderId;
    }

    /**
     * 价格清单ID
     * @param priceOrderId 价格清单ID
     */
    public void setPriceOrderId(Long priceOrderId) {
        this.priceOrderId = priceOrderId;
    }

    /**
     * 调整原因
     * @return adjust_reason 调整原因
     */
    public String getAdjustReason() {
        return adjustReason;
    }

    /**
     * 调整原因
     * @param adjustReason 调整原因
     */
    public void setAdjustReason(String adjustReason) {
        this.adjustReason = adjustReason == null ? null : adjustReason.trim();
    }

    /**
     * 状态（0-禁用；1-启用
     * @return status 状态（0-禁用；1-启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-禁用；1-启用
     * @param status 状态（0-禁用；1-启用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码 
     * @return creater_code 创建人编码 
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码 
     * @param createrCode 创建人编码 
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称 
     * @return creater_name 创建人名称 
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称 
     * @param createrName 创建人名称 
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID 
     * @return modifier_id 最后修改人ID 
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID 
     * @param modifierId 最后修改人ID 
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称 
     * @return modifier_name 最后修改人名称 
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称 
     * @param modifierName 最后修改人名称 
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", orderCode=").append(orderCode);
        sb.append(", adjustDate=").append(adjustDate);
        sb.append(", adjustManId=").append(adjustManId);
        sb.append(", adjustManCode=").append(adjustManCode);
        sb.append(", adjustManName=").append(adjustManName);
        sb.append(", priceOrderId=").append(priceOrderId);
        sb.append(", adjustReason=").append(adjustReason);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}