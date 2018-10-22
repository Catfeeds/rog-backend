package com.rograndec.feijiayun.chain.business.system.enterprise.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseQualificationConfigBean;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseQualificationConfigVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.QualificationValidateVO;
import com.rograndec.feijiayun.chain.common.SelectBean;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface EnterpriseQualificationConfigService {

	List<EnterpriseQualificationConfigVO> selectEnterpriseQualificationByEnterpriseId(
			Enterprise enterprise);

	QualificationValidateVO queryEnterpriseQualificationValidateByEnterpriseIdAndId(
			Long enterpriseId, String entrepriseQualificationId);

	List<SelectBean> selectEntrepriseOptionalQualification(
			Enterprise enterprise);

	String saveOrUpdate(EnterpriseQualificationConfigBean config, Enterprise enterprise, 
			UserVO loginUser);

	int deleteEnterpriseQualificationConfig(String id, UserVO loginUser);

	String validateEnterpriseQualificationConfig(
			EnterpriseQualificationConfigBean config, Enterprise enterprise,
			UserVO loginUser);

	EnterpriseQualificationConfigVO transformationConfigToVo(
			EnterpriseQualificationConfigBean config);

}
