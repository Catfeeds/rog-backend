package com.rograndec.feijiayun.chain.business.distr.parent.service.impl;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.warehouse.dao.WarehouseShelfMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.*;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.*;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrReturnInService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.*;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.transfer.DistrReturnCheckListParam;
import com.rograndec.feijiayun.chain.business.goods.manage.dao.SafetyStockMapper;
import com.rograndec.feijiayun.chain.business.goods.manage.entity.SafetyStock;
import com.rograndec.feijiayun.chain.business.goods.set.dao.GoodsTaxRateMapper;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.CheckProjectGoodsVO;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.CheckProjectVO;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.entity.QualitySet;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.status.DistrReturnStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * The type Distr return in service.
 */
@Service
public class DistrReturnInServiceImpl implements DistrReturnInService {


    private static final Logger logger = LoggerFactory.getLogger(DistrReturnInServiceImpl.class);

    @Autowired
    private DistrReturnCheckMapper distrReturnCheckMapper;

    @Autowired
    private DistrReturnCheckDetailMapper distrReturnCheckDetailMapper;

    @Autowired
    private DistrReturnCheckLotMapper distrReturnCheckLotMapper;

    @Autowired
    private QualitySetMapper qualitySetMapper;

    @Autowired
    private OrderCodeComponent orderCodeComponent;

    @Autowired
    private DistrReturnNoticeMapper distrReturnNoticeMapper;

    @Autowired
    private DistrReturnNoticeDetailMapper distrReturnNoticeDetailMapper;

    @Autowired
    private DistrOutMapper distrOutMapper;

    @Autowired
    private DistrOutDetailMapper distrOutDetailMapper;

    @Autowired
    private DistrOutShelfMapper distrOutShelfMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsTaxRateMapper goodsTaxRateMapper;

    @Autowired
    private DistrReturnInMapper distrReturnInMapper;

    @Autowired
    private DistrReturnInDetailMapper distrReturnInDetailMapper;

    @Autowired
    private DistrReturnInShelfMapper distrReturnInShelfMapper;

    @Autowired
    private WarehouseShelfMapper warehouseShelfMapper;

    @Autowired
    private SafetyStockMapper safetyStockMapper;

    @Autowired
    private DistrComponent distrComponent;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private ManageConfigComponent manageConfigComponent;


    @Autowired
    private FinanceComponent financeComponent;


    /**
     * 查询验收单
     * Get distr return checks list.
     *
     * @return the list
     */
    @Override
    public List<DistrReturnCheck> getDistrReturnChecks(DistrReturnCheckListParam distrReturnCheckListParam, Page page) {

        Map<String, Object> map = new HashMap<>();


        map.put("enterpriseId", distrReturnCheckListParam.getUserVO().getEnterpriseId());
        if (!StringUtils.isEmpty(distrReturnCheckListParam.getRequestUnitCode())) {
            map.put("requestUnitCode", distrReturnCheckListParam.getRequestUnitCode());
        }

        if (!StringUtils.isEmpty(distrReturnCheckListParam.getRequestUnitName())) {
            map.put("requestUnitName", distrReturnCheckListParam.getRequestUnitName());
        }

        if (!StringUtils.isEmpty(distrReturnCheckListParam.getCode())) {
            map.put("code", distrReturnCheckListParam.getCode());
        }

        if (null != distrReturnCheckListParam.getDistrType()) {
            map.put("distrType", distrReturnCheckListParam.getDistrType());
        }

        if (!StringUtils.isEmpty(distrReturnCheckListParam.getCheckerName())) {
            map.put("checkerName", distrReturnCheckListParam.getCheckerName());
        }

        if (!StringUtils.isEmpty(distrReturnCheckListParam.getSecondCheckerName())) {
            map.put("secondCheckerName", distrReturnCheckListParam.getSecondCheckerName());
        }

        if (null != distrReturnCheckListParam.getStartTime()) {
            map.put("startTime", distrReturnCheckListParam.getStartTime());
        }

        if (null != distrReturnCheckListParam.getEndTime()) {
            map.put("endTime", distrReturnCheckListParam.getEndTime());
        }
        /**
         * 购后退回单查询的待入库列表中验收单的状态必须为待入库
         */
        map.put("status", DistrReturnStatus.WAIT_IN);

        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        List<DistrReturnCheck> distrReturnChecks = distrReturnCheckMapper.selectByParam(map);

        List<DistrReturnCheck4ReturnInVO> distrReturnCheck4ReturnInVOs = DistrReturnCheck4ReturnInVO.getDistrReturnCheck4ReturnInVOs(distrReturnChecks);

        page.setResult(distrReturnCheck4ReturnInVOs);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return distrReturnChecks;
    }


    /**
     * 查询验收单明细
     *
     * @param checkId
     * @return
     */
    @Override
    public DistrReturnCheck4ReturnInVO getDistrReturnCheck4ReturnInVO(Long checkId) {

        DistrReturnCheck distrReturnCheck = distrReturnCheckMapper.selectByPrimaryKey(checkId);

        DistrReturnCheck4ReturnInVO distrReturnCheck4ReturnInVO = DistrReturnCheck4ReturnInVO.getDistrReturnCheck4ReturnInVO(distrReturnCheck);

        List<DistrReturnCheckDetail> distrReturnCheckDetails = distrReturnCheckDetailMapper.selectByhCheckId(distrReturnCheck4ReturnInVO.getCheckId());

        List<DistrReturnCheckDetail4ReturnInVO> distrReturnCheckDetail4ReturnInVOs = DistrReturnCheckDetail4ReturnInVO.getDistrReturnCheckDetail4ReturnInVOs(distrReturnCheckDetails);

        distrReturnCheck4ReturnInVO.setDistrReturnCheckDetail4ReturnInVOS(distrReturnCheckDetail4ReturnInVOs);

        List<DistrReturnCheckLot> distrReturnCheckLots = distrReturnCheckLotMapper.selectByCheckId(distrReturnCheck4ReturnInVO.getCheckId());

        List<Long> qualitySetIds = DistrReturnCheckLot.getQualitySetIds(distrReturnCheckLots);

        List<QualitySet> qualitySets = qualitySetMapper.selectByIds(qualitySetIds);

        distrReturnCheckDetail4ReturnInVOs.forEach(drcd -> {
            distrReturnCheckLots.forEach(drc -> {
                if (drcd.getCheckDtId().equals(drc.getDtlId())) {
                    DistrReturnCheckLot4ReturnInVO distrReturnCheckLot4ReturnInVO = DistrReturnCheckLot4ReturnInVO.getDistrReturnCheckLot4ReturnInVO(
                            drc
                            , qualitySets
                    );
                    drcd.getDistrReturnCheckLot4ReturnInVOS().add(distrReturnCheckLot4ReturnInVO);
                }

            });
        });

        return distrReturnCheck4ReturnInVO;
    }

