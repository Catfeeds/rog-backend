package com.rograndec.feijiayun.chain.business.auth.login.vo;

import com.rograndec.feijiayun.chain.common.vo.BaseTreeVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

public class UserMenusVO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 企业ID
     */
    private Long enterpriseId;

    /**
     * 企业编码
     */
    private String enterpriseCode;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 上级企业ID
     */
    private Long parentId;

    /**
     * 上级企业编码
     */
    private String parentCode;

    /**
     * 上级企业名称
     */
    private String parentName;

    /**
     * 企业类型：0-总部；1-自营店；2-加盟店
     */
    private Integer chainType;

    /**
     * 用户编码
     */
    private String userCode;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 登录账号
     */
    private String loginAccount;

    /**
     * 菜单
     */
    private List<BaseTreeVO> menus = new ArrayList<>();

    public static UserMenusVO getUserMenusVO(UserVO userVO){

        UserMenusVO userMenusVO = new UserMenusVO();

        /**
         * 用户ID
         */
        userMenusVO.setUserId(userVO.getUserId());
        /**
         * 企业ID
         */
        userMenusVO.setEnterpriseId(userVO.getEnterpriseId());

        /**
         * 企业编码
         */
        userMenusVO.setEnterpriseCode(userVO.getEnterpriseCode());

        /**
         * 企业名称
         */
        userMenusVO.setEnterpriseName(userVO.getEnterpriseName());

        /**
         * 上级企业ID
         */
        userMenusVO.setParentId(userVO.getParentId());

        /**
         * 上级企业编码
         */
        userMenusVO.setParentCode(userVO.getParentCode());

        /**
         * 上级企业名称
         */
        userMenusVO.setParentName(userVO.getParentName());

        /**
         * 企业类型：0-总部；1-自营店；2-加盟店
         */
        userMenusVO.setChainType(userVO.getChainType());

        /**
         * 用户编码
         */
        userMenusVO.setUserCode(userVO.getUserCode());

        /**
         * 用户名称
         */
        userMenusVO.setUserName(userVO.getUserName());

        /**
         * 登录账号
         */
        userMenusVO.setLoginAccount(userVO.getLoginAccount());

        return userMenusVO;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Integer getChainType() {
        return chainType;
    }

    public void setChainType(Integer chainType) {
        this.chainType = chainType;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public List<BaseTreeVO> getMenus() {
        return menus;
    }

    public void setMenus(List<BaseTreeVO> menus) {
        this.menus = menus;
    }
}
