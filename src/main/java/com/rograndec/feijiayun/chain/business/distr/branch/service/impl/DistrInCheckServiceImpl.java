package com.rograndec.feijiayun.chain.business.distr.branch.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.*;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.*;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInCheckService;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.inCheck.*;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrOutShelfMapper;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.ManageConfigComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.SpecialDrugs;
import com.rograndec.feijiayun.chain.common.constant.status.DistrInStatus;
import com.rograndec.feijiayun.chain.common.constant.status.PubStatus;
import com.rograndec.feijiayun.chain.common.file.dao.FileMapper;
import com.rograndec.feijiayun.chain.common.file.entity.File;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.EntityUtils;
import com.rograndec.feijiayun.chain.utils.date.DateStyle;
import com.rograndec.feijiayun.chain.utils.date.DateUtils;
import com.rograndec.feijiayun.chain.utils.excel.ExcelWriter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

/**
 * 功能描述：
 * Created by ST on 2017/10/10 11:37
 */
@Service
public class DistrInCheckServiceImpl implements DistrInCheckService {

    @Autowired
    private DistrInCheckMapper distrInCheckMapper;

    @Autowired
    private DistrInCheckDetailMapper distrInCheckDetailMapper;

    @Autowired
    private DistrInCheckLotMapper distrInCheckLotMapper;

    @Autowired
    private DistrInReceiveDetailMapper distrInReceiveDetailMapper;

    @Autowired
    private DistrInReceiveMapper distrInReceiveMapper;

    @Autowired
    private OrderCodeComponent  orderCodeComponent;

    @Autowired
    private ManageConfigComponent manageConfigComponent;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private QualitySetMapper qualitySetMapper;

    @Autowired
    private DistrInNoticeMapper distrInNoticeMapper;

    @Autowired
    private DistrInNoticeDetailMapper distrInNoticeDetailMapper;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private DistrOutShelfMapper distrOutShelfMapper;

    @Autowired
    private CommonComponent commonComponent;

    /**
     * 查询已验收列表
     *
     * @param page
     * @param param
     * @param enterpriseId
     */
    @Override
    public void getDistrInCheckList(Page<List<DistrInCheck2ListVO>> page, RequestParamForListVO param, Long enterpriseId) {
//        com.github.pagehelper.Page<Object> objects = PageHelper.startPage(page.getStart(), page.getPageSize());
        param.setStart(page.getStart());
        if("".equals(param.getStartDate())){
            param.setStartDate(null);
        }else if (param.getStartDate() != null){
            param.setStartDate(param.getStartDate().trim()+" 00:00:00");
        }
        if("".equals(param.getEndDate())){
            param.setEndDate(null);
        }else if(param.getEndDate() != null){
            param.setStartDate(param.getStartDate().trim()+" 23:59:59");
        }
        Integer count = distrInCheckMapper.getDistrInCheckListCount(param, enterpriseId, new CheckStatusType(1));
        List<DistrInCheck2ListVO> inCheckList = distrInCheckMapper.getDistrInCheckList(param, enterpriseId, new CheckStatusType(1));
        inCheckList.forEach(item->{
            item.setDistrTypeName(DistrType.getValue(item.getDistrType()));
            item.setStatusName(DistrInStatus.getStatusDesc(item.getStatus()));
        });
        page.setResult(inCheckList);
        page.setTotalRecord(count);
    }

