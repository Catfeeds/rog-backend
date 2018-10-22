package com.rograndec.feijiayun.chain.business.retail.prescription.service.impl;

import com.google.common.collect.Sets;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.vo.UserForPrescVO;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.dao.PharmacySetMapper;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.entity.PharmacySet;
import com.rograndec.feijiayun.chain.business.goods.pharmacy.vo.PharmacySetForPrescVO;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.goods.set.entity.GoodsTaxRate;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.member.info.dao.MemberInfoMapper;
import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.member.info.vo.MemberForPrescVO;
import com.rograndec.feijiayun.chain.business.retail.prescription.dao.*;
import com.rograndec.feijiayun.chain.business.retail.prescription.entity.*;
import com.rograndec.feijiayun.chain.business.retail.prescription.service.PrescriptionRegisterService;
import com.rograndec.feijiayun.chain.business.retail.prescription.vo.*;
import com.rograndec.feijiayun.chain.business.retail.pricing.vo.SelectPricingGoodsVO;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleDetailMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.dao.SaleMapper;
import com.rograndec.feijiayun.chain.business.retail.saleflow.entity.Sale;
import com.rograndec.feijiayun.chain.business.retail.saleflow.vo.SaleDetailForPrescrVO;
import com.rograndec.feijiayun.chain.business.retail.saleflow.vo.SaleForPrescrVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.CalculateComponent;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.ManageConfigComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.PrescriptionStatus;
import com.rograndec.feijiayun.chain.common.model.CalculateResultModel;
import com.rograndec.feijiayun.chain.common.vo.GoodsParamStockVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import com.rograndec.feijiayun.chain.utils.string.StringUtil;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能描述：处方登记service
 * Created by ST on 2017/9/22 14:51
 */
@Service
public class PrescriptionRegisterServiceImpl implements PrescriptionRegisterService {



    @Autowired
    EnterpriseBusinessMapper enterpriseBusinessMapper;

    @Autowired
    private PrescriptionRegisterMapper prescriptionRegisterMapper;


    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private PrescriptionRegisterDetailMapper prescriptionRegisterDetailMapper;

    @Autowired
    private PrescriptionRegisterShelfMapper prescriptionRegisterShelfMapper;

    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;

    @Autowired
    private MemberInfoMapper memberInfoMapper;

    @Autowired
    private PharmacySetMapper pharmacySetMapper;

    @Autowired
    private PrescriptionSignatureMapper signatureMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private SaleDetailMapper saleDetailMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private ManageConfigComponent manageConfigComponent;

    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private PrescriptionSignatureDetailMapper signatureDetailMapper;

    @Autowired
    private LotNumberMapper lotNumberMapper;

    @Autowired
    private CommonComponent commonComponent;


    /**
     * 处方登记列表查询
     *
     * @param page
     * @param userVO
     */
    @Override
    public void getPrescriptionList(Page<List<ResponsePrescriptionRegisterForListVO>> page, RequestParamForListVO paramForListVO, UserVO userVO) {
        Integer isSuper = userVO.getChainType() == 0 ? 0 : 1;
        List<ResponsePrescriptionRegisterForListVO> prescriptionList = prescriptionRegisterMapper.getPrescriptionList(paramForListVO, userVO.getEnterpriseId(),isSuper);
        page.setResult(prescriptionList);
        page.setTotalRecord(prescriptionRegisterMapper.getCountPrescriptionList(paramForListVO,userVO.getEnterpriseId(),isSuper));
    }



    /**
     * 根据处方登记id 查询详情
     *
     * @param id
     * @return
     */
    @Override
    public ResponsePrescriptionRegisterForDetailVO getDetailById(Long id,Long enterpriseId) {
        PrescriptionRegister prescriptionRegister = prescriptionRegisterMapper.selectByPrimaryKey(id);
        if(prescriptionRegister.getPrescriptionType() == PrescriptionType.TCM.getCode()){
            //中药
            return prescriptionRegisterMapper.getRegisterDetailTCMById(id,null);
        } else{
            return prescriptionRegisterMapper.getRegisterDetailById(id,null);
        }


    }

//    /**
//     * 商品查询
//     * @param enterpriseId
//     * @param param
//     * @return
//     */
//    @Override
//    public List<GoodsInfoAndStockVO> getGoodsInfoForStock(Long enterpriseId, String param) {
//        GoodsParamStockVO paramVO = new GoodsParamStockVO();
//        paramVO.setEnterpriseId(enterpriseId);
//        paramVO.setParam(param);
//        return goodsComponent.getGoodsInfoForStock(paramVO);
//    }

//    /**
//     * 查询商品的货位信息
//     * @param enterpriseId
//     * @param goodsId
//     * @return
//     */
//    @Override
//    public List<GoodsInfoStockShelfVO> getGoodsStockInfoByGoodsId(Long enterpriseId, Long goodsId) {
//        return stockMapper.getGoodsStockInfoByGoodsId(enterpriseId,goodsId);
//    }

