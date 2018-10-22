package com.rograndec.feijiayun.chain.business.goods.info.excel;

import com.rograndec.feijiayun.chain.business.basic.user.vo.UserDataVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsBusinessVO;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsExcelVO;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsStorageVO;
import com.rograndec.feijiayun.chain.business.goods.info.vo.GoodsVO;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsCategory;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsDosageVO;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsTaxRateVO;
import com.rograndec.feijiayun.chain.business.goods.set.vo.GoodsUnitVO;
import com.rograndec.feijiayun.chain.business.system.opening.common.ExcelErrorCodeEnum;
import com.rograndec.feijiayun.chain.business.system.set.vo.BusinessScopeVO;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.component.CodeComponent;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.ExcelMethodUtils;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.IRowReader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * 商品信息导入行解析器
 * Created by ST on 2017/9/7.
 *
 */
@Component
@Scope("prototype")
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GoodsInfoIRowReader extends IRowReader {

    private static final Logger logger = LoggerFactory.getLogger(GoodsInfoIRowReader.class);

    //合格产品
    private List<GoodsExcelVO> qualifiedList;
    //不合格产品
    private List<GoodsExcelVO> disqualificationList;

    //标准入库实体list
    private List<GoodsVO> qualifiedGVOList;


    //商品分类
    private List<GoodsCategory>  categoryList;

    //是否自定义编码 (自定义编码-true;系统自定义-false)
    private boolean isCustomCode;

    //剂型list
    private List<GoodsDosageVO> dosageList;

    //库存计量单位
    private List<GoodsUnitVO> unitList;

    //经营范围(key---品种类别)
    private Map<String,List<BusinessScopeVO>> businessScopeMap;

    //验收分类（key---品种类别）
    private Map<String,List<QualitySetVO>>  qualitySetMap;

    //税收
    private List<GoodsTaxRateVO> taxRateVOList;

    private UserVO userVO;

    private List<UserDataVO> userDataVOList;

    //商品信息编码规则（0-商品分组+4位流水码；1-自定义编码）
    private Integer goodsCodeRule;

    @Autowired
    private CodeComponent codeComponent;

    @Autowired
    private GoodsMapper goodsMapper;

    private boolean update;


    private List<String> codeList = new ArrayList<>();


    /**
     * 校验业务
     * @param sheetIndex
     * @param curRow
     * @param rowList
     */
    @Override
    public void getRows(int sheetIndex, int curRow, List<String> rowList) {
        if(logger.isDebugEnabled()){
            logger.debug("Sheet={},Row={},Data={}",sheetIndex,curRow,rowList);
        }
        int k = 0;
//        boolean isOK = true;
        if(curRow > 1){//从第三行开始计算（excel行号从0开始）
            if(rowList != null && rowList.size() > 0){
                String businessVariety = rowList.get(k++);//品种类别
                String category = rowList.get(k++);//商品类别
                String code = rowList.get(k++);//商品编码*
                String barcode = rowList.get(k++);//条形码*
                String genericName = rowList.get(k++);//通用名称*
                String name = rowList.get(k++);//商品名称
                String goodsAttribute = rowList.get(k++);//商品属性
                String oldCode = rowList.get(k++);//原商品编码*
                String nationalStandardCode = rowList.get(k++);//国家本位码*
                String dosageName = rowList.get(k++);//剂型*
                String specification = rowList.get(k++);//规格*
                String unitName = rowList.get(k++);//库存计量单位*
                String manufacturer = rowList.get(k++);//生产厂商*
                String place = rowList.get(k++);//产地*
                String domesticImport = rowList.get(k++);//国产/进口*
                String approvalNumber = rowList.get(k++);//批准文号*
                String validUntil = rowList.get(k++);//有效期至*
                String managementScopeName = rowList.get(k++);//经营范围*
                String checkTypeName = rowList.get(k++);//验收分类
                String qualityPeriod = rowList.get(k++);//保质期*
                String qualityPeriodUnit = rowList.get(k++);//保质期类型*
                String specialDrug = rowList.get(k++);//特殊管理药品*
                String inChargeDrug = rowList.get(k++);//专门药品*


                String limitQuantity = rowList.get(k++);//限购数量*
                String medicalInsurance = rowList.get(k++);//医保药品*
                String medicalInsuranceType = rowList.get(k++);//医保类别
                String medicalInsuranceCode = rowList.get(k++);//医保编号
                String registeredTrademark = rowList.get(k++);//注册商标*
                String brand = rowList.get(k++);//品牌*
                String season = rowList.get(k++);//季节*
                String grade = rowList.get(k++);//等级*
                String inbagQuantity = rowList.get(k++);//中包数量*
                String bigbagQuantity = rowList.get(k++);//大包数量*
                String goodsNature = rowList.get(k++);//商品性质*
                String retailPrice = rowList.get(k++);//零售基价*
                String distrPrice = rowList.get(k++);//配货基价*
                String configurationFlag = rowList.get(k++);//配置标志*
                String distrFlag = rowList.get(k++);//配货标志*
                String status = rowList.get(k++);//状态*
                String validFlag = rowList.get(k++);//标记*
                String remark = rowList.get(k++);//备注*
                String purchaseTaxRate = rowList.get(k++);//进项税*
                String managementMode = rowList.get(k++);//经营方式*

                String first = rowList.get(k++);//首营品种
                String applicantName = rowList.get(k++);//申请人员*
                String applicationTime = rowList.get(k++);//申请日期
                String applicationOpinion = rowList.get(k++);//申请意见
                String saleTaxRate = rowList.get(k++);//销项税*
                String distrTaxRate = rowList.get(k++);//配货税*
                String bargainGoods = rowList.get(k++);//特价商品*
                String integralGoods = rowList.get(k++);//积分商品*
                String integralMultiple = rowList.get(k++);//积分商品倍数*
                String pricingPolicy = rowList.get(k++);//销售定价*
                String deliveryMode = rowList.get(k++);//配送方式*
                String minimumPurchasingBatch = rowList.get(k++);//最小采购批量*
                String minimumDistributionBatch = rowList.get(k++);//最小配送批量*
                String storageTemp = rowList.get(k++);//储藏区域*

                String nearEffectPeriod = rowList.get(k++);//近效期周期
                String nearEffectPeriodUnit = rowList.get(k++);//周期类型*
                String unsalableCycle = rowList.get(k++);//滞销周期
                String unsalableCycleUnit = rowList.get(k++);//周期类型
                String maintenanceType = rowList.get(k++);//养护类型*
                String maintenanceCycle = rowList.get(k++);//重点养护周期*常规养护周期*
                String maintenanceCycleUnit = rowList.get(k);//周期类型*


                //excel操作的使用的商品实体
                GoodsExcelVO goodsExcelVO = new GoodsExcelVO();
                //正确的数据按照能保存入库的方式构建
                GoodsVO goodsVO = new GoodsVO();
                GoodsStorageVO storageMaintenanceVO = new GoodsStorageVO();
                goodsVO.setStorageMaintenanceVO(storageMaintenanceVO);
                GoodsBusinessVO goodsBusinessVO = new GoodsBusinessVO();
                goodsVO.setGoodsBusinessVO(goodsBusinessVO);

                boolean isOK = true;
                boolean is_RX_DRUG = false;//这一行是否为处方药 true--处方药 false--非处方药

                //品种类别
                if(StringUtils.isBlank(businessVariety)){
                    businessVariety = BusinessVariety.DRUGS.getName();
                    goodsVO.setBusinessVariety(BusinessVariety.DRUGS.getCode());
                    goodsExcelVO.setBusinessVariety(businessVariety);
                } else {
                    int val = BusinessVariety.getCode(businessVariety);
                    if(val == -1){
                        isOK = false;
                        goodsExcelVO.setBusinessVariety(ExcelErrorCodeEnum.BLANK.getName());
                    } else {
                        goodsExcelVO.setBusinessVariety(businessVariety);
                        goodsVO.setBusinessVariety(val);
                    }
                }

                boolean existCategory = true;
                if(StringUtils.isBlank(category)){
                    //商品类别校验
                    isOK = false;
                    existCategory = false;
                    goodsExcelVO.setCategory(ExcelErrorCodeEnum.BLANK.getName());
                } else {
                    final boolean[] ok = {false};
                    categoryList.forEach(item->{
                        if(category.equals(item.getName())){
                            ok[0] = true;
                            goodsVO.setCategoryId(item.getId());
                            goodsExcelVO.setCategory(category);
                        }
                    });
                    if(!ok[0]){
                        isOK = false;
                        existCategory = false;
                        goodsExcelVO.setCategory(ExcelErrorCodeEnum.GOODS_CATEGORY_INCONFORMITY.getName());
                    }
                }

                /**
                 * 根据当前登录人判断该企业的的商品编码设置，如果为系统自动生成，不管是否输入都按照系统自动生成的编码，如果是自定义，没有输入判断需要输入
                 */
                if(!update){
                    if(goodsCodeRule == 1){
                        //自定义
                        if(StringUtils.isAnyBlank(code)){
                            isOK = false;
                            goodsExcelVO.setCode(ExcelErrorCodeEnum.BLANK.getName());
                        } else {
                            if(!codeList.contains(code)){
                                if(code.length() > 20){
                                    isOK = false;
                                    goodsExcelVO.setCode(ExcelErrorCodeEnum.MAXVALUE.getName());
                                } else {

                                    Integer countByCode = goodsMapper.getCountByCode(code, ChainType.getHeadEnterpriseId(userVO));
                                    if(countByCode > 0){
                                        isOK = false;
                                        goodsExcelVO.setCode(ExcelErrorCodeEnum.GOODSEXSIST_CODE.getName());
                                    } else {
                                        codeList.add(code);
                                        goodsExcelVO.setCode(code);
                                        goodsVO.setCode(code);
                                    }
                                }

                            } else {
                                isOK = false;
                                goodsExcelVO.setCode(ExcelErrorCodeEnum.GOODSEXSIST_CODE.getName());
                            }

                        }


                    } else {
                        //系统生成
                        if(existCategory){
                            //商品分类存在
                            categoryList.stream().forEach(cat->{
                                if(cat.getName().equals(category)){
                                    String goodsCode = "";
                                    try {
                                        goodsCode = codeComponent.generate(Goods.class.getSimpleName(), 8, ChainType.getHeadEnterpriseId(userVO));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        throw new BusinessException("商品导入变成生产编码异常");
                                    }
                                    goodsCode = cat.getCode() + goodsCode;
                                    goodsExcelVO.setCode(goodsCode);
                                    goodsVO.setCode(goodsCode);
                                }
                            });


                        } else {
                            isOK = false;
                            goodsExcelVO.setCode(ExcelErrorCodeEnum.GOODS_CATEGORY_INCONFORMITY.getName());
                        }

                    }
                } else {
                    goodsVO.setCode(code);
                }


                //      条形码
                goodsExcelVO.setBarcode(barcode);
                goodsVO.setBarcode(barcode);
                if(StringUtils.isAnyBlank(genericName)){
                    isOK = false;
                    goodsExcelVO.setGenericName(ExcelErrorCodeEnum.BLANK.getName());
                } else {
                    if(genericName.length() > 39){
                        isOK = false;
                        goodsExcelVO.setGenericName(ExcelErrorCodeEnum.MAXVALUE.getName());
                    } else {
                        goodsExcelVO.setGenericName(genericName);
                        goodsVO.setGenericName(genericName);
                    }
                }

                //商品名称
                if(StringUtils.isNoneBlank(name)){
                    if(name.length() > 100){
                        goodsExcelVO.setName(ExcelErrorCodeEnum.MAXVALUE.getName());
                    } else {
                        goodsExcelVO.setName(name);
                        goodsVO.setName(name);
                    }
                }

                //商品属性
                if(StringUtils.isAnyBlank(goodsAttribute)){
                    isOK = false;
                    goodsExcelVO.setGoodsAttribute(ExcelErrorCodeEnum.BLANK.getName());
                } else {
                    if(BusinessVariety.DRUGS.getName().equals(businessVariety)){
                        //如果品种分类是药品
                        int val = GoodsAttributeDrugs.getCode(goodsAttribute);
                        if(val == -1){
                            isOK = false;
                            goodsExcelVO.setGoodsAttribute(ExcelErrorCodeEnum.GOODSATTRIBUTE_INCONFORMITY.getName());
                        } else {
                            String[] attr = goodsAttribute.split("-");
                            if(attr.length == 3){

                                //成药非处方药
                                // excelvo
                                goodsExcelVO.setGoodsAttribute(goodsAttribute);
                                //
                                goodsVO.setGoodsAttribute(GoodsAttributeDrugsPatentMedicine.getCode(attr[0]));
                                goodsVO.setPrescriptionDrug(GoodsAttributeDrugsOTC.getCode(attr[1]));
                                goodsVO.setOtcType(GoodsAttributeDrugsOTCType.getCode(attr[2]));
                            }

                            if(attr.length == 2){
                                //成药处方药
                                is_RX_DRUG = true;
                                // excelvo
                                goodsExcelVO.setGoodsAttribute(goodsAttribute);
                                goodsVO.setGoodsAttribute(GoodsAttributeDrugsPatentMedicine.getCode(attr[0]));
                                goodsVO.setPrescriptionDrug(GoodsAttributeDrugsRXDrug.getCode(attr[1]));
                            }

                            if(attr.length == 1){
                                //中药等
                                // excelvo
                                goodsExcelVO.setGoodsAttribute(goodsAttribute);
                                goodsVO.setGoodsAttribute(GoodsAttribute2DrugsA.getCode(attr[0]));
                            }
                        }
                    } else if(BusinessVariety.COSMETICS.getName().equals(businessVariety)){
                        //如果品种分类是化妆品
                        int val = GoodsAttributeCosmetics2Enum.getCode(goodsAttribute);
                        if(val == -1){
                            isOK = false;
                            goodsExcelVO.setGoodsAttribute(ExcelErrorCodeEnum.GOODSATTRIBUTE_INCONFORMITY.getName());
                        } else {
                            String[] attr = goodsAttribute.split("-");
                            if(attr.length == 2){
                                //化妆品
                                goodsExcelVO.setGoodsAttribute(goodsAttribute);
                                goodsVO.setGoodsAttribute(GoodsAttributeCosmetics.getCode(attr[0]));
                                goodsVO.setCosmetics(Cosmetics.getCode(attr[1]));
                            } else if(attr.length == 1){
                                //化妆品
                                goodsExcelVO.setGoodsAttribute(goodsAttribute);
                                goodsVO.setGoodsAttribute(GoodsAttributeCosmetics.getCode(goodsAttribute));
                            }
                        }
                    } else if(BusinessVariety.MEDICAL_APPARATUS.getName().equals(businessVariety)){
                        //如果品种分类是医疗器械
                        int val = GoodsAttributeMedicalApparatus.getCode(goodsAttribute);
                        if(val == -1){
                            isOK = false;
                            goodsExcelVO.setGoodsAttribute(ExcelErrorCodeEnum.GOODSATTRIBUTE_INCONFORMITY.getName());
                        } else {
                            goodsExcelVO.setGoodsAttribute(goodsAttribute);
                            goodsVO.setGoodsAttribute(GoodsAttributeMedicalApparatus.getCode(goodsAttribute));
                        }
                    } else if(BusinessVariety.FOODS.getName().equals(businessVariety)){
                        //如果品种分类是食物
                        int val = GoodsAttributeFood.getCode(goodsAttribute);
                        if(val == -1){
                            isOK = false;
                            goodsExcelVO.setGoodsAttribute(ExcelErrorCodeEnum.GOODSATTRIBUTE_INCONFORMITY.getName());
                        } else {
                            goodsExcelVO.setGoodsAttribute(goodsAttribute);
                            goodsVO.setGoodsAttribute(GoodsAttributeFood.getCode(goodsAttribute));
                        }
                    } else  if(BusinessVariety.OTHERS.getName().equals(businessVariety)){
                        //如果品种分类是其他
                        int val = GoodsAttributeOthers.getCode(goodsAttribute);
                        if(val == -1){
                            isOK = false;
                            goodsExcelVO.setGoodsAttribute(ExcelErrorCodeEnum.GOODSATTRIBUTE_INCONFORMITY.getName());
                        } else {
                            goodsExcelVO.setGoodsAttribute(goodsAttribute);
                            goodsVO.setGoodsAttribute(GoodsAttributeOthers.getCode(goodsAttribute));
                        }
                    }
                }

                //原商品编码
                if(StringUtils.isNoneBlank(oldCode)){
                    if(oldCode.length() > 20){
                        goodsExcelVO.setOldCode(ExcelErrorCodeEnum.MAXVALUE.getName());
                    } else{
                        goodsExcelVO.setOldCode(oldCode);
                        goodsVO.setOldCode(oldCode);
                    }
                }

                //国家本位码
                if(StringUtils.isNoneBlank(nationalStandardCode)){
                    if(oldCode.length() > 30){
                        goodsExcelVO.setNationalStandardCode(ExcelErrorCodeEnum.MAXVALUE.getName());
                    } else {
                        goodsExcelVO.setNationalStandardCode(nationalStandardCode);
                        goodsVO.setNationalStandardCode(nationalStandardCode);
                    }
                }

                //剂型
                if(StringUtils.isNoneBlank(dosageName)){
                    //查询剂型
                    if(dosageName.length() > 30){
                        isOK = false;
                        goodsExcelVO.setDosageName(ExcelErrorCodeEnum.MAXVALUE.getName());
                    }
                    final boolean[] ok = {false};
                    dosageList.forEach(item->{
                        if(dosageName.equals(item.getName())){
                            ok[0] = true;
                            goodsVO.setDosageName(dosageName);
                            goodsVO.setDosageId(item.getId());

                            goodsExcelVO.setDosageName(dosageName);
                        }
                    });
                    if(!ok[0]){
                        isOK = false;
                        goodsExcelVO.setDosageName(ExcelErrorCodeEnum.DOSAGE_INEXISTENCE.getName());
                    }
                }else{
                    goodsExcelVO.setDosageName(dosageName);
                    for(GoodsDosageVO goodsDosageVO : dosageList){
                        goodsVO.setDosageName("片剂");
                        if(("片剂").equals(goodsDosageVO.getName())){
                            goodsVO.setDosageId(goodsDosageVO.getId());
                        }
                    }
                }
//                规格
                if(StringUtils.isNoneBlank(specification)){
                    if(specification.length() > 100){
                        isOK = false;
                        goodsExcelVO.setSpecification(ExcelErrorCodeEnum.MAXVALUE.getName());
                    } else {
                        goodsExcelVO.setSpecification(specification);
                        goodsVO.setSpecification(specification);
                    }

                }
                //库房计量单位
                if(StringUtils.isNoneBlank(unitName)){
                    if(unitName.length() > 30){
                        isOK = false;
                        goodsExcelVO.setUnitName(ExcelErrorCodeEnum.MAXVALUE.getName());
                    }
                    final boolean[] ok = {false};
                    unitList.forEach(item->{
                        if(unitName.equals(item.getName())){
                            ok[0] = true;
                            goodsVO.setUnitName(unitName);
                            goodsVO.setUnitId(item.getId());
                            //excel
                            goodsExcelVO.setUnitName(unitName);
                        }
                    });

                    if(!ok[0]){
                        isOK = false;
                        goodsExcelVO.setUnitName(ExcelErrorCodeEnum.UNITNAME_INEXISTENCE.getName());
                    }
                }else{
                    goodsExcelVO.setUnitName(unitName);
                    for(GoodsUnitVO unitVO:unitList){
                        goodsVO.setUnitName("盒");
                        if("盒".equals(unitVO.getName())){
                            goodsVO.setUnitId(unitVO.getId());
                        }
                    }
                }
                //生成厂商
                if(StringUtils.isNoneBlank(manufacturer)){
                    if(manufacturer.length() > 100){
                        isOK = false;
                        goodsExcelVO.setManufacturer(ExcelErrorCodeEnum.MAXVALUE.getName());
                    } else {
                        goodsExcelVO.setManufacturer(manufacturer);
                        goodsVO.setManufacturer(manufacturer);
                    }
                }

                //产地
                if(StringUtils.isNoneBlank(place)){
                    if(place.length() > 100){
                        isOK = false;
                        goodsExcelVO.setPlace(ExcelErrorCodeEnum.MAXVALUE.getName());
                    } else {
                        goodsExcelVO.setPlace(place);
                        goodsVO.setPlace(place);
                    }
                }
                //国产/进口
                if(StringUtils.isNoneBlank(domesticImport)){
                   int val = DomesticImport.getCode(domesticImport);
                    if(val == -1){
                        isOK = false;
                        goodsExcelVO.setDomesticImport(ExcelErrorCodeEnum.DOMESTICIMPORT_INEXISTENCE.getName());
                    } else{
                        goodsExcelVO.setDomesticImport(domesticImport);
                        goodsVO.setDomesticImport(val);
                    }
                } else {
                    //空
                    goodsExcelVO.setDomesticImport(domesticImport);
                    goodsVO.setDomesticImport(DomesticImport.DOMESTIC.getCode());
                }

                //approvalNumber 批准文号的如果不填写默认填写“-”
                if(StringUtils.isNotBlank(approvalNumber)){
                    if(approvalNumber.length() <= 100){
                        goodsExcelVO.setApprovalNumber(approvalNumber);
                        goodsVO.setApprovalNumber(approvalNumber);
                    } else {
                        isOK = false;
                        goodsExcelVO.setApprovalNumber(ExcelErrorCodeEnum.APPROVAL_NUMBER_LENGTH.getName());
                    }

                } else {
                    goodsExcelVO.setApprovalNumber("-");
                    goodsVO.setApprovalNumber("-");
                }

                //有效期至
                if(StringUtils.isNoneBlank(validUntil)){
                    if(!ExcelMethodUtils.checkPattern(validUntil)){
                        isOK = false;
                        goodsExcelVO.setValidUntil(ExcelErrorCodeEnum.DATE_FORMAT.getName());
                    } else {
                        goodsExcelVO.setValidUntil(validUntil);
                        String date = ExcelMethodUtils.getDate(validUntil);
                        goodsExcelVO.setValidUntil(date);
                        goodsVO.setValidUntil(DateUtils.StringToDate(date, DateStyle.YYYY_MM_DD_HH_MM_SS));
                    }
                }
                //经营范围
                if(StringUtils.isNoneBlank(managementScopeName)){
                    //根据品种分类查询对应的经营范围
                    List<BusinessScopeVO> scopeList = businessScopeMap.get(businessVariety);
                    final boolean[] ok = {false};
                    if(scopeList != null){
                        scopeList.forEach(item->{
                            if(managementScopeName.equals(item.getName())){
                                //经营范围
                                goodsVO.setManagementScopeName(managementScopeName);
                                goodsVO.setManagementScopeId(item.getId());
                                ok[0] = true;
                                goodsExcelVO.setManagementScopeName(managementScopeName);
                            }
                        });
                    }
                    if(!ok[0]){
                        isOK = false;
                        goodsExcelVO.setManagementScopeName(ExcelErrorCodeEnum.BUSINESS_SCOPE_INEXISTENCE.getName());
                    }
                } else {
                    isOK = false;
                    goodsExcelVO.setManagementScopeName(ExcelErrorCodeEnum.BLANK.getName());
                }

                //验收分类
                if(StringUtils.isNoneBlank(checkTypeName)){
                    //根据品种分类查询对应的验收分类
                    List<QualitySetVO> qualitySetList = qualitySetMap.get(businessVariety);
                    final boolean[] ok = {false};
                    if(qualitySetList != null){
                        qualitySetList.forEach(item->{
                            if(checkTypeName.equals(item.getDescription())){
                                //验收分类
                                goodsVO.setCheckTypeId(item.getId());
                                goodsVO.setCheckTypeName(checkTypeName);
                                ok[0] = true;
                                goodsExcelVO.setCheckTypeName(checkTypeName);
                            }
                        });
                    }

                    if(!ok[0]){
                        isOK = false;
                        goodsExcelVO.setCheckTypeName(ExcelErrorCodeEnum.CHECK_TYPE_INEXISTENCE.getName());
                    }
                } else {
                    List<QualitySetVO> qualitySetList = qualitySetMap.get(businessVariety);
                    final boolean[] ok = {false};
                    if(qualitySetList != null){
                        for (QualitySetVO item : qualitySetList) {
                            if(BusinessVariety.DRUGS.getName().equals(businessVariety)){
                                if ("国产成药".equals(item.getDescription())) {
                                    //验收分类
                                    goodsVO.setCheckTypeId(item.getId());
                                    goodsVO.setCheckTypeName(item.getDescription());
                                    ok[0] = true;
                                    goodsExcelVO.setCheckTypeName("国产成药");
                                    break;
                                }

                            } else if (BusinessVariety.MEDICAL_APPARATUS.getName().equals(businessVariety) ||
                                    BusinessVariety.FOODS.getName().equals(businessVariety) ||
                                    BusinessVariety.COSMETICS.getName().equals(businessVariety) ||
                                    BusinessVariety.OTHERS.getName().equals(businessVariety)) {
                                //品种类型是医疗器械
                                if(item.getSysType() == SysType.SYSTEM.getCode()){
                                    goodsVO.setCheckTypeId(item.getId());
                                    goodsVO.setCheckTypeName(item.getDescription());
                                    ok[0] = true;
                                    goodsExcelVO.setCheckTypeName(item.getDescription());
                                    break;
                                }

                            }

                        }
                    }
                    if(!ok[0]){
                        isOK = false;
                        goodsExcelVO.setCheckTypeName(ExcelErrorCodeEnum.CHECK_TYPE_INEXISTENCE.getName());
                    }
                }

                //保质期
                if(StringUtils.isNoneBlank(qualityPeriod)){
                    if(!ExcelMethodUtils.isPureDigital(qualityPeriod)){
                        isOK = false;
                        goodsExcelVO.setQualityPeriod(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                    } else {
                        goodsExcelVO.setQualityPeriod(qualityPeriod);
                        goodsVO.setQualityPeriod(Integer.parseInt(qualityPeriod));
                    }
                } else {
                    goodsExcelVO.setQualityPeriod(qualityPeriod);
                    goodsVO.setQualityPeriod(36);
                }
                //保质期单位
                if(StringUtils.isNoneBlank(qualityPeriodUnit)){
                    int val = TimeUnit.getCode(qualityPeriodUnit);
                    if(val == -1){
                        isOK = false;
                        goodsExcelVO.setQualityPeriodUnit(ExcelErrorCodeEnum.TIME_UNIT_INEXISTENCE.getName());
                    } else {
                        goodsVO.setQualityPeriodUnit(val);
                        goodsExcelVO.setQualityPeriodUnit(qualityPeriodUnit);
                    }
                } else {
                    goodsExcelVO.setQualityPeriodUnit(qualityPeriodUnit);
                    goodsVO.setQualityPeriodUnit(TimeUnit.MONTH.getCode());
                }
                //特殊管理药品
                if(StringUtils.isNoneBlank(specialDrug)){
                    if(is_RX_DRUG){
                        int val = SpecialDrugs.getCode(specialDrug);
                        if(val == -1){
                            //不存在该药品
                            isOK = false;
                            goodsExcelVO.setSpecialDrug(ExcelErrorCodeEnum.SPECIAL_DRUGS_INEXISTENCE.getName());
                        } else {
                            String[] drugs = specialDrug.split("-");
                            if(drugs.length == 2){
                                //精神药
                                goodsVO.setSpecialDrug(SpiritDrugs.getCode(drugs[0]));
                                goodsVO.setSpiritDrugType(SpiritDrugsType.getCode(drugs[1]));

                                goodsExcelVO.setSpecialDrug(specialDrug);
                            }
                            if(drugs.length == 1){
                                //
                                goodsVO.setSpecialDrug(SpecialDrugs.getCode(drugs[0]));
//                                goodsVO.setSpiritDrugType(SpiritDrugsType.getCode(drugs[0]));
                                goodsExcelVO.setSpecialDrug(specialDrug);
                            }
                        }
                    } else {
                        isOK = false;
                        goodsExcelVO.setSpecialDrug(ExcelErrorCodeEnum.SPECIAL_DRUGS_CANNOTWRITE.getName());
                    }

                }

                //专门管理药品
                if(StringUtils.isNoneBlank(inChargeDrug)){
//                    if(is_RX_DRUG){
                        int val = ChargeDrugs.getCode(inChargeDrug);
                        if(val == -1){
                            //不存在该药品
                            isOK = false;
                            goodsExcelVO.setInChargeDrug(ExcelErrorCodeEnum.SPECIAL_DRUGS_INEXISTENCE.getName());
                        } else {
                            String[] drugs = inChargeDrug.split("-");
                            if(drugs.length == 2){
                                //含特殊药品复方制剂
                                goodsVO.setInChargeDrug(SpecialCompoundPreparations.getCode(drugs[0]));
                                goodsVO.setFormulationType(SpecialCompoundPreparationsType.getCode(drugs[1]));
                                goodsExcelVO.setInChargeDrug(inChargeDrug);
                            }
                            if(drugs.length == 1){
                                goodsVO.setInChargeDrug(SpecialCompoundPreparations.getCode(drugs[0]));
                                goodsExcelVO.setInChargeDrug(inChargeDrug);
                            }
                        }
//                    } else {
//                        isOK = false;
//                        goodsExcelVO.setInChargeDrug(ExcelErrorCodeEnum.SPECIAL_DRUGS_CANNOTWRITE.getName());
//                    }
                }
                // 限购数量

                if(StringUtils.isNoneBlank(limitQuantity) && !ExcelMethodUtils.isPureDigital(limitQuantity)){
                    isOK = false;
                    goodsExcelVO.setLimitQuantity(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                }
                //医保
                if(StringUtils.isNoneBlank(medicalInsurance)){
                    int val = MedicalFlag.getCode(medicalInsurance);
                    if(val == -1){
                        isOK = false;
                        goodsExcelVO.setMedicalInsurance(medicalInsurance);
                    } else {
                        goodsExcelVO.setMedicalInsurance(medicalInsurance);
                        goodsVO.setMedicalInsurance(val);
                    }
                } else {
                    goodsExcelVO.setMedicalInsurance(MedicalFlag.MEDICAL_NO.getName());
                    goodsVO.setMedicalInsurance(MedicalFlag.MEDICAL_NO.getCode());
                }
                //医保类别
                if(StringUtils.isNoneBlank(medicalInsuranceType)){
                    int val = MedicalFlag.getCode(medicalInsurance);
                    if(val == 1){
                        //是医保药品
                        int val2 = MedicalInsuranceType.getCode(medicalInsuranceType);
                        if(val2 == -1){
                            isOK = false;
                            goodsExcelVO.setMedicalInsuranceType(ExcelErrorCodeEnum.MEDICALINSURANCETYPE_INCONFORMITY.getName());
                        } else {
                            goodsExcelVO.setMedicalInsuranceType(medicalInsuranceType);
                            goodsVO.setMedicalInsuranceType(val2);
                        }
                    }
                } else {
                    int val = MedicalFlag.getCode(medicalInsurance);
                    if(val == 1){
                        isOK = false;
                        goodsExcelVO.setMedicalInsuranceType(ExcelErrorCodeEnum.EDICALINSURANCETYPE_INCONFORMITY2.getName());
                    }
                }

                //医保编号
                if(StringUtils.isNoneBlank(medicalInsuranceCode)){
                    if(medicalInsuranceCode.length() > 30){
                        isOK = false;
                        goodsExcelVO.setMedicalInsuranceCode(ExcelErrorCodeEnum.MAXVALUE.getName());
                    } else {
                        goodsExcelVO.setMedicalInsuranceCode(medicalInsuranceCode);
                        goodsVO.setMedicalInsuranceCode(medicalInsuranceCode);
                    }
                }

                //注册商标
                goodsExcelVO.setRegisteredTrademark(registeredTrademark);
                goodsVO.setRegisteredTrademark(registeredTrademark);
                //品牌
                goodsExcelVO.setBrand(brand);
                goodsVO.setBrand(brand);
                //季节
                goodsExcelVO.setSeason(season);
                goodsVO.setSeason(season);
                //等级
                goodsExcelVO.setGrade(grade);
                goodsVO.setGrade(grade);
                //中包数量
                if(StringUtils.isNoneBlank(inbagQuantity)){
                    if(ExcelMethodUtils.isDecimals(inbagQuantity)){
                        goodsExcelVO.setInbagQuantity(inbagQuantity);
                        goodsVO.getStorageMaintenanceVO().setInbagQuantity(new BigDecimal(inbagQuantity));
                    } else {
                        isOK = false;
                        goodsExcelVO.setInbagQuantity(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                    }
                } else {
                    goodsExcelVO.setInbagQuantity("1");
                    goodsVO.getStorageMaintenanceVO().setInbagQuantity(new BigDecimal(1));
                }
                //大包数量
                if(StringUtils.isNoneBlank(bigbagQuantity)){
                    if(ExcelMethodUtils.isDecimals(bigbagQuantity)){
                        goodsExcelVO.setBigbagQuantity(bigbagQuantity);
                        goodsVO.getStorageMaintenanceVO().setBigbagQuantity(new BigDecimal(bigbagQuantity));
                    } else {
                        isOK = false;
                        goodsExcelVO.setBigbagQuantity(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                    }
                } else {
                    goodsExcelVO.setBigbagQuantity("1");
                    goodsVO.getStorageMaintenanceVO().setBigbagQuantity(new BigDecimal(1));
                }
                //商品性质
                if(StringUtils.isNoneBlank(goodsNature)){
                    int val = GoodsNature.getCode(goodsNature);
                    if(val == -1){
                        //
                        isOK = false;
                        goodsExcelVO.setGoodsNature(ExcelErrorCodeEnum.GOODS_NATURE_INCONFORMITY.getName());
                    }else {
                        goodsExcelVO.setGoodsNature(goodsNature);
                        goodsVO.setGoodsNature(val);
                    }
                } else {
                    goodsExcelVO.setGoodsNature(goodsNature);
                    goodsVO.setGoodsNature(GoodsNature.GOODSNATURE_A.getCode());
                }
                //零售基价
                if(StringUtils.isNoneBlank(retailPrice)){
                    if(ExcelMethodUtils.isDecimals(retailPrice)){
                        goodsExcelVO.setRetailPrice(retailPrice);
                        goodsVO.getGoodsBusinessVO().setRetailPrice(new BigDecimal(retailPrice));
                    } else {
                        isOK = false;
                        goodsExcelVO.setRetailPrice(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                    }
                } else {
                    goodsExcelVO.setRetailPrice("0");
                    goodsVO.getGoodsBusinessVO().setRetailPrice(new BigDecimal(0));
                }

                //配货基价
                if(StringUtils.isNoneBlank(distrPrice)){
                    if(ExcelMethodUtils.isDecimals(distrPrice)){
                        goodsExcelVO.setDistrPrice(distrPrice);
                        goodsVO.getGoodsBusinessVO().setDistrPrice(new BigDecimal(distrPrice));
                    } else {
                        isOK = false;
                        goodsExcelVO.setDistrPrice(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                    }
                } else {
                    goodsExcelVO.setDistrPrice("0");
                    goodsVO.getGoodsBusinessVO().setDistrPrice(new BigDecimal(0));
                }
                //configurationFlag
                if(StringUtils.isNoneBlank(configurationFlag)){
                    int val = ConfigurationFlag.getCode(configurationFlag);
                    if(val == -1){
                        isOK = false;
                        goodsExcelVO.setConfigurationFlag(ExcelErrorCodeEnum.CONFIGURATIONFLAG_INCONFORMITY.getName());
                    } else{
                        goodsExcelVO.setConfigurationFlag(configurationFlag);
                        goodsVO.setConfigurationFlag(val);
                    }
                } else {
                    goodsVO.setConfigurationFlag(ConfigurationFlag.CONFIGURATION_NO.getCode());
                }

                // 配货标识（0-普通；1-首推），默认为0-普通
                if(userVO.getChainType() == ChainType.Headquarters.getCode()){
                    if(StringUtils.isNoneBlank(distrFlag)){
                        int val = DistrFlag.getCode(distrFlag);
                        if(val == -1){
                            isOK = false;
                            goodsExcelVO.setDistrFlag(ExcelErrorCodeEnum.DISTR_FLAG_INEXISTENCE.getName());
                        } else {
                            goodsExcelVO.setDistrFlag(distrFlag);
                            goodsVO.setDistrFlag(val);
                        }
                    } else {
                        goodsExcelVO.setDistrFlag(distrFlag);
                        goodsVO.setDistrFlag(DistrFlag.DISTRFLAG_A.getCode());
                    }
                } else {
                    goodsExcelVO.setDistrFlag(DistrFlag.DISTRFLAG_A.getName());
                    goodsVO.setDistrFlag(DistrFlag.DISTRFLAG_A.getCode());
                }

                //状态
                if(StringUtils.isNoneBlank(status)){
                    int val = EnableStatus.getCode(status);
                    if(val == -1){
                        isOK = false;
                        goodsExcelVO.setStatus(ExcelErrorCodeEnum.ANY_INEXISTENCE.getName());
                    } else{
                        goodsExcelVO.setStatus(status);
                        goodsVO.setStatus(val);
                    }
                } else {
                    goodsExcelVO.setStatus(status);
                    goodsVO.setStatus(EnableStatus.ENABLE.getStatus());
                }

                //validFlag
                if(StringUtils.isNoneBlank(validFlag)){
                    int val = ValidFlag.getCode(validFlag);
                    if(val == -1){
                        isOK = false;
                        goodsExcelVO.setValidFlag(ExcelErrorCodeEnum.ANY_INEXISTENCE.getName());
                    } else{
                        goodsExcelVO.setValidFlag(validFlag);
                        goodsVO.setValidFlag(val);
                    }
                } else {
                    goodsExcelVO.setValidFlag(validFlag);
                    goodsVO.setValidFlag(ValidFlag.NORMAL.getCode());
                }

                //备注
                if(StringUtils.isNoneBlank(remark)){
                    if(remark.length() <= 200){
                        goodsExcelVO.setRemark(remark);
                        goodsVO.setRemark(remark);
                    } else {
                        isOK = false;
                        goodsExcelVO.setRemark(ExcelErrorCodeEnum.REMARK_LENGTH.getName());
                    }
                }

                //进项税
                if(StringUtils.isNoneBlank(purchaseTaxRate)){
                    if(purchaseTaxRate.endsWith("%")){
                        purchaseTaxRate = purchaseTaxRate.substring(0,purchaseTaxRate.lastIndexOf('%'));
                    }
                    String purchaseTaxRate1 = purchaseTaxRate;
                    if(ExcelMethodUtils.isDecimals(purchaseTaxRate1)){
                        final boolean[] ok = {false};
                        taxRateVOList.forEach(item->{
                            BigDecimal rate = item.getTaxRate();
                            if(rate.compareTo(new BigDecimal(purchaseTaxRate1)) == 0){
                                goodsExcelVO.setPurchaseTaxRate(purchaseTaxRate1 + "%");
                                goodsVO.getGoodsBusinessVO().setPurchaseTaxRate(new BigDecimal(purchaseTaxRate1));
                                ok[0] = true;

                                Optional<GoodsTaxRateVO> first1 = taxRateVOList.stream().filter(it -> it.getTaxRate().compareTo(new BigDecimal(purchaseTaxRate1)) == 0).findFirst();
                                if(first1.isPresent()){
                                    goodsVO.getGoodsBusinessVO().setPurchaseTaxRateId(first1.get().getId());
                                } else {
                                    ok[0] = false;
                                    goodsExcelVO.setPurchaseTaxRate(ExcelErrorCodeEnum.ANY_INEXISTENCE.getName());
                                }
                            }
                        });
                        if(!ok[0]){
                            isOK = false;
                            goodsExcelVO.setPurchaseTaxRate(ExcelErrorCodeEnum.ANY_INEXISTENCE.getName());
                        }

                    } else {
                        isOK = false;
                        goodsExcelVO.setPurchaseTaxRate(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                    }
                } else {
                    goodsExcelVO.setPurchaseTaxRate("0%");
                    goodsVO.getGoodsBusinessVO().setPurchaseTaxRate(new BigDecimal(0));
                    Optional<GoodsTaxRateVO> first1 = taxRateVOList.stream().filter(it -> it.getTaxRate().compareTo(new BigDecimal(0)) == 0).findFirst();
                    if(first1.isPresent()){
                        goodsVO.getGoodsBusinessVO().setPurchaseTaxRateId(first1.get().getId());
                    } else {
                        throw new BusinessException("系统中不存在0税率");
                    }

                }
                //经营方式
                if(StringUtils.isNoneBlank(managementMode)){

                    if(ManagementMode.getCode(managementMode) == -1){
                        isOK = false;
                        goodsExcelVO.setManagementMode(ExcelErrorCodeEnum.ANY_INEXISTENCE.getName());
                    } else {
                        goodsExcelVO.setManagementMode(managementMode);
                        goodsVO.getGoodsBusinessVO().setManagementMode(ManagementMode.getCode(managementMode));
                    }

                } else {
                    goodsExcelVO.setManagementMode(ManagementMode.PURCHASE_SALE.getName());
                    goodsVO.getGoodsBusinessVO().setManagementMode(ManagementMode.PURCHASE_SALE.getCode());
                }

//                首营品种
                if("是".equals(first)){
                    goodsVO.setFirst(1);
                    goodsExcelVO.setFirst(first);
                } else {
                    goodsVO.setFirst(0);
                    goodsExcelVO.setFirst(first);
                }


                //申请人员
                if(StringUtils.isNoneBlank(applicantName)){
                    Optional<UserDataVO> userDataVO = userDataVOList.stream().filter(item -> applicantName.equals(item.getName())).findFirst();
                    if(userDataVO.isPresent()){
                        goodsExcelVO.setApplicantName(applicantName);
                        goodsVO.setApplicantName(applicantName);
                        goodsVO.setApplicantCode(userDataVO.get().getCode());
                        goodsVO.setApplicantId(userDataVO.get().getId());
                    } else {
                        isOK = false;
                        goodsExcelVO.setApplicantName(ExcelErrorCodeEnum.USER_NOT_EXSIST.getName());
                    }

                } else {
                    goodsExcelVO.setApplicantName(userVO.getUserName());
                    goodsVO.setApplicantName(userVO.getUserName());
                    goodsVO.setApplicantCode(userVO.getUserCode());
                    goodsVO.setApplicantId(userVO.getUserId());
                }

                if(StringUtils.isNoneBlank(applicationTime)){
                    if(ExcelMethodUtils.checkPattern(applicationTime)){
                        //合格
                        String date = ExcelMethodUtils.getDate(applicationTime);
                        goodsExcelVO.setApplicationTime(date);
                        goodsVO.setApplicationTime(DateUtils.StringToDate(date, DateStyle.YYYY_MM_DD_HH_MM_SS));
                    } else {
                        isOK = false;
                        goodsExcelVO.setApplicationTime(ExcelErrorCodeEnum.TIME_UNIT_INEXISTENCE.getName());
                    }
                } else {
                    goodsExcelVO.setApplicationTime(DateUtils.getDate(new Date()));
                    goodsVO.setApplicationTime(new Date());
                    goodsExcelVO.setApplicationOpinion(applicationOpinion);
                    goodsVO.setApplicationOpinion(applicationOpinion);
                }
                //销项税
                if(StringUtils.isNoneBlank(saleTaxRate)){
                    if(saleTaxRate.endsWith("%")){
                        saleTaxRate = saleTaxRate.substring(0,saleTaxRate.lastIndexOf('%'));
                    }
                    String saleTaxRate1 = saleTaxRate;
                    if(ExcelMethodUtils.isDecimals(saleTaxRate1)){
                        final boolean[] ok = {false};
                        taxRateVOList.forEach(item->{
                            BigDecimal rate = item.getTaxRate();
                            if(rate.compareTo(new BigDecimal(saleTaxRate1)) == 0){
                                goodsExcelVO.setSaleTaxRate(saleTaxRate1 + "%");

                                goodsVO.getGoodsBusinessVO().setSaleTaxRate(new BigDecimal(saleTaxRate1));
                                Optional<GoodsTaxRateVO> first1 = taxRateVOList.stream().filter(it -> it.getTaxRate().compareTo(new BigDecimal(saleTaxRate1)) == 0).findFirst();
                                if(first1.isPresent()){
                                    goodsVO.getGoodsBusinessVO().setSaleTaxRateId(first1.get().getId());
                                } else {
                                    ok[0] = false;
                                    goodsExcelVO.setSaleTaxRate(ExcelErrorCodeEnum.ANY_INEXISTENCE.getName());
                                }
                                ok[0] = true;
                            }
                        });
                        if(!ok[0]){
                            isOK = false;
                            goodsExcelVO.setSaleTaxRate(ExcelErrorCodeEnum.ANY_INEXISTENCE.getName());
                        }

                    } else {
                        isOK = false;
                        goodsExcelVO.setSaleTaxRate(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                    }
                } else {
                    goodsExcelVO.setSaleTaxRate("0%");
                    goodsVO.getGoodsBusinessVO().setSaleTaxRate(new BigDecimal(0));
                    Optional<GoodsTaxRateVO> first1 = taxRateVOList.stream().filter(it -> it.getTaxRate().compareTo(new BigDecimal(0)) == 0).findFirst();
                    if(first1.isPresent()){
                        goodsVO.getGoodsBusinessVO().setSaleTaxRateId(first1.get().getId());
                    } else {
                        throw new BusinessException("系统中不存在0税率");
                    }

                }

                //配货税
                if(StringUtils.isNotBlank(distrTaxRate)){
                    if(distrTaxRate.endsWith("%")){
                        distrTaxRate = distrTaxRate.substring(0,distrTaxRate.lastIndexOf('%'));
                    }
                    String distrTaxRate1 = distrTaxRate;
                    if(ExcelMethodUtils.isDecimals(distrTaxRate1)){
                        final boolean[] ok = {false};
                        taxRateVOList.forEach(item->{
                            BigDecimal rate = item.getTaxRate();
                            if(rate.compareTo(new BigDecimal(distrTaxRate1)) == 0){
                                goodsExcelVO.setDistrTaxRate(distrTaxRate1 + "%");
                                goodsVO.getGoodsBusinessVO().setDistrTaxRate(new BigDecimal(distrTaxRate1));
                                ok[0] = true;

                                Optional<GoodsTaxRateVO> first1 = taxRateVOList.stream().filter(it -> it.getTaxRate().compareTo(new BigDecimal(distrTaxRate1)) == 0).findFirst();
                                if(first1.isPresent()){
                                    goodsVO.getGoodsBusinessVO().setDistrTaxRateId(first1.get().getId());
                                } else {
                                    ok[0] = false;
                                    goodsExcelVO.setDistrTaxRate(ExcelErrorCodeEnum.ANY_INEXISTENCE.getName());
                                }
                            }
                        });
                        if(!ok[0]){
                            isOK = false;
                            goodsExcelVO.setDistrTaxRate(ExcelErrorCodeEnum.ANY_INEXISTENCE.getName());
                        }

                    } else {
                        isOK = false;
                        goodsExcelVO.setDistrTaxRate(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                    }
                }else {
                    goodsExcelVO.setDistrTaxRate("0%");
                    goodsVO.getGoodsBusinessVO().setDistrTaxRate(new BigDecimal(0));
                    Optional<GoodsTaxRateVO> first1 = taxRateVOList.stream().filter(it -> it.getTaxRate().compareTo(new BigDecimal(0)) == 0).findFirst();
                    if(first1.isPresent()){
                        goodsVO.getGoodsBusinessVO().setDistrTaxRateId(first1.get().getId());
                    } else {
                        throw new BusinessException("系统中不存在0税率");
                    }

                }

//                特价商品
                if("是".equals(bargainGoods)){
                    goodsExcelVO.setBargainGoods("是");
                    goodsVO.getGoodsBusinessVO().setBargainGoods(1);
                } else {
                    goodsExcelVO.setBargainGoods("否");
                    goodsVO.getGoodsBusinessVO().setBargainGoods(0);
                }

                //积分商品
                if("是".equals(integralGoods)){
                    goodsExcelVO.setIntegralGoods("是");
                    goodsVO.getGoodsBusinessVO().setIntegralGoods(1);
                } else {
                    goodsExcelVO.setIntegralGoods("否");
                    goodsVO.getGoodsBusinessVO().setIntegralGoods(0);
                }

                //积分商品倍数*
                if(StringUtils.isNoneBlank(integralMultiple)){
                    if(integralMultiple.endsWith("%")){
                        integralMultiple = integralMultiple.substring(0,integralMultiple.lastIndexOf('%'));
                    }
                    goodsVO.getGoodsBusinessVO().setIntegralMultiple(new BigDecimal(integralMultiple));
                    goodsExcelVO.setIntegralMultiple(integralMultiple);
                } else {
                    goodsVO.getGoodsBusinessVO().setIntegralMultiple(new BigDecimal(100));
                    goodsExcelVO.setIntegralMultiple(integralMultiple);
                }
                //pricingPolicy
                //销售定价 销售定价策略：0总部统一定价,1允许门店自主定价.总部添加的商品默
//                认总部统一定价， 加盟店添加的商品默认允许门店自主定价
                if(userVO.getChainType() == ChainType.Headquarters.getCode()){
                    //总部
                    if(StringUtils.isNoneBlank(pricingPolicy)){
                        int val = PricingPolicyType.getCode(pricingPolicy);
                        if(val == -1){
                            isOK = false;
                            goodsExcelVO.setPricingPolicy(ExcelErrorCodeEnum.PRICINGPOLICYTYPE_INEXISTENCE.getName());
                        } else {
                            goodsExcelVO.setPricingPolicy(pricingPolicy);
                            goodsVO.getGoodsBusinessVO().setPricingPolicy(val);
                        }
                    } else {
                        goodsExcelVO.setPricingPolicy(pricingPolicy);
                        goodsVO.getGoodsBusinessVO().setPricingPolicy(PricingPolicyType.HEADQUARTERS_UNIFORM_PRICING.getCode());
                    }
                } else {
                    goodsExcelVO.setPricingPolicy(PricingPolicyType.AUTOMATICALLY_PRICED_STORES.getName());
                    goodsVO.getGoodsBusinessVO().setPricingPolicy(PricingPolicyType.AUTOMATICALLY_PRICED_STORES.getCode());
                }

                //配送方式
                if(StringUtils.isNoneBlank(deliveryMode)){
                    int val = DeliveryMode.getCode(deliveryMode);
                    if(val == -1){
                        isOK = false;
                        goodsExcelVO.setDeliveryMode(ExcelErrorCodeEnum.ANY_INEXISTENCE.getName());
                    } else {
                        goodsExcelVO.setDeliveryMode(deliveryMode);
                        goodsVO.getStorageMaintenanceVO().setDeliveryMode(val);
                    }
                } else {
                    goodsExcelVO.setDeliveryMode("配送中心配送");
                    goodsVO.getStorageMaintenanceVO().setDeliveryMode(0);
                }
                //最小采购批量
                if(StringUtils.isNoneBlank(minimumPurchasingBatch)){
                    if(ExcelMethodUtils.isDecimals(minimumPurchasingBatch) || ExcelMethodUtils.isPureDigital(minimumPurchasingBatch)){
                        goodsExcelVO.setMinimumPurchasingBatch(minimumPurchasingBatch);
                        goodsVO.getStorageMaintenanceVO().setMinimumPurchasingBatch(new BigDecimal(minimumPurchasingBatch));
                    } else {
                        isOK = false;
                        goodsExcelVO.setMinimumPurchasingBatch(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                    }
                } else {
                    goodsExcelVO.setMinimumPurchasingBatch("1");
                    goodsVO.getStorageMaintenanceVO().setMinimumPurchasingBatch(new BigDecimal(1));
                }
//                goodsExcelVO.setMinimumDistributionBatch(minimumPurchasingBatch);
//                goodsVO.getStorageMaintenanceVO().setMinimumPurchasingBatch(new BigDecimal(minimumPurchasingBatch));
                //最小配送批量*
                if(StringUtils.isNoneBlank(minimumDistributionBatch)){
                    if(ExcelMethodUtils.isDecimals(minimumDistributionBatch) || ExcelMethodUtils.isPureDigital(minimumDistributionBatch)){
                        goodsExcelVO.setMinimumDistributionBatch(minimumDistributionBatch);
                        goodsVO.getStorageMaintenanceVO().setMinimumDistributionBatch(new BigDecimal(minimumDistributionBatch));
                    } else {
                        isOK = false;
                        goodsExcelVO.setMinimumDistributionBatch(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                    }
                } else {
                    goodsExcelVO.setMinimumDistributionBatch("1");
                    goodsVO.getStorageMaintenanceVO().setMinimumDistributionBatch(new BigDecimal(1));
                }
//                goodsExcelVO.setMinimumDistributionBatch(minimumDistributionBatch);
//                goodsVO.getStorageMaintenanceVO().setMinimumDistributionBatch(
//                        new BigDecimal(minimumDistributionBatch)
//                );
//                储藏温度
                if(StringUtils.isNoneBlank(storageTemp)){
                    int val = StorageTemp.getCode(storageTemp);
                    if(val == -1){
                        isOK = false;
                        goodsExcelVO.setStorageTemp(ExcelErrorCodeEnum.ANY_INEXISTENCE.getName());
                    } else {
                        goodsExcelVO.setStorageTemp(storageTemp);
                        goodsVO.setStorageTemp(val);
                    }
                } else {
                    goodsVO.setStorageTemp(StorageTemp.STORAGETEMP_A.getCode());
                }
//                近效期周期
                if(ExcelMethodUtils.isPureDigital(nearEffectPeriod)){
                    goodsExcelVO.setNearEffectPeriod(nearEffectPeriod);
                    goodsVO.getStorageMaintenanceVO().setNearEffectPeriod(Integer.parseInt(nearEffectPeriod));
                } else {
                    goodsExcelVO.setNearEffectPeriod("180");
                    goodsVO.getStorageMaintenanceVO().setNearEffectPeriod(180);
                }

//                周期类型*
                if(StringUtils.isNoneBlank(nearEffectPeriodUnit)){
                    if(TimeUnit.getCode(nearEffectPeriodUnit) != -1){
                        goodsExcelVO.setNearEffectPeriodUnit(nearEffectPeriodUnit);
                        goodsVO.getStorageMaintenanceVO().setNearEffectPeriodUnit(TimeUnit.getCode(nearEffectPeriodUnit));
                    } else {
                        isOK = false;
                        goodsExcelVO.setNearEffectPeriodUnit(ExcelErrorCodeEnum.PERIOD_NOT_EXSIST.getName());
                    }
                } else {
                    goodsExcelVO.setNearEffectPeriodUnit(TimeUnit.DAY.getName());
                    goodsVO.getStorageMaintenanceVO().setNearEffectPeriodUnit(TimeUnit.DAY.getCode());
                }

//                滞销周期
                if(StringUtils.isNoneBlank(unsalableCycle)){
                    if(ExcelMethodUtils.isPureDigital(unsalableCycle)){
                        goodsExcelVO.setUnsalableCycle(unsalableCycle);
                        goodsVO.getStorageMaintenanceVO().setUnsalableCycle(Integer.parseInt(unsalableCycle));
                    } else {
                        isOK = false;
                        goodsExcelVO.setUnsalableCycle(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                    }

                } else {
                    goodsExcelVO.setUnsalableCycle("90");
                    goodsVO.getStorageMaintenanceVO().setUnsalableCycle(90);
                }
//                滞销周期d单位类型
                if(StringUtils.isNoneBlank(unsalableCycleUnit)){
                    if(TimeUnit.getCode(unsalableCycleUnit) != -1){
                        goodsExcelVO.setUnsalableCycleUnit(unsalableCycleUnit);
                        goodsVO.getStorageMaintenanceVO().setUnsalableCycleUnit(TimeUnit.getCode(unsalableCycleUnit));
                    } else {
                        isOK = false;
                        goodsExcelVO.setUnsalableCycleUnit(ExcelErrorCodeEnum.PERIOD_NOT_EXSIST.getName());
                    }
                } else {
                    goodsExcelVO.setUnsalableCycleUnit(TimeUnit.DAY.getName());
                    goodsVO.getStorageMaintenanceVO().setUnsalableCycleUnit(TimeUnit.DAY.getCode());
                }
//                养护类型*
                if(StringUtils.isNoneBlank(maintenanceType)){
                    if(MaintenanceType.getCode(maintenanceType) != -1){
                        goodsExcelVO.setMaintenanceType(maintenanceType);
                        goodsVO.getStorageMaintenanceVO().setMaintenanceType(MaintenanceType.getCode(maintenanceType));
                    } else {
                        isOK = false;
                        goodsExcelVO.setMaintenanceType(ExcelErrorCodeEnum.MAINTENANCETYPE_NOT_EXSIST.getName());
                    }
                } else {
                    goodsExcelVO.setMaintenanceType(MaintenanceType.DELIVERYMODE_A.getName());
                    goodsVO.getStorageMaintenanceVO().setMaintenanceType(MaintenanceType.DELIVERYMODE_A.getCode());
                }

                //养护周期
                if(StringUtils.isNoneBlank(maintenanceCycle)){
                    if(ExcelMethodUtils.isPureDigital(maintenanceCycle)){
                        goodsExcelVO.setMaintenanceCycle(maintenanceCycle);
                        goodsVO.getStorageMaintenanceVO().setMaintenanceCycle(Integer.parseInt(maintenanceCycle));
                    } else {
                        isOK = false;
                        goodsExcelVO.setMaintenanceCycle(ExcelErrorCodeEnum.NUMBER_FORMAT.getName());
                    }
                } else {
                    if(MaintenanceType.getCode(maintenanceType) != -1){
                        if(maintenanceType.equals(MaintenanceType.DELIVERYMODE_A)){
                            goodsExcelVO.setMaintenanceCycle("90");
                            goodsVO.getStorageMaintenanceVO().setMaintenanceCycle(90);
                        } else if(maintenanceType.equals(MaintenanceType.DELIVERYMODE_B)){
                            goodsExcelVO.setMaintenanceCycle("30");
                            goodsVO.getStorageMaintenanceVO().setMaintenanceCycle(30);
                        }
                    } else {
                        goodsExcelVO.setMaintenanceCycle("90");
                        goodsVO.getStorageMaintenanceVO().setMaintenanceCycle(90);
                    }

                }

                goodsVO.getStorageMaintenanceVO().setMaintenanceCycleUnit(TimeUnit.getCode(maintenanceCycleUnit));
                if(StringUtils.isNoneBlank(maintenanceCycleUnit)){
                    if(TimeUnit.getCode(maintenanceCycleUnit) != -1){
                        goodsExcelVO.setMaintenanceCycleUnit(maintenanceCycleUnit);
                        goodsVO.getStorageMaintenanceVO().setMaintenanceCycleUnit(TimeUnit.getCode(maintenanceCycleUnit));
                    } else {
                        isOK = false;
                        goodsExcelVO.setMaintenanceCycleUnit(ExcelErrorCodeEnum.PERIOD_NOT_EXSIST.getName());
                    }
                } else {
                    goodsExcelVO.setMaintenanceCycleUnit(TimeUnit.DAY.getName());
                    goodsVO.getStorageMaintenanceVO().setMaintenanceCycleUnit(TimeUnit.DAY.getCode());
                }
                if(isOK){
                    qualifiedList.add(goodsExcelVO);
                    qualifiedGVOList.add(goodsVO);
                } else {
                    disqualificationList.add(goodsExcelVO);
                }
            }
        }

    }

    public List<GoodsTaxRateVO> getTaxRateVOList() {
        return taxRateVOList;
    }

    public void setTaxRateVOList(List<GoodsTaxRateVO> taxRateVOList) {
        this.taxRateVOList = taxRateVOList;
    }

    public List<GoodsCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<GoodsCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public List<GoodsExcelVO> getQualifiedList() {
        return qualifiedList;
    }

    public void setQualifiedList(List<GoodsExcelVO> qualifiedList) {
        this.qualifiedList = qualifiedList;
    }

    public List<GoodsExcelVO> getDisqualificationList() {
        return disqualificationList;
    }

    public void setDisqualificationList(List<GoodsExcelVO> disqualificationList) {
        this.disqualificationList = disqualificationList;
    }

    public List<GoodsUnitVO> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<GoodsUnitVO> unitList) {
        this.unitList = unitList;
    }

    public static Logger getLogger() {
        return logger;
    }


    public boolean isCustomCode() {
        return isCustomCode;
    }

    public void setCustomCode(boolean customCode) {
        isCustomCode = customCode;
    }


    public List<GoodsDosageVO> getDosageList() {
        return dosageList;
    }

    public void setDosageList(List<GoodsDosageVO> dosageList) {
        this.dosageList = dosageList;
    }


    public List<GoodsVO> getQualifiedGVOList() {
        return qualifiedGVOList;
    }

    public void setQualifiedGVOList(List<GoodsVO> qualifiedGVOList) {
        this.qualifiedGVOList = qualifiedGVOList;
    }

    public Map<String, List<BusinessScopeVO>> getBusinessScopeMap() {
        return businessScopeMap;
    }

    public void setBusinessScopeMap(Map<String, List<BusinessScopeVO>> businessScopeMap) {
        this.businessScopeMap = businessScopeMap;
    }

    public Map<String, List<QualitySetVO>> getQualitySetMap() {
        return qualitySetMap;
    }

    public void setQualitySetMap(Map<String, List<QualitySetVO>> qualitySetMap) {
        this.qualitySetMap = qualitySetMap;
    }



    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public List<UserDataVO> getUserDataVOList() {
        return userDataVOList;
    }

    public void setUserDataVOList(List<UserDataVO> userDataVOList) {
        this.userDataVOList = userDataVOList;
    }

    public Integer getGoodsCodeRule() {
        return goodsCodeRule;
    }

    public void setGoodsCodeRule(Integer goodsCodeRule) {
        this.goodsCodeRule = goodsCodeRule;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }
}