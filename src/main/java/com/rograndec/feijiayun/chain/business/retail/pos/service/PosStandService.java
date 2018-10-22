package com.rograndec.feijiayun.chain.business.retail.pos.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosStandSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosStandVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


 /**
 * 
 * @ClassName: PosStandService   
 * @Description:  零售管理-POS管理-款台-接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:40:37
 */
public interface PosStandService {
	
	List<PosStandVO> getStandData(UserVO userVO) throws Exception;
	
	void save(PosStandSaveOrupdateVO bean,UserVO userVO) throws Exception;
	
	void update(PosStandSaveOrupdateVO bean,UserVO userVO) throws Exception;

}
