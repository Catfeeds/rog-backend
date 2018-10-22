package com.rograndec.feijiayun.chain.business.report.finance.tax.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class GeneralLedgerResultVO implements Serializable{

	/**
	 * @Description: TODO(描述funcion功能)
	 * author liuqun
	 * @date 2018年1月2日 下午6:01:25 
	 * @version 1.0   
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "年月")
	private String years;
	
	private List<GeneralLedgerNewResultVO> detailList;

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public List<GeneralLedgerNewResultVO> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<GeneralLedgerNewResultVO> detailList) {
		this.detailList = detailList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
