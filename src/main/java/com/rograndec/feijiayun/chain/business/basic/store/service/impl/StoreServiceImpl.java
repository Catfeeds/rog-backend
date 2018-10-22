package com.rograndec.feijiayun.chain.business.basic.store.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.validation.ValidationException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.rograndec.feijiayun.chain.business.basic.store.dao.EnterpriseGroupMapper;
import com.rograndec.feijiayun.chain.business.basic.store.dao.SaleAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.store.dao.SaleCircleMapper;
import com.rograndec.feijiayun.chain.business.basic.store.dao.StoreLevelMapper;
import com.rograndec.feijiayun.chain.business.basic.store.service.StoreModifyRecordService;
import com.rograndec.feijiayun.chain.business.basic.store.service.StoreService;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreEnterpriseBusinessVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreEnterpriseVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreExportVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreVO;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserAdministrationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserPersonalMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserRoleMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.GoodsManageMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.init.service.SysDataInitService;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowActionMapper;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowAction;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SubmitApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseQualificationConfigMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.LocationMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseQualificationConfigService;
import com.rograndec.feijiayun.chain.business.system.set.dao.BusinessScopeMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.NewSelectBean;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SelectBeanWithCode;
import com.rograndec.feijiayun.chain.common.component.ApprovalFlowComponent;
import com.rograndec.feijiayun.chain.common.component.CodeComponent;
import com.rograndec.feijiayun.chain.common.component.EnterpriseComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.BusinessVariety;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.DistrPriceType;
import com.rograndec.feijiayun.chain.common.constant.EconomyType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.MedicalFlag;
import com.rograndec.feijiayun.chain.common.constant.PaymentPeriodUnit;
import com.rograndec.feijiayun.chain.common.constant.PaymentProvision;
import com.rograndec.feijiayun.chain.common.constant.PaymentTimeUnit;
import com.rograndec.feijiayun.chain.common.constant.SettlementMode;
import com.rograndec.feijiayun.chain.common.constant.ValidFlag;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;

@Service
public class StoreServiceImpl implements StoreService{

	private static final Log logger = LogFactory.getLog(StoreServiceImpl.class);
	
	@Autowired
	EnterpriseMapper enterpriseMapper;
	
	@Autowired
	EnterpriseBusinessMapper enterpriseBusinessMapper;
	
	@Autowired
	EnterpriseGroupMapper enterpriseGroupMapper;
	
	@Autowired
	StoreLevelMapper storeLevelMapper;
	
	@Autowired
	SaleAreaMapper saleAreaMapper;
	
	@Autowired
	SaleCircleMapper saleCircleMapper;
	
	@Autowired
	StoreModifyRecordService storeModifyRecordService;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	UserAdministrationMapper administrationMapper;
	
	@Autowired
	UserPersonalMapper userPersonalMapper;
	
	@Autowired
	UserRoleMapper userRoleMapper;
	
	@Autowired
	CodeComponent codeComponent;
	
	@Autowired
	ManageConfigMapper manageConfigMapper;
	
	@Autowired
	LocationMapper locationMapper;
	
	@Autowired
	BusinessScopeMapper businessScopeMapper;
	
	@Autowired
	EnterpriseQualificationConfigService enterpriseQualificationConfigService;
	
	@Autowired
	EnterpriseQualificationConfigMapper enterpriseQualificationConfigMapper;
	
	@Autowired
	GoodsMapper goodsMapper;
	
	@Autowired
	SafetyStockMapper safetyStockMapper;
	
	@Autowired
	PriceOrderMapper priceOrderMapper;
	
	@Autowired
	PriceOrderDetailMapper priceOrderDetailMapper;
	
	@Autowired
	GoodsManageMapper goodsManageMapper;
	
	@Autowired
	SysDataInitService sysDataInitService;

	@Autowired
	private EnterpriseComponent enterpriseComponent;
	
	@Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

	@Autowired
	private ApprovalFlowComponent approvalFlowComponent;

	@Autowired
	private ApprovalFlowActionMapper approvalFlowActionMapper;
	
	@Override
	public List<StoreVO> selectStoreVoPage(int pageNo, int pageSize,
			Integer chainType, String key, Integer groupId, String businessManName,
			Integer distrPriceType, Integer storeLevelId, Integer saleAreaId,
			Integer saleCircleId, Integer paymentProvision, Long enterpriseId,
			Page page, String order, String sort,Integer approveStatus) {
		if(chainType != null && chainType == 0){
			chainType = null;
		}
		
		if("code".equals(order)){
			order = "e.code";
		}else if("chainTypeName".equals(order)){
			order = "e.chain_type";
		}else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "";
		}

