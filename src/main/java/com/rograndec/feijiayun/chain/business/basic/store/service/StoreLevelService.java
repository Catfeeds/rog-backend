package com.rograndec.feijiayun.chain.business.basic.store.service;

import com.rograndec.feijiayun.chain.business.basic.store.entity.StoreLevel;
import com.rograndec.feijiayun.chain.business.basic.store.vo.StoreVO;
import com.rograndec.feijiayun.chain.business.system.set.entity.TreePOJO;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

/**
 * Created by madong on 2017/8/28.
 */
public interface StoreLevelService {

    List<TreePOJO> getAllStoreLevel(Long enterpriseId) throws Exception;

    int insertStoreLevel(UserVO loginUser, StoreLevel storeLevel) throws Exception;

    int saveStoreLevelShops(UserVO loginUser, Long id, List<Long> shopIds) throws Exception;

    List<StoreVO> getNoChooseShops(Long enterpriseId) throws Exception;

    int updateStoreLevel(UserVO loginUser, StoreLevel storeLevel) throws Exception;

    int removeStoreLevelShop(UserVO loginUser, Long id,Long shopId) throws Exception;

    boolean canDelete(UserVO loginUser, Long id) throws Exception;

    int deleteStoreLevel(Long id) throws Exception;

    int removeAllStoreLevelShop(UserVO loginUser, Long id) throws Exception;

    void excelExport(UserVO loginUser, OutputStream output) throws Exception;

    boolean checkCodeExists(UserVO loginUser, StoreLevel storeLevel) throws Exception;
}