    @Override
    public List<DistrReturnInPageVO> getDistrReturnInPageVOs(DistrReturnCheckListParam distrReturnCheckListParam, Page page) {
        Map<String, Object> map = new HashMap<>();


        if (!StringUtils.isEmpty(distrReturnCheckListParam.getRequestUnitCode())) {
            map.put("requestUnitCode", distrReturnCheckListParam.getRequestUnitCode());
        }

        if (!StringUtils.isEmpty(distrReturnCheckListParam.getRequestUnitName())) {
            map.put("requestUnitName", distrReturnCheckListParam.getRequestUnitName());
        }

        if (!StringUtils.isEmpty(distrReturnCheckListParam.getCode())) {
            map.put("code", distrReturnCheckListParam.getCode());
        }

        if (null != distrReturnCheckListParam.getDistrType()) {
            map.put("distrType", distrReturnCheckListParam.getDistrType());
        }

        if (!StringUtils.isEmpty(distrReturnCheckListParam.getCheckerName())) {
            map.put("checkerName", distrReturnCheckListParam.getCheckerName());
        }

        if (null != distrReturnCheckListParam.getStartTime()) {
            map.put("startTime", distrReturnCheckListParam.getStartTime());
        }

        if (null != distrReturnCheckListParam.getEndTime()) {
            map.put("endTime", distrReturnCheckListParam.getEndTime());
        }
        if (!StringUtils.isEmpty(distrReturnCheckListParam.getOrderName())) {

            if ("returnInDate".equals(distrReturnCheckListParam.getOrderName())) {
                map.put("orderName", "return_in_date");
            } else if ("code".equals(distrReturnCheckListParam.getOrderName())) {
                map.put("orderName", "code");
            }

        }

        if (!StringUtils.isEmpty(distrReturnCheckListParam.getOrderType())) {
            map.put("orderType", distrReturnCheckListParam.getOrderType());
        }


        map.put("enterpriseId", distrReturnCheckListParam.getUserVO().getEnterpriseId());
        /**
         * 购后退回单查询的待入库列表中验收单的状态必须为待入库或是待配送
         */
        List<Integer> statuses = new ArrayList<>();

        statuses.add(DistrReturnStatus.WAIT_SEND);
        statuses.add(DistrReturnStatus.WAIT_BILL);
        statuses.add(DistrReturnStatus.PART_BILL);
        statuses.add(DistrReturnStatus.FINISHED);
        map.put("status", statuses);


        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        List<DistrReturnIn> distrReturnIns = distrReturnInMapper.selectByParam(map);

        DistrReturnInPageTotalVO distrReturnInPageTotalVO = distrReturnInMapper.selectByParamCount(map);


        List<DistrReturnInPageVO> distrReturnInPageVOs = DistrReturnInPageVO.getDistrReturnInPageVOs(distrReturnIns);

        List<Long> ids = DistrReturnIn.getIds(distrReturnIns);

        if (org.springframework.util.CollectionUtils.isEmpty(ids)) {
            distrReturnInPageTotalVO = new DistrReturnInPageTotalVO();
            page.setResult(distrReturnInPageTotalVO);
            return distrReturnInPageVOs;
        }

        List<DistrReturnInDetail> distrReturnInDetails = distrReturnInDetailMapper.selectByReturnIds(ids);

        if (org.springframework.util.CollectionUtils.isEmpty(distrReturnInDetails)) {
            distrReturnInPageTotalVO = new DistrReturnInPageTotalVO();
            page.setResult(distrReturnInPageTotalVO);
            return distrReturnInPageVOs;
        }

        List<DistrReturnInDetailPageVO> distrReturnInDetailPageVOs = DistrReturnInDetailPageVO.getDistrReturnInDetailPageVOs(distrReturnInDetails);

        distrReturnInPageVOs.forEach(dri -> {
            distrReturnInDetailPageVOs.forEach(drid -> {
                if (drid.getReturnInId().equals(dri.getId())) {
                    dri.getDistrReturnInDetailFormVOS().add(drid);
                }
            });

        });

        List<DistrReturnInShelf> distrReturnInShelves = distrReturnInShelfMapper.selectByReturnIds(ids);

        if (org.springframework.util.CollectionUtils.isEmpty(distrReturnInShelves)) {
            distrReturnInPageTotalVO = new DistrReturnInPageTotalVO();
            page.setResult(distrReturnInPageTotalVO);
            return distrReturnInPageVOs;
        }

        List<DistrReturnInShelfPageVO> distrReturnInShelfPageVOs = DistrReturnInShelfPageVO.getDistrReturnInShelfPageVOs(distrReturnInShelves);

        distrReturnInDetailPageVOs.forEach(drid -> {

            distrReturnInShelfPageVOs.forEach(drsf -> {
                if (drid.getId().equals(drsf.getDtlId())) {
                    drid.getDistrReturnInShelfFormVOS().add(drsf);
                }

            });
        });

        if (null != distrReturnInPageTotalVO) {
            distrReturnInPageTotalVO.setDistrReturnInPageVOS(distrReturnInPageVOs);
        }

        page.setResult(distrReturnInPageTotalVO);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());

