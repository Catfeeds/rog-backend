package com.rograndec.feijiayun.chain.inf.pos.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.inf.pos.goods.dao.POSGoodsMapper;
import com.rograndec.feijiayun.chain.inf.pos.goods.service.POSGoodsService;
import com.rograndec.feijiayun.chain.inf.pos.goods.vo.SelectPOSGoodsLotNumVO;
import com.rograndec.feijiayun.chain.inf.pos.goods.vo.SelectPOSGoodsParamVO;
import com.rograndec.feijiayun.chain.inf.pos.goods.vo.SelectPOSGoodsVO;

@Service
public class POSGoodsServiceImpl implements POSGoodsService{
	
	@Autowired
	private POSGoodsMapper pOSGoodsMapper;

	@Override
	public List<SelectPOSGoodsVO> selectGoods(SelectPOSGoodsParamVO param) throws Exception {
		List<SelectPOSGoodsVO> list = pOSGoodsMapper.selectGoods(param.getEnterpriseId(), param.getParam());
		return list;
	}

	@Override
	public List<SelectPOSGoodsLotNumVO> selectGoodsLotNum(Long enterpriseId, Long goodsId,int usableQuantity) throws Exception {
		return pOSGoodsMapper.selectGoodsLotNum(enterpriseId, goodsId,usableQuantity);
	}

	@Override
	public List<SelectPOSGoodsVO> selectGoodsAttribute(SelectPOSGoodsParamVO param) throws Exception {
		return pOSGoodsMapper.selectGoodsAttribute(param.getEnterpriseId(), param.getParam());
	}
	
	

}
