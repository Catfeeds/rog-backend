package com.rograndec.feijiayun.chain.business.purchase.retout.service;

import com.rograndec.feijiayun.chain.business.keytable.vo.StockShelfVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.RequestPurchaseReturnParamVO;
import com.rograndec.feijiayun.chain.business.purchase.ret.vo.ResponsePurchaseReturnDetailVO;
import com.rograndec.feijiayun.chain.business.purchase.retout.vo.*;
import com.rograndec.feijiayun.chain.business.system.set.entity.ManageConfig;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.List;

/**
 * 功能描述：够进退出 出库
 * Created by ST on 2017/9/15 13:21
 */
public interface PurchaseReturnOutService {

    /**
     * 查询待出库的数据
     * @param paramVO
     * @param page
     * @return
     */
    Page<List<ResponsePurchaseReturnOutVO>> getReturnOutListForOutBound(RequestPurchaseReturnParamVO paramVO, Page<List<ResponsePurchaseReturnOutVO>> page);

    /**
     * 查询待出库的购进退出
     * @param id
     * @return
     */
    ResponsePurchaseReturnDetailVO getPurchaseReturnDetail(Long id,UserVO userVO);

    /**
     * 查询已出库待复核的数据、已完成复核的数据
     * @param paramVO
     * @param page
     * @return
     */
    Page<List<ResponsePurchaseReturnOutVO>> getReturnOutListByParam(UserVO userVO, RequestPurchaseReturnParamVO paramVO, Page<List<ResponsePurchaseReturnOutVO>> page);

    /**
     * 查询已出库待复核的数据、已完成复核的的购进退出出库详情
     * @param id
     * @return
     */
    ResponsePurchaseReturnOutDetailVO getPurchaseReturnOutDetail(Long id);

    /**
     * 查询货位
     * @param enterpriseId
     * @param goodsId
     * @param lotNum
     * @return
     */
    List<StockShelfVO> getShelfByLotNum(Long enterpriseId, Long goodsId, String lotNum);

    /**
     * 出库
     * @param purchaseReturnOutInfoVO
     * @param userVO
     */
    void updateOutStock(RequestPurchaseReturnOutInfoVO purchaseReturnOutInfoVO, UserVO userVO) throws Exception;

    /**
     * 复核
     * @param requestCheckVO
     */
    void updateReCheck(RequestCheckVO requestCheckVO,UserVO userVO) throws Exception;

    /**
     * 导出
     * @param output
     * @param id
     */
    void exportExcel(OutputStream output,Long id);


    void savePurchaseReturnOut(PurchaseReturnOutInfoVO purchaseReturnOutInfoVO, UserVO loginUser) throws Exception;

    ManageConfig getDefaultReturnMan(UserVO loginUser) throws Exception;

    PurchaseReturnOutInfoVO getPurchaseReturnOutFromInStorage(UserVO loginUser, List<Long> inStorageDtlS) throws Exception;
}