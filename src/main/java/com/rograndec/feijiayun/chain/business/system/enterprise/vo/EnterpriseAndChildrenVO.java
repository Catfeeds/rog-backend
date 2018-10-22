package com.rograndec.feijiayun.chain.business.system.enterprise.vo;

import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.utils.string.StringSplit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiModel
public class EnterpriseAndChildrenVO implements Serializable {
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
     * 经营范围ID数组
     */
    @ApiModelProperty(value = "经营品种map （可多选：0-药品；1-食品；2-化妆品；3-医疗器械；4-其它）")
    private List< Map<String, Object>>businessVarietys = new ArrayList<>();



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

    public List<Map<String, Object>> getBusinessVarietys() {
        return businessVarietys;
    }

    public void setBusinessVarietys(List<Map<String, Object>> businessVarietys) {
        this.businessVarietys = businessVarietys;
    }

    public static EnterpriseAndChildrenVO getEnterprise4VO(Enterprise enterprise){
        EnterpriseAndChildrenVO enterpriseAndChildrenVO = new EnterpriseAndChildrenVO();
        enterpriseAndChildrenVO.setId(enterprise.getId());
        enterpriseAndChildrenVO.setParentId(enterprise.getParentId());
        enterpriseAndChildrenVO.setChainType(enterprise.getChainType());
        enterpriseAndChildrenVO.setName(enterprise.getName());
        enterpriseAndChildrenVO.setCode(enterprise.getCode());
        enterpriseAndChildrenVO.setPinyin(enterprise.getPinyin());
        String businessScopeId = enterprise.getBusinessVariety();
        List<Long> varietyArray = StringSplit.strSplit(businessScopeId);


        List< Map<String, Object>> list = new ArrayList<>();
        for(Long id : varietyArray){
            Map<String, Object> varietyMap = new HashMap<>();
            varietyMap.put("id",id);
            varietyMap.put("name",BusinessVariety.getName(id.intValue()));
            list.add(varietyMap);
        }
        enterpriseAndChildrenVO.setBusinessVarietys(list);

        return enterpriseAndChildrenVO;
    }

}