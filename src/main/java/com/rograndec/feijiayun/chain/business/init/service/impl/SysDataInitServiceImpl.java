package com.rograndec.feijiayun.chain.business.init.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.basic.equipment.dao.EquipmentTypeMapper;
import com.rograndec.feijiayun.chain.business.basic.equipment.entity.EquipmentType;
import com.rograndec.feijiayun.chain.business.basic.store.dao.EnterpriseGroupMapper;
import com.rograndec.feijiayun.chain.business.basic.store.entity.EnterpriseGroup;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierGroupMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierGroup;
import com.rograndec.feijiayun.chain.business.basic.user.constant.UserCoderRule;
import com.rograndec.feijiayun.chain.business.basic.user.constant.UserTypeEum;
import com.rograndec.feijiayun.chain.business.basic.user.dao.NationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserAdministrationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserRoleMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.Nation;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserRole;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseCargoAreaMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.Warehouse;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseCargoArea;
import com.rograndec.feijiayun.chain.business.basic.warehouse.entity.WarehouseShelf;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.dao.PharmacySetMapper;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.PharmacySet;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsCategoryMapper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsDosageMapper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsUnitMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsDosage;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsUnit;
import com.rograndec.feijiayun.chain.business.init.model.ApprovalFlowContentModel;
import com.rograndec.feijiayun.chain.business.init.model.BusinessScopeModel;
import com.rograndec.feijiayun.chain.business.init.model.CheckConclusionModel;
import com.rograndec.feijiayun.chain.business.init.model.CheckPojectModel;
import com.rograndec.feijiayun.chain.business.init.model.CheckTypeModel;
import com.rograndec.feijiayun.chain.business.init.model.DeptModel;
import com.rograndec.feijiayun.chain.business.init.model.EnterpriseQualificationModel;
import com.rograndec.feijiayun.chain.business.init.model.EnterpriseWarnModel;
import com.rograndec.feijiayun.chain.business.init.model.EquipmentTypeModel;
import com.rograndec.feijiayun.chain.business.init.model.GoodsCategoryModel;
import com.rograndec.feijiayun.chain.business.init.model.GoodsDosageModel;
import com.rograndec.feijiayun.chain.business.init.model.GoodsQualificationModel;
import com.rograndec.feijiayun.chain.business.init.model.GoodsUnitModel;
import com.rograndec.feijiayun.chain.business.init.model.GoodsWarnModel;
import com.rograndec.feijiayun.chain.business.init.model.HandleMeasuresModel;
import com.rograndec.feijiayun.chain.business.init.model.MaintanceMeasuresModel;
import com.rograndec.feijiayun.chain.business.init.model.MemberCardTypeModel;
import com.rograndec.feijiayun.chain.business.init.model.MenuModel;
import com.rograndec.feijiayun.chain.business.init.model.NationModel;
import com.rograndec.feijiayun.chain.business.init.model.PharmacySetModel;
import com.rograndec.feijiayun.chain.business.init.model.PosBankModel;
import com.rograndec.feijiayun.chain.business.init.model.PosPayTypeModel;
import com.rograndec.feijiayun.chain.business.init.model.PosSalePeriodModel;
import com.rograndec.feijiayun.chain.business.init.model.PositionModel;
import com.rograndec.feijiayun.chain.business.init.model.RejectReasonModel;
import com.rograndec.feijiayun.chain.business.init.model.ReturnReasonModel;
import com.rograndec.feijiayun.chain.business.init.model.RoleModel;
import com.rograndec.feijiayun.chain.business.init.model.StockWarnModel;
import com.rograndec.feijiayun.chain.business.init.model.StoreGroupModel;
import com.rograndec.feijiayun.chain.business.init.model.SupplierGroupModel;
import com.rograndec.feijiayun.chain.business.init.model.SupplierWarnModel;
import com.rograndec.feijiayun.chain.business.init.model.TaxRateModel;
import com.rograndec.feijiayun.chain.business.init.model.UnqualifiedReasonModel;
import com.rograndec.feijiayun.chain.business.init.model.UserQualificationModel;
import com.rograndec.feijiayun.chain.business.init.model.UserWarnModel;
import com.rograndec.feijiayun.chain.business.init.service.SysDataInitService;
import com.rograndec.feijiayun.chain.business.member.set.dao.MemberCardLevelMapper;
import com.rograndec.feijiayun.chain.business.member.set.dao.MemberCardTypeMapper;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardLevel;
import com.rograndec.feijiayun.chain.business.member.set.entity.MemberCardType;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosBankMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosPayTypeMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosSalePeriodMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosSetMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.dao.PosTeamMapper;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosBank;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosPayType;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosSalePeriod;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosSet;
import com.rograndec.feijiayun.chain.business.retail.pos.entity.PosTeam;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowContentMapper;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowDetailMapper;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowMapper;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlow;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowContent;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowDetail;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.BusinessScopeMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.DepartmentMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.EnterpriseQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.GoodsQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.PositionMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.SysActionMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.SysRoleActionMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.SysRoleMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.UserQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.WarnSetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.business.system.set.entity.Department;
import com.rograndec.feijiayun.chain.business.system.set.entity.EnterpriseQualification;
import com.rograndec.feijiayun.chain.business.system.set.entity.GoodsQualification;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.entity.Position;
import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysAction;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRole;
import com.rograndec.feijiayun.chain.business.system.set.entity.SysRoleAction;
import com.rograndec.feijiayun.chain.business.system.set.entity.UserQualification;
import com.rograndec.feijiayun.chain.business.system.set.entity.WarnSet;
import com.rograndec.feijiayun.chain.common.component.ApprovalFlowComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.QualitySetType;
import com.rograndec.feijiayun.chain.common.constant.SupplierCodeRule;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.MD5.MD5Utils;

/**
 * @author lizhongyi
 */
@Service(value = "sysDataInitService")
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
public class SysDataInitServiceImpl implements SysDataInitService{

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ManageConfigMapper manageConfigMapper;
	
	@Autowired
	private UserAdministrationMapper userAdministrationMapper;

	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	private PositionMapper positionMapper;
	
	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysActionMapper sysActionMapper;

	@Autowired
	private SysRoleActionMapper sysRoleActionMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Autowired
	private QualitySetMapper qualitySetMapper;
	
	@Autowired
	private BusinessScopeMapper businessScopeMapper;
	
	@Autowired
	private EnterpriseQualificationMapper enterpriseQualificationMapper;
	
	@Autowired
	private UserQualificationMapper userQualificationMapper;
	
	@Autowired
	private GoodsQualificationMapper goodsQualificationMapper;
	
	@Autowired
	private WarnSetMapper warnSetMapper;
	
	@Autowired
	private EnterpriseGroupMapper enterpriseGroupMapper;
	
	@Autowired
	private SupplierGroupMapper supplierGroupMapper;
	
	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;
	
	@Autowired
	private GoodsDosageMapper goodsDosageMapper;
	
	@Autowired
	private GoodsUnitMapper goodsUnitMapper;

	@Autowired
	private GoodsTaxRateMapper goodsTaxRateMapper;
	
	@Autowired
	private WarehouseMapper warehouseMapper;
	
	@Autowired
	private WarehouseAreaMapper warehouseAreaMapper;
	
	@Autowired
	private WarehouseCargoAreaMapper warehouseCargoAreaMapper;

	@Autowired
	private WarehouseShelfMapper warehouseShelfMapper;
	
	@Autowired
	private ApprovalFlowMapper approvalFlowMapper;
	
	@Autowired
	private ApprovalFlowDetailMapper approvalFlowDetailMapper;
	
	@Autowired
	private EnterpriseMapper enterpriseMapper;
	
	@Autowired
	private ApprovalFlowContentMapper approvalFlowContentMapper;
	
	@Autowired
	private NationMapper nationMapper;
	
	@Autowired
	private PharmacySetMapper pharmacySetMapper;

	@Autowired
	private MemberCardLevelMapper memberCardLevelMapper;

	@Autowired
	private MemberCardTypeMapper memberCardTypeMapper;

	@Autowired
	private PosSetMapper posSetMapper;

	@Autowired
	private PosPayTypeMapper posPayTypeMapper;

	@Autowired
	private PosBankMapper posBankMapper;

	@Autowired
	private PosTeamMapper posTeamMapper;

	@Autowired
	private PosSalePeriodMapper posSalePeriodMapper;

	@Autowired
	private PriceOrderMapper priceOrderMapper;

	@Autowired
	private EquipmentTypeMapper equipmentTypeMapper;

	@Autowired
	private EnterpriseBusinessMapper enterpriseBusinessMapper;

	@Autowired
	private ApprovalFlowComponent approvalFlowComponent;

	@Deprecated
	@Override
	public void initGlobalDataOnlyOnce() throws Exception {
		// 系统管理员
		User user = initUser();
		// 部门
		List<Department> deptList = initDeptment(user);
		// 岗位
		List<Position> positionList = initPosition(user, deptList);
		// 角色
		initSysRole(user, deptList, positionList);

		// 质量设置-拒收原因
		initRejectReason(user);
		// 质量设置-不合格原因
		initUnqualifiedReason(user);
		// 质量设置-退货原因
		initReturnReason(user);
		// 质量设置-处置措施
		initHandleMeasures(user);
		// 质量设置-验收类型
		initCheckType(user);
		// 质量设置-验收项目
		initCheckPoject(user);
		// 质量设置-验收结论
		initCheckConclusion(user);
		// 质量设置-养护措施
		initMaintanceMeasures(user);
		// 经营范围
		initBusinessScope(user);
		// 企业资质
		initEnterpriseQualification(user);
		// 员工资质
		initUserQualification(user, positionList);
		// 商品资质
		initGoodsQualification(user);

		// 门店分组
		initEnterpriseGroup(user);
		// 供货单位分组
		initSupplierGroup(user);
		// 商品设置-分类
		initGoodsCategory(user);
		// 商品设置-单位
		initGoodsUnit(user);
		// 商品设置-剂型
		initGoodsDosage(user);
		// 商品设置-税率
		initGoodsTaxRate(user);
		// 审批内容
		initApprovalFlowContent();

		// 民族
		initNation();
		// 药学设置
		initPharmacySet(user);
		// 会员卡级别、会员卡类型
		initMemberCardLevelAndType(user);
		// 设施设备类型
		initEquipmentType();

	}

	private void initEquipmentType() {
		List<EquipmentTypeModel> modelList = EquipmentTypeModel.build();
		for(EquipmentTypeModel m:modelList){
			EquipmentType equipmentType = new EquipmentType();
			equipmentType.setEnterpriseId(0L);
			equipmentType.setParentId(0L);
			equipmentType.setCode(m.getCode());
			equipmentType.setName(m.getName());
			equipmentType.setParentTypeCode(m.getParentTypeCode());
			equipmentType.setParentTypeName(m.getParentTypeName());
			equipmentTypeMapper.insertSelective(equipmentType);
		}
	}

	@Override
	public void initCommonData(Enterprise enterprise, UserVO user) throws Exception {
		// 初始化管理设置数据
		initManageConfigData(enterprise, user);
		// 初始化仓库、库区、货区、货位数据
		initWarehouseData(enterprise, user);
		// 初始化审批流数据
		initApprovalFlowData(enterprise, user);
		// 初始化POS设置数据
		initPosSetData(enterprise, user);
		// 初始化价格清单数据
		initPriceOrderData(enterprise, user);

		// 预警设置-企业预警
		List<EnterpriseQualification> enterpriseQualificationList = enterpriseQualificationMapper.queryDefEnterpriseQualificationList();
		initEnterpriseWarn(enterprise, user, enterpriseQualificationList);
		// 预警设置-员工预警
		List<UserQualification> userQualificationList = userQualificationMapper.queryDefUserQualificationList();
		initUserWarn(enterprise, user, userQualificationList);
		// 预警设置-供货单位预警
		initSupplierWarn(enterprise, user, enterpriseQualificationList);
		// 预警设置-商品预警
		List<GoodsQualification> goodsQualificationList = goodsQualificationMapper.queryDefGoodsQualificationList();
		initGoodsWarn(enterprise, user, goodsQualificationList);
		// 预警设置-库存预警
		initStockWarn(enterprise, user);

	}

	@Override
	public void initPermissionData() throws Exception {
		User user = userMapper.selectByCodeAndEnterpriseIdAndParentId("999999", 0L, 0L);
		// 功能、企业菜单维护
		List<SysAction> actionList = initSysAction(user);

		SysRole sysRole = sysRoleMapper.selectDefRoleByCode("090101");
		List<SysRole> roleList = new ArrayList<SysRole>();
		roleList.add(sysRole);

		// 角色菜单、权限设置
		initSysRoleAction(user, roleList, actionList);

		initUserRole(user, sysRole);
	}

