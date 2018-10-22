package com.rograndec.feijiayun.chain.business.report.finance.account.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AdjustPriceDetail;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AdjustPriceTotalVO;
import com.rograndec.feijiayun.chain.business.report.finance.account.vo.AdjustPriceVO;

public interface AdjustPriceAnalysisMapper {
	
	/**
	 * 获取应付调价列表
	 * @param map
	 * @return
	 */
	List<AdjustPriceVO> queryAdjustPriceList(Map<String,Object> map);
	
	/**
	 * 获取应付调价列表总记录数
	 * @param map
	 * @return
	 */
	Integer getAdjustPriceTotalRecord(Map<String,Object> map);
	
	/**
	 * 获取应付调价列表合计信息
	 * @param map
	 * @return
	 */
	List<AdjustPriceTotalVO> getAdjustPriceListTotal(Map<String,Object> map);
	
	/**
	 * 根据类型获取应付调价列表
	 * @param map
	 * @return
	 */
	List<AdjustPriceVO> queryAdjustPriceListByType(Map<String,Object> map);
	
	/**
	 * 根据类型获取应付调价列表总记录数
	 * @param map
	 * @return
	 */
	Integer getAdjustPriceTotalRecordByType(Map<String,Object> map);
	
	/**
	 * 获取应付调价列表合计信息
	 * @param map
	 * @return
	 */
	List<AdjustPriceTotalVO> getAdjustPriceListByTypeTotal(Map<String,Object> map);
	
	/**
	 * 获取应付调价详情列表
	 * @param id
	 * @param type
	 * @return
	 */
	List<AdjustPriceDetail> queryAdjustPriceDetailList(@Param("id") Long id,@Param("type") Integer type,@Param("chainType") Integer chainType);
	
	/**
	 * 获取应收调价列表
	 * @param map
	 * @return
	 */
	List<AdjustPriceVO> queryReceiveAdjustPriceList(Map<String,Object> map);
	
	/**
	 * 获取应收调价列表总记录数
	 * @param map
	 * @return
	 */
	Integer getReceiveAdjustPriceTotalRecord(Map<String,Object> map);
	
	/**
	 * 获取应收调价列表合计信息
	 * @param map
	 * @return
	 */
	List<AdjustPriceTotalVO> getReceiveAdjustPriceListTotal(Map<String,Object> map);
	
	/**
	 * 根据类型获取应收调价列表
	 * @param map
	 * @return
	 */
	List<AdjustPriceVO> queryReceiveAdjustPriceListByType(Map<String,Object> map);
	
	/**
	 * 根据类型获取应收调价列表总记录数
	 * @param map
	 * @return
	 */
	Integer getReceiveAdjustPriceTotalRecordByType(Map<String,Object> map);
	
	/**
	 * 获取应付调价列表合计信息
	 * @param map
	 * @return
	 */
	List<AdjustPriceTotalVO> getReceiveAdjustPriceListByTypeTotal(Map<String,Object> map);
	
	/**
	 * 获取应收调价详情列表
	 * @param id
	 * @param type
	 * @return
	 */
	List<AdjustPriceDetail> queryReceiveAdjustPriceDetailList(@Param("id") Long id,@Param("type") Integer type,@Param("chainType") Integer chainType);
}
