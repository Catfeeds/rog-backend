package com.rograndec.feijiayun.chain.business.goods.price.service;

import com.rograndec.feijiayun.chain.business.goods.price.entity.PriceAdjustDetail;
import com.rograndec.feijiayun.chain.business.goods.price.vo.AddOrUpdatePriceAdjustVO;
import com.rograndec.feijiayun.chain.business.goods.price.vo.PriceAdjustGoodsVO;
import com.rograndec.feijiayun.chain.business.goods.price.vo.PriceAdjustReturnVO;
import com.rograndec.feijiayun.chain.common.Page;
import com.rograndec.feijiayun.chain.common.QueryBean;
import com.rograndec.feijiayun.chain.common.vo.UserVO;

import java.util.List;
import java.util.Map;

/**
 * Created by zhaiwei on 2017/9/6.
 */
public interface PriceAdjustService {

//    List<PriceAdjustGoodsVO> queryPriceAdjustGoodsVOs4PriceOrder(Long priceOrderId, UserVO userVO, String param, Page page);
//
List<PriceAdjustGoodsVO> queryPriceAdjustGoodsVOs4PriceOrder(UserVO userVO,Long priceOrderId, String param, Page page);

    List<PriceAdjustGoodsVO>  queryPriceAdjustGoodsVOs4Adjust(Long adjustId,UserVO userVO);

    List<QueryBean> queryPriceOrderIdAndNameList(Long eId,Integer type,Long id);

    PriceAdjustDetail queryAdjust(Long adjustId, Long goodsId, Long eId);

    void savePriceAdjusts(UserVO userVO, AddOrUpdatePriceAdjustVO addOrUpdatePriceAdjustVO, boolean isAdd) throws Exception;

    void cancelPriceAdjust(Long actionflowId);

    List<PriceAdjustReturnVO> queryPriceAdjust4Param(Map<String, Object> map,Page page);

    /**
     * 获取价格调整 总单数据
     * @param adjustId
     * @return
     */
    PriceAdjustReturnVO getPriceAdjustReturnVOById(Long adjustId);
}
