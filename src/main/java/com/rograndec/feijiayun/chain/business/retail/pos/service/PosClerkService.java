package com.rograndec.feijiayun.chain.business.retail.pos.service;

import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosClerkVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosClerkSaveOrupdateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;



 /**
 * 
 * @ClassName: PosClerkService   
 * @Description:  零售管理-POS管理-营业人员-接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-21 15:59:33
 */
public interface PosClerkService {
	
	
	List<PosClerkVO> getPosClerkData(UserVO userVO) throws Exception;
	
	int save(PosClerkSaveOrupdateVO posClerk,UserVO userVO) throws Exception;
	
	int update(PosClerkSaveOrupdateVO posClerk,UserVO userVO) throws Exception;
	
	int delete(Long id) throws Exception;
	

}
