package com.rograndec.feijiayun.chain.business.storage.split.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 版权：融贯资讯 <br/>
 * 作者：xingjian.lan@rograndec.com <br/>
 * 生成日期：2017/9/30 <br/>
 * 描述：商品拆零单据列表
 */
public class SplitOrderPageVO implements Serializable {
    private static final long serialVersionUID = -4661454252088717275L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value="拆零主键id")
    private Long id;

    /**
     * 日期
     */
    @ApiModelProperty(value="拆零日期")
    private String splitDate;

    /**
     * 拆零单号
     */
    @ApiModelProperty(value="拆零单号")
    private String code;

    /**
     * 分拆人员ID
     */
    @ApiModelProperty(value="分拆人员ID")
    private Integer splitManId;

    /**
     * 分拆人员名称
     */
    @ApiModelProperty(value="分拆人员名称")
    private String splitManCode;

    /**
     * 分拆人员名称
     */
    @ApiModelProperty(value="分拆人员名称")
    private String splitManName;

    /**
     * 复核人员id
     */
    @ApiModelProperty(value = "复核人员ID")
    private Integer auditManId;

    /**
     * 复核人员编码
     */
    @ApiModelProperty(value = "复核人员编码")
    private String auditManCode;

    /**
     * 复核人员名称
     */
    @ApiModelProperty(value = "复核人员名称")
    private String auditManName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSplitDate() {
        return splitDate;
    }

    public void setSplitDate(String splitDate) {
        this.splitDate = splitDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getSplitManId() {
        return splitManId;
    }

    public void setSplitManId(Integer splitManId) {
        this.splitManId = splitManId;
    }

    public String getSplitManCode() {
        return splitManCode;
    }

    public void setSplitManCode(String splitManCode) {
        this.splitManCode = splitManCode;
    }

    public String getSplitManName() {
        return splitManName;
    }

    public void setSplitManName(String splitManName) {
        this.splitManName = splitManName;
    }

    public Integer getAuditManId() {
        return auditManId;
    }

    public void setAuditManId(Integer auditManId) {
        this.auditManId = auditManId;
    }

    public String getAuditManCode() {
        return auditManCode;
    }

    public void setAuditManCode(String auditManCode) {
        this.auditManCode = auditManCode;
    }

    public String getAuditManName() {
        return auditManName;
    }

    public void setAuditManName(String auditManName) {
        this.auditManName = auditManName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SplitOrderPageVO{" +
                "id=" + id +
                ", splitDate='" + splitDate + '\'' +
                ", code='" + code + '\'' +
                ", splitManId=" + splitManId +
                ", splitManCode='" + splitManCode + '\'' +
                ", splitManName='" + splitManName + '\'' +
                ", auditManId=" + auditManId +
                ", auditManCode='" + auditManCode + '\'' +
                ", auditManName='" + auditManName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
