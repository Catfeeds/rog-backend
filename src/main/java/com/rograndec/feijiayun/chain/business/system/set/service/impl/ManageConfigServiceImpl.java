/**
 * 
 */
package com.rograndec.feijiayun.chain.business.system.set.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.service.ManageConfigService;
import com.rograndec.feijiayun.chain.business.system.set.vo.ValidateCodeRuleVO;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**

 * @Description:TODO

 * @author:LeiSu

 * @time:2017年8月22日 上午10:13:19

 */
@Service
public class ManageConfigServiceImpl implements ManageConfigService{

	/* 获取管理设置的基本信息
	 * @see com.rograndec.feijiayun.chain.system.set.service.ManageConfigService#getManageConfig(com.rograndec.feijiayun.chain.system.set.entity.ManageConfig)
	 */
	@Autowired
	private ManageConfigMapper manageConfigMapper;

	@Autowired
	private SupplierMapper supplierMapper;

	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private EnterpriseBusinessMapper  businessMapper;



	@Override
	@Transactional
	public ManageConfig getManageConfig(UserVO user) {
		ManageConfig mc;

		//当前登录用户的企业ID，企业类型和上级企业不为空时
		mc = manageConfigMapper.selectByCurrentUser(user);
		//分店取总部的几个控制信息
		if (user.getChainType() != ChainType.Headquarters.getCode()){
			ManageConfig manageConfig = manageConfigMapper.selectManageConfigByEnterpriseId(user.getParentId());
			mc.setGoodsCodeRule(manageConfig.getGoodsCodeRule());
			mc.setSupplierCodeRule(manageConfig.getSupplierCodeRule());
			mc.setUserCodeRule(manageConfig.getSupplierCodeRule());
		}
		/**
		 * 预留接口判断当前的质量控制和审批流程是否可编辑,先都默认为1
		 */
		if (mc != null){
			mc.setApprovalEdit(1);
			mc.setQualityEdit(1);
		}

		return mc;
	}

