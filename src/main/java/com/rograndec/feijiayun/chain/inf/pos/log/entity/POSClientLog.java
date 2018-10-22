package com.rograndec.feijiayun.chain.inf.pos.log.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_client_log
 * 
 * 
 * @author liyut
 * 
 * 2017-10-08
 */
public class POSClientLog implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 类型 0 POS 1 浏览器壳
     */
    @ApiModelProperty(value = "类型 0 POS 1 浏览器壳")
    private Integer clientType;

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private Long enterpriseId;

    /**
     * 网卡
     */
    @ApiModelProperty(value = "网卡")
    private String pcMac;

    /**
     * 端末编号
     */
    @ApiModelProperty(value = "端末编号")
    private String terminalCode;

    /**
     * 日志时间
     */
    @ApiModelProperty(value = "日志时间")
    private Date logDate;

    /**
     * 上传时间
     */
    @ApiModelProperty(value = "上传时间")
    private Date uploadDate;

    /**
     * 日志内容
     */
    @ApiModelProperty(value = "日志内容")
    private String content;

    /**
     * saas_client_log
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     * @return id id
     */
    public Long getId() {
        return id;
    }

    /**
     * id
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 类型 0 POS 1 浏览器壳
     * @return client_type 类型 0 POS 1 浏览器壳
     */
    public Integer getClientType() {
        return clientType;
    }

    /**
     * 类型 0 POS 1 浏览器壳
     * @param clientType 类型 0 POS 1 浏览器壳
     */
    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

    public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/**
     * 网卡
     * @return pc_mac 网卡
     */
    public String getPcMac() {
        return pcMac;
    }

    /**
     * 网卡
     * @param pcMac 网卡
     */
    public void setPcMac(String pcMac) {
        this.pcMac = pcMac == null ? null : pcMac.trim();
    }

    /**
     * 端末编号
     * @return terminal_code 端末编号
     */
    public String getTerminalCode() {
        return terminalCode;
    }

    /**
     * 端末编号
     * @param terminalCode 端末编号
     */
    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode == null ? null : terminalCode.trim();
    }

    /**
     * 日志时间
     * @return log_date 日志时间
     */
    public Date getLogDate() {
        return logDate;
    }

    /**
     * 日志时间
     * @param logDate 日志时间
     */
    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    /**
     * 上传时间
     * @return upload_date 上传时间
     */
    public Date getUploadDate() {
        return uploadDate;
    }

    /**
     * 上传时间
     * @param uploadDate 上传时间
     */
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    /**
     * 日志内容
     * @return content 日志内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 日志内容
     * @param content 日志内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
        sb.append(", clientType=").append(clientType);
        sb.append(", enterpriseId=").append(enterpriseId);
        sb.append(", pcMac=").append(pcMac);
        sb.append(", terminalCode=").append(terminalCode);
        sb.append(", logDate=").append(logDate);
        sb.append(", uploadDate=").append(uploadDate);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}