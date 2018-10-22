package com.rograndec.feijiayun.chain.inf.pos.pricing.service;

import java.util.List;

import com.rograndec.feijiayun.chain.inf.pos.pricing.vo.POSSalePricingVO;

public interface POSSalePricingService {

	List<POSSalePricingVO> selectSalePricingDataByEnterpriseId(
			Long enterpriseId, Long parentId);

}
