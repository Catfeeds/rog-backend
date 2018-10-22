package com.rograndec.feijiayun.chain.business.online.purchase.smart.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.ManualSearchQueryVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SelectMphSupplierRequestVO;
import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.SmartSpecificPriceVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface SmartSpecificPriceService {

	List<SmartSpecificPriceVO> selectSmartSpecificPrice(UserVO userVO,
			Long supplierId, Long goodsId, Long gId) throws Exception ;

	Page<List<SmartSpecificPriceVO>> selectManualSearch(UserVO userVO,
			ManualSearchQueryVO vo) throws Exception ;

	String selectMphSupplier(UserVO userVO, SelectMphSupplierRequestVO vo);

}