        return distrReturnInPageVOs;
    }

    @Override
    public DistrReturnInPageVO getDistrReturnInPageVO(Long returnId) {

        DistrReturnIn distrReturnIn = distrReturnInMapper.selectByPrimaryKey(returnId);
        DistrReturnInPageVO distrReturnInPageVO = DistrReturnInPageVO.getDistrReturnInPageVO(distrReturnIn);

        List<DistrReturnInDetail> distrReturnInDetails = distrReturnInDetailMapper.selectByReturnId(distrReturnIn.getId());

        List<DistrReturnInDetailPageVO> distrReturnInDetailPageVOs = DistrReturnInDetailPageVO.getDistrReturnInDetailPageVOs(distrReturnInDetails);

        distrReturnInPageVO.setDistrReturnInDetailFormVOS(distrReturnInDetailPageVOs);

        List<DistrReturnInShelf> distrReturnInShelves = distrReturnInShelfMapper.selectByReturnId(distrReturnIn.getId());

        List<DistrReturnInShelfPageVO> distrReturnInShelfPageVOs = DistrReturnInShelfPageVO.getDistrReturnInShelfPageVOs(distrReturnInShelves);

        distrReturnInDetailPageVOs.forEach(drid -> {

            distrReturnInShelfPageVOs.forEach(drsf -> {
                if (drid.getId().equals(drsf.getDtlId())) {
                    drid.getDistrReturnInShelfFormVOS().add(drsf);
                }

            });
        });

        return distrReturnInPageVO;
    }

    /**
     * 新增购后退回单
     *
     * @param userVO
     * @param distrReturnInFormVO
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDistrReturnIn(UserVO userVO, DistrReturnInFormVO distrReturnInFormVO) throws Exception {


        DistrReturnInVO distrReturnInVO = commonComponent.generateAndSaveDistrReturnIn(userVO, distrReturnInFormVO);

        /**
         * 修改总部配货出库的未清数量跟以清数量
         */
        /**
         DistrReturnIn distrReturnIn = distrReturnInVO.getDistrReturnIn();
         DistrReturnCheck distrReturnCheck = distrReturnCheckMapper.selectByPrimaryKey(distrReturnIn.getBaseOrderId());
         DistrReturnNotice distrReturnNotice = distrReturnNoticeMapper.selectByPrimaryKey(distrReturnCheck.getNoticeId());
         DistrOut distrOut = distrOutMapper.selectByPrimaryKey(distrReturnNotice.getBaseOrderId());
         List<DistrOutDetail> distrOutDetailS = distrOutDetailMapper.getDistrOutDetailList(distrReturnNotice.getBaseOrderId());
         BigDecimal quantity = BigDecimal.ZERO;
         for(DistrReturnInDetail distrReturnInDetail : distrReturnInVO.getDistrReturnInDetails()){
         for(DistrOutDetail distrOutDetail : distrOutDetailS){
         //全退标识
         boolean allBackFlag = false;
         if(distrReturnInDetail.getGoodsId().equals(distrOutDetail.getGoodsId())){
         //将配后退回数量加到总部的配货出库单的未清数量,同时以清数量减少
         distrOutDetail.setUnclearQuantity(distrReturnInDetail.getUnclearQuantity().add(distrReturnInDetail.getQuantity()));
         distrOutDetail.setClearQuantity(distrReturnInDetail.getClearQuantity().add(distrReturnInDetail.getQuantity()));
         //判断配后退回数量跟配货出库的数量是否相等,如果相等,则将状态更新为已完成
         if(distrReturnInDetail.getQuantity().compareTo(distrOutDetail.getQuantity()) == 0){
         allBackFlag = true;
         distrOutDetail.setStatus(DistrInStatus.FINISHED);
         }
         quantity = quantity.add(distrReturnInDetail.getQuantity());
         distrOutDetailMapper.updateByPrimaryKeySelective(distrOutDetail);
         for(DistrReturnInShelf distrReturnInShelf : distrReturnInVO.getDistrReturnInShelfs()){
         List<DistrOutShelf> distrOutShelves = distrOutShelfMapper.getDistrOutShelfList(distrOutDetail.getId());
         for(DistrOutShelf distrOutShelf : distrOutShelves){
         if(distrReturnInShelf.getLotNumber().equals(distrOutShelf.getLotNumber())
         && distrReturnInShelf.getShelfId().equals(distrOutShelf.getShelfId())){
         //将配后退回数量加到总部的配货出库单的未清数量,同时以清数量减少
         distrOutShelf.setUnclearQuantity(distrOutShelf.getUnclearQuantity().add(distrReturnInShelf.getQuantity()));
         distrOutShelf.setClearQuantity(distrOutShelf.getClearQuantity().subtract(distrReturnInShelf.getQuantity()));
         //判断配后退回数量跟配货出库的数量是否相等,如果相等,则将状态更新为已完成
         if(allBackFlag){
         distrOutShelf.setStatus(DistrInStatus.FINISHED);
         }
         distrOutShelfMapper.updateByPrimaryKeySelective(distrOutShelf);
         }
         }
         }
         }
         }

         }
         if(quantity.compareTo(distrOut.getQuantityTotal()) == 0){
         distrOut.setStatus(DistrInStatus.FINISHED);
         distrOutMapper.updateByPrimaryKeySelective(distrOut);
         }
         */

        /**
         * 调用财务业务表数据
         */
