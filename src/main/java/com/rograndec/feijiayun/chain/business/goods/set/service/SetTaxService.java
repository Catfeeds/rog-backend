package com.rograndec.feijiayun.chain.business.goods.set.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsTaxRateVO;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * Created by zeshi.sun on 2017/9/5.
 */
public interface SetTaxService {

	List<GoodsTaxRate> getSetTax(Long enterpriseId) throws Exception;

	Result<String> insertSetTax(UserVO loginUser, GoodsTaxRate goodsTaxRate) throws Exception;

	int updateSetTax(UserVO loginUser, GoodsTaxRate goodsTaxRate) throws Exception;

	boolean canDelete(Long id) throws Exception;

	int deleteSetTax(Long id) throws Exception;

	/**
	 * 前台显示的VO数据
	 * 
	 * @param enterpriseId
	 * @return
	 */
	List<GoodsTaxRateVO> getSetTaxVO(Long enterpriseId) throws Exception;

}
