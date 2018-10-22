package com.rograndec.feijiayun.chain.business.retail.pos.service;

import com.rograndec.feijiayun.chain.common.vo.UserVO;

/**
 * 
 * @ClassName: PosBankAndPayTypeInitDataService   
 * @Description: 自主控制时，初始化开户银行与支付方式数据
 * @author yuting.li
 * @version 1.0 
 * @date 2018年1月19日 下午1:42:23
 */
public interface PosBankAndPayTypeInitDataService {
	
	/**
	 * 
	 * @Description: 自主控制时，初始化开户银行与支付方式数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2018年1月19日 下午2:09:13 
	 * @param posSet
	 * @param user
	 * @param enterpriseId 
	 * @return void
	 */
	void initBankAndPayTypeData(Integer posSet,UserVO user,Long enterpriseId);
	
	String initBankAndPayTypeDataByDivision(UserVO userVO);

}
