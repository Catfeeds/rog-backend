package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.common.vo.GoodsStorageDataVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsStorageMapper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsTaxRateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class GoodsComponent {

	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private GoodsTaxRateMapper goodsTaxRateMapper;
	@Autowired
	GoodsStorageMapper goodsStorageMapper;



	/**
	 * 查询税率
	 * @param enterpriseId
	 * @param status
	 * @return
	 */
	public List<GoodsTaxRateVO> getGoodsTaxRateVO(Long enterpriseId, Integer status){
		return goodsTaxRateMapper.selectAllVO(enterpriseId, status);
	}

	public List<GoodsTaxRate> getGoodsTaxRates(Set<Long> ids){
		List<Long> gs = new ArrayList<>(ids);
		List<GoodsTaxRate> goodsTaxRates = goodsTaxRateMapper.selectByIds(gs);

		return goodsTaxRates;
	}

//	/**
//	 * 查询商品库存
//	 * @param goodsParamVO
//	 * @return
//	 */
//	public List<GoodsInfoAndStockVO> getGoodsInfoForStock(GoodsParamStockVO goodsParamVO){
//		return  goodsMapper.getGoodsInfoForStock(goodsParamVO);
//	}

	/**
	 * 查询货品的储存和养护信息
	 * @param goodsId
	 * @return
	 */
	public GoodsStorageDataVO getGoodsStorageData(Long enterpriseId, Long goodsId) {
		GoodsStorageDataVO goodsStorageDataVO = goodsStorageMapper.selectByGoodsidAndEnterpriseId(enterpriseId, goodsId);
		return goodsStorageDataVO;
	}
}
