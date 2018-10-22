package com.rograndec.feijiayun.chain.business.basic.store.service;

import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface StoreModifyRecordService {

	void saveEnterpriseModifyRecord(Enterprise enterprise,
			Enterprise newEnterprise, EnterpriseBusiness enterpriseBusiness,
			EnterpriseBusiness newEnterpriseBusiness, UserVO loginUser,
			String reason);

}
