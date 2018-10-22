package com.rograndec.feijiayun.chain.business.goods.info.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Sets;
import com.rograndec.feijiayun.chain.app.SpringBeanFactory;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.vo.UserDataVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.goods.info.constant.GoodsInfoConstant;
import com.rograndec.feijiayun.chain.business.goods.info.dao.*;
import com.rograndec.feijiayun.chain.business.goods.info.entity.*;
import com.rograndec.feijiayun.chain.business.goods.info.excel.GoodsInfoIRowReader;
import com.rograndec.feijiayun.chain.business.goods.info.service.GoodsService;
import com.rograndec.feijiayun.chain.business.goods.info.vo.*;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.GoodsManageMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.GoodsManage;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.dao.SpecialGoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.SpecialGoods;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsCategoryMapper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsDosageMapper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsUnitMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsDosage;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsUnit;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsDosageVO;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsTaxRateVO;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsUnitVO;
import com.rograndec.feijiayun.chain.business.storage.split.dao.SplitSetMapper;
import com.rograndec.feijiayun.chain.business.storage.split.entity.SplitSet;
import com.rograndec.feijiayun.chain.business.storage.split.vo.CanSpitGoodVO;
import com.rograndec.feijiayun.chain.business.storage.split.vo.SplitSetSaveVO;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatus;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SubmitApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.BusinessScopeMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.GoodsQualificationMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.BusinessScope;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import com.rograndec.feijiayun.chain.business.system.set.service.ManageConfigService;
import com.rograndec.feijiayun.chain.business.system.set.vo.BusinessScopeVO;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.FileUtils;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelReaderUtil;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.param.ParamUtils;
import com.rograndec.feijiayun.chain.utils.pinyin.PinYinUtils;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品服务接口
 * Created by ST on 2017/8/23.
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    private Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsBusinessMapper goodsBusinessMapper;

    @Autowired
    private GoodsInstructionsMapper goodsInstructionsMapper;

    @Autowired
    private GoodsQualificationConfigMapper goodsQualificationConfigMapper;

    @Autowired
    private GoodsStorageMapper goodsStorageMapper;


    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private GoodsManageMapper goodsManageMapper;

    @Autowired
    private SafetyStockMapper safetyStockMapper;

    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;

    @Autowired
    private PriceOrderMapper priceOrderMapper;

    @Autowired
    private ManufacturerMapper manufacturerMapper;



    @Autowired
    private BusinessScopeMapper businessScopeMapper;

    @Autowired
    private QualitySetMapper qualitySetMapper;

    @Autowired
    private ModifyRecordCompoent modifyRecordCompoent;

    @Autowired
    private GoodsModifyRecordMapper goodsModifyRecordMapper;

//    @Autowired
//    private GoodsInfoIRowReader goodsInfoIRowReader;

    @Autowired
    private RedisComponent redisComponent;

