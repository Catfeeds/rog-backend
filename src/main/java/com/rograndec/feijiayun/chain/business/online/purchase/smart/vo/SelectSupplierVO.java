package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class SelectSupplierVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 检索码
     */
    @ApiModelProperty(value = "检索码")
    private String pinyin;

    /**
     * 销售人员ID
     */
    @ApiModelProperty(value = "销售人员ID")
    private Long saleManId;

    /**
     * 销售人员编码
     */
    @ApiModelProperty(value = "销售人员编码")
    private String saleManCode;

    /**
     * 销售人员名称
     */
    @ApiModelProperty(value = "销售人员名称")
    private String saleManName;

    /**
     * 销售人员联系方式
     */
    @ApiModelProperty(value = "销售人员联系方式")
    private String saleManPhone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Long getSaleManId() {
        return saleManId;
    }

    public void setSaleManId(Long saleManId) {
        this.saleManId = saleManId;
    }

    public String getSaleManCode() {
        return saleManCode;
    }

    public void setSaleManCode(String saleManCode) {
        this.saleManCode = saleManCode;
    }

    public String getSaleManName() {
        return saleManName;
    }

    public void setSaleManName(String saleManName) {
        this.saleManName = saleManName;
    }

    public String getSaleManPhone() {
        return saleManPhone;
    }

    public void setSaleManPhone(String saleManPhone) {
        this.saleManPhone = saleManPhone;
    }
}
