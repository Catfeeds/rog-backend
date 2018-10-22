package com.rograndec.feijiayun.chain.business.purchase.receive.service.impl;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.SimpleUserVO;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.goods.info.entity.Goods;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.dao.PurchaseOrderOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrder;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderDetail;
import com.rograndec.feijiayun.chain.business.purchase.order.entity.PurchaseOrderOther;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderDetailVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.PurchaseOrderOtherRequestVO;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveDetailMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.dao.PurchaseReceiveOtherMapper;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceive;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceiveDetail;
import com.rograndec.feijiayun.chain.business.purchase.receive.entity.PurchaseReceiveOther;
import com.rograndec.feijiayun.chain.business.purchase.receive.service.PurchaseReceiveService;
import com.rograndec.feijiayun.chain.business.purchase.receive.vo.*;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.dao.ManageConfigMapper;
import com.rograndec.feijiayun.chain.business.system.set.dao.QualitySetMapper;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.component.CalculateComponent;
import com.rograndec.feijiayun.chain.common.component.OrderCodeComponent;
import com.rograndec.feijiayun.chain.common.component.PurchaseGeneralComponent;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.constant.DistributionType;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.SpecialDrugs;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.file.dao.FileMapper;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import com.rograndec.feijiayun.chain.utils.bean.CopyFromNotNullBeanUtils;
import com.rograndec.feijiayun.chain.utils.user.UserEnterpriseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by madong on 2017/9/13.
 */
@Service
public class PurchaseReceiveServiceImpl implements PurchaseReceiveService {
    @Autowired
    PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    PurchaseOrderDetailMapper purchaseOrderDetailMapper;
    @Autowired
    PurchaseOrderOtherMapper purchaseOrderOtherMapper;
    @Autowired
    PurchaseReceiveMapper purchaseReceiveMapper;
    @Autowired
    PurchaseReceiveDetailMapper purchaseReceiveDetailMapper;
    @Autowired
    PurchaseReceiveOtherMapper purchaseReceiveOtherMapper;
    @Autowired
    ManageConfigMapper manageConfigMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    QualitySetMapper qualitySetMapper;
    @Autowired
    EnterpriseMapper enterpriseMapper;
    @Autowired
    PurchaseGeneralComponent<PurchaseReceiveDetailVO> purchaseGeneralComponent;
    @Autowired
    OrderCodeComponent orderCodeComponent;
    @Autowired
    FileMapper fileMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public Page getReceive(UserVO loginUser, Integer pageNo, Integer isReceive, Integer pageSize, String orderName,
                           String orderType, String startTime, String endTime, String supplierCode,
                           String supplierName, String code, String receiverName) throws Exception {
        Map param = new HashMap();
        param.put("start",(pageNo-1)*pageSize);
        param.put("pageSize",pageSize);
        if(orderName == null || ("").equals(orderName)){
            orderName = null;
        }else if(("orderDate").equals(orderName.trim())){
            orderName = "order_date";
        }else if(("code").equals(orderName.trim())){
            orderName = "code";
        }else if(("receiveDate").equals(orderName.trim())){
            orderName = "receive_date";
        }
        param.put("orderName",orderName);
        param.put("orderType",(orderType == null || ("").equals(orderType))?null:orderType);
        param.put("startTime",(startTime == null || ("").equals(startTime))?null:startTime);
        param.put("endTime",(endTime == null || ("").equals(endTime))?null:endTime);
        param.put("supplierCode",(supplierCode == null || ("").equals(supplierCode))?null:supplierCode);
        param.put("supplierName",(supplierName == null || ("").equals(supplierName))?null:supplierName);
        param.put("code",(code == null || ("").equals(code))?null:code);
        param.put("receiverName",(receiverName == null || ("").equals(receiverName))?null:receiverName);
        param.put("enterpriseId",loginUser.getEnterpriseId());
        param.put("status", PurchaseStatus.WAIT_RECEIVE.getStatus());
        Page page = new Page(pageNo,pageSize);
        if(isReceive==0)
            return getWillReceive(param,page,getGspFlag(loginUser));
        else
            return getReceived(param,page,getGspFlag(loginUser));
    }

    @Override
    public PurchaseOrderRequestVO getOrderDetail(UserVO loginUser, Long id) throws Exception {
        List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailMapper.selectByEnterpriseIdByOrderId(loginUser.getEnterpriseId(),id);
        List<PurchaseOrderDetailVO> purchaseOrderDetailVOS = new ArrayList<>();
        List<Integer> specialDrus = new ArrayList<>();

        for (PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetails){
            PurchaseOrderDetailVO purchaseOrderDetailVO = new PurchaseOrderDetailVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchaseOrderDetail,purchaseOrderDetailVO);
            Goods goods = goodsMapper.selectByPrimaryKey(purchaseOrderDetail.getGoodsId());
            purchaseOrderDetailVO.setSpecialDrug(goods.getSpecialDrug());

            purchaseOrderDetailVOS.add(purchaseOrderDetailVO);
        }