	/* (非 Javadoc)  
	 * <p>Title: updateManageConfig</p>  
	 * <p>Description: </p>  
	 * @param manageConfig
	 * @param user  
	 * @see com.rograndec.feijiayun.chain.system.set.service.ManageConfigService#updateManageConfig(com.rograndec.feijiayun.chain.system.set.entity.ManageConfig, com.rograndec.feijiayun.chain.common.vo.UserVO)  
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateManageConfig (ManageConfig manageConfig, UserVO user) throws Exception{



		EnterpriseBusiness enterpriseBusiness = businessMapper.queryEnterpriseBusinessByEnterpriseId(user.getEnterpriseId());

		Integer qualityControl = enterpriseBusiness.getQualityControl();//质量控制
		if(!user.getChainType().equals(ChainType.Headquarters.getCode())
				&& qualityControl == 0) {//
			// 如果是门店，自营店或加盟店“质量管理”选择“总部控制”，则将该门店的管理配置信息中的
			// 基础数据质量控制、业务流程质量控制、POS质量控制、审批流程控制   不给于修改

			manageConfig.setPosControl(null);
			manageConfig.setQualityControl(null);
			manageConfig.setApprovalControl(null);
			manageConfig.setBusinessControl(null);

		}


		int chainType = user.getChainType();
		/**
		 * 记录修改人记录
		 */
		manageConfig.setModifierId(user.getUserId());
		manageConfig.setModifierCode(user.getUserCode());
		manageConfig.setModifierName(user.getUserName());
		//收货人员1 + 收货人员2 + 验收人员1 + 验收人员2 + 入库出库人员 + 出库复核人员
		Long receiverId = manageConfig.getReceiverId();
		User receiver = userMapper.selectByPrimaryKey(receiverId);
		if (receiver != null){
			manageConfig.setReceiverCode(receiver.getCode());
			manageConfig.setReceiverName(receiver.getName());
		}
		Long secondReceiverId = manageConfig.getSecondReceiverId();
		User secondReceiver = userMapper.selectByPrimaryKey(secondReceiverId);
		if (secondReceiver != null){
			manageConfig.setSecondReceiverCode(secondReceiver.getCode());
			manageConfig.setSecondReceiverName(secondReceiver.getName());
		}
		Long checkerId = manageConfig.getCheckerId();
		User checker = userMapper.selectByPrimaryKey(checkerId);
		if (checker != null){
			manageConfig.setCheckerCode(checker.getCode());
			manageConfig.setCheckerName(checker.getName());
		}
		Long secondCheckerId = manageConfig.getSecondCheckerId();
		User secondChecker = userMapper.selectByPrimaryKey(secondCheckerId);
		if (secondChecker != null){
			manageConfig.setSecondCheckerCode(secondChecker.getCode());
			manageConfig.setSecondCheckerName(secondChecker.getName());
		}
		Long inOutManId = manageConfig.getInOutManId();
		User inOutMan = userMapper.selectByPrimaryKey(inOutManId);
		if (inOutMan != null){
			manageConfig.setInOutManCode(inOutMan.getCode());
			manageConfig.setInOutManName(inOutMan.getName());
		}
		Long outCheckerId = manageConfig.getOutCheckerId();
		User outChecker = userMapper.selectByPrimaryKey(outCheckerId);
		if (outChecker != null){
			manageConfig.setOutCheckerCode(outChecker.getCode());
			manageConfig.setOutCheckerName(outChecker.getName());
		}
		/**
		 * 总部可以修改所有&&总部修改时需要将所属分部修改（人员信息不包含在内）
		 */
		if (chainType == ChainType.Headquarters.getCode()){
			UserEnterpriseUtils.setUserCreateOrModify(manageConfig,user,false);
			/**
			 * 采购人员 + 配货人员
			 */
			Long purchaserId = manageConfig.getPurchaserId();
			User purchaser = userMapper.selectByPrimaryKey(purchaserId);
			if (purchaser != null){
				manageConfig.setPurchaserCode(purchaser.getCode());
				manageConfig.setPurchaserName(purchaser.getName());
			}
			Long distrManId = manageConfig.getDistrManId();
			User distrManer = userMapper.selectByPrimaryKey(distrManId);
			if (distrManer != null){
				manageConfig.setDistrManCode(distrManer.getCode());
				manageConfig.setDistrManName(distrManer.getName());
			}
			manageConfigMapper.updateByPrimaryKeySelective(manageConfig);
			/**
			 * 修改当前总部下所属分店的基础配置
			 */
			//manageConfigMapper.updateDivisionByConfig(manageConfig);
			updateDivisionConfig(manageConfig,user);
		}else {
			/**
			 * 要货人员
			 */
			Long requesterId = manageConfig.getRequesterId();
			User requester = userMapper.selectByPrimaryKey(requesterId);
			if (requester != null){
				manageConfig.setRequesterCode(requester.getCode());
				manageConfig.setRequesterName(requester.getName());
			}
			/**
			 * 分店一期不能修改原型置灰的菜单
			 */
			UserEnterpriseUtils.setUserCreateOrModify(manageConfig,user,false);
			manageConfigMapper.updateByPrimaryKeySelective(manageConfig);
		}
	}

	/**
	 * 将 质量控制中 选择总部控制的门店的 业务流程控制、基础数据、POS、流程控制 同步更新为 总部的
	 * @param manageConfig
	 * @param user
	 */
	private void updateDivisionConfig(ManageConfig manageConfig, UserVO user) {

		// 获取 当前总部下 质量控制为 总部控制的所有门店
		Map<String,Object>  param = new HashMap<>();
		param.put("parentId",manageConfig.getEnterpriseId());
		param.put("qualityControl",0);

		List<EnterpriseBusiness> businessList  =  businessMapper.selectbyParam(param);
		for (EnterpriseBusiness business: businessList) {
			ManageConfig branchConfig = manageConfigMapper.selectManageConfigByEnterpriseId(business.getEnterpriseId());
			branchConfig.setBusinessControl(manageConfig.getBusinessControl());
			branchConfig.setApprovalControl(manageConfig.getApprovalControl());
			branchConfig.setPosControl(manageConfig.getPosControl());
			branchConfig.setQualityControl(manageConfig.getQualityControl());
			manageConfigMapper.updateByPrimaryKeySelective(branchConfig);
		}
	}

	@Override
	@Transactional
	public int judgeCodeStyle(Long id) {
		int type = manageConfigMapper.judgeCodeStyle(id);
		return type;
	}

	@Override
	public ValidateCodeRuleVO judgeCodeRule(Long id, UserVO userVO) {
		ValidateCodeRuleVO vo = new ValidateCodeRuleVO();
		/**
		 * 修改之前先判断供货单位 + 商品信息 + 员工信息 如果有一条信息存在，那么他的编码规则就无法修改
		 */
		List<Supplier> supplierList = supplierMapper.selectExisitSupplier(userVO.getEnterpriseId());
		if (!CollectionUtils.isEmpty(supplierList) && supplierList.size() > 0){
			vo.setEditSupplierCodeRule(0);
		}else {
			vo.setEditSupplierCodeRule(1);
		}

		List<Goods> goodsList = goodsMapper.selectExisitGoods(userVO.getEnterpriseId());
		if (!CollectionUtils.isEmpty(goodsList) && goodsList.size() > 0){
			vo.setEditGoodsCodeRule(0);
		}else {
			vo.setEditGoodsCodeRule(1);
		}

		/**
		 * 初始化必有一个默认的企业员工
		 */
		List<User> userList = userMapper.selectExisitUser(userVO.getEnterpriseId());
		/**
		 * 系统或默认初始化4个默认员工,所以要判断数量大于4
		 */
		if (!CollectionUtils.isEmpty(userList) && userList.size() > 4){
			vo.setEditUserCodeRule(0);
		}else {
			vo.setEditUserCodeRule(1);
		}
		return vo;
	}


}
