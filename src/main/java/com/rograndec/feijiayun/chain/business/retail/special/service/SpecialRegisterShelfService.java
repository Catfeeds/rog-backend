package com.rograndec.feijiayun.chain.business.retail.special.service;

import com.rograndec.feijiayun.chain.business.retail.special.vo.SpecialRegisterShelfVO;
import com.rograndec.feijiayun.chain.business.retail.special.vo.SpecialRegisterShelfSaveOrupdateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;



 /**
 * 
 * @ClassName: SpecialRegisterShelfService   
 * @Description:  零售管理-专管登记单货位明细-接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-22 16:34:35
 */
public interface SpecialRegisterShelfService {
	
	
	List<SpecialRegisterShelfVO> getSpecialRegisterShelfData(UserVO userVO) throws Exception;
	
	int save(SpecialRegisterShelfSaveOrupdateVO specialRegisterShelf, UserVO userVO) throws Exception;
	
	int update(SpecialRegisterShelfSaveOrupdateVO specialRegisterShelf, UserVO userVO) throws Exception;
	
	int delete(Long id) throws Exception;
	

}
