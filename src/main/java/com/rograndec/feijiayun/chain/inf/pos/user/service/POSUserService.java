package com.rograndec.feijiayun.chain.inf.pos.user.service;

import java.util.List;

import com.rograndec.feijiayun.chain.inf.pos.user.vo.SelectPOSPayeeTeamVO;
import com.rograndec.feijiayun.chain.inf.pos.user.vo.SelectPOSclerkVO;

/**
 * 
 * @ClassName: POSUserMapper   
 * @Description: POS 选择人员相关接口
 * @author yuting.li
 * @version 1.0 
 * @date 2017年9月30日 下午4:38:20
 */
public interface POSUserService {
	
	/**
	 * 
	 * @Description: 选择款员信息
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年9月30日 下午4:39:44 
	 * @param enterpriseId
	 * @return 
	 * @return List<SelectPOSPayeeTeamVO>
	 */
	SelectPOSPayeeTeamVO findByEnterpriseId(Long enterpriseId,Long userId) throws Exception;
	
	List<SelectPOSclerkVO> getPosClerk(Long enterpriseId) throws Exception;
	
}
