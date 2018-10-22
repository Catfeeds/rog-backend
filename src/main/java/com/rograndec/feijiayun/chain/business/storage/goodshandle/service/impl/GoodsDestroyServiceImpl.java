package com.rograndec.feijiayun.chain.business.storage.goodshandle.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.rograndec.feijiayun.chain.business.keytable.vo.AvgCostVO;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.TaxRateType;
import com.rograndec.feijiayun.chain.common.constant.status.GoodsDestroyStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.GoodsDefTaxRateVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrReqPlan;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.init.model.ApprovalFlowContentModel;
import com.rograndec.feijiayun.chain.business.keytable.dao.CostMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.Cost;
import com.rograndec.feijiayun.chain.business.keytable.entity.Stock;
import com.rograndec.feijiayun.chain.business.keytable.vo.GoodsInfoStockShelfVO;
import com.rograndec.feijiayun.chain.business.purchase.order.approvalProcessor.PurchaseOrderApprovalProcessor;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderCountVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderDetailResponseVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderResponseVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.approvalProcessor.GoodsDestroyApprovalProcessor;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.GoodsDestroyDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.GoodsDestroyMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.GoodsDestroyShelfMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsDestroy;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsDestroyDetail;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsDestroyShelf;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.service.GoodsDestroyService;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsDestroyDetailRVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsDestroyRVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsDestroyShelfRVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.GoodsDestroyVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.StockDestroyVO;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.vo.UserDestroyVO;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SubmitApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.service.ManageConfigService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;

/**
 * @author 孙帮祥
 * Created by ST on 2017/9/25.
 */
@Service
public class GoodsDestroyServiceImpl implements GoodsDestroyService {
    @Autowired
    private GoodsDestroyMapper goodsDestroyMapper;
    @Autowired
    private GoodsDestroyDetailMapper goodsDestroyDetailMapper;
    @Autowired
    private ManageConfigMapper manageConfigMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private GoodsDestroyShelfMapper goodsDestroyShelfMapper;
    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
    @Autowired
    private OrderCodeComponent orderCodeComponent;
    @Autowired
    private InOutComponent inOutComponent;
    @Autowired
    private CostMapper costMapper;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private ApprovalFlowComponent approvalFlowComponent;
    @Autowired
    private GoodsDestroyApprovalProcessor goodsDestroyApprovalProcessor;
    @Autowired
    private ManageConfigService manageConfigService;
    @Autowired
    private FinanceComponent financeComponent;

    /**
     * 获取用户信息列表
     */
    @Override
    public List<UserDestroyVO> getUserList(Map map) {
        return goodsDestroyMapper.selectUserList(map);
    }

    /**
     * 获取商品列表
     */
    @Override
    public List<GoodsDestroyVO> getGoodsList(Map map) {
        return goodsDestroyMapper.selectGoodsList(map);
    }

