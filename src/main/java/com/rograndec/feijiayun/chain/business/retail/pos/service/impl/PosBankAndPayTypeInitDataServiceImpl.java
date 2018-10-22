package com.rograndec.feijiayun.chain.business.retail.pos.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.rograndec.feijiayun.chain.business.init.service.impl.SysDataInitServiceImpl;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosBankMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayTypeMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosSalePeriodMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosSalePeriod;
import com.rograndec.feijiayun.chain.business.retail.pos.service.PosBankAndPayTypeInitDataService;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@Service
public class PosBankAndPayTypeInitDataServiceImpl implements PosBankAndPayTypeInitDataService{
	
	private static Logger logger = LoggerFactory.getLogger(PosBankAndPayTypeInitDataServiceImpl.class);
	
	@Autowired
	private SysDataInitServiceImpl sysDataInitService;
	
	@Autowired
	private PosPayTypeMapper posPayTypeMapper;

	@Autowired
	private PosBankMapper posBankMapper;
	
	@Autowired
	EnterpriseMapper enterpriseMapper;
	
	@Autowired
	PosSalePeriodMapper posSalePeriodMapper;

	@Override
	public void initBankAndPayTypeData(Integer posSet,UserVO user,Long enterpriseId) {
		try {
			// 门店自主控制
			if (null != posSet && posSet == 1) {
				Long pay = posPayTypeMapper.findByEidAndType(enterpriseId);
				if(0 == pay) {
					// 初始化POS设置-支付方式
					sysDataInitService.initPosPayType(user, enterpriseId);
				}
				Long bank = posBankMapper.findByEidAndType(enterpriseId);
				if(0 == bank) {
					// 初始化POS设置-开户银行
					sysDataInitService.initPosBank(user, enterpriseId);
				}
			}
		} catch (Exception e) {
			logger.error("POS自主控制初始化支付方式，开户银行数据失败", e);
		}
	}


	@Override
	public String initBankAndPayTypeDataByDivision(UserVO user) {
		List<Long> eId = enterpriseMapper.getEnterpriseByType();
		Integer count = 0;
		JSONObject msg = new JSONObject();
		if(null != eId && eId.size() > 0) {
			count = eId.size();
			msg.put("加盟店总数", count);
			List<Map<String, Long>> listMap = new ArrayList<>();
			for(Long enterpriseId : eId) {
				Map<String, Long> map = new HashMap<>();
				Long pay = posPayTypeMapper.findByEidAndType(enterpriseId);
				if(0 == pay) {
					// 初始化POS设置-支付方式
					sysDataInitService.initPosPayType(user, enterpriseId);
					map.put("添加支付方式企业id", enterpriseId);
				}
				Long bank = posBankMapper.findByEidAndType(enterpriseId);
				if(0 == bank) {
					// 初始化POS设置-开户银行
					sysDataInitService.initPosBank(user, enterpriseId);
					map.put("添加开户银行企业id", enterpriseId);
				}
				//初始化销售时段
				List<PosSalePeriod> list = posSalePeriodMapper.selectByEnterpriseId(enterpriseId);
				if(null != list && list.size() == 0) {
					sysDataInitService.initPosSalePeriod(user, enterpriseId);
					map.put("初始化销售时段企业id", enterpriseId);
				}
				if(map.size() > 0) {
					listMap.add(map);
				}
			}
			msg.put("enterpriseId", listMap);
		}
		return msg.toJSONString();
	}

}
