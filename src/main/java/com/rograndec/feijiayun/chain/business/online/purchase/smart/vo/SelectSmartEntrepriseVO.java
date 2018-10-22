package com.rograndec.feijiayun.chain.business.online.purchase.smart.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class SelectSmartEntrepriseVO implements Serializable {

    private static final long serialVersionUID = 667361064393188435L;

    @ApiModelProperty(value = "'一级分类")
    private List<String> gcName1S;

    @ApiModelProperty(value = "'二级分类")
    private List<String> gcName2S;

    @ApiModelProperty(value = "'生产厂家")
    private List<String> manufacturer;

    @ApiModelProperty(value = "剂型集合")
    private List<String> dosageNames;

    @ApiModelProperty(value = "开始记录数")
    private Integer start;

    @ApiModelProperty(value = "行数")
    private Integer rows;

    @ApiModelProperty(value = "总数量")
    private Integer totalCount;

    List<SelectSmartSupplierVO> selectSmartSupplierVO;

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

    public List<SelectSmartSupplierVO> getSelectSmartSupplierVO() {
        return selectSmartSupplierVO;
    }

    public void setSelectSmartSupplierVO(List<SelectSmartSupplierVO> selectSmartSupplierVO) {
        this.selectSmartSupplierVO = selectSmartSupplierVO;
    }

    public List<String> getDosageNames() {
        return dosageNames;
    }

    public void setDosageNames(List<String> dosageNames) {
        this.dosageNames = dosageNames;
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

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "SelectActivityEntrepriseVO{" +
                "gcName1S=" + gcName1S +
                ", gcName2S=" + gcName2S +
                ", manufacturer=" + manufacturer +
                ", dosageNames=" + dosageNames +
                ", start=" + start +
                ", rows=" + rows +
                ", totalCount=" + totalCount +
                ", selectSmartSupplierVO=" + selectSmartSupplierVO +
                '}';
    }
}
