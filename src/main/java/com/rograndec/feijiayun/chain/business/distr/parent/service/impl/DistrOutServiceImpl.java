package com.rograndec.feijiayun.chain.business.distr.parent.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.common.vo.StockLockShelfVO;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInReturnOutMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrReqPlanMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReturnOut;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.*;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.*;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrOutService;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrSendService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.*;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.LotNumberMapper;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.keytable.vo.StockLockRecordVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageMapper;
import com.rograndec.feijiayun.chain.business.purchase.instorage.dao.PurchaseInStorageShelfMapper;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrOutReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.OrderReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrOut;
import com.rograndec.feijiayun.chain.business.storage.chgoods.service.ChGoodsLoadService;
import com.rograndec.feijiayun.chain.business.storage.chgoods.vo.GoodsShelfStatusDescVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseBusinessMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.EnterpriseBusiness;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DistrOutStatus;
import com.rograndec.feijiayun.chain.common.constant.status.DistrSendStatus;
import com.rograndec.feijiayun.chain.common.model.CalculateResultModel;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @author zhengbin.jin
 * @version 1.0
 * @ClassName: DistrOutServiceImpl
 * @Description: 总部-配货出库-配货出库单-实现接口
 * @date 2017-10-07 15:57:42
 */
@Service
public class DistrOutServiceImpl implements DistrOutService {

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(DistrOutServiceImpl.class);

    @Autowired
    private DistrOutMapper distrOutMapper;
    @Autowired
    private DistrOutDetailMapper distrOutDetailMapper;
    @Autowired
    private DistrOutShelfMapper distrOutShelfMapper;
    @Autowired
    private DistrSendMapper distrSendMapper;
    @Autowired
    private DistrSendDetailMapper distrSendDetailMapper;
    @Autowired
    private CommonComponent commonComponent;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;
    @Autowired
    @Lazy
    private DistrSendService distrSendService;
    @Autowired
    private ManageConfigMapper manageConfigMapper;
    @Autowired
    private ManageConfigComponent manageConfigComponent;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrderCodeComponent orderCodeComponent;
    @Autowired
    private DistrComponent distrComponent;
    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
    @Autowired
    private InOutComponent inOutComponent;
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private LotNumberMapper lotNumberMapper;
    @Autowired
    private PurchaseInStorageDetailMapper purchaseInStorageDetailMapper;
    @Autowired
    private PurchaseInStorageShelfMapper purchaseInStorageShelfMapper;
    @Autowired
    private ChGoodsLoadService chGoodsLoadService;
    @Autowired
    private DistrReturnInDetailMapper distrReturnInDetailMapper;
    @Autowired
    private DistrReturnInShelfMapper distrReturnInShelfMapper;
    @Autowired
    private EnterpriseBusinessMapper enterpriseBusinessMapper;
    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private PurchaseInStorageMapper purchaseInStorageMapper;
    @Autowired
    private DistrReturnInMapper distrReturnInMapper;
    @Autowired
    private DistrReqPlanMapper distrReqPlanMapper;
    @Autowired
    private DistrReturnNoticeMapper distrReturnNoticeMapper;
    @Autowired
    private DistrInReturnOutMapper distrInReturnOutMapper;

