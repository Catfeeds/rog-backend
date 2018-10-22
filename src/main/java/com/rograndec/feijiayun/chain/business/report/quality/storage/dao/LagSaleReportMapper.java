package com.rograndec.feijiayun.chain.business.report.quality.storage.dao;

import java.util.List;

import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.LagSaleGoodsRequestParamVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.LagSaleGoodsTotalVO;
import com.rograndec.feijiayun.chain.business.report.quality.storage.vo.LagSaleGoodsVO;

public interface LagSaleReportMapper {
	
	/**
	 * 
	 * @Description: 滞销商品查询
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月21日 下午4:12:54 
	 * @param paramVO
	 * @return 
	 * @return List<LagSaleGoodsVO>
	 */
	List<LagSaleGoodsVO> getLagSaleGoods(LagSaleGoodsRequestParamVO paramVO);
	LagSaleGoodsTotalVO sumLagSaleGoods(LagSaleGoodsRequestParamVO paramVO);
	Integer countLagSaleGoods(LagSaleGoodsRequestParamVO paramVO);
}
