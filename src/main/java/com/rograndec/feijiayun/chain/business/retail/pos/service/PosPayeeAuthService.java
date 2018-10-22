package com.rograndec.feijiayun.chain.business.retail.pos.service;

import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayeeAuthVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosPayeeAuthSaveOrupdateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;



 /**
 * 
 * @ClassName: PosPayeeAuthService   
 * @Description:  零售管理-POS管理-款员权限-接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-22 10:18:46
 */
public interface PosPayeeAuthService {
	
	
	List<PosPayeeAuthVO> getPosPayeeAuthData(UserVO userVO) throws Exception;
	
	int save(PosPayeeAuthSaveOrupdateVO posPayeeAuth,UserVO userVO) throws Exception;
	
	int update(PosPayeeAuthSaveOrupdateVO posPayeeAuth,UserVO userVO) throws Exception;
	
	int delete(Long id) throws Exception;
	

}
