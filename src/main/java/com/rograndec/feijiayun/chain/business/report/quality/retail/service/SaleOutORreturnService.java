package com.rograndec.feijiayun.chain.business.report.quality.retail.service;

import java.io.OutputStream;

import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.SaleOutORreturnParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.SaleOutORreturnTotalVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface SaleOutORreturnService {
	
	/**
	 * 
	 * @Description: 销售/销退报表数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月23日 下午4:31:00 
	 * @param paramVO
	 * @return 
	 * @return Page<SaleOutORreturnTotalVO>
	 */
	Page<SaleOutORreturnTotalVO> getSaleOutORreturn(SaleOutORreturnParamVO paramVO) throws Exception;
	void exportExcel(OutputStream output,SaleOutORreturnParamVO paramVO,UserVO userVO) throws Exception;
	
}
