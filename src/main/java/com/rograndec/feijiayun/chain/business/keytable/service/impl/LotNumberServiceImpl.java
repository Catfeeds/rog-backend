package com.rograndec.feijiayun.chain.business.keytable.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.keytable.service.LotNumberService;

@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
public class LotNumberServiceImpl implements LotNumberService{

	@Autowired
	private LotNumberMapper lotNumberMapper;
	
	@Override
	public void saveLotNumber(LotNumber lotNumber){
		lotNumberMapper.insertSelective(lotNumber);
	}
	
	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly=true)
	public LotNumber getLotNumberInfo(Long lotId){
		return lotNumberMapper.selectByPrimaryKey(lotId);
	}
	
}
