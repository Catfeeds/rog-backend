package com.rograndec.feijiayun.chain.business.purchase.instorage.service;

import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.StayInstorageDetailVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.StayInstorageFormVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.StayInstorageOtherVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.StayInstorageSaveVO;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

public interface StayInstorageService {

	StayInstorageFormVO queryStayInstorageFormByCheckId(Long id,
			UserVO loginUser);

	List<StayInstorageDetailVO> selectStayInstorageDetailListByCheckId(Long id,
			UserVO loginUser);

	StayInstorageOtherVO queryStayInstorageOther(Long id);

	String saveStayInstorage(StayInstorageSaveVO vo, UserVO loginUser) throws Exception ;

    DraftCacheVO getDraftCacheVO(UserVO userVO, Long baseOrderId);

	DraftCacheVO<StayInstorageSaveVO> saveDraftCache(UserVO userVO, DraftCacheVO<StayInstorageSaveVO> draftCacheVO) throws Exception;

	void removeDraftCach(Long enterpriseId, String codePrefix, String redisKeyValue);

    /*StayInstorageSaveVO getStayInstorageDraft(Long id, UserVO userVO);*/
}
