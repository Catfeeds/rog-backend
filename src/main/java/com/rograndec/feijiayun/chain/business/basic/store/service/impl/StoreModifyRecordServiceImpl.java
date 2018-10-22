package com.rograndec.feijiayun.chain.business.basic.store.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rograndec.feijiayun.chain.business.basic.store.dao.EnterpriseGroupMapper;
import com.rograndec.feijiayun.chain.business.basic.store.dao.SaleAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.store.dao.SaleCircleMapper;
import com.rograndec.feijiayun.chain.business.basic.store.dao.StoreLevelMapper;
import com.rograndec.feijiayun.chain.business.basic.store.entity.EnterpriseGroup;
import com.rograndec.feijiayun.chain.business.basic.store.entity.SaleArea;
import com.rograndec.feijiayun.chain.business.basic.store.entity.SaleCircle;
import com.rograndec.feijiayun.chain.business.basic.store.entity.StoreLevel;
import com.rograndec.feijiayun.chain.business.basic.store.service.StoreModifyRecordService;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseModifyRecordWithBLOBs;
import com.rograndec.feijiayun.chain.business.system.set.dao.BusinessScopeMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.DistrPriceType;
import com.rograndec.feijiayun.chain.common.constant.EconomyType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.ModeOperation;
import com.rograndec.feijiayun.chain.common.constant.ValidFlag;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
@Service
public class StoreModifyRecordServiceImpl implements StoreModifyRecordService{
	
	private static final Log logger = LogFactory.getLog(StoreModifyRecordServiceImpl.class);
	
	@Autowired
	BusinessScopeMapper businessScopeMapper;
	
	@Autowired
	EnterpriseModifyRecordMapper enterpriseModifyRecordMapper;
	
	@Autowired
	EnterpriseGroupMapper enterpriseGroupMapper;
	
	@Autowired
	SaleAreaMapper saleAreaMapper;
	
	@Autowired
	StoreLevelMapper storeLevelMapper;
	
	@Autowired
	SaleCircleMapper saleCircleMapper;

