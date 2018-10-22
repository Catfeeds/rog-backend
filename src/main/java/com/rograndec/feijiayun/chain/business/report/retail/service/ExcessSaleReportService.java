package com.rograndec.feijiayun.chain.business.report.retail.service;

import java.io.OutputStream;
import java.util.List;

import com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale.ExcessSaleQueryVO;
import com.rograndec.feijiayun.chain.business.report.retail.vo.excesssale.ExcessSaleToOtherInVO;
import com.rograndec.feijiayun.chain.business.storage.move.vo.OtherInDetailVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface ExcessSaleReportService {

	Page selectExcessSalePageByParam(ExcessSaleQueryVO vo,
			UserVO loginUser);

	void exportExcel(OutputStream output, ExcessSaleQueryVO vo,
			UserVO loginUser, UserVO loginUser2)throws Exception;

	List<OtherInDetailVO> selectExcessSaleDataByParam(ExcessSaleQueryVO vo, UserVO loginUser)throws Exception;

}