	private void initUserRole(User user, SysRole sysRole) {
		UserRole userRole = new UserRole();
		userRole.setEnterpriseId(user.getEnterpriseId());
		userRole.setParentId(user.getParentId());
		userRole.setUserId(user.getId());
		userRole.setRoleId(sysRole.getId());
		userRole.setRemark("系统默认用户角色表");
		userRole.setCreaterId(user.getId());
		userRole.setCreaterCode(user.getCode());
		userRole.setCreaterName(user.getName());
		userRole.setCreateTime(new Date());
		userRole.setModifierId(user.getId());
		userRole.setModifierCode(user.getCode());
		userRole.setModifierName(user.getName());
		userRole.setUpdateTime(new Date());
		userRoleMapper.insertSelective(userRole);
	}

	private void initSysRoleAction(User user, List<SysRole> roleList, List<SysAction> actionList) {
		for(SysRole sysRole:roleList){
			for(SysAction sysAction:actionList){
				SysRoleAction sysRoleAction = new SysRoleAction();
				sysRoleAction.setEnterpriseId(0L);
				sysRoleAction.setParentId(0L);
				sysRoleAction.setChainType(0);
				sysRoleAction.setRoleId(sysRole.getId());
				sysRoleAction.setActionId(sysAction.getId());
				sysRoleAction.setRemark("系统默认权限");
				sysRoleAction.setCreaterId(user.getId());
				sysRoleAction.setCreaterCode(user.getCode());
				sysRoleAction.setCreaterName(user.getName());
				sysRoleAction.setCreateTime(new Date());
				sysRoleAction.setModifierId(user.getId());
				sysRoleAction.setModifierCode(user.getCode());
				sysRoleAction.setModifierName(user.getName());
				sysRoleAction.setUpdateTime(new Date());
				sysRoleActionMapper.insertSelective(sysRoleAction);
			}
		}
	}

	private List<SysAction> initSysAction(User user) {
		List<SysAction> sysActionList = new ArrayList<SysAction>();
		List<MenuModel> mList = MenuModel.build();
		int index = 1;
		for(MenuModel m:mList){
			SysAction sysAction = new SysAction();
			sysAction.setEnterpriseId(0L);
			sysAction.setParentId(0L);
			Long parentActionId = 0L;
			String parentCode = m.getParentCode();
			if(StringUtils.isNotEmpty(parentCode)){
				SysAction parentAction = sysActionMapper.selectByCodeAndEnterpriseIdAndParentId(parentCode,0L,0L);
				if(parentAction != null){
					parentActionId = parentAction.getId()==null?0L:parentAction.getId();
				}
			}
			sysAction.setParentActionId(parentActionId);
			sysAction.setType(m.getType());
			sysAction.setCode(m.getCode());
			sysAction.setName(m.getName());
			sysAction.setUrl(m.getUrl());
			sysAction.setForParent(m.getForParent());
			sysAction.setForLeague(m.getForLeague());
			sysAction.setForBranch(m.getForBranch());
			sysAction.setShowOrder(index);
			sysAction.setIcon(m.getIcon());
			sysAction.setIsParent(m.getIsParent());
			sysAction.setStatus(EnableStatus.ENABLE.getStatus());
			sysAction.setRemark("系统默认功能");
			sysAction.setCreaterId(user.getId());
			sysAction.setCreaterCode(user.getCode());
			sysAction.setCreaterName(user.getName());
			sysAction.setCreateTime(new Date());
			sysAction.setModifierId(user.getId());
			sysAction.setModifierCode(user.getCode());
			sysAction.setModifierName(user.getName());
			sysAction.setUpdateTime(new Date());
			sysActionMapper.insertSelective(sysAction);
			sysActionList.add(sysAction);
			index ++;
		}
		return sysActionList;
	}

	private void initMemberCardLevelAndType(User user) {
		MemberCardLevel memberCardLevel = new MemberCardLevel();
		memberCardLevel.setEnterpriseId(0L);
		memberCardLevel.setParentId(0L);
		memberCardLevel.setSysType(SysType.SYSTEM.getCode());
		memberCardLevel.setCode("01");
		memberCardLevel.setName("注册会员");
		// 成长值（0-累积积分；1-当前积分；2-累积储值）
		memberCardLevel.setGrowthValue(0);
		memberCardLevel.setIntegral(BigDecimal.ZERO);
		memberCardLevel.setStatus(EnableStatus.ENABLE.getStatus());
		memberCardLevel.setRemark("系统默认会员卡级别");
		memberCardLevel.setCreaterId(user.getId());
		memberCardLevel.setCreaterCode(user.getCode());
		memberCardLevel.setCreaterName(user.getName());
		memberCardLevel.setCreateTime(new Date());
		memberCardLevel.setModifierId(user.getId());
		memberCardLevel.setModifierCode(user.getCode());
		memberCardLevel.setModifierName(user.getName());
		memberCardLevel.setUpdateTime(new Date());
		memberCardLevelMapper.insertSelective(memberCardLevel);

		List<MemberCardTypeModel> memberCardTypeModelList = MemberCardTypeModel.build();
		for(MemberCardTypeModel model:memberCardTypeModelList){
			MemberCardType memberCardType = new MemberCardType();
			memberCardType.setEnterpriseId(0L);
			memberCardType.setParentId(0L);
			memberCardType.setSysType(SysType.SYSTEM.getCode());
			memberCardType.setCode(model.getCode());
			memberCardType.setName(model.getName());
			memberCardType.setType(model.getType());
			memberCardType.setLevelId(memberCardLevel.getId());
			memberCardType.setLevelName(model.getLevelName());
			memberCardType.setPriceStrategy(model.getPriceStrategy());
			memberCardType.setDiscountStrategy(model.getDiscountStrategy());
			memberCardType.setAmount(model.getAmount());
			memberCardType.setIntegral(model.getIntegral());
			memberCardType.setStatus(EnableStatus.ENABLE.getStatus());
			memberCardType.setRemark("系统默认会员卡类型");
			memberCardType.setCreaterId(user.getId());
			memberCardType.setCreaterCode(user.getCode());
			memberCardType.setCreaterName(user.getName());
			memberCardType.setCreateTime(new Date());
			memberCardType.setModifierId(user.getId());
			memberCardType.setModifierCode(user.getCode());
			memberCardType.setModifierName(user.getName());
			memberCardType.setUpdateTime(new Date());
			memberCardTypeMapper.insertSelective(memberCardType);
		}

	}

	private void initPharmacySet(User user) {
		List<PharmacySetModel> pharmacySetModelList = PharmacySetModel.build();
		for(PharmacySetModel model:pharmacySetModelList){
			PharmacySet pharmacySet = new PharmacySet();
			pharmacySet.setEnterpriseId(0L);
			pharmacySet.setSetType(model.getSetType());
			pharmacySet.setSysType(SysType.SYSTEM.getCode());
			pharmacySet.setCode(model.getCode());
			pharmacySet.setName(model.getName());
			pharmacySet.setStatus(EnableStatus.ENABLE.getStatus());
			pharmacySet.setRemark("系统默认药学设置");
			pharmacySet.setCreaterId(user.getId());
			pharmacySet.setCreaterCode(user.getCode());
			pharmacySet.setCreaterName(user.getName());
			pharmacySet.setCreateTime(new Date());
			pharmacySet.setModifierId(user.getId());
			pharmacySet.setModifierCode(user.getCode());
			pharmacySet.setModifierName(user.getName());
			pharmacySet.setUpdateTime(new Date());
			pharmacySetMapper.insertSelective(pharmacySet);
		}
	}

	private void initNation() {
		List<NationModel> nationModelList = NationModel.build();
		for(NationModel model:nationModelList){
			Nation nation = new Nation();
			nation.setCode(model.getCode());
			nation.setName(model.getName());
			nation.setStatus(EnableStatus.ENABLE.getStatus());
			nationMapper.insertSelective(nation);
		}
	}

	private void initApprovalFlowContent() {
		List<ApprovalFlowContentModel> approvalFlowContentModelList = ApprovalFlowContentModel.build();
		for(ApprovalFlowContentModel model:approvalFlowContentModelList){
			ApprovalFlowContent approvalFlowContent = new ApprovalFlowContent();
			approvalFlowContent.setContentId(model.getContentId());
			approvalFlowContent.setContentPid(model.getContentPid());
			approvalFlowContent.setName(model.getName());
			approvalFlowContent.setSort(model.getSort());
			approvalFlowContent.setLevel(model.getLevel());
			approvalFlowContent.setIsLeaf(model.getIsLeaf());
			approvalFlowContent.setStatus(model.getStatus());
			approvalFlowContent.setCreateTime(new Date());
			approvalFlowContent.setUpdateTime(new Date());
			approvalFlowContentMapper.insertSelective(approvalFlowContent);
		}
	}

	private void initGoodsTaxRate(User user) {
		List<TaxRateModel> taxRateModelList = TaxRateModel.build();
		for(TaxRateModel model:taxRateModelList){
			GoodsTaxRate goodsTaxRate = new GoodsTaxRate();
			goodsTaxRate.setEnterpriseId(0L);
			goodsTaxRate.setCode(model.getCode());
			goodsTaxRate.setTaxRate(model.getTaxRate());
			goodsTaxRate.setStatus(EnableStatus.ENABLE.getStatus());
			goodsTaxRate.setSysType(SysType.SYSTEM.getCode());
			goodsTaxRate.setRemark("系统默认商品税率");
			goodsTaxRate.setCreaterId(user.getId());
			goodsTaxRate.setCreaterCode(user.getCode());
			goodsTaxRate.setCreaterName(user.getName());
			goodsTaxRate.setCreateTime(new Date());
			goodsTaxRate.setModifierId(user.getId());
			goodsTaxRate.setModifierCode(user.getCode());
			goodsTaxRate.setModifierName(user.getName());
			goodsTaxRate.setUpdateTime(new Date());
			goodsTaxRateMapper.insertSelective(goodsTaxRate);
		}
	}

	private void initGoodsDosage(User user) {
		List<GoodsDosageModel> goodsDosageModelList = GoodsDosageModel.build();
		for(GoodsDosageModel model:goodsDosageModelList){
			GoodsDosage goodsDosage = new GoodsDosage();
			goodsDosage.setEnterpriseId(0L);
			goodsDosage.setCode(model.getCode());
			goodsDosage.setName(model.getName());
			goodsDosage.setStatus(EnableStatus.ENABLE.getStatus());
			goodsDosage.setSysType(SysType.SYSTEM.getCode());
			goodsDosage.setRemark("系统默认商品剂型");
			goodsDosage.setCreaterId(user.getId());
			goodsDosage.setCreaterCode(user.getCode());
			goodsDosage.setCreaterName(user.getName());
			goodsDosage.setCreateTime(new Date());
			goodsDosage.setModifierId(user.getId());
			goodsDosage.setModifierCode(user.getCode());
			goodsDosage.setModifierName(user.getName());
			goodsDosage.setUpdateTime(new Date());
			goodsDosageMapper.insertSelective(goodsDosage);
		}
	}

	private void initGoodsUnit(User user) {
		List<GoodsUnitModel> goodsUnitModelList = GoodsUnitModel.build();
		for(GoodsUnitModel model:goodsUnitModelList){
			GoodsUnit goodsUnit = new GoodsUnit();
			goodsUnit.setEnterpriseId(0L);
			goodsUnit.setCode(model.getCode());
			goodsUnit.setName(model.getName());
			goodsUnit.setStatus(EnableStatus.ENABLE.getStatus());
			goodsUnit.setSysType(SysType.SYSTEM.getCode());
			goodsUnit.setRemark("系统默认商品单位");
			goodsUnit.setCreaterId(user.getId());
			goodsUnit.setCreaterCode(user.getCode());
			goodsUnit.setCreaterName(user.getName());
			goodsUnit.setCreateTime(new Date());
			goodsUnit.setModifierId(user.getId());
			goodsUnit.setModifierCode(user.getCode());
			goodsUnit.setModifierName(user.getName());
			goodsUnit.setUpdateTime(new Date());
			goodsUnitMapper.insertSelective(goodsUnit);
		}
	}

	private void initGoodsCategory(User user) {
		List<GoodsCategoryModel> goodsCategoryModelList = GoodsCategoryModel.build();
		for(GoodsCategoryModel model:goodsCategoryModelList){
			GoodsCategory goodsCategory = new GoodsCategory();
			goodsCategory.setEnterpriseId(0L);
			goodsCategory.setParentCategoryId(0L);
			goodsCategory.setType(model.getType());
			goodsCategory.setCode(model.getCode());
			goodsCategory.setName(model.getName());
			goodsCategory.setStatus(EnableStatus.ENABLE.getStatus());
			goodsCategory.setSysType(SysType.SYSTEM.getCode());
			goodsCategory.setRemark("系统默认商品分类");
			goodsCategory.setCreaterId(user.getId());
			goodsCategory.setCreaterCode(user.getCode());
			goodsCategory.setCreaterName(user.getName());
			goodsCategory.setCreateTime(new Date());
			goodsCategory.setModifierId(user.getId());
			goodsCategory.setModifierCode(user.getCode());
			goodsCategory.setModifierName(user.getName());
			goodsCategory.setUpdateTime(new Date());
			goodsCategoryMapper.insertSelective(goodsCategory);
		}
	}

