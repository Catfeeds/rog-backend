package com.rograndec.feijiayun.chain.business.retail.payment.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.retail.payment.vo.DynamicColumnVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.PaymentVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.StoreAlreadyPageVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.StoreAlreadySearchConditionVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.StoreStaySearchConditionVO;
import com.rograndec.feijiayun.chain.business.retail.payment.vo.StoreStayViewSearchConditionVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface StoreSalePaymentService {

	List<DynamicColumnVO> selectDynamicColumnBySource(Integer source, UserVO loginUser)throws Exception;

	PaymentVO queryPaymentVOByShiftId(Long shiftId, UserVO loginUser);

	String saveSalePayment(PaymentVO paymentVO, UserVO loginUser) throws Exception;

	@SuppressWarnings("rawtypes")
	List<StoreAlreadyPageVO> selectAlreadyPayment(StoreAlreadySearchConditionVO condition, Long enterpriseId,
			Page page);

	PaymentVO queryAlreadyPaymentInfoById(Long id);

	List<SelectBean> selectPosStandSelectBeanByUserVO(UserVO loginUser);

	List<SelectBean> selectPosTeamSelectBeanByUserVO(UserVO loginUser);

	@SuppressWarnings("rawtypes")
	void getStayPaymentPageByParams(StoreStaySearchConditionVO condition,
			Page page, UserVO loginUser)throws Exception;

	@SuppressWarnings("rawtypes")
	void getStayPaymentViewPage(StoreStayViewSearchConditionVO condition,
			Page page, UserVO loginUser)throws Exception;

}
