package com.rograndec.feijiayun.chain.inf.pos.special.service;

import java.util.List;

import com.rograndec.feijiayun.chain.inf.pos.special.vo.POSSpecialRegisterVO;

public interface POSSpecialRegisterService {

	List<POSSpecialRegisterVO> selectSpecialRegisterDataByEnterpriseId(
			Long enterpriseId, Long parentId);

}
