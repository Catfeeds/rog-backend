package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class OnlineMarketVO implements Serializable {

    @ApiModelProperty(value = "关键字：名称、批准文号、规格、生产厂家", required = true)
    private String keyword;

    @ApiModelProperty(value = "供应商ID，多个逗号（,）分隔", required = true)
    private String supplierId;

    @ApiModelProperty(value = "用户ID", required = true)
    private Integer userId;

    @ApiModelProperty(value = "企业ID", required = true)
    private Integer enterpriseId;

    @ApiModelProperty(value = "供应商IDMap,key:优先级值，value:供应商ID   \n" +
            "\t * \t\tdemo [{\"standard_library_id\":\"548\",\"order_num\":1},{\"standard_library_id\":\"402\",\"order_num\":2}]   ", required = true)
    private List<Map<String, Object>> supplierIds;
    
    private String orderSupplierIds;

    @ApiModelProperty(value = "开始记录", required = true)
    private Integer start;

    @ApiModelProperty(value = "行数", required = true)
    private Integer rows;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public List<Map<String, Object>> getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(List<Map<String, Object>> supplierIds) {
        this.supplierIds = supplierIds;
    }
    
    public String getOrderSupplierIds() {
		return orderSupplierIds;
	}

	public void setOrderSupplierIds(String orderSupplierIds) {
		this.orderSupplierIds = orderSupplierIds;
	}

	public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
