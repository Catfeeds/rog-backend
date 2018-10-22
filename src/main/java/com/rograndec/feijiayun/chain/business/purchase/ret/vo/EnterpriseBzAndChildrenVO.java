package com.rograndec.feijiayun.chain.business.purchase.ret.vo;

import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.common.constant.SettlementMode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
public class EnterpriseBzAndChildrenVO implements Serializable {
    /**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年9月1日 下午3:02:37 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    private Long id;

    /**
     * 上级企业ID，默认值为0
     */
    @ApiModelProperty(value = "上级企业ID，默认值为0", required = true)
    private Long parentId;

    /**
     * 药店类型（0-总部；1-自营店；2-加盟店）
     */
    @ApiModelProperty(value = "药店类型（0-总部；1-自营店；2-加盟店）", required = true)
    private Integer chainType;

    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称", required = true)
    private String name;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码", required = true)
    private String code;

    /**
     * 检索码
     */
    @ApiModelProperty(value = "检索码", required = true)
    private String pinyin;

    /**
     * 公司电话
     */
    @ApiModelProperty(value = "公司电话")
    private String tel;

    /**
     * 公司地址
     */
    @ApiModelProperty(value = "公司地址")
    private String companyAddress;
    /**
     * 结算方式（0-零售缴款；1-应收账款）
     */
    @ApiModelProperty(value = "结算方式名称")
    private String settlementModeName;

    /**
     * 结算方式（0-零售缴款；1-应收账款）
     */
    @ApiModelProperty(value = "结算方式ID（0-零售缴款；1-应收账款）")
    private Integer settlementMode;

    public String getSettlementModeName() {
        return settlementModeName;
    }

    public void setSettlementModeName(String settlementModeName) {
        this.settlementModeName = settlementModeName;
    }

    public Integer getSettlementMode() {
        return settlementMode;
    }

    public void setSettlementMode(Integer settlementMode) {
        this.settlementMode = settlementMode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public static EnterpriseBzAndChildrenVO getEnterprise4VO(Enterprise enterprise,EnterpriseBusiness business){
        EnterpriseBzAndChildrenVO enterpriseAndChildrenVO = new EnterpriseBzAndChildrenVO();
        enterpriseAndChildrenVO.setId(enterprise.getId());
        enterpriseAndChildrenVO.setParentId(enterprise.getParentId());
        enterpriseAndChildrenVO.setChainType(enterprise.getChainType());
        enterpriseAndChildrenVO.setName(enterprise.getName());
        enterpriseAndChildrenVO.setCode(enterprise.getCode());
        enterpriseAndChildrenVO.setPinyin(enterprise.getPinyin());
        enterpriseAndChildrenVO.setTel(enterprise.getTel());
        enterpriseAndChildrenVO.setCompanyAddress(enterprise.getCompanyAddress());
        if(null != business){
            enterpriseAndChildrenVO.setSettlementMode(business.getSettlementMode());
            enterpriseAndChildrenVO.setSettlementModeName(business.getSettlementModeName());
        }else {
            enterpriseAndChildrenVO.setSettlementMode(SettlementMode.RETAIL_CONTRIBUTION.getCode());
            enterpriseAndChildrenVO.setSettlementModeName("现结");
        }
        return enterpriseAndChildrenVO;
    }

}