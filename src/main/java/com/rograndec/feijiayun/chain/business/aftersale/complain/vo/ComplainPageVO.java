package com.rograndec.feijiayun.chain.business.aftersale.complain.vo;

import com.rograndec.feijiayun.chain.business.aftersale.complain.constant.ComplainChannelType;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class ComplainPageVO implements Serializable {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 投诉日期
     */
    @ApiModelProperty(value = "投诉日期")
    private Date complainDate;

    @ApiModelProperty("后台用")
    private String complainDateStr;

    /**
     * 单号
     */
    @ApiModelProperty(value = "单号")
    private String code;

    /**
     * 受理人员名称
     */
    @ApiModelProperty(value = "受理人员名称")
    private String acceptManName;

    @ApiModelProperty(value = "投诉渠道")
    private Integer channel;

    /**
     * 投诉渠道名字（0-电话；1-邮箱；2-现场）
     */
    @ApiModelProperty(value = "投诉渠道名字（0-电话；1-邮箱；2-现场）")
    private String channelName;

    /**
     * 投诉人员
     */
    @ApiModelProperty(value = "投诉人员")
    private String name;

    /**
     * 投诉内容
     */
    @ApiModelProperty(value = "投诉内容")
    private String content;

    @ApiModelProperty("组织机构编码")
    private String enterpriseCode;

    @ApiModelProperty("组织机构名称")
    private String enterpriseName;


    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getComplainDate() {
        return complainDate;
    }

    public void setComplainDate(Date complainDate) {
        this.complainDate = complainDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAcceptManName() {
        return acceptManName;
    }

    public void setAcceptManName(String acceptManName) {
        this.acceptManName = acceptManName;
    }

    public String getChannelName() {
        if(channel == null){
            return "";
        }
        return ComplainChannelType.getValue(channel);
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }


    public String getComplainDateStr() {
        if(complainDate == null){
            return "";
        }
        return  DateUtils.DateToString(complainDate, DateStyle.YYYY_MM_DD);
    }

    public void setComplainDateStr(String complainDateStr) {
        this.complainDateStr = complainDateStr;
    }

    @Override
    public String toString() {
        return "ComplainPageVO{" +
                "id=" + id +
                ", complainDate=" + complainDate +
                ", code='" + code + '\'' +
                ", acceptManName='" + acceptManName + '\'' +
                ", channel=" + channel +
                ", channelName='" + channelName + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", enterpriseCode='" + enterpriseCode + '\'' +
                ", enterpriseName='" + enterpriseName + '\'' +
                '}';
    }
}