	private void initSupplierGroup(User user) {
		List<SupplierGroupModel> supplierGroupModelList = SupplierGroupModel.build();
		for(SupplierGroupModel model:supplierGroupModelList){
			SupplierGroup supplierGroup = new SupplierGroup();
			supplierGroup.setEnterpriseId(0L);
			supplierGroup.setEnterpriseType(model.getEnterpriseType());
			supplierGroup.setCode(model.getCode());
			supplierGroup.setName(model.getName());
			supplierGroup.setStatus(EnableStatus.ENABLE.getStatus());
			supplierGroup.setRemark("系统默认供货单位分组");
			supplierGroup.setCreaterId(user.getId());
			supplierGroup.setCreaterCode(user.getCode());
			supplierGroup.setCreaterName(user.getName());
			supplierGroup.setCreateTime(new Date());
			supplierGroup.setModifierId(user.getId());
			supplierGroup.setModifierCode(user.getCode());
			supplierGroup.setModifierName(user.getName());
			supplierGroup.setUpdateTime(new Date());
			supplierGroupMapper.insertSelective(supplierGroup);
		}
	}

	private void initEnterpriseGroup(User user) {
		List<StoreGroupModel> storeGroupModelList = StoreGroupModel.build();
		for(StoreGroupModel model:storeGroupModelList){
			EnterpriseGroup enterpriseGroup = new EnterpriseGroup();
			enterpriseGroup.setEnterpriseId(0L);
			enterpriseGroup.setParentId(0L);
			enterpriseGroup.setType(model.getType());
			enterpriseGroup.setCode(model.getCode());
			enterpriseGroup.setName(model.getName());
			enterpriseGroup.setStatus(EnableStatus.ENABLE.getStatus());
			enterpriseGroup.setRemark("系统默认门店分组");
			enterpriseGroup.setCreaterId(user.getId());
			enterpriseGroup.setCreaterCode(user.getCode());
			enterpriseGroup.setCreaterName(user.getName());
			enterpriseGroup.setCreateTime(new Date());
			enterpriseGroup.setModifierId(user.getId());
			enterpriseGroup.setModifierCode(user.getCode());
			enterpriseGroup.setModifierName(user.getName());
			enterpriseGroup.setUpdateTime(new Date());
			enterpriseGroupMapper.insertSelective(enterpriseGroup);
		}
	}

	private void initStockWarn(Enterprise enterprise, UserVO user) {
		List<StockWarnModel> stockWarnModelList = StockWarnModel.build();
		for(StockWarnModel model:stockWarnModelList){
			WarnSet warnSet = new WarnSet();
			warnSet.setEnterpriseId(enterprise.getId());
			warnSet.setParentId(enterprise.getParentId());
			warnSet.setChainType(ChainType.Headquarters.getCode());
			// 4-表示库存预警设置
			warnSet.setSetType(4);
			warnSet.setSysType(SysType.SYSTEM.getCode());
			String content = model.getContent();
			// 库存预警设置，没有对应的资质，默认为0L
		    warnSet.setQualificationId(0L);
			warnSet.setContent(content);
			// 冗余字段，默认为0，用户自己输入
		    warnSet.setWarnDays(0);
			// 冗余字段，默认为空，用户自己输入
		    warnSet.setRoleIds(null);
		    warnSet.setStatus(EnableStatus.ENABLE.getStatus());
		    warnSet.setRemark("系统默认库存预警设置");
			warnSet.setCreaterId(user.getUserId());
			warnSet.setCreaterCode(user.getUserCode());
			warnSet.setCreaterName(user.getUserName());
			warnSet.setCreateTime(new Date());
			warnSet.setModifierId(user.getUserId());
			warnSet.setModifierCode(user.getUserCode());
			warnSet.setModifierName(user.getUserName());
			warnSet.setUpdateTime(new Date());
			warnSetMapper.insertSelective(warnSet);
		}
	}

	private void initGoodsWarn(Enterprise enterprise, UserVO user, List<GoodsQualification> goodsQualificationList) {
		List<GoodsWarnModel> goodsWarnModelList = GoodsWarnModel.build();
		for(GoodsWarnModel model:goodsWarnModelList){
			WarnSet warnSet = new WarnSet();
			warnSet.setEnterpriseId(enterprise.getId());
			warnSet.setParentId(enterprise.getParentId());
			warnSet.setChainType(ChainType.Headquarters.getCode());
			// 3-表示商品预警设置
			warnSet.setSetType(3);
			warnSet.setSysType(SysType.SYSTEM.getCode());
			String content = model.getContent();
		    Long qualificationId = getGoodsQualificationIdByName(content, goodsQualificationList);
		    warnSet.setQualificationId(qualificationId);
			warnSet.setContent(content);
			// 冗余字段，默认为0，用户自己输入
		    warnSet.setWarnDays(0);
			// 冗余字段，默认为空，用户自己输入
		    warnSet.setRoleIds(null);
		    warnSet.setStatus(EnableStatus.ENABLE.getStatus());
		    warnSet.setRemark("系统默认商品预警设置");
			warnSet.setCreaterId(user.getUserId());
			warnSet.setCreaterCode(user.getUserCode());
			warnSet.setCreaterName(user.getUserName());
			warnSet.setCreateTime(new Date());
			warnSet.setModifierId(user.getUserId());
			warnSet.setModifierCode(user.getUserCode());
			warnSet.setModifierName(user.getUserName());
			warnSet.setUpdateTime(new Date());
			warnSetMapper.insertSelective(warnSet);
		}
	}

	private void initSupplierWarn(Enterprise enterprise, UserVO user, List<EnterpriseQualification> enterpriseQualificationList) {
		List<SupplierWarnModel> supplierWarnModelList = SupplierWarnModel.build();
		for(SupplierWarnModel model:supplierWarnModelList){
			WarnSet warnSet = new WarnSet();
			warnSet.setEnterpriseId(enterprise.getId());
			warnSet.setParentId(enterprise.getParentId());
			warnSet.setChainType(ChainType.Headquarters.getCode());
			// 2-表示供货单位预警设置
			warnSet.setSetType(2);
			warnSet.setSysType(SysType.SYSTEM.getCode());
			String content = model.getContent();
		    Long qualificationId = getEnterpriseQualificationIdByName(content, enterpriseQualificationList);
		    warnSet.setQualificationId(qualificationId);
			warnSet.setContent(content);
			// 冗余字段，默认为0，用户自己输入
		    warnSet.setWarnDays(0);
			// 冗余字段，默认为空，用户自己输入
		    warnSet.setRoleIds(null);
		    warnSet.setStatus(EnableStatus.ENABLE.getStatus());
		    warnSet.setRemark("系统默认供货单位预警设置");
			warnSet.setCreaterId(user.getUserId());
			warnSet.setCreaterCode(user.getUserCode());
			warnSet.setCreaterName(user.getUserName());
			warnSet.setCreateTime(new Date());
			warnSet.setModifierId(user.getUserId());
			warnSet.setModifierCode(user.getUserCode());
			warnSet.setModifierName(user.getUserName());
			warnSet.setUpdateTime(new Date());
			warnSetMapper.insertSelective(warnSet);
		}
	}

	private void initUserWarn(Enterprise enterprise, UserVO user, List<UserQualification> userQualificationList) {
		List<UserWarnModel> userWarnModelList = UserWarnModel.build();
		for(UserWarnModel model:userWarnModelList){
			WarnSet warnSet = new WarnSet();
			warnSet.setEnterpriseId(enterprise.getId());
			warnSet.setParentId(enterprise.getParentId());
			warnSet.setChainType(ChainType.Headquarters.getCode());
			// 1-表示员工预警设置
			warnSet.setSetType(1);
			warnSet.setSysType(SysType.SYSTEM.getCode());
			String content = model.getContent();
		    Long qualificationId = getUserQualificationIdByName(content, userQualificationList);
		    warnSet.setQualificationId(qualificationId);
			warnSet.setContent(content);
			// 冗余字段，默认为0，用户自己输入
		    warnSet.setWarnDays(0);
			// 冗余字段，默认为空，用户自己输入
		    warnSet.setRoleIds(null);
		    warnSet.setStatus(EnableStatus.ENABLE.getStatus());
		    warnSet.setRemark("系统默认员工预警设置");
			warnSet.setCreaterId(user.getUserId());
			warnSet.setCreaterCode(user.getUserCode());
			warnSet.setCreaterName(user.getUserName());
			warnSet.setCreateTime(new Date());
			warnSet.setModifierId(user.getUserId());
			warnSet.setModifierCode(user.getUserCode());
			warnSet.setModifierName(user.getUserName());
			warnSet.setUpdateTime(new Date());
			warnSetMapper.insertSelective(warnSet);
		}
	}

	private void initEnterpriseWarn(Enterprise enterprise, UserVO user, List<EnterpriseQualification> enterpriseQualificationList) {
		List<EnterpriseWarnModel> enterpriseWarnModelList = EnterpriseWarnModel.build();
		for(EnterpriseWarnModel model:enterpriseWarnModelList){
			WarnSet warnSet = new WarnSet();
			warnSet.setEnterpriseId(enterprise.getId());
			warnSet.setParentId(enterprise.getParentId());
			warnSet.setChainType(ChainType.Headquarters.getCode());
			// 设置类型（0-企业；1-员工；2-供货单位；3-商品；4-库存）注：当chain_type=1或2（自营店）时，set_type只能为（0-企业；1-员工；4-库存）三种
			// 0-表示企业预警设置
			warnSet.setSetType(0);
			warnSet.setSysType(SysType.SYSTEM.getCode());
			String content = model.getContent();
		    Long qualificationId = getEnterpriseQualificationIdByName(content, enterpriseQualificationList);
		    warnSet.setQualificationId(qualificationId);
			warnSet.setContent(content);
			// 冗余字段，默认为0，用户自己输入
		    warnSet.setWarnDays(0);
			// 冗余字段，默认为空，用户自己输入
		    warnSet.setRoleIds(null);
		    warnSet.setStatus(EnableStatus.ENABLE.getStatus());
		    warnSet.setRemark("系统默认企业预警设置");
			warnSet.setCreaterId(user.getUserId());
			warnSet.setCreaterCode(user.getUserCode());
			warnSet.setCreaterName(user.getUserName());
			warnSet.setCreateTime(new Date());
			warnSet.setModifierId(user.getUserId());
			warnSet.setModifierCode(user.getUserCode());
			warnSet.setModifierName(user.getUserName());
			warnSet.setUpdateTime(new Date());
			warnSetMapper.insertSelective(warnSet);
		}
	}

	private Long getGoodsQualificationIdByName(String name, List<GoodsQualification> goodsQualificationList) {
		Long qualificationId = 0L;
		for(GoodsQualification qualification:goodsQualificationList){
			if(name.equals(qualification.getName())){
				return qualification.getId();
			}
		}
		return qualificationId;
	}

	private Long getUserQualificationIdByName(String name, List<UserQualification> userQualificationList) {
		Long qualificationId = 0L;
		for(UserQualification qualification:userQualificationList){
			if(name.equals(qualification.getName())){
				return qualification.getId();
			}
		}
		return qualificationId;
	}

	private Long getEnterpriseQualificationIdByName(String name,
			List<EnterpriseQualification> enterpriseQualificationList) {
		Long qualificationId = 0L;
		for(EnterpriseQualification qualification:enterpriseQualificationList){
			if(name.equals(qualification.getName())){
				return qualification.getId();
			}
		}
		return qualificationId;
	}