	@Override
	public void saveEnterpriseModifyRecord(Enterprise enterprise,
			Enterprise newEnterprise, EnterpriseBusiness enterpriseBusiness,
			EnterpriseBusiness newEnterpriseBusiness, UserVO vo,
			String reason) {
		if(enterpriseBusiness == null){
			enterpriseBusiness = new EnterpriseBusiness();
		}
		
		/*System.out.println(JSONObject.toJSONString(enterprise)); 
		
		System.out.println(JSONObject.toJSONString(newEnterprise));*/
		
		Map<String, String> map = compareEnterprise(enterprise, newEnterprise);
		
		map = compareEnterpriseBusiness(enterpriseBusiness, newEnterpriseBusiness, map);
		
		if(map != null && !map.isEmpty()){
			
			if(StringUtils.isNotBlank(map.get("sbEnName"))){
				
				EnterpriseModifyRecordWithBLOBs blob = setBlobData(map, newEnterprise, newEnterpriseBusiness, vo, reason);
				
				String[] enNames = blob.getColumnEnName().split("<br>");
				String[] sbChName = blob.getColumnChName().split("<br>");
				String[] sbOldContent = blob.getOldContent().split("<br>");
				String[] sbNewContent = blob.getNewContent().split("<br>");
				
				for (int i = 0; i < enNames.length; i++) {
					blob.setColumnEnName(enNames.length>i?enNames[i]:"");
					blob.setColumnChName(sbChName.length>i?sbChName[i]:"");
					blob.setOldContent(sbOldContent.length>i?sbOldContent[i]:"");
					blob.setNewContent(sbNewContent.length>i?sbNewContent[i]:"");
					blob.setCreaterId(vo.getUserId());
					blob.setCreaterCode(vo.getUserCode());
					blob.setCreaterName(vo.getUserName());
					blob.setCreateTime(new Date());
					blob.setModifierId(vo.getUserId());
					blob.setModifierCode(vo.getUserCode());
					blob.setModifierName(vo.getUserName());
					blob.setUpdateTime(new Date());
					enterpriseModifyRecordMapper.insertSelective(blob);
				}
				
			}
		}
		
	}
	
	
	/**
	 * @Description: blob表赋值
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年8月24日 下午5:37:04 
	 * @param map
	 * @param newEnterpriseBusiness
	 * @return 
	 * @return EnterpriseModifyRecordWithBLOBs
	 */
	private EnterpriseModifyRecordWithBLOBs setBlobData(
			Map<String, String> map, Enterprise newEnterprise, EnterpriseBusiness newEnterpriseBusiness,
			UserVO vo, String reason) {
		EnterpriseModifyRecordWithBLOBs blob = new EnterpriseModifyRecordWithBLOBs();
		
		blob.setEnterpriseId(newEnterprise.getId());
		blob.setParentId(newEnterprise.getParentId());
		blob.setTableName("");
		blob.setKeyId(newEnterprise.getId());
		blob.setReason(reason);//原因
		blob.setColumnEnName(map.get("sbEnName")==null?"":map.get("sbEnName"));
		blob.setColumnChName(map.get("sbChName")==null?"":map.get("sbChName"));
		blob.setOldContent(map.get("sbOldContent")==null?"":map.get("sbOldContent"));
		blob.setNewContent(map.get("sbNewContent")==null?"":map.get("sbNewContent"));
		blob.setCreaterId(vo.getUserId());
		blob.setCreaterCode(vo.getUserCode());
		blob.setCreaterName(vo.getUserName());
		return blob;
	}
	
	
	/**
	 * @Description: 对比企业基表
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年8月24日 下午3:12:01 
	 * @param oldEnterprise
	 * @param newEnterprise
	 * @return 
	 * @return Map<String,String>
	 */
	private Map<String, String> compareEnterprise(Enterprise oldEnterprise,
			Enterprise newEnterprise) {
		Map<String,String> map = new HashMap<String, String>();
		StringBuilder sbEnName = new StringBuilder();
		StringBuilder sbChName = new StringBuilder();
		StringBuilder sbOldContent = new StringBuilder();
		StringBuilder sbNewContent = new StringBuilder();
		
		if(oldEnterprise.getChainType() != newEnterprise.getChainType()){
			sbEnName.append("chain_type").append("<br>");
			sbChName.append("药店类型").append("<br>");
			sbOldContent.append(getChainTypeTextById(oldEnterprise.getChainType())).append("<br>");
			sbNewContent.append(getChainTypeTextById(newEnterprise.getChainType())).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getCode(),newEnterprise.getCode())){
			sbEnName.append("code").append("<br>");
			sbChName.append("编码").append("<br>");
			sbOldContent.append(oldEnterprise.getCode()==null?"":oldEnterprise.getCode()).append("<br>");
			sbNewContent.append(newEnterprise.getCode()==null?"":newEnterprise.getCode()).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getPinyin(),newEnterprise.getPinyin())){
			sbEnName.append("pinyin").append("<br>");
			sbChName.append("检索码").append("<br>");
			sbOldContent.append(oldEnterprise.getPinyin()==null?"":oldEnterprise.getPinyin()).append("<br>");
			sbNewContent.append(newEnterprise.getPinyin()==null?"":newEnterprise.getPinyin()).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getName(),newEnterprise.getName())){
			sbEnName.append("name").append("<br>");
			sbChName.append("名称").append("<br>");
			sbOldContent.append(oldEnterprise.getName()==null?"":oldEnterprise.getName()).append("<br>");
			sbNewContent.append(newEnterprise.getName()==null?"":newEnterprise.getName()).append("<br>");
		}
		
		if(oldEnterprise.getGroupId() != newEnterprise.getGroupId()){
			sbEnName.append("group_id").append("<br>");
			sbChName.append("门店分组").append("<br>");
			sbOldContent.append(getGroupNameById(oldEnterprise.getGroupId())).append("<br>");
			sbNewContent.append(getGroupNameById(newEnterprise.getGroupId())).append("<br>");
		}
		
		if(oldEnterprise.getEconomicType() != newEnterprise.getEconomicType()){
			sbEnName.append("economic_type").append("<br>");
			sbChName.append("经济类型").append("<br>");
			sbOldContent.append(getEconomicTypeName(oldEnterprise.getEconomicType())).append("<br>");
			sbNewContent.append(getEconomicTypeName(newEnterprise.getEconomicType())).append("<br>");
		}
		
		if(oldEnterprise.getBusinessMode() != newEnterprise.getBusinessMode()){
			sbEnName.append("business_mode").append("<br>");
			sbChName.append("经营方式").append("<br>");
			sbOldContent.append(getBusinessModeName(oldEnterprise.getBusinessMode())).append("<br>");
			sbNewContent.append(getBusinessModeName(newEnterprise.getBusinessMode())).append("<br>");
		}
		
		if(validateBigDecimalEquals(oldEnterprise.getRegisterMoney(), newEnterprise.getRegisterMoney())){//---
			sbEnName.append("register_money").append("<br>");
			sbChName.append("注册资金").append("<br>");
			sbOldContent.append(optimizeObject(oldEnterprise.getRegisterMoney())).append("<br>");
			sbNewContent.append(optimizeObject(newEnterprise.getRegisterMoney())).append("<br>");
		}
		
		if(validateObjectEquals(oldEnterprise.getProvinceId(), newEnterprise.getProvinceId())){
			sbEnName.append("province_id").append("<br>");
			sbChName.append("省").append("<br>");
			sbOldContent.append(optimizeObject(oldEnterprise.getProvinceName())).append("<br>");
			sbNewContent.append(optimizeObject(newEnterprise.getProvinceName())).append("<br>");
		}
		
		if(validateObjectEquals(oldEnterprise.getCityId(), newEnterprise.getCityId())){
			sbEnName.append("city_id").append("<br>");
			sbChName.append("市").append("<br>");
			sbOldContent.append(optimizeObject(oldEnterprise.getCityName())).append("<br>");
			sbNewContent.append(optimizeObject(newEnterprise.getCityName())).append("<br>");
		}
		
		if(validateObjectEquals(oldEnterprise.getAreaId(), newEnterprise.getAreaId())){
			sbEnName.append("area_id").append("<br>");
			sbChName.append("区").append("<br>");
			sbOldContent.append(optimizeObject(oldEnterprise.getAreaName())).append("<br>");
			sbNewContent.append(optimizeObject(newEnterprise.getAreaName())).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getCompanyAddress(),newEnterprise.getCompanyAddress())){
			sbEnName.append("company_address").append("<br>");
			sbChName.append("公司地址").append("<br>");
			sbOldContent.append(oldEnterprise.getCompanyAddress()==null?"":oldEnterprise.getCompanyAddress()).append("<br>");
			sbNewContent.append(newEnterprise.getCompanyAddress()==null?"":newEnterprise.getCompanyAddress()).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getPostcode(),newEnterprise.getPostcode())){
			sbEnName.append("postcode").append("<br>");
			sbChName.append("邮政编码").append("<br>");
			sbOldContent.append(oldEnterprise.getPostcode()==null?"":oldEnterprise.getPostcode()).append("<br>");
			sbNewContent.append(newEnterprise.getPostcode()==null?"":newEnterprise.getPostcode()).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getTel(),newEnterprise.getTel())){
			sbEnName.append("tel").append("<br>");
			sbChName.append("公司电话").append("<br>");
			sbOldContent.append(oldEnterprise.getTel()==null?"":oldEnterprise.getTel()).append("<br>");
			sbNewContent.append(newEnterprise.getTel()==null?"":newEnterprise.getTel()).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getFax(), newEnterprise.getFax())){
			sbEnName.append("fax").append("<br>");
			sbChName.append("公司传真").append("<br>");
			sbOldContent.append(oldEnterprise.getFax()==null?"":oldEnterprise.getFax()).append("<br>");
			sbNewContent.append(newEnterprise.getFax()==null?"":newEnterprise.getFax()).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getEmail(),newEnterprise.getEmail())){
			sbEnName.append("email").append("<br>");
			sbChName.append("公司邮箱").append("<br>");
			sbOldContent.append(oldEnterprise.getEmail()==null?"":oldEnterprise.getEmail()).append("<br>");
			sbNewContent.append(newEnterprise.getEmail()==null?"":newEnterprise.getEmail()).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getSite(),newEnterprise.getSite())){
			sbEnName.append("site").append("<br>");
			sbChName.append("公司网址").append("<br>");
			sbOldContent.append(oldEnterprise.getSite()==null?"":oldEnterprise.getSite()).append("<br>");
			sbNewContent.append(newEnterprise.getSite()==null?"":oldEnterprise.getSite()).append("<br>");
		}
		
		if(validateObjectEquals(oldEnterprise.getSaleAreaId(), newEnterprise.getSaleAreaId())){
			sbEnName.append("sale_area_id").append("<br>");
			sbChName.append("销售片区").append("<br>");
			sbOldContent.append(getSaleAreaNameById(oldEnterprise.getSaleAreaId())).append("<br>");
			sbNewContent.append(getSaleAreaNameById(newEnterprise.getSaleAreaId())).append("<br>");
		}
		
		if(validateObjectEquals(oldEnterprise.getSaleCircleId(), newEnterprise.getSaleCircleId())){
			sbEnName.append("sale_circle_id").append("<br>");
			sbChName.append("销售商圈").append("<br>");
			sbOldContent.append(getSaleCircleNameById(oldEnterprise.getSaleCircleId())).append("<br>");
			sbNewContent.append(getSaleCircleNameById(newEnterprise.getSaleCircleId())).append("<br>");
		}
		
		if(validateObjectEquals(oldEnterprise.getStoreLevelId(), newEnterprise.getStoreLevelId())){
			
			String oldStoreLevel = getStoreLevelNameById(oldEnterprise.getStoreLevelId());
			String newStoreLevel = getStoreLevelNameById(newEnterprise.getStoreLevelId());
			if(!oldStoreLevel.equals(newStoreLevel)){
				sbEnName.append("store_level_id").append("<br>");
				sbChName.append("门店级别").append("<br>");
				sbOldContent.append(oldStoreLevel).append("<br>");
				sbNewContent.append(newStoreLevel).append("<br>");
			}
		}
		
		//分店
		if(validateBigDecimalEquals(oldEnterprise.getAcreage(), newEnterprise.getAcreage())){
			sbEnName.append("acreage").append("<br>");
			sbChName.append("面积").append("<br>");
			sbOldContent.append(optimizeObject(oldEnterprise.getAcreage())).append("<br>");
			sbNewContent.append(optimizeObject(newEnterprise.getAcreage())).append("<br>");
		}
		
		//分店
		if(validateBigDecimalEquals(oldEnterprise.getMonthly(), newEnterprise.getMonthly())){
			sbEnName.append("monthly").append("<br>");
			sbChName.append("月租").append("<br>");
			sbOldContent.append(optimizeObject(oldEnterprise.getMonthly())).append("<br>");
			sbNewContent.append(optimizeObject(newEnterprise.getMonthly())).append("<br>");
		}
		
		//分店
		if(validateDateEquals(oldEnterprise.getShopDate(), newEnterprise.getShopDate())){
			sbEnName.append("shop_date").append("<br>");
			sbChName.append("开店日期").append("<br>");
			sbOldContent.append(oldEnterprise.getShopDate()==null?"":
				DateUtils.DateToString(oldEnterprise.getShopDate(), DateUtils.FMT_DATE)).append("<br>");
			sbNewContent.append(newEnterprise.getShopDate()==null?"":
				DateUtils.DateToString(newEnterprise.getShopDate(), DateUtils.FMT_DATE)).append("<br>");
		}
		
		//分店
		if(validateDateEquals(oldEnterprise.getRelocationDate(), newEnterprise.getRelocationDate())){
			sbEnName.append("relocation_date").append("<br>");
			sbChName.append("最近搬迁日期").append("<br>");
			sbOldContent.append(oldEnterprise.getRelocationDate()==null?"":
				DateUtils.DateToString(oldEnterprise.getRelocationDate(), DateUtils.FMT_DATE)).append("<br>");
			sbNewContent.append(newEnterprise.getRelocationDate()==null?"":
				DateUtils.DateToString(newEnterprise.getRelocationDate(), DateUtils.FMT_DATE)).append("<br>");
		}
		
		//分店
		if(validateDateEquals(oldEnterprise.getReformDate(),newEnterprise.getReformDate())){
			sbEnName.append("reform_date").append("<br>");
			sbChName.append("最近改造日期").append("<br>");
			sbOldContent.append(oldEnterprise.getReformDate()==null?"":
				DateUtils.DateToString(oldEnterprise.getReformDate(), DateUtils.FMT_DATE)).append("<br>");
			sbNewContent.append(newEnterprise.getReformDate()==null?"":
				DateUtils.DateToString(newEnterprise.getReformDate(), DateUtils.FMT_DATE)).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getBusinessManName(),newEnterprise.getBusinessManName())){
			sbEnName.append("business_man_name").append("<br>");
			sbChName.append("企业负责人").append("<br>");
			sbOldContent.append(optimizeString(oldEnterprise.getBusinessManName())).append("<br>");
			sbNewContent.append(optimizeString(newEnterprise.getBusinessManName())).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getLegalManName(),newEnterprise.getLegalManName())){
			sbEnName.append("legal_man_name").append("<br>");
			sbChName.append("法定代表人").append("<br>");
			sbOldContent.append(optimizeString(oldEnterprise.getLegalManName())).append("<br>");
			sbNewContent.append(optimizeString(newEnterprise.getLegalManName())).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getQualityOfficerName(),newEnterprise.getQualityOfficerName())){
			sbEnName.append("quality_officer_name").append("<br>");
			sbChName.append("质量负责人").append("<br>");
			sbOldContent.append(optimizeString(oldEnterprise.getQualityOfficerName())).append("<br>");
			sbNewContent.append(optimizeString(newEnterprise.getQualityOfficerName())).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getBankName(),newEnterprise.getBankName())){
			sbEnName.append("bank_name").append("<br>");
			sbChName.append("开户银行").append("<br>");
			sbOldContent.append(optimizeString(oldEnterprise.getBankName())).append("<br>");
			sbNewContent.append(optimizeString(newEnterprise.getBankName())).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getBankAccount(),newEnterprise.getBankAccount())){
			sbEnName.append("bank_account").append("<br>");
			sbChName.append("开户账号").append("<br>");
			sbOldContent.append(optimizeString(oldEnterprise.getBankAccount())).append("<br>");
			sbNewContent.append(optimizeString(newEnterprise.getBankAccount())).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getBankAccountName(),newEnterprise.getBankAccountName())){
			sbEnName.append("bank_account_name").append("<br>");
			sbChName.append("开户户名").append("<br>");
			sbOldContent.append(optimizeString(oldEnterprise.getBankAccountName())).append("<br>");
			sbNewContent.append(optimizeString(newEnterprise.getBankAccountName())).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getBusinessVariety(),newEnterprise.getBusinessVariety())){
			sbEnName.append("business_variety").append("<br>");
			sbChName.append("经营品种").append("<br>");
			sbOldContent.append(getBusinessVarietyName(oldEnterprise.getBusinessVariety())).append("<br>");
			sbNewContent.append(getBusinessVarietyName(newEnterprise.getBusinessVariety())).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getBusinessScopeId(),newEnterprise.getBusinessScopeId())){
			sbEnName.append("business_scope_id").append("<br>");
			sbChName.append("经营范围").append("<br>");
			sbOldContent.append(getBusinessScopeName(oldEnterprise.getParentId()==0?oldEnterprise.getId():oldEnterprise.getParentId(), 
					oldEnterprise.getBusinessScopeId())).append("<br>");
			sbNewContent.append(getBusinessScopeName(newEnterprise.getParentId()==0?newEnterprise.getId():newEnterprise.getParentId(), 
					newEnterprise.getBusinessScopeId())).append("<br>");
		}
		
		//分店
		if(oldEnterprise.getMedicalFlag() != newEnterprise.getMedicalFlag()){
			sbEnName.append("medical_flag").append("<br>");
			sbChName.append("医保药店").append("<br>");
			sbOldContent.append(getMedicalFlagName(oldEnterprise.getMedicalFlag())).append("<br>");
			sbNewContent.append(getMedicalFlagName(newEnterprise.getMedicalFlag())).append("<br>");
		}
		
		//分店
		if(validateStringEquals(oldEnterprise.getMedicalCode(),newEnterprise.getMedicalCode())){
			sbEnName.append("medical_code").append("<br>");
			sbChName.append("医保机构编码").append("<br>");
			sbOldContent.append(optimizeString(oldEnterprise.getMedicalCode())).append("<br>");
			sbNewContent.append(optimizeString(newEnterprise.getMedicalCode())).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getCompanyAbout(),newEnterprise.getCompanyAbout())){
			sbEnName.append("company_about").append("<br>");
			sbChName.append("公司简介").append("<br>");
			sbOldContent.append(optimizeString(oldEnterprise.getCompanyAbout())).append("<br>");
			sbNewContent.append(optimizeString(newEnterprise.getCompanyAbout())).append("<br>");
		}

		// 状态（0-禁用；1-启用）
		if(!oldEnterprise.getStatus().equals(newEnterprise.getStatus())){
			sbEnName.append("status").append("<br>");
			sbChName.append("状态").append("<br>");
			sbOldContent.append(EnableStatus.getName(oldEnterprise.getStatus())).append("<br>");
			sbNewContent.append(EnableStatus.getName(newEnterprise.getStatus())).append("<br>");
		}

		// 标识（0-作废；1-正常）
		if(!oldEnterprise.getValidFlag().equals(newEnterprise.getValidFlag())){
			sbEnName.append("valid_flag").append("<br>");
			sbChName.append("标识").append("<br>");
			sbOldContent.append(ValidFlag.getName(oldEnterprise.getValidFlag())).append("<br>");
			sbNewContent.append(ValidFlag.getName(newEnterprise.getValidFlag())).append("<br>");
		}

		/*if(validateStringEquals(oldEnterprise.getUserName(),newEnterprise.getUserName())){
			sbEnName.append("user_name").append("<br>");
			sbChName.append("管理员账号").append("<br>");
			sbOldContent.append(optimizeString(oldEnterprise.getUserName())).append("<br>");
			sbNewContent.append(optimizeString(newEnterprise.getUserName())).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getPassWord(),newEnterprise.getPassWord())){
			sbEnName.append("password").append("<br>");
			sbChName.append("登录密码").append("<br>");
			sbOldContent.append(optimizeString(oldEnterprise.getPassWord())).append("<br>");
			sbNewContent.append(optimizeString(newEnterprise.getPassWord())).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getPassWordConfirmation(),newEnterprise.getPassWordConfirmation())){
			sbEnName.append("password_confirm").append("<br>");
			sbChName.append("密码确认").append("<br>");
			sbOldContent.append(optimizeString(oldEnterprise.getPassWordConfirmation())).append("<br>");
			sbNewContent.append(optimizeString(newEnterprise.getPassWordConfirmation())).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getAdminPhone(),newEnterprise.getAdminPhone())){
			sbEnName.append("mobile_phone").append("<br>");
			sbChName.append("管理员手机号").append("<br>");
			sbOldContent.append(optimizeString(oldEnterprise.getAdminPhone())).append("<br>");
			sbNewContent.append(optimizeString(newEnterprise.getAdminPhone())).append("<br>");
		}
		
		if(validateStringEquals(oldEnterprise.getAdminEmail(),newEnterprise.getAdminEmail())){
			sbEnName.append("email").append("<br>");
			sbChName.append("管理员邮箱").append("<br>");
			sbOldContent.append(optimizeString(oldEnterprise.getAdminEmail())).append("<br>");
			sbNewContent.append(optimizeString(newEnterprise.getAdminEmail())).append("<br>");
		}*/
		
		
		map.put("sbEnName", sbEnName.toString());
		map.put("sbChName", sbChName.toString());
		map.put("sbOldContent", sbOldContent.toString());
		map.put("sbNewContent", sbNewContent.toString());
		return map;
	}
	
	
	private String getStoreLevelNameById(Long storeLevelId) {
		StoreLevel level = storeLevelMapper.selectByPrimaryKey(storeLevelId);
		return level == null ? "" : level.getName();
	}


	private Object getSaleCircleNameById(Long saleCircleId) {
		SaleCircle circle = saleCircleMapper.selectByPrimaryKey(saleCircleId);
		return circle == null ? "" : circle.getName();
	}


	private Object getSaleAreaNameById(Long saleAreaId) {
		SaleArea area = saleAreaMapper.selectByPrimaryKey(saleAreaId);
		return area == null ? "" : area.getName();
	}


	private Object getGroupNameById(Long groupId) {
		EnterpriseGroup group = enterpriseGroupMapper.selectByPrimaryKey(groupId);
		return group == null ? "" : group.getName();
	}


	/**
	 * @Description: 对比企业业务表
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年8月24日 下午3:12:23 
	 * @param oldEnterprise
	 * @param newEnterprise
	 * @param map
	 * @return 
	 * @return Map<String,String>
	 */
	private Map<String, String> compareEnterpriseBusiness(
			EnterpriseBusiness oldEnterpriseBusiness, EnterpriseBusiness newEnterpriseBusiness,
			Map<String, String> map) {
		
		StringBuilder sbEnName = new StringBuilder();
		StringBuilder sbChName = new StringBuilder();
		StringBuilder sbOldContent = new StringBuilder();
		StringBuilder sbNewContent = new StringBuilder();
		
		sbEnName.append(map.get("sbEnName"));
		sbChName.append(map.get("sbChName"));
		sbOldContent.append(map.get("sbOldContent"));
		sbNewContent.append(map.get("sbNewContent"));
		
		if(oldEnterpriseBusiness.getSettlementMode() != newEnterpriseBusiness.getSettlementMode()){
			sbEnName.append("settlement_mode").append("<br>");
			sbChName.append("结算方式").append("<br>");
			sbOldContent.append(getSettlementModeName(oldEnterpriseBusiness.getSettlementMode())).append("<br>");
			sbNewContent.append(getSettlementModeName(newEnterpriseBusiness.getSettlementMode())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getDistrPriceType() != newEnterpriseBusiness.getDistrPriceType()){
			sbEnName.append("distr_price_type").append("<br>");
			sbChName.append("配货价格类型").append("<br>");
			sbOldContent.append(getDistrPriceTypeName(oldEnterpriseBusiness.getDistrPriceType())).append("<br>");
			sbNewContent.append(getDistrPriceTypeName(newEnterpriseBusiness.getDistrPriceType())).append("<br>");
		}
		
		if(validateStringEquals(oldEnterpriseBusiness.getDistrPriceOrderName(),newEnterpriseBusiness.getDistrPriceOrderName())){
			sbEnName.append("distr_price_order_name").append("<br>");
			sbChName.append("价格清单名称").append("<br>");
			sbOldContent.append(oldEnterpriseBusiness.getDistrPriceOrderName()==null?"":oldEnterpriseBusiness.getDistrPriceOrderName()).append("<br>");
			sbNewContent.append(newEnterpriseBusiness.getDistrPriceOrderName()==null?"":newEnterpriseBusiness.getDistrPriceOrderName()).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getPaymentProvision() != newEnterpriseBusiness.getPaymentProvision()){
			sbEnName.append("payment_provision").append("<br>");
			sbChName.append("付款条款").append("<br>");
			sbOldContent.append(getPaymentProvisionName(oldEnterpriseBusiness.getPaymentProvision())).append("<br>");
			sbNewContent.append(getPaymentProvisionName(newEnterpriseBusiness.getPaymentProvision())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getPaymentPeriod() != newEnterpriseBusiness.getPaymentPeriod()){
			sbEnName.append("payment_period").append("<br>");
			sbChName.append("付款账期").append("<br>");
			sbOldContent.append(optimizeObject(oldEnterpriseBusiness.getPaymentPeriod())).append("<br>");
			sbNewContent.append(optimizeObject(newEnterpriseBusiness.getPaymentPeriod())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getPaymentPeriodUnit() != newEnterpriseBusiness.getPaymentPeriodUnit()){
			sbEnName.append("payment_period_unit").append("<br>");
			sbChName.append("付款账期单位").append("<br>");
			sbOldContent.append(getPaymentPeriodUnitName(oldEnterpriseBusiness.getPaymentPeriodUnit())).append("<br>");
			sbNewContent.append(getPaymentPeriodUnitName(newEnterpriseBusiness.getPaymentPeriodUnit())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getPaymentTime() != newEnterpriseBusiness.getPaymentTime()){
			sbEnName.append("payment_time").append("<br>");
			sbChName.append("付款时间").append("<br>");
			sbOldContent.append(optimizeObject(oldEnterpriseBusiness.getPaymentTime())).append("<br>");
			sbNewContent.append(optimizeObject(newEnterpriseBusiness.getPaymentTime())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getPaymentTimeUnit() != newEnterpriseBusiness.getPaymentTimeUnit()){
			sbEnName.append("payment_time_unit").append("<br>");
			sbChName.append("付款时间单位").append("<br>");
			sbOldContent.append(getPaymentTimeUnitName(oldEnterpriseBusiness.getPaymentTimeUnit())).append("<br>");
			sbNewContent.append(getPaymentTimeUnitName(newEnterpriseBusiness.getPaymentTimeUnit())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getQualityControl() != newEnterpriseBusiness.getQualityControl()){
			sbEnName.append("quality_control").append("<br>");
			sbChName.append("质量管理").append("<br>");
			sbOldContent.append(getQualityControlName(oldEnterpriseBusiness.getQualityControl())).append("<br>");
			sbNewContent.append(getQualityControlName(newEnterpriseBusiness.getQualityControl())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getPermissionSet() != newEnterpriseBusiness.getPermissionSet()){
			sbEnName.append("permission_set").append("<br>");
			sbChName.append("权限管理").append("<br>");
			sbOldContent.append(getQualityControlName(oldEnterpriseBusiness.getPermissionSet())).append("<br>");
			sbNewContent.append(getQualityControlName(newEnterpriseBusiness.getPermissionSet())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getApprovalControl() != newEnterpriseBusiness.getApprovalControl()){
			sbEnName.append("approval_control").append("<br>");
			sbChName.append("审批管理").append("<br>");
			sbOldContent.append(getQualityControlName(oldEnterpriseBusiness.getApprovalControl())).append("<br>");
			sbNewContent.append(getQualityControlName(newEnterpriseBusiness.getApprovalControl())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getWarnSet() != newEnterpriseBusiness.getWarnSet()){
			sbEnName.append("warn_set").append("<br>");
			sbChName.append("预警管理").append("<br>");
			sbOldContent.append(getQualityControlName(oldEnterpriseBusiness.getWarnSet())).append("<br>");
			sbNewContent.append(getQualityControlName(newEnterpriseBusiness.getWarnSet())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getPriceManage() != newEnterpriseBusiness.getPriceManage()){
			sbEnName.append("price_manage").append("<br>");
			sbChName.append("价格管理").append("<br>");
			sbOldContent.append(getQualityControlName(oldEnterpriseBusiness.getPriceManage())).append("<br>");
			sbNewContent.append(getQualityControlName(newEnterpriseBusiness.getPriceManage())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getSpecialPriceManage() != newEnterpriseBusiness.getSpecialPriceManage()){
			sbEnName.append("special_price_manage").append("<br>");
			sbChName.append("特价管理").append("<br>");
			sbOldContent.append(getQualityControlName(oldEnterpriseBusiness.getSpecialPriceManage())).append("<br>");
			sbNewContent.append(getQualityControlName(newEnterpriseBusiness.getSpecialPriceManage())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getMemberInfo() != newEnterpriseBusiness.getMemberInfo()){
			sbEnName.append("member_info").append("<br>");
			sbChName.append("会员管理").append("<br>");
			sbOldContent.append(getQualityControlName(oldEnterpriseBusiness.getMemberInfo())).append("<br>");
			sbNewContent.append(getQualityControlName(newEnterpriseBusiness.getMemberInfo())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getPromotionRule() != newEnterpriseBusiness.getPromotionRule()){
			sbEnName.append("promotion_rule").append("<br>");
			sbChName.append("促销管理").append("<br>");
			sbOldContent.append(getQualityControlName(oldEnterpriseBusiness.getPromotionRule())).append("<br>");
			sbNewContent.append(getQualityControlName(newEnterpriseBusiness.getPromotionRule())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getPosSet() != newEnterpriseBusiness.getPosSet()){
			sbEnName.append("pos_set").append("<br>");
			sbChName.append("POS设置").append("<br>");
			sbOldContent.append(getQualityControlName(oldEnterpriseBusiness.getPosSet())).append("<br>");
			sbNewContent.append(getQualityControlName(newEnterpriseBusiness.getPosSet())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getPriceTagPrint() != newEnterpriseBusiness.getPriceTagPrint()){
			sbEnName.append("price_tag_print").append("<br>");
			sbChName.append("价签管理").append("<br>");
			sbOldContent.append(getQualityControlName(oldEnterpriseBusiness.getPriceTagPrint())).append("<br>");
			sbNewContent.append(getQualityControlName(newEnterpriseBusiness.getPriceTagPrint())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getRoyaltyRule() != newEnterpriseBusiness.getRoyaltyRule()){
			sbEnName.append("royalty_rule").append("<br>");
			sbChName.append("提成管理").append("<br>");
			sbOldContent.append(getQualityControlName(oldEnterpriseBusiness.getRoyaltyRule())).append("<br>");
			sbNewContent.append(getQualityControlName(newEnterpriseBusiness.getRoyaltyRule())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getRemoteTrial() != newEnterpriseBusiness.getRemoteTrial()){
			sbEnName.append("remote_trial").append("<br>");
			sbChName.append("远程审方").append("<br>");
			sbOldContent.append(getQualityControlName(oldEnterpriseBusiness.getRemoteTrial())).append("<br>");
			sbNewContent.append(getQualityControlName(newEnterpriseBusiness.getRemoteTrial())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getReasonableStock() != newEnterpriseBusiness.getReasonableStock()){
			sbEnName.append("reasonable_stock").append("<br>");
			sbChName.append("安全库存").append("<br>");
			sbOldContent.append(getQualityControlName(oldEnterpriseBusiness.getReasonableStock())).append("<br>");
			sbNewContent.append(getQualityControlName(newEnterpriseBusiness.getReasonableStock())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getDistributionManage() != newEnterpriseBusiness.getDistributionManage()){
			sbEnName.append("distribution_manage").append("<br>");
			sbChName.append("配送管理").append("<br>");
			sbOldContent.append(getDistributionManageName(oldEnterpriseBusiness.getDistributionManage())).append("<br>");
			sbNewContent.append(getDistributionManageName(newEnterpriseBusiness.getDistributionManage())).append("<br>");
		}
		
		if(oldEnterpriseBusiness.getEquipmentManage() != newEnterpriseBusiness.getEquipmentManage()){
			sbEnName.append("equipment_manage").append("<br>");
			sbChName.append("设施设备").append("<br>");
			sbOldContent.append(getQualityControlName(oldEnterpriseBusiness.getEquipmentManage())).append("<br>");
			sbNewContent.append(getQualityControlName(newEnterpriseBusiness.getEquipmentManage())).append("<br>");
		}
		
		map.put("sbEnName", sbEnName.toString());
		map.put("sbChName", sbChName.toString());
		map.put("sbOldContent", sbOldContent.toString());
		map.put("sbNewContent", sbNewContent.toString());
		return map;
	}
	
	private Object getDistributionManageName(Integer distributionManage) {
		if(distributionManage == null){
			return "";
		}
		if(0 == distributionManage){
			return "禁止直调";
		}
		return "允许直调";
	}


	private Object getQualityControlName(Integer qualityControl) {
		if(qualityControl == null){
			return "";
		}
		if(0 == qualityControl){
			return "总部控制";
		}
		return "自主控制";
	}


	//经营方式
	private Object getBusinessModeName(Integer businessMode) {
		if(businessMode == null){
			return "";
		}
		for (ModeOperation s : ModeOperation.values()){
			if(s.getCode() == businessMode){
				return s.getName();
			}
		}
		return "";
	}

	//经济类型
	private String getEconomicTypeName(Integer economicType) {
		if(economicType == null){
			return "";
		}
		for (EconomyType s : EconomyType.values()){
			if(s.getCode() == economicType){
				return s.getName();
			}
		}
		return "";
	}
	
	//是否医保药店
	private String getMedicalFlagName(Integer medicalFlag) {
		if(medicalFlag == null){
			return "";
		}
		if(0 == medicalFlag){
			return "否";
		}
		return "是";
	}

	//经营范围
	private Object getBusinessScopeName(Long enterpriseId, String businessScopeId) {
		try {
			if(StringUtils.isNotBlank(businessScopeId)){
				
				List<Long> listIds = new ArrayList<Long>();
				for (String var : businessScopeId.split(",")) {
					if(StringUtils.isNotBlank(var)){
						listIds.add(Long.parseLong(var));
					}
				}
				
				StringBuilder sb = new StringBuilder();
				List<BusinessScope> list = businessScopeMapper.getBusinessScopeByBusinessScopeId(enterpriseId, listIds);
				if(list != null){
					for (int i = 0; i < list.size(); i++) {
						sb.append(list.get(i).getName());
						
						if(i != list.size()){
							sb.append(",");
						}
					}
				}
				return sb.toString();
			}
		} catch (Exception e) {
			logger.error("获取经营范围失败", e);
			e.printStackTrace();
		}
		return "";
	}

	//经营品种
	private Object getBusinessVarietyName(String businessVariety) {
		if(StringUtils.isNotBlank(businessVariety)){
			StringBuilder sb = new StringBuilder();
			
			String [] varietys = businessVariety.split(",");
			Map<String, String> map = new HashMap<String, String>();
			for (BusinessVariety s : BusinessVariety.values()) {
				map.put(String.valueOf(s.getCode()), s.getName());
			}
			for (int i = 0; i < varietys.length; i++) {
				if(map.containsKey(varietys[i])){
					sb.append(map.get(varietys[i]));
					if(i == varietys.length-1){
						sb.append(",");
					}
				}
			}
			
			return sb.toString();
		}
		return "";
	}

	//药店类型
	private String getChainTypeTextById(Integer chainType) {
		if(chainType == null){
			return "";
		}
		for (ChainType s : ChainType.values()){
			if(s.getCode() == chainType){
				return s.getName();
			}
		}
		return "";
	}
	
	//不相等返回true
	private boolean validateDateEquals(Date val1, Date val2){
		if(val1 == null && val2 == null){
			return false;
		}
		if((val1 == null && val2 != null) || (val1 != null && val2 == null)){
			return true;
		}
		if(val1.getTime() != val2.getTime() ){
			return true;
		}
		return false;
		
	}
	
	//不相等返回true
	private boolean validateBigDecimalEquals(BigDecimal val1, BigDecimal val2){
		if(val1 == null && val2 == null){
			return false;
		}
		if((val1 == null && val2 != null) || (val1 != null && val2 == null)){
			return true;
		}
		if(val1.compareTo(val2) != 0){
			return true;
		}
		return false;
		
	}
	
	//不相等返回true,用于Integer、Long
	private boolean validateObjectEquals(Object val1, Object val2){
		if(val1 == null && val2 == null){
			return false;
		}
		if((val1 == null && val2 != null) || (val1 != null && val2 == null)){
			return true;
		}
		if(val1.equals(val2)){
			return false;
		}
		return true;
		
	}
	
	//不相等返回true
	private boolean validateStringEquals(String val1, String val2){
		if(StringUtils.isBlank(val1)){
			val1 = "";
		}
		if(StringUtils.isBlank(val2)){
			val2 = "";
		}
		if(val1.equals(val2)){
			return false;
		}
		return true;
		
	}
	
	//优化Object显示
	private Object optimizeObject(Object val1){
		if(val1 == null){
			val1 = "";
		}
		return val1;
		
	}
	
	//优化String显示
	private String optimizeString(String val1){
		if(StringUtils.isBlank(val1)){
			val1 = "";
		}
		return val1;
		
	}

	//付款时间单位（0-每月；1-每周)
	private Object getPaymentTimeUnitName(Integer paymentTimeUnit) {
		if(paymentTimeUnit == null){
			return "";
		}
		if(0 == paymentTimeUnit){
			return "每月";
		}
		return "每周";
	}

	//付款账期单位0-天；1-月
	private Object getPaymentPeriodUnitName(Integer paymentPeriodUnit) {
		if(paymentPeriodUnit == null){
			return "";
		}
		if(0 == paymentPeriodUnit){
			return "天";
		}
		return "月";
	}

	//付款条款（0-现结；1-账期）
	private Object getPaymentProvisionName(Integer paymentProvision) {
		if(paymentProvision == null){
			return "";
		}
		if(0 == paymentProvision){
			return "现结";
		}
		return "账期";
	}

	//配货价格类型
	private String getDistrPriceTypeName(Integer distrPriceType) {
		if(distrPriceType == null){
			return "";
		}
		for (DistrPriceType s : DistrPriceType.values()){
			if(s.getCode() == distrPriceType){
				return s.getName();
			}
		}
		return "";
	}

	//结算方式名称
	private Object getSettlementModeName(Integer settlementMode) {
		if(settlementMode == null){
			return "";
		}
		if(0 == settlementMode){
			return "零售缴款";
		}
		return "应收账款";
	}

}