		Long totalRecord = enterpriseMapper.queryCountByParams(enterpriseId, page.getStart(), pageSize, chainType, key,
				groupId, businessManName, distrPriceType, storeLevelId, saleAreaId, saleCircleId, paymentProvision,approveStatus);
		
		List<StoreVO> list = enterpriseMapper.selectStoreVoByParams(enterpriseId, page.getStart(), pageSize, chainType, key,
				groupId, businessManName, distrPriceType, storeLevelId, saleAreaId, saleCircleId, paymentProvision,
				order, sort,approveStatus);


		for(StoreVO storeVO : list){

			Integer status = storeVO.getApproveStatus();
			if(null != status){
				storeVO.setApproveStatusDesc(ApprovalFlowAuditStatusRecom.getApprovalFlowAuditStatusRecomEnum(status).getName());
			}
		}
		
		if(list != null){
			
			for (StoreVO storeVo : list) {
				storeVo.setChainTypeName(storeVo.getChainType()==null?"":ChainType.getName(storeVo.getChainType()));
				
				storeVo.setStatusName(storeVo.getStatus()==null?"":EnableStatus.getName(storeVo.getStatus()));
			}
		}
		
		page.setTotalRecord(totalRecord==null?0:totalRecord.intValue());
		
		return list;
	}

	

	



	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
	public String updateEnterprise(StoreEnterpriseVO storeEnterpriseVO,
			UserVO loginUser, String reason) throws Exception {




		String msg = validateEnterpriseVoData(storeEnterpriseVO, loginUser);
		if(StringUtils.isNotBlank(msg)){
			return msg;
		}
		
		StoreEnterpriseBusinessVO storeEnterpriseBusinessVO = storeEnterpriseVO.getStoreEnterpriseBusinessVO()
				==null?null:storeEnterpriseVO.getStoreEnterpriseBusinessVO();
		
		//查询旧的
		Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(storeEnterpriseVO.getId());

		if(enterprise == null){
			return "没有符合条件的门店数据";
		}
		
		/**
		 * 待审批和审批中不允许修改
		 */
		if(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.equals(enterprise.getApproveStatus())
				|| ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_IN.equals(enterprise.getApproveStatus())){
			throw new ValidationException("该门店审批状态处于"+ApprovalFlowAuditStatusRecom.getApprovalFlowAuditStatusRecomEnum(enterprise.getApproveStatus()).getName()+";不允许修改");
		}


		
		Enterprise oldEnterprise = copyOldEnterprise(enterprise);
		
		//查询旧的
		EnterpriseBusiness enterpriseBusiness = storeEnterpriseBusinessVO == null ? null : enterpriseBusinessMapper.selectByPrimaryKey(storeEnterpriseBusinessVO.getId());

		if(null != enterpriseBusiness){
			/**
			 * 判断审批流程如果有在审批不允许修改自主控制
			 */
			Integer oldApprovalControl = enterpriseBusiness.getApprovalControl();
			Integer newApprovalControl = storeEnterpriseBusinessVO.getApprovalControl();

			if(!oldApprovalControl.equals(newApprovalControl) && ChainType.Headquarters.getCode() != enterprise.getChainType()){
				/**
				 * 分店加盟店,并且要修改自主控制
				 */
				/**
				 * 如果该审批流程关联的审批有未完成的，不允许修改
				 */
				List<Integer> status = new ArrayList<>();
				status.add(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue());
				status.add(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_IN.getValue());
				List<ApprovalFlowAction> waitApprovalFlows = approvalFlowActionMapper.getListByEnterpriseAndStatusRecom(enterprise.getId(),status);
				if(!CollectionUtils.isEmpty(waitApprovalFlows)) return "该门店有待审核的审批流程,不允许修改审批流程自主流程控制";


				/**
				 * 分店,加盟店 检查出修改了审批控制,并且是修改为自主控制时
				 */
				if(newApprovalControl == 1){

					approvalFlowComponent.initRequirePlanApprovalFlow(loginUser,enterprise.getId(),enterprise.getParentId(),enterprise.getName(),enterprise.getChainType(),false);
				}

			}

		}
		
		EnterpriseBusiness oldEnterpriseBusiness = copyOldEnterpriseBussiness(enterpriseBusiness);
		
		String errMsg = validateEnterpriseBusinessData(enterprise, enterpriseBusiness, loginUser);
			
		if(StringUtils.isNotBlank(errMsg)){
			return errMsg;
		}
		
		Enterprise newEnterprise = setUpdateEnterprise(storeEnterpriseVO, enterprise, loginUser);
		/**
		 * 每次修改都添加待审核状态,重新提交审批流程
		 */
		newEnterprise.setApproveStatus(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue());
		enterpriseMapper.updateByPrimaryKey(newEnterprise);

		EnterpriseBusiness newEnterpriseBusiness = enterpriseComponent.setEnterpriseBusiness(storeEnterpriseBusinessVO, enterpriseBusiness, enterprise, loginUser);
		
		if(enterpriseBusiness == null){//新增
			enterpriseBusinessMapper.insertSelective(newEnterpriseBusiness);
		}else{//修改
			enterpriseBusinessMapper.updateByPrimaryKeySelective(newEnterpriseBusiness);
		}
		
		
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		
		try {
			singleThreadExecutor.execute(() -> {
				storeModifyRecordService.saveEnterpriseModifyRecord(oldEnterprise, newEnterprise, 
						oldEnterpriseBusiness, newEnterpriseBusiness, loginUser, reason);
			});
		} catch (Exception e) {
			logger.error("保存门店修改记录失败！", e);
			e.printStackTrace();
		}finally{
			singleThreadExecutor.shutdown();
		}


		// --------------dongyang.du----------------------
		// 如果是门店，自营店或加盟店“质量管理”选择“总部控制”，则将该门店的管理配置信息中的
		// 基础数据质量控制、业务流程质量控制、POS质量控制、审批流程控制   同步为总部配置

		if(newEnterpriseBusiness.getQualityControl() == 0){// 总部控制

			ManageConfig  parentConfig = manageConfigMapper.selectManageConfigByEnterpriseId(loginUser.getEnterpriseId());// 当前总部
			ManageConfig  branchConfig  = manageConfigMapper.selectManageConfigByEnterpriseId(newEnterpriseBusiness.getEnterpriseId());
			branchConfig.setQualityControl(parentConfig.getQualityControl());
			branchConfig.setApprovalControl(parentConfig.getApprovalControl());
			branchConfig.setBusinessControl(parentConfig.getBusinessControl());
			branchConfig.setPosControl(parentConfig.getPosControl());
			manageConfigMapper.updateByPrimaryKeySelective(branchConfig);
		}

		//------------------------------------------------


		//审批流对象
		// 查询一条默认初始化的审批流/
		Integer chainType = loginUser.getChainType();
		SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(loginUser.getEnterpriseId(),loginUser.getEnterpriseName(),
				loginUser.getUserId(), loginUser.getUserName(), loginUser.getChainType(), loginUser.getParentId(),
				chainType.equals(ChainType.Headquarters.getCode()) ? loginUser.getEnterpriseId() : loginUser.getParentId(),
				"0103", newEnterprise.getId(), newEnterprise.getCode(), newEnterprise.getName());
		approvalFlowComponent.apply(submitApprovalFlowVO,loginUser);
		
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
	
	private String validateEnterpriseBusinessData(Enterprise enterprise,
			EnterpriseBusiness enterpriseBusiness, UserVO user) {
		StringBuilder sb = new StringBuilder();
		if(enterpriseBusiness != null && !enterprise.getId().equals(enterpriseBusiness.getEnterpriseId())){
			sb.append("门店业务对象不属于该门店！");
		}
		if(!user.getEnterpriseId().equals(enterprise.getParentId())){
			sb.append("当前用户无操作门店权限！");
		}
		if(enterpriseBusiness != null && !user.getEnterpriseId().equals(enterpriseBusiness.getParentId())){
			sb.append("当前用户无操作门店业务对象权限！");
		}
		return sb.toString();
	}

	

	
	/**
	 * @Description: 验证修改信息
	 * @author liuqun
	 * @version 1.0 
	 * @date 2017年8月30日 上午10:36:55 
	 * @param enterpriseVO
	 * @param sb
	 * @return 
	 * @return String
	 */
	private String validateEnterpriseVoData(StoreEnterpriseVO enterpriseVO, UserVO loginUser) {
		StringBuilder sb = new StringBuilder();
		
		ManageConfig manageConfig = manageConfigMapper.selectManageConfigByEnterpriseId(loginUser.getEnterpriseId());
		
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
			if(enterpriseVO.getBusinessVarietyArray() == null || enterpriseVO.getBusinessVarietyArray().size() == 0){
				sb.append("开启基础数据质量控制，经营品种必填！");
			}
			if(enterpriseVO.getBusinessScopeIdArray() == null || enterpriseVO.getBusinessScopeIdArray().size() == 0){
				sb.append("开启基础数据质量控制，经营范围必填！");
			}
		}	
		if(StringUtils.isBlank(enterpriseVO.getName())){
			sb.append("门店名称不能为空！");
		}
		
		List<NewSelectBean> beanList = saleAreaMapper.selectSaleAreaByEnterpriseId(loginUser.getEnterpriseId());
		Map<Long, Long> areaMap = new HashMap<>();
		if(beanList != null){
			for (NewSelectBean newSelectBean : beanList) {
				areaMap.put(newSelectBean.getId(), newSelectBean.getId());
			}
		}
		if(enterpriseVO.getSaleAreaId() != null && enterpriseVO.getSaleAreaId() != 0 && !areaMap.containsKey(enterpriseVO.getSaleAreaId())){
			sb.append("销售片区ID错误，请重新选择！");
		}
		
		StoreEnterpriseBusinessVO storeb = enterpriseVO.getStoreEnterpriseBusinessVO();
		if(storeb != null && storeb.getDistrPriceType() == 0 && (storeb.getDistrPriceOrderId() == null || storeb.getDistrPriceOrderId() == 0)){
			sb.append("配货价格类型为价格清单，必须选择一个价格清单！");
		}
		
		return sb.toString();
	}
	

