package com.rograndec.feijiayun.chain.business.basic.equipment.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class EquipmentMaintanceRequestVO  implements Serializable{

    @ApiModelProperty("当前页码(必传)")
    private Integer  pageNo;

    @ApiModelProperty("显示条数(必传)")
    private Integer pageSize;


    @ApiModelProperty("按某一列排序(组织机构编码：enterpriseCode(默认),设备编码:equipmentCode )")
    private String order;

    @ApiModelProperty("排序方式（升序：asc(默认),降序：desc）")
    private String sort;


    @ApiModelProperty("搜索关键字")
    private String key;
    
    @ApiModelProperty("搜索关键字")
    private String code;

    @ApiModelProperty(value = "类型ID")
    private Long typeId;


    @ApiModelProperty("不需要传")
    private Long enterpriseId;

    @ApiModelProperty("不需要传")
    private Long parentId;

    @ApiModelProperty("不需要传")
    private Integer pageStart;
    /**
     * 企业id集合
     */
    private List<Long> list;


    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public List<Long> getList() {
        return list;
    }

    public void setList(List<Long> list) {
        this.list = list;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
		this.key = code;
	}
    
}