	private List<GoodsQualification> initGoodsQualification(User user) {
		List<GoodsQualification> goodsQualificationList = new ArrayList<GoodsQualification>();
		List<GoodsQualificationModel> goodsQualificationModelList = GoodsQualificationModel.build();
		for(GoodsQualificationModel model:goodsQualificationModelList){
			GoodsQualification goodsQualification = new GoodsQualification();
			goodsQualification.setEnterpriseId(0L);
			// 0-全部
			goodsQualification.setCheckTypeId(model.getCheckTypeId());
			goodsQualification.setCheckTypeName("全部");
			goodsQualification.setSysType(SysType.SYSTEM.getCode());
			goodsQualification.setTypeMust(model.getTypeMust());
			goodsQualification.setCode(model.getCode());
			goodsQualification.setName(model.getName());
			goodsQualification.setControlType(model.getControlType());
			goodsQualification.setCodeMust(model.getCodeMust());
			goodsQualification.setValidUntilMust(model.getValidUntilMust());
			goodsQualification.setFileMust(model.getFileMust());
			goodsQualification.setStatus(EnableStatus.ENABLE.getStatus());
			goodsQualification.setRemark("系统默认商品资质");
			goodsQualification.setCreaterId(user.getId());
			goodsQualification.setCreaterCode(user.getCode());
			goodsQualification.setCreaterName(user.getName());
			goodsQualification.setCreateTime(new Date());
			goodsQualification.setModifierId(user.getId());
			goodsQualification.setModifierCode(user.getCode());
			goodsQualification.setModifierName(user.getName());
			goodsQualification.setUpdateTime(new Date());
			goodsQualificationMapper.insertSelective(goodsQualification);
			goodsQualificationList.add(goodsQualification);
		}
		return goodsQualificationList;
	}

	private List<UserQualification> initUserQualification(User user, List<Position> positionList) {
		List<UserQualification> userQualificationList = new ArrayList<UserQualification>();
		List<UserQualificationModel> userQualificationModelList = UserQualificationModel.build();
		for(UserQualificationModel model:userQualificationModelList){
			UserQualification userQualification = new UserQualification();
			userQualification.setEnterpriseId(0L);
			userQualification.setSuitableUnit(model.getSuitableUnit());
			String positionIds = getPositionIdsByPositionNames(model.getPositionNames(), positionList);
			userQualification.setPositionIds(positionIds);
			userQualification.setSysType(SysType.SYSTEM.getCode());
			userQualification.setTypeMust(model.getTypeMust());
			userQualification.setCode(model.getCode());
			userQualification.setName(model.getName());
			userQualification.setControlType(model.getControlType());
			userQualification.setGradeMust(model.getGradeMust());
			userQualification.setCodeMust(model.getCodeMust());
			userQualification.setRegisterCodeMust(model.getRegisterCodeMust());
			userQualification.setRegionMust(model.getRegionMust());
			userQualification.setCategoryMust(model.getCategoryMust());
			userQualification.setRangeMust(model.getRangeMust());
			userQualification.setFileMust(model.getFileMust());
			userQualification.setStatus(EnableStatus.ENABLE.getStatus());
			userQualification.setRemark("系统默认员工资质");
			userQualification.setCreaterId(user.getId());
			userQualification.setCreaterCode(user.getCode());
			userQualification.setCreaterName(user.getName());
			userQualification.setCreateTime(new Date());
			userQualification.setModifierId(user.getId());
			userQualification.setModifierCode(user.getCode());
			userQualification.setModifierName(user.getName());
			userQualification.setUpdateTime(new Date());
			userQualificationMapper.insertSelective(userQualification);
			userQualificationList.add(userQualification);
		}
		return userQualificationList;
	}

	private String getPositionIdsByPositionNames(String positionNames, List<Position> positionList) {
		StringBuffer sb = new StringBuffer();
		String[] positionNameArr = positionNames.split(",");
		for(Position position:positionList){
			for(int i=0;i<positionNameArr.length;i++){
				String positionName = positionNameArr[i];
				if(positionName.equals(position.getName())){
					sb.append(",").append(position.getId());
				}
			}
		}
		return sb.toString().substring(1);
	}

	private List<EnterpriseQualification> initEnterpriseQualification(User user) {
		List<EnterpriseQualification> enterpriseQualificationList = new ArrayList<EnterpriseQualification>();
		List<EnterpriseQualificationModel> enterpriseQualificationModelList = EnterpriseQualificationModel.build();
		for(EnterpriseQualificationModel model:enterpriseQualificationModelList){
			EnterpriseQualification enterpriseQualification = new EnterpriseQualification();
			enterpriseQualification.setEnterpriseId(0L);
			enterpriseQualification.setSysType(SysType.SYSTEM.getCode());
			enterpriseQualification.setSuitableUnit(model.getSuitableUnit());
			enterpriseQualification.setTypeMust(model.getTypeMust());
			enterpriseQualification.setCode(model.getCode());
			enterpriseQualification.setName(model.getName());
			enterpriseQualification.setControlType(model.getControlType());
			enterpriseQualification.setCodeMust(model.getCodeMust());
			enterpriseQualification.setValidUntilMust(model.getValidUntilMust());
			enterpriseQualification.setFileMust(model.getFileMust());
			enterpriseQualification.setStatus(EnableStatus.ENABLE.getStatus());
			enterpriseQualification.setRemark("系统默认企业资质");
			enterpriseQualification.setCreaterId(user.getId());
			enterpriseQualification.setCreaterCode(user.getCode());
			enterpriseQualification.setCreaterName(user.getName());
			enterpriseQualification.setCreateTime(new Date());
			enterpriseQualification.setModifierId(user.getId());
			enterpriseQualification.setModifierCode(user.getCode());
			enterpriseQualification.setModifierName(user.getName());
			enterpriseQualification.setUpdateTime(new Date());
			enterpriseQualificationMapper.insertSelective(enterpriseQualification);
			enterpriseQualificationList.add(enterpriseQualification);
		}
		return enterpriseQualificationList;
	}

	private void initBusinessScope(User user) {
		List<BusinessScopeModel> businessScopeModelList = BusinessScopeModel.build();
		for(BusinessScopeModel businessScopeModel:businessScopeModelList){
			BusinessScope businessScope = new BusinessScope();
			businessScope.setEnterpriseId(0L);
			businessScope.setSysType(SysType.SYSTEM.getCode());
			businessScope.setBusinessVariety(businessScopeModel.getBusinessVariety());
			businessScope.setCode(businessScopeModel.getCode());
			businessScope.setName(businessScopeModel.getDescription());
			businessScope.setStatus(EnableStatus.ENABLE.getStatus());
			businessScope.setRemark("系统默认经营范围");
			businessScope.setCreaterId(user.getId());
			businessScope.setCreaterCode(user.getCode());
			businessScope.setCreaterName(user.getName());
			businessScope.setCreateTime(new Date());
			businessScope.setModifierId(user.getId());
			businessScope.setModifierCode(user.getCode());
			businessScope.setModifierName(user.getName());
			businessScope.setUpdateTime(new Date());
			businessScopeMapper.insertSelective(businessScope);
		}
	}

	private void initMaintanceMeasures(User user) {
		List<MaintanceMeasuresModel> maintanceMeasuresModelLsit = MaintanceMeasuresModel.build();
		for(MaintanceMeasuresModel model:maintanceMeasuresModelLsit){
			QualitySet qualitySet = new QualitySet();
			qualitySet.setEnterpriseId(0L);
			qualitySet.setSetType(QualitySetType.MAINTANCE_MEASURES.getSetType());
			qualitySet.setSysType(SysType.SYSTEM.getCode());
			qualitySet.setCode(model.getCode());
			qualitySet.setDescription(model.getDescription());
			qualitySet.setType(model.getType());
			// 冗余字段，默认为0
			qualitySet.setHaveFile(0);
			qualitySet.setStatus(EnableStatus.ENABLE.getStatus());
			qualitySet.setRemark("系统默认养护措施");
			qualitySet.setCreaterId(user.getId());
			qualitySet.setCreaterCode(user.getCode());
			qualitySet.setCreaterName(user.getName());
			qualitySet.setCreateTime(new Date());
			qualitySet.setModifierId(user.getId());
			qualitySet.setModifierCode(user.getCode());
			qualitySet.setModifierName(user.getName());
			qualitySet.setUpdateTime(new Date());
			qualitySetMapper.insertSelective(qualitySet);
		}
	}

	private void initCheckConclusion(User user) {
		List<CheckConclusionModel> checkConclusionModelList = CheckConclusionModel.build();
		for(CheckConclusionModel model:checkConclusionModelList){
			QualitySet qualitySet = new QualitySet();
			qualitySet.setEnterpriseId(0L);
			qualitySet.setSetType(QualitySetType.CHECK_CONCLUSION.getSetType());
			qualitySet.setSysType(SysType.SYSTEM.getCode());
			qualitySet.setCode(model.getCode());
			qualitySet.setDescription(model.getDescription());
			// 冗余字段，默认为0
			qualitySet.setType(model.getChainType());
			// 冗余字段，默认为0
			qualitySet.setHaveFile(0);
			qualitySet.setStatus(EnableStatus.ENABLE.getStatus());
			qualitySet.setRemark("系统默认验收结论");
			qualitySet.setCreaterId(user.getId());
			qualitySet.setCreaterCode(user.getCode());
			qualitySet.setCreaterName(user.getName());
			qualitySet.setCreateTime(new Date());
			qualitySet.setModifierId(user.getId());
			qualitySet.setModifierCode(user.getCode());
			qualitySet.setModifierName(user.getName());
			qualitySet.setUpdateTime(new Date());
			qualitySetMapper.insertSelective(qualitySet);
		}
	}

	private void initCheckPoject(User user) {
		List<CheckPojectModel> checkPojectModelList = CheckPojectModel.build();
		for(CheckPojectModel model:checkPojectModelList){
			QualitySet qualitySet = new QualitySet();
			qualitySet.setEnterpriseId(0L);
			qualitySet.setSetType(QualitySetType.CHECK_POJECT.getSetType());
			qualitySet.setSysType(SysType.SYSTEM.getCode());
			qualitySet.setCode(model.getCode());
			qualitySet.setDescription(model.getDescription());
			qualitySet.setType(model.getType());
			qualitySet.setHaveFile(model.getHaveFile());
			qualitySet.setStatus(EnableStatus.ENABLE.getStatus());
			qualitySet.setRemark("系统默认验收项目");
			qualitySet.setCreaterId(user.getId());
			qualitySet.setCreaterCode(user.getCode());
			qualitySet.setCreaterName(user.getName());
			qualitySet.setCreateTime(new Date());
			qualitySet.setModifierId(user.getId());
			qualitySet.setModifierCode(user.getCode());
			qualitySet.setModifierName(user.getName());
			qualitySet.setUpdateTime(new Date());
			qualitySetMapper.insertSelective(qualitySet);
		}
	}

	private void initCheckType(User user) {
		List<CheckTypeModel> checkTypeModelList = CheckTypeModel.build();
		for(CheckTypeModel model:checkTypeModelList){
			QualitySet qualitySet = new QualitySet();
			qualitySet.setEnterpriseId(0L);
			qualitySet.setSetType(QualitySetType.CHECK_TYPE.getSetType());
			qualitySet.setSysType(SysType.SYSTEM.getCode());
			qualitySet.setCode(model.getCode());
			qualitySet.setDescription(model.getDescription());
			qualitySet.setType(model.getType());
			// 冗余字段，默认为0
			qualitySet.setHaveFile(0);
			qualitySet.setStatus(EnableStatus.ENABLE.getStatus());
			qualitySet.setRemark("系统默认验收类型");
			qualitySet.setCreaterId(user.getId());
			qualitySet.setCreaterCode(user.getCode());
			qualitySet.setCreaterName(user.getName());
			qualitySet.setCreateTime(new Date());
			qualitySet.setModifierId(user.getId());
			qualitySet.setModifierCode(user.getCode());
			qualitySet.setModifierName(user.getName());
			qualitySet.setUpdateTime(new Date());
			qualitySetMapper.insertSelective(qualitySet);
		}
	}

	private void initHandleMeasures(User user) {
		List<HandleMeasuresModel> handleMeasuresModelList = HandleMeasuresModel.build();
		for(HandleMeasuresModel model:handleMeasuresModelList){
			QualitySet qualitySet = new QualitySet();
			qualitySet.setEnterpriseId(0L);
			qualitySet.setSetType(QualitySetType.HANDLE_MEASURES.getSetType());
			qualitySet.setSysType(SysType.SYSTEM.getCode());
			qualitySet.setCode(model.getCode());
			qualitySet.setDescription(model.getDescription());
			// 冗余字段，默认为0
			qualitySet.setType(0);
			// 冗余字段，默认为0
			qualitySet.setHaveFile(0);
			qualitySet.setStatus(EnableStatus.ENABLE.getStatus());
			qualitySet.setRemark("系统默认处置措施");
			qualitySet.setCreaterId(user.getId());
			qualitySet.setCreaterCode(user.getCode());
			qualitySet.setCreaterName(user.getName());
			qualitySet.setCreateTime(new Date());
			qualitySet.setModifierId(user.getId());
			qualitySet.setModifierCode(user.getCode());
			qualitySet.setModifierName(user.getName());
			qualitySet.setUpdateTime(new Date());
			qualitySetMapper.insertSelective(qualitySet);
		}
	}

