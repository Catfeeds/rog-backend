package com.rograndec.feijiayun.chain.business.storage.chgoods.service.Impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.entity.LotNumber;
import com.rograndec.feijiayun.chain.business.keytable.vo.AvgCostVO;
import com.rograndec.feijiayun.chain.business.storage.chgoods.dao.GoodsClearDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.chgoods.dao.GoodsClearMapper;
import com.rograndec.feijiayun.chain.business.storage.chgoods.dao.GoodsClearShelfMapper;
import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsClear;
import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsClearDetail;
import com.rograndec.feijiayun.chain.business.storage.chgoods.entity.GoodsClearShelf;
import com.rograndec.feijiayun.chain.business.storage.chgoods.service.ChGoodsClearService;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.*;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.TaxRateType;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.GoodsDefTaxRateVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ChGoodsClearServiceImpl implements ChGoodsClearService {

    @Autowired
    GoodsClearMapper goodsClearMapper;
    @Autowired
    GoodsClearShelfMapper goodsClearShelfMapper;
    @Autowired
    GoodsClearDetailMapper goodsClearDetailMapper;
    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    PurchaseGeneralComponent<ChGoodsClearOrderDtlShelfListVO> purchaseGeneralComponent;
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderCodeComponent orderCodeComponent;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    LotNumberMapper lotNumberMapper;
    @Autowired
    StockMapper stockMapper;
    @Autowired
    InOutComponent inOutComponent;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    FinanceComponent financeComponent;

    @Override
    public List<ChGoodsClearOrderListVO> getChGoodsClearOrderList(int pageNo, int pageSize, Long enterpriseId, Page page, Date startTime, Date endTime, String code, String clearManName, String auditManName, String orderName, String orderType) {

        if (orderName != null && orderName.equals("clearDate")) {
            orderName = "clear_date";
        } else {
            orderName = null;
            orderType = null;
        }
        String startTimes = null;
        String endTimes = null;
        if (startTime != null) {
            startTimes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
        }
        if (endTime != null) {
            endTimes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime);
        }

        Long totalRecord = goodsClearMapper.queryCountByParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes, code, clearManName, auditManName);
        List<ChGoodsClearOrderListVO> list = goodsClearMapper.selectByParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes, orderName, orderType, code, clearManName, auditManName);
        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return list;
    }

    @Override
    public ChGoodsClearOrderDtlListVO getChGoodsClearOrderDtlList(Long enterpriseId, Long id) {

        ChGoodsClearOrderDtlListVO list = goodsClearMapper.selectByDtlParams(enterpriseId, id);
        List<ChGoodsClearOrderDtlShelfListVO> shelfListVOList = goodsClearShelfMapper.selectByDtlShelfListParams(enterpriseId, id);
        for (int i = 0; i < shelfListVOList.size(); i++) {
            List<ChGoodsClearOrderDtlShelfListOneVO> shelfListOneVOList = goodsClearShelfMapper.selectByDtlShelfListOneParams(enterpriseId, id, shelfListVOList.get(i).getGoodsId());
            shelfListVOList.get(i).setChGoodsClearOrderDtlShelfListOneVO(shelfListOneVOList);
        }
        list.setChGoodsClearOrderDtlShelfListVO(shelfListVOList);
        return list;
    }

    @Override
    public void exportExcel(OutputStream output, ChGoodsClearOrderDtlListVO chGoodsClearOrderDtlListVO, UserVO loginUser) {
        //转换一下显示日期
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("goodsCode", "商品编码");
        map.put("goodsGenericName", "通用名称");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("manufacturer", "生产厂商");
        map.put("unitName", "单位");
        map.put("lotNumber", "批号");
        map.put("productDate", "生产日期");
        map.put("validDate", "有效期至");
        map.put("shelfName", "货位");
        map.put("quantity", "数量");
        map.put("shelfStatusDesc", "质量状况");
        map.put("remark", "备注");
        List<String> titleSecond = new ArrayList<>();
        //标题栏下第一行
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("清斗单号:");
        titleSecondRow.append(chGoodsClearOrderDtlListVO.getCode() == null ? "" : chGoodsClearOrderDtlListVO.getCode());
        titleSecondRow.append("  清斗日期:");
        titleSecondRow.append(chGoodsClearOrderDtlListVO.getClearDate() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(chGoodsClearOrderDtlListVO.getClearDate()));
        titleSecondRow.append("  清斗人员:");
        titleSecondRow.append(chGoodsClearOrderDtlListVO.getClearManName() == null ? "" : chGoodsClearOrderDtlListVO.getClearManName());
        titleSecondRow.append("  复核人员:");
        titleSecondRow.append(chGoodsClearOrderDtlListVO.getAuditManName() == null ? "" : chGoodsClearOrderDtlListVO.getAuditManName());
        titleSecondRow.append("  备注:");
        titleSecondRow.append(chGoodsClearOrderDtlListVO.getRemark() == null ? "" : chGoodsClearOrderDtlListVO.getRemark());
        titleSecondRow.append("  流通监管码:");
        titleSecondRow.append(chGoodsClearOrderDtlListVO.getFlowCode() == null ? "" : chGoodsClearOrderDtlListVO.getFlowCode());
        titleSecond.add(titleSecondRow.toString());

        StringBuilder end = new StringBuilder();

        List<String> needTotalName = new ArrayList<>();

        List<String> name = new ArrayList<>();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(goodsClearMapper.selectByPrimaryKey(chGoodsClearOrderDtlListVO.getId()).getEnterpriseId());
        name.add(enterprise.getName());
        name.add("中药清斗");
        purchaseGeneralComponent.commExcelExport(output, map, chGoodsClearOrderDtlListVO.getChGoodsClearOrderDtlShelfListVO(), name, titleSecond, end.toString(), true, needTotalName);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int saveChGoodsClearOrder(UserVO loginUser, SaveChGoodsClearOrderVO saveChGoodsClearOrderVO) throws Exception, BusinessException {


        SaveChGoodsClearOrderHeadVO saveChGoodsClearOrderHeadVO = saveChGoodsClearOrderVO.getSaveChGoodsClearOrderHead();
        GoodsClear goodsClear = new GoodsClear();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveChGoodsClearOrderHeadVO, goodsClear);
        UserEnterpriseUtils.setUserCreateOrModify(goodsClear, loginUser, true);
        goodsClear.setEnterpriseId(loginUser.getEnterpriseId());
        goodsClear.setParentId(loginUser.getParentId());
        //set清斗人信息--start
        User user = userMapper.selectByPrimaryKey(goodsClear.getClearManId());
        if (user != null) {
            goodsClear.setClearManCode(user.getCode());
            goodsClear.setClearManName(user.getName());
        }
        //set复核人员信息--start
        User users = userMapper.selectByPrimaryKey(goodsClear.getAuditManId());
        if (user != null) {
            goodsClear.setAuditManCode(users.getCode());
            goodsClear.setAuditManName(users.getName());
        }
        //--end
        goodsClear.setQuantityTotal(BigDecimal.ZERO);
        goodsClear.setVarietiesQuantity(0);
        goodsClear.setAmountTotal(BigDecimal.ZERO);
        goodsClear.setNotaxAmountTotal(BigDecimal.ZERO);
        goodsClear.setTaxAmountTotal(BigDecimal.ZERO);
        //单号,单据类型
        goodsClear.setOrderType(OrderRule.CLEAR_BUCKET.getType());
        goodsClear.setStatus(1);
        goodsClear.setCode(getCode(OrderRule.CLEAR_BUCKET.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
        int mainId = goodsClearMapper.insertSelective(goodsClear);

        int reNum = saveDetial(loginUser, goodsClear, saveChGoodsClearOrderVO.getSaveChGoodsClearOrderDtl());

        GoodsClearShelfSumVO goodsClearShelfSumVO = goodsClearShelfMapper.selectBySum(goodsClear.getId(), loginUser.getEnterpriseId());
        goodsClear.setAmountTotal(goodsClearShelfSumVO.getAmountTotal());
        goodsClear.setNotaxAmountTotal(goodsClearShelfSumVO.getNotaxAmountTotal());
        goodsClear.setTaxAmountTotal(goodsClearShelfSumVO.getTaxAmountTotal());
        goodsClearMapper.updateByPrimaryKeySelective(goodsClear);

        //有关财务接口
        financeComponent.goodsClearToBalanceAndVoucher(loginUser, goodsClear);

        return reNum;

    }

    private int saveDetial(UserVO loginUser, GoodsClear goodsClear, List<SaveChGoodsClearOrderDtlVO> saveChGoodsClearOrderDtls) throws Exception, BusinessException {

        if (saveChGoodsClearOrderDtls.size() > 0) {
            List<GoodsClearShelf> goodsClearShelfList = new ArrayList<>();
            for (SaveChGoodsClearOrderDtlVO saveChGoodsClearOrderDtlVO : saveChGoodsClearOrderDtls) {

//                if (judge(saveChGoodsClearOrderDtls)) {
//                    throw new BusinessException("存在相同的商品信息,无法保存");
//                }
                GoodsClearDetail goodsClearDetail = new GoodsClearDetail();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveChGoodsClearOrderDtlVO, goodsClearDetail);
                //set状态
                goodsClearDetail.setStatus(1);
                //set单据id/code/类型/日期
                goodsClearDetail.setClearId(goodsClear.getId());
                goodsClearDetail.setClearCode(goodsClear.getCode());
                goodsClearDetail.setOrderType(OrderRule.LOAD_BUCKET.getType());
                goodsClearDetail.setClearDate(goodsClear.getClearDate());
                UserEnterpriseUtils.setUserCreateOrModify(goodsClearDetail, loginUser, true);
                //货品信息
                Goods goods = goodsMapper.selectByPrimaryKey(goodsClearDetail.getGoodsId());
                goodsClearDetail.setGoodsCode(goods.getCode());
                goodsClearDetail.setBarcode(goods.getBarcode());
                goodsClearDetail.setGoodsName(goods.getName());
                goodsClearDetail.setGoodsGenericName(goods.getGenericName());
                goodsClearDetail.setDosageId(goods.getDosageId());
                goodsClearDetail.setDosageName(goods.getDosageName());
                goodsClearDetail.setUnitId(goods.getUnitId());
                goodsClearDetail.setUnitName(goods.getUnitName());
                goodsClearDetail.setGoodsSpecification(goods.getSpecification());
                goodsClearDetail.setManufacturerId(goods.getManufacturerId());
                goodsClearDetail.setManufacturer(goods.getManufacturer());
                goodsClearDetail.setGoodsPlace(goods.getPlace());
                goodsClearDetail.setApprovalNumber(goods.getApprovalNumber());
                //set企业信息
                goodsClearDetail.setEnterpriseId(loginUser.getEnterpriseId());
                goodsClearDetail.setParentId(loginUser.getParentId());

                goodsClearDetail.setUnitPrice(BigDecimal.ZERO);
                goodsClearDetail.setAmount(BigDecimal.ZERO);
                GoodsDefTaxRateVO goodsDefTaxRateVO = commonComponent.getGoodsDefTaxRateInfo(loginUser.getEnterpriseId(), goodsClearDetail.getGoodsId(), TaxRateType.PURCHASE.getType());
                BigDecimal taxRate = goodsDefTaxRateVO.getTaxRate() == null ? BigDecimal.ZERO : goodsDefTaxRateVO.getTaxRate();
                goodsClearDetail.setTaxRate(taxRate);
                goodsClearDetail.setTaxRateId(goodsDefTaxRateVO.getTaxRateId() == null ? 0 : goodsDefTaxRateVO.getTaxRateId());
                goodsClearDetail.setTaxAmount(BigDecimal.ZERO);
                goodsClearDetail.setNotaxAmount(BigDecimal.ZERO);
                goodsClearDetail.setNotaxPrice(BigDecimal.ZERO);

                BigDecimal notaxAmountDtl = BigDecimal.ZERO;
                BigDecimal amountDtl = BigDecimal.ZERO;

                int dtlId = goodsClearDetailMapper.insertSelective(goodsClearDetail);

                if (saveChGoodsClearOrderDtlVO.getSaveChGoodsClearOrderDtlOne().size() > 0) {

                    for (SaveChGoodsClearOrderDtlOneVO saveChGoodsClearOrderDtlOneVO : saveChGoodsClearOrderDtlVO.getSaveChGoodsClearOrderDtlOne()) {
                        GoodsClearShelf goodsClearShelf = new GoodsClearShelf();
                        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveChGoodsClearOrderDtlOneVO, goodsClearShelf);
                        //set企业信息
                        goodsClearShelf.setEnterpriseId(loginUser.getEnterpriseId());
                        goodsClearShelf.setParentId(loginUser.getParentId());
                        //set状态
                        goodsClearShelf.setStatus(1);
                        //set单据id
                        goodsClearShelf.setClearId(goodsClear.getId());
                        goodsClearShelf.setDtlId(goodsClearDetail.getId());
                        UserEnterpriseUtils.setUserCreateOrModify(goodsClearShelf, loginUser, true);
                        //set货品ID
                        goodsClearShelf.setGoodsId(goodsClearDetail.getGoodsId());
                        goodsClearShelf.setGoodsCode(goodsClearDetail.getGoodsCode());
                        goodsClearShelf.setGoodsName(goodsClearDetail.getGoodsName());
                        //批号信息
                        LotNumber lotNumber = lotNumberMapper.selectByPrimaryKey(goodsClearShelf.getLotId());
                        goodsClearShelf.setLotNumber(lotNumber.getLotNum());
                        goodsClearShelf.setProductDate(lotNumber.getProductDate());
                        goodsClearShelf.setValidDate(lotNumber.getValidUntil());
                        goodsClearShelf.setRemark(goodsClearDetail.getRemark());
                        //单价  税额 等
                        setPriceTaxData(goodsClearShelf);
                        goodsClearShelfMapper.insertSelective(goodsClearShelf);

                        notaxAmountDtl = notaxAmountDtl.add(goodsClearShelf.getNotaxAmount());
                        amountDtl = amountDtl.add(goodsClearShelf.getAmount());

                        goodsClearShelfList.add(goodsClearShelf);
                    }
                } else {
                    throw new BusinessException("保存信息数据不全,无法保存");
                }
                //单价  税额 等 明细
                setPriceTaxDataDtl(goodsClearDetail,notaxAmountDtl,amountDtl);
                goodsClearDetailMapper.updateByPrimaryKeySelective(goodsClearDetail);
            }
            //生成其他关联表数据入口
            OrderModelBuilder builder = new OrderModelBuilder(loginUser);
            OrderModel orderModel = builder.buildOrderModel(OrderRule.CLEAR_BUCKET, goodsClear, goodsClearShelfList);
            inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel);

        } else {
            throw new BusinessException("保存信息数据不全,无法保存");
        }
        return 1;
    }

    //获取单号
    private String getCode(String codePrefix, Long enterpriseId, String enterpriseCode) throws Exception {
        return orderCodeComponent.generate(codePrefix, enterpriseId, enterpriseCode);
    }

    /**
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(BigDecimal v1, BigDecimal v2) {
        return v1.subtract(v2).doubleValue();
    }

    /**
     * 判断是否存在重复货品数据
     *
     * @param saveChGoodsClearOrderDtls
     * @return
     */
    private boolean judge(List<SaveChGoodsClearOrderDtlVO> saveChGoodsClearOrderDtls) {

        String goodsids = "";
        for (int i = 0; i < saveChGoodsClearOrderDtls.size(); i++) {
            Long goodsidList = saveChGoodsClearOrderDtls.get(i).getGoodsId();
            goodsids += goodsidList + ",";
        }

        String[] array = goodsids.split(",");

        Map<Object, Object> map = new HashMap<Object, Object>();

        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {

                return true;
            } else {
                map.put(array[i], 1);
            }
        }
        return false;
    }

    public void setPriceTaxData(GoodsClearShelf goodsClearShelf) {

        // 取成本价
        Long enterpriseId = goodsClearShelf.getEnterpriseId();
        Long goodsId = goodsClearShelf.getGoodsId();
        List<Long> lotIds = new ArrayList<>();
        lotIds.add(goodsClearShelf.getLotId());
        //BigDecimal costPrice = commonComponent.getCostPrice(enterpriseId, goodsId, goodsClearShelf.getQuantity());
        AvgCostVO avgCostVO = commonComponent.getAvgCostVO(enterpriseId,goodsId,lotIds,goodsClearShelf.getQuantity());
        BigDecimal costPrice = avgCostVO.getAvgCostPrice();
        BigDecimal notatCostPrice = avgCostVO.getAvgNotaxCostPrice();
        // 取商品的默认的进项税
        GoodsDefTaxRateVO goodsDefTaxRateVO = commonComponent.getGoodsDefTaxRateInfo(enterpriseId, goodsId, TaxRateType.PURCHASE.getType());
        BigDecimal taxRate = goodsDefTaxRateVO.getTaxRate() == null ? BigDecimal.ZERO : goodsDefTaxRateVO.getTaxRate();
        goodsClearShelf.setUnitPrice(costPrice == null ? BigDecimal.ZERO : costPrice);
        BigDecimal amount = goodsClearShelf.getUnitPrice().multiply(goodsClearShelf.getQuantity()).setScale(2, RoundingMode.HALF_UP);
        goodsClearShelf.setAmount(amount == null ? BigDecimal.ZERO : amount);
        goodsClearShelf.setTaxRate(taxRate == null ? BigDecimal.ZERO : taxRate);

//        BigDecimal notaxRealPrice = costPrice.divide(BigDecimal.ONE.add(taxRate.divide(new BigDecimal(100))), 6, BigDecimal.ROUND_UP);
        BigDecimal notaxAmount = notatCostPrice.multiply(goodsClearShelf.getQuantity()).setScale(2, BigDecimal.ROUND_UP);
        BigDecimal taxAmount = amount.subtract(notaxAmount).setScale(2, BigDecimal.ROUND_UP);
        goodsClearShelf.setTaxRateId(goodsDefTaxRateVO.getTaxRateId() == null ? 0 : goodsDefTaxRateVO.getTaxRateId());
        goodsClearShelf.setTaxAmount(taxAmount == null ? BigDecimal.ZERO : taxAmount);

        goodsClearShelf.setNotaxAmount(notaxAmount == null ? BigDecimal.ZERO : notaxAmount);
        goodsClearShelf.setNotaxPrice(notatCostPrice == null ? BigDecimal.ZERO : notatCostPrice);

    }

    public void setPriceTaxDataDtl(GoodsClearDetail goodsClearDetail, BigDecimal notaxAmountDtlTotal, BigDecimal amountDtlTotal) {

        // 取成本价
        Long enterpriseId = goodsClearDetail.getEnterpriseId();
        Long goodsId = goodsClearDetail.getGoodsId();
        BigDecimal costPrice = amountDtlTotal.divide(goodsClearDetail.getQuantity(), 2,BigDecimal.ROUND_UP);
        BigDecimal notatCostPrice = notaxAmountDtlTotal.divide(goodsClearDetail.getQuantity(), 2,BigDecimal.ROUND_UP);
//        BigDecimal costPrice = commonComponent.getCostPrice(enterpriseId, goodsId, goodsClearDetail.getQuantity());
//        AvgCostVO avgCostVO = commonComponent.getAvgCostVO(enterpriseId,goodsId,null,goodsClearDetail.getQuantity());
//        BigDecimal costPrice = avgCostVO.getAvgCostPrice();
//        BigDecimal notatCostPrice = avgCostVO.getAvgNotaxCostPrice();
        // 取商品的默认的进项税
        GoodsDefTaxRateVO goodsDefTaxRateVO = commonComponent.getGoodsDefTaxRateInfo(enterpriseId, goodsId, TaxRateType.PURCHASE.getType());
        BigDecimal taxRate = goodsDefTaxRateVO.getTaxRate() == null ? BigDecimal.ZERO : goodsDefTaxRateVO.getTaxRate();
        goodsClearDetail.setUnitPrice(costPrice == null ? BigDecimal.ZERO : costPrice);
        BigDecimal amount = goodsClearDetail.getUnitPrice().multiply(goodsClearDetail.getQuantity()).setScale(2, RoundingMode.HALF_UP);
        goodsClearDetail.setAmount(amount == null ? BigDecimal.ZERO : amount);
        goodsClearDetail.setTaxRate(taxRate == null ? BigDecimal.ZERO : taxRate);

//        BigDecimal notaxRealPrice = costPrice.divide(BigDecimal.ONE.add(taxRate.divide(new BigDecimal(100))), 6, BigDecimal.ROUND_UP);
        BigDecimal notaxAmount = notatCostPrice.multiply(goodsClearDetail.getQuantity()).setScale(2, BigDecimal.ROUND_UP);
        BigDecimal taxAmount = amount.subtract(notaxAmount).setScale(2, BigDecimal.ROUND_UP);
        goodsClearDetail.setTaxRateId(goodsDefTaxRateVO.getTaxRateId() == null ? 0 : goodsDefTaxRateVO.getTaxRateId());
        goodsClearDetail.setTaxAmount(taxAmount == null ? BigDecimal.ZERO : taxAmount);

        goodsClearDetail.setNotaxAmount(notaxAmount == null ? BigDecimal.ZERO : notaxAmount);
        goodsClearDetail.setNotaxPrice(notatCostPrice == null ? BigDecimal.ZERO : notatCostPrice);

    }
}
