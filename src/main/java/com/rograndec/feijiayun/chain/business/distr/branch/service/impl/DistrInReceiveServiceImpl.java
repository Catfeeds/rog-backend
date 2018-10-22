package com.rograndec.feijiayun.chain.business.distr.branch.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInNoticeDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInNoticeMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInReceiveDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.dao.DistrInReceiveMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInNotice;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInNoticeDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReceive;
import com.rograndec.feijiayun.chain.business.distr.branch.entity.DistrInReceiveDetail;
import com.rograndec.feijiayun.chain.business.distr.branch.service.DistrInReceiveService;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReceiveDetailSaveVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReceiveDetailVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReceiveSaveVO;
import com.rograndec.feijiayun.chain.business.distr.branch.vo.DistrInReceiveVO;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrReturnNoticeService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnNoticeDtlVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnNoticeFormVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.purchase.receive.service.PurchaseReceiveService;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.DistrInReceiveReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.OrderReportVo;
import com.rograndec.feijiayun.chain.business.report.quality.distr.vo.RequestDistrInReceive;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.CommonComponent;
import com.rograndec.feijiayun.chain.common.component.DistrComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.SpecialDrugs;
import com.rograndec.feijiayun.chain.common.constant.status.PubStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class DistrInReceiveServiceImpl implements DistrInReceiveService {

    @Autowired
    private OrderCodeComponent orderCodeComponent;
    @Autowired
    private DistrInNoticeMapper distrInNoticeMapper;
    @Autowired
    private DistrInNoticeDetailMapper distrInNoticeDetailMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DistrInReceiveMapper distrInReceiveMapper;
    @Autowired
    private DistrInReceiveDetailMapper distrInReceiveDetailMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
    @Autowired
    private DistrReturnNoticeService distrReturnNoticeService;
    @Autowired
    private DistrComponent distrComponent;
    @Autowired
    private PurchaseReceiveService purchaseReceiveService;
    @Autowired
    private PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private CommonComponent commonComponent;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveDistrInReceiveOrder(UserVO loginUser, DistrInReceiveSaveVO distrInReceiveSaveVO) throws Exception {
        //保存总单
        DistrInReceive distrInReceive = new DistrInReceive();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrInReceiveSaveVO,distrInReceive);
        //set 单据类型
        distrInReceive.setOrderType(OrderRule.DISTR_IN_RECEIVE.getType());
        //set 单号
        distrInReceive.setCode(orderCodeComponent.generate(OrderRule.DISTR_IN_RECEIVE.getCodePrefix(), loginUser.getEnterpriseId(), loginUser.getEnterpriseCode()));
        //set 基础单据信息(从上游单据获取)
        DistrInNotice distrInNotice = distrInNoticeMapper.selectByPrimaryKey(distrInReceiveSaveVO.getBaseOrderId());
        //收货日期不能小于配进订单日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(distrInNotice.getOrderDate());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if(distrInReceive.getReceiveDate().before(calendar.getTime())){
            return "收货日期不能小于配进订单日期";
        }
        distrInReceive.setBaseOrderCode(distrInNotice.getCode());
        distrInReceive.setBaseOrderDate(distrInNotice.getOrderDate());
        distrInReceive.setBaseOrderType(distrInNotice.getOrderType());
        //set 要货单位(从上游单据获取)
        distrInReceive.setDistrUnitId(distrInNotice.getDistrUnitId());
        distrInReceive.setDistrUnitCode(distrInNotice.getDistrUnitCode());
        distrInReceive.setDistrUnitName(distrInNotice.getDistrUnitName());
        distrInReceive.setOutboundUnitId(distrInNotice.getOutboundUnitId());
        distrInReceive.setOutboundUnitCode(distrInNotice.getOutboundUnitCode());
        distrInReceive.setOutboundUnitName(distrInNotice.getOutboundUnitName());
        //set 配货类型(从上游单据获取)
        distrInReceive.setDistrType(distrInNotice.getDistrType());
        //set 收货人员
        User user = userMapper.selectByPrimaryKey(distrInReceiveSaveVO.getReceiverId());
        distrInReceive.setReceiverCode(user.getCode());
        distrInReceive.setReceiverName(user.getName());
        //有第二收货人时 set信息
        if (distrInReceiveSaveVO.getSecondReceiverId() != null) {
            user = userMapper.selectByPrimaryKey(distrInReceiveSaveVO.getSecondReceiverId());
            distrInReceive.setSecondReceiverCode(user.getCode());
            distrInReceive.setSecondReceiverName(user.getName());
        }
        //set 状态
        distrInReceive.setStatus(PubStatus.distrInStatus.WAIT_CHECK);
        //set 创建人信息
        UserEnterpriseUtils.setUserCreateOrModify(distrInReceive, loginUser, true);
        //set 企业id
        distrInReceive.setEnterpriseId(loginUser.getEnterpriseId());
        distrInReceive.setParentId(loginUser.getParentId());
        distrInReceiveMapper.insertSelective(distrInReceive);
        //保存细单
        boolean isWholeRefuseQuantity = true;//是否是整单拒收
        List<DistrInReceiveDetail> distrInReceiveDetails = new ArrayList<>();
        for (DistrInReceiveDetailSaveVO distrInReceiveDetailSaveVO : distrInReceiveSaveVO.getDistrInReceiveDetailSaveVOS()) {
            DistrInReceiveDetail distrInReceiveDetail = new DistrInReceiveDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrInReceiveDetailSaveVO, distrInReceiveDetail);
            //set 配后退回收货单ID/类型/单号/日期
            distrInReceiveDetail.setReceiveId(distrInReceive.getId());
            distrInReceiveDetail.setOrderType(distrInReceive.getOrderType());
            distrInReceiveDetail.setReceiveCode(distrInReceive.getCode());
            distrInReceiveDetail.setReceiveDate(distrInReceive.getReceiveDate());
            //set 基础单据信息
            distrInReceiveDetail.setBaseOrderCode(distrInReceive.getBaseOrderCode());
            distrInReceiveDetail.setBaseOrderDate(distrInReceive.getBaseOrderDate());
            distrInReceiveDetail.setBaseOrderType(distrInReceive.getBaseOrderType());
            //set 商品信息
            Goods goods = goodsMapper.selectByPrimaryKey(distrInReceiveDetailSaveVO.getGoodsId());
            if(goods.getSpecialDrug() != -1 && distrInReceive.getSecondReceiverId() == null){
                throw new BusinessException("第"+distrInReceiveDetailSaveVO.getLineNum()+"行的"+goods.getGenericName()+"为特管商品,必须有第二收货人!");
            }
            distrInReceiveDetail.setGoodsName(goods.getName());
            distrInReceiveDetail.setGoodsCode(goods.getCode());
            distrInReceiveDetail.setBarcode(goods.getBarcode());
            distrInReceiveDetail.setGoodsGenericName(goods.getGenericName());
            distrInReceiveDetail.setDosageId(goods.getDosageId());
            distrInReceiveDetail.setDosageName(goods.getDosageName());
            distrInReceiveDetail.setUnitId(goods.getUnitId());
            distrInReceiveDetail.setUnitName(goods.getUnitName());
            distrInReceiveDetail.setGoodsSpecification(goods.getSpecification());
            distrInReceiveDetail.setManufacturerId(goods.getManufacturerId());
            distrInReceiveDetail.setManufacturer(goods.getManufacturer());
            distrInReceiveDetail.setGoodsPlace(goods.getPlace());
            distrInReceiveDetail.setApprovalNumber(goods.getApprovalNumber());
            distrInReceiveDetail.setReceiveQuantity(distrInReceiveDetailSaveVO.getReceiveQuantity());
            //set 未清数量=收货数量 以清数量 = 0
            distrInReceiveDetail.setUnclearQuantity(distrInReceiveDetailSaveVO.getReceiveQuantity());
            distrInReceiveDetail.setClearQuantity(BigDecimal.ZERO);
            if(distrInReceiveDetail.getRefuseQuantity().compareTo(BigDecimal.ZERO) == 1){
                if(StringUtils.isBlank(distrInReceiveDetail.getRefuseReasonIds())) throw new BusinessException("拒收条件不能为空");
            }
            //set 状态(如果收货数量为0,则状态为已完成)
            if(distrInReceiveDetail.getReceiveQuantity().compareTo(BigDecimal.ZERO)==0){
                distrInReceiveDetail.setStatus(PubStatus.distrInStatus.FINISHED);

                BigDecimal arrivalQuantity = distrInReceiveDetail.getArrivalQuantity();

                //修改通知单细单状态 和 数量
                distrInNoticeDetailMapper.updateStatusById(distrInReceiveDetailSaveVO.getBaseOrderDtlId(),PubStatus.distrInStatus.FINISHED,loginUser.getEnterpriseId(),arrivalQuantity);
            }else {
                isWholeRefuseQuantity = false;
                distrInReceiveDetail.setStatus(distrInReceive.getStatus());

                BigDecimal arrivalQuantity = distrInReceiveDetail.getArrivalQuantity();

                //修改通知单细单状态
                distrInNoticeDetailMapper.updateStatusById(distrInReceiveDetailSaveVO.getBaseOrderDtlId(),distrInReceive.getStatus(),loginUser.getEnterpriseId(),arrivalQuantity);
            }
            //set 创建人信息
            UserEnterpriseUtils.setUserCreateOrModify(distrInReceiveDetail, loginUser, true);
            distrInReceiveDetail.setEnterpriseId(loginUser.getEnterpriseId());
            distrInReceiveDetail.setParentId(loginUser.getParentId());
            distrInReceiveDetails.add(distrInReceiveDetail);
        }
        distrInReceiveDetailMapper.batchInsert(distrInReceiveDetails);
        //修改通知单状态
        if(isWholeRefuseQuantity) {
            distrInNoticeMapper.updateStatus(distrInReceive.getBaseOrderId(), PubStatus.distrInStatus.FINISHED);
            distrInNoticeDetailMapper.updateStatusByOrderId(distrInReceive.getBaseOrderId(),PubStatus.distrInStatus.FINISHED);
            distrInReceiveMapper.updateStatusById(user.getEnterpriseId(), distrInReceive.getId(), PubStatus.distrInStatus.FINISHED);
            distrInReceiveDetailMapper.updateDetailStatus(user.getEnterpriseId(),distrInReceive.getId(), PubStatus.distrInStatus.FINISHED);
        } else {
            distrInNoticeMapper.updateStatus(distrInReceive.getBaseOrderId(), PubStatus.distrInStatus.WAIT_CHECK);
        }
        if(distrInNotice.getDistrType() == DistributionType.DISTRIBUTION_HEAD.getCode()){
            getReturnNotice(distrInReceive, distrInReceiveDetails, loginUser);
        }

        return "";
    }

    @Override
    public DistrInReceiveVO getDistrInReceiveOrderDtlList(UserVO loginUser, Long id) throws Exception {
        List<DistrInReceiveDetailVO> distrInReceiveDetailVOS = distrInReceiveDetailMapper.selectByReceiveIdByEnterpriseId(id,loginUser.getEnterpriseId());
        
        BigDecimal arrivalQuantityTotal = BigDecimal.ZERO;//到货数量
        BigDecimal receiveQuantityTotal = BigDecimal.ZERO;//收货数量
        BigDecimal refuseQuantityTotal = BigDecimal.ZERO;//拒收数量
        
        for (DistrInReceiveDetailVO distrInReceiveDetailVO : distrInReceiveDetailVOS) {
            String refuseReasonIds = distrInReceiveDetailVO.getRefuseReasonIds();
            distrInReceiveDetailVO.setRefuseReason(purchaseReceiveService.getRefuseReasons(refuseReasonIds, loginUser));
        
            arrivalQuantityTotal = arrivalQuantityTotal.add(distrInReceiveDetailVO.getArrivalQuantity()!=null?distrInReceiveDetailVO.getArrivalQuantity():BigDecimal.ZERO);
            receiveQuantityTotal = receiveQuantityTotal.add(distrInReceiveDetailVO.getReceiveQuantity()!=null?distrInReceiveDetailVO.getReceiveQuantity():BigDecimal.ZERO);
            refuseQuantityTotal = refuseQuantityTotal.add(distrInReceiveDetailVO.getRefuseQuantity()!=null?distrInReceiveDetailVO.getRefuseQuantity():BigDecimal.ZERO);
        }
        DistrInReceive distrInReceive = distrInReceiveMapper.selectByPrimaryKey(id);
        DistrInReceiveVO distrInReceiveVO = new DistrInReceiveVO();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrInReceive,distrInReceiveVO);
        distrInReceiveVO.setStatusName(PubStatus.distrInStatus.getStatusDesc(distrInReceiveVO.getStatus()));
        distrInReceiveVO.setDistrTypeName(distrInReceiveVO.getDistrType()==0?"总部配送":distrInReceiveVO.getDistrType()==3?"分店间调剂":"直调配送");
        distrInReceiveVO.setEnterpriseName(loginUser.getEnterpriseName());
        distrInReceiveVO.setArrivalQuantityTotal(arrivalQuantityTotal);
        distrInReceiveVO.setReceiveQuantityTotal(receiveQuantityTotal);
        distrInReceiveVO.setRefuseQuantityTotal(refuseQuantityTotal);
        distrInReceiveVO.setDistrInReceiveDetailVOList(distrInReceiveDetailVOS);
        return distrInReceiveVO;
    }

    @Override
    public Page<List<DistrInReceiveVO>> getDistrInReceiveOrderList(UserVO loginUser, Date startDate, Date endDate, String distrUnitCode, String distrUnitName, String code, Integer distrType, Integer status, String receiverName, String secondReceiverName, String orderName, String orderType, Integer pageNo, Integer pageSize) throws Exception {
        if (orderName != null) {
            if ("receiveDate".equals(orderName))
                orderName = "receive_date";
            else if ("code".equals(orderName))
                orderName = "code";
            else orderName = null;
        }
        if(startDate != null && "".equals(startDate.toString())){
            startDate = null;
        }
        if(endDate != null && "".equals(endDate.toString())){
            endDate = null;
        }
        Page<List<DistrInReceiveVO>> page = new Page<>(pageNo, pageSize);
//        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DistrInReceiveVO> distrInReceiveVOS = distrInReceiveMapper.selectByEnterpriseId(loginUser.getEnterpriseId(), startDate, endDate, distrUnitCode, distrUnitName, code, distrType, status, receiverName, secondReceiverName, orderName, orderType, (pageNo-1)*pageSize, pageSize);
        for(DistrInReceiveVO distrInReceiveVO : distrInReceiveVOS){
            distrInReceiveVO.setStatusName(PubStatus.distrInStatus.getStatusDesc(distrInReceiveVO.getStatus()));
            if(distrInReceiveVO.getDistrType().equals(DistributionType.DISTRIBUTION_HEAD.getCode())){
            	distrInReceiveVO.setDistrTypeName(DistributionType.DISTRIBUTION_HEAD.getName());
            }else if(distrInReceiveVO.getDistrType().equals(DistributionType.SWAP_BETWEEN_STORES.getCode())){
            	distrInReceiveVO.setDistrTypeName(DistributionType.SWAP_BETWEEN_STORES.getName());
            }else if(distrInReceiveVO.getDistrType().equals(DistributionType.DIRECT_DISTRIBUTION.getCode())){
            	distrInReceiveVO.setDistrTypeName(DistributionType.DIRECT_DISTRIBUTION.getName());
            }else if(distrInReceiveVO.getDistrType().equals(DistributionType.ENTRUST_TRANSPORT.getCode())){
            	distrInReceiveVO.setDistrTypeName(DistributionType.ENTRUST_TRANSPORT.getName());
            }else if(distrInReceiveVO.getDistrType().equals(DistributionType.SELF.getCode())){
            	distrInReceiveVO.setDistrTypeName(DistributionType.SELF.getName());
            }
        }
        page.setResult(distrInReceiveVOS);
        page.setTotalRecord(distrInReceiveMapper.selectCountByEnterpriseId(loginUser.getEnterpriseId(), startDate, endDate, distrUnitCode, distrUnitName, code, distrType, status, receiverName, secondReceiverName));
        return page;
    }

    @Override
    public void exportExcel(OutputStream output, Long id, UserVO loginUser) throws Exception{
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("goodsCode","商品编码");
        map.put("goodsName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("unitName","单位");
        map.put("manufacturer","生产厂商");
        map.put("arrivalQuantity","到货数量");
        map.put("receiveQuantity","收货数量");
        map.put("refuseQuantity","拒收数量");
        map.put("refuseReasons","拒收原因");
        //标题栏下第一行
        List<String> titleSecond = new ArrayList<>();
        DistrInReceive distrInReceive = distrInReceiveMapper.selectByPrimaryKey(id);
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("配货单位编码:");
        titleSecondRow.append(distrInReceive.getDistrUnitCode());
        titleSecondRow.append("  配货单位名称:");
        titleSecondRow.append(distrInReceive.getDistrUnitName());
        titleSecondRow.append("  配进收货单号:");
        titleSecondRow.append(distrInReceive.getCode());
        titleSecondRow.append("  收货日期:");
        //日期转成yyyy-mm-dd形式
        titleSecondRow.append(distrInReceive.getReceiveDate());
        titleSecond.add(titleSecondRow.toString());
        //标题栏下第二行
        titleSecondRow = new StringBuilder();
        titleSecondRow.append("收货人员1:");
        titleSecondRow.append(distrInReceive.getReceiverName());
        titleSecondRow.append("  收货人员2:");
        titleSecondRow.append(distrInReceive.getSecondReceiverName());
        titleSecondRow.append("  配进类型:");
        titleSecondRow.append(distrInReceive.getDistrType()==0?"总部配送":distrInReceive.getDistrType()==1?"分店间调剂":"直调配送");
        titleSecondRow.append("  备注:");
        titleSecondRow.append(distrInReceive.getRemark());
        titleSecond.add(titleSecondRow.toString());
        StringBuilder end = new StringBuilder();
        BigDecimal arrivalQuantity = BigDecimal.ZERO;//到货数量
        BigDecimal receiveQuantity = BigDecimal.ZERO;//收货数量
        BigDecimal refuseQuantity = BigDecimal.ZERO;//拒收数量
        List<DistrInReceiveDetailVO> distrInReceiveDetailVOS = distrInReceiveDetailMapper.selectByReceiveIdByEnterpriseId(id,loginUser.getEnterpriseId());
        for (DistrInReceiveDetailVO distrInReceiveDetailVO : distrInReceiveDetailVOS){
            arrivalQuantity = arrivalQuantity.add(distrInReceiveDetailVO.getArrivalQuantity());
            receiveQuantity = receiveQuantity.add(distrInReceiveDetailVO.getReceiveQuantity());
            refuseQuantity = receiveQuantity.add(distrInReceiveDetailVO.getRefuseQuantity());
        }
        end.append(arrivalQuantity);
        end.append(";");
        end.append(receiveQuantity);
        end.append(";");
        end.append(refuseQuantity);
        List<String> needTotalName = new ArrayList<>();
        needTotalName.add("arrivalQuantity");
        needTotalName.add("receiveQuantity");
        needTotalName.add("refuseQuantity");
        List<String> name = new ArrayList<>();
        name.add(loginUser.getEnterpriseName());
        name.add("配进收货单");
        purchaseGeneralComponent.commExcelExport(output,map,distrInReceiveDetailVOS,name,titleSecond,end.toString(),false,needTotalName);
    }

    @Override
    public DistrInReceiveVO showWillSaveList(UserVO loginUser, Long id) throws Exception {
        DistrInReceiveVO distrInReceiveVO = new DistrInReceiveVO();
        DistrInNotice distrInNotice = distrInNoticeMapper.selectByPrimaryKey(id);
        //set 基础单据信息
        distrInReceiveVO.setBaseOrderId(distrInNotice.getId());
        distrInReceiveVO.setBaseOrderCode(distrInNotice.getCode());
        distrInReceiveVO.setBaseOrderDate(distrInNotice.getOrderDate());
        distrInReceiveVO.setBaseOrderType(distrInNotice.getOrderType());
        //set 配货单位编码/名称/配货类型
        distrInReceiveVO.setDistrUnitId(distrInNotice.getDistrUnitId());
        distrInReceiveVO.setDistrUnitCode(distrInNotice.getDistrUnitCode());
        distrInReceiveVO.setDistrUnitName(distrInNotice.getDistrUnitName());
        distrInReceiveVO.setDistrType(distrInNotice.getDistrType());
        //set数量合计/品种合计
        distrInReceiveVO.setQuantityTotal(distrInNotice.getQuantityTotal());
        distrInReceiveVO.setVarietiesQuantity(distrInNotice.getVarietiesQuantity());
        List<DistrInReceiveDetailVO> distrInReceiveDetailVOS = new ArrayList<>();
        //设置出库单位
        distrInReceiveVO.setOutboundUnitId(distrInNotice.getOutboundUnitId());
        distrInReceiveVO.setOutboundUnitCode(distrInNotice.getOutboundUnitCode());
        distrInReceiveVO.setOutboundUnitName(distrInNotice.getOutboundUnitName());
        Boolean gspFlag = commonComponent.getGspFlag(loginUser);

        List<DistrInNoticeDetail> distrInNoticeDetails = distrInNoticeDetailMapper.selectByNoticeIdByEnterpriseId(id,loginUser.getEnterpriseId());
        for (DistrInNoticeDetail distrInNoticeDetail : distrInNoticeDetails){
            DistrInReceiveDetailVO distrInReceiveDetailVO = new DistrInReceiveDetailVO();
            //set 商品信息
            distrInReceiveDetailVO.setGoodsId(distrInNoticeDetail.getGoodsId());
            distrInReceiveDetailVO.setGoodsName(distrInNoticeDetail.getGoodsName());
            distrInReceiveDetailVO.setGoodsCode(distrInNoticeDetail.getGoodsCode());
            distrInReceiveDetailVO.setBarcode(distrInNoticeDetail.getBarcode());
            distrInReceiveDetailVO.setGoodsGenericName(distrInNoticeDetail.getGoodsGenericName());
            distrInReceiveDetailVO.setDosageId(distrInNoticeDetail.getDosageId());
            distrInReceiveDetailVO.setDosageName(distrInNoticeDetail.getDosageName());
            distrInReceiveDetailVO.setUnitId(distrInNoticeDetail.getUnitId());
            distrInReceiveDetailVO.setUnitName(distrInNoticeDetail.getUnitName());
            distrInReceiveDetailVO.setGoodsSpecification(distrInNoticeDetail.getGoodsSpecification());
            distrInReceiveDetailVO.setManufacturerId(distrInNoticeDetail.getManufacturerId());
            distrInReceiveDetailVO.setManufacturer(distrInNoticeDetail.getManufacturer());
            distrInReceiveDetailVO.setGoodsPlace(distrInNoticeDetail.getGoodsPlace());
            distrInReceiveDetailVO.setApprovalNumber(distrInNoticeDetail.getApprovalNumber());

            Goods goods = goodsMapper.selectByPrimaryKey(distrInNoticeDetail.getGoodsId());
            distrInReceiveDetailVO.setSpecialDrug(goods.getSpecialDrug());

            //set 基础单据信息
            distrInReceiveDetailVO.setBaseOrderId(distrInNoticeDetail.getOrderId());
            distrInReceiveDetailVO.setBaseOrderCode(distrInNoticeDetail.getOrderCode());
            distrInReceiveDetailVO.setBaseOrderDate(distrInNoticeDetail.getOrderDate());
            distrInReceiveDetailVO.setBaseOrderDtlId(distrInNoticeDetail.getId());
            distrInReceiveDetailVO.setBaseOrderType(distrInNoticeDetail.getOrderType());
            //set 到货数量(订单的数量) 拒收数量默认为0 收货数量默认到货数量
            distrInReceiveDetailVO.setArrivalQuantity(distrInNoticeDetail.getQuantity());
            distrInReceiveDetailVO.setReceiveQuantity(distrInNoticeDetail.getQuantity());
            distrInReceiveDetailVO.setRefuseQuantity(BigDecimal.ZERO);
            distrInReceiveDetailVOS.add(distrInReceiveDetailVO);

            if(null != goods.getSpecialDrug() && SpecialDrugs.getSpecialDrugsCodes().contains(goods.getSpecialDrug())){
                distrInReceiveVO.setSpecialDrug(1);
            }

            if(null != goods.getSpecialDrug() && gspFlag && SpecialDrugs.getSpecialDrugsCodes().contains(goods.getSpecialDrug())){
                distrInReceiveVO.setSecondReceiverId(loginUser.getUserId());
                distrInReceiveVO.setSecondReceiverCode(loginUser.getUserCode());
                distrInReceiveVO.setSecondReceiverName(loginUser.getUserName());
            }
        }
        distrInReceiveVO.setDistrInReceiveDetailVOList(distrInReceiveDetailVOS);
        return distrInReceiveVO;
    }

    private void getReturnNotice(DistrInReceive distrInReceive, List<DistrInReceiveDetail> distrInReceiveDetails, UserVO loginUser) throws Exception{
        //如果拒收数量不为0,则生成总部的配进退回通知单
        Set<Long> goodsIdSet = new HashSet<>();
        BigDecimal quantityTotal = BigDecimal.ZERO;//数量合计
        BigDecimal amountTotal = BigDecimal.ZERO;//金额合计
        BigDecimal realAmountTotal = BigDecimal.ZERO;//实际金额合计
        BigDecimal notaxRealAmountTotal = BigDecimal.ZERO;//不含税金额合计
        BigDecimal taxAmountTotal = BigDecimal.ZERO;//税额合计
        //先获取配货单位信息
//        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(distrInReceive.getDistrUnitId());
        //待生成门店配进订单细单集合
        List<DistrReturnNoticeDtlVO> distrReturnNoticeDetails = new ArrayList<>();
        for(DistrInReceiveDetail distrInReceiveDetail : distrInReceiveDetails){
            int lineNum = 1;
            if (distrInReceiveDetail.getRefuseQuantity().compareTo(BigDecimal.ZERO) == 0)
                continue;
            if (distrInReceiveDetail.getRefuseQuantity().compareTo(BigDecimal.ZERO) > 0) {
                DistrReturnNoticeDtlVO distrReturnNoticeDetail = new DistrReturnNoticeDtlVO();
                //set 创建人信息
                UserEnterpriseUtils.setUserCreateOrModify(distrReturnNoticeDetail, loginUser, true);
                //set 基础单据信息(收货单的信息)
                distrReturnNoticeDetail.setBaseOrderId(distrInReceiveDetail.getReceiveId());
                distrReturnNoticeDetail.setBaseOrderCode(distrInReceiveDetail.getBaseOrderCode());
                distrReturnNoticeDetail.setBaseOrderDate(distrInReceiveDetail.getReceiveDate());
                distrReturnNoticeDetail.setBaseOrderDtlId(distrInReceiveDetail.getId());
                distrReturnNoticeDetail.setBaseOrderType(distrInReceiveDetail.getOrderType());
                //set 商品信息
                distrReturnNoticeDetail.setGoodsId(distrInReceiveDetail.getGoodsId());
//                distrReturnNoticeDetail.setGoodsName(distrInReceiveDetail.getGoodsName());
                distrReturnNoticeDetail.setGoodsCode(distrInReceiveDetail.getGoodsCode());
//                distrReturnNoticeDetail.setBarcode(distrInReceiveDetail.getBarcode());
                distrReturnNoticeDetail.setGoodsGenericName(distrInReceiveDetail.getGoodsGenericName());
                distrReturnNoticeDetail.setDosageId(distrInReceiveDetail.getDosageId());
                distrReturnNoticeDetail.setDosageName(distrInReceiveDetail.getDosageName());
                distrReturnNoticeDetail.setUnitId(distrInReceiveDetail.getUnitId());
                distrReturnNoticeDetail.setUnitName(distrInReceiveDetail.getUnitName());
                distrReturnNoticeDetail.setGoodsSpecification(distrInReceiveDetail.getGoodsSpecification());
                distrReturnNoticeDetail.setManufacturerId(distrInReceiveDetail.getManufacturerId());
                distrReturnNoticeDetail.setManufacturer(distrInReceiveDetail.getManufacturer());
                distrReturnNoticeDetail.setGoodsPlace(distrInReceiveDetail.getGoodsPlace());
//                distrReturnNoticeDetail.setApprovalNumber(distrInReceiveDetail.getApprovalNumber());
                //set 数量(拒收数量)
                distrReturnNoticeDetail.setQuantity(distrInReceiveDetail.getRefuseQuantity());
                //set 单价(配退通知单上的单价)
                DistrInNoticeDetail noticeDetail = distrInNoticeDetailMapper.selectByPrimaryKey(distrInReceiveDetail.getBaseOrderDtlId());
                distrReturnNoticeDetail.setUnitPrice(noticeDetail.getUnitPrice());
                //set 行折扣
                distrReturnNoticeDetail.setLineDiscount(noticeDetail.getLineDiscount());
                //set 金额(数量*单价*行折扣)
                distrReturnNoticeDetail.setAmount(distrReturnNoticeDetail.getQuantity().multiply(noticeDetail.getUnitPrice().multiply(noticeDetail.getLineDiscount())).setScale(2, RoundingMode.HALF_UP));
                //set 整单折扣
                distrReturnNoticeDetail.setWholeDiscount(noticeDetail.getWholeDiscount());
                //set 行优惠
                distrReturnNoticeDetail.setLineDiscountAmount(noticeDetail.getLineDiscountAmount());
                //set 实际金额(数量*单价*行折扣*整单折扣-优惠分摊)
                distrReturnNoticeDetail.setRealAmount(distrReturnNoticeDetail.getQuantity().multiply(noticeDetail.getLineDiscount().multiply(noticeDetail.getWholeDiscount())).setScale(2, RoundingMode.HALF_UP).subtract(noticeDetail.getLineDiscountAmount()));
                //set 实际单价(实际金额/数量)
                distrReturnNoticeDetail.setRealPrice(distrReturnNoticeDetail.getRealAmount().divide(distrReturnNoticeDetail.getQuantity(), 2, RoundingMode.HALF_UP));
                //税率id/税率
                distrReturnNoticeDetail.setTaxRateId(noticeDetail.getTaxRateId());
                distrReturnNoticeDetail.setTaxRate(noticeDetail.getTaxRate());
                //set 不含税实际单价(实际单价/(1+税率))
                distrReturnNoticeDetail.setNotaxRealPrice(distrReturnNoticeDetail.getRealPrice().divide(noticeDetail.getTaxRate().divide(new BigDecimal(100)).add(BigDecimal.ONE), 2, RoundingMode.HALF_UP));
                //set 不含税实际金额(实际金额/(1+税率))
                distrReturnNoticeDetail.setNotaxRealAmount(distrReturnNoticeDetail.getRealAmount().divide(noticeDetail.getTaxRate().divide(new BigDecimal(100)).add(BigDecimal.ONE), 2, RoundingMode.HALF_UP));
                //set 税额(实际金额-不含税金额)
                distrReturnNoticeDetail.setTaxAmount(distrReturnNoticeDetail.getRealAmount().subtract(distrReturnNoticeDetail.getNotaxRealAmount()));
                //set 未清数量/以清数量
                distrReturnNoticeDetail.setUnclearQuantity(distrReturnNoticeDetail.getQuantity());
                distrReturnNoticeDetail.setClearQuantity(BigDecimal.ZERO);
                //set 行号
                distrReturnNoticeDetail.setLineNum(lineNum);
                lineNum++;
                //set 备注
                distrReturnNoticeDetail.setRemark(distrInReceiveDetail.getRemark());
                //总单金额信息
                goodsIdSet.add(distrReturnNoticeDetail.getGoodsId());
                quantityTotal = quantityTotal.add(distrReturnNoticeDetail.getQuantity());
                amountTotal = amountTotal.add(distrReturnNoticeDetail.getAmount());
                realAmountTotal = realAmountTotal.add(distrReturnNoticeDetail.getRealAmount());
                notaxRealAmountTotal = notaxRealAmountTotal.add(distrReturnNoticeDetail.getNotaxRealAmount());
                taxAmountTotal = taxAmountTotal.add(distrReturnNoticeDetail.getTaxAmount());
                distrReturnNoticeDetails.add(distrReturnNoticeDetail);
            }
            if (distrReturnNoticeDetails.size() > 0) {
                DistrReturnNoticeFormVO distrReturnNoticeFormVO = new DistrReturnNoticeFormVO();
                //set 订单日期
                distrReturnNoticeFormVO.setNoticeDate(new Date());
                //set 基础单据信息
                distrReturnNoticeFormVO.setBaseOrderId(distrInReceive.getId());
                distrReturnNoticeFormVO.setBaseOrderCode(distrInReceive.getCode());
                distrReturnNoticeFormVO.setBaseOrderDate(distrInReceive.getBaseOrderDate());
                distrReturnNoticeFormVO.setBaseOrderType(distrInReceive.getOrderType());
                //set 配货类型（0-总部配送；3-分店间调剂；4-直调配送）
                distrReturnNoticeFormVO.setDistrType(0);
                //set 数量合计
                distrReturnNoticeFormVO.setQuantityTotal(quantityTotal);
                //set 金额合计
                distrReturnNoticeFormVO.setAmountTotal(amountTotal);
                //set 整单折扣(从通知单取)
                DistrInNotice distrInNotice = distrInNoticeMapper.selectByPrimaryKey(distrInReceive.getBaseOrderId());
                distrReturnNoticeFormVO.setWholeDiscount(distrInNotice.getWholeDiscount());
                //set 整单优惠金额(从通知单取)
                distrReturnNoticeFormVO.setWholeDiscountAmount(distrInNotice.getWholeDiscountAmount());
                //set 实际金额合计
                distrReturnNoticeFormVO.setRealAmountTotal(realAmountTotal);
                //set 不含税金额合计
                distrReturnNoticeFormVO.setNotaxRealAmountTotal(notaxRealAmountTotal);
                //set 税额合计
                distrReturnNoticeFormVO.setTaxAmountTotal(taxAmountTotal);
                //set 备注
                distrReturnNoticeFormVO.setRemark(distrInReceive.getRemark());
                //set 状态
                distrReturnNoticeFormVO.setStatus(PubStatus.distrReturnNoticeStatus.WAIT_RECEIVE);
                //set 要货单位信息
                distrReturnNoticeFormVO.setRequestUnitId(loginUser.getParentId());
                Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(loginUser.getParentId());
                distrReturnNoticeFormVO.setRequestUnitCode(enterprise.getCode());
                distrReturnNoticeFormVO.setRequestUnitName(enterprise.getName());
                //set 细单集合
                distrReturnNoticeFormVO.setDistrReturnNoticeDtlVOList(distrReturnNoticeDetails);
                distrComponent.saveInNoticeStatus4Parent(distrReturnNoticeFormVO,loginUser);
            }
        }
    }


    @Override
    public void getDistrInReceiveList(Page<OrderReportVo<DistrInReceiveReportVo>> page, RequestDistrInReceive requestDistrInReceive) {
        if(requestDistrInReceive.getPageNo()!=null &&requestDistrInReceive.getPageSize()!=null){
            requestDistrInReceive.setPageNo(page.getStart());
        }
        int count=distrInReceiveMapper.getDistrInReceiveListCount(requestDistrInReceive);
        Integer sortDate=requestDistrInReceive.getSortDate();
        Integer sortCode=requestDistrInReceive.getSortCode();
        String sort="";
        if(sortDate==null&&sortCode==null){
            sort="";
        }
        if(sortDate!=null&&sortDate==0){
            sort+="a.receive_date,";
        }
        if(sortDate!=null&&sortDate==1){
            sort+="a.receive_date desc,";
        }
        if(sortCode!=null&&sortCode==0){
            sort+="a.code,";
        }
        if(sortCode!=null&&sortCode==1){
            sort+="a.code desc,";
        }
        if(!"".equals(sort)){
            sort=sort.substring(0,sort.length()-1);
        }
        requestDistrInReceive.setSort(sort);
        List<DistrInReceiveReportVo> distrInReceiveReportVos=distrInReceiveMapper.getDistrInReceiveList(requestDistrInReceive);
        OrderReportVo<DistrInReceiveReportVo> orderReportVo=new OrderReportVo<>();
        orderReportVo.setDataList(distrInReceiveReportVos);
        requestDistrInReceive.setPageNo(null);
        requestDistrInReceive.setPageSize(null);
        List<DistrInReceiveReportVo> distrInReceiveReportVos2=distrInReceiveMapper.getDistrInReceiveList(requestDistrInReceive);
        BigDecimal quantity = BigDecimal.ZERO;
        BigDecimal arrivalQuantity = BigDecimal.ZERO;
        BigDecimal receiveQuantity = BigDecimal.ZERO;
        BigDecimal refuseQuantity = BigDecimal.ZERO;
        for (DistrInReceiveReportVo d : distrInReceiveReportVos2) {
            quantity=quantity.add(d.getQuantity()==null?BigDecimal.ZERO:d.getQuantity());
            arrivalQuantity=arrivalQuantity.add(d.getArrivalQuantity()==null?BigDecimal.ZERO:d.getArrivalQuantity());
            receiveQuantity=receiveQuantity.add(d.getReceiveQuantity()==null?BigDecimal.ZERO:d.getReceiveQuantity());
            refuseQuantity=refuseQuantity.add(d.getRefuseQuantity()==null?BigDecimal.ZERO:d.getRefuseQuantity());
        }
        orderReportVo.setQuantity(quantity);
        orderReportVo.setArrivalQuantity(arrivalQuantity);
        orderReportVo.setReceiveQuantity(receiveQuantity);
        orderReportVo.setRefuseQuantity(refuseQuantity);
        page.setTotalRecord(count);
        page.setResult(orderReportVo);
    }

    @Override
    public void excelExportReport(OutputStream output, List<DistrInReceiveReportVo> distrInReceiveReportVos, UserVO userVO) {
        List<String> names = new ArrayList<>();
        names.add(userVO.getEnterpriseName());
        names.add("配进收货单");
        //内容数据
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("enterpriseCode", "组织机构编码");
        map.put("enterpriseName", "组织机构名称");
        map.put("receiveDateStr", "日期");
        map.put("code", "单号");
        map.put("distrUnitCode", "配货单位编码");
        map.put("distrUnitName", "配货单位名称");
        map.put("receiverName", "收货人员");
        map.put("secondReceiverName", "收货人员2");
        map.put("distrOutCode", "配货单号");
        map.put("baseOrderCode", "配进订单单号");
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
        map.put("quantity", "配后退回数量");
        map.put("arrivalQuantity", "到货数量");
        map.put("receiveQuantity", "收货数量");
        map.put("refuseQuantity", "拒收数量");
        map.put("refuseReason", "拒收原因");
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
        BigDecimal arrivalQuantity = BigDecimal.ZERO;
        BigDecimal receiveQuantity = BigDecimal.ZERO;
        BigDecimal refuseQuantity = BigDecimal.ZERO;
        for (DistrInReceiveReportVo d : distrInReceiveReportVos) {
            quantity=quantity.add(d.getQuantity()==null?BigDecimal.ZERO:d.getQuantity());
            arrivalQuantity=arrivalQuantity.add(d.getArrivalQuantity()==null?BigDecimal.ZERO:d.getArrivalQuantity());
            receiveQuantity=receiveQuantity.add(d.getReceiveQuantity()==null?BigDecimal.ZERO:d.getReceiveQuantity());
            refuseQuantity=refuseQuantity.add(d.getRefuseQuantity()==null?BigDecimal.ZERO:d.getRefuseQuantity());
        }
        endTotal.append(quantity);
        endTotal.append(";");
        endTotal.append(arrivalQuantity);
        endTotal.append(";");
        endTotal.append(receiveQuantity);
        endTotal.append(";");
        endTotal.append(refuseQuantity);
        List<String> locationList = new ArrayList<String>();
        locationList.add("quantity");
        locationList.add("arrivalQuantity");
        locationList.add("receiveQuantity");
        locationList.add("refuseQuantity");
        purchaseGeneralComponent.commExcelExport(output, map, distrInReceiveReportVos, names, null, endTotal.toString(), false, locationList);
    }

    @Override
    public boolean checkReceived(UserVO loginUser, DistrInReceiveSaveVO distrInReceiveSaveVO) throws Exception {
        List<DistrInReceive> distrInReceives = distrInReceiveMapper.selectByEnterpriseIdByBaseOrderId(loginUser.getEnterpriseId(),distrInReceiveSaveVO.getBaseOrderId());
        return distrInReceives.isEmpty();
    }
}