	private void initReturnReason(User user) {
		List<ReturnReasonModel> returnReasonModelList = ReturnReasonModel.build();
		for(ReturnReasonModel model:returnReasonModelList){
			QualitySet qualitySet = new QualitySet();
			qualitySet.setEnterpriseId(0L);
			qualitySet.setSetType(QualitySetType.RETURN_REASON.getSetType());
			qualitySet.setSysType(SysType.SYSTEM.getCode());
			qualitySet.setCode(model.getCode());
			qualitySet.setDescription(model.getDescription());
			qualitySet.setType(model.getType());
			// 冗余字段，默认为0
			qualitySet.setHaveFile(0);
			qualitySet.setStatus(EnableStatus.ENABLE.getStatus());
			qualitySet.setRemark("系统默认退货原因");
			qualitySet.setCreaterId(user.getId());
			qualitySet.setCreaterCode(user.getCode());
			qualitySet.setCreaterName(user.getName());
			qualitySet.setCreateTime(new Date());
			qualitySet.setModifierId(user.getId());
			qualitySet.setModifierCode(user.getCode());
			qualitySet.setModifierName(user.getName());
			qualitySet.setUpdateTime(new Date());
			qualitySetMapper.insertSelective(qualitySet);
		}
	}

	private void initUnqualifiedReason(User user) {
		List<UnqualifiedReasonModel> unqualifiedReasonModelList = UnqualifiedReasonModel.build();
		for(UnqualifiedReasonModel model:unqualifiedReasonModelList){
			QualitySet qualitySet = new QualitySet();
			qualitySet.setEnterpriseId(0L);
			qualitySet.setSetType(QualitySetType.UNQUALIFIED_REASON.getSetType());
			qualitySet.setSysType(SysType.SYSTEM.getCode());
			qualitySet.setCode(model.getCode());
			qualitySet.setDescription(model.getDescription());
			// 冗余字段，默认为0
			qualitySet.setType(0);
			// 冗余字段，默认为0
			qualitySet.setHaveFile(0);
			qualitySet.setStatus(EnableStatus.ENABLE.getStatus());
			qualitySet.setRemark("系统默认不合格原因");
			qualitySet.setCreaterId(user.getId());
			qualitySet.setCreaterCode(user.getCode());
			qualitySet.setCreaterName(user.getName());
			qualitySet.setCreateTime(new Date());
			qualitySet.setModifierId(user.getId());
			qualitySet.setModifierCode(user.getCode());
			qualitySet.setModifierName(user.getName());
			qualitySet.setUpdateTime(new Date());
			qualitySetMapper.insertSelective(qualitySet);
		}
	}

	private void initRejectReason(User user) {
		List<RejectReasonModel> rejectReasonModelList = RejectReasonModel.build();
		for(RejectReasonModel model:rejectReasonModelList){
			QualitySet qualitySet = new QualitySet();
			qualitySet.setEnterpriseId(0L);
			qualitySet.setSetType(QualitySetType.REJECTR_EASON.getSetType());
			qualitySet.setSysType(SysType.SYSTEM.getCode());
			qualitySet.setCode(model.getCode());
			qualitySet.setDescription(model.getDescription());
			// 冗余字段，默认为0
			qualitySet.setType(0);
			// 冗余字段，默认为0
			qualitySet.setHaveFile(0);
			qualitySet.setStatus(EnableStatus.ENABLE.getStatus());
			qualitySet.setRemark("系统默认拒收原因");
			qualitySet.setCreaterId(user.getId());
			qualitySet.setCreaterCode(user.getCode());
			qualitySet.setCreaterName(user.getName());
			qualitySet.setCreateTime(new Date());
			qualitySet.setModifierId(user.getId());
			qualitySet.setModifierCode(user.getCode());
			qualitySet.setModifierName(user.getName());
			qualitySet.setUpdateTime(new Date());
			qualitySetMapper.insertSelective(qualitySet);
		}
	}

	private List<SysRole> initSysRole(User user, List<Department> deptList, List<Position> positionList) {
		List<SysRole> sysRoleList = new ArrayList<SysRole>();
		List<RoleModel> roleModelList = RoleModel.build();
		for(RoleModel roleModel:roleModelList){
			SysRole sysRole = new SysRole();
			sysRole.setEnterpriseId(0L);
			sysRole.setParentId(0L);
			sysRole.setChainType(ChainType.Headquarters.getCode());
			String deptCode = roleModel.getDeptCode();
			Long deptId = getDeptIdByDeptCode(deptCode, deptList);
			sysRole.setDeptId(deptId);
			String positionCode = roleModel.getPositionCode();
			Long positionId = getPositionIdByPositionCode(positionCode, positionList);
			sysRole.setPositionId(positionId);
			sysRole.setCode(roleModel.getCode());
			sysRole.setName(roleModel.getName());
			sysRole.setSysType(SysType.SYSTEM.getCode());
			sysRole.setShowOrder(Integer.valueOf(roleModel.getCode()));
			sysRole.setStatus(EnableStatus.ENABLE.getStatus());
			sysRole.setRemark("系统默认角色");
			sysRole.setCreaterId(user.getId());
			sysRole.setCreaterCode(user.getCode());
			sysRole.setCreaterName(user.getName());
			sysRole.setCreateTime(new Date());
			sysRole.setModifierId(user.getId());
			sysRole.setModifierCode(user.getCode());
			sysRole.setModifierName(user.getName());
			sysRole.setUpdateTime(new Date());
		    sysRoleMapper.insertSelective(sysRole);
		    sysRoleList.add(sysRole);
		}
		return sysRoleList;
	}

	private Long getPositionIdByPositionCode(String positionCode, List<Position> positionList) {
		Long positionId = 0L;
		for(Position position:positionList){
			if(positionCode.equals(position.getCode())){
				return position.getId();
			}
		}
		return positionId;
	}

	private List<Position> initPosition(User user, List<Department> deptList) {
		List<Position> positionList = new ArrayList<Position>();
		List<PositionModel> positionModelList = PositionModel.build();
		for(PositionModel positionModel:positionModelList){
			Position position = new Position();
			String deptCode = positionModel.getDeptCode();
			Long deptId = getDeptIdByDeptCode(deptCode, deptList);
			position.setDeptId(deptId);
			position.setEnterpriseId(0L);
			position.setParentId(0L);
			position.setCode(positionModel.getCode());
			position.setName(positionModel.getName());
			position.setSysType(SysType.SYSTEM.getCode());
			// 直接接触药品（0-否；1-是）
			position.setContactDrug(positionModel.getContactDrug());
			position.setStatus(EnableStatus.ENABLE.getStatus());
			position.setRemark("系统默认岗位");
			position.setCreaterId(user.getId());
			position.setCreaterCode(user.getCode());
			position.setCreaterName(user.getName());
			position.setCreateTime(new Date());
			position.setModifierId(user.getId());
			position.setModifierCode(user.getCode());
			position.setModifierName(user.getName());
			position.setUpdateTime(new Date());
			positionMapper.insertSelective(position);
			positionList.add(position);
		}
		return positionList;
	}

	private Long getDeptIdByDeptCode(String deptCode, List<Department> deptList) {
		Long deptId = 0L;
		for(Department department:deptList){
			if(deptCode.equals(department.getCode())){
				return department.getId();
			}
		}
		return deptId;
	}

	private List<Department> initDeptment(User user) {
		List<Department> departmentList = new ArrayList<Department>();
		List<DeptModel> deptModelList = DeptModel.build();
		for(DeptModel deptModel:deptModelList){
			Department department = new Department();
			department.setParentDeptId(0L);
			department.setEnterpriseId(0L);
			department.setParentId(0L);
			department.setCode(deptModel.getCode());
			department.setName(deptModel.getName());
			department.setSysType(SysType.SYSTEM.getCode());
			department.setStatus(EnableStatus.ENABLE.getStatus());
			department.setRemark("系统默认部门");
			department.setCreaterId(user.getId());
			department.setCreaterCode(user.getCode());
			department.setCreaterName(user.getName());
			department.setCreateTime(new Date());
			department.setModifierId(user.getId());
			department.setModifierCode(user.getCode());
			department.setModifierName(user.getName());
			department.setUpdateTime(new Date());
			departmentMapper.insertSelective(department);
			departmentList.add(department);
		}
		return departmentList;
	}

	private User initUser() {
		User user = new User();
		user.setEnterpriseId(0L);
		user.setParentId(0L);
		user.setCode("999999");
		user.setPinyin("FJYLSCJGLY");
		user.setName("菲加云连锁超级管理员");
		user.setStatus(EnableStatus.ENABLE.getStatus());
		user.setRemark("系统默认人员");
		userMapper.insertSelective(user);
		
		UserAdministration userAdministration = new UserAdministration();
		userAdministration.setEnterpriseId(0L);
		userAdministration.setParentId(0L);
		userAdministration.setUserId(user.getId());
		userAdministration.setUserType(String.valueOf(UserTypeEum.SYSTEM_USER.getCode()));
		userAdministration.setLoginAccount("999999");
		String password = MD5Utils.getMD5String("1qaz@WSX");
		userAdministration.setPassword(password);
		userAdministration.setPasswordConfirm(password);
		userAdministration.setDeptIds("0");
		userAdministration.setPositionIds("0");
		userAdministration.setRoleIds("0");
		userAdministration.setStatus(EnableStatus.ENABLE.getStatus());
		userAdministrationMapper.insertSelective(userAdministration);
		return user;
	}

	private void initManageConfigData(Enterprise enterprise, UserVO user) throws Exception {
		ManageConfig manageConfig = new ManageConfig();
		manageConfig.setEnterpriseId(enterprise.getId());
		manageConfig.setParentId(enterprise.getParentId());
		manageConfig.setChainType(enterprise.getChainType());
		// 供货单位编码规则;供货单位分组+四位流水码;总部可编辑，门店不显示
		manageConfig.setSupplierCodeRule(SupplierCodeRule.GROUPANDSTREAM.getCode());
		// 商品信息编码规则;商品分组+四位流水码;总部可编辑，门店不显示
		manageConfig.setGoodsCodeRule(0);
		// 员工信息编码规则;部门+四位流水码;总部可编辑，门店读取总部设置
		manageConfig.setUserCodeRule(UserCoderRule.SYSTEM_SET_DEPT_SERIAL_NUM.getCode());
		// 业务单据价格;含税价;总部可编辑，门店读取总部设置
		manageConfig.setPriceControl(0);
		// 业务单据输入顺序;倒序;总部可编辑，门店读取总部设置
		manageConfig.setEnterOrder(1);
		// 库存占用量控制;开启;总部可编辑，门店读取总部设置
		manageConfig.setInventoryOccupancy(EnableStatus.ENABLE.getStatus());
		// 基础数据质量控制;开启;总部、门店皆可编辑
		manageConfig.setQualityControl(EnableStatus.ENABLE.getStatus());
		// 业务流程质量控制;开启;总部、门店皆可编辑
		manageConfig.setBusinessControl(EnableStatus.ENABLE.getStatus());
		// POS质量控制;开启;总部、门店皆可编辑
		manageConfig.setPosControl(EnableStatus.ENABLE.getStatus());
		// 审批流程控制;开启;总部、门店皆可编辑
		manageConfig.setApprovalControl(EnableStatus.ENABLE.getStatus());
		// 零成本入库（0禁止；1-允许）
		manageConfig.setZeroCostIn(0);

		manageConfig.setRemark("系统默认管理设置");
		manageConfig.setCreaterId(user.getUserId());
		manageConfig.setCreaterCode(user.getUserCode());
		manageConfig.setCreaterName(user.getUserName());
		manageConfig.setCreateTime(new Date());
		manageConfig.setModifierId(user.getUserId());
		manageConfig.setModifierCode(user.getUserCode());
		manageConfig.setModifierName(user.getUserName());
		manageConfig.setUpdateTime(new Date());
		manageConfigMapper.insertSelective(manageConfig);
	}

	private void initWarehouseData(Enterprise enterprise, UserVO user) throws Exception {
		// 初始化仓库、库区、货区、货位
		Integer chainType = enterprise.getChainType();
		if(chainType == ChainType.Headquarters.getCode()){
			initParentWarehouseInfo(enterprise, user);
		}else{// 分店
			initStoreWarehouseInfo(enterprise, user);
		}
	}

