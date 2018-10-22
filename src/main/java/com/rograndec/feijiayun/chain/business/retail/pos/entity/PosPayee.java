package com.rograndec.feijiayun.chain.business.retail.pos.entity;

import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosTeamVO;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
 
/**
 * 
 * saas_pos_payee
 * 
 * 
 * @author liyut
 * 
 * 2017-09-18
 */
public class PosPayee implements Serializable {
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
     * 收款人员ID
     */
    @ApiModelProperty(value = "收款人员ID")
    private Long payeeId;

    /**
     * 收款人编码
     */
    @ApiModelProperty(value = "收款人编码")
    private String payeeCode;

    /**
     * 收款人名称
     */
    @ApiModelProperty(value = "收款人名称")
    private String payeeName;

    /**
     * 班组ID
     */
    @ApiModelProperty(value = "班组ID")
    private Long teamId;

    /**
     * 班组编码
     */
    @ApiModelProperty(value = "班组编码")
    private String teamCode;

    /**
     * 班组名称
     */
    @ApiModelProperty(value = "班组名称")
    private String teamName;

    /**
     * 状态（0-禁用；1-启用）
     */
    @ApiModelProperty(value = "状态（0-禁用；1-启用）")
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

    public static PosPayee getPosPayee(User user, PosTeamVO defultPosTeam){

        PosPayee posPayee = new PosPayee();

        /**
         * 企业ID
         */
        posPayee.setEnterpriseId(user.getEnterpriseId());

        /**
         * 上级企业ID
         */

        posPayee.setParentId(user.getParentId());

        /**
         * 收款人员ID
         */
        posPayee.setPayeeId(user.getId());

        /**
         * 收款人编码
         */
        posPayee.setPayeeCode(user.getCode());

        /**
         * 收款人名称
         */
        posPayee.setPayeeName(user.getName());

        /**
         * 班组ID
         */
        posPayee.setTeamId(defultPosTeam.getId());

        /**
         * 班组编码
         */
        posPayee.setTeamCode(defultPosTeam.getCode());

        /**
         * 班组名称
         */
        posPayee.setTeamName(defultPosTeam.getName());

        /**
         * 状态（0-禁用；1-启用）
         */
        posPayee.setStatus(EnableStatus.ENABLE.getStatus());

        posPayee.setCreaterId(user.getCreaterId());
        posPayee.setCreaterCode(user.getCreaterCode());
        posPayee.setCreaterName(user.getCreaterName());
        posPayee.setCreateTime(new Date());
        posPayee.setModifierId(user.getCreaterId());
        posPayee.setModifierCode(user.getCreaterCode());
        posPayee.setModifierName(user.getCreaterName());
        posPayee.setUpdateTime(new Date());

        return posPayee;
    }
    /**
     * saas_pos_payee
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
     * 收款人员ID
     * @return payee_id 收款人员ID
     */
    public Long getPayeeId() {
        return payeeId;
    }

    /**
     * 收款人员ID
     * @param payeeId 收款人员ID
     */
    public void setPayeeId(Long payeeId) {
        this.payeeId = payeeId;
    }

    /**
     * 收款人编码
     * @return payee_code 收款人编码
     */
    public String getPayeeCode() {
        return payeeCode;
    }

    /**
     * 收款人编码
     * @param payeeCode 收款人编码
     */
    public void setPayeeCode(String payeeCode) {
        this.payeeCode = payeeCode == null ? null : payeeCode.trim();
    }

    /**
     * 收款人名称
     * @return payee_name 收款人名称
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     * 收款人名称
     * @param payeeName 收款人名称
     */
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName == null ? null : payeeName.trim();
    }

    /**
     * 班组ID
     * @return team_id 班组ID
     */
    public Long getTeamId() {
        return teamId;
    }

    /**
     * 班组ID
     * @param teamId 班组ID
     */
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    /**
     * 班组编码
     * @return team_code 班组编码
     */
    public String getTeamCode() {
        return teamCode;
    }

    /**
     * 班组编码
     * @param teamCode 班组编码
     */
    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode == null ? null : teamCode.trim();
    }

    /**
     * 班组名称
     * @return tean_name 班组名称
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * 班组名称
     * @param teamName 班组名称
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
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
        sb.append(", payeeId=").append(payeeId);
        sb.append(", payeeCode=").append(payeeCode);
        sb.append(", payeeName=").append(payeeName);
        sb.append(", teamId=").append(teamId);
        sb.append(", teamCode=").append(teamCode);
        sb.append(", teamName=").append(teamName);
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