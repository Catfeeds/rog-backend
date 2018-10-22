package com.rograndec.feijiayun.chain.business.distr.parent.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.distr.branch.constant.DistrType;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnNoticeDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnNoticeMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnReceiveDetailMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.dao.DistrReturnReceiveMapper;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNotice;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnNoticeDetail;
import com.rograndec.feijiayun.chain.business.distr.parent.entity.DistrReturnReceive;
import com.rograndec.feijiayun.chain.business.distr.parent.service.DistrReturnReceiveService;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnReceiveDetailVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnReceiveSaveVO;
import com.rograndec.feijiayun.chain.business.distr.parent.vo.DistrReturnReceiveVO;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.service.PurchaseReceiveService;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.Result;
import com.rograndec.feijiayun.chain.common.component.*;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.status.PubStatus;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

@Service
public class DistrReturnReceiveServiceImpl implements DistrReturnReceiveService {
    @Autowired
    DistrReturnReceiveMapper distrReturnReceiveMapper;
    @Autowired
    DistrReturnReceiveDetailMapper distrReturnReceiveDetailMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    QualitySetMapper qualitySetMapper;
    @Autowired
    DistrReturnNoticeMapper distrReturnNoticeMapper;
    @Autowired
    DistrReturnNoticeDetailMapper distrReturnNoticeDetailMapper;
    @Autowired
    OrderCodeComponent orderCodeComponent;
    @Autowired
    PriceOrderDetailMapper priceOrderDetailMapper;
    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    PurchaseReceiveService purchaseReceiveService;

    @Autowired
    DistrComponent distrComponent;
    @Autowired
    ManageConfigComponent manageConfigComponent;
    @Autowired
    PurchaseGeneralComponent purchaseGeneralComponent;

    @Autowired
    private CommonComponent commonComponent;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Map<String,Object>> saveDistrReturnReceiveOrder(UserVO loginUser, DistrReturnReceiveSaveVO distrReturnReceiveSaveVO) throws Exception {

        return commonComponent.generateAndSaveDistrReturnReceiveOrder(loginUser,distrReturnReceiveSaveVO);
    }

    @Override
    public Page<List<DistrReturnReceiveVO>> getDistrReturnReceiveOrderList(UserVO loginUser, Date startDate, Date endDate, String requestUnitCode, String requestUnitName, String code, Integer distrType, Integer status, String receiverName, String secondReceiverName, String orderName, String orderType, Integer pageNo, Integer pageSize) throws Exception {
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
        Page<List<DistrReturnReceiveVO>> page = new Page<>(pageNo, pageSize);
//        com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<DistrReturnReceiveVO> distrReturnReceiveVOS = distrReturnReceiveMapper.selectByEnterpriseId(loginUser.getEnterpriseId(), startDate, endDate, requestUnitCode, requestUnitName, code, distrType, status, receiverName, secondReceiverName, orderName, orderType,(pageNo-1)*pageSize,pageSize);
        for(DistrReturnReceiveVO distrReturnReceiveVO : distrReturnReceiveVOS){
            distrReturnReceiveVO.setStatusName(PubStatus.distrReturnNoticeStatus.getStatusDesc(distrReturnReceiveVO.getStatus()));
            distrReturnReceiveVO.setDistrTypeName(DistributionType.getName(distrReturnReceiveVO.getDistrType()));
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(distrReturnReceiveVO.getRequestUnitId());
            if(enterprise.getChainType() == ChainType.Division.getCode()){
                distrReturnReceiveVO.setFranchisedStoreFlag(1);
            }else{
                distrReturnReceiveVO.setFranchisedStoreFlag(0);
            }

        }
        page.setResult(distrReturnReceiveVOS);
        page.setTotalRecord(distrReturnReceiveMapper.selectCountByEnterpriseId(loginUser.getEnterpriseId(), startDate, endDate, requestUnitCode, requestUnitName, code, distrType, status, receiverName, secondReceiverName, orderName, orderType));
        return page;
    }

