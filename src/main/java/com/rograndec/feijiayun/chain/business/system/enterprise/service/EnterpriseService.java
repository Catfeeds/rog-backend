package com.rograndec.feijiayun.chain.business.system.enterprise.service;


import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreEnterpriseResponseVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseBusinessVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EntrepriseValidateVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

public interface EnterpriseService {

    List<BusinessScope> queryBusinessScopeByBusinessVariety(UserVO loginUser, String businessVariety);

    Enterprise queryEnterpriseById(Long enterpriseId, String type);

	List<BusinessScope> getBusinessScopeByBusinessVariety(Long enterpriseId, String businessVariety);

	List<BusinessScope> getBusinessScopeByBusinessVariety(
			Long enterpriseId, List<Long> businessVarietys);

	String saveEnterprise(EnterpriseVO enterpriseVO,
						  EnterpriseBusinessVO enterpriseBusinessVO, UserVO loginUser, String reason);

    List<Enterprise> getChildrens(Long id);

	EntrepriseValidateVO queryEnterpriseValidateByEnterpriseId(Long enterpriseId);

    Enterprise queryEnterpriseById4StatusEnable(Long enterpriseId, String type);

    Enterprise queryEnterpriseAuxiliary(Enterprise enterprise);

	StoreEnterpriseResponseVO transfromationResponseEnterprise(
			Enterprise enterprise);
}
