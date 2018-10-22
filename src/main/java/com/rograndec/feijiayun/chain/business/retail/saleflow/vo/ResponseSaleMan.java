package com.rograndec.feijiayun.chain.business.retail.saleflow.vo;

import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.SaleDetail;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * saas_sale_detail
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-09-25
 */
public class ResponseSaleMan implements Serializable {


    /**
     * 营业员ID
     */
    @ApiModelProperty(value = "营业员ID")
    private Long clerkId;

    /**
     * 营业员编码
     */
    @ApiModelProperty(value = "营业员编码")
    private String clerkCode;

    /**
     * 营业员名称
     */
    @ApiModelProperty(value = "营业员名称")
    private String clerkName;

    public static List<ResponseSaleMan> getResponseSaleMan(List<SaleDetail> saleDetails){
        List<ResponseSaleMan> list = new ArrayList<>();

        for(SaleDetail saleDetail : saleDetails){
            ResponseSaleMan saleMan = new ResponseSaleMan();
            saleMan.setClerkId(saleDetail.getClerkId());
            saleMan.setClerkName(saleDetail.getClerkName());
            saleMan.setClerkCode(saleDetail.getClerkCode());
            list.add(saleMan);
        }
        return list;
    }


    public Long getClerkId() {
        return clerkId;
    }

    public void setClerkId(Long clerkId) {
        this.clerkId = clerkId;
    }

    public String getClerkCode() {
        return clerkCode;
    }

    public void setClerkCode(String clerkCode) {
        this.clerkCode = clerkCode;
    }

    public String getClerkName() {
        return clerkName;
    }

    public void setClerkName(String clerkName) {
        this.clerkName = clerkName;
    }
}