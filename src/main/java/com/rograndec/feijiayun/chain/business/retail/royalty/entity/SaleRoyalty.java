package com.rograndec.feijiayun.chain.business.retail.royalty.entity;

import com.rograndec.feijiayun.chain.business.retail.royalty.vo.transfer.SaveOrUpdateVO;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * saas_sale_royalty
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-09-23
 */
public class SaleRoyalty implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 提成管理单类型（6243）
     */
    @ApiModelProperty(value = "提成管理单类型（6243）")
    private Integer orderType;

    /**
     * 提成日期
     */
    @ApiModelProperty(value = "提成日期")
    private Date royaltyDate;

    /**
     * 提成管理单号
     */
    @ApiModelProperty(value = "提成管理单号")
    private String code;

    /**
     * 提成人员ID
     */
    @ApiModelProperty(value = "提成人员ID")
    private Long royaltyManId;

    /**
     * 提成人员编码
     */
    @ApiModelProperty(value = "提成人员编码")
    private String royaltyManCode;

    /**
     * 提成人员名称
     */
    @ApiModelProperty(value = "提成人员名称")
    private String royaltyManName;

    /**
     * 销售日期从
     */
    @ApiModelProperty(value = "销售日期从")
    private Date saleDateFrom;

    /**
     * 销售日期至
     */
    @ApiModelProperty(value = "销售日期至")
    private Date saleDateTo;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    public static List<Long> getIds(List<SaleRoyalty> saleRoyalties){

        List<Long> ids = new ArrayList<>();

        for(SaleRoyalty saleRoyalty : saleRoyalties){
            ids.add(saleRoyalty.getId());
        }
        return ids;
    }

    public static List<Long> getEnterpriseIds(List<SaleRoyalty> saleRoyalties){

        List<Long> ids = new ArrayList<>();

        for(SaleRoyalty saleRoyalty : saleRoyalties){
            ids.add(saleRoyalty.getEnterpriseId());
        }
        return ids;
    }


    public static List<SaleRoyalty> getSaleRoyaltys(List<SaveOrUpdateVO> saveOrUpdateVOS, Boolean isAdd) throws Exception {

        List<SaleRoyalty> saleRoyalties = new ArrayList<>();

        for(SaveOrUpdateVO vo : saveOrUpdateVOS){
            saleRoyalties.add(getSaleRoyalty(vo,isAdd));
        }

        return saleRoyalties;
    }

    public static SaleRoyalty getSaleRoyalty(
            SaveOrUpdateVO saveOrUpdateVO
            ,Boolean isAdd
        ) throws Exception {

        SaleRoyalty saleRoyalty = new SaleRoyalty();
        saleRoyalty.setId(saveOrUpdateVO.getSaveOrUpdateSaleRoyaltyVO().getId());
        saleRoyalty.setEnterpriseId(saveOrUpdateVO.getEnterprise().getId());
        saleRoyalty.setParentId(saveOrUpdateVO.getEnterprise().getParentId());
      /*  if(saveOrUpdateVO.getUserVO().getChainType().equals(ChainType.Headquarters.getCode())){
            saleRoyalty.setParentId(0L);
            *//**
             * 如果是总部,则取前端用户选择的
             *//*
            saleRoyalty.setEnterpriseId(saveOrUpdateVO.getSaveOrUpdateSaleRoyaltyVO().getEnterpriseId());
        }else {
            saleRoyalty.setParentId(saveOrUpdateVO.getUserVO().getParentId());
        }*/
        /**
         * 提成管理单类型（6243）
         */
        saleRoyalty.setOrderType(OrderRule.COMMISSION.getType());
        /**
         * 提成日期
         */
        saleRoyalty.setRoyaltyDate(saveOrUpdateVO.getSaveOrUpdateSaleRoyaltyVO().getRoyaltyDate());

        /**
         * 提成单号
         */
        saleRoyalty.setCode(saveOrUpdateVO.getCode());

        /**
         * 提成人员ID
         */
        saleRoyalty.setRoyaltyManId(saveOrUpdateVO.getRoyaltyMan().getId());
        saleRoyalty.setRoyaltyManCode(saveOrUpdateVO.getRoyaltyMan().getCode());
        saleRoyalty.setRoyaltyManName(saveOrUpdateVO.getRoyaltyMan().getName());

        /**
         * 销售日期范围
         */
        saleRoyalty.setSaleDateFrom(saveOrUpdateVO.getSaveOrUpdateSaleRoyaltyVO().getSaleDateFrom());
        saleRoyalty.setSaleDateTo(saveOrUpdateVO.getSaveOrUpdateSaleRoyaltyVO().getSaleDateTo());

        /**
         * 状态（0-禁用；1-启用）
         */
        saleRoyalty.setStatus(EnableStatus.ENABLE.getStatus());

        UserEnterpriseUtils.setUserCreateOrModify(saleRoyalty,saveOrUpdateVO.getUserVO(),isAdd);

        return saleRoyalty;
    }


    /**
     * saas_sale_royalty
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
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
     * 提成管理单类型（6243）
     * @return order_type 提成管理单类型（6243）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 提成管理单类型（6243）
     * @param orderType 提成管理单类型（6243）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 提成日期
     * @return royalty_date 提成日期
     */
    public Date getRoyaltyDate() {
        return royaltyDate;
    }

    /**
     * 提成日期
     * @param royaltyDate 提成日期
     */
    public void setRoyaltyDate(Date royaltyDate) {
        this.royaltyDate = royaltyDate;
    }

    /**
     * 提成管理单号
     * @return code 提成管理单号
     */
    public String getCode() {
        return code;
    }

    /**
     * 提成管理单号
     * @param code 提成管理单号
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 提成人员ID
     * @return royalty_man_id 提成人员ID
     */
    public Long getRoyaltyManId() {
        return royaltyManId;
    }

    /**
     * 提成人员ID
     * @param royaltyManId 提成人员ID
     */
    public void setRoyaltyManId(Long royaltyManId) {
        this.royaltyManId = royaltyManId;
    }

    /**
     * 提成人员编码
     * @return royalty_man_code 提成人员编码
     */
    public String getRoyaltyManCode() {
        return royaltyManCode;
    }

    /**
     * 提成人员编码
     * @param royaltyManCode 提成人员编码
     */
    public void setRoyaltyManCode(String royaltyManCode) {
        this.royaltyManCode = royaltyManCode == null ? null : royaltyManCode.trim();
    }

    /**
     * 提成人员名称
     * @return royalty_man_name 提成人员名称
     */
    public String getRoyaltyManName() {
        return royaltyManName;
    }

    /**
     * 提成人员名称
     * @param royaltyManName 提成人员名称
     */
    public void setRoyaltyManName(String royaltyManName) {
        this.royaltyManName = royaltyManName == null ? null : royaltyManName.trim();
    }

    /**
     * 销售日期从
     * @return sale_date_from 销售日期从
     */
    public Date getSaleDateFrom() {
        return saleDateFrom;
    }

    /**
     * 销售日期从
     * @param saleDateFrom 销售日期从
     */
    public void setSaleDateFrom(Date saleDateFrom) {
        this.saleDateFrom = saleDateFrom;
    }

    /**
     * 销售日期至
     * @return sale_date_to 销售日期至
     */
    public Date getSaleDateTo() {
        return saleDateTo;
    }

    /**
     * 销售日期至
     * @param saleDateTo 销售日期至
     */
    public void setSaleDateTo(Date saleDateTo) {
        this.saleDateTo = saleDateTo;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @return status 状态（0-禁用；1-启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @param status 状态（0-禁用；1-启用）
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
        sb.append(", orderType=").append(orderType);
        sb.append(", royaltyDate=").append(royaltyDate);
        sb.append(", code=").append(code);
        sb.append(", royaltyManId=").append(royaltyManId);
        sb.append(", royaltyManCode=").append(royaltyManCode);
        sb.append(", royaltyManName=").append(royaltyManName);
        sb.append(", saleDateFrom=").append(saleDateFrom);
        sb.append(", saleDateTo=").append(saleDateTo);
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