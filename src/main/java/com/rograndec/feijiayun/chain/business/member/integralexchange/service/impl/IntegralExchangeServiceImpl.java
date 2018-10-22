package com.rograndec.feijiayun.chain.business.member.integralexchange.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseShelfDefVO;
import com.rograndec.feijiayun.chain.business.keytable.dao.CostMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.Cost;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
import com.rograndec.feijiayun.chain.business.keytable.vo.GoodsInfoStockShelfVO;
import com.rograndec.feijiayun.chain.business.member.info.dao.MemberInfoMapper;
import com.rograndec.feijiayun.chain.business.member.info.entity.MemberInfo;
import com.rograndec.feijiayun.chain.business.member.info.vo.SimpleMemberInfoVO;
import com.rograndec.feijiayun.chain.business.member.integralexchange.dao.IntegralExchangeDtlMapper;
import com.rograndec.feijiayun.chain.business.member.integralexchange.dao.IntegralExchangeGoodsMapper;
import com.rograndec.feijiayun.chain.business.member.integralexchange.dao.IntegralExchangeMapper;
import com.rograndec.feijiayun.chain.business.member.integralexchange.dao.IntegralExchangeShelfMapper;
import com.rograndec.feijiayun.chain.business.member.integralexchange.entity.IntegralExchange;
import com.rograndec.feijiayun.chain.business.member.integralexchange.entity.IntegralExchangeDtl;
import com.rograndec.feijiayun.chain.business.member.integralexchange.entity.IntegralExchangeGoods;
import com.rograndec.feijiayun.chain.business.member.integralexchange.entity.IntegralExchangeShelf;
import com.rograndec.feijiayun.chain.business.member.integralexchange.service.IntegralExchangeService;
import com.rograndec.feijiayun.chain.business.member.integralexchange.vo.*;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.enterprise.service.EnterpriseBusinessService;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.service.ManageConfigService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.SysCode;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.GoodsDefTaxRateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by dongyang.du on 2017/9/23.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IntegralExchangeServiceImpl implements IntegralExchangeService {

    private static final Log logger = LogFactory.getLog(IntegralExchangeServiceImpl.class);


    @Autowired
    private IntegralExchangeMapper exchangeMapper;

    @Autowired
    private IntegralExchangeDtlMapper exchangeDtlMapper;

    @Autowired
    private IntegralExchangeShelfMapper exchangeShelfMapper;

    @Autowired
    private ManageConfigService manageConfigService;


    @Autowired
    private UserComponent userComponent;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private MemberInfoMapper memberInfoMapper;

    @Autowired
    private IntegralExchangeGoodsMapper exchangeGoodsMapper;


    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private InOutComponent inOutComponent;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private LotNumberMapper lotNumberMapper;

    @Autowired
    private EnterpriseBusinessService  enterpriseBusinessService;

    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;

    @Override
    public Result<String> save(IntegralExchangeVO integralExchangeVO, UserVO userVO) throws Exception {

        Result<String> result = new Result<>();

        ManageConfig manageConfig = manageConfigService.getManageConfig(userVO);
        Long eId = userVO.getEnterpriseId();
        String eCode = userVO.getEnterpriseCode();

        // 总的扣除积分
        BigDecimal deductIntegral = BigDecimal.ZERO;
        List<IntegralExchangeDtl> exchangeDtlList = new ArrayList<>();

        List<IntegralExchangeShelf> integralExchangeShelfList = new ArrayList<>();
        EnterpriseBusiness enterpriseBusiness = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(userVO.getEnterpriseId());

        Long goodsEnterpriseId = userVO.getEnterpriseId();
        Long goodsParentId = userVO.getParentId();
        if (!userVO.getChainType().equals(ChainType.Headquarters.getCode()) && enterpriseBusiness != null
                && 0 == enterpriseBusiness.getMemberInfo()) {
            goodsEnterpriseId =  userVO.getParentId();
            goodsParentId = 0L;
        }

        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal taxAmountTotal = BigDecimal.ZERO;
        BigDecimal quantityTotal = BigDecimal.ZERO;
        BigDecimal notaxAmountTotal = BigDecimal.ZERO;
        // 1. 计算扣除积分并组装数据
        for (IntegralExchangeDtlVO exchangeDtlVO : integralExchangeVO.getExchangeDtlVOList()) {
            IntegralExchangeDtl exchangeDtl = new IntegralExchangeDtl();
            IntegralExchangeGoods exchangeGoods = exchangeGoodsMapper.selectByGoodsId(exchangeDtlVO.getGoodsId(),goodsEnterpriseId,goodsParentId);
            logger.info("[积分商品]: " + exchangeGoods);

            Long goodsId = exchangeGoods.getGoodsId();
            exchangeDtl.setEnterpriseId(eId);
            exchangeDtl.setParentId(userVO.getParentId());
            exchangeDtl.setExchangeGoodsId(exchangeGoods.getId());
            exchangeDtl.setGoodsId(goodsId);
            exchangeDtl.setGoodsCode(exchangeGoods.getGoodsCode());
            exchangeDtl.setBarcode(exchangeGoods.getBarcode());
            exchangeDtl.setGoodsName(exchangeGoods.getGoodsName());
            exchangeDtl.setGoodsGenericName(exchangeGoods.getGoodsGenericName());
            exchangeDtl.setDosageId(exchangeGoods.getDosageId());
            exchangeDtl.setDosageName(exchangeGoods.getDosageName());
            exchangeDtl.setUnitId(exchangeGoods.getUnitId());
            exchangeDtl.setUnitName(exchangeGoods.getUnitName());
            exchangeDtl.setGoodsSpecification(exchangeGoods.getGoodsSpecification());
            exchangeDtl.setManufacturerId(exchangeGoods.getManufacturerId());
            exchangeDtl.setManufacturer(exchangeGoods.getManufacturer());
            exchangeDtl.setUseableQuantity(exchangeGoods.getUseableQuantity());
            exchangeDtl.setIntegralExchange(exchangeGoods.getIntegralExchange()); // 积分
            BigDecimal quantity = exchangeDtlVO.getExchangeQuantity();
            exchangeDtl.setExchangeQuantity(quantity); // 兑换数量
            quantityTotal = quantityTotal.add(quantity);// 总的数量

            // ==================== ↓↓↓↓↓↓↓ 校验库存是否足够 ↓↓↓↓↓↓↓ ===================
            //BigDecimal usableQuantity = stockMapper.getUsableQuantityGroupByGoodsId(eId, exchangeDtlVO.getGoodsId());
            Map<String, Object> map = new HashMap<>();
            map.put("enterpriseId", userVO.getEnterpriseId());
            map.put("parentId", userVO.getParentId());
            map.put("goodsId", exchangeDtlVO.getGoodsId());
            map.put("today",new Date()); // 去掉过期的商品

            BigDecimal usableQuantity = stockMapper.getQualifiedUsableQuantity(map);
            logger.info("[库存数量]:" + usableQuantity + "[兑换数量]" + exchangeDtlVO.getExchangeQuantity());
            if (usableQuantity.compareTo(exchangeDtlVO.getExchangeQuantity()) < 0) {
                result.setBizCodeFallInfo(SysCode.FAIL.getCode(), exchangeGoods.getGoodsGenericName() + "库存数量不足");
                return result;
            }
            // ==================== ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ ===================


            GoodsDefTaxRateVO goodsDefTaxRateInfo = commonComponent.getGoodsDefTaxRateInfo(eId, goodsId, TaxRateType.PURCHASE.getType());
            BigDecimal costPrice = commonComponent.getCostPrice(eId, goodsId, quantity);// 单价
            // 金额
            exchangeDtl.setUnitPrice(costPrice);
            BigDecimal lineDiscount = new BigDecimal(100);
            exchangeDtl.setAmount(CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(quantity, costPrice, lineDiscount)); // 金额
            exchangeDtl.setNotaxAmount(CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(exchangeDtl.getAmount(), goodsDefTaxRateInfo.getTaxRate()));// 不含税金额
            exchangeDtl.setNotaxPrice(CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(exchangeDtl.getNotaxAmount(), quantity)); // 不含税单价
            exchangeDtl.setTaxAmount(
                    CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(exchangeDtl.getAmount(), exchangeDtl.getNotaxAmount()));// 税额
            //税率
            exchangeDtl.setTaxRateId(goodsDefTaxRateInfo.getTaxRateId());
            exchangeDtl.setTaxRate(goodsDefTaxRateInfo.getTaxRate());

            // 计算总和
            amountTotal = amountTotal.add(exchangeDtl.getAmount());
            notaxAmountTotal = notaxAmountTotal.add(exchangeDtl.getNotaxAmount());
            taxAmountTotal = taxAmountTotal.add(exchangeDtl.getTaxAmount());

            // 扣除积分
            exchangeDtl.setDeductIntegral(exchangeDtlVO.getExchangeQuantity().multiply(exchangeGoods.getIntegralExchange()));
            deductIntegral = deductIntegral.add(exchangeDtl.getDeductIntegral());

            exchangeDtl.setStatus(EnableStatus.ENABLE.getStatus());
            exchangeDtl.setRemark("新增");
            exchangeDtl.setCreaterId(userVO.getUserId());
            exchangeDtl.setCreaterCode(userVO.getUserCode());
            exchangeDtl.setCreaterName(userVO.getUserName());
            exchangeDtl.setCreateTime(new Date());
            exchangeDtl.setUpdateTime(new Date());
            exchangeDtl.setModifierId(userVO.getUserId());
            exchangeDtl.setModifierCode(userVO.getUserCode());
            exchangeDtl.setModifierName(userVO.getUserName());

            exchangeDtlList.add(exchangeDtl);

        }

        // 2. 验证当前会员积分是否足够
        // 获取会员信息
        MemberInfo memberInfo = memberInfoMapper.selectByPrimaryKey(integralExchangeVO.getMemberId());

        logger.info("[当前会员积分]: " + memberInfo.getCurrentIntegral() + ", [扣除积分]：" + deductIntegral);

        if (memberInfo.getCurrentIntegral().compareTo(deductIntegral) < 0) { // 会员积分不够
            result.setBizCodeFallInfo(SysCode.FAIL_WITH_TIPS.getCode(), "当前会员积分不够");
            return result;
        }


        //============================== 3.更新会员积分 ========================
        BigDecimal currentIntegral = memberInfo.getCurrentIntegral();
        BigDecimal remainderIntegral = memberInfo.getCurrentIntegral().subtract(deductIntegral);
        memberInfo.setCurrentIntegral(remainderIntegral);

        memberInfoMapper.updateByPrimaryKeySelective(memberInfo);


        // =============================  5. 新增记录表  =================================
        IntegralExchange exchange = new IntegralExchange();

        exchange.setOrderType(OrderRule.INTEGRAL_EXCHANGE.getType());// 积分队列类型
        exchange.setMemberId(memberInfo.getId());
        exchange.setMemberCardCode(memberInfo.getCardCode());
        exchange.setCurrentIntegral(currentIntegral);// 当前积分 扣除之前的
        exchange.setDeductIntegral(deductIntegral); // 扣除积分
        exchange.setRemainderIntegral(remainderIntegral);// 剩余积分s

        exchange.setStatus(EnableStatus.ENABLE.getStatus());
        exchange.setExchangeDate(integralExchangeVO.getExchangeDate());
        exchange.setEnterpriseId(eId);
        exchange.setParentId(userVO.getParentId());

        // 获取兑换人员信息
        if (manageConfig.getBusinessControl() == 0) { // 业务流程控制关闭
            exchange.setExchangeManId(integralExchangeVO.getExchangeManId());
            User exchangeMan = userComponent.findUserByUserId(integralExchangeVO.getExchangeManId());
            exchange.setExchangeManName(exchangeMan.getName());
            exchange.setExchangeManCode(exchangeMan.getCode());
        } else {
            exchange.setExchangeManId(userVO.getUserId());
            exchange.setExchangeDate(new Date());
            exchange.setExchangeManName(userVO.getUserName());
            exchange.setExchangeManCode(userVO.getUserCode());
        }
        // 单号
        String code = orderCodeComponent.generate(OrderRule.INTEGRAL_EXCHANGE.getCodePrefix(), eId, eCode);// 单号
        exchange.setCode(code);
        // 合计信息

        exchange.setAmountTotal(amountTotal);// 金额合计
        exchange.setTaxAmountTotal(taxAmountTotal);//税额合计
        exchange.setNotaxAmountTotal(notaxAmountTotal);// 不含税合计
        exchange.setQuantityTotal(quantityTotal);// 数量合计
        exchange.setVarietiesQuantity(exchangeDtlList.size());// 品种合计

        exchange.setCreaterId(userVO.getUserId());
        exchange.setCreaterCode(userVO.getUserCode());
        exchange.setCreaterName(userVO.getUserName());
        exchange.setCreateTime(new Date());
        exchange.setUpdateTime(new Date());
        exchange.setModifierId(userVO.getUserId());
        exchange.setModifierCode(userVO.getUserCode());
        exchange.setModifierName(userVO.getUserName());

        exchange.setRemark("新增");
        exchangeMapper.insertSelective(exchange);

        for (IntegralExchangeDtl exchangeDtl : exchangeDtlList) {
            // 积分兑换一些信息
            exchangeDtl.setExchangeId(exchange.getId());
            exchangeDtl.setExchangeCode(code);
            exchangeDtl.setExchangeDate(exchange.getExchangeDate());
            exchangeDtl.setOrderType(OrderRule.INTEGRAL_EXCHANGE.getType());
            exchangeDtlMapper.insertSelective(exchangeDtl);


            // ==================== ↓↓↓↓↓↓↓ 组装货位数据 ↓↓↓↓↓↓↓↓↓↓ ===================
            // 分配货位
            List<IntegralExchangeShelf> tmpIntegralExchangeShelves = buildExchangeShelf(exchangeDtl, exchangeDtl.getTaxRate(), exchangeDtl.getTaxRateId(), exchangeDtl.getUnitPrice(), userVO);

            logger.info("[分配货位信息]:" + tmpIntegralExchangeShelves);

            integralExchangeShelfList.addAll(tmpIntegralExchangeShelves);

            exchangeDtl.setExchangeShelfList(tmpIntegralExchangeShelves);

            // ==================== ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑   ===================
            for (IntegralExchangeShelf exchangeShelf : exchangeDtl.getExchangeShelfList()) {
                exchangeShelf.setDtlId(exchangeDtl.getId());
                exchangeShelf.setExchangeId(exchange.getId());
                exchangeShelf.setEnterpriseId(eId);
                exchangeShelf.setParentId(userVO.getParentId());
                exchangeShelf.setGoodsId(exchangeDtl.getGoodsId());
                exchangeShelf.setGoodsCode(exchangeDtl.getGoodsCode());
                exchangeShelf.setGoodsName(exchangeDtl.getGoodsName());
                exchangeShelf.setStatus(EnableStatus.ENABLE.getStatus());
                exchangeShelf.setRemark("积分兑换");
                exchangeShelf.setCreaterId(userVO.getUserId());
                exchangeShelf.setCreaterCode(userVO.getUserCode());
                exchangeShelf.setCreaterName(userVO.getUserName());
                exchangeShelf.setCreateTime(new Date());
                exchangeShelf.setUpdateTime(new Date());
                exchangeShelf.setModifierId(userVO.getUserId());
                exchangeShelf.setModifierCode(userVO.getUserCode());
                exchangeShelf.setModifierName(userVO.getUserName());


                exchangeShelfMapper.insertSelective(exchangeShelf);

            }
        }

        //=============================== 4.更新库存  ==============================

        logger.info("[构建的货位信息]" + integralExchangeShelfList);

        OrderModelBuilder builder = new OrderModelBuilder(userVO);
        OrderModel orderModel = builder.buildOrderModel(OrderRule.INTEGRAL_EXCHANGE, exchange, integralExchangeShelfList);
        inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel);

        result.setBizCodeSuccessInfo(SysCode.SUCCESS, "新增兑换成功");
        return result;

    }


    /**
     * 组装积分兑换货位信息
     */
    private List<IntegralExchangeShelf> buildExchangeShelf(IntegralExchangeDtl exchangeDtl, BigDecimal taxRate, Long taxRateId, BigDecimal costPrice, UserVO userVO) {

        List<IntegralExchangeShelf> integralExchangeShelfList = new ArrayList<>();


        BigDecimal quantity = exchangeDtl.getExchangeQuantity();
        BigDecimal tmpQuantity = quantity;

        // 分配货位, 先数量满足，后FIFO
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("enterpriseId", userVO.getEnterpriseId());
        paramMap.put("parentId", userVO.getParentId());
        paramMap.put("goodsId", exchangeDtl.getGoodsId());
        paramMap.put("jobType", 0);
        paramMap.put("jobArea", 0);
        paramMap.put("usableQuantity", 1);
        paramMap.put("today",new Date());
        List<Stock> stockList = stockMapper.selectGTZEROByParamMap(paramMap);

        List<StockVO> shelfStockList = new ArrayList<>();
        //
        if (CollectionUtils.isNotEmpty(stockList)) {
            for (Stock s : stockList) {

                BigDecimal usableQuantity = s.getUsableQuantity();// 可用数量

                StockVO  stockVO =  new StockVO(s);
                shelfStockList.add(stockVO);//

                if (exchangeDtl.getExchangeQuantity().compareTo(BigDecimal.ZERO) <= 0) {
                    break;
                }

                if (usableQuantity.compareTo(exchangeDtl.getExchangeQuantity()) >= 0) {
                    stockVO.setExchangeQuanlity(exchangeDtl.getExchangeQuantity());
                    break;
                } else {
                    // 多个货位,分配剩下的数量
                    quantity = quantity.subtract(usableQuantity);
                    stockVO.setExchangeQuanlity(usableQuantity);
                }

            }
        }

        BigDecimal shelfTotal = BigDecimal.ZERO;
        // 组装积分兑换货位信息表
        for (StockVO p : shelfStockList) {

            IntegralExchangeShelf exchangeShelf = new IntegralExchangeShelf();

            exchangeShelf.setLotId(p.getLotId());
            exchangeShelf.setLotNumber(p.getLotNum());

            LotNumber lotNumber = lotNumberMapper.selectByPrimaryKey(p.getLotId());
            exchangeShelf.setProductDate(lotNumber.getProductDate());
            exchangeShelf.setValidDate(lotNumber.getValidUntil());

            exchangeShelf.setShelfId(p.getShelfId());
            exchangeShelf.setShelfName(p.getShelfName());
            exchangeShelf.setShelfStatusDesc(p.getShelfStatusDesc());
            exchangeShelf.setQuantity(p.getExchangeQuanlity());

            shelfTotal = shelfTotal.add(p.getExchangeQuanlity());

            exchangeShelf.setTaxRateId(taxRateId);
            exchangeShelf.setTaxRate(taxRate);
            // 金额
            exchangeShelf.setUnitPrice(costPrice);
            BigDecimal lineDiscount = new BigDecimal(100);
            exchangeShelf.setAmount(CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(p.getQuantity(), costPrice, lineDiscount)); // 金额
            exchangeShelf.setNotaxAmount(CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(exchangeDtl.getAmount(), taxRate));// 不含税金额
            exchangeShelf.setNotaxPrice(CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(exchangeDtl.getNotaxAmount(), p.getQuantity())); // 不含税单价
            exchangeShelf.setTaxAmount(
                    CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(exchangeDtl.getAmount(), exchangeDtl.getNotaxAmount()));// 税额

            integralExchangeShelfList.add(exchangeShelf);
        }


        if(tmpQuantity.compareTo(shelfTotal) != 0){
            throw new BusinessException(SysCode.FAIL.getCode(),"积分兑换失败,请联系管理员");
        }

        return integralExchangeShelfList;
    }


    @Override
    public IntegralExchangeVO selectDetailById(Long exchangeId) {
        IntegralExchangeVO integralExchangeVO = exchangeMapper.selectDetailById(exchangeId);

        BigDecimal exchangeQuantityTotal = BigDecimal.ZERO;
        BigDecimal deductIntegralTotal = BigDecimal.ZERO;

        for (IntegralExchangeDtlVO exchangeDtlVO : integralExchangeVO.getExchangeDtlVOList()) {
            exchangeQuantityTotal = exchangeQuantityTotal.add(exchangeDtlVO.getExchangeQuantity());
            deductIntegralTotal = deductIntegralTotal.add(exchangeDtlVO.getDeductIntegral());
        }

        integralExchangeVO.setDeductIntegralTotal(deductIntegralTotal);
        integralExchangeVO.setExchangeQuantityTotal(exchangeQuantityTotal);

        return integralExchangeVO;
    }

    @Override
    public Page<IntegralExchangeReturnVO> getIntegralExchangePage(IntegralExchangeRequestVO exchangeRequestVO, UserVO userVO) {

        Long eId = userVO.getEnterpriseId();

        exchangeRequestVO.setEnterpriseId(eId);

        if (!"asc".equals(exchangeRequestVO.getSort()) && !"desc".equals(exchangeRequestVO.getSort())) {
            //sort = "desc";
            exchangeRequestVO.setSort("desc");
        }
        if (StringUtils.isEmpty(exchangeRequestVO.getOrder()) || "exchangeDate".equals(exchangeRequestVO.getOrder())) {
            //order = "plan_date"; // 默认计划日期倒序
            exchangeRequestVO.setOrder("exchange_date");
        }
        Date start = DateUtils.StringToDate(exchangeRequestVO.getStartDate());
        Date end = DateUtils.addDay(DateUtils.StringToDate(exchangeRequestVO.getEndDate()), 1);

        Page<IntegralExchangeReturnVO> page = new Page<>(exchangeRequestVO.getPageNo(), exchangeRequestVO.getPageSize());


        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        // 获取列表集合
        List<IntegralExchangePageVO> list = exchangeMapper.selectExchangePage(exchangeRequestVO);

        // 获取总和

        Map<String, Object> total = exchangeMapper.selectTotal(exchangeRequestVO);


        IntegralExchangeReturnVO exchangeReturnVO = new IntegralExchangeReturnVO();
        exchangeReturnVO.setExchangeList(list);
        if (total != null) {
            exchangeReturnVO.setCurrentIntegralTotal((BigDecimal) total.get("currentIntegralTotal"));
            exchangeReturnVO.setDeductIntegralTotal((BigDecimal) total.get("deductIntegralTotal"));
            exchangeReturnVO.setRemainderIntegralTotal((BigDecimal) total.get("remainderIntegralTotal"));

        } else {
            exchangeReturnVO.setCurrentIntegralTotal(BigDecimal.ZERO);
            exchangeReturnVO.setDeductIntegralTotal(BigDecimal.ZERO);
            exchangeReturnVO.setRemainderIntegralTotal(BigDecimal.ZERO);

        }
        page.setResult(exchangeReturnVO);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());

        return page;
    }

    @Override
    public List<SimpleMemberInfoVO> getSimpleMemberInfo(String param, UserVO userVO) {
        Long eId = userVO.getEnterpriseId();
        //门店: 会员管理由总部控制时，读取总部的数据
        EnterpriseBusiness enterpriseBus = enterpriseBusinessService.queryEnterpriseBusinessByEnterpriseId(eId);
        if (!userVO.getChainType().equals(ChainType.Headquarters.getCode())
                && 0 == enterpriseBus.getMemberInfo()){
            eId = userVO.getParentId();
        }

        return memberInfoMapper.getSimpleMemberInfo(eId, param);
    }
}
