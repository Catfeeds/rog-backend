package com.rograndec.feijiayun.chain.inf.pos.sale.service;

import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.sale.vo.POSOrderParamVO;

public interface POSOrderService {

	@SuppressWarnings("rawtypes")
	Page selectOrderDataByParam(POSOrderParamVO param, UserVO userVO);

	@SuppressWarnings("rawtypes")
	Page selectOrderDeatilDataByParam(POSOrderParamVO param, UserVO userVO);

}
