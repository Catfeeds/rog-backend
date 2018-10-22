package com.rograndec.feijiayun.chain.business.basic.equipment.entity;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_equipment_type
 * 
 * 
 * @author Administrator
 * 
 * 2017-10-13
 */
public class EquipmentType implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

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
     * 上级类型编码
     */
    @ApiModelProperty(value = "上级类型编码")
    private String parentTypeCode;

    /**
     * 上级类型名称
     */
    @ApiModelProperty(value = "上级类型名称")
    private String parentTypeName;

    List<EquipmentType> equipmentTypeList;

    public List<EquipmentType> getEquipmentTypeList() {
        return equipmentTypeList;
    }

    public void setEquipmentTypeList(List<EquipmentType> equipmentTypeList) {
        this.equipmentTypeList = equipmentTypeList;
    }

    /**
     * saas_equipment_type
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
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 编码
     * @return code 编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 编码
     * @param code 编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 名称
     * @return name 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 上级类型编码
     * @return parent_type_code 上级类型编码
     */
    public String getParentTypeCode() {
        return parentTypeCode;
    }

    /**
     * 上级类型编码
     * @param parentTypeCode 上级类型编码
     */
    public void setParentTypeCode(String parentTypeCode) {
        this.parentTypeCode = parentTypeCode == null ? null : parentTypeCode.trim();
    }

    /**
     * 上级类型名称
     * @return parent_type_name 上级类型名称
     */
    public String getParentTypeName() {
        return parentTypeName;
    }

    /**
     * 上级类型名称
     * @param parentTypeName 上级类型名称
     */
    public void setParentTypeName(String parentTypeName) {
        this.parentTypeName = parentTypeName == null ? null : parentTypeName.trim();
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
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", parentId=").append(parentId);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", parentTypeCode=").append(parentTypeCode);
        sb.append(", parentTypeName=").append(parentTypeName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}