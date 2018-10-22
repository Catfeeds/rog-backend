package com.rograndec.feijiayun.chain.business.storage.inventory.flow;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserAdministrationMapper;
import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.basic.user.entity.UserAdministration;
import com.rograndec.feijiayun.chain.business.keytable.dao.StockMapper;
import com.rograndec.feijiayun.chain.business.storage.inventory.dao.InventoryDetailMapper;
import com.rograndec.feijiayun.chain.business.storage.inventory.dao.InventoryMapper;
import com.rograndec.feijiayun.chain.business.storage.inventory.dao.InventoryShelfMapper;
import com.rograndec.feijiayun.chain.business.storage.inventory.entity.Inventory;
import com.rograndec.feijiayun.chain.business.storage.inventory.entity.InventoryShelf;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatus;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.component.InOutComponent;
import com.rograndec.feijiayun.chain.common.component.OrderModelBuilder;
import com.rograndec.feijiayun.chain.common.constant.InventoryStatus;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.PubStatus;
import com.rograndec.feijiayun.chain.common.model.ApprovalFlowPostProcessor;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 功能描述：
 * Created by ST on 2017/10/27 17:34
 */
@Service
public class InventoryApprovalFlowPostProcessor implements ApprovalFlowPostProcessor {
    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private InventoryDetailMapper inventoryDetailMapper;

    @Autowired
    private InventoryShelfMapper inventoryShelfMapper;

    @Autowired
    private EnterpriseMapper enterpriseMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAdministrationMapper userAdministrationMapper;

    @Autowired
    private InOutComponent inOutComponent;

    @Autowired
    private StockMapper stockMapper;


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
    public void afterAudit(Long id, Integer status,Integer approvalStatus) throws Exception {
        //更新盘点单的审批状态
         if(ApprovalFlowAuditStatus.DETAIL_STATUS_PASS.getValue() == approvalStatus){
             Inventory inventory = inventoryMapper.selectByPrimaryKey(id);
             if(inventory == null) throw new BusinessException("盘点单不存在！");
             Long enterpriseId = inventory.getEnterpriseId();
             Long modifierId = inventory.getModifierId();
             List<InventoryShelf> inventoryShelfList =  inventoryShelfMapper.getIdInvIdDtlId(id,inventory.getEnterpriseId());
             if(!CollectionUtils.isEmpty(inventoryShelfList)){
                 Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
                 User user = userMapper.selectByPrimaryKey(modifierId);
                 UserAdministration userAdministration = userAdministrationMapper.selectByUserId(modifierId);
                 UserVO userVO  = UserVO.getUserVO(enterprise, user, userAdministration);

                 OrderModelBuilder builder = new OrderModelBuilder(userVO);
                 OrderModel orderModel = builder.buildOrderModel(OrderRule.INVENTORY, inventory,inventoryShelfList);
                 inOutComponent.generateKeyTableDatas(OrderDirection.INVENTORY, orderModel);
             }
             //更新商品的库存状态为正常
             updateStockGoodsInventoryStatus(enterpriseId, inventory.getId(), InventoryStatus.NORMAL.getStatus());

             //盘点单审批通过,更改盘点单的状态为待过账
             inventoryMapper.updateInventory(id, PubStatus.inventoryOrderStatus.FINISHED);
             inventoryDetailMapper.updateStatusByInvId(id,PubStatus.inventoryOrderStatus.FINISHED);
             inventoryShelfMapper.updateStatusByInvId(id,PubStatus.inventoryOrderStatus.FINISHED);
         } else {
             //审核拒绝
             inventoryMapper.updateInventory(id, PubStatus.inventoryOrderStatus.AUDIT_REJECT);
             inventoryDetailMapper.updateStatusByInvId(id,PubStatus.inventoryOrderStatus.AUDIT_REJECT);
             inventoryShelfMapper.updateStatusByInvId(id,PubStatus.inventoryOrderStatus.AUDIT_REJECT);
         }
    }

    private void updateStockGoodsInventoryStatus(Long enterpriseId, Long inventoryId, Integer inventoryStatus) {
        List<InventoryShelf> shelfList = inventoryShelfMapper.getInventoryShelfByParam(enterpriseId, null, inventoryId, null,"asc");
        shelfList.forEach(item->{
            //更新库存盘点状态
            stockMapper.updateStockInventoryStatus(enterpriseId,item.getGoodsId(),item.getLotId(),item.getShelfId(), inventoryStatus);
        });
    }
}