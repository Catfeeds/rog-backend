package com.rograndec.feijiayun.chain.business.system.enterprise.service;

import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreEnterpriseBusinessResponseVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;

import java.util.List;

public interface EnterpriseBusinessService {

	List<EnterpriseBusiness> queryEnterpriseBusinessByEnterpriseIds(List<Long> ids);

	EnterpriseBusiness queryEnterpriseBusinessByEnterpriseId(Long enterpriseId);

	StoreEnterpriseBusinessResponseVO transfromationResponseEnterpriseBusiness(
			EnterpriseBusiness enterpriseBus);

}
