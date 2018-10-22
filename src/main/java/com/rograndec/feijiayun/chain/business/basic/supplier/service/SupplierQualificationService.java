package com.rograndec.feijiayun.chain.business.basic.supplier.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierQulificationVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface SupplierQualificationService {

	List<SupplierQulificationVO> getDefaultQualification(UserVO user, List<Long> idList);

    EnterpriseQualification getQualification(Long qualificationId);
}
