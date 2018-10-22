package com.rograndec.feijiayun.chain.business.init.model;

import com.rograndec.feijiayun.chain.business.basic.store.approvalProcessor.StoreApprovalProcessor;
import com.rograndec.feijiayun.chain.business.basic.supplier.flow.SupplierApprovalFlowPostProcessor;
import com.rograndec.feijiayun.chain.business.basic.user.approvalProcessor.UserApprovalProcessor;
import com.rograndec.feijiayun.chain.business.distr.branch.approvalProcessor.DistrInNoticeApprovalProcessor;
import com.rograndec.feijiayun.chain.business.distr.branch.approvalProcessor.DistrReqPlanApprovalProcessor;
import com.rograndec.feijiayun.chain.business.distr.branch.approvalProcessor.ReturnInApprovalProcessor;
import com.rograndec.feijiayun.chain.business.distr.parent.approvalProcessor.DistrSendApprovalProcessor;
import com.rograndec.feijiayun.chain.business.goods.info.flow.GoodsApprovalFlowPostProcessor;
import com.rograndec.feijiayun.chain.business.goods.price.approvalProcessor.PriceApprovalProcessor;
import com.rograndec.feijiayun.chain.business.purchase.order.approvalProcessor.PurchaseOrderApprovalProcessor;
import com.rograndec.feijiayun.chain.business.purchase.plan.approvalProcessor.PurchasePlanApprovalProcessor;
import com.rograndec.feijiayun.chain.business.purchase.ret.approvalProcessor.ReturnApprovalProcessor;
import com.rograndec.feijiayun.chain.business.storage.goodshandle.approvalProcessor.GoodsDestroyApprovalProcessor;
import com.rograndec.feijiayun.chain.business.storage.inventory.flow.InventoryApprovalFlowPostProcessor;
import com.rograndec.feijiayun.chain.business.storage.move.flow.OtherInApprovalFlowPostProcessor;
import com.rograndec.feijiayun.chain.business.system.approval.constant.ApprovalFlowAuditStatusRecom;
import com.rograndec.feijiayun.chain.common.constant.EnableStatus;
import com.rograndec.feijiayun.chain.common.constant.status.*;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: ApprovalFlowContentModel
 * @Description: 审批流内容初始化数据模型
 * @author zhongyi.li zhongyi.li@rograndec.com
 * @date 2017年9月8日 下午3:59:23
 *
 */
public class ApprovalFlowContentModel {

	private String contentId;// 审批内容ID
	private String contentPid;// 审批内容PID（如果为根节点，该字段为0）
	private String name;// 审批内容名称
	private Integer sort;// 排序字段
	private Integer level;// 级别
	private Integer isLeaf;// 是否叶子节点（0-是 1-否）
	private Integer status;

	public ApprovalFlowContentModel() {
	}

	public ApprovalFlowContentModel(String contentId, String contentPid, String name, Integer sort, Integer level, Integer isLeaf,
			Integer status) {
		super();
		this.contentId = contentId;
		this.contentPid = contentPid;
		this.name = name;
		this.sort = sort;
		this.level = level;
		this.isLeaf = isLeaf;
		this.status = status;
	}

	public static List<ApprovalFlowContentModel> build() {
		List<ApprovalFlowContentModel> afList = new ArrayList<ApprovalFlowContentModel>();
		afList.add(new ApprovalFlowContentModel("01", "0", "基础资料", 1, 1, 0, 1));
		afList.add(new ApprovalFlowContentModel("0101", "01", "供货单位", 1, 2, 1, 1));
		afList.add(new ApprovalFlowContentModel("0102", "01", "员工信息", 2, 2, 1, 1));
		afList.add(new ApprovalFlowContentModel("0103", "01", "门店信息", 3, 2, 1, 1));

		afList.add(new ApprovalFlowContentModel("02", "0", "商品管理", 2, 1, 0, 1));
		afList.add(new ApprovalFlowContentModel("0201", "0", "商品信息", 1, 2, 1, 1));
		afList.add(new ApprovalFlowContentModel("0202", "0", "价格调整", 2, 2, 1, 1));

		afList.add(new ApprovalFlowContentModel("03", "0", "采购管理", 3, 1, 0, 1));
		afList.add(new ApprovalFlowContentModel("0301", "0", "采购计划", 1, 2, 1, 1));
		afList.add(new ApprovalFlowContentModel("0302", "0", "采购订单", 2, 2, 1, 1));
		afList.add(new ApprovalFlowContentModel("0303", "0", "购进退出", 3, 2, 1, 1));

		afList.add(new ApprovalFlowContentModel("04", "0", "库存管理", 4, 1, 0, 1));
		afList.add(new ApprovalFlowContentModel("0401", "0", "盘点", 1, 2, 1, 1));
		afList.add(new ApprovalFlowContentModel("0402", "0", "其他入库", 2, 2, 1, 1));
		afList.add(new ApprovalFlowContentModel("0403", "0", "药品销毁", 3, 2, 1, 1));

		afList.add(new ApprovalFlowContentModel("05", "0", "配送管理", 5, 1, 0, 1));
		afList.add(new ApprovalFlowContentModel("0501", "0", "要货计划", 1, 2, 1, 1));
		afList.add(new ApprovalFlowContentModel("0502", "0", "配货单", 2, 2, 1, 1));
		afList.add(new ApprovalFlowContentModel("0503", "0", "配进订单", 3, 2, 1, 1));
		afList.add(new ApprovalFlowContentModel("0504", "0", "配进退出", 4, 2, 1, 1));
		return afList;
	}

