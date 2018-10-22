package com.rograndec.feijiayun.chain.inf.pos.prescription.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.PharmacySetForPrescVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.prescription.vo.PrescriptionParamVO;
import com.rograndec.feijiayun.chain.inf.pos.prescription.vo.PrescriptionUserVO;
import com.rograndec.feijiayun.chain.inf.pos.prescription.vo.PrescriptionVO;
import com.rograndec.feijiayun.chain.inf.pos.prescription.vo.UserParamVO;

public interface POSPrescriptionService {

	List<PrescriptionVO> selectPrescriptionDataByEnterpriseId(Long enterpriseId, Long parentId);

	List<PharmacySetForPrescVO> selectPharmacySetData(Long enterpriseId,
			Long parentId, PrescriptionParamVO param);

	List<PrescriptionUserVO> selectPrescriptionUserData(UserVO userVO,
			UserParamVO param);

}
