package com.rograndec.feijiayun.chain.business.basic.supplier.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.rograndec.feijiayun.chain.common.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rograndec.feijiayun.chain.business.basic.supplier.dao.SupplierModifyRecordMapper;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierModifyRecord;
import com.rograndec.feijiayun.chain.business.basic.supplier.service.SupplierModifyRecordService;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierModifyRecordVO;
import com.rograndec.feijiayun.chain.common.constant.ChainType;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

@Service
public class SupplierModifyRecordServiceImpl implements SupplierModifyRecordService {

	@Autowired
	private SupplierModifyRecordMapper supplierModifyRecordMapper;
	
	@Override
	@Transactional
	public Page getSupplierRecord(Page page, UserVO user, Long supplierId) {
		List<SupplierModifyRecord> supplierModifyRecord = new ArrayList<SupplierModifyRecord>();
		com.github.pagehelper.Page hPage = PageHelper.startPage(page.getPageNo(),page.getPageSize());
		supplierModifyRecord = supplierModifyRecordMapper.selectBySupplierIdAndUserId(supplierId,user.getEnterpriseId());
		List<SupplierModifyRecordVO> vo = new ArrayList<SupplierModifyRecordVO>();
		if (supplierModifyRecord != null){
			for (SupplierModifyRecord s : supplierModifyRecord) {
				SupplierModifyRecordVO supplierModifyRecordVO = new SupplierModifyRecordVO();
				supplierModifyRecordVO = SupplierModifyRecordVO.changeRecordToVo(s,user);
				vo.add(supplierModifyRecordVO);
			}
		}
		page.setResult(vo);
		page.setTotalRecord(Integer.parseInt(hPage.getTotal()+""));
		page.setTotalPage(hPage.getPages());
		return page;
	}

}