/*
        financeComponent.distrReturnInToBalanceAndVoucher(userVO, distrReturnInVO.getDistrReturnIn());
*/
    }

    @Override
    /**
     * 入库前准备数据
     * @return
     */
    public DistrReturnInPageVO getDistrReturnInPageVO4Check(UserVO userVO, Long checkId) {

        DistrReturnCheck distrReturnCheck = distrReturnCheckMapper.selectByPrimaryKey(checkId);

        List<DistrReturnCheckDetail> distrReturnCheckDetails = distrReturnCheckDetailMapper.selectByhCheckId(checkId);

        List<DistrReturnCheckLot> distrReturnCheckLots = distrReturnCheckLotMapper.selectByCheckId(checkId);

        Long noticeId = distrReturnCheck.getNoticeId();

        DistrReturnNotice distrReturnNotice = distrReturnNoticeMapper.selectByPrimaryKey(noticeId);

        List<DistrReturnNoticeDetail> distrReturnNoticeDetails = distrReturnNoticeDetailMapper.selectByEnterpriseIdAndNoticeId(userVO.getEnterpriseId(), noticeId);

        DistrReturnInPageVO distrReturnInPageVO = DistrReturnInPageVO.getDistrReturnInPageVO4ReturnCheck(userVO, distrReturnCheck, distrReturnNotice);

        List<Long> goodsIds = DistrReturnCheckLot.getGoodsIds(distrReturnCheckLots);

        List<SafetyStock> safetyStocks = safetyStockMapper.selectByGoodsIdsAndEnterpriseId(goodsIds, userVO.getEnterpriseId());

        Map<Long, SafetyStock> safetyStockMap = SafetyStock.getSafetyStockMap(safetyStocks);

        for (DistrReturnCheckDetail distrReturnCheckDetail : distrReturnCheckDetails) {

            for (DistrReturnNoticeDetail distrReturnNoticeDetail : distrReturnNoticeDetails) {

                if (distrReturnCheckDetail.getNoticeDtlId().equals(distrReturnNoticeDetail.getId())) {

                    DistrReturnInDetailPageVO distrReturnInDetailPageVO = DistrReturnInDetailPageVO.getDistrReturnInDetailPageVO4Check(
                            distrReturnCheckDetail
                            , distrReturnNoticeDetail
                            , distrReturnNotice
                    );
                    distrReturnInPageVO.getDistrReturnInDetailFormVOS().add(distrReturnInDetailPageVO);

                    for (DistrReturnCheckLot distrReturnCheckLot : distrReturnCheckLots) {
                        if (distrReturnCheckLot.getDtlId().equals(distrReturnCheckDetail.getId())) {

                            List<DistrReturnInShelfPageVO> distrReturnInShelfPageVO4Check = DistrReturnInShelfPageVO.getDistrReturnInShelfPageVO4Check(
                                    distrReturnCheckLot
                                    , safetyStockMap.get(distrReturnCheckLot.getGoodsId())
                            );

                            distrReturnInDetailPageVO.getDistrReturnInShelfFormVOS().addAll(distrReturnInShelfPageVO4Check);
                        }
                    }
                }
            }

        }

        List<DistrReturnInDetailPageVO> distrReturnInDetailFormVOS = distrReturnInPageVO.getDistrReturnInDetailFormVOS();
        distrReturnInPageVO = DistrReturnInPageVO.afterGenerateDetailsetReturnIn(distrReturnInPageVO, distrReturnInDetailFormVOS);

        return distrReturnInPageVO;

    }


    @Override
    public void exportDetail(OutputStream output, Long orderId) {

        DistrReturnInPageVO distrReturnInPageVO = getDistrReturnInPageVO(orderId);

        DistrReturnInExcelVO distrReturnInExcelVO = DistrReturnInExcelVO.getDistrReturnInPageVO(distrReturnInPageVO);

        List<DistrReturnInDetailPageVO> distrReturnInDetailFormVOS = distrReturnInPageVO.getDistrReturnInDetailFormVOS();

        List<DistrReturnInDetailExcelVO> distrReturnInDetailExcelVOSAll = new ArrayList<>();
        for (DistrReturnInDetailPageVO distrReturnInDetailPageVO : distrReturnInDetailFormVOS) {
            List<DistrReturnInDetailExcelVO> distrReturnInDetailExcelVOS = DistrReturnInDetailExcelVO.getDistrReturnInDetailPageVO(distrReturnInDetailPageVO);
            distrReturnInDetailExcelVOSAll.addAll(distrReturnInDetailExcelVOS);
        }


        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("goodsCode", "商品编码");
        // map.put("goodsName", "商品名称");
        map.put("goodsGenericName", "商品通用名称");
        // map.put("dosageId", "剂型ID");
        map.put("dosageName", "剂型");
        map.put("goodsSpecification", "规格");
        map.put("unitName", "单位");
        map.put("manufacturer", "生产厂商");

        map.put("shelfStatusDesc", "质量状况");
        map.put("lotNumber", "批号");
        map.put("productDate", "生产日期");
        map.put("validDate", "有效期至");
        map.put("shelfName", "货位");
        map.put("quantity", "数量");
        map.put("notaxRealPrice", "单价");
        map.put("lineDiscount", "折扣");
        map.put("amount", "金额");
        map.put("taxRate", "税率");
        map.put("notaxRealPrice", "不含税单价");
        map.put("notaxRealAmount", "不含税金额");
        map.put("taxAmount", "税额");
        map.put("remark", "备注");

        List<String> titleSecond = new ArrayList<>();
        // 标题栏下第一行
        StringBuilder titleSecondRow = new StringBuilder();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 格式化下日期
        titleSecondRow.append("  要货单位编码： ");
        titleSecondRow.append(distrReturnInExcelVO.getRequestUnitCode());
        titleSecondRow.append("  要货单位名称：");
        titleSecondRow.append(distrReturnInExcelVO.getRequestUnitName());
        titleSecondRow.append("  配后退回入库单号：  ");
        titleSecondRow.append(distrReturnInExcelVO.getCode());
        titleSecondRow.append("  入库日期：     ");
        titleSecondRow.append(format.format(distrReturnInExcelVO.getReturnInDate()));
        titleSecondRow.append("  入库人员：  ");
        titleSecondRow.append(distrReturnInExcelVO.getStorageManName());
        titleSecondRow.append("  配货类型：");
        titleSecondRow.append(DistrType.getValue(distrReturnInExcelVO.getDistrType()));
        titleSecondRow.append("  流通监管码： ");
        titleSecondRow.append(distrReturnInExcelVO.getFlowCode());

        titleSecond.add(titleSecondRow.toString());

        BigDecimal divide = distrReturnInExcelVO.getWholeDiscount().divide(new BigDecimal(100));
        BigDecimal subtract = new BigDecimal(1).subtract(divide);
        BigDecimal multiply = distrReturnInExcelVO.getAmountTotal().multiply(subtract);

        StringBuilder end = new StringBuilder();
        end.append("商品  ");
        end.append("总金额为：");
        end.append(distrReturnInExcelVO.getAmountTotal());
        //每行以分号隔开
        end.append(";");
        end.append("折扣：");
        end.append(distrReturnInExcelVO.getWholeDiscount());
        end.append("%");
        end.append("   " + multiply);
        end.append(";");
        end.append("优惠：");
        end.append(distrReturnInExcelVO.getWholeDiscountAmount());
        end.append(";");
        end.append("总额：");
        end.append(distrReturnInExcelVO.getRealAmountTotal());
        end.append(";");
        end.append("不含税总额：");
        end.append(distrReturnInExcelVO.getNotaxRealAmountTotal());
        end.append(";");
        end.append("税额：");
        end.append(distrReturnInExcelVO.getTaxAmountTotal());
        end.append(";");

        List<String> name = new ArrayList<>();
        name.add("配后退回入库单");


        StringBuilder endTotal = new StringBuilder();
        endTotal.append(distrReturnInExcelVO.getQuantityTotal());
        endTotal.append(";");
        endTotal.append(distrReturnInExcelVO.getAmountTotal());
        endTotal.append(";");
        endTotal.append(distrReturnInExcelVO.getNotaxRealAmountTotal());
        endTotal.append(";");
        endTotal.append(distrReturnInExcelVO.getTaxAmountTotal());
        List<String> locationList = new ArrayList<String>();
        locationList.add("quantity");
        locationList.add("amount");
        locationList.add("notaxRealAmount");
        locationList.add("taxAmount");
        purchaseGeneralComponent.commExcelDistrExport(output, map, distrReturnInDetailExcelVOSAll, name, titleSecond, end.toString(), endTotal.toString(), locationList);


    }

    @Override
    public Page getDistrReturnNoticeList(Page page, Long enterpriseId, Date startTime, Date endTime, String order, String sort) {

        if ("noticeDate".equals(order)) {
            order = "notice_date";
        } else if ("code".equals(order)) {
            order = "code";
        }

        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", enterpriseId);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("status", DistrReturnStatus.WAIT_RECEIVE);// 待收货

        map.put("order", order);
        map.put("sort", sort);


        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        //查询配货单列表
        List<DistrReturnNoticePageVO> orderList = distrReturnNoticeMapper.getDistrReturnNoticeList(map);
        page.setResult(orderList);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return page;

    }

    @Override
    public Page<List<DistrReturnNoticeDtlVO>> getDistrReturnNoticeDtlList(UserVO loginUser, Long id, Page page) {

        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

        //查明细单
        List<DistrReturnNoticeDetail> detailList = distrReturnNoticeDetailMapper.selectByEnterpriseIdAndNoticeId(loginUser.getEnterpriseId(), id);
        List<DistrReturnNoticeDtlVO> returnNoticeDtlVOList = new ArrayList<DistrReturnNoticeDtlVO>();
        for (DistrReturnNoticeDetail d : detailList) {
            DistrReturnNoticeDtlVO vo = new DistrReturnNoticeDtlVO();
            vo = DistrReturnNoticeDtlVO.convertToVO(d);
            returnNoticeDtlVOList.add(vo);
        }

        page.setResult(returnNoticeDtlVOList);
        page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
        page.setTotalPage(hPage.getPages());
        return page;

    }


    @Override
    public DistrReturnInFromNoticeVO initReturnInFromNoticeVO(UserVO loginUser, Long id) {

        DistrReturnInFromNoticeVO inFromNoticeVO = new DistrReturnInFromNoticeVO();
        inFromNoticeVO.setNoticeId(id);

        // 默认人员  收货 、 验收 、 入库

        ManageConfig manageConfig = manageConfigComponent.getMangeConfigByEPId(loginUser);

        inFromNoticeVO.setReceiverId(manageConfig.getReceiverId());
        inFromNoticeVO.setReceiverName(manageConfig.getReceiverName());

        inFromNoticeVO.setCheckerId(manageConfig.getCheckerId());
        inFromNoticeVO.setCheckerName(manageConfig.getCheckerName());

        inFromNoticeVO.setStorageManId(loginUser.getUserId());
        inFromNoticeVO.setStorageManName(loginUser.getUserName());


        // 查询总单
        DistrReturnNotice distrReturnNotice = distrReturnNoticeMapper.selectByPrimaryKey(id);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrReturnNotice, inFromNoticeVO);

        //查明细单
        List<DistrReturnNoticeDetail> detailList = distrReturnNoticeDetailMapper.selectByEnterpriseIdAndNoticeId(loginUser.getEnterpriseId(), id);

        List<DistrReturnInFromNoticeDtlVO> inFromNoticeDtlVOList = new ArrayList<>();
        inFromNoticeVO.setDistrReturnInFromNoticeDtlVOList(inFromNoticeDtlVOList);

        List<String> codeList = new ArrayList<>();
        for (DistrReturnNoticeDetail noticeDetail : detailList) {

            codeList.add(noticeDetail.getGoodsCode());
            DistrReturnInFromNoticeDtlVO inFromNoticeDtlVO = new DistrReturnInFromNoticeDtlVO();

            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(noticeDetail, inFromNoticeDtlVO);
            inFromNoticeDtlVO.setReceiveQuantity(noticeDetail.getQuantity());// 收货数量
            inFromNoticeDtlVO.setArrivalQuantity(noticeDetail.getQuantity());// 到货数量
            //inFromNoticeDtlVO.setRefuseQuantity(BigDecimal.ZERO);
            inFromNoticeDtlVO.setQualifiedQuantity(noticeDetail.getQuantity());// 合格数量
            inFromNoticeDtlVO.setUnqualifiedQuantity(BigDecimal.ZERO); // 不合格数量
            inFromNoticeDtlVO.setInQuantity(noticeDetail.getQuantity());// 入库数量

            inFromNoticeDtlVO.setBaseOrderId(distrReturnNotice.getId());// 配后退回通知单ID
            inFromNoticeDtlVO.setBaseOrderDtlId(noticeDetail.getId());// 配后退回明细单ID
            inFromNoticeDtlVOList.add(inFromNoticeDtlVO);

            List<DistrReturnInFromNoticeSheltDtlVO> inFromNOticeShelfVOList = new ArrayList<>();

            List<Long> goodsIds = new ArrayList<>();
            goodsIds.add(noticeDetail.getGoodsId());

            // 查询批号和货位
            //查询分店-配进入库-配进退出出库货位明细   通过配后退回通知单ID
            Long baseOrderDtlId = noticeDetail.getId();
            List<ClickCheckDetailOneVO> clickCheckDetailOneVO = distrReturnCheckLotMapper.selectByBranchDistrInReturnOutShelf(loginUser.getEnterpriseId(), baseOrderDtlId);

            for (ClickCheckDetailOneVO checkDetailOneVO : clickCheckDetailOneVO) {

                DistrReturnInFromNoticeSheltDtlVO inFromNoticeShelfVO = new DistrReturnInFromNoticeSheltDtlVO();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(checkDetailOneVO, inFromNoticeShelfVO);
                inFromNoticeShelfVO.setSamplingQuantity(checkDetailOneVO.getReceiveQuantity());// 抽样数量
                inFromNoticeShelfVO.setQualifiedQuantity(checkDetailOneVO.getReceiveQuantity());// 合格数量
                inFromNoticeShelfVO.setUnqualifiedQuantity(BigDecimal.ZERO);// 不合格数量
                inFromNOticeShelfVOList.add(inFromNoticeShelfVO);

                // 默认的合格品货位
                List<SafetyStock> safetyStocks = safetyStockMapper.selectByGoodsIdsAndEnterpriseId(goodsIds, loginUser.getEnterpriseId());
                if (CollectionUtils.isNotEmpty(safetyStocks)) {
                    inFromNoticeShelfVO.setShelfId(safetyStocks.get(0).getDefaultShelfId());
                    inFromNoticeShelfVO.setShelfName(safetyStocks.get(0).getDefaultShelfName());
                }

            }
            inFromNoticeDtlVO.setDistrReturnInFromNoticeSheltDtlVOList(inFromNOticeShelfVOList);

        }


        CheckProjectGoodsVO checkProjects = commonComponent.getCheckProjects(codeList, loginUser.getEnterpriseId());

        Map<Long, List<CheckProjectVO>> checkProjectVOMap = checkProjects.getCheckProjectVOMap();


        for (DistrReturnInFromNoticeDtlVO inFromNoticeDtlVO : inFromNoticeVO.getDistrReturnInFromNoticeDtlVOList()) {

            Long goodsId = inFromNoticeDtlVO.getGoodsId();

            for (DistrReturnInFromNoticeSheltDtlVO inFromNoticeShelfVO : inFromNoticeDtlVO.getDistrReturnInFromNoticeSheltDtlVOList()) {

                List<CheckProjectVO> checkProjectVOS = checkProjectVOMap.get(goodsId);

                if (checkProjectVOS != null) {
                    String projectIds = "";
                    for (CheckProjectVO vo : checkProjectVOS) {

                        projectIds = projectIds + vo.getId() + ",";

                    }
                    inFromNoticeShelfVO.setCheckProIds(projectIds);
                }


            }

        }

        return inFromNoticeVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> saveDistrReturnInFromNotice(UserVO loginUser, DistrReturnInFromNoticeVO distrReturnInFromNoticeVO) throws Exception {
        List<String> resultList = new ArrayList<>();
        // 收货
        Result<Map<String, Object>> msg = new Result<>();
        msg = saveDistrReturnReceiveOrder(loginUser, distrReturnInFromNoticeVO, msg);
        DistrReturnReceive distrReturnReceive = (DistrReturnReceive) msg.getData().get("distrReturnReceive");
        resultList.add("已经成功生成总部配后收货单:" + distrReturnReceive.getCode());

        // 验收
        msg = saveDistrReturnCheckOrder(loginUser, distrReturnInFromNoticeVO, msg);

        DistrReturnCheck distrReturnCheck = (DistrReturnCheck) msg.getData().get("distrReturnCheck");
        resultList.add("已经成功生成总部配后验收单:" + distrReturnCheck.getCode());

        // 入库
        msg = saveDistrReturnInOrder(loginUser, distrReturnInFromNoticeVO, msg);
        DistrReturnInVO distrReturnInVO = (DistrReturnInVO) msg.getData().get("distrReturnInVO");
        resultList.add("已经成功生成总部配后入库单:" + distrReturnInVO.getDistrReturnIn().getCode());


        return resultList;
    }


    /**
     * 保存配后入库
     *
     * @param loginUser
     * @param distrReturnInFromNoticeVO
     * @param msg
     */
    private Result<Map<String, Object>> saveDistrReturnInOrder(UserVO loginUser, DistrReturnInFromNoticeVO distrReturnInFromNoticeVO, Result<Map<String, Object>> msg) throws Exception {
        Map<String, Object> idMap = msg.getData();
        DistrReturnCheck distrReturnCheck = (DistrReturnCheck) idMap.get("distrReturnCheck");

        Long baseOrderId = distrReturnCheck.getId();
        List<DistrReturnCheckDetail> distrReturnCheckDetailList = (List<DistrReturnCheckDetail>) idMap.get("baseOrderDtlIdList");
        List<DistrReturnCheckLot> distrReturnCheckLots = (List<DistrReturnCheckLot>) idMap.get("distrReturnCheckLots");

        logger.info("配后验收 总单：" + distrReturnCheck);
        logger.info("配后验收 明细单：" + distrReturnCheckDetailList);
        DistrReturnInFormVO returnInFormVO = new DistrReturnInFormVO();

        returnInFormVO.setCheckId(baseOrderId);
        returnInFormVO.setFlowCode(distrReturnInFromNoticeVO.getFlowCode());
        returnInFormVO.setReturnInDate(distrReturnInFromNoticeVO.getReturnInDate());
        returnInFormVO.setWholeDiscount(distrReturnInFromNoticeVO.getWholeDiscount());
        returnInFormVO.setWholeDiscountAmount(distrReturnInFromNoticeVO.getWholeDiscountAmount());
        returnInFormVO.setStorageManId(distrReturnInFromNoticeVO.getStorageManId());
        List<DistrReturnInDetailFormVO> returnInDetailVOList = new ArrayList<>();

        for (DistrReturnInFromNoticeDtlVO inFromNoticeDtlVO : distrReturnInFromNoticeVO.getDistrReturnInFromNoticeDtlVOList()) {

            DistrReturnInDetailFormVO returnInDetailFormVO = new DistrReturnInDetailFormVO();

//            for (DistrReturnCheckDetail checkDetail : distrReturnCheckDetailList) {
//
//                if (checkDetail.getGoodsId().equals(inFromNoticeDtlVO.getGoodsId())) {
//                    returnInDetailFormVO.setCheckDtId(checkDetail.getId());
//                }
//            }

            returnInDetailFormVO.setLineDiscount(inFromNoticeDtlVO.getLineDiscount());
            returnInDetailFormVO.setLineNum(inFromNoticeDtlVO.getLineNum());
            returnInDetailFormVO.setRemark(inFromNoticeDtlVO.getRemark());
            returnInDetailFormVO.setTaxRateId(inFromNoticeDtlVO.getTaxRateId());
            returnInDetailFormVO.setUnitPrice(inFromNoticeDtlVO.getUnitPrice());
            List<DistrReturnInShelfFormVO> returnInShelfList = new ArrayList<>();

            for (DistrReturnInFromNoticeSheltDtlVO sheltDtlVO : inFromNoticeDtlVO.getDistrReturnInFromNoticeSheltDtlVOList()) {

                DistrReturnInShelfFormVO inShelfFormVO = new DistrReturnInShelfFormVO();

                // 设置验收的明细单，可能会通知单 明细里有多个相同商品不同批号的商品
                for (DistrReturnCheckLot checkLot : distrReturnCheckLots) {
                    if (inFromNoticeDtlVO.getGoodsId().equals(checkLot.getGoodsId()) && checkLot.getLotNumber().equals(sheltDtlVO.getLotNumber())) {
                        returnInDetailFormVO.setCheckDtId(checkLot.getDtlId());
                    }
                }

                inShelfFormVO.setShelfId(sheltDtlVO.getShelfId());
                inShelfFormVO.setJobArea(0);
                inShelfFormVO.setLotNumber(sheltDtlVO.getLotNumber());
                inShelfFormVO.setLineNum(sheltDtlVO.getLineNum());
                returnInShelfList.add(inShelfFormVO);
                if (sheltDtlVO.getUnqualifiedShelfId() != null) {
                    inShelfFormVO.setShelfId(sheltDtlVO.getUnqualifiedShelfId());
                    inShelfFormVO.setJobArea(2);
                    inShelfFormVO.setLotNumber(sheltDtlVO.getLotNumber());
                    inShelfFormVO.setLineNum(sheltDtlVO.getLineNum());
                }
            }

            returnInDetailFormVO.setDistrReturnInShelfFormVOS(returnInShelfList);

            returnInDetailVOList.add(returnInDetailFormVO);

        }

        returnInFormVO.setDistrReturnInDetailFormVOS(returnInDetailVOList);


        DistrReturnInVO distrReturnInVO = commonComponent.generateAndSaveDistrReturnIn(loginUser, returnInFormVO);


        /**
         * 调用财务业务表数据
         */
/*
        financeComponent.distrReturnInToBalanceAndVoucher(loginUser, distrReturnInVO.getDistrReturnIn());
*/
        idMap.put("distrReturnInVO", distrReturnInVO);
        return msg;
    }

    /**
     * 保存配后验收
     *
     * @param loginUser
     * @param distrReturnInFromNoticeVO
     * @param msg
     */
    private Result<Map<String, Object>> saveDistrReturnCheckOrder(UserVO loginUser, DistrReturnInFromNoticeVO distrReturnInFromNoticeVO, Result<Map<String, Object>> msg) throws Exception {

        Map<String, Object> idMap = msg.getData();
        DistrReturnReceive distrReturnReceive = (DistrReturnReceive) idMap.get("distrReturnReceive");
        Long baseOrderId = distrReturnReceive.getId();

        List<DistrReturnReceiveDetail> distrReturnReceiveDetails = (List<DistrReturnReceiveDetail>) idMap.get("distrReturnReceiveDetails");

        logger.info("配后收货总单" + distrReturnReceive);
        logger.info("配后收货 明细单" + distrReturnReceiveDetails);

        //查询分店-配进入库-配进退出出库货位明细   通过配后退回通知单ID
        List<ClickCheckDetailOneVO> clickCheckDetailOneVOList = new ArrayList<>();

        for(DistrReturnReceiveDetail receiveDetail :distrReturnReceiveDetails){

            List<ClickCheckDetailOneVO> tempDetailOneVOList = distrReturnCheckLotMapper.selectByBranchDistrInReturnOutShelf(loginUser.getEnterpriseId(), receiveDetail.getBaseOrderDtlId());
            tempDetailOneVOList.forEach(item -> item.setDistrReturnReceiveDetailId(receiveDetail.getId()));
            clickCheckDetailOneVOList.addAll(tempDetailOneVOList);
        }




        SaveDistrReturnCheckVO returnCheckVO = new SaveDistrReturnCheckVO();
        SaveDistrReturnCheckHeadVO checkHeadVO = new SaveDistrReturnCheckHeadVO();
        returnCheckVO.setSaveDistrReturnCheckHeadVO(checkHeadVO);
        List<SaveDistrReturnCheckDetailVO> checkDtlVOList = new ArrayList<>();
        returnCheckVO.setSaveDistrReturnCheckDetailVO(checkDtlVOList);

        checkHeadVO.setCheckDate(distrReturnInFromNoticeVO.getCheckDate());
        checkHeadVO.setCheckerId(distrReturnInFromNoticeVO.getCheckerId());
        checkHeadVO.setDistrType(distrReturnInFromNoticeVO.getDistrType());
        checkHeadVO.setSecondCheckerId(distrReturnInFromNoticeVO.getSecondCheckerId());
        checkHeadVO.setReceiveId(baseOrderId);
        checkHeadVO.setRemark(distrReturnInFromNoticeVO.getRemark());

        for (DistrReturnInFromNoticeDtlVO inFromNoticeDtlVO : distrReturnInFromNoticeVO.getDistrReturnInFromNoticeDtlVOList()) {

            SaveDistrReturnCheckDetailVO checkDetailVO = new SaveDistrReturnCheckDetailVO();

//            for (DistrReturnReceiveDetail receiveDetail : distrReturnReceiveDetails) {
//                if (receiveDetail.getGoodsId().equals(inFromNoticeDtlVO.getGoodsId())) {
//                    checkDetailVO.setDistrReturnReceiveDetailId(receiveDetail.getId());// 明细ID
//                }
//            }

            Long goodsId = inFromNoticeDtlVO.getGoodsId();
            Integer lineNum = inFromNoticeDtlVO.getLineNum();
            checkDetailVO.setGoodsId(goodsId);
            checkDetailVO.setLineNum(lineNum);
            checkDetailVO.setReceiveQuantity(inFromNoticeDtlVO.getReceiveQuantity());
            checkDetailVO.setQualifiedQuantity(inFromNoticeDtlVO.getQualifiedQuantity());
            checkDetailVO.setUnqualifiedQuantity(inFromNoticeDtlVO.getUnqualifiedQuantity());
            List<SaveDistrReturnCheckDetailOneVO> checkDetailOneVOList = new ArrayList<>();
            checkDetailVO.setSaveDistrReturnCheckDetailOneVO(checkDetailOneVOList);

            for (DistrReturnInFromNoticeSheltDtlVO sheltDtlVO : inFromNoticeDtlVO.getDistrReturnInFromNoticeSheltDtlVOList()) {

                // 细单关联 收货单的明细ID
                for (ClickCheckDetailOneVO checkDetailOneVO:clickCheckDetailOneVOList) {

                    if(inFromNoticeDtlVO.getGoodsId().equals(checkDetailOneVO.getGoodsId())
                            && sheltDtlVO.getLotNumber().equals(checkDetailOneVO.getLotNumber())){
                        checkDetailVO.setDistrReturnReceiveDetailId(checkDetailOneVO.getDistrReturnReceiveDetailId());
                    }

                }


                SaveDistrReturnCheckDetailOneVO checkDetailOneVO = new SaveDistrReturnCheckDetailOneVO();
                CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(sheltDtlVO, checkDetailOneVO);// TODO: 不用copy的形式
                checkDetailOneVO.setGoodsId(goodsId);
                checkDetailOneVO.setLineNum(lineNum);
                checkDetailOneVO.setConclusionIds(sheltDtlVO.getConclusionIds());
                if (sheltDtlVO.getCheckProIds() != null) {
                    checkDetailOneVO.setCheckProjectIds(sheltDtlVO.getCheckProIds().split(","));
                }

                checkDetailOneVOList.add(checkDetailOneVO);
            }


            checkDtlVOList.add(checkDetailVO);

        }

        return commonComponent.generateAndSaveDistrReturnCheck(loginUser, returnCheckVO);
    }

    /**
     * 保存配后收货
     *
     * @param loginUser
     * @param distrReturnInFromNoticeVO
     */
    private Result<Map<String, Object>> saveDistrReturnReceiveOrder(UserVO loginUser, DistrReturnInFromNoticeVO distrReturnInFromNoticeVO, Result<Map<String, Object>> msg) throws Exception {

        DistrReturnReceiveSaveVO receiveSaveVO = new DistrReturnReceiveSaveVO();

        Long baseOrderId = distrReturnInFromNoticeVO.getNoticeId();
        receiveSaveVO.setBaseOrderId(baseOrderId);
        receiveSaveVO.setReceiveDate(distrReturnInFromNoticeVO.getReceiveDate());
        receiveSaveVO.setReceiverId(distrReturnInFromNoticeVO.getReceiverId());
        receiveSaveVO.setSecondReceiverId(distrReturnInFromNoticeVO.getSecondReceiverId());
        receiveSaveVO.setQuantityTotal(distrReturnInFromNoticeVO.getQuantityTotal());
        receiveSaveVO.setRemark(distrReturnInFromNoticeVO.getRemark());
        receiveSaveVO.setVarietiesQuantity(distrReturnInFromNoticeVO.getDistrReturnInFromNoticeDtlVOList().size());


        List<DistrReturnReceiveDetailSaveVO> receiveDtlVOList = new ArrayList<>();
        for (DistrReturnInFromNoticeDtlVO inFromNoticeDtlVO : distrReturnInFromNoticeVO.getDistrReturnInFromNoticeDtlVOList()) {

            DistrReturnReceiveDetailSaveVO receiveDetailSaveVO = new DistrReturnReceiveDetailSaveVO();

            receiveDetailSaveVO.setBaseOrderId(baseOrderId);
            receiveDetailSaveVO.setArrivalQuantity(inFromNoticeDtlVO.getArrivalQuantity());
            receiveDetailSaveVO.setBaseOrderDtlId(inFromNoticeDtlVO.getBaseOrderDtlId());
            receiveDetailSaveVO.setGoodsId(inFromNoticeDtlVO.getGoodsId());
            receiveDetailSaveVO.setLineNum(inFromNoticeDtlVO.getLineNum());
            receiveDetailSaveVO.setReceiveQuantity(inFromNoticeDtlVO.getReceiveQuantity());
            receiveDetailSaveVO.setRefuseQuantity(BigDecimal.ZERO);
            receiveDetailSaveVO.setRemark(inFromNoticeDtlVO.getRemark());
            receiveDetailSaveVO.setRefuseReasonIds(inFromNoticeDtlVO.getRefuseReasonIds());
            receiveDtlVOList.add(receiveDetailSaveVO);

        }
        receiveSaveVO.setDistrReturnReceiveDetailSaveVOS(receiveDtlVOList);

        return commonComponent.generateAndSaveDistrReturnReceiveOrder(loginUser, receiveSaveVO);

    }


}
