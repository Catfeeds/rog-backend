package com.rograndec.feijiayun.chain.business.report.quality.distr.service;

import java.io.OutputStream;
import java.util.List;

import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrInCheckReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrInReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrInCheckVO;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrInVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface DistrInReportService {

	void getDistrInCheckReportList(Page<DistrReportVo<DistrInCheckReportVO>> page,UserVO user,RequestDistrInCheckVO requestDistrInCheckVO);
	
	void exportDistrInCheckReportListExcel(OutputStream output,UserVO userVO,RequestDistrInCheckVO requestDistrInCheckVO);
	
    void getDistrInReportList(Page<DistrReportVo<DistrInReportVO>> page,UserVO user,RequestDistrInVO requestDistrInVO);
	
	void exportDistrInReportListExcel(OutputStream output,UserVO userVO,RequestDistrInVO requestDistrInVO);
}
