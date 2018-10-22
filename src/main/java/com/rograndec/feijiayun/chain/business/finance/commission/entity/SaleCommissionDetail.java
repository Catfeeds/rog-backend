package com.rograndec.feijiayun.chain.business.finance.commission.entity;

import com.rograndec.feijiayun.chain.business.finance.commission.vo.NoSaleCommissionDetailResponseVO;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.common.constant.status.SaleCommissionStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
/**
 * 
 * saas_sale_commission_detail
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class SaleCommissionDetail implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
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
     * 总单ID
     */
    @ApiModelProperty(value = "总单ID")
    private Long commissionId;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 商品编码
     */
    @ApiModelProperty(value = "商品编码")
    private String goodsCode;

    /**
     * 条形码
     */
    @ApiModelProperty(value = "条形码")
    private String barcode;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    /**
     * 商品通用名称
     */
    @ApiModelProperty(value = "商品通用名称")
    private String goodsGenericName;

    /**
     * 剂型ID
     */
    @ApiModelProperty(value = "剂型ID")
    private Long dosageId;

    /**
     * 剂型名称
     */
    @ApiModelProperty(value = "剂型名称")
    private String dosageName;

    /**
     * 单位ID
     */
    @ApiModelProperty(value = "单位ID")
    private Long unitId;

    /**
     * 单位名称
     */
    @ApiModelProperty(value = "单位名称")
    private String unitName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    private String goodsSpecification;

    /**
     * 生产厂商ID
     */
    @ApiModelProperty(value = "生产厂商ID")
    private Long manufacturerId;

    /**
     * 生产厂商
     */
    @ApiModelProperty(value = "生产厂商")
    private String manufacturer;

    /**
     * 商品产地
     */
    @ApiModelProperty(value = "商品产地")
    private String goodsPlace;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    private String approvalNumber;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal quantity;

    /**
     * 销售总额
     */
    @ApiModelProperty(value = "销售总额")
    private BigDecimal amount;

    /**
     * 成本总额
     */
    @ApiModelProperty(value = "成本总额")
    private BigDecimal costAmount;

    /**
     * 利润总额
     */
    @ApiModelProperty(value = "利润总额")
    private BigDecimal profit;

    /**
     * 利润率
     */
    @ApiModelProperty(value = "利润率")
    private BigDecimal profitRate;

    /**
     * 提成策略
     */
    @ApiModelProperty(value = "提成策略")
    private String commissionStrategy;

    /**
     * 提成策略ID
     */
    @ApiModelProperty(value = "提成策略ID")
    private Long commissionStrategyId;

    /**
     * 提成金额
     */
    @ApiModelProperty(value = "提成金额")
    private BigDecimal commissionAmount;

    /**
     * 行号
     */
    @ApiModelProperty(value = "行号")
    private Integer lineNum;

    /**
     * 状态（1-已完成；2-已冲销）
     */
    @ApiModelProperty(value = "状态（1-已完成；2-已冲销）")
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

    /**
     * 并非数据库字段,保存的时候需要
     */
    private List<Long> saleDtlIds;

    public static List<SaleCommissionDetail> generateSaleCommissionDetails(UserVO userVO,
                                                                           SaleCommission saleCommission,
                                                                           List<Goods> goods,
                                                                           List<NoSaleCommissionDetailResponseVO> commissionDetailVOS,
                                                                           boolean isAdd
    ) throws Exception {


        List<SaleCommissionDetail> saleCommissionDetails = new ArrayList<>();

        for(NoSaleCommissionDetailResponseVO cvo : commissionDetailVOS){

            SaleCommissionDetail saleCommissionDetail = new SaleCommissionDetail();

            /**
             * 企业ID
             */
            saleCommissionDetail.setEnterpriseId(userVO.getEnterpriseId());

            /**
             * 上级企业ID
             */
            saleCommissionDetail.setParentId(userVO.getParentId());

            /**
             * 总单ID
             */
            saleCommissionDetail.setCommissionId(saleCommission.getId());

            /**
             * 商品ID
             */
            saleCommissionDetail.setGoodsId(cvo.getGoodsId());



            for(Goods g : goods){
                if(g.getId().equals(cvo.getGoodsId())){

                    /**
                     * 商品编码
                     */
                    saleCommissionDetail.setGoodsCode(g.getCode());

                    /**
                     * 条形码
                     */
                    saleCommissionDetail.setBarcode(g.getBarcode());

                    /**
                     * 商品名称
                     */
                    saleCommissionDetail.setGoodsName(g.getName());

                    /**
                     * 商品通用名称
                     */
                    saleCommissionDetail.setGoodsGenericName(g.getGenericName());

                    /**
                     * 剂型ID
                     */
                    saleCommissionDetail.setDosageId(g.getDosageId());

                    /**
                     * 剂型名称
                     */
                    saleCommissionDetail.setDosageName(g.getDosageName());

                    /**
                     * 单位ID
                     */
                    saleCommissionDetail.setUnitId(g.getUnitId());

                    /**
                     * 单位名称
                     */
                    saleCommissionDetail.setUnitName(g.getUnitName());

                    /**
                     * 商品规格
                     */
                    saleCommissionDetail.setGoodsSpecification(g.getSpecification());

                    /**
                     * 生产厂商ID
                     */
                    saleCommissionDetail.setManufacturerId(g.getManufacturerId());

                    /**
                     * 生产厂商
                     */
                    saleCommissionDetail.setManufacturer(g.getManufacturer());

                    /**
                     * 商品产地
                     */
                    saleCommissionDetail.setGoodsPlace(g.getPlace());

                    /**
                     * 批准文号
                     */
                    saleCommissionDetail.setApprovalNumber(g.getApprovalNumber());
                }

            }

            /**
             * 数量
             */
            saleCommissionDetail.setQuantity(cvo.getQuantity());

            /**
             * 销售总额
             */
            saleCommissionDetail.setAmount(cvo.getAmount());

            /**
             * 成本总额
             */
            saleCommissionDetail.setCostAmount(cvo.getCostAmount());

            /**
             * 利润总额
             */
            saleCommissionDetail.setProfit(cvo.getProfit());

            /**
             * 利润率
             */
            saleCommissionDetail.setProfitRate(cvo.getProfitRate());

            /**
             * 提成策略
             */
            saleCommissionDetail.setCommissionStrategy(cvo.getCommissionStrategy());

            /**
             * 提成策略ID
             */
            saleCommissionDetail.setCommissionStrategyId(cvo.getStrategyId());

            /**
             * 提成金额
             */
            saleCommissionDetail.setCommissionAmount(cvo.getCommissionAmount());

            /**
             * 行号
             */
            saleCommissionDetail.setLineNum(cvo.getLineNum());

            /**
             * 状态（1-已完成；2-已冲销）
             */
            saleCommissionDetail.setStatus(SaleCommissionStatus.OVER);

            /**
             * 备注
             */
            saleCommissionDetail.setRemark(cvo.getRemark());

            UserEnterpriseUtils.setUserCreateOrModify(saleCommissionDetail,userVO,isAdd);


            List<Long> saleDtlIds = cvo.getSaleDtlIds();

            saleCommissionDetail.setSaleDtlIds(saleDtlIds);


            saleCommissionDetails.add(saleCommissionDetail);
        }

        return saleCommissionDetails;

    }

    /**
     * saas_sale_commission_detail
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
     * 总单ID
     * @return commission_id 总单ID
     */
    public Long getCommissionId() {
        return commissionId;
    }

    /**
     * 总单ID
     * @param commissionId 总单ID
     */
    public void setCommissionId(Long commissionId) {
        this.commissionId = commissionId;
    }

    /**
     * 商品ID
     * @return goods_id 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 商品ID
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 商品编码
     * @return goods_code 商品编码
     */
    public String getGoodsCode() {
        return goodsCode;
    }

    /**
     * 商品编码
     * @param goodsCode 商品编码
     */
    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    /**
     * 条形码
     * @return barcode 条形码
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * 条形码
     * @param barcode 条形码
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    /**
     * 商品名称
     * @return goods_name 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 商品名称
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    /**
     * 商品通用名称
     * @return goods_generic_name 商品通用名称
     */
    public String getGoodsGenericName() {
        return goodsGenericName;
    }

    /**
     * 商品通用名称
     * @param goodsGenericName 商品通用名称
     */
    public void setGoodsGenericName(String goodsGenericName) {
        this.goodsGenericName = goodsGenericName == null ? null : goodsGenericName.trim();
    }

    /**
     * 剂型ID
     * @return dosage_id 剂型ID
     */
    public Long getDosageId() {
        return dosageId;
    }

    /**
     * 剂型ID
     * @param dosageId 剂型ID
     */
    public void setDosageId(Long dosageId) {
        this.dosageId = dosageId;
    }

    /**
     * 剂型名称
     * @return dosage_name 剂型名称
     */
    public String getDosageName() {
        return dosageName;
    }

    /**
     * 剂型名称
     * @param dosageName 剂型名称
     */
    public void setDosageName(String dosageName) {
        this.dosageName = dosageName == null ? null : dosageName.trim();
    }

    /**
     * 单位ID
     * @return unit_id 单位ID
     */
    public Long getUnitId() {
        return unitId;
    }

    /**
     * 单位ID
     * @param unitId 单位ID
     */
    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    /**
     * 单位名称
     * @return unit_name 单位名称
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * 单位名称
     * @param unitName 单位名称
     */
    public void setUnitName(String unitName) {
        this.unitName = unitName == null ? null : unitName.trim();
    }

    /**
     * 商品规格
     * @return goods_specification 商品规格
     */
    public String getGoodsSpecification() {
        return goodsSpecification;
    }

    /**
     * 商品规格
     * @param goodsSpecification 商品规格
     */
    public void setGoodsSpecification(String goodsSpecification) {
        this.goodsSpecification = goodsSpecification == null ? null : goodsSpecification.trim();
    }

    /**
     * 生产厂商ID
     * @return manufacturer_id 生产厂商ID
     */
    public Long getManufacturerId() {
        return manufacturerId;
    }

    /**
     * 生产厂商ID
     * @param manufacturerId 生产厂商ID
     */
    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    /**
     * 生产厂商
     * @return manufacturer 生产厂商
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * 生产厂商
     * @param manufacturer 生产厂商
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    /**
     * 商品产地
     * @return goods_place 商品产地
     */
    public String getGoodsPlace() {
        return goodsPlace;
    }

    /**
     * 商品产地
     * @param goodsPlace 商品产地
     */
    public void setGoodsPlace(String goodsPlace) {
        this.goodsPlace = goodsPlace == null ? null : goodsPlace.trim();
    }

    /**
     * 批准文号
     * @return approval_number 批准文号
     */
    public String getApprovalNumber() {
        return approvalNumber;
    }

    /**
     * 批准文号
     * @param approvalNumber 批准文号
     */
    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber == null ? null : approvalNumber.trim();
    }

    /**
     * 数量
     * @return quantity 数量
     */
    public BigDecimal getQuantity() {
        return quantity;
    }

    /**
     * 数量
     * @param quantity 数量
     */
    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    /**
     * 销售总额
     * @return amount 销售总额
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * 销售总额
     * @param amount 销售总额
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * 成本总额
     * @return cost_amount 成本总额
     */
    public BigDecimal getCostAmount() {
        return costAmount;
    }

    /**
     * 成本总额
     * @param costAmount 成本总额
     */
    public void setCostAmount(BigDecimal costAmount) {
        this.costAmount = costAmount;
    }

    /**
     * 利润总额
     * @return profit 利润总额
     */
    public BigDecimal getProfit() {
        return profit;
    }

    /**
     * 利润总额
     * @param profit 利润总额
     */
    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    /**
     * 利润率
     * @return profit_rate 利润率
     */
    public BigDecimal getProfitRate() {
        return profitRate;
    }

    /**
     * 利润率
     * @param profitRate 利润率
     */
    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    /**
     * 提成策略
     * @return commission_strategy 提成策略
     */
    public String getCommissionStrategy() {
        return commissionStrategy;
    }

    /**
     * 提成策略
     * @param commissionStrategy 提成策略
     */
    public void setCommissionStrategy(String commissionStrategy) {
        this.commissionStrategy = commissionStrategy == null ? null : commissionStrategy.trim();
    }

    /**
     * 提成金额
     * @return commission_amount 提成金额
     */
    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    /**
     * 提成金额
     * @param commissionAmount 提成金额
     */
    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    /**
     * 行号
     * @return line_num 行号
     */
    public Integer getLineNum() {
        return lineNum;
    }

    /**
     * 行号
     * @param lineNum 行号
     */
    public void setLineNum(Integer lineNum) {
        this.lineNum = lineNum;
    }

    /**
     * 状态（1-已完成；2-已冲销）
     * @return status 状态（1-已完成；2-已冲销）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（1-已完成；2-已冲销）
     * @param status 状态（1-已完成；2-已冲销）
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

    public List<Long> getSaleDtlIds() {
        return saleDtlIds;
    }

    public void setSaleDtlIds(List<Long> saleDtlIds) {
        this.saleDtlIds = saleDtlIds;
    }

    public Long getCommissionStrategyId() {
        return commissionStrategyId;
    }

    public void setCommissionStrategyId(Long commissionStrategyId) {
        this.commissionStrategyId = commissionStrategyId;
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
        sb.append(", commissionId=").append(commissionId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", barcode=").append(barcode);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsGenericName=").append(goodsGenericName);
        sb.append(", dosageId=").append(dosageId);
        sb.append(", dosageName=").append(dosageName);
        sb.append(", unitId=").append(unitId);
        sb.append(", unitName=").append(unitName);
        sb.append(", goodsSpecification=").append(goodsSpecification);
        sb.append(", manufacturerId=").append(manufacturerId);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", goodsPlace=").append(goodsPlace);
        sb.append(", approvalNumber=").append(approvalNumber);
        sb.append(", quantity=").append(quantity);
        sb.append(", amount=").append(amount);
        sb.append(", costAmount=").append(costAmount);
        sb.append(", profit=").append(profit);
        sb.append(", profitRate=").append(profitRate);
        sb.append(", commissionStrategyId=").append(commissionStrategyId);
        sb.append(", commissionStrategy=").append(commissionStrategy);
        sb.append(", commissionAmount=").append(commissionAmount);
        sb.append(", lineNum=").append(lineNum);
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