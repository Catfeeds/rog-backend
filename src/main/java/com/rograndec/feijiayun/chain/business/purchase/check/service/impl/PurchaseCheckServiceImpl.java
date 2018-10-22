package com.rograndec.feijiayun.chain.business.purchase.check.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsStorageMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.info.entity.GoodsStorage;
import com.rograndec.feijiayun.chain.business.purchase.check.dao.PurchaseCheckDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.check.dao.PurchaseCheckLotMapper;
import com.rograndec.feijiayun.chain.business.purchase.check.dao.PurchaseCheckMapper;
import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheck;
import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheckDetail;
import com.rograndec.feijiayun.chain.business.purchase.check.entity.PurchaseCheckLot;
import com.rograndec.feijiayun.chain.business.purchase.check.service.PurchaseCheckService;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.*;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderOther;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceive;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceiveDetail;
import com.rograndec.feijiayun.chain.business.purchase.ret.constant.TempControlMode;
import com.rograndec.feijiayun.chain.business.purchase.ret.constant.TransportMode;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.constant.*;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.file.dao.FileMapper;
import com.rograndec.feijiayun.chain.common.file.entity.File;
import com.rograndec.feijiayun.chain.common.vo.DraftCacheVO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zeshi.sun on 2017/9/13.
 */
@Service
public class PurchaseCheckServiceImpl implements PurchaseCheckService {

    @Autowired
    PurchaseCheckMapper purchaseCheckMapper;

    @Autowired
    PurchaseReceiveOtherMapper purchaseReceiveOtherMapper;

    @Autowired
    PurchaseReceiveMapper purchaseReceiveMapper;

    @Autowired
    PurchaseCheckDetailMapper purchaseCheckDetailMapper;

    @Autowired
    PurchaseCheckLotMapper purchaseCheckLotMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OrderCodeComponent orderCodeComponent;

    @Autowired
    PurchaseReceiveDetailMapper purchaseReceiveDetailMapper;

    @Autowired
    QualitySetMapper qualitySetMapper;

    @Autowired
    EnterpriseMapper enterpriseMapper;

    @Autowired
    PurchaseGeneralComponent<CheckDeatilsVO> purchaseGeneralComponent;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    FileMapper fileMapper;

    @Autowired
    private CommonComponent commonComponent;

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    GoodsStorageMapper goodsStorageMapper;

    @Autowired
    PurchaseOrderOtherMapper purchaseOrderOtherMapper;

