package com.rograndec.feijiayun.chain.business.system.enterprise.service.impl;

import com.rograndec.feijiayun.chain.business.basic.store.dao.EnterpriseGroupMapper;
import com.rograndec.feijiayun.chain.business.basic.store.dao.SaleAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.store.dao.SaleCircleMapper;
import com.rograndec.feijiayun.chain.business.basic.store.dao.StoreLevelMapper;
import com.rograndec.feijiayun.chain.business.basic.store.entity.EnterpriseGroup;
import com.rograndec.feijiayun.chain.business.basic.store.entity.SaleArea;
import com.rograndec.feijiayun.chain.business.basic.store.entity.SaleCircle;
import com.rograndec.feijiayun.chain.business.basic.store.entity.StoreLevel;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreEnterpriseResponseVO;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserAdministrationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserPersonalMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserPersonal;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.LocationMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Location;
import com.rograndec.feijiayun.chain.business.system.enterprise.exception.EnterpriseBizException;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseModifyRecordService;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseService;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseBusinessVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EntrepriseValidateVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.BusinessScopeMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Service
public class EnterpriseServiceImpl implements EnterpriseService{
	
	private static final Log logger = LogFactory.getLog(EnterpriseServiceImpl.class);
	
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	
	@Autowired
	private BusinessScopeMapper businessScopeMapper;
	
	@Autowired
	private EnterpriseBusinessMapper enterpriseBusinessMapper;
	
	@Autowired
	private EnterpriseModifyRecordService enterpriseModifyRecordService;
	
	@Autowired
	private UserAdministrationMapper userAdministrationMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserPersonalMapper userPersonalMapper;
	
	@Autowired
	private ManageConfigMapper manageConfigMapper;
	
	@Autowired
	private LocationMapper locationMapper;
	
	@Autowired
	private SaleAreaMapper saleAreaMapper;
	
	@Autowired
	private StoreLevelMapper storeLevelMapper;
	
	@Autowired
	private SaleCircleMapper saleCircleMapper;
	
	@Autowired
	private EnterpriseGroupMapper enterpriseGroupMapper;
	
	@Autowired
    private PriceOrderMapper priceOrderMapper;


	@Override
	public List<BusinessScope> queryBusinessScopeByBusinessVariety(UserVO loginUser, String businessVariety){
		Enterprise enterprise = queryEnterpriseById(loginUser.getEnterpriseId(), "enterprise");
		if(enterprise == null){

			throw new EnterpriseBizException(EnterpriseBizException.NOT_ENTERPRISE,"查询不到企业信息");
		}

		List<BusinessScope> scopeList = getBusinessScopeByBusinessVariety(enterprise.getParentId()==0?enterprise.getId():enterprise.getParentId(), businessVariety);
		return scopeList;
	}

	@Override
	public Enterprise queryEnterpriseById(Long enterpriseId, String type) {
		Enterprise en = enterpriseMapper.selectByPrimaryKey(enterpriseId);
		return setUser2Enterprise(type,en);
	}

	@Override
	public Enterprise queryEnterpriseById4StatusEnable(Long enterpriseId, String type) {
		Enterprise en = enterpriseMapper.selectByPrimaryKeyEnable(enterpriseId);
		return setUser2Enterprise(type,en);
	}

	public Enterprise setUser2Enterprise(String type,Enterprise en){
		if("store".equals(type) && en != null && en.getAdminId() != null && en.getAdminId() != 0){
			List<UserAdministration> adminList = userAdministrationMapper.selectUserAdministrationByUserId(en.getAdminId());
			if(adminList != null && adminList.size() > 0){
				en.setUserName(adminList.get(0).getLoginAccount());
				en.setPassWord(adminList.get(0).getPassword());
				en.setPassWordConfirmation(adminList.get(0).getPasswordConfirm());
			}

			List<UserPersonal> personalList = userPersonalMapper.selectUserPersonalByUserId(en.getAdminId());
			if(personalList != null && personalList.size() > 0){
				en.setAdminPhone(personalList.get(0).getMobilePhone());
				en.setAdminEmail(personalList.get(0).getEmail());
			}
		}
		if(en != null && StringUtils.isNotBlank(en.getBusinessScopeId())){
			en.setEnterpriseId(en.getId());
			List<Integer> ids = getIntegerArray(en.getBusinessScopeId());
			en.setBusinessScopeIdArray(ids);
			
		}
		if(en != null && StringUtils.isNotBlank(en.getBusinessVariety())){
			List<Integer> ids = getIntegerArray(en.getBusinessVariety());
			en.setBusinessVarietyArray(ids);
		}
		return en;
	}
	