    /**
     * 处方登记新增
     *
     * @param registerForAddUpdateVO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addPrescription(RequestPrescriptionRegisterForAddUpdateVO registerForAddUpdateVO,UserVO userVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();

        PrescriptionRegister prescriptionRegister = new PrescriptionRegister();
        BeanUtils.copyProperties(registerForAddUpdateVO,prescriptionRegister);
        PrescriptionRegister prescriptionRegisterDefault = (PrescriptionRegister) EntityUtils.reflectAddSetDefaultValue(PrescriptionRegister.class,userVO);

        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(prescriptionRegisterDefault,prescriptionRegister);

        Long memberId = registerForAddUpdateVO.getMemberId();//会员id
        checkMember(prescriptionRegister, memberId);

        Integer prescriptionType = prescriptionRegister.getPrescriptionType();
        if(PrescriptionType.TCM.getCode() == prescriptionType){
            //中药
            pharmacySet(prescriptionRegister,registerForAddUpdateVO);

        }
        Long baseOrderId = registerForAddUpdateVO.getBaseOrderId();
        if(baseOrderId != null){
            Sale sale = saleMapper.selectByPrimaryKey(baseOrderId);
            prescriptionRegister.setBaseOrderCode(sale.getCode());
            prescriptionRegister.setBaseOrderType(sale.getOrderType());
            prescriptionRegister.setBaseOrderDate(sale.getSaleDate());
        }
        prescriptionRegister.setOrderType(OrderRule.PRESCRIPTION_REGISTER.getType());
        //处方登记单号
        String code = orderCodeComponent.generate(OrderRule.PRESCRIPTION_REGISTER.getCodePrefix(),userVO.getEnterpriseId(),userVO.getEnterpriseCode());
        prescriptionRegister.setCode(code);
        prescriptionRegister.setStatus(PrescriptionStatus.PENDING_AUDIT);
        //登记人员
        ManageConfig mangeConfig = manageConfigComponent.getMangeConfigByEPId(userVO);
        addRegisterManInfo(userVO, prescriptionRegister, mangeConfig);

        prescriptionRegister.setProfitRate(BigDecimal.ZERO);
        prescriptionRegister.setNotaxProfitRate(BigDecimal.ZERO);
        prescriptionRegister.setProfitTotal(BigDecimal.ZERO);
        prescriptionRegister.setNotaxProfitTotal(BigDecimal.ZERO);

        prescriptionRegister.setRealAmountTotal(BigDecimal.ZERO);
        prescriptionRegister.setNotaxRealAmountTotal(BigDecimal.ZERO);
        prescriptionRegister.setTaxAmountTotal(BigDecimal.ZERO);
        prescriptionRegister.setAmountTotal(BigDecimal.ZERO);
        //商品数量
        prescriptionRegister.setQuantityTotal(BigDecimal.ZERO);
        prescriptionRegister.setVarietiesQuantity(0);
        prescriptionRegister.setId(null);
        prescriptionRegisterMapper.insertSelective(prescriptionRegister);

        BigDecimal amountTotal = BigDecimal.ZERO;//金额合计（整单优惠前的金额合计）
        BigDecimal wholeDiscount = prescriptionRegister.getWholeDiscount();//整单折扣

        List<RequestPrescriptionRegisterDetailForAddUpdateVO> detailForAddUpdateVOS = registerForAddUpdateVO.getRegisterDetailForAddUpdateVOList();
        for(RequestPrescriptionRegisterDetailForAddUpdateVO vo : detailForAddUpdateVOS){
            /**.
             * 金额（整单优惠前金额）
             */
            BigDecimal amount = calcLineDiscount(vo);
            amountTotal = amountTotal.add(amount);
        }
        BigDecimal wholeDiscountAmount = registerForAddUpdateVO.getWholeDiscountAmount();//整单优惠金额

        BigDecimal realAmountTotal = BigDecimal.ZERO;//实际金额合计
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;//不含税金额合计
        BigDecimal taxAmountTotal = BigDecimal.ZERO;//税额合计

        BigDecimal allQuantity = BigDecimal.ZERO;

        for(RequestPrescriptionRegisterDetailForAddUpdateVO detailForAddUpdateVO : detailForAddUpdateVOS){
            PrescriptionRegisterDetail registerDetail = (PrescriptionRegisterDetail) EntityUtils.reflectAddSetDefaultValue(PrescriptionRegisterDetail.class,userVO);
            //商品冗余属性
            Long goodsId = detailForAddUpdateVO.getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            packGoodsInfo(registerDetail, goodsId, goods);
            if(PrescriptionType.TCM.getCode() != prescriptionType){
                pharmacySetNoTCM(registerDetail,detailForAddUpdateVO);
            }

            allQuantity = allQuantity.add(detailForAddUpdateVO.getQuantity());
            registerDetail.setSingleDose(detailForAddUpdateVO.getSingleDose());
            registerDetail.setRegisterId(prescriptionRegister.getId());
            registerDetail.setRegisterCode(prescriptionRegister.getCode());
            registerDetail.setRegisterDate(prescriptionRegister.getRegisterDate());
            registerDetail.setOrderType(OrderRule.PRESCRIPTION_REGISTER.getType());


            //利润为0
            registerDetail.setProfit(BigDecimal.ZERO);
            registerDetail.setNotaxProfit(BigDecimal.ZERO);
            registerDetail.setProfitRate(BigDecimal.ZERO);
            registerDetail.setNotaxProfitRate(BigDecimal.ZERO);

            BigDecimal quantity = detailForAddUpdateVO.getQuantity();//数量
            verifyQuantity(userVO, goodsId, goods, quantity);

            BigDecimal unitPrice = detailForAddUpdateVO.getUnitPrice();//单价
            BigDecimal lineDiscount = detailForAddUpdateVO.getLineDiscount();//行折扣
            GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(detailForAddUpdateVO.getTaxRateId());
            if(goodsTaxRate == null) throw new BusinessException("税率不存在");
            BigDecimal taxRate = goodsTaxRate.getTaxRate();//税率

            //单剂数量
            registerDetail.setSingleDose(detailForAddUpdateVO.getSingleDose());
            registerDetail.setQuantity(quantity);
            registerDetail.setUnitPrice(unitPrice);
            registerDetail.setLineDiscount(lineDiscount);
            registerDetail.setTaxRateId(goodsTaxRate.getId());
            registerDetail.setTaxRate(taxRate);
            registerDetail.setWholeDiscount(wholeDiscount);

            //金额计算
            CalculateResultModel calculateResultModel = CalculateComponent.getCalculateResult(quantity, unitPrice, lineDiscount, wholeDiscount, wholeDiscountAmount, taxRate, amountTotal);
            registerDetail.setAmount(calculateResultModel.getAmount());
            registerDetail.setLineDiscountAmount(calculateResultModel.getLineRoundOff());//行优惠金额
            registerDetail.setRealPrice(calculateResultModel.getRealPrice());//实际单价
            registerDetail.setRealAmount(calculateResultModel.getRealAmount());//实际金额
            realAmountTotal = realAmountTotal.add(calculateResultModel.getRealAmount());

            registerDetail.setNotaxRealPrice(calculateResultModel.getNotaxPrice());//不含税实际单价

            registerDetail.setNotaxRealAmount(calculateResultModel.getNotaxAmount());//不含税实际金额

            notaxRealAmountTotal = notaxRealAmountTotal.add(calculateResultModel.getNotaxAmount());

            registerDetail.setTaxAmount(calculateResultModel.getTaxAmount());//税额
            taxAmountTotal = taxAmountTotal.add(calculateResultModel.getTaxAmount());//税额合计
            //已清数量 未清数量
            registerDetail.setUnclearQuantity(quantity);
            registerDetail.setClearQuantity(BigDecimal.ZERO);
            //行号
            registerDetail.setLineNum(detailForAddUpdateVO.getLineNum());

            registerDetail.setStatus(PrescriptionStatus.PENDING_AUDIT);
            registerDetail.setId(null);
            prescriptionRegisterDetailMapper.insertSelective(registerDetail);


            //处方登记单货位明细
            List<RequestPrescriptionRegisterShelfForAddUpdateVO> shelfForAddUpdateVOList = detailForAddUpdateVO.getShelfForAddUpdateVOList();
            for(RequestPrescriptionRegisterShelfForAddUpdateVO shelfForAddUpdateVO : shelfForAddUpdateVOList){
                PrescriptionRegisterShelf registerShelf = (PrescriptionRegisterShelf) EntityUtils.reflectAddSetDefaultValue(PrescriptionRegisterShelf.class, userVO);
                registerShelf.setRegisterDtlId(registerDetail.getId());
                registerShelf.setRegisterId(prescriptionRegister.getId());
                registerShelf.setGoodsId(registerDetail.getGoodsId());
                if(shelfForAddUpdateVO.getLotNumberId() == null) {
                    throw new BusinessException("批号lotNumberId不能为空");
                } else {
                    LotNumber lotNumber = lotNumberMapper.selectByPrimaryKey(shelfForAddUpdateVO.getLotNumberId());
                    if(lotNumber == null){
                        throw new BusinessException("批号不存在");
                    } else {
                        registerShelf.setLotNumberId(shelfForAddUpdateVO.getLotNumberId());
                        registerShelf.setLotNumber(lotNumber.getLotNum());//批号
                    }
                }

                registerShelf.setProductDate(shelfForAddUpdateVO.getProductDate());
                registerShelf.setValidDate(shelfForAddUpdateVO.getValidDate());
                registerShelf.setShelfId(shelfForAddUpdateVO.getShelfId());
                registerShelf.setTaxRateId(detailForAddUpdateVO.getTaxRateId());
                registerShelf.setTaxRate(taxRate);
                if(shelfForAddUpdateVO.getLineNum() == null){
                    throw new BusinessException("明细中行号没有提供！");
                }
                registerShelf.setLineNum(shelfForAddUpdateVO.getLineNum());

                Map<String,Object> warehouseShelf = warehouseShelfMapper.getShelfInfoById(shelfForAddUpdateVO.getShelfId());
                if(warehouseShelf == null){
                    throw new BusinessException("货位不存在");
                }
                registerShelf.setShelfName(StringUtil.transferTrimStr(warehouseShelf.get("name")));
                Object jobArea = warehouseShelf.get("jobArea");
                if(jobArea != null){
                    registerShelf.setShelfStatusDesc(ShelfStatus.getName((Integer) jobArea));
                }

                //货位分摊的数量
                BigDecimal shelfQuantity = shelfForAddUpdateVO.getQuantity();
                verifyShelfQuantity(userVO, goodsId, goods, quantity, shelfForAddUpdateVO);


                registerShelf.setQuantity(shelfQuantity);
                //金额计算
                registerShelf.setUnitPrice(unitPrice);
                registerShelf.setLineDiscount(lineDiscount);
                registerShelf.setWholeDiscount(wholeDiscount);
                CalculateResultModel shelfResultModel = CalculateComponent.getCalculateResult(shelfQuantity, unitPrice, lineDiscount, lineDiscount, registerDetail.getLineDiscountAmount(), taxRate, registerDetail.getAmount());
                registerShelf.setAmount(shelfResultModel.getAmount());

                registerShelf.setLineDiscountAmount(shelfResultModel.getLineRoundOff());//行优惠金额
                registerShelf.setRealPrice(shelfResultModel.getRealPrice());//实际单价
                registerShelf.setRealAmount(shelfResultModel.getRealAmount());//实际金额
                registerShelf.setNotaxRealPrice(shelfResultModel.getNotaxPrice());//不含税实际单价
                registerShelf.setNotaxRealAmount(shelfResultModel.getNotaxAmount());//不含税实际金额
                registerShelf.setTaxAmount(shelfResultModel.getTaxAmount());//税额

                //利润设置
                registerShelf.setProfit(BigDecimal.ZERO);
                registerShelf.setNotaxProfit(BigDecimal.ZERO);
                registerShelf.setProfitRate(BigDecimal.ZERO);
                registerShelf.setNotaxProfitRate(BigDecimal.ZERO);

                //已清、未清数量
                registerShelf.setUnclearQuantity(shelfQuantity);
                registerShelf.setClearQuantity(BigDecimal.ZERO);

                registerShelf.setStatus(PrescriptionStatus.PENDING_AUDIT);
                //插入货位明细
                registerShelf.setId(null);
                prescriptionRegisterShelfMapper.insertSelective(registerShelf);
            }
        }

        //更新总单金额
        PrescriptionRegister updateRegister = prescriptionRegisterMapper.selectByPrimaryKey(prescriptionRegister.getId());
        updateRegister.setRealAmountTotal(realAmountTotal.setScale(2,BigDecimal.ROUND_HALF_UP));
        updateRegister.setNotaxRealAmountTotal(notaxRealAmountTotal.setScale(2,BigDecimal.ROUND_HALF_UP));
        updateRegister.setTaxAmountTotal(taxAmountTotal.setScale(2,BigDecimal.ROUND_HALF_UP));
        updateRegister.setAmountTotal(amountTotal);
        updateRegister.setQuantityTotal(allQuantity);
        updateRegister.setVarietiesQuantity(prescriptionRegisterDetailMapper.getCountVarietiesQuantity(prescriptionRegister.getId()));
        prescriptionRegisterMapper.updateByPrimaryKeySelective(updateRegister);


    }

    private void verifyShelfQuantity(UserVO userVO, Long goodsId, Goods goods, BigDecimal quantity, RequestPrescriptionRegisterShelfForAddUpdateVO shelfForAddUpdateVO) {
        //登记数量不能大于库存可用数量
        Map<String,Object> mapShelf = new HashMap<>();
        mapShelf.put("enterpriseId",userVO.getEnterpriseId());
        mapShelf.put("goodsId",goodsId);
        mapShelf.put("today",new Date());
        mapShelf.put("shelfId",shelfForAddUpdateVO.getShelfId());

        BigDecimal usableQuantityShelf = stockMapper.getQualifiedUsableQuantity(mapShelf);
        if(usableQuantityShelf == null || BigDecimal.ZERO.compareTo(usableQuantityShelf) == 0){
            throw new BusinessException("商品[" + goods.getCode() + "] 在货位[" + shelfForAddUpdateVO.getShelfName()+"] 可用数量为空！");
        }
        if(quantity.compareTo(usableQuantityShelf) == 1){
            throw new BusinessException("登记数量不能大于库存可用数量");
        }
    }

    private void verifyQuantity(UserVO userVO, Long goodsId, Goods goods, BigDecimal quantity) {
        //数量不能为0
        if (quantity.compareTo(BigDecimal.ZERO) <= 0){
            throw new BusinessException(SysCode.FAIL.getCode(),"数量不能小于等于0");
        }
        //登记数量不能大于库存可用数量
        Map<String,Object> map = new HashMap<>();
        map.put("enterpriseId",userVO.getEnterpriseId());
        map.put("goodsId",goodsId);
        map.put("today",new Date());
        BigDecimal usableQuantity = stockMapper.getQualifiedUsableQuantity(map);
        if(usableQuantity == null || BigDecimal.ZERO.compareTo(usableQuantity) == 0){
            throw new BusinessException("商品[" + goods.getCode() + "] 库存可用数量为空！");
        }
        if(quantity.compareTo(usableQuantity) == 1){
            throw new BusinessException("登记数量不能大于库存可用数量");
        }
    }


    /**
     * 处方登记修改
     *
     * @param registerForAddUpdateVO
     * @param userVO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePrescription(RequestPrescriptionRegisterForAddUpdateVO registerForAddUpdateVO, UserVO userVO) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        Long parentId = userVO.getParentId();
        PrescriptionRegister prescriptionRegister = (PrescriptionRegister) EntityUtils.reflectUpdateSetDefaultValue(PrescriptionRegister.class,userVO);
        prescriptionRegister.setEnterpriseId(enterpriseId);
        prescriptionRegister.setParentId(parentId);
        BeanUtils.copyProperties(registerForAddUpdateVO,prescriptionRegister);
        Long memberId = registerForAddUpdateVO.getMemberId();//会员id
        checkMember(prescriptionRegister, memberId);
        Integer prescriptionType = prescriptionRegister.getPrescriptionType();
        if(PrescriptionType.TCM.getCode() == prescriptionType){
            //中药
            pharmacySet(prescriptionRegister,registerForAddUpdateVO);
        }

        ManageConfig mangeConfig = manageConfigComponent.getMangeConfigByEPId(userVO);
        addRegisterManInfo(userVO, prescriptionRegister, mangeConfig);

        prescriptionRegisterMapper.updateByPrimaryKeySelective(prescriptionRegister);//更新
        List<Long> detailIDList = prescriptionRegisterDetailMapper.getDetailIdsByRId(enterpriseId,prescriptionRegister.getId());

        //原业务单据中已存在的商品id
        List<Long> tempList = new ArrayList<>();
        BigDecimal wholeDiscount = prescriptionRegister.getWholeDiscount();//整单折扣
        BigDecimal amountTotal = BigDecimal.ZERO;//金额合计（整单优惠前的金额合计）
        List<RequestPrescriptionRegisterDetailForAddUpdateVO> detailForAddUpdateVOS = registerForAddUpdateVO.getRegisterDetailForAddUpdateVOList();
        for(RequestPrescriptionRegisterDetailForAddUpdateVO vo : detailForAddUpdateVOS){
            /**.
             * 金额（整单优惠前金额）
             */
            BigDecimal amount = calcLineDiscount(vo);
            amountTotal = amountTotal.add(amount);
        }

        BigDecimal wholeDiscountAmount = amountTotal.subtract(amountTotal.multiply(wholeDiscount.divide(new BigDecimal(100)))).setScale(2,BigDecimal.ROUND_HALF_UP);//整单优惠金额

        BigDecimal realAmountTotal = BigDecimal.ZERO;//实际金额合计
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;//不含税金额合计
        BigDecimal taxAmountTotal = BigDecimal.ZERO;//税额合计
        BigDecimal allQuantity = BigDecimal.ZERO;
        for(RequestPrescriptionRegisterDetailForAddUpdateVO detailForAddUpdateVO : detailForAddUpdateVOS){

            PrescriptionRegisterDetail registerDetail = (PrescriptionRegisterDetail) EntityUtils.reflectUpdateSetDefaultValue(PrescriptionRegisterDetail.class,userVO);
            registerDetail.setId(detailForAddUpdateVO.getId());
            registerDetail.setEnterpriseId(enterpriseId);
            registerDetail.setParentId(parentId);
            //商品冗余属性
            Long goodsId = detailForAddUpdateVO.getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            packGoodsInfo(registerDetail, goodsId, goods);
            if(PrescriptionType.TCM.getCode() != prescriptionType){
                pharmacySetNoTCM(registerDetail,detailForAddUpdateVO);
            }

            //利润为0
            registerDetail.setProfit(BigDecimal.ZERO);
            registerDetail.setNotaxProfit(BigDecimal.ZERO);
            registerDetail.setProfitRate(BigDecimal.ZERO);
            registerDetail.setNotaxProfitRate(BigDecimal.ZERO);

            BigDecimal quantity = detailForAddUpdateVO.getQuantity();//数量
            verifyQuantity(userVO, goodsId, goods, quantity);

            BigDecimal unitPrice = detailForAddUpdateVO.getUnitPrice();//单价
            BigDecimal lineDiscount = detailForAddUpdateVO.getLineDiscount();//行折扣

            GoodsTaxRate goodsTaxRate = goodsTaxRateMapper.selectByPrimaryKey(detailForAddUpdateVO.getTaxRateId());
            if(goodsTaxRate == null) throw new BusinessException("税率不存在");
            BigDecimal taxRate = goodsTaxRate.getTaxRate();//税率


            //单剂数量
            registerDetail.setSingleDose(detailForAddUpdateVO.getSingleDose());
            registerDetail.setQuantity(quantity);
            registerDetail.setUnitPrice(unitPrice);
            registerDetail.setLineDiscount(lineDiscount);
            registerDetail.setTaxRateId(detailForAddUpdateVO.getTaxRateId());
            registerDetail.setTaxRate(taxRate);
            registerDetail.setWholeDiscount(wholeDiscount);
            //金额计算
            CalculateResultModel calculateResultModel = CalculateComponent.getCalculateResult(quantity, unitPrice, lineDiscount, wholeDiscount, wholeDiscountAmount, taxRate, amountTotal);
            registerDetail.setAmount(calculateResultModel.getAmount());
            registerDetail.setLineDiscountAmount(calculateResultModel.getLineRoundOff());//行优惠金额
            registerDetail.setRealPrice(calculateResultModel.getRealPrice());//实际单价
            registerDetail.setRealAmount(calculateResultModel.getRealAmount());//实际金额
            realAmountTotal = realAmountTotal.add(calculateResultModel.getRealAmount());

            registerDetail.setNotaxRealPrice(calculateResultModel.getNotaxPrice());//不含税实际单价
            registerDetail.setNotaxRealAmount(calculateResultModel.getNotaxAmount());//不含税实际金额

            notaxRealAmountTotal = notaxRealAmountTotal.add(calculateResultModel.getNotaxAmount());
            registerDetail.setTaxAmount(calculateResultModel.getTaxAmount());//税额

            taxAmountTotal = taxAmountTotal.add(calculateResultModel.getNotaxAmount());//税额合计
            //已清数量 未清数量
            registerDetail.setUnclearQuantity(quantity);
            registerDetail.setClearQuantity(BigDecimal.ZERO);
            //行号
            if(detailForAddUpdateVO.getLineNum() == null){
                throw new BusinessException("行号不能为空！");
            }
            registerDetail.setLineNum(detailForAddUpdateVO.getLineNum());
            registerDetail.setRemark(detailForAddUpdateVO.getRemark());
            if(detailIDList.contains(detailForAddUpdateVO.getGoodsId())){
                //已存在的
                prescriptionRegisterDetailMapper.updateByPrimaryKeySelective(registerDetail);
                tempList.add(detailForAddUpdateVO.getGoodsId());
            } else {
                PrescriptionRegister prescriptionRegister1 = prescriptionRegisterMapper.selectByPrimaryKey(prescriptionRegister.getId());
                registerDetail.setRegisterId(prescriptionRegister.getId());
                registerDetail.setRegisterCode(prescriptionRegister1.getCode());
                registerDetail.setRegisterDate(prescriptionRegister1.getRegisterDate());
                registerDetail.setOrderType(OrderRule.PRESCRIPTION_REGISTER.getType());
                //新的
                registerDetail.setStatus(prescriptionRegister1.getStatus());
                prescriptionRegisterDetailMapper.insertSelective(registerDetail);
            }


            //查询原有的货位明细
            List<Long> shelfIdsByDId = prescriptionRegisterShelfMapper.getShelfIdsByDId(prescriptionRegister.getId(), detailForAddUpdateVO.getId(), enterpriseId);
            List<Long> tempShelfIdList = new ArrayList<>();
            //处方登记单货位明细
            List<RequestPrescriptionRegisterShelfForAddUpdateVO> shelfForAddUpdateVOList = detailForAddUpdateVO.getShelfForAddUpdateVOList();
            for(RequestPrescriptionRegisterShelfForAddUpdateVO shelfForAddUpdateVO : shelfForAddUpdateVOList){
                PrescriptionRegisterShelf registerShelf = (PrescriptionRegisterShelf) EntityUtils.reflectAddSetDefaultValue(PrescriptionRegisterShelf.class, userVO);
                registerShelf.setId(shelfForAddUpdateVO.getId());
                registerShelf.setRegisterDtlId(registerDetail.getId());
                registerShelf.setRegisterId(prescriptionRegister.getId());
                registerShelf.setGoodsId(registerDetail.getGoodsId());
                registerShelf.setLotNumberId(shelfForAddUpdateVO.getLotNumberId());
                registerShelf.setLotNumber(shelfForAddUpdateVO.getLotNumber());//批号
                registerShelf.setProductDate(shelfForAddUpdateVO.getProductDate());
                registerShelf.setValidDate(shelfForAddUpdateVO.getValidDate());
                registerShelf.setShelfId(shelfForAddUpdateVO.getShelfId());
                registerShelf.setTaxRateId(detailForAddUpdateVO.getTaxRateId());
                registerShelf.setTaxRate(taxRate);
                Map<String,Object> warehouseShelf = warehouseShelfMapper.getShelfInfoById(shelfForAddUpdateVO.getShelfId());
                if(warehouseShelf == null){
                    throw new BusinessException("货位不存在");
                }
                registerShelf.setShelfName(StringUtil.transferTrimStr(warehouseShelf.get("name")));
                Object jobArea = warehouseShelf.get("jobArea");
                if(jobArea != null){
                    registerShelf.setShelfStatusDesc(ShelfStatus.getName((Integer) jobArea));
                }
                registerShelf.setQuantity(shelfForAddUpdateVO.getQuantity());
                //金额计算
                registerShelf.setUnitPrice(unitPrice);
                registerShelf.setLineDiscount(lineDiscount);
                registerShelf.setWholeDiscount(wholeDiscount);
                BigDecimal shelfQuantity = shelfForAddUpdateVO.getQuantity();//货位分摊的数量
                verifyShelfQuantity(userVO, goodsId, goods, quantity, shelfForAddUpdateVO);

                CalculateResultModel shelfResultModel = CalculateComponent.getCalculateResult(shelfQuantity, unitPrice, lineDiscount, lineDiscount, registerDetail.getLineDiscountAmount(),taxRate, amountTotal);
                registerShelf.setAmount(shelfResultModel.getAmount());
                registerShelf.setLineDiscountAmount(shelfResultModel.getLineRoundOff());//行优惠金额
                registerShelf.setRealPrice(shelfResultModel.getRealPrice());//实际单价
                registerShelf.setRealAmount(shelfResultModel.getRealAmount());//实际金额
                registerShelf.setNotaxRealPrice(shelfResultModel.getNotaxPrice());//不含税实际单价
                registerShelf.setNotaxRealAmount(shelfResultModel.getNotaxAmount());//不含税实际金额
                registerShelf.setTaxAmount(shelfResultModel.getTaxAmount());//税额
                if(shelfForAddUpdateVO.getLineNum() == null) throw new BusinessException("行号不能为空");
                registerShelf.setLineNum(shelfForAddUpdateVO.getLineNum());

                //利润设置
                registerShelf.setProfit(BigDecimal.ZERO);
                registerShelf.setNotaxProfit(BigDecimal.ZERO);
                registerShelf.setProfitRate(BigDecimal.ZERO);
                registerShelf.setNotaxProfitRate(BigDecimal.ZERO);

                //已清、未清数量
                registerShelf.setUnclearQuantity(shelfQuantity);
                registerShelf.setClearQuantity(BigDecimal.ZERO);

                //更新货位明细
                if(detailForAddUpdateVO.getId() != null){
                    //处方登记明细是已有的
                    if(shelfIdsByDId.contains(shelfForAddUpdateVO.getId())){
                        //已有的货位明细id,执行更新操作
                        prescriptionRegisterShelfMapper.updateByPrimaryKeySelective(registerShelf);
                        tempShelfIdList.add(shelfForAddUpdateVO.getId());
                    } else {
                        registerShelf.setStatus(PrescriptionStatus.PENDING_AUDIT);
                        prescriptionRegisterShelfMapper.insertSelective(registerShelf);
                    }
                } else {
                    //处方登记明细是新增的
                    registerShelf.setStatus(PrescriptionStatus.PENDING_AUDIT);
                    prescriptionRegisterShelfMapper.insertSelective(registerShelf);
                }
            }
            //求出登记明细的货位明细的差集，删除已有登记明细中不存在的货位明细数据
            //取出差集，删除明细，并且删除明细对应的货位详情
            Set<Long> differenceSetShelf = Sets.difference(Sets.newHashSet(shelfIdsByDId), Sets.newHashSet(tempShelfIdList));
            Iterator<Long> iteratorShelf = differenceSetShelf.iterator();
            while (iteratorShelf.hasNext()){
                Long next = iteratorShelf.next();
                prescriptionRegisterShelfMapper.deleteByPrimaryKey(next);
            }
        }

        //取出差集，删除明细，并且删除明细对应的货位详情
        Set<Long> differenceSet = Sets.difference(Sets.newHashSet(detailIDList), Sets.newHashSet(tempList));
        Iterator<Long> iterator = differenceSet.iterator();
        while (iterator.hasNext()){
            //商品id
            Long gId = iterator.next();
            prescriptionRegisterDetailMapper.deleteByGIdAndRId(gId,prescriptionRegister.getId());
            //删除货位明细
            prescriptionRegisterShelfMapper.deleteByDetailId(prescriptionRegister.getId(),null,enterpriseId,gId);
        }
        //更新总单金额
        PrescriptionRegister updateRegister = prescriptionRegisterMapper.selectByPrimaryKey(prescriptionRegister.getId());
        updateRegister.setRealAmountTotal(realAmountTotal.setScale(2,BigDecimal.ROUND_HALF_UP));
        updateRegister.setNotaxRealAmountTotal(notaxRealAmountTotal.setScale(2,BigDecimal.ROUND_HALF_UP));
        updateRegister.setTaxAmountTotal(taxAmountTotal.setScale(2,BigDecimal.ROUND_HALF_UP));
        updateRegister.setAmountTotal(amountTotal);
        updateRegister.setQuantityTotal(allQuantity);
        updateRegister.setVarietiesQuantity(prescriptionRegisterDetailMapper.getCountVarietiesQuantity(prescriptionRegister.getId()));
        prescriptionRegisterMapper.updateByPrimaryKeySelective(updateRegister);
    }

    private void pharmacySet(PrescriptionRegister prescriptionRegister,RequestPrescriptionRegisterForAddUpdateVO registerForAddUpdateVO) {
        PharmacySet usage = pharmacySetMapper.selectByPrimaryKey(registerForAddUpdateVO.getUsageId());
        if (usage != null){
            prescriptionRegister.setUsageName(usage.getName());//用法
            prescriptionRegister.setUsageId(usage.getId());
        }
        PharmacySet useQty = pharmacySetMapper.selectByPrimaryKey(registerForAddUpdateVO.getUseQtyId());
        if (useQty != null){
            prescriptionRegister.setUseQtyName(useQty.getName());//用量
            prescriptionRegister.setUseQtyId(useQty.getId());
        }
        PharmacySet attention = pharmacySetMapper.selectByPrimaryKey(registerForAddUpdateVO.getAttentionMatterId());
        if (attention != null){
            prescriptionRegister.setAttentionMatterName(attention.getName());//	注意事项名称
            prescriptionRegister.setAttentionMatterId(attention.getId());
        }
    }
    private void pharmacySetNoTCM(PrescriptionRegisterDetail detail,RequestPrescriptionRegisterDetailForAddUpdateVO detailForAddUpdateVO) {
        PharmacySet usage = pharmacySetMapper.selectByPrimaryKey(detailForAddUpdateVO.getUsageId());
        if(usage == null){
            throw new BusinessException("用法不存在");
        }
        detail.setUsageName(usage.getName());//用法
        detail.setUsageId(usage.getId());
        PharmacySet useQty = pharmacySetMapper.selectByPrimaryKey(detailForAddUpdateVO.getUseQtyId());
        if(useQty == null){
            throw new BusinessException("用量不存在");
        }
        detail.setUseQtyName(useQty.getName());//用量
        detail.setUseQtyId(useQty.getId());
        PharmacySet timeDose = pharmacySetMapper.selectByPrimaryKey(detailForAddUpdateVO.getTimeDoseId());
        if(timeDose == null){
            throw new BusinessException("单次剂量不存在");
        }
        detail.setTimeDoseName(timeDose.getName());//	单次剂量
        detail.setTimeDoseId(timeDose.getId());
    }

    /**
     * 查询会员信息
     * @param enterpriseId
     * @param param
     * @return
     */
    @Override
    public List<MemberForPrescVO> getMemberInfoForPresc(Long enterpriseId, String param){
        List<MemberForPrescVO> memberInfoForPresc = memberInfoMapper.getMemberInfoForPresc(enterpriseId, param);
        memberInfoForPresc.forEach(item->{
            if(0 == item.getPriceStrategy()) {
                item.setPriceStrategyStr("零售价");
            }
            if(1 == item.getPriceStrategy()) {
                item.setPriceStrategyStr("会员价");
            }
        });
        return memberInfoForPresc;
    }

    /**
     * 查询会员信息
     * @param enterpriseId
     * @param setType 设置类型（0-用法；1-用量；2-单次计量；3-注意事项）
     * @return
     */
    @Override
    public List<PharmacySetForPrescVO> getPharmacySetInfo(Long enterpriseId, Integer setType){
        List<PharmacySetForPrescVO> pharmacySetByParam = pharmacySetMapper.getPharmacySetByParam(enterpriseId, setType, EnableStatus.ENABLE.getStatus());
        return pharmacySetByParam;
    }

    /**
     * 审批
     * @return
     */
    @Override
    public void updateAuditorDrug(UserVO userVO, RequestOperateParamVO operateParamVO){
        Integer status = PrescriptionStatus.WAIT_MIX;
        if(operateParamVO.getAuditResult() == 0){//拒绝
            status = PrescriptionStatus.CANCELED;
        }
        Long enterpriseId = userVO.getEnterpriseId();
        Long rid = operateParamVO.getId();//登记id
        PrescriptionRegister prescriptionRegister = new PrescriptionRegister();
        prescriptionRegister.setId(rid);
        prescriptionRegister.setAuditOpinion(operateParamVO.getAuditOpinion());
        prescriptionRegister.setAuditorId(userVO.getUserId());
        prescriptionRegister.setAuditorCode(userVO.getUserCode());
        prescriptionRegister.setAuditorName(userVO.getUserName());
        prescriptionRegister.setAuditResult(operateParamVO.getAuditResult());
        prescriptionRegister.setStatus(status);

        prescriptionRegisterMapper.updateByPrimaryKeySelective(prescriptionRegister);
        //明细状态修改
        prescriptionRegisterDetailMapper.updateStatusByRid(status, enterpriseId, rid);
        //货位明细状态更新
        List<Long> detailIds= prescriptionRegisterDetailMapper.getDetailIdsByRId(enterpriseId, rid);
        Integer finalStatus = status;
        detailIds.parallelStream().forEach((Long item) ->{
            prescriptionRegisterShelfMapper.updateStatusByParam(finalStatus, enterpriseId, rid, item);
        });
    }


    /**
     * 调剂
     * @return
     */
    @Override
    public void updateSwapDrug(UserVO userVO, RequestOperateParamVO operateParamVO){

        Long enterpriseId = userVO.getEnterpriseId();
        Long rid = operateParamVO.getId();


        PrescriptionRegister prescriptionRegister = new PrescriptionRegister();
        prescriptionRegister.setId(operateParamVO.getId());

        User swapUser = userMapper.selectByPrimaryKey(operateParamVO.getSwapManId());
        prescriptionRegister.setSwapManId(operateParamVO.getSwapManId());
        prescriptionRegister.setSwapManCode(swapUser.getCode());
        prescriptionRegister.setSwapManName(swapUser.getName());
        prescriptionRegister.setSwapTime(new Date());

        prescriptionRegister.setCheckerId(operateParamVO.getCheckerId());
        User checkUser = userMapper.selectByPrimaryKey(operateParamVO.getCheckerId());
        prescriptionRegister.setCheckerCode(checkUser.getCode());
        prescriptionRegister.setCheckerName(checkUser.getName());
        User sendUser = userMapper.selectByPrimaryKey(operateParamVO.getSendId());
        prescriptionRegister.setSendId(operateParamVO.getSendId());
        prescriptionRegister.setSendCode(sendUser.getCode());
        prescriptionRegister.setSendName(sendUser.getName());
        prescriptionRegister.setStatus(PrescriptionStatus.MIXED);
        prescriptionRegisterMapper.updateByPrimaryKeySelective(prescriptionRegister);
        //明细状态修改
        prescriptionRegisterDetailMapper.updateStatusByRid(PrescriptionStatus.MIXED, enterpriseId, rid);
        //货位明细状态更新
        List<Long> detailIds= prescriptionRegisterDetailMapper.getDetailIdsByRId(enterpriseId, rid);
        detailIds.forEach(item->{
            prescriptionRegisterShelfMapper.updateStatusByParam(PrescriptionStatus.MIXED, enterpriseId, rid, item);
        });
    }

    /**
     * 取消
     *
     * @param userVO
     * @param id
     * @return
     */
    @Override
    public void updateForCancel(UserVO userVO, Long rid) {
        Long enterpriseId = userVO.getEnterpriseId();
        PrescriptionRegister prescriptionRegister = new PrescriptionRegister();
        prescriptionRegister.setId(rid);
        prescriptionRegister.setStatus(PrescriptionStatus.CANCELED);
        prescriptionRegisterMapper.updateByPrimaryKeySelective(prescriptionRegister);
        //明细状态修改
        prescriptionRegisterDetailMapper.updateStatusByRid(PrescriptionStatus.CANCELED, enterpriseId, rid);
        //货位明细状态更新
        List<Long> detailIds= prescriptionRegisterDetailMapper.getDetailIdsByRId(enterpriseId, rid);
        detailIds.forEach(item->{
            prescriptionRegisterShelfMapper.updateStatusByParam(PrescriptionStatus.CANCELED, enterpriseId, rid, item);
        });
    }

    @Override
    public void getSignatureList(UserVO userVO, Page<List<ResponsePrescriptionSignatureForListVO>> page) {
        Long enterpriseId = userVO.getEnterpriseId();
        EnterpriseBusiness business = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());
        Integer remoteTrial = business.getRemoteTrial();

        Long enterpriseId2 = userVO.getEnterpriseId();
        if(userVO.getChainType() != 0){
            if(remoteTrial == 0){
                //总部控制，查询总部的设置的审核人员
                enterpriseId2 = userVO.getParentId();
            }
        }

        List<ResponsePrescriptionSignatureForListVO> signatureList = signatureMapper.getSignatureList(userVO.getEnterpriseId(),enterpriseId2,page.getStart(),page.getPageSize());
