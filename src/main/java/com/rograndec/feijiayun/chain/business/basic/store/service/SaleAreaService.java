package com.rograndec.feijiayun.chain.business.basic.store.service;

import com.rograndec.feijiayun.chain.business.basic.store.entity.SaleArea;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreVO;
import com.rograndec.feijiayun.chain.business.system.enterprise.entity.Enterprise;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by madong on 2017/8/28.
 */
public interface SaleAreaService {
    List<TreePOJO> getSaleArea(Long enterpriseId) throws Exception;

    List<StoreVO> getShops(Long id, Long enterpriseId) throws Exception;

    int deleteSaleArea(Long id, Long enterpriseId) throws Exception;

    int removeSaleAreaShop(Long id, Long enterpriseId, Long shopId) throws Exception;

    int saveSaleArea(SaleArea saleArea, UserVO loginUser) throws Exception;

    int saveSaleAreaShops(List<Enterprise> shops, UserVO loginUser) throws Exception;

    int updateSaleArea(SaleArea saleArea, UserVO loginUser) throws Exception;

    int updateSaleAreaShops(Long id, List<Long> shopIds, UserVO loginUser) throws Exception;

    void excelExport(UserVO loginUser, OutputStream output) throws Exception ;

    List<SaleArea> getSaleAreaParent(Long enterpriseId);

    boolean checkCodeExists(UserVO loginUser, SaleArea saleArea) throws Exception;

    boolean canDelete(UserVO loginUser, Long id) throws Exception;

}