	private List<Integer> getIntegerArray(String businessScopeId) {
		if(StringUtils.isBlank(businessScopeId)){
			return null;
		}else{
			return Arrays.asList(businessScopeId.split(",")).stream().map(m -> {
				return Integer.parseInt(m.trim());
			}).collect(Collectors.toList());
		}
	}

	@Override
	public Enterprise queryEnterpriseAuxiliary(Enterprise en){
		
		if(en != null){
			en.setChainTypeName(ChainType.getName(en.getChainType()));
			en.setEconomicTypeName(EconomyType.getName(en.getEconomicType()));
			en.setBusinessModeName(ModeOperation.getName(en.getBusinessMode()));
			en.setMedicalFlagName(MedicalFlag.getName(en.getMedicalFlag()));
			if(StringUtils.isNotBlank(en.getBusinessScopeId())){
				
				try{
					String[] businessScopeIds = en.getBusinessScopeId().split(",");
					
					List<Long> list = new ArrayList<Long>();
					for (String var : businessScopeIds) {
						if(StringUtils.isNotBlank(var)){
							list.add(Long.parseLong(var));
						}
					}
					
					List<BusinessScope> scopeList = businessScopeMapper.getBusinessScopeByBusinessScopeId(
							(en.getParentId()==null||en.getParentId()==0)?en.getId():en.getParentId(), list);
					StringBuilder sb = new StringBuilder();
					if(scopeList != null){
						for (BusinessScope scope : scopeList) {
							sb.append(scope.getName()).append(";");
						}
					}
					en.setBusinessScopeName(sb.toString());
				} catch (Exception e) {
					logger.error("获取经营范围失败", e);
					e.printStackTrace();
				}
			}
			
			en.setGroupName(getGroupNameById(en.getGroupId()));
			
			en.setSaleAreaName(getSaleAreaNameById(en.getSaleAreaId()));
			
			en.setSaleCircleName(getSaleCircleNameById(en.getSaleCircleId()));
			
			en.setStoreLevelName(getStoreLevelNameById(en.getStoreLevelId()));
		}
		return en;
	}

	private String getStoreLevelNameById(Long storeLevelId) {
		StoreLevel level = storeLevelMapper.selectByPrimaryKey(storeLevelId);
		return level == null ? "" : level.getName();
	}

	private String getSaleCircleNameById(Long saleCircleId) {
		SaleCircle circle = saleCircleMapper.selectByPrimaryKey(saleCircleId);
		return circle==null?"":circle.getName();
	}

	private String getSaleAreaNameById(Long saleAreaId) {
		SaleArea area = saleAreaMapper.selectByPrimaryKey(saleAreaId);
		return area==null?"":area.getName();
	}

	private String getGroupNameById(Long groupId) {
		EnterpriseGroup group = enterpriseGroupMapper.selectByPrimaryKey(groupId);
		return group == null ? "" : group.getName();
	}

	@Override
	public List<BusinessScope> getBusinessScopeByBusinessVariety(
			Long enterpriseId, String businessVariety) {
		List<Long> list = new ArrayList<Long>();
		if(StringUtils.isNotBlank(businessVariety)){
			for (String var : businessVariety.split(",")) {
				if(StringUtils.isNotBlank(var) && !"null".equals(var)){
					list.add(Long.parseLong(var));
				}
			}
		}
		if(list.size() > 0){
			return businessScopeMapper.getBusinessScopeByBusinessVariety(enterpriseId, list);
		}else{
			return new ArrayList<BusinessScope>();
		}
	}
	
