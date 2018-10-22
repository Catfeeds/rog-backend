package com.rograndec.feijiayun.chain.common.vo;

import com.rograndec.feijiayun.chain.common.constant.RedisKeyPrefix;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class DraftCacheVO<T> implements Serializable {

    /**
     * 草稿存储日期
     */
    @ApiModelProperty("草稿存储日期,新增修改不需要")
    private String draftTime;

    /**
     * 草稿存储日期毫秒数
     */
    @ApiModelProperty("草稿存储日期毫秒数,新增修改不需要")
    private Long draftTimeMilli;

    /**
     * 类型参考OrderRule
     */
    @ApiModelProperty("类型参考OrderRule,新增修改不需要")
    private String orderCode;

    /**
     * rediskey(值) 最后以orderCode:draftTimeMilli 结尾,格式例如: orderCode:id:
     */
    @ApiModelProperty("rediskey(值),新增不需要,修改必须传递")
    private String id;


    @ApiModelProperty("上游单据id,特殊单据需要传递")
    private Long baseOrderId;

    /**
     * 企业id
     */
    @ApiModelProperty("企业id,新增修改不需要")
    private Long enterpriseId;

    @ApiModelProperty("供应商id,预付发票,应付发票的时候需要")
    private Long supplierId;

    @ApiModelProperty("查询供应商类型,收付款需要 0:供货单位 1:购货单位")
    private Integer supplierType;

    /**
     * 业务单据实体
     */
    @ApiModelProperty("业务单据实体")
    private T orderData;

    @ApiModelProperty("业务单据实体字符串")
    private String orderStr;

    @ApiModelProperty("业务单据 0 : javaBean , 1 : json字符串")
    private int valueType;

   /**
     * 业务类型 在redis中的key 例如: eid:CJ
     * @param draftCacheVOs
     * @return
     */
    public static String generateRediskey(DraftCacheVO draftCacheVOs){

        StringBuffer sb = new StringBuffer();
        sb.append(RedisKeyPrefix.DRAFT_CACHE.getName());
        sb.append(draftCacheVOs.getEnterpriseId()).append(":");
        if(null != draftCacheVOs.getSupplierId()){
            sb.append(draftCacheVOs.getSupplierId()).append(":");
        }
        if(null != draftCacheVOs.getSupplierType()){
            sb.append(draftCacheVOs.getSupplierType()).append(":");
        }
        sb.append(draftCacheVOs.getOrderCode()).toString();

       return sb.toString();
    }


    /**
     * 业务单据在redis中map的key
     * @param draftCacheVO
     * @return
     */
    public static String generateRedisValueKey(DraftCacheVO draftCacheVO){

        StringBuffer sb = new StringBuffer();

        if(null != draftCacheVO.getBaseOrderId() && 0L != draftCacheVO.getBaseOrderId()) {

            sb.append(draftCacheVO.getOrderCode()).append(":").append(draftCacheVO.getBaseOrderId());

        }else {

            sb.append(draftCacheVO.getOrderCode()).append(":").append(draftCacheVO.getDraftTimeMilli());

        }

        return sb.toString();
    }


    public String getDraftTime() {
        return draftTime;
    }

    public void setDraftTime(String draftTime) {
        this.draftTime = draftTime;
    }

    public Long getDraftTimeMilli() {
        return draftTimeMilli;
    }

    public void setDraftTimeMilli(Long draftTimeMilli) {
        this.draftTimeMilli = draftTimeMilli;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public T getOrderData() {
        return orderData;
    }

    public void setOrderData(T orderData) {
        this.orderData = orderData;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getBaseOrderId() {
        return baseOrderId;
    }

    public void setBaseOrderId(Long baseOrderId) {
        this.baseOrderId = baseOrderId;
    }

    public String getOrderStr() {
        return orderStr;
    }

    public void setOrderStr(String orderStr) {
        this.orderStr = orderStr;
    }

    public int getValueType() {
        return valueType;
    }

    public void setValueType(int valueType) {
        this.valueType = valueType;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(Integer supplierType) {
        this.supplierType = supplierType;
    }
}