    @Override
    public List<PurchaseCheckVO> getPurchaseCheckPage(int pageNo, int pageSize, Long enterpriseId, Page page, Date startTime, Date endTime, String supplierCode,
                                                      String supplierName, String code, String checkerName, String orderName, String orderType, Long type) {

        Long totalRecord = null;
        List<PurchaseCheckVO> list = null;
        if (orderName != null && orderName.equals("checkDate"))
            orderName = "check_date";
        else {
            orderName = "id";
            orderType = "desc";
        }
        String startTimes = null;
        String endTimes = null;
        if (startTime != null) {
            startTimes = new SimpleDateFormat("yyyy-MM-dd").format(startTime) + " 00:00:00";
        }
        if (endTime != null) {
            endTimes = new SimpleDateFormat("yyyy-MM-dd").format(endTime) + " 23:59:59";
        }

        if (type == 0) {
//            String status = PurchaseStatus.WAIT_STORAGE.getStatus() + "," + PurchaseStatus.WAIT_BILL.getStatus();
//            String[] statuss = status.split(",");
            totalRecord = purchaseCheckMapper.queryCountByPurchaseCheckParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes, supplierCode,
                    supplierName, code, checkerName);

            list = purchaseCheckMapper.selectPurchaseCheckVoByPurchaseCheckParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes, supplierCode,
                    supplierName, code, checkerName, orderName, orderType);
            for (int i = 0; i < list.size(); i++) {
                String statusName = PurchaseStatus.getName(Integer.valueOf(list.get(i).getStatus()));
                list.get(i).setStatus(statusName);
                PurchaseCheckOtherVO purchaseCheckOtherVos = getPurchaseCheckRight(enterpriseId, list.get(i).getId(), 1L);
                list.get(i).setCarriageModeName(purchaseCheckOtherVos.getCarriageMode());
                list.get(i).setCarriageModeId(purchaseCheckOtherVos.getCarriageModeId());
                PurchaseOrderOther purchaseOrderOther = purchaseOrderOtherMapper.selectByEnterpriseIdByOrderId(enterpriseId, purchaseCheckOtherVos.getOrderId());
                if (purchaseOrderOther != null) {
                    Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(purchaseOrderOther.getSettlementUnitId());
                    if (enterprise == null) {
                        list.get(i).setFranchisedStoreFlag(0);
                    } else if (enterprise.getChainType() == ChainType.Division.getCode()) {
                        list.get(i).setFranchisedStoreFlag(1);
                    } else {
                        list.get(i).setFranchisedStoreFlag(0);
                    }
                } else {
                    list.get(i).setFranchisedStoreFlag(0);
                }
            }
        } else if (type == 1) {
            totalRecord = purchaseCheckMapper.queryCountByOtherParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes, supplierCode,
                    supplierName, code, checkerName, PurchaseStatus.WAIT_STORAGE.getStatus());

            list = purchaseCheckMapper.selectPurchaseCheckVoByOtherParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes, supplierCode,
                    supplierName, code, checkerName, orderName, orderType, PurchaseStatus.WAIT_STORAGE.getStatus());
            for (int i = 0; i < list.size(); i++) {
                String statusName = PurchaseStatus.getName(Integer.valueOf(list.get(i).getStatus()));
                list.get(i).setStatus(statusName);
                PurchaseCheckOtherVO purchaseCheckOtherVos = getPurchaseCheckRight(enterpriseId, list.get(i).getId(), 1L);
                list.get(i).setCarriageModeName(purchaseCheckOtherVos.getCarriageMode());
                list.get(i).setCarriageModeId(purchaseCheckOtherVos.getCarriageModeId());
            }
        }

        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public List<PurchaseCheckHeadVO> getPurchaseCheckHead(UserVO userVO, Long id) {
        List<PurchaseCheckHeadVO> purchaseCheckHeadVos = purchaseCheckMapper.selectHead(userVO.getEnterpriseId(), id);
        
        BigDecimal receiveQuantityTotal = BigDecimal.ZERO;//收货数量
        BigDecimal qualifiedQuantityTotal = BigDecimal.ZERO;//验收合格数量
        BigDecimal unqualifiedQuantityTotal = BigDecimal.ZERO;//验收不合格数量
        BigDecimal samplingQuantityTotal = BigDecimal.ZERO;//抽样数量
        
        List<PurchaseCheckLot> lotList = purchaseCheckLotMapper.selectByEnterpriseIdByCheckId(userVO.getEnterpriseId(), id);
        if(lotList != null){
        	for (PurchaseCheckLot purchaseCheckLot : lotList) {
        		receiveQuantityTotal = receiveQuantityTotal.add(purchaseCheckLot.getReceiveQuantity()!=null?purchaseCheckLot.getReceiveQuantity():BigDecimal.ZERO);
        		qualifiedQuantityTotal = qualifiedQuantityTotal.add(purchaseCheckLot.getQualifiedQuantity()!=null?purchaseCheckLot.getQualifiedQuantity():BigDecimal.ZERO);
        		unqualifiedQuantityTotal = unqualifiedQuantityTotal.add(purchaseCheckLot.getUnqualifiedQuantity()!=null?purchaseCheckLot.getUnqualifiedQuantity():BigDecimal.ZERO);
        		samplingQuantityTotal = samplingQuantityTotal.add(purchaseCheckLot.getSamplingQuantity()!=null?purchaseCheckLot.getSamplingQuantity():BigDecimal.ZERO);
			}
        	if(purchaseCheckHeadVos!=null){
        		for (PurchaseCheckHeadVO head : purchaseCheckHeadVos) {
					head.setReceiveQuantityTotal(receiveQuantityTotal);
					head.setQualifiedQuantityTotal(qualifiedQuantityTotal);
					head.setUnqualifiedQuantityTotal(unqualifiedQuantityTotal);
					head.setSamplingQuantityTotal(samplingQuantityTotal);
					head.setEnterpriseName(userVO.getEnterpriseName());
				}
        	}
        }
        
        return purchaseCheckHeadVos == null ? new ArrayList<>() : purchaseCheckHeadVos;
    }

    @Override
    public PurchaseCheckOtherVO getPurchaseCheckRight(Long enterpriseId, Long id, Long type) {

        PurchaseCheckOtherVO purchaseCheckOtherVos = purchaseCheckMapper.selectDistributionByCheckId(enterpriseId, id, type);
        if (purchaseCheckOtherVos != null) {
            //运输方式（0-陆运；1-空运；2-海运）
            if (purchaseCheckOtherVos.getTransportModeId() != null) {
                purchaseCheckOtherVos.setTransportMode(TransportMode.getName(purchaseCheckOtherVos.getTransportModeId()).getValue());
            }

            //温控方式（0-冷藏车；1-冷藏箱；2-保温箱）
            if (purchaseCheckOtherVos.getTempControlModeId() != null) {
                purchaseCheckOtherVos.setTempControlMode(TempControlMode.getName(purchaseCheckOtherVos.getTempControlModeId()).getValue());
            }

            //承运方式（0-配送；1-委托运输；2-自提）
            if (purchaseCheckOtherVos.getCarriageModeId() != null) {
                purchaseCheckOtherVos.setCarriageMode(DistributionType.getName(purchaseCheckOtherVos.getCarriageModeId()));
            }

            if (purchaseCheckOtherVos.getFileId() != null && !"".equals(purchaseCheckOtherVos.getFileId()))
                purchaseCheckOtherVos.setFileName(fileMapper.selectByPrimaryKey(purchaseCheckOtherVos.getFileId()).getFileName());
        }


        return purchaseCheckOtherVos == null ? new PurchaseCheckOtherVO() : purchaseCheckOtherVos;
    }

    @Override
    public List<PurchaseReceiveReVO> getWaitPurchaseCheckPage(int pageNo, int pageSize, Long enterpriseId, Page page, Date startTime, Date endTime, String supplierCode,
                                                              String supplierName, String code, String receiverName, String orderName, String orderType) {

        String startTimes = null;
        String endTimes = null;
        if (startTime != null) {
            startTimes = new SimpleDateFormat("yyyy-MM-dd").format(startTime) + " 00:00:00";
        }
        if (endTime != null) {
            endTimes = new SimpleDateFormat("yyyy-MM-dd").format(endTime) + " 23:59:59";
        }

        if (orderName != null && orderName.equals("receiveDate"))
            orderName = "receive_date";
        else {
            orderName = "receive_date";
            orderType = "desc";
        }

        Long totalRecord = purchaseCheckMapper.queryCountByWaitParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes, supplierCode,
                supplierName, code, receiverName, PurchaseStatus.WAIT_CHECK.getStatus());
        List<PurchaseReceiveReVO> list = purchaseCheckMapper.selectPurchaseCheckVoByWaitParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes, supplierCode,
                supplierName, code, receiverName, orderName, orderType, PurchaseStatus.WAIT_CHECK.getStatus());
        for (int i = 0; i < list.size(); i++) {
            String statusName = PurchaseStatus.getName(Integer.valueOf(list.get(i).getStatus()));
            list.get(i).setStatus(statusName);
            PurchaseCheckOtherVO purchaseCheckOtherVos = getPurchaseCheckRight(enterpriseId, list.get(i).getId(), 0L);
            list.get(i).setCarriageModeId(purchaseCheckOtherVos.getCarriageModeId());
            list.get(i).setCarriageModeName(purchaseCheckOtherVos.getCarriageMode());
        }
        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return list == null ? new ArrayList<>() : list;
    }

    @Override
    public List<WaitPurchaseCheckHeadVO> getWaitPurchaseCheckHead(Long enterpriseId, Long id) {
        List<WaitPurchaseCheckHeadVO> waitPurchaseCheckHeadVO = purchaseCheckMapper.selectWaitHead(enterpriseId, id);
        return waitPurchaseCheckHeadVO == null ? new ArrayList<>() : waitPurchaseCheckHeadVO;
    }

    @Override
    public List<WaitPurchaseCheckDeatilVO> getWaitPurchaseCheckDetail(Long enterpriseId, Long id) {
        List<WaitPurchaseCheckDeatilVO> waitPurchaseCheckHeadVO = purchaseCheckMapper.selectWaitDeatil(enterpriseId, id);
        //拒收原因ID集合
        for (int j = 0; j < waitPurchaseCheckHeadVO.size(); j++) {
            String refuse_reason_ids = waitPurchaseCheckHeadVO.get(j).getRefuseReasonIds();
            if (refuse_reason_ids != null) {
                String refuse_reason_ids_names = getDeatilNum(enterpriseId, refuse_reason_ids, (long) 0);
                waitPurchaseCheckHeadVO.get(j).setRefuseReasonIds(refuse_reason_ids_names);
            }
        }
        return waitPurchaseCheckHeadVO == null ? new ArrayList<>() : waitPurchaseCheckHeadVO;
    }


    @Override
    public List<PurchaseCheckDeatilVO> getPurchaseCheckDetail(Long enterpriseId, Long id) {
        List<PurchaseCheckDeatilVO> purchaseCheckDeatilVos;
        List<PurchaseCheckDeatilTwoVO> purchaseCheckDeatilTwoVos;

        purchaseCheckDeatilVos = purchaseCheckMapper.selectDeatil(enterpriseId, id);
        for (int i = 0; i < purchaseCheckDeatilVos.size(); i++) {
            Long CheckDtlId = purchaseCheckDeatilVos.get(i).getId();
            purchaseCheckDeatilTwoVos = purchaseCheckMapper.selectDeatilTwo(enterpriseId, CheckDtlId);
            for (int j = 0; j < purchaseCheckDeatilTwoVos.size(); j++) {
                //检验项目ID集合
                String check_project_ids = purchaseCheckDeatilTwoVos.get(j).getCheckProjectIds();
                if (StringUtils.isNotBlank(check_project_ids)) {
                    String check_project_ids_names = getDeatilNum(enterpriseId, check_project_ids, (long) 5);
                    if (check_project_ids_names != null) {
                        purchaseCheckDeatilTwoVos.get(j).setCheckProjectIds(check_project_ids_names);
                    }
                }

                //验收结论ID集合
                String conclusion_ids = purchaseCheckDeatilTwoVos.get(j).getConclusionIds();
                if (StringUtils.isNotBlank(conclusion_ids)) {
                    String conclusion_ids_names = getDeatilNum(enterpriseId, conclusion_ids, (long) 6);
                    if (conclusion_ids_names != null) {
                        purchaseCheckDeatilTwoVos.get(j).setConclusionIds(conclusion_ids_names);
                    }
                }

                //处置措施ID集合
                String measures_ids = purchaseCheckDeatilTwoVos.get(j).getMeasuresIds();
                if (StringUtils.isNotBlank(measures_ids)) {
                    String measures_ids_names = getDeatilNum(enterpriseId, measures_ids, (long) 3);
                    if (measures_ids_names != null) {
                        purchaseCheckDeatilTwoVos.get(j).setMeasuresIds(measures_ids_names);
                    }
                }

                //验收不合格原因ID集合
                String unqualified_reason_ids = purchaseCheckDeatilTwoVos.get(j).getUnqualifiedReasonIds();
                if (StringUtils.isNotBlank(unqualified_reason_ids)) {
                    String unqualified_reason_ids_names = getDeatilNum(enterpriseId, unqualified_reason_ids, (long) 1);
                    if (unqualified_reason_ids_names != null) {
                        purchaseCheckDeatilTwoVos.get(j).setUnqualifiedReasonIds(unqualified_reason_ids_names);
                    }
                }

                //附件信息集合
                String testReportIds = purchaseCheckDeatilTwoVos.get(j).getTestReportIds();
                if (StringUtils.isNotBlank(testReportIds)) {
                    String testReportIdsNames = getTestReportIdsNames(testReportIds);
                    if (testReportIdsNames != null) {
                        purchaseCheckDeatilTwoVos.get(j).setTestReportIds(testReportIdsNames);
                    }
                }
//                //附件信息集合
//                String testReportIds = purchaseCheckDeatilTwoVos.get(j).getTestReportIds();
//                if(!(testReportIds.trim().isEmpty())){
//                    List<TestReportIdsVO> TestReportIdsVO = getTestReportIdsNames(testReportIds,enterpriseId);
//                    purchaseCheckDeatilTwoVos.get(j).setTestReportIdsVO(TestReportIdsVO);
//                }
            }
            purchaseCheckDeatilVos.get(i).setPurchaseCheckDeatilTwoVO(purchaseCheckDeatilTwoVos);
        }

        return purchaseCheckDeatilVos == null ? new ArrayList<>() : purchaseCheckDeatilVos;
    }

    private String getTestReportIdsNames(String testReportIds) {

        String testReportIdNames = "";
        String[] idNames = testReportIds.split(",");
        if (idNames.length > 0) {
            for (int i = 0; i < idNames.length; i++) {
                String[] ids = idNames[i].split(":");
                if (ids.length > 1) {
                    File file = fileMapper.selectByPrimaryKey(Long.valueOf(ids[1]));
                    String fileName = idNames[i] + ":" + file.getFileName();
                    testReportIdNames += fileName + ",";
                }
            }
            if (!testReportIdNames.isEmpty()) {
                testReportIdNames = testReportIdNames.substring(0, testReportIdNames.length() - 1);
            }
        }
        return testReportIdNames;
    }

