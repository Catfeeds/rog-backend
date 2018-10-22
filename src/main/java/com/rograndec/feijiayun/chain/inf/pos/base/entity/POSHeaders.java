package com.rograndec.feijiayun.chain.inf.pos.base.entity;

/**
 * 
 * @ClassName: POSHeaders   
 * @Description: POS接口请求头
 * @author yuting.li
 * @version 1.0 
 * @date 2017年10月8日 下午3:29:01
 */
public class POSHeaders {
	
	/**
	 * 请求头统一小写 Enumeration<String> header = request.getHeaderNames();获取头统一为小写，方便转为实体
	 */
	
	//机器mac地址
	private String mac;
	
	//客户端标示
	private String fromclient;
	
	//版本
	private String version;
	
	//企业id
	private String enterpriseid;
	
	//企业父id
	private String parentid;
	
	//用户票据
	private String usertoken;
	
	//用户账号
	private String loginaccount;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getFromclient() {
		return fromclient;
	}

	public void setFromclient(String fromclient) {
		this.fromclient = fromclient;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEnterpriseid() {
		return enterpriseid;
	}

	public void setEnterpriseid(String enterpriseid) {
		this.enterpriseid = enterpriseid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getUsertoken() {
		return usertoken;
	}

	public void setUsertoken(String usertoken) {
		this.usertoken = usertoken;
	}

	public String getLoginaccount() {
		return loginaccount;
	}

	public void setLoginaccount(String loginaccount) {
		this.loginaccount = loginaccount;
	}

	@Override
	public String toString() {
		return "POSHeaders [mac=" + mac + ", fromclient=" + fromclient + ", version=" + version + ", enterpriseid="
				+ enterpriseid + ", parentid=" + parentid + ", usertoken=" + usertoken + ", loginaccount="
				+ loginaccount + "]";
	}
	
}
