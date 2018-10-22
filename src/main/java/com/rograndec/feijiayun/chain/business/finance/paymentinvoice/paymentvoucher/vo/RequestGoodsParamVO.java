package com.rograndec.feijiayun.chain.business.finance.paymentinvoice.paymentvoucher.vo;

import com.rograndec.feijiayun.chain.common.vo.RequestBaseParamVO;

import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2018/1/16 14:49
 */

public class RequestGoodsParamVO extends RequestBaseParamVO {

    private String param;

    private Integer status;

    private Long supplierId;

    private Long enterpriseId;

    private List<String> ids;

    private Integer partBillStatus;


    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    @Override
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public Integer getPartBillStatus() {
        return partBillStatus;
    }

    public void setPartBillStatus(Integer partBillStatus) {
        this.partBillStatus = partBillStatus;
    }


}