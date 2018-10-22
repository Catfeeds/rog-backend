package com.rograndec.feijiayun.chain.business.report.quality.purchase.vo.supplier;

import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierSalerVO;
import com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO.QualificationConfigVO;
import com.rograndec.feijiayun.chain.common.constant.Nature;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;


/**
 * 功能描述：
 * Created by ST on 2017/10/18 15:44
 */

public class SupplierQualificationReportVO implements Serializable{

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
     * 性质（0-批发企业；1-生产企业）
     */
    @ApiModelProperty(value = "性质（0-批发企业；1-生产企业）")
    private Integer nature;

    @ApiModelProperty(value = "性质（0-批发企业；1-生产企业）")
    private String natureName;


    @ApiModelProperty(value = "供货单位资质列表")
    private List<QualificationConfigVO> qualificationConfigVOS;

    @ApiModelProperty(value = "供货单位员工列表")
    private List<SupplierSalerVO> supplierSalerVOS;



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

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public String getNatureName() {
        return Nature.getName(nature);
    }

    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }

    public List<QualificationConfigVO> getQualificationConfigVOS() {
        return qualificationConfigVOS;
    }

    public void setQualificationConfigVOS(List<QualificationConfigVO> qualificationConfigVOS) {
        this.qualificationConfigVOS = qualificationConfigVOS;
    }

    public List<SupplierSalerVO> getSupplierSalerVOS() {
        return supplierSalerVOS;
    }

    public void setSupplierSalerVOS(List<SupplierSalerVO> supplierSalerVOS) {
        this.supplierSalerVOS = supplierSalerVOS;
    }


}