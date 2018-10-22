package com.rograndec.feijiayun.chain.common.constant;

/**
 * 标识（0-非必填；1-必填）
 * @ClassName: MustInput
 * @Description: TODO(描述该类做什么)
 * @author zhaiwei
 * @version 1.0 
 * @date 2017年8月30日 上午11:37:49
 */
public enum MustInput {

	OPTIONAL(0,"非必填"),
	MUST(1,"必填");

	private int code;
    private String name;

    private MustInput(int code, String name) {
		this.code = code;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setType(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
