package com.rograndec.feijiayun.chain.business.retail.payment.service;

import com.rograndec.feijiayun.chain.business.retail.payment.vo.HeadquartersAlreadySearchConditionVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.HeadquartersStaySearchConditionVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.StoreStayViewSearchConditionVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface HeadquartersSalePaymentService {

	@SuppressWarnings("rawtypes")
	void getStayPaymentViewPage(StoreStayViewSearchConditionVO condition,
			Page page, UserVO loginUser);

	@SuppressWarnings("rawtypes")
	void getAlreadyPaymentPage(HeadquartersAlreadySearchConditionVO condition,
			Page page, UserVO loginUser);

	@SuppressWarnings("rawtypes")
	void getStayPaymentPage(HeadquartersStaySearchConditionVO condition,
			Page page, UserVO loginUser);

}
