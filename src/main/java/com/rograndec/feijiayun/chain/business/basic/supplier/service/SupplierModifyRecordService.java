package com.rograndec.feijiayun.chain.business.basic.supplier.service;

import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

public interface SupplierModifyRecordService {

	Page getSupplierRecord(Page page, UserVO user, Long supplierId);

}
