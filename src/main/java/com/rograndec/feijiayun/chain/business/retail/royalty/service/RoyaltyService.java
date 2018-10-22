package com.rograndec.feijiayun.chain.business.retail.royalty.service;

import com.rograndec.feijiayun.chain.business.retail.royalty.entity.SaleRoyalty;
import com.rograndec.feijiayun.chain.business.retail.royalty.vo.*;
import com.rograndec.feijiayun.chain.business.retail.saleflow.vo.ResponseSaleMan;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaiwei on 2017/9/25.
 */
public interface RoyaltyService {
    ResponseSaleRoyaltyVO getHasSaleRoyalty(UserVO userVO, Long royaltyId);


    ResponseSaleTotalVO getHasSaleRoyaltys(UserVO userVO, Page page, Date startTime, Date endTime);

    ResponseSaleTotalVO getHasSaleRoyaltys(UserVO userVO, Page page, Date startTime , Date endTime, Long id);

    void exportExcel(OutputStream output, UserVO userVO,Long id);

    List<ResponseSaleRoyaltyTotalVO> getHasSaleRoyaltyTotals(SaleRoyalty saleRoyalty);

    void saveRoyaltys(
            List<SaveOrUpdateSaleRoyaltyVO> saveOrUpdateSaleRoyaltyVOs
            , UserVO userVO

    ) throws Exception;

    List<ResponseNoSaleRoyaltyTotalVO> getNoRoyaltys(
            Integer chainType
            , String code
            , String name
            , String clerkId
            , Date startTime
            , Date endTime
            , UserVO userVO
    );

    List<ResponseSaleMan> getSaleMans(Map<String, Object> map);
}
