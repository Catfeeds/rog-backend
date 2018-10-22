package com.rograndec.feijiayun.chain.business.storage.maintance.service;

import com.rograndec.feijiayun.chain.business.basic.warehouse.vo.WarehouseAreaVO;
import com.rograndec.feijiayun.chain.business.storage.maintance.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.vo.QualitySetVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;

public interface GoodsMaintanceService {
    List<WarehouseAreaVO> getWarehouseAreaList(UserVO loginUser) throws Exception;

    List<QualitySetVO> getMaintanceMeasuresList(UserVO loginUser, Integer setType, Integer type) throws Exception;

    List<SelectMaintanceGoodsVO> getGoodsStockList(UserVO loginUser, String param, Long warehouseAreaId, Integer maintanceType, Integer goodsType, Integer type) throws Exception;

    int cancelMaintanceOrder(UserVO loginUser, Long id) throws Exception;

    Page<List<ShowGoodsMaintanceVO>> getMaintanceOrderList(UserVO loginUser, Integer maintanceOrderType, String orderName, String orderType, Date startDate, Date endDate, Long warehouseAreaId, Integer maintanceType, Integer goodsType, String code, Integer pageSize, Integer pageNo) throws Exception;

    List<GoodsMaintanceDetailVO> getMaintanceOrderDtlList(UserVO loginUser, String ids) throws Exception;

    int saveMaintanceOrder(UserVO loginUser, GoodsMaintacneInfoVO goodsMaintacneInfoVO) throws Exception;

    int finishMaintanceOrder(UserVO loginUser, List<GoodsMaintanceDetailVO> goodsMaintanceDetailVOS) throws Exception;

    Page<List<ShowMaintanceVO>> getWillMaintanceOrderList(UserVO loginUser, String orderName, String orderType, Date startDate, Date endDate, Long warehouseAreaId, Integer maintanceType, Integer goodsType, Integer pageNo, Integer pageSize) throws Exception;

    void exportExcel(OutputStream output, UserVO loginUser, Long id) throws Exception;

	List<WarehouseAreaVO> getWarehouseAreaListByEnterpriseId(Long enterpriseId);
}

