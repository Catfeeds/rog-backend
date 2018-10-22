package com.rograndec.feijiayun.chain.common.component;

import com.rograndec.feijiayun.chain.business.auth.register.exception.RegisterBizException;
import com.rograndec.feijiayun.chain.business.basic.store.vo.AddStoreEnterpriseVO;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreEnterpriseBusinessVO;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserAdministrationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserPersonalMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserRoleMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserPersonal;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserRole;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.GoodsManageMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.GoodsManage;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.init.service.SysDataInitService;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseQualificationConfigMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.LocationMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseQualificationConfig;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Location;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseQualificationConfigService;
import com.rograndec.feijiayun.chain.business.system.enterprise.vo.EnterpriseQualificationConfigBean;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.GoodsManageStatus;
import com.rograndec.feijiayun.chain.common.constant.SysType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.pinyin.PinYinUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * create by sulei
 * todo：获取所有可用企业
 */
@Component
public class EnterpriseComponent {

    private static final Log logger = LogFactory.getLog(EnterpriseComponent.class);

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private ManageConfigMapper manageConfigMapper;

    @Autowired
    private EnterpriseQualificationConfigService enterpriseQualificationConfigService;

    @Autowired
    private EnterpriseQualificationConfigMapper enterpriseQualificationConfigMapper;

    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAdministrationMapper administrationMapper;

    @Autowired
    private CodeComponent codeComponent;

    @Autowired
    private SysDataInitService sysDataInitService;

    @Autowired
    private UserPersonalMapper userPersonalMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PriceOrderMapper priceOrderMapper;

    @Autowired
    private SafetyStockMapper safetyStockMapper;

    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;

    @Autowired
    private GoodsManageMapper goodsManageMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private LocationMapper locationMapper;

