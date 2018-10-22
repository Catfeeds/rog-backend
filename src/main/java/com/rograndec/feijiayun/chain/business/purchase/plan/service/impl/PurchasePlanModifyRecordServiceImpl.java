package com.rograndec.feijiayun.chain.business.purchase.plan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.business.purchase.plan.dao.PurchasePlanModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.purchase.plan.service.PurchasePlanModifyRecordService;
import com.rograndec.feijiayun.chain.business.purchase.plan.vo.PurchasePlanModifyRecordVO;
import com.rograndec.feijiayun.chain.common.Page;

@Service
public class PurchasePlanModifyRecordServiceImpl implements PurchasePlanModifyRecordService {

	@Autowired
	private PurchasePlanModifyRecordMapper recordMapper;

	@Override
	public Page<List<PurchasePlanModifyRecordVO>> getModifyRecordPage(Page<List<PurchasePlanModifyRecordVO>> page, Long planId) {

		com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(), page.getPageSize());

		List<PurchasePlanModifyRecordVO> recordVOs = recordMapper.getRecordPageByPlanID(planId);

		page.setResult(recordVOs);
		page.setTotalRecord(Integer.parseInt(hPage.getTotal() + ""));
		page.setTotalPage(hPage.getPages());
		return page;
	}

}
