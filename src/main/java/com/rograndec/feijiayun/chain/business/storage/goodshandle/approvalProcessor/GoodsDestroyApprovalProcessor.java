package com.rograndec.feijiayun.chain.business.storage.goodshandle.approvalProcessor;

import com.rograndec.feijiayun.chain.business.basic.user.dao.UserMapper;
import com.rograndec.feijiayun.chain.business.basic.user.entity.User;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.GoodsDestroyMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.dao.GoodsDestroyShelfMapper;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsDestroy;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.entity.GoodsDestroyShelf;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.business.system.enterprise.dao.EnterpriseMapper;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.common.component.InOutComponent;
import com.rograndec.feijiayun.chain.common.component.OrderModelBuilder;
import com.rograndec.feijiayun.chain.common.component.RedisComponent;
import com.rograndec.feijiayun.chain.common.constant.OrderDirection;
import com.rograndec.feijiayun.chain.common.constant.OrderRule;
import com.rograndec.feijiayun.chain.common.constant.status.GoodsDestroyStatus;
import com.rograndec.feijiayun.chain.common.model.ApprovalFlowPostProcessor;
import com.rograndec.feijiayun.chain.common.model.OrderModel;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by 孙帮祥 on 2017/11/10.
 */
@Service
public class GoodsDestroyApprovalProcessor implements ApprovalFlowPostProcessor{


	@Autowired
	private GoodsDestroyMapper goodsDestroyMapper;
	@Autowired
	private GoodsDestroyShelfMapper goodsDestroyShelfMapper;
    @Autowired
    private RedisComponent redisComponent;
    @Autowired
    private InOutComponent inOutComponent;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EnterpriseMapper enterpriseMapper;
	@Override
	public void afterReapply(String content, Long dataId) {

	}

	/**
	 * 取消成功后更新为取消状态
	 */
	@Override
	public void afterCancel(String content, Long dataId, Long eId) {


	}

	@Override
	public void afterApply(String content, Long dataId) {
	
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
	/**
	 * @author 孙帮祥
	 * @param status
	 */
	@Override
	public void afterAudit(Long id, Integer status,Integer approvalStatus) {//审批之后的任何操作都调用

		GoodsDestroy  destroy = goodsDestroyMapper.selectByPrimaryKey(id);

		if(null == destroy){
			return;
		}

		if(approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue())) {

			destroy.setStatus(GoodsDestroyStatus.FINISHED);
			//审核通过之后处理商品销毁逻辑
			UserVO userVO=new UserVO();

			User user=userMapper.selectByPrimaryKey(id);
			userVO.setUserId(user.getId());
			userVO.setUserCode(user.getCode());
			userVO.setUserName(user.getName());
			Enterprise enterprise=enterpriseMapper.selectByPrimaryKey(user.getEnterpriseId());
			userVO.setEnterpriseId(enterprise.getId());
			userVO.setEnterpriseCode(enterprise.getCode());
			userVO.setEnterpriseName(enterprise.getName());
			userVO.setChainType(enterprise.getChainType());
			userVO.setParentId(enterprise.getParentId());
			OrderModelBuilder builder_ = new OrderModelBuilder(userVO);
			List<GoodsDestroyShelf> goodsDestroyShelfList=goodsDestroyShelfMapper.selectByDestroyId(id);
	 		OrderModel orderModel_ = builder_.buildOrderModel(OrderRule.DESTROY, destroy, goodsDestroyShelfList);
	 		try {
				inOutComponent.generateKeyTableDatas(OrderDirection.OUT, orderModel_);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else  if (approvalStatus.equals(ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_UNPASS.getValue())){
			destroy.setStatus(GoodsDestroyStatus.AUDIT_REJECT);
			goodsDestroyMapper.updateByPrimaryKeySelective(destroy);
		}
		goodsDestroyMapper.updateByPrimaryKeySelective(destroy);
	}
}
