package com.rograndec.feijiayun.chain.business.system.set.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class QualitySetVO implements Serializable {
    /**
     * 主键ID
     */
	@ApiModelProperty(value="主键ID",required=true)
    private Long id;

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
     * 新增当前用户企业类型 0-总部 1-自营店 2-加盟店
     */
	@ApiModelProperty(value="新增当前用户企业类型 0-总部 1-自营店 2-加盟店",required=true)
    private Integer chainType;
    
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



    public Integer getChainType() {
		return chainType;
	}

	public void setChainType(Integer chainType) {
		this.chainType = chainType;
	}

    public Integer getSysType() {
        return sysType;
    }

    public void setSysType(Integer sysType) {
        this.sysType = sysType;
    }
}