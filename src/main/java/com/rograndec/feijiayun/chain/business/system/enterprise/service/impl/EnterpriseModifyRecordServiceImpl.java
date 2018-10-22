package com.rograndec.feijiayun.chain.business.system.enterprise.service.impl;

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

import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseModifyRecordWithBLOBs;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseQualificationConfig;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseModifyRecordService;
import com.rograndec.feijiayun.chain.business.system.set.dao.BusinessScopeMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.EnterpriseQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.DistrPriceType;
import com.rograndec.feijiayun.chain.common.constant.EconomyType;
import com.rograndec.feijiayun.chain.common.constant.ModeOperation;
import com.rograndec.feijiayun.chain.common.file.model.AccessFile;
import com.rograndec.feijiayun.chain.common.file.service.FileService;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;

@Service
public class EnterpriseModifyRecordServiceImpl implements EnterpriseModifyRecordService{

	private static final Log logger = LogFactory.getLog(EnterpriseModifyRecordServiceImpl.class);
	
	@Autowired
	EnterpriseModifyRecordMapper enterpriseModifyRecordMapper;
	
	@Autowired
	BusinessScopeMapper businessScopeMapper;
	
	@Autowired
	EnterpriseQualificationMapper enterpriseQualificationMapper;
	
	@Autowired
	FileService fileService;
	
	@Override
	public List<EnterpriseModifyRecordWithBLOBs> selectEnterpriseModifyRecordPage(
			int pageNo, int pageSize, Long enterpriseId, Page page) {
		
		Long totalRecord = enterpriseModifyRecordMapper.queryCountByEnterpriseId(enterpriseId);
		
		List<EnterpriseModifyRecordWithBLOBs> list = enterpriseModifyRecordMapper.selectEnterpriseModifyRecord(enterpriseId, page.getStart(), pageSize);
		
		page.setTotalRecord(totalRecord.intValue());
		
		return list;
	}
	