//    private List<TestReportIdsVO> getTestReportIdsNames(String testReportIds, Long enterpriseId) {
//
//        List<TestReportIdsVO> testReportIdsVOs = new ArrayList<>();
//        TestReportIdsVO testReportIdsVO = new TestReportIdsVO();
//        String[] idNames = testReportIds.split(",");
//        if(idNames.length > 0){
//            for(int i = 0; i < idNames.length; i++){
//                String[] ids = idNames[i].split(":");
//                File file = fileMapper.selectByPrimaryKey(Long.valueOf(ids[1]));
//                testReportIdsVO.setCheckProjectId(Long.valueOf(ids[0]));
//                getRefuseReasons(ids[1], enterpriseId, 5);
//                testReportIdsVO.setCheckProjectName();
//                testReportIdsVO.setFileId(file.getId());
//                testReportIdsVO.setFileName(file.getFileName());
//                testReportIdsVOs.add(testReportIdsVO);
//            }
//        }
//        return testReportIdsVOs;
//    }


    public String getDeatilNum(Long enterpriseId, String ids, Long setType) {

        String[] id = ids.split(",");
        String backVal = purchaseCheckMapper.selectById(enterpriseId, id, setType);
        return backVal;
    }

    @Override
    public CheckHeadVO getCheckHead(Long enterpriseId, Long id, UserVO userVO) throws Exception {

        CheckHeadVO checkHeadVO = new CheckHeadVO();
        DraftCacheVO draftCacheVO = getDraftCacheVO(userVO, id);
        if (draftCacheVO != null) {
            checkHeadVO = (CheckHeadVO) draftCacheVO.getOrderData();
            checkHeadVO.setRedisKeyValue(draftCacheVO.getId());
            checkHeadVO.setCheckDeatilVO(null);
        } else {

            checkHeadVO = purchaseCheckMapper.selectCheckHead(enterpriseId, id);
            checkHeadVO.setCheckerId(userVO.getUserId());
            checkHeadVO.setCheckerCode(userVO.getUserCode());
            checkHeadVO.setCheckerName(userVO.getUserName());
        }
        List<CheckDeatilVO> checkDetail = getCheckDetail(userVO, enterpriseId, checkHeadVO.getId());


        /**
         * 查库明细行是否有特殊药品
         *
         */

        Boolean gspFlag = commonComponent.getGspFlag(userVO);

        for (CheckDeatilVO checkDeatilVO : checkDetail) {

            if (null != checkDeatilVO.getSpecialDrug() && SpecialDrugs.getSpecialDrugsCodes().contains(checkDeatilVO.getSpecialDrug())) {
                checkHeadVO.setSpecialDrug(1);
            }

            if (null != checkDeatilVO.getSpecialDrug() && gspFlag && SpecialDrugs.getSpecialDrugsCodes().contains(checkDeatilVO.getSpecialDrug())) {
                checkHeadVO.setSecondCheckerId(userVO.getUserId());
                checkHeadVO.setSecondCheckerCode(userVO.getUserCode());
                checkHeadVO.setSecondCheckerName(userVO.getUserName());
            }

        }

        return checkHeadVO == null ? new CheckHeadVO() : checkHeadVO;
    }


    @Override
    public List<CheckDeatilVO> getCheckDetail(UserVO userVO, Long enterpriseId, Long id) throws Exception {

        CheckHeadVO checkHeadVO = new CheckHeadVO();
        List<CheckDeatilVO> checkDeatilVo = new ArrayList<>();
        DraftCacheVO draftCacheVO = getDraftCacheVO(userVO, id);
        if (draftCacheVO != null) {
            checkHeadVO = (CheckHeadVO) draftCacheVO.getOrderData();
            checkDeatilVo = checkHeadVO.getCheckDeatilVO();
        } else {
            checkDeatilVo = purchaseCheckMapper.selectCheckDeatil(enterpriseId, id);
        }
        for (int i = 0; i < checkDeatilVo.size(); i++) {
            Long goodsid = checkDeatilVo.get(i).getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsid);
            if (goods.getSpecialDrug() == null) {
                throw new BusinessException("没有取到货品特殊管理药品信息,请查询货品管理");
            }
            if (goods.getSpecialDrug() == -1 || goods.getSpecialDrug() == null) {
                checkDeatilVo.get(i).setSpecialDrug(1);
            } else {
                checkDeatilVo.get(i).setSpecialDrug(0);  //是特殊药品
            }
            GoodsStorage goodsStorage = goodsStorageMapper.getByGoodsId(goodsid);
            if (goodsStorage != null) {
                checkDeatilVo.get(i).setNearEffectPeriod(goodsStorage.getNearEffectPeriod());
                checkDeatilVo.get(i).setNearEffectPeriodUnit(goodsStorage.getNearEffectPeriodUnit());
            }
        }
        return checkDeatilVo == null ? new ArrayList<>() : checkDeatilVo;
    }

    @Override
    public List<CheckProjectVO> getCheckProject(Long enterpriseId, String code) {
        List<CheckProjectVO> checkProjectVo = purchaseCheckMapper.selectCheckProject(enterpriseId, code);
        return checkProjectVo == null ? new ArrayList<>() : checkProjectVo;
    }

    @Override
    public List<ConclusionVO> getConclusion(Long enterpriseId, Integer chainType, Long setType) {
        List<ConclusionVO> conclusionVO = purchaseCheckMapper.selectConclusion(enterpriseId, setType, EnableStatus.ENABLE.getStatus(), chainType);
        return conclusionVO == null ? new ArrayList<>() : conclusionVO;
    }

    //获取采购验收单号
    private String getCode(String codePrefix, Long enterpriseId, String enterpriseCode) throws Exception {
        return orderCodeComponent.generate(codePrefix, enterpriseId, enterpriseCode);
    }

    @Override
    public List<SimpleUserVO> getChecker(UserVO loginUser) throws Exception {
        return userMapper.getSimpleUserVOByEnterpriseId(loginUser.getEnterpriseId(), 1);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Integer saveCheck(UserVO loginUser, SaveCheckVO saveCheckVO) throws Exception, BusinessException {

        SaveCheckHeadVO saveCheckHeadVO = saveCheckVO.getSaveCheckHeadVO();
        PurchaseCheck purchaseCheck = new PurchaseCheck();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveCheckHeadVO, purchaseCheck);
        UserEnterpriseUtils.setUserCreateOrModify(purchaseCheck, loginUser, true);
        purchaseCheck.setEnterpriseId(loginUser.getEnterpriseId());
        purchaseCheck.setParentId(loginUser.getParentId());
        //set验收人信息--start
        User user = userMapper.selectByPrimaryKey(purchaseCheck.getCheckerId());
        if (user != null) {
            purchaseCheck.setCheckerCode(user.getCode());
            purchaseCheck.setCheckerName(user.getName());
            if (purchaseCheck.getSecondCheckerId() != null) {
                user = userMapper.selectByPrimaryKey(purchaseCheck.getSecondCheckerId());
                purchaseCheck.setSecondCheckerCode(user.getCode());
                purchaseCheck.setSecondCheckerName(user.getName());
            }
        }
        //--end
        //set收货订单信息 基础单据,供应商信息,供应商销售人员信息--start
        PurchaseReceive purchaseReceive = purchaseCheckMapper.selectByReceive(loginUser.getEnterpriseId(), purchaseCheck.getBaseOrderId());

        Date receiveDate = purchaseReceive.getReceiveDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(receiveDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if (purchaseCheck.getCheckDate().before(calendar.getTime())) {
            throw new BusinessException("验收时间不能小于收货时间,否则无法保存");
        }

        //基础单据
        purchaseCheck.setBaseOrderId(purchaseReceive.getId());
        purchaseCheck.setBaseOrderDate(purchaseReceive.getReceiveDate());
        purchaseCheck.setBaseOrderCode(purchaseReceive.getCode());
        purchaseCheck.setBaseOrderType(purchaseReceive.getOrderType());
        //供应商信息
        purchaseCheck.setSupplierId(purchaseReceive.getSupplierId());
        purchaseCheck.setSupplierCode(purchaseReceive.getSupplierCode());
        purchaseCheck.setSupplierName(purchaseReceive.getSupplierName());
        //供应商销售人员信息
        purchaseCheck.setSupplierSalerId(purchaseReceive.getSupplierSalerId());
        purchaseCheck.setSupplierSalerCode(purchaseReceive.getSupplierSalerCode());
        purchaseCheck.setSupplierSalerName(purchaseReceive.getSupplierSalerName());
        purchaseCheck.setSupplierSalerPhone(purchaseReceive.getSupplierSalerPhone());
        //--end
        purchaseCheck.setQuantityTotal(BigDecimal.ZERO);
        purchaseCheck.setVarietiesQuantity(0);
        //验收单号,单据类型
        purchaseCheck.setOrderType(OrderRule.PURCHASE_CHECK.getType());
        purchaseCheck.setStatus(PurchaseStatus.WAIT_STORAGE.getStatus());
        purchaseCheck.setCode(getCode(OrderRule.PURCHASE_CHECK.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
        purchaseCheck.setPurchaseOrderId(purchaseReceive.getBaseOrderId());
        int MainId = purchaseCheckMapper.insertSelective(purchaseCheck);

        int reNum = saveDetial(loginUser, purchaseReceive, purchaseCheck, saveCheckVO.getSaveCheckDetailVO());

        //更新总单品种数量和数量合计
        BigDecimal total = purchaseCheckMapper.selectTotal(loginUser.getEnterpriseId(), purchaseCheck.getId());
        purchaseCheckMapper.updateTotal(loginUser.getEnterpriseId(), purchaseCheck.getId(), total);
        purchaseCheckMapper.updateVarieties(loginUser.getEnterpriseId(), purchaseCheck.getId(), saveCheckVO.getSaveCheckDetailVO().size());

        //修改收货单状态,如果gsp不管控,修改其他信息
        purchaseCheckMapper.updateByReceive(loginUser.getEnterpriseId(), purchaseCheck.getBaseOrderId(), PurchaseStatus.WAIT_STORAGE.getStatus());
        purchaseReceiveOtherMapper.updateStatusFinishedByReceiveId(PurchaseStatus.WAIT_STORAGE.getStatus(),purchaseCheck.getBaseOrderId());
        //修改收货明细单状态为待入库，已清数量为收货数量，未清数量为0
        purchaseCheckMapper.updateByReceiveDetail(loginUser.getEnterpriseId(), purchaseCheck.getBaseOrderId(), PurchaseStatus.WAIT_STORAGE.getStatus());

        //修改订单总单状态,如果gsp不管控,修改其他信息
        purchaseCheckMapper.updateByOrder(loginUser.getEnterpriseId(), purchaseReceive.getBaseOrderId(), PurchaseStatus.WAIT_STORAGE.getStatus());
        purchaseCheckMapper.updateByOrderDetail(loginUser.getEnterpriseId(), purchaseReceive.getBaseOrderId(), PurchaseStatus.WAIT_STORAGE.getStatus());
        purchaseOrderOtherMapper.updateStatusFinishedByOrderId(PurchaseStatus.WAIT_STORAGE.getStatus(),purchaseReceive.getBaseOrderId());
        //调用草稿保存时  删除调用草稿
        if (saveCheckVO.getSaveCheckHeadVO().getRedisKeyValue() != null) {
            removeDraftCach(loginUser.getEnterpriseId(), OrderRule.PURCHASE_CHECK.getCodePrefix(), String.valueOf(saveCheckVO.getSaveCheckHeadVO().getRedisKeyValue()));
        }
        return reNum;
    }

    private Integer saveDetial(UserVO loginUser, PurchaseReceive purchaseReceive, PurchaseCheck purchaseCheck, List<SaveCheckDetailVO> saveCheckDetailVOs) throws Exception, BusinessException {

        //验收明细集合
        List<PurchaseCheckLot> purchaseCheckLots = new ArrayList<>();

        for (SaveCheckDetailVO saveCheckDetailVO : saveCheckDetailVOs) {

            PurchaseCheckDetail purchaseCheckDetail = new PurchaseCheckDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(saveCheckDetailVO, purchaseCheckDetail);
            BigDecimal summationRqNum = BigDecimal.ZERO;
            BigDecimal summationQqNum = BigDecimal.ZERO;
            if (saveCheckDetailVO.getBaseOrderId() != null) {
                //收货明细
                PurchaseReceiveDetail purchaseReceiveDetails = purchaseCheckMapper.selectByReceiveDetail(loginUser.getEnterpriseId(), saveCheckDetailVO.getBaseOrderDtlId());
                //set基础单据信息
                purchaseCheckDetail.setBaseOrderId(purchaseReceiveDetails.getReceiveId());
                purchaseCheckDetail.setBaseOrderDate(purchaseReceiveDetails.getReceiveDate());
                purchaseCheckDetail.setBaseOrderType(purchaseReceiveDetails.getOrderType());
                purchaseCheckDetail.setBaseOrderCode(purchaseReceiveDetails.getReceiveCode());
                purchaseCheckDetail.setBaseOrderDtlId(purchaseReceiveDetails.getId());
                //set商品信息
                purchaseCheckDetail.setGoodsName(purchaseReceiveDetails.getGoodsName());
                purchaseCheckDetail.setGoodsCode(purchaseReceiveDetails.getGoodsCode());
                purchaseCheckDetail.setBarcode(purchaseReceiveDetails.getBarcode());
                purchaseCheckDetail.setGoodsGenericName(purchaseReceiveDetails.getGoodsGenericName());
                purchaseCheckDetail.setDosageId(purchaseReceiveDetails.getDosageId());
                purchaseCheckDetail.setDosageName(purchaseReceiveDetails.getDosageName());
                purchaseCheckDetail.setUnitId(purchaseReceiveDetails.getUnitId());
                purchaseCheckDetail.setUnitName(purchaseReceiveDetails.getUnitName());
                purchaseCheckDetail.setGoodsSpecification(purchaseReceiveDetails.getGoodsSpecification());
                purchaseCheckDetail.setManufacturerId(purchaseReceiveDetails.getManufacturerId());
                purchaseCheckDetail.setManufacturer(purchaseReceiveDetails.getManufacturer());
                purchaseCheckDetail.setGoodsPlace(purchaseReceiveDetails.getGoodsPlace());
                purchaseCheckDetail.setApprovalNumber(purchaseReceiveDetails.getApprovalNumber());
                //set状态
                purchaseCheckDetail.setStatus(PurchaseStatus.WAIT_STORAGE.getStatus());
                //set未清数量为收货数量/以清数量为0
                purchaseCheckDetail.setQuantity(purchaseReceiveDetails.getReceiveQuantity());
                purchaseCheckDetail.setUnclearQuantity(purchaseCheckDetail.getQuantity());
                purchaseCheckDetail.setClearQuantity(BigDecimal.ZERO);
                //set验收单据id/code/类型/日期
                purchaseCheckDetail.setCheckId(purchaseCheck.getId());
                purchaseCheckDetail.setCheckCode(purchaseCheck.getCode());
                purchaseCheckDetail.setOrderType(OrderRule.PURCHASE_RECEIVE.getType());
                purchaseCheckDetail.setCheckDate(purchaseCheck.getCheckDate());

                purchaseCheckDetail.setQualifiedQuantity(saveCheckDetailVO.getQualifiedQuantity());
                purchaseCheckDetail.setUnqualifiedQuantity(saveCheckDetailVO.getUnqualifiedQuantity());
                purchaseCheckDetail.setEnterpriseId(loginUser.getEnterpriseId());
                purchaseCheckDetail.setParentId(loginUser.getParentId());
                UserEnterpriseUtils.setUserCreateOrModify(purchaseCheckDetail, loginUser, true);

                purchaseCheckDetail.setPurchaseOrderId(purchaseReceiveDetails.getBaseOrderId());
                purchaseCheckDetail.setPurchaseOrderDtlId(purchaseReceiveDetails.getBaseOrderDtlId());

                purchaseCheckDetailMapper.insertSelective(purchaseCheckDetail);

                Long detailId = purchaseCheckDetail.getId();
                if (saveCheckDetailVO.getSaveCheckDetailTwoVO().size() > 0) {
                    List<SaveCheckDetailTwoVO> saveCheckDetailTwoVOs = saveCheckDetailVO.getSaveCheckDetailTwoVO();
                    for (int j = 0; j < saveCheckDetailTwoVOs.size(); j++) {

                        if (saveCheckDetailTwoVOs.get(j).getReceiveQuantity().compareTo(BigDecimal.ZERO) < 0) {
                            throw new BusinessException("收货数量必须大于等于0");
                        }
                        if (saveCheckDetailTwoVOs.get(j).getSamplingQuantity().compareTo(BigDecimal.ZERO) < 0) {
                            throw new BusinessException("抽样数量必须大于等于0");
                        }
                        if (saveCheckDetailTwoVOs.get(j).getQualifiedQuantity().compareTo(BigDecimal.ZERO) < 0) {
                            throw new BusinessException("验收合格数量必须大于等于0");
                        }
                        if (saveCheckDetailTwoVOs.get(j).getUnqualifiedQuantity().compareTo(BigDecimal.ZERO) < 0) {
                            throw new BusinessException("验收不合格数量必须大于等于0");
                        }


                        PurchaseCheckLot purchaseCheckLot = new PurchaseCheckLot();
                        //验收单，明细单ID
                        purchaseCheckLot.setCheckDtlId(detailId);
                        purchaseCheckLot.setCheckId(purchaseCheck.getId());
                        purchaseCheckLot.setCheckProjectIds(saveCheckDetailTwoVOs.get(j).getCheckProjectIds());
                        //
                        purchaseCheckLot.setGoodsId(purchaseCheckDetail.getGoodsId());
                        purchaseCheckLot.setLotNumber(saveCheckDetailTwoVOs.get(j).getLotNumber());
                        purchaseCheckLot.setProductDate(saveCheckDetailTwoVOs.get(j).getProductDate());
                        purchaseCheckLot.setValidDate(saveCheckDetailTwoVOs.get(j).getValidDate());
                        purchaseCheckLot.setTestReportIds(saveCheckDetailTwoVOs.get(j).getTestReportIds());
                        purchaseCheckLot.setReceiveQuantity(saveCheckDetailTwoVOs.get(j).getReceiveQuantity());
                        purchaseCheckLot.setSamplingQuantity(saveCheckDetailTwoVOs.get(j).getSamplingQuantity());
                        purchaseCheckLot.setQualifiedQuantity(saveCheckDetailTwoVOs.get(j).getQualifiedQuantity());
                        purchaseCheckLot.setConclusionIds(String.valueOf(saveCheckDetailTwoVOs.get(j).getConclusionIds()));
                        purchaseCheckLot.setUnqualifiedReasonIds(saveCheckDetailTwoVOs.get(j).getUnqualifiedReasonIds());
                        purchaseCheckLot.setUnqualifiedQuantity(saveCheckDetailTwoVOs.get(j).getUnqualifiedQuantity());
                        purchaseCheckLot.setMeasuresIds(saveCheckDetailTwoVOs.get(j).getMeasuresIds());
                        purchaseCheckLot.setLineNum(saveCheckDetailTwoVOs.get(j).getLineNum());
                        purchaseCheckLot.setRemark(saveCheckDetailTwoVOs.get(j).getRemark());
                        //状态
                        purchaseCheckLot.setStatus(PurchaseStatus.WAIT_STORAGE.getStatus());
                        purchaseCheckLot.setOrderType(OrderRule.PURCHASE_CHECK.getType());
                        //set未清数量/以清数量
                        purchaseCheckLot.setUnclearQuantity(saveCheckDetailTwoVOs.get(j).getReceiveQuantity());
                        purchaseCheckLot.setClearQuantity(BigDecimal.ZERO);

                        if (saveCheckDetailTwoVOs.get(j).getConclusionIds() == null) {
                            throw new BusinessException("验收结论必须填写,否则无法保存");
                        }
                        if (saveCheckDetailTwoVOs.get(j).getCheckProjectIds() == null) {
                            throw new BusinessException("检验项目必须填写,否则无法保存");
                        }

                        if (purchaseCheckLot.getValidDate().before(purchaseCheckLot.getProductDate())) {
                            throw new BusinessException("有效期必须大于生产日期,否则无法保存");
                        }

                        int num = purchaseCheckLot.getReceiveQuantity().compareTo(purchaseCheckLot.getSamplingQuantity()); //和0，Zero比较
                        if (num < 0) {
                            throw new BusinessException("抽样数量不能大于收货数量,否则无法保存");
                        }

                        int numTwo = purchaseCheckLot.getUnqualifiedQuantity().compareTo(BigDecimal.ZERO); //和0，Zero比较
                        if (numTwo > 0 && (saveCheckDetailTwoVOs.get(j).getUnqualifiedReasonIds() == null
                                || saveCheckDetailTwoVOs.get(j).getMeasuresIds() == null || saveCheckDetailTwoVOs.get(j).getUnqualifiedReasonIds().equals("")
                                || saveCheckDetailTwoVOs.get(j).getMeasuresIds().equals(""))) {
                            throw new BusinessException("存在验收不合格数量,必须填写不合格原因和处置措施,否则无法保存");
                        }

                        summationRqNum = summationRqNum.add(purchaseCheckLot.getReceiveQuantity());
                        summationQqNum = summationQqNum.add(purchaseCheckLot.getQualifiedQuantity());
                        int numThree = summationRqNum.compareTo(purchaseCheckDetail.getQuantity());
                        if (numThree > 0) {
                            throw new BusinessException("该验收单中的收货数量超出收货单中的收货数量,无法保存");
                        }
                        int numFore = summationQqNum.compareTo(purchaseCheckDetail.getQuantity());
                        if (numFore > 0) {
                            throw new BusinessException("该验收单中的验收合格数量超出收货单中的收货数量,无法保存");
                        }

                        if (saveCheckDetailTwoVOs.get(j).getReceiveQuantity() == null) {//验收数量不能为空
                            throw new BusinessException("验收数量不能为空");
                        }
                        if (saveCheckDetailTwoVOs.get(j).getQualifiedQuantity() == null) {//验收合格数量不能为空
                            throw new BusinessException("验收合格数量不能为空");
                        }
                        //如果验收数量小于验收合格数量
                        if (saveCheckDetailTwoVOs.get(j).getReceiveQuantity().compareTo(saveCheckDetailTwoVOs.get(j).getQualifiedQuantity()) == -1) {
                            throw new BusinessException("验收合格数量不能大于验收数量");
                        }
                        purchaseCheckLot.setEnterpriseId(loginUser.getEnterpriseId());
                        purchaseCheckLot.setParentId(loginUser.getParentId());
                        UserEnterpriseUtils.setUserCreateOrModify(purchaseCheckLot, loginUser, true);
                        purchaseCheckLots.add(purchaseCheckLot);

                    }
                } else {
                    throw new BusinessException("没有得到批号的相关数据,无法保存");
                }
            } else {
                throw new BusinessException("没有得到关联基础数据,无法保存");
            }
        }
        purchaseCheckLotMapper.batchInsert(purchaseCheckLots);
        return 1;
    }

    @Override
    public PurchaseCheckRequestVO getCheckDetails(UserVO loginUser, Long id) throws Exception {
        List<PurchaseCheckLot> purchaseCheckLots = purchaseCheckLotMapper.selectByEnterpriseIdByCheckId(loginUser.getEnterpriseId(), id);

        List<CheckDeatilsVO> checkDeatilsVOs = new ArrayList<>();
        int i = 0;
        for (PurchaseCheckLot PurchaseCheckLot : purchaseCheckLots) {
            CheckDeatilsVO checkDeatilsVO = new CheckDeatilsVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(PurchaseCheckLot, checkDeatilsVO);
            //检验项目
            checkDeatilsVO.setCheckProjectNames(getRefuseReasons(checkDeatilsVO.getCheckProjectIds(), loginUser, 5));
            //验收结论
            checkDeatilsVO.setConclusionIds(getRefuseReasons(checkDeatilsVO.getConclusionIds(), loginUser, 6));
            //不合格原因
            checkDeatilsVO.setUnqualifiedReasonIds(getRefuseReasons(checkDeatilsVO.getUnqualifiedReasonIds(), loginUser, 1));
            //处置措施
            checkDeatilsVO.setMeasuresIds(getRefuseReasons(checkDeatilsVO.getMeasuresIds(), loginUser, 3));
            Long goodsid = purchaseCheckLots.get(i).getGoodsId();
            PurchaseCheckDetail purchaseCheckDetail = purchaseCheckDetailMapper.selectByGoodsid(loginUser.getEnterpriseId(), goodsid, id);
            checkDeatilsVO.setGenericName(purchaseCheckDetail.getGoodsGenericName());
            checkDeatilsVO.setCode(purchaseCheckDetail.getCheckCode());
            checkDeatilsVO.setDosageName(purchaseCheckDetail.getDosageName());
            checkDeatilsVO.setSpecification(purchaseCheckDetail.getGoodsSpecification());
            checkDeatilsVO.setUnitName(purchaseCheckDetail.getUnitName());
            checkDeatilsVO.setManufacturer(purchaseCheckDetail.getManufacturer());
            checkDeatilsVO.setReceiveQuantityMain(purchaseCheckDetail.getQuantity());
            checkDeatilsVO.setUnqualifiedQuantityMain(purchaseCheckDetail.getUnqualifiedQuantity());
            checkDeatilsVO.setQualifiedQuantityMain(purchaseCheckDetail.getQualifiedQuantity());
            checkDeatilsVOs.add(checkDeatilsVO);
        }
        PurchaseCheckRequestVO purchaseReceiveRequestVO = new PurchaseCheckRequestVO();
        purchaseReceiveRequestVO.setCheckDeatilsVO(checkDeatilsVOs);
        CheckHeadVO checkHeadVO = new CheckHeadVO();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchaseCheckMapper.selectByPrimaryKey(id), checkHeadVO);
        purchaseReceiveRequestVO.setCheckHeadVO(checkHeadVO);
        return purchaseReceiveRequestVO;
    }


    //根据拒收原因id查找拒收原因
    public String getRefuseReasons(String refuseReasonIds, UserVO loginUser, int setType) throws Exception {
        String refuseReasons = "";
        List<QualitySetVO> qualitySetVOS = qualitySetMapper.getQualitySetVOList(loginUser.getEnterpriseId(), setType, null, 1);
        if (refuseReasonIds == null || "".equals(refuseReasonIds))
            refuseReasons = "";
        else if (!refuseReasonIds.contains(",")) {
            for (QualitySetVO qualitySetVO : qualitySetVOS) {
                if (qualitySetVO.getId() == (Integer.parseInt(refuseReasonIds)))
                    refuseReasons = qualitySetVO.getDescription();
            }
        } else {
            String[] ids = refuseReasonIds.split(",");
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < ids.length; i++) {
                for (QualitySetVO qualitySetVO : qualitySetVOS) {
                    if (qualitySetVO.getId() == (Integer.parseInt(ids[i]))) {
                        result.append(qualitySetVO.getDescription());
                        if (i != ids.length - 1)
                            result.append(",");
                    }
                }
            }
            refuseReasons = result.toString();
        }
        return refuseReasons;
    }

    @Override
    public void exportExcel(OutputStream output, PurchaseCheckRequestVO purchaseCheckRequestVO, UserVO loginUser) {
        //转换一下显示日期
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("code", "商品编码");
        map.put("genericName", "通用名称");
        map.put("dosageName", "剂型");
        map.put("specification", "规格");
        map.put("manufacturer", "生产厂商");
        map.put("unitName", "单位");
        map.put("receiveQuantityMain", "收货数量");
        map.put("qualifiedQuantityMain", "验收合格数量");
        map.put("unqualifiedQuantityMain", "验收不合格数量");
        map.put("lotNumber", "批号");
        map.put("productDate", "生产日期");
        map.put("validDate", "有效期至");
        map.put("receiveQuantity", "收货数量");
        map.put("samplingQuantity", "抽样数量");
        map.put("checkProjectNames", "检验项目");
        map.put("qualifiedQuantity", "验收合格数量");
        map.put("conclusionIds", "验收结论");
        map.put("unqualifiedQuantity", "验收不合格数量");
        map.put("unqualifiedReasonIds", "不合格原因");
        map.put("measuresIds", "处置措施");
        map.put("remark", "备注");
        List<String> titleSecond = new ArrayList<>();
        //标题栏下第一行
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("供货单位编码:");
        titleSecondRow.append(purchaseCheckRequestVO.getCheckHeadVO().getSupplierCode() == null ? "" : purchaseCheckRequestVO.getCheckHeadVO().getSupplierCode());
        titleSecondRow.append("  供货单位名称:");
        titleSecondRow.append(purchaseCheckRequestVO.getCheckHeadVO().getSupplierName() == null ? "" : purchaseCheckRequestVO.getCheckHeadVO().getSupplierName());
        titleSecondRow.append("  供货单位销售人员:");
        titleSecondRow.append(purchaseCheckRequestVO.getCheckHeadVO().getSupplierSalerName() == null ? "" : purchaseCheckRequestVO.getCheckHeadVO().getSupplierSalerName());
        titleSecondRow.append("  联系电话:");
        titleSecondRow.append(purchaseCheckRequestVO.getCheckHeadVO().getSupplierSalerPhone() == null ? "" : purchaseCheckRequestVO.getCheckHeadVO().getSupplierSalerPhone());
        titleSecond.add(titleSecondRow.toString());
        //标题栏下第二行
        titleSecondRow = new StringBuilder();
        titleSecondRow.append("  验收单号:");
        titleSecondRow.append(purchaseCheckRequestVO.getCheckHeadVO().getCode() == null ? "" : purchaseCheckRequestVO.getCheckHeadVO().getCode());
        titleSecondRow.append("  验收日期:");
        titleSecondRow.append(purchaseCheckRequestVO.getCheckHeadVO().getCheckDate() == null ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(purchaseCheckRequestVO.getCheckHeadVO().getCheckDate()));
        titleSecondRow.append("  验收人员1:");
        titleSecondRow.append(purchaseCheckRequestVO.getCheckHeadVO().getCheckerName() == null ? "" : purchaseCheckRequestVO.getCheckHeadVO().getCheckerName());
        titleSecondRow.append("  验收人员2:");
        titleSecondRow.append(purchaseCheckRequestVO.getCheckHeadVO().getSecondCheckerName() == null ? "" : purchaseCheckRequestVO.getCheckHeadVO().getSecondCheckerName());
        titleSecond.add(titleSecondRow.toString());
        StringBuilder end = new StringBuilder();

        //总数量
        BigDecimal receiveQuantityMain = BigDecimal.ZERO;//收货数量
        BigDecimal qualifiedQuantityMain = BigDecimal.ZERO;//验收合格数量
        BigDecimal unqualifiedQuantityMain = BigDecimal.ZERO;//验收不合格数量
        //分批号数量
        BigDecimal receiveQuantity = BigDecimal.ZERO;//收货数量
        BigDecimal qualifiedQuantity = BigDecimal.ZERO;//验收合格数量
        BigDecimal unqualifiedQuantity = BigDecimal.ZERO;//验收不合格数量
        BigDecimal samplingQuantity = BigDecimal.ZERO;//抽样数量

        for (CheckDeatilsVO checkDeatilsVO : purchaseCheckRequestVO.getCheckDeatilsVO()) {
            receiveQuantity = receiveQuantity.add(checkDeatilsVO.getReceiveQuantity());
            qualifiedQuantity = qualifiedQuantity.add(checkDeatilsVO.getQualifiedQuantity());
            unqualifiedQuantity = unqualifiedQuantity.add(checkDeatilsVO.getUnqualifiedQuantity());
            samplingQuantity = samplingQuantity.add(checkDeatilsVO.getSamplingQuantity());
            receiveQuantityMain = receiveQuantityMain.add(checkDeatilsVO.getReceiveQuantity());
            qualifiedQuantityMain = qualifiedQuantityMain.add(checkDeatilsVO.getQualifiedQuantity());
            unqualifiedQuantityMain = unqualifiedQuantityMain.add(checkDeatilsVO.getUnqualifiedQuantity());
        }
        end.append(receiveQuantityMain);
        end.append(";");
        end.append(qualifiedQuantityMain);
        end.append(";");
        end.append(unqualifiedQuantityMain);
        end.append(";");
        end.append(receiveQuantity);
        end.append(";");
        end.append(samplingQuantity);
        end.append(";");
        end.append(qualifiedQuantity);
        end.append(";");
        end.append(unqualifiedQuantity);

        List<String> needTotalName = new ArrayList<>();
        needTotalName.add("receiveQuantityMain");
        needTotalName.add("qualifiedQuantityMain");
        needTotalName.add("unqualifiedQuantityMain");
        needTotalName.add("receiveQuantity");
        needTotalName.add("samplingQuantity");
        needTotalName.add("qualifiedQuantity");
        needTotalName.add("unqualifiedQuantity");

        List<String> name = new ArrayList<>();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(purchaseCheckMapper.selectByPrimaryKey(purchaseCheckRequestVO.getCheckHeadVO().getId()).getEnterpriseId());
        name.add(enterprise.getName());
        name.add("采购验收单");
        purchaseGeneralComponent.commExcelExport(output, map, purchaseCheckRequestVO.getCheckDeatilsVO(), name, titleSecond, end.toString(), false, needTotalName);
    }

    @Override
    public DraftCacheVO<CheckHeadVO> saveDraftCache(UserVO userVO, DraftCacheVO<CheckHeadVO> draftCache) throws Exception {

        if (draftCache.getBaseOrderId() == null) {
            throw new BusinessException("保存采购验收草稿时,上级单据ID没有传输!");
        }
        String redisKeyValue = draftCache.getId();

        DraftCacheVO<CheckHeadVO> draftCacheVO = new DraftCacheVO();

        draftCacheVO.setOrderCode(OrderRule.PURCHASE_CHECK.getCodePrefix());

        draftCacheVO.setEnterpriseId(userVO.getEnterpriseId());

        CheckHeadVO checkHeadVO = draftCache.getOrderData();
        if (checkHeadVO.getSupplierId() == null) {
            throw new BusinessException("采购验收草稿保存总单时必传字段：SupplierId 缺失!");
        } else if (checkHeadVO.getId() == null) {
            throw new BusinessException("采购验收草稿保存总单时必传字段：Id 缺失!");
        } else if (checkHeadVO.getCode() == null) {
            throw new BusinessException("采购验收草稿保存总单时必传字段：Code 缺失!");
        } else if (checkHeadVO.getBaseOrderId() == null) {
            throw new BusinessException("采购验收草稿保存总单时必传字段： BaseOrderId 缺失!");
        } else if (checkHeadVO.getBaseOrderDate() == null) {
            throw new BusinessException("采购验收草稿保存总单时必传字段：BaseOrderDate 缺失!");
        }

        if (checkHeadVO.getCheckDeatilVO().isEmpty()) {
            throw new BusinessException("采购验收草稿保存缺失货品明细信息数据!");
        }

        for (CheckDeatilVO checkDeatilVO : checkHeadVO.getCheckDeatilVO()) {
            if (checkDeatilVO.getGoodsId() == null) {
                throw new BusinessException("采购验收草稿保存货品明细时必传字段：GoodsId 缺失!");
            } else if (checkDeatilVO.getQuantity() == null) {
                throw new BusinessException("采购验收草稿保存货品明细时必传字段：Quantity 缺失!");
            } else if (checkDeatilVO.getQualifiedQuantity() == null) {
                throw new BusinessException("采购验收草稿保存货品明细时必传字段：QualifiedQuantity 缺失!");
            } else if (checkDeatilVO.getUnqualifiedQuantity() == null) {
                throw new BusinessException("采购验收草稿保存货品明细时必传字段：UnqualifiedQuantity 缺失!");
            } else {
                if (checkDeatilVO.getSaveCheckDetailTwoVO().isEmpty()) {
                    throw new BusinessException("采购验收草稿保存缺失货位明细信息数据!");
                }
                for (SaveCheckDetailTwoVO saveCheckDetailTwoVO : checkDeatilVO.getSaveCheckDetailTwoVO()) {
                    if (saveCheckDetailTwoVO.getReceiveQuantity() == null) {
                        throw new BusinessException("采购验收草稿保存货位明细时必传字段：ReceiveQuantity 缺失!");
                    } else if (saveCheckDetailTwoVO.getSamplingQuantity() == null) {
                        throw new BusinessException("采购验收草稿保存货位明细时必传字段：SamplingQuantity 缺失!");
                    } else if (saveCheckDetailTwoVO.getQualifiedQuantity() == null) {
                        throw new BusinessException("采购验收草稿保存货位明细时必传字段：QualifiedQuantity 缺失!");
                    } else if (saveCheckDetailTwoVO.getUnqualifiedQuantity() == null) {
                        throw new BusinessException("采购验收草稿保存货位明细时必传字段：UnqualifiedQuantity 缺失!");
                    }
                }
            }
        }


        draftCacheVO.setOrderData(draftCache.getOrderData());
        draftCacheVO.setId(redisKeyValue);
        draftCacheVO.setBaseOrderId(draftCache.getBaseOrderId());
        redisComponent.saveDraftCacheVO(draftCacheVO);
        return draftCacheVO;

    }

    @Override
    public void removeDraftCach(Long enterpriseId, String type, String redisKeyValue) {

        redisComponent.removeDraftCacheVO(enterpriseId, type, redisKeyValue);
    }

    @Override
    public DraftCacheVO getDraftCacheVO(UserVO userVO, Long baseOrderId) {
        Class<CheckHeadVO> clazz = CheckHeadVO.class;
        return redisComponent.getDraftCacheVO(userVO.getEnterpriseId(), baseOrderId, OrderRule.PURCHASE_CHECK.getCodePrefix(), clazz);
    }

}