//        for(ResponsePrescriptionSignatureForListVO listVO : signatureList){
////            if(userVO.getChainType() != 0){
////                if(remoteTrial == 0 && listVO.getPrescriptionType() == PrescriptionOperateType.AUDIT.getCode()){
////                    //总部控制并且是审核类型
////                    enterpriseId = userVO.getParentId();
////                }
////            }
//
//        }
        signatureList.forEach((ResponsePrescriptionSignatureForListVO item) ->{

            if(userVO.getChainType() != 0){
                if(remoteTrial == 0){
                    //总部控制，查询总部的设置的
                    item.setCanUpdate(false);
                }
            }

            Long userId = item.getUserId();
            Integer prescriptionType = item.getPrescriptionType();
            if(prescriptionType == PrescriptionOperateType.AUDIT.getCode()){
                //审批
                item.setIsDelete(signatureMapper.getCountAuditor(userId));
            }
            if(prescriptionType == PrescriptionOperateType.SWAP.getCode()){
                //调剂
                item.setIsDelete(signatureMapper.getCountSwapMan(userId));
            }
            if(prescriptionType == PrescriptionOperateType.CHECKER.getCode()){
                //核对
                item.setIsDelete(signatureMapper.getCountChecker(userId));
            }
            if(prescriptionType == PrescriptionOperateType.MEDICINE.getCode()){
                //发药
                item.setIsDelete(signatureMapper.getCountSender(userId));
            }


            List<PrescriptionSignatureDetailVO> signatureDetailVOS = signatureDetailMapper.selectBySignatureIdByEnterpriseId(item.getId(),null);
            item.setPrescriptionSignatureDetailVOS(signatureDetailVOS);

        });
        page.setResult(signatureList);
        page.setTotalRecord(signatureMapper.getCountSignatureList(enterpriseId,enterpriseId2));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addPrescriptionSignature(ResponsePrescriptionSignatureForAddVO signatureVO, UserVO userVO) throws Exception {
        PrescriptionSignature prescriptionSignature = (PrescriptionSignature) EntityUtils.reflectAddSetDefaultValue(PrescriptionSignature.class,userVO);
        BeanUtils.copyProperties(signatureVO,prescriptionSignature);
        UserForPrescVO userById = userMapper.getUserById(userVO.getEnterpriseId(),signatureVO.getUserId());
        prescriptionSignature.setLoginAccount(userById.getLoginAccount());
        prescriptionSignature.setUserCode(userById.getCode());
        prescriptionSignature.setUserName(userById.getName());
        prescriptionSignature.setStatus(EnableStatus.ENABLE.getStatus());
        prescriptionSignature.setId(null);
        signatureMapper.insertSelective(prescriptionSignature);
        List<PrescriptionSignatureDetailVO> detailVOS = signatureVO.getPrescriptionSignatureDetailVOS();
        for(PrescriptionSignatureDetailVO detailVO : detailVOS){
            PrescriptionSignatureDetail detail = new PrescriptionSignatureDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(detailVO,detail);
            UserEnterpriseUtils.setUserCreateOrModify(detail,userVO,true);
            detail.setEnterpriseId(userVO.getEnterpriseId());
            detail.setParentId(userVO.getParentId());
            detail.setSignatureId(prescriptionSignature.getId());
            signatureDetailMapper.insertSelective(detail);
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePrescriptionSignature(ResponsePrescriptionSignatureForAddVO signatureVO, UserVO userVO) throws Exception {
        PrescriptionSignature prescriptionSignature = (PrescriptionSignature) EntityUtils.reflectUpdateSetDefaultValue(PrescriptionSignature.class,userVO);
        BeanUtils.copyProperties(signatureVO,prescriptionSignature);
        UserForPrescVO userById = userMapper.getUserById(userVO.getEnterpriseId(),signatureVO.getUserId());
        prescriptionSignature.setLoginAccount(userById.getLoginAccount());
        prescriptionSignature.setUserCode(userById.getCode());
        prescriptionSignature.setUserName(userById.getName());
        prescriptionSignature.setStatus(EnableStatus.ENABLE.getStatus());
        signatureMapper.updateByPrimaryKeySelective(prescriptionSignature);
        signatureDetailMapper.deleteBySignatureId(signatureVO.getId());
        List<PrescriptionSignatureDetailVO> detailVOS = signatureVO.getPrescriptionSignatureDetailVOS();
        for(PrescriptionSignatureDetailVO detailVO : detailVOS){
            PrescriptionSignatureDetail detail = new PrescriptionSignatureDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(detailVO,detail);
            UserEnterpriseUtils.setUserCreateOrModify(detail,userVO,true);
            detail.setSignatureId(prescriptionSignature.getId());
            detail.setEnterpriseId(userVO.getEnterpriseId());
            detail.setParentId(userVO.getParentId());
            signatureDetailMapper.insertSelective(detail);
        }
        return 0;
    }

    /**
     * 增加签章中 查询用户
     * @param param
     * @param enterpriseId
     * @param prescriptionType
     * @return
     */
    @Override
    public List<UserForPrescVO> getUserInfo(String param, Long enterpriseId,Integer prescriptionType) {
        List<UserForPrescVO> userInfoForPresc = userMapper.getUserInfoForPresc(param, enterpriseId);
        List<UserForPrescVO> userForPrescVOS = userInfoForPresc.stream().filter(item -> signatureMapper.getCountSignatureByUserId(item.getId(), enterpriseId, prescriptionType) == 0).collect(Collectors.toList());
        return userForPrescVOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deletePrescriptionSignature(Long id) {
        PrescriptionSignature prescriptionSignature = signatureMapper.selectByPrimaryKey(id);
        Long userId = prescriptionSignature.getUserId();
        Integer prescriptionType = prescriptionSignature.getPrescriptionType();
        int isDel = 0;
        if(prescriptionType == PrescriptionOperateType.AUDIT.getCode()){
            //审核
            isDel = signatureMapper.getCountAuditor(userId);
        }else
        if(prescriptionType == PrescriptionOperateType.SWAP.getCode()){
            //调剂
            isDel = signatureMapper.getCountSwapMan(userId);
        } else
        if(prescriptionType == PrescriptionOperateType.CHECKER.getCode()){
            //核对
            isDel = signatureMapper.getCountChecker(userId);
        }
        if(isDel > 0){
            return 1;
        }
        signatureMapper.deleteByPrimaryKey(id);
        signatureDetailMapper.deleteBySignatureId(id);
        return 0;
    }

    @Override
    public int getCountSignatureByUserId(Long userId, Long enterpriseId,Integer prescriptionType) {
        return signatureMapper.getCountSignatureByUserId(userId,enterpriseId,prescriptionType);
    }

    /**
     * 查询调配、核对、发药人员
     *
     * @param userVO
     * @return
     */
    @Override
    public ResponseSwapCheckerSendVO getSwapCheckerSendInfo(UserVO userVO) {
        ResponseSwapCheckerSendVO responseSwapCheckerSendVO = new ResponseSwapCheckerSendVO();
        List<Map<String, Object>> swapList = signatureMapper.getSignatureMap(userVO.getEnterpriseId(),PrescriptionOperateType.SWAP.getCode());
        packList(userVO, swapList);
        responseSwapCheckerSendVO.setSwapList(swapList);

        List<Map<String, Object>> checkerList = signatureMapper.getSignatureMap(userVO.getEnterpriseId(),PrescriptionOperateType.CHECKER.getCode());

        packList(userVO, checkerList);
        responseSwapCheckerSendVO.setCheckerList(checkerList);

        List<Map<String, Object>> sendList = signatureMapper.getSignatureMap(userVO.getEnterpriseId(),PrescriptionOperateType.MEDICINE.getCode());
        packList(userVO, sendList);
        responseSwapCheckerSendVO.setSendList(sendList);

        return responseSwapCheckerSendVO;
    }

    /**
     * 获取签章类型
     * @param userVO
     * @return
     */
    @Override
    public Map getAuditUser(UserVO userVO){
        List<Map<String, Object>> auditList = signatureMapper.getSignatureMap(userVO.getEnterpriseId(),PrescriptionOperateType.AUDIT.getCode());
        packList(userVO, auditList);
        Optional<Map<String, Object>> mapUser = auditList.stream().filter(item -> userVO.getUserId().equals(item.get("userId"))).findFirst();
        if(mapUser.isPresent()){
            return mapUser.get();
        }
        return null;
    }

    private void packList(UserVO userVO, List<Map<String, Object>> auditList) {
        auditList.stream().forEach(item->{
            List<PrescriptionSignatureDetailVO> tmpList = signatureDetailMapper.selectBySignatureIdByEnterpriseId((Long) item.get("id"), userVO.getEnterpriseId());
            if(CollectionUtils.isNotEmpty(tmpList)){
                item.put("pictureId",tmpList.get(0).getPictureId());
            } else {
                item.put("pictureId","");
            }
        });
    }

    /**
     * 获取销售单列表
     * @param page
     * @param startDate
     * @param endDate
     * @param enterpriseId
     */
    @Override
    public void getSaleForPrescrList(Page<List<SaleForPrescrVO>> page, @Param("startDate") String startDate, @Param("endDate") String endDate, Long enterpriseId){
        page.setResult(saleMapper.getSaleForPrescrList(enterpriseId,startDate,endDate,page.getStart(),page.getPageSize()));
        page.setTotalRecord(saleMapper.getCountSaleForPrescrList(enterpriseId,startDate,endDate));
    }

    /**
     * 获取销售单详情
     * @param enterpriseId
     * @param salesId
     * @return
     */
    @Override
    public List<ResponsePrescriptionRegisterDetailForDetailVO> getSaleDetailForPrescrBySaleId(Long enterpriseId, Long salesId){

        ResponsePrescriptionRegisterForDetailVO  registerForDetailVO = new ResponsePrescriptionRegisterForDetailVO();
        Sale sale = saleMapper.selectByPrimaryKey(salesId);
        MemberForPrescVO memberForPresc = memberInfoMapper.getMemberForPresc(enterpriseId, sale.getMemberId());
        if(memberForPresc != null){
            BeanUtils.copyProperties(memberForPresc,registerForDetailVO);
        }
        List<ResponsePrescriptionRegisterDetailForDetailVO> registerDetailForDetailVOList = new ArrayList<>();

        List<SaleDetailForPrescrVO> saleDetailForPrescr = saleDetailMapper.getSaleDetailForPrescrBySaleId(enterpriseId, salesId);
        saleDetailForPrescr.forEach(item->{
            ResponsePrescriptionRegisterDetailForDetailVO registerDetailForDetailVO = new ResponsePrescriptionRegisterDetailForDetailVO();
            BeanUtils.copyProperties(item,registerDetailForDetailVO);
            registerDetailForDetailVO.setTaxRateId(item.getTaxId());
            registerDetailForDetailVO.setShelfForAddUpdateVOList(item.getRegisterDetailForDetailVOList());
            registerDetailForDetailVOList.add(registerDetailForDetailVO);
        });
        return registerDetailForDetailVOList;
    }


    @Override
    public PrescriptionRegister selectByPrimaryKey(Long id){
        return  prescriptionRegisterMapper.selectByPrimaryKey(id);
    }

    /**
     * 导出
     * @param output
     * @param enterpriseId
     * @param id
     * @throws Exception
     */
    @Override
    public void exportPrescriptionRecord(OutputStream output, Long enterpriseId, Long id) throws Exception {

        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);//企业


        PrescriptionRegister registerDetail = prescriptionRegisterMapper.selectByPrimaryKey(id);
        //处方类型（0-普通；1-急诊；2-儿科；3-麻；4-精一；5-精二；6-中药）
        Integer prescriptionType = registerDetail.getPrescriptionType();
        //（状态（21-待审核 31-待调配 32-待收款 33-已完成 34-已取消）
        Integer status = registerDetail.getStatus();


        List<ResponsePrescriptionRegisterDetailForDetail2ExcelVO> registerDetailForDetailVOList = prescriptionRegisterMapper.getRegisterDetailById2Excel(id, enterpriseId);


        ExcelWriter writer = new ExcelWriter() {
            public void generate() throws Exception {
                int kk = 0;
                // 电子表格开始
                this.beginWorkSheet();
                this.beginSheet();
                Map<String, XSSFCellStyle> styleMap = this.getCellStyles();
                //第一行
                this.insertRow(kk++);
                this.createCell(0, enterprise.getName(),styleMap.get("cell_string").getIndex());
                this.endRow();
                //第二行
                this.insertRow(kk++);
                if(status == PrescriptionStatus.PENDING_AUDIT){
                    this.createCell(0, "处方待审核");
                } else if(status == PrescriptionStatus.WAIT_MIX || status == PrescriptionStatus.CANCELED){
                    this.createCell(0, "处方待调剂");
                } else if(status == PrescriptionStatus.MIXED || status == PrescriptionStatus.FINISHED){
                    this.createCell(0, "处方已调剂");
                }

                this.endRow();
                //第三行
                this.insertRow(kk++);
                this.createCell(0, "登记单号：" + Optional.ofNullable(registerDetail.getCode()).orElse(""));
                this.createCell(2, "登记日期：" + DateUtils.DateToString(registerDetail.getRegisterDate(),DateStyle.YYYY_MM_DD_HH_MM_SS));

                this.createCell(4, "登记人员：" + Optional.ofNullable(registerDetail.getRegisterManName()).orElse(""));
                this.createCell(6, "处方类型：" + PrescriptionType.getName(registerDetail.getPrescriptionType()));

                this.createCell(8, "处方单号：" + registerDetail.getPrescriptionCode());
                String billingDate=DateUtils.DateToString(registerDetail.getBillingDate(),DateStyle.YYYY_MM_DD);
                this.createCell(10, "开具日期：" +(billingDate==null?"":billingDate));

                this.createCell(12, "医疗机构编码：" + Optional.ofNullable(registerDetail.getMedicalOrgCode()).orElse(""));
                this.createCell(14, "医疗机构名称：" + Optional.ofNullable(registerDetail.getMedicalOrgName()).orElse(""));

                this.createCell(16, "费别：" + FeeType.getName(registerDetail.getFeeType()));
                this.createCell(18, "门诊/病历号：" + Optional.ofNullable(registerDetail.getOutpatientCaseNum()).orElse(""));
                this.createCell(20, "科室：" + Optional.ofNullable(registerDetail.getSectionRoom()).orElse(""));
                this.endRow();

                //第四行
                this.insertRow(kk++);
                this.createCell(0, "病人标识：" + Optional.ofNullable(registerDetail.getPatientId()).orElse(""));

                this.createCell(2, "姓名：" + Optional.ofNullable(registerDetail.getName()).orElse(""));
                this.createCell(4, "性别：" + SexType.getSexType4Code(registerDetail.getSex()).getName());

                this.createCell(6, "年龄：" + "");
                this.createCell(8, "身份证号：" + Optional.ofNullable(registerDetail.getIdNum()).orElse(""));

                String height=""+(registerDetail.getHeight()==null?"":registerDetail.getHeight());
            	String weight=""+(registerDetail.getWeight()==null?"":registerDetail.getWeight());
            	this.createCell(10, "身高：" + height);
            	this.createCell(12, "体重：" + weight);

                this.createCell(14, "过敏试验：" + Optional.ofNullable(registerDetail.getAllergyTest()).orElse(""));
                this.createCell(16, "医师：" + Optional.ofNullable(registerDetail.getPhysician()).orElse(""));

                this.createCell(18, "医嘱：" + Optional.ofNullable(registerDetail.getDoctorAdvice()).orElse(""));
                this.endRow();
                if(prescriptionType == PrescriptionType.TCM.getCode()){
                    //中药
                    this.insertRow(kk++);
                    this.createCell(0, "每日剂量：" + (registerDetail.getDayDose() == null?"": registerDetail.getDayDose()));
                    this.createCell(2, "用法：" +  Optional.ofNullable(registerDetail.getUsageName()).orElse(""));

                    this.createCell(4, "用量：" + Optional.ofNullable(registerDetail.getUseQtyName()).orElse(""));
                    this.createCell(6, "注意事项：" + registerDetail.getAttentionMatterName());

                    this.createCell(8, "剂量：" + (registerDetail.getDose() == null?"":registerDetail.getDose()));

                    this.endRow();
                }
                if(status == PrescriptionStatus.WAIT_MIX  || status == PrescriptionStatus.CANCELED){
                    //待调配
                    this.insertRow(kk++);
                    String audditTime = DateUtils.DateToString( registerDetail.getAuditTime(),DateStyle.YYYY_MM_DD_HH_MM_SS);
                    this.createCell(0, "审核时间：" +(audditTime == null?"":audditTime));
                    this.createCell(2, "审核人员：" + Optional.ofNullable(registerDetail.getAuditorName()).orElse(""));

                    this.createCell(4, "审核意见：" + Optional.ofNullable(registerDetail.getAuditOpinion()).orElse(""));
                    if(status == PrescriptionStatus.WAIT_MIX) {
                	   this.createCell(6, "审核结果：" + "同意");
                    }else {
                    	 this.createCell(6, "审核结果：" + "已取消");
                    }
                    this.endRow();

                } else if(status == PrescriptionStatus.MIXED || status == PrescriptionStatus.FINISHED){
                    //已调配

                    this.insertRow(kk++);
                    String audditTime = DateUtils.DateToString( registerDetail.getAuditTime(),DateStyle.YYYY_MM_DD_HH_MM_SS);
                    this.createCell(0, "审核时间：" + (audditTime == null?"":audditTime));
                    this.createCell(2, "审核人员：" + Optional.ofNullable(registerDetail.getAuditorName()).orElse(""));

                    this.createCell(4, "审核意见：" + Optional.ofNullable(registerDetail.getAuditOpinion()).orElse(""));
                    this.createCell(6, "审核结果：" + "同意");

                    String swapTime =  DateUtils.DateToString(registerDetail.getSwapTime(),DateStyle.YYYY_MM_DD_HH_MM_SS);
                    this.createCell(8, "调配时间：" +(swapTime == null?"":swapTime));
                    this.createCell(10, "调配人员：" + Optional.ofNullable(registerDetail.getSwapManName()).orElse(""));

                    this.createCell(12, "核对人员：" + Optional.ofNullable(registerDetail.getCheckerName()).orElse(""));
                    this.createCell(14, "发药人员：" + Optional.ofNullable(registerDetail.getSendName()).orElse(""));
                    this.endRow();

                }


                createRowHeader(this,kk++,prescriptionType);
                for (int rowNum = 0; rowNum < registerDetailForDetailVOList.size(); rowNum++) {
                    ResponsePrescriptionRegisterDetailForDetail2ExcelVO detailVO = registerDetailForDetailVOList.get(rowNum);
                    // 插入新行
                    this.insertRow(rowNum + kk);
                    // 建立新单元格,索引值从0开始,表示第一列
                    int k = 0;
                    this.createCell(k++, k);
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getGoodsCode()));
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getGoodsGenericName()));
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getDosageName()));
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getGoodsSpecification()));
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getManufacturer()));
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getGoodsPlace()));
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getUnitName()));
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getLotNumber()));
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getProductDate()));
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getValidDate()));
                    this.createCell(k++, detailVO.getShelfName());
                    if(prescriptionType == PrescriptionType.TCM.getCode()){
                        this.createCell(k++, StringUtil.transferTrimStr(detailVO.getSingleDose()));
                    }
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getQuantity()));
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getUnitPrice()));
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getRealAmount()));
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getTaxRate()));
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getNotaxRealPrice()));
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getNotaxRealAmount()));
                    this.createCell(k++, StringUtil.transferTrimStr(detailVO.getTaxAmount()));
                    if(prescriptionType != PrescriptionType.TCM.getCode()){
                        this.createCell(k++, StringUtil.transferTrimStr(detailVO.getUsageName()));
                        this.createCell(k++, StringUtil.transferTrimStr(detailVO.getUseQtyName()));
                        this.createCell(k++, StringUtil.transferTrimStr(detailVO.getTimeDoseName()));
                    }

                    this.createCell(k, StringUtil.transferTrimStr(detailVO.getRemark()));

                    // 结束行
                    this.endRow();


                }

                // 电子表格结束
                this.endSheet();

                //合并单元格
                this.beginMergerCell();
                if(prescriptionType == PrescriptionType.TCM.getCode()) {
                	this.setMergeCell(0, 0, 0, 23);
                }else {
                	this.setMergeCell(0, 0, 0, 20);
                }
                if(prescriptionType == PrescriptionType.TCM.getCode()) {
                	this.setMergeCell(1, 0, 1, 23);
                }else {
                	this.setMergeCell(1, 0, 1, 20);
                }
                this.endMergerCell();
                this.endWorkSheet();


            }
        };
        writer.process(output);

    }

    @Override
    public List<SelectPricingGoodsVO> selectGoods(UserVO userVO, String param, Integer prescriptionType) {
        GoodsParamStockVO goodsParamStockVO = new GoodsParamStockVO();
        goodsParamStockVO.setPrescriptionType(prescriptionType);
        goodsParamStockVO.setParam(param);
        return commonComponent.selectGoodsByParam(userVO,goodsParamStockVO);
    }


    private void createRowHeader(ExcelWriter writer,int kk,Integer prescriptionType) throws IOException {
        // 插入新行
        writer.insertRow(kk);
        // 建立新单元格,索引值从0开始,表示第一列
        int k = 0;
        writer.createCell(k++, "序号");
        writer.createCell(k++, "商品编码");
        writer.createCell(k++, "通用名称");
        writer.createCell(k++, "剂型");
        writer.createCell(k++, "规格");
        writer.createCell(k++, "生产厂商");
        writer.createCell(k++, "产地");
        writer.createCell(k++, "单位");
        writer.createCell(k++, "批号");
        writer.createCell(k++, "生产日期");
        writer.createCell(k++, "有效期至");
        writer.createCell(k++, "货位");
        if(prescriptionType == PrescriptionType.TCM.getCode()){
        	writer.createCell(k++, "单剂数量");
        }
        writer.createCell(k++, "数量");
        writer.createCell(k++, "单价");
        writer.createCell(k++, "金额");
        writer.createCell(k++, "税率");
        writer.createCell(k++, "不含税单价");
        writer.createCell(k++, "不含税金额");
        writer.createCell(k++, "税额");
        if(prescriptionType != PrescriptionType.TCM.getCode()){
        	writer.createCell(k++, "用法");
        	writer.createCell(k++, "用量");
        	writer.createCell(k++, "单次剂量");
        }
        writer.createCell(k, "备注");

        // 结束行
        writer.endRow();
    }




    private void packGoodsInfo(PrescriptionRegisterDetail registerDetail, Long goodsId, Goods goods) {
        registerDetail.setGoodsCode(goods.getCode());
        registerDetail.setGoodsId(goodsId);
        registerDetail.setGoodsGenericName(goods.getGenericName());
        registerDetail.setGoodsName(goods.getName());
        registerDetail.setBarcode(goods.getBarcode());
        registerDetail.setGoodsSpecification(goods.getSpecification());
        registerDetail.setUnitId(goods.getUnitId());
        registerDetail.setUnitName(goods.getUnitName());
        registerDetail.setDosageId(goods.getDosageId());
        registerDetail.setDosageName(goods.getDosageName());
        registerDetail.setManufacturerId(goods.getManufacturerId());
        registerDetail.setManufacturer(goods.getManufacturer());
        registerDetail.setApprovalNumber(goods.getApprovalNumber());
        registerDetail.setGoodsSpecification(goods.getSpecification());
        registerDetail.setGoodsPlace(goods.getPlace());
        registerDetail.setApprovalNumber(goods.getApprovalNumber());
    }

    private void addRegisterManInfo(UserVO userVO, PrescriptionRegister prescriptionRegister, ManageConfig mangeConfig) {
        if(mangeConfig.getQualityControl() == EnableStatus.DISABLE.getStatus()){
            //关闭
            User user = userMapper.selectByPrimaryKey(prescriptionRegister.getRegisterManId());
            prescriptionRegister.setRegisterManCode(user.getCode());
            prescriptionRegister.setRegisterManName(user.getName());
        } else {
            prescriptionRegister.setRegisterManId(userVO.getUserId());
            prescriptionRegister.setRegisterManCode(userVO.getUserCode());
            prescriptionRegister.setRegisterManName(userVO.getUserName());
        }
    }

    private void checkMember(PrescriptionRegister prescriptionRegister, Long memberId) {
        if(memberId != null ){
            MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(memberId);
            if(memberInfo != null){
                prescriptionRegister.setName(memberInfo.getMemberName());
                prescriptionRegister.setIdNum(memberInfo.getIdNum());
                prescriptionRegister.setSex(memberInfo.getSex());
            }
        }
    }


    /**
     * 获得整单优惠前金额
     * @param detailVO
     * @return
     */
    private   BigDecimal calcLineDiscount(RequestPrescriptionRegisterDetailForAddUpdateVO detailVO){
        BigDecimal amountByQuantityAndPriceAndLineDiscount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(
                detailVO.getQuantity()
                , detailVO.getUnitPrice()
                , detailVO.getLineDiscount()
        );

        return amountByQuantityAndPriceAndLineDiscount.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

}