	/**
	 * 生成修改记录
	 */
	@Override
	public void saveEnterpriseModifyRecord(Enterprise oldEnterprise, Enterprise newEnterprise, 
			EnterpriseBusiness oldEnterpriseBusiness, EnterpriseBusiness newEnterpriseBusiness, UserVO vo,
			String reason){
		
		if(oldEnterprise == null){
			oldEnterprise = new Enterprise();
		}
		if(oldEnterpriseBusiness == null){
			oldEnterpriseBusiness = new EnterpriseBusiness();
		}
		if(newEnterpriseBusiness == null){
			newEnterpriseBusiness = new EnterpriseBusiness();
		}
		
//		System.out.println(JSONObject.toJSONString(oldEnterprise)); 
		
//		System.out.println(JSONObject.toJSONString(newEnterprise));
		
		Map<String, String> map = compareEnterprise(oldEnterprise, newEnterprise);
		
		map = compareEnterpriseBusiness(oldEnterpriseBusiness, newEnterpriseBusiness, map);
		
		if(map != null){
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
					enterpriseModifyRecordMapper.insertSelective(blob);
				}
				
			}
		}
	}
	
	/**
	 * TODO 生成资质修改记录 <br><pre>
	 * 覆盖方法saveEnterpriseConfigModifyRecord详细说明 <br>
	 * @author liuqun
	 * @date 2017年8月25日 上午10:48:46 </pre>
	 * @param 参数类型 参数名 说明
	 * @return 返回值类型 说明
	 * @throws 异常类型 说明
	 * @see com.rograndec.feijiayun.chain.system.enterprise.service.EnterpriseModifyRecordService#saveEnterpriseConfigModifyRecord(com.rograndec.feijiayun.chain.system.enterprise.entity.EnterpriseQualificationConfig, com.rograndec.feijiayun.chain.system.enterprise.entity.EnterpriseQualificationConfig, com.rograndec.feijiayun.chain.common.vo.UserVO, java.lang.String)
	 */
	@Override
	public void saveEnterpriseConfigModifyRecord(
			EnterpriseQualificationConfig enterpriseConfig,
			EnterpriseQualificationConfig newEnterpriseConfig,
			UserVO loginUser, String reason) {
		
		if(enterpriseConfig == null){
			enterpriseConfig = new EnterpriseQualificationConfig();
		}
		
		Map<String, String> map = compareEnterpriseConfig(enterpriseConfig, newEnterpriseConfig);
		
		if(map != null){
			if(StringUtils.isNotBlank(map.get("sbEnName"))){
			
				EnterpriseModifyRecordWithBLOBs blob = setBlobData(map, enterpriseConfig, newEnterpriseConfig, loginUser, reason);
				
				String[] enNames = blob.getColumnEnName().split("<br>");
				String[] sbChName = blob.getColumnChName().split("<br>");
				String[] sbOldContent = blob.getOldContent().split("<br>");
				String[] sbNewContent = blob.getNewContent().split("<br>");
				
				for (int i = 0; i< enNames.length; i++) {
					blob.setColumnEnName(enNames.length>i?enNames[i]:"");
					blob.setColumnChName(sbChName.length>i?sbChName[i]:"");
					blob.setOldContent(sbOldContent.length>i?sbOldContent[i]:"");
					blob.setNewContent(sbNewContent.length>i?sbNewContent[i]:"");
					enterpriseModifyRecordMapper.insertSelective(blob);
				}
				
			}	
		}
	}

	private EnterpriseModifyRecordWithBLOBs setBlobData(
			Map<String, String> map,
			EnterpriseQualificationConfig enterpriseConfig,
			EnterpriseQualificationConfig newEnterpriseConfig,
			UserVO vo, String reason) {
		EnterpriseModifyRecordWithBLOBs blob = new EnterpriseModifyRecordWithBLOBs();
		
		blob.setEnterpriseId(enterpriseConfig.getEnterpriseId());
		blob.setParentId(enterpriseConfig.getParentId());
		blob.setTableName("saas_enterprise_qualification_config");
		blob.setKeyId(enterpriseConfig.getEnterpriseId());
		blob.setReason(reason);//原因
		blob.setColumnEnName(map.get("sbEnName")==null?"":map.get("sbEnName"));
		blob.setColumnChName(map.get("sbChName")==null?"":map.get("sbChName"));
		blob.setOldContent(map.get("sbOldContent")==null?"":map.get("sbOldContent"));
		blob.setNewContent(map.get("sbNewContent")==null?"":map.get("sbNewContent"));
		blob.setCreaterId(vo.getUserId());
		blob.setCreaterCode(vo.getUserCode());
		blob.setCreaterName(vo.getUserName());
		blob.setCreateTime(new Date());
		blob.setModifierId(vo.getUserId());
		blob.setModifierCode(vo.getUserCode());
		blob.setModifierName(vo.getUserName());
		blob.setUpdateTime(new Date());
		return blob;
	}

	/**
	 * @Description: 对比企业资质表
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年8月25日 上午10:54:15 
	 * @param enterpriseConfig
	 * @param newEnterpriseConfig
	 * @return 
	 * @return Map<String,String>
	 */
	private Map<String, String> compareEnterpriseConfig(
			EnterpriseQualificationConfig enterpriseConfig,
			EnterpriseQualificationConfig newEnterpriseConfig) {
		Map<String,String> map = new HashMap<String, String>();
		StringBuilder sbEnName = new StringBuilder();
		StringBuilder sbChName = new StringBuilder();
		StringBuilder sbOldContent = new StringBuilder();
		StringBuilder sbNewContent = new StringBuilder();
		
		if(validateObjectEquals(enterpriseConfig.getQualificationId(), newEnterpriseConfig.getQualificationId())){
			sbEnName.append("qualification_name").append("<br>");
			sbChName.append("资质名称").append("<br>");
			sbOldContent.append(getQualificationName(enterpriseConfig.getQualificationId())).append("<br>");
			sbNewContent.append(getQualificationName(newEnterpriseConfig.getQualificationId())).append("<br>");
		}
		
		if(validateStringEquals(enterpriseConfig.getQualificationCode(), newEnterpriseConfig.getQualificationCode())){
			sbEnName.append("qualification_code").append("<br>");
			sbChName.append("资质编号").append("<br>");
			sbOldContent.append(optimizeString(enterpriseConfig.getQualificationCode())).append("<br>");
			sbNewContent.append(optimizeString(newEnterpriseConfig.getQualificationCode())).append("<br>");
		}
		
		if(validateDateEquals(enterpriseConfig.getValidUntil(), newEnterpriseConfig.getValidUntil())){
			sbEnName.append("valid_until").append("<br>");
			sbChName.append("有效期至").append("<br>");
			
			sbOldContent.append(enterpriseConfig.getValidUntil()==null?"":
				DateUtils.DateToString(enterpriseConfig.getValidUntil(), DateUtils.FMT_DATE)).append("<br>");
			sbNewContent.append(newEnterpriseConfig.getValidUntil()==null?"":
				DateUtils.DateToString(newEnterpriseConfig.getValidUntil(), DateUtils.FMT_DATE)).append("<br>");
		}
		
		if(validateObjectEquals(enterpriseConfig.getFileId(), newEnterpriseConfig.getFileId())){
			if((enterpriseConfig.getFileId()==null||enterpriseConfig.getFileId()==0)&&
					(newEnterpriseConfig.getFileId()==null||newEnterpriseConfig.getFileId()==0)){
			}else{
				sbEnName.append("file_id").append("<br>");
				sbChName.append("附件").append("<br>");
				sbOldContent.append(getFileName(enterpriseConfig.getFileId())).append("<br>");
				sbNewContent.append(getFileName(newEnterpriseConfig.getFileId())).append("<br>");
			}
		}
		
		
		map.put("sbEnName", sbEnName.toString());
		map.put("sbChName", sbChName.toString());
		map.put("sbOldContent", sbOldContent.toString());
		map.put("sbNewContent", sbNewContent.toString());
		return map;
	}

	private String getFileName(Long fileId) {
		if(fileId == null || fileId == 0){
			return "";
		}
		try {
			List<Long> idList = new ArrayList<>();
			idList.add(fileId);
			List<AccessFile> fileList = fileService.accessFilesByIdsOrFileKeys(idList, null);
			if(fileList != null){
				for (AccessFile accessFile : fileList) {
					return accessFile.getFileName();
				}
			}
		} catch (Exception e) {
			logger.error("保存企业资质修改记录-获取附件名称失败！", e);
		}	
		return "";
	}

	/**
	 * @Description: 查询资质名称
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年8月25日 上午11:12:24 
	 * @param qualificationId
	 * @return 
	 * @return String
	 */
	private String getQualificationName(Long qualificationId) {
		EnterpriseQualification qualification = enterpriseQualificationMapper.selectByPrimaryKey(qualificationId);
		return qualification == null ? "" : qualification.getName();
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
		blob.setCreateTime(new Date());
		blob.setModifierId(vo.getUserId());
		blob.setModifierCode(vo.getUserCode());
		blob.setModifierName(vo.getUserName());
		blob.setUpdateTime(new Date());
		return blob;
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
		
		map.put("sbEnName", sbEnName.toString());
		map.put("sbChName", sbChName.toString());
		map.put("sbOldContent", sbOldContent.toString());
		map.put("sbNewContent", sbNewContent.toString());
		return map;
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
		
		if(validateStringEquals(oldEnterprise.getStorageAddress(),newEnterprise.getStorageAddress())){
			sbEnName.append("storage_address").append("<br>");
			sbChName.append("仓库地址").append("<br>");
			sbOldContent.append(oldEnterprise.getStorageAddress()==null?"":oldEnterprise.getStorageAddress()).append("<br>");
			sbNewContent.append(newEnterprise.getStorageAddress()==null?"":newEnterprise.getStorageAddress()).append("<br>");
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
		/*if(validateDateEquals(oldEnterprise.getShopDate(), newEnterprise.getShopDate())){
			sbEnName.append("shop_date").append("<br>");
			sbChName.append("开店日期").append("<br>");
			sbOldContent.append(optimizeObject(oldEnterprise.getShopDate())).append("<br>");
			sbNewContent.append(optimizeObject(newEnterprise.getShopDate())).append("<br>");
		}*/
		
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
		
		//分店
		if(validateDateEquals(oldEnterprise.getShopDate(), newEnterprise.getShopDate())){
			sbEnName.append("shop_date").append("<br>");
			sbChName.append("开店日期").append("<br>");
			sbOldContent.append(oldEnterprise.getShopDate()==null?"":
				DateUtils.DateToString(oldEnterprise.getShopDate(), DateUtils.FMT_DATE)).append("<br>");
			sbNewContent.append(newEnterprise.getShopDate()==null?"":
				DateUtils.DateToString(newEnterprise.getShopDate(), DateUtils.FMT_DATE)).append("<br>");
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
			sbOldContent.append(getBusinessScopeName(oldEnterprise.getParentId()==null||oldEnterprise.getParentId()==0?oldEnterprise.getId():oldEnterprise.getParentId(), 
					oldEnterprise.getBusinessScopeId())).append("<br>");
			sbNewContent.append(getBusinessScopeName(newEnterprise.getParentId()==null||newEnterprise.getParentId()==0?newEnterprise.getId():newEnterprise.getParentId(), 
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
		
		map.put("sbEnName", sbEnName.toString());
		map.put("sbChName", sbChName.toString());
		map.put("sbOldContent", sbOldContent.toString());
		map.put("sbNewContent", sbNewContent.toString());
		return map;
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

	
}
