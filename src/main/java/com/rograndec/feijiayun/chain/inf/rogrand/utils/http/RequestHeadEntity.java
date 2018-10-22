package com.rograndec.feijiayun.chain.inf.rogrand.utils.http;

/**
 * 功能描述：
 * Created by ST on 2017/10/31 14:43
 */

public class RequestHeadEntity {


    /**
     * 接口编号 非必填
     */
    private String method;

    /**
     * 请求唯一流水号 非必填
     */
    private String serialNumber;
    /**
     * 接口版本号；定值为1.0
     */
    private String verison;

    /**
     * 标识用户登录状态的令牌
     */
    private String tokenId;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 应用密码
     */
    private String appSecret;

    private String sourceCode;

    private String terminalstate;

    private String imei;

    private String sysVersion;

    private String appType;

    private String macAddress;

    private String appVersion;

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getVerison() {
        return verison;
    }

    public void setVerison(String verison) {
        this.verison = verison;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    @Override
    public String toString() {
        return "RequestHeadEntity{" +
                "method='" + method + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", verison='" + verison + '\'' +
                ", tokenId='" + tokenId + '\'' +
                ", appId='" + appId + '\'' +
                ", appSecret='" + appSecret + '\'' +
                '}';
    }

    public String getTerminalstate() {
        return terminalstate;
    }

    public void setTerminalstate(String terminalstate) {
        this.terminalstate = terminalstate;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSysVersion() {
        return sysVersion;
    }

    public void setSysVersion(String sysVersion) {
        this.sysVersion = sysVersion;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}