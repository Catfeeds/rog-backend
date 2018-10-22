package com.rograndec.feijiayun.chain.business.distr.branch.service.impl;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierVarietiesMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierVarieties;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.*;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.*;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInNoticeService;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.*;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrOutMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnInMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnNoticeMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrSendDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrOut;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNotice;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrSendDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnInStorageVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsBusinessMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsBusiness;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.init.model.ApprovalFlowContentModel;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrInNoticeReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.OrderReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrInNotice;
import com.rograndec.feijiayun.chain.business.system.approval.vo.SubmitApprovalFlowVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.service.ManageConfigService;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.ApprovalFlowComponent;
import com.rograndec.feijiayun.chain.common.component.CalculateComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInStatus;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReqPlanStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PubStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.vo.CommonParamSupplierAndGoods;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.param.ParamUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by dudy on 2017/10/7.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class DistrInNoticeServiceImpl implements DistrInNoticeService {

    @Autowired
    private DistrInNoticeMapper noticeMapper;

    @Autowired
    private SafetyStockMapper safetyStockMapper;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    OrderCodeComponent orderCodeComponent;
    @Autowired
    DistrInNoticeMapper distrInNoticeMapper;
    @Autowired
    SupplierMapper supplierMapper;
    @Autowired
    ManageConfigService manageConfigService;
    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    ApprovalFlowComponent approvalFlowComponent;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    DistrInNoticeDetailMapper distrInNoticeDetailMapper;
    @Autowired
    GoodsBusinessMapper goodsBusinessMapper;
    @Autowired
    DistrReqPlanMapper distrReqPlanMapper;
    @Autowired
    DistrReqPlanDetailMapper distrReqPlanDetailMapper;
    @Autowired
    DistrOutMapper distrOutMapper;
    @Autowired
    DistrSendDetailMapper distrSendDetailMapper;
    @Autowired
    private DistrReturnInMapper distrReturnInMapper;
    @Autowired
    private DistrReturnNoticeMapper distrReturnNoticeMapper;
    @Autowired
    private DistrInReturnOutMapper distrInReturnOutMapper;

    @Autowired
    private SupplierVarietiesMapper supplierVarietiesMapper;

    @Override
    public DistrInNoticeVO getDistrInOrderDtlList(Long id,UserVO loginUser) {
        DistrInNoticeVO distrInNoticeVO = noticeMapper.getDistrInOrderDtlList(id);

        distrInNoticeVO.setDistrTypeName(DistributionType.getName(distrInNoticeVO.getDistrType()));

        if(distrInNoticeVO.getDistrType() == DistributionType.SWAP_BETWEEN_STORES.getCode()){
            distrInNoticeVO.setInboundUnitId(loginUser.getEnterpriseId());
            distrInNoticeVO.setInboundUnitCode(loginUser.getEnterpriseCode());
            distrInNoticeVO.setInboundUnitName(loginUser.getEnterpriseName());

            DistrOut distrOut = distrOutMapper.selectByPrimaryKey(distrInNoticeVO.getBaseOrderId());
            if(distrOut != null){
                List<DistrSendDetail> distrSendDetail = distrSendDetailMapper.listDistrSendDetailList(distrOut.getBaseOrderId());
                if(!distrSendDetail.isEmpty()){
                    DistrReturnInStorageVO distrReturnInStorageVO = distrReturnInMapper.selectById(distrSendDetail.get(0).getBaseOrderId());

                    //查询调入单位信息
                    DistrReturnNotice distrReturnNotice = distrReturnNoticeMapper.selectByPrimaryKey(distrReturnInStorageVO.getDistrOrderId());
                    if(distrReturnNotice == null){
                        throw new BusinessException("无效的配后退回通知单单据ID："+distrReturnInStorageVO.getDistrOrderId());
                    }
                    DistrInReturnOut distrInReturnOut = distrInReturnOutMapper.selectByPrimaryKey(distrReturnNotice.getBaseOrderId());
                    if(distrInReturnOut == null){
                        throw new BusinessException("无效的配进退出出库单单据ID："+distrReturnNotice.getBaseOrderId());
                    }
                    distrInNoticeVO.setOutboundUnitId(distrInReturnOut.getEnterpriseId());
                    Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(distrInReturnOut.getEnterpriseId());
                    if(enterprise == null){
                        throw new BusinessException("无效的企业ID："+distrInReturnOut.getEnterpriseId());
                    }
                    distrInNoticeVO.setOutboundUnitCode(enterprise.getCode());
                    distrInNoticeVO.setOutboundUnitName(enterprise.getName());
                }

            }
        }

        if(distrInNoticeVO.getDistrType() == DistributionType.DIRECT_DISTRIBUTION.getCode()){
            distrInNoticeVO.setInboundUnitId(loginUser.getEnterpriseId());
            distrInNoticeVO.setInboundUnitCode(loginUser.getEnterpriseCode());
            distrInNoticeVO.setInboundUnitName(loginUser.getEnterpriseName());
        }

        distrInNoticeVO.setDiscountAmount(distrInNoticeVO.getAmountTotal().multiply(
                new BigDecimal(100).subtract(distrInNoticeVO.getWholeDiscount()).divide(new BigDecimal(100))));


        distrInNoticeVO.setEnterpriseName(loginUser.getEnterpriseName());
        
        return distrInNoticeVO;
    }

    @Override
    public Page getDistrInNoticePageList(DistrInNoticeRequestVO requestVO, UserVO userVO) throws Exception{


        requestVO.setEnterpriseId(userVO.getEnterpriseId());

//        if (!"asc".equals(requestVO.getSort()) && !"desc".equals(requestVO.getSort())) {
//            requestVO.setSort("desc");
//        }
//        if (StringUtils.isEmpty(requestVO.getOrder()) || "date".equals(requestVO.getOrder())) {
//            // 默认日期倒序
//            requestVO.setOrder("order_date");
//        }
//        Date start = DateUtils.StringToDate(requestVO.getStartDate());
//        Date end = DateUtils.addDay(DateUtils.StringToDate(requestVO.getEndDate()), 1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(requestVO.getStartDate() != null || requestVO.getEndDate() != null) {
            String startDate = requestVO.getStartDate() + " 00:00:00";
            String endDate = requestVO.getEndDate() + " 23:59:59";

            Date start = format.parse(startDate);
            Date end = format.parse(endDate);

            requestVO.setStart(start);
            requestVO.setEnd(end);
        }

//        if (requestVO.getStatus() != null) {
//            if (DistrInStatus.FINISHED == requestVO.getStatus()) {
//                requestVO.setFinishStatus(DistrInStatus.WAIT_BILL);
//            }
//        }

        Page<List<DistrInNoticePageVO>> page = new Page<>(requestVO.getPageNo(), requestVO.getPageSize());


        //com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        requestVO.setPageStart(page.getStart());
        List<DistrInNoticePageVO> dataList = noticeMapper.getDistrInNoticePageList(requestVO);
        Integer count = noticeMapper.getDistrInNoticePageCount(requestVO);
        dataList.forEach(item -> {
            item.setDistrTypeName(DistributionType.getName(item.getDistrType()));
            item.setStatusName(DistrInStatus.getStatusDesc(item.getStatus()));
        });

        page.setResult(dataList);
        page.setTotalRecord(count);

        return page;
    }

    @Override
    public Page getDistrNoticeDetailList(String ids,Integer pageNo,Integer pageSize,UserVO userVO) {

        if(StringUtils.isBlank(ids)){
            throw new BusinessException("配进订单ID,参数传递为空");
        }
        String[] noticeIds = ids.split(",");
        Page<List<DistrInNoticeDetailVO>> page = new Page<>(pageNo, pageSize);
        List<DistrInNoticeDetailVO> distrNoticeDetailPageList = noticeMapper.getDistrNoticeDetailPageList(noticeIds, page.getStart(), pageSize);
        Integer count = noticeMapper.getDistrNoticeDetailPageListCount(noticeIds);
        page.setResult(distrNoticeDetailPageList);
        page.setTotalRecord(count);
        return page;
    }

    @Override
    public void exportDetail(OutputStream output, Long orderId,UserVO loginUser) {

        DistrInNoticeVO distrInNoticeVO = this.getDistrInOrderDtlList(orderId,loginUser);

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("goodsCode", "商品编码");
        // map.put("goodsName", "商品名称");
        map.put("goodsGenericName", "商品通用名称");
        // map.put("dosageId", "剂型ID");
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

        map.put("retailPrice", "零售单价");
        map.put("memberPrice", "会员单价");
        List<String> titleSecond = new ArrayList<>();
        // 标题栏下第一行
        StringBuilder titleSecondRow = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 格式化下日期
        titleSecondRow.append("  配送单位编码: ");
        titleSecondRow.append(distrInNoticeVO.getCode());
        titleSecondRow.append("  配送单位名称:");
        titleSecondRow.append(distrInNoticeVO.getDistrUnitName());
        titleSecondRow.append("  订单日期:");
        titleSecondRow.append(format.format(distrInNoticeVO.getOrderDate()));
        titleSecondRow.append("  配进人员:");
        titleSecondRow.append(distrInNoticeVO.getStorageManName());
        titleSecondRow.append("  配货类型:");
        titleSecondRow.append(DistributionType.getName(distrInNoticeVO.getDistrType()));
        titleSecondRow.append("  备注:");
        titleSecondRow.append(distrInNoticeVO.getRemark() == null?"":distrInNoticeVO.getRemark());

        titleSecond.add(titleSecondRow.toString());

        StringBuilder end = new StringBuilder();
        end.append("商品  ");
        end.append("总金额为：");
        end.append(distrInNoticeVO.getAmountTotal());
        //每行以分号隔开
        end.append(";");
        end.append("折扣：");
        end.append(distrInNoticeVO.getWholeDiscount());
        end.append("%");
        end.append("   " + distrInNoticeVO.getDiscountAmount());
        end.append(";");
        end.append("优惠：");
        end.append(distrInNoticeVO.getWholeDiscountAmount());
        end.append(";");
        end.append("总额：");
        end.append(distrInNoticeVO.getRealAmountTotal());
        end.append(";");
        end.append("不含税总额：");
        end.append(distrInNoticeVO.getNotaxRealAmountTotal());
        end.append(";");
        end.append("税额：");
        end.append(distrInNoticeVO.getTaxAmountTotal());
        end.append(";");

        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("配进订单");


        StringBuilder endTotal = new StringBuilder();
        endTotal.append(distrInNoticeVO.getQuantityTotal());
        endTotal.append(";");
        endTotal.append(distrInNoticeVO.getAmountTotal());
        endTotal.append(";");
        endTotal.append(distrInNoticeVO.getNotaxRealAmountTotal());
        endTotal.append(";");
        endTotal.append(distrInNoticeVO.getTaxAmountTotal());
        List<String> locationList = new ArrayList<String>();
        locationList.add("quantity");
        locationList.add("amount");
        locationList.add("notaxRealAmount");
        locationList.add("taxAmount");
        purchaseGeneralComponent.commExcelDistrExport(output, map, distrInNoticeVO.getDistrInNoticeDetailVOS(), name, titleSecond, end.toString(), endTotal.toString(), locationList);


    }

    @Override
    public void getDistrInNoticeList(Page<OrderReportVo<DistrInNoticeReportVo>> page, RequestDistrInNotice requestDistrInNotice) {
        if (requestDistrInNotice.getPageNo() != null && requestDistrInNotice.getPageSize() != null) {
            requestDistrInNotice.setPageNo(page.getStart());
        }
        int count = noticeMapper.getDistrInNoticeListCount(requestDistrInNotice);
        Integer sortDate = requestDistrInNotice.getSortDate();
        Integer sortCode = requestDistrInNotice.getSortCode();
        String sort = "";
        if (sortDate == null && sortCode == null) {
            sort = "";
        }
        if (sortDate != null && sortDate == 0) {
            sort += "a.order_date,";
        }
        if (sortDate != null && sortDate == 1) {
            sort += "a.order_date desc,";
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
        requestDistrInNotice.setSort(sort);
        List<DistrInNoticeReportVo> distrInNoticeReportVos = noticeMapper.getDistrInNoticeList(requestDistrInNotice);
        OrderReportVo<DistrInNoticeReportVo> orderReportVo = new OrderReportVo();
        orderReportVo.setDataList(distrInNoticeReportVos);
        requestDistrInNotice.setPageNo(null);
        requestDistrInNotice.setPageSize(null);
        List<DistrInNoticeReportVo> distrInNoticeReportVos2 = noticeMapper.getDistrInNoticeList(requestDistrInNotice);
        BigDecimal quantity = BigDecimal.ZERO;
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal realAmount = BigDecimal.ZERO;
        BigDecimal notaxRealAmount = BigDecimal.ZERO;
        BigDecimal taxAmount = BigDecimal.ZERO;
        for (DistrInNoticeReportVo d : distrInNoticeReportVos2) {
            quantity = quantity.add(d.getQuantity());
            amount = amount.add(d.getAmount());
            realAmount = realAmount.add(d.getRealAmount());
            notaxRealAmount = notaxRealAmount.add(d.getNotaxRealAmount());
            taxAmount = taxAmount.add(d.getTaxAmount());
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
    public void excelExportReport(OutputStream output, List<DistrInNoticeReportVo> distrInNoticeReportVos, UserVO userVO) {
        List<String> names = new ArrayList<>();
        names.add(userVO.getEnterpriseName());
        names.add("配进订单");
        //内容数据
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("enterpriseCode", "组织机构编码");
        map.put("enterpriseName", "组织机构名称");
        map.put("orderDateStr", "日期");
        map.put("code", "单号");
        map.put("distrUnitCode", "配货单位编码");
        map.put("distrUnitName", "配货单位名称");
        map.put("distrTypeStr", "配货类型");
        map.put("distrSendCode", "配货单号");
        map.put("baseOrderCode", "配货出库单号");
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
        for (DistrInNoticeReportVo d : distrInNoticeReportVos) {
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
        purchaseGeneralComponent.commExcelExport(output, map, distrInNoticeReportVos, names, null, endTotal.toString(), false, locationList);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public void saveDistrInNotice(UserVO loginUser, SaveDistrInNoticeVO saveDistrInNoticeVO) throws Exception {

        DistrInNotice distrInNotice = new DistrInNotice();
        if(saveDistrInNoticeVO.getDistrType() != DistributionType.DIRECT_DISTRIBUTION.getCode()){
            throw new BusinessException("只能允许新增直调配送类型的单据");
        }
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveDistrInNoticeVO, distrInNotice);
        UserEnterpriseUtils.setUserCreateOrModify(distrInNotice, loginUser, true);
        //set配进人信息--start
        if(distrInNotice.getStorageManId() == null){
            throw new BusinessException("请选择配进人");
        }
        User user = userMapper.selectByPrimaryKey(distrInNotice.getStorageManId());
        if (user != null) {
            distrInNotice.setStorageManCode(user.getCode());
            distrInNotice.setStorageManName(user.getName());
        }
        //set配货单位
        distrInNotice.setDistrUnitId(loginUser.getParentId());
        distrInNotice.setDistrUnitCode(loginUser.getParentCode());
        distrInNotice.setDistrUnitName(loginUser.getParentName());
        //set供货单位
        if(distrInNotice.getOutboundUnitId() == null){
            throw new BusinessException("请选择供货单位");
        }
        Supplier supplier = supplierMapper.selectByPrimaryKey(distrInNotice.getOutboundUnitId());
        if (supplier != null) {
            distrInNotice.setOutboundUnitCode(supplier.getCode());
            distrInNotice.setOutboundUnitName(supplier.getName());
        }
        distrInNotice.setEnterpriseId(loginUser.getEnterpriseId());
        distrInNotice.setParentId(loginUser.getParentId());
        //单号,单据类型
        distrInNotice.setOrderType(OrderRule.DISTR_IN_ORDER.getType());
        distrInNotice.setCode(getCode(OrderRule.DISTR_IN_ORDER.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));

        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal quantityTotal = BigDecimal.ZERO;
        for (int i = 0; i < saveDistrInNoticeVO.getSaveDistrInNoticeDetailVOList().size(); i++) {
            BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(saveDistrInNoticeVO.getSaveDistrInNoticeDetailVOList().get(i).getQuantity(), saveDistrInNoticeVO.getSaveDistrInNoticeDetailVOList().get(i).getUnitPrice(), saveDistrInNoticeVO.getSaveDistrInNoticeDetailVOList().get(i).getLineDiscount());
            amountTotal = amountTotal.add(amount);
            quantityTotal = quantityTotal.add(saveDistrInNoticeVO.getSaveDistrInNoticeDetailVOList().get(i).getQuantity());
        }

        distrInNotice.setVarietiesQuantity(saveDistrInNoticeVO.getSaveDistrInNoticeDetailVOList().size());//品种数量
        distrInNotice.setQuantityTotal(quantityTotal);//数量合计
        distrInNotice.setAmountTotal(amountTotal);//金额合计（整单优惠前的金额合计）
        distrInNotice.setRealAmountTotal(BigDecimal.ZERO);//实际金额合计
        distrInNotice.setNotaxRealAmountTotal(BigDecimal.ZERO);//不含税金额合计
        distrInNotice.setTaxAmountTotal(BigDecimal.ZERO);//税额合计

        if(distrInNotice.getBaseOrderId() != null){//调用要货计划时  插入上级单据信息
            DistrReqPlan distrReqPlan = distrReqPlanMapper.selectByPrimaryKey(distrInNotice.getBaseOrderId());
            if(distrReqPlan != null){
                distrInNotice.setBaseOrderCode(distrReqPlan.getCode());
                distrInNotice.setBaseOrderDate(distrReqPlan.getPlanDate());
                distrInNotice.setBaseOrderType(distrReqPlan.getOrderType());

                distrReqPlan.setStatus(DistrReqPlanStatus.FINISHED);
                distrReqPlanMapper.updateByPrimaryKeySelective(distrReqPlan);
                List<DistrReqPlanDetail> distrReqPlanDetails = distrReqPlanDetailMapper.selectByDistrReqPlanId(distrInNotice.getBaseOrderId());
                if(distrReqPlanDetails != null){
                    for(DistrReqPlanDetail distrReqPlanDetail : distrReqPlanDetails){
                        distrReqPlanDetail.setStatus(DistrReqPlanStatus.FINISHED);
                        distrReqPlanDetailMapper.updateByPrimaryKeySelective(distrReqPlanDetail);
                    }
                }
            }
        }

        Integer status = PubStatus.distrInStatus.WAIT_RECEIVE;
        ManageConfig manageConfig = manageConfigService.getManageConfig(loginUser);
        if (manageConfig.getApprovalControl() == 1) {// 如果审批流开启
            status = PurchaseStatus.PENDING_AUDIT.getStatus();// 待审核状态
        }
        distrInNotice.setStatus(status);
        distrInNotice.setId(null);
        distrInNoticeMapper.insertSelective(distrInNotice);

        saveDistrInNoticeDetail(distrInNotice, saveDistrInNoticeVO, loginUser);

        // 提交流程审批
        if (manageConfig.getApprovalControl() == 1) {// 如果审批流开启
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(loginUser.getEnterpriseId());
            SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(loginUser.getEnterpriseId(), enterprise.getName(),
                    loginUser.getUserId(), loginUser.getUserName(), loginUser.getChainType(), loginUser.getParentId(),
                    loginUser.getChainType().equals(ChainType.Headquarters.getCode()) ? loginUser.getEnterpriseId() : loginUser.getParentId(),
                    ApprovalFlowContentModel.getDistrInNoticeCode(), distrInNotice.getId(), distrInNotice.getCode(), enterprise.getName()+"的配进订单");
            approvalFlowComponent.apply(submitApprovalFlowVO, loginUser);

        }
    }



    private void saveDistrInNoticeDetail(DistrInNotice distrInNotice, SaveDistrInNoticeVO saveDistrInNoticesVO, UserVO loginUser) throws Exception {

        BigDecimal realAmountTotal = BigDecimal.ZERO;
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
        BigDecimal taxAmountTotal = BigDecimal.ZERO;
        for (SaveDistrInNoticeDetailVO saveDistrInNoticeDetailVO : saveDistrInNoticesVO.getSaveDistrInNoticeDetailVOList()) {
            DistrInNoticeDetail distrInNoticeDetail = saveDeatil(loginUser,distrInNotice,saveDistrInNoticeDetailVO);
            realAmountTotal = realAmountTotal.add(distrInNoticeDetail.getRealAmount());//总单实际金额合计
            notaxRealAmountTotal = notaxRealAmountTotal.add(distrInNoticeDetail.getNotaxRealAmount());//总单不含税金额合计
            taxAmountTotal = taxAmountTotal.add(distrInNoticeDetail.getTaxAmount());//总单税额合计
            distrInNoticeDetail.setId(null);
            distrInNoticeDetailMapper.insertSelective(distrInNoticeDetail);
        }

        distrInNotice.setRealAmountTotal(realAmountTotal);
        distrInNotice.setNotaxRealAmountTotal(notaxRealAmountTotal);
        distrInNotice.setTaxAmountTotal(taxAmountTotal);
        distrInNoticeMapper.updateByPrimaryKeySelective(distrInNotice);
    }

    @Override
    public List<GoodsInNoticeVO> getGoodsByParam(UserVO userVO,Long supplierId,String param) {
        CommonParamSupplierAndGoods commonParam = new CommonParamSupplierAndGoods();
        ParamUtils.packParam(userVO,commonParam);
        List<GoodsInNoticeVO> goodsInNoticeVOS = distrInNoticeMapper.getGoodsByParam(commonParam,supplierId,param);
        for(GoodsInNoticeVO goodsInNoticeVO : goodsInNoticeVOS){
            List<SupplierVarieties> supplierVarieties = supplierVarietiesMapper.selectSupplierVarietiesByParam(userVO.getEnterpriseId(), goodsInNoticeVO.getId(), supplierId);
            if(CollectionUtils.isEmpty(supplierVarieties)){
                if(goodsInNoticeVO.getTaxRateId() == null){
                    goodsInNoticeVO.setTaxRateId(goodsInNoticeVO.getPurchaseTaxRateId());
                }
                if(goodsInNoticeVO.getTaxRate() == null){
                    goodsInNoticeVO.setTaxRate(goodsInNoticeVO.getPurchaseTaxRate());
                }
                goodsInNoticeVO.setUnitPrice(BigDecimal.ZERO);
            } else {
                goodsInNoticeVO.setTaxRateId(supplierVarieties.get(0).getLastPurTaxRateId());
                goodsInNoticeVO.setTaxRate(supplierVarieties.get(0).getLastPurTaxRate());
                goodsInNoticeVO.setUnitPrice(supplierVarieties.get(0).getLastPurPrice());
            }
            // 查询商品的默认货位

            //将安全库存的默认货位带过去，配进入库新增
            SafetyStock safetyStock = safetyStockMapper.selectByGoodsIdAndEnterpriseId(goodsInNoticeVO.getId(), userVO.getEnterpriseId());
            if (safetyStock != null){
                if (safetyStock.getDefaultShelfId() != 0){
                    goodsInNoticeVO.setDefaultShelfId(safetyStock.getDefaultShelfId());
                }
                if (!"0".equals(safetyStock.getDefaultShelfName())){
                    goodsInNoticeVO.setDefaultShelfName(safetyStock.getDefaultShelfName());
                }
            }

        }
        return goodsInNoticeVOS;
    }

    @Override
    public void cancelDistrInNotice(Long id,UserVO loginUser) throws Exception{

        DistrInNotice distrInNotice = distrInNoticeMapper.selectByPrimaryKey(id);
        if(distrInNotice.getStatus() != PubStatus.distrInStatus.AUDIT_REJECT){
            throw new BusinessException("只有审核驳回的单据才能取消!");
        }
        distrInNotice.setStatus(PubStatus.distrInStatus.CANCELED);
        distrInNoticeMapper.updateByPrimaryKeySelective(distrInNotice);

        List<DistrInNoticeDetail> distrInNoticeDetails = distrInNoticeDetailMapper.selectByNoticeIdByEnterpriseId(id,loginUser.getEnterpriseId());
        for(DistrInNoticeDetail distrInNoticeDetail : distrInNoticeDetails){
            distrInNoticeDetail.setStatus(PubStatus.distrInStatus.CANCELED);
            distrInNoticeDetailMapper.updateByPrimaryKeySelective(distrInNoticeDetail);
        }

        if(distrInNotice.getBaseOrderId() != null){
            DistrReqPlan distrReqPlan = distrReqPlanMapper.selectByPrimaryKey(distrInNotice.getBaseOrderId());
            distrReqPlan.setStatus(PubStatus.distrInStatus.CANCELED);
            distrReqPlanMapper.updateByPrimaryKeySelective(distrReqPlan);

            List<DistrReqPlanDetail> distrReqPlanDetails = distrReqPlanDetailMapper.selectByDistrReqPlanId(distrInNotice.getBaseOrderId());
            for(DistrReqPlanDetail distrReqPlanDetail : distrReqPlanDetails){
                distrReqPlanDetail.setStatus(PubStatus.distrInStatus.CANCELED);
                distrReqPlanDetailMapper.updateByPrimaryKeySelective(distrReqPlanDetail);
            }
        }
    }

    @Override
    public void updateDistrInNotice(UserVO loginUser, SaveDistrInNoticeVO saveDistrInNoticeVO) throws Exception{

        if(saveDistrInNoticeVO.getDistrType() != DistributionType.DIRECT_DISTRIBUTION.getCode()){
            throw new BusinessException("只能允许直调配送类型的单据");
        }
        DistrInNotice distrInNotice = distrInNoticeMapper.selectByPrimaryKey(saveDistrInNoticeVO.getId());
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveDistrInNoticeVO, distrInNotice);
        UserEnterpriseUtils.setUserCreateOrModify(distrInNotice, loginUser, false);
        Integer status = PubStatus.distrInStatus.WAIT_RECEIVE;
        ManageConfig manageConfig = manageConfigService.getManageConfig(loginUser);
        if (manageConfig.getApprovalControl() == 1) {// 如果审批流开启
            status = PurchaseStatus.PENDING_AUDIT.getStatus();// 待审核状态
        }
        distrInNotice.setStatus(status);
        //set配进人信息--start
        if(distrInNotice.getStorageManId() == null){
            throw new BusinessException("请选择配进人");
        }
        User user = userMapper.selectByPrimaryKey(distrInNotice.getStorageManId());
        if (user != null) {
            distrInNotice.setStorageManCode(user.getCode());
            distrInNotice.setStorageManName(user.getName());
        }
        //set供货单位
        if(distrInNotice.getOutboundUnitId() == null){
            throw new BusinessException("请选择供货单位");
        }
        Supplier supplier = supplierMapper.selectByPrimaryKey(distrInNotice.getOutboundUnitId());
        if (supplier != null) {
            distrInNotice.setOutboundUnitCode(supplier.getCode());
            distrInNotice.setOutboundUnitName(supplier.getName());
        }

        BigDecimal amountTotal = BigDecimal.ZERO;
        BigDecimal quantityTotal = BigDecimal.ZERO;
        for (int i = 0; i < saveDistrInNoticeVO.getSaveDistrInNoticeDetailVOList().size(); i++) {
            BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(saveDistrInNoticeVO.getSaveDistrInNoticeDetailVOList().get(i).getQuantity(), saveDistrInNoticeVO.getSaveDistrInNoticeDetailVOList().get(i).getUnitPrice(), saveDistrInNoticeVO.getSaveDistrInNoticeDetailVOList().get(i).getLineDiscount());
            amountTotal = amountTotal.add(amount);
            quantityTotal = quantityTotal.add(saveDistrInNoticeVO.getSaveDistrInNoticeDetailVOList().get(i).getQuantity());
        }
        distrInNotice.setDistrType(DistributionType.DIRECT_DISTRIBUTION.getCode());
        distrInNotice.setVarietiesQuantity(saveDistrInNoticeVO.getSaveDistrInNoticeDetailVOList().size());//品种数量
        distrInNotice.setQuantityTotal(quantityTotal);//数量合计
        distrInNotice.setAmountTotal(amountTotal);//金额合计（整单优惠前的金额合计）
        distrInNotice.setRealAmountTotal(BigDecimal.ZERO);//实际金额合计
        distrInNotice.setNotaxRealAmountTotal(BigDecimal.ZERO);//不含税金额合计
        distrInNotice.setTaxAmountTotal(BigDecimal.ZERO);//税额合计

        distrInNoticeMapper.updateByPrimaryKeySelective(distrInNotice);

        BigDecimal realAmountTotal = BigDecimal.ZERO;
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;
        BigDecimal taxAmountTotal = BigDecimal.ZERO;
        List<SaveDistrInNoticeDetailVO> saveDistrInNoticeDetailVOS = saveDistrInNoticeVO.getSaveDistrInNoticeDetailVOList();
        for(SaveDistrInNoticeDetailVO saveDistrInNoticeDetailVO : saveDistrInNoticeDetailVOS){

            if(saveDistrInNoticeDetailVO.getId() != null){//老数据
                DistrInNoticeDetail distrInNoticeDetail = saveDeatil(loginUser,distrInNotice,saveDistrInNoticeDetailVO);
                distrInNoticeDetailMapper.updateByPrimaryKeySelective(distrInNoticeDetail);
                realAmountTotal = realAmountTotal.add(distrInNoticeDetail.getRealAmount());//总单实际金额合计
                notaxRealAmountTotal = notaxRealAmountTotal.add(distrInNoticeDetail.getNotaxRealAmount());//总单不含税金额合计
                taxAmountTotal = taxAmountTotal.add(distrInNoticeDetail.getTaxAmount());//总单税额合计
            }else{//新增数据
                DistrInNoticeDetail distrInNoticeDetail = saveDeatil(loginUser,distrInNotice,saveDistrInNoticeDetailVO);
                distrInNoticeDetail.setId(null);
                distrInNoticeDetailMapper.insertSelective(distrInNoticeDetail);
                realAmountTotal = realAmountTotal.add(distrInNoticeDetail.getRealAmount());//总单实际金额合计
                notaxRealAmountTotal = notaxRealAmountTotal.add(distrInNoticeDetail.getNotaxRealAmount());//总单不含税金额合计
                taxAmountTotal = taxAmountTotal.add(distrInNoticeDetail.getTaxAmount());//总单税额合计
            }
        }
        distrInNotice.setRealAmountTotal(realAmountTotal);
        distrInNotice.setNotaxRealAmountTotal(notaxRealAmountTotal);
        distrInNotice.setTaxAmountTotal(taxAmountTotal);
        distrInNoticeMapper.updateByPrimaryKeySelective(distrInNotice);

        // 提交流程审批
        if (manageConfig.getApprovalControl() == 1) {// 如果审批流开启
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(loginUser.getEnterpriseId());
            SubmitApprovalFlowVO submitApprovalFlowVO = new SubmitApprovalFlowVO(loginUser.getEnterpriseId(), enterprise.getName(),
                    loginUser.getUserId(), loginUser.getUserName(), loginUser.getChainType(), loginUser.getParentId(),
                    loginUser.getChainType().equals(ChainType.Headquarters.getCode()) ? loginUser.getEnterpriseId() : loginUser.getParentId(),
                    ApprovalFlowContentModel.getDistrInNoticeCode(), distrInNotice.getId(), "", "");
            approvalFlowComponent.apply(submitApprovalFlowVO, loginUser);

        }
    }

    @Override
    public List<DistrReqPlanPageVO> getDistrReqPlanPage(int pageNo, int pageSize, Long enterpriseId, Page page, Date startTime, Date endTime, String orderName, String orderType) {

        Long totalRecord;
        List<DistrReqPlanPageVO> list = new ArrayList<>();
        if (orderName != null && orderName.equals("planDate"))
            orderName = "plan_date";
        else {
            orderName = "plan_date";
            orderType = "desc";
        }
        String startTimes = null;
        String endTimes = null;
        if (startTime != null) {
            startTimes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
        }
        if (endTime != null) {
            endTimes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime);
        }

        totalRecord = distrReqPlanMapper.queryCountByDistrReqPlanParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes, DistrReqPlanStatus.WAIT_DISTR, DistributionType.DIRECT_DISTRIBUTION.getCode());
        list = distrReqPlanMapper.selectByRequestType(enterpriseId, page.getStart(), pageSize, startTimes, endTimes, DistrReqPlanStatus.WAIT_DISTR, DistributionType.DIRECT_DISTRIBUTION.getCode(), orderName, orderType);
        for(DistrReqPlanPageVO distrReqPlanPageVO : list){
            distrReqPlanPageVO.setRequestTypeName(DistributionType.getName(distrReqPlanPageVO.getRequestType()));
        }

        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return list;
    }

    @Override
    public List<DistrReqPlanDetailPageVO> getDistrReqPlanDetailPage(int pageNo, int pageSize, Long enterpriseId, Page page, Date startTime, Date endTime, Long planId) {

        String startTimes = null;
        String endTimes = null;
        if (startTime != null) {
            startTimes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime);
        }
        if (endTime != null) {
            endTimes = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endTime);
        }
        List<DistrReqPlanDetailPageVO> distrReqPlanDetailPageVOS = distrReqPlanDetailMapper.selectByPlanId(planId,startTimes,endTimes,page.getStart(), pageSize);
        Long totalRecord = distrReqPlanDetailMapper.countByPlanId(planId,page.getStart(), pageSize,startTimes,endTimes);
        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return distrReqPlanDetailPageVOS;
    }

    @Override
    public DistrInNoticeVO callDistrReqPlan(Long id, UserVO loginUser) throws Exception{

        DistrInNoticeVO distrInNoticeVO = new DistrInNoticeVO();
        DistrReqPlan distrReqPlan = distrReqPlanMapper.selectByPrimaryKey(id);
        distrInNoticeVO.setOrderDate(new Date());
        distrInNoticeVO.setDistrType(DistributionType.DIRECT_DISTRIBUTION.getCode());
        distrInNoticeVO.setDistrTypeName(DistributionType.DIRECT_DISTRIBUTION.getName());
        distrInNoticeVO.setStorageManId(loginUser.getUserId());
        distrInNoticeVO.setStorageManName(loginUser.getUserName());
        distrInNoticeVO.setRemark(distrReqPlan.getRemark());
        distrInNoticeVO.setOutboundUnitId(distrReqPlan.getOutboundUnitId());
        distrInNoticeVO.setOutboundUnitCode(distrReqPlan.getOutboundUnitCode());
        distrInNoticeVO.setOutboundUnitName(distrReqPlan.getOutboundUnitName());
        distrInNoticeVO.setDistrUnitId(distrReqPlan.getDistrUnitId());
        distrInNoticeVO.setDistrUnitCode(distrReqPlan.getDistrUnitCode());
        distrInNoticeVO.setDistrUnitName(distrReqPlan.getDistrUnitName());
        distrInNoticeVO.setBaseOrderId(distrReqPlan.getId());
        distrInNoticeVO.setQuantityTotal(BigDecimal.ZERO);
        distrInNoticeVO.setAmountTotal(BigDecimal.ZERO);
        distrInNoticeVO.setWholeDiscount(new BigDecimal(100));
        distrInNoticeVO.setWholeDiscountAmount(BigDecimal.ZERO);
        distrInNoticeVO.setRealAmountTotal(BigDecimal.ZERO);
        distrInNoticeVO.setNotaxRealAmountTotal(BigDecimal.ZERO);
        distrInNoticeVO.setTaxAmountTotal(BigDecimal.ZERO);
        distrInNoticeVO.setDiscountAmount(BigDecimal.ZERO);

        List<DistrInNoticeDetailVO> distrInNoticeDetailVOS = new ArrayList<>();
        List<DistrReqPlanDetail> distrReqPlanDetails = distrReqPlanDetailMapper.selectByDistrReqPlanId(id);
        for(DistrReqPlanDetail distrReqPlanDetail : distrReqPlanDetails){
            DistrInNoticeDetailVO distrInNoticeDetailVO = distrInNoticeDetailMapper.selectByDistrReqPlan(distrReqPlan.getOutboundUnitId(),distrReqPlanDetail.getGoodsId());
            if(distrInNoticeDetailVO == null){
                throw new BusinessException("无效的商品："+distrReqPlanDetail.getGoodsName());
            }
            if(distrInNoticeDetailVO.getTaxRateId() == null){
                distrInNoticeDetailVO.setTaxRateId(distrInNoticeDetailVO.getPurchaseTaxRateId());
            }
            if(distrInNoticeDetailVO.getTaxRate() == null){
                distrInNoticeDetailVO.setTaxRate(distrInNoticeDetailVO.getPurchaseTaxRate());
            }
            if(distrInNoticeDetailVO.getRealPrice() == null){
                distrInNoticeDetailVO.setRealPrice(BigDecimal.ZERO);
            }
            if(distrInNoticeDetailVO.getMemberPrice() == null){
                distrInNoticeDetailVO.setMemberPrice(BigDecimal.ZERO);
            }
            if(distrInNoticeDetailVO.getUnitPrice() == null){
                distrInNoticeDetailVO.setUnitPrice(BigDecimal.ZERO);
            }

            distrInNoticeDetailVO.setQuantity(distrReqPlanDetail.getQuantity());
            distrInNoticeDetailVO.setRemark(distrReqPlanDetail.getRemark());
            distrInNoticeDetailVO.setLineNum(distrReqPlanDetail.getLineNum());
            distrInNoticeDetailVO.setLineDiscount(new BigDecimal(100));
            distrInNoticeDetailVO.setAmount(BigDecimal.ZERO);
            distrInNoticeDetailVO.setWholeDiscount(new BigDecimal(100));
            distrInNoticeDetailVO.setLineDiscountAmount(BigDecimal.ZERO);
            distrInNoticeDetailVO.setRealAmount(BigDecimal.ZERO);
            distrInNoticeDetailVO.setNotaxRealAmount(BigDecimal.ZERO);
            distrInNoticeDetailVO.setNotaxRealPrice(BigDecimal.ZERO);
            distrInNoticeDetailVO.setTaxAmount(BigDecimal.ZERO);
            distrInNoticeDetailVO.setBaseOrderDtlId(distrReqPlanDetail.getId());
            distrInNoticeDetailVO.setBaseOrderId(distrReqPlan.getId());
            distrInNoticeDetailVOS.add(distrInNoticeDetailVO);
        }
        distrInNoticeVO.setDistrInNoticeDetailVOS(distrInNoticeDetailVOS);
        return distrInNoticeVO;
    }

    //获取采购验收单号
    private String getCode(String codePrefix, Long enterpriseId, String enterpriseCode) throws Exception {
        return orderCodeComponent.generate(codePrefix, enterpriseId, enterpriseCode);
    }

    private DistrInNoticeDetail saveDeatil(UserVO loginUser, DistrInNotice distrInNotice, SaveDistrInNoticeDetailVO saveDistrInNoticeDetailVO) throws Exception{
        DistrInNoticeDetail distrInNoticeDetail = new DistrInNoticeDetail();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveDistrInNoticeDetailVO, distrInNoticeDetail);
        UserEnterpriseUtils.setUserCreateOrModify(distrInNoticeDetail, loginUser, true);
        distrInNoticeDetail.setEnterpriseId(loginUser.getEnterpriseId());
        distrInNoticeDetail.setParentId(loginUser.getParentId());
        distrInNoticeDetail.setOrderId(distrInNotice.getId());
        distrInNoticeDetail.setOrderType(distrInNotice.getOrderType());
        distrInNoticeDetail.setOrderCode(distrInNotice.getCode());
        distrInNoticeDetail.setOrderDate(distrInNotice.getOrderDate());
        if(distrInNoticeDetail.getBaseOrderId() != null){
            DistrReqPlan distrReqPlan = distrReqPlanMapper.selectByPrimaryKey(distrInNoticeDetail.getBaseOrderId());
            if(distrReqPlan != null) {
                distrInNoticeDetail.setBaseOrderCode(distrReqPlan.getCode());
                distrInNoticeDetail.setBaseOrderDate(distrReqPlan.getPlanDate());
                distrInNoticeDetail.setBaseOrderType(distrReqPlan.getOrderType());
            }
        }
        Goods goods = goodsMapper.selectByPrimaryKey(distrInNoticeDetail.getGoodsId());
        if (goods != null) {
            distrInNoticeDetail.setGoodsCode(goods.getCode());
            distrInNoticeDetail.setGoodsName(goods.getName());
            distrInNoticeDetail.setBarcode(goods.getBarcode());
            distrInNoticeDetail.setGoodsGenericName(goods.getGenericName());
            distrInNoticeDetail.setDosageId(goods.getDosageId());//剂型ID
            distrInNoticeDetail.setDosageName(goods.getDosageName());
            distrInNoticeDetail.setUnitId(goods.getUnitId());
            distrInNoticeDetail.setUnitName(goods.getUnitName());
            distrInNoticeDetail.setGoodsPlace(goods.getPlace());
            distrInNoticeDetail.setGoodsSpecification(goods.getSpecification());
            distrInNoticeDetail.setManufacturerId(goods.getManufacturerId());//生产厂商ID
            distrInNoticeDetail.setManufacturer(goods.getManufacturer());
            distrInNoticeDetail.setApprovalNumber(goods.getApprovalNumber());
        }
        distrInNoticeDetail.setUnclearQuantity(distrInNoticeDetail.getQuantity());
        distrInNoticeDetail.setClearQuantity(BigDecimal.ZERO);
        distrInNoticeDetail.setStatus(distrInNotice.getStatus());
        distrInNoticeDetail.setWholeDiscount(distrInNotice.getWholeDiscount());

        Map map = new HashMap();
        map.put("enterpriseId", loginUser.getEnterpriseId());
        map.put("goodsId", distrInNoticeDetail.getGoodsId());
        GoodsBusiness goodsBusiness = goodsBusinessMapper.selectByEnterpriseIdAndGoodsId(map);
        if (goodsBusiness != null) {
            distrInNoticeDetail.setRetailPrice(goodsBusiness.getRetailPrice());//零售单价
            distrInNoticeDetail.setMemberPrice(goodsBusiness.getMemberPrice());//会员单价
        }

        //计算金额（整单优惠前金额）
        BigDecimal amount = CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(distrInNoticeDetail.getQuantity(), distrInNoticeDetail.getUnitPrice(), distrInNoticeDetail.getLineDiscount());
        distrInNoticeDetail.setAmount(amount);//金额（整单优惠前金额）
        //计算行优惠（整单优惠分摊到行的金额）
        BigDecimal lineDiscountAmount = BigDecimal.ZERO;
        if (amount.compareTo(BigDecimal.ZERO) == 1) {//大于0
            lineDiscountAmount = CalculateComponent.getLineRoundOffByLineAmountAndWholeAmountTotal(distrInNotice.getWholeDiscountAmount(), amount, distrInNotice.getAmountTotal());
        }
        distrInNoticeDetail.setLineDiscountAmount(lineDiscountAmount);//行优惠（整单优惠分摊到行的金额）
        //计算实际金额
        BigDecimal realAmount = CalculateComponent.getRealAmountByQuantityAndPriceAndLineDiscount(distrInNoticeDetail.getQuantity(), distrInNoticeDetail.getUnitPrice(), distrInNoticeDetail.getLineDiscount(), distrInNotice.getWholeDiscount(), lineDiscountAmount);
        distrInNoticeDetail.setRealAmount(realAmount);//实际金额
        //计算实际单价（实际金额/数量）
        BigDecimal realPrice = BigDecimal.ZERO;
        if (distrInNoticeDetail.getQuantity().compareTo(BigDecimal.ZERO) == 1) {//大于0
            realPrice = CalculateComponent.getRealPriceByRealAmountAndQuantity(realAmount, distrInNoticeDetail.getQuantity());
        }
        distrInNoticeDetail.setRealPrice(realPrice);//实际单价（实际金额/数量）
        //计算不含税实际金额
        BigDecimal notaxRealAmount = CalculateComponent.getNotaxAmountByRealAmountAndTaxRate(realAmount, distrInNoticeDetail.getTaxRate());
        distrInNoticeDetail.setNotaxRealAmount(notaxRealAmount);//不含税实际金额
        //计算不含税实际单价
        BigDecimal notaxRealPrice = new BigDecimal(0);
        if (distrInNoticeDetail.getQuantity().compareTo(BigDecimal.ZERO) == 1) {//如果不含税金额大于0的时候
            notaxRealPrice = CalculateComponent.getNotaxPriceByNotaxAmountAndQuantity(notaxRealAmount, distrInNoticeDetail.getQuantity());
        }
        distrInNoticeDetail.setNotaxRealPrice(notaxRealPrice);//不含税实际单价
        //计算税额
        BigDecimal taxAmount = CalculateComponent.getTaxAmountByRealAmountAndNotaxAmount(realAmount, notaxRealAmount);
        distrInNoticeDetail.setTaxAmount(taxAmount);//税额

        return distrInNoticeDetail;
    }
}