//    @Autowired
//    private RedisUtilsComponent redisUtilsComponent;

    @Autowired
    private GoodsDosageMapper dosageMapper;
    @Autowired
    private GoodsUnitMapper unitMapper;
    @Autowired
    private GoodsCategoryMapper categoryMapper;

    @Autowired
    private ManageConfigComponent manageConfigComponent;

    @Autowired
    private CodeComponent codeComponent;

    @Autowired
    private GoodsQualificationMapper goodsQualificationMapper;

    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Autowired
    private SplitSetMapper splitSetMapper;

    @Autowired
    private GoodsUnitMapper goodsUnitMapper;

    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;

    @Autowired
    private ApprovalFlowComponent approvalFlowComponent;

    @Autowired
    private UserMapper userMapper;

	@Autowired
	private ManageConfigService manageConfigService;

    @Autowired
    SpecialGoodsMapper specialGoodsMapper;

    /**
     * 根據商品编码查詢商品信息
     * @param code
     * @return
     */
    @Override
    public Goods getGoodsByCode(Long enterpriseId, String code){
        GoodsParamVO paramVO = new GoodsParamVO();
        paramVO.setEnterpriseId(enterpriseId);
        paramVO.setParam(code);
        return goodsMapper.getGoodsByCode(paramVO);
    }

    /**
     * 根據商品编码查詢商品信息
     * @param code
     * @return
     */
    @Override
    public Goods getGoodsByCode2(CommonParamSupplierAndGoods paramSupplierAndGoods, UserVO userVO, String code){
        return goodsMapper.getGoodsByCode2(paramSupplierAndGoods,code);
    }



    /**
     * 查询商品编码是否存在
     * @param oldCode
     * @param enterpriseId
     * @return
     */
    @Override
    public Goods getGoodsByOldCode(String oldCode, Long enterpriseId){
        return goodsMapper.getGoodsByOldCode(oldCode,enterpriseId);
    }




    @Override
    public Page<List<GoodsVO>> getGoodsListByParam(UserVO userVO, RequestGoodsVO goodsVO,Page<List<GoodsVO>> page) {
//        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        ParamUtils.packParam(userVO,goodsVO);

        RequestGoodsVO2 requestGoodsVO2 = new RequestGoodsVO2();
        BeanUtils.copyProperties(goodsVO,requestGoodsVO2);
        requestGoodsVO2.setStart(page.getStart());
        requestGoodsVO2.setPageNo(page.getPageNo());
        requestGoodsVO2.setPageSize(page.getPageSize());
        List<GoodsVO> list = goodsMapper.getGoodsListByParam(requestGoodsVO2);

        //添加未绑定商品信息的必填的商品资质
        list.forEach(item->{
            GoodsCategory goodsCategory = categoryMapper.selectByPrimaryKey(item.getCategoryId());
            item.setCategoryName(goodsCategory.getName());
            List<GoodsQualificationConfigVO> qualificationRelateGoods = goodsMapper.getGoodsQualificationConfig(item.getId(),item.getEnterpriseId());
            List<GoodsQualificationConfigVO>  qualificationNotRelateGoods = goodsQualificationMapper.getQualificationNotRelateGoods(item.getEnterpriseId(),item.getId(),1,1,item.getCheckTypeId());
            qualificationNotRelateGoods.addAll(qualificationRelateGoods);
            item.setGoodsQualificationConfigVOList(qualificationNotRelateGoods);
            item.setIsOwner(item.getOwnerId().equals(userVO.getEnterpriseId()) ? 1 : 0);  //是否可编辑
        });
        Integer count = goodsMapper.getGoodsCountByParam(requestGoodsVO2);
        page.setResult(list);
        page.setTotalRecord(count);
        return page;
    }

    /**
     * 商品信息添加
     * @param userVO
     * @param goodsVO
     * @param exlImport true 是由导入生成
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int addGoods(UserVO userVO, GoodsVO goodsVO, boolean exlImport) throws Exception {
        logger.info("商品信息新增");
        EntityFixedInfoVO entityVo = new EntityFixedInfoVO();

        EntityFixedInfoVO entityFixedVO  = (EntityFixedInfoVO) EntityUtils.reflectAddSetDefaultValue(entityVo.getClass(),userVO);
        Long enterpriseId;
        if(userVO.getChainType() == ChainType.Headquarters.getCode()){
            enterpriseId = userVO.getEnterpriseId();
        } else {
            enterpriseId = userVO.getParentId();
        }

        Long userId = userVO.getUserId();
        String userName = userVO.getUserName();
        goodsVO.setId(null);

        //xinhao
        //审核流逻辑处理start
        Integer status = ApprovalFlowAuditStatus.DETAIL_STATUS_PASS.getValue();// 默认状态为审核通过状态
        ManageConfig flow = manageConfigService.getManageConfig(userVO);
		if (flow.getApprovalControl() == 1  && !exlImport) {// 如果审批流开启
			status = ApprovalFlowAuditStatus.DETAIL_STATUS_WAIT.getValue();// 待审核状态
		}
        //审核流逻辑处理end

//            Integer businessControl = 1;//业务流程质量控制（0关闭；1-开启）
        ManageConfig manageConfig = manageConfigComponent.getMangeConfigByHeadquarters(userVO);
        //商品信息编码规则（0-商品分组+4位流水码；1-自定义编码）
        Integer goodsCodeRule = manageConfig.getGoodsCodeRule();//商品信息规则

        if(goodsCodeRule == 0){
            if(!exlImport){ //商品导入，商品编码为系统设置的不需要再次生成编码
                //系统设置编码
                Long categoryId = goodsVO.getCategoryId();//商品分类id
                GoodsCategory category = categoryMapper.selectByPrimaryKey(categoryId);
                if(category != null ){
                    String categoryCode = category.getCode();
                    String code = codeComponent.generate(Goods.class.getSimpleName(), 8, enterpriseId);
                    goodsVO.setCode(categoryCode + code);
                } else {
                    throw  new BusinessException("商品分类的编码不存在");
                }
            }

        } else {
            String code = goodsVO.getCode();
            Integer countByCode = goodsMapper.getCountByCode(code, ChainType.getHeadEnterpriseId(userVO));
            if(countByCode > 0){
                throw  new BusinessException("商品编码已存在");
            }

        }

        //生成厂商id
        Long manufacturerId = goodsVO.getManufacturerId();
        String manufactureName = goodsVO.getManufacturer();
        executeManufactStorage(goodsVO, entityFixedVO, userVO.getEnterpriseId(), manufacturerId, manufactureName);
        //####################################################################################//
        //#                                商品信息的添加,只维护总部
        //####################################################################################//
        //从goodsVO 中复制Goods的是值
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsVO,goods);


        Goods goods1 = (Goods) EntityUtils.reflectAddSetDefaultValue(goods.getClass(),userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goods,goods1);

        goods1.setEnterpriseId(enterpriseId);
        //当前企业
        goods1.setOwnerId(userVO.getEnterpriseId());
        goods1.setChainType(userVO.getChainType());
//        goods1.setParentId(userVO.getParentId());
        checkGoods(goodsVO, goods1);
        //如果商品名称为空的话，把通用名称作为商品名称
        if(StringUtils.isEmpty(goodsVO.getName())){
            goods1.setName(goodsVO.getGenericName());
        }
        //检索码
        if(exlImport){
            //生成检索码
            String pinyinCode = "";
            pinyinCode = PinYinUtils.getFirstSpell(goods1.getGenericName()) + "-" + PinYinUtils.getFirstSpell(goods1.getName());
            goods1.setPinyinCode(pinyinCode);
        } else {
            goods1.setPinyinCode(StringUtil.transferTrimStr(goodsVO.getPinyinCodeBegin()) + "-" +  StringUtil.transferTrimStr(goodsVO.getPinyinCodeEnd()));
        }

        //专管药品
        Integer inChargeDrug = goods1.getInChargeDrug();
        if(inChargeDrug == null || inChargeDrug == -1){
            goods1.setFormulationType(-1);
            goods1.setLimitQuantity(BigDecimal.ZERO);
        }


        goods1.setApproveStatus(status);//设置商品的审核状态


        if(goods1.getFirstGoodsCode() == null && goodsCodeRule == 0){//如果首营编码为空,则默认为商品编码
            goods1.setFirstGoodsCode(goods1.getCode());
        }

        if(userVO.getChainType() == ChainType.Headquarters.getCode()){
            if(goods1.getDistrFlag() != null){
                goods1.setDistrFlag(goods1.getDistrFlag());
            } else {
                goods1.setDistrFlag(DistrFlag.DISTRFLAG_A.getCode());
            }
        } else {
            goods1.setDistrFlag(DistrFlag.DISTRFLAG_A.getCode());
        }


        goodsMapper.insertSelective(goods1);
        Long goodsId = goods1.getId();



        GoodsBusiness goodsBusiness = new GoodsBusiness();
        //从goodsVO 中复制goodsBusiness的是值
        if(goodsVO.getGoodsBusinessVO() != null){
            goodsVO.getGoodsBusinessVO().setId(null);
            BeanUtils.copyProperties(goodsVO.getGoodsBusinessVO(),goodsBusiness);
            GoodsBusiness goodsBusiness1 = (GoodsBusiness) EntityUtils.reflectAddSetDefaultValue(goodsBusiness.getClass(),userVO);
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsBusiness,goodsBusiness1);
            goodsBusiness1.setGoodsId(goodsId);
            goodsBusiness1.setEnterpriseId(enterpriseId);

            //设置税率
            Long purchaseTaxRateId = goodsBusiness1.getPurchaseTaxRateId();
            GoodsTaxRate purchaseTaxRate = goodsTaxRateMapper.selectByPrimaryKey(purchaseTaxRateId);
            if(purchaseTaxRate != null){
                goodsBusiness1.setPurchaseTaxRateId(purchaseTaxRate.getId());
                goodsBusiness1.setPurchaseTaxRate(purchaseTaxRate.getTaxRate());
            } else {
                throw new BusinessException("进项税不存在");
            }
            Long saleTaxRateId = goodsBusiness1.getSaleTaxRateId();
            GoodsTaxRate saleTaxRate = goodsTaxRateMapper.selectByPrimaryKey(saleTaxRateId);
            if(saleTaxRate != null){
                goodsBusiness1.setSaleTaxRateId(saleTaxRate.getId());
                goodsBusiness1.setSaleTaxRate(saleTaxRate.getTaxRate());
            } else {
                throw new BusinessException("销项税不存在");
            }
            Long distrTaxRateId = goodsBusiness1.getDistrTaxRateId();
            GoodsTaxRate distrTaxRate = goodsTaxRateMapper.selectByPrimaryKey(distrTaxRateId);
            if(distrTaxRate != null){
                goodsBusiness1.setDistrTaxRateId(distrTaxRate.getId());
                goodsBusiness1.setDistrTaxRate(distrTaxRate.getTaxRate());
            } else {
                throw new BusinessException("配货税率不存在");
            }
//            销售定价策略：0总部统一定价,1允许门店自主定价.总部添加的商品默
//        认总部统一定价， 加盟店添加的商品默认允许门店自主定价且只读
            Integer pricingPolicy = goodsBusiness1.getPricingPolicy();

            if(userVO.getChainType() == ChainType.Headquarters.getCode()){
                if(pricingPolicy != null){
                    goodsBusiness1.setPricingPolicy(pricingPolicy);
                } else {
                    goodsBusiness1.setPricingPolicy(PricingPolicyType.HEADQUARTERS_UNIFORM_PRICING.getCode());
                }
            } else {
                goodsBusiness1.setPricingPolicy(PricingPolicyType.AUTOMATICALLY_PRICED_STORES.getCode());
            }


            goodsBusinessMapper.insertSelective(goodsBusiness1);
        } else {
            GoodsBusiness goodsBusiness1 = (GoodsBusiness) EntityUtils.reflectAddSetDefaultValue(goodsBusiness.getClass(),userVO);
            goodsBusiness1.setGoodsId(goodsId);
            goodsBusiness1.setEnterpriseId(userVO.getEnterpriseId());
//            goodsBusiness1.setParentId(parentId);
            goodsBusinessMapper.insertSelective(goodsBusiness1);
        }

        //从goodsVO 中复制 goodsStorage 的是值
        GoodsStorage goodsStorage = new GoodsStorage();
        if(goodsVO.getStorageMaintenanceVO() != null){
            goodsVO.getStorageMaintenanceVO().setId(null);
            BeanUtils.copyProperties(goodsVO.getStorageMaintenanceVO(), goodsStorage);
            GoodsStorage goodsStorage1 = (GoodsStorage) EntityUtils.reflectAddSetDefaultValue(goodsStorage.getClass(),userVO);
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsStorage, goodsStorage1);
            goodsStorage1.setGoodsId(goodsId);
            goodsStorage1.setEnterpriseId(enterpriseId);
//            goodsStorage1.setParentId(parentId);
            goodsStorageMapper.insertSelective(goodsStorage1);

        } else {
            GoodsStorage goodsStorage1 = (GoodsStorage) EntityUtils.reflectAddSetDefaultValue(goodsStorage.getClass(),userVO);
            goodsStorage1.setGoodsId(goodsId);
            goodsStorage1.setEnterpriseId(enterpriseId);
//            goodsStorage1.setParentId(parentId);
            goodsStorageMapper.insertSelective(goodsStorage1);
        }




        //从goodsVO 中复制 goodsInstructions 的是值
        GoodsInstructions goodsInstructions = new GoodsInstructions();
        if(goodsVO.getGoodsInstructionsVO() != null){
            goodsVO.getGoodsInstructionsVO().setId(null);
            BeanUtils.copyProperties(goodsVO.getGoodsInstructionsVO(),goodsInstructions);
            GoodsInstructions goodsInstructions1 = (GoodsInstructions) EntityUtils.reflectAddSetDefaultValue(goodsInstructions.getClass(),userVO);
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsInstructions,goodsInstructions1);
            goodsInstructions1.setGoodsId(goodsId);
            goodsInstructions1.setEnterpriseId(enterpriseId);
//            goodsInstructions1.setParentId(parentId);
            goodsInstructionsMapper.insertSelective(goodsInstructions1);
        } else {
            GoodsInstructions goodsInstructions1 = (GoodsInstructions) EntityUtils.reflectAddSetDefaultValue(goodsInstructions.getClass(),userVO);
            goodsInstructions1.setGoodsId(goodsId);
            goodsInstructions1.setEnterpriseId(enterpriseId);
//            goodsInstructions1.setParentId(parentId);
            goodsInstructionsMapper.insertSelective(goodsInstructions1);
        }



        List<GoodsQualificationConfigVO> qualificationConfigVOList = goodsVO.getGoodsQualificationConfigVOList();
        if(qualificationConfigVOList != null){
            qualificationConfigVOList.forEach(item->{
                GoodsQualificationConfig goodsQualificationConfig = new GoodsQualificationConfig();

                //从goodsVO 中复制 goodsQualificationConfig 的是值
                BeanUtils.copyProperties(item,goodsQualificationConfig);
                GoodsQualificationConfig goodsQualificationConfig1 = null;
                try {
                    goodsQualificationConfig1 = (GoodsQualificationConfig) EntityUtils.reflectAddSetDefaultValue(goodsQualificationConfig.getClass(),userVO);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsQualificationConfig,goodsQualificationConfig1);
                goodsQualificationConfig1.setGoodsId(goodsId);
                goodsQualificationConfig1.setEnterpriseId(enterpriseId);
//                goodsQualificationConfig1.setParentId(parentId);
                goodsQualificationConfig1.setId(null);// 前台会穿ID过来
                goodsQualificationConfigMapper.insertSelective(goodsQualificationConfig1);
            });
        }

        //新增商品同步新增到专管药品表,add by xiaokang.zhu
        if ( null != goodsVO.getInChargeDrug() && goodsVO.getInChargeDrug() != -1){
            SpecialGoods specialGoods = this.setSpecialGoods(goods1, userVO);
            UserEnterpriseUtils.setUserCreateOrModify(specialGoods, userVO, true);
            specialGoodsMapper.insertSelective(specialGoods);
        }


        handleGoodsSafetyStockManagePriceOrder(userVO, null, entityFixedVO, goodsVO, goodsId);



        //####################################################################################//
        //#                                商品审批流程添加                                  #//
        //####################################################################################//

        //审批流对象
        // 查询一条默认初始化的审批流
        if(!exlImport){
            Integer chainType = userVO.getChainType();
            SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(userVO.getEnterpriseId(),userVO.getEnterpriseName(),
                    userId, userName, chainType, userVO.getParentId(),enterpriseId,"0201", goodsId, goods1.getCode(), goods1.getName());
            approvalFlowComponent.apply(submitApprovalFlowVO,userVO);
        }
        return 0;
    }

    /**
     * 设置专管商品信息
     * @param goods
     * @param userVO
     * @throws Exception
     */
    private SpecialGoods setSpecialGoods(Goods goods, UserVO userVO) throws Exception{
        SpecialGoods specialGoods = new SpecialGoods();
        specialGoods.setEnterpriseId(userVO.getEnterpriseId());
        specialGoods.setParentId(userVO.getParentId());
        specialGoods.setCreateTime(new Date());
        specialGoods.setDosageId(goods.getDosageId());
        specialGoods.setDosageName(goods.getDosageName());
        specialGoods.setGoodsId(goods.getId());
        specialGoods.setGoodsCode(goods.getCode());
        specialGoods.setGoodsName(goods.getName());
        specialGoods.setSpecification(goods.getSpecification());
        specialGoods.setManufacturer(goods.getManufacturer());
        specialGoods.setManufacturerId(goods.getManufacturerId());
        specialGoods.setSpecialGoods(goods.getInChargeDrug());
        specialGoods.setFormulationType(goods.getFormulationType());
        specialGoods.setUnitId(goods.getUnitId());
        specialGoods.setUnitName(goods.getUnitName());
        specialGoods.setLimitSaleQuantity(goods.getLimitQuantity());
        specialGoods.setStatus(1);  //启用
        specialGoods.setFormulationType(goods.getFormulationType());
        return specialGoods;
    }

    /**
     * 拆零商品添加
     * @param userVO 用户信息
     * @param splitSetSaveVO 拆零设置信息
     * @return
     * @throws Exception
     */
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    @Override
    public int addSplitGoods(UserVO userVO, SplitSetSaveVO splitSetSaveVO) throws Exception {
        EntityFixedInfoVO entityVo = new EntityFixedInfoVO();
        EntityFixedInfoVO entityFixedVO  = (EntityFixedInfoVO) EntityUtils.reflectAddSetDefaultValue(entityVo.getClass(), userVO);
        Long enterpriseId = userVO.getEnterpriseId();
        Long parentId = userVO.getParentId();


        if(userVO.getChainType() == ChainType.Headquarters.getCode()){
            enterpriseId = userVO.getEnterpriseId();
        } else {
            enterpriseId = userVO.getParentId();
        }

        GoodsVO goodsVO = goodsMapper.selectGoodsInfoById(splitSetSaveVO.getGoodsId());
        if(goodsVO == null) {
            throw new BusinessException("查询商品信息错误！");
        }

        //单独查询资质相关信息
        List<GoodsQualificationConfigVO> qualificationRelateGoods = goodsMapper.getGoodsQualificationConfig(splitSetSaveVO.getGoodsId(), enterpriseId);
        List<GoodsQualificationConfigVO>  qualificationNotRelateGoods = goodsQualificationMapper.getQualificationNotRelateGoods(enterpriseId, splitSetSaveVO.getGoodsId(),1,1,goodsVO.getCheckTypeId());
        if (qualificationRelateGoods != null && qualificationNotRelateGoods!= null) {
            qualificationNotRelateGoods.addAll(qualificationRelateGoods);
            goodsVO.setGoodsQualificationConfigVOList(qualificationNotRelateGoods);
        }

        //拆零商品编码 生成规则：原商品code + "_" + “01”流水号
        int sCount = splitSetMapper.countSplitGoods(splitSetSaveVO.getGoodsId(), userVO.getEnterpriseId());
        String splitGoodsCode;
        if (sCount + 1 < 10) {
            splitGoodsCode = goodsVO.getCode() + "_0" + (sCount + 1);
        } else {
            splitGoodsCode = goodsVO.getCode() + "_" + (sCount + 1);
        }

        //替换商品编码
        goodsVO.setCode(splitGoodsCode);
        //替换商品规格
        goodsVO.setSpecification(splitSetSaveVO.getSplitSpecification());
        //替换库存计量单位、名称
        GoodsUnit goodsUnit = goodsUnitMapper.selectByPrimaryKey(splitSetSaveVO.getSplitUnitId());
        if (goodsUnit == null) {
            throw new BusinessException("查询库存单位信息错误！");
        }
        goodsVO.setUnitId(goodsUnit.getId());
        goodsVO.setUnitName(goodsUnit.getName());
        splitSetSaveVO.setSplitUnitName(goodsUnit.getName());

        //默认货位信息
//        WarehouseShelf warehouseShelf = warehouseShelfMapper.selectByPrimaryKey(splitSetSaveVO.getSplitShelfId());
//        if (warehouseShelf == null) {
//            throw new BusinessException("查询货位信息错误！");
//        }
//        splitSetSaveVO.setSplitShelfName(warehouseShelf.getName());

        //商品性质改为 拆零商品
        goodsVO.setGoodsNature(1);

        //生成厂商id
        Long manufacturerId = goodsVO.getManufacturerId();
        String manufactureName = goodsVO.getManufacturer();
        executeManufactStorage(goodsVO, entityFixedVO, enterpriseId, manufacturerId, manufactureName);
        //####################################################################################//
        //#                                商品信息的添加,只维护总部
        //####################################################################################//
        //从goodsVO 中复制Goods的是值
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsVO, goods);
        Goods goods1 = (Goods) EntityUtils.reflectAddSetDefaultValue(goods.getClass(), userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goods, goods1);
        goods1.setEnterpriseId(enterpriseId);

        //当前企业
        goods1.setOwnerId(userVO.getEnterpriseId());
        goods1.setChainType(userVO.getChainType());
        goods1.setId(null);
        //商品性质改为 拆零商品,确保商品性质为1
        goods1.setGoodsNature(1);
        goods1.setApproveStatus(goodsVO.getApproveStatus());
        goods1.setName(goods1.getName() + "(拆零)");
        goods1.setGenericName(goods1.getGenericName() + "(拆零)");
        goodsMapper.insertSelective(goods1);
        Long goodsId = goods1.getId();
        goodsVO.setId(goodsId);

        GoodsBusiness goodsBusiness = new GoodsBusiness();
        //从goodsVO 中复制goodsBusiness的是值
        if(goodsVO.getGoodsBusinessVO() != null){

            //拆零商品价格替换
            goodsVO.getGoodsBusinessVO().setRetailPrice(splitSetSaveVO.getSplitRetailPrice());
            goodsVO.getGoodsBusinessVO().setMemberPrice(splitSetSaveVO.getSplitMemberPrice());
            goodsVO.getGoodsBusinessVO().setDistrPrice(BigDecimal.ZERO);

            goodsVO.getGoodsBusinessVO().setId(null);
            BeanUtils.copyProperties(goodsVO.getGoodsBusinessVO(),goodsBusiness);
            GoodsBusiness goodsBusiness1 = (GoodsBusiness) EntityUtils.reflectAddSetDefaultValue(goodsBusiness.getClass(),userVO);
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsBusiness,goodsBusiness1);
            goodsBusiness1.setGoodsId(goodsId);
            goodsBusiness1.setEnterpriseId(enterpriseId);
            goodsBusinessMapper.insertSelective(goodsBusiness1);
        } else {
            GoodsBusiness goodsBusiness1 = (GoodsBusiness) EntityUtils.reflectAddSetDefaultValue(goodsBusiness.getClass(),userVO);
            goodsBusiness1.setGoodsId(goodsId);
            goodsBusiness1.setEnterpriseId(enterpriseId);
            goodsBusinessMapper.insertSelective(goodsBusiness1);
        }

        //从goodsVO 中复制 goodsStorage 的是值
        GoodsStorage goodsStorage = new GoodsStorage();
        if(goodsVO.getStorageMaintenanceVO() != null){
            goodsVO.getStorageMaintenanceVO().setId(null);
            BeanUtils.copyProperties(goodsVO.getStorageMaintenanceVO(), goodsStorage);
            GoodsStorage goodsStorage1 = (GoodsStorage) EntityUtils.reflectAddSetDefaultValue(goodsStorage.getClass(),userVO);
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsStorage, goodsStorage1);
            goodsStorage1.setGoodsId(goodsId);
            goodsStorage1.setEnterpriseId(enterpriseId);
            goodsStorageMapper.insertSelective(goodsStorage1);
        } else {
            GoodsStorage goodsStorage1 = (GoodsStorage) EntityUtils.reflectAddSetDefaultValue(goodsStorage.getClass(),userVO);
            goodsStorage1.setGoodsId(goodsId);
            goodsStorage1.setEnterpriseId(enterpriseId);
            goodsStorageMapper.insertSelective(goodsStorage1);
        }

        //从goodsVO 中复制 goodsInstructions 的是值
        GoodsInstructions goodsInstructions = new GoodsInstructions();
        if(goodsVO.getGoodsInstructionsVO() != null){
            goodsVO.getGoodsInstructionsVO().setId(null);
            goodsVO.getGoodsInstructionsVO().setUpdateTime(null);
            BeanUtils.copyProperties(goodsVO.getGoodsInstructionsVO(),goodsInstructions);
            GoodsInstructions goodsInstructions1 = (GoodsInstructions) EntityUtils.reflectAddSetDefaultValue(goodsInstructions.getClass(),userVO);
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsInstructions,goodsInstructions1);
            goodsInstructions1.setGoodsId(goodsId);
            goodsInstructions1.setEnterpriseId(enterpriseId);
            goodsInstructionsMapper.insertSelective(goodsInstructions1);
        } else {
            GoodsInstructions goodsInstructions1 = (GoodsInstructions) EntityUtils.reflectAddSetDefaultValue(goodsInstructions.getClass(),userVO);
            goodsInstructions1.setGoodsId(goodsId);
            goodsInstructions1.setEnterpriseId(enterpriseId);
            goodsInstructionsMapper.insertSelective(goodsInstructions1);
        }

        List<GoodsQualificationConfigVO> qualificationConfigVOList = goodsVO.getGoodsQualificationConfigVOList();
        if(qualificationConfigVOList != null){
            for (GoodsQualificationConfigVO item :qualificationConfigVOList){

                GoodsQualificationConfig goodsQualificationConfig = new GoodsQualificationConfig();

                //从goodsVO 中复制 goodsQualificationConfig 的是值
                BeanUtils.copyProperties(item,goodsQualificationConfig);
                GoodsQualificationConfig goodsQualificationConfig1 = null;
                try {
                    goodsQualificationConfig1 = (GoodsQualificationConfig) EntityUtils.reflectAddSetDefaultValue(goodsQualificationConfig.getClass(),userVO);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsQualificationConfig,goodsQualificationConfig1);
                goodsQualificationConfig1.setId(null);
                goodsQualificationConfig1.setGoodsId(goodsId);
                goodsQualificationConfig1.setEnterpriseId(enterpriseId);
                goodsQualificationConfigMapper.insertSelective(goodsQualificationConfig1);
            };
        }

        handleGoodsSafetyStockManagePriceOrder(userVO, splitSetSaveVO, entityFixedVO, goodsVO, goodsId);


        //添加拆零单据记录
        SplitSet splitSet = SplitSetSaveVO.convertToSplitSet(splitSetSaveVO);
        splitSet.setEnterpriseId(userVO.getEnterpriseId());
        splitSet.setParentId(userVO.getParentId());
        splitSet.setSplitGoodsId(goodsId);
        splitSet.setStatus(1);//暂未使用到该状态,默认1
        BeanUtils.copyProperties(entityFixedVO, splitSet);
        int i = splitSetMapper.insertSelective(splitSet);
        if (i != 1) {
            throw new BusinessException("生成拆单设置单据失败！");
        }

        //将该商品下的其他拆零商品的启用状态修改为禁用
        splitSetMapper.updateSplitGoodsStatus(splitSetSaveVO.getGoodsId(), goodsId, enterpriseId);
        //将修改的拆零设置状态改为禁用
        if(splitSetSaveVO.getSplitSetId() != null){
            SplitSet splitSetOld = splitSetMapper.selectByPrimaryKey(splitSetSaveVO.getSplitSetId());
            splitSetOld.setStatus(EnableStatus.DISABLE.getStatus());
            UserEnterpriseUtils.setUserCreateOrModify(splitSetOld, userVO, false);
            splitSetMapper.updateByPrimaryKeySelective(splitSetOld);
        }


        //审批流对象
        // 查询一条默认初始化的审批流
