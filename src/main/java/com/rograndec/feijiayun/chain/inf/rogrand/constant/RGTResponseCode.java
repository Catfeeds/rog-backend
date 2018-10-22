package com.rograndec.feijiayun.chain.inf.rogrand.constant;

import java.util.Arrays;

/**
 * 功能描述：系统级别错误码定义
 * Created by ST on 2017/10/31 11:54
 */

public enum RGTResponseCode {
    SUCCESS("000000","成功"),
    USERNAME_OR_PWD_ERROR("000006","用户名或密码不正确"),
    SYS_ERROR("10001","系统错误"),
    SERVER_PAUSE("10002","服务暂停"),
    REMOTE_SERVER_ERROR("10003","远程服务错误"),
    IP_LIMITATION_ERROR("10004","IP限制不能请求该资源"),
    SYSTEM_BUSY("10005","系统繁忙"),
    TIMEOUTS("10006","任务超时"),
    ILLEGAL_REQUEST("10007","非法请求"),
    NONSUPPORT_METHOD("10008","请求的HTTP METHOD不支持，请检查是否选择了正确的POST/GET方式"),
    MISSING_REQUIRED_PARAMETERS("10010","缺失必选参数"),
    PARAMETERS_ERROR("10011","参数错误"),
    ILLEGAL_PARAMETERS("10012","参数值非法"),

    PARAMETER_EXCEEDS_LIMIT("10013","参数值的长度超过限制"),
    ILLEGAL_APPLICATION("10014","不合法的应用"),
    ILLEGAL_USER("10015","不合法的用户"),
    CAN_NOT_OPERATION("10016","账号、IP或应用非法，暂时无法完成此操作"),
    DATABASE_ERROR("10017","数据库错误，请联系系统管理员"),
    VERSION_ERROR("10018","版本号错误"),

    APPID_IS_NULL("20001","APP_ID参数为空"),
    APP_SECRET_IS_NULL("20002","APP Secret参数为空"),
    VERIFIER_ERROR("20101","Verifier错误"),
    SIGNATURE_INVALID("20102","签名值不合法"),
    RECORD_NONEXISTENCE("20103","记录不存在"),
    RECORD_EXISTING("20104","记录已存在"),

    INVALID_TICKET("20201","无效的 Ticket"),
    AUTHENTICATION_FAILURE("20202","认证失败"),
    REGISTRATION_FAILED("20203","用户注册失败"),
    USER_NOT_EXIST("20301","用户不存在"),
    EMAIL_USED("20302","该Email 已经被使用"),
    PHONE_USED("20303","该手机号已经被使用"),
    USER_HAS_BIND_PHONE("20304","该用户已经绑定手机"),
    USERNAME_EXIST("20305","该用户名已存在"),
    USER_LOGOUT_FAILED("20306","用户注销失败"),
    LICENSENO_EXIST("20307","证件号已存在"),
    USERNAME_OR_PWD_ERROR1("20401","用户名或密码不正确"),
    NEED_CODE("20402","需要输入验证码"),
    CODE_ERROR("20403","验证码错误"),

    PASSWORD_ERROR("20404","密码错误"),
    AUTH_CODE_USED("20501","Auth code 已经被使用"),
    AUTH_CODE_EXPIRE("20502","Auth code 已经过期"),
    AUTH_CODE_ILLEGAL("20503","Auth code不合法"),
    AUTHENTICATION_FAILED("20504","验证失败"),
    TOKEN_EXPIRE("21001","Token过期"),
    VALID_CODE_STATUS("21101","无效状态码")
            ;


    private String code;
    private String name;

    RGTResponseCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (RGTResponseCode c : RGTResponseCode.values()) {
            if (c.getCode().equals(code)) {
                return c.getName();
            }
        }
        return "融贯接口异常";
    }

    public static String getCode(String name) {
        for (RGTResponseCode c : RGTResponseCode.values()) {
            if (c.getName().equals(name)) {
                return c.getCode();
            }
        }
        return "-1";
    }

    public static boolean isSuccess(String code){

       return Arrays.stream(RGTResponseCode.values()).map(
                rgt -> rgt.code.equals(code)
        ).findFirst().orElse(null);
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}