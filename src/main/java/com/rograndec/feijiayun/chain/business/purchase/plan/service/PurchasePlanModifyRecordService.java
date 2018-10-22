package com.rograndec.feijiayun.chain.business.purchase.plan.service;

import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanModifyRecordVO;
import com.rograndec.feijiayun.chain.common.Page;

import java.util.List;

public interface PurchasePlanModifyRecordService {

	Page<List<PurchasePlanModifyRecordVO>> getModifyRecordPage(Page<List<PurchasePlanModifyRecordVO>> page, Long planId);

}