	public static String getPriceAdjustCode() {
		return "0202";
	}

	public static String getReturnCode() {
		return "0303";
	}

    public static String getDistrSendCode() {
        return "0502";
    }

    public static String getDisInReturnCode() {
		return "0504";
	}

	public static String getDistrInNoticeCode() {
		return "0503";
	}


	public static String getPurchasePlanCode() {
		return "0301";
	}
	public static String getPurchaseOrderCode() {
		return "0302";
	}
	public static String getDistrReqPlanCode() {
		return "0501";
	}
	public static String getGoodsDestroyCode() {
		return "0403";
	}
	public static Class<T> getProcessor(String code) {
		if ("0202".equals(code)) {
			Class clazz = PriceApprovalProcessor.class;
			return clazz;
		} else if ("0301".equals(code)) {// 采购计划
			Class clazz = PurchasePlanApprovalProcessor.class;
			return clazz;
		}else if ("0302".equals(code)) {// 采购订单
			Class clazz = PurchaseOrderApprovalProcessor.class;
			return clazz;
		}else if ("0501".equals(code)) {// 要货计划
			Class clazz = DistrReqPlanApprovalProcessor.class;
			return clazz;
		}else if ("0403".equals(code)) {// 药品销毁
			Class clazz = GoodsDestroyApprovalProcessor.class;
			return clazz;
		}else if ("0303".equals(code)) {// 购进退出
			Class clazz = ReturnApprovalProcessor.class;
			return clazz;
		} else if ("0502".equals(code)) {//配货单
		    Class clazz = DistrSendApprovalProcessor.class;
		    return clazz;
		} else if ("0503".equals(code)) {//配进订单
			Class clazz = DistrInNoticeApprovalProcessor.class;
			return clazz;
        }else if("0504".equals(code)){//配进退出
        	Class clazz = ReturnInApprovalProcessor.class;
		    return clazz;
        }else if("0201".equals(code)){//商品信息处理
			Class clazz = GoodsApprovalFlowPostProcessor.class;
			return clazz;
		}else if ("0101".equals(code)){
			Class clazz = SupplierApprovalFlowPostProcessor.class;
			return clazz;
		} else if("0401".equals(code)){//盘点审核
			Class clazz = InventoryApprovalFlowPostProcessor.class;
			return clazz;
		} else if("0402".equals(code)){//其他入库
			Class clazz = OtherInApprovalFlowPostProcessor.class;
			return clazz;
		}else if("0102".equals(code)){//员工信息
			Class clazz = UserApprovalProcessor.class;
			return clazz;
		}else if("0103".equals(code)){//门店信息
			Class clazz = StoreApprovalProcessor.class;
			return clazz;
		}
		return null;
	}

	public static Integer getOverOrderStatus(String code) {
		if ("0202".equals(code)) {
			return PurchaseStatus.FINISHED.getStatus();
		}else if ("0301".equals(code)) {// 采购计划
			return PurchaseStatus.PENDING_ORDER.getStatus();
		}else if ("0302".equals(code)) {// 采购订单
			return PurchaseStatus.WAIT_RECEIVE.getStatus();
		}else if ("0302".equals(code)) {// 采购订单
			return PurchaseStatus.WAIT_RECEIVE.getStatus();
		}else if ("0501".equals(code)) {//要货计划
			return DistrReqPlanStatus.WAIT_DISTR;
		}else if ("0403".equals(code)) {//药品销毁
			return GoodsDestroyStatus.FINISHED;
		}else if ("0303".equals(code)) {// 购进退出
			return PurchaseStatus.WAIT_OUT.getStatus();
		} else if ("0502".equals(code)) {//配货单
		    return DistrSendStatus.WAIT_PICK;
		} else if ("0503".equals(code)) {//配进订单
			return DistrInStatus.WAIT_RECEIVE;
        }else if("0504".equals(code)){//配进退出
        	return DistrInReturnStatus.WAIT_OUT;
        }else if("0201".equals(code)){//商品信息审成功的状态
        	return EnableStatus.ENABLE.getStatus();
		}else if("0401".equals(code)){//盘点审核成功的状态
			return EnableStatus.ENABLE.getStatus();
		}else if("0101".equals(code)){
			return EnableStatus.ENABLE.getStatus();
		}else if("0402".equals(code)){
			return EnableStatus.ENABLE.getStatus();
		}else if("0102".equals(code)){
			return ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue();
		}else if("0103".equals(code)){
			return ApprovalFlowAuditStatusRecom.ACTION_STATUS_RECOM_PASS.getValue();
		}
		return null;
	}


	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getContentPid() {
		return contentPid;
	}

	public void setContentPid(String contentPid) {
		this.contentPid = contentPid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
