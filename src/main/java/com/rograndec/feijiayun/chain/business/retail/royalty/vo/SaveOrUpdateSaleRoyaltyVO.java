package com.rograndec.feijiayun.chain.business.retail.royalty.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

/**
 * 
 * saas_sale_royalty
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-09-23
 */
@ApiModel
public class SaveOrUpdateSaleRoyaltyVO implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;

    /**
     * 提成日期
     */
    @ApiModelProperty(value = "提成日期" ,required = true)
    @NotNull(message = "提成日期不能为空")
    private Date royaltyDate;

    /**
     * 门店id
     */
    @ApiModelProperty(value = "门店id" ,required = true)
    @NotNull(message = "门店id不能为空")
    private Long enterpriseId;

    /**
     * 提成人员ID
     */
    @ApiModelProperty(value = "提成人员ID" ,required = true)
    @NotNull(message = "提成人员ID不能为空")
    private Long royaltyManId;

    /**
     * 销售日期从
     */
    @ApiModelProperty(value = "销售日期从" ,required = true)
    @NotNull(message = "销售日期不能为空")
    private Date saleDateFrom;

    /**
     * 销售日期至
     */
    @ApiModelProperty(value = "销售日期至" ,required = true)
    @NotNull(message = "销售日期不能为空")
    private Date saleDateTo;

    @ApiModelProperty(value = "提成人员,提成信息汇总集合")
    private List<SaveOrUpdateSaleRoyaltyTotalVO> saveOrUpdateSaleRoyaltyTotalVOS;

    public static Set<Long> getRoyaltyManIds(List<SaveOrUpdateSaleRoyaltyVO> saveOrUpdateSaleRoyaltyVOS){
        Set<Long> ids = new HashSet<>();
        for(SaveOrUpdateSaleRoyaltyVO vo : saveOrUpdateSaleRoyaltyVOS ){
            ids.add(vo.getRoyaltyManId());
        }
        return ids;
    }

    public static List<Long> getEnterpriseIds(List<SaveOrUpdateSaleRoyaltyVO> saveOrUpdateSaleRoyaltyVOS){
        List<Long> ids = new ArrayList<>();
        for(SaveOrUpdateSaleRoyaltyVO vo : saveOrUpdateSaleRoyaltyVOS ){
            ids.add(vo.getEnterpriseId());
        }
        return ids;
    }

    public static Set<Long> getClerkIds(List<SaveOrUpdateSaleRoyaltyVO> saveOrUpdateSaleRoyaltyVOS){
        Set<Long> ids = new HashSet<>();
        for(SaveOrUpdateSaleRoyaltyVO vo : saveOrUpdateSaleRoyaltyVOS ){
            List<SaveOrUpdateSaleRoyaltyTotalVO> saveOrUpdateSaleRoyaltyDetailVOList = vo.getSaveOrUpdateSaleRoyaltyTotalVOS();
            for(SaveOrUpdateSaleRoyaltyTotalVO totalVO : saveOrUpdateSaleRoyaltyDetailVOList){
                ids.add(totalVO.getClerkId());
            }

        }
        return ids;
    }

    public static List<Long> getSaleIds(List<SaveOrUpdateSaleRoyaltyVO> saveOrUpdateSaleRoyaltyVOS){
        List<Long> ids = new ArrayList<>();
        for(SaveOrUpdateSaleRoyaltyVO vo : saveOrUpdateSaleRoyaltyVOS ){
            List<SaveOrUpdateSaleRoyaltyTotalVO> saveOrUpdateSaleRoyaltyDetailVOList = vo.getSaveOrUpdateSaleRoyaltyTotalVOS();
            for(SaveOrUpdateSaleRoyaltyTotalVO totalVO : saveOrUpdateSaleRoyaltyDetailVOList){
                List<SaveOrUpdateSaleRoyaltyDetailVO> saveOrUpdateSaleRoyaltyDetailVOS = totalVO.getSaveOrUpdateSaleRoyaltyDetailVOS();
                for(SaveOrUpdateSaleRoyaltyDetailVO detailVO : saveOrUpdateSaleRoyaltyDetailVOS){
                    ids.add(detailVO.getSaleId());
                }
            }

        }
        return ids;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRoyaltyDate() {
        return royaltyDate;
    }

    public void setRoyaltyDate(Date royaltyDate) {
        this.royaltyDate = royaltyDate;
    }


    public Long getRoyaltyManId() {
        return royaltyManId;
    }

    public void setRoyaltyManId(Long royaltyManId) {
        this.royaltyManId = royaltyManId;
    }

    public Date getSaleDateFrom() {
        return saleDateFrom;
    }

    public void setSaleDateFrom(Date saleDateFrom) {
        this.saleDateFrom = saleDateFrom;
    }

    public Date getSaleDateTo() {
        return saleDateTo;
    }

    public void setSaleDateTo(Date saleDateTo) {
        this.saleDateTo = saleDateTo;
    }

    public List<SaveOrUpdateSaleRoyaltyTotalVO> getSaveOrUpdateSaleRoyaltyTotalVOS() {
        return saveOrUpdateSaleRoyaltyTotalVOS;
    }

    public void setSaveOrUpdateSaleRoyaltyTotalVOS(List<SaveOrUpdateSaleRoyaltyTotalVO> saveOrUpdateSaleRoyaltyTotalVOS) {
        this.saveOrUpdateSaleRoyaltyTotalVOS = saveOrUpdateSaleRoyaltyTotalVOS;
    }


    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}