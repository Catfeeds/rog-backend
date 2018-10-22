package com.rograndec.feijiayun.chain.business.goods.price.approvalProcessor;

import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceAdjustDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceAdjustMapper;
import com.rograndec.feijiayun.chain.business.goods.price.dao.PriceOrderDetailMapper;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceAdjustDetail;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceAdjust;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrderDetail;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.approval.dao.ApprovalFlowActionMapper;
import com.rograndec.feijiayun.chain.business.system.approval.entity.ApprovalFlowAction;
import com.rograndec.feijiayun.chain.common.constant.status.PurchaseStatus;
import com.rograndec.feijiayun.chain.common.model.ApprovalFlowPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaiwei on 2017/9/19.
 */
@Service
public class PriceApprovalProcessor implements ApprovalFlowPostProcessor{

    @Autowired
    private PriceAdjustMapper priceAdjustMapper;

    @Autowired
    private PriceAdjustDetailMapper priceAdjustDetailMapper;

    @Autowired
    private ApprovalFlowActionMapper approvalFlowActionMapper;

    @Autowired
    private PriceOrderDetailMapper priceOrderDetailMapper;

    @Override
    public void afterCancel(String content,Long dataId,Long eId) {
        Map<String,Object> map = new HashMap<>();
        map.put("content",content);
        map.put("dataId",dataId);
        map.put("eId",eId);
        ApprovalFlowAction approvalFlowAction = approvalFlowActionMapper.selectByDataId(map);
        PriceAdjust priceAdjust = priceAdjustMapper.selectByPrimaryKey(approvalFlowAction.getDataId());
        List<PriceAdjustDetail> priceAdjustDetails = priceAdjustDetailMapper.selectByPriceAdjustId(approvalFlowAction.getDataId());

        PriceAdjust newPriceAjust = new PriceAdjust();
        newPriceAjust.setStatus(PurchaseStatus.CANCELED.getStatus());
        newPriceAjust.setId(priceAdjust.getId());
        newPriceAjust.setModifierId(priceAdjust.getModifierId());
        newPriceAjust.setModifierCode(priceAdjust.getModifierCode());
        newPriceAjust.setModifierName(priceAdjust.getModifierName());
        newPriceAjust.setUpdateTime(new Date());
        priceAdjustMapper.updateByPrimaryKeySelective(newPriceAjust);

        for(PriceAdjustDetail priceAdjustDetail : priceAdjustDetails){
            PriceAdjustDetail newPriceAdjustDetail = new PriceAdjustDetail();
            newPriceAdjustDetail.setId(priceAdjustDetail.getId());
            newPriceAdjustDetail.setStatus(PurchaseStatus.CANCELED.getStatus());

            newPriceAdjustDetail.setModifierId(priceAdjust.getModifierId());
            newPriceAdjustDetail.setModifierCode(priceAdjust.getModifierCode());
            newPriceAdjustDetail.setModifierName(priceAdjust.getModifierName());
            newPriceAdjustDetail.setUpdateTime(new Date());

            priceAdjustDetailMapper.updateByPrimaryKeySelective(newPriceAdjustDetail);

        }
    }

    @Override
    public void afterReapply(String content,Long dataId) {

    }

    @Override
    public void afterApply(String content,Long dataId) {

    }

    @Override
    public void afterReapply() {

    }

    @Override
    public void afterApply() {

    }

    @Override
    public void afterCancel() {

    }

    @Override
    public void afterAudit(Long id, Integer status,Integer approvalStatus){

    if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue())) {
        PriceAdjust priceAdjust = priceAdjustMapper.selectByPrimaryKey(id);

        PriceAdjust newPriceAdjust = new PriceAdjust();
        newPriceAdjust.setId(priceAdjust.getId());
        newPriceAdjust.setStatus(status);

        priceAdjustMapper.updateByPrimaryKeySelective(newPriceAdjust);


        /**
         * 价格调整完成需要更新价格清单中的价格
         */
        List<PriceOrderDetail> priceOrderDetails = priceOrderDetailMapper.selectByPriceOrderId(priceAdjust.getPriceOrderId());

        List<PriceAdjustDetail> priceAdjustDetails = priceAdjustDetailMapper.selectByPriceAdjustId(priceAdjust.getId());

        for(PriceAdjustDetail priceAdjustDetail : priceAdjustDetails){

            for(PriceOrderDetail priceOrderDetail : priceOrderDetails){

                if(priceAdjustDetail.getGoodsId().equals(priceOrderDetail.getGoodsId())){

                    PriceOrderDetail newPriceOrderDetail = PriceOrderDetail.setPriceOrderDetail4AdjustDetail(priceOrderDetail, priceAdjustDetail);
                    newPriceOrderDetail.setPriceOrderId(priceOrderDetail.getPriceOrderId());
                    priceOrderDetailMapper.updateByPrimaryKeySelective(newPriceOrderDetail);
                }

            }

        }


    }else if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue())){

        //审核驳回
        PriceAdjust priceAdjust = priceAdjustMapper.selectByPrimaryKey(id);

        PriceAdjust newPriceAdjust = new PriceAdjust();
        newPriceAdjust.setId(priceAdjust.getId());
        newPriceAdjust.setStatus(PurchaseStatus.AUDIT_WITHDRAWAL.getStatus());
    }else if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_IN.getValue())){

        //审核中
        PriceAdjust priceAdjust = priceAdjustMapper.selectByPrimaryKey(id);

        PriceAdjust newPriceAdjust = new PriceAdjust();
        newPriceAdjust.setId(priceAdjust.getId());
        newPriceAdjust.setStatus(PurchaseStatus.PENDING_AUDIT.getStatus());
    }




    }
}
