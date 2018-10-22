package com.rograndec.feijiayun.chain.business.basic.supplier.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.*;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.*;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierService;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.*;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatus;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SubmitApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.BusinessScopeMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.EnterpriseQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.service.ManageConfigService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.SendPlace;
import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.reflect.ReflectUtils;
import com.rograndec.feijiayun.chain.utils.string.ChineseString;
import com.rograndec.feijiayun.chain.utils.string.StringSplit;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.validation.ValidationException;

import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by ST on 2017/8/23.
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierMapper supplierMapper;
    
    @Autowired
    private SupplierGroupMapper supplierGroupMapper;
    
    @Autowired
    private SupplierSalerMapper supplierSalerMapper;
    
    @Autowired
    private SupplierBusinessMapper supplierBusinessMapper;
    
    @Autowired
    private SupplierQualificationConfigMapper supplierQualificationConfigMapper;
    
    @Autowired
    private SupplierModifyRecordMapper supplierModifyRecordMapper;
    
    @Autowired
    private BusinessScopeMapper businessScopeMapper;
    
    @Autowired
    private ExcelComponent excelComponent;

	@Autowired
    private ApprovalFlowComponent approvalFlowComponent;

	@Autowired
	private CodeComponent codeComponent;

	@Autowired
	private EnterpriseQualificationMapper enterpriseQualificationMapper;

	@Autowired
	private ManageConfigMapper manageConfigMapper;

	@Autowired
	private ManageConfigService manageConfigService;

	@Autowired
	public SupplierComponent supplierComponent;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private FinanceComponent financeComponent;
	
	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@Override
    public Supplier getSupplierByCode(String code,Long enterpriseId){
        return supplierMapper.getSupplierByCode(enterpriseId,code);
    }

	@Override
	public Supplier getSupplierByCode2(String code,CommonParamSupplierAndGoods paramSupplierAndGoods){
		return supplierMapper.getSupplierByCode2(paramSupplierAndGoods,code);
	}
	@Override
	public List<Supplier> getSupplier(UserVO userVO) throws Exception {
//		supplierMapper.selectByEnterpriseId(enterpriseId);
		Integer chainType = userVO.getChainType();
		List<Supplier> supplierList = new ArrayList<>();
		List<Long> paramList = new ArrayList<>();
		if(ChainType.Division.getCode() == chainType){
			//加盟店
			List<Long> list = new ArrayList<>();
			list.add(userVO.getParentId());
			list.add(userVO.getEnterpriseId());
			supplierList.addAll(supplierMapper.selectByEnterpriseIdAndOwnerId(userVO.getParentId(),list,null));
		} else if(ChainType.Headquarters.getCode() == chainType){
			//总部
			supplierList.addAll(supplierMapper.selectByEnterpriseIdAndOwnerId(userVO.getEnterpriseId(),null,null));
		} else if(ChainType.Selfoperatedshop.getCode() == chainType){
			//自营店
			supplierList = supplierMapper.selectByEnterpriseIdAndOwnerId(userVO.getParentId(),Arrays.asList(userVO.getParentId()),null);
		}
		return supplierList;
	}

	@Override
	public List<Supplier> getSupplier4Add(UserVO userVO) throws Exception {
		//supplierMapper.selectByEnterpriseId(enterpriseId);
		Integer chainType = userVO.getChainType();
		List<Supplier> supplierList = new ArrayList<>();
		List<Long> list = new ArrayList<>();
		if(ChainType.Division.getCode() == chainType){
			//加盟店

			list.add(userVO.getEnterpriseId());
			supplierList.addAll(supplierMapper.selectByEnterpriseIdAndOwnerId(userVO.getParentId(),list,null));
		} else if(ChainType.Headquarters.getCode() == chainType){
			//总部
			list.add(userVO.getEnterpriseId());
			supplierList.addAll(supplierMapper.selectByEnterpriseIdAndOwnerId(userVO.getEnterpriseId(),list,null));
		} else if(ChainType.Selfoperatedshop.getCode() == chainType){
			//自营店
			list.add(userVO.getParentId());
			supplierList.addAll(supplierMapper.selectByEnterpriseIdAndOwnerId(userVO.getParentId(),list,null));
		}
		return supplierList;
	}


    /**
     * 根据条件查询供货商
     * @param param
     * @return
     */
    @Override
    public List<Supplier> getSupplierByParam(String param,Long enterpriseId) {
        List<Supplier> supplierList = new ArrayList<>();
        Supplier supplier = supplierMapper.getSupplierByCode(enterpriseId,param);
        if(supplier != null){
            supplierList.add(supplier);
            return supplierList;
        }
        supplierList = supplierMapper.getSupplierByLikeName(enterpriseId,param);
        if(supplierList != null && supplierList.size() > 0){
            List<Supplier> supplierList2 = supplierMapper.getSupplierByLikePinyin(enterpriseId,param);
            if(supplierList2 != null && supplierList2.size() > 0){
                supplierList.addAll(supplierList2);
            }
        } else {
            supplierList = supplierMapper.getSupplierByLikePinyin(enterpriseId, param);
        }
        return supplierList == null ? new ArrayList<>() : supplierList;
    }

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Page getBasicSupplier(Integer pageNo, Integer pageSize,Page page,String param, UserVO userVO, Integer nature, Integer groupId,
			String businessName, String purchaseName,Integer approveStatus,Integer ownerChainType,String ownerCode,String ownerName) {
		List<SupplierBasicVO> supplierVOList = new ArrayList<SupplierBasicVO>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("param", param);
		map.put("nature", nature);
		map.put("groupId", groupId);
		map.put("businessName", businessName);
		map.put("purchaseName", purchaseName);
        map.put("pageSize",pageSize);
        map.put("start",(pageNo-1)*pageSize);
        map.put("ownerChainType", ownerChainType);
		map.put("ownerCode", ownerCode);
        map.put("ownerName",ownerName);
		List<Long> list = new ArrayList<>();
		//企业ID
		supplierComponent.setDistinguishDivisionParamMap(userVO,map);
/**
 * 审核状态（查询全部不需要传值;0-待审核；1-已完成 2-审核被驳回;）
 * 审批状态（0-待审核；1-审核通过；2-审核拒绝；-2-审核撤回；-1-审核中） tips：0和 -1的情况不可以修改
 */
        if(null != approveStatus &&
                (approveStatus == ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue()
                        || approveStatus == ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue()
                        || approveStatus == ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue())){

            map.put("approveStatus", approveStatus);

        }
		supplierVOList = supplierMapper.getBasicSupplier(map);
		for (SupplierBasicVO supplierBasicVO : supplierVOList) {
			if (String.valueOf(Nature.Wholesale_enterprise.getCode()).equals(supplierBasicVO.getNature())){
				supplierBasicVO.setNature(Nature.Wholesale_enterprise.getName());
			}
			if (String.valueOf(Nature.manufacturer.getCode()).equals(supplierBasicVO.getNature())) {
				supplierBasicVO.setNature(Nature.manufacturer.getName());
			}
			supplierBasicVO.setIsOwner(supplierBasicVO.getOwnerId().equals(userVO.getEnterpriseId())? 1:0);
			if(userVO.getChainType().equals(ChainType.Headquarters.getCode())) {
				supplierBasicVO.setFranchisedStoreFlag(supplierBasicVO.getOwnerId().equals(userVO.getEnterpriseId())? 0:1);
				
				//总部增加所属机构类型、所属机构编码、所属机构名称
				supplierBasicVO.setOwnerChainTypeName(ChainType.getName(supplierBasicVO.getOwnerChainType()));
				Enterprise en = null;
				if(supplierBasicVO.getEnterpriseId().equals(supplierBasicVO.getOwnerId())){
					en = enterpriseMapper.selectByPrimaryKey(supplierBasicVO.getEnterpriseId());
				}else{
					en = enterpriseMapper.selectByPrimaryKey(supplierBasicVO.getOwnerId());
				}
				supplierBasicVO.setOwnerCode(en.getCode());
				supplierBasicVO.setOwnerName(en.getName());
			}else {
				supplierBasicVO.setFranchisedStoreFlag(supplierBasicVO.getOwnerId().equals(userVO.getEnterpriseId())? 1:0);
			}
			
		}
		//总记录数
		Integer totalRecord = supplierMapper.getCountSupplier(map);
		page.setResult(supplierVOList);
        page.setTotalRecord(totalRecord);
		return page;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public SupplierDetailVO getDetailSupplier(UserVO user,Long id) {
		Long enterpriseId = user.getEnterpriseId();
		Supplier s =  supplierMapper.getSupplierById(id);
		String businessScopeId = s.getBusinessScopeId();
		SupplierDetailVO vo = new SupplierDetailVO();
		vo = supplierMapper.getDetailSupplier(id);
		//拼接经营范围
		
		if (businessScopeId != null && !businessScopeId.equals("")) {
			String[] split = businessScopeId.split(",");
			List<BusinessScope> list = businessScopeMapper.getScope(Arrays.asList(split));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < list.size(); i++){
				sb.append(list.get(i).getName());
				if (i < (list.size() - 1)){
					sb.append(";");
				}
			}
			vo.setBusinessScopeName(sb.toString());
		}else{
			vo.setBusinessScopeName("");
		}
		//查询业务信息对象
		SupplierBusinessVO supplierBusinessVO = supplierMapper.getBusiness(vo.getId());
		/**
		 * paymentTimeStr，deliveryTimeStr，sendTimeStr，accountTimeStr
		 */
		Integer paymentTimeUnit = supplierBusinessVO.getPaymentTimeUnit();
		Integer deliveryTimeUnit = supplierBusinessVO.getDeliveryTimeUnit();
		Integer sendTimeUnit = supplierBusinessVO.getSendTimeUnit();
		Integer accountTimeUnit = supplierBusinessVO.getAccountTimeUnit();
		String paymentTime = supplierBusinessVO.getPaymentTime();
		String deliveryTime = supplierBusinessVO.getDeliveryTime();
		String sendTime = supplierBusinessVO.getSendTime();
		String accountTime = supplierBusinessVO.getAccountTime();
		String paymentTimeStr = "";
		String deliveryTimeStr = "";
		String sendTimeStr = "";
		String accountTimeStr = "";
		paymentTimeStr = ConvertToChDate(paymentTimeUnit,paymentTime);
		deliveryTimeStr = ConvertToChDate(deliveryTimeUnit,deliveryTime);
		sendTimeStr = ConvertToChDate(sendTimeUnit,sendTime);
		accountTimeStr = ConvertToChDate(accountTimeUnit,accountTime);
		supplierBusinessVO.setPaymentTimeStr(paymentTimeStr);
		supplierBusinessVO.setDeliveryTimeStr(deliveryTimeStr);
		supplierBusinessVO.setSendTimeStr(sendTimeStr);
		supplierBusinessVO.setAccountTimeStr(accountTimeStr);
		vo.setSupplierBusinessVO(supplierBusinessVO);
		//查询资质对象集合
		List<SupplierQulificationVO> list = supplierMapper.getQulification(vo.getId());
		vo.setSupplierQulificationVO(list);
		return vo;
	}

	private String ConvertToChDate(Integer unit, String time) {
		boolean flag = StringUtils.isEmpty(time);
		if (flag){
			return "";
		}else {
			String[] timeSplit = time.split(",");
			StringBuilder sb = new StringBuilder();
			//每月
			if (unit == 0){
				for (int i = 0; i < timeSplit.length; i++){
					if (StringUtils.isEmpty(timeSplit[i])){
						continue;
					}
					if (i == timeSplit.length - 1){
						sb.append((Integer.parseInt(timeSplit[i]) + 1) + "号");
					}else {
						sb.append((Integer.parseInt(timeSplit[i]) + 1) + "号" + ",");
					}
				}
			}else if (unit == 1){
				//每周
				for (int i = 0; i < timeSplit.length; i++){
					if (StringUtils.isEmpty(timeSplit[i])){
						continue;
					}
					if (i == timeSplit.length - 1){
						sb.append("周" + convertToChWeek(Integer.parseInt(timeSplit[i]) + 1));
					}else {
						sb.append("周" + convertToChWeek(Integer.parseInt(timeSplit[i]) + 1)+ ",");
					}
				}
			}
			return sb.toString();
		}
	}

	private String convertToChWeek(int i) {
    	switch (i){
			case 1:
				return "一";
			case 2:
				return "二";
			case 3:
				return "三";
			case 4:
				return "四";
			case 5:
				return "五";
			case 6:
				return "六";
			case 7:
				return "日";
			default:
				return "";
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveDetailSupplier(UserVO user, SupplierDetailVO supplierDetailVO) throws Exception{
    	//******************************************************信息验证**************************************************************
		SupplierSalerVO supplierSalerVO = supplierDetailVO.getSupplierSalerVO();
		/**
		 * 分店 读取的供货单位编码规则是读取的总部的规则
		 */
		int type = manageConfigMapper.judgeCodeStyle(user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
		StringBuilder sb = new StringBuilder();
		Long enterpriseId = ChainType.getHeadEnterpriseId(user);
		if (type == SupplierCodeRule.GROUPANDSTREAM.getCode()) {
			String groupCode = getGroupCode(supplierDetailVO.getGroupId());
			sb.append(groupCode);
			sb.append(codeComponent.generate("Supplier", 4, enterpriseId));
		}else if (type == SupplierCodeRule.STREAM.getCode()) {
			sb.append(codeComponent.generate("Supplier", 4, enterpriseId));
		}else if (type == SupplierCodeRule.SELF.getCode()) {
			sb.append(supplierDetailVO.getCode());
		}
		supplierDetailVO.setCode(sb.toString());
		//验证供货单位名称(如果当前企业的供货单位编码规则是自定义编码，则需要判断自定义编码不能重复 + 供货单位名称也不能重复);
		if (type == SupplierCodeRule.SELF.getCode()){
			if (StringUtils.isEmpty(supplierDetailVO.getCode())){
				throw new BusinessException(SysCode.FAIL.getCode(),"当前供货单位编码不能为空!");
			}
			if (ChineseString.isChinese(supplierDetailVO.getCode())){
				throw new BusinessException(SysCode.FAIL.getCode(),"当前供货单位编码不能输入中文!");
			}
			/**
			 * 供货单位编码判断重复逻辑
			 * 供货单位的编码不能和同一企业以及旗下的加盟店重复
			 */
			Supplier hasCodeSupplier = supplierMapper.hasSupplierCode(supplierDetailVO.getCode(),user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
			if (hasCodeSupplier != null){
				throw new BusinessException(SysCode.FAIL.getCode(),"当前供货单位编码已存在!");
			}
		}
		if (StringUtils.isEmpty(supplierDetailVO.getName())){
			throw new BusinessException(SysCode.FAIL.getCode(),"当前供货单位名称不能为空!");
		}
		//校验供货单位的经营品种和经营范围集合大于等于销售人员的经营品种和经营范围的范围;
		String supplierBusinessVariety = supplierDetailVO.getBusinessVariety();
		String supplierBusinessScopeId = supplierDetailVO.getBusinessScopeId();

		SupplierSalerVO saler = supplierDetailVO.getSupplierSalerVO();
		if (saler != null){
			String authorizedVariety = saler.getAuthorizedVariety();
			String authorizedVarietyScope = saler.getAuthorizedVarietyScope();

			StringBuilder businessVariety = new StringBuilder();
			StringBuilder businessScopeId = new StringBuilder();
			if (supplierBusinessVariety != null && supplierBusinessScopeId != null && authorizedVariety != null && authorizedVarietyScope != null){
				String[] supplierVarietyArray = supplierBusinessVariety.split(",");
				String[] supplierScopeIdArray = supplierBusinessScopeId.split(",");
				String[] salerVarietyArray = authorizedVariety.split(",");
				String[] salerVarietyScope = authorizedVarietyScope.split(",");
				for (String su:supplierVarietyArray) {
					for (String sa:salerVarietyArray) {
						if (su.equals(sa)){
							businessVariety.append(su).append(",");
						}
					}
				}
				for (String suScope:supplierScopeIdArray) {
					for (String saScope:salerVarietyScope) {
						if (suScope.equals(saScope)){
							businessScopeId.append(suScope).append(",");
						}
					}
				}
				saler.setAuthorizedVariety(businessVariety.toString());
				saler.setAuthorizedVarietyScope(businessScopeId.toString());
				supplierDetailVO.setSupplierSalerVO(saler);
			}
		}
		//******************************************************信息验证**************************************************************
		//总部人员进行保存供货单位基本信息
		Map<String,Object> map = new HashMap<String,Object>();
		Supplier sp = new Supplier();
		CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(supplierDetailVO, sp);
		UserEnterpriseUtils.setUserCreateOrModify(sp,user,true);
		sp.setApproveStatus(ApprovalFlowAuditStatus.DETAIL_STATUS_WAIT.getValue());
		Long headEnterpriseId = ChainType.getHeadEnterpriseId(user);
		sp.setOwnerId(user.getEnterpriseId());
		sp.setEnterpriseId(headEnterpriseId);
		sp.setChainType(user.getChainType());
		/**
		 * 查看
		 */
		supplierMapper.insertSelective(sp);
		Long supplierId = 0L;
		supplierId = sp.getId();
		supplierDetailVO.setId(supplierId);
		//供货单位的Id
		AddSupplierSalerWithUpdateSupplier(supplierSalerVO,supplierId,headEnterpriseId,user,sp);

		/**
		 * 保存业务信息
		 */
		Map<String,Object> mapbusiness = new HashMap<String,Object>();
		//补充:applicant_code + purchaser_code
		//业务对象
		SupplierBusinessVO supplierBusinessVO = supplierDetailVO.getSupplierBusinessVO();
		User purchase = userMapper.selectByPrimaryKey(supplierBusinessVO.getPurchaserId());
		if (purchase != null){
			supplierBusinessVO.setPurchaserCode(purchase.getCode());
			supplierBusinessVO.setPurchaserName(purchase.getName());
		}
		User applicant = userMapper.selectByPrimaryKey(supplierBusinessVO.getApplicantId());
		if (applicant != null){
			supplierBusinessVO.setApplicantCode(applicant.getCode());
			supplierBusinessVO.setApplicantName(applicant.getName());
		}
		mapbusiness.put("user", user);
		mapbusiness.put("supplierDetailVO", supplierDetailVO);
		mapbusiness.put("enterpriseId", sp.getEnterpriseId());
		supplierBusinessMapper.saveBusinessSupplier(mapbusiness);
		/**
		 * 保存资质信息
		 */
		//判断资质必填字段是否填写
		List<SupplierQulificationVO> supplierQulificationVO = supplierDetailVO.getSupplierQulificationVO();
		if (supplierQulificationVO.size() > 0) {
			for (SupplierQulificationVO sq : supplierQulificationVO) {
				sq.setSupplierId(supplierId);
				Map<String,Object> mapQualification = new HashMap<String,Object>();
				mapQualification.put("sq", sq);
				mapQualification.put("user", user);
				mapQualification.put("enterpriseId", sp.getEnterpriseId());
				supplierQualificationConfigMapper.saveQualificationSupplier(mapQualification);
			}
		}
		//审批流对象
		// 查询一条默认初始化的审批流/
		Integer chainType = user.getChainType();
        SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(user.getEnterpriseId(),user.getEnterpriseName(),
                user.getUserId(), user.getUserName(), user.getChainType(), user.getParentId(),
                chainType.equals(ChainType.Headquarters.getCode()) ? user.getEnterpriseId() : user.getParentId(),
                "0101", sp.getId(), sp.getCode(), sp.getName());
        approvalFlowComponent.apply(submitApprovalFlowVO,user);

		financeComponent.initSupplierBalance(user, sp);
	}

	private void AddSupplierSalerWithUpdateSupplier(SupplierSalerVO supplierSalerVO, Long supplierId, Long headEnterpriseId, UserVO user, Supplier sp) throws Exception{
		//如果供货单位中的销售人员是新增的,那么供货单位表需要修改销售人员信息
		if (supplierSalerVO != null){
			supplierSalerVO.setSupplierId(supplierId);
			SupplierSaler supplierSaler = new SupplierSaler();
			supplierSaler.setSupplierId(supplierSalerVO.getSupplierId());
			supplierSaler.setEnterpriseId(headEnterpriseId);
			supplierSaler.setCode(supplierSalerVO.getCode() == null ? "" : supplierSalerVO.getCode());
			supplierSaler.setName(supplierSalerVO.getName() == null ? "" : supplierSalerVO.getName());
			supplierSaler.setIdNum(supplierSalerVO.getIdNum() == null ? "" : supplierSalerVO.getIdNum());
			supplierSaler.setIdValidUntil(supplierSalerVO.getIdValidUntil());
			supplierSaler.setIdFileId(supplierSalerVO.getIdFileId());
			supplierSaler.setCertificateNum(supplierSalerVO.getCertificateNum() == null ? "" : supplierSalerVO.getCertificateNum());
			supplierSaler.setCertificateValidUntil(supplierSalerVO.getCertificateValidUntil());
			supplierSaler.setAuthorizedVariety(supplierSalerVO.getAuthorizedVariety() == null ? "" : supplierSalerVO.getAuthorizedVariety());
			supplierSaler.setAuthorizedVarietyScope(supplierSalerVO.getAuthorizedVarietyScope() == null ? "" : supplierSalerVO.getAuthorizedVarietyScope());
			supplierSaler.setCertificateFileId(supplierSalerVO.getCertificateFileId());
			supplierSaler.setTelephone(supplierSalerVO.getTelephone() == null ? "" : supplierSalerVO.getTelephone());
			supplierSaler.setFax(supplierSalerVO.getFax() == null ? "" : supplierSalerVO.getFax());
			supplierSaler.setMobilePhone(supplierSalerVO.getMobilePhone() == null ? "" : supplierSalerVO.getMobilePhone());
			supplierSaler.setEmail(supplierSalerVO.getEmail() == null ? "" : supplierSalerVO.getEmail());
			supplierSaler.setWechatNum(supplierSalerVO.getWechatNum() == null ? "" : supplierSalerVO.getWechatNum());
			supplierSaler.setQqNum(supplierSalerVO.getQqNum() == null ? "" : supplierSalerVO.getQqNum());
			supplierSaler.setStatus(supplierSalerVO.getStatus());
			supplierSaler.setRemark(supplierSalerVO.getRemark() == null ? "" : supplierSalerVO.getRemark());
			UserEnterpriseUtils.setUserCreateOrModify(supplierSaler,user,true);
			supplierSalerMapper.insertSelective(supplierSaler);
			//更新供货单位中销售人员的信息
			Supplier newSp = new Supplier();
			newSp.setId(sp.getId());
			newSp.setSaleManId(supplierSaler.getId());
			newSp.setSaleManCode(supplierSaler.getCode());
			newSp.setSaleManName(supplierSaler.getName());
			supplierMapper.updateByPrimaryKeySelective(newSp);
		}
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateDetailSupplier(UserVO user, SupplierDetailVO supplierDetailVO) throws Exception {
    	//******************************************************信息验证********************************************************
		Supplier preSupplier = supplierMapper.selectByPrimaryKey(supplierDetailVO.getId());
		//权限校验(加盟店不能更新总部数据)
        if (!preSupplier.getOwnerId().equals(user.getEnterpriseId())){
            throw new BusinessException(SysCode.FAIL.getCode(),"您没有修改权限");
        }

		/**
		 * 审批状态处于待审核和审核中时
		 */
		if(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.equals(preSupplier.getApproveStatus())
				|| ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_IN.equals(preSupplier.getApproveStatus())){
			throw new ValidationException("该供货单位审批状态处于"+ApprovalFlowAuditStatusRecom.getApprovalFlowAuditStatusRecomEnum(preSupplier.getApproveStatus()).getName()+";不允许修改");
		}

		String preName = preSupplier.getName();
		if (StringUtils.isEmpty(supplierDetailVO.getName())){
			throw new BusinessException(SysCode.FAIL.getCode(),"当前供货单位名称不能为空!");
		}

		SupplierBusinessVO supplierBusinessVO = supplierDetailVO.getSupplierBusinessVO();
		Supplier oldSupplier = supplierMapper.selectByPrimaryKey(supplierDetailVO.getId());
		SupplierBusiness oldSupplierBusiness = supplierBusinessMapper.selectByPrimaryKey(supplierBusinessVO.getId());
		/**
		 * 第一步先修改供货单位基表
		 */
		Supplier supplier = Supplier.getSupplierAddSupplierVO(supplierDetailVO,user,false);
		/**
		 * 修改主表审批流状态
		 */
        //审核流逻辑处理start
        Integer status = ApprovalFlowAuditStatus.DETAIL_STATUS_PASS.getValue();// 默认状态为审核通过状态
        ManageConfig flow = manageConfigService.getManageConfig(user);
		if (flow.getApprovalControl() == 1) {// 如果审批流开启
			status = ApprovalFlowAuditStatus.DETAIL_STATUS_WAIT.getValue();// 待审核状态
		}
        //审核流逻辑处理end
		supplier.setApproveStatus(status);
		/**
		 * 修改主表销售人员
		 */
		Long saleManId = supplierDetailVO.getSaleManId();
		if (saleManId != null){
			SupplierSaler sale = supplierSalerMapper.selectByPrimaryKey(saleManId);
			if (sale != null){
				supplier.setSaleManCode(sale.getCode());
				supplier.setSaleManName(sale.getName());
			}
		}
		supplierMapper.updateByPrimaryKeySelective(supplier);
		/**
		* 第二步修改业务信息
		 */
		SupplierBusiness supplierBusiness = SupplierBusiness.getSupplierBusinessAddSupplierBusinessVO(supplierDetailVO,supplierBusinessVO,user,false);
		/**
		 * 修改业务信息的采购人员和申请人员
		 */
		User purchase = userMapper.selectByPrimaryKey(supplierBusiness.getPurchaserId());
		if (purchase != null){
			supplierBusiness.setPurchaserCode(purchase.getCode());
			supplierBusiness.setPurchaserName(purchase.getName());
		}
		User applicant = userMapper.selectByPrimaryKey(supplierBusiness.getApplicantId());
		if (applicant != null){
			supplierBusiness.setApplicantCode(applicant.getCode());
			supplierBusiness.setApplicantName(applicant.getName());
		}
		supplierBusinessMapper.updateByPrimaryKeySelective(supplierBusiness);
		/**
		 * 第三步修改资质信息---因为产品修改相关资质变化情况，原来逻辑需要修改
		 */
		//先删除已经被移除的资质信息
		/*List<Long> deleteSupplierQualificationConfigIds = supplierDetailVO.getDeleteSupplierQualificationConfigIds();
		if (deleteSupplierQualificationConfigIds != null){
			for (Long id : deleteSupplierQualificationConfigIds) {
				supplierQualificationConfigMapper.deleteByPrimaryKey(id);
			}
		}
		
		List<SupplierQualificationConfig> supplierQualificationConfigs = SupplierQualificationConfig.getSupplierQualificationAddSupplierQualificationVO(supplierDetailVO,supplierDetailVO.getSupplierQulificationVO(),user,false);
		if (supplierQualificationConfigs != null){
			for (SupplierQualificationConfig sq : supplierQualificationConfigs) {
				 if(null != sq.getId()){
		               supplierQualificationConfigMapper.updateByPrimaryKeySelective(sq);
		           }else {
		        	   supplierQualificationConfigMapper.insertSelective(sq);
		           }
			}
		}*/
		//先删除对应供货单位所有资质信息
		Long supplierId = supplierDetailVO.getId();
		supplierQualificationConfigMapper.deleteBySupplierId(supplierId);
		//重新插入对应的资质信息
		List<SupplierQualificationConfig> supplierQualificationConfigs = SupplierQualificationConfig.getSupplierQualificationAddSupplierQualificationVO(supplierDetailVO,supplierDetailVO.getSupplierQulificationVO(),user);
		if (supplierQualificationConfigs != null){
			for (SupplierQualificationConfig sq : supplierQualificationConfigs) {
				supplierQualificationConfigMapper.insertSelective(sq);
			}
		}

		/**
		 * 记录保存记录
		 */
		List<SupplierModifyRecordWithBLOBs> supplierModifyRecordWithBLOBs = getQualificationModifyRecord(supplierDetailVO,user,supplier, supplierBusiness,oldSupplier, oldSupplierBusiness,supplier.getUpdateTime());

        for(SupplierModifyRecordWithBLOBs ur : supplierModifyRecordWithBLOBs){
        	ur.setRemark(supplierDetailVO.getUpdateDetail());
        	supplierModifyRecordMapper.insertSelective(ur);
        }

		//审批流对象
		// 查询一条默认初始化的审批流/
		Integer chainType = user.getChainType();
		SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(user.getEnterpriseId(),user.getEnterpriseName(),
				user.getUserId(), user.getUserName(), user.getChainType(), user.getParentId(),
				chainType.equals(ChainType.Headquarters.getCode()) ? user.getEnterpriseId() : user.getParentId(),
				"0101", supplier.getId(), supplier.getCode(), supplier.getName());
		approvalFlowComponent.apply(submitApprovalFlowVO,user);
	}
	
	private List<SupplierModifyRecordWithBLOBs> getQualificationModifyRecord(SupplierDetailVO supplierDetailVO,
																			 UserVO user, Supplier supplier, SupplierBusiness supplierBusiness, Supplier oldSupplier,
																			 SupplierBusiness oldSupplierBusiness, Date updateTime) throws Exception {
		Map<String,Object> newSupplierMap = getFieldsMap(supplier);
        Map<String,Object> newSupplierBuseinessMap = getFieldsMap(supplierBusiness);


        Map<String,Object> supplierMap = getFieldsMap(oldSupplier);
        Map<String,Object> supplierBuseinessMap = getFieldsMap(oldSupplierBusiness);
        
        Map<String,String> fieldSkipMap = fieldSkipMap();
        
        List<SupplierModifyRecordWithBLOBs> supplierModifyRecord = getModifyRecordList(user,supplier,"saas_supplier"
                ,updateTime,supplierMap,newSupplierMap,fieldSkipMap);

        List<SupplierModifyRecordWithBLOBs> supplierAdministrationRecord = getModifyRecordList(user,supplier,"saas_supplier_business"
                ,updateTime,supplierBuseinessMap,newSupplierBuseinessMap,fieldSkipMap);
        supplierModifyRecord.addAll(supplierAdministrationRecord);
		return supplierModifyRecord;
	}

	private List<SupplierModifyRecordWithBLOBs> getModifyRecordList(
            UserVO user ,Supplier supplier,String tableName,Date updateTime
            , Map<String,Object> valMap
            ,Map<String,Object> newMap ,Map<String,String> fieldSkipMap){

        List<SupplierModifyRecordWithBLOBs> modifyRecordWithBLOBs = new ArrayList<>();

        for(Map.Entry<String,String> entry : fieldSkipMap.entrySet()){
            Object obj = valMap.get(entry.getKey());
            Object newObj = newMap.get(entry.getKey());
			if(obj != null ){
				/**
				 * 判断Bigdecimal是否相等
				 */
				if(obj instanceof BigDecimal && newObj instanceof BigDecimal && ((BigDecimal) obj).compareTo((BigDecimal) newObj) == 0){
					continue;
				/**
				 * 判断Date类型是否相等
				 */
				}else if (obj instanceof Date && newObj instanceof Date){
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
					Date pre = (Date)obj;
					String preStr = df.format(pre);
					Date now = (Date)newObj;
					String nowStr = df.format(now);
					if (!preStr.equals(nowStr)){
						obj = preStr;
						newObj = nowStr;
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry, obj, newObj);
					}
				}else if(!obj.equals(newObj)){
					//状态
					if ("status".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								EnableStatus.getName((int)obj),EnableStatus.getName((int)newObj));
						continue;
					}
					//标识
					if ("validFlag".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								ValidFlag.getName((int)obj), ValidFlag.getName((int)newObj));
						continue;
					}
					//nature 性质（0-批发企业；1-生产企业）
					if ("nature".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								Nature.getName((int)obj),Nature.getName((int)newObj));
						continue;
					}
					//供货分组ID
					if ("groupId".equals(entry.getKey())){
						SupplierGroup sg = supplierGroupMapper.selectByPrimaryKey((Long) obj);
						SupplierGroup sgL = supplierGroupMapper.selectByPrimaryKey((Long) newObj);
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								sg == null ? "" : sg.getName(),sgL == null ? "" : sgL.getName());
						continue;
					}
					//经济类型
					if ("economicType".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								EconomyType.getName((int)obj),EconomyType.getName((int)newObj));
						continue;
					}
					//经营方式
					if ("businessMode".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								BusinessMode.getName((int)obj),BusinessMode.getName((int)newObj));
						continue;
					}
					//paymentProvision
					if ("paymentProvision".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								PaymentProvision.getName((int)obj),PaymentProvision.getName((int)newObj));
						continue;
					}
					//paymentPeriodUnit
					if ("paymentPeriodUnit".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								PaymentPeriodUnit.getName((int)obj),PaymentPeriodUnit.getName((int)newObj));
						continue;
					}
					//paymentTimeUnit
					if ("paymentTimeUnit".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								PaymentTimeUnit.getName((int)obj),PaymentTimeUnit.getName((int)newObj));
						continue;
					}
					//deliveryTimeUnit
					if ("deliveryTimeUnit".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								DeliveryTimeUnit.getName((int)obj),DeliveryTimeUnit.getName((int)newObj));
						continue;
					}
					//deliveryMode
					if ("deliveryMode".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								SupplierDeliveryMode.getName((int)obj),SupplierDeliveryMode.getName((int)newObj));
						continue;
					}
					//sendTimeUnit
					if ("sendTimeUnit".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								SendTimeUnit.getName((int)obj),SendTimeUnit.getName((int)newObj));
						continue;
					}
					//serviceCircleUnit
					if ("serviceCircleUnit".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								ServiceCircleUnit.getName((int)obj),ServiceCircleUnit.getName((int)newObj));
						continue;
					}
					//AccountTimeUnit
					if ("accountTimeUnit".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								AccountTimeUnit.getName((int)obj),AccountTimeUnit.getName((int)newObj));
						continue;
					}
					//经营品种
					if ("businessVariety".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								LimitVarietyType.getName4CodeStr(obj.toString()),LimitVarietyType.getName4CodeStr(newObj.toString()));
						continue;
					}
					//经营范围
					if ("businessScopeId".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								getLimitVarietyRangeDescStr(obj.toString()),getLimitVarietyRangeDescStr(newObj.toString()));
						continue;
					}
					//managementMode
					if ("managementMode".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								ManagementMode.getName((int)obj),ManagementMode.getName((int)newObj));
						continue;
					}
					//transportMode
					if ("transportMode".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								TransportStyle.getName((int)obj),TransportStyle.getName((int)newObj));
						continue;
					}
					//first
					if ("first".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								MedicalFlag.getName((int)obj),MedicalFlag.getName((int)newObj));
						continue;
					}
					//sendPlace
					if ("sendPlace".equals(entry.getKey())){
						excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry,
								SendPlace.getName((int)obj),SendPlace.getName((int)newObj));
						continue;
					}
					excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry, obj, newObj);
				}

			}
			if(obj == null && newObj != null){
				excuteSupplierModifyRecord(user, supplier, tableName, updateTime, modifyRecordWithBLOBs, entry, obj, newObj);
			}
        }
        return modifyRecordWithBLOBs;
    }

	/**
	 * 经验范围id串转换成经验范围名称串
	 *
	 * @param scopeIds
	 * @return
	 */
	private String getLimitVarietyRangeDescStr(String scopeIds) {
		List<Long> scopeIdList = StringSplit.strSplit(scopeIds);
		if (CollectionUtils.isEmpty(scopeIdList)) {
			return "";
		} else {
			List<BusinessScope> businessScopes = businessScopeMapper.selectByIds(scopeIdList);
			StringBuilder name = new StringBuilder();
			for (Long id : scopeIdList) {
				for (BusinessScope businessScope : businessScopes) {
					if (businessScope.getId().equals(id)) {
						name.append(businessScope.getName()).append(",");
						break;
					}
				}
			}
			return name.length() > 0 ? name.substring(0, name.length() - 1) : "";
		}
	}

	private void excuteSupplierModifyRecord(UserVO user, Supplier supplier, String tableName, Date updateTime, List<SupplierModifyRecordWithBLOBs> modifyRecordWithBLOBs, Map.Entry<String, String> entry, Object obj, Object newObj) {
		SupplierModifyRecordWithBLOBs supplierModify = new SupplierModifyRecordWithBLOBs();
		supplierModify.setSupplierId(supplier.getId());
		supplierModify.setEnterpriseId(user.getEnterpriseId());

		supplierModify.setTableName(tableName);
		supplierModify.setKeyId(supplier.getId());
		supplierModify.setColumnEnName(entry.getKey());
		supplierModify.setColumnChName(entry.getValue());
		supplierModify.setUpdateTime(updateTime);
		supplierModify.setCreaterId(user.getUserId());
		supplierModify.setCreaterCode(user.getUserCode());
		supplierModify.setCreaterName(user.getUserName());
		supplierModify.setModifierId(user.getUserId());
		supplierModify.setModifierCode(user.getUserCode());
		supplierModify.setModifierName(user.getUserName());
		supplierModify.setCreateTime(new Date());
		supplierModify.setOldContent(obj == null ? "" : obj.toString());
		supplierModify.setNewContent(newObj == null ? "" : newObj.toString());
		modifyRecordWithBLOBs.add(supplierModify);
	}

	private Map<String,String> fieldSkipMap(){
        Map<String,String> fieldNames = new HashMap();
        fieldNames.put("nature","性质");
        fieldNames.put("pinyin","检索码");
        fieldNames.put("name","名称");
        fieldNames.put("groupId","分组");

        fieldNames.put("economicType","经济类型");
        fieldNames.put("businessMode","经营方式");
        fieldNames.put("registeredCapital","注册资金");
        fieldNames.put("provinceName","省名称");
        fieldNames.put("cityName","市名称");
		fieldNames.put("taxpayerCode","纳税人识别号");
        fieldNames.put("areaName","区名称");
        fieldNames.put("companyAddress","公司地址");
        fieldNames.put("postcode","邮政编码");
        fieldNames.put("tel","公司电话");
        fieldNames.put("fax","公司传真");
        fieldNames.put("email","公司邮箱");
        fieldNames.put("site","公司网址");
        fieldNames.put("storageAddress","仓库地址");
        fieldNames.put("businessManName","企业负责人");
        fieldNames.put("legalManName","法定代表人");
        fieldNames.put("qualityOfficerName","质量负责人");
        fieldNames.put("bankName","开户银行");
        fieldNames.put("bankAccount","开户账号");
        fieldNames.put("bankAccountName","开户户名");
        fieldNames.put("businessVariety","经营品种");
        fieldNames.put("businessScopeId","经营范围");
        fieldNames.put("supplierAbout","公司简介");
        fieldNames.put("saleManName","销售人员");
        fieldNames.put("status","状态");
        fieldNames.put("validFlag","标识");

        fieldNames.put("purchaserName","采购人员");
        fieldNames.put("paymentProvision","付款条款");
        fieldNames.put("paymentPeriod","付款账期");
        fieldNames.put("paymentPeriodUnit","付款账期单位");
        fieldNames.put("paymentTimeUnit","付款时间单位");
		fieldNames.put("paymentTime","付款时间");
        fieldNames.put("deliveryTimeUnit","报货时间单位");
        fieldNames.put("deliveryTime","报货时间");
        fieldNames.put("deliveryMode","报货方式");
		fieldNames.put("managementMode","结算方式");
        fieldNames.put("sendTimeUnit","送货时间单位");
        fieldNames.put("sendTime","送货时间");
        fieldNames.put("serviceCircle","送达周期");
        fieldNames.put("serviceCircleUnit","送达周期单位");
        fieldNames.put("sendPlace","送达地址");
        fieldNames.put("accountTimeUnit","对账时间单位");
        fieldNames.put("accountTime","对账时间");
        fieldNames.put("firstCode","首营企业编号");
        fieldNames.put("applicantName","申请人员");
        fieldNames.put("applicationTime","申请日期");
        fieldNames.put("qualityManageSys","企业质量管理体系");
        fieldNames.put("transportMode","运输方式");
        fieldNames.put("first","首营企业");
        return fieldNames;
    }

	public Map<String,Object> getFieldsMap(Object obj) throws Exception {
        Class newUserClazz = obj.getClass();
        Field[] feilds = newUserClazz.getDeclaredFields();
        Map<String,Object> feildMap = new HashMap<>();
        for(Field field : feilds){
            field.setAccessible(true);
            Object valObj = ReflectUtils.getValueOfGet(obj,field.getName());
            feildMap.put(field.getName(),valObj);
        }
        return feildMap;
    }



	@Override
	public List<SupplierBasicExcelVO> selectSupplierVoPage(String param, Integer nature, Integer groupId, String businessName,
														   String purchaseName, UserVO userVO, Integer approveStatus) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("param", param);
		map.put("nature", nature);
		map.put("groupId", groupId);
		map.put("businessName", businessName);
		map.put("purchaseName", purchaseName);
		supplierComponent.setDistinguishDivisionParamMap(userVO,map);
		/**
		 * 审核状态（查询全部不需要传值;0-待审核；1-已完成 2-审核被驳回;）
		 * 审批状态（0-待审核；1-审核通过；2-审核拒绝；-2-审核撤回；-1-审核中） tips：0和 -1的情况不可以修改
		 */
		if(null != approveStatus &&
				(approveStatus == ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_WAIT.getValue()
						|| approveStatus == ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue()
						|| approveStatus == ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue())){

			map.put("approveStatus", approveStatus);

		}
		List<SupplierBasicExcelVO> list = supplierMapper.selectSupplierExcel(map);
		
		for (SupplierBasicExcelVO s : list) {
			if (s.getEconomicType() != null){
				for (EconomyType e : EconomyType.values()) {
					if (s.getEconomicType().equals(String.valueOf(e.getCode()))){
						s.setEconomicType(e.getName());
					}
				}
			}
			
				if ("0".equals(s.getStatus())){
					s.setStatus("禁用");
				}
				if ("1".equals(s.getStatus())) {
					s.setStatus("启用");
				}
			
			if (s.getValidFlag() != null){
				for (ValidFlag v : ValidFlag.values()) {
					if (s.getValidFlag().equals(String.valueOf(v.getCode()))){
						s.setStatus(v.getName());
					}
				}
			}
			if (s.getBusinessMode() != null){
				for (SaleType st : SaleType.values()) {
					if (s.getBusinessMode().equals(String.valueOf(st.getCode()))){
						s.setBusinessMode(st.getName());
					}
				}
			}
			
			StringBuilder sb = new StringBuilder();
			if (s.getBusinessVariety() != null && !"".equals(s.getBusinessVariety())){
				String[] split = s.getBusinessVariety().split(",");
				for (String string : split) {
					for (LimitVarietyType l : LimitVarietyType.values()) {
						if (string.equals(String.valueOf(l.getCode()))){
							sb.append(l.getName());
						}
					}
				}
			}
			s.setBusinessVariety(sb.toString());
			StringBuilder sbSc = new StringBuilder();
			if (s.getBusinessScopeId() != null && !"".equals(s.getBusinessScopeId())) {
				String[] sbcope = s.getBusinessScopeId().split(",");
				List<BusinessScope> scopList = businessScopeMapper.getScope(Arrays.asList(sbcope));
				for (int i = 0; i < scopList.size(); i++){
					sbSc.append(scopList.get(i).getName());
					if (i < (scopList.size() - 1)){
						sbSc.append(";");
					}
				}
			}
			s.setBusinessScopeId(sbSc.toString());
			
		}
		return list;
	}

	@Override
	public void exportExcel(OutputStream output, List<SupplierBasicExcelVO> supplierVOList) {
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
     	map.put("code","供应商编码");
     	map.put("name","供应商名称");
     	map.put("economicType","经济类型");
     	map.put("tel","电话");
     	map.put("fax","传真");
     	map.put("companyAddress","地址");
     	map.put("status","状态");
     	map.put("validFlag","标识");
     	map.put("businessMode","经营方式");
     	map.put("registeredCapital","注册资金");
     	map.put("postcode","邮政编码");
     	map.put("email","公司邮箱");
     	map.put("site","公司网址");
     	map.put("storageAddress","仓库地址");
     	map.put("saleManName","销售人员");
     	map.put("businessManName","企业负责人");
     	map.put("legalManName","法定代表人");
     	map.put("qualityOfficerName","质量负责人");
     	map.put("bankName","开户银行");
     	map.put("bankAccount","开户账号");
     	map.put("bankAccountName","开户户名");
     	map.put("businessVariety","经营品种");
     	map.put("businessScopeId","经营范围");
		excelComponent.commExcelExport(output, map, supplierVOList);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void saveConnectSupplier(SupplierGroupIncludeVO supplierGroupIncludeVO,Long enterpriseId) {
		if (supplierGroupIncludeVO != null){
			Long groupId = supplierGroupIncludeVO.getGroupId();
			List<SupplierGroupConnectVO> list = supplierGroupIncludeVO.getList();
			if (list != null && list.size() > 0) {
				for (SupplierGroupConnectVO sg:
					 list ) {
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("type", groupId);
						map.put("sg", sg);
						map.put("enterpriseId",enterpriseId);
						supplierMapper.updateSupplier(map);
					}
				}
			}

	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void updateSupplerGroup(UserVO user, SupplierGroupVO supplierGroupVO) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String name = supplierGroupVO.getName();
		if(StringUtils.isEmpty(name)){
			throw new BusinessException(SysCode.FAIL.getCode(),"名称不能为空!");
		}
		Long id = supplierGroupVO.getId();
		SupplierGroup preSupplierGroup = supplierGroupMapper.selectByPrimaryKey(id);
		String preName = preSupplierGroup.getName();
		if (!preName.equals(supplierGroupVO.getName())){
			Supplier hasNameSupplier = supplierMapper.hasSupplierName(supplierGroupVO.getName(),user.getChainType() == ChainType.Headquarters.getCode() ? user.getEnterpriseId() : user.getParentId());
			if (hasNameSupplier != null){
				throw new BusinessException(SysCode.FAIL.getCode(),"当前供货单位名称已经存在!");
			}
		}
		map.put("user",user);
		map.put("supplierGroupVO", supplierGroupVO);
		if(supplierGroupVO!=null){
			if(supplierGroupVO.getStatus().equals(0)){
		if(preSupplierGroup!=null){
			if(preSupplierGroup.getEnterpriseId()==0){//如果供货单位为系统默认的不允许删除
				throw new BusinessException("系统默认的供货单位不允许禁用！");
			}
		}
		Map map_=new HashMap();
		map_.put("groupId", id);
		Long count=supplierMapper.queryCountByGroupId(map_);
		if(count>0){
			throw new BusinessException("被供货单位引用不允许禁用！");
		    }
		  }
		}
		supplierGroupMapper.updateSupplerGroup(map);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void deleteSupplierGroup(Long id) {
		SupplierGroup supplierGroup= supplierGroupMapper.selectByPrimaryKey(id);
		if(supplierGroup!=null){
			if(supplierGroup.getEnterpriseId()==0){//如果供货单位为系统默认的不允许删除
				throw new BusinessException("系统默认的供货单位不允许删除！");
			}
		}
		Map map=new HashMap();
		map.put("groupId", id);
		Long count=supplierMapper.queryCountByGroupId(map);
		if(count>0){
			throw new BusinessException("被供货单位引用不允许删除！");
		}
		supplierGroupMapper.deleteByPrimaryKey(id);
		//移除当前分组下的供货单位的分组ID
		supplierMapper.updateSupplierByGroupId(id);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void removeSupplierGroup(Long id) {
		supplierMapper.updateSupplierByGroupId(id);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void RemoveSupplier(Long id) {
		supplierMapper.RemoveSupplier(id);
	}

	@Override
	public List<SupplierGroupExcelVO> ExportGroupSupplier(Long enterpriseId) {
		List<SupplierGroupExcelVO> list = new ArrayList<SupplierGroupExcelVO>();
		list = supplierMapper.ExportGroupSupplier(enterpriseId);
		for (Nature n : Nature.values()) {
			if (list.size() > 0 && list != null){
				for (SupplierGroupExcelVO sg : list) {
					if (sg.getNature() != null){
						if (sg.getNature().equals(String.valueOf(n.getCode()))){
							sg.setNature(n.getName());
						}
					}
				}
			}
		}
		return list;
	}

	@Override
	public void exporGroupExcel(OutputStream output, List<SupplierGroupExcelVO> supplierVOList) {
		LinkedHashMap<String,String> map = new LinkedHashMap<>();
		map.put("code","供应商编码");
     	map.put("name","供应商名称");
     	map.put("groupName","分组名称");
     	map.put("groupCode","分组编码");
     	map.put("nature","供应商类型");
		excelComponent.commExcelExport(output, map, supplierVOList);
	}

	@Override
	public String checkSupplier(Long enterpriseId) {
		String type = supplierMapper.checkSupplier(enterpriseId);
		return type;
	}

	@Override
	public String getGroupCode(Long groupId) {
		SupplierGroup supplierGroup = supplierGroupMapper.selectByPrimaryKey(groupId);
		if (supplierGroup != null){
			return supplierGroup.getCode();
		}
		return "";
	}


}