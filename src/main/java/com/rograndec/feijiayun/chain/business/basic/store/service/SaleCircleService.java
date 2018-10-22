package com.rograndec.feijiayun.chain.business.basic.store.service;

import com.rograndec.feijiayun.chain.business.basic.store.entity.SaleCircle;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

/**
 * Created by madong on 2017/8/28.
 */
public interface SaleCircleService {

    List<TreePOJO> getAllSaleCircle(Long enterpriseId) throws Exception;

    int insertSaleCircle(UserVO loginUser, SaleCircle saleCircle) throws Exception;

    int saveSaleCircleShops(UserVO loginUser, Long id, List<Long> shopIds) throws Exception;

    List<StoreVO> getNoChooseShops(Long enterpriseId) throws Exception;

    int updateSaleCircle(UserVO loginUser, SaleCircle saleCircle) throws Exception;

    int removeSaleCircleShop(UserVO loginUser, Long id,Long shopId) throws Exception;

    boolean canDelete(UserVO loginUser, Long id) throws Exception;

    int deleteSaleCircle(Long id) throws Exception;

    int removeAllSaleCircleShop(UserVO loginUser, Long id);

    void excelExport(UserVO loginUser, OutputStream output) throws Exception ;

    boolean checkCodeExists(UserVO loginUser, SaleCircle saleCircle) throws Exception;
}
