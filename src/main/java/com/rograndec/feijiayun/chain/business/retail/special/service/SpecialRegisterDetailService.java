package com.rograndec.feijiayun.chain.business.retail.special.service;

import com.rograndec.feijiayun.chain.business.retail.special.vo.SpecialRegisterDetailVO;
import com.rograndec.feijiayun.chain.business.retail.special.vo.SpecialRegisterDetailSaveOrupdateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;



 /**
 * 
 * @ClassName: SpecialRegisterDetailService   
 * @Description:  零售管理-专管登记单品种明细-接口
 * @author zhengbin.jin
 * @version 1.0 
 * @date 2017-09-22 16:34:07
 */
public interface SpecialRegisterDetailService {
	
	
	List<SpecialRegisterDetailVO> getSpecialRegisterDetailData(UserVO userVO) throws Exception;
	
	int save(SpecialRegisterDetailSaveOrupdateVO specialRegisterDetail, UserVO userVO) throws Exception;
	
	int update(SpecialRegisterDetailSaveOrupdateVO specialRegisterDetail, UserVO userVO) throws Exception;
	
	int delete(Long id) throws Exception;
	

}
