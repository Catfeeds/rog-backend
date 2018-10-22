package com.rograndec.feijiayun.chain.business.retail.pos.service;

import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayeeVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayeeSaveOrupdateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;



 /**
 * 
 * @ClassName: PosPayeeService   
 * @Description:  零售管理-POS管理-收款人员-接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-21 17:33:09
 */
public interface PosPayeeService {
	
	
	List<PosPayeeVO> getPosPayeeData(UserVO userVO) throws Exception;
	
	int save(PosPayeeSaveOrupdateVO posPayee,UserVO userVO) throws Exception;
	
	int update(PosPayeeSaveOrupdateVO posPayee,UserVO userVO) throws Exception;
	
	int delete(Long id,UserVO userVO) throws Exception;
	
	public boolean getDeleteFlag(Long enterpriseId, Long id);
	

}
