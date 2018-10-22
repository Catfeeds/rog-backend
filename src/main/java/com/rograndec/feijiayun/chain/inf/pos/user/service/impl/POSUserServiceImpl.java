package com.rograndec.feijiayun.chain.inf.pos.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.inf.pos.user.dao.POSUserMapper;
import com.rograndec.feijiayun.chain.inf.pos.user.service.POSUserService;
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
@Service
public class POSUserServiceImpl implements POSUserService{
	
	@Autowired
	private POSUserMapper pOSUserMapper;
	
	
	@Override
	public SelectPOSPayeeTeamVO findByEnterpriseId(Long enterpriseId,Long userId) throws Exception {
		return pOSUserMapper.findByEnterpriseId(enterpriseId,userId);
	}

	@Override
	public List<SelectPOSclerkVO> getPosClerk(Long enterpriseId) throws Exception {
		return pOSUserMapper.getClerkList(enterpriseId);
	}
	

	
}
