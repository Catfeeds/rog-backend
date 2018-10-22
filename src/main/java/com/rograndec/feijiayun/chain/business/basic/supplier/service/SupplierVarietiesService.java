package com.rograndec.feijiayun.chain.business.basic.supplier.service;

import com.rograndec.feijiayun.chain.business.basic.supplier.entity.Supplier;
import com.rograndec.feijiayun.chain.business.basic.supplier.entity.SupplierVarieties;
import com.rograndec.feijiayun.chain.business.basic.supplier.vo.SupplierVarietiesVO;
import com.rograndec.feijiayun.chain.business.purchase.order.vo.GoodsOrderVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by madong on 2017/8/31.
 */
public interface SupplierVarietiesService {
    List<Map> getSupplier(Long enterpriseId) throws Exception;

    int saveSupplierVarieties(UserVO loginUser, List<SupplierVarieties> supplierVarieties) throws Exception;

    int updateSupplierVarietie(UserVO loginUser, SupplierVarieties supplierVarietie) throws Exception;

    boolean isHappenedBusiness(Long id, Long goodsId, Long supplierId, UserVO loginUser) throws Exception;

    int deleteSupplierVarietie(Long id,UserVO loginUser) throws Exception;

    void excelExport(UserVO loginUser, OutputStream output, String supplierInfo, String goodsInfo) throws Exception;

    Page getSupplierVarieties(UserVO userVO, String supplierInfo, String goodsInfo, String orderName, String orderType, Integer pageNo, Integer pageSize);

    Page<List<GoodsOrderVO>> getSupplierVarietiesGoods(UserVO userVO, Long supplierId, Integer pageNo, Integer pageSize, String param);
}
