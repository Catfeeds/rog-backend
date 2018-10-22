package com.rograndec.feijiayun.chain.business.auth.register.entity;

import com.rograndec.feijiayun.chain.common.vo.UserVO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
 
/**
 * 
 * saas_login_log
 * 
 * 
 * @author zhaiwei
 * 
 * 2017-10-21
 */
public class LoginLog implements Serializable {
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
     * 登录来源
     */
    @ApiModelProperty(value = "登录来源")
    private String loginSrc;

    /**
     * 登录账号
     */
    @ApiModelProperty(value = "登录账号")
    private String loginAccount;

    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    private String password;

    /**
     * 登录时间
     */
    @ApiModelProperty(value = "登录时间")
    private Date loginTime;

    /**
     * 登录IP地址
     */
    @ApiModelProperty(value = "登录IP地址")
    private String loginIp;

    /**
     * 登录令牌
     */
    @ApiModelProperty(value = "登录令牌")
    private String loginToken;

    /**
     * 登录次数
     */
    @ApiModelProperty(value = "登录次数")
    private Integer loginCount;

    /**
     * 登出时间
     */
    @ApiModelProperty(value = "登出时间")
    private Date loginOutTime;

    public static LoginLog getLoginLog(UserVO userByLoginAccount,String ip,Integer loginCount,String loginSrc){

        LoginLog loginLog = new LoginLog();

        /**
         * 企业ID
         */
        loginLog.setEnterpriseId(userByLoginAccount.getEnterpriseId());

        /**
         * 上级企业ID
         */
        loginLog.setParentId(userByLoginAccount.getParentId());

        /**
         * 登录来源
         */
        loginLog.setLoginSrc(loginSrc);

        /**
         * 登录账号
         */
        loginLog.setLoginAccount(userByLoginAccount.getLoginAccount());

        /**
         * 登录密码
         */
        loginLog.setPassword(userByLoginAccount.getPassword());

        /**
         * 登录时间
         */
        loginLog.setLoginTime(new Date());

        /**
         * 登录IP地址
         */
        loginLog.setLoginIp(ip);

        /**
         * 登录令牌
         */
        loginLog.setLoginToken("00000");

        /**
         * 登录次数
         */
        loginLog.setLoginCount(loginCount);

        return loginLog;
    }

    /**
     * saas_login_log
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
     * 登录来源
     * @return login_src 登录来源
     */
    public String getLoginSrc() {
        return loginSrc;
    }

    /**
     * 登录来源
     * @param loginSrc 登录来源
     */
    public void setLoginSrc(String loginSrc) {
        this.loginSrc = loginSrc == null ? null : loginSrc.trim();
    }

    /**
     * 登录账号
     * @return login_account 登录账号
     */
    public String getLoginAccount() {
        return loginAccount;
    }

    /**
     * 登录账号
     * @param loginAccount 登录账号
     */
    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount == null ? null : loginAccount.trim();
    }

    /**
     * 登录密码
     * @return password 登录密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 登录密码
     * @param password 登录密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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
     * 登录IP地址
     * @return login_ip 登录IP地址
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 登录IP地址
     * @param loginIp 登录IP地址
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    /**
     * 登录令牌
     * @return login_token 登录令牌
     */
    public String getLoginToken() {
        return loginToken;
    }

    /**
     * 登录令牌
     * @param loginToken 登录令牌
     */
    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken == null ? null : loginToken.trim();
    }

    /**
     * 登录次数
     * @return login_count 登录次数
     */
    public Integer getLoginCount() {
        return loginCount;
    }

    /**
     * 登录次数
     * @param loginCount 登录次数
     */
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    /**
     * 登出时间
     * @return login_out_time 登出时间
     */
    public Date getLoginOutTime() {
        return loginOutTime;
    }

    /**
     * 登出时间
     * @param loginOutTime 登出时间
     */
    public void setLoginOutTime(Date loginOutTime) {
        this.loginOutTime = loginOutTime;
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
        sb.append(", loginSrc=").append(loginSrc);
        sb.append(", loginAccount=").append(loginAccount);
        sb.append(", password=").append(password);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", loginIp=").append(loginIp);
        sb.append(", loginToken=").append(loginToken);
        sb.append(", loginCount=").append(loginCount);
        sb.append(", loginOutTime=").append(loginOutTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}