//        Integer chainType = userVO.getChainType();
//        SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(enterpriseId,userVO.getEnterpriseName(),
//                userVO.getUserId(), userVO.getUserName(), userVO.getChainType(), userVO.getParentId(),
//                chainType.equals(ChainType.Headquarters.getCode()) ? enterpriseId : userVO.getParentId(),
//                "0201", goodsId, goods1.getCode(), goods1.getName());
//        approvalFlowComponent.apply(submitApprovalFlowVO,userVO);
        return 0;
    }

    private void handleGoodsSafetyStockManagePriceOrder(UserVO userVO, SplitSetSaveVO splitSetSaveVO, EntityFixedInfoVO entityFixedVO, GoodsVO goodsVO, Long goodsId) throws Exception {
        List<Enterprise> enterprises = new ArrayList<>();
        if(userVO.getChainType() == ChainType.Division.getCode()){
            //加盟店
            Enterprise enterprise = new Enterprise();
            enterprise.setId(userVO.getEnterpriseId());
            enterprise.setParentId(userVO.getParentId());
            enterprise.setChainType(userVO.getChainType());
            enterprises.add(enterprise);

            Enterprise enterprise1 = enterpriseMapper.selectByPrimaryKey(userVO.getParentId());
            if (enterprise1 == null){
                throw new BusinessException("企业总部不存在");
            }
            enterprises.add(enterprise1);

        } else if(userVO.getChainType() == ChainType.Headquarters.getCode()){
            //总部
            enterprises = enterpriseMapper.selectChildrenByPrimaryKey(userVO.getEnterpriseId());
        } else {
            //
            throw new BusinessException("企业类型不对！");
        }

        for(Enterprise enterprise : enterprises){
            //遍历分店，维护分店的商品信息
            EnterpriseInGoodsVO enterpriseInGoodsVO = new EnterpriseInGoodsVO();
            enterpriseInGoodsVO.setEnterpriseId(enterprise.getId());
            enterpriseInGoodsVO.setParentId(enterprise.getParentId());
            enterpriseInGoodsVO.setChainType(enterprise.getChainType());
            //####################################################################################//
            //#                                安全库存表的添加
            //                                 价格清单表的添加                                 #//
            //                                 商品管理表的添加                                  #//
            //####################################################################################//
            addSafetyStockGoodsManage(entityFixedVO, goodsId, enterpriseInGoodsVO, userVO);

            //增加价格清单明细
            addPriceOrderDetail(enterpriseInGoodsVO.getEnterpriseId(),enterpriseInGoodsVO.getParentId(),goodsId,goodsVO.getGoodsBusinessVO(),entityFixedVO,splitSetSaveVO);

//            //企业自定义价格清单
//            List<PriceOrder> priceOrderList = priceOrderMapper.selectByCodeAndEnterpriceIdAndParentId2List(SysType.CUSTOMIZE.getCode(), enterprise.getId(), enterprise.getParentId());
//
//            if(CollectionUtils.isNotEmpty(priceOrderList)){
//                for (PriceOrder priceOrder : priceOrderList){
//                    getPriceOrderDetail(goodsId, entityFixedVO, goodsVO.getGoodsBusinessVO(), priceOrder);
//                }
//            }

        }
    }

    private void handleUpdateGoodsSafetyStockManagePriceOrder(UserVO userVO, SplitSetSaveVO splitSetSaveVO, GoodsVO goodsVO, Long goodsId) throws Exception {
        Integer chainType = userVO.getChainType();
        Long enterpriseId = userVO.getEnterpriseId();
        Long parentId = userVO.getParentId();
        PriceOrder item;
        //总部 + 加盟店
        if(chainType == ChainType.Headquarters.getCode() || chainType == ChainType.Division.getCode()){
            item = priceOrderMapper.selectByCodeAndEnterpriceIdAndParentId(SysType.SYSTEM.getCode(), enterpriseId, parentId);
            if(item == null) throw new BusinessException("价格清单查询失败");
        } else {
            throw new BusinessException("该账号没有权利修改");
        }
        GoodsBusinessVO businessVO = goodsVO.getGoodsBusinessVO();
        List<Long> priceDetailIdS = priceOrderMapper.getPriceDetailByParam(enterpriseId, item.getId(), goodsId);
        for(Long id : priceDetailIdS){
            PriceOrderDetail orderDetail = (PriceOrderDetail) EntityUtils.reflectUpdateSetDefaultValue(PriceOrderDetail.class, userVO);
            orderDetail.setId(id);

            BigDecimal retailPrice = businessVO.getRetailPrice();
            //零售单价
            orderDetail.setRetailPrice(retailPrice);
            //会员单价
            BigDecimal memberPrice = businessVO.getMemberPrice();
            orderDetail.setMemberPrice(memberPrice);
            //配货单价
            BigDecimal distrPrice = businessVO.getDistrPrice();
            orderDetail.setDistrPrice(distrPrice);

            //设置税率
            Long saleTaxRateId = businessVO.getSaleTaxRateId();
            GoodsTaxRate saleTaxRate = goodsTaxRateMapper.selectByPrimaryKey(saleTaxRateId);
            if(saleTaxRate != null){
                orderDetail.setSaleTaxRateId(saleTaxRate.getId());
                orderDetail.setSaleTaxRate(saleTaxRate.getTaxRate());
            } else {
                throw new BusinessException("销项税不存在");
            }
            Long distrTaxRateId = businessVO.getDistrTaxRateId();
            GoodsTaxRate distrTaxRate = goodsTaxRateMapper.selectByPrimaryKey(distrTaxRateId);
            if(distrTaxRate != null){
                orderDetail.setDistrTaxRateId(distrTaxRate.getId());
                orderDetail.setDistrTaxRate(distrTaxRate.getTaxRate());
            } else {
                throw new BusinessException("配货税率不存在");
            }
            //不含税零售单价
            if(retailPrice != null){
                orderDetail.setNotaxRetailPrice(retailPrice.divide(new BigDecimal(100).add(saleTaxRate.getTaxRate()).divide(new BigDecimal(100)), 6, BigDecimal.ROUND_HALF_UP));
            }
            //不含税会员单价
            if(memberPrice != null){
                orderDetail.setNotaxMemberPrice(memberPrice.divide(new BigDecimal(100).add(saleTaxRate.getTaxRate()).divide(new BigDecimal(100)), 6, BigDecimal.ROUND_HALF_UP));
            }
            //不含税配货单价
            if(distrPrice != null){
                orderDetail.setNotaxDistrPrice(distrPrice.divide(new BigDecimal(100).add(distrTaxRate.getTaxRate()).divide(new BigDecimal(100)), 6, BigDecimal.ROUND_HALF_UP));
            }
            //更新
            priceOrderDetailMapper.updateByPrimaryKeySelective(orderDetail);
        }
    }

    /**
     * 修改商品信息
     * @param userVO
     * @param goodsVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateGoods(UserVO userVO, GoodsVO goodsVO) throws Exception {


        EntityFixedInfoVO entityVo = new EntityFixedInfoVO();

        EntityFixedInfoVO entityFixedVO  = (EntityFixedInfoVO) EntityUtils.reflectAddSetDefaultValue(entityVo.getClass(),userVO);
        Long userId = userVO.getUserId();
        String userName = userVO.getUserName();
        Long goodsId = goodsVO.getId();

        Long enterpriseId;
        if(userVO.getChainType() == ChainType.Headquarters.getCode()){
            enterpriseId = userVO.getEnterpriseId();
        } else {
            enterpriseId = userVO.getParentId();
        }

        //查询原数据 做修改记录的对比
        Goods oldGoods = goodsMapper.selectByPrimaryKey(goodsId);
        //权限校验(加盟店不能更新总部数据)
        if (!oldGoods.getOwnerId().equals(userVO.getEnterpriseId())){
            throw new BusinessException(SysCode.FAIL.getCode(),"您没有修改权限");
        }

        Integer approveStatus = oldGoods.getApproveStatus();
        if(approveStatus == null){
            throw new BusinessException("审核状态不存在");
        }
        if(approveStatus == ApprovalFlowAuditStatus.DETAIL_STATUS_WAIT.getValue()){
            throw new BusinessException("待审核状态,不允许修改");
        }
        //xinhao
        //审核流逻辑处理start
        Integer status = ApprovalFlowAuditStatus.DETAIL_STATUS_PASS.getValue();// 默认状态为审核通过状态
        ManageConfig flow = manageConfigService.getManageConfig(userVO);
		if (flow.getApprovalControl() == 1) {// 如果审批流开启
			status = ApprovalFlowAuditStatus.DETAIL_STATUS_WAIT.getValue();// 待审核状态
		}
        //审核流逻辑处理end
        //生成厂商id
        Long manufacturerId = goodsVO.getManufacturerId();
        String manufactureName = goodsVO.getManufacturer();
        executeManufactStorage(goodsVO, entityFixedVO, userVO.getEnterpriseId(), manufacturerId, manufactureName);
        //####################################################################################//
        //#                                商品信息的添加,只维护总部
        //####################################################################################//
        //从goodsVO 中复制Goods的是值
        Goods goods = new Goods();
        BeanUtils.copyProperties(goodsVO,goods);
        Goods goods1 = (Goods) EntityUtils.reflectUpdateSetDefaultValue(goods.getClass(),userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goods,goods1);
        checkGoods(goodsVO, goods1);
        //检索码
        goods1.setPinyinCode(StringUtil.transferTrimStr(goodsVO.getPinyinCodeBegin()) + "-" +  StringUtil.transferTrimStr(goodsVO.getPinyinCodeEnd()));
        //特殊管理药品
        Integer inChargeDrug = goods1.getInChargeDrug();
        if(inChargeDrug == null || inChargeDrug == -1){
            goods1.setFormulationType(-1);
            goods1.setLimitQuantity(BigDecimal.ZERO);
        }


        GoodsBusiness goodsBusiness = new GoodsBusiness();
        //从goodsVO 中复制goodsBusiness的是值
        BeanUtils.copyProperties(goodsVO.getGoodsBusinessVO(),goodsBusiness);
        GoodsBusiness goodsBusiness1 = (GoodsBusiness) EntityUtils.reflectUpdateSetDefaultValue(goodsBusiness.getClass(),userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsBusiness,goodsBusiness1);
        goodsBusiness1.setGoodsId(goodsId);

        //设置税率
        Long purchaseTaxRateId = goodsBusiness1.getPurchaseTaxRateId();
        GoodsTaxRate purchaseTaxRate = goodsTaxRateMapper.selectByPrimaryKey(purchaseTaxRateId);
        if(purchaseTaxRate != null){
            goodsBusiness1.setPurchaseTaxRateId(purchaseTaxRate.getId());
            goodsBusiness1.setPurchaseTaxRate(purchaseTaxRate.getTaxRate());
        } else {
            throw new BusinessException("进项税不存在");
        }
        Long saleTaxRateId = goodsBusiness1.getSaleTaxRateId();
        GoodsTaxRate saleTaxRate = goodsTaxRateMapper.selectByPrimaryKey(saleTaxRateId);
        if(saleTaxRate != null){
            goodsBusiness1.setSaleTaxRateId(saleTaxRate.getId());
            goodsBusiness1.setSaleTaxRate(saleTaxRate.getTaxRate());
        } else {
            throw new BusinessException("销项税不存在");
        }
        Long distrTaxRateId = goodsBusiness1.getDistrTaxRateId();
        GoodsTaxRate distrTaxRate = goodsTaxRateMapper.selectByPrimaryKey(distrTaxRateId);
        if(distrTaxRate != null){
            goodsBusiness1.setDistrTaxRateId(distrTaxRate.getId());
            goodsBusiness1.setDistrTaxRate(distrTaxRate.getTaxRate());
        } else {
            throw new BusinessException("配货税率不存在");
        }
        GoodsBusiness oldGoodsBusiness = goodsBusinessMapper.getByGoodsId(goodsId);
        oldGoodsBusiness = oldGoodsBusiness == null ? new GoodsBusiness() : oldGoodsBusiness;


        //从goodsVO 中复制 goodsStorage 的是值
        GoodsStorage goodsStorage = new GoodsStorage();
        BeanUtils.copyProperties(goodsVO.getStorageMaintenanceVO(), goodsStorage);
        GoodsStorage goodsStorage1 = (GoodsStorage) EntityUtils.reflectAddSetDefaultValue(goodsStorage.getClass(),userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsStorage, goodsStorage1);
        goodsStorage1.setGoodsId(goodsId);
        GoodsStorage oldStorageMaintenance = goodsStorageMapper.getByGoodsId(goodsId);
        oldStorageMaintenance = oldStorageMaintenance == null ? new GoodsStorage() : oldStorageMaintenance;

        //从goodsVO 中复制 goodsInstructions 的是值
        GoodsInstructions goodsInstructions = new GoodsInstructions();
        BeanUtils.copyProperties(goodsVO.getGoodsInstructionsVO(),goodsInstructions);
        GoodsInstructions goodsInstructions1 = (GoodsInstructions) EntityUtils.reflectAddSetDefaultValue(goodsInstructions.getClass(),userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsInstructions,goodsInstructions1);
        goodsInstructions1.setGoodsId(goodsId);
        GoodsInstructions oldGoodsInstructions = goodsInstructionsMapper.getByGoodsId(goodsId);
        oldGoodsInstructions = oldGoodsInstructions == null ? new GoodsInstructions() : oldGoodsInstructions;


        List<GoodsModifyRecordWithBLOBs> goodsModifyRecordList =
                getGoodsModifyRecord(userVO,goodsVO,goods1,goodsBusiness1, goodsStorage1,goodsInstructions1,oldGoods,oldGoodsBusiness,oldStorageMaintenance,oldGoodsInstructions,new Date());

        //插入修改记录
        goodsModifyRecordList.forEach(item->{
            goodsModifyRecordMapper.insertSelective(item);
        });
        
        goods1.setApproveStatus(status);//设置商品的状态
        goodsMapper.updateByPrimaryKeySelective(goods1);
        goodsBusinessMapper.updateByGoodsId(goodsBusiness1);
        goodsStorageMapper.updateByGoodsId(goodsStorage1);
        goodsInstructionsMapper.updateByGoodsId(goodsInstructions1);

        //同步修改 specialGoods 的值
        SpecialGoods oldSpecialGoods = specialGoodsMapper.selectByGoodsId(goodsId);
        if (null != goodsVO.getInChargeDrug()){
            SpecialGoods specialGoods = this.setSpecialGoods(goods1, userVO);
            if (null == oldSpecialGoods){
                //新增
                UserEnterpriseUtils.setUserCreateOrModify(specialGoods, userVO, true);
                specialGoodsMapper.insertSelective(specialGoods);
            }else {
                //修改
                UserEnterpriseUtils.setUserCreateOrModify(specialGoods, userVO, false);
                specialGoods.setId(oldSpecialGoods.getId());
                specialGoodsMapper.updateByPrimaryKeySelective(specialGoods);
            }
        }else {
            //删除
            if (null != oldSpecialGoods){
                specialGoodsMapper.deleteByPrimaryKey(oldSpecialGoods.getId());
            }
        }

        List<Long> orgConfigIds = new ArrayList<>();
        List<GoodsQualificationConfigVO> configList = goodsQualificationConfigMapper.getQualificationConfigList(goodsId);
        configList.stream().forEach(item->{
            orgConfigIds.add(item.getQualificationConfigId());
        });

        List<Long> newQuConfigId = new ArrayList<>();
        //从goodsVO 中复制 goodsQualificationConfig 的是值
        List<GoodsQualificationConfigVO> qualificationConfigList  = goodsVO.getGoodsQualificationConfigVOList();
        qualificationConfigList.forEach(item->{
            GoodsQualificationConfig goodsQualificationConfig = null;
            try {
                goodsQualificationConfig = (GoodsQualificationConfig) EntityUtils.reflectUpdateSetDefaultValue(GoodsQualificationConfig.class,userVO);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException("构建商品资质失败");
            }
            BeanUtils.copyProperties(item,goodsQualificationConfig);
            GoodsQualificationConfig goodsQualificationConfig1 = null;
            try {
                goodsQualificationConfig1 = (GoodsQualificationConfig) EntityUtils.reflectUpdateSetDefaultValue(goodsQualificationConfig.getClass(),userVO);
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(goodsQualificationConfig,goodsQualificationConfig1);
            } catch (Exception e) {
                throw new RuntimeException();
            }
            goodsQualificationConfig1.setGoodsId(goodsId);
            goodsQualificationConfig1.setEnterpriseId(enterpriseId);
            //修改商品信息的时候，验证一下这个商品资质是否为新增加的商品资质，如果是，插入一条新记录
            GoodsQualificationConfigVO qualificationConfigVO = goodsQualificationConfigMapper.getGoodsQualificationConfigByGoodsIdQUID(goodsId,item.getQualificationId(),enterpriseId);
            if(qualificationConfigVO == null){
                //add
                goodsQualificationConfig1.setCreaterId(userVO.getUserId());
                goodsQualificationConfig1.setCreaterCode(userVO.getUserCode());
                goodsQualificationConfig1.setCreaterName(userVO.getUserName());
                goodsQualificationConfig1.setCreateTime(new Date());
                goodsQualificationConfigMapper.insertSelective(goodsQualificationConfig1);
            } else {
                try {
                    List<GoodsModifyRecordWithBLOBs> goodsQualificationModifyRecordList = getQualificationConfigModifyRecord(userVO,goodsVO,goodsQualificationConfig1,qualificationConfigVO,new Date());
                    //插入修改记录
                    goodsQualificationModifyRecordList.forEach(qualification->{
                        goodsModifyRecordMapper.insertSelective(qualification);
                    });
                } catch (Exception e) {
                  throw new RuntimeException();
                }
                //update
                goodsQualificationConfigMapper.updateByGoodsIdQUID(goodsQualificationConfig1);

            }

            newQuConfigId.add(item.getId());

        });



        //求出登记明细的货位明细的差集，删除已有登记明细中不存在的货位明细数据
        //取出差集，删除明细，并且删除明细对应的货位详情
        Set<Long> differenceSetShelf = Sets.difference(Sets.newHashSet(orgConfigIds), Sets.newHashSet(newQuConfigId));
        Iterator<Long> iteratorShelf = differenceSetShelf.iterator();
        while (iteratorShelf.hasNext()){
            Long next = iteratorShelf.next();
            goodsQualificationConfigMapper.deleteByPrimaryKey(next);
        }

        //同步更新总部基价价格清单，和基础价格清单为总部基价价格清单的价格清单

        handleUpdateGoodsSafetyStockManagePriceOrder(userVO,null,goodsVO,goodsId);
        //更新审批流状态
        //首营产品需要更改审批流的状态为待审核
        //只有正常的（审核通过并且启用）和 审核驳回的 可以修改，并且重新走审批流

        //审批流对象
        // 查询一条默认初始化的审批流


        Integer chainType = userVO.getChainType();
        SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(userVO.getEnterpriseId(),userVO.getEnterpriseName(),
                userId, userName, chainType, userVO.getParentId(),enterpriseId,"0201", goodsId, goods1.getCode(), goods1.getName());
        approvalFlowComponent.apply(submitApprovalFlowVO,userVO);

        return 0;
    }

    private void checkGoods(GoodsVO goodsVO, Goods goods1) {
        //剂型
        Long dosageId = goodsVO.getDosageId();
        GoodsDosage goodsDosage = dosageMapper.selectByPrimaryKey(dosageId);
        if(goodsDosage != null){
            goods1.setDosageName(goodsDosage.getName());
            goods1.setDosageId(dosageId);
        } else {
            throw new BusinessException("剂型不存在");
        }
        //单位
        Long unitId = goodsVO.getUnitId();
        GoodsUnit goodsUnit = unitMapper.selectByPrimaryKey(unitId);
        if(goodsUnit != null){
            goods1.setUnitName(goodsUnit.getName());
            goods1.setUnitId(unitId);
        } else {
            throw new BusinessException("单位不存在");
        }
        //经营范围
        Long managementScopeId = goodsVO.getManagementScopeId();
        if(managementScopeId != null){
            BusinessScope businessScope = businessScopeMapper.selectByPrimaryKey(managementScopeId);
            if(businessScope != null){
                goods1.setManagementScopeId(managementScopeId);
                goods1.setManagementScopeName(businessScope.getName());
            } else {
                throw new BusinessException("经营范围不存在");
            }
        }

        //验收分类
        Long checkTypeId = goodsVO.getCheckTypeId();
        QualitySet qualitySet = qualitySetMapper.selectByPrimaryKey(checkTypeId);
        if(qualitySet != null){
            goods1.setCheckTypeId(checkTypeId);
            goods1.setCheckTypeName(qualitySet.getDescription());
        } else {
            throw new BusinessException("验收分类不存在");
        }
    }

    private void executeManufactStorage(GoodsVO goodsVO, EntityFixedInfoVO entityFixedVO, Long enterpriseId, Long manufacturerId, String manufactureName) {
        if(manufacturerId == null && StringUtils.isNotBlank(manufactureName)){
            //如果厂商id为空，manafactureName不为空，则往生成企业表中插入一条数据
            //先判断 manafactureName在表里面有没有记录,有的话 直接返回该记录的id
            Manufacturer manufacturer = manufacturerMapper.selectByName(enterpriseId,manufactureName);
            if(manufacturer == null){
                manufacturer = new Manufacturer();
                manufacturer.setName(manufactureName);
                manufacturer.setEnterpriseId(enterpriseId);
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(entityFixedVO,manufacturer);
                manufacturerMapper.insertSelective(manufacturer);
            }
            goodsVO.setManufacturer(manufactureName);
            goodsVO.setManufacturerId(manufacturer.getId());
        }
    }


    /**
     * 商品基表，商品业务信息，商品存储信息，商品说明书 修改记录
     * @param userVO
     * @param goodsVO
     * @param newGoods
     * @param newGoodsBusiness
     * @param newGoodsStorage
     * @param newGoodsInstructions
     * @param goods
     * @param goodsBusiness
     * @param goodsStorage
     * @param goodsInstructions
     * @param updateDate
     * @return
     * @throws Exception
     */
    public List<GoodsModifyRecordWithBLOBs> getGoodsModifyRecord(UserVO userVO, GoodsVO goodsVO,
                                                                 Goods newGoods, GoodsBusiness newGoodsBusiness, GoodsStorage newGoodsStorage, GoodsInstructions newGoodsInstructions
            , Goods goods, GoodsBusiness goodsBusiness, GoodsStorage goodsStorage, GoodsInstructions goodsInstructions, Date updateDate) throws Exception {

        Map<String,Object> newGoodsMap = modifyRecordCompoent.getFieldsMap(newGoods);
        Map<String,Object> newGoodsBusinessMap = modifyRecordCompoent.getFieldsMap(newGoodsBusiness);
        Map<String,Object> newGoodsStorageMaintenanceMap = modifyRecordCompoent.getFieldsMap(newGoodsStorage);
        Map<String,Object> newGoodsInstructionsMap = modifyRecordCompoent.getFieldsMap(newGoodsInstructions);


        Map<String,Object> goodsMap = modifyRecordCompoent.getFieldsMap(goods);
        Map<String,Object> goodsBusinessMap = modifyRecordCompoent.getFieldsMap(goodsBusiness);
        Map<String,Object> goodsStorageMaintenanceMap = modifyRecordCompoent.getFieldsMap(goodsStorage);
        Map<String,Object> goodsInstructionsMap = modifyRecordCompoent.getFieldsMap(goodsInstructions);

        Map<String,String> fieldMustMap = fieldMustMap();

        List<GoodsModifyRecordWithBLOBs> goodsRecord = getModifyRecordList(userVO,goodsVO,"saas_goods",goodsVO.getId()
                ,updateDate,goodsMap,newGoodsMap,fieldMustMap);

        List<GoodsModifyRecordWithBLOBs> goodsBusinessRecord = getModifyRecordList(userVO,goodsVO,"saas_goods_business",goodsVO.getId()
                ,updateDate,goodsBusinessMap,newGoodsBusinessMap,fieldMustMap);

        List<GoodsModifyRecordWithBLOBs> storageMaintenanceRecord = getModifyRecordList(userVO,goodsVO,"saas_goods_storage",goodsVO.getId()
                ,updateDate,goodsStorageMaintenanceMap,newGoodsStorageMaintenanceMap,fieldMustMap);

        List<GoodsModifyRecordWithBLOBs> goodsInstructionsRecord = getModifyRecordList(userVO,goodsVO,"saas_goods_instructions",goodsVO.getId()
                ,updateDate,goodsInstructionsMap,newGoodsInstructionsMap,fieldMustMap);

        goodsRecord.addAll(goodsBusinessRecord);
        goodsRecord.addAll(storageMaintenanceRecord);
        goodsRecord.addAll(goodsInstructionsRecord);
        return goodsRecord;
    }


    public List<GoodsModifyRecordWithBLOBs> getQualificationConfigModifyRecord(UserVO userVO,GoodsVO goodsVO,
                                                               GoodsQualificationConfig newGoodsQualificationConfig,
                    GoodsQualificationConfigVO goodsQualificationConfig,Date updateDate) throws Exception {

        Map<String,Object> newGoodsQualificationConfigMap = modifyRecordCompoent.getFieldsMap(newGoodsQualificationConfig);


        Map<String,Object> goodsQualificationConfigMap = modifyRecordCompoent.getFieldsMap(goodsQualificationConfig);

        Map<String,String> fieldMustMap = qualificationConfigFieldMustMap();

        List<GoodsModifyRecordWithBLOBs> goodsRecord = getModifyRecordList(userVO,goodsVO,"saas_goods_qualification_config",goodsVO.getId()
                ,updateDate,goodsQualificationConfigMap,newGoodsQualificationConfigMap,fieldMustMap);
        return goodsRecord;
    }

    private List<GoodsModifyRecordWithBLOBs> getModifyRecordList(
            UserVO userVO ,GoodsVO goodsVO,String tableName,Long keyId,Date updateTime
            , Map<String,Object> valMap
            ,Map<String,Object> newMap ,Map<String,String> fieldMustMap) throws Exception {

        List<GoodsModifyRecordWithBLOBs> modifyRecordWithBLOBs = new ArrayList<>();

        // 商品属性特殊处理
        Integer oldBusinessVariety = (Integer)valMap.get("businessVariety");
        Integer newBusinessVariety = (Integer)newMap.get("businessVariety");
        Integer oldGoodsAttr = (Integer)valMap.get("goodsAttribute");
        Integer newGoodsAttr = (Integer)newMap.get("goodsAttribute");
        Integer oldPredrug  =  (Integer)valMap.get("prescriptionDrug");
        Integer oldOTCType  =  (Integer)valMap.get("otcType");

        Integer newPredrug  =  (Integer)newMap.get("prescriptionDrug");
        Integer newOTCType  =  (Integer)newMap.get("otcType");

//        Integer oldDistrFlag = (Integer)valMap.get("distrFlag");
//        Integer newDistrFlag = (Integer)newMap.get("distrFlag");


        if(oldBusinessVariety != null && newBusinessVariety != null){
            String oldAttr = getGoodsAttr(oldBusinessVariety,oldGoodsAttr,oldPredrug,oldOTCType);
            String newAttr = getGoodsAttr(newBusinessVariety,newGoodsAttr,newPredrug,newOTCType);

            if(!oldAttr.equals(newAttr)){
                Map<String,String> fieldNames = new HashMap<>();
                fieldNames.put("goodsAttribute","商品属性");

                for(Map.Entry<String,String> entry : fieldNames.entrySet()){
                    excuteGoodsModifyRecord(userVO, goodsVO, tableName, updateTime, modifyRecordWithBLOBs,entry , oldAttr, newAttr);
                }
            }
        }


        for(Map.Entry<String,String> entry : fieldMustMap.entrySet()){
            Object obj = valMap.get(entry.getKey());
            Object newObj = newMap.get(entry.getKey());
            if(obj != null ){
                if(obj instanceof BigDecimal && newObj instanceof BigDecimal && ((BigDecimal) obj).compareTo((BigDecimal) newObj) == 0){
                    continue;
                } else if (obj instanceof Date && newObj instanceof Date){// 时间格式化
                    if(!obj.equals(newObj)){
                        excuteGoodsModifyRecord(userVO, goodsVO, tableName, updateTime, modifyRecordWithBLOBs, entry,
                                DateUtils.DateToString((Date) obj,DateUtils.FMT_DATE), DateUtils.DateToString((Date) newObj,DateUtils.FMT_DATE));
                    }
                }
                else if(!obj.equals(newObj)){
                    // 商品属性上边特殊处理，这里不再处理
                    if("goodsAttribute".equals(entry.getKey()) || "applicantId".equals(entry.getKey())){
                        continue;
                    }

                    // 反射枚举的具体值
                    if(enumClassMap().containsKey(entry.getKey())){
                        Class aClass = enumClassMap().get(entry.getKey());
                        Object oldValue = reflectionEnumValue(aClass,obj);
                        Object newValue = reflectionEnumValue(aClass,newObj);
                        excuteGoodsModifyRecord(userVO, goodsVO, tableName, updateTime, modifyRecordWithBLOBs, entry, oldValue, newValue);
                        continue;
                    }

                    // 只能sql查询
                    if("categoryId".equals(entry.getKey())){// 商品分类
                        GoodsCategory oldGoodsCategory = categoryMapper.selectByPrimaryKey((Long) obj);
                        GoodsCategory newGoodsCategory = categoryMapper.selectByPrimaryKey((Long) newObj);
                        excuteGoodsModifyRecord(userVO, goodsVO, tableName, updateTime, modifyRecordWithBLOBs, entry, oldGoodsCategory.getName(), newGoodsCategory.getName());

                        continue;
                    }

                    if("maintenanceMeasureIds".equals(entry.getKey())){// 养护措施

                        String[] oldIds = ((String) obj).split(",");
                        String[] newIds = ((String) newObj).split(",");
                        String oldMainMea = "";
                        for (String id : oldIds) {
                            oldMainMea  =  oldMainMea +  qualitySetMapper.selectByPrimaryKey(Long.parseLong(id)).getDescription() + ",";
                        }

                        String newMainMea = "";
                        for (String id : newIds) {
                            newMainMea  =  newMainMea +  qualitySetMapper.selectByPrimaryKey(Long.parseLong(id)).getDescription() + ",";
                        }

                        excuteGoodsModifyRecord(userVO, goodsVO, tableName, updateTime, modifyRecordWithBLOBs, entry, oldMainMea, newMainMea);
                        continue;
                    }

                    excuteGoodsModifyRecord(userVO, goodsVO, tableName, updateTime, modifyRecordWithBLOBs, entry, obj, newObj);


                }

            }
            if(obj == null && newObj != null){
                excuteGoodsModifyRecord(userVO, goodsVO, tableName, updateTime, modifyRecordWithBLOBs, entry, obj, newObj);
            }
        }

        return modifyRecordWithBLOBs;
    }

    /**
    * @Description:反射获取枚举 具体值
     * 约定条件: 枚举中有getName 和 getCode 方法
    * @return:
    * @Author: dongyang.du
    * @Date: 31/01/2018
    */
    private Object reflectionEnumValue(Class aClass,Object o) {

        try {
            Method getName = aClass.getMethod("getName");
            Method getCode = aClass.getMethod("getCode");

            Object[] objs = aClass.getEnumConstants();
            for (Object obj : objs) {
                Object code = getCode.invoke(obj);

                if(code.equals(o)){
                    return getName.invoke(obj);
                }
            }
        } catch (Exception e) {
           logger.error(e.getMessage());
        }

        return  null;
    }

    private void excuteGoodsModifyRecord(UserVO userVO, GoodsVO goodsVO, String tableName, Date updateTime, List<GoodsModifyRecordWithBLOBs> modifyRecordWithBLOBs, Map.Entry<String, String> entry, Object obj, Object newObj) {
        GoodsModifyRecordWithBLOBs goodsModify = new GoodsModifyRecordWithBLOBs();
        goodsModify.setGoodsId(goodsVO.getId());
        goodsModify.setEnterpriseId(userVO.getEnterpriseId());
        goodsModify.setParentId(userVO.getParentId());
        goodsModify.setTableName(tableName);
        goodsModify.setColumnEnName(entry.getKey());
        goodsModify.setColumnChName(entry.getValue());
        goodsModify.setUpdateTime(updateTime);
        goodsModify.setModifierId(userVO.getUserId());
        goodsModify.setModifierCode(userVO.getUserCode());
        goodsModify.setModifierName(userVO.getUserName());
        goodsModify.setCreaterId(userVO.getUserId());
        goodsModify.setCreaterCode(userVO.getUserCode());
        goodsModify.setCreaterName(userVO.getUserName());
        goodsModify.setCreateTime(new Date());

        goodsModify.setOldContent(obj == null ? null : obj.toString());
        goodsModify.setNewContent(newObj == null ? null : newObj.toString());
        goodsModify.setReason(goodsVO.getUpdateReason());
        modifyRecordWithBLOBs.add(goodsModify);
    }


    /**
     * 根据商品id 查询商品资质配置
     * @param goodsId
     * @return
     */
    @Override
    public List<GoodsQualificationConfigVO> getQualificationConfigList(Long goodsId) {
        return goodsQualificationConfigMapper.getQualificationConfigList(goodsId);
    }


    /**
     * 查询生产企业列表
     * @param enterpriseId
     * @return
     */
    @Override
    public List<ResponseManufacturerVO> getManufacturer(Long enterpriseId) {
        return manufacturerMapper.getManufacturer(enterpriseId);
    }

    @Override
    public List<ResponseStorageConditionVO> getStorageCondition(Long enterpriseId) {
        return null;
    }

    @Override
    public List<BusinessScopeVO> getBusinessScopeVOList(Integer businessVariety, Integer status, Long enterpriseId) {
        if(-1 == businessVariety){// -1 代表查询全部
            businessVariety = null;
        }
        return businessScopeMapper.getBusinessScopeVOList(businessVariety,status,enterpriseId);
    }

    @Override
    public List<QualitySetVO> getQualitySetVOList(@Param("enterpriseId") Long enterpriseId, @Param("setType") Integer setType, @Param("type") Integer type, @Param("status") Integer status) {
        List<QualitySetVO> qualitySetVOList = qualitySetMapper.getQualitySetVOList(enterpriseId, setType, type, status);
        return qualitySetVOList.stream().filter(a -> !"全部".equals(a.getDescription())).collect(Collectors.toList());

    }

