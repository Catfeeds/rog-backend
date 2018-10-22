package com.rograndec.feijiayun.chain.business.aftersale.complain.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
 
/**
 * 
 * saas_complain
 * 
 * 
 * @author Asze
 * 
 * 2017-10-16
 */
public class Complain implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private Long id;

    /**
     * 企业ID
     */
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    @ApiModelProperty(value = "上级企业ID")
    private Long parentId;

    /**
     * 单据编码
     */
    @ApiModelProperty(value = "单据编码")
    private String code;

    /**
     * 单据类型（5331）
     */
    @ApiModelProperty(value = "单据类型（5331）")
    private Integer orderType;

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
     * 受理人员编码
     */
    @ApiModelProperty(value = "受理人员编码")
    private String acceptManCode;

    /**
     * 受理人员名称
     */
    @ApiModelProperty(value = "受理人员名称")
    private String acceptManName;

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
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    private Long createrId;

    /**
     * 创建人编码
     */
    @ApiModelProperty(value = "创建人编码")
    private String createrCode;

    /**
     * 创建人名称
     */
    @ApiModelProperty(value = "创建人名称")
    private String createrName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 最后修改人ID
     */
    @ApiModelProperty(value = "最后修改人ID")
    private Long modifierId;

    /**
     * 最后修改人编码
     */
    @ApiModelProperty(value = "最后修改人编码")
    private String modifierCode;

    /**
     * 最后修改人名称
     */
    @ApiModelProperty(value = "最后修改人名称")
    private String modifierName;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * saas_complain
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
     * 企业ID
     * @return enterprise_id 企业ID
     */
    public Long getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 上级企业ID
     * @return parent_id 上级企业ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 上级企业ID
     * @param parentId 上级企业ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 单据编码
     * @return code 单据编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 单据编码
     * @param code 单据编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 单据类型（5331）
     * @return order_type 单据类型（5331）
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 单据类型（5331）
     * @param orderType 单据类型（5331）
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 投诉日期
     * @return complain_date 投诉日期
     */
    public Date getComplainDate() {
        return complainDate;
    }

    /**
     * 投诉日期
     * @param complainDate 投诉日期
     */
    public void setComplainDate(Date complainDate) {
        this.complainDate = complainDate;
    }

    /**
     * 受理人员ID
     * @return accept_man_id 受理人员ID
     */
    public Long getAcceptManId() {
        return acceptManId;
    }

    /**
     * 受理人员ID
     * @param acceptManId 受理人员ID
     */
    public void setAcceptManId(Long acceptManId) {
        this.acceptManId = acceptManId;
    }

    /**
     * 受理人员编码
     * @return accept_man_code 受理人员编码
     */
    public String getAcceptManCode() {
        return acceptManCode;
    }

    /**
     * 受理人员编码
     * @param acceptManCode 受理人员编码
     */
    public void setAcceptManCode(String acceptManCode) {
        this.acceptManCode = acceptManCode == null ? null : acceptManCode.trim();
    }

    /**
     * 受理人员名称
     * @return accept_man_name 受理人员名称
     */
    public String getAcceptManName() {
        return acceptManName;
    }

    /**
     * 受理人员名称
     * @param acceptManName 受理人员名称
     */
    public void setAcceptManName(String acceptManName) {
        this.acceptManName = acceptManName == null ? null : acceptManName.trim();
    }

    /**
     * 投诉渠道（0-电话；1-邮箱；2-现场）
     * @return channel 投诉渠道（0-电话；1-邮箱；2-现场）
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     * 投诉渠道（0-电话；1-邮箱；2-现场）
     * @param channel 投诉渠道（0-电话；1-邮箱；2-现场）
     */
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    /**
     * 姓名
     * @return name 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 姓名
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 性别（0-男；1-女；2-其它）
     * @return sex 性别（0-男；1-女；2-其它）
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 性别（0-男；1-女；2-其它）
     * @param sex 性别（0-男；1-女；2-其它）
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 年龄
     * @return age 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 年龄
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 手机
     * @return mobile_phone 手机
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 手机
     * @param mobilePhone 手机
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    /**
     * 电话
     * @return telephone 电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 电话
     * @param telephone 电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * 邮箱
     * @return email 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 单位
     * @return company 单位
     */
    public String getCompany() {
        return company;
    }

    /**
     * 单位
     * @param company 单位
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * 地址
     * @return address 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 商品ID
     * @return goods_id 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 商品ID
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 投诉内容
     * @return content 投诉内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 投诉内容
     * @param content 投诉内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 投诉人意见或建议
     * @return opinion 投诉人意见或建议
     */
    public String getOpinion() {
        return opinion;
    }

    /**
     * 投诉人意见或建议
     * @param opinion 投诉人意见或建议
     */
    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
    }

    /**
     * 调查和评估
     * @return investigate_estimate 调查和评估
     */
    public String getInvestigateEstimate() {
        return investigateEstimate;
    }

    /**
     * 调查和评估
     * @param investigateEstimate 调查和评估
     */
    public void setInvestigateEstimate(String investigateEstimate) {
        this.investigateEstimate = investigateEstimate == null ? null : investigateEstimate.trim();
    }

    /**
     * 调查时间
     * @return investigate_time 调查时间
     */
    public Date getInvestigateTime() {
        return investigateTime;
    }

    /**
     * 调查时间
     * @param investigateTime 调查时间
     */
    public void setInvestigateTime(Date investigateTime) {
        this.investigateTime = investigateTime;
    }

    /**
     * 调查人名称
     * @return investigate_man_name 调查人名称
     */
    public String getInvestigateManName() {
        return investigateManName;
    }

    /**
     * 调查人名称
     * @param investigateManName 调查人名称
     */
    public void setInvestigateManName(String investigateManName) {
        this.investigateManName = investigateManName == null ? null : investigateManName.trim();
    }

    /**
     * 评估时间
     * @return estimate_time 评估时间
     */
    public Date getEstimateTime() {
        return estimateTime;
    }

    /**
     * 评估时间
     * @param estimateTime 评估时间
     */
    public void setEstimateTime(Date estimateTime) {
        this.estimateTime = estimateTime;
    }

    /**
     * 评估人名称
     * @return estimate_man_name 评估人名称
     */
    public String getEstimateManName() {
        return estimateManName;
    }

    /**
     * 评估人名称
     * @param estimateManName 评估人名称
     */
    public void setEstimateManName(String estimateManName) {
        this.estimateManName = estimateManName == null ? null : estimateManName.trim();
    }

    /**
     * 处置措施
     * @return handle_measures 处置措施
     */
    public String getHandleMeasures() {
        return handleMeasures;
    }

    /**
     * 处置措施
     * @param handleMeasures 处置措施
     */
    public void setHandleMeasures(String handleMeasures) {
        this.handleMeasures = handleMeasures == null ? null : handleMeasures.trim();
    }

    /**
     * 处理时间
     * @return handle_time 处理时间
     */
    public Date getHandleTime() {
        return handleTime;
    }

    /**
     * 处理时间
     * @param handleTime 处理时间
     */
    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    /**
     * 处理人名称
     * @return handle_man_name 处理人名称
     */
    public String getHandleManName() {
        return handleManName;
    }

    /**
     * 处理人名称
     * @param handleManName 处理人名称
     */
    public void setHandleManName(String handleManName) {
        this.handleManName = handleManName == null ? null : handleManName.trim();
    }

    /**
     * 反馈和跟踪
     * @return feedback_follow 反馈和跟踪
     */
    public String getFeedbackFollow() {
        return feedbackFollow;
    }

    /**
     * 反馈和跟踪
     * @param feedbackFollow 反馈和跟踪
     */
    public void setFeedbackFollow(String feedbackFollow) {
        this.feedbackFollow = feedbackFollow == null ? null : feedbackFollow.trim();
    }

    /**
     * 反馈日期
     * @return feedback_date 反馈日期
     */
    public Date getFeedbackDate() {
        return feedbackDate;
    }

    /**
     * 反馈日期
     * @param feedbackDate 反馈日期
     */
    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    /**
     * 反馈人名称
     * @return feedback_man_name 反馈人名称
     */
    public String getFeedbackManName() {
        return feedbackManName;
    }

    /**
     * 反馈人名称
     * @param feedbackManName 反馈人名称
     */
    public void setFeedbackManName(String feedbackManName) {
        this.feedbackManName = feedbackManName == null ? null : feedbackManName.trim();
    }

    /**
     * 状态
     * @return status 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 创建人ID
     * @return creater_id 创建人ID
     */
    public Long getCreaterId() {
        return createrId;
    }

    /**
     * 创建人ID
     * @param createrId 创建人ID
     */
    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    /**
     * 创建人编码
     * @return creater_code 创建人编码
     */
    public String getCreaterCode() {
        return createrCode;
    }

    /**
     * 创建人编码
     * @param createrCode 创建人编码
     */
    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode == null ? null : createrCode.trim();
    }

    /**
     * 创建人名称
     * @return creater_name 创建人名称
     */
    public String getCreaterName() {
        return createrName;
    }

    /**
     * 创建人名称
     * @param createrName 创建人名称
     */
    public void setCreaterName(String createrName) {
        this.createrName = createrName == null ? null : createrName.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 最后修改人ID
     * @return modifier_id 最后修改人ID
     */
    public Long getModifierId() {
        return modifierId;
    }

    /**
     * 最后修改人ID
     * @param modifierId 最后修改人ID
     */
    public void setModifierId(Long modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * 最后修改人编码
     * @return modifier_code 最后修改人编码
     */
    public String getModifierCode() {
        return modifierCode;
    }

    /**
     * 最后修改人编码
     * @param modifierCode 最后修改人编码
     */
    public void setModifierCode(String modifierCode) {
        this.modifierCode = modifierCode == null ? null : modifierCode.trim();
    }

    /**
     * 最后修改人名称
     * @return modifier_name 最后修改人名称
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 最后修改人名称
     * @param modifierName 最后修改人名称
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    /**
     * 更新时间
     * @return update_time 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
        sb.append(", parentId=").append(parentId);
        sb.append(", code=").append(code);
        sb.append(", orderType=").append(orderType);
        sb.append(", complainDate=").append(complainDate);
        sb.append(", acceptManId=").append(acceptManId);
        sb.append(", acceptManCode=").append(acceptManCode);
        sb.append(", acceptManName=").append(acceptManName);
        sb.append(", channel=").append(channel);
        sb.append(", name=").append(name);
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", mobilePhone=").append(mobilePhone);
        sb.append(", telephone=").append(telephone);
        sb.append(", email=").append(email);
        sb.append(", company=").append(company);
        sb.append(", address=").append(address);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", content=").append(content);
        sb.append(", opinion=").append(opinion);
        sb.append(", investigateEstimate=").append(investigateEstimate);
        sb.append(", investigateTime=").append(investigateTime);
        sb.append(", investigateManName=").append(investigateManName);
        sb.append(", estimateTime=").append(estimateTime);
        sb.append(", estimateManName=").append(estimateManName);
        sb.append(", handleMeasures=").append(handleMeasures);
        sb.append(", handleTime=").append(handleTime);
        sb.append(", handleManName=").append(handleManName);
        sb.append(", feedbackFollow=").append(feedbackFollow);
        sb.append(", feedbackDate=").append(feedbackDate);
        sb.append(", feedbackManName=").append(feedbackManName);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifierId=").append(modifierId);
        sb.append(", modifierCode=").append(modifierCode);
        sb.append(", modifierName=").append(modifierName);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}