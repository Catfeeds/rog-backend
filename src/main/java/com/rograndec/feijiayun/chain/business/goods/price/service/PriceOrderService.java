package com.rograndec.feijiayun.chain.business.goods.price.service;

import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceModifyRecordWithBLOBs;
import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceOrder;
import com.rograndec.feijiayun.chain.business.goods.price.vo.*;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.QueryBean;
import com.rograndec.feijiayun.chain.common.vo.UserVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaiwei on 2017/9/6.
 */
public interface PriceOrderService {
    void savePriceOrder(UserVO userVO, AddOrUpdatePriceOrderVO addOrUpdatePriceOrderVO) throws Exception;

//    public abstract void saveOrUpdatePriceOrderDetail(UserVO userVO, UpdatePriceOrderDetailVO updatePriceOrderDetailVO
//            , boolean isAdd) throws Exception;

     void updatePriceOrder(UserVO userVO, AddOrUpdatePriceOrderVO addOrUpdatePriceOrderVO) throws Exception;

    /*void testTh();*/

    void savePriceOrderDetail(UserVO userVO, PriceOrder priceOrder, boolean isAdd) throws Exception;

    PriceOrderInitVO init();

    List<PriceOrderReturnVO> queryPriceOrders(UserVO userVO , Integer queryType,Page page,String order,String sort) throws Exception;

    Page<PriceOrderReturnVO> queryPriceOrderDetails(Long priceOrderId,UserVO userVO,Page<PriceOrderReturnVO> page) throws Exception;



    void  queryPriceOrderDetails4Param(Long priceOrderId, String param, UserVO userVO, Page<List<PriceOrderDetailReturnVO>> page);

    void deletePriceOrder(Long priceOrderId);

    List<QueryBean> queryPriceOrderIdAndNameList(Long eId,Integer type,Long id);

    List<PriceModifyRecordWithBLOBs> getPriceModifyRecordWithBLOBs(Map<String, Object> map, Page page);

    void queryPriceOrderDetails2SelectOrderDetails(Long oldPriceOrderId, Long newPriceOrderId,UserVO userVO,Page<List<PriceOrderDetailReturnVO>> page);

    List<PriceOrderDetailReturnVO> queryPriceOrderDetailsSetUpdateFlag(UserVO userVO,List<PriceOrderDetailReturnVO> details);

    Page<PriceOrderReturnVO> getPriceOrderDetail(UserVO userVO,Page<PriceOrderReturnVO> page) throws Exception;
    
    void excelExport(OutputStream output, UserVO userVO, Long id, PriceOrderReturnVO priceOrderReturnVO,Integer sign);
    
    PriceOrderReturnVO excelExportDate (UserVO userVO, Long id,Integer sign) throws Exception;

    ResponsePriceOrderExcelVO excelImport(UserVO userVO, Long priceOrderId, MultipartFile file) throws Exception;

    PriceOrder queryPriceOrder4Id(Long id);

    void excelDisQualtfiedExport(OutputStream output, PriceOrder priceOrder, UserVO userVO, String key,Integer type);

    void importPriceOrder(UserVO userVO, Long priceOrderId, String redisKey) throws Exception;
}
