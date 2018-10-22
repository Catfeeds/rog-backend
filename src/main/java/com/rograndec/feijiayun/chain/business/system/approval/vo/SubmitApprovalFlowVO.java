package com.rograndec.feijiayun.chain.business.system.approval.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 提交审批entity
 */
public class SubmitApprovalFlowVO implements Serializable {

    /**
     * 发起机构ID
     */
    @NotNull(message = "发起机构ID不能为空")
    private Long startOrgId;

    /**
     * 发起机构名称
     */
    @NotNull(message = "发起机构名称不能为空")
    @Size(min = 1,message = "发起机构名称不能为空")
    private String startOrgName;

    /**
     * 发起人ID
     */
    @NotNull(message = "发起人ID不能为空")
    private Long starterId;

    /**
     * 发起人名称
     */
    @NotNull(message = "发起人名称不能为空")
    @Size(min = 1,message = "发起人名称不能为空")
    private String starterName;

    /**
     * 发起人企业类型（0-总部；1-自营店；2-加盟店；）
     */
    private Integer chainType;

    /**
     * 发起机构上级企业ID
     */
    @NotNull(message = "发起机构上级企业ID不能为空")
    private Long parentId;

    /**
     * 发起机构企业总部id
     */
    @NotNull(message = "发起机构企业总部id不能为空")
    private Long headquartersEnterpriseId;

    /**
     * 审批内容(0101-供货单位；0102-员工信息；0103-门店信息；0201-商品信息；0202-价格调整；0301-采购计划；0302-购进退出；0401-盘点；0402-其他入库；0403-药品销毁；0501-配进退出）
     */
    @NotNull(message = "审批内容不能为空")
    @Size(min = 1,message = "审批内容不能为空")
    private String content;
    

    /**
     * 根据审批内容不同具体关联数据ID，如审核内容表示商品信息，则该字段表示商品ID
     */
    @NotNull(message = "关联数据ID不能为空")
    private Long dataId;

    /**
     * 关联数据的编码
     */
    @NotNull(message = "关联数据的编码不能为空")
    @Size(min = 1,message = "关联数据的编码不能为空")
    private String code;

    /**
     * 关联数据的名称
     */
    @NotNull(message = "关联数据的名称不能为空")
    @Size(min = 1,message = "关联数据的名称不能为空")
    private String name;

    public SubmitApprovalFlowVO(Long startOrgId, String startOrgName, Long starterId, String starterName, Integer chainType, Long parentId, Long headquartersEnterpriseId, String content, Long dataId, String code, String name) {
        this.startOrgId = startOrgId;
        this.startOrgName = startOrgName;
        this.starterId = starterId;
        this.starterName = starterName;
        this.chainType = chainType;
        this.parentId = parentId;
        this.headquartersEnterpriseId = headquartersEnterpriseId;
        this.content = content;
        this.dataId = dataId;
        this.code = code;
        this.name = name;
    }

    public SubmitApprovalFlowVO() {
    }

    public Long getStartOrgId() {
        return startOrgId;
    }

    public void setStartOrgId(Long startOrgId) {
        this.startOrgId = startOrgId;
    }

    public String getStartOrgName() {
        return startOrgName;
    }

    public void setStartOrgName(String startOrgName) {
        this.startOrgName = startOrgName;
    }

    public Long getStarterId() {
        return starterId;
    }

    public void setStarterId(Long starterId) {
        this.starterId = starterId;
    }

    public String getStarterName() {
        return starterName;
    }

    public void setStarterName(String starterName) {
        this.starterName = starterName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getHeadquartersEnterpriseId() {
        return headquartersEnterpriseId;
    }

    public void setHeadquartersEnterpriseId(Long headquartersEnterpriseId) {
        this.headquartersEnterpriseId = headquartersEnterpriseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
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

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }
}