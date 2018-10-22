package com.rograndec.feijiayun.chain.inf.mph.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.inf.mph.service.WhetherSaasUserService;

@Service
public class WhetherSaasUserServiceImpl implements WhetherSaasUserService{

	@Autowired
	private EnterpriseMapper enterpriseMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public String isSaasUser(JSONObject ob) {
		
		String uId = ob.get("uId")==null?"":ob.get("uId").toString();
		String eId = ob.get("eId")==null?"":ob.get("eId").toString();
		
		if(StringUtils.isBlank(uId) || StringUtils.isBlank(eId)){
			return "1";
		}
		
		List<Enterprise> enList = enterpriseMapper.selectByRgtEnterpriseId(eId);
		if(enList != null && enList.size() > 0){
			for (Enterprise enterprise : enList) {
				List<User> userList = userMapper.selectByRgtUserId(enterprise.getId(), uId);
				if(userList != null && userList.size() > 0){
					return "0";
				}
			}
		}
		
		return "1";
	}

}