	private void initStoreWarehouseInfo(Enterprise enterprise, UserVO user) {
		// 仓库
		Warehouse warehouse = new Warehouse();
		Long enterpriseId = enterprise.getId();
		warehouse.setEnterpriseId(enterpriseId);
		Long parentId = enterprise.getParentId();
		warehouse.setParentId(parentId);
		warehouse.setSysType(SysType.SYSTEM.getCode());
		String enterpriseCode = enterprise.getCode();
		warehouse.setCode(enterpriseCode);
		warehouse.setName(enterprise.getName()+"店内区域");
		// 性质（0-自营；1-第三方）
		warehouse.setNature(0);
		// 面积（㎡）
		warehouse.setAcreage(null);
		warehouse.setAddress(enterprise.getCompanyAddress());
		// 库区编码长度
		warehouse.setAreaCodeLength(2);
		// 货区编码长度
		warehouse.setCargoAreaCodeLength(2);
		// 货位编码长度
		warehouse.setShelfCodeLength(4);
		// 间隔符
		warehouse.setSpacer("");
		// 负责人ID
		warehouse.setManagerId(enterprise.getBusinessManId());
		// 负责人编码
		warehouse.setManagerCode(enterprise.getBusinessManCode());
		// 负责人名称
		warehouse.setManagerName(enterprise.getBusinessManName());
		warehouse.setStatus(EnableStatus.ENABLE.getStatus());
		warehouse.setRemark("系统默认店内区域");
		warehouse.setCreaterId(user.getUserId());
		warehouse.setCreaterCode(user.getUserCode());
		warehouse.setCreaterName(user.getUserName());
		warehouse.setCreateTime(new Date());
		warehouse.setModifierId(user.getUserId());
		warehouse.setModifierCode(user.getUserCode());
		warehouse.setModifierName(user.getUserName());
		warehouse.setUpdateTime(new Date());
		warehouseMapper.insertSelective(warehouse);
		Long warehouseId = warehouse.getId();
		String warehouseName = warehouse.getName();
		// 库区
		for(int i=1;i<4;i++){
			WarehouseArea warehouseArea = new WarehouseArea();
			warehouseArea.setEnterpriseId(enterpriseId);
			warehouseArea.setParentId(parentId);
			warehouseArea.setSysType(SysType.SYSTEM.getCode());
			warehouseArea.setWarehouseId(warehouseId);
			warehouseArea.setWarehouseName(warehouseName);
			// 类型（0-常温；1-阴凉；2-冷藏；3-冷冻）
			warehouseArea.setType(i);
			warehouseArea.setCode(enterpriseCode+"0"+i);
			if(i==1){
				warehouseArea.setName("常温");
			}else if(i==2){
				warehouseArea.setName("阴凉");
			}else if(i==3){
				warehouseArea.setName("冷藏");
			}
			// 温度下限（℃）
			warehouseArea.setTempLowLimit(BigDecimal.ZERO);
			// 温度上限（℃）
			warehouseArea.setTempHighLimit(new BigDecimal(30));
			// 相对湿度下限（%）
			warehouseArea.setHumidityLowLimit(new BigDecimal(35));
			// 相对湿度上限（%）
			warehouseArea.setHumidityHighLimit(new BigDecimal(75));
			// 面积（㎡）
			warehouseArea.setAcreage(null);
			// 负责人ID
			warehouseArea.setManagerId(enterprise.getBusinessManId());
			// 负责人编码
			warehouseArea.setManagerCode(enterprise.getBusinessManCode());
			// 负责人名称
			warehouseArea.setManagerName(enterprise.getBusinessManName());
			warehouseArea.setStatus(EnableStatus.ENABLE.getStatus());
			warehouseArea.setRemark("系统默认库区");
			warehouseArea.setCreaterId(user.getUserId());
			warehouseArea.setCreaterCode(user.getUserCode());
			warehouseArea.setCreaterName(user.getUserName());
			warehouseArea.setCreateTime(new Date());
			warehouseArea.setModifierId(user.getUserId());
			warehouseArea.setModifierCode(user.getUserCode());
			warehouseArea.setModifierName(user.getUserName());
			warehouseArea.setUpdateTime(new Date());
			warehouseAreaMapper.insertSelective(warehouseArea);

			// 库区ID
			Long warehouseAreaId = warehouseArea.getId();
			String warehouseAreaName = warehouseArea.getName();

			for(int j=1;j<3;j++){
				WarehouseCargoArea warehouseCargoArea = new WarehouseCargoArea();
				warehouseCargoArea.setEnterpriseId(enterpriseId);
				warehouseCargoArea.setParentId(parentId);
				warehouseCargoArea.setSysType(SysType.SYSTEM.getCode());
				warehouseCargoArea.setWarehouseId(warehouseId);
				warehouseCargoArea.setWarehouseAreaId(warehouseAreaId);
				warehouseCargoArea.setCode(enterpriseCode+"0"+i+"0"+j);
				// 作业类型（0-存储作业区域；1-辅助作业区域；2-存储设备）
				warehouseCargoArea.setJobType(0);
				if(j==1) {
					warehouseCargoArea.setName(warehouseAreaName+"合格品");
					// 作业区域
					warehouseCargoArea.setJobArea(0);
				}else if(j==2){
					warehouseCargoArea.setName(warehouseAreaName+"不合格品");
					// 作业区域
					warehouseCargoArea.setJobArea(2);
				}
				// 层数
				warehouseCargoArea.setLayerQuantity(null);
				// 货位数量
				warehouseCargoArea.setShelfQuantity(null);
				warehouseCargoArea.setStatus(EnableStatus.ENABLE.getStatus());
				warehouseCargoArea.setRemark("系统默认货区");
				warehouseCargoArea.setCreaterId(user.getUserId());
				warehouseCargoArea.setCreaterCode(user.getUserCode());
				warehouseCargoArea.setCreaterName(user.getUserName());
				warehouseCargoArea.setCreateTime(new Date());
				warehouseCargoArea.setModifierId(user.getUserId());
				warehouseCargoArea.setModifierCode(user.getUserCode());
				warehouseCargoArea.setModifierName(user.getUserName());
				warehouseCargoArea.setUpdateTime(new Date());
				warehouseCargoAreaMapper.insertSelective(warehouseCargoArea);
				Long cargoAreaId = warehouseCargoArea.getId();

				String warehouseCargoAreaName = warehouseCargoArea.getName();

				WarehouseShelf warehouseShelf = new WarehouseShelf();
				warehouseShelf.setEnterpriseId(enterpriseId);
				warehouseShelf.setParentId(parentId);
				warehouseShelf.setSysType(SysType.SYSTEM.getCode());
				warehouseShelf.setWarehouseId(warehouseId);
				warehouseShelf.setWarehouseAreaId(warehouseAreaId);
				warehouseShelf.setCargoAreaId(cargoAreaId);
				warehouseShelf.setCode(enterpriseCode+"0"+i+"0"+j+"000"+j);
				if(j==1){
					warehouseShelf.setName(warehouseCargoAreaName+"货位");
				}else if(j==2){
					warehouseShelf.setName(warehouseCargoAreaName+"货位");
				}
				// 编码前缀
				warehouseShelf.setPrefix("");
				// 起始编码
				warehouseShelf.setStartCode("");
				// 结束编码
				warehouseShelf.setEndCode("");
				// 数量上限
				warehouseShelf.setQuantityLimit(null);
				// 重量上限（g）
				warehouseShelf.setWeightLimit(null);
				// 体积-长上限（cm）
				warehouseShelf.setLengthLimit(null);
				// 体积-宽上限（cm）
				warehouseShelf.setWidthLimit(null);
				// 体积-高上限（cm）
				warehouseShelf.setHighLimit(null);
				// 所在层级
				warehouseShelf.setLayer(1);
				warehouseShelf.setStatus(EnableStatus.ENABLE.getStatus());
				warehouseShelf.setRemark("系统默认货位");
				warehouseShelf.setCreaterId(user.getUserId());
				warehouseShelf.setCreaterCode(user.getUserCode());
				warehouseShelf.setCreaterName(user.getUserName());
				warehouseShelf.setCreateTime(new Date());
				warehouseShelf.setModifierId(user.getUserId());
				warehouseShelf.setModifierCode(user.getUserCode());
				warehouseShelf.setModifierName(user.getUserName());
				warehouseShelf.setUpdateTime(new Date());
				warehouseShelfMapper.insertSelective(warehouseShelf);
			}
		}
	}

	private void initParentWarehouseInfo(Enterprise enterprise, UserVO user) {
		// 仓库
		Warehouse warehouse = new Warehouse();
		Long enterpriseId = enterprise.getId();
		warehouse.setEnterpriseId(enterpriseId);
		Long parentId = enterprise.getParentId();
		warehouse.setParentId(parentId);
		warehouse.setSysType(SysType.SYSTEM.getCode());
		String enterpriseCode = enterprise.getCode();
		warehouse.setCode(enterpriseCode);
		warehouse.setName(enterprise.getName()+"仓库");
		// 性质（0-自营；1-第三方）
		warehouse.setNature(0);
		// 面积（㎡）
		warehouse.setAcreage(null);
		warehouse.setAddress(enterprise.getCompanyAddress());
		// 库区编码长度
		warehouse.setAreaCodeLength(2);
		// 货区编码长度
		warehouse.setCargoAreaCodeLength(2);
		// 货位编码长度
		warehouse.setShelfCodeLength(4);
		// 间隔符
		warehouse.setSpacer("");
		// 负责人ID
		warehouse.setManagerId(enterprise.getBusinessManId());
		// 负责人编码
		warehouse.setManagerCode(enterprise.getBusinessManCode());
		// 负责人名称
		warehouse.setManagerName(enterprise.getBusinessManName());
		warehouse.setStatus(EnableStatus.ENABLE.getStatus());
		warehouse.setRemark("系统默认仓库");
		warehouse.setCreaterId(user.getUserId());
		warehouse.setCreaterCode(user.getUserCode());
		warehouse.setCreaterName(user.getUserName());
		warehouse.setCreateTime(new Date());
		warehouse.setModifierId(user.getUserId());
		warehouse.setModifierCode(user.getUserCode());
		warehouse.setModifierName(user.getUserName());
		warehouse.setUpdateTime(new Date());
		warehouseMapper.insertSelective(warehouse);
		Long warehouseId = warehouse.getId();
		String warehouseName = warehouse.getName();
		// 库区
		for(int i=1;i<4;i++){
			WarehouseArea warehouseArea = new WarehouseArea();
			warehouseArea.setEnterpriseId(enterpriseId);
			warehouseArea.setParentId(parentId);
			warehouseArea.setSysType(SysType.SYSTEM.getCode());
			warehouseArea.setWarehouseId(warehouseId);
			warehouseArea.setWarehouseName(warehouseName);
			// 类型（0-常温；1-阴凉；2-冷藏；3-冷冻）
			warehouseArea.setType(i);
			warehouseArea.setCode(enterpriseCode+"0"+i);
			if(i==1){
				warehouseArea.setName(warehouseName+"-"+"常温库区");
				// 温度下限（℃）
				warehouseArea.setTempLowLimit(BigDecimal.ZERO);
				// 温度上限（℃）
				warehouseArea.setTempHighLimit(new BigDecimal(30));
			}else if(i==2){
				warehouseArea.setName(warehouseName+"-"+"阴凉库区");
				// 温度下限（℃）
				warehouseArea.setTempLowLimit(BigDecimal.ZERO);
				// 温度上限（℃）
				warehouseArea.setTempHighLimit(new BigDecimal(20));
			}else if(i==3){
				warehouseArea.setName(warehouseName+"-"+"冷藏库区");
				// 温度下限（℃）
				warehouseArea.setTempLowLimit(new BigDecimal(2));
				// 温度上限（℃）
				warehouseArea.setTempHighLimit(new BigDecimal(8));
			}
			// 相对湿度下限（%）
			warehouseArea.setHumidityLowLimit(new BigDecimal(35));
			// 相对湿度上限（%）
			warehouseArea.setHumidityHighLimit(new BigDecimal(75));
			// 面积（㎡）
			warehouseArea.setAcreage(null);
			// 负责人ID
			warehouseArea.setManagerId(enterprise.getBusinessManId());
			// 负责人编码
			warehouseArea.setManagerCode(enterprise.getBusinessManCode());
			// 负责人名称
			warehouseArea.setManagerName(enterprise.getBusinessManName());
			warehouseArea.setStatus(EnableStatus.ENABLE.getStatus());
			warehouseArea.setRemark("系统默认库区");
			warehouseArea.setCreaterId(user.getUserId());
			warehouseArea.setCreaterCode(user.getUserCode());
			warehouseArea.setCreaterName(user.getUserName());
			warehouseArea.setCreateTime(new Date());
			warehouseArea.setModifierId(user.getUserId());
			warehouseArea.setModifierCode(user.getUserCode());
			warehouseArea.setModifierName(user.getUserName());
			warehouseArea.setUpdateTime(new Date());
			warehouseAreaMapper.insertSelective(warehouseArea);

			// 库区ID
			Long warehouseAreaId = warehouseArea.getId();
			String warehouseAreaName = warehouseArea.getName();
			initDefCargoAreaAndWarehouseShelfForParent(user, enterpriseId, parentId, enterpriseCode, warehouseId, i, warehouseAreaId, warehouseAreaName);
		}
	}

