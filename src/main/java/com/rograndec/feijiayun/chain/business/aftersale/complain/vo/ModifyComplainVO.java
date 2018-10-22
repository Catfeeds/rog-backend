package com.rograndec.feijiayun.chain.business.aftersale.complain.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

public class ModifyComplainVO implements Serializable {

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

    /**
     * 受理人员ID
     */
    @ApiModelProperty(value = "受理人员ID")
    private Long acceptManId;

    /**
     * 投诉渠道（0-电话；1-邮箱；2-现场）
     */
    @ApiModelProperty(value = "投诉渠道（0-电话；1-邮箱；2-现场）")
    private Integer channel;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 性别（0-男；1-女；2-其它）
     */
    @ApiModelProperty(value = "性别（0-男；1-女；2-其它）")
    private Integer sex;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 手机
     */
    @ApiModelProperty(value = "手机")
    private String mobilePhone;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String telephone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 单位
     */
    @ApiModelProperty(value = "单位")
    private String company;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;

    /**
     * 商品ID
     */
    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    /**
     * 投诉内容
     */
    @ApiModelProperty(value = "投诉内容")
    private String content;

    /**
     * 投诉人意见或建议
     */
    @ApiModelProperty(value = "投诉人意见或建议")
    private String opinion;

    /**
     * 调查和评估
     */
    @ApiModelProperty(value = "调查和评估")
    private String investigateEstimate;

    /**
     * 调查时间
     */
    @ApiModelProperty(value = "调查时间")
    private Date investigateTime;

    /**
     * 调查人名称
     */
    @ApiModelProperty(value = "调查人名称")
    private String investigateManName;

    /**
     * 评估时间
     */
    @ApiModelProperty(value = "评估时间")
    private Date estimateTime;

    /**
     * 评估人名称
     */
    @ApiModelProperty(value = "评估人名称")
    private String estimateManName;

    /**
     * 处置措施
     */
    @ApiModelProperty(value = "处置措施")
    private String handleMeasures;

    /**
     * 处理时间
     */
    @ApiModelProperty(value = "处理时间")
    private Date handleTime;

    /**
     * 处理人名称
     */
    @ApiModelProperty(value = "处理人名称")
    private String handleManName;

    /**
     * 反馈和跟踪
     */
    @ApiModelProperty(value = "反馈和跟踪")
    private String feedbackFollow;

    /**
     * 反馈日期
     */
    @ApiModelProperty(value = "反馈日期")
    private Date feedbackDate;

    /**
     * 反馈人名称
     */
    @ApiModelProperty(value = "反馈人名称")
    private String feedbackManName;

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

    public Date getComplainDate() {
        return complainDate;
    }

    public void setComplainDate(Date complainDate) {
        this.complainDate = complainDate;
    }

    public Long getAcceptManId() {
        return acceptManId;
    }

    public void setAcceptManId(Long acceptManId) {
        this.acceptManId = acceptManId;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getInvestigateEstimate() {
        return investigateEstimate;
    }

    public void setInvestigateEstimate(String investigateEstimate) {
        this.investigateEstimate = investigateEstimate;
    }

    public Date getInvestigateTime() {
        return investigateTime;
    }

    public void setInvestigateTime(Date investigateTime) {
        this.investigateTime = investigateTime;
    }

    public String getInvestigateManName() {
        return investigateManName;
    }

    public void setInvestigateManName(String investigateManName) {
        this.investigateManName = investigateManName;
    }

    public Date getEstimateTime() {
        return estimateTime;
    }

    public void setEstimateTime(Date estimateTime) {
        this.estimateTime = estimateTime;
    }

    public String getEstimateManName() {
        return estimateManName;
    }

    public void setEstimateManName(String estimateManName) {
        this.estimateManName = estimateManName;
    }

    public String getHandleMeasures() {
        return handleMeasures;
    }

    public void setHandleMeasures(String handleMeasures) {
        this.handleMeasures = handleMeasures;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandleManName() {
        return handleManName;
    }

    public void setHandleManName(String handleManName) {
        this.handleManName = handleManName;
    }

    public String getFeedbackFollow() {
        return feedbackFollow;
    }

    public void setFeedbackFollow(String feedbackFollow) {
        this.feedbackFollow = feedbackFollow;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getFeedbackManName() {
        return feedbackManName;
    }

    public void setFeedbackManName(String feedbackManName) {
        this.feedbackManName = feedbackManName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CheckComplainVO[" +
                "id=" + id +
                ", complainDate=" + complainDate +
                ", acceptManId=" + acceptManId +
                ", channel=" + channel +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", address='" + address + '\'' +
                ", goodsId=" + goodsId +
                ", content='" + content + '\'' +
                ", opinion='" + opinion + '\'' +
                ", investigateEstimate='" + investigateEstimate + '\'' +
                ", investigateTime=" + investigateTime +
                ", investigateManName='" + investigateManName + '\'' +
                ", estimateTime=" + estimateTime +
                ", estimateManName='" + estimateManName + '\'' +
                ", handleMeasures='" + handleMeasures + '\'' +
                ", handleTime=" + handleTime +
                ", handleManName='" + handleManName + '\'' +
                ", feedbackFollow='" + feedbackFollow + '\'' +
                ", feedbackDate=" + feedbackDate +
                ", feedbackManName='" + feedbackManName + '\'' +
                ", remark='" + remark + '\'' +
                ']';
    }
}
