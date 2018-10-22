package com.rograndec.feijiayun.chain.business.retail.pos.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayTypeSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayTypeVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


 /**
 * 
 * @ClassName: PosPayTypeService   
 * @Description:  零售管理-POS管理-支付方式-接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:19:11
 */
public interface PosPayTypeService {
	
	List<PosPayTypeVO> getPayTypeData(UserVO userVO) throws Exception;
	
	void save(PosPayTypeSaveOrupdateVO bean,UserVO userVO) throws Exception;
	
	void update(PosPayTypeSaveOrupdateVO bean,UserVO userVO) throws Exception;
	
	void delete(Long id, UserVO loginUser) throws Exception;
	
	Long findByCode(String code,Long enterpriseId) throws Exception;

}