    /**
     * 查看验收详情
     *
     * @param userVO
     * @param id
     * @return
     */
    @Override
    public DistrInCheck2DetailVO getInCheckDetail(UserVO userVO, Long id) {
        Long enterpriseId = userVO.getEnterpriseId();
        Long parentId = userVO.getParentId();
        DistrInCheck2DetailVO inCheck2DetailVO = distrInCheckMapper.getDistrInCheckById(enterpriseId, id);
        if(inCheck2DetailVO != null){

            List<QualitySetVO> qualitySetVOList = qualitySetMapper.getQualitySetVOList(parentId, null, null, EnableStatus.ENABLE.getStatus());

            List<DistrInCheckDetail2DetailVO> inCheckDetail2DetailList = distrInCheckDetailMapper.getInCheckDetail2Detail(enterpriseId, id);
            
            BigDecimal receiveQuantityTotal = BigDecimal.ZERO;//收货数量
            BigDecimal samplingQuantityTotal = BigDecimal.ZERO;//抽样数量
            BigDecimal qualifiedQuantityTotal = BigDecimal.ZERO;//验收合格数量
            BigDecimal unqualifiedQuantityTotal = BigDecimal.ZERO;//验收不合格数量
            
            for (DistrInCheckDetail2DetailVO item : inCheckDetail2DetailList) {
                filterPackData1(item.getLot2DetailVOList(), qualitySetVOList);
                for(DistrInCheckLot2DetailVO distrInCheckLot2DetailVO : item.getLot2DetailVOList()){
                    String testReportIds = distrInCheckLot2DetailVO.getTestReportIds();
                    
                    receiveQuantityTotal = receiveQuantityTotal.add(distrInCheckLot2DetailVO.getReceiveQuantity()!=null?
                    		distrInCheckLot2DetailVO.getReceiveQuantity():BigDecimal.ZERO);
                    samplingQuantityTotal = samplingQuantityTotal.add(distrInCheckLot2DetailVO.getSamplingQuantity()!=null?
                    		distrInCheckLot2DetailVO.getSamplingQuantity():BigDecimal.ZERO);
                    qualifiedQuantityTotal = qualifiedQuantityTotal.add(distrInCheckLot2DetailVO.getQualifiedQuantity()!=null?
                    		distrInCheckLot2DetailVO.getQualifiedQuantity():BigDecimal.ZERO);
                    unqualifiedQuantityTotal = unqualifiedQuantityTotal.add(distrInCheckLot2DetailVO.getUnqualifiedQuantity()!=null?
                    		distrInCheckLot2DetailVO.getUnqualifiedQuantity():BigDecimal.ZERO);
                    
                    if(testReportIds != null) {
                        if (testReportIds.contains(":")) {
                            StringBuilder sb = new StringBuilder();
                            if(testReportIds.contains(",")) {
                                String[] testReportIdsArr = testReportIds.split(",");
                                for (int i = 0; i < testReportIdsArr.length; i++) {
                                    String[] project2ReportArr = testReportIdsArr[i].split(":");
                                    File file = fileMapper.selectByPrimaryKey(Long.valueOf(project2ReportArr[1]));
                                    if (file == null) {
                                        throw new BusinessException("文件ID:" + project2ReportArr[1] + "没有记录!");
                                    }
                                    sb.append(testReportIdsArr[i]);
                                    sb.append(":");
                                    sb.append(file.getFileName());
                                    if(i != testReportIdsArr.length-1){
                                        sb.append(",");
                                    }
                                }
                            } else {
                                String[] project2ReportArr = testReportIds.split(":");
                                File file = fileMapper.selectByPrimaryKey(Long.valueOf(project2ReportArr[1]));
                                if (file == null) {
                                    throw new BusinessException("文件ID:" + project2ReportArr[1] + "没有记录!");
                                }
                                sb.append(testReportIds);
                                sb.append(":");
                                sb.append(file.getFileName());
                            }
                            distrInCheckLot2DetailVO.setTestReportIds(sb.toString());
                        }


                    }
                }
            }
            inCheck2DetailVO.setDetailVOList(inCheckDetail2DetailList);
            
            inCheck2DetailVO.setEnterpriseName(userVO.getEnterpriseName());
            
            inCheck2DetailVO.setDistrTypeName(inCheck2DetailVO.getDistrType()==null?"":DistrType.getValue(inCheck2DetailVO.getDistrType()));

            inCheck2DetailVO.setReceiveQuantityTotal(receiveQuantityTotal);
            inCheck2DetailVO.setSamplingQuantityTotal(samplingQuantityTotal);
            inCheck2DetailVO.setQualifiedQuantityTotal(qualifiedQuantityTotal);
            inCheck2DetailVO.setUnqualifiedQuantityTotal(unqualifiedQuantityTotal);
        }
        return inCheck2DetailVO;
    }

