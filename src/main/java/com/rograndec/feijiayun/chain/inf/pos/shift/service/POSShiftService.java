package com.rograndec.feijiayun.chain.inf.pos.shift.service;

import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.pos.shift.vo.POSAddShiftVO;
import com.rograndec.feijiayun.chain.inf.pos.shift.vo.POSCommitShiftVO;
import com.rograndec.feijiayun.chain.inf.pos.shift.vo.POSShiftAddReturnVO;
import com.rograndec.feijiayun.chain.inf.pos.shift.vo.POSShiftVO;

public interface POSShiftService {
	
	/**
	 * 
	 * @Description: 获取交班数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月7日 下午4:50:43 
	 * @param enterpriseId
	 * @return 
	 * @return POSShfitVO
	 */
	POSShiftVO getShiftByEnterpriseId(Long enterpriseId) throws Exception;
	
	/**
	 * 
	 * @Description: 生成开班数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月7日 下午6:32:04 
	 * @param svo
	 * @return
	 * @throws Exception 
	 * @return int
	 */
	int saveShift(POSAddShiftVO svo,UserVO posLoginUser) throws Exception;
	
	POSShiftAddReturnVO saveReturnShift(POSAddShiftVO svo,UserVO posLoginUser) throws Exception;
	
	
	/**
	 * 
	 * @Description: POS交班数据
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年10月8日 上午9:31:09 
	 * @param svo
	 * @param posLoginUser
	 * @return
	 * @throws Exception 
	 * @return int
	 */
	int updateShift(POSCommitShiftVO svo,UserVO posLoginUser) throws Exception;
	
}
