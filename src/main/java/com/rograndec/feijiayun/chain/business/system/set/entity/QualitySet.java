package com.rograndec.feijiayun.chain.business.system.set.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class QualitySet implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value="主键ID",required=true)
    private Long id;

    /**
     * 企业（总部）ID
     */
	@ApiModelProperty(value="企业（总部）ID",required=true)
    private Long enterpriseId;

    /**
     * 0：用户自定义；1-系统默认
     */
	@ApiModelProperty(value="0：用户自定义；1-系统默认",required=true)
    private Integer sysType;

    /**
     * 设置类型（0-拒收原因；1-不合格原因；2-退货原因；3-处置措施；4-验收类型；5-验收项目；6-验收结论；7-养护措施）
     */
	@ApiModelProperty(value="设置类型（0-拒收原因；1-不合格原因；2-退货原因；3-处置措施；4-验收类型；5-验收项目；6-验收结论；7-养护措施）",required=true)
    private Integer setType;

    /**
     * 编码
     */
	@ApiModelProperty(value="编码",required=true)
    private String code;

    /**
     * 描述
     */
	@ApiModelProperty(value="描述",required=true)
    private String description;

    /**
     * 当set_type=2（退货原因）时，type含义为退货类型（0-质量问题退货；1-非质量问题退货）；当set_type=5（验收项目）时，type含义为验收类型（0-全部；1-国产药品；2-进口药品；3-中药饮品；4-中药材；5-医疗器械；6-食品；7-化妆品；8-其它）当set_type=6（验收结论）时，type含义为机构类型（0-总部；1-门店）当set_type=7（养护措施）时，type含义为养护措施类型（0-养护和检查；1-仅养护）
     */
	@ApiModelProperty(value="当set_type=2（退货原因）时，type含义为退货类型（0-质量问题退货；1-非质量问题退货）；当set_type=5（验收项目）时，type含义为验收类型（0-全部；1-国产药品；2-进口药品；3-中药饮品；4-中药材；5-医疗器械；6-食品；7-化妆品；8-其它）当set_type=6（验收结论）时，type含义为机构类型（0-总部；1-门店）当set_type=7（养护措施）时，type含义为养护措施类型（0-养护和检查；1-仅养护）",required=true)
    private Integer type;

    /**
     * 当set_type=5（验收项目）时，have_file含义为是否有附件（0-无；1-有）
     */
	@ApiModelProperty(value="当set_type=5（验收项目）时，have_file含义为是否有附件（0-无；1-有）",required=true)
    private Integer haveFile;

    /**
     * 状态（0-禁用；1-启用）
     */
	@ApiModelProperty(value="状态（0-禁用；1-启用）",required=true)
    private Integer status;

    /**
     * 备注
     */
	@ApiModelProperty(value="备注",required=true)
    private String remark;

    /**
     * 创建人ID
     */
	@ApiModelProperty(value="创建人ID",required=true)
    private Long createrId;

    /**
     * 创建人编码
     */
	@ApiModelProperty(value="创建人编码",required=true)
    private String createrCode;

    /**
     * 创建人名称
     */
	@ApiModelProperty(value="创建人名称",required=true)
    private String createrName;

    /**
     * 创建时间
     */
	@ApiModelProperty(value="创建时间",required=true)
    private Date createTime;

    /**
     * 最后修改人ID
     */
	@ApiModelProperty(value="最后修改人ID",required=true)
    private Long modifierId;

    /**
     * 最后修改人编码
     */
	@ApiModelProperty(value="最后修改人编码",required=true)
    private String modifierCode;

    /**
     * 最后修改人名称
     */
	@ApiModelProperty(value="最后修改人名称",required=true)
    private String modifierName;

    /**
     * 更新时间
     */
	@ApiModelProperty(value="更新时间",required=true)
    private Date updateTime;
    
    
    /**
     * 新增当前用户企业类型 0-总部 1-自营店 2-加盟店
     */
	@ApiModelProperty(value="新增当前用户企业类型 0-总部 1-自营店 2-加盟店",required=true)
    private Integer chainType;

    /**
     * 当质量设置的原因是验收项目时也就是set_type = 5的时候,tips:界面上的查询界面的验收类型是我穿给前台显示。
     */
    @ApiModelProperty(value="当质量设置的原因是验收项目时也就是set_type = 5的时候,tips:界面上的查询界面的验收类型是我穿给前台显示",required=true)
    private String checkTypeDetail;

    /**
     * 当前质量设置是否可删除 true可删 false不可删
     */

    private Boolean deleteFlag = true;

    /**
     * 是否可修改
     */
    private Boolean updateFlag = true;
    /**
     * saas_quality_set
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
     * 企业（总部）ID
     * @return enterprise_id 企业（总部）ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业（总部）ID
     * @param enterpriseId 企业（总部）ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 0：用户自定义；1-系统默认
     * @return sys_type 0：用户自定义；1-系统默认
     */
    public Integer getSysType() {
        return sysType;
    }

    /**
     * 0：用户自定义；1-系统默认
     * @param sysType 0：用户自定义；1-系统默认
     */
    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }

    /**
     * 设置类型（0-拒收原因；1-不合格原因；2-退货原因；3-处置措施；4-验收类型；5-验收项目；6-验收结论；7-养护措施）
     * @return set_type 设置类型（0-拒收原因；1-不合格原因；2-退货原因；3-处置措施；4-验收类型；5-验收项目；6-验收结论；7-养护措施）
     */
    public Integer getSetType() {
        return setType;
    }

    /**
     * 设置类型（0-拒收原因；1-不合格原因；2-退货原因；3-处置措施；4-验收类型；5-验收项目；6-验收结论；7-养护措施）
     * @param setType 设置类型（0-拒收原因；1-不合格原因；2-退货原因；3-处置措施；4-验收类型；5-验收项目；6-验收结论；7-养护措施）
     */
    public void setSetType(Integer setType) {
        this.setType = setType;
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
     * 描述
     * @return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 当set_type=2（退货原因）时，type含义为退货类型（0-质量问题退货；1-非质量问题退货）；当set_type=5（验收项目）时，type含义为验收类型（0-全部；1-国产药品；2-进口药品；3-中药饮品；4-中药材；5-医疗器械；6-食品；7-化妆品；8-其它）当set_type=6（验收结论）时，type含义为机构类型（0-总部；1-门店）当set_type=7（养护措施）时，type含义为养护措施类型（0-养护和检查；1-仅养护）
     * @return type 当set_type=2（退货原因）时，type含义为退货类型（0-质量问题退货；1-非质量问题退货）；当set_type=5（验收项目）时，type含义为验收类型（0-全部；1-国产药品；2-进口药品；3-中药饮品；4-中药材；5-医疗器械；6-食品；7-化妆品；8-其它）当set_type=6（验收结论）时，type含义为机构类型（0-总部；1-门店）当set_type=7（养护措施）时，type含义为养护措施类型（0-养护和检查；1-仅养护）
     */
    public Integer getType() {
        return type;
    }

    /**
     * 当set_type=2（退货原因）时，type含义为退货类型（0-质量问题退货；1-非质量问题退货）；当set_type=5（验收项目）时，type含义为验收类型（0-全部；1-国产药品；2-进口药品；3-中药饮品；4-中药材；5-医疗器械；6-食品；7-化妆品；8-其它）当set_type=6（验收结论）时，type含义为机构类型（0-总部；1-门店）当set_type=7（养护措施）时，type含义为养护措施类型（0-养护和检查；1-仅养护）
     * @param type 当set_type=2（退货原因）时，type含义为退货类型（0-质量问题退货；1-非质量问题退货）；当set_type=5（验收项目）时，type含义为验收类型（0-全部；1-国产药品；2-进口药品；3-中药饮品；4-中药材；5-医疗器械；6-食品；7-化妆品；8-其它）当set_type=6（验收结论）时，type含义为机构类型（0-总部；1-门店）当set_type=7（养护措施）时，type含义为养护措施类型（0-养护和检查；1-仅养护）
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 当set_type=5（验收项目）时，have_file含义为是否有附件（0-无；1-有）
     * @return have_file 当set_type=5（验收项目）时，have_file含义为是否有附件（0-无；1-有）
     */
    public Integer getHaveFile() {
        return haveFile;
    }

    /**
     * 当set_type=5（验收项目）时，have_file含义为是否有附件（0-无；1-有）
     * @param haveFile 当set_type=5（验收项目）时，have_file含义为是否有附件（0-无；1-有）
     */
    public void setHaveFile(Integer haveFile) {
        this.haveFile = haveFile;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @return status 状态（0-禁用；1-启用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态（0-禁用；1-启用）
     * @param status 状态（0-禁用；1-启用）
     */
    public void setStatus(Integer status) {
        this.status = status;
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

    public Boolean getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(Boolean updateFlag) {
        this.updateFlag = updateFlag;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

    public String getCheckTypeDetail() {
        return checkTypeDetail;
    }

    public void setCheckTypeDetail(String checkTypeDetail) {
        this.checkTypeDetail = checkTypeDetail;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
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
        sb.append(", sysType=").append(sysType);
        sb.append(", setType=").append(setType);
        sb.append(", code=").append(code);
        sb.append(", description=").append(description);
        sb.append(", type=").append(type);
        sb.append(", haveFile=").append(haveFile);
        sb.append(", status=").append(status);
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
}