//    @Deprecated
//    @Override
//    public GoodsVO getGoodsInfoById(Long id) {
//        return goodsMapper.getGoodsInfoById(id);
//    }


    @Override
    public ResponseGoodsExcelVO excelImport(HttpServletRequest request,boolean update) throws Exception {

        GoodsInfoIRowReader goodsInfoIRowReader = SpringBeanFactory.getBean(GoodsInfoIRowReader.class);
        goodsInfoIRowReader.setUpdate(update);
        HttpSession session = request.getSession(true);
        UserVO userVO = (UserVO) session.getAttribute("user");

        Long enterpriseId = userVO.getEnterpriseId();
        Part part = request.getPart("file");
        InputStream input = part.getInputStream();
        //合格产品
        List<GoodsExcelVO> qualifiedList = new ArrayList<>();
        //不合格产品
        List<GoodsExcelVO> disqualificationList = new ArrayList<>();

        ManageConfig manageConfig = manageConfigComponent.getMangeConfigByEPId(userVO);
        //
        List<UserDataVO> userDataVOList = userMapper.getUserDataByEId(enterpriseId);
        goodsInfoIRowReader.setUserDataVOList(userDataVOList);
        goodsInfoIRowReader.setUserVO(userVO);
        goodsInfoIRowReader.setGoodsCodeRule(manageConfig.getGoodsCodeRule());

        //标准入库实体list
        List<GoodsVO> qualifiedGVOList = new ArrayList<>();
        goodsInfoIRowReader.setQualifiedList(qualifiedList);
        goodsInfoIRowReader.setDisqualificationList(disqualificationList);
        goodsInfoIRowReader.setQualifiedGVOList(qualifiedGVOList);


        //查询所有的最后一级的商品分类
//        Map<String,List<GoodsCategory>> categoryMap = new HashMap<>();
//        for (BusinessVariety c : BusinessVariety.values()) {
//            categoryMap.put(c.getName(),categoryMapper.getAllChildrenCategory(enterpriseId,EnableStatus.ENABLE.getStatus(),c.getCode()));
//        }
        List<GoodsCategory> allChildrenCategory = categoryMapper.getAllChildrenCategory(ChainType.getHeadEnterpriseId(userVO), EnableStatus.ENABLE.getStatus(),null);
        goodsInfoIRowReader.setCategoryList(allChildrenCategory);
        //剂型list
        List<GoodsDosageVO> dosageList = dosageMapper.selectAll(ChainType.getHeadEnterpriseId(userVO), null);
        goodsInfoIRowReader.setDosageList(dosageList);
        //unitList 单位list
        List<GoodsUnitVO> unitVOList = unitMapper.selectAll(ChainType.getHeadEnterpriseId(userVO), null);
        goodsInfoIRowReader.setUnitList(unitVOList);
        //经营范围(key---品种类别)
        Map<String,List<BusinessScopeVO>> businessScopeMap = new HashMap<>();
        for (BusinessVariety c : BusinessVariety.values()) {
            businessScopeMap.put(c.getName(),businessScopeMapper.getBusinessScopeVOList(c.getCode(),EnableStatus.ENABLE.getStatus(),ChainType.getHeadEnterpriseId(userVO)));
        }
        goodsInfoIRowReader.setBusinessScopeMap(businessScopeMap);
        //验收分类（key---品种类别）
        Map<String,List<QualitySetVO>> qualitySetMap = new HashMap<>();
        for (BusinessVariety c : BusinessVariety.values()) {
            qualitySetMap.put(c.getName(),qualitySetMapper.getQualitySetVOList(ChainType.getHeadEnterpriseId(userVO),QualitySetType.CHECK_TYPE.getSetType(),c.getCode(),EnableStatus.ENABLE.getStatus()));
        }

        goodsInfoIRowReader.setQualitySetMap(qualitySetMap);

        //税收
        List<GoodsTaxRateVO> goodsTaxRateVOs = goodsTaxRateMapper.selectAllVO(ChainType.getHeadEnterpriseId(userVO), EnableStatus.ENABLE.getStatus());
        goodsInfoIRowReader.setTaxRateVOList(goodsTaxRateVOs);

        ExcelReaderUtil.excelToArrayList(goodsInfoIRowReader, FileUtils.getFileName(part), input, 63, 0);
        ResponseGoodsExcelVO goodsExcelVO = new ResponseGoodsExcelVO();
        goodsExcelVO.setQualifiedCount(qualifiedList.size());
        goodsExcelVO.setDisqualificationCount(disqualificationList.size());

        //goodsExcelVO.setQualifiedGoodsList(goodsInfoIRowReader.getQualifiedList());
        //goodsExcelVO.setDisqualificationGoodsList(goodsInfoIRowReader.getDisqualificationList());
        //向redis中存储合格/不合格的商品
        Long timestamp = System.currentTimeMillis();
        List<GoodsExcelVO> qualifiedList2Redis = goodsInfoIRowReader.getQualifiedList();
        List<GoodsExcelVO> disQualifiedList2Redis = goodsInfoIRowReader.getDisqualificationList();
        List<GoodsVO> qualifiedGVOList2Redis = goodsInfoIRowReader.getQualifiedGVOList();

        redisComponent.set(GoodsInfoConstant.QUALIFIED_FIELD + timestamp, JSON.toJSONString(qualifiedList2Redis));
        redisComponent.set(GoodsInfoConstant.DISQUALIFIED_FIELD + timestamp,JSON.toJSONString(disQualifiedList2Redis));
        redisComponent.set(GoodsInfoConstant.STANDARD_QUALIFIED_FIELD + timestamp,JSON.toJSONString(qualifiedGVOList2Redis));

        logger.error("商品信息导入保存到Redis的key,成功数据={},失败的数据={},成功标准数据={}",GoodsInfoConstant.QUALIFIED_FIELD + timestamp,GoodsInfoConstant.DISQUALIFIED_FIELD + timestamp,GoodsInfoConstant.STANDARD_QUALIFIED_FIELD + timestamp);
        goodsInfoIRowReader.getCodeList().clear();
        goodsExcelVO.setTimestamp(timestamp.toString());
        return goodsExcelVO;
    }


