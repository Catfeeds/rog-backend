package com.rograndec.feijiayun.chain.business.system.set.vo;

import com.rograndec.feijiayun.chain.business.system.set.entity.UserQualification;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel
public class UserQualificationReturnVO implements Serializable {
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
     * 适用机构（0-全部；1-总部；2-分店）
     */
	@ApiModelProperty(value="适用机构（0-全部；1-总部；2-分店）",required=true)
    private Integer suitableUnit;

    /**
     * 适用岗位ID
     */
	@ApiModelProperty(value="适用岗位ID",required=true)
    private String  positionIds;

	/**
     * 0：用户自定义；1-系统默认
     */
	@ApiModelProperty(value = "0：用户自定义；1-系统默认")
    private Integer sysType;
	
	/**
     * 资质类型是否必选（0-可选；1-必选）
     */
	@ApiModelProperty(value = "资质类型是否必选（0-可选；1-必选）")
    private Integer typeMust;
	
    /**
     * 编码
     */
	@ApiModelProperty(value="编码",required=true)
    private String code;

    /**
     * 名称
     */
	@ApiModelProperty(value="名称",required=true)
    private String name;

    /**
     * 控制类型（0-质量控制；1-仅提示）
     */
	@ApiModelProperty(value="控制类型（0-质量控制；1-仅提示）",required=true)
    private Integer controlType;

    /**
     * 等级是否必填（0-非必填；1-必填）
     */
	@ApiModelProperty(value="等级是否必填（0-非必填；1-必填）",required=true)
    private Integer gradeMust;

    /**
     * 资格证书号是否必填（0-非必填；1-必填）
     */
	@ApiModelProperty(value="资格证书号是否必填（0-非必填；1-必填）",required=true)
    private Integer codeMust;

    /**
     * 注册证书号是否必填（0-非必填；1-必填）
     */
	@ApiModelProperty(value="注册证书号是否必填（0-非必填；1-必填）",required=true)
    private Integer registerCodeMust;

    /**
     * 适用地区是否必填（0-非必填；1-必填）
     */
	@ApiModelProperty(value="适用地区是否必填（0-非必填；1-必填）",required=true)
    private Integer regionMust;

    /**
     * 适用类别是否必填（0-非必填；1-必填）
     */
	@ApiModelProperty(value="适用类别是否必填（0-非必填；1-必填）",required=true)
    private Integer categoryMust;

    /**
     * 适用范围是否必填（0-只读；1-必填）
     */
	@ApiModelProperty(value="适用范围是否必填（0-只读；1-必填）",required=true)
    private Integer rangeMust;

    /**
     * 附件是否必填（0-非必填；1-必填）
     */
	@ApiModelProperty(value="附件是否必填（0-非必填；1-必填）",required=true)
    private Integer fileMust;

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
     * 新增企业类型
     */
	@ApiModelProperty(value="新增企业类型",required=true)
    private Integer chainType;

    public static List<UserQualificationReturnVO> getUserQualificationReturnsVO4UserQualifications(List<UserQualification> userQualifications){
        List<UserQualificationReturnVO> userQualificationReturnVOS = new ArrayList<>();

        for(UserQualification userQualification : userQualifications){

            UserQualificationReturnVO userQualificationReturnVO = getUserQualificationReturnVO4UserQualification(userQualification);
            userQualificationReturnVOS.add(userQualificationReturnVO);
        }

        return userQualificationReturnVOS;
    }

	public static UserQualificationReturnVO getUserQualificationReturnVO4UserQualification(UserQualification userQualification){
        UserQualificationReturnVO userQualificationReturnVO = new UserQualificationReturnVO();
        userQualificationReturnVO.setId(userQualification.getId());
        userQualificationReturnVO.setEnterpriseId(userQualification.getEnterpriseId());
        userQualificationReturnVO.setSuitableUnit(userQualification.getSuitableUnit());
        userQualificationReturnVO.setPositionIds(userQualification.getPositionIds());
        userQualificationReturnVO.setSysType(userQualification.getSysType());
        userQualificationReturnVO.setTypeMust(userQualification.getTypeMust());
        userQualificationReturnVO.setCode(userQualification.getCode());
        userQualificationReturnVO.setName(userQualification.getName());
        userQualificationReturnVO.setControlType(userQualification.getControlType());
        userQualificationReturnVO.setGradeMust(userQualification.getGradeMust());
        userQualificationReturnVO.setCodeMust(userQualification.getCodeMust());
        userQualificationReturnVO.setRegisterCodeMust(userQualification.getRegisterCodeMust());
        userQualificationReturnVO.setRegionMust(userQualification.getRegionMust());
        userQualificationReturnVO.setCategoryMust(userQualification.getCategoryMust());
        userQualificationReturnVO.setRangeMust(userQualification.getRangeMust());
        userQualificationReturnVO.setFileMust(userQualification.getFileMust());
        userQualificationReturnVO.setStatus(userQualification.getStatus());
        userQualificationReturnVO.setRemark(userQualification.getRemark());
        userQualificationReturnVO.setChainType(userQualification.getChainType());

        return userQualificationReturnVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Integer getSuitableUnit() {
        return suitableUnit;
    }

    public void setSuitableUnit(Integer suitableUnit) {
        this.suitableUnit = suitableUnit;
    }

    public String getPositionIds() {
        return positionIds;
    }

    public void setPositionIds(String positionIds) {
        this.positionIds = positionIds;
    }

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }

    public Integer getTypeMust() {
        return typeMust;
    }

    public void setTypeMust(Integer typeMust) {
        this.typeMust = typeMust;
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

    public Integer getControlType() {
        return controlType;
    }

    public void setControlType(Integer controlType) {
        this.controlType = controlType;
    }

    public Integer getGradeMust() {
        return gradeMust;
    }

    public void setGradeMust(Integer gradeMust) {
        this.gradeMust = gradeMust;
    }

    public Integer getCodeMust() {
        return codeMust;
    }

    public void setCodeMust(Integer codeMust) {
        this.codeMust = codeMust;
    }

    public Integer getRegisterCodeMust() {
        return registerCodeMust;
    }

    public void setRegisterCodeMust(Integer registerCodeMust) {
        this.registerCodeMust = registerCodeMust;
    }

    public Integer getRegionMust() {
        return regionMust;
    }

    public void setRegionMust(Integer regionMust) {
        this.regionMust = regionMust;
    }

    public Integer getCategoryMust() {
        return categoryMust;
    }

    public void setCategoryMust(Integer categoryMust) {
        this.categoryMust = categoryMust;
    }

    public Integer getRangeMust() {
        return rangeMust;
    }

    public void setRangeMust(Integer rangeMust) {
        this.rangeMust = rangeMust;
    }

    public Integer getFileMust() {
        return fileMust;
    }

    public void setFileMust(Integer fileMust) {
        this.fileMust = fileMust;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
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
        sb.append(", suitableUnit=").append(suitableUnit);
        sb.append(", positionIds=").append(positionIds);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", controlType=").append(controlType);
        sb.append(", gradeMust=").append(gradeMust);
        sb.append(", codeMust=").append(codeMust);
        sb.append(", registerCodeMust=").append(registerCodeMust);
        sb.append(", regionMust=").append(regionMust);
        sb.append(", categoryMust=").append(categoryMust);
        sb.append(", rangeMust=").append(rangeMust);
        sb.append(", fileMust=").append(fileMust);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append("]");
        return sb.toString();
    }
}