    /**
     * 获取货位信息
     */
    @Override
    public List<StockDestroyVO> getStockList(Map map) {
        return goodsDestroyMapper.selectStockList(map);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void saveGoodsDestroy(UserVO userVO, GoodsDestroyRVO goodsDestroyVO) throws Exception, BusinessException {
        //定义出库model start
        List<GoodsDestroyShelf> goodsDestroyShelfList = new ArrayList<GoodsDestroyShelf>();
        //定义出库model end
        GoodsDestroy goodsDestroy = new GoodsDestroy();
        List<GoodsDestroyDetailRVO> goodsDestroyDetailRVOList = goodsDestroyVO.getGoodsDestroyDetailRVOList();
        BeanUtils.copyProperties(goodsDestroyVO, goodsDestroy);//从vo里面往实体复制
        Integer status = GoodsDestroyStatus.FINISHED;// 销毁状态
        ManageConfig manageConfig = manageConfigService.getManageConfig(userVO);
        if (manageConfig.getApprovalControl() == 1) {// 如果审批流开启
            status = GoodsDestroyStatus.PENDING_AUDIT;// 待审核状态
        }
        goodsDestroy.setEnterpriseId(userVO.getEnterpriseId());
        goodsDestroy.setParentId(userVO.getParentId());//此处先用订单的编码方式进行编码
        goodsDestroy.setCreateTime(new Date());
        goodsDestroy.setUpdateTime(new Date());
        goodsDestroy.setCreaterId(userVO.getUserId());
        goodsDestroy.setCreaterName(userVO.getUserName());
        goodsDestroy.setCreaterCode(userVO.getUserCode());
        goodsDestroy.setModifierId(userVO.getUserId());
        goodsDestroy.setModifierName(userVO.getUserName());
        goodsDestroy.setModifierCode(userVO.getUserCode());
        if (goodsDestroy.getDestroyGoods() == null) {
            throw new BusinessException("请选择销毁品种");
        }
        //如果是特殊管理药品的时候
        if (goodsDestroy.getDestroyGoods().equals(1)) {
            if (goodsDestroy.getMonitor() == null) {
                throw new BusinessException("监督人员1不能为空");
            }
            if (goodsDestroy.getSecondMonitor() == null) {
                throw new BusinessException("监督人员2不能为空");
            }
        }
        String code = orderCodeComponent.generate(OrderRule.DESTROY.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode());
        goodsDestroy.setCode(code);
        goodsDestroy.setStatus(status);//给它一个默认状态
        goodsDestroy.setOrderType(OrderRule.DESTROY.getType());//单据类型
        ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl = manage.getBusinessControl() == 0 ? false : true;//等于0的时候是关闭
        if (zl) {//质量流程开启的时候不必提供销毁日期与销毁人员
            goodsDestroy.setDestroyManId(userVO.getUserId());
            goodsDestroy.setDestroyManName(userVO.getUserName());
            goodsDestroy.setDestroyManCode(userVO.getUserCode());
        } else {
            User user = userMapper.selectByPrimaryKey(goodsDestroy.getDestroyManId());
            if (user == null) {
                throw new BusinessException("找不到该人员，请确认人员ID" + goodsDestroy.getDestroyManId() + "是否正确");
            }
            goodsDestroy.setDestroyManId(user.getId());
            goodsDestroy.setDestroyManName(user.getName());
            goodsDestroy.setDestroyManCode(user.getCode());
        }
        //计算总数量
        BigDecimal quantityTotal = new BigDecimal(0);
        for (int i = 0; i < goodsDestroyDetailRVOList.size(); i++) {
            BigDecimal quantityTotal_ = new BigDecimal(0);
            List<GoodsDestroyShelfRVO> goodsDestroyShelfRVOList = goodsDestroyDetailRVOList.get(i).getGoodsDestroyShelfRVOList();
            for (int j = 0; j < goodsDestroyShelfRVOList.size(); j++) {//计算商品数量
                if (goodsDestroyShelfRVOList.get(j).getQuantity() == null) {
                    throw new BusinessException("销毁数量不能为空");
                }
                quantityTotal_ = quantityTotal_.add(goodsDestroyShelfRVOList.get(j).getQuantity());
            }
            goodsDestroyDetailRVOList.get(i).setQuantity(quantityTotal_);
            quantityTotal = quantityTotal.add(goodsDestroyDetailRVOList.get(i).getQuantity());
        }
        goodsDestroy.setQuantityTotal(quantityTotal);//商品数量
        if (CollectionUtils.isNotEmpty(goodsDestroyDetailRVOList)) {//商品种类数量
            goodsDestroy.setVarietiesQuantity(goodsDestroyDetailRVOList.size());
        }
        goodsDestroy.setAmountTotal(new BigDecimal(0));
        goodsDestroy.setNotaxAmountTotal(new BigDecimal(0));
        goodsDestroy.setTaxAmountTotal(new BigDecimal(0));
        goodsDestroyMapper.insert(goodsDestroy);//插入销毁单主表
        /**
         * 初始金额合计 + 不含税金额合计 + 税额合计
         */
        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal notaxAmountTotal = BigDecimal.ZERO;
        BigDecimal taxAmountTotal = BigDecimal.ZERO;

        if (goodsDestroyDetailRVOList != null) {
            for (int i = 0; i < goodsDestroyDetailRVOList.size(); i++) {
                GoodsDestroyDetail goodsDestroyDetail = new GoodsDestroyDetail();
                BeanUtils.copyProperties(goodsDestroyDetailRVOList.get(i), goodsDestroyDetail);//从vo里面往实体复制
                // EntityUtils.reflectAddSetDefaultValue(goodsDestroyDetail.getClass(),userVO);//复制用户基本信息到基础表
                goodsDestroyDetail.setEnterpriseId(userVO.getEnterpriseId());
                goodsDestroyDetail.setParentId(userVO.getParentId());
                goodsDestroyDetail.setDestroyId(goodsDestroy.getId());
                goodsDestroyDetail.setCreaterId(userVO.getUserId());
                goodsDestroyDetail.setCreaterCode(userVO.getUserCode());
                goodsDestroyDetail.setCreaterName(userVO.getUserName());
                goodsDestroyDetail.setModifierId(userVO.getUserId());
                goodsDestroyDetail.setModifierCode(userVO.getUserCode());
                goodsDestroyDetail.setModifierName(userVO.getUserName());
                goodsDestroyDetail.setCreateTime(new Date());
                goodsDestroyDetail.setUpdateTime(new Date());
                goodsDestroyDetail.setOrderType(goodsDestroy.getOrderType());
                //根据商品ID获取商品信息
                Goods goods = goodsMapper.selectByPrimaryKey(goodsDestroyDetail.getGoodsId());//根据商品id获取商品信息
                if (goods == null) {
                    throw new BusinessException("找不到该商品");
                }
                goodsDestroyDetail.setDestroyCode(code);//销毁单编码
                goodsDestroyDetail.setStatus(goodsDestroy.getStatus());
                goodsDestroyDetail.setDestroyDate(goodsDestroy.getDestroyDate());//销毁单日期
                goodsDestroyDetail.setGoodsId(goods.getId());//商品ID
                goodsDestroyDetail.setGoodsCode(goods.getCode());//商品编码
                goodsDestroyDetail.setGoodsName(goods.getName());//商品名称
                goodsDestroyDetail.setDosageId(goods.getDosageId());//剂型ID
                goodsDestroyDetail.setDosageName(goods.getDosageName());//剂型名称
                goodsDestroyDetail.setGoodsSpecification(goods.getSpecification());//商品规格
                goodsDestroyDetail.setManufacturerId(goods.getManufacturerId());//生产厂商ID
                goodsDestroyDetail.setManufacturer(goods.getManufacturer());//生产厂商
                goodsDestroyDetail.setUnitId(goods.getUnitId());//单位ID
                goodsDestroyDetail.setUnitName(goods.getUnitName());//单位名称 库存计量单位名称
                goodsDestroyDetail.setApprovalNumber(goods.getApprovalNumber());//批准文号
                goodsDestroyDetail.setGoodsGenericName(goods.getGenericName());//商品通用名称
                goodsDestroyDetail.setGoodsPlace(goods.getPlace());//商品产地
                /**
                 * 获得商品的单价 + 金额 + 税率ID + 税率 + 不含税单价 + 不含税金额 + 税额
                 */

                //BigDecimal unitPrice = commonComponent.getCostPrice(userVO.getEnterpriseId(), goods.getId(),goodsDestroyDetail.getQuantity());
                //BigDecimal amount = costPrice.multiply(goodsDestroyDetail.getQuantity()).setScale(2, RoundingMode.HALF_UP);
                GoodsDefTaxRateVO goodsDefTaxRateInfo = commonComponent.getGoodsDefTaxRateInfo(userVO.getEnterpriseId(), goods.getId(), TaxRateType.PURCHASE.getType());
                Long taxRateId = goodsDefTaxRateInfo.getTaxRateId();
                BigDecimal taxRate = goodsDefTaxRateInfo.getTaxRate();
                //BigDecimal notaxRealPrice = unitPrice.divide(BigDecimal.ONE.add(taxRate.divide(new BigDecimal(100))),2,BigDecimal.ROUND_UP);
                //BigDecimal notaxAmount = notatCostPrice.multiply(goodsDestroyDetail.getQuantity()).setScale(2, BigDecimal.ROUND_UP);
                //BigDecimal taxAmount = amount.subtract(notaxAmount).setScale(2, BigDecimal.ROUND_UP);
                goodsDestroyDetail.setUnitPrice(BigDecimal.ZERO);
                goodsDestroyDetail.setAmount(BigDecimal.ZERO);
                goodsDestroyDetail.setTaxRateId(taxRateId);
                goodsDestroyDetail.setTaxRate(taxRate);
                goodsDestroyDetail.setNotaxPrice(BigDecimal.ZERO);
                goodsDestroyDetail.setNotaxAmount(BigDecimal.ZERO);
                goodsDestroyDetail.setTaxAmount(BigDecimal.ZERO);
                goodsDestroyDetailMapper.insert(goodsDestroyDetail);//插入商品销毁单明细

                // 细单Amount
                BigDecimal  dtlAmountTotal = BigDecimal.ZERO;
                BigDecimal  dtlNotaxAmountTotal = BigDecimal.ZERO;
                BigDecimal  dtlTaxAmountTotal = BigDecimal.ZERO;
                List<GoodsDestroyShelfRVO> goodsDestroyShelfRVOList = goodsDestroyDetailRVOList.get(i).getGoodsDestroyShelfRVOList();
                if (goodsDestroyShelfRVOList != null) {
                    for (int j = 0; j < goodsDestroyShelfRVOList.size(); j++) {
                        GoodsDestroyShelf goodsDestroyShelf = new GoodsDestroyShelf();
                        BeanUtils.copyProperties(goodsDestroyShelfRVOList.get(j), goodsDestroyShelf);//从vo里面往实体复制
                        EntityUtils.reflectAddSetDefaultValue(goodsDestroyShelf.getClass(), userVO);//复制用户基本信息到基础表
                        goodsDestroyShelf.setDtlId(goodsDestroyDetail.getId());
                        goodsDestroyShelf.setGoodsId(goods.getId());
                        goodsDestroyShelf.setGoodsCode(goods.getCode());
                        goodsDestroyShelf.setGoodsName(goods.getName());
                        goodsDestroyShelf.setDestroyId(goodsDestroy.getId());
                        goodsDestroyShelf.setEnterpriseId(userVO.getEnterpriseId());
                        goodsDestroyShelf.setParentId(userVO.getParentId());
                        goodsDestroyShelf.setCreaterId(userVO.getUserId());
                        goodsDestroyShelf.setCreaterName(userVO.getUserName());
                        goodsDestroyShelf.setUpdateTime(new Date());
                        goodsDestroyShelf.setCreateTime(new Date());
                        goodsDestroyShelf.setModifierId(userVO.getUserId());
                        goodsDestroyShelf.setModifierCode(userVO.getUserCode());
                        goodsDestroyShelf.setModifierName(userVO.getUserName());
                        goodsDestroyShelf.setStatus(goodsDestroy.getStatus());
                        goodsDestroyShelf.setCreaterCode(userVO.getUserCode());
                        GoodsInfoStockShelfVO goodsInfoStockShelfVO = stockMapper.getGoodsStockInfoByGoodsIdAndShelfId(userVO.getEnterpriseId(),
                                goods.getId(),
                                goodsDestroyShelf.getShelfId(),
                                goodsDestroyShelf.getLotId());
                        if (goodsInfoStockShelfVO == null) {
                            throw new BusinessException("货位信息不存在,请检查货位ID=" + goodsDestroyShelf.getShelfId());
                        }
                        goodsDestroyShelf.setProductDate(goodsInfoStockShelfVO.getProductDate());
                        goodsDestroyShelf.setValidDate(goodsInfoStockShelfVO.getValidUtil());
                        goodsDestroyShelf.setShelfName(goodsInfoStockShelfVO.getShelfName());
                        goodsDestroyShelf.setLotNumber(goodsInfoStockShelfVO.getLotNum());
                        goodsDestroyShelf.setLotId(goodsInfoStockShelfVO.getLotNumberId());
                        goodsDestroyShelf.setShelfStatusDesc(goodsInfoStockShelfVO.getShelfStatusDesc());
                        goodsDestroyShelf.setUpdateTime(new Date());
                        /**
                         * 获得商品的单价 + 金额 + 税率ID + 税率 + 不含税单价 + 不含税金额 + 税额
                         */
                        // 取平均成本价、平均不含税成本价
                        Long enterpriseId = goodsDestroyDetail.getEnterpriseId();
                        Long goodsId = goodsDestroyDetail.getGoodsId();
                        Long lotId = goodsDestroyShelf.getLotId();
                        List<Long> lotIds = new ArrayList<>();
                        lotIds.add(lotId);

                        AvgCostVO avgCostVO = commonComponent.getAvgCostVO(enterpriseId, goodsId, lotIds, goodsDestroyDetail.getQuantity());
                        BigDecimal costPrice = avgCostVO.getAvgCostPrice();
                        BigDecimal notatCostPrice = avgCostVO.getAvgNotaxCostPrice();

                        BigDecimal shelfamount = costPrice.multiply(goodsDestroyShelf.getQuantity()).setScale(2, RoundingMode.HALF_UP);

                        Long shelftaxRateId = goodsDestroyDetail.getTaxRateId();
                        BigDecimal shelftaxRate = goodsDestroyDetail.getTaxRate();
                        BigDecimal shelfnotaxRealPrice = costPrice.divide(BigDecimal.ONE.add(shelftaxRate.divide(new BigDecimal(100))), 2, BigDecimal.ROUND_UP);
                        BigDecimal shelfnotaxAmount = notatCostPrice.multiply(goodsDestroyShelf.getQuantity()).setScale(2, BigDecimal.ROUND_UP);
                        BigDecimal shelftaxAmount = shelfamount.subtract(shelfnotaxAmount).setScale(2, BigDecimal.ROUND_UP);

                        // 计算总计
                        dtlAmountTotal = dtlAmountTotal.add(shelfamount);
                        dtlNotaxAmountTotal = dtlNotaxAmountTotal.add(shelfnotaxAmount);
                        dtlTaxAmountTotal = dtlTaxAmountTotal.add(shelftaxAmount);

                        goodsDestroyShelf.setUnitPrice(costPrice);
                        goodsDestroyShelf.setAmount(shelfamount);
                        goodsDestroyShelf.setTaxRateId(shelftaxRateId);
                        goodsDestroyShelf.setTaxRate(shelftaxRate);
                        goodsDestroyShelf.setNotaxPrice(shelfnotaxRealPrice);
                        goodsDestroyShelf.setNotaxAmount(shelfnotaxAmount);
                        goodsDestroyShelf.setTaxAmount(shelftaxAmount);
                        goodsDestroyShelfMapper.insert(goodsDestroyShelf);
                        goodsDestroyShelfList.add(goodsDestroyShelf);
                    }

                    goodsDestroyDetail.setUnitPrice(dtlAmountTotal.divide(goodsDestroyDetail.getQuantity(),2,BigDecimal.ROUND_UP));
                    goodsDestroyDetail.setNotaxPrice(dtlNotaxAmountTotal.divide(goodsDestroyDetail.getQuantity(),2,BigDecimal.ROUND_UP));

                    goodsDestroyDetail.setAmount(dtlAmountTotal);
                    goodsDestroyDetail.setNotaxAmount(dtlNotaxAmountTotal);
                    goodsDestroyDetail.setTaxAmount(dtlTaxAmountTotal);
                    goodsDestroyDetailMapper.updateByPrimaryKeySelective(goodsDestroyDetail);//更新商品销毁单明细单价，总的金额

                    amountTotal = amountTotal.add(dtlAmountTotal);
                    notaxAmountTotal = notaxAmountTotal.add(dtlNotaxAmountTotal);
                    taxAmountTotal = taxAmountTotal.add(dtlTaxAmountTotal);

                }


            }
        }
        /**
         * 插入主单金额合计 + 不含税金额合计 + 税额合计
         */
        GoodsDestroy newGoodDesTroy = new GoodsDestroy();
        newGoodDesTroy.setId(goodsDestroy.getId());
        newGoodDesTroy.setAmountTotal(amountTotal);
        newGoodDesTroy.setNotaxAmountTotal(notaxAmountTotal);
        newGoodDesTroy.setTaxAmountTotal(taxAmountTotal);
        goodsDestroyMapper.updateByPrimaryKeySelective(newGoodDesTroy);
        // 提交流程审批
        if (manageConfig.getApprovalControl() == 1) {// 如果审批流开启
            SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(userVO.getEnterpriseId(), userVO.getEnterpriseName(),
                    userVO.getUserId(), userVO.getUserName(), userVO.getChainType(), userVO.getParentId(),
                    userVO.getChainType().equals(ChainType.Headquarters.getCode()) ? userVO.getEnterpriseId() : userVO.getParentId(),
                    ApprovalFlowContentModel.getGoodsDestroyCode(), goodsDestroy.getId(), goodsDestroy.getCode(), "药品销毁");
            approvalFlowComponent.apply(submitApprovalFlowVO, userVO);
        } else {
            OrderModelBuilder builder_ = new OrderModelBuilder(userVO);
            goodsDestroy.setId(goodsDestroy.getId());
            goodsDestroy.setAmountTotal(taxAmountTotal);
            goodsDestroy.setNotaxAmountTotal(notaxAmountTotal);
            goodsDestroy.setTaxAmountTotal(taxAmountTotal);
            OrderModel orderModel_ = builder_.buildOrderModel(OrderRule.DESTROY, goodsDestroy, goodsDestroyShelfList);
            inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel_);
        }
        //财务通用接口
        financeComponent.goodsDestroyToBalanceAndVoucher(userVO, goodsDestroy);
/*      OrderModelBuilder builder_ = new OrderModelBuilder(userVO);
        goodsDestroy.setId(goodsDestroy.getId());
        goodsDestroy.setAmountTotal(taxAmountTotal);
        goodsDestroy.setNotaxAmountTotal(notaxAmountTotal);
        goodsDestroy.setTaxAmountTotal(taxAmountTotal);
 		OrderModel orderModel_ = builder_.buildOrderModel(OrderRule.DESTROY, goodsDestroy, goodsDestroyShelfList);
 		inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel_);*/
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void updateGoodsDestroy(UserVO userVO, GoodsDestroyRVO goodsDestroyVO) throws Exception, BusinessException {
        GoodsDestroy goodsDestroy = new GoodsDestroy();
        BeanUtils.copyProperties(goodsDestroyVO, goodsDestroy);//从vo里面往实体复制
        goodsDestroy.setEnterpriseId(userVO.getEnterpriseId());
        goodsDestroy.setParentId(userVO.getParentId());
        ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl = manage.getBusinessControl() == 0 ? false : true;//等于0的时候是关闭
        if (zl) {//质量流程开启的时候不必提供销毁日期与销毁人员
            goodsDestroy.setDestroyManId(userVO.getUserId());
            goodsDestroy.setDestroyManName(userVO.getUserName());
            goodsDestroy.setDestroyManCode(userVO.getUserCode());
        } else {
            User user = userMapper.selectByPrimaryKey(goodsDestroy.getDestroyManId());
            if (user == null) {
                throw new BusinessException("找不到该人员，请确认人员ID" + goodsDestroy.getDestroyManId() + "是否正确");
            }
            goodsDestroy.setDestroyManId(user.getId());
            goodsDestroy.setDestroyManName(user.getName());
            goodsDestroy.setDestroyManCode(user.getCode());
        }
        goodsDestroyMapper.updateByPrimaryKeySelective(goodsDestroy);//更新销毁单主表
        goodsDestroyDetailMapper.deleteByDestroyId(goodsDestroy.getId());//当修改的操作时，新增之前先做修改操作
        List<GoodsDestroyDetailRVO> goodsDestroyDetailRVOList = goodsDestroyVO.getGoodsDestroyDetailRVOList();
        if (goodsDestroyDetailRVOList != null) {
            for (int i = 0; i < goodsDestroyDetailRVOList.size(); i++) {
                GoodsDestroyDetail goodsDestroyDetail = new GoodsDestroyDetail();
                BeanUtils.copyProperties(goodsDestroyDetailRVOList.get(i), goodsDestroyDetail);//从vo里面往实体复制
                //EntityUtils.reflectAddSetDefaultValue(goodsDestroyDetail.getClass(),userVO);//复制用户基本信息到基础表
                goodsDestroyDetail.setEnterpriseId(userVO.getEnterpriseId());
                goodsDestroyDetail.setParentId(userVO.getParentId());
                goodsDestroyDetail.setDestroyId(goodsDestroy.getId());
                //根据商品ID获取商品信息
                Map goodsMap = new HashMap();
                goodsMap.put("goodsId", goodsDestroyDetail.getGoodsId());
                GoodsDestroyVO goods = goodsDestroyMapper.selectGoods(goodsMap);//根据商品id获取商品信息
                if (goods == null) {
                    throw new BusinessException("找不到该商品");
                }
                goodsDestroyDetail.setGoodsId(goods.getGoodsId());//商品ID
                goodsDestroyDetail.setGoodsCode(goods.getGoodsCode());//商品编码
                goodsDestroyDetail.setGoodsName(goods.getGoodsName());//商品名称
                goodsDestroyDetail.setDosageId(goods.getDosageId());//剂型ID
                goodsDestroyDetail.setDosageName(goods.getDosageName());//剂型名称
                goodsDestroyDetail.setGoodsSpecification(goods.getGoodsSpecification());//商品规格
                goodsDestroyDetail.setManufacturerId(goods.getManufacturerId());//生产厂商ID
                goodsDestroyDetail.setManufacturer(goods.getManufacturer());//生产厂商
                goodsDestroyDetail.setUnitId(goods.getUnitId());//单位ID
                goodsDestroyDetail.setUnitName(goods.getUnitName());//单位名称 库存计量单位名称
                goodsDestroyDetail.setApprovalNumber(goods.getApprovalNumber());//批准文号
                goodsDestroyDetail.setGoodsGenericName(goods.getGoodsGenericName());//商品通用名称
                goodsDestroyDetail.setGoodsPlace(goods.getGoodsPlace());//商品产地
                goodsDestroyDetailMapper.insert(goodsDestroyDetail);//插入商品销毁单明细
                List<GoodsDestroyShelfRVO> goodsDestroyShelfRVOList = goodsDestroyDetailRVOList.get(i).getGoodsDestroyShelfRVOList();
                goodsDestroyShelfMapper.deleteByDestroyId(goodsDestroy.getId());
                if (goodsDestroyShelfRVOList != null) {
                    for (int j = 0; j < goodsDestroyShelfRVOList.size(); j++) {
                        GoodsDestroyShelf goodsDestroyShelf = new GoodsDestroyShelf();
                        BeanUtils.copyProperties(goodsDestroyShelfRVOList.get(i), goodsDestroyShelf);//从vo里面往实体复制
                        EntityUtils.reflectAddSetDefaultValue(goodsDestroyShelf.getClass(), userVO);//复制用户基本信息到基础表
                        goodsDestroyShelf.setDtlId(goodsDestroyDetail.getId());
                        goodsDestroyShelf.setDestroyId(goodsDestroy.getId());
                        goodsDestroyShelf.setEnterpriseId(userVO.getEnterpriseId());
                        goodsDestroyShelf.setParentId(userVO.getParentId());
                        goodsDestroyShelfMapper.insert(goodsDestroyShelf);
                    }
                }
            }
        }
    }

    @Override
    public void getGoodsDestroyList(Page page, Map map) {
        Long totalRecord = goodsDestroyMapper.selectCount(map);
        List<GoodsDestroyRVO> goodsDestroyList = goodsDestroyMapper.selectGoodsDestroyList(map);
        page.setTotalRecord(totalRecord.intValue());
        page.setResult(goodsDestroyList);
    }

    @Override
    public GoodsDestroyRVO getGoodsDestroyById(Long Id) {
        return goodsDestroyMapper.selectById(Id);
    }

    /**
     * 商品销毁
     */
    @Override
    public void exportExcel(OutputStream output, Long id, UserVO loginUser) {
        //转换一下显示日期
        GoodsDestroyRVO goodsDestroy = goodsDestroyMapper.selectById(id);
        List<GoodsDestroyDetailRVO> goodsDestroyDetailRVO = goodsDestroy.getGoodsDestroyDetailRVOList();
        try {
            ExcelWriter writer = new ExcelWriter() {
                @Override
                public void generate() throws Exception {
                    //开启excel
                    this.beginWorkSheet();
                    this.beginSheet();
                    //第一行
                    this.insertRow(0);
                    this.createCell(8, "荣贯医药");
                    this.endRow();
                    //第二行
                    this.insertRow(1);
                    this.createCell(8, "商品销毁");//头部
                    this.endRow();
                    //第三行
                    StringBuffer titleSecondRow = new StringBuffer();
                    titleSecondRow.append("销毁日期:");
                    titleSecondRow.append(goodsDestroy.getDestroyDate() == null ? "" : DateUtils.DateToString(goodsDestroy.getDestroyDate(), "yyyy-mm-dd"));
                    titleSecondRow.append("  销毁人员:");
                    titleSecondRow.append(goodsDestroy.getDestroyManName() == null ? "" : goodsDestroy.getDestroyManName());
                    titleSecondRow.append("  销毁品种:");
                    titleSecondRow.append(goodsDestroy.getDestroyGoods() == null ? "" : goodsDestroy.getDestroyGoods());
                    titleSecondRow.append("  销毁原因:");
                    titleSecondRow.append(goodsDestroy.getDestroyReason() == null ? "" : goodsDestroy.getDestroyReason());
                    titleSecondRow.append("  销毁时间:");
                    titleSecondRow.append(goodsDestroy.getDestroyDate() == null ? "" : DateUtils.DateToString(goodsDestroy.getDestroyDate(), "yyyy-mm-dd"));
                    titleSecondRow.append("  销毁方式:");
                    titleSecondRow.append(goodsDestroy.getDestroyMode() == null ? "" : goodsDestroy.getDestroyMode());
                    titleSecondRow.append("  销毁机构");
                    titleSecondRow.append(goodsDestroy.getDestroyUnit() == null ? "" : goodsDestroy.getDestroyUnit());
                    titleSecondRow.append("  销毁地点");
                    titleSecondRow.append(goodsDestroy.getDestroyPlace() == null ? "" : goodsDestroy.getDestroyPlace());
                    titleSecondRow.append("  监督人员1");
                    titleSecondRow.append(goodsDestroy.getMonitor() == null ? "" : goodsDestroy.getMonitor());
                    titleSecondRow.append("  监督人员2");
                    titleSecondRow.append(goodsDestroy.getSecondMonitor() == null ? "" : goodsDestroy.getSecondMonitor());
                    this.insertRow(2);
                    this.createCell(0, titleSecondRow.toString());
                    this.endRow();
                    //列表头开始
                    this.insertRow(3);
                    this.createCell(0, "序号");
                    this.createCell(1, "商品编码");
                    this.createCell(2, "通用名称");
                    this.createCell(3, "剂型");
                    this.createCell(4, "规格");
                    this.createCell(5, "单位");
                    this.createCell(6, "生产厂商");
                    this.createCell(7, "产地");
                    this.createCell(8, "批号");
                    this.createCell(9, "生产日期");
                    this.createCell(10, "有效日期");
                    this.createCell(11, "货位");
                    this.createCell(12, "数量");
                    this.createCell(13, "总量");
                    this.endRow();
                    //列表开始
                    for (int i = 0; i < goodsDestroyDetailRVO.size(); i++) {
                        List<GoodsDestroyShelfRVO> goodsDestroyShelfRVOList = goodsDestroyDetailRVO.get(i).getGoodsDestroyShelfRVOList();
                        StringBuffer lotNum = new StringBuffer();
                        StringBuffer productDate = new StringBuffer();
                        StringBuffer validDate = new StringBuffer();
                        StringBuffer shelfName = new StringBuffer();
                        StringBuffer shelfStatusDesc = new StringBuffer();
                        StringBuffer quantity = new StringBuffer();
                        this.insertRow(i + 4);
                        this.createCell(0, i);
                        this.createCell(1, goodsDestroyDetailRVO.get(i).getGoodsCode() == null ? "" : goodsDestroyDetailRVO.get(i).getGoodsCode());
                        this.createCell(2, goodsDestroyDetailRVO.get(i).getGoodsGenericName() == null ? "" : goodsDestroyDetailRVO.get(i).getGoodsGenericName());
                        this.createCell(3, goodsDestroyDetailRVO.get(i).getDosageName() == null ? "" : goodsDestroyDetailRVO.get(i).getDosageName());
                        this.createCell(4, goodsDestroyDetailRVO.get(i).getGoodsSpecification() == null ? "" : goodsDestroyDetailRVO.get(i).getGoodsSpecification());
                        this.createCell(5, goodsDestroyDetailRVO.get(i).getUnitName() == null ? "" : goodsDestroyDetailRVO.get(i).getUnitName());
                        this.createCell(6, goodsDestroyDetailRVO.get(i).getManufacturer() == null ? "" : goodsDestroyDetailRVO.get(i).getManufacturer());
                        this.createCell(7, goodsDestroyDetailRVO.get(i).getGoodsPlace() == null ? "" : goodsDestroyDetailRVO.get(i).getGoodsPlace());
                        if (goodsDestroyShelfRVOList != null) {
                            for (int j = 0; j < goodsDestroyShelfRVOList.size(); j++) {
                                lotNum = lotNum.append(goodsDestroyShelfRVOList.get(j).getLotNumber()).append(",");
                                productDate = productDate.append(DateUtils.DateToString(goodsDestroyShelfRVOList.get(j).getProductDate(), "yyyy-mm-dd")).append(",");
                                validDate = validDate.append(DateUtils.DateToString(goodsDestroyShelfRVOList.get(j).getValidDate(), "yyyy-mm-dd")).append(",");
                                shelfName = shelfName.append(goodsDestroyShelfRVOList.get(j).getShelfName()).append(",");
                                shelfStatusDesc = shelfStatusDesc.append(goodsDestroyShelfRVOList.get(j).getShelfStatusDesc()).append(",");
                                quantity = quantity.append(goodsDestroyShelfRVOList.get(j).getQuantity()).append(",");
                            }
                        }
                        this.createCell(8, lotNum.toString());
                        this.createCell(9, productDate.toString());
                        this.createCell(10, shelfName.toString());
                        this.createCell(11, shelfStatusDesc.toString());
                        this.createCell(12, quantity.toString());
                        this.createCell(13, goodsDestroyDetailRVO.get(i).getQuantity() == null ? "" : goodsDestroyDetailRVO.get(i).getQuantity().toString());
                        this.endRow();
                    }
                    //关闭excel
                    this.endSheet();
                    this.endWorkSheet();
                }
            };
            writer.process(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 取消销毁单据
     *
     * @throws Exception
     */
    @Override
    public void changeStatus(UserVO userVO, GoodsDestroy destroy) throws Exception {
        goodsDestroyMapper.updateByPrimaryKeySelective(destroy);
        // 提交流程审批
    }
}