package com.rograndec.feijiayun.chain.common.vo;

/**
 * 功能描述：
 * Created by ST on 2017/9/23 14:04
 */

public class GoodsParamStockVO {
    //企业id
    private Long enterpriseId;
    //
    private Integer chainType;

    private Long parentId;

    //关键key
    private String param;

    //是否为专管药（默认为0；0/否 1/是）
    private Integer isInChargeDrug = 0;
    //锁定数量是否需要大于0(0/否 1是)
    private Integer lockQuantity = 0;

//    处方类型(中药=0;其他=1)
    private Integer prescriptionType;

    //是否合格品区
    /**
     job_area=0，含义为“合格品区”；
     job_area=1，含义为“待处理区”；
     job_area=2，含义为“不合格品区
     */
    private Integer jobArea;


    public Integer getJobArea() {
        return jobArea;
    }

    public void setJobArea(Integer jobArea) {
        this.jobArea = jobArea;
    }

    public Integer getLockQuantity() {
        return lockQuantity;
    }

    public void setLockQuantity(Integer lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Integer getIsInChargeDrug() {
        return isInChargeDrug;
    }

    public void setIsInChargeDrug(Integer isInChargeDrug) {
        this.isInChargeDrug = isInChargeDrug;
    }

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getPrescriptionType() {
        return prescriptionType;
    }

    public void setPrescriptionType(Integer prescriptionType) {
        this.prescriptionType = prescriptionType;
    }
}