    @Override
    public DistrOut getDistrOutData(Long id) throws Exception {
        DistrOut distrOut = distrOutMapper.selectByPrimaryKey(id);
        if (distrOut == null) {
            return null;
        }
        List<DistrOutDetail> distrOutDetailList = distrOutDetailMapper.getDistrOutDetailList(id);
        for (DistrOutDetail distrOutDetail : distrOutDetailList) {
            List<DistrOutShelf> distrOutShelfList = distrOutShelfMapper.getDistrOutShelfList(distrOutDetail.getId());
            distrOutDetail.setDistrOutShelfList(distrOutShelfList);
        }
        distrOut.setDistrOutDetailList(distrOutDetailList);
        BigDecimal wholeDiscountValue = distrOut.getAmountTotal().multiply(distrOut.getWholeDiscount());
        distrOut.setWholeDiscountValue(wholeDiscountValue);
        distrOut.setDistrTypeName(DistributionType.getName(distrOut.getDistrType()));
        return distrOut;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public int save(DistrOut distrOut, UserVO userVO) throws Exception {
        commonComponent.genrateAndSaveDistrOut(distrOut,userVO);
        return 1;
    }

    @Override
    public DistrOut calculation(DistrOut distrOut, UserVO userVO) throws Exception {
        DistrOut copy = (DistrOut) EntityUtils.reflectAddSetDefaultValue(new DistrOut().getClass(), userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrOut,copy);
        distrOut.setUpdateTime(new Date());
        if (distrOut.getStatus() != DistrOutStatus.AUDIT_REBUT) {
            DistrSend distrSend = distrSendMapper.selectByPrimaryKey(distrOut.getBaseOrderId());
            if (distrSend == null) {
                throw new BusinessException("没有找到该配送单,请检查配送单id=" + distrOut.getBaseOrderId());
            }
            if (distrSend.getStatus() != DistrSendStatus.WAIT_OUT) {
                throw new BusinessException("该配送单无法出库,请检查配送单状态");
            }
        }

        ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl = manage.getBusinessControl() == 0 ? true : false;
        if (zl) {
            //质量流程开的时候前台必须提供采购人员id
            User user = userMapper.selectByPrimaryKey(distrOut.getOutManId());
            if (user == null) {
                throw new BusinessException("没有该员工，请核实员工id");
            }
            if (user.getEnterpriseId().intValue() != userVO.getEnterpriseId().intValue()) {
                throw new BusinessException("没有该员工，请核实员工id");
            }
            distrOut.setOutDate(DateUtils.StringToDate(distrOut.getOutDateStr()));
            distrOut.setOutManId(user.getId());
            distrOut.setOutManCode(user.getCode());
            distrOut.setOutManName(user.getName());
        } else {
            distrOut.setOutDate(new Date());
            distrOut.setOutManId(userVO.getUserId());
            distrOut.setOutManCode(userVO.getUserCode());
            distrOut.setOutManName(userVO.getUserName());
        }
        commonComponent.calculationPrice(distrOut);
//            distrOut.setStatus(DistrOutStatus.WAIT_AUDIT);
        distrOut.setStatus(distrOut.getStatus());
        distrOut.setCode(orderCodeComponent.generate(OrderRule.DISTR_OUT.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode()));
        Long outId = distrOut.getId();
        for (DistrOutDetail distrOutDetail : distrOut.getDistrOutDetailList()) {
            BigDecimal quantity = new BigDecimal(0);
            for (DistrOutShelf distrOutShelf : distrOutDetail.getDistrOutShelfList()) {
                quantity = quantity.add(distrOutShelf.getQuantity());
            }
            distrOutDetail.setQuantity(quantity);
        }
        for (DistrOutDetail distrOutDetail : distrOut.getDistrOutDetailList()) {
            distrOutDetail.setOutId(outId);
            distrOutDetail.setOutCode(distrOut.getCode());
            distrOutDetail.setOutDate(distrOut.getOutDate());
            distrOutDetail.setUpdateTime(new Date());
            distrOutDetail.setModifierCode(userVO.getUserCode());
            distrOutDetail.setModifierId(userVO.getUserId());
            distrOutDetail.setModifierName(userVO.getUserName());
            long outDtlId = distrOutDetail.getId();
            BigDecimal quantity = new BigDecimal(0);
            for (DistrOutShelf distrOutShelf : distrOutDetail.getDistrOutShelfList()) {
                distrOutShelf.setOutId(outId);
                distrOutShelf.setDtlId(outDtlId);
                quantity = quantity.add(distrOutShelf.getQuantity());
            }
            if (distrOut.getStatus() != DistrOutStatus.AUDIT_REBUT) {
                Long baseDtlId = distrOutDetail.getBaseOrderDtlId();
                if (baseDtlId == null) {
                    throw new BusinessException("出库单明细基础明细id不能为空");
                }
                DistrSendDetail distrSendDetail = distrSendDetailMapper.selectByPrimaryKey(baseDtlId);
                if (distrSendDetail == null) {
                    throw new BusinessException("出库单明细基础明细id对应明细信息缺失,请检查数据");
                }
            }
            if (quantity.compareTo(distrOutDetail.getQuantity()) != 0) {
                throw new BusinessException("出库数量与配送单不一致,请检查出库数量!");
            }
            if (distrOutDetail.getQuantity().compareTo(distrOutDetail.getQuantity()) != 0) {
                throw new BusinessException("出库数量与明细数量不一致,请检查出库数量!");
            }
        }
        return distrOut;
    }

    /**
     * <配送出库单复核>
     *
     * @param distrOutCheckVo
     * @param userVO
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/10/11 11:06
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void check(DistrOutCheckVo distrOutCheckVo, UserVO userVO) throws Exception {
        commonComponent.distrOutcheck(distrOutCheckVo,userVO);
    }

    /**
     * <出库单导出>
     *
     * @param id
     * @param sta
     * @param userVO
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/10/11 14:50
     */
    @Override
    public void excelExport(OutputStream outPut, Long id, Long sta, UserVO userVO) {

        if (sta == DistrOutStatus.WAIT_OUT) {//配送单
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            map.put("goodsCode", "商品编码");
            map.put("goodsGenericName", "通用名称");
            map.put("dosageName", "剂型");
            map.put("goodsSpecification", "规格");

            map.put("unitName", "单位");
            map.put("manufacturer", "生产厂商");
            map.put("goodsPlace", "产地");
            map.put("quantity", "数量");
            map.put("unitPrice", "单价");
            map.put("lineDiscount", "折扣");

            map.put("amount", "金额");
            map.put("taxRate", "税率");
            map.put("notaxRealPrice", "不含税单价");

            map.put("notaxRealAmount", "不含税金额");
            map.put("taxAmount", "税额");
            map.put("remark", "备注");
            DistrSend distrSend = distrSendMapper.selectByPrimaryKey(id);
            List<DistrSendDetail> distrSendDetailList = distrSendDetailMapper.listDistrSendDetailList(id);

            StringBuilder titleSecondRow = new StringBuilder();
            titleSecondRow.append("要货单位编码:");
            titleSecondRow.append(distrSend.getRequestUnitCode() == null ? "" : distrSend.getRequestUnitCode());
            titleSecondRow.append("     ");
            titleSecondRow.append("要货单位名称:");
            titleSecondRow.append(distrSend.getRequestUnitName() == null ? "" : distrSend.getRequestUnitName());
            titleSecondRow.append("     ");
            titleSecondRow.append("配货日期:");
            titleSecondRow.append(new SimpleDateFormat("yyyy-MM-dd").format(distrSend.getDistrDate()));
            titleSecondRow.append("     ");
            titleSecondRow.append("配货人员:");
            titleSecondRow.append(distrSend.getDistrManName() == null ? "" : distrSend.getDistrManName());
            titleSecondRow.append("     ");
            titleSecondRow.append("配货单号:");
            titleSecondRow.append(distrSend.getCode() == null ? "" : distrSend.getCode());
            titleSecondRow.append("     ");
            titleSecondRow.append("配货类型:");
            String distrType = "";
            switch (distrSend.getDistrType()) {
                case 0:
                    distrType = "总部配送";
                    break;
                case 1:
                    distrType = "分店间调剂";
                    break;
                case 2:
                    distrType = "直调配送";
                    break;
            }
            titleSecondRow.append(distrType);
            titleSecondRow.append("     ");
            titleSecondRow.append("流通监管码:");
            titleSecondRow.append("");
            titleSecondRow.append("     ");
            List<String> name = new ArrayList<>();
            name.add(userVO.getEnterpriseName());
            name.add("配货出库单");
            List<String> secondTitle = new ArrayList<>();
            secondTitle.add(titleSecondRow.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("总商品金额:   ");
            sb.append(distrSend.getAmountTotal());
            sb.append(";");
            sb.append("折扣:   ");
            sb.append(distrSend.getWholeDiscount());
            sb.append("%");
            sb.append("  ");
            sb.append(distrSend.getAmountTotal().subtract(distrSend.getAmountTotal().multiply(distrSend.getWholeDiscount()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)));
            sb.append(";");
            sb.append("优惠:");
            sb.append("   ");
            sb.append(distrSend.getWholeDiscountAmount());
            sb.append(";");
            sb.append("总额:");
            sb.append("   ");
            sb.append(distrSend.getRealAmountTotal());
            sb.append(";");
            sb.append("不含税总额:");
            sb.append("   ");
            sb.append(distrSend.getNotaxRealAmountTotal());
            sb.append(";");
            sb.append("税额:");
            sb.append("   ");
            sb.append(distrSend.getTaxAmountTotal());

            StringBuilder endTotal = new StringBuilder();
            BigDecimal totalQuantity = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal totalNotaxRealAmount = BigDecimal.ZERO;
            BigDecimal totalTaxAmount = BigDecimal.ZERO;
            for (DistrSendDetail d : distrSendDetailList) {
                totalQuantity = totalQuantity.add(d.getQuantity());
                totalAmount = totalAmount.add(d.getAmount());
                totalNotaxRealAmount = totalNotaxRealAmount.add(d.getNotaxRealAmount());
                totalTaxAmount = totalTaxAmount.add(d.getTaxAmount());
            }
            endTotal.append(totalQuantity);
            endTotal.append(";");
            endTotal.append(totalAmount);
            endTotal.append(";");
            endTotal.append(totalNotaxRealAmount);
            endTotal.append(";");
            endTotal.append(totalTaxAmount);
            List<String> locationList = new ArrayList<String>();
            locationList.add("quantity");
            locationList.add("amount");
            locationList.add("notaxRealAmount");
            locationList.add("taxAmount");
            purchaseGeneralComponent.commExcelDistrExport(outPut, map, distrSendDetailList, name, secondTitle, sb.toString(), endTotal.toString(), locationList);
        } else if (sta == DistrOutStatus.WAIT_AUDIT) {//出库单
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            map.put("goodsCode", "商品编码");
            map.put("goodsGenericName", "通用名称");
            map.put("dosageName", "剂型");
            map.put("goodsSpecification", "规格");

            map.put("unitName", "单位");
            map.put("manufacturer", "生产厂商");
            map.put("goodsPlace", "产地");
            map.put("quantity", "数量");
            map.put("unitPrice", "单价");
            map.put("lineDiscount", "折扣");

            map.put("amount", "金额");
            map.put("taxRate", "税率");
            map.put("notaxRealPrice", "不含税单价");

            map.put("notaxRealAmount", "不含税金额");
            map.put("taxAmount", "税额");
            map.put("remark", "备注");
            DistrOut distrOut = distrOutMapper.selectByPrimaryKey(id);
            List<DistrOutDetail> distrOutDetailList = distrOutDetailMapper.getDistrOutDetailList(id);

            StringBuilder titleSecondRow = new StringBuilder();
            titleSecondRow.append("要货单位编码:");
            titleSecondRow.append(distrOut.getRequestUnitCode() == null ? "" : distrOut.getRequestUnitCode());
            titleSecondRow.append("     ");
            titleSecondRow.append("要货单位名称:");
            titleSecondRow.append(distrOut.getRequestUnitName() == null ? "" : distrOut.getRequestUnitName());
            titleSecondRow.append("     ");
            titleSecondRow.append("出库日期:");
            titleSecondRow.append(new SimpleDateFormat("yyyy-MM-dd").format(distrOut.getOutDate()));
            titleSecondRow.append("     ");
            titleSecondRow.append("出库人员:");
            titleSecondRow.append(distrOut.getOutManName() == null ? "" : distrOut.getOutManName());
            titleSecondRow.append("     ");
            titleSecondRow.append("配货出库单号:");
            titleSecondRow.append(distrOut.getCode() == null ? "" : distrOut.getCode());
            titleSecondRow.append("     ");
            titleSecondRow.append("配货类型:");
            String distrType = "";
            switch (distrOut.getDistrType()) {
                case 0:
                    distrType = "总部配送";
                    break;
                case 1:
                    distrType = "分店间调剂";
                    break;
                case 2:
                    distrType = "直调配送";
                    break;
            }
            titleSecondRow.append(distrType);
            titleSecondRow.append("     ");
            titleSecondRow.append("流通监管码:");
            titleSecondRow.append(distrOut.getFlowCode() == null ? "" : distrOut.getFlowCode());
            titleSecondRow.append("     ");
            List<String> name = new ArrayList<>();
            name.add(userVO.getEnterpriseName());
            name.add("配货出库单");
            List<String> secondTitle = new ArrayList<>();
            secondTitle.add(titleSecondRow.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("总商品金额:   ");
            sb.append(distrOut.getAmountTotal());
            sb.append(";");
            sb.append("折扣:   ");
            sb.append(distrOut.getWholeDiscount());
            sb.append("%");
            sb.append("  ");
            sb.append(distrOut.getAmountTotal().subtract(distrOut.getAmountTotal().multiply(distrOut.getWholeDiscount()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)));
            sb.append(";");
            sb.append("优惠:");
            sb.append("   ");
            sb.append(distrOut.getWholeDiscountAmount());
            sb.append(";");
            sb.append("总额:");
            sb.append("   ");
            sb.append(distrOut.getRealAmountTotal());
            sb.append(";");
            sb.append("不含税总额:");
            sb.append("   ");
            sb.append(distrOut.getNotaxRealAmountTotal());
            sb.append(";");
            sb.append("税额:");
            sb.append("   ");
            sb.append(distrOut.getTaxAmountTotal());

            StringBuilder endTotal = new StringBuilder();
            BigDecimal totalQuantity = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal totalNotaxRealAmount = BigDecimal.ZERO;
            BigDecimal totalTaxAmount = BigDecimal.ZERO;
            for (DistrOutDetail d : distrOutDetailList) {
                totalQuantity = totalQuantity.add(d.getQuantity());
                totalAmount = totalAmount.add(d.getAmount());
                totalNotaxRealAmount = totalNotaxRealAmount.add(d.getNotaxRealAmount());
                totalTaxAmount = totalTaxAmount.add(d.getTaxAmount());
            }
            endTotal.append(totalQuantity);
            endTotal.append(";");
            endTotal.append(totalAmount);
            endTotal.append(";");
            endTotal.append(totalNotaxRealAmount);
            endTotal.append(";");
            endTotal.append(totalTaxAmount);
            List<String> locationList = new ArrayList<String>();
            locationList.add("quantity");
            locationList.add("amount");
            locationList.add("notaxRealAmount");
            locationList.add("taxAmount");
            purchaseGeneralComponent.commExcelDistrExport(outPut, map, distrOutDetailList, name, secondTitle, sb.toString(), endTotal.toString(), locationList);
        } else if (sta == DistrOutStatus.FINISHED) {
            LinkedHashMap<String, String> map = new LinkedHashMap<>();
            map.put("goodsCode", "商品编码");
            map.put("goodsGenericName", "通用名称");
            map.put("dosageName", "剂型");
            map.put("goodsSpecification", "规格");

            map.put("unitName", "单位");
            map.put("manufacturer", "生产厂商");
            map.put("goodsPlace", "产地");
            map.put("quantity", "数量");
            map.put("unitPrice", "单价");
            map.put("lineDiscount", "折扣");

            map.put("amount", "金额");
            map.put("taxRate", "税率");
            map.put("notaxRealPrice", "不含税单价");

            map.put("notaxRealAmount", "不含税金额");
            map.put("taxAmount", "税额");
            map.put("remark", "备注");
            DistrOut distrOut = distrOutMapper.selectByPrimaryKey(id);
            List<DistrOutDetail> distrOutDetailList = distrOutDetailMapper.getDistrOutDetailList(id);

            StringBuilder titleSecondRow = new StringBuilder();
            titleSecondRow.append("要货单位编码:");
            titleSecondRow.append(distrOut.getRequestUnitCode() == null ? "" : distrOut.getRequestUnitCode());
            titleSecondRow.append("     ");
            titleSecondRow.append("要货单位名称:");
            titleSecondRow.append(distrOut.getRequestUnitName() == null ? "" : distrOut.getRequestUnitName());
            titleSecondRow.append("     ");
            titleSecondRow.append("出库日期:");
            titleSecondRow.append(new SimpleDateFormat("yyyy-MM-dd").format(distrOut.getOutDate()));
            titleSecondRow.append("     ");
            titleSecondRow.append("出库人员:");
            titleSecondRow.append(distrOut.getOutManName() == null ? "" : distrOut.getOutManName());
            titleSecondRow.append("     ");
            titleSecondRow.append("复核人员:");
            titleSecondRow.append(distrOut.getCheckerName() == null ? "" : distrOut.getCheckerName());
            titleSecondRow.append("     ");
            titleSecondRow.append("配货出库单号:");
            titleSecondRow.append(distrOut.getCode() == null ? "" : distrOut.getCode());
            titleSecondRow.append("     ");
            titleSecondRow.append("配货类型:");
            String distrType = "";
            switch (distrOut.getDistrType()) {
                case 0:
                    distrType = "总部配送";
                    break;
                case 1:
                    distrType = "分店间调剂";
                    break;
                case 2:
                    distrType = "直调配送";
                    break;
            }
            titleSecondRow.append(distrType);
            titleSecondRow.append("     ");
            titleSecondRow.append("流通监管码:");
            titleSecondRow.append(distrOut.getFlowCode() == null ? "" : distrOut.getFlowCode());
            titleSecondRow.append("     ");
            List<String> name = new ArrayList<>();
            name.add(userVO.getEnterpriseName());
            name.add("配货出库单");
            List<String> secondTitle = new ArrayList<>();
            secondTitle.add(titleSecondRow.toString());
            StringBuilder sb = new StringBuilder();
            sb.append("总商品金额:   ");
            sb.append(distrOut.getAmountTotal());
            sb.append(";");
            sb.append("折扣:   ");
            sb.append(distrOut.getWholeDiscount());
            sb.append("%");
            sb.append("  ");
            sb.append(distrOut.getAmountTotal().subtract(distrOut.getAmountTotal().multiply(distrOut.getWholeDiscount()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)));
            sb.append(";");
            sb.append("优惠:");
            sb.append("   ");
            sb.append(distrOut.getWholeDiscountAmount());
            sb.append(";");
            sb.append("总额:");
            sb.append("   ");
            sb.append(distrOut.getRealAmountTotal());
            sb.append(";");
            sb.append("不含税总额:");
            sb.append("   ");
            sb.append(distrOut.getNotaxRealAmountTotal());
            sb.append(";");
            sb.append("税额:");
            sb.append("   ");
            sb.append(distrOut.getTaxAmountTotal());

            StringBuilder endTotal = new StringBuilder();
            BigDecimal totalQuantity = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal totalNotaxRealAmount = BigDecimal.ZERO;
            BigDecimal totalTaxAmount = BigDecimal.ZERO;
            for (DistrOutDetail d : distrOutDetailList) {
                totalQuantity = totalQuantity.add(d.getQuantity());
                totalAmount = totalAmount.add(d.getAmount());
                totalNotaxRealAmount = totalNotaxRealAmount.add(d.getNotaxRealAmount());
                totalTaxAmount = totalTaxAmount.add(d.getTaxAmount());
            }
            endTotal.append(totalQuantity);
            endTotal.append(";");
            endTotal.append(totalAmount);
            endTotal.append(";");
            endTotal.append(totalNotaxRealAmount);
            endTotal.append(";");
            endTotal.append(totalTaxAmount);
            List<String> locationList = new ArrayList<String>();
            locationList.add("quantity");
            locationList.add("amount");
            locationList.add("notaxRealAmount");
            locationList.add("taxAmount");
            purchaseGeneralComponent.commExcelDistrExport(outPut, map, distrOutDetailList, name, secondTitle, sb.toString(), endTotal.toString(), locationList);
        }
    }

    /**
     * <获取合格品货位信息>>
     *
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/10/11 17:29
     */
    @Override
    public List<StockLockRecordVO> getQualifiedGoodsInfoShelf(String lotNum, Long goodsId, Long baseOrderId, UserVO userVO) {
//		return commonComponent.getQualifiedGoodsInfoShelf(goodsId,userVO.getEnterpriseId());
        StockLockShelfVO stockLockShelfVO = new StockLockShelfVO();
        stockLockShelfVO.setGoodsId(goodsId);
        stockLockShelfVO.setLotNum(lotNum);
        stockLockShelfVO.setBaseOrderId(baseOrderId);

        List<StockLockRecordVO> stockLockRecord = commonComponent.getStockLockRecord(stockLockShelfVO, userVO.getEnterpriseId());

        ManageConfig mangeConfigByEPId = manageConfigComponent.getMangeConfigByEPId(userVO);
        Integer businessControl = mangeConfigByEPId.getBusinessControl();
        if (businessControl == 0) {
            //业务流程控制按钮关闭
            stockLockRecord.forEach(item -> {
                item.setUsableQuantity(item.getQuantity());
            });
        } else {
            stockLockRecord.forEach(item -> {
                item.setQuantity(item.getUsableQuantity());
            });
        }
        return stockLockRecord;
    }

    @Override
    public int update(DistrOutSaveOrupdateVO distrOut, UserVO userVO) throws Exception {
        DistrOut copy = (DistrOut) EntityUtils.reflectUpdateSetDefaultValue(new DistrOut().getClass(), userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrOut, copy);
        return distrOutMapper.updateByPrimaryKeySelective(copy);
    }

    @Override
    public int delete(Long id) throws Exception {
        return distrOutMapper.deleteByPrimaryKey(id);
    }

    /**
     * <获取配货出库列表数据>
     *
     * @param requestDistrOutPram
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/10/7 19:13
     */
    @Override
    public void getDistrOutDataList(RequestDistrOutPram requestDistrOutPram, Page page, UserVO userVO) {
        if (requestDistrOutPram.getStatus().intValue() == DistrOutStatus.WAIT_OUT) {
            //请求配货单列表接口
            Map map = new HashMap<>();
            List<Integer> statusList = new ArrayList<>();
            statusList.add(DistrSendStatus.WAIT_OUT);
            map.put("status", statusList);
            map.put("statusTwo", DistrSendStatus.AUDIT_REBUT);
            map.put("startTime", requestDistrOutPram.getStartDate());
            map.put("endTime", requestDistrOutPram.getEndDate());
            map.put("requestUnitCode", requestDistrOutPram.getRequestUnitCode());
            map.put("requestUnitName", requestDistrOutPram.getRequestUnitName());
            map.put("distrCode", requestDistrOutPram.getCode());
            map.put("distrType", requestDistrOutPram.getDistrType());
            map.put("distrManName", requestDistrOutPram.getSendManName());
            map.put("enterpriseId", userVO.getEnterpriseId());
            map.put("sortDate", requestDistrOutPram.getSortDate());
            map.put("sortCode", requestDistrOutPram.getSortCode());
            map.put("pageNo", page.getStart());
            map.put("pageSize", requestDistrOutPram.getPageSize());

            page = getDistrSendPage(page, map);
//            page = distrSendService.getDistrSendPage(page, map);
        } else {
            DistrListTotalVO distrListTotalVO = distrOutMapper.getSumPrice(requestDistrOutPram);
            if (distrListTotalVO == null) {
                page.setTotalRecord(0);
                page.setResult(new ArrayList<DistrListVO>());
                return;
            }
            requestDistrOutPram.setPageNo(page.getStart());
            //请求配货出库单接口
            int count = distrOutMapper.countDistrOutDataList(requestDistrOutPram);
            Integer sortDate = requestDistrOutPram.getSortDate();
            Integer sortCode = requestDistrOutPram.getSortCode();
            String sort = "";
            if (sortDate == null && sortCode == null) {
                sort = "";
            }
            if (sortDate != null && sortDate == 0) {
                sort += "a.out_date,";
            }
            if (sortDate != null && sortDate == 1) {
                sort += "a.out_date desc,";
            }
            if (sortCode != null && sortCode == 0) {
                sort += "a.code,";
            }
            if (sortCode != null && sortCode == 1) {
                sort += "a.code desc,";
            }
            if (!"".equals(sort)) {
                sort = sort.substring(0, sort.length() - 1);
            }
            requestDistrOutPram.setSort(sort);
            List<DistrListVO> distrListVOList = distrOutMapper.getDistrOutDataList(requestDistrOutPram);
            for (DistrListVO distrListVO : distrListVOList) {
                Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(distrListVO.getRequestUnitId());
                if (enterprise == null) {
                    throw new BusinessException("无效的企业ID：" + distrListVO.getRequestUnitId());
                }
                if (enterprise.getChainType() == ChainType.Division.getCode()) {
                    distrListVO.setFranchisedStoreFlag(1);
                } else {
                    distrListVO.setFranchisedStoreFlag(0);
                }
            }
            distrListTotalVO.setDistrListVOList(distrListVOList);
            page.setResult(distrListTotalVO);
            page.setTotalRecord(count);
        }
    }

    private Page getDistrSendPage(Page page, Map map) {

        //查询合计
        Integer sortDate = map.get("sortDate") == null ? null : (Integer) map.get("sortDate");
        Integer sortCode = map.get("sortCode") == null ? null : (Integer) map.get("sortCode");
        String sort = "";
        if (sortDate == null && sortCode == null) {
            sort = "";
        }
        if (sortDate != null && sortDate == 0) {
            sort += "distr_date,";
        }
        if (sortDate != null && sortDate == 1) {
            sort += "distr_date desc,";
        }
        if (sortCode != null && sortCode == 0) {
            sort += "code,";
        }
        if (sortCode != null && sortCode == 1) {
            sort += "code desc,";
        }
        if (!"".equals(sort)) {
            sort = sort.substring(0, sort.length() - 1);
        }
        map.put("sort", sort);
        DistrListTotalVO distrListTotalVO = distrSendMapper.getSearchResultTotal(map);
        //查询复核拒绝的单据
        DistrListTotalVO distrListTotalVOBack = distrOutMapper.getSearchResultTotalBack(map);
        if (distrListTotalVO == null) {
            distrListTotalVO = new DistrListTotalVO();
            if (distrListTotalVOBack != null) {
                distrListTotalVO.setTotalSumNotaxRealAmount(distrListTotalVOBack.getTotalSumNotaxRealAmount());
                distrListTotalVO.setTotalSumRealAmout(distrListTotalVOBack.getTotalSumRealAmout());
                distrListTotalVO.setTotalSumTaxAmount(distrListTotalVOBack.getTotalSumTaxAmount());
            }
        } else {
            if (distrListTotalVOBack != null) {
                distrListTotalVO.setTotalSumNotaxRealAmount(distrListTotalVO.getTotalSumNotaxRealAmount().add(distrListTotalVOBack.getTotalSumNotaxRealAmount()));
                distrListTotalVO.setTotalSumRealAmout(distrListTotalVO.getTotalSumRealAmout().add(distrListTotalVOBack.getTotalSumRealAmout()));
                distrListTotalVO.setTotalSumTaxAmount(distrListTotalVO.getTotalSumTaxAmount().add(distrListTotalVOBack.getTotalSumTaxAmount()));
            } else {
                distrListTotalVO.setTotalSumNotaxRealAmount(distrListTotalVO.getTotalSumNotaxRealAmount());
                distrListTotalVO.setTotalSumRealAmout(distrListTotalVO.getTotalSumRealAmout());
                distrListTotalVO.setTotalSumTaxAmount(distrListTotalVO.getTotalSumTaxAmount());
            }

        }


//        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        //查询配货单列表
        Integer count = distrSendMapper.getDistrSendOrDistrOutPageCount(map);
        List<DistrListVO> distrListVOList = distrSendMapper.getDistrSendOrDistrOutPage(map);
        for (DistrListVO distrListVO : distrListVOList) {
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(distrListVO.getRequestUnitId());
            if (enterprise == null) {
                throw new BusinessException("无效的企业ID：" + distrListVO.getRequestUnitId());
            }
            if (enterprise.getChainType() == ChainType.Division.getCode()) {
                distrListVO.setFranchisedStoreFlag(1);
            } else {
                distrListVO.setFranchisedStoreFlag(0);
            }
        }

        distrListTotalVO.setDistrListVOList(distrListVOList == null ? new ArrayList<>() : distrListVOList);

        page.setResult(distrListTotalVO);
        page.setTotalRecord(count);
//        page.setTotalPage(hPage.getPages());
        return page;
    }

    /**
     * <根据配送单id封装出库单数据>
     *
     * @param id
     * @Author: Zhengbin.jin 金正斌
     * @Email: Zhengbin.jin@rograndec.com
     * @2017/10/8 18:44
     */
    @Override
    public DistrOut getDistrOutDataForSave(Long id, UserVO userVO) throws Exception {
        DistrSend distrSend = distrSendMapper.selectByPrimaryKey(id);
        if (distrSend == null) {
            throw new BusinessException("没有找到该配送单,请检查配送单id=" + id);
        }
        if (distrSend.getStatus() != DistrSendStatus.WAIT_OUT) {
            throw new BusinessException("该配送单无法出库,请检查配送单状态");
        }
        DistrOut distrOut = new DistrOut();
        DistrOut distrOutOld = distrOutMapper.selectByBaseOrderId(userVO.getEnterpriseId(), id);
        //封装出库单信息
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrSend, distrOut);
        if (distrOutOld != null) {
            distrOut.setId(distrOutOld.getId());
        }
        distrOut.setBaseOrderCode(distrSend.getCode());
        distrOut.setBaseOrderDate(distrSend.getDistrDate());
        distrOut.setBaseOrderType(distrSend.getOrderType());
        distrOut.setSendManCode(distrSend.getDistrManCode());
        distrOut.setSendManId(distrSend.getDistrManId());
        distrOut.setSendManName(distrSend.getDistrManName());
        distrOut.setOutManId(userVO.getUserId());
        distrOut.setOutManName(userVO.getUserName());
        distrOut.setOutManCode(userVO.getUserCode());
        distrOut.setBaseOrderId(distrSend.getId());
        List<DistrSendDetail> distrSendDetailList = distrSendDetailMapper.listDistrSendDetailList(distrSend.getId());
        List<DistrOutDetail> distrOutDetailList = new ArrayList<>();
        for (DistrSendDetail distrSendDetail : distrSendDetailList) {
            //封装出库单明细数据
            DistrOutDetail distrOutDetail = new DistrOutDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrSendDetail, distrOutDetail);
            distrOutDetail.setBaseOrderDtlId(distrSendDetail.getId());
            distrOutDetail.setBaseOrderId(distrSend.getId());
            List<DistrOutShelf> distrOutShelfList = new ArrayList<>();

            StockLockShelfVO stockLockShelfVO = new StockLockShelfVO();
            stockLockShelfVO.setCode(distrSend.getCode());
            List<StockLockRecordVO> goodsInfoStockShelfVOList = commonComponent.getStockLockRecordByCode(stockLockShelfVO, userVO.getEnterpriseId());

            if (distrOutOld != null) {
                DistrOutDetail distrOutDetailOld = distrOutDetailMapper.selectByBaseOrderDtlId(userVO.getEnterpriseId(), distrSendDetail.getId());
                if (distrOutDetailOld != null) {
                    distrOutDetail.setId(distrOutDetailOld.getId());
                }
            }
            /**
             * 直接取配货单的单价作为实际出库的价格
             */
            BigDecimal unitPrice = distrSendDetail.getUnitPrice() == null ? BigDecimal.ZERO : distrSendDetail.getUnitPrice();
            distrOutDetail.setUnitPrice(unitPrice);

            buildDistrOutShelfData(distrOut, distrOutShelfList, goodsInfoStockShelfVOList, distrOutDetail);
            distrOutDetail.setDistrOutShelfList(distrOutShelfList);
            distrOutDetailList.add(distrOutDetail);
        }
        distrOut.setDistrOutDetailList(distrOutDetailList);
        //重新计算所有价格信息
        commonComponent.calculationPrice(distrOut);
        return distrOut;
    }


    /**
     * 购进配货出库货位明细单
     *
     * @param distrOutShelfList
     * @param goodsInfoStockShelfVOList
     * @param distrOutDetail
     */
    private void buildDistrOutShelfData(DistrOut distrOut, List<DistrOutShelf> distrOutShelfList,
                                        List<StockLockRecordVO> goodsInfoStockShelfVOList, DistrOutDetail distrOutDetail) {
        BigDecimal outQuantity = new BigDecimal(0);
        for (StockLockRecordVO goodsInfoStockShelfVO : goodsInfoStockShelfVOList) {
            if (goodsInfoStockShelfVO.getGoodsId().equals(distrOutDetail.getGoodsId())) {
                DistrOutShelf distrOutShelf = new DistrOutShelf();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrOutDetail, distrOutShelf);
                outQuantity = outQuantity.add(goodsInfoStockShelfVO.getUsableQuantity());

//                distrOutShelf.setId();
                distrOutShelf.setShelfId(goodsInfoStockShelfVO.getShelfId());
                distrOutShelf.setLotId(goodsInfoStockShelfVO.getLotId());
                distrOutShelf.setLotNumber(goodsInfoStockShelfVO.getLotNum());
                distrOutShelf.setProductDate(goodsInfoStockShelfVO.getProductDate());
                distrOutShelf.setValidDate(goodsInfoStockShelfVO.getValidDate());
                distrOutShelf.setShelfName(goodsInfoStockShelfVO.getShelfName());
                distrOutShelf.setShelfStatusDesc(goodsInfoStockShelfVO.getShelfStatusDesc());
                if (outQuantity.compareTo(distrOutDetail.getQuantity()) == 0 || outQuantity.compareTo(distrOutDetail.getQuantity()) == 1) {
                    distrOutShelf.setQuantity(goodsInfoStockShelfVO.getUsableQuantity().subtract(outQuantity.subtract(distrOutDetail.getQuantity())));
                    distrOutShelfList.add(distrOutShelf);
                    break;
                } else {
                    distrOutShelf.setQuantity(goodsInfoStockShelfVO.getUsableQuantity());
                    distrOutShelfList.add(distrOutShelf);
                }
                // 重算单价金额等相关信息
                try {
                    // 单价
                    BigDecimal unitPrice = distrOutDetail.getUnitPrice();
                    // 行折扣
                    BigDecimal lineDiscount = distrOutDetail.getLineDiscount();
                    // 整单折扣
                    BigDecimal wholeDiscount = distrOutDetail.getWholeDiscount();
                    // 整单优惠
                    BigDecimal wholeRuondOff = distrOut.getWholeDiscountAmount();
                    Long taxRateId = distrOutDetail.getTaxRateId();
                    BigDecimal taxRate = distrOutDetail.getTaxRate();
                    // 整单金额合计
                    BigDecimal wholeAmountTotal = distrOutDetail.getAmount();
                    // 重算货位明细单单价、金额信息
                    CalculateResultModel resultModel = CalculateComponent.getCalculateResult(outQuantity, distrOutDetail.getUnitPrice(), lineDiscount,
                            wholeDiscount, wholeRuondOff, taxRate, wholeAmountTotal);
                    distrOutShelf.setUnitPrice(unitPrice);
                    distrOutShelf.setLineDiscount(lineDiscount);
                    distrOutShelf.setAmount(resultModel.getAmount());
                    distrOutShelf.setWholeDiscount(wholeDiscount);
                    distrOutShelf.setLineDiscountAmount(resultModel.getLineRoundOff());
                    distrOutShelf.setRealPrice(resultModel.getRealPrice());
                    distrOutShelf.setRealAmount(resultModel.getRealAmount());
                    distrOutShelf.setTaxRateId(taxRateId);
                    distrOutShelf.setTaxRate(taxRate);
                    distrOutShelf.setNotaxRealPrice(resultModel.getNotaxPrice());
                    distrOutShelf.setNotaxRealAmount(resultModel.getNotaxAmount());
                    distrOutShelf.setTaxAmount(resultModel.getTaxAmount());
                    distrOutShelf.setUnclearQuantity(outQuantity);
                    distrOutShelf.setClearQuantity(BigDecimal.ZERO);
                } catch (Exception e) {
                    logger.error("配货出库单重算货位明细单价、金额相关信息异常！" + e.getMessage());
                    throw new BusinessException("配货出库单重算货位明细单价、金额相关信息异常！" + e.getMessage());
                }

            }
        }
    }



