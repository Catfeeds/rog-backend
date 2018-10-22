package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserRoleMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserRole;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName: CommonServiceImpl  
 * @Description: TODO(这里用一句话描述这个类的作用)  
 * @author zhongyi.li zhongyi.li@rograndec.com  
 * @date 2017年8月24日 下午3:59:26  
 *
 */
@Component
public class UserRoleComponent {

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Transactional(rollbackFor = Exception.class)
	public void save(User userVO, List<Long> roles){

		/*List<UserRole> userRoles = userRoleMapper.selectByUserId(userVO.getId());*/

		userRoleMapper.deleteByUserId(userVO.getId());

		for(Long roleId : roles){
			UserRole userRole = new UserRole();
			userRole.setEnterpriseId(userVO.getEnterpriseId());
//			if(userVO.getChainType().equals(ChainType.Headquarters.getCode())){
//				userRole.setParentId(0L);
//			}else {
			userRole.setParentId(userVO.getParentId());
			userRole.setUserId(userVO.getId());
			userRole.setRoleId(roleId);
		/*	if(CollectionUtils.isEmpty(userRoles)){*/
			userRole.setCreaterId(userVO.getId());
			userRole.setCreaterCode(userVO.getCode());
			userRole.setCreaterName(userVO.getName());
			userRole.setCreateTime(new Date());
			userRole.setModifierCode(userVO.getCode());
			userRole.setModifierId(userVO.getId());
			userRole.setModifierName(userVO.getName());
			userRole.setUpdateTime(new Date());
			userRoleMapper.insertSelective(userRole);
			/*}else {
				userRole.setModifierId(userVO.getId());
				userRole.setModifierCode(userVO.getCode());
				userRole.setModifierName(userVO.getName());
				userRole.setUpdateTime(new Date());
				userRoleMapper.updateByPrimaryKeySelective(userRole);
			}*/

		}

	}


}
