package com.rograndec.feijiayun.chain.business.auth.login.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @ClassName: SysActionVO  
 * @Description: 系统功能对象
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月23日 下午4:31:51  
 *
 */
public class MenuVO implements Serializable{

	/**
	 * @Fields serialVersionUID :
	 */
	private static final long serialVersionUID = -3622620800201948518L;

	/**
     * 主键ID
     */
    private Long actionId;

    /**
     * 功能编码
     */
    private String actionCode;

    /**
     * 功能名称
     */
    private String actionName;

	/**
	 * url
	 */
	private String url;

    /**
     * 角色集合
     */
    private Set<MenuVO> menus=new HashSet<MenuVO>();

	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set<MenuVO> getMenus() {
		return menus;
	}

	public void setMenus(Set<MenuVO> menus) {
		this.menus = menus;
	}
}
