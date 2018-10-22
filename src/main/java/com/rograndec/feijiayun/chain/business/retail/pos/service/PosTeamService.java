package com.rograndec.feijiayun.chain.business.retail.pos.service;

import java.util.List;

import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosTeamSaveOrupdateVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosTeamSelectVO;
import com.rograndec.feijiayun.chain.business.retail.pos.vo.PosTeamVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;


 /**
 * 
 * @ClassName: PosTeamService   
 * @Description:  零售管理-POS管理-班组-接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017-09-19 14:51:24
 */
public interface PosTeamService {
	
	List<PosTeamVO> getBankData(UserVO userVO) throws Exception;
	
	void save(PosTeamSaveOrupdateVO bean,UserVO userVO) throws Exception;
	
	void update(PosTeamSaveOrupdateVO bean,UserVO userVO) throws Exception;
	
	void delete(Long id, UserVO userVO) throws Exception;
	
	Long findByCode(String code,Long enterpriseId) throws Exception;
	
	List<PosTeamSelectVO> selectPosTeam(Long enterpriseId) throws Exception;

}
