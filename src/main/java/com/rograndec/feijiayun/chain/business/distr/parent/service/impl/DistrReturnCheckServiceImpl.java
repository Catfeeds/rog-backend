package com.rograndec.feijiayun.chain.business.distr.parent.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnCheckDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnCheckLotMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnCheckMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.*;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrReturnCheckService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.*;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.purchase.check.vo.TestReportIdsVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.SpecialDrugs;
import com.rograndec.feijiayun.chain.common.constant.status.PubStatus;
import com.rograndec.feijiayun.chain.common.file.dao.FileMapper;
import com.rograndec.feijiayun.chain.common.file.entity.File;
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
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class DistrReturnCheckServiceImpl implements DistrReturnCheckService {

    @Autowired
    DistrReturnCheckMapper distrReturnCheckMapper;
    @Autowired
    DistrReturnCheckDetailMapper distrReturnCheckDetailMapper;
    @Autowired
    DistrReturnCheckLotMapper distrReturnCheckLotMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderCodeComponent orderCodeComponent;
    @Autowired
    QualitySetMapper qualitySetMapper;
    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    PurchaseGeneralComponent<ExportDistrReturnCheckDtlVO> purchaseGeneralComponent;
    @Autowired
    FileMapper fileMapper;

    @Autowired
    private CommonComponent commonComponent;

    @Override
    public ClickCheckHeadVO getClickCheckHead(Long enterpriseId, Long id, UserVO userVO) throws Exception {

        ClickCheckHeadVO clickCheckHeadVO = distrReturnCheckMapper.selectByReceiveHead(enterpriseId, id);
        if(null == clickCheckHeadVO){
            return new ClickCheckHeadVO();
        }

        clickCheckHeadVO.setCheckDate(new Date());
        clickCheckHeadVO.setCheckerId(userVO.getUserId());
        clickCheckHeadVO.setCheckerName(userVO.getUserName());
        int distrType = clickCheckHeadVO.getDistrType();
        clickCheckHeadVO.setDistrTypeName(DistrType.getValue(distrType));

        List<ClickCheckDetailVO> clickCheckDetailVOS = ClickCheckDetail(enterpriseId, id);

        Boolean gspFlag = commonComponent.getGspFlag(userVO);

        for(ClickCheckDetailVO clickCheckDetailVO : clickCheckDetailVOS){
            if(null != clickCheckDetailVO.getSpecialDrug() && SpecialDrugs.getSpecialDrugsCodes().contains(clickCheckDetailVO.getSpecialDrug())){
                clickCheckHeadVO.setSpecialDrug(1);
            }

            if(null != clickCheckDetailVO.getSpecialDrug() && gspFlag && SpecialDrugs.getSpecialDrugsCodes().contains(clickCheckDetailVO.getSpecialDrug())){
                clickCheckHeadVO.setSecondCheckerId(userVO.getUserId());
                clickCheckHeadVO.setSecondCheckerName(userVO.getUserName());
            }
        }

        return clickCheckHeadVO == null ? new ClickCheckHeadVO() : clickCheckHeadVO;
    }

    @Override
    public List<ClickCheckDetailVO> ClickCheckDetail(Long enterpriseId, Long id) {

        List<ClickCheckDetailVO> clickCheckDetailVO = distrReturnCheckMapper.selectByReceiveDetail(enterpriseId, id);
        for (int i = 0; i < clickCheckDetailVO.size(); i++) {
            Long goodsid = clickCheckDetailVO.get(i).getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsid);
            if (goods.getSpecialDrug() == -1 || goods.getSpecialDrug() == null) {
                clickCheckDetailVO.get(i).setSpecialDrug(1);
            } else {
                clickCheckDetailVO.get(i).setSpecialDrug(0);  //是特殊药品
            }
            //查询分店-配进入库-配进退出出库货位明细   通过配后退回通知单ID
            Long baseOrderDtlId = clickCheckDetailVO.get(i).getBaseOrderDtlId();
            List<ClickCheckDetailOneVO> clickCheckDetailOneVO = distrReturnCheckLotMapper.selectByBranchDistrInReturnOutShelf(enterpriseId, baseOrderDtlId);
            clickCheckDetailVO.get(i).setClickCheckDetailOneVO(clickCheckDetailOneVO);
        }

        return clickCheckDetailVO == null ? new ArrayList<>() : clickCheckDetailVO;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Throwable.class)
    public Result<Map<String,Object>> saveDistrReturnCheck(UserVO loginUser, SaveDistrReturnCheckVO saveDistrReturnCheckVO) throws Exception, BusinessException {

        return commonComponent.generateAndSaveDistrReturnCheck(loginUser,saveDistrReturnCheckVO);

    }

    @Override
    public List<DistrReturnCheckVO> getDistrReturnCheckPage(int pageNo, int pageSize, Long enterpriseId, Page page, Date startTime, Date endTime, String requestUnitCode, String requestUnitName, String code, Integer distrType, String checkerName, String secondCheckerName, String orderName, String orderType, Long type) {

        Long totalRecord;
        List<DistrReturnCheckVO> list = null;
        if (orderName != null && orderName.equals("checkDate"))
            orderName = "check_date";
        else {
           /* orderName = "check_date";
            orderType = "desc";*/
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
            totalRecord = distrReturnCheckMapper.queryCountByDistrReturnCheckParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes, requestUnitCode, requestUnitName, code, distrType,
                    checkerName, secondCheckerName);
            list = distrReturnCheckMapper.selectByDistrReturnCheckParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes, requestUnitCode, requestUnitName, code, distrType,
                    checkerName, secondCheckerName, orderName, orderType);
            for (int i = 0; i < list.size(); i++) {
                String statusName = PubStatus.distrReturnNoticeStatus.getStatusDesc(Integer.valueOf(list.get(i).getStatus()));
                list.get(i).setStatus(statusName);
                int distrTypes = list.get(i).getDistrType();
                list.get(i).setDistrTypeName(DistrType.getValue(distrTypes));
                Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(list.get(i).getRequestUnitId());
                if(enterprise.getChainType() == ChainType.Division.getCode()){
                    list.get(i).setFranchisedStoreFlag(1);
                }else{
                    list.get(i).setFranchisedStoreFlag(0);
                }
            }
        }else{
            totalRecord = distrReturnCheckMapper.queryCountByDistrReturnCheckOtherParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes, requestUnitCode, requestUnitName, code, distrType,
                    checkerName, secondCheckerName, PubStatus.distrReturnNoticeStatus.WAIT_IN);
            list = distrReturnCheckMapper.selectByDistrReturnCheckOtherParams(enterpriseId, page.getStart(), pageSize, startTimes, endTimes, requestUnitCode, requestUnitName, code, distrType,
                    checkerName, secondCheckerName, orderName, orderType, PubStatus.distrReturnNoticeStatus.WAIT_IN);
            for (int i = 0; i < list.size(); i++) {
                String statusName = PubStatus.distrReturnNoticeStatus.getStatusDesc(Integer.valueOf(list.get(i).getStatus()));
                list.get(i).setStatus(statusName);
                int distrTypes = list.get(i).getDistrType();
                list.get(i).setDistrTypeName(DistrType.getValue(distrTypes));
                Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(list.get(i).getRequestUnitId());
                if(enterprise.getChainType() == ChainType.Division.getCode()){
                    list.get(i).setFranchisedStoreFlag(1);
                }else{
                    list.get(i).setFranchisedStoreFlag(0);
                }
            }
        }


        page.setTotalRecord(totalRecord == null ? 0 : totalRecord.intValue());
        return list;
    }

    @Override
    public DistrReturnCheckHeadVO getDistrReturnCheckHead(Long enterpriseId, Long id, UserVO userVO) throws Exception{

        DistrReturnCheckHeadVO distrReturnCheckHeadVo = distrReturnCheckMapper.selectByIdAndEnterpriseId(id, enterpriseId);
        int distrTypes = distrReturnCheckHeadVo.getDistrType();
        distrReturnCheckHeadVo.setDistrTypeName(DistrType.getValue(distrTypes));
        /**
         * 打印接口需要的参数
         */
        distrReturnCheckHeadVo.setEnterpriseName(userVO.getEnterpriseName());
        List<DistrReturnCheckDetailVO> distrReturnCheckDetail = getDistrReturnCheckDetail(enterpriseId, id, userVO);
        BigDecimal receiveQuantityTotal = BigDecimal.ZERO;
        BigDecimal qualifiedQuantityTotal = BigDecimal.ZERO;
        BigDecimal unqualifiedQuantityTotal = BigDecimal.ZERO;
        for (DistrReturnCheckDetailVO d : distrReturnCheckDetail) {
            receiveQuantityTotal = receiveQuantityTotal.add(d.getReceiveQuantity());
            qualifiedQuantityTotal = qualifiedQuantityTotal.add(d.getQualifiedQuantity());
            unqualifiedQuantityTotal = unqualifiedQuantityTotal.add(d.getUnqualifiedQuantity());
        }
        distrReturnCheckHeadVo.setReceiveQuantityTotal(receiveQuantityTotal);
        distrReturnCheckHeadVo.setQualifiedQuantityTotal(qualifiedQuantityTotal);
        distrReturnCheckHeadVo.setUnqualifiedQuantityTotal(unqualifiedQuantityTotal);
        return distrReturnCheckHeadVo == null ? new DistrReturnCheckHeadVO() : distrReturnCheckHeadVo;
    }

    @Override
    public List<DistrReturnCheckDetailVO> getDistrReturnCheckDetail(Long enterpriseId, Long id, UserVO loginUser) throws Exception{

        List<DistrReturnCheckDetailVO> distrReturnCheckDetailVo = distrReturnCheckDetailMapper.selectByCheckIdAndEnterpriseId(id, enterpriseId);
        for(int i = 0; i < distrReturnCheckDetailVo.size(); i++){
            List<DistrReturnCheckDetailOneVO> distrReturnCheckDetailOneVo = distrReturnCheckLotMapper.selectByCheckIdAndEnterpriseId(id, distrReturnCheckDetailVo.get(i).getId(), enterpriseId);

            Goods goods = goodsMapper.selectByPrimaryKey(distrReturnCheckDetailVo.get(i).getGoodsId());
            if(goods == null){
                throw new BusinessException("无效的商品ID："+ distrReturnCheckDetailVo.get(i).getGoodsId());
            }
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(goods.getEnterpriseId());
            if(enterprise.getChainType() == ChainType.Division.getCode()){
                distrReturnCheckDetailVo.get(i).setFranchisedStoreFlag(1);
            }else{
                distrReturnCheckDetailVo.get(i).setFranchisedStoreFlag(0);
            }

            for(int j =0; j < distrReturnCheckDetailOneVo.size(); j++){
                //检验项目
                distrReturnCheckDetailOneVo.get(j).setCheckProjectIds(getRefuseReasons(distrReturnCheckDetailOneVo.get(j).getCheckProjectIds(),loginUser,5));
                //验收结论
                distrReturnCheckDetailOneVo.get(j).setConclusionIds(getRefuseReasons(distrReturnCheckDetailOneVo.get(j).getConclusionIds(),loginUser,6));
                //不合格原因
                distrReturnCheckDetailOneVo.get(j).setUnqualifiedReasonIds(getRefuseReasons(distrReturnCheckDetailOneVo.get(j).getUnqualifiedReasonIds(),loginUser,1));
                //处置措施
                distrReturnCheckDetailOneVo.get(j).setMeasuresIds(getRefuseReasons(distrReturnCheckDetailOneVo.get(j).getMeasuresIds(),loginUser,3));

                //附件信息集合
                String testReportIds = distrReturnCheckDetailOneVo.get(j).getTestReportIds();
                if(testReportIds != null){
                    if(!(testReportIds.trim().isEmpty())){
                        List<TestReportIdsVO> TestReportIdsVO = getTestReportIdsNames(testReportIds,loginUser);
                        distrReturnCheckDetailOneVo.get(j).setTestReportIdsVO(TestReportIdsVO);
                    }
                }

            }
            distrReturnCheckDetailVo.get(i).setDistrReturnCheckDetailOneVO(distrReturnCheckDetailOneVo);
        }
        return distrReturnCheckDetailVo == null ? new ArrayList<>() : distrReturnCheckDetailVo;
    }

    private List<TestReportIdsVO> getTestReportIdsNames(String testReportIds, UserVO loginUser) throws Exception {

        List<TestReportIdsVO> testReportIdsVOs = new ArrayList<>();
        String[] idNames = testReportIds.split(",");
        if(idNames.length > 0){
            for(int i = 0; i < idNames.length; i++){
                TestReportIdsVO testReportIdsVO = new TestReportIdsVO();
                String[] ids = idNames[i].split(":");
                if(!ids[1].equals("null")){
                    File file = fileMapper.selectByPrimaryKey(Long.valueOf(ids[1]));
                    testReportIdsVO.setFileId(file.getId());
                    testReportIdsVO.setFileName(file.getFileName());
                }
                testReportIdsVO.setCheckProjectId(Long.valueOf(ids[0]));
                String checkProjectName = getRefuseReasons(ids[0], loginUser, 5);
                testReportIdsVO.setCheckProjectName(checkProjectName);
                testReportIdsVOs.add(testReportIdsVO);
            }
        }
        return testReportIdsVOs;
    }

    @Override
    public ExportDistrReturnCheckVO exportDistrReturnCheck(UserVO loginUser, Long id) throws Exception{
        ExportDistrReturnCheckVO exportDistrReturnCheckVO = new ExportDistrReturnCheckVO();
        List<ExportDistrReturnCheckDtlVO> exportDistrReturnCheckDtlVo = distrReturnCheckLotMapper.selectByCheckIdAndEnterpriseIdList(loginUser.getEnterpriseId(), id);
        for(int j =0; j < exportDistrReturnCheckDtlVo.size(); j++){
            //检验项目
            exportDistrReturnCheckDtlVo.get(j).setCheckProjectIds(getRefuseReasons(exportDistrReturnCheckDtlVo.get(j).getCheckProjectIds(),loginUser,5));
            //验收结论
            exportDistrReturnCheckDtlVo.get(j).setConclusionIds(getRefuseReasons(exportDistrReturnCheckDtlVo.get(j).getConclusionIds(),loginUser,6));
            //不合格原因
            exportDistrReturnCheckDtlVo.get(j).setUnqualifiedReasonIds(getRefuseReasons(exportDistrReturnCheckDtlVo.get(j).getUnqualifiedReasonIds(),loginUser,1));
            //处置措施
            exportDistrReturnCheckDtlVo.get(j).setMeasuresIds(getRefuseReasons(exportDistrReturnCheckDtlVo.get(j).getMeasuresIds(),loginUser,3));
        }
        for(int i = 0; i < exportDistrReturnCheckDtlVo.size(); i++){
            DistrReturnCheckDetailVO distrReturnCheckDetailVO = distrReturnCheckDetailMapper.selectByCheckDtlIdAndEnterpriseId(exportDistrReturnCheckDtlVo.get(i).getDtlId(), loginUser.getEnterpriseId());
            exportDistrReturnCheckDtlVo.get(i).setGoodsCode(distrReturnCheckDetailVO.getGoodsCode());
            exportDistrReturnCheckDtlVo.get(i).setGoodsGenericName(distrReturnCheckDetailVO.getGoodsGenericName());
            exportDistrReturnCheckDtlVo.get(i).setGoodsPlace(distrReturnCheckDetailVO.getGoodsPlace());
            exportDistrReturnCheckDtlVo.get(i).setGoodsSpecification(distrReturnCheckDetailVO.getGoodsSpecification());
            exportDistrReturnCheckDtlVo.get(i).setDosageName(distrReturnCheckDetailVO.getDosageName());
            exportDistrReturnCheckDtlVo.get(i).setManufacturer(distrReturnCheckDetailVO.getManufacturer());
            exportDistrReturnCheckDtlVo.get(i).setUnitName(distrReturnCheckDetailVO.getUnitName());
            exportDistrReturnCheckDtlVo.get(i).setReceiveQuantityDtl(distrReturnCheckDetailVO.getReceiveQuantity());
            exportDistrReturnCheckDtlVo.get(i).setQualifiedQuantityDtl(distrReturnCheckDetailVO.getQualifiedQuantity());
            exportDistrReturnCheckDtlVo.get(i).setUnqualifiedQuantityDtl(distrReturnCheckDetailVO.getUnqualifiedQuantity());
        }
        exportDistrReturnCheckVO.setExportDistrReturnCheckDtlVO(exportDistrReturnCheckDtlVo);
        DistrReturnCheckHeadVO distrReturnCheckHeadVO = getDistrReturnCheckHead(loginUser.getEnterpriseId(), id, loginUser);
        exportDistrReturnCheckVO.setDistrReturnCheckHeadVO(distrReturnCheckHeadVO);
        return exportDistrReturnCheckVO;
    }

    @Override
    public void exportExcel(OutputStream output, ExportDistrReturnCheckVO exportDistrReturnCheckVo, UserVO loginUser) {
        //转换一下显示日期
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("goodsCode","商品编码");
        map.put("goodsGenericName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("unitName","单位");
        map.put("manufacturer","生产厂商");
        map.put("goodsPlace","产地");
        map.put("receiveQuantityDtl","收货数量");
        map.put("qualifiedQuantityDtl","验收合格数量");
        map.put("unqualifiedQuantityDtl","验收不合格数量");
        map.put("lotNumber","批号");
        map.put("productDate","生产日期");
        map.put("validDate","有效期至");
        map.put("receiveQuantity","收货数量");
        map.put("samplingQuantity","抽样数量");
        map.put("checkProjectIds","检验项目");
        map.put("qualifiedQuantity","验收合格数量");
        map.put("conclusionIds","验收结论");
        map.put("unqualifiedQuantity","验收不合格数量");
        map.put("unqualifiedReasonIds","不合格原因");
        map.put("measuresIds","处置措施");
        map.put("remark","备注");
        List<String> titleSecond = new ArrayList<>();
        //标题栏下第一行
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("要货单位编码:");
        titleSecondRow.append(exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getRequestUnitCode() == null ? "":exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getRequestUnitCode());
        titleSecondRow.append("  要货单位名称:");
        titleSecondRow.append(exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getRequestUnitName() == null ? "":exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getRequestUnitName());
        titleSecondRow.append("  配后退回验收单号:");
        titleSecondRow.append(exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getCode() == null ? "":exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getCode());
        titleSecondRow.append("  验货日期:");
        titleSecondRow.append(exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getCheckDate() == null ? "":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getCheckDate()));
        titleSecond.add(titleSecondRow.toString());
        //标题栏下第二行
        titleSecondRow = new StringBuilder();
        titleSecondRow.append("  验收人员1:");
        titleSecondRow.append(exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getCheckerName() == null ? "":exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getCheckerName());
        titleSecondRow.append("  验收人员2:");
        titleSecondRow.append(exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getSecondCheckerName() == null ? "":exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getSecondCheckerName());
        titleSecondRow.append("  配货类型:");
        titleSecondRow.append(exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getDistrTypeName() == null ? "":exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getDistrTypeName());
        titleSecondRow.append("  备注:");
        titleSecondRow.append(exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getRemark() == null ? "":exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getRemark());
        titleSecond.add(titleSecondRow.toString());
        StringBuilder end = new StringBuilder();

        //总数量
        BigDecimal receiveQuantityDtl = BigDecimal.ZERO;//收货数量
        BigDecimal qualifiedQuantityDtl = BigDecimal.ZERO;//验收合格数量
        BigDecimal unqualifiedQuantityDtl = BigDecimal.ZERO;//验收不合格数量
        //分批号数量
        BigDecimal receiveQuantity = BigDecimal.ZERO;//收货数量
        BigDecimal qualifiedQuantity = BigDecimal.ZERO;//验收合格数量
        BigDecimal unqualifiedQuantity = BigDecimal.ZERO;//验收不合格数量
        BigDecimal samplingQuantity = BigDecimal.ZERO;//抽样数量

        for (ExportDistrReturnCheckDtlVO exportDistrReturnCheckDtlVO : exportDistrReturnCheckVo.getExportDistrReturnCheckDtlVO()) {
            receiveQuantity = receiveQuantity.add(exportDistrReturnCheckDtlVO.getReceiveQuantity());
            qualifiedQuantity = qualifiedQuantity.add(exportDistrReturnCheckDtlVO.getQualifiedQuantity());
            unqualifiedQuantity = unqualifiedQuantity.add(exportDistrReturnCheckDtlVO.getUnqualifiedQuantity());
            samplingQuantity = samplingQuantity.add(exportDistrReturnCheckDtlVO.getSamplingQuantity());
            receiveQuantityDtl = receiveQuantityDtl.add(exportDistrReturnCheckDtlVO.getReceiveQuantityDtl());
            qualifiedQuantityDtl = qualifiedQuantityDtl.add(exportDistrReturnCheckDtlVO.getQualifiedQuantityDtl());
            unqualifiedQuantityDtl = unqualifiedQuantityDtl.add(exportDistrReturnCheckDtlVO.getUnqualifiedQuantityDtl());
        }
        end.append(receiveQuantityDtl);
        end.append(";");
        end.append(qualifiedQuantityDtl);
        end.append(";");
        end.append(unqualifiedQuantityDtl);
        end.append(";");
        end.append(receiveQuantity);
        end.append(";");
        end.append(samplingQuantity);
        end.append(";");
        end.append(qualifiedQuantity);
        end.append(";");
        end.append(unqualifiedQuantity);

        List<String> needTotalName = new ArrayList<>();
        needTotalName.add("receiveQuantityDtl");
        needTotalName.add("qualifiedQuantityDtl");
        needTotalName.add("unqualifiedQuantityDtl");
        needTotalName.add("receiveQuantity");
        needTotalName.add("samplingQuantity");
        needTotalName.add("qualifiedQuantity");
        needTotalName.add("unqualifiedQuantity");
        List<String> name = new ArrayList<>();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(distrReturnCheckMapper.selectByPrimaryKey(exportDistrReturnCheckVo.getDistrReturnCheckHeadVO().getId()).getEnterpriseId());
        name.add(enterprise.getName());
        name.add("配后退回验收单");
        purchaseGeneralComponent.commExcelExport(output,map,exportDistrReturnCheckVo.getExportDistrReturnCheckDtlVO(),name,titleSecond,end.toString(),false,needTotalName);
    }




    //根据拒收原因id查找拒收原因
    public String getRefuseReasons(String refuseReasonIds, UserVO loginUser, int setType) throws Exception {
        String refuseReasons = "";
        List<QualitySetVO> qualitySetVOS = qualitySetMapper.getQualitySetVOList(loginUser.getEnterpriseId(), setType, null, 1);
        if (refuseReasonIds == null || "".equals(refuseReasonIds))
            refuseReasons =  "";
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
                    if (qualitySetVO.getId() == (Integer.parseInt(ids[i]))){
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
}
