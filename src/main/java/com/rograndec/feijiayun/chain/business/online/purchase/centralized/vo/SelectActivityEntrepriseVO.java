package com.rograndec.feijiayun.chain.business.online.purchase.centralized.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class SelectActivityEntrepriseVO implements Serializable {

    private static final long serialVersionUID = 667361064393188435L;
    @ApiModelProperty(value = "活动ID")
    private Long id;

    @ApiModelProperty(value = "'一级分类")
    private List<String> gcName1S;

    @ApiModelProperty(value = "'二级分类")
    private List<String> gcName2S;

    @ApiModelProperty(value = "'生产厂家")
    private List<String> manufacturer;

    SelectActivitySupplierVO selectActivitySupplierVO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getGcName1S() {
        return gcName1S;
    }

    public void setGcName1S(List<String> gcName1S) {
        this.gcName1S = gcName1S;
    }

    public List<String> getGcName2S() {
        return gcName2S;
    }

    public void setGcName2S(List<String> gcName2S) {
        this.gcName2S = gcName2S;
    }

    public List<String> getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(List<String> manufacturer) {
        this.manufacturer = manufacturer;
    }

    public SelectActivitySupplierVO getSelectActivitySupplierVO() {
        return selectActivitySupplierVO;
    }

    public void setSelectActivitySupplierVO(SelectActivitySupplierVO selectActivitySupplierVO) {
        this.selectActivitySupplierVO = selectActivitySupplierVO;
    }

    @Override
    public String toString() {
        return "SelectActivityEntrepriseVO{" +
                "id=" + id +
                ", gcName1S=" + gcName1S +
                ", gcName2S=" + gcName2S +
                ", manufacturer=" + manufacturer +
                ", selectActivitySupplierVO=" + selectActivitySupplierVO +
                '}';
    }
}
