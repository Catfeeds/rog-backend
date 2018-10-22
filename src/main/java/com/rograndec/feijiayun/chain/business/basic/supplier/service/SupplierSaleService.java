package com.rograndec.feijiayun.chain.business.basic.supplier.service;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierModifyRecord;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierSaler;
import com.rograndec.feijiayun.chain.business.basic.supplier.exception.SupplierBizException;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierSalerReturnVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

public interface SupplierSaleService {

	void addSale(SupplierSaler supplierSaler);


    void updateSale(SupplierSaler supplierSaler, UserVO userVO) throws Exception;

    void deleteSale(Long id, UserVO userVO) throws SupplierBizException;

    List<SupplierModifyRecord> findSupplierModifyRecords(Long id,Page page);

    void excelExport4User(OutputStream output, List<SupplierSalerReturnVO> supplierSalers);

    List<SupplierSaler> findSalerBySuppliereId(Long eId);

    List<SupplierSalerReturnVO> findSalers(UserVO userVO, Integer queryType, String queryStr);

    List<SupplierSalerReturnVO> findSalers(UserVO userVO, Integer queryType, String queryStr, Page page,String order,String sort);

    List<SupplierSaler> getTransport(Long id);


}