    /**
     * 验收
     *
     * @param userVO
     * @param receiveId
     * @return
     */
    @Override
    public DistrInCheck2DetailVO getReceiveDetail2Check(UserVO userVO, Long receiveId) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        DistrInCheck2DetailVO inCheck2DetailVO = distrInReceiveMapper.getDistrReceive2InCheckById(enterpriseId, receiveId);
        if(inCheck2DetailVO != null){
            List<DistrInCheckDetail2DetailVO> detailForCheck = distrInReceiveDetailMapper.getReceiveDetail2DetailForCheck(enterpriseId, receiveId);
            Boolean gspFlag = commonComponent.getGspFlag(userVO);

            Iterator<DistrInCheckDetail2DetailVO> iterator = detailForCheck.iterator();
            while (iterator.hasNext()){
                DistrInCheckDetail2DetailVO item = iterator.next();
                if(BigDecimal.ZERO.compareTo(item.getReceiveQuantity()) == 0){
                    iterator.remove();
                    continue;
                }
                Long goodsId = item.getGoodsId();
                Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
                if(SpecialDrugs.getSpecialDrugsCodes().contains(goods.getSpecialDrug())){
                    item.setIsSpiritDrugType(1);
                    inCheck2DetailVO.setSpecialDrug(1);
                }

                if(SpecialDrugs.getSpecialDrugsCodes().contains(goods.getSpecialDrug()) && gspFlag){
                    inCheck2DetailVO.setSecondCheckerId(userVO.getUserId());
                    inCheck2DetailVO.setSecondCheckerName(userVO.getUserName());
                }

                Long distrOutId = item.getDistrOutId();//配货出库id

                if(distrOutId == null){
                    List<DistrInCheckLot2DetailVO> lot2DetailVOList = new ArrayList<>();
                    DistrInCheckLot2DetailVO lot2DetailVO = new DistrInCheckLot2DetailVO();
                    lot2DetailVO.setSamplingQuantity(BigDecimal.ONE);
                    lot2DetailVO.setReceiveQuantity(item.getReceiveQuantity());
                    lot2DetailVO.setQualifiedQuantity(item.getReceiveQuantity());
                    lot2DetailVO.setUnqualifiedQuantity(BigDecimal.ZERO);
                    lot2DetailVOList.add(lot2DetailVO);
                    item.setLot2DetailVOList(lot2DetailVOList);
                } else {
                    List<DistrInCheckLot2DetailVO> lot2DetailVOList = distrOutShelfMapper.getDistrOutListByOutIdGID(distrOutId, goodsId);
                    lot2DetailVOList.stream().forEach(lot->{
                        lot.setSamplingQuantity(BigDecimal.ONE);
                        lot.setQualifiedQuantity(lot.getReceiveQuantity());
                        lot.setUnqualifiedQuantity(BigDecimal.ZERO);
                    });
                    item.setLot2DetailVOList(lot2DetailVOList);
                }

            }
            inCheck2DetailVO.setDetailVOList(detailForCheck);
        }
        return inCheck2DetailVO;
    }


    /**
     * 保存验收单
     *
     * @param userVO
     * @param inCheck2DetailVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int saveDistrInCheck(UserVO userVO, DistrInCheck2DetailVO inCheck2DetailVO) throws Exception {

        int status = PubStatus.distrInStatus.WAIT_IN;
        /**
         * 含有特殊管理药品第二个验收员必填
         */

        Long enterpriseId = userVO.getEnterpriseId();

        Long receiveId = inCheck2DetailVO.getId();//收货单id
        DistrInReceive distrInReceive = distrInReceiveMapper.selectByPrimaryKey(receiveId);

        Long distrInNoticeId = distrInReceive.getBaseOrderId();

        DistrInCheck distrInCheck = (DistrInCheck) EntityUtils.reflectAddSetDefaultValue(DistrInCheck.class,userVO);
        String code = orderCodeComponent.generate(OrderRule.DISTR_IN_CHECK.getCodePrefix(),enterpriseId,userVO.getEnterpriseCode());
        distrInCheck.setCode(code);
        ManageConfig mangeConfig = manageConfigComponent.getMangeConfigByEPId(userVO);
        if(mangeConfig.getBusinessControl() == 0){
            //关闭
            User user = userMapper.selectByPrimaryKey(inCheck2DetailVO.getCheckerId());
            distrInCheck.setCheckerId(user.getId());
            distrInCheck.setCheckerCode(user.getCode());
            distrInCheck.setCheckerName(user.getName());
        } else {
            //开启
            distrInCheck.setCheckerId(userVO.getUserId());
            distrInCheck.setCheckerCode(userVO.getUserCode());
            distrInCheck.setCheckerName(userVO.getUserName());
        }
        Long secondCheckerId = inCheck2DetailVO.getSecondCheckerId();
        if(secondCheckerId != null){
            User secondCheck = userMapper.selectByPrimaryKey(secondCheckerId);
            distrInCheck.setSecondCheckerId(secondCheck.getId());
            distrInCheck.setSecondCheckerCode(secondCheck.getCode());
            distrInCheck.setSecondCheckerName(secondCheck.getName());
        }


        distrInCheck.setOrderType(OrderRule.DISTR_IN_CHECK.getType());


        distrInCheck.setCheckDate(inCheck2DetailVO.getCheckDate());

        distrInCheck.setBaseOrderCode(distrInReceive.getCode());
        distrInCheck.setBaseOrderId(distrInReceive.getId());
        distrInCheck.setBaseOrderDate(distrInReceive.getReceiveDate());
        distrInCheck.setBaseOrderType(distrInReceive.getOrderType());

        //配货单位信息
        distrInCheck.setDistrUnitCode(distrInReceive.getDistrUnitCode());
        distrInCheck.setDistrUnitId(distrInReceive.getDistrUnitId());
        distrInCheck.setDistrUnitName(distrInReceive.getDistrUnitName());

        distrInCheck.setOutboundUnitId(distrInReceive.getOutboundUnitId());
        distrInCheck.setOutboundUnitCode(distrInReceive.getOutboundUnitCode());
        distrInCheck.setOutboundUnitName(distrInReceive.getOutboundUnitName());
        distrInCheck.setDistrType(distrInReceive.getDistrType());//配货类型
        distrInCheck.setNoticeId(distrInNoticeId);//配进订单id

        List<DistrInCheckDetail2DetailVO> detailVOList = inCheck2DetailVO.getDetailVOList();
        int size = detailVOList.size();
        distrInCheck.setVarietiesQuantity(size);//品种数量
        distrInCheck.setStatus(status);
        distrInCheck.setRemark(inCheck2DetailVO.getRemark());
        distrInCheck.setQuantityTotal(BigDecimal.ZERO);
        distrInCheckMapper.insertSelective(distrInCheck);

        //总单的数量合计
        BigDecimal quantityTotal = BigDecimal.ZERO;

        for(DistrInCheckDetail2DetailVO item : detailVOList){
            DistrInCheckDetail detail = (DistrInCheckDetail) EntityUtils.reflectAddSetDefaultValue(DistrInCheckDetail.class,userVO);
            Long receiveDetailId = item.getId();
            DistrInReceiveDetail distrInReceiveDetail = distrInReceiveDetailMapper.selectByPrimaryKey(receiveDetailId);
            detail.setCheckId(distrInCheck.getId());
            detail.setCheckCode(code);
            detail.setCheckDate(inCheck2DetailVO.getCheckDate());
            detail.setOrderType(OrderRule.DISTR_IN_CHECK.getType());

            detail.setBaseOrderCode(distrInReceive.getCode());
            detail.setBaseOrderId(distrInReceive.getId());
            detail.setBaseOrderDate(distrInReceive.getReceiveDate());
            detail.setBaseOrderType(distrInReceive.getOrderType());
            detail.setBaseOrderDtlId(receiveDetailId);//收货明细id
            detail.setNoticeId(distrInNoticeId);
            detail.setNoticeDtlId(distrInReceiveDetail.getBaseOrderDtlId());//配进订单明细

            //包装商品信息
            Long goodsId = item.getGoodsId();
            Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
            detail.setGoodsId(goods.getId());
            detail.setGoodsName(goods.getName());
            detail.setGoodsCode(goods.getCode());
            detail.setGoodsGenericName(goods.getGenericName());
            detail.setGoodsPlace(goods.getPlace());
            detail.setGoodsSpecification(goods.getSpecification());
            detail.setDosageId(goods.getDosageId());
            detail.setDosageName(goods.getDosageName());
            detail.setUnitId(goods.getUnitId());
            detail.setUnitName(goods.getUnitName());
            detail.setManufacturerId(goods.getManufacturerId());
            detail.setManufacturer(goods.getManufacturer());
            detail.setApprovalNumber(goods.getApprovalNumber());
            detail.setBarcode(goods.getBarcode());


            //收货数量
            //BigDecimal receiveQuantity = distrInReceiveDetail.getReceiveQuantity();
            detail.setReceiveQuantity(item.getReceiveQuantity());
            detail.setStatus(status);
            detail.setUnclearQuantity(item.getReceiveQuantity());
            detail.setClearQuantity(BigDecimal.ZERO);//已清数量
            detail.setQualifiedQuantity(BigDecimal.ZERO);
            detail.setUnqualifiedQuantity(BigDecimal.ZERO);
            detail.setLineNum(item.getLineNum());
            quantityTotal = quantityTotal.add(item.getReceiveQuantity());

            distrInCheckDetailMapper.insertSelective(detail);

            BigDecimal qualifiedQuantity = BigDecimal.ZERO;
            BigDecimal unqualifiedQuantity = BigDecimal.ZERO;

            //验收品种明细
            List<DistrInCheckLot2DetailVO> lot2DetailVOList = item.getLot2DetailVOList();
            for (DistrInCheckLot2DetailVO lot : lot2DetailVOList){
                DistrInCheckLot checkLot = (DistrInCheckLot) EntityUtils.reflectAddSetDefaultValue(DistrInCheckLot.class,userVO);
                checkLot.setCheckId(distrInCheck.getId());
                checkLot.setDtlId(detail.getId());
                checkLot.setGoodsId(goodsId);
                checkLot.setGoodsCode(goods.getCode());
                checkLot.setGoodsName(goods.getName());
//                checkLot.setLotId(lot.getLotId());
                checkLot.setLotNumber(lot.getLotNumber());
                checkLot.setProductDate(lot.getProductDate());
                checkLot.setValidDate(lot.getValidDate());

                checkLot.setTestReportIds(lot.getTestReportIds());
                if(lot.getCheckProjectIds() == null){
                    throw new BusinessException("检验项目不能为空");
                }
                checkLot.setCheckProjectIds(lot.getCheckProjectIds());

                checkLot.setConclusionIds(lot.getConclusionIds());
                if(lot.getConclusionIds() == null){
                    throw new BusinessException("验收结论不能为空");
                }
                checkLot.setMeasuresIds(lot.getMeasuresIds());

                checkLot.setReceiveQuantity(lot.getReceiveQuantity());
                checkLot.setSamplingQuantity(lot.getSamplingQuantity());

                checkLot.setQualifiedQuantity(lot.getQualifiedQuantity());
                checkLot.setUnqualifiedQuantity(lot.getUnqualifiedQuantity());
                checkLot.setUnqualifiedReasonIds(lot.getUnqualifiedReasonIds());

                if(lot.getLineNum() == null){
                    throw new RuntimeException("lineNum 行号不能为空");
                }
                checkLot.setLineNum(lot.getLineNum());
                checkLot.setStatus(status);
                checkLot.setRemark(lot.getRemark());

                checkLot.setUnclearQuantity(lot.getReceiveQuantity());//未清数量
                checkLot.setClearQuantity(BigDecimal.ZERO);

                //合格 不合格 数量
                if(lot.getQualifiedQuantity() == null){
                    throw new BusinessException("合格数量QualifiedQuantity不能为空");
                }
                qualifiedQuantity = qualifiedQuantity.add(lot.getQualifiedQuantity());
                if(lot.getUnqualifiedQuantity() == null){
                    throw new BusinessException("不合格数量UnqualifiedQuantity不能为空");
                }
                unqualifiedQuantity = unqualifiedQuantity.add(lot.getUnqualifiedQuantity());
                distrInCheckLotMapper.insertSelective(checkLot);


            }
            detail.setQualifiedQuantity(qualifiedQuantity);
            detail.setUnqualifiedQuantity(unqualifiedQuantity);
            distrInCheckDetailMapper.updateByPrimaryKeySelective(detail);

            //更新收货单已清 未清数量
            DistrInReceiveDetail inReceiveDetail = (DistrInReceiveDetail) EntityUtils.reflectUpdateSetDefaultValue(DistrInReceiveDetail.class,userVO);
            inReceiveDetail.setId(receiveDetailId);
            inReceiveDetail.setUnclearQuantity(BigDecimal.ZERO);
            inReceiveDetail.setClearQuantity(detail.getReceiveQuantity());//已清数量
            distrInReceiveDetailMapper.updateByPrimaryKeySelective(inReceiveDetail);
        }
        distrInCheck.setQuantityTotal(quantityTotal);
        distrInCheckMapper.updateByPrimaryKey(distrInCheck);

        //更新上游状态-配进收货单,-配进订单
        distrInReceive = (DistrInReceive) EntityUtils.reflectUpdateSetDefaultValue(DistrInReceive.class,userVO);
        distrInReceive.setId(receiveId);
        distrInReceive.setStatus(status);
        distrInReceiveMapper.updateByPrimaryKeySelective(distrInReceive);
        distrInReceiveDetailMapper.updateDetailStatus(enterpriseId,receiveId,status);

        DistrInNotice distrInNotice = (DistrInNotice) EntityUtils.reflectUpdateSetDefaultValue(DistrInNotice.class,userVO);
        distrInNotice.setId(distrInNoticeId);
        distrInNotice.setStatus(status);
        distrInNoticeMapper.updateByPrimaryKeySelective(distrInNotice);
        distrInNoticeDetailMapper.updateStatusByOrderId(distrInNoticeId,status);
        return 0;
    }


    /**
     * 导出
     * @param output
     * @param enterpriseId
     * @param id
     * @throws Exception
     */
    @Override
    public void export(OutputStream output, UserVO userVO, Long id) throws Exception {
        Long enterpriseId = userVO.getEnterpriseId();
        Long parentId = userVO.getParentId();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);//企业
        DistrInCheck2DetailVO inCheck2DetailVO = distrInCheckMapper.getDistrInCheckById(enterpriseId, id);

        Map<String,BigDecimal> quantityTotalMap = distrInCheckDetailMapper.getQuantityTotalInCheckById(enterpriseId, id);

        List<DistrInCheckDetail2Detail2ExcelVO> inCheckDetail2DetailList = distrInCheckDetailMapper.getInCheckDetail2Detail2Excel(enterpriseId, id);

        List<QualitySetVO> qualitySetVOList = qualitySetMapper.getQualitySetVOList(parentId, null, null, EnableStatus.ENABLE.getStatus());

        filterPackData(inCheckDetail2DetailList, qualitySetVOList);


        ExcelWriter writer = new ExcelWriter() {
            public void generate() throws Exception {


                int kk = 0;
                // 电子表格开始
                this.beginWorkSheet();
                this.beginSheet();
                Map<String, XSSFCellStyle> styleMap = this.getCellStyles();
                short cellStrIndex = styleMap.get("cell_string").getIndex();
                //第一行
                this.insertRow(kk++);
                this.createCell(0, enterprise.getName() + "大药房",cellStrIndex);
                this.endRow();
                //第二行
                this.insertRow(kk++);
                this.createCell(0, "配进验收单",cellStrIndex);
                this.endRow();
                //第三行
                this.insertRow(kk++);
                this.createCell(0, "配货单位编码：" + Optional.ofNullable(inCheck2DetailVO.getDistrUnitCode()).orElse(""));
                this.createCell(2, "配货单位名称：" + Optional.ofNullable(inCheck2DetailVO.getDistrUnitName()).orElse(""));

                this.createCell(4, "配进验收单号：" + Optional.ofNullable(inCheck2DetailVO.getCode()).orElse(""));
                this.createCell(6, "验收日期：" + DateUtils.DateToString(inCheck2DetailVO.getCheckDate(),DateStyle.YYYY_MM_DD_HH_MM_SS));
                this.endRow();
                //第四行
                this.insertRow(kk++);
                this.createCell(0, "验收人员1：" + Optional.ofNullable(inCheck2DetailVO.getCheckerName()).orElse(""));
                this.createCell(2, "验收人员2：" + Optional.ofNullable(inCheck2DetailVO.getSecondCheckerName()).orElse(""));

                this.createCell(4, "配货类型：" + DistrType.getValue(inCheck2DetailVO.getDistrType()));
                this.createCell(6, "备注：" + Optional.ofNullable(inCheck2DetailVO.getRemark()).orElse(""));
                this.endRow();

                createRowHeader(this,kk++);
                for (int rowNum = 0; rowNum < inCheckDetail2DetailList.size(); rowNum++) {
                    DistrInCheckDetail2Detail2ExcelVO detailVO = inCheckDetail2DetailList.get(rowNum);

                    // 插入新行
                    this.insertRow(rowNum + kk);
                    // 建立新单元格,索引值从0开始,表示第一列
                    int k = 0;
                    this.createCell(k++, k);
                    this.createCell(k++, detailVO.getGoodsCode());
                    this.createCell(k++, detailVO.getGoodsGenericName());
                    this.createCell(k++, detailVO.getDosageName());
                    this.createCell(k++, detailVO.getGoodsSpecification());
                    this.createCell(k++, detailVO.getUnitName());
                    this.createCell(k++, detailVO.getManufacturer());
                    this.createCell(k++, detailVO.getGoodsPlace());
                    this.createCell(k++, detailVO.getReceiveQuantity() + "");
                    this.createCell(k++, detailVO.getQualifiedQuantity() + "");
                    this.createCell(k++, detailVO.getUnqualifiedQuantity() + "");
                    this.createCell(k++, detailVO.getLotNumber());
                    this.createCell(k++, DateUtils.DateToString(detailVO.getProductDate(),DateStyle.YYYY_MM_DD_HH_MM_SS) );
                    this.createCell(k++, DateUtils.DateToString(detailVO.getValidDate(),DateStyle.YYYY_MM_DD_HH_MM_SS));
                    this.createCell(k++, detailVO.getCheckProjectNames());
                    this.createCell(k++, detailVO.getReceiveQuantityLot() + "");
                    this.createCell(k++, detailVO.getSamplingQuantity() + "");
                    this.createCell(k++, detailVO.getQualifiedQuantityLot() + "");
                    this.createCell(k++, detailVO.getConclusionNames() + "");
                    this.createCell(k++, detailVO.getUnqualifiedQuantityLot() + "");
                    this.createCell(k++, detailVO.getUnqualifiedReasonNames());
                    this.createCell(k++, detailVO.getMeasuresNames());
                    this.createCell(k, detailVO.getRemark());
                    // 结束行
                    this.endRow();


                }
                int row = kk + inCheckDetail2DetailList.size();

                this.insertRow(row);
                this.createCell(0, "合计");
                this.createCell(8, quantityTotalMap.get("quantityTotal") + "");
                this.createCell(9, quantityTotalMap.get("qualifiedQuantityTotal") + "");
                this.createCell(10, quantityTotalMap.get("unqualifiedQuantityTotal") + "");
                this.endRow();
                // sheet表格结束
                this.endSheet();

//                合并单元格
                this.beginMergerCell();
                this.setMergeCell(0, 0, 0, 22);

                this.setMergeCell(1, 0, 1, 22);

                this.setMergeCell(row,0,row,7);
                this.endMergerCell();

                this.endWorkSheet();


            }
        };
        writer.process(output);

    }

    private void filterPackData(List<DistrInCheckDetail2Detail2ExcelVO> inCheckDetail2DetailList, List<QualitySetVO> qualitySetVOList) {
        inCheckDetail2DetailList.stream().forEach(item->{
            String checkProjectIds = item.getCheckProjectIds();//检验项目集合
            if(checkProjectIds != null){
                String[] checkProjectIdArr = checkProjectIds.split(",");

                Stream.of(checkProjectIdArr).forEach((String project) ->{
                    String name = qualitySetVOList.stream().filter(q->project.equals(q.getId()+"")).findFirst().orElse(new QualitySetVO()).getDescription();
                    String names = item.getCheckProjectNames();
                    if(name != null){
                        item.setCheckProjectNames(names == null ? name : names + "," + name);
                    }

                });
            }


            String conclusionIds = item.getConclusionIds();//验收结论集合
            if(conclusionIds != null){
                String[] conclusionIdsArr = conclusionIds.split(",");
                Stream.of(conclusionIdsArr).forEach((String project) ->{
                    String name = qualitySetVOList.stream().filter(q->project.equals(q.getId()+"")).findFirst().orElse(new QualitySetVO()).getDescription();
                    String names = item.getConclusionNames();
                    if(name != null){
                        item.setConclusionNames(names == null ? name : names + "," + name);
                    }
                });
            }


            String unqualifiedReasonIds = item.getUnqualifiedReasonIds();//不合格原因集合
            if(unqualifiedReasonIds != null){
                String[] unqualifiedReasonIdsArr = unqualifiedReasonIds.split(",");
                Stream.of(unqualifiedReasonIdsArr).forEach((String project) ->{
                    String name = qualitySetVOList.stream().filter(q->project.equals(q.getId()+"")).findFirst().orElse(new QualitySetVO()).getDescription();
                    String names = item.getUnqualifiedReasonNames();
                    if(name != null){
                        item.setUnqualifiedReasonNames(names == null ? name : names + "," + name);
                    }

                });
            }

            String measuresIds = item.getMeasuresIds();
            if(measuresIds != null){
                String[] measuresIdsArr = measuresIds.split(",");
                Stream.of(measuresIdsArr).forEach((String project) ->{
                    String name = qualitySetVOList.stream().filter(q->project.equals(q.getId()+"")).findFirst().orElse(new QualitySetVO()).getDescription();
                    String names = item.getMeasuresNames();
                    if(name != null){
                        item.setMeasuresNames(names == null ? name : names + "," + name);
                    }
                });
            }
        });
    }

    private void filterPackData1(List<DistrInCheckLot2DetailVO> lot2DetailVOList, List<QualitySetVO> qualitySetVOList) {
        lot2DetailVOList.stream().forEach(item->{
            String checkProjectIds = item.getCheckProjectIds();//检验项目集合
            if(checkProjectIds != null){
                String[] checkProjectIdArr = checkProjectIds.split(",");

                Stream.of(checkProjectIdArr).forEach((String project) ->{
                    String name = qualitySetVOList.stream().filter(q->project.equals(q.getId()+"")).findFirst().orElse(new QualitySetVO()).getDescription();
                    String names = item.getCheckProjectNames();
                    if(name != null){
                        item.setCheckProjectNames(names == null ? name : names + "," + name);
                    }

                });
            }


            String conclusionIds = item.getConclusionIds();//验收结论集合
            if(conclusionIds != null){
                String[] conclusionIdsArr = conclusionIds.split(",");
                Stream.of(conclusionIdsArr).forEach((String project) ->{
                    String name = qualitySetVOList.stream().filter(q->project.equals(q.getId()+"")).findFirst().orElse(new QualitySetVO()).getDescription();
                    String names = item.getConclusionNames();
                    if(name != null){
                        item.setConclusionNames(names == null ? name : names + "," + name);
                    }
                });
            }


            String unqualifiedReasonIds = item.getUnqualifiedReasonIds();//不合格原因集合
            if(unqualifiedReasonIds != null){
                String[] unqualifiedReasonIdsArr = unqualifiedReasonIds.split(",");
                Stream.of(unqualifiedReasonIdsArr).forEach((String project) ->{
                    String name = qualitySetVOList.stream().filter(q->project.equals(q.getId()+"")).findFirst().orElse(new QualitySetVO()).getDescription();
                    String names = item.getUnqualifiedReasonNames();
                    if(name != null){
                        item.setUnqualifiedReasonNames(names == null ? name : names + "," + name);
                    }

                });
            }
            //处置措施
            String measuresIds = item.getMeasuresIds();
            if(measuresIds != null){
                String[] measuresIdsArr = measuresIds.split(",");
                Stream.of(measuresIdsArr).forEach((String project) ->{
                    String name = qualitySetVOList.stream().filter(q->project.equals(q.getId()+"")).findFirst().orElse(new QualitySetVO()).getDescription();
                    String names = item.getMeasuresNames();
                    if(name != null){
                        item.setMeasuresNames(names == null ? name : names + "," + name);
                    }
                });
            }
        });
    }


    private void createRowHeader(ExcelWriter writer,int kk) throws IOException {
        // 插入新行
        writer.insertRow(kk);
        // 建立新单元格,索引值从0开始,表示第一列
        int k = 0;
        writer.createCell(k++, "序号");
        writer.createCell(k++, "商品编码");
        writer.createCell(k++, "通用名称");
        writer.createCell(k++, "剂型");
        writer.createCell(k++, "规格");
        writer.createCell(k++, "单位");
        writer.createCell(k++, "生产厂商");
        writer.createCell(k++, "产地");

        writer.createCell(k++, "收货数量");
        writer.createCell(k++, "验收合格数量");
        writer.createCell(k++, "验收不合格数量");
        writer.createCell(k++, "批号");
        writer.createCell(k++, "生产日期");
        writer.createCell(k++, "有效期至");

        writer.createCell(k++, "检验项目");
        writer.createCell(k++, "收货数量");
        writer.createCell(k++, "抽样数量");
        writer.createCell(k++, "验收合格数量");
        writer.createCell(k++, "验收结论");
        writer.createCell(k++, "验收不合格数量");
        writer.createCell(k++, "不合格原因");
        writer.createCell(k++, "处置措施");
        writer.createCell(k, "备注");

        // 结束行
        writer.endRow();
    }

}