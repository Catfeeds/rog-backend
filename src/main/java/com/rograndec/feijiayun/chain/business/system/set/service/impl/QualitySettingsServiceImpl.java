/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.QualitySetType;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;
import com.sun.net.httpserver.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import com.rograndec.feijiayun.chain.business.system.set.service.QualitySettingsService;
import org.springframework.transaction.annotation.Transactional;

/**

 * @Description:TODO

 * @author:LeiSu

 * @time:2017年8月22日 上午10:15:25

 */
@Service
public class QualitySettingsServiceImpl implements QualitySettingsService{
	
	@Autowired
	private QualitySetMapper qualitySetMapper;
	@Autowired
	QualitySettingsDeleteValidImpl valid;

	@Override
	public List<QualitySet> getQualitySettings(UserVO user,Integer setType,Integer ty) {

		List<QualitySet> qualitySet = new ArrayList<QualitySet>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user", user);
		map.put("setType", setType);
		map.put("type", ty);
		//如果当前用户不是总部的
		if (user.getChainType() != ChainType.Headquarters.getCode()) {
			qualitySet = qualitySetMapper.getQualitySettingsById(map);
		}else {
			qualitySet = qualitySetMapper.getQualitySettingsByUser(map);
		}
		//当查询验收项目时，需要将验收类型的中文传给前台

		//当前对象记录企业类型
		for (QualitySet ql : qualitySet) {
			if (ql.getSetType().equals(QualitySetType.CHECK_POJECT.getSetType())){
				Long type = ql.getType().longValue();
				QualitySet q = qualitySetMapper.selectByPrimaryKey(type);
				if (q != null){
					ql.setCheckTypeDetail(q.getDescription());
				}
			}
			ql.setChainType(user.getChainType());
		}
		return qualitySet;

	}

	/* (非 Javadoc)  
	 * <p>Title: getQualitySettings</p>  
	 * <p>Description: </p>  
	 * @param user
	 * @return  
	 * @see com.rograndec.feijiayun.chain.system.set.service.QualitySettingsService#getQualitySettings(com.rograndec.feijiayun.chain.common.vo.UserVO)  
	 */
	@Override
	public List<QualitySet> getQualitySettings(UserVO user,Integer setType) {
		List<QualitySet> resList= getQualitySettings( user,setType,null);
		for(QualitySet set:resList){
			//若是系统默认则设置不可删除不可更改
			if(set.getSysType()==1){
				set.setDeleteFlag(false);
				set.setUpdateFlag(false);
			}else{
				//自定义的需要根据判断设置
				set.setDeleteFlag(valid.valid(set));
			}
		}
		return resList;
	}

	/* (非 Javadoc)  
	 * <p>Title: updateQualitySettings</p>  
	 * <p>Description: </p>  
	 * @param qualitySet  
	 * @see com.rograndec.feijiayun.chain.system.set.service.QualitySettingsService#updateQualitySettings(com.rograndec.feijiayun.chain.system.set.entity.QualitySet)  
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateQualitySettings(QualitySet qualitySet,UserVO user) throws Exception{
		if ("".equals(qualitySet.getDescription().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"修改时描述不能为空!");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user", user);
		map.put("qualitySet", qualitySet);
		/**
		 * 修改时描述不可以相同
		 */
		String preDescription = qualitySetMapper.selectByPrimaryKey(qualitySet.getId()).getDescription();
		Integer setType = qualitySet.getSetType();
		Integer type = qualitySet.getType();
		String description = qualitySet.getDescription();
		QualitySet qualityDescription = qualitySetMapper.hasQualityDescription(setType,description,user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
		if (!preDescription.equals(description)){
			if (qualityDescription != null){
				throw new BusinessException(SysCode.FAIL.getCode(),"当前描述已存在!");
			}
		}
		if (user.getChainType() == ChainType.Headquarters.getCode()){
			qualitySetMapper.updateQualitySettings(map);
		}
	}

	/* (非 Javadoc)
	 * <p>Title: addQualitySettings</p>  
	 * <p>Description: </p>  
	 * @param qualitySet  
	 * @see com.rograndec.feijiayun.chain.system.set.service.QualitySettingsService#addQualitySettings(com.rograndec.feijiayun.chain.system.set.entity.QualitySet)  
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addQualitySettings(QualitySet qualitySet,UserVO user) throws Exception{
		if ("".equals(qualitySet.getCode().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"编码不允许为空!");
		}
		if ("".equals(qualitySet.getDescription().trim())){
			throw new BusinessException(SysCode.FAIL.getCode(),"描述不允许为空!");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user", user);
		map.put("qualitySet", qualitySet);
		/***
		 * set_type + type + enterpriseId为唯一键
		 */
		Integer setType = qualitySet.getSetType();
		Integer type = qualitySet.getType();
		String code = qualitySet.getCode();
		String description = qualitySet.getDescription();
		/**
		 * 添加时编码和描述不能重复
		 */
		if (ChineseString.isChinese(code)){
			throw new BusinessException(SysCode.FAIL.getCode(),"编码不允许有汉字!");
		}
		QualitySet qualityCode = qualitySetMapper.hasQualityCode(setType,code,user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
		if (qualityCode != null){
			throw new BusinessException(SysCode.FAIL.getCode(),"当前编码已存在!");
		}
		QualitySet qualityDescription = qualitySetMapper.hasQualityDescription(setType,description,user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
		if (qualityDescription != null){
			throw new BusinessException(SysCode.FAIL.getCode(),"当前描述已存在!");
		}

		//只有总部可以增加
		if (user.getChainType() == ChainType.Headquarters.getCode()){
			qualitySetMapper.addQualitySettings(map);
		}
	}

	/* (非 Javadoc)  
	 * <p>Title: deleteQualitySettings</p>  
	 * <p>Description: </p>  
	 * @param id  
	 * @see com.rograndec.feijiayun.chain.system.set.service.QualitySettingsService#deleteQualitySettings(java.lang.Long)  
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteQualitySettings(Long id) {
		QualitySet qualitySet=qualitySetMapper.selectByPrimaryKey(id);
		if(!valid.valid(qualitySet)) throw new BusinessException(SysCode.FAIL.getCode(),"已被使用，无法删除");
		qualitySetMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<QualitySet> selectCheckProductSelector(UserVO user) {
		Long enterpriseId = user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId();
		List<QualitySet> list = qualitySetMapper.selectCheckProductSelector(enterpriseId);
		return list;
	}

}
