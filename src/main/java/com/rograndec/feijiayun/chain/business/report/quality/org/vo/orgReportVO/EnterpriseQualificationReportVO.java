package com.rograndec.feijiayun.chain.business.report.quality.org.vo.orgReportVO;

import com.rograndec.feijiayun.chain.common.constant.ChainType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel(value = "enterprise", description = "企业基本信息对象")
public class EnterpriseQualificationReportVO implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value = "企业id")
    private Long id;
	
    /**
     * 药店类型（0-总部；1-自营店；2-加盟店）
     */
	@ApiModelProperty(value = "药店类型（0-总部；1-自营店；2-加盟店）")
    private Integer chainType;

    @ApiModelProperty(value = "药店类型（0-总部；1-自营店；2-加盟店）")
	private String chainTypeName;

    /**
     * 编码
     */
	@ApiModelProperty(value = "组织机构编码")
    private String code;

    /**
     * 企业名称
     */
	@ApiModelProperty(value = "组织机构名称")
    private String name;

    @ApiModelProperty(value = "分组名称")
	private String groupName;



	private List<QualificationConfigVO> qualificationConfigVOS;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    public String getChainTypeName() {
        return ChainType.getName(chainType);
    }

    public void setChainTypeName(String chainTypeName) {
        this.chainTypeName = chainTypeName;
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

    public List<QualificationConfigVO> getQualificationConfigVOS() {
        return qualificationConfigVOS;
    }

    public void setQualificationConfigVOS(List<QualificationConfigVO> qualificationConfigVOS) {
        this.qualificationConfigVOS = qualificationConfigVOS;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}