    @Override
    public void getDistrOutList(Page<OrderReportVo<DistrOutReportVo>> page, RequestDistrOut requestDistrOut) {
        if (requestDistrOut.getPageNo() != null && requestDistrOut.getPageSize() != null) {
            requestDistrOut.setPageNo(page.getStart());
        }
        int count = distrOutMapper.getDistrOutListCount(requestDistrOut);
        Integer sortDate = requestDistrOut.getSortDate();
        Integer sortCode = requestDistrOut.getSortCode();
        String sort = "";
        if (sortDate == null && sortCode == null) {
            sort = "";
        }
        if (sortDate != null && sortDate == 0) {
            sort += "a.out_date,";
        }
        if (sortDate != null && sortDate == 1) {
            sort += "a.out_date desc,";
        }
        if (sortCode != null && sortCode == 0) {
            sort += "a.code,";
        }
        if (sortCode != null && sortCode == 1) {
            sort += "a.code desc,";
        }
        if (!"".equals(sort)) {
            sort = sort.substring(0, sort.length() - 1);
        }
        requestDistrOut.setSort(sort);
        List<DistrOutReportVo> distrOutReportVos = distrOutMapper.getDistrOutList(requestDistrOut);
        OrderReportVo<DistrOutReportVo> orderReportVo = new OrderReportVo<>();
        orderReportVo.setDataList(distrOutReportVos);
        requestDistrOut.setPageNo(null);
        requestDistrOut.setPageSize(null);
        List<DistrOutReportVo> distrOutReportVos2 = distrOutMapper.getDistrOutList(requestDistrOut);
        BigDecimal quantity = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal realAmount = BigDecimal.ZERO;
        BigDecimal notaxRealAmount = BigDecimal.ZERO;
        BigDecimal taxAmount = BigDecimal.ZERO;
        for (DistrOutReportVo d : distrOutReportVos2) {
            if(null != d.getQuantity()){
                quantity = quantity.add(d.getQuantity());
            }
            if(null != d.getAmount()) {
                amount = amount.add(d.getAmount());
            }
            if(null != d.getRealAmount()) {
                realAmount = realAmount.add(d.getRealAmount());
            }
            if(null != d.getNotaxRealAmount()) {
                notaxRealAmount = notaxRealAmount.add(d.getNotaxRealAmount());
            }
            if(null != d.getTaxAmount()) {
                taxAmount = taxAmount.add(d.getTaxAmount());
            }
        }
        orderReportVo.setQuantity(quantity);
        orderReportVo.setAmount(amount);
        orderReportVo.setRealAmount(realAmount);
        orderReportVo.setNotaxRealAmount(notaxRealAmount);
        orderReportVo.setTaxAmount(taxAmount);
        page.setTotalRecord(count);
        page.setResult(orderReportVo);
    }

