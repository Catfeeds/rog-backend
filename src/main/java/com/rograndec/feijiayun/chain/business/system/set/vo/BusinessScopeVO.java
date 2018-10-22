package com.rograndec.feijiayun.chain.business.system.set.vo;

import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "businessScope", description = "经营范围对象")
public class BusinessScopeVO implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业（总部）ID
     */
	@ApiModelProperty(value = "企业（总部）ID")
    private Long enterpriseId;

    /**
     * 0：用户自定义；1-系统默认
     */
	@ApiModelProperty(value = "0：用户自定义；1-系统默认")
    private Integer sysType;

    /**
     * 品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）
     */
	@ApiModelProperty(value = "品种类别（0-药品；1-医疗器械；2-食品；3-化妆品；4-其它）")
    private Integer businessVariety;

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
     * 状态（0-禁用；1-启用）
     */
	@ApiModelProperty(value = "状态（0-禁用；1-启用）")
    private Integer status;

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

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }

    public Integer getBusinessVariety() {
        return businessVariety;
    }

    public void setBusinessVariety(Integer businessVariety) {
        this.businessVariety = businessVariety;
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
        sb.append(", businessVariety=").append(businessVariety);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }

    public static List<BusinessScopeVO> getBusinessScopeVOs4BusinessScopes(List<BusinessScope> businessScopes){
        List<BusinessScopeVO> businessScopeVOS = new ArrayList<>();
        for(BusinessScope businessScope : businessScopes){
            BusinessScopeVO businessScopeVO = new BusinessScopeVO();
            businessScopeVO.setId(businessScope.getId());
            businessScopeVO.setBusinessVariety(businessScope.getBusinessVariety());
            businessScopeVO.setCode(businessScope.getCode());
            businessScopeVO.setEnterpriseId(businessScope.getEnterpriseId());
            businessScopeVO.setName(businessScope.getName());
            businessScopeVO.setStatus(businessScope.getStatus());
            businessScopeVO.setSysType(businessScope.getSysType());
            businessScopeVOS.add(businessScopeVO);
        }

        return businessScopeVOS;
    }
}