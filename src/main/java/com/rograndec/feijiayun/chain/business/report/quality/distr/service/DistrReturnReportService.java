package com.rograndec.feijiayun.chain.business.report.quality.distr.service;

import java.io.OutputStream;
import java.util.List;

import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrRetuenInReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReturnCheckReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReturnNoticeReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReturnReceiveReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrReturnCheckVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrReturnInVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrReturnNoticeVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrReturnReceiveVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface DistrReturnReportService {
	
	void getDistrReturnNoticeReport(Page<DistrReportVo<DistrReturnNoticeReportVO>> page,UserVO user,RequestDistrReturnNoticeVO requestDistrReturnNoticeVO);
	
	void exportDistrReturnNoticeReportListExcel(OutputStream output,UserVO user, RequestDistrReturnNoticeVO requestDistrReturnNoticeVO);

	void getDistrReturnReceiveReport(Page<DistrReportVo<DistrReturnReceiveReportVO>> page,UserVO user,RequestDistrReturnReceiveVO requestDistrReturnReceiveVO);
	
	void exportDistrReturnReceiveReportListExcel(OutputStream output,UserVO user,RequestDistrReturnReceiveVO requestDistrReturnReceiveVO);
	
	void getDistrReturnCheckReport(Page<DistrReportVo<DistrReturnCheckReportVO>> page,UserVO user,RequestDistrReturnCheckVO requestDistrReturnCheckVO);
	
	void exportDistrReturnCheckReportListExcel(OutputStream output,UserVO user,RequestDistrReturnCheckVO requestDistrReturnCheckVO);
	
    void getDistrReturnInReport(Page<DistrReportVo<DistrRetuenInReportVO>> page,UserVO user,RequestDistrReturnInVO requestDistrReturnInVO);
	
	void exportDistrReturnInReportListExcel(OutputStream output,UserVO user,RequestDistrReturnInVO requestDistrReturnInVO);
	
	
	
}
