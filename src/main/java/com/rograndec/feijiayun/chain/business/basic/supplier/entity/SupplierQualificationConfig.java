package com.rograndec.feijiayun.chain.business.basic.supplier.entity;

import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierDetailVO;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierQulificationVO;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SupplierQualificationConfig implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 供货单位ID
     */
	@ApiModelProperty(value = "供货单位ID")
    private Long supplierId;

    /**
     * 企业ID
     */
	@ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 资质ID
     */
	@ApiModelProperty(value = "资质ID")
    private Long qualificationId;

	/**
	 * 资质编号(不是资质表编码)
	 */
	@ApiModelProperty(value = " 资质类型(不是资质表编码)")
	private String qualificationCode;

    /**
     * 起始日期
     */
    @ApiModelProperty(value = "起始日期")
    private Date startDate;
	/**
     * 有效期至
     */
	@ApiModelProperty(value = "有效期至")
    private Date validUntil;

    /**
     * 资质附件ID
     */
	@ApiModelProperty(value = "资质附件ID")
    private Long fileId;

    /**
     * 备注
     */
	@ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人ID
     */
	@ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
	@ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
	@ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
	@ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
	@ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
	@ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
	@ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * saas_supplier_qualification_config
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 供货单位ID
     * @return supplier_id 供货单位ID
     */
    public Long getSupplierId() {
        return supplierId;
    }

    /**
     * 供货单位ID
     * @param supplierId 供货单位ID
     */
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 资质ID
     * @return qualification_id 资质ID
     */
    public Long getQualificationId() {
        return qualificationId;
    }

    /**
     * 资质ID
     * @param qualificationId 资质ID
     */
    public void setQualificationId(Long qualificationId) {
        this.qualificationId = qualificationId;
    }

    /**
     * 有效期至
     * @return valid_until 有效期至
     */
    public Date getValidUntil() {
        return validUntil;
    }

    /**
     * 有效期至
     * @param validUntil 有效期至
     */
    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    /**
     * 资质附件ID
     * @return file_id 资质附件ID
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * 资质附件ID
     * @param fileId 资质附件ID
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getQualificationCode() {
		return qualificationCode;
	}

	public void setQualificationCode(String qualificationCode) {
		this.qualificationCode = qualificationCode;
	}

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @mbg.generated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", supplierId=").append(supplierId);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", qualificationId=").append(qualificationId);
        sb.append(", validUntil=").append(validUntil);
        sb.append(", fileId=").append(fileId);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

	public static List<SupplierQualificationConfig> getSupplierQualificationAddSupplierQualificationVO(
            SupplierDetailVO supplierDetailVO, List<SupplierQulificationVO> supplierQulificationVO, UserVO user) {
		List<SupplierQualificationConfig> list = new ArrayList<>();
        for(SupplierQulificationVO configVO : supplierQulificationVO){
        	SupplierQualificationConfig supplierQualificationConfig = getSupplierQualificationConfig4VO(configVO,supplierDetailVO,user);
            list.add(supplierQualificationConfig);
        }
        return list;
	}

	private static SupplierQualificationConfig getSupplierQualificationConfig4VO(SupplierQulificationVO configVO,
                                                                                 SupplierDetailVO supplierDetailVO, UserVO user) {
		SupplierQualificationConfig supplierQualificationConfig = new SupplierQualificationConfig();
		supplierQualificationConfig.setSupplierId(supplierDetailVO.getId());
        Long headEnterpriseId = ChainType.getHeadEnterpriseId(user);
        supplierQualificationConfig.setEnterpriseId(headEnterpriseId);
		supplierQualificationConfig.setQualificationId(configVO.getQualificationId());
		supplierQualificationConfig.setQualificationCode(configVO.getQualificationCode());
		supplierQualificationConfig.setValidUntil(configVO.getValidUntil());
        supplierQualificationConfig.setStartDate(configVO.getStartDate());
		supplierQualificationConfig.setFileId(configVO.getFileId());
        supplierQualificationConfig.setCreaterId(user.getUserId());
        supplierQualificationConfig.setCreaterCode(user.getUserCode());
        supplierQualificationConfig.setCreaterName(user.getUserName());
        supplierQualificationConfig.setCreateTime(new Date());
        supplierQualificationConfig.setModifierId(user.getUserId());
        supplierQualificationConfig.setModifierCode(user.getUserCode());
        supplierQualificationConfig.setModifierName(user.getUserName());
        supplierQualificationConfig.setUpdateTime(new Date());
		return supplierQualificationConfig;
	}
}