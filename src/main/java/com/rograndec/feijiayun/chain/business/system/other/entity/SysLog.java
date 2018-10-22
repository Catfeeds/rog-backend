package com.rograndec.feijiayun.chain.business.system.other.entity;

import java.io.Serializable;
import java.util.Date;

public class SysLog implements Serializable {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 账号
     */
    private String account;

    /**
     * 员工ID
     */
    private Long employeeId;

    /**
     * 员工编码
     */
    private String employeeCode;

    /**
     * 员工名称
     */
    private String employeeName;

    /**
     * 登录地点
     */
    private String loginPlace;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 分类名称
     */
    private String className;

    /**
     * 功能名称
     */
    private String actionName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人ID
     */
    private Long createrId;

    /**
     * 创建人编码
     */
    private String createrCode;

    /**
     * 创建人名称
     */
    private String createrName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * saas_sys_log
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

    public Long getEnterpriseId() {
        return enterpriseId;
    }

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
     * 登录时间
     * @return login_time 登录时间
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * 登录时间
     * @param loginTime 登录时间
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 账号
     * @return account 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 账号
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * 员工ID
     * @return employee_id 员工ID
     */
    public Long getEmployeeId() {
        return employeeId;
    }

    /**
     * 员工ID
     * @param employeeId 员工ID
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    /**
     * 员工名称
     * @return employee_name 员工名称
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * 员工名称
     * @param employeeName 员工名称
     */
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName == null ? null : employeeName.trim();
    }

    /**
     * 登录地点
     * @return login_place 登录地点
     */
    public String getLoginPlace() {
        return loginPlace;
    }

    /**
     * 登录地点
     * @param loginPlace 登录地点
     */
    public void setLoginPlace(String loginPlace) {
        this.loginPlace = loginPlace == null ? null : loginPlace.trim();
    }

    /**
     * IP地址
     * @return ip IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * IP地址
     * @param ip IP地址
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 功能名称
     * @return action_name 功能名称
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * 功能名称
     * @param actionName 功能名称
     */
    public void setActionName(String actionName) {
        this.actionName = actionName == null ? null : actionName.trim();
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
        sb.append(", loginTime=").append(loginTime);
        sb.append(", account=").append(account);
        sb.append(", employeeId=").append(employeeId);
        sb.append(", employeeCode=").append(employeeCode);
        sb.append(", employeeName=").append(employeeName);
        sb.append(", loginPlace=").append(loginPlace);
        sb.append(", ip=").append(ip);
        sb.append(", actionName=").append(actionName);
        sb.append(", remark=").append(remark);
        sb.append(", createrId=").append(createrId);
        sb.append(", createrCode=").append(createrCode);
        sb.append(", createrName=").append(createrName);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}