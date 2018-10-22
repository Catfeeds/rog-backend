package com.rograndec.feijiayun.chain.inf.pos.prescription.vo;

import java.io.Serializable;

public class PrescriptionUserVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2017年10月9日 下午7:28:00 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键ID
     */
    private Long id;
    
    /**
     * 编码
     */
    private String code;

    /**
     * 员工名称
     */
    private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
