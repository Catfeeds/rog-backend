package com.rograndec.feijiayun.chain.business.system.enterprise.service.impl;


import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.entity.Equipment;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreEnterpriseBusinessResponseVO;
import com.rograndec.feijiayun.chain.business.member.info.dao.MemberInfoMapper;
import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.member.set.dao.MemberCardLevelMapper;
import com.rograndec.feijiayun.chain.business.member.set.dao.MemberCardTypeMapper;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardLevel;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosBankMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayTypeMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosSalePeriodMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosBank;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosPayType;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowMapper;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlow;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.business.system.set.dao.SysRoleActionMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.WarnSetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRoleAction;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.constant.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseBusinessServiceImpl implements EnterpriseBusinessService{

	private static final Log logger = LogFactory.getLog(EnterpriseBusinessServiceImpl.class);
	
	@Autowired
	EnterpriseBusinessMapper enterpriseBusinessMapper;

	@Autowired
	private SysRoleActionMapper sysRoleActionMapper;

	@Autowired
	private ApprovalFlowMapper approvalFlowMapper;

	@Autowired
	private WarnSetMapper warnSetMapper;

	@Autowired
	private MemberCardLevelMapper memberCardLevelMapper;

	@Autowired
	private MemberCardTypeMapper memberCardTypeMapper;

	@Autowired
	private MemberInfoMapper memberInfoMapper;

	@Autowired
	private PosPayTypeMapper posPayTypeMapper;

	@Autowired
	private PosBankMapper posBankMapper;

	@Autowired
	private PosSalePeriodMapper posSalePeriodMapper;

	@Autowired
	private EquipmentMapper equipmentMapper;

	@Autowired
	private CommonComponent commonComponent;

	@Override
	public List<EnterpriseBusiness> queryEnterpriseBusinessByEnterpriseIds(List<Long> ids){
		List<EnterpriseBusiness> enterpriseBusinesses = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseIds(ids);

		for(EnterpriseBusiness bus : enterpriseBusinesses){
			bus.setSettlementModeName(SettlementMode.getName(bus.getSettlementMode()));
			bus.setDistrPriceTypeName(DistrPriceType.getName(bus.getDistrPriceType()));
			bus.setPaymentProvisionName(PaymentProvision.getName(bus.getPaymentProvision()));
			bus.setPaymentPeriodUnitName(PaymentPeriodUnit.getName(bus.getPaymentPeriodUnit()));
			bus.setPaymentTimeUnitName(PaymentTimeUnit.getName(bus.getPaymentTimeUnit()));
		}
		return enterpriseBusinesses;
	}

	@Override
	public EnterpriseBusiness queryEnterpriseBusinessByEnterpriseId(Long enterpriseId) {
		return commonComponent.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
	}


	@Override
	public StoreEnterpriseBusinessResponseVO transfromationResponseEnterpriseBusiness(
			EnterpriseBusiness enterpriseBus) {
		StoreEnterpriseBusinessResponseVO vo = new StoreEnterpriseBusinessResponseVO();
		
		try {
			BeanUtils.copyProperties(vo, enterpriseBus);
			//当前那条记录企业信息的企业ID
			Long enterpriseId = vo.getEnterpriseId();
			/**
			 * 按钮展示的判断
			 */
			//1.权限管理按钮  ** 系统管理/权限设置/系统设置
			List<SysRoleAction> sysRoleActions = sysRoleActionMapper.selectByEnterpriseId(enterpriseId);
			if (sysRoleActions.size() > 0){
				vo.setPermissionSetFlag(false);
			}
			//2.审批管理按钮  ** 系统管理/审批管理/审批流程(排除默认的)
			List<ApprovalFlow> approvalFlows = approvalFlowMapper.selectByEnterPriseId(enterpriseId);
			if (approvalFlows.size() > 0){
				vo.setApprovalControlFlag(false);
			}
			//3.预警管理按钮  ** 系统管理/系统设置/预警设置(排除默认的)
			List<WarnSet> warnSets = warnSetMapper.selectByEnterPriseId(enterpriseId);
			if (warnSets.size() > 0){
				vo.setWarnSetFlag(false);
			}
			//4.会员管理按钮  ** (1)会员管理/会员卡级别 || (2)会员管理/会员卡类型 || (3)会员管理/会员信息
			List<MemberCardLevel> memberCardLevels = memberCardLevelMapper.selectByEnterpriseId(enterpriseId);
			List<MemberCardType> memberCardTypes = memberCardTypeMapper.selectByEnterPriseId(enterpriseId);
			List<MemberInfo> memberInfos = memberInfoMapper.selectByStoreEnterPriseId(enterpriseId);
			if (memberCardLevels.size() > 0 ||
					memberCardTypes.size() > 0 ||
					memberInfos.size() > 0){
				vo.setMemberInfoFlag(false);
			}
			//5.POS设置按钮   ** (1)零售管理/POS设置/支付方式 || (2)零售管理/POS设置/开户银行
			List<PosPayType> posPayTypes = posPayTypeMapper.selectByEnterpriseId(enterpriseId);
			List<PosBank> posBanks = posBankMapper.selectByEnterpriseId(enterpriseId);
			if (posPayTypes.size() > 0 ||
					posBanks.size() > 0){
				vo.setPosSetFlag(false);
			}
			//6.设施设备按钮  ** 基础资料/设施设备/设施设备
			List<Equipment> equipments = equipmentMapper.selectByEnterpriseId(enterpriseId);
			if (equipments.size() > 0){
				vo.setEquipmentManageFlag(false);
			}
		} catch (Exception e) {
			logger.error("转换StoreEnterpriseBusinessResponseVO异常", e);
			e.printStackTrace();
		}
		return vo;
	}

}
