package com.rograndec.feijiayun.chain.business.online.purchase.smart.service;

import com.rograndec.feijiayun.chain.business.online.purchase.smart.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import com.rograndec.feijiayun.chain.inf.search.vo.SearchMphGroupSupplierGoodsResult;
import com.rograndec.feijiayun.chain.inf.search.vo.SearchMphSupplierGoodsResult;

import java.util.Date;
import java.util.List;

public interface OnlineMarketService {
    
    OnlineMarketVO selectOnlineMarket(UserVO userVO, int pageNo, int pageSize, String searchValues);

    SelectSmartEntrepriseVO getOnlineMarket(SearchMphGroupSupplierGoodsResult searchMphGroupSupplierGoodsResult,Page page);

    GetReceiptDataVO geteceiptRData(String returnJsonData, Long orderId, String orderCode, String supplyName, UserVO loginUser, Date oaddTime) throws Exception;

    void insertCart(UserVO loginUser, SelectSmartSupplierVO supplierVO) throws Exception;

    String getRedisKey(UserVO loginUser) throws Exception;

    SelectSmartEntrepriseVO getOnlineMarketAll(UserVO loginUser, SearchMphSupplierGoodsResult searchMphSupplierGoodsResult, Long gcName1Type,Long gcName2Type,Long manufacturerType,Long dosageNamesType) throws Exception;

    String reward(GetReceiptDataVO getReceiptDataVO, UserVO loginUser) throws Exception;

    Page boundGoods(Page page, UserVO loginUser, String mphGoodsName, String mphGoodsManufacturer, Long cargoAreaId) throws InterruptedException;

    void unBoundGoods(UserVO loginUser, String mphGoodsId, Long goodsId) throws Exception;

    void saveBoundGoods(UserVO loginUser, SaveBoundGoodsVO saveBoundGoods) throws Exception;

    List<SelectBoundSupplyVO> boundSupply(UserVO loginUser, String mphSupplyName);

    void unBoundSupply(UserVO loginUser, String mphSupplyId, Long supplyId) throws Exception;

    void saveBoundSupply(UserVO loginUser, SaveBoundSupplyVO saveBoundSupply) throws Exception;

    void updateOrder(GetReceiptDataVO getReceiptDataVO, UserVO loginUser) throws Exception;

	SelectBoundGoodsVO getGoodsDefaultShelt(UserVO loginUser, Long goodsId);
}
