package com.rograndec.feijiayun.chain.business.finance.commission.entity;

import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleDetail;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
 
/**
 * 
 * saas_sale_commission_relation
 * 
 * 
 * @author lizhongyi
 * 
 * 2018-01-05
 */
public class SaleCommissionRelation implements Serializable {
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
     * 提成单ID
     */
    @ApiModelProperty(value = "提成单ID")
    private Long commissionId;

    @ApiModelProperty(value = "提成单明细ID")
    private Long commissionDtlId;

    /**
     * 销售／销退单ID
     */
    @ApiModelProperty(value = "销售／销退单ID")
    private Long saleId;

    @ApiModelProperty(value = "销售／销退明细单ID")
    private Long saleDtlId;

    /**
     * 销售类型（0-销售；1-销退）
     */
    @ApiModelProperty(value = "销售类型（0-销售；1-销退）")
    private Integer saleType;

    public static List<SaleCommissionRelation> generateSaleCommissionRelations(UserVO userVO,SaleCommission saleCommission ,List<Sale> sales, List<SaleDetail> saleDetails, SaleCommissionDetail saleCommissionDetail){

        List<SaleCommissionRelation> saleCommissionRelations = new ArrayList<>();

        List<Long> saleDtlIds = saleCommissionDetail.getSaleDtlIds();

        for(Long dtlId : saleDtlIds){

            SaleCommissionRelation saleCommissionRelation = new SaleCommissionRelation();
            /**
             * 企业ID
             */
            saleCommissionRelation.setEnterpriseId(userVO.getEnterpriseId());

            /**
             * 上级企业ID
             */
            saleCommissionRelation.setParentId(userVO.getParentId());

            /**
             * 提成单ID
             */
            saleCommissionRelation.setCommissionId(saleCommission.getId());

            saleCommissionRelation.setCommissionDtlId(saleCommissionDetail.getId());


            for(SaleDetail saleDetail : saleDetails){
                if(saleDetail.getId().equals(dtlId)){

                    saleCommissionRelation.setSaleDtlId(saleDetail.getId());

                    saleCommissionRelation.setSaleId(saleDetail.getSaleId());

                    /**
                     * 销售类型（0-销售；1-销退）
                     */
                    Sale sl = sales.stream().filter(sale -> sale.getId().equals(saleDetail.getSaleId())).findFirst().orElse(null);
                    saleCommissionRelation.setSaleType(sl.getSaleType());

                }

            }

            saleCommissionRelations.add(saleCommissionRelation);

        }

        return saleCommissionRelations;

    }

    /**
     * saas_sale_commission_relation
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
     * 提成单ID
     * @return commission_id 提成单ID
     */
    public Long getCommissionId() {
        return commissionId;
    }

    /**
     * 提成单ID
     * @param commissionId 提成单ID
     */
    public void setCommissionId(Long commissionId) {
        this.commissionId = commissionId;
    }

    /**
     * 销售／销退单ID
     * @return sale_id 销售／销退单ID
     */
    public Long getSaleId() {
        return saleId;
    }

    /**
     * 销售／销退单ID
     * @param saleId 销售／销退单ID
     */
    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    /**
     * 销售类型（0-销售；1-销退）
     * @return sale_type 销售类型（0-销售；1-销退）
     */
    public Integer getSaleType() {
        return saleType;
    }

    /**
     * 销售类型（0-销售；1-销退）
     * @param saleType 销售类型（0-销售；1-销退）
     */
    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
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
        sb.append(", saleId=").append(saleId);
        sb.append(", saleType=").append(saleType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Long getCommissionDtlId() {
        return commissionDtlId;
    }

    public void setCommissionDtlId(Long commissionDtlId) {
        this.commissionDtlId = commissionDtlId;
    }

    public Long getSaleDtlId() {
        return saleDtlId;
    }

    public void setSaleDtlId(Long saleDtlId) {
        this.saleDtlId = saleDtlId;
    }
}