package com.rograndec.feijiayun.chain.business.goods.httpInf.entity;

import java.io.Serializable;



public class MphNormRelations implements Serializable {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 683126118571339197L;

	private String saasGoodId;// SAAS平台商品唯一标识
	private Integer nrId;
	private String nrCommonName;// 药品名
	private String nrLicenseNo;// 批准文号
	private String nrProduceUnit;// 生产厂家
	private String nrSpecifications;// 规格
	private String gdfName;// 剂型
	private String nrBarCode;// 条形码
	private String nrUnit;// 单位
	private Integer nrOtc;// otc类型 是否处方药
	
	private NormDrug normDrug;

	public String getSaasGoodId() {
		return saasGoodId;
	}

	public void setSaasGoodId(String saasGoodId) {
		this.saasGoodId = saasGoodId;
	}

	public Integer getNrId() {
		return nrId;
	}

	public void setNrId(Integer nrId) {
		this.nrId = nrId;
	}

	public String getNrCommonName() {
		return nrCommonName;
	}

	public void setNrCommonName(String nrCommonName) {
		this.nrCommonName = nrCommonName;
	}

	public String getNrLicenseNo() {
		return nrLicenseNo;
	}

	public void setNrLicenseNo(String nrLicenseNo) {
		this.nrLicenseNo = nrLicenseNo;
	}

	public String getNrProduceUnit() {
		return nrProduceUnit;
	}

	public void setNrProduceUnit(String nrProduceUnit) {
		this.nrProduceUnit = nrProduceUnit;
	}

	public String getNrSpecifications() {
		return nrSpecifications;
	}

	public void setNrSpecifications(String nrSpecifications) {
		this.nrSpecifications = nrSpecifications;
	}

	public String getGdfName() {
		return gdfName;
	}

	public void setGdfName(String gdfName) {
		this.gdfName = gdfName;
	}

	public String getNrBarCode() {
		return nrBarCode;
	}

	public void setNrBarCode(String nrBarCode) {
		this.nrBarCode = nrBarCode;
	}

	public String getNrUnit() {
		return nrUnit;
	}

	public void setNrUnit(String nrUnit) {
		this.nrUnit = nrUnit;
	}

	public Integer getNrOtc() {
		return nrOtc;
	}

	public void setNrOtc(Integer nrOtc) {
		this.nrOtc = nrOtc;
	}

	public NormDrug getNormDrug() {
		return normDrug;
	}

	public void setNormDrug(NormDrug normDrug) {
		this.normDrug = normDrug;
	}

	
}
