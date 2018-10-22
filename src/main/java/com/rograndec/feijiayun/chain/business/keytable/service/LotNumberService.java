package com.rograndec.feijiayun.chain.business.keytable.service;

import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;

public interface LotNumberService {
	
	public void saveLotNumber(LotNumber lotNumber);
	
	public LotNumber getLotNumberInfo(Long lotId);
	
}
