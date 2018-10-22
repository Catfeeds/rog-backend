package com.rograndec.feijiayun.chain.business.retail.pos.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosBankSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosBankVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


 /**
 * 
 * @ClassName: PosBankService   
 * @Description:  零售管理-POS管理-开户银行-接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:34:42
 */
public interface PosBankService {
	
	List<PosBankVO> getBankData(UserVO userVO) throws Exception;
	
	void save(PosBankSaveOrupdateVO bean,UserVO userVO) throws Exception;
	
	void update(PosBankSaveOrupdateVO bean,UserVO userVO) throws Exception;
	
	void delete(Long id) throws Exception;
	
	Long findByCode(String code,Long enterpriseId) throws Exception;
	

}
