package com.rograndec.feijiayun.chain.business.goods.info.vo;


import java.io.Serializable;

/**
 * Created by ST on 2017/9/6.
 */
public class GoodsParamVO implements Serializable {

    private String param;
    private Long enterpriseId;
    private Long parentId;
    private Integer status;

    private Integer start = 0;	 //起始条

    private Integer pageSize = 10;// 每页显示的记录数，默认10


    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}