    @Override
    public void excelExportReport(OutputStream output, List<DistrOutReportVo> distrOutReportVos, UserVO userVO) {
        List<String> names = new ArrayList<>();
        names.add(userVO.getEnterpriseName());
        names.add("配货出库单");
        //内容数据
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("sendDateStr", "日期");
        map.put("code", "单号");
        map.put("requestUnitCode", "要货单位编码");
        map.put("requestUnitName", "要货单位名称");
        map.put("outManName", "出库人员");
        map.put("checkerName", "复核人员");
        map.put("distrTypeStr", "配货类型");
        map.put("flowCode", "流通监管码");
        map.put("baseOrderCode", "配货单号");
        map.put("goodsCode", "商品编码");
        map.put("barcode", "条形码");
        map.put("goodsGenericName", "通用名称");
        map.put("goodsName", "商品名称");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("unitName", "单位");
        map.put("manufacturer", "生产厂商");
        map.put("goodsPlace", "产地");
        map.put("approvalNumber", "批准文号");
        map.put("lotNumber", "批号");
        map.put("productDateStr", "生产日期");
        map.put("validDateStr", "有效期至");
        map.put("shelfName", "货位");
        map.put("shelfStatusDesc", "质量状况");
        map.put("quantity", "数量");
        map.put("unitPrice", "单价");
        map.put("lineDiscount", "折扣");
        map.put("amount", "金额");
        map.put("wholeDiscount", "整单折扣");
        map.put("lineDiscountAmount", "优惠分摊");
        map.put("realPrice", "实际单价");
        map.put("realAmount", "实际金额");
        map.put("taxRate", "税率");
        map.put("notaxRealPrice", "不含税单价");
        map.put("notaxRealAmount", "不含税金额");
        map.put("taxAmount", "税额");
        map.put("statusStr", "状态");
        map.put("businessVarietyName", "品种类型");
        map.put("categoryName", "商品分类");
        map.put("goodsAttributeName", "商品属性");
        map.put("domesticImportDesc", "国产/进口");
        map.put("managementScopeName", "经营范围");
        map.put("specialDrugName", "特殊管理药品");
        map.put("inChargeDrugName", "专管药品");
        map.put("limitQuantity", "限购数量");
        map.put("storageTempDesc", "贮藏温度");
        map.put("storageConditionName", "贮藏条件");
        map.put("qualityPeriodDesc", "保质期");
        StringBuilder endTotal = new StringBuilder();
        BigDecimal quantity = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal realAmount = BigDecimal.ZERO;
        BigDecimal notaxRealAmount = BigDecimal.ZERO;
        BigDecimal taxAmount = BigDecimal.ZERO;
        for (DistrOutReportVo d : distrOutReportVos) {
            quantity = quantity.add(d.getQuantity());
            amount = amount.add(d.getAmount());
            realAmount = realAmount.add(d.getRealAmount());
            notaxRealAmount = notaxRealAmount.add(d.getNotaxRealAmount());
            taxAmount = taxAmount.add(d.getTaxAmount());
        }
        endTotal.append(quantity);
        endTotal.append(";");
        endTotal.append(amount);
        endTotal.append(";");
        endTotal.append(realAmount);
        endTotal.append(";");
        endTotal.append(notaxRealAmount);
        endTotal.append(";");
        endTotal.append(taxAmount);
        List<String> locationList = new ArrayList<String>();
        locationList.add("quantity");
        locationList.add("amount");
        locationList.add("realAmount");
        locationList.add("notaxRealAmount");
        locationList.add("taxAmount");
        purchaseGeneralComponent.commExcelExport(output, map, distrOutReportVos, names, null, endTotal.toString(), false, locationList);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void saveDistrOut(UserVO loginUser, SaveDistrOutVO saveDistrOutVO) throws Exception {

       commonComponent.saveDistrOut(loginUser,saveDistrOutVO,true);
    }



















    //该计算只能在 复核驳回的单据重新  出库时调用
    private void calculationCheckDistrOutPrice(DistrOut distrOut, DistrOut DistrOutOld) {
        //数量合计
        BigDecimal quantityTotal = BigDecimal.ZERO;
        //整单金额
        BigDecimal amountTotal = BigDecimal.ZERO;
        //行金额
        BigDecimal lineAmount;
        for (DistrOutDetail distrOutDetail : DistrOutOld.getDistrOutDetailList()) {
            quantityTotal = quantityTotal.add(distrOutDetail.getQuantity());
            //行金额=数量*单价*行折扣
            lineAmount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(distrOutDetail.getQuantity(), distrOutDetail.getUnitPrice(), distrOutDetail.getLineDiscount());
            amountTotal = amountTotal.add(lineAmount);
        }

        //数量合计
        distrOut.setQuantityTotal(quantityTotal);
        //品种合计
        distrOut.setVarietiesQuantity(DistrOutOld.getDistrOutDetailList().size());
        //金额合计（整单优惠前的金额合计）
        distrOut.setAmountTotal(amountTotal);
        //整单折扣
        distrOut.setWholeDiscount(DistrOutOld.getWholeDiscount());
        //整单优惠
        distrOut.setWholeDiscountAmount(DistrOutOld.getWholeDiscountAmount());
        //实际金额
        BigDecimal realAmountTotal = amountTotal.multiply(DistrOutOld.getWholeDiscount()).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).subtract(DistrOutOld.getWholeDiscountAmount());
        if (realAmountTotal.compareTo(BigDecimal.ZERO) < 0) {
            realAmountTotal = BigDecimal.ZERO;
        }
        distrOut.setRealAmountTotal(realAmountTotal);

        //行实际金额
        BigDecimal lineRealAmount;
        //行不含税实际金额
        BigDecimal notaxRealAmount;
        //行优惠（总优惠分摊）
        BigDecimal lineDiscountAmount = BigDecimal.ZERO;
        //行税额
        BigDecimal lineTaxAmount;
        //税额合计
        BigDecimal taxAmountTotal = BigDecimal.ZERO;
        for (DistrOutDetail distrOutDetail : DistrOutOld.getDistrOutDetailList()) {
            //行金额=数量*单价*行折扣
            lineAmount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(distrOutDetail.getQuantity(), distrOutDetail.getUnitPrice(), distrOutDetail.getLineDiscount());
            //优惠分摊 = 整单优惠*(金额/sum(金额))
            if (amountTotal.compareTo(BigDecimal.ZERO) == 1) {
                lineDiscountAmount = DistrOutOld.getWholeDiscountAmount().multiply(lineAmount.divide(amountTotal, 2, BigDecimal.ROUND_HALF_UP));
            }
            //行实际金额 = 数量*单价*行折扣*整单折扣-优惠分摊
            lineRealAmount = lineAmount
                    .multiply(DistrOutOld.getWholeDiscount())
                    .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)
                    .subtract(lineDiscountAmount);
            if (lineRealAmount.compareTo(BigDecimal.ZERO) < 0) {
                lineRealAmount = BigDecimal.ZERO;
            }
            //不含税实际金额：
            notaxRealAmount = lineRealAmount.divide(new BigDecimal(1).add(distrOutDetail.getTaxRate().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
            //行税额
            lineTaxAmount = lineRealAmount.subtract(notaxRealAmount);
            taxAmountTotal = taxAmountTotal.add(lineTaxAmount);
        }
        //税额合计
        distrOut.setTaxAmountTotal(taxAmountTotal);
        //不含税金额合计
        distrOut.setNotaxRealAmountTotal(distrOut.getRealAmountTotal().subtract(taxAmountTotal));
    }

    //该计算只能在 复核驳回的单据重新  出库时调用
    private void calculationCheckDistrOutDetailPrice(DistrOut distrOut, DistrOutDetail distrOutDetail, DistrOutDetail distrOutDetailNovel) {
        //获取默认税率
        if (distrOutDetailNovel.getTaxRateId() == null) {
            //税率
            Map<String, Object> distrTaxRateMap = getDistrTaxRate(distrOut.getRequestUnitId(), distrOutDetailNovel.getGoodsId());
            if (distrTaxRateMap != null) {
                distrOutDetailNovel.setTaxRateId(distrTaxRateMap.get("distrTaxRateId") == null ? 0 : (Long) distrTaxRateMap.get("distrTaxRateId"));
                distrOutDetailNovel.setTaxRate(distrTaxRateMap.get("distrTaxRate") == null ? BigDecimal.ZERO : new BigDecimal((Double) distrTaxRateMap.get("distrTaxRate")));
            } else {
                distrOutDetailNovel.setTaxRateId(new Long(0));
                distrOutDetailNovel.setTaxRate(BigDecimal.ZERO);
            }
        }


        distrOutDetail.setQuantity(distrOutDetailNovel.getQuantity());
        //单价
        distrOutDetail.setUnitPrice(distrOutDetailNovel.getUnitPrice());
        //行折扣
        distrOutDetail.setLineDiscount(distrOutDetailNovel.getLineDiscount());
        //金额（整单优惠前金额） = 数量*单价*行折扣
        BigDecimal lineAmount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(distrOutDetailNovel.getQuantity(), distrOutDetailNovel.getUnitPrice(), distrOutDetailNovel.getLineDiscount());
        distrOutDetail.setAmount(lineAmount);
        //整单折扣（%）
        distrOutDetail.setWholeDiscount(distrOut.getWholeDiscount());
        //优惠分摊 = 整单优惠*(金额/sum(金额))
        BigDecimal lineDiscountAmount = BigDecimal.ZERO;
        if (distrOut.getAmountTotal().compareTo(BigDecimal.ZERO) == 1) {
            lineDiscountAmount = distrOut.getWholeDiscountAmount().multiply(lineAmount.divide(distrOut.getAmountTotal(), 2, BigDecimal.ROUND_HALF_UP));
        }
        distrOutDetail.setLineDiscountAmount(lineDiscountAmount);
        //行实际金额 = 数量*单价*行折扣*整单折扣-优惠分摊
        BigDecimal lineRealAmount = lineAmount
                .multiply(distrOut.getWholeDiscount())
                .divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)
                .subtract(lineDiscountAmount);
        if (lineRealAmount.compareTo(BigDecimal.ZERO) < 0) {
            lineRealAmount = BigDecimal.ZERO;
        }
        distrOutDetail.setRealAmount(lineRealAmount);
        //实际单价：实际金额/数量
        distrOutDetail.setRealPrice(lineRealAmount.divide(distrOutDetailNovel.getQuantity(), 2, BigDecimal.ROUND_HALF_UP));
        //税率ID
        distrOutDetail.setTaxRateId(distrOutDetailNovel.getTaxRateId());
        //税率
        distrOutDetail.setTaxRate(distrOutDetailNovel.getTaxRate());
        //不含税实际金额
        BigDecimal notaxRealAmount = lineRealAmount.divide(new BigDecimal(1).add(distrOutDetailNovel.getTaxRate().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)), 2, BigDecimal.ROUND_HALF_UP);
        distrOutDetail.setNotaxRealAmount(notaxRealAmount);
        //不含税实际单价
        distrOutDetail.setNotaxRealPrice(notaxRealAmount.divide(distrOutDetailNovel.getQuantity(), 2, BigDecimal.ROUND_HALF_UP));
        //税额
        distrOutDetail.setTaxAmount(lineRealAmount.subtract(notaxRealAmount));
    }



    private Map<String, Object> getDistrTaxRate(Long enterpriseId, Long goodsId) {
        EnterpriseBusiness enterpriseBusiness = enterpriseBusinessMapper.queryEnterpriseBusinessByEnterpriseId(enterpriseId);
        return priceOrderDetailMapper.selectDistrTaxRateByGoodsIdAndDistrPriceOrderId(goodsId, enterpriseBusiness.getDistrPriceOrderId(), enterpriseId);
    }

    @Override
    public DistrPurchaseInStorageVO getPurchaseInStorageDtlShelfList(UserVO loginUser, Long id) {

        DistrPurchaseInStorageVO distrPurchaseInStorageVO = purchaseInStorageMapper.selectById(id);

        List<DistrPurchaseInstorageDetailShelfVO> distrPurchaseInstorageDetailShelfVOList = purchaseInStorageDetailMapper.selectByPurchaseInstorageId(id);
        if (distrPurchaseInstorageDetailShelfVOList.isEmpty()) {
            throw new BusinessException("无效的单据ID：" + id);
        }
        for (DistrPurchaseInstorageDetailShelfVO distrPurchaseInstorageDetailShelfVO : distrPurchaseInstorageDetailShelfVOList) {

            distrPurchaseInstorageDetailShelfVO.setCallDataDtlId(distrPurchaseInstorageDetailShelfVO.getId());

            List<DistrPurchaseInStorageShelfVO> distrPurchaseInStorageShelfVOS = purchaseInStorageShelfMapper.selectByPurchaseInstorageDetailId(distrPurchaseInstorageDetailShelfVO.getId());
            if (!distrPurchaseInStorageShelfVOS.isEmpty()) {
                for (DistrPurchaseInStorageShelfVO distrPurchaseInStorageShelfVO : distrPurchaseInStorageShelfVOS) {
                    GoodsShelfStatusDescVO goodsShelfStatusDescVO = chGoodsLoadService.getGoodsShelfStatusDesc(loginUser.getEnterpriseId(), distrPurchaseInStorageShelfVO.getShelfId());
                    if (goodsShelfStatusDescVO != null) {
                        distrPurchaseInStorageShelfVO.setShelfStatusDesc(goodsShelfStatusDescVO.getShelfStatusDesc());
                    }
                }
                distrPurchaseInstorageDetailShelfVO.setDistrPurchaseInStorageShelfVOS(distrPurchaseInStorageShelfVOS);
            }
        }
        distrPurchaseInStorageVO.setDistrPurchaseInstorageDetailShelfVOList(distrPurchaseInstorageDetailShelfVOList);
        return distrPurchaseInStorageVO;
    }

    @Override
    public List<GoodsLotShelfVO> getGoodsLotShelfList(UserVO loginUser, Long id) {

        List<GoodsLotShelfVO> goodsLotShelfVOS = distrOutShelfMapper.selectByGoodsIdAndEnterpriseId(loginUser.getEnterpriseId(), id);
        return goodsLotShelfVOS;
    }

    @Override
    public DistrReturnInStorageVO getDistrReturnInDetailShelfList(UserVO loginUser, Long id) {

        DistrReturnInStorageVO distrReturnInStorageVO = distrReturnInMapper.selectById(id);

        //查询调入单位信息
        DistrReturnNotice distrReturnNotice = distrReturnNoticeMapper.selectByPrimaryKey(distrReturnInStorageVO.getDistrOrderId());
        if (distrReturnNotice == null) {
            throw new BusinessException("无效的配后退回通知单单据ID：" + distrReturnInStorageVO.getDistrOrderId());
        }
        DistrInReturnOut distrInReturnOut = distrInReturnOutMapper.selectByPrimaryKey(distrReturnNotice.getBaseOrderId());
        if (distrInReturnOut == null) {
            throw new BusinessException("无效的配进退出出库单单据ID：" + distrReturnNotice.getBaseOrderId());
        }
//        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(distrInReturnOut.getEnterpriseId());
//        if(enterprise == null){
//            throw new BusinessException("无效的企业ID："+distrInReturnOut.getEnterpriseId());
//        }

        distrReturnInStorageVO.setRequestUnitId(distrInReturnOut.getOutboundUnitId());
        distrReturnInStorageVO.setRequestUnitCode(distrInReturnOut.getOutboundUnitCode());
        distrReturnInStorageVO.setRequestUnitName(distrInReturnOut.getOutboundUnitName());


//        distrReturnInStorageVO.setRequestUnitId(enterprise.getId());
//        distrReturnInStorageVO.setRequestUnitCode(enterprise.getCode());
//        distrReturnInStorageVO.setRequestUnitName(enterprise.getName());

        List<DistrReturnInDetailShelfVO> distrReturnInDetailShelfVOList = distrReturnInDetailMapper.selectByDistrReturnInId(id);
        if (distrReturnInDetailShelfVOList.isEmpty()) {
            throw new BusinessException("无效的单据ID：" + id);
        }
        for (DistrReturnInDetailShelfVO distrReturnInDetailShelfVO : distrReturnInDetailShelfVOList) {

            distrReturnInDetailShelfVO.setCallDataDtlId(distrReturnInDetailShelfVO.getId());

            List<DistrPurchaseInStorageShelfVO> distrPurchaseInStorageShelfVOS = distrReturnInShelfMapper.selectByDistrReturnInDetailId(distrReturnInDetailShelfVO.getId());
            if (!distrPurchaseInStorageShelfVOS.isEmpty()) {
                for (DistrPurchaseInStorageShelfVO distrPurchaseInStorageShelfVO : distrPurchaseInStorageShelfVOS) {
                    GoodsShelfStatusDescVO goodsShelfStatusDescVO = chGoodsLoadService.getGoodsShelfStatusDesc(loginUser.getEnterpriseId(), distrPurchaseInStorageShelfVO.getShelfId());
                    if (goodsShelfStatusDescVO != null) {
                        distrPurchaseInStorageShelfVO.setShelfStatusDesc(goodsShelfStatusDescVO.getShelfStatusDesc());
                    }
                }
                distrReturnInDetailShelfVO.setDistrPurchaseInStorageShelfVOS(distrPurchaseInStorageShelfVOS);
            }
        }
        distrReturnInStorageVO.setDistrReturnInDetailShelfVOList(distrReturnInDetailShelfVOList);
        return distrReturnInStorageVO;
    }

    @Override
    public void saveRecheck(DistrOut distrOut, UserVO userVO) throws Exception {

        DistrOut distr = distrOutMapper.selectByPrimaryKey(distrOut.getId());
        //更新配货出库数据
        //计算总单金额
        calculationCheckDistrOutPrice(distr, distrOut);
        distrOut.setStatus(DistrOutStatus.WAIT_AUDIT);
        UserEnterpriseUtils.setUserCreateOrModify(distrOut, userVO, false);
        distrOutMapper.updateByPrimaryKeySelective(distrOut);

        //更新可能存在配货单的数据
        if (distr.getBaseOrderId() != null) {
            DistrSend distrSend = distrSendMapper.selectByPrimaryKey(distr.getBaseOrderId());
            if (distrSend != null) {
                //数量合计
                distrSend.setQuantityTotal(distrOut.getQuantityTotal());
                //品种合计
                distrSend.setVarietiesQuantity(distrOut.getDistrOutDetailList().size());
                //金额合计（整单优惠前的金额合计）
                distrSend.setAmountTotal(distrOut.getAmountTotal());
                //整单折扣
                distrSend.setWholeDiscount(distrOut.getWholeDiscount());
                //整单优惠
                distrSend.setWholeDiscountAmount(distrOut.getWholeDiscountAmount());
                //实际金额
                distrSend.setRealAmountTotal(distrOut.getRealAmountTotal());
                //税额合计
                distrSend.setTaxAmountTotal(distrOut.getTaxAmountTotal());
                //不含税金额合计
                distrSend.setNotaxRealAmountTotal(distrOut.getNotaxRealAmountTotal());
                distrSend.setStatus(DistrSendStatus.OUTING);
                UserEnterpriseUtils.setUserCreateOrModify(distrSend, userVO, false);
                distrSendMapper.updateByPrimaryKey(distrSend);
            }

        }

        for (DistrOutDetail distrOutDetailNovel : distrOut.getDistrOutDetailList()) {

            DistrOutDetail detail = distrOutDetailMapper.selectByPrimaryKey(distrOutDetailNovel.getId());
            //计算明细金额
            calculationCheckDistrOutDetailPrice(distrOut, detail, distrOutDetailNovel);
            distrOutDetailNovel.setStatus(DistrOutStatus.WAIT_AUDIT);
            UserEnterpriseUtils.setUserCreateOrModify(distrOutDetailNovel, userVO, false);
            distrOutDetailMapper.updateByPrimaryKeySelective(distrOutDetailNovel);

            //更新可能存在配货明细单的数据
            DistrSendDetail distrSendDetail = distrSendDetailMapper.selectByPrimaryKey(distrOutDetailNovel.getBaseOrderDtlId());
            if (distrSendDetail != null) {
                //数量
                distrSendDetail.setQuantity(distrOutDetailNovel.getQuantity());
                //单价
                distrSendDetail.setUnitPrice(distrOutDetailNovel.getUnitPrice());
                //行折扣
                distrSendDetail.setLineDiscount(distrOutDetailNovel.getLineDiscount());
                //金额（整单优惠前金额） = 数量*单价*行折扣
                distrSendDetail.setAmount(distrOutDetailNovel.getAmount());
                //整单折扣（%）
                distrSendDetail.setWholeDiscount(distrOutDetailNovel.getWholeDiscount());
                //优惠分摊 = 整单优惠*(金额/sum(金额))
                distrSendDetail.setLineDiscountAmount(distrOutDetailNovel.getLineDiscountAmount());
                //行实际金额 = 数量*单价*行折扣*整单折扣-优惠分摊
                distrSendDetail.setRealAmount(distrOutDetailNovel.getRealAmount());
                //实际单价：实际金额/数量
                distrSendDetail.setRealPrice(distrOutDetailNovel.getRealPrice());
                //税率ID
                distrSendDetail.setTaxRateId(distrOutDetailNovel.getTaxRateId());
                //税率
                distrSendDetail.setTaxRate(distrOutDetailNovel.getTaxRate());
                //不含税实际金额
                distrSendDetail.setNotaxRealAmount(distrOutDetailNovel.getNotaxRealAmount());
                //不含税实际单价
                distrSendDetail.setNotaxRealPrice(distrOutDetailNovel.getNotaxRealPrice());
                //税额
                distrSendDetail.setTaxAmount(distrOutDetailNovel.getTaxAmount());
                distrSendDetail.setStatus(DistrSendStatus.OUTING);
                UserEnterpriseUtils.setUserCreateOrModify(distrSendDetail, userVO, false);
                distrSendDetailMapper.updateByPrimaryKeySelective(distrSendDetail);
            }


            for (DistrOutShelf distrOutShelfNovel : distrOutDetailNovel.getDistrOutShelfList()) {
                // 重算货位明细单单价、金额信息
                CalculateResultModel resultModel = CalculateComponent.getCalculateResult(distrOutShelfNovel.getQuantity(), distrOutDetailNovel.getUnitPrice(), distrOutDetailNovel.getLineDiscount(),
                        distrOutDetailNovel.getWholeDiscount(), distrOut.getWholeDiscountAmount(), distrOutDetailNovel.getTaxRate(), distrOut.getAmountTotal());
                distrOutShelfNovel.setAmount(resultModel.getAmount());
                distrOutShelfNovel.setLineDiscountAmount(resultModel.getLineRoundOff());
                distrOutShelfNovel.setRealPrice(resultModel.getRealPrice());
                distrOutShelfNovel.setRealAmount(resultModel.getRealAmount());
                distrOutShelfNovel.setNotaxRealPrice(resultModel.getNotaxPrice());
                distrOutShelfNovel.setNotaxRealAmount(resultModel.getNotaxAmount());
                distrOutShelfNovel.setTaxAmount(resultModel.getTaxAmount());
                distrOutShelfNovel.setStatus(DistrOutStatus.WAIT_AUDIT);
                UserEnterpriseUtils.setUserCreateOrModify(distrOutShelfNovel, userVO, false);
                distrOutShelfMapper.updateByPrimaryKeySelective(distrOutShelfNovel);
            }
        }
    }

    @Override
    public List<GetPurchaseInStorageShelfListVO> getPurchaseInStorageShelfList(UserVO loginUser, int pageNo, int pageSize, Long id, String orderName, String orderType, Page page) {

        if (orderName != null && orderName.equals("inStorageCode")) {
            orderName = "pis.code";
        } else if (orderName != null && orderName.equals("lineNum")) {
            orderName = "piss.line_num";
        } else {
            orderName = "piss.line_num";
            orderType = "asc";
        }
        Long totalRecord = purchaseInStorageShelfMapper.queryCount(loginUser.getEnterpriseId(), id, orderName, orderType);
        List<GetPurchaseInStorageShelfListVO> getPurchaseInStorageShelfListVOList = purchaseInStorageShelfMapper.getPurchaseInStorageShelfList(loginUser.getEnterpriseId(), id, page.getStart(), pageSize, orderName, orderType);
        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return getPurchaseInStorageShelfListVOList;
    }

    @Override
    public List<GetDistrReturnInShelfListVO> getDistrReturnInShelfList(int pageNo, int pageSize, Long id, String orderName, String orderType, Page page) {

        if (orderName != null && orderName.equals("returnInCode")) {
            orderName = "pis.code";
        } else if (orderName != null && orderName.equals("lineNum")) {
            orderName = "piss.line_num";
        } else {
            orderName = "piss.line_num";
            orderType = "asc";
        }
        Long totalRecord = distrReturnInShelfMapper.queryCount(id, orderName, orderType);
        List<GetDistrReturnInShelfListVO> getDistrReturnInShelfListVOList = distrReturnInShelfMapper.getDistrReturnInShelfList(id, page.getStart(), pageSize, orderName, orderType);
        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return getDistrReturnInShelfListVOList;
    }

    @Override
    public DistrOut calculationByCall(DistrOut distrOut, UserVO userVO) throws Exception {
        DistrOut copy = (DistrOut) EntityUtils.reflectAddSetDefaultValue(new DistrOut().getClass(), userVO);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(copy, distrOut);
        distrOut.setUpdateTime(new Date());
        ManageConfig manage = manageConfigMapper.selectByCurrentUser(userVO);//判断质量流程是否开启
        Boolean zl = manage.getBusinessControl() == 0 ? true : false;
        if (zl) {
            //质量流程开的时候前台必须提供采购人员id
            User user = userMapper.selectByPrimaryKey(distrOut.getOutManId());
            if (user == null) {
                throw new BusinessException("没有该员工，请核实员工id");
            }
            if (user.getEnterpriseId().intValue() != userVO.getEnterpriseId().intValue()) {
                throw new BusinessException("没有该员工，请核实员工id");
            }
            distrOut.setOutDate(DateUtils.StringToDate(distrOut.getOutDateStr()));
            distrOut.setOutManId(user.getId());
            distrOut.setOutManCode(user.getCode());
            distrOut.setOutManName(user.getName());
        } else {
            distrOut.setOutDate(new Date());
            distrOut.setOutManId(userVO.getUserId());
            distrOut.setOutManCode(userVO.getUserCode());
            distrOut.setOutManName(userVO.getUserName());
        }
        commonComponent.calculationPrice(distrOut);
        distrOut.setStatus(DistrOutStatus.WAIT_AUDIT);
        distrOut.setCode(orderCodeComponent.generate(OrderRule.DISTR_OUT.getCodePrefix(), userVO.getEnterpriseId(), userVO.getEnterpriseCode()));
        Long outId = distrOut.getId();
        for (DistrOutDetail distrOutDetail : distrOut.getDistrOutDetailList()) {
            BigDecimal quantity = new BigDecimal(0);
            for (DistrOutShelf distrOutShelf : distrOutDetail.getDistrOutShelfList()) {
                quantity = quantity.add(distrOutShelf.getQuantity());
            }
            distrOutDetail.setQuantity(quantity);
        }
        for (DistrOutDetail distrOutDetail : distrOut.getDistrOutDetailList()) {
            distrOutDetail.setOutId(outId);
            distrOutDetail.setOutCode(distrOut.getCode());
            distrOutDetail.setOutDate(distrOut.getOutDate());
            distrOutDetail.setUpdateTime(new Date());
            distrOutDetail.setModifierCode(userVO.getUserCode());
            distrOutDetail.setModifierId(userVO.getUserId());
            distrOutDetail.setModifierName(userVO.getUserName());
            long outDtlId = distrOutDetail.getId();
            BigDecimal quantity = new BigDecimal(0);
            for (DistrOutShelf distrOutShelf : distrOutDetail.getDistrOutShelfList()) {
                distrOutShelf.setOutId(outId);
                distrOutShelf.setDtlId(outDtlId);
                quantity = quantity.add(distrOutShelf.getQuantity());
            }
            if (quantity.compareTo(distrOutDetail.getQuantity()) != 0) {
                throw new BusinessException("出库数量与配送单不一致,请检查出库数量!");
            }
            if (distrOutDetail.getQuantity().compareTo(distrOutDetail.getQuantity()) != 0) {
                throw new BusinessException("出库数量与明细数量不一致,请检查出库数量!");
            }
        }
        return distrOut;
    }
}