	private void initDefCargoAreaAndWarehouseShelfForParent(UserVO user, Long enterpriseId, Long parentId, String enterpriseCode,
												   Long warehouseId, int i, Long warehouseAreaId, String warehouseAreaName) {
		for(int j=1;j<4;j++){
            WarehouseCargoArea warehouseCargoArea = new WarehouseCargoArea();
            warehouseCargoArea.setEnterpriseId(enterpriseId);
            warehouseCargoArea.setParentId(parentId);
			warehouseCargoArea.setSysType(SysType.SYSTEM.getCode());
            warehouseCargoArea.setWarehouseId(warehouseId);
            warehouseCargoArea.setWarehouseAreaId(warehouseAreaId);
            warehouseCargoArea.setCode(enterpriseCode+"0"+i+"0"+j);
			// 作业类型（0-存储作业区域；1-辅助作业区域；2-存储设备）
			warehouseCargoArea.setJobType(0);
			if(j==1){
				warehouseCargoArea.setName(warehouseAreaName+"-"+"合格品货区");
				// 作业区域
				warehouseCargoArea.setJobArea(0);
			}else if(j==2){
				warehouseCargoArea.setName(warehouseAreaName+"-"+"不合格品货区");
				// 作业区域
				warehouseCargoArea.setJobArea(2);
			}else if(j==3){
				warehouseCargoArea.setName(warehouseAreaName+"-"+"待处理货区");
				// 作业区域
				warehouseCargoArea.setJobArea(1);
			}
            // 层数
            warehouseCargoArea.setLayerQuantity(null);
            // 货位数量
            warehouseCargoArea.setShelfQuantity(null);
            warehouseCargoArea.setStatus(EnableStatus.ENABLE.getStatus());
            warehouseCargoArea.setRemark("系统默认货区");
            warehouseCargoArea.setCreaterId(user.getUserId());
            warehouseCargoArea.setCreaterCode(user.getUserCode());
            warehouseCargoArea.setCreaterName(user.getUserName());
            warehouseCargoArea.setCreateTime(new Date());
            warehouseCargoArea.setModifierId(user.getUserId());
            warehouseCargoArea.setModifierCode(user.getUserCode());
            warehouseCargoArea.setModifierName(user.getUserName());
            warehouseCargoArea.setUpdateTime(new Date());
            warehouseCargoAreaMapper.insertSelective(warehouseCargoArea);
            Long cargoAreaId = warehouseCargoArea.getId();

			String warehouseCargoAreaName = warehouseCargoArea.getName();

			WarehouseShelf warehouseShelf = new WarehouseShelf();
            warehouseShelf.setEnterpriseId(enterpriseId);
            warehouseShelf.setParentId(parentId);
			warehouseShelf.setSysType(SysType.SYSTEM.getCode());
            warehouseShelf.setWarehouseId(warehouseId);
            warehouseShelf.setWarehouseAreaId(warehouseAreaId);
            warehouseShelf.setCargoAreaId(cargoAreaId);
            warehouseShelf.setCode(enterpriseCode+"0"+i+"0"+j+"0001");
            if(j==1){
                warehouseShelf.setName(warehouseCargoAreaName+"-"+"合格品货位");
            }else if(j==2){
                warehouseShelf.setName(warehouseCargoAreaName+"-"+"不合格品货位");
            }else if(j==3){
                warehouseShelf.setName(warehouseCargoAreaName+"-"+"待处理货位");
            }
            // 编码前缀
            warehouseShelf.setPrefix("");
            // 起始编码
            warehouseShelf.setStartCode("");
            // 结束编码
            warehouseShelf.setEndCode("");
            // 数量上限
            warehouseShelf.setQuantityLimit(null);
            // 重量上限（g）
            warehouseShelf.setWeightLimit(null);
            // 体积-长上限（cm）
            warehouseShelf.setLengthLimit(null);
            // 体积-宽上限（cm）
            warehouseShelf.setWidthLimit(null);
            // 体积-高上限（cm）
            warehouseShelf.setHighLimit(null);
            // 所在层级
            warehouseShelf.setLayer(1);
            warehouseShelf.setStatus(EnableStatus.ENABLE.getStatus());
            warehouseShelf.setRemark("系统默认货位");
            warehouseShelf.setCreaterId(user.getUserId());
            warehouseShelf.setCreaterCode(user.getUserCode());
            warehouseShelf.setCreaterName(user.getUserName());
            warehouseShelf.setCreateTime(new Date());
            warehouseShelf.setModifierId(user.getUserId());
            warehouseShelf.setModifierCode(user.getUserCode());
            warehouseShelf.setModifierName(user.getUserName());
            warehouseShelf.setUpdateTime(new Date());
            warehouseShelfMapper.insertSelective(warehouseShelf);
        }
	}

	private void initApprovalFlowData(Enterprise enterprise, UserVO user) throws Exception {
		Long enterpriseId = enterprise.getId();
		Long parentId = enterprise.getParentId();
		String enterpriseName = enterprise.getName();
		Integer chainType = enterprise.getChainType();
		if(ChainType.Headquarters.getCode() == chainType){
			// 首营品种审批
			initParentFirstApprovalFlow(user, enterpriseId, parentId, enterpriseName, chainType, "Goods");
			// 首营企业审批
			initParentFirstApprovalFlow(user, enterpriseId, parentId, enterpriseName, chainType, "Supplier");

			//初始化总部的价格调整审批流程
			initDefultPriceAdjustApprovalFlow(user,enterprise);

			//初始化要货计划,发起机构是分店,审批机构是总部
			approvalFlowComponent.initRequirePlanApprovalFlow(user, enterpriseId, parentId, enterpriseName, chainType,true);

		}else if(ChainType.Selfoperatedshop.getCode() == chainType){

			// 分店要货计划审批
			/*initRequirePlanApprovalFlow(user, enterpriseId, parentId, enterpriseName, chainType);*/

		}else if(ChainType.Division.getCode() == chainType){

			// 首营品种审批
			initParentFirstApprovalFlow(user, enterpriseId, parentId, enterpriseName, chainType, "Goods");
			// 首营企业审批
			initParentFirstApprovalFlow(user, enterpriseId, parentId, enterpriseName, chainType, "Supplier");
			// 分店要货计划审批
			/*initRequirePlanApprovalFlow(user, enterpriseId, parentId, enterpriseName, chainType);*/
		}
	}

	/*private void initRequirePlanApprovalFlow(UserVO user, Long enterpriseId, Long parentId, String enterpriseName,
			Integer chainType,boolean headControl) {
		ApprovalFlow approvalFlow = new ApprovalFlow();
		approvalFlow.setEnterpriseId(enterpriseId);
		approvalFlow.setParentId(parentId);
		approvalFlow.setChainType(chainType);
		approvalFlow.setName("要货计划审批");
		// 要货计划
		approvalFlow.setContent("0501");
		// 发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）
		if(!headControl) {
			*//**
			 * 分部控制
			 *//*
			approvalFlow.setStartOrg(4);
			approvalFlow.setStartOrgId(enterpriseId);
			approvalFlow.setStartOrgName(enterpriseName);
		}else {
			approvalFlow.setStartOrg(1);
		}


		approvalFlow.setDefaultFlag(SysType.SYSTEM.getCode());
		approvalFlow.setStatus(EnableStatus.ENABLE.getStatus());
		approvalFlow.setRemark("系统默认审批流");
		approvalFlow.setCreaterId(user.getUserId());
		approvalFlow.setCreaterCode(user.getUserCode());
		approvalFlow.setCreaterName(user.getUserName());
		approvalFlow.setCreateTime(new Date());
		approvalFlow.setModifierId(user.getUserId());
		approvalFlow.setModifierCode(user.getUserCode());
		approvalFlow.setModifierName(user.getUserName());
		approvalFlow.setUpdateTime(new Date());
		approvalFlowMapper.insertSelective(approvalFlow);
		Long flowId = approvalFlow.getId();
		
		ApprovalFlowDetail approvalFlowDetail = new ApprovalFlowDetail();
		approvalFlowDetail.setEnterpriseId(enterpriseId);
		approvalFlowDetail.setParentId(parentId);
		approvalFlowDetail.setFlowId(flowId);
		approvalFlowDetail.setApprovalStage("配送中心经理审批");
		// 审批机构（0-总部；1-发起机构；2-指定审核机构）
		if(!headControl) {
			*//**
			 * 分部控制
			 *//*
			approvalFlowDetail.setApprovalOrg(2);
			approvalFlowDetail.setOrgId(enterpriseId);
			approvalFlowDetail.setOrgName(enterpriseName);
		}else {
			approvalFlowDetail.setApprovalOrg(0);
		}


		// 审批角色名称
		String roleName = "配送中心经理";
		Map<String, Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("enterpriseId", 0L);
		paramMap.put("roleName", roleName);
		SysRole sysRole = sysRoleMapper.selectDefaultRoleByParamMap(paramMap);
		// 审批角色ID
		Long roleId = sysRole.getId();
		approvalFlowDetail.setRoleId(roleId);
		approvalFlowDetail.setRoleName(roleName);
		approvalFlowDetail.setApproverId(null);
		approvalFlowDetail.setApproverCode(null);
		approvalFlowDetail.setApproverName(null);
		// 所在级别
		approvalFlowDetail.setLevel(1);
		// 最高级别
		approvalFlowDetail.setHighestLevel(3);
		approvalFlowDetail.setStatus(EnableStatus.ENABLE.getStatus());
		approvalFlowDetail.setRemark("系统默认审批流");
		approvalFlowDetail.setCreaterId(user.getUserId());
		approvalFlowDetail.setCreaterCode(user.getUserCode());
		approvalFlowDetail.setCreaterName(user.getUserName());
		approvalFlowDetail.setCreateTime(new Date());
		approvalFlowDetail.setModifierId(user.getUserId());
		approvalFlowDetail.setModifierCode(user.getUserCode());
		approvalFlowDetail.setModifierName(user.getUserName());
		approvalFlowDetail.setUpdateTime(new Date());
		approvalFlowDetailMapper.insertSelective(approvalFlowDetail);
	}*/

	private void initParentFirstApprovalFlow(UserVO user, Long enterpriseId, Long parentId, String enterpriseName,
			Integer chainType, String type) {
		ApprovalFlow approvalFlow = new ApprovalFlow();
		approvalFlow.setEnterpriseId(enterpriseId);
		approvalFlow.setParentId(parentId);
		approvalFlow.setChainType(chainType);
		if("Goods".equals(type)){
			approvalFlow.setName("首营品种审批");
			// 商品信息
			approvalFlow.setContent("0201");
		}else if("Supplier".equals(type)){
			approvalFlow.setName("首营企业审批");
			// 供货单位
			approvalFlow.setContent("0101");
		}
		// 发起机构（0-总部；1-全部分店；2-仅自营店；3-仅加盟店；4-指定发起机构）
		approvalFlow.setStartOrg(0);
		approvalFlow.setStartOrgId(enterpriseId);
		approvalFlow.setStartOrgName(enterpriseName);
		approvalFlow.setDefaultFlag(SysType.SYSTEM.getCode());
		approvalFlow.setStatus(EnableStatus.ENABLE.getStatus());
		approvalFlow.setRemark("系统默认审批流");
		approvalFlow.setCreaterId(user.getUserId());
		approvalFlow.setCreaterCode(user.getUserCode());
		approvalFlow.setCreaterName(user.getUserName());
		approvalFlow.setCreateTime(new Date());
		approvalFlow.setModifierId(user.getUserId());
		approvalFlow.setModifierCode(user.getUserCode());
		approvalFlow.setModifierName(user.getUserName());
		approvalFlow.setUpdateTime(new Date());
		approvalFlowMapper.insertSelective(approvalFlow);
		Long flowId = approvalFlow.getId();
		for(int i=0;i<3;i++){
			ApprovalFlowDetail approvalFlowDetail = new ApprovalFlowDetail();
			approvalFlowDetail.setEnterpriseId(enterpriseId);
			approvalFlowDetail.setParentId(parentId);
			approvalFlowDetail.setFlowId(flowId);
			// 审批角色名称
			String roleName = "";
			if(i==0){
				approvalFlowDetail.setApprovalStage("业务负责人审批");
				roleName = "采购经理";
			}else if(i==1){
				approvalFlowDetail.setApprovalStage("质量负责人审批");
				roleName = "质量负责人";
			}else if(i==2){
				approvalFlowDetail.setApprovalStage("总经理审批");
				roleName = "企业负责人";
			}
			// 审批机构（0-总部；1-发起机构；2-指定审核机构）
			approvalFlowDetail.setApprovalOrg(0);
			approvalFlowDetail.setOrgId(enterpriseId);
			approvalFlowDetail.setOrgName(enterpriseName);
			
			Map<String, Object> paramMap = new HashMap<String, Object>(); 
			paramMap.put("enterpriseId", 0L);
			paramMap.put("roleName", roleName);
			SysRole sysRole = sysRoleMapper.selectDefaultRoleByParamMap(paramMap);
			// 审批角色ID
			Long roleId = sysRole.getId();
			approvalFlowDetail.setRoleId(roleId);
			approvalFlowDetail.setRoleName(roleName);
			
			approvalFlowDetail.setApproverId(null);
			approvalFlowDetail.setApproverCode(null);
			approvalFlowDetail.setApproverName(null);
			// 所在级别
			approvalFlowDetail.setLevel(i);
			// 最高级别
			approvalFlowDetail.setHighestLevel(3);
			approvalFlowDetail.setStatus(EnableStatus.ENABLE.getStatus());
			approvalFlowDetail.setRemark("系统默认审批流");
			approvalFlowDetail.setCreaterId(user.getUserId());
			approvalFlowDetail.setCreaterCode(user.getUserCode());
			approvalFlowDetail.setCreaterName(user.getUserName());
			approvalFlowDetail.setCreateTime(new Date());
			approvalFlowDetail.setModifierId(user.getUserId());
			approvalFlowDetail.setModifierCode(user.getUserCode());
			approvalFlowDetail.setModifierName(user.getUserName());
			approvalFlowDetail.setUpdateTime(new Date());
			approvalFlowDetailMapper.insertSelective(approvalFlowDetail);
		}
	}

