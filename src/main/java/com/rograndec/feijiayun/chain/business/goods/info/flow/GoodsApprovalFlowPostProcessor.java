package com.rograndec.feijiayun.chain.business.goods.info.flow;

import com.rograndec.feijiayun.chain.business.goods.info.dao.GoodsMapper;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatus;
import com.rograndec.feijiayun.chain.common.model.ApprovalFlowPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能描述：
 * Created by ST on 2017/10/27 17:34
 */
@Service
public class GoodsApprovalFlowPostProcessor implements ApprovalFlowPostProcessor {

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 后置处理,取消处理方法
     *
     * @param content
     * @param dataId
     */
    @Override
    public void afterApply(String content, Long dataId) {

    }

    /**
     * 执行完重新发起审批,后处理逻辑
     *
     * @param content
     * @param dataId
     */
    @Override
    public void afterReapply(String content, Long dataId) {

    }

    /**
     * 执行完发起审批,后处理逻辑
     *
     * @param content
     * @param dataId
     * @param eId
     */
    @Override
    public void afterCancel(String content, Long dataId, Long eId) {

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
    public void afterAudit(Long id, Integer status,Integer approvalStatus) {
        //更新商品信息的审批状态
         if(ApprovalFlowAuditStatus.DETAIL_STATUS_PASS.getValue() == approvalStatus){
             goodsMapper.updateGoodsStatus(id, null,approvalStatus);
         } else {
             goodsMapper.updateGoodsStatus(id, null,approvalStatus);
         }
    }
}