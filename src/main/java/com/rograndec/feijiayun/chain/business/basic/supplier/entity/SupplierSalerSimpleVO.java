package com.rograndec.feijiayun.chain.business.basic.supplier.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel
public class SupplierSalerSimpleVO implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "人员id")
    private Long id;

    /**
     * 供货单位ID
     */
    @ApiModelProperty(value = "供货单位ID")
    private Long supplierId;

    /**
     * 企业（总部）ID
     */
    @ApiModelProperty(value = "企业（总部）ID")
    private Long enterpriseId;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 电话
     */
    @ApiModelProperty(value = "联系电话")
    private String telephone;


    /**
     * 状态（0-离职；1-在职）
     */
    @ApiModelProperty(value = "状态（0-离职；1-在职）")
    private Integer status;

    public static List<SupplierSalerSimpleVO> getSupplierSalerSimpleVOs(List<SupplierSaler> supplierSalers){
        List<SupplierSalerSimpleVO> list = new ArrayList<>();
        for(SupplierSaler supplierSaler : supplierSalers){
            SupplierSalerSimpleVO supplierSalerSimpleVO = getSupplierSalerSimpleVO(supplierSaler);
            list.add(supplierSalerSimpleVO);
        }
        return list;
    }

    public static SupplierSalerSimpleVO getSupplierSalerSimpleVO(SupplierSaler supplierSaler){
        SupplierSalerSimpleVO supplierSalerSimpleVO = new SupplierSalerSimpleVO();
        supplierSalerSimpleVO.setId(supplierSaler.getId());
        supplierSalerSimpleVO.setEnterpriseId(supplierSaler.getEnterpriseId());
        supplierSalerSimpleVO.setCode(supplierSaler.getCode());
        supplierSalerSimpleVO.setName(supplierSaler.getName());
        supplierSalerSimpleVO.setStatus(supplierSaler.getStatus());
        supplierSalerSimpleVO.setTelephone(supplierSaler.getTelephone());
        supplierSalerSimpleVO.setSupplierId(supplierSaler.getSupplierId());
        return supplierSalerSimpleVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}