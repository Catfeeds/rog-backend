package com.rograndec.feijiayun.chain.business.system.opening.service;

import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningTaxExcelVO;
import com.rograndec.feijiayun.chain.business.system.opening.vo.OpeningTaxVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface OpeningTaxService {
	
	OpeningTaxVO getOpeningTaxList(UserVO userVO);
	
	void saveDraftCache(OpeningTaxVO openingTaxVO, UserVO userVO);
	
	String save(OpeningTaxVO openingTaxVO, UserVO userVO) throws Exception;
	
	void exportOpeningTax(UserVO userVO, OutputStream outPut);
	
	void exportOpeningTaxModel(UserVO userVO, OutputStream outPut);
	
	OpeningTaxExcelVO excelImportTax(HttpServletRequest request) throws Exception;
	
	void exportUnqualified(OutputStream outputStream, String key, Integer type, UserVO userVO);
	
	void continueToImport(OpeningTaxVO openingTaxVO, String key, UserVO userVO);
}
