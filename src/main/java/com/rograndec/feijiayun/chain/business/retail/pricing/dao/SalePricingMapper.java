package com.rograndec.feijiayun.chain.business.retail.pricing.dao;

import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.PrescriptionReportVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.RequestSalePricingVO;
import com.rograndec.feijiayun.chain.business.report.quality.retail.vo.SalePricingShelfVO;
import com.rograndec.feijiayun.chain.business.retail.pricing.entity.SalePricing;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.*;
import com.rograndec.feijiayun.chain.common.vo.GoodsParamStockVO;
import com.rograndec.feijiayun.chain.inf.pos.pricing.vo.POSSalePricingVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SalePricingMapper {
	/**
	 * @mbg.generated
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * @mbg.generated
	 */
	int insert(SalePricing record);

	/**
	 * @mbg.generated
	 */
	int insertSelective(SalePricing record);

	/**
	 * @mbg.generated
	 */
	SalePricing selectByPrimaryKey(Long id);

	/**
	 * @mbg.generated
	 */
	int updateByPrimaryKeySelective(SalePricing record);

	/**
	 * @mbg.generated
	 */
	int updateByPrimaryKey(SalePricing record);

	/**
	 * @return List<SalePricingVO>
	 * @Description: 划价单主表数据
	 * @author yuting.li
	 * @version 1.0
	 * @date 2017年9月22日 下午5:57:48
	 */
	List<SalePricingVO> findByEnterpriseIdSalePricing(SalePricingParamVO param);

	Integer countEnterpriseIdSalePricing(SalePricingParamVO param);

	SalePricingViewVO findByIdSalePricing(Long id);

	SalePricingTotalVO countSalePricingData(SalePricingParamVO param);
	/**
	 * 划价单获取商品
	 **/
	List<SelectPricingGoodsVO> selectGoods(@Param("enterpriseId") Long enterpriseId, @Param("param") String param, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

	Integer countSelectGoods(@Param("enterpriseId") Long enterpriseId, @Param("param") String param);

	/**
	 * @param enterpriseId
	 * @param goodsId
	 * @return List<SelectPricingGoodsLotShelfVO>
	 * @Description: 选择商品的货位批号
	 * @author yuting.li
	 * @version 1.0
	 * @date 2017年9月27日 下午4:03:19
	 */
	List<SelectPricingGoodsLotShelfVO> selectGoodsLotShelf(@Param("enterpriseId") Long enterpriseId, @Param("goodsId") Long goodsId);
	
	/**
	 * 
	 * @Description: 按商品货位货区可用库存数量
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月6日 下午4:44:45 
	 * @param enterpriseId
	 * @param goodsId
	 * @param shelfId
	 * @return 
	 * @return List<SelectPricingGoodsLotShelfVO>
	 */
	List<SelectPricingGoodsLotShelfVO> selectGoodsLotShelfId(@Param("enterpriseId") Long enterpriseId, @Param("goodsId") Long goodsId,@Param("shelfId")Long shelfId);

	/**
	 * @param enterpriseId
	 * @param param
	 * @return List<SelectMemberTypeVO>
	 * @Description: 选择会员
	 * @author yuting.li
	 * @version 1.0
	 * @date 2017年9月27日 下午4:03:03
	 */
	List<SelectMemberTypeVO> selectMemberType(@Param("enterpriseId") Long enterpriseId, @Param("param") String param);

//	/**
//	 * <根据条件查询药品>
//	 *
//	 * @Author: Zhengbin.jin 金正斌
//	 * @Email: Zhengbin.jin@rograndec.com
//	 * @2017/9/27 19:45
//	 */
//	List<SelectPricingGoodsVO> selectGoodsByParam(GoodsParamStockVO goodsParamStockVO);

	SelectGoodsMemberPrice selectGoodsMemberPrice(@Param("enterpriseId") Long enterpriseId, @Param("goodsId") Long goodsId);

	List<POSSalePricingVO> selectSalePricingDataByEnterpriseId(
			@Param("enterpriseId") Long enterpriseId, @Param("status") Integer status);
	
	List<SalePricingShelfVO> getSalePricingReportList(RequestSalePricingVO requestSalePricingVO);
	
	Integer getSalePricingReportListTotal(RequestSalePricingVO requestSalePricingVO);
	
	PrescriptionReportVO<SalePricingShelfVO> getPrescriptionReportVo(RequestSalePricingVO requestSalePricingVO);

	List<SelectPricingGoodsVO> selectLockGoodsByParam(GoodsParamStockVO goodsParamStockVO);
}