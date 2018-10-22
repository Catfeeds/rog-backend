package com.rograndec.feijiayun.chain.common;

import org.springframework.http.HttpStatus;

/**
 * 版权：融贯资讯 <br/>
 * 作者：yong.chen@rogrand.com <br/>
 * 生成日期：2013年11月6日 <br/>
 * 描述：调用服务接口后返回的操作码 和 信息
 */
public enum SysCode {
    // 成功代码：000000
    // 失败代码：111111
    
    SUCCESS("000000", "操作成功"),
    SUCCESS_WITH_WARN("000001", "操作成功，但有警告"),
    SUCCESS_BUT_FORBID("000002", "操作成功，提醒并禁止下一步操作"),
    FAIL("111111", "操作失败,请联系客服人员"),
    FAIL_WITH_TIPS("111112", "操作失败,提示具体信息"),
    SYS_PARAM_ERROR("200100", "参数错误"),
    BIS_MEMBER_LEVE_ID_NOT_FIND("100201", "没有符合条件的数据"),
    FORBIDDEN(HttpStatus.FORBIDDEN.value()+"", "拒绝访问"),
    RGT_BIND_ERROR("111112", "绑定融贯通用户报错!请联系客服人员解决"),

	;
    
    private SysCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
    
    private String code;
    private String message;
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