//	public static void main(String[] args) {
//		System.out.println(getExclusiveTax(BigDecimal.valueOf(117), BigDecimal.valueOf(17)).doubleValue());
//	}


	
	
	private Enterprise setUpdateEnterprise(StoreEnterpriseVO enterpriseVO, Enterprise newEnterprise, UserVO loginUser) {
		newEnterprise.setId(enterpriseVO.getId());
		newEnterprise.setChainType(enterpriseVO.getChainType());
		try {
			newEnterprise.setCode(StringUtils.isNotBlank(enterpriseVO.getCode())?enterpriseVO.getCode():codeComponent.generate("Enterprise", 4, loginUser.getEnterpriseId()));
		} catch (Exception e) {
			logger.error("生成门店编码失败！", e);
			e.printStackTrace();
		}
		newEnterprise.setPinyin(enterpriseVO.getPinyin());
		newEnterprise.setName(enterpriseVO.getName());
		newEnterprise.setGroupId(enterpriseVO.getGroupId()==null?0:enterpriseVO.getGroupId());
		newEnterprise.setEconomicType(enterpriseVO.getEconomicType());
		newEnterprise.setBusinessMode(enterpriseVO.getBusinessMode());
		newEnterprise.setRegisterMoney(enterpriseVO.getRegisterMoney());
		newEnterprise.setProvinceId(enterpriseVO.getProvinceId());
		newEnterprise.setProvinceName(enterpriseComponent.getLocationName(enterpriseVO.getProvinceId()));
		newEnterprise.setCityId(enterpriseVO.getCityId());
		newEnterprise.setCityName(enterpriseComponent.getLocationName(enterpriseVO.getCityId()));
		newEnterprise.setAreaId(enterpriseVO.getAreaId());
		newEnterprise.setAreaName(enterpriseComponent.getLocationName(enterpriseVO.getAreaId()));
		newEnterprise.setCompanyAddress(enterpriseVO.getCompanyAddress());
		newEnterprise.setPostcode(enterpriseVO.getPostcode());
		newEnterprise.setTel(enterpriseVO.getTel());
		newEnterprise.setFax(enterpriseVO.getFax());
		newEnterprise.setEmail(enterpriseVO.getEmail());
		newEnterprise.setSite(enterpriseVO.getSite());
		newEnterprise.setSaleAreaId(enterpriseVO.getSaleAreaId()==null?0:enterpriseVO.getSaleAreaId());
		newEnterprise.setSaleCircleId(enterpriseVO.getSaleCircleId()==null?0:enterpriseVO.getSaleCircleId());
		newEnterprise.setStoreLevelId(enterpriseVO.getStoreLevelId()==null?0:enterpriseVO.getStoreLevelId());
		newEnterprise.setAcreage(enterpriseVO.getAcreage());
		newEnterprise.setMonthly(enterpriseVO.getMonthly());
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
		newEnterprise.setStatus(enterpriseVO.getStatus());
		newEnterprise.setValidFlag(enterpriseVO.getValidFlag());
		
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
	



	@Override
	public List<SelectBeanWithCode> selectStoreGroupByEnterpriseId(Long enterpriseId, Integer type) {
		//enterprise表chain_type --> saas_enterprise_group表type
		if(type != null){
			switch (type) {
			case 1:
				type = 0;
				break;
			case 2:
				type = 1;
				break;
			default:
				type = 2;
				break;
			}
		}
		List<SelectBeanWithCode> beanList = enterpriseGroupMapper.selectChildStoreGroupByEnterpriseId(enterpriseId, type);
		return beanList;
	}

	@Override
	public List<NewSelectBean> selectStoreLevelByEnterpriseId(Long enterpriseId) {
		List<NewSelectBean> beanList = storeLevelMapper.selectStoreLevelByEnterpriseId(enterpriseId);
		return beanList;
	}

	@Override
	public List<NewSelectBean> selectSaleAreaByEnterpriseId(Long enterpriseId) {
		List<NewSelectBean> beanList = saleAreaMapper.selectSaleAreaByEnterpriseId(enterpriseId);
		return beanList;
	}

	@Override
	public List<NewSelectBean> selectSaleCircleByEnterpriseId(Long enterpriseId) {
		List<NewSelectBean> beanList = saleCircleMapper.selectSaleCircleByEnterpriseId(enterpriseId);
		return beanList;
	}

	@Override
	public List<StoreExportVO> selectStoreVoPage(Integer chainType, String key,
			Integer groupId, String businessManName, Integer distrPriceType,
			Integer storeLevelId, Integer saleAreaId, Integer saleCircleId,
			Integer paymentProvisionId, Long enterpriseId, String order, String sort) {
		
		if("code".equals(order)){
			order = "e.code";
		}else if("chainTypeName".equals(order)){
			order = "e.chain_type";
		}else{
			order = null;
		}
		
		if(!"asc".equals(sort) && !"desc".equals(sort)){
			sort = "";
		}
		
		List<StoreExportVO> list = enterpriseMapper.selectExportStoreVoByParams(enterpriseId, chainType, key,
				groupId, businessManName, distrPriceType, storeLevelId, saleAreaId, saleCircleId, paymentProvisionId, order, sort);
		
		if(list != null){
			for (StoreExportVO store : list) {
				store.setChainTypeName(store.getChainType()==null?"":ChainType.getName(store.getChainType()));
				store.setProvinceName(store.getProvinceName() + store.getCityName() + store.getAreaName());
				store.setEconomicTypeName(store.getEconomicType()==null?"":EconomyType.getName(store.getEconomicType()));
				store.setBusinessVarietyName(String.valueOf(getBusinessVarietyName(store.getBusinessVariety())));
				store.setBusinessScopeName(String.valueOf(getBusinessScopeName(store.getId(), store.getBusinessScopeId())));
				store.setMedicalFlagName(store.getMedicalFlag()==null?"":MedicalFlag.getName(store.getMedicalFlag()));
				store.setStatusName(store.getStatus()==null?"":EnableStatus.getName(store.getStatus()));
				store.setValidFlagName(store.getValidFlag()==null?"":ValidFlag.getName(store.getValidFlag()));
				store.setSettlementModeName(store.getSettlementMode()==null?"":SettlementMode.getName(store.getSettlementMode()));
				store.setDistrPriceTypeName(store.getDistrPriceType()==null?"":DistrPriceType.getName(store.getDistrPriceType()));
				store.setPaymentProvisionName(store.getPaymentProvision()==null?"":PaymentProvision.getName(store.getPaymentProvision()));
				store.setPaymentPeriodUnitName(store.getPaymentPeriodUnit()==null?"":PaymentPeriodUnit.getName(store.getPaymentPeriodUnit())+"-"
						+store.getPaymentPeriod());
				store.setPaymentTimeUnitName(store.getPaymentTimeUnit()==null?"":PaymentTimeUnit.getName(store.getPaymentTimeUnit())+"-"
						+store.getPaymentTime());
				
				store.setShopDateString(store.getShopDate()==null?"":DateUtils.DateToString(store.getShopDate(), DateStyle.YYYY_MM_DD));
				store.setRelocationDateString(store.getRelocationDate()==null?"":DateUtils.DateToString(store.getRelocationDate(), DateStyle.YYYY_MM_DD));
				store.setReformDateString(store.getReformDate()==null?"":DateUtils.DateToString(store.getReformDate(), DateStyle.YYYY_MM_DD));
			}
		}
		
		return list;
	}
	
	
	//经营范围
	private Object getBusinessScopeName(Long enterpriseId, String businessScopeId) {
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
	

	@Override
	public void excelExport(OutputStream output, List<StoreExportVO> storeVoList, UserVO loginUser) {
		
		//转换一下显示日期
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("code","编码");
        map.put("pinyin","检索码");
        map.put("name","门店名称");
        map.put("chainTypeName","门店类型");
        map.put("businessManName","企业负责人");
        map.put("legalManName","法定代表人");
        map.put("qualityOfficerName","质量负责人");
        map.put("provinceName","行政区域");
        map.put("economicTypeName","经济类型");
        map.put("registerMoney","注册资金");
        map.put("companyAddress","公司地址");
        map.put("bankName","开户银行");
        map.put("bankAccountName","开户户名");
        map.put("bankAccount","开户账号");
        map.put("email","邮箱");
        
        map.put("tel","电话");
        map.put("fax","传真");
        map.put("acreage","面积");
        map.put("site","网址");
        map.put("monthly","月租");
        map.put("shopDateString","开店日期");
        map.put("relocationDateString","最近搬迁日期");
        map.put("reformDateString","最近改造日期");
        
        map.put("businessVarietyName","经营品种");
        map.put("businessScopeName","经营范围");
        map.put("medicalFlagName","医保药店");
        map.put("statusName","状态");
        map.put("validFlagName","标识");
        
        map.put("settlementModeName","结算方式");
        map.put("distrPriceTypeName","配货价格");
        map.put("paymentProvisionName","付款条款");
        map.put("paymentPeriodUnitName","付款账期");
        map.put("paymentTimeUnitName","付款时间");

        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("门店导出");
        
        
        List<String> needTotalName = new ArrayList<>();
        purchaseGeneralComponent.commExcelExport(output, map, storeVoList, name, null, "", false, needTotalName);
		
	}
	
	
	private void createRowHeader(ExcelWriter writer) throws IOException {
        // 插入新行
        writer.insertRow(0);
        // 建立新单元格,索引值从0开始,表示第一列
        int k = 0;
        writer.createCell(k++, "序号");
        writer.createCell(k++, "编码");
        writer.createCell(k++, "检索码");
        writer.createCell(k++, "门店名称");
        writer.createCell(k++, "门店类型");
        writer.createCell(k++, "企业负责人");
        writer.createCell(k++, "法定代表人");
        writer.createCell(k++, "质量负责人");
        writer.createCell(k++, "行政区域");
        writer.createCell(k++, "经济类型");
        writer.createCell(k++, "注册资金");
        writer.createCell(k++, "公司地址");
        writer.createCell(k++, "开户银行");
        writer.createCell(k++, "开户户名");
        writer.createCell(k++, "开户账号");
        writer.createCell(k++, "邮箱");
        writer.createCell(k++, "电话");
        writer.createCell(k++, "传真");
        writer.createCell(k++, "面积");
        writer.createCell(k++, "网址");
        writer.createCell(k++, "月租");
        writer.createCell(k++, "开店日期");
        writer.createCell(k++, "最近搬迁日期");
        writer.createCell(k++, "最近改造日期");
        writer.createCell(k++, "经营品种");
        writer.createCell(k++, "经营范围");
        writer.createCell(k++, "医保药店");
        writer.createCell(k++, "状态");
        writer.createCell(k++, "标识");
        writer.createCell(k++, "结算方式");
        writer.createCell(k++, "配货价格");
        writer.createCell(k++, "付款条款");
        writer.createCell(k++, "付款账期");
        writer.createCell(k, "付款时间");
        // 结束行
        writer.endRow();
    }

	@Override
	public List<SelectBeanWithCode> getStoreUserSelectBeanByEnterpriseId(
			Long enterpriseId) {
		List<SelectBeanWithCode> list = new ArrayList<SelectBeanWithCode>();
		List<User> userList = userMapper.findUserByEnterpriseId(enterpriseId);
		SelectBeanWithCode bean = null;
		if(userList != null && userList.size() > 0){
			for (User user : userList) {
				
				bean = new SelectBeanWithCode();
				bean.setId(user.getId());
				bean.setName(user.getName());
				
				list.add(bean);
			}
		}
		return list;
	}



	@Override
	public List<SelectBeanWithCode> selectPriceOrderListByEnterpriseId(Long enterpriseId) {
		return priceOrderMapper.selectStorePriceOrderSelectBeanByEnterpriseId(enterpriseId);
	}


}