    public List<Enterprise> getEnterprise(){
        List<Enterprise> list = enterpriseMapper.selectUsefulEnterprise();
        return list;
    }
    public List<Enterprise> getAllParent(String eName){
        List<Enterprise> list = enterpriseMapper.selectByAllParent(eName);
        return list;
    }


    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Enterprise saveEnterprise(AddStoreEnterpriseVO addStoreEnterpriseVO,
                                     UserVO loginUser) throws Exception {
        //验证基本信息
        String msg = validateEnterpriseVoData(addStoreEnterpriseVO, loginUser);
        if(StringUtils.isNotBlank(msg)){
            throw  new RegisterBizException(SysCode.FAIL.getCode(),msg);
        }

        //验证资质集合
        if(addStoreEnterpriseVO.getQualificationConfigList() != null && addStoreEnterpriseVO.getQualificationConfigList().size() > 0){

            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(loginUser.getEnterpriseId());

            for (EnterpriseQualificationConfigBean bean : addStoreEnterpriseVO.getQualificationConfigList()) {
                String configMsg = enterpriseQualificationConfigService.validateEnterpriseQualificationConfig(bean, enterprise, loginUser);
                if(StringUtils.isNotBlank(configMsg)){
                    throw  new RegisterBizException(SysCode.FAIL.getCode(),configMsg);
                }
            }
        }

        //验证管理员重复
        if(valudateUserName(addStoreEnterpriseVO.getUserName())){
            throw new Exception("管理员账号："+addStoreEnterpriseVO.getUserName()+"重复！");
        }

        StoreEnterpriseBusinessVO storeEnterpriseBusinessVO = addStoreEnterpriseVO.getStoreEnterpriseBusinessVO()
                ==null?null:addStoreEnterpriseVO.getStoreEnterpriseBusinessVO();

        //转数据库enterprise对象
        Enterprise newEnterprise = setAddEnterprise(addStoreEnterpriseVO, null, loginUser);

        //保存
        enterpriseMapper.insertSelective(newEnterprise);

        //转数据库newEnterpriseBusiness对象
        EnterpriseBusiness newEnterpriseBusiness = setEnterpriseBusiness(storeEnterpriseBusinessVO, null, newEnterprise, loginUser);

        //保存
        enterpriseBusinessMapper.insertSelective(newEnterpriseBusiness);

        if(addStoreEnterpriseVO.getQualificationConfigList() != null && addStoreEnterpriseVO.getQualificationConfigList().size() > 0){

            for (EnterpriseQualificationConfigBean bean : addStoreEnterpriseVO.getQualificationConfigList()) {
                EnterpriseQualificationConfig config = new EnterpriseQualificationConfig();

                //转数据库config对象
                try {
                    BeanUtils.copyProperties(config, bean);
                } catch (Exception e) {
                    config = setEnterpriseQualificationConfigByBean(bean);
                }
                config.setId(null);
                config.setCreaterId(loginUser.getUserId());
                config.setCreaterCode(loginUser.getUserCode());
                config.setCreaterName(loginUser.getUserName());
                config.setCreateTime(new Date());
                config.setEnterpriseId(newEnterprise.getId());
                config.setParentId(newEnterprise.getParentId());

                //保存
                enterpriseQualificationConfigMapper.insertSelective(config);
            }
        }

        saveStoreDefaultData(newEnterprise, loginUser, storeEnterpriseBusinessVO==null?null:storeEnterpriseBusinessVO.getDistrPriceOrderId());

		/*ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

		//存储默认数据
		try {
			singleThreadExecutor.execute(() -> {
				saveStoreDefaultData(newEnterprise, loginUser, storeEnterpriseBusinessVO==null?null:storeEnterpriseBusinessVO.getDistrPriceOrderId());
			});
		} catch (Exception e) {
			logger.error("新增门店保存默认数据失败！", e);
			e.printStackTrace();
		}finally{
			singleThreadExecutor.shutdown();
		}*/

        return newEnterprise;
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
    private String validateEnterpriseVoData(
            AddStoreEnterpriseVO enterpriseVO, UserVO loginUser) {
        StringBuilder sb = new StringBuilder();

        if(StringUtils.isBlank(enterpriseVO.getUserName())){
            sb.append("管理员账号必填！");
        }
        if(StringUtils.isBlank(enterpriseVO.getPassWord())){
            sb.append("登录密码必填！");
        }
        if(StringUtils.isBlank(enterpriseVO.getPassWordConfirmation())){
            sb.append("确认密码必填！");
        }
        if(enterpriseVO.getPassWord() != null && enterpriseVO.getPassWordConfirmation() != null
                && !enterpriseVO.getPassWord().equals(enterpriseVO.getPassWordConfirmation())){
            sb.append("确认密码与登录密码不一致！");
        }
        if(StringUtils.isBlank(enterpriseVO.getAdminPhone())){
            sb.append("管理员手机号必填！");
        }
        if(StringUtils.isBlank(enterpriseVO.getAdminEmail())){
            sb.append("管理员邮箱必填！");
        }

        ManageConfig manageConfig = manageConfigMapper.selectManageConfigByEnterpriseId(loginUser.getEnterpriseId());

        if(manageConfig != null && manageConfig.getQualityControl() == 1){//开启基础数据质量控制，验证

			/* 新增时门店无人员，新增不校验，修改校验。2017.9.6张红运确认
			 * if(enterpriseVO.getBusinessManId() == null || enterpriseVO.getBusinessManId() == 0){
				sb.append("开启基础数据质量控制，企业负责人必填！");
			}
			if(enterpriseVO.getLegalManId() == null || enterpriseVO.getLegalManId() == 0){
				sb.append("开启基础数据质量控制，法定代表人必填！");
			}
			if(enterpriseVO.getQualityOfficerId() == null || enterpriseVO.getQualityOfficerId() == 0){
				sb.append("开启基础数据质量控制，质量负责人必填！");
			}*/
            if(StringUtils.isBlank(enterpriseVO.getBankName())){
                sb.append("开启基础数据质量控制，开户银行必填！");
            }
            if(StringUtils.isBlank(enterpriseVO.getBankAccount())){
                sb.append("开启基础数据质量控制，开户账号必填！");
            }
            if(StringUtils.isBlank(enterpriseVO.getBankAccountName())){
                sb.append("开启基础数据质量控制，开户户名必填！");
            }
            if(enterpriseVO.getBusinessVarietyArray() == null || enterpriseVO.getBusinessVarietyArray().length == 0){
                sb.append("开启基础数据质量控制，经营品种必填！");
            }
            if(enterpriseVO.getBusinessScopeIdArray() == null || enterpriseVO.getBusinessScopeIdArray().length == 0){
                sb.append("开启基础数据质量控制，经营范围必填！");
            }
        }
        return sb.toString();
    }


    private boolean valudateUserName(String userName) {
        Long count = administrationMapper.queryCountUserAdministrationByAccount(userName);
        if(count > 0){
            return true;
        }
        return false;
    }

    private Enterprise setAddEnterprise(
            AddStoreEnterpriseVO enterpriseVO, Object object,
            UserVO loginUser) {
        Enterprise newEnterprise = new Enterprise();
        newEnterprise.setId(enterpriseVO.getId());
        newEnterprise.setParentId(loginUser.getEnterpriseId());
        newEnterprise.setChainType(enterpriseVO.getChainType());
        try {
            newEnterprise.setCode(StringUtils.isNotBlank(enterpriseVO.getCode())?enterpriseVO.getCode():codeComponent.generate("Enterprise", 4, loginUser.getEnterpriseId()));
        } catch (Exception e) {
            logger.error("生成门店编码失败！", e);
            e.printStackTrace();
        }
        newEnterprise.setPinyin(enterpriseVO.getPinyin());
        newEnterprise.setName(enterpriseVO.getName());
        newEnterprise.setGroupId(enterpriseVO.getGroupId());
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
        newEnterprise.setPostcode(enterpriseVO.getPostcode());
        newEnterprise.setTel(enterpriseVO.getTel());
        newEnterprise.setFax(enterpriseVO.getFax());
        newEnterprise.setEmail(enterpriseVO.getEmail());
        newEnterprise.setSite(enterpriseVO.getSite());
        newEnterprise.setSaleAreaId(enterpriseVO.getSaleAreaId());
        newEnterprise.setSaleCircleId(enterpriseVO.getSaleCircleId());
        newEnterprise.setStoreLevelId(enterpriseVO.getStoreLevelId());
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
        if(enterpriseVO.getBusinessVarietyArray() != null && enterpriseVO.getBusinessVarietyArray().length > 0){
            for (int i = 0; i < enterpriseVO.getBusinessVarietyArray().length; i++) {
                busVarietys.append(enterpriseVO.getBusinessVarietyArray()[i]);
                if(i != enterpriseVO.getBusinessVarietyArray().length - 1){
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

        newEnterprise.setUserName(enterpriseVO.getUserName());
        newEnterprise.setPassWord(enterpriseVO.getPassWord());
        newEnterprise.setPassWordConfirmation(enterpriseVO.getPassWordConfirmation());
        newEnterprise.setAdminPhone(enterpriseVO.getAdminPhone());
        newEnterprise.setAdminEmail(enterpriseVO.getAdminEmail());

        newEnterprise.setStatus(enterpriseVO.getStatus()==null?1:enterpriseVO.getStatus());
        newEnterprise.setValidFlag(enterpriseVO.getValidFlag()==null?1:enterpriseVO.getValidFlag());

        newEnterprise.setCreaterId(loginUser.getUserId());
        newEnterprise.setCreaterCode(loginUser.getUserCode());
        newEnterprise.setCreaterName(loginUser.getUserName());
        newEnterprise.setCreateTime(new Date());

        newEnterprise.setModifierId(loginUser.getUserId());
        newEnterprise.setModifierCode(loginUser.getUserCode());
        newEnterprise.setModifierName(loginUser.getUserName());
        newEnterprise.setUpdateTime(new Date());

        StringBuilder busIds = new StringBuilder();
        if(enterpriseVO.getBusinessScopeIdArray() != null && enterpriseVO.getBusinessScopeIdArray().length > 0){
            for (int i = 0; i < enterpriseVO.getBusinessScopeIdArray().length; i++) {
                busIds.append(enterpriseVO.getBusinessScopeIdArray()[i]);
                if(i != enterpriseVO.getBusinessScopeIdArray().length - 1){
                    busIds.append(",");
                }
            }
        }
        newEnterprise.setBusinessScopeId(busIds.toString());

        return newEnterprise;
    }

    public EnterpriseBusiness setEnterpriseBusiness(
            StoreEnterpriseBusinessVO vo, EnterpriseBusiness enterBus,
            Enterprise enter, UserVO loginUser) {

        EnterpriseBusiness bus = new EnterpriseBusiness();

        bus.setEnterpriseId(enter.getId());
        bus.setParentId(enter.getParentId());
        if(enterBus == null){
            EnterpriseBusiness busParent = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(loginUser.getEnterpriseId());
            if(vo != null){
            	setEnterpriseBusinessDataByVo(bus, vo);
            }else{
            	bus.setSettlementMode(busParent.getSettlementMode());
                bus.setDistrPriceType(busParent.getDistrPriceType());
                bus.setDistrPriceOrderId(busParent.getDistrPriceOrderId());
                if(busParent.getDistrPriceOrderId() != null && busParent.getDistrPriceOrderId() != 0){
                	PriceOrder order = priceOrderMapper.selectByPrimaryKey(busParent.getDistrPriceOrderId());
                	if(order != null){
                		bus.setDistrPriceOrderName(order.getName());
                	}	
                }
                bus.setAddRate(busParent.getAddRate());
                bus.setPaymentProvision(busParent.getPaymentProvision());
                bus.setPaymentPeriod(busParent.getPaymentPeriod());
                bus.setPaymentPeriodUnit(busParent.getPaymentPeriodUnit());
                bus.setPaymentTime(busParent.getPaymentTime());
                bus.setPaymentTimeUnit(busParent.getPaymentTimeUnit());
                bus.setQualityControl(busParent.getQualityControl());
                bus.setPermissionSet(busParent.getPermissionSet());
                bus.setApprovalControl(busParent.getApprovalControl());
                bus.setWarnSet(busParent.getWarnSet());
                bus.setPriceManage(busParent.getPriceManage());
                bus.setSpecialPriceManage(busParent.getSpecialPriceManage());
                bus.setMemberInfo(busParent.getMemberInfo());
                bus.setPromotionRule(busParent.getPromotionRule());
                bus.setPosSet(busParent.getPosSet());
                bus.setPriceTagPrint(busParent.getPriceTagPrint());
                bus.setRoyaltyRule(busParent.getRoyaltyRule());
                bus.setRemoteTrial(busParent.getRemoteTrial());
                bus.setReasonableStock(busParent.getReasonableStock());
                bus.setDistributionManage(busParent.getDistributionManage());
                bus.setEquipmentManage(busParent.getEquipmentManage());
            } 
            bus.setCreaterId(loginUser.getUserId());
            bus.setCreaterCode(loginUser.getUserCode());
            bus.setCreaterName(loginUser.getUserName());
            bus.setCreateTime(new Date());
        }else{
            bus.setId(enterBus.getId());
            setEnterpriseBusinessDataByVo(bus, vo);
            bus.setCreaterId(enterBus.getCreaterId());
            bus.setCreaterCode(enterBus.getCreaterCode());
            bus.setCreaterName(enterBus.getCreaterName());
            bus.setCreateTime(enterBus.getCreateTime());
        }
        bus.setModifierId(loginUser.getUserId());
        bus.setModifierCode(loginUser.getUserCode());
        bus.setModifierName(loginUser.getUserName());
        bus.setUpdateTime(new Date());
        return bus;
    }


    private void setEnterpriseBusinessDataByVo(EnterpriseBusiness bus,
			StoreEnterpriseBusinessVO vo) {
    	bus.setSettlementMode(vo.getSettlementMode());
        bus.setDistrPriceType(vo.getDistrPriceType());
        bus.setDistrPriceOrderId(vo.getDistrPriceOrderId());
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
        bus.setQualityControl(vo.getQualityControl());
        bus.setPermissionSet(vo.getPermissionSet());
        bus.setApprovalControl(vo.getApprovalControl());
        bus.setWarnSet(vo.getWarnSet());
        bus.setPriceManage(vo.getPriceManage());
        bus.setSpecialPriceManage(vo.getSpecialPriceManage());
        bus.setMemberInfo(vo.getMemberInfo());
        bus.setPromotionRule(vo.getPromotionRule());
        bus.setPosSet(vo.getPosSet());
        bus.setPriceTagPrint(vo.getPriceTagPrint());
        bus.setRoyaltyRule(vo.getRoyaltyRule());
        bus.setRemoteTrial(vo.getRemoteTrial());
        bus.setReasonableStock(vo.getReasonableStock());
        bus.setDistributionManage(vo.getDistributionManage());
        bus.setEquipmentManage(vo.getEquipmentManage());
	}
	/**
     * @Description: 根据configBean -> config
     * @author liuqun
     * @version 1.0
     * @date 2017年8月25日 上午10:25:15
     * @param configBean
     * @return
     * @return EnterpriseQualificationConfig
     */
    private EnterpriseQualificationConfig setEnterpriseQualificationConfigByBean(
            EnterpriseQualificationConfigBean configBean) {

        EnterpriseQualificationConfig config = new EnterpriseQualificationConfig();
        config.setId(configBean.getId());
        config.setEnterpriseId(configBean.getEnterpriseId());
        config.setParentId(configBean.getParentId());
        config.setQualificationId(configBean.getQualificationId());
        config.setQualificationCode(configBean.getQualificationCode());
        config.setValidUntil(configBean.getValidUntil());
        config.setFileId(configBean.getFileId());
        config.setRemark(configBean.getRemark());
        return config;
    }



    private void saveStoreDefaultData(Enterprise newEnterprise, UserVO loginUser, Long distrPriceOrderId) {
        //初始化角色菜单、权限设置；管理设置；仓库、库区、货区、货位；审批流；POS设置；价格清单
        try {
            sysDataInitService.initCommonData(newEnterprise, loginUser);
        } catch (Exception e) {
            logger.error("初始化角色菜单、权限设置；管理设置；仓库、库区、货区、货位；审批流；POS设置；价格清单相关数据错误！", e);
            e.printStackTrace();
        }

        //默认用户相关数据
        try {
            saveStoreDefaultUser(newEnterprise, loginUser);
        } catch (Exception e) {
            logger.error("新增门店添加默认用户相关数据错误！", e);
            e.printStackTrace();
        }

        PriceOrder order = savePriceOrder(newEnterprise, loginUser);

        //默认总部当前商品相关数据
        List<Map<String, Object>> goodsIdList = goodsMapper.selectGoodsIdByEnterpriseId(loginUser.getEnterpriseId());

        if(goodsIdList != null && goodsIdList.size() > 0){

            int totalSize = goodsIdList.size();
            int pageSize = 2000; // 每页N条
            int totalPage = (totalSize + pageSize - 1) / pageSize;// 算出页数

            List<Map<String, Object>> subGoodsIdList = null;
            for (int pageNum = 1; pageNum < totalPage + 1; pageNum++) {
                int starNum = (pageNum - 1) * pageSize;

                subGoodsIdList = new ArrayList<Map<String,Object>>();

                int endNum = (starNum + pageSize) > goodsIdList.size() ? goodsIdList.size() : (starNum + pageSize);

                subGoodsIdList.addAll(goodsIdList.subList(starNum, endNum));

                saveStoreDefailtGoods(newEnterprise, loginUser, subGoodsIdList, distrPriceOrderId, order);
            }
        }

    }

    private void saveStoreDefaultUser(Enterprise newEnterprise, UserVO loginUser) {
        //user
        User user = new User();
        try {
            user.setCode(newEnterprise.getCode()+codeComponent.generate("User", 4, newEnterprise.getId()));
        } catch (Exception e) {
            logger.error("生成员工编码失败！", e);
            e.printStackTrace();
        }
        user.setPinyin(PinYinUtils.getFirstSpell(newEnterprise.getUserName()));
        user.setName(newEnterprise.getUserName());
        user.setEnterpriseId(newEnterprise.getId());
        user.setParentId(loginUser.getEnterpriseId());
        user.setStatus(1);
        user.setCreaterId(loginUser.getUserId());
        user.setCreaterCode(loginUser.getUserCode());
        user.setCreaterName(loginUser.getUserName());
        user.setCreateTime(new Date());
        user.setModifierId(loginUser.getUserId());
        user.setModifierCode(loginUser.getUserCode());
        user.setModifierName(loginUser.getUserName());
        user.setUpdateTime(new Date());
        userMapper.insertSelective(user);

        newEnterprise.setAdminId(user.getId());
        enterpriseMapper.updateByPrimaryKeySelective(newEnterprise);

        //saas_user_administration
        UserAdministration admin = new UserAdministration();
        admin.setUserId(user.getId());
        admin.setEnterpriseId(newEnterprise.getId());
        admin.setParentId(loginUser.getEnterpriseId());
        admin.setUserType("0");
        admin.setLoginAccount(newEnterprise.getUserName());
        admin.setPassword(newEnterprise.getPassWord());
        admin.setPasswordConfirm(newEnterprise.getPassWordConfirmation());
        admin.setDeptIds("");//部门ID集合,部门-信息管理部，等待默认数据
        admin.setPositionIds("");//岗位ID集合,岗位-信息管理经理，等待默认数据
        admin.setRoleIds("");//角色ID集合,角色-信息管理经理
        admin.setLimitVariety("0,1,2,3,4");//受限品种集合（可多选：0-药品；1-食品；2-化妆品；3-医疗器械；4-其它）
        admin.setLimitVarietyRange("");//受限品种范围ID集合
        admin.setStatus(EnableStatus.ENABLE.getStatus());
        admin.setCreaterId(loginUser.getUserId());
        admin.setCreaterCode(loginUser.getUserCode());
        admin.setCreaterName(loginUser.getUserName());
        admin.setCreateTime(new Date());
        admin.setModifierId(loginUser.getUserId());
        admin.setModifierCode(loginUser.getUserCode());
        admin.setModifierName(loginUser.getUserName());
        admin.setUpdateTime(new Date());
        administrationMapper.insertSelective(admin);
        //saas_user_personal
        UserPersonal per = new UserPersonal();
        per.setUserId(user.getId());
        per.setEnterpriseId(newEnterprise.getId());
        per.setParentId(loginUser.getEnterpriseId());
        per.setSex(0);//男
        per.setIdNum("");
//				per.setBirthDate();
        per.setMaritalStatus(0);//婚姻状况（0-未婚；1-已婚）
        per.setNationId(0L);//民族ID,民族-汉族
        per.setPoliticalOutlook("群众");
        per.setOriginPlace("");//籍贯
        per.setAdderss("");//住址
        per.setMobilePhone(newEnterprise.getAdminPhone());
        per.setEmail(newEnterprise.getEmail());
        per.setStatus(1);
        per.setCreaterId(loginUser.getUserId());
        per.setCreaterCode(loginUser.getUserCode());
        per.setCreaterName(loginUser.getUserName());
        per.setCreateTime(new Date());
        per.setModifierId(loginUser.getUserId());
        per.setModifierCode(loginUser.getUserCode());
        per.setModifierName(loginUser.getUserName());
        per.setUpdateTime(new Date());
        userPersonalMapper.insertSelective(per);
        //saas_user_role
        UserRole role = new UserRole();
        role.setUserId(user.getId());
        role.setEnterpriseId(newEnterprise.getId());
        role.setParentId(loginUser.getEnterpriseId());
        role.setRoleId(10000L);
        role.setCreaterId(loginUser.getUserId());
        role.setCreaterCode(loginUser.getUserCode());
        role.setCreaterName(loginUser.getUserName());
        role.setCreateTime(new Date());
        role.setModifierId(loginUser.getUserId());
        role.setModifierCode(loginUser.getUserCode());
        role.setModifierName(loginUser.getUserName());
        role.setUpdateTime(new Date());
        userRoleMapper.insertSelective(role);

    }

    private PriceOrder savePriceOrder(Enterprise newEnterprise, UserVO loginUser) {
        //默认价格清单主表
        PriceOrder order = new PriceOrder();
        order.setEnterpriseId(newEnterprise.getId());
        order.setParentId(loginUser.getEnterpriseId());
        PriceOrder parentPriceOrder = priceOrderMapper.selectByCodeAndEnterpriceIdAndParentId(SysType.SYSTEM.getCode(),
                newEnterprise.getEnterpriseId(), newEnterprise.getParentId());
        order.setParentOrderId(parentPriceOrder.getId());
        // 类型：0-自定义价格清单；1-系统价格清单
        order.setSysType(SysType.SYSTEM.getCode());
        // 价格类型（0-基础价格；1-配货价格；2-零售价格）
        order.setPriceType(2);
        try {
            order.setCode(codeComponent.generate("PriceOrder", 4, loginUser.getEnterpriseId()));
        } catch (Exception e) {
            logger.error("生成价格清单编码失败！", e);
            e.printStackTrace();
        }
        order.setName(newEnterprise.getName()+"分店价格清单");
        order.setStatus(EnableStatus.ENABLE.getStatus());
        order.setRemark("系统默认总部价格清单");
        order.setCreaterId(loginUser.getUserId());
        order.setCreaterCode(loginUser.getUserCode());
        order.setCreaterName(loginUser.getUserName());
        order.setCreateTime(new Date());
        order.setModifierId(loginUser.getUserId());
        order.setModifierCode(loginUser.getUserCode());
        order.setModifierName(loginUser.getUserName());
        order.setUpdateTime(new Date());
        priceOrderMapper.insertSelective(order);

        return order;
    }

    private void saveStoreDefailtGoods(Enterprise newEnterprise, UserVO loginUser,
                                       List<Map<String, Object>> goodsIdList, Long distrPriceOrderId, PriceOrder order) {
        //默认安全库存表数据
        List<SafetyStock> stockList = new ArrayList<>();
        SafetyStock stock = null;
        for (Map<String, Object> map : goodsIdList) {
            stock = new SafetyStock();
            stock.setEnterpriseId(newEnterprise.getId());
            stock.setParentId(loginUser.getEnterpriseId());
            stock.setChainType(newEnterprise.getChainType());
            stock.setGoodsId(Long.parseLong(map.get("id").toString()));
            stock.setStatus(EnableStatus.ENABLE.getStatus());
            stock.setCreaterId(loginUser.getUserId());
            stock.setCreaterCode(loginUser.getUserCode());
            stock.setCreaterName(loginUser.getUserName());
            stock.setCreateTime(new Date());
            stock.setModifierId(loginUser.getUserId());
            stock.setModifierCode(loginUser.getUserCode());
            stock.setModifierName(loginUser.getUserName());
            stock.setUpdateTime(new Date());
            stockList.add(stock);
        }
        safetyStockMapper.batchInsert(stockList);

        //价格清单Map
        Map<Long, Double> priceOrderMap = new HashMap<Long, Double>();
        boolean priceOrder = false;
        if(distrPriceOrderId != null && distrPriceOrderId != 0){
            priceOrder = true;
            List<Map<String, Object>> priceOrderList = priceOrderDetailMapper.selectDistrPriceByOrderIdAndEnterpriseId(distrPriceOrderId, loginUser.getEnterpriseId());
            if(priceOrderList != null){
                for (Map<String, Object> map : priceOrderList) {
                    priceOrderMap.put(map.get("goods_id")!=null?Long.parseLong(map.get("goods_id").toString()):0L,
                            map.get("distr_price")!=null?Double.parseDouble(map.get("distr_price").toString()):0);
                }
            }
        }

        //默认价格清单细表
        List<PriceOrderDetail> orderDetailList = new ArrayList<>();
        PriceOrderDetail orderDetail = null;
        for (Map<String, Object> map : goodsIdList) {
            orderDetail = new PriceOrderDetail();
            orderDetail.setPriceOrderId(order.getId());
            orderDetail.setEnterpriseId(newEnterprise.getId());
            orderDetail.setParentId(loginUser.getEnterpriseId());
            orderDetail.setGoodsId(Long.parseLong(map.get("id").toString()));
            orderDetail.setStatus(1);
            orderDetail.setDistrTaxRate(getBigDecimalValue("distr_tax_rate", map));
            orderDetail.setRetailPrice(getBigDecimalValue("retail_price", map));
            orderDetail.setMemberPrice(getBigDecimalValue("member_price", map));
            orderDetail.setSaleTaxRate(getBigDecimalValue("sale_tax_rate", map));

            if(priceOrder){
                orderDetail.setDistrPrice(BigDecimal.valueOf(priceOrderMap.get("goods_id")));
                orderDetail.setNotaxDistrPrice(getExclusiveTax(orderDetail.getDistrPrice(),orderDetail.getDistrTaxRate()));
            }else{
                orderDetail.setDistrPrice(BigDecimal.valueOf(0));
                orderDetail.setNotaxDistrPrice(BigDecimal.valueOf(0));
            }
            orderDetail.setNotaxRetailPrice(getExclusiveTax(orderDetail.getRetailPrice(),orderDetail.getSaleTaxRate()));
            orderDetail.setNotaxMemberPrice(getExclusiveTax(orderDetail.getMemberPrice(),orderDetail.getSaleTaxRate()));

            orderDetail.setCreaterId(loginUser.getUserId());
            orderDetail.setCreaterCode(loginUser.getUserCode());
            orderDetail.setCreaterName(loginUser.getUserName());
            orderDetail.setCreateTime(new Date());
            orderDetail.setModifierId(loginUser.getUserId());
            orderDetail.setModifierCode(loginUser.getUserCode());
            orderDetail.setModifierName(loginUser.getUserName());
            orderDetail.setUpdateTime(new Date());
            orderDetailList.add(orderDetail);
        }
        priceOrderDetailMapper.batchInsert(orderDetailList);
        //默认商品管理表
        List<GoodsManage> manageList = new ArrayList<>();
        GoodsManage goodsManage = null;
        for(int i=0; i<goodsIdList.size(); i++){
            goodsManage = new GoodsManage();
            goodsManage.setEnterpriseId(newEnterprise.getId());
            goodsManage.setParentId(loginUser.getEnterpriseId());
            goodsManage.setChainType(newEnterprise.getChainType());
            goodsManage.setGoodsId(Long.parseLong(goodsIdList.get(i).get("id").toString()));
            goodsManage.setSafetyStockId(stockList.get(i).getId());
            goodsManage.setPriceOrderDtlId(orderDetailList.get(i).getId());
            goodsManage.setStatus(GoodsManageStatus.WAIT_ON_SHELVES.getCode());
            goodsManage.setCreaterId(loginUser.getUserId());
            goodsManage.setCreaterCode(loginUser.getUserCode());
            goodsManage.setCreaterName(loginUser.getUserName());
            goodsManage.setCreateTime(new Date());
            goodsManage.setModifierId(loginUser.getUserId());
            goodsManage.setModifierCode(loginUser.getUserCode());
            goodsManage.setModifierName(loginUser.getUserName());
            goodsManage.setUpdateTime(new Date());
            manageList.add(goodsManage);
        }
        goodsManageMapper.batchInsert(manageList);
    }

    public BigDecimal getBigDecimalValue(String string, Map<String, Object> map) {
        if(map == null || map.get(string) == null || StringUtils.isBlank(map.get(string).toString())){
            return new BigDecimal(0);
        }else{
            return new BigDecimal(map.get(string).toString());
        }
    }




    public BigDecimal getExclusiveTax(BigDecimal distrPrice,
                                              BigDecimal distrTaxRate) {
        return distrPrice.divide(
                (distrTaxRate.divide(BigDecimal.valueOf(100),4,BigDecimal.ROUND_HALF_UP).add(BigDecimal.valueOf(1)))
                ,2
                ,BigDecimal.ROUND_HALF_UP
        );
    }

    public String getLocationName(Integer id) {
        Location lo = locationMapper.selectByPrimaryKey(id);
        return lo != null?lo.getName():"";
    }

    public List<Enterprise> getEnterpriseWithSon(Long enterpriseId) {
        List<Enterprise> list = enterpriseMapper.selectEnterpriseWithSon(enterpriseId);
        return list;
    }

    public List<Enterprise> selectEnterpriseById(Long enterpriseId) {
        List<Enterprise> list = new ArrayList<>();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        list.add(enterprise);
        return list;
    }
}