    @Override
    public DistrReturnReceiveVO getDistrReturnReceiveOrderDtlList(UserVO loginUser, Long receiveId) throws Exception {
        List<DistrReturnReceiveDetailVO> distrReturnReceiveDetailVOS = distrReturnReceiveDetailMapper.selectByReceiveId(receiveId, loginUser.getEnterpriseId());
        //set 拒收原因
        for (DistrReturnReceiveDetailVO distrReturnReceiveDetailVO : distrReturnReceiveDetailVOS) {
            String refuseReasonIds = distrReturnReceiveDetailVO.getRefuseReasonIds();
            distrReturnReceiveDetailVO.setRefuseReason(purchaseReceiveService.getRefuseReasons(refuseReasonIds, loginUser));
            Goods goods = goodsMapper.selectByPrimaryKey(distrReturnReceiveDetailVO.getGoodsId());
            if(goods == null){
                throw new BusinessException("无效的商品ID："+ distrReturnReceiveDetailVO.getGoodsId());
            }
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(goods.getEnterpriseId());
            if(enterprise.getChainType() == ChainType.Division.getCode()){
                distrReturnReceiveDetailVO.setFranchisedStoreFlag(1);
            }else{
                distrReturnReceiveDetailVO.setFranchisedStoreFlag(0);
            }

        }
        DistrReturnReceive distrReturnReceive = distrReturnReceiveMapper.selectByPrimaryKey(receiveId);
        DistrReturnReceiveVO distrReturnReceiveVO = new DistrReturnReceiveVO();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(distrReturnReceive,distrReturnReceiveVO);
        distrReturnReceiveVO.setStatusName(PubStatus.distrReturnNoticeStatus.getStatusDesc(distrReturnReceiveVO.getStatus()));
        distrReturnReceiveVO.setDistrTypeName(distrReturnReceiveVO.getDistrType()==0?"总部配送":distrReturnReceiveVO.getDistrType()==1?"分店间调剂":"直调配送");
        distrReturnReceiveVO.setDistrReturnReceiveDetailVOS(distrReturnReceiveDetailVOS);
        //为了打印数据添加的三个字段到货数量合计，收货数量合计，拒收数量合计
        BigDecimal arrivalQuantityTotal = BigDecimal.ZERO;
        BigDecimal receiveQuantityTotal = BigDecimal.ZERO;
        BigDecimal refuseQuantityTotal = BigDecimal.ZERO;
        for (DistrReturnReceiveDetailVO d : distrReturnReceiveDetailVOS) {
            arrivalQuantityTotal = arrivalQuantityTotal.add(d.getArrivalQuantity());
            receiveQuantityTotal = receiveQuantityTotal.add(d.getReceiveQuantity());
            refuseQuantityTotal = refuseQuantityTotal.add(d.getRefuseQuantity());
        }
        distrReturnReceiveVO.setArrivalQuantityTotal(arrivalQuantityTotal);
        distrReturnReceiveVO.setReceiveQuantityTotal(receiveQuantityTotal);
        distrReturnReceiveVO.setRefuseQuantityTotal(refuseQuantityTotal);
        distrReturnReceiveVO.setEnterpriseName(loginUser.getEnterpriseName());
        return distrReturnReceiveVO;
    }