	/**
	 * 添加默认的价格调整审批流程
	 * @param userVO
	 * @param enterprise
	 */
	/**
	 * 默认销售经理角色id
	 */
	private static final String SALES_MANAGER_ROLECODE = "070101";

	public void initDefultPriceAdjustApprovalFlow(UserVO userVO,Enterprise enterprise) throws Exception {

		ApprovalFlow defPriceAdjustApprovalFlow = ApprovalFlow.getDefPriceAdjustApprovalFlow(userVO, enterprise);


		ApprovalFlow approvalFlow = approvalFlowMapper.selectByContent(defPriceAdjustApprovalFlow.getContent(), enterprise.getId());

		if(null != approvalFlow) return;

		approvalFlowMapper.insertSelective(defPriceAdjustApprovalFlow);


		SysRole sysRole = sysRoleMapper.selectDefRoleByRoleCode(SALES_MANAGER_ROLECODE);

		ApprovalFlowDetail defPriceAdjustApprovalFlowDetail = ApprovalFlowDetail.getDefPriceAdjustApprovalFlowDetail(userVO, enterprise, defPriceAdjustApprovalFlow.getId(), sysRole);

		approvalFlowDetailMapper.insertSelective(defPriceAdjustApprovalFlowDetail);
	}

	private void initPosSetData(Enterprise enterprise, UserVO user) throws Exception{
		Long enterpriseId = enterprise.getId();
		Long parentId = enterprise.getParentId();
		Integer chainType = enterprise.getChainType();
		// 总部
		if(chainType == ChainType.Headquarters.getCode()){
			// 初始化POS设置-系统设置
			initPosSetInfo(user, enterpriseId);
			// 初始化POS设置-支付方式
			initPosPayType(user, enterpriseId);
			// 初始化POS设置-开户银行
			initPosBank(user, enterpriseId);
			// 初始化POS设置-销售时段
			initPosSalePeriod(user, enterpriseId);

		}else{
			// 自营店 加盟店
			// 初始化POS设置-班组
			initPosTeam(user, enterpriseId, parentId);
			// 初始化POS设置-收款人员（员工保存后生成）
			// 初始化POS设置-款员权限（员工保存后生成）

			/*EnterpriseBusiness enterpriseBusiness = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(user.getEnterpriseId());
			// 门店自主控制
			if(null != enterpriseBusiness && enterpriseBusiness.getPosSet() == 1) {
				// 初始化POS设置-支付方式
				initPosPayType(user, enterpriseId);
				// 初始化POS设置-开户银行
				initPosBank(user, enterpriseId);
				// 初始化POS设置-销售时段
				initPosSalePeriod(user, enterpriseId);
			}*/
			
			// 加盟店初始化自己POS的数据
			if(chainType == ChainType.Division.getCode()) {
				// 初始化POS设置-支付方式
				initPosPayType(user, enterpriseId);
				// 初始化POS设置-开户银行
				initPosBank(user, enterpriseId);
				// 初始化POS设置-销售时段
				initPosSalePeriod(user, enterpriseId);
			}
			
		}
	}

	public void initPosSalePeriod(UserVO user, Long enterpriseId) {
		List<PosSalePeriodModel> pspList = PosSalePeriodModel.build();
		for(PosSalePeriodModel m:pspList){
            PosSalePeriod posSalePeriod = new PosSalePeriod();
            posSalePeriod.setEnterpriseId(enterpriseId);
            posSalePeriod.setCode(m.getCode());
            posSalePeriod.setName(m.getName());
            posSalePeriod.setStartTime(m.getStartTime());
            posSalePeriod.setEndTime(m.getEndTime());
            posSalePeriod.setStatus(EnableStatus.ENABLE.getStatus());
            posSalePeriod.setRemark("系统默认销售时段");
            posSalePeriod.setCreaterId(user.getUserId());
            posSalePeriod.setCreaterCode(user.getUserCode());
            posSalePeriod.setCreaterName(user.getUserName());
            posSalePeriod.setCreateTime(new Date());
            posSalePeriod.setModifierId(user.getUserId());
            posSalePeriod.setModifierCode(user.getUserCode());
            posSalePeriod.setModifierName(user.getUserName());
            posSalePeriod.setUpdateTime(new Date());
            posSalePeriodMapper.insertSelective(posSalePeriod);
        }
	}

	public void initPosTeam(UserVO user, Long enterpriseId, Long parentId) {
		PosTeam posTeam = new PosTeam();
		posTeam.setEnterpriseId(enterpriseId);
		posTeam.setParentId(parentId);
		posTeam.setCode("01");
		posTeam.setName("全班");
		posTeam.setStartTime("08:00:00");
		posTeam.setEndTime("21:00:00");
		posTeam.setTeamType(SysType.SYSTEM.getCode());
		posTeam.setStatus(EnableStatus.ENABLE.getStatus());
		posTeam.setRemark("系统默认班组");
		posTeam.setCreaterId(user.getUserId());
		posTeam.setCreaterCode(user.getUserCode());
		posTeam.setCreaterName(user.getUserName());
		posTeam.setCreateTime(new Date());
		posTeam.setModifierId(user.getUserId());
		posTeam.setModifierCode(user.getUserCode());
		posTeam.setModifierName(user.getUserName());
		posTeam.setUpdateTime(new Date());
		posTeamMapper.insertSelective(posTeam);
	}

	public void initPosBank(UserVO user, Long enterpriseId) {
		List<PosBankModel> pbList = PosBankModel.build();
		for(PosBankModel m:pbList){
            PosBank posBank = new PosBank();
            posBank.setEnterpriseId(enterpriseId);
            posBank.setBankType(SysType.SYSTEM.getCode());
            posBank.setCode(m.getCode());
            posBank.setName(m.getName());
            posBank.setStatus(EnableStatus.ENABLE.getStatus());
            posBank.setRemark("系统默认开户银行");
            posBank.setCreaterId(user.getUserId());
            posBank.setCreaterCode(user.getUserCode());
            posBank.setCreaterName(user.getUserName());
            posBank.setCreateTime(new Date());
            posBank.setModifierId(user.getUserId());
            posBank.setModifierCode(user.getUserCode());
            posBank.setModifierName(user.getUserName());
            posBank.setUpdateTime(new Date());
            posBankMapper.insertSelective(posBank);
        }
	}

	public void initPosPayType(UserVO user, Long enterpriseId) {
		List<PosPayTypeModel> pptList = PosPayTypeModel.build();
		for(PosPayTypeModel m:pptList){
            PosPayType ppt = new PosPayType();
            ppt.setEnterpriseId(enterpriseId);
			ppt.setPayType(SysType.SYSTEM.getCode());
            ppt.setCode(m.getCode());
            ppt.setName(m.getName());
            ppt.setShortcutKey(m.getShortcutKey());
            ppt.setStatus(EnableStatus.ENABLE.getStatus());
            ppt.setRemark("系统默认支付方式");
            ppt.setCreaterId(user.getUserId());
            ppt.setCreaterCode(user.getUserCode());
            ppt.setCreaterName(user.getUserName());
            ppt.setCreateTime(new Date());
            ppt.setModifierId(user.getUserId());
            ppt.setModifierCode(user.getUserCode());
            ppt.setModifierName(user.getUserName());
            ppt.setUpdateTime(new Date());
            posPayTypeMapper.insertSelective(ppt);
        }
	}

	private void initPosSetInfo(UserVO user, Long enterpriseId) {
		PosSet posSet = new PosSet();
		posSet.setEnterpriseId(enterpriseId);
		// 各门店使用统一的设置（0-关闭；1-开启）
		posSet.setUnifiedFlag(1);
		// 处方药销售登记（0-否；1-是）
		posSet.setPrescriptionSaleRegister(1);
		// 特殊药销售登记（0-否；1-是）
		posSet.setSpecialSaleRegister(1);
		// 近效期商品提示（0-否；1-是）
		posSet.setNearPeriodSaleTips(1);
		// 配伍禁忌商品销售提示（0-否；1-是）
		posSet.setIncompatibilitySaleRegister(1);
		// 常规模式销售中药饮片（0-禁止；1-允许）
		posSet.setChMedicineLimit(1);
		// 售价小于成本价提示（0-否；1-是）
		posSet.setLowPriceTips(1);
		// 售价等于零提示（0-否；1-是）
		posSet.setZeroPriceTips(1);
		// 营业人员强制登记（0-否；1-是）
		posSet.setClerkForceRegister(1);
		// 商品超量销售（0-否；1-是）
		posSet.setExcessiveSale(1);
		// 近效期N天内商品销售（0-禁止；1-允许）
		posSet.setNearPeriodSale(1);
		// 近效期天数
		posSet.setNearPeriodSaleDays(7);
		// 中药饮片N种以上处方登记（0-关闭；1-开启）
		posSet.setChPrescriptionRegister(1);
		// 中药饮片品种数
		posSet.setChMedicineQty(5);
		// 名称列显示活动标识（0-否；1-是）
		posSet.setShowActivityLogo(1);
		// 自动抹零（0-否；1-是）
		posSet.setAutoMaling(1);
		// 自动抹零类型（0-抹零到角；1-抹零到元）
		posSet.setAutoMalingType(0);
		// 小票样式（0-常规；1-标准；2-精简；3-自定义1）
		posSet.setSmallTicketStyle(0);
		// 打印份数，默认为1
		posSet.setPrintCopiess(1);
		posSet.setStatus(EnableStatus.ENABLE.getStatus());
		posSet.setRemark("系统默认POS系统设置");
		posSet.setCreaterId(user.getUserId());
		posSet.setCreaterCode(user.getUserCode());
		posSet.setCreaterName(user.getUserName());
		posSet.setCreateTime(new Date());
		posSet.setModifierId(user.getUserId());
		posSet.setModifierCode(user.getUserCode());
		posSet.setModifierName(user.getUserName());
		posSet.setUpdateTime(new Date());
		posSetMapper.insertSelective(posSet);
	}

	private void initPriceOrderData(Enterprise enterprise, UserVO user) throws Exception {
		Long enterpriseId = enterprise.getId();
		Long parentId = enterprise.getParentId();
		String enterpriseName = enterprise.getName();
		Integer chainType = enterprise.getChainType();
		// 总部
		if(chainType == 0){
			PriceOrder priceOrder = new PriceOrder();
			priceOrder.setEnterpriseId(enterpriseId);
			priceOrder.setParentId(parentId);
			priceOrder.setParentOrderId(0L);
			// 类型：0-自定义价格清单；1-系统价格清单
			priceOrder.setSysType(SysType.SYSTEM.getCode());
			priceOrder.setCode(enterprise.getCode());
			priceOrder.setName(enterpriseName+"基础价格清单");
			// 价格类型（0-基础价格；1-配货价格；2-零售价格）
			priceOrder.setPriceType(0);
			priceOrder.setStatus(EnableStatus.ENABLE.getStatus());
			priceOrder.setRemark("系统默认总部价格清单");
			priceOrder.setCreaterId(user.getUserId());
			priceOrder.setCreaterCode(user.getUserCode());
			priceOrder.setCreaterName(user.getUserName());
			priceOrder.setCreateTime(new Date());
			priceOrder.setModifierId(user.getUserId());
			priceOrder.setModifierCode(user.getUserCode());
			priceOrder.setModifierName(user.getUserName());
			priceOrder.setUpdateTime(new Date());
			priceOrderMapper.insertSelective(priceOrder);
		}
	}


}
