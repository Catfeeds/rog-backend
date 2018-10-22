package com.rograndec.feijiayun.chain.common.component;

import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserPersonal;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.constant.status.ProfilesActive;
import com.rograndec.feijiayun.chain.inf.rogrand.service.RGTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class RGTUserComponent {

	private static final Logger logger = LoggerFactory.getLogger(RGTUserComponent.class);

	@Autowired
	private Environment env;
	@Autowired
	private RGTService rgtService;

	@Autowired
	private UserMapper userMapper;

	public void loginRgtUser(String loginAccount ,String password){

		String property = env.getProperty("spring.profiles.active");

		if(!isPrdProfilesActive(property))
			return;

		/**
		 * 登录融贯通
		 */
		Map<String,String> map = new HashMap<>();
		map.put("username",loginAccount);
		map.put("password",password);
		JSONObject jsonObject = rgtService.loginUser(map);
		logger.info("融贯通用户信息:"+jsonObject.toJSONString());

	}

	public void updateRgtUserPwd(Integer rgtUserId,String loginAccount,String newPassword,String oldPassword){

		String property = env.getProperty("spring.profiles.active");

		if(!isPrdProfilesActive(property))
			return;

		Map<String,Object> map = new HashMap<>();
		map.put("uid",rgtUserId);
		map.put("flag",3);
		map.put("username",loginAccount);
		map.put("password",newPassword);
		map.put("oldPassword",oldPassword);
		rgtService.updateUserPwd(map);
	}

	public void updateUser2Rgt(User user,UserAdministration userAdministration, UserPersonal userPersonal, Enterprise enterprise){

		String property = env.getProperty("spring.profiles.active");

		if(!isPrdProfilesActive(property))
			return;

		Map<String,Object> map = new HashMap<>();
		map.put("username",userAdministration.getLoginAccount());
		map.put("mobile",userPersonal.getMobilePhone());
		map.put("email",userPersonal.getEmail());
		map.put("password",userAdministration.getPassword());
		map.put("eId",enterprise.getRgtEnterpriseId());
		map.put("userType",1);
		map.put("staffType",0);


		/**
		 * 新增融贯通
		 */

		Integer registerRGTUserId = rgtService.registerRGTUser(map, user.getId());

		user.setRgtUserId(registerRGTUserId);

		User newUser = new User();
		newUser.setId(user.getId());
		newUser.setRgtUserId(registerRGTUserId);

		userMapper.updateByPrimaryKeySelective(newUser);
	}

	private boolean isPrdProfilesActive(String property){
		if(ProfilesActive.PRD_ALI.getName().equals(property))
			return true;

		return false;
	}

}
