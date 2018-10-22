package com.rograndec.feijiayun.chain.business.system.enterprise.service;

import java.util.List;

import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseModifyRecordWithBLOBs;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseQualificationConfig;

public interface EnterpriseModifyRecordService {

	List<EnterpriseModifyRecordWithBLOBs> selectEnterpriseModifyRecordPage(
			int pageNo, int pageSize, Long enterpriseId, Page page);

	void saveEnterpriseModifyRecord(Enterprise enterprise,
			Enterprise newEnterprise, EnterpriseBusiness enterpriseBusiness,
			EnterpriseBusiness newEnterpriseBusiness, UserVO loginUser,
			String reason);

	void saveEnterpriseConfigModifyRecord(EnterpriseQualificationConfig enterpriseConfig,
			EnterpriseQualificationConfig newEnterpriseConfig, UserVO loginUser,
			String reason);
	
}
