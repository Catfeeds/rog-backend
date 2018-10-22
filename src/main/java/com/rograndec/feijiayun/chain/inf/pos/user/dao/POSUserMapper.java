package com.rograndec.feijiayun.chain.inf.pos.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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
public interface POSUserMapper {
	
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
	SelectPOSPayeeTeamVO findByEnterpriseId(@Param("enterpriseId")Long enterpriseId,@Param("userId")Long userId);
	
	/**
	 * 
	 * @Description: 获取营业员
	 * @author yuting.li
	 * @version 1.0 
	 * @date 2017年9月30日 下午5:51:12 
	 * @param enterpriseId
	 * @return 
	 * @return List<PosClerkVO>
	 */
	List<SelectPOSclerkVO> getClerkList(Long enterpriseId);
	
}
