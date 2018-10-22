package com.rograndec.feijiayun.chain.business.report.quality.storage.service;

import java.io.OutputStream;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.NearEffectPeriodGoodsVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.NearEffectReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestNearEffectExportVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.RequestNearEffectVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface NearEffectPeriodGoodsReportService {

	void exportNearEffectPeriodGoodsListExcel(UserVO user,OutputStream output,RequestNearEffectExportVO requestNearEffectExportVO);
	
	//2017-10-28改进
	String getNearEffectPeriodGoodsListNew(UserVO user,Page<NearEffectReportVO<NearEffectPeriodGoodsVO>> page,RequestNearEffectVO requestNearEffectVO);
	
}
