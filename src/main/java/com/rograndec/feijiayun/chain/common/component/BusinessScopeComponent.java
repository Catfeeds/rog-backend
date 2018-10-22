package com.rograndec.feijiayun.chain.common.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.system.set.dao.BusinessScopeMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;


@Component
public class BusinessScopeComponent {
	
	@Autowired
	private BusinessScopeMapper businessScopeMapper;
	
	/**
	 * 
	 * @Title: findEnterpriseQualificationSelectorByEnterpriseId  
	 * @Description:经营范围 
	 * @param @param enterpriseId
	 * @param @return    设定文件  
	 * @return List<BusinessScope>    返回类型  
	 * @throws
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly=true)
	public List<BusinessScope> findBusinessScopeSelectorByEnterpriseId(Long enterpriseId) {
		if (enterpriseId != null) {
			List<BusinessScope> list = businessScopeMapper.findBusinessScopeSelectorByEnterpriseId(enterpriseId);
			return list;
		}
		return null;
	}

}
