package com.rograndec.feijiayun.chain.business.basic.supplier.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierQualificationConfigMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierQualificationService;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierQulificationVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.EnterpriseQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;
import com.rograndec.feijiayun.chain.common.component.SupplierComponent;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SupplierQualificationServiceImpl implements SupplierQualificationService{

	@Autowired
	private SupplierQualificationConfigMapper supplierQualificationConfigMapper;

	@Autowired
	private EnterpriseQualificationMapper enterpriseQualificationMapper;

	@Autowired
	private SupplierComponent supplierComponent;
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public List<SupplierQulificationVO> getDefaultQualification(UserVO user, List<Long> idList) {
		//supplierQualificationConfigMapper
		List<SupplierQulificationVO> defaultQualification = supplierComponent.getDefaultQualification(user,idList);
		return defaultQualification;
	}

	@Override
	public EnterpriseQualification getQualification(Long qualificationId) {
		EnterpriseQualification enterpriseQualification = enterpriseQualificationMapper.selectByPrimaryKey(qualificationId);
		return enterpriseQualification;
	}

}