    @Override
    public void exportExcel(OutputStream output, Long id, UserVO loginUser) {
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
        DistrReturnReceive distrReturnReceive = distrReturnReceiveMapper.selectByPrimaryKey(id);
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("配货单位编码:");
        titleSecondRow.append(distrReturnReceive.getRequestUnitCode());
        titleSecondRow.append("  配货单位名称:");
        titleSecondRow.append(distrReturnReceive.getRequestUnitName());
        titleSecondRow.append("  配后退回收货单号:");
        titleSecondRow.append(distrReturnReceive.getCode());
        titleSecondRow.append("  收货日期:");
        //日期转成yyyy-mm-dd形式
        titleSecondRow.append(distrReturnReceive.getReceiveDate());
        titleSecond.add(titleSecondRow.toString());
        //标题栏下第二行
        titleSecondRow = new StringBuilder();
        titleSecondRow.append("收货人员1:");
        titleSecondRow.append(distrReturnReceive.getReceiverName());
        titleSecondRow.append("  收货人员2:");
        titleSecondRow.append(distrReturnReceive.getSecondReceiverName());
        titleSecondRow.append("  配进类型:");
        titleSecondRow.append(distrReturnReceive.getDistrType()==0?"总部配送":distrReturnReceive.getDistrType()==1?"分店间调剂":"直调配送");
        titleSecondRow.append("  备注:");
        titleSecondRow.append(distrReturnReceive.getRemark());
        titleSecond.add(titleSecondRow.toString());
        StringBuilder end = new StringBuilder();
        BigDecimal arrivalQuantity = BigDecimal.ZERO;//到货数量
        BigDecimal receiveQuantity = BigDecimal.ZERO;//收货数量
        BigDecimal refuseQuantity = BigDecimal.ZERO;//拒收数量
        List<DistrReturnReceiveDetailVO> distrReturnReceiveDetailVOS = distrReturnReceiveDetailMapper.selectByReceiveId(id,loginUser.getEnterpriseId());
        for (DistrReturnReceiveDetailVO distrReturnReceiveDetailVO : distrReturnReceiveDetailVOS){
            arrivalQuantity = arrivalQuantity.add(distrReturnReceiveDetailVO.getArrivalQuantity());
            receiveQuantity = receiveQuantity.add(distrReturnReceiveDetailVO.getReceiveQuantity());
            refuseQuantity = receiveQuantity.add(distrReturnReceiveDetailVO.getRefuseQuantity());
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
        name.add("配后退回收货单");
        purchaseGeneralComponent.commExcelExport(output,map,distrReturnReceiveDetailVOS,name,titleSecond,end.toString(),false,needTotalName);

    }

    @Override
    public DistrReturnReceiveVO showWillSaveList(UserVO loginUser, Long id) throws Exception {
        DistrReturnReceiveVO distrReturnReceiveVO = new DistrReturnReceiveVO();
        DistrReturnNotice distrReturnNotice = distrReturnNoticeMapper.selectByPrimaryKey(id);
        //set 基础单据信息
        distrReturnReceiveVO.setBaseOrderId(distrReturnNotice.getId());
        distrReturnReceiveVO.setBaseOrderCode(distrReturnNotice.getCode());
        distrReturnReceiveVO.setBaseOrderDate(distrReturnNotice.getNoticeDate());
        distrReturnReceiveVO.setBaseOrderType(distrReturnNotice.getOrderType());
        //set 配货单位编码/名称/配货类型
        distrReturnReceiveVO.setRequestUnitId(distrReturnNotice.getRequestUnitId());
        distrReturnReceiveVO.setRequestUnitCode(distrReturnNotice.getRequestUnitCode());
        distrReturnReceiveVO.setRequestUnitName(distrReturnNotice.getRequestUnitName());
        distrReturnReceiveVO.setDistrType(distrReturnNotice.getDistrType());
        distrReturnReceiveVO.setDistrTypeName(DistrType.getValue(distrReturnNotice.getDistrType()));
        //set数量合计/品种合计
        distrReturnReceiveVO.setQuantityTotal(distrReturnNotice.getQuantityTotal());
        distrReturnReceiveVO.setVarietiesQuantity(distrReturnNotice.getVarietiesQuantity());
        List<DistrReturnReceiveDetailVO> distrReturnReceiveDetailVOS = new ArrayList<>();
        List<DistrReturnNoticeDetail> distrReturnNoticeDetails = distrReturnNoticeDetailMapper.selectByEnterpriseIdAndNoticeId(loginUser.getEnterpriseId(),id);
        for (DistrReturnNoticeDetail distrReturnNoticeDetail : distrReturnNoticeDetails){
            DistrReturnReceiveDetailVO distrReturnReceiveDetailVO = new DistrReturnReceiveDetailVO();
            //set 商品信息
            distrReturnReceiveDetailVO.setGoodsId(distrReturnNoticeDetail.getGoodsId());
            distrReturnReceiveDetailVO.setGoodsName(distrReturnNoticeDetail.getGoodsName());
            distrReturnReceiveDetailVO.setGoodsCode(distrReturnNoticeDetail.getGoodsCode());
            distrReturnReceiveDetailVO.setBarcode(distrReturnNoticeDetail.getBarcode());
            distrReturnReceiveDetailVO.setGoodsGenericName(distrReturnNoticeDetail.getGoodsGenericName());
            distrReturnReceiveDetailVO.setDosageId(distrReturnNoticeDetail.getDosageId());
            distrReturnReceiveDetailVO.setDosageName(distrReturnNoticeDetail.getDosageName());
            distrReturnReceiveDetailVO.setUnitId(distrReturnNoticeDetail.getUnitId());
            distrReturnReceiveDetailVO.setUnitName(distrReturnNoticeDetail.getUnitName());
            distrReturnReceiveDetailVO.setGoodsSpecification(distrReturnNoticeDetail.getGoodsSpecification());
            distrReturnReceiveDetailVO.setManufacturerId(distrReturnNoticeDetail.getManufacturerId());
            distrReturnReceiveDetailVO.setManufacturer(distrReturnNoticeDetail.getManufacturer());
            distrReturnReceiveDetailVO.setGoodsPlace(distrReturnNoticeDetail.getGoodsPlace());
            distrReturnReceiveDetailVO.setApprovalNumber(distrReturnNoticeDetail.getApprovalNumber());
            //set 基础单据信息
            distrReturnReceiveDetailVO.setBaseOrderId(distrReturnNoticeDetail.getNoticeId());
            distrReturnReceiveDetailVO.setBaseOrderCode(distrReturnNoticeDetail.getNoticeCode());
            distrReturnReceiveDetailVO.setBaseOrderDate(distrReturnNoticeDetail.getNoticeDate());
            distrReturnReceiveDetailVO.setBaseOrderDtlId(distrReturnNoticeDetail.getId());
            distrReturnReceiveDetailVO.setBaseOrderType(distrReturnNoticeDetail.getOrderType());
            //set 到货数量(订单的数量) 拒收数量默认为0 收货数量默认到货数量
            distrReturnReceiveDetailVO.setArrivalQuantity(distrReturnNoticeDetail.getQuantity());
            distrReturnReceiveDetailVO.setReceiveQuantity(distrReturnNoticeDetail.getQuantity());
            distrReturnReceiveDetailVO.setRefuseQuantity(BigDecimal.ZERO);
            distrReturnReceiveDetailVOS.add(distrReturnReceiveDetailVO);
        }
        distrReturnReceiveVO.setDistrReturnReceiveDetailVOS(distrReturnReceiveDetailVOS);
        return distrReturnReceiveVO;
    }

    @Override
    public Boolean isSpecialDrug(UserVO loginUser, Long id) throws Exception {
        List<Integer> specialDrugs = distrReturnNoticeDetailMapper.selectSpecialDrugByEnterpriseIdAndNoticeId(loginUser.getEnterpriseId(),id);
        return specialDrugs.contains(0) || specialDrugs.contains(1) || specialDrugs.contains(2) || specialDrugs.contains(3);
    }

    @Override
    public boolean checkReceived(UserVO loginUser, DistrReturnReceiveSaveVO distrReturnReceiveSaveVO) throws Exception {
        List<DistrReturnReceive> distrReturnReceives = distrReturnReceiveMapper.selectByEnterpriseIdByBaseOrderId(loginUser.getEnterpriseId(),distrReturnReceiveSaveVO.getBaseOrderId());
        return distrReturnReceives.isEmpty();
    }

}