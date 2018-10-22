package com.rograndec.feijiayun.chain.common.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;

@Component
public class CheckTypeComponent {
	
	@Autowired
	private QualitySetMapper qualitySetMapper;
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly=true)
	public List<QualitySet> findCheckTypes(Long enterpriseId) {
		List<QualitySet> list = qualitySetMapper.findCheckTypes(enterpriseId);
		return list;
	}
}