//    volatile int i = 0;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importSuccessGoods(UserVO userVO,String key) throws Exception {
        List<GoodsVO> list = JSON.parseArray((String) redisComponent.get(GoodsInfoConstant.STANDARD_QUALIFIED_FIELD + key),GoodsVO.class);
//        Runtime runtime = Runtime.getRuntime();
//        //JVM可以使用的处理器个数
//        int processors = runtime.availableProcessors();
//        logger.info("JVM可以使用的处理器个数 processors = " + processors);
//        ExecutorService executorService = Executors.newFixedThreadPool(processors - 1,new HandlerThreadFactory());
//
//        long start = new Date().getTime();
//        //            try {
//        //            } catch (Exception e) {
//        //                e.printStackTrace();
//        //                throw new RuntimeException("导入异常");
//        //
//        // //                    try {          }
//
//        try {
//        for (GoodsVO item : list) {
//            executorService.execute(() -> {
////                                i++;
////                            System.out.println(i + "++++++++++++++++++++++++++线程" + Thread.currentThread().getName() + " 开始导入" + item.getCode() + "++++++++++++++++++++++");
//                item.getGoodsBusinessVO().setCommissionGoods(1);
//                item.getGoodsBusinessVO().setMemberPrice(BigDecimal.ZERO);
//                try {
//                    addGoods(userVO, item, true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    throw new RuntimeException(e);
//                }
////
//
//            });
//        }
//        } catch (Exception e) {
//            System.out.println("--------------------$$$$$---------------");
//           throw new RuntimeException(e);
//        }
        logger.error("开始导入成功商品数据");
        list.forEach(item->{
            try {
                item.getGoodsBusinessVO().setCommissionGoods(1);
                item.getGoodsBusinessVO().setMemberPrice(BigDecimal.ZERO);
                addGoods(userVO,item,true);
            } catch (DuplicateKeyException du){
                logger.error("导入商品编码重复" + item.getCode() );
                throw new RuntimeException("商品编码重复");
            } catch (Exception e) {
                logger.error("商品导入异常" + e.getMessage());
                throw new RuntimeException("导入异常");
            }
        });

        logger.error("结束导入成功商品数据");


    }


    /**
     * 导出成功数据
     *
     * @param output
     * @param key
     * @param type 1--导出成功数据；2--导出失败数据
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void exportGoods(OutputStream output, String key,Integer type) throws Exception {
        List<GoodsExcelVO> list = new ArrayList<>();
        if(type == 1){
             list = JSON.parseArray((String) redisComponent.get(GoodsInfoConstant.QUALIFIED_FIELD + key),GoodsExcelVO.class);
        } else {
             list = JSON.parseArray((String) redisComponent.get(GoodsInfoConstant.DISQUALIFIED_FIELD + key),GoodsExcelVO.class);
        }
        createExcel(output, list,1);
    }

    /**
     * 商品信息首页查询导出excel
     */
    @Override
    public void exportGoodsInfo(OutputStream outputStream, RequestGoodsVO requestGoodsVO, UserVO userVO){

        RequestGoodsVO2 requestGoodsVO2 = new RequestGoodsVO2();
        BeanUtils.copyProperties(requestGoodsVO,requestGoodsVO2);
        requestGoodsVO2.setStart(null);
        requestGoodsVO2.setPageSize(null);
        ParamUtils.packParam(userVO,requestGoodsVO2);
        List<GoodsVO> list = goodsMapper.getGoodsListByParam(requestGoodsVO2);

        //添加未绑定商品信息的必填的商品资质
        list.parallelStream().forEach(item->{
            GoodsCategory goodsCategory = categoryMapper.selectByPrimaryKey(item.getCategoryId());
            item.setCategoryName(goodsCategory.getName());
        });
        List<GoodsExcelVO> excelVOList = packListForExportExcel(list);
        createExcel(outputStream, excelVOList,0);
    }
    @Override
    public Page<List<ResponseGoodsModifyRecordVO>> getGoodsModifyRecord(Long enterpriseId, Long parentId, Page<List<ResponseGoodsModifyRecordVO>> page, Long goodsId) {
        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        page.setResult(goodsModifyRecordMapper.getGoodsModifyRecordPage(enterpriseId,parentId,goodsId));
        page.setTotalRecord((int) objects.getTotal());
        return page;
    }

    /**
     * 根据条件查询与商品未关联的商品资质
     *
     * @param enterpriseId
     * @param goodsId
     * @param typeMust
     * @param status
     * @return
     */
    @Override
    public List<GoodsQualificationConfigVO> getQualificationNotRelateGoods(Long enterpriseId, Long goodsId, Integer typeMust, Integer status,Long checkTypeId) {
        return goodsQualificationMapper.getQualificationNotRelateGoods(enterpriseId,goodsId,typeMust,status,checkTypeId);
    }
    private void createExcel(OutputStream output, List<GoodsExcelVO> list,Integer type) {
        try {
            final List<GoodsExcelVO> finalList = list;
            ExcelWriter writer = new ExcelWriter() {
                public void generate() throws Exception {
                    // 电子表格开始
                    this.beginWorkSheet();
                    this.beginSheet();
                    int tmp = 1;
                    createRowHeader(this);
                    if(type == 1){
                        tmp = 2;
                        createFirstRow(this);
                    }
                    Map<String, XSSFCellStyle> styleMap = this.getCellStyles();
                    short cellStrIndex = styleMap.get("cell_string").getIndex();
                    for (int rowNum = 0; rowNum < finalList.size(); rowNum++) {
                        GoodsExcelVO goodsVO = finalList.get(rowNum);
                        // 插入新行
                        this.insertRow(rowNum + tmp);
                        // 建立新单元格,索引值从0开始,表示第一列
                        int k = 0;
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getBusinessVariety()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getCategory()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getCode()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getBarcode()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getGenericName()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getName()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getGoodsAttribute()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getOldCode()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getNationalStandardCode()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getDosageName()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getSpecification()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getUnitName()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getManufacturer()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getPlace()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getDomesticImport()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getApprovalNumber()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getValidUntil()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getManagementScopeName()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getCheckTypeName()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getQualityPeriod()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getQualityPeriodUnit()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getSpecialDrug()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getInChargeDrug()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getLimitQuantity()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getMedicalInsurance()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getMedicalInsuranceType()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getMedicalInsuranceCode()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getRegisteredTrademark()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getBrand()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getSeason()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getGrade()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getInbagQuantity()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getBigbagQuantity()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getGoodsNature()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getRetailPrice()),cellStrIndex);
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getDistrPrice()),cellStrIndex);
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getConfigurationFlag()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getDistrFlag()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getStatus()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getValidFlag()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getRemark()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getPurchaseTaxRate()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getManagementMode()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getFirst()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getApplicantName()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getApplicationTime()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getApplicationOpinion()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getSaleTaxRate()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getDistrTaxRate()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getBargainGoods()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getIntegralGoods()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getIntegralMultiple()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getPricingPolicy()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getDeliveryMode()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getMinimumPurchasingBatch()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getMinimumDistributionBatch()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getStorageTemp()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getNearEffectPeriod()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getNearEffectPeriodUnit()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getUnsalableCycle()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getUnsalableCycleUnit()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getMaintenanceType()));
                        this.createCell(k++, StringUtil.transferTrimStr(goodsVO.getMaintenanceCycle()),cellStrIndex);
                        this.createCell(k, StringUtil.transferTrimStr(goodsVO.getMaintenanceCycleUnit()));
                        // 结束行
                        this.endRow();
                    }
                    // 电子表格结束
                    this.endSheet();
                    this.endWorkSheet();
                }
            };
            writer.process(output);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    private void createRowHeader(ExcelWriter writer) throws IOException {
        // 插入新行
        writer.insertRow(0);
        // 建立新单元格,索引值从0开始,表示第一列
        int k = 0;
        writer.createCell(k++, "品种类别");
        writer.createCell(k++, "商品分类");
        writer.createCell(k++, "商品编码");
        writer.createCell(k++, "条形码");
        writer.createCell(k++, "通用名称");
        writer.createCell(k++, "商品名称");
        writer.createCell(k++, "商品属性");
        writer.createCell(k++, "原商品编码");
        writer.createCell(k++, "国家本位码");
        writer.createCell(k++, "剂型");
        writer.createCell(k++, "规格");
        writer.createCell(k++, "库存计量单位");
        writer.createCell(k++, "生产厂商");
        writer.createCell(k++, "产地");
        writer.createCell(k++, "国产/进口");
        writer.createCell(k++, "批准文号");
        writer.createCell(k++, "有效期至");
        writer.createCell(k++, "经营范围");
        writer.createCell(k++, "验收分类");
        writer.createCell(k++, "保质期");
        writer.createCell(k++, "保质期类型");
        writer.createCell(k++, "特殊管理药品");
        writer.createCell(k++, "专门药品");
        writer.createCell(k++, "限购数量");
        writer.createCell(k++, "医保药品");
        writer.createCell(k++, "医保类别");
        writer.createCell(k++, "医保编号");
        writer.createCell(k++, "注册商标");
        writer.createCell(k++, "品牌");
        writer.createCell(k++, "季节");
        writer.createCell(k++, "等级");
        writer.createCell(k++, "中包数量");
        writer.createCell(k++, "大包数量");
        writer.createCell(k++, "商品性质");
        writer.createCell(k++, "零售基价");
        writer.createCell(k++, "配货基价");
        writer.createCell(k++, "配置标识");
        writer.createCell(k++, "配货标识");
        writer.createCell(k++, "状态");
        writer.createCell(k++, "标记");
        writer.createCell(k++, "备注");
        writer.createCell(k++, "进项税%");
        writer.createCell(k++, "经营方式");
        writer.createCell(k++, "首营品种");
        writer.createCell(k++, "申请人员");
        writer.createCell(k++, "申请日期");
        writer.createCell(k++, "申请意见");
        writer.createCell(k++, "销项税%");
        writer.createCell(k++, "配货税率%");
        writer.createCell(k++, "特价商品");
        writer.createCell(k++, "积分商品");
        writer.createCell(k++, "积分倍数%");
        writer.createCell(k++, "销售定价");
        writer.createCell(k++, "配送方式");
        writer.createCell(k++, "最小采购批量");
        writer.createCell(k++, "最小配送批量");
        writer.createCell(k++, "储藏温度");
        writer.createCell(k++, "近效期周期");
        writer.createCell(k++, "周期类型");
        writer.createCell(k++, "滞销周期");
        writer.createCell(k++, "周期类型");
        writer.createCell(k++, "养护类型");
        writer.createCell(k++, "养护周期");
        writer.createCell(k, "周期类型");
        // 结束行
        writer.endRow();
    }
    private void createFirstRow(ExcelWriter writer) throws IOException {
        // 插入新行
        writer.insertRow(1);
        // 建立新单元格,索引值从0开始,表示第一列
        int k = 0;
        writer.createCell(k++, "药品");
        writer.createCell(k++, "处方药");
        writer.createCell(k++, "01010001");
        writer.createCell(k++, "6959695848384");
        writer.createCell(k++, "阿莫西林胶囊");
        writer.createCell(k++, "阿莫西林胶囊");
        writer.createCell(k++, "成药-非处方药-甲类");
        writer.createCell(k++, "原商品编码");
        writer.createCell(k++, "01010001");
        writer.createCell(k++, "片剂");
        writer.createCell(k++, "0.25g*10粒*2板/盒");
        writer.createCell(k++, "盒");
        writer.createCell(k++, "吉林显锋科技制药有限公司");
        writer.createCell(k++, "吉林显锋科技制药有限公司");
        writer.createCell(k++, "国产");
        writer.createCell(k++, "国药准字H20073235");
        writer.createCell(k++, "2019-12-31");
        writer.createCell(k++, "中成药");
        writer.createCell(k++, "常规成药");
        writer.createCell(k++, "36");
        writer.createCell(k++, "日");
        writer.createCell(k++, "");
        writer.createCell(k++, "肽类激素");
        writer.createCell(k++, "");
        writer.createCell(k++, "是");
        writer.createCell(k++, "国家医保");
        writer.createCell(k++, "");
        writer.createCell(k++, "显锋");
        writer.createCell(k++, "显锋");
        writer.createCell(k++, "全季");
        writer.createCell(k++, "A");
        writer.createCell(k++, "1");
        writer.createCell(k++, "1");
        writer.createCell(k++, "组装商品");
        writer.createCell(k++, "26");
        writer.createCell(k++, "36");
        writer.createCell(k++, "必备");
        writer.createCell(k++, "普通");
        writer.createCell(k++, "启用");
        writer.createCell(k++, "正常");
        writer.createCell(k++, " ");
        writer.createCell(k++, "0%");
        writer.createCell(k++, "购销");
        writer.createCell(k++, "否");
        writer.createCell(k++, "张三");
        writer.createCell(k++, "2015-01-02");
        writer.createCell(k++, "申请意见");
        writer.createCell(k++, "10.36%");
        writer.createCell(k++, "0%");
        writer.createCell(k++, "否");
        writer.createCell(k++, "是");
        writer.createCell(k++, "100%");
        writer.createCell(k++, "总部定价");
        writer.createCell(k++, "供货单位配送");
        writer.createCell(k++, "1");
        writer.createCell(k++, "1");
        writer.createCell(k++, "阴凉");
        writer.createCell(k++, "180");
        writer.createCell(k++, "天");
        writer.createCell(k++, "90");
        writer.createCell(k++, "天");
        writer.createCell(k++, "重点养护");
        writer.createCell(k++, "30");
        writer.createCell(k, "天");
        // 结束行
        writer.endRow();
    }


    private void addPriceOrderDetail(Long enterpriseId,Long parentId,Long goodsId,GoodsBusinessVO businessVO,EntityFixedInfoVO entityFixedVO,SplitSetSaveVO splitSetSaveVO) {

        List<PriceOrder> priceOrderList =  priceOrderMapper.selectByCodeAndEnterpriceIdAndParentId2List(null, enterpriseId, parentId);
        if (priceOrderList == null) {
            throw new BusinessException("查询企业价格清单数据异常！");
        }
        for(PriceOrder item : priceOrderList){
            if(splitSetSaveVO == null){
                PriceOrderDetail orderDetail = getPriceOrderDetail(goodsId, entityFixedVO, businessVO, item);
            } else {
                //拆零设置
                Long priceOrdeId = item.getId();
                PriceOrderDetail priceOrderDetail = priceOrderDetailMapper.selectByEnterpriseIdAndPriceOrderIdAndGoodId(enterpriseId, priceOrdeId, splitSetSaveVO.getGoodsId());
                if(priceOrderDetail == null){
//                    logger.error("商品 [" + splitSetSaveVO.getGoodsId() + "] 在企业[" + enterpriseId + "] 中对应的价格清单[" + priceOrdeId +"]不存在");
//                    throw new BusinessException("商品 [" + splitSetSaveVO.getGoodsId() + "] 在企业[" + enterpriseId + "] 中对应的价格清单[" + priceOrdeId +"]不存在");
                } else {
                    getPriceOrderDetail(goodsId,entityFixedVO,priceOrderDetail,splitSetSaveVO.getSplitQuantity());
                }

            }
        }

    }

    private PriceOrderDetail getPriceOrderDetail(Long goodsId, EntityFixedInfoVO entityFixedVO, GoodsBusinessVO businessVO, PriceOrder item) {
        Long orderId = item.getId();
        PriceOrderDetail orderDetail = new PriceOrderDetail();
        orderDetail.setPriceOrderId(orderId);
        orderDetail.setGoodsId(goodsId);
        orderDetail.setStatus(EnableStatus.ENABLE.getStatus());
        orderDetail.setEnterpriseId(item.getEnterpriseId());
        orderDetail.setParentId(item.getParentId());
        BigDecimal retailPrice = businessVO.getRetailPrice();
        //零售单价
        orderDetail.setRetailPrice(retailPrice);
        //会员单价
        BigDecimal memberPrice = businessVO.getMemberPrice();
        orderDetail.setMemberPrice(memberPrice);
        //配货单价
        BigDecimal distrPrice = businessVO.getDistrPrice();
        orderDetail.setDistrPrice(distrPrice);

        //设置税率
        Long saleTaxRateId = businessVO.getSaleTaxRateId();
        GoodsTaxRate saleTaxRate = goodsTaxRateMapper.selectByPrimaryKey(saleTaxRateId);
        if(saleTaxRate != null){
            orderDetail.setSaleTaxRateId(saleTaxRate.getId());
            orderDetail.setSaleTaxRate(saleTaxRate.getTaxRate());
        } else {
            throw new BusinessException("销项税不存在");
        }
        Long distrTaxRateId = businessVO.getDistrTaxRateId();
        GoodsTaxRate distrTaxRate = goodsTaxRateMapper.selectByPrimaryKey(distrTaxRateId);
        if(distrTaxRate != null){
            orderDetail.setDistrTaxRateId(distrTaxRate.getId());
            orderDetail.setDistrTaxRate(distrTaxRate.getTaxRate());
        } else {
            throw new BusinessException("配货税率不存在");
        }
        //不含税零售单价
        if(retailPrice != null){
            orderDetail.setNotaxRetailPrice(retailPrice.divide(new BigDecimal(100).add(saleTaxRate.getTaxRate()).divide(new BigDecimal(100)), 6, BigDecimal.ROUND_HALF_UP));
        }
        //不含税会员单价
        if(memberPrice != null){
            orderDetail.setNotaxMemberPrice(memberPrice.divide(new BigDecimal(100).add(saleTaxRate.getTaxRate()).divide(new BigDecimal(100)), 6, BigDecimal.ROUND_HALF_UP));
        }
        //不含税配货单价
        if(distrPrice != null){
            orderDetail.setNotaxDistrPrice(distrPrice.divide(new BigDecimal(100).add(distrTaxRate.getTaxRate()).divide(new BigDecimal(100)), 6, BigDecimal.ROUND_HALF_UP));
        }


        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(entityFixedVO,orderDetail);
        //插入
        priceOrderDetailMapper.insertSelective(orderDetail);
        return orderDetail;
    }

    private PriceOrderDetail getPriceOrderDetail(Long goodsId, EntityFixedInfoVO entityFixedVO, PriceOrderDetail priceOrderDetail, BigDecimal splitCount) {
        PriceOrderDetail orderDetail = priceOrderDetail;

        orderDetail.setPriceOrderId(priceOrderDetail.getPriceOrderId());
        orderDetail.setGoodsId(goodsId);

        BigDecimal retailPrice = priceOrderDetail.getRetailPrice();
        //零售单价
        orderDetail.setRetailPrice(retailPrice.divide(splitCount,6,BigDecimal.ROUND_HALF_UP));
        BigDecimal memberPrice = priceOrderDetail.getMemberPrice();
        //会员单价
        orderDetail.setMemberPrice(memberPrice.divide(splitCount,6,BigDecimal.ROUND_HALF_UP));
        //设置税率
        orderDetail.setSaleTaxRateId(priceOrderDetail.getSaleTaxRateId());
        orderDetail.setSaleTaxRate(priceOrderDetail.getSaleTaxRate());

        //不含税零售单价
        if(retailPrice != null){
            orderDetail.setNotaxRetailPrice(retailPrice.divide(new BigDecimal(100).add(priceOrderDetail.getSaleTaxRate()).divide(new BigDecimal(100)), 6, BigDecimal.ROUND_HALF_UP));
        }
        //不含税会员单价
        if(memberPrice != null){
            orderDetail.setNotaxMemberPrice(memberPrice.divide(new BigDecimal(100).add(priceOrderDetail.getSaleTaxRate()).divide(new BigDecimal(100)), 6, BigDecimal.ROUND_HALF_UP));
        }

        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(entityFixedVO,orderDetail);
        orderDetail.setStatus(EnableStatus.ENABLE.getStatus());
        orderDetail.setEnterpriseId(priceOrderDetail.getEnterpriseId());
        orderDetail.setParentId(priceOrderDetail.getParentId());
        orderDetail.setId(null);
        //插入
        priceOrderDetailMapper.insertSelective(orderDetail);
        return orderDetail;
    }


    /**
     * 封装安全库存，商品管理对象，并插入
     * @param entityFixedVO
     * @param goodsId
     * @param userVO
     */
    private void addSafetyStockGoodsManage(EntityFixedInfoVO entityFixedVO, Long goodsId,
                                           EnterpriseInGoodsVO enterpriseVO,
                                           UserVO userVO) throws Exception {
        SafetyStock safetyStock = (SafetyStock) EntityUtils.reflectAddSetDefaultValue(SafetyStock.class,userVO);
        safetyStock.setGoodsId(goodsId);
        safetyStock.setStatus(EnableStatus.ENABLE.getStatus());
        safetyStock.setEnterpriseId(enterpriseVO.getEnterpriseId());
        safetyStock.setParentId(enterpriseVO.getParentId());
        safetyStock.setChainType(enterpriseVO.getChainType());

//        //替换拆零商品的默认货位信息
//        if (splitSetSaveVO != null) {
//            safetyStock.setDefaultShelfId(splitSetSaveVO.getSplitShelfId());
//            safetyStock.setDefaultShelfName(splitSetSaveVO.getSplitShelfName());
//        }

        UserEnterpriseUtils.setUserCreateOrModify(safetyStock,userVO,true);

        BeanUtils.copyProperties(entityFixedVO, safetyStock);
        //增加安全库存表
        safetyStockMapper.insertSelective(safetyStock);
        GoodsManage goodsManage = (GoodsManage) EntityUtils.reflectAddSetDefaultValue(GoodsManage.class,userVO);
        goodsManage.setEnterpriseId(enterpriseVO.getEnterpriseId());
        goodsManage.setParentId(enterpriseVO.getParentId());
        goodsManage.setChainType(enterpriseVO.getChainType());
        goodsManage.setGoodsId(goodsId);
        goodsManage.setSafetyStockId(safetyStock.getId());
//        goodsManage.setPriceOrderDtlId(priceId);
        goodsManage.setStatus(GoodsManageStatus.WAIT_ON_SHELVES.getCode());
        goodsManageMapper.insertSelective(goodsManage);
    }


    /**
     * 属性字段对应的枚举类Map
     * @return
     */
    private Map<String,Class> enumClassMap(){
        Map<String,Class> map = new HashMap();
        map.put("businessVariety",BusinessVariety.class);// 品种类别

        map.put("prescriptionDrug",PrescriptionDrug.class);// 商品属性-是否为处方药

        //map.put("goodsAttribute",GoodsAttribute2DrugsA.class);// 商品属性

        map.put("otcType",GoodsAttributeDrugsOTCType.class);// otcType

        map.put("domesticImport",DomesticImport.class);// 国产/进口

        map.put("qualityPeriodUnit",GoodsQualityPeriodUnit.class);// 保质期单位

        map.put("specialDrug",SpecialDrugsAll.class);// 特殊管理药品

        map.put("spiritDrugType",SpiritDrugsType.class);// 精神药品分类

        map.put("inChargeDrug",InChargeDrug.class);//专管药品

        map.put("formulationType",SpecialCompoundPreparationsType.class);// 含特殊药品复方制剂类型

        map.put("storageTemp",StorageTemp.class);// 储藏温度

        map.put("medicalInsurance",MedicalFlag.class);// 是否为医保药品（0-否；1-是）

        map.put("medicalInsuranceType",MedicalInsuranceType.class);// 医保类别

        map.put("first",MedicalFlag.class);// 是否首营品种

        map.put("goodsNature",GoodsNature.class);// 商品性质（0-普通商品；1-拆零商品；2-组装商品）

        map.put("configurationFlag",ConfigurationFlag.class);// 配置标识（0-必备；1-可选）

        map.put("distrFlag",DistrFlag.class);// 配货标识（0-普通；1-首推）

        map.put("validFlag",ValidFlag.class);//标记（0-作废；1-正常）

        map.put("status",OpeningInventoryStatus.class);// 状态

        // 业务表

        map.put("managementMode",ManagementMode.class);// 经营方式

        map.put("integralGoods",YesAndNo.class);//是否积分商品

        map.put("bargainGoods",YesAndNo.class);//特价商品

        map.put("commissionGoods",YesAndNo.class);// 提成商品

        map.put("pricingPolicy",PricingPolicyType.class);//销售定价策略

        // 存储和养护
        map.put("deliveryMode",DeliveryMode.class);// 配送类型
        map.put("replenishmentPolicy",ReplenishmentPolicyType.class); //补货策略
        map.put("maintenanceType",MaintenanceType.class);// 养护类型


        return map;

    }


    private Map<String,String> fieldMustMap(){
        Map<String,String> fieldNames = new HashMap();
        fieldNames.put("businessVariety","品种类别");
        fieldNames.put("categoryId","商品分类ID");
        fieldNames.put("code","编码");
        fieldNames.put("barcode","条形码");
        fieldNames.put("genericName","通用名称");
        fieldNames.put("name","商品名称");
        fieldNames.put("goodsAttribute","商品属性");

        fieldNames.put("prescriptionDrug","商品属性-是否为处方药");
        fieldNames.put("otcType","非处方药类别");
        fieldNames.put("pinyinCode","检索码（型如“通用名检索码—商品名检索码”）");
        fieldNames.put("oldCode","原商品编码");
        fieldNames.put("nationalStandardCode","国家本位码");
        fieldNames.put("dosageId","剂型ID");
        fieldNames.put("dosageName","剂型名称");
        fieldNames.put("specification","规格");
        fieldNames.put("unitId","库存计量单位ID");
        fieldNames.put("unitName","库存计量单位名称");
        fieldNames.put("domesticImport","国产/进口（0-国产；1-进口）");
        fieldNames.put("manufacturerId","生产厂商ID");
        fieldNames.put("manufacturer","生产厂商");
        fieldNames.put("place","产地");
        fieldNames.put("approvalNumber","批准文号");
        fieldNames.put("validUntil","有效期至");
        fieldNames.put("managementScopeId","经营范围ID");
        fieldNames.put("managementScopeName","经营范围名称");
        fieldNames.put("checkTypeId","验收分类ID");


        fieldNames.put("checkTypeName","验收分类名称");
        fieldNames.put("qualityPeriod","保质期");
        fieldNames.put("qualityPeriodUnit","保质期单位（0-日；1-月；2-年）");
        fieldNames.put("specialDrug","特殊管理药品：（0-精神药品；1-麻醉药品；2-医疗用毒性药品；3-放射性药品）");
        fieldNames.put("spiritDrugType","精神药品分类（0-一类；1-二类）");
        fieldNames.put("inChargeDrug","专管药品（0-含特殊药品复方制剂；1-蛋白同化制剂；2-肽类激素）");
        fieldNames.put("formulationType","含特殊药品复方制剂类型");
        fieldNames.put("limitQuantity","限购数量");
        fieldNames.put("storageTemp","贮藏温度");
        fieldNames.put("storageConditionName","贮藏条件名称");
        fieldNames.put("registeredTrademark","注册商标");
        fieldNames.put("brand","品牌");
        fieldNames.put("season","季节");


        fieldNames.put("grade","等级");
        fieldNames.put("medicalInsurance","是否为医保药品");
        fieldNames.put("medicalInsuranceType","医保类别");
        fieldNames.put("medicalInsuranceCode","医保号");
        fieldNames.put("first","首营品种");
        fieldNames.put("firstGoodsCode","首营品种编号");
        fieldNames.put("applicantId","申请人ID");
        fieldNames.put("applicantCode","申请人员编码");
        fieldNames.put("applicantName","申请人员");
        fieldNames.put("applicationTime","申请日期");
        fieldNames.put("applicationOpinion","申请意见");
        fieldNames.put("goodsNature","商品性质");
        fieldNames.put("configurationFlag","配置标识");
        fieldNames.put("distrFlag","配货标识");
        fieldNames.put("status","状态");

        fieldNames.put("validFlag","标记");
        fieldNames.put("pictureIds","商品图片ID");
        fieldNames.put("remark","商品备注");

        //业务
        fieldNames.put("purchaseTaxRate","进项税（%）");
        fieldNames.put("saleTaxRate","销售税（%）");
        fieldNames.put("managementMode","经营方式（0-购销；1-实销实结）");
        fieldNames.put("integralGoods","积分商品（0-否；1-是）");
        fieldNames.put("integralMultiple","积分倍数");
        fieldNames.put("bargainGoods","特价商品（0-否；1-是）");
        fieldNames.put("commissionGoods","提成商品（0-否；1-是）");
        fieldNames.put("retailPrice","零售基价");
        fieldNames.put("memberPrice","会员基价");
        fieldNames.put("distrPrice","配货基价");
        fieldNames.put("distrTaxRate","配货税率（%）");
        //储存和养护

        fieldNames.put("inbagQuantity","中包装数量");
        fieldNames.put("bigbagQuantity","大包装数量");
        fieldNames.put("smallbagLength","小包装长度（cm）");
        fieldNames.put("smallbagWidth","小包装宽度（cm）");
        fieldNames.put("smallbagHeight","小包装高度（cm）");
        fieldNames.put("smallbagVolume","小包装体积（cm³）");
        fieldNames.put("smallbagWeight","小包装重量（g）");
        fieldNames.put("inbagLength","中包装长度（cm）");
        fieldNames.put("inbagWidth","中包装宽度（cm）");
        fieldNames.put("inbagHeight","中包装高度（cm）");
        fieldNames.put("inbagVolume","中包装体积（cm³））");

        fieldNames.put("inbagWeight","中包装重量（g）");
        fieldNames.put("bigbagLength","大包装长度（cm）");
        fieldNames.put("bigbagWidth","大包装宽度（cm）");
        fieldNames.put("bigbagHeight","大包装高度（cm）");
        fieldNames.put("bigbagVolume","大包装体积（cm³）");
        fieldNames.put("bigbagWeight","大包装重量（g）");
        fieldNames.put("deliveryMode","配送方式（0-配送中心配送；1-委托配送）");
        fieldNames.put("replenishmentPolicy","补货政策（0-动态存量补货；1-安全库存补货；2-人工补货；3-禁止补货）");
        fieldNames.put("minimumPurchasingBatch","最小采购批量");
        fieldNames.put("minimumDistributionBatch","最小配货批量");
        fieldNames.put("nearEffectPeriod","近效期");

        fieldNames.put("nearEffectPeriodUnit","近效期单位（0-天；1-月；2-年）");
        fieldNames.put("unsalableCycle","滞销周期");
        fieldNames.put("unsalableCycleUnit","滞销周期单位（0-天；1-月；2-年）");
        fieldNames.put("maintenanceType","养护类型（0-常规养护；1-重点养护）");
        fieldNames.put("maintenanceCycle","养护周期");
        fieldNames.put("firstGoodsCode","大包装重量（g）");
        fieldNames.put("maintenanceCycleUnit","养护周期单位（0-天；1-月；2-年））");
        fieldNames.put("maintenanceMeasureIds","养护标准（养护措施ID集合）");

        //说明书
        fieldNames.put("approvalDate","核准日期");
        fieldNames.put("chPinyin","汉语拼音");
        fieldNames.put("englishName","英文名称");
        fieldNames.put("component","成分");
        fieldNames.put("properties","性状");
        fieldNames.put("indication","适应症");
        fieldNames.put("usageDosage","用法用量");
        fieldNames.put("adverseReaction","不良反应");
        fieldNames.put("taboo","禁忌");
        fieldNames.put("notice","注意事项");
        fieldNames.put("womenMedication","孕妇及哺乳期妇女用药");
        fieldNames.put("childrenMedication","儿童用药");
        fieldNames.put("senileMedication","老年用药");
        fieldNames.put("drugsInteractions","药物和相互作用");

        fieldNames.put("overdose","用药过量");
        fieldNames.put("pharmacologyToxicology","药理毒理");
        fieldNames.put("pharmacokinetics","药代动力学");
        fieldNames.put("packing","包装");
        fieldNames.put("operativeNorm","执行标准");
        fieldNames.put("listedCompany","上市企业");
        fieldNames.put("manufacturerAddress","生产企业地址");
        fieldNames.put("repackingEnterprise","分装企业");
        fieldNames.put("repackingEnterpriseAddress","分装企业地址");
        fieldNames.put("telephone","电话");
        fieldNames.put("email","邮箱");
        fieldNames.put("postalCode","邮政编码");
        fieldNames.put("fax","传真");
        fieldNames.put("productTelephone","产品咨询电话");

        fieldNames.put("website","网址");
        fieldNames.put("fileids","附件ID集合，多个用“，”分隔");

        //资质


        return fieldNames;
    }

    private Map<String,String> qualificationConfigFieldMustMap(){
        Map<String,String> fieldNames = new HashMap();
        fieldNames.put("qualificationId","资质ID");
        fieldNames.put("qualificationCode","资质编号（不是资质表编码）");
        fieldNames.put("validUntil","有效期至");
        fieldNames.put("fileId","附件ID");
        return fieldNames;
    }


    private List<GoodsExcelVO> packListForExportExcel(List<GoodsVO> list){
        List<GoodsExcelVO> excelVOList = new ArrayList<>();
        list.forEach(item->{
            GoodsExcelVO excelVO = new GoodsExcelVO();
            BeanUtils.copyProperties(item,excelVO);
            //品种分类
            excelVO.setBusinessVariety(BusinessVariety.getName(item.getBusinessVariety()));
            //商品分类
            excelVO.setCategory(item.getCategoryName());
            //商品属性
            Integer attr = item.getGoodsAttribute();
            Integer preDrug = item.getPrescriptionDrug();
            Integer otcType = item.getOtcType();
            
            String goodsAttr = getGoodsAttr(item.getBusinessVariety(),attr,preDrug,otcType);


            excelVO.setGoodsAttribute(goodsAttr);
            //国产、出口
            excelVO.setDomesticImport(DomesticImport.getName(item.getDomesticImport()));
            //有效期
            excelVO.setValidUntil(DateUtils.getDate(item.getValidUntil()));
            //保质期
            excelVO.setQualityPeriod(item.getQualityPeriod() + "");
            //保质期类型
            excelVO.setQualityPeriodUnit(TimeUnit.getName(item.getQualityPeriodUnit()));
            //特殊管理药品：
            Integer specialDrug = item.getSpecialDrug();
            Integer spiritDrugType  = item.getSpiritDrugType();
            String specialName = "";
           if(specialDrug != null){
               if(SpecialDrugsAll.SPIRIT_DRUGS.getCode() == specialDrug){
                   if(spiritDrugType != null){
                       if(SpiritDrugsType.SPIRIT_DRUGS_FIRST.getCode() == spiritDrugType){
                           specialName = SpecialDrugsAll.SPIRIT_DRUGS.getName() + "-" + SpiritDrugsType.SPIRIT_DRUGS_FIRST.getName();
                       } else if(SpiritDrugsType.SPIRIT_DRUGS_SECOND.getCode() == spiritDrugType){
                           specialName = SpecialDrugsAll.SPIRIT_DRUGS.getName() + "-" + SpiritDrugsType.SPIRIT_DRUGS_SECOND.getName();
                       }
                   }

               } else {
                   specialName = SpecialDrugsAll.getName(specialDrug);
               }
           }

            excelVO.setSpecialDrug(specialName);
            //专管药品
            Integer inChargeDrug = item.getInChargeDrug();
            Integer formulationType = item.getFormulationType();
            String chargeDrug = "";
            if(inChargeDrug != null && formulationType != null){
                if(SpecialCompoundPreparations.CONTAINING_EPHEDRINE.getCode() == inChargeDrug){
                    chargeDrug = SpecialCompoundPreparations.CONTAINING_EPHEDRINE.getName() + "-" + SpecialCompoundPreparationsType.getName(formulationType);
                } else {
                    chargeDrug = SpecialCompoundPreparations.getName(inChargeDrug);
                }
            }

            excelVO.setInChargeDrug(chargeDrug);
            //是否为医保
            excelVO.setMedicalInsurance(MedicalFlag.getName(Optional.ofNullable(item.getMedicalInsurance()).orElse(-100)));
            //医保类别
            excelVO.setMedicalInsuranceType(MedicalInsuranceType.getName(Optional.ofNullable(item.getMedicalInsuranceType()).orElse(-100)));
            //商品性质
            excelVO.setGoodsNature(GoodsNature.getName(Optional.ofNullable(item.getGoodsNature()).orElse(-100)));
            //零售极价
            if(item.getGoodsBusinessVO() != null){
                BigDecimal retailPrice = item.getGoodsBusinessVO().getRetailPrice();
                excelVO.setRetailPrice(Optional.ofNullable(retailPrice.toString()).orElse(""));
                //配货基价
                BigDecimal distrPrice = item.getGoodsBusinessVO().getDistrPrice();
                excelVO.setDistrPrice(Optional.ofNullable(distrPrice.toString()).orElse(""));
                //进项税
                BigDecimal purchaseTaxRate = item.getGoodsBusinessVO().getPurchaseTaxRate();
                excelVO.setPurchaseTaxRate(Optional.ofNullable(purchaseTaxRate + "%").orElse(""));

                //消项税
                BigDecimal saleTaxRate = item.getGoodsBusinessVO().getSaleTaxRate();
                excelVO.setSaleTaxRate(saleTaxRate != null ? saleTaxRate + "%" : "");
                //配货税
                BigDecimal distrTaxRate = item.getGoodsBusinessVO().getDistrTaxRate();
                excelVO.setDistrTaxRate(distrTaxRate != null ?  distrTaxRate + "%" : "'");
                //特价商品
                Integer bargainGoods = item.getGoodsBusinessVO().getBargainGoods();
                Integer integralGoods = item.getGoodsBusinessVO().getIntegralGoods();//积分商品
                excelVO.setBargainGoods(YesAndNo.getName(Optional.ofNullable(bargainGoods).orElse(-100)));
                excelVO.setIntegralGoods(YesAndNo.getName(Optional.ofNullable(integralGoods).orElse(-100)));
                //积分倍数
                BigDecimal integralMultiple = item.getGoodsBusinessVO().getIntegralMultiple();
                excelVO.setIntegralMultiple(integralMultiple != null ? integralMultiple + "%" : "");
                excelVO.setPricingPolicy(PricingPolicyType.getName(Optional.ofNullable(item.getGoodsBusinessVO().getPricingPolicy()).orElse(-100)));
                //经营方式
                excelVO.setManagementMode(ManagementMode.getName(Optional.ofNullable(item.getGoodsBusinessVO().getManagementMode()).orElse(-100)));
            }



            //配置标示
            excelVO.setConfigurationFlag(ConfigurationFlag.getName(Optional.ofNullable(item.getConfigurationFlag()).orElse(-100)));
            excelVO.setDistrFlag(DistrFlag.getName(Optional.ofNullable(item.getDistrFlag()).orElse(-100)));
            //状态
            excelVO.setStatus(PurchaseStatus.getName(Optional.ofNullable(item.getStatus()).orElse(-100)));
            //标记
            excelVO.setValidFlag(ValidFlag.getName(Optional.ofNullable(item.getValidFlag()).orElse(-100)));



            //首营品种
            excelVO.setFirst(FirstGoods.getName(item.getFirst()));
            excelVO.setApplicationTime(DateUtils.getDate(item.getApplicationTime()));

            if(item.getStorageMaintenanceVO() != null){
                //配送方式
                excelVO.setDeliveryMode(DeliveryMode.getName(Optional.ofNullable(item.getStorageMaintenanceVO().getDeliveryMode()).orElse(-100)));
                //最小采购量
                BigDecimal minimumPurchasingBatch = item.getStorageMaintenanceVO().getMinimumPurchasingBatch();
                excelVO.setMinimumPurchasingBatch( minimumPurchasingBatch != null ? minimumPurchasingBatch + "" : "");
                // 最小配货批量
                BigDecimal minimumDistributionBatch = item.getStorageMaintenanceVO().getMinimumDistributionBatch();
                excelVO.setMinimumDistributionBatch(minimumDistributionBatch != null ? minimumDistributionBatch + "" : "");

                //近效期周期
                Integer nearEffectPeriod = item.getStorageMaintenanceVO().getNearEffectPeriod();
                excelVO.setNearEffectPeriod(nearEffectPeriod != null ? nearEffectPeriod + "" : "");
                //近效期周期单位
                excelVO.setNearEffectPeriodUnit(TimeUnit.getName(Optional.ofNullable(item.getStorageMaintenanceVO().getNearEffectPeriodUnit()).orElse(-100)));
                //滞销周期
                Integer unsalableCycle = item.getStorageMaintenanceVO().getUnsalableCycle();
                excelVO.setUnsalableCycle(unsalableCycle != null ? unsalableCycle + "" : "");
                //滞销周期单位（
                excelVO.setUnsalableCycleUnit(TimeUnit.getName(Optional.ofNullable(item.getStorageMaintenanceVO().getMaintenanceCycleUnit()).orElse(-100)));
                //养护周期类型
                excelVO.setMaintenanceCycleUnit(TimeUnit.getName(Optional.ofNullable(item.getStorageMaintenanceVO().getMaintenanceCycleUnit()).orElse(-100)));
                //养护类型
                excelVO.setMaintenanceType(MaintenanceType.getName(Optional.ofNullable(item.getStorageMaintenanceVO().getMaintenanceType()).orElse(-100)));
                //养护周期
                Integer maintenanceCycle = item.getStorageMaintenanceVO().getMaintenanceCycle();
                excelVO.setMaintenanceCycle(maintenanceCycle != null ? maintenanceCycle +"" : "");

            }

            //储藏温度
            excelVO.setStorageTemp(StorageTemp.getName(Optional.ofNullable(item.getStorageTemp()).orElse(-100)));

            excelVOList.add(excelVO);
        });
        return excelVOList;
    }

    /** 
    * @Description: 获取商品属性
    * @return:  
    * @Author: dongyang.du
    * @Date: 31/01/2018
     * @param businessVariety
     * @param attr
     * @param preDrug
     * @param otcType
    */ 
    private String getGoodsAttr( Integer businessVariety, Integer attr,Integer preDrug, Integer otcType) {

        String goodsAttr = "";

        if(BusinessVariety.DRUGS.getCode() == businessVariety){
            //药品
            if(GoodsAttribute2DrugsA.PATENTMEDICINE.getCode() == (attr)){
                //成药
                goodsAttr = GoodsAttribute2DrugsA.PATENTMEDICINE.getName();
                if(null != preDrug){
                    if(GoodsAttributeDrugsOTC.OTC.getCode() == preDrug){
                        // 非处方药
                        goodsAttr = goodsAttr + "-" + GoodsAttributeDrugsOTC.OTC.getName() + "-" + GoodsAttributeDrugsOTCType.getName(otcType);
                    } else if(GoodsAttributeDrugsRXDrug.RX_DRUG.getCode() == preDrug){
                        goodsAttr = goodsAttr + "-" + GoodsAttributeDrugsRXDrug.RX_DRUG.getName();
                    }
                }
            } else if(GoodsAttribute2DrugsA.CHINESE_MEDICINAL_MATERIALS.getCode()== attr){
                //中药材
                goodsAttr = GoodsAttribute2DrugsA.CHINESE_MEDICINAL_MATERIALS.getName();

            } else if(GoodsAttribute2DrugsA.CHINESE_MEDICINE_DECOCTION_PIECES.getCode() == attr ){
                //中药饮片
                goodsAttr = GoodsAttribute2DrugsA.CHINESE_MEDICINE_DECOCTION_PIECES.getName();
            }

        } else if(BusinessVariety.FOODS.getCode() == businessVariety){
            //食品
            goodsAttr = GoodsAttributeFood.getName(attr);
        } else if(BusinessVariety.COSMETICS.getCode() == businessVariety){
            //化妆品
            goodsAttr = GoodsAttributeCosmetics.getName(attr);
        } else if(BusinessVariety.MEDICAL_APPARATUS.getCode() == businessVariety){
            //医疗器械
            goodsAttr = GoodsAttributeMedicalApparatus.getName(attr);
        } else if(BusinessVariety.OTHERS.getCode() == businessVariety){
            //其它
            goodsAttr = GoodsAttributeOthers.getName(attr);
        }

        return goodsAttr;
    }

    @Override
    public CanSpitGoodVO getSplitGoodInfoById(Long id, Long enterpriseId) {
        CanSpitGoodVO canSpitGoodVO = goodsMapper.getSplitGoodById(id);

        if (canSpitGoodVO == null) {
            canSpitGoodVO = new CanSpitGoodVO();
        }

        //获取零售价格、会员价格
        Map params = new HashMap();
        params.put("queryType",1);
        params.put("enterpriseId",enterpriseId);
        List<PriceOrder> priceOrders = priceOrderMapper.selectByQueryType(params);
        Long priceOrderId = null;
        for(PriceOrder temp : priceOrders){
            if(temp.getEnterpriseId().equals(enterpriseId)){
                priceOrderId = temp.getId();
                break;
            }
        }
        if(priceOrderId == null){
            logger.error("企业："+enterpriseId+"下面没有默认的价格清单");
            return canSpitGoodVO;
        }
        PriceOrderDetail priceOrderDetail = priceOrderDetailMapper.selectByEnterpriseIdAndPriceOrderIdAndGoodId(enterpriseId,priceOrderId,id);
        if (priceOrderDetail != null) {
            canSpitGoodVO.setRetailPrice(priceOrderDetail.getRetailPrice());
            canSpitGoodVO.setMemberPrice(priceOrderDetail.getMemberPrice());
        }
        return canSpitGoodVO;
    }

    @Override
    public Page<List<GoodsVO>> getGoodsListByParamForSale(UserVO userVO, RequestGoodsVO goodsVO, Page<List<GoodsVO>> page) {
        ParamUtils.packParam(userVO,goodsVO);
        RequestGoodsVO2 requestGoodsVO2 = new RequestGoodsVO2();
        BeanUtils.copyProperties(goodsVO,requestGoodsVO2);
        requestGoodsVO2.setStart(page.getStart());
        requestGoodsVO2.setPageNo(page.getPageNo());
        requestGoodsVO2.setPageSize(page.getPageSize());
        List<GoodsVO> list = goodsMapper.getGoodsListByParamForSale(requestGoodsVO2);
        Integer count = goodsMapper.getGoodsCountByParamForSale(requestGoodsVO2);
        page.setResult(list);
        page.setTotalRecord(count);
        return page;
    }

    /**
     *
     * @param request
     * @return
     * @throws Exception
     */

    @Override
    public ResponseGoodsExcelWithSqlVO excelImportUpdate(HttpServletRequest request) throws Exception {
        ResponseGoodsExcelVO responseGoodsExcelVO = excelImport(request,true);
        String key = responseGoodsExcelVO.getTimestamp();
        List<GoodsVO> list = JSON.parseArray((String) redisComponent.get(GoodsInfoConstant.STANDARD_QUALIFIED_FIELD + key),GoodsVO.class);
        StringBuilder stringBuilder = new StringBuilder();
        for (GoodsVO goodsVO : list){
            stringBuilder.append("UPDATE saas_goods set ")
                    .append("goods_attribute = ").append(goodsVO.getGoodsAttribute())
                    .append(",prescription_drug = ").append(goodsVO.getPrescriptionDrug())
                    .append(",otc_type = ").append(goodsVO.getOtcType())
                    .append(",cosmetics = ").append(goodsVO.getCosmetics())
                    .append(",in_charge_drug = ").append(goodsVO.getInChargeDrug())
                    .append(",formulation_type = ").append(goodsVO.getFormulationType())
                    .append(" where code = ").append("'").append(goodsVO.getCode()).append("'").append(";");
        }
        ResponseGoodsExcelWithSqlVO excelWithSqlVO = new ResponseGoodsExcelWithSqlVO();
        excelWithSqlVO.setTimestamp(key);
        excelWithSqlVO.setUpdateSql(stringBuilder.toString());
        return excelWithSqlVO;


    }



}