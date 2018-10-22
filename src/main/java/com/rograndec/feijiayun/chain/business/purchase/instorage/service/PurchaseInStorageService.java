package com.rograndec.feijiayun.chain.business.purchase.instorage.service;

import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.HasBeenInstorageFormVO;
import com.rograndec.feijiayun.chain.business.purchase.instorage.vo.StayInstorageSaveVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;

public interface PurchaseInStorageService {
    Page getHasBeenInstoragePage(Page page, Date startDate, Date endDate, String supplierCode, String supplierName, String code, String storageManName, String order, String sort, Long enterpriseId);

    HasBeenInstorageFormVO getHasBeenInstorageForm(Long enterpriseId, Long id);

    void exportExcel(OutputStream output, HasBeenInstorageFormVO hasBeenInstorageFormVO, UserVO loginUser);

    StayInstorageSaveVO getHasBeenInstorage(Long enterpriseId, Long id);
}