        PurchaseOrderRequestVO purchaseOrderRequestVO = new PurchaseOrderRequestVO();
        purchaseOrderRequestVO.setPurchaseOrderDetailVOS(purchaseOrderDetailVOS);
        //总单
        PurchaseReceiveOrderVO purchaseReceiveOrderVO = new PurchaseReceiveOrderVO();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchaseOrderMapper.selectByPrimaryKey(id),purchaseReceiveOrderVO);
        //设置默认收货人员
        purchaseReceiveOrderVO.setReceiverId(loginUser.getUserId());
        purchaseReceiveOrderVO.setReceiverCode(loginUser.getUserCode());
        purchaseReceiveOrderVO.setReceiverName(loginUser.getUserName());
        purchaseReceiveOrderVO.setGspFlag(getGspFlag(loginUser));
        purchaseOrderRequestVO.setPurchaseReceiveOrderVO(purchaseReceiveOrderVO);

        /**
         * 查库明细行是否有特殊药品
         *
         */

        Boolean gspFlag = getGspFlag(loginUser);

        for (PurchaseOrderDetailVO purchaseOrderDetail : purchaseOrderDetailVOS){

            if(null != purchaseOrderDetail.getSpecialDrug() && SpecialDrugs.getSpecialDrugsCodes().contains(purchaseOrderDetail.getSpecialDrug())){
                purchaseOrderRequestVO.getPurchaseReceiveOrderVO().setSpecialDrug(1);

            }

            if(null != purchaseOrderDetail.getSpecialDrug() && gspFlag && SpecialDrugs.getSpecialDrugsCodes().contains(purchaseOrderDetail.getSpecialDrug())){
                purchaseOrderRequestVO.getPurchaseReceiveOrderVO().setSecondReceiverId(loginUser.getUserId());
                purchaseOrderRequestVO.getPurchaseReceiveOrderVO().setSecondReceiverCode(loginUser.getUserCode());
                purchaseOrderRequestVO.getPurchaseReceiveOrderVO().setSecondReceiverName(loginUser.getUserName());
            }

        }
        return purchaseOrderRequestVO;
    }

    @Override
    public PurchaseOrderOtherRequestVO getOrderSet(UserVO loginUser, Long id) throws Exception {
        PurchaseOrderOther purchaseOrderOther = purchaseOrderOtherMapper.selectByEnterpriseIdByOrderId(loginUser.getEnterpriseId(),id);
        PurchaseOrderOtherRequestVO purchaseOrderOtherVO = new PurchaseOrderOtherRequestVO();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchaseOrderOther,purchaseOrderOtherVO);
        return purchaseOrderOtherVO;
    }

    @Override
    public PurchaseReceiveRequestVO getReceiveDetail(UserVO loginUser, Long id) throws Exception {
        List<PurchaseReceiveDetail> purchaseReceiveDetails = purchaseReceiveDetailMapper.selectByEnterpriseIdByReceiveId(loginUser.getEnterpriseId(),id);
        List<PurchaseReceiveDetailVO> purchaseReceiveDetailVOS = new ArrayList<>();
        
        BigDecimal arrivalQuantityTotal = BigDecimal.ZERO;//到货数量合计
        BigDecimal receiveQuantityTotal = BigDecimal.ZERO;//收货数量合计
        BigDecimal refuseQuantityTotal = BigDecimal.ZERO;//拒收数量合计
        
        for(PurchaseReceiveDetail purchaseReceiveDetail : purchaseReceiveDetails){
            PurchaseReceiveDetailVO purchaseReceiveDetailVO = new PurchaseReceiveDetailVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchaseReceiveDetail,purchaseReceiveDetailVO);
            purchaseReceiveDetailVO.setRefuseReasons(getRefuseReasons(purchaseReceiveDetailVO.getRefuseReasonIds(),loginUser));
            purchaseReceiveDetailVOS.add(purchaseReceiveDetailVO);
            
            arrivalQuantityTotal = arrivalQuantityTotal.add(purchaseReceiveDetail.getArrivalQuantity());
            receiveQuantityTotal = receiveQuantityTotal.add(purchaseReceiveDetail.getReceiveQuantity());
            refuseQuantityTotal = refuseQuantityTotal.add(purchaseReceiveDetail.getRefuseQuantity());
        }
        PurchaseReceiveRequestVO purchaseReceiveRequestVO = new PurchaseReceiveRequestVO();
        purchaseReceiveRequestVO.setPurchaseReceiveDetailVOS(purchaseReceiveDetailVOS);
        PurchaseReceiveVO purchaseReceiveVO = new PurchaseReceiveVO();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchaseReceiveMapper.selectByPrimaryKey(id),purchaseReceiveVO);
        
        purchaseReceiveVO.setArrivalQuantityTotal(arrivalQuantityTotal);
        purchaseReceiveVO.setReceiveQuantityTotal(receiveQuantityTotal);
        purchaseReceiveVO.setRefuseQuantityTotal(refuseQuantityTotal);
        purchaseReceiveVO.setEnterpriseName(loginUser.getEnterpriseName());
        
        purchaseReceiveRequestVO.setPurchaseReceiveVO(purchaseReceiveVO);
        return purchaseReceiveRequestVO;
    }

    //根据拒收原因id查找拒收原因
    @Override
    public String getRefuseReasons(String refuseReasonIds, UserVO loginUser) throws Exception {
        String refuseReasons = "";
        List<QualitySetVO> qualitySetVOS = qualitySetMapper.getQualitySetVOList(loginUser.getEnterpriseId(), 0, null, 1);
        if (refuseReasonIds == null || "".equals(refuseReasonIds))
            refuseReasons =  null;
        else if (!refuseReasonIds.contains(",")) {
            for (QualitySetVO qualitySetVO : qualitySetVOS) {
                if (qualitySetVO.getId().toString().equals(refuseReasonIds))
                    refuseReasons = qualitySetVO.getDescription();
            }
        } else {
            String[] ids = refuseReasonIds.split(",");
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < ids.length; i++) {
                for (QualitySetVO qualitySetVO : qualitySetVOS) {
                    if (qualitySetVO.getId().toString().equals(ids[i])) {
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
    public PurchaseReceiveOtherVO getReceiveSet(UserVO loginUser, Long id) throws Exception {
        PurchaseReceiveOtherVO purchaseReceiveOrderVO = new PurchaseReceiveOtherVO();
        PurchaseReceiveOther purchaseReceiveOrder = purchaseReceiveOtherMapper.selectByEnterpriseIdByReceiveId(loginUser.getEnterpriseId(),id);
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchaseReceiveOrder,purchaseReceiveOrderVO);
        if(purchaseReceiveOrderVO.getFileId()!=null && !"".equals(purchaseReceiveOrderVO.getFileId()))
            purchaseReceiveOrderVO.setFileName(fileMapper.selectByPrimaryKey(purchaseReceiveOrderVO.getFileId()).getFileName());
        return purchaseReceiveOrderVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveReceive(UserVO loginUser, SavePurchaseReceiveVO savePurchaseReceiveVO) throws Exception {
        //先保存收货总单
        PurchaseReceiveReturnVO purchaseReceiveReturnVO = savePurchaseReceiveVO.getPurchaseReceiveReturnVO();
        PurchaseReceive purchaseReceive = new PurchaseReceive();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchaseReceiveReturnVO,purchaseReceive);
        //set修改人,创建人,企业id信息--start
        UserEnterpriseUtils.setUserCreateOrModify(purchaseReceive,loginUser,true);
        purchaseReceive.setEnterpriseId(loginUser.getEnterpriseId());
        purchaseReceive.setParentId(loginUser.getParentId());
        //--end
        //set收货人信息--start
        User user = userMapper.selectByPrimaryKey(purchaseReceive.getReceiverId());
        purchaseReceive.setReceiverCode(user.getCode());
        purchaseReceive.setReceiverName(user.getName());
        if(purchaseReceive.getSecondReceiverId() != null){
            user = userMapper.selectByPrimaryKey(purchaseReceive.getSecondReceiverId());
            purchaseReceive.setSecondReceiverCode(user.getCode());
            purchaseReceive.setSecondReceiverName(user.getName());
        }
        //--end
        //set收货订单信息 基础单据,供应商信息,供应商销售人员信息--start
        PurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(purchaseReceive.getBaseOrderId());
        //收货日期不能小于订单日期
        Date orderDate = purchaseOrder.getOrderDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        if(purchaseReceive.getReceiveDate().before(calendar.getTime())){
            return "收货日期不能小于订单日期!";
        }
        //基础单据
        purchaseReceive.setBaseOrderCode(purchaseOrder.getCode());
        purchaseReceive.setBaseOrderType(purchaseOrder.getOrderType());
        purchaseReceive.setBaseOrderDate(purchaseOrder.getOrderDate());
        //供应商信息
        purchaseReceive.setSupplierId(purchaseOrder.getSupplierId());
        purchaseReceive.setSupplierCode(purchaseOrder.getSupplierCode());
        purchaseReceive.setSupplierName(purchaseOrder.getSupplierName());
        //供应商销售人员信息
        purchaseReceive.setSupplierSalerId(purchaseOrder.getSupplierSalerId());
        purchaseReceive.setSupplierSalerCode(purchaseOrder.getSupplierSalerCode());
        purchaseReceive.setSupplierSalerName(purchaseOrder.getSupplierSalerName());
        purchaseReceive.setSupplierSalerPhone(purchaseOrder.getSupplierSalerPhone());
        //--end
        //收货单号,单据类型
        purchaseReceive.setOrderType(OrderRule.PURCHASE_RECEIVE.getType());
        purchaseReceive.setStatus(PurchaseStatus.WAIT_CHECK.getStatus());
        purchaseReceive.setCode(getCode(OrderRule.PURCHASE_RECEIVE.getCodePrefix(),loginUser.getEnterpriseId(),loginUser.getEnterpriseCode()));
        Set goodsSet = new HashSet();//统计商品种类
        BigDecimal quantityTotal = BigDecimal.ZERO;
        for(PurchaseReceiveDetailReturnVO purchaseReceiveDetailReturnVO : savePurchaseReceiveVO.getPurchaseReceiveDetailReturnVOs()){
            quantityTotal = quantityTotal.add(purchaseReceiveDetailReturnVO.getReceiveQuantity());
            goodsSet.add(purchaseReceiveDetailReturnVO.getGoodsId());
            if(purchaseReceiveDetailReturnVO.getReceiveQuantity().compareTo(purchaseReceiveDetailReturnVO.getArrivalQuantity()) == 0){
                purchaseReceiveDetailReturnVO.setRefuseQuantity(BigDecimal.ZERO);
            }
        }
        purchaseReceive.setQuantityTotal(quantityTotal);
        purchaseReceive.setVarietiesQuantity(goodsSet.size());
        purchaseReceiveMapper.insertSelective(purchaseReceive);
        //更新采购订单状态
        purchaseOrder.setStatus(PurchaseStatus.WAIT_CHECK.getStatus());
        //保存收货明细

        //如果gsp不管控,,到货数量可修改,要保持订单数量跟到货数量一致,修改订单细单的数量跟订单总单的数量合计
        //为了保证金额精度,统一在细单算金额,总单金额修改为细单金额合计,总单不单另计算
        List<PurchaseOrderDetail> purchaseOrderDetails = saveDetial(loginUser,purchaseOrder,purchaseReceive,savePurchaseReceiveVO.getPurchaseReceiveDetailReturnVOs());
        purchaseOrderMapper.updateByPrimaryKeySelective(purchaseOrder);//修改订单总单状态,如果gsp不管控,修改其他信息
        for(PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetails){
            purchaseOrderDetailMapper.updateByPrimaryKeySelective(purchaseOrderDetail);
        }
        //保存收货配送跟结算
        PurchaseReceiveOther purchaseReceiveOther = new PurchaseReceiveOther();
        CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(savePurchaseReceiveVO.getPurchaseReceiveOtherRetrunVO(),purchaseReceiveOther);
        UserEnterpriseUtils.setUserCreateOrModify(purchaseReceiveOther,loginUser,true);
        purchaseReceiveOther.setEnterpriseId(loginUser.getEnterpriseId());
        purchaseReceiveOther.setParentId(loginUser.getParentId());
        purchaseReceiveOther.setStatus(PurchaseStatus.WAIT_CHECK.getStatus());
        purchaseReceiveOther.setReceiveId(purchaseReceive.getId());
        purchaseReceiveOther.setOrderId(purchaseReceive.getBaseOrderId());
        purchaseReceiveOther.setOrderCode(purchaseReceive.getBaseOrderCode());
        purchaseReceiveOtherMapper.insertSelective(purchaseReceiveOther);
        //更新采购订单配送结算状态
        PurchaseOrderOther purchaseOrderOther = purchaseOrderOtherMapper.selectByEnterpriseIdByOrderId(loginUser.getEnterpriseId(),purchaseReceive.getBaseOrderId());
        purchaseOrderOther.setStatus(PurchaseStatus.WAIT_CHECK.getStatus());
        purchaseOrderOtherMapper.updateByPrimaryKeySelective(purchaseOrderOther);
        //再次检查细单收货数量,如果收货数量为0,则状态应该改为已完成,如果所有细单的收货数量都为0,则总单状态也更新为已完成
        checkStatus(purchaseOrder,purchaseOrderDetails,purchaseReceive,purchaseReceiveOther,purchaseOrderOther,loginUser);
        return "";
    }

    private void checkStatus(PurchaseOrder purchaseOrder, List<PurchaseOrderDetail> purchaseOrderDetails, PurchaseReceive purchaseReceive, PurchaseReceiveOther purchaseReceiveOther, PurchaseOrderOther purchaseOrderOther, UserVO loginUser) throws Exception{
        int dtlCount = 0;//细单收货数量为0的条数
        List<PurchaseReceiveDetail> purchaseReceiveDetails = purchaseReceiveDetailMapper.selectByEnterpriseIdByReceiveId(loginUser.getEnterpriseId(),purchaseReceive.getId());
        for(PurchaseReceiveDetail purchaseReceiveDetail : purchaseReceiveDetails){
            if(purchaseReceiveDetail.getReceiveQuantity().compareTo(BigDecimal.ZERO) <= 0){
                dtlCount++;
                //更新细单的状态
                purchaseReceiveDetail.setStatus(PurchaseStatus.FINISHED.getStatus());
                purchaseReceiveDetailMapper.updateByPrimaryKeySelective(purchaseReceiveDetail);
                //更新订单细单的状态
                purchaseOrderDetailMapper.updateStatusFinishedById(PurchaseStatus.FINISHED.getStatus(),loginUser.getEnterpriseId(),purchaseReceiveDetail.getBaseOrderDtlId());
            }
        }
        if(dtlCount==purchaseReceiveDetails.size()){
            //更新总单的状态
            purchaseReceive.setStatus(PurchaseStatus.FINISHED.getStatus());
            purchaseReceiveMapper.updateByPrimaryKeySelective(purchaseReceive);
            purchaseReceiveOther.setStatus(PurchaseStatus.FINISHED.getStatus());
            purchaseReceiveOtherMapper.updateByPrimaryKeySelective(purchaseReceiveOther);
            purchaseOrderOther.setStatus(PurchaseStatus.FINISHED.getStatus());
            purchaseOrderOtherMapper.updateByPrimaryKey(purchaseOrderOther);
            purchaseOrder.setStatus(PurchaseStatus.FINISHED.getStatus());
            purchaseOrderMapper.updateByPrimaryKeySelective(purchaseOrder);
        }
    }

    private List<PurchaseOrderDetail> saveDetial(UserVO loginUser, PurchaseOrder purchaseOrder, PurchaseReceive purchaseReceive, List<PurchaseReceiveDetailReturnVO> purchaseReceiveDetailReturnVOs) throws Exception {
        //收货明细集合
        List<PurchaseReceiveDetail> purchaseReceiveDetails = new ArrayList<>();
        //订单明细集合
        List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailMapper.selectByEnterpriseIdByOrderId(loginUser.getEnterpriseId(),purchaseReceive.getBaseOrderId());
        for(PurchaseReceiveDetailReturnVO purchaseReceiveDetailReturnVO : purchaseReceiveDetailReturnVOs){
            PurchaseReceiveDetail purchaseReceiveDetail = new PurchaseReceiveDetail();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchaseReceiveDetailReturnVO,purchaseReceiveDetail);
            BigDecimal amountTotal=new BigDecimal(0);//金额合计
            for(int i=0;i<purchaseOrderDetails.size();i++){//计算合计
                //计算金额合计
                BigDecimal amount=CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(purchaseOrderDetails.get(i).getQuantity(), purchaseOrderDetails.get(i).getUnitPrice(), purchaseOrderDetails.get(i).getLineDiscount());
                amountTotal=amountTotal.add(amount);
            }
            BigDecimal notaxProfitTotal=BigDecimal.ZERO;//不含税总额
            BigDecimal taxAmountTotal=BigDecimal.ZERO;//税额
            boolean orderFlag = false;//是否修改订单
            for(PurchaseOrderDetail purchaseOrderDetail : purchaseOrderDetails){
                if(purchaseReceiveDetailReturnVO.getBaseOrderDtlId().equals(purchaseOrderDetail.getId())){
                    //set基础单据信息
                    purchaseReceiveDetail.setBaseOrderCode(purchaseOrderDetail.getOrderCode());
                    purchaseReceiveDetail.setBaseOrderDate(purchaseOrderDetail.getOrderDate());
                    purchaseReceiveDetail.setBaseOrderType(purchaseOrderDetail.getOrderType());
                    purchaseReceiveDetail.setBaseOrderDtlId(purchaseOrderDetail.getId());
                    //set商品信息
                    Goods goods = goodsMapper.selectByPrimaryKey(purchaseOrderDetail.getGoodsId());
                    if(goods.getSpecialDrug() != -1 && purchaseReceive.getSecondReceiverId() == null){
                        throw new BusinessException("第"+purchaseOrderDetail.getLineNum()+"行的"+goods.getGenericName()+"为特管商品,必须有第二收货人!");
                    }
                    purchaseReceiveDetail.setGoodsName(goods.getName());
                    purchaseReceiveDetail.setGoodsCode(goods.getCode());
                    purchaseReceiveDetail.setBarcode(goods.getBarcode());
                    purchaseReceiveDetail.setGoodsGenericName(goods.getGenericName());
                    purchaseReceiveDetail.setDosageId(goods.getDosageId());
                    purchaseReceiveDetail.setDosageName(goods.getDosageName());
                    purchaseReceiveDetail.setUnitId(goods.getUnitId());
                    purchaseReceiveDetail.setUnitName(goods.getUnitName());
                    purchaseReceiveDetail.setGoodsSpecification(goods.getSpecification());
                    purchaseReceiveDetail.setManufacturerId(goods.getManufacturerId());
                    purchaseReceiveDetail.setManufacturer(goods.getManufacturer());
                    purchaseReceiveDetail.setGoodsPlace(goods.getPlace());
                    purchaseReceiveDetail.setApprovalNumber(goods.getApprovalNumber());
                    //set状态
                    purchaseReceiveDetail.setStatus(PurchaseStatus.WAIT_CHECK.getStatus());
                    //set未清数量为收货数量/以清数量为0
                    purchaseReceiveDetail.setUnclearQuantity(purchaseReceiveDetail.getReceiveQuantity());
                    purchaseReceiveDetail.setClearQuantity(BigDecimal.ZERO);
                    //set收货单据id/code/类型/日期
                    purchaseReceiveDetail.setReceiveId(purchaseReceive.getId());
                    purchaseReceiveDetail.setReceiveCode(purchaseReceive.getCode());
                    purchaseReceiveDetail.setOrderType(OrderRule.PURCHASE_RECEIVE.getType());
                    purchaseReceiveDetail.setReceiveDate(purchaseReceive.getReceiveDate());
                    //如果到货数量被修改,则订单的数量也要变,金额同时也变
                    if(purchaseReceiveDetail.getArrivalQuantity().compareTo(purchaseOrderDetail.getQuantity()) != 0){
                        orderFlag = true;
                        purchaseOrderDetail.setQuantity(purchaseReceiveDetail.getArrivalQuantity());
                        //根据数量、单价、行折扣获取金额（整单折扣金额）：数量*单价*行折扣
                        BigDecimal amount=CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(purchaseOrderDetail.getQuantity(), purchaseOrderDetail.getUnitPrice(), purchaseOrderDetail.getLineDiscount());
                        BigDecimal lineDiscountAmount = BigDecimal.ZERO;
                        if(!amount.equals(new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP))){
                            lineDiscountAmount=purchaseOrder.getWholeDiscountAmount().multiply(amount.divide(amountTotal,2,BigDecimal.ROUND_HALF_UP));
                        }
                        //计算实际金额，根据数量、单价、行折扣、整单折扣、行舍入获取实际金额：数量*单价*行折扣*整单折扣-行舍入
                        BigDecimal realAmount=CalculateComponent.getRealAmountByQuantityAndPriceAndLineDiscount(purchaseOrderDetail.getQuantity(),purchaseOrderDetail.getUnitPrice(),purchaseOrderDetail.getLineDiscount(),purchaseOrder.getWholeDiscount(),lineDiscountAmount);
                        //计算实际单价   ：实际金额/数量
                        BigDecimal realPrice =CalculateComponent.getRealPriceByRealAmountAndQuantity(realAmount, purchaseOrderDetail.getQuantity());
                        //获取不含税金额
                        BigDecimal val1=new BigDecimal(1);
                        BigDecimal notaxRealAmount= realAmount.divide(val1.add(purchaseOrderDetail.getTaxRate().multiply(new BigDecimal(0.01))),2,BigDecimal.ROUND_HALF_UP);
                        //获取税额   实际金额-不含税金额
                        BigDecimal taxAmount=realAmount.subtract(notaxRealAmount);
                        purchaseOrderDetail.setTaxAmount(taxAmount);
                        purchaseOrderDetail.setNotaxRealAmount(notaxRealAmount);
                        purchaseOrderDetail.setRealAmount(realAmount);
                        purchaseOrderDetail.setRealPrice(realPrice);
                        //计算订单详情里面的金额
                        purchaseOrderDetail.setAmount(amount);
                        //行优惠（行舍入）优惠分摊    计算优惠分摊
                        purchaseOrderDetail.setWholeDiscount(purchaseOrder.getWholeDiscount());//整单折扣
                        purchaseOrderDetail.setLineDiscountAmount(lineDiscountAmount);

                        notaxProfitTotal=notaxProfitTotal.add(notaxRealAmount);//不含税金额总额
                        taxAmountTotal=taxAmountTotal.add(taxAmount);
                        purchaseOrderDetail.setAmount(purchaseOrderDetail.getQuantity().multiply(purchaseOrderDetail.getUnitPrice()).setScale(2,BigDecimal.ROUND_HALF_UP));
                       // purchaseOrderDetail.setWholeDiscount(CalculateComponent.getAmountByQuantityAndPriceAndLineDiscount(purchaseOrderDetail.getQuantity(),purchaseOrderDetail.getUnitPrice(),purchaseOrderDetail.getLineDiscount()));
                    }
                    //更新未清数量为0，已清数量为数量
                    purchaseOrderDetail.setUnclearQuantity(BigDecimal.ZERO);
                    purchaseOrderDetail.setClearQuantity(purchaseOrderDetail.getQuantity());
                }
                //更新状态
                purchaseOrderDetail.setStatus(PurchaseStatus.WAIT_CHECK.getStatus());
            }
            //实际金额合计
            BigDecimal realAmountTotal =amountTotal.multiply(purchaseOrder.getWholeDiscount()).subtract(purchaseOrder.getWholeDiscountAmount());
            //不含税总额
            //税额
            if(orderFlag) {
                purchaseOrder.setAmountTotal(amountTotal);//总额（优惠前金额合计）
                purchaseOrder.setRealAmountTotal(realAmountTotal);//实际金额合计
                purchaseOrder.setTaxAmountTotal(taxAmountTotal);
            }
            purchaseReceiveDetail.setEnterpriseId(loginUser.getEnterpriseId());
            purchaseReceiveDetail.setParentId(loginUser.getParentId());
            UserEnterpriseUtils.setUserCreateOrModify(purchaseReceiveDetail,loginUser,true);
            purchaseReceiveDetail.setStatus(PurchaseStatus.WAIT_CHECK.getStatus());
            purchaseReceiveDetails.add(purchaseReceiveDetail);
        }
        purchaseReceiveDetailMapper.batchInsert(purchaseReceiveDetails);
        return purchaseOrderDetails;
    }


    //获取采购订单单号
    private String getCode(String codePrefix,Long enterpriseId,String enterpriseCode) throws Exception {
        return orderCodeComponent.generate(codePrefix,enterpriseId,enterpriseCode);
    }

    @Override
    public List<SimpleUserVO> getReceiver(UserVO loginUser) throws Exception {
        return userMapper.getSimpleUserVOByEnterpriseId(loginUser.getEnterpriseId(),1);
    }

    @Override
    public List<QualitySetVO> getRefuseReason(UserVO loginUser) throws Exception {
        List<QualitySetVO> qualitySetVOS = qualitySetMapper.getQualitySetVOList(loginUser.getEnterpriseId(),0,null,1);
//        List<Map<Long,String>> resultList = new ArrayList<>();
//        for(QualitySetVO qualitySetVO : qualitySetVOS){
//            Map<Long,String> map = new HashMap<>();
//            map.put(qualitySetVO.getId(),qualitySetVO.getDescription());
//            resultList.add(map);
//        }
        return qualitySetVOS;
    }

    @Override
    public Map<String, String> getReceiveUnit(UserVO loginUser) throws Exception {
        List<Enterprise> enterprises = enterpriseMapper.selectChildrenByPrimaryKey(loginUser.getEnterpriseId());
        Map<String, String> resultMap = new HashMap<>();
        for (Enterprise enterprise : enterprises){
            resultMap.put(enterprise.getName(),enterprise.getCompanyAddress());
        }
        return resultMap;
    }

    @Override
    public void exportExcel(OutputStream output, PurchaseReceiveRequestVO purchaseReceiveRequestVO, UserVO loginUser) {
        //转换一下显示日期
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("goodsCode","商品编码");
        map.put("goodsName","通用名称");
        map.put("dosageName","剂型");
        map.put("goodsSpecification","规格");
        map.put("manufacturer","生产厂商");
        map.put("unitName","单位");
        map.put("arrivalQuantity","到货数量");
        map.put("receiveQuantity","收货数量");
        map.put("refuseQuantity","拒收数量");
        map.put("refuseReasons","拒收原因");
        List<String> titleSecond = new ArrayList<>();
        //标题栏下第一行
        StringBuilder titleSecondRow = new StringBuilder();
        titleSecondRow.append("供货单位编码:");
        titleSecondRow.append(purchaseReceiveRequestVO.getPurchaseReceiveVO().getSupplierCode() == null ? "":purchaseReceiveRequestVO.getPurchaseReceiveVO().getSupplierCode());
            titleSecondRow.append("  供货单位名称:");
        titleSecondRow.append(purchaseReceiveRequestVO.getPurchaseReceiveVO().getSupplierName() == null ? "":purchaseReceiveRequestVO.getPurchaseReceiveVO().getSupplierName());
        titleSecondRow.append("  供货单位销售人员:");
        titleSecondRow.append(purchaseReceiveRequestVO.getPurchaseReceiveVO().getSupplierSalerName() == null ? "":purchaseReceiveRequestVO.getPurchaseReceiveVO().getSupplierSalerName());
        titleSecondRow.append("  联系电话:");
        titleSecondRow.append(purchaseReceiveRequestVO.getPurchaseReceiveVO().getSupplierSalerPhone() == null ? "":purchaseReceiveRequestVO.getPurchaseReceiveVO().getSupplierSalerPhone());
        titleSecond.add(titleSecondRow.toString());
        //标题栏下第二行
        titleSecondRow = new StringBuilder();
        titleSecondRow.append("  收货单号:");
        titleSecondRow.append(purchaseReceiveRequestVO.getPurchaseReceiveVO().getCode() == null ? "":purchaseReceiveRequestVO.getPurchaseReceiveVO().getCode());
        titleSecondRow.append("  收货日期:");
        titleSecondRow.append(purchaseReceiveRequestVO.getPurchaseReceiveVO().getReceiveDate() == null ? "":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(purchaseReceiveRequestVO.getPurchaseReceiveVO().getReceiveDate()));
        titleSecondRow.append("  收货人员1:");
        titleSecondRow.append(purchaseReceiveRequestVO.getPurchaseReceiveVO().getReceiverName() == null ? "":purchaseReceiveRequestVO.getPurchaseReceiveVO().getReceiverName());
        titleSecondRow.append("  收货人员2:");
        titleSecondRow.append(purchaseReceiveRequestVO.getPurchaseReceiveVO().getSecondReceiverName() == null ? "":purchaseReceiveRequestVO.getPurchaseReceiveVO().getSecondReceiverName());
        titleSecond.add(titleSecondRow.toString());
        StringBuilder end = new StringBuilder();
        BigDecimal arrivalQuantity = BigDecimal.ZERO;//到货数量
        BigDecimal receiveQuantity = BigDecimal.ZERO;//收货数量
        BigDecimal refuseQuantity = BigDecimal.ZERO;//拒收数量
        for (PurchaseReceiveDetailVO purchaseReceiveDetailVO : purchaseReceiveRequestVO.getPurchaseReceiveDetailVOS()) {
            arrivalQuantity = arrivalQuantity.add(purchaseReceiveDetailVO.getArrivalQuantity());
            receiveQuantity = receiveQuantity.add(purchaseReceiveDetailVO.getReceiveQuantity());
            refuseQuantity = receiveQuantity.add(purchaseReceiveDetailVO.getRefuseQuantity());
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
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(purchaseReceiveMapper.selectByPrimaryKey(purchaseReceiveRequestVO.getPurchaseReceiveVO().getId()).getEnterpriseId());
        name.add(enterprise.getName());
        name.add("采购入库单");
        purchaseGeneralComponent.commExcelExport(output,map,purchaseReceiveRequestVO.getPurchaseReceiveDetailVOS(),name,titleSecond,end.toString(),false,needTotalName);
    }

    @Override
    public boolean checkReceived(UserVO loginUser, SavePurchaseReceiveVO savePurchaseReceiveVO) {
        Long baseOrderId = savePurchaseReceiveVO.getPurchaseReceiveReturnVO().getBaseOrderId();
        List<PurchaseReceive> purchaseReceives = purchaseReceiveMapper.selectByEnterpriseIdBybaseOrderId(loginUser.getEnterpriseId(),baseOrderId);
        return purchaseReceives.size()>0;
    }

    /**
     * 获取已收货的单据
     * @param param
     * @return
     */
    private Page getReceived(Map param,Page page,Boolean gspFlag) throws Exception {
        List<PurchaseReceiveVO> purchaseReceiveVOS = new ArrayList<>();//总单细单集合
        List<PurchaseReceive> purchaseReceives = purchaseReceiveMapper.getReceived(param);
        for (PurchaseReceive purchaseReceive : purchaseReceives){
            PurchaseReceiveVO purchaseReceiveVO = new PurchaseReceiveVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchaseReceive,purchaseReceiveVO);
            purchaseReceiveVO.setStatusName(PurchaseStatus.getName(purchaseReceiveVO.getStatus()));
            PurchaseReceiveOther receiveOther = purchaseReceiveOtherMapper.selectByEnterpriseIdByReceiveId((Long) param.get("enterpriseId"), purchaseReceive.getId());
            if(receiveOther != null){
                purchaseReceiveVO.setCarriageMode(receiveOther.getCarriageMode());
                purchaseReceiveVO.setCarriageModeName(DistributionType.getName(receiveOther.getCarriageMode()));
            }
            PurchaseOrderOther purchaseOrderOther = purchaseOrderOtherMapper.selectByEnterpriseIdByOrderId((Long) param.get("enterpriseId"), purchaseReceive.getBaseOrderId());
            if(purchaseOrderOther == null){
                throw new BusinessException("无效的采购订单ID：" + purchaseReceive.getBaseOrderId());
            }
            Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(purchaseOrderOther.getSettlementUnitId());
            if(enterprise.getChainType() == ChainType.Division.getCode()){
                purchaseReceiveVO.setFranchisedStoreFlag(1);
            }else{
                purchaseReceiveVO.setFranchisedStoreFlag(0);
            }


            purchaseReceiveVOS.add(purchaseReceiveVO);//添加总单
        }
        page.setResult(purchaseReceiveVOS);
        param.replace("pageSize",null);
        param.replace("start",null);
        page.setTotalRecord(purchaseReceiveMapper.getReceivedCount(param));
        return page;
    }

    /**
     * 获取未收货的单据
     * @param param
     * @return
     */
    private Page getWillReceive(Map param,Page page,Boolean gspFlag) throws Exception {
        List<PurchaseReceiveOrderVO> purchaseReceiveOrderVOS = new ArrayList<>();
        List<PurchaseOrder> purchaseOrders = purchaseOrderMapper.getWillReceive(param);
        for(PurchaseOrder purchaseOrder : purchaseOrders){
            PurchaseReceiveOrderVO purchaseReceiveOrderVO = new PurchaseReceiveOrderVO();
            CopyFromNotNullBeanUtils.copyPropertiesIgnoreNull(purchaseOrder,purchaseReceiveOrderVO);
            purchaseReceiveOrderVO.setStatusName(PurchaseStatus.getName(purchaseReceiveOrderVO.getStatus()));
            purchaseReceiveOrderVO.setGspFlag(gspFlag);
            purchaseReceiveOrderVO.setCarriageMode(purchaseOrderOtherMapper.selectByEnterpriseIdByOrderId((Long)param.get("enterpriseId"),purchaseReceiveOrderVO.getId()).getCarriageMode());
            purchaseReceiveOrderVO.setCarriageModeName(DistributionType.getName(purchaseReceiveOrderVO.getCarriageMode()));
            purchaseReceiveOrderVOS.add(purchaseReceiveOrderVO);
        }
        page.setResult(purchaseReceiveOrderVOS);
        param.replace("pageSize",null);
        param.replace("start",null);
        page.setTotalRecord(purchaseOrderMapper.getWillReceiveCount(param));
        return page;
    }

    /**
     * 是否质量控制
     * @param loginUser
     * @return
     * @throws Exception
     */
    private Boolean getGspFlag(UserVO loginUser) throws Exception {
        ManageConfig manageConfig = manageConfigMapper.selectByCurrentUser(loginUser);
        return manageConfig.getBusinessControl()==0?false:true;
    }
}