	public static void main(String[] args) {
		String businessVariety = null;
		
		for (String var : businessVariety.split(",")) {
			if(StringUtils.isNotBlank(var)){
				System.out.println(Long.parseLong(var));
			}
		}
	}

	@Override
	public List<BusinessScope> getBusinessScopeByBusinessVariety(
			Long enterpriseId, List<Long> businessVarietys) {

		return businessScopeMapper.getBusinessScopeByBusinessVariety(enterpriseId, businessVarietys);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String saveEnterprise(EnterpriseVO enterpriseVO,
			EnterpriseBusinessVO enterpriseBusinessVO, UserVO loginUser, String reason) {
		
		//查询旧的
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseVO.getId());
		
		if(enterprise == null){
			return "企业ID异常，查不到企业数据！";
		}
		
		Enterprise oldEnterprise = copyOldEnterprise(enterprise);
		
		Enterprise newEnterprise = setEnterprise(enterpriseVO, enterprise, loginUser);
	
		//查询旧的
		EnterpriseBusiness enterpriseBusiness = enterpriseBusinessMapper.selectByPrimaryKey(enterpriseBusinessVO==null?null:enterpriseBusinessVO.getId());
		
		EnterpriseBusiness oldEnterpriseBusiness = copyOldEnterpriseBussiness(enterpriseBusiness);
		
		String msg = validateEnterpriseVoData(enterpriseVO, enterprise, enterpriseBusiness, loginUser);
		if(StringUtils.isNotBlank(msg)){
			return msg;
		}
		
		EnterpriseBusiness newEnterpriseBusiness = setEnterpriseBusiness(enterpriseBusinessVO, enterpriseBusiness, enterprise, loginUser);

		if(enterpriseBusiness != null){
			enterpriseBusinessMapper.updateByPrimaryKeySelective(newEnterpriseBusiness);
		}else if (newEnterpriseBusiness != null){
			enterpriseBusinessMapper.insertSelective(newEnterpriseBusiness);
		}
		
		enterpriseMapper.updateByPrimaryKey(newEnterprise);
		
		
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		
		try {
			singleThreadExecutor.execute(() -> {
				enterpriseModifyRecordService.saveEnterpriseModifyRecord(oldEnterprise, newEnterprise, 
						oldEnterpriseBusiness, newEnterpriseBusiness, loginUser, reason);
			});
		} catch (Exception e) {
			logger.error("保存企业修改记录失败！", e);
			e.printStackTrace();
		}finally{
			singleThreadExecutor.shutdown();
		}
		return "";
	}

	private EnterpriseBusiness copyOldEnterpriseBussiness(
			EnterpriseBusiness enterpriseBusiness) {
		EnterpriseBusiness oldEnterpriseBusiness = new EnterpriseBusiness();
		
		if(enterpriseBusiness != null){
			try {
				BeanUtils.copyProperties(oldEnterpriseBusiness, enterpriseBusiness);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return oldEnterpriseBusiness;
	}

	private Enterprise copyOldEnterprise(Enterprise enterprise) {

		Enterprise oldEnterprise = new Enterprise();
		
		if(enterprise != null){
			try {
				BeanUtils.copyProperties(oldEnterprise, enterprise);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return oldEnterprise;
	}

	/**
	 * @Description: 验证保存信息
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年8月30日 上午10:36:55 
	 * @param enterpriseVO
	 * @param sb
	 * @return 
	 * @return String
	 */
	private String validateEnterpriseVoData(EnterpriseVO enterpriseVO, Enterprise enterprise, 
			EnterpriseBusiness enterpriseBusiness, UserVO user) {
		
		ManageConfig manageConfig = manageConfigMapper.selectManageConfigByEnterpriseId(
				(enterprise.getParentId()==null||enterprise.getParentId()==0) ? enterprise.getId() : enterprise.getParentId());
		
		StringBuilder sb = new StringBuilder();
		
		if(manageConfig != null && manageConfig.getQualityControl() == 1){//开启基础数据质量控制，验证
		
			if(enterpriseVO.getBusinessManId() == null || enterpriseVO.getBusinessManId() == 0){
				sb.append("开启基础数据质量控制，企业负责人必填！");
			}
			if(enterpriseVO.getLegalManId() == null || enterpriseVO.getLegalManId() == 0){
				sb.append("开启基础数据质量控制，法定代表人必填！");
			}
			if(enterpriseVO.getQualityOfficerId() == null || enterpriseVO.getQualityOfficerId() == 0){
				sb.append("开启基础数据质量控制，质量负责人必填！");
			}
			if(StringUtils.isBlank(enterpriseVO.getBankName())){
				sb.append("开启基础数据质量控制，开户银行必填！");
			}
			if(StringUtils.isBlank(enterpriseVO.getBankAccount())){
				sb.append("开启基础数据质量控制，开户账号必填！");
			}
			if(StringUtils.isBlank(enterpriseVO.getBankAccountName())){
				sb.append("开启基础数据质量控制，开户户名必填！");
			}
			if(enterpriseVO.getBusinessVarietyArray()==null || enterpriseVO.getBusinessVarietyArray().size() == 0){
				sb.append("开启基础数据质量控制，经营品种必填！");
			}
			if(enterpriseVO.getBusinessScopeIdArray() == null || enterpriseVO.getBusinessScopeIdArray().size() == 0){
				sb.append("开启基础数据质量控制，经营范围必填！");
			}
		}	
		if(!user.getEnterpriseId().equals(enterpriseVO.getId())){
			sb.append("当前用户无操作企业基本信息权限！");
		}
		if(enterpriseBusiness != null && !user.getEnterpriseId().equals(enterpriseBusiness.getEnterpriseId())){
			sb.append("当前用户无操作企业业务信息权限！");
		}
		if(StringUtils.isBlank(enterpriseVO.getName())){
			sb.append("企业名称不能为空！");
		}
		return sb.toString();
	}

	private Enterprise setEnterprise(EnterpriseVO enterpriseVO, Enterprise newEnterprise, UserVO loginUser) {
		newEnterprise.setId(enterpriseVO.getId());
		newEnterprise.setChainType(enterpriseVO.getChainType());
		newEnterprise.setCode(enterpriseVO.getCode());
		newEnterprise.setPinyin(enterpriseVO.getPinyin());
		newEnterprise.setName(enterpriseVO.getName());
		newEnterprise.setEconomicType(enterpriseVO.getEconomicType());
		newEnterprise.setBusinessMode(enterpriseVO.getBusinessMode());
		newEnterprise.setRegisterMoney(enterpriseVO.getRegisterMoney());
		newEnterprise.setProvinceId(enterpriseVO.getProvinceId());
		newEnterprise.setProvinceName(getLocationName(enterpriseVO.getProvinceId()));
		newEnterprise.setCityId(enterpriseVO.getCityId());
		newEnterprise.setCityName(getLocationName(enterpriseVO.getCityId()));
		newEnterprise.setAreaId(enterpriseVO.getAreaId());
		newEnterprise.setAreaName(getLocationName(enterpriseVO.getAreaId()));
		newEnterprise.setCompanyAddress(enterpriseVO.getCompanyAddress());
		newEnterprise.setStorageAddress(enterpriseVO.getStorageAddress());
		newEnterprise.setPostcode(enterpriseVO.getPostcode());
		newEnterprise.setTel(enterpriseVO.getTel());
		newEnterprise.setFax(enterpriseVO.getFax());
		newEnterprise.setEmail(enterpriseVO.getEmail());
		newEnterprise.setSite(enterpriseVO.getSite());
		newEnterprise.setAcreage(enterpriseVO.getAcreage());
		newEnterprise.setMonthly(enterpriseVO.getMonthly());
		newEnterprise.setEmployeeNum(enterpriseVO.getEmployeeNum());
		newEnterprise.setShopDate(enterpriseVO.getShopDate());
		newEnterprise.setRelocationDate(enterpriseVO.getRelocationDate());
		newEnterprise.setReformDate(enterpriseVO.getReformDate());

		newEnterprise.setBusinessManId(enterpriseVO.getBusinessManId());
		User useBus = userMapper.selectByPrimaryKey(enterpriseVO.getBusinessManId());
		newEnterprise.setBusinessManCode(useBus==null?"":useBus.getCode());
		newEnterprise.setBusinessManName(useBus==null?"":useBus.getName());
		
		newEnterprise.setLegalManId(enterpriseVO.getLegalManId());
		User useLeg = userMapper.selectByPrimaryKey(enterpriseVO.getLegalManId());
		newEnterprise.setLegalManCode(useLeg==null?"":useLeg.getCode());
		newEnterprise.setLegalManName(useLeg==null?"":useLeg.getName());

		newEnterprise.setQualityOfficerId(enterpriseVO.getQualityOfficerId());
		User useQua = userMapper.selectByPrimaryKey(enterpriseVO.getQualityOfficerId());
		newEnterprise.setQualityOfficerCode(useQua==null?"":useQua.getCode());
		newEnterprise.setQualityOfficerName(useQua==null?"":useQua.getName());
		
		newEnterprise.setBankName(enterpriseVO.getBankName());
		newEnterprise.setBankAccount(enterpriseVO.getBankAccount());
		newEnterprise.setBankAccountName(enterpriseVO.getBankAccountName());
		
		StringBuilder busVarietys = new StringBuilder();
		if(enterpriseVO.getBusinessVarietyArray() != null && enterpriseVO.getBusinessVarietyArray().size() > 0){
			for (int i = 0; i < enterpriseVO.getBusinessVarietyArray().size(); i++) {
				busVarietys.append(enterpriseVO.getBusinessVarietyArray().get(i));
				if(i != enterpriseVO.getBusinessVarietyArray().size() - 1){
					busVarietys.append(",");
				}
			}
		}
		newEnterprise.setBusinessVariety(busVarietys.toString());
		
		newEnterprise.setMedicalFlag(enterpriseVO.getMedicalFlag());
		newEnterprise.setMedicalCode(enterpriseVO.getMedicalCode());
		newEnterprise.setCompanyAbout(enterpriseVO.getCompanyAbout());
			
		newEnterprise.setModifierId(loginUser.getUserId());
		newEnterprise.setModifierCode(loginUser.getUserCode());
		newEnterprise.setModifierName(loginUser.getUserName());
		newEnterprise.setUpdateTime(new Date());
		
		StringBuilder busIds = new StringBuilder();
		if(enterpriseVO.getBusinessScopeIdArray() != null && enterpriseVO.getBusinessScopeIdArray().size() > 0){
			for (int i = 0; i < enterpriseVO.getBusinessScopeIdArray().size(); i++) {
				busIds.append(enterpriseVO.getBusinessScopeIdArray().get(i));
				if(i != enterpriseVO.getBusinessScopeIdArray().size() - 1){
					busIds.append(",");
				}
			}
		}
		
		
		newEnterprise.setBusinessScopeId(busIds.toString());
		
		return newEnterprise;
	}
	
	private String getLocationName(Integer id) {
		Location lo = locationMapper.selectByPrimaryKey(id);
		return lo != null?lo.getName():"";
	}

	private EnterpriseBusiness setEnterpriseBusiness(
			EnterpriseBusinessVO vo,
			EnterpriseBusiness enterBus, Enterprise enterprise, UserVO loginUser) {
		
		if(vo == null){
			return null;
		}
		
		if(enterBus == null){
			EnterpriseBusiness bus = new EnterpriseBusiness();
			bus.setEnterpriseId(enterprise.getId());
			bus.setParentId(enterprise.getParentId());
			bus.setSettlementMode(vo.getSettlementMode());
			bus.setDistrPriceType(vo.getDistrPriceType());
			bus.setDistrPriceOrderId(vo.getDistrPriceOrderId());
			bus.setDistrPriceOrderName(vo.getDistrPriceOrderName());
			if(vo.getDistrPriceOrderId() != null && vo.getDistrPriceOrderId() != 0){
            	PriceOrder order = priceOrderMapper.selectByPrimaryKey(vo.getDistrPriceOrderId());
            	if(order != null){
            		bus.setDistrPriceOrderName(order.getName());
            	}	
            }
			bus.setAddRate(vo.getAddRate());
			bus.setPaymentProvision(vo.getPaymentProvision());
			bus.setPaymentPeriod(vo.getPaymentPeriod());
			bus.setPaymentPeriodUnit(vo.getPaymentPeriodUnit());
			bus.setPaymentTime(vo.getPaymentTime());
			bus.setPaymentTimeUnit(vo.getPaymentTimeUnit());
				
			bus.setCreaterId(loginUser.getUserId());
			bus.setCreaterCode(loginUser.getUserCode());
			bus.setCreaterName(loginUser.getUserName());
			bus.setCreateTime(new Date());
			bus.setModifierId(loginUser.getUserId());
			bus.setModifierCode(loginUser.getUserCode());
			bus.setModifierName(loginUser.getUserName());
			bus.setUpdateTime(new Date());
			return bus;
		}else{
			enterBus.setSettlementMode(vo.getSettlementMode());
			enterBus.setDistrPriceType(vo.getDistrPriceType());
			enterBus.setDistrPriceOrderId(vo.getDistrPriceOrderId());
			enterBus.setDistrPriceOrderName(vo.getDistrPriceOrderName());
			if(vo.getDistrPriceOrderId() != null && vo.getDistrPriceOrderId() != 0){
            	PriceOrder order = priceOrderMapper.selectByPrimaryKey(vo.getDistrPriceOrderId());
            	if(order != null){
            		enterBus.setDistrPriceOrderName(order.getName());
            	}	
            }
			enterBus.setAddRate(vo.getAddRate());
			enterBus.setPaymentProvision(vo.getPaymentProvision());
			enterBus.setPaymentPeriod(vo.getPaymentPeriod());
			enterBus.setPaymentPeriodUnit(vo.getPaymentPeriodUnit());
			enterBus.setPaymentTime(vo.getPaymentTime());
			enterBus.setPaymentTimeUnit(vo.getPaymentTimeUnit());
			enterBus.setModifierId(loginUser.getUserId());
			enterBus.setModifierCode(loginUser.getUserCode());
			enterBus.setModifierName(loginUser.getUserName());
			enterBus.setUpdateTime(new Date());
			return enterBus;
		}
			
	}

	


	@Override
	public List<Enterprise> getChildrens(Long id){
		List<Enterprise> enterprises = enterpriseMapper.selectChildrenByPrimaryKey(id);
		return enterprises;
	}

	@Override
	public EntrepriseValidateVO queryEnterpriseValidateByEnterpriseId(
			Long enterpriseId) {
		EntrepriseValidateVO bean = new EntrepriseValidateVO();
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
		if(enterprise == null){
			
			return null;
		}
		ManageConfig manageConfig = manageConfigMapper.selectManageConfigByEnterpriseId(
				(enterprise.getParentId()==null||enterprise.getParentId()==0) ? enterprise.getId() : enterprise.getParentId());
		
		if(manageConfig == null){
			
			return null;
		}
	
		bean.setQualityControl(manageConfig.getQualityControl());
		return bean;
	}

	@Override
	public StoreEnterpriseResponseVO transfromationResponseEnterprise(
			Enterprise enterprise) {
		if(enterprise != null){
			StoreEnterpriseResponseVO vo = new StoreEnterpriseResponseVO();
			try {
				BeanUtils.copyProperties(vo, enterprise);
			} catch (Exception e) {
				logger.error("转换StoreEnterpriseResponseVO异常！", e);
				e.printStackTrace();
			}
			if(vo.getStoreLevelId() != null && vo.getStoreLevelId() == 0){
				vo.setStoreLevelId(null);
			}
			if(vo.getGroupId() != null && vo.getGroupId() == 0){
				vo.setGroupId(null);		
			}
			if(vo.getSaleAreaId() != null && vo.getSaleAreaId() == 0){
				vo.setSaleAreaId(null);
			}
			if(vo.getSaleCircleId() != null && vo.getSaleCircleId() == 0){
				vo.setSaleCircleId(null);
			}
			vo.setStatusName(PurchaseStatus.getName(vo.getStatus()));
			vo.setValidFlagName(ValidFlag.getName(vo.getValidFlag()));
			return vo;
		}
		return null;
	}
}
