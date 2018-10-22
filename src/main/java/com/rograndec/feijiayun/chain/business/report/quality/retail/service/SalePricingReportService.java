package com.rograndec.feijiayun.chain.business.report.quality.retail.service;

import java.io.OutputStream;
import java.util.List;

import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.PrescriptionRegisterReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.PrescriptionReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.RequestPrescriptionVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.RequestSalePricingVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.RequestSpecialRegisterVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.SalePricingShelfVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.SpecialRegisterReportVO;
import com.rograndec.feijiayun.chain.business.retail.prescription.entity.PrescriptionRegister;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.ResponsePrescriptionRegisterForDetailVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface SalePricingReportService {

	void getSalePricingReportList(Page<PrescriptionReportVO<SalePricingShelfVO>> page,UserVO user,RequestSalePricingVO requestSalePricingVO);
	
	void exportSalePricingReportListExcel(OutputStream output,UserVO user,RequestSalePricingVO requestSalePricingVO);
	
	void getSpecialRegisterReportList(Page<PrescriptionReportVO<SpecialRegisterReportVO>> page,UserVO user,RequestSpecialRegisterVO requestSpecialRegisterVO);
	
	void exportSpecialRegisterReportListExcel(OutputStream output,UserVO user,RequestSpecialRegisterVO requestSpecialRegisterVO);
	
    void getPrescriptionList(Page<PrescriptionReportVO> page,UserVO user,RequestPrescriptionVO requestPrescriptionVO);
	
	void exportPrescriptionListExcel(OutputStream output,UserVO user,RequestPrescriptionVO requestPrescriptionVO);
	
	/**
	 * 根据处方登记id 查询详情
	 * @param id
	 * @return
	 */
	ResponsePrescriptionRegisterForDetailVO getDetailById(Long id,Long enterpriseId);
	
    void exportPrescriptionRecord(OutputStream output, Long enterpriseId, Long id) throws Exception;
    
    PrescriptionRegister  getPrescriptionRegisterById(Long id);
}
