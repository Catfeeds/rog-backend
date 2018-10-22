package com.rograndec.feijiayun.chain.business.report.quality.retail.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.SaleOutORreturnParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.SaleOutORreturnTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.SaleOutORreturnVO;

public interface SaleOutORreturnMapper {
	
	/**
	 * 
	 * @Description: 销售/销退报表数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月23日 下午4:31:00 
	 * @param paramVO
	 * @return 
	 * @return List<SaleOutORreturnVO>
	 */
	List<SaleOutORreturnVO> getSaleOutORreturn(SaleOutORreturnParamVO paramVO);
	SaleOutORreturnTotalVO sumSaleOutORreturn(SaleOutORreturnParamVO paramVO);
	Integer countSaleOutORreturn(SaleOutORreturnParamVO